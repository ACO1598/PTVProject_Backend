package es.loyola.iitv.ptv.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.json.JsonParseException;
import org.json.JSONObject;

import es.loyola.iitv.ptv.Connection.ConsultasMongoDB;
import es.loyola.iitv.ptv.Connection.Respuesta;
import es.loyola.iitv.ptv.DAO.User;


@WebServlet(urlPatterns="/inicioSesion")
public class InicioSesionServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		resp.setHeader("Cache-Control", "no-cache");
		resp.setHeader("Cache-Contorl", "no-store");
		PrintWriter writer= resp.getWriter();
		JSONObject respuesta= new JSONObject();
		
		String request = new String();
		
		//USE this for normal use
		BufferedReader myRequest = req.getReader();
		for(String line; (line = myRequest.readLine()) != null; request+= line);
		
		//Use this for testing
		
		//request = req.getParameter("data");
		
		User usuario= null;
		
		try {
			if(request != null) {
				usuario= new User(request);

				User userLoginData= ConsultasMongoDB.getLoginData(usuario.getEmail());
				User userUsersData= ConsultasMongoDB.getUserData(usuario.getEmail());
				userLoginData.setToken(userLoginData.generarToken());
				
				if(userLoginData != null && userUsersData != null) {
					if(userLoginData.getPassword().equals(usuario.getPassword()) && userLoginData.getEmail().equals(usuario.getEmail())) {
						if(ConsultasMongoDB.updateLoginDoc(userLoginData)) {
							JSONObject result= new JSONObject();
							result.put("FirstName", userUsersData.getFirstname());
							result.put("LastName", userUsersData.getLastname());
							result.put("Token", userLoginData.getToken());
							result.put("Rol", userLoginData.getRol());
							respuesta= Respuesta.prepMensaje(result, userLoginData.getEmail(), userLoginData.getToken(), userLoginData.getRol());
						}else {
							respuesta= Respuesta.prepMensajeError("PTV03", "LoginData no actualizado", usuario.getEmail(), usuario.getToken(),  usuario.getRol());
						}
					}else {
						respuesta= Respuesta.prepMensajeError("PTV03", "Contraseña o email no concuerdan", usuario.getEmail(), usuario.getToken(), usuario.getRol());
					}
				}
			}
		}catch (NullPointerException e) {  
			respuesta= Respuesta.prepMensajeError("PTV04", e.toString(), "", "", "");
		}
		catch (ClassCastException e) {        
			respuesta= Respuesta.prepMensajeError("PTV01", e.toString(), "", "", "");
		}
		writer.write(respuesta.toString());
	}
	
	
}
