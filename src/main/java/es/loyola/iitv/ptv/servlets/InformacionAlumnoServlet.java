package es.loyola.iitv.ptv.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import es.loyola.iitv.ptv.Connection.ConsultasMongoDB;
import es.loyola.iitv.ptv.Connection.Respuesta;
import es.loyola.iitv.ptv.DAO.Curso;
import es.loyola.iitv.ptv.DAO.User;

@WebServlet(urlPatterns="/infoAlumno")
public class InformacionAlumnoServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -765222077948575997L;
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		resp.setHeader("Cache-Control", "no-cache");
		resp.setHeader("Cache-Contorl", "no-store");
		PrintWriter writer= resp.getWriter();
		JSONObject respuesta= new JSONObject();
		JSONObject result= new JSONObject();
		
		String request = new String();
		
		//USE this for normal use
		BufferedReader myRequest = req.getReader();
		for(String line; (line = myRequest.readLine()) != null; request+= line);
		
		//Use this for testing
		
//		request = req.getParameter("data");
		
		User usuario= null;
		
		try {
			if(request != null) {
				JSONObject jrequest= new JSONObject(request);
				
				if(jrequest.has("Token")) {
					if(ConsultasMongoDB.comprobarToken(jrequest.getString("Token"))) {
						usuario= new User(request);
						if(ConsultasMongoDB.comprobarProfesor(jrequest.getString("solicitante"))) {
							User userLoginData= ConsultasMongoDB.getLoginData(usuario.getEmail());
							User userUsersData= ConsultasMongoDB.getUserData(usuario.getEmail());
							List<Curso> cursos= ConsultasMongoDB.getCoursesData(usuario.getEmail());
							
							if(userLoginData != null && userUsersData != null && cursos != null) {
								
								result.put("email", userUsersData.getEmail());
								result.put("nombre", userUsersData.getFirstname());
								result.put("apellidos", userUsersData.getLastname());
								result.put("rol", userLoginData.getRol());
								
								List<String> sCursos= new LinkedList<String>();
								
								for(Curso curso:cursos) {
									sCursos.add(curso.getCursoName());
								}
								
								result.put("cursos", sCursos);
								result.put("grupo", cursos.get(0).getListGrupos().get(0).getGroupDescription());
								
								respuesta= Respuesta.prepMensaje(result, "", jrequest.getString("Token"), "");
							}
						}else {
							respuesta= Respuesta.prepMensajeError("PTV06", "Permision not granted", usuario.getEmail(), jrequest.getString("Token"), "Alumno");
						}
					}else {
						respuesta= Respuesta.prepMensajeError("PTV02", "Token expirado 2", "", jrequest.getString("Token"), "");
					}
				}else {
					respuesta= Respuesta.prepMensajeError("PTV02", "Token expirado 1", "", jrequest.getString("Token"), "");
				}
			}
		}catch (NullPointerException e) {
			respuesta= Respuesta.prepMensajeError("PTV04", e.toString(), "", "", "");
		}
		catch (ClassCastException e) {       
			respuesta= Respuesta.prepMensajeError("PTV01", e.toString(), "", "", "");
		}
		catch (ParseException e) {
			respuesta= Respuesta.prepMensajeError("PTV04", "Error Parseo", "", "", "");
		}
		writer.write(respuesta.toString());
	}

}
