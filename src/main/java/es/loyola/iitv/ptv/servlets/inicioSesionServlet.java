package es.loyola.iitv.ptv.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import es.loyola.iitv.ptv.Connection.ConsultasMongoDB;
import es.loyola.iitv.ptv.DAO.User;


@WebServlet(urlPatterns="/inicioSesion")
public class inicioSesionServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		PrintWriter writer= resp.getWriter();
		JSONObject respuesta= new JSONObject();
		JSONObject result= new JSONObject();
		JSONObject session= new JSONObject();
		
		//Code made for testing
		String request= req.getParameter("request");

//		String test = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		
		System.out.println(request);

		User usuario= new User();
		
		try {
			if(request != null) {
				usuario= new User(request);
			} else {
				usuario= new User();
			}
			
		}catch (ClassCastException e) {
			//TODO corregir mensaje devuelto
			respuesta.put("status", e.toString());
			
			result.put("errormsg", "Al crear User: " + e);
		}
		
		try {
			User userLoginData= ConsultasMongoDB.getLoginData(usuario.getEmail());
			User userUsersData= ConsultasMongoDB.getUserData(usuario.getEmail());
			userLoginData.setToken(userLoginData.generarToken());
			
			if(userLoginData.getPassword().equals(usuario.getPassword()) && userLoginData.getEmail().equals(usuario.getEmail())) {
				if(ConsultasMongoDB.updateLoginDoc(userLoginData) == true) {
					//TODO corregir mensaje devuelto
					respuesta.put("status", "ok");
					result.put("FirstName", userUsersData.getFirstname());
					result.put("LastName", userUsersData.getLastname());
					String token= userLoginData.generarToken();
					result.put("Token", token);
					result.put("Rol", userLoginData.getRol());
					respuesta.put("result", result);
					session.put("User", userLoginData);
					session.put("Token", token);
					session.put("role", userLoginData.getRol());
					respuesta.put("session", session);
				}else {
					//TODO corregir mensaje devuelto
					respuesta.put("status", "Error update login");
//					respuesta.put("result", "Error al actualizar loginData");
				}
			}else {
				//TODO corregir mensaje devuelto
				respuesta.put("status", "Error comprobacion contraseña usuario");
//				respuesta.put("result", "Incorrect password or User");
			}
		}catch (ClassCastException e){
			respuesta.put("status", "ERROR");
			result= new JSONObject();
			result.put("code", "PTV01");
			result.put("errormsg", e.toString());
			respuesta.put("result", result);
			session= new JSONObject();
			session.put("user", usuario.getEmail());
			session.put("Token", usuario.getToken());
			session.put("role", usuario.getRol());
			respuesta.put("session", session);
		}
		
		//Devolvemos el mensaje final
		writer.write(respuesta.toString());
	}
	
	
}
