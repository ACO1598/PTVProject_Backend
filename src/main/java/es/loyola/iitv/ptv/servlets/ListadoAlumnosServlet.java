package es.loyola.iitv.ptv.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import es.loyola.iitv.ptv.Connection.ConsultasMongoDB;
import es.loyola.iitv.ptv.DAO.Curso;
import es.loyola.iitv.ptv.DAO.Grupo;
import es.loyola.iitv.ptv.DAO.User;

@WebServlet(urlPatterns="/ListadoAlumnos")
public class ListadoAlumnosServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8450186377561292082L;

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		resp.setHeader("Cache-Control", "no-cache");
		resp.setHeader("Cache-Contorl", "no-store");
		PrintWriter writer= resp.getWriter();
		JSONObject respuesta= new JSONObject();
		JSONObject result= new JSONObject();
		JSONObject session= new JSONObject();
		
		String request = new String();
		
		//USE this for normal use
		BufferedReader myRequest = req.getReader();
		for(String line; (line = myRequest.readLine()) != null; request+= line);
		
		//Use this for testing
		
//		request = req.getParameter("data");
		
		try {
			if(request != null) {
				JSONObject jrequest= new JSONObject(request);
				String groupName, cursoId;
				
				if(jrequest.has("Token")) {
					
				}
				
				if(jrequest.has("Curso") && jrequest.has("Grupo")){
					groupName= (String) jrequest.get("Grupo");
					cursoId= (String) jrequest.get("Curso");
					
					List<User> Users= ConsultasMongoDB.buscarAlumnos(cursoId, groupName);
					
					if(Users.isEmpty()) {
						respuesta.put("status", "ERROR");
						result.put("code", "PTV01");
						result.put("errormsg", "No hay alumnos con esta combinacion");
						respuesta.put("result", Users);
						session.put("user", "");
						session.put("Token", "");
						session.put("role", "");
						respuesta.put("result",result);
						respuesta.put("session", session);
					}else {
						respuesta.put("status", "ok");
						result.put("ListAlumnos", Users);
						respuesta.put("result", result);
						//TODO resolver el tema de falta de token/email usuario ejecutando el Servlet
//						session.put("User", userLoginData.getEmail());
//						session.put("Token", userLoginData.getToken());
//						session.put("role", userLoginData.getRol());
						respuesta.put("session", session);
					}
					
				}else {
					throw new NullPointerException("empty request 1");
				}
				
			}else {
				throw new NullPointerException("empty request 2");
			}
		}catch (NullPointerException e) {
			respuesta.put("status", "ERROR");
			result= new JSONObject();
			result.put("code", "PTV04");
			result.put("errormsg", e.toString());
			respuesta.put("result", result);
			session= new JSONObject();
			session.put("user", "");
			session.put("Token", "");
			session.put("role", "");
			respuesta.put("session", session);
		}
		catch (ClassCastException e) {                                          
			respuesta.put("status", "ERROR");
			result= new JSONObject();
			result.put("code", "PTV01");
			result.put("errormsg", e.toString());
			respuesta.put("result", result);
			session= new JSONObject();
//			session.put("user", usuario.getEmail());
//			session.put("Token", usuario.getToken());
//			session.put("role", usuario.getRol());
			respuesta.put("session", session);
		}
		writer.write(respuesta.toString());
	}
}
