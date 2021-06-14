package es.loyola.iitv.ptv.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import es.loyola.iitv.ptv.Connection.ConsultasMongoDB;
import es.loyola.iitv.ptv.Connection.ManagerMongoDB;
import es.loyola.iitv.ptv.DAO.User;

@WebServlet(urlPatterns="/registro")
public class RegistroServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, NullPointerException {
		resp.setHeader("Cache-Control", "no-cache");
		resp.setHeader("Cache-Contorl", "no-store");
		resp.setContentType("application/json");
		PrintWriter writer= resp.getWriter();
		
		JSONObject respuesta= new JSONObject();
		JSONObject result= new JSONObject();
		JSONObject session= new JSONObject();
		
		String request= req.getParameter("request");
		System.out.println(request);

		User usuario= null;
		
		try {
			if(request != null) {
				usuario= new User(request);

				User userlogin= ConsultasMongoDB.getLoginData(usuario.getEmail());
				User userUsers= ConsultasMongoDB.getUserData(usuario.getEmail());

				if (userlogin == null && userUsers == null){
					Document newlogindata= new Document("email", usuario.getEmail());
					if(!usuario.getPassword().isEmpty()) {
						newlogindata.append("Password", usuario.getPassword());
					}else {
						throw new NullPointerException("password vacio");
					}
					//De momento todo nuevo usuario es un alumno de forma predeterminada
					newlogindata.append("Rol", "Alumno");
					newlogindata.append("Token", usuario.generarToken());
					//TODO cambiar las fechas
					newlogindata.append("LastAction", LocalDate.now().toString());					
					
					Document newUserdata= new Document("Dni", usuario.getDni());
					if(!usuario.getFirstname().isEmpty()) {
						newUserdata.append("FirstName", usuario.getFirstname());
					}else {
						throw new NullPointerException("FirstName vacio");
					}
					if(!usuario.getLastname().isEmpty()) {
						newUserdata.append("LastName", usuario.getLastname());
					}else {
						throw new NullPointerException("LastName vacio");
					}
					if(!usuario.getEmail().isEmpty()) {
						newUserdata.append("email", usuario.getEmail());
					}else {
						throw new NullPointerException("email vacio");
					}
					
					if(ManagerMongoDB.setLoginData(newlogindata) && ManagerMongoDB.setUserData(newUserdata)) {
						respuesta.put("status", "ok");
						result.put("FirstName", usuario.getFirstname());
						result.put("LastName", usuario.getLastname());
						result.put("Token", usuario.getToken());
						result.put("Rol", usuario.getRol());
						respuesta.put("result", result);
						session.put("User", usuario.getEmail());
						session.put("Token", usuario.getToken());
						session.put("role", usuario.getRol());
						respuesta.put("session", session);
					}
				}else if (userlogin.getEmail().equals(usuario.getEmail()) || userUsers.getEmail().equals(usuario.getEmail())) {
					respuesta.put("status", "ERROR");
					result.put("code", "PTV03");
					result.put("errormsg", "There is another user with those credentials");
					respuesta.put("result", result);
					session.put("User", usuario.getEmail());
					session.put("Token", usuario.getToken());
					session.put("role", usuario.getRol());
					respuesta.put("session", session);
				}else {
					respuesta.put("status", "ERROR");
					result.put("code", "PTV05");
					result.put("errormsg", "Error inesperado");
					respuesta.put("result", result);
					session.put("User", usuario.getEmail());
					session.put("Token", usuario.getToken());
					session.put("role", usuario.getRol());
					respuesta.put("session", session);
				}
			} else {
				throw new NullPointerException("request vacio");
			}
		}
		catch (NullPointerException e) {
			respuesta.put("status", "ERROR");
			result.put("code", "PTV05");
			result.put("errormsg", e.toString());
			respuesta.put("result", result);
			session.put("User", usuario.getEmail());
			session.put("Token", usuario.getToken());
			session.put("role", usuario.getRol());
			respuesta.put("session", session);
		}
		catch (ClassCastException e) {
			respuesta.put("status", "ERROR");
			result.put("code", "PTV05");
			result.put("errormsg", e.toString());
			respuesta.put("result", result);
			session.put("User", usuario.getEmail());
			session.put("Token", usuario.getToken());
			session.put("role", usuario.getRol());
			respuesta.put("session", session);
		}
		writer.write(respuesta.toString());
	}
}
