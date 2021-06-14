package es.loyola.iitv.ptv.servlets;

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
import es.loyola.iitv.ptv.DAO.User;

@WebServlet(urlPatterns="/ListadoCursos")
public class ListadoCursosServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		PrintWriter writer= resp.getWriter();
		JSONObject respuesta= new JSONObject();
		JSONObject result= new JSONObject();
		JSONObject session= new JSONObject();
		
		String request= req.getParameter("request");
		
		User usuario= null;
		
		try {
			if(request != null) {
				usuario= new User(request);

				User userLoginData= ConsultasMongoDB.getLoginData(usuario.getEmail());
				
				List<Curso> cursos= new LinkedList<Curso>();
				
				cursos= ConsultasMongoDB.getCoursesData(usuario.getEmail());
				if(cursos != null & !cursos.isEmpty()) {
					respuesta.put("status", "ok");
					result.put("ListCursos", cursos);
					respuesta.put("result", result);
					session.put("User", userLoginData.getEmail());
					session.put("Token", userLoginData.getToken());
					session.put("role", userLoginData.getRol());
					respuesta.put("session", session);
				}else {
					respuesta.put("status", "ERROR");
					result= new JSONObject();
					result.put("code", "PTV01");
					result.put("errormsg", "No se han encontrado cursos");
					respuesta.put("result", result);
					session= new JSONObject();
					session.put("user", "");
					session.put("Token", "");
					session.put("role", "");
					respuesta.put("session", session);
				}
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
			session.put("user", usuario.getEmail());
			session.put("Token", usuario.getToken());
			session.put("role", usuario.getRol());
			respuesta.put("session", session);
		}
		writer.write(respuesta.toString());
	}
	
}