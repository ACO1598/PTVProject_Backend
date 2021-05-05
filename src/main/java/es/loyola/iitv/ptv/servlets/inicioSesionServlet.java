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
		
		//Code made for testing
		String request= req.getParameter("request");
		
//		User usuario= new User(request);
//		
//		String test = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		
		System.out.println(request);

		String aux = "";
		User usuario;
		try {
			if(request != null) {
				usuario= new User(request);
			} else {
				usuario= new User();
			}
			System.out.println(usuario);
			respuesta.put("status", usuario);
			
		}catch (ClassCastException e) {
			respuesta.put("status", e.toString());
			aux = aux + e;
			JSONObject result= new JSONObject();
			result.put("errormsg", "Al crear User: " + e);
			//respuesta.put("result", result);
		}
		

//		User userLoginData= ConsultasMongoDB.getLoginData(user.getDni());
		
//		if(userLoginData != null) {
//			if(userLoginData.getPassword().equals(user.getPassword()) && userLoginData.getEmail().equals(user.getEmail())) {
//				if(ConsultasMongoDB.updateLoginDoc(userLoginData) == true) {
//					respuesta.put("status", "ok");
//					JSONObject result= new JSONObject();
//					//result.put("msg", "login succesfull");
//					//result.put("User data", userLoginData);
//					//respuesta.put("result", result);
//				}else {
//					respuesta.put("status", "Error");
////					respuesta.put("result", "Error al actualizar loginData");
//				}
//			}else {
//				respuesta.put("status", "Error");
////				respuesta.put("result", "Incorrect password or User");
//			}
//		}else {
//			respuesta.put("status", "Error");
////			respuesta.put("respuesta", "DB error");
//		}

		//for testing
//		respuesta.put("status", usuario);
		writer.write(respuesta.toString());
	}
	
	
}
