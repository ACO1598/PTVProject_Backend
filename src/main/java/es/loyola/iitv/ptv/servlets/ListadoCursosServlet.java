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

import org.json.JSONArray;
import org.json.JSONObject;

import es.loyola.iitv.ptv.Connection.ConsultasMongoDB;
import es.loyola.iitv.ptv.Connection.Respuesta;
import es.loyola.iitv.ptv.DAO.Curso;
import es.loyola.iitv.ptv.DAO.Grupo;
import es.loyola.iitv.ptv.DAO.User;

@WebServlet(urlPatterns="/ListadoCursos")
public class ListadoCursosServlet extends HttpServlet{

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
		JSONObject result= new JSONObject();
		
		String request = new String();
		
		//USE this for normal use
		BufferedReader myRequest = req.getReader();
		for(String line; (line = myRequest.readLine()) != null; request+= line);
		
//		request= req.getParameter("data");
		
		User usuario= null;
		
		try {
			if(request != null) {
				JSONObject jrequest= new JSONObject(request);
				if(jrequest.has("Token")) {
					if(ConsultasMongoDB.comprobarToken(jrequest.getString("Token"))) {
						usuario= new User(request);

						User userLoginData= ConsultasMongoDB.getLoginData(usuario.getEmail());
						
						List<Curso> cursos= new LinkedList<Curso>();
						
						cursos= ConsultasMongoDB.getCoursesData(usuario.getEmail());
						
						JSONArray res= new JSONArray();
						for(Curso curso:cursos) {
							JSONObject jcurso= new JSONObject();
							jcurso.put("Curso", curso.getCursoId());
							JSONArray grupos= new JSONArray();
							for (Grupo grupo: curso.getListGrupos()) {
								grupos.put(grupo.getGroupName());
							}
							jcurso.put("Grupo", grupos);
							res.put(jcurso);
						}

						if(cursos != null & !cursos.isEmpty()) {
							result.put("ListCursos", res);
							respuesta.put("result", result);
							respuesta= Respuesta.prepMensaje(result, usuario.getEmail(), jrequest.getString("Token"), userLoginData.getRol());
						}else {
							respuesta= Respuesta.prepMensajeError("PTV01", "No se han encontrado cursos", userLoginData.getEmail(), jrequest.getString("Token"), userLoginData.getRol());
						}
					}else {
						respuesta= Respuesta.prepMensajeError("PTV02", "Token expirado", "", jrequest.getString("Token"), "");
					}
				}
			}
		}catch (NullPointerException e) {
			respuesta= Respuesta.prepMensajeError("PTV04", e.toString(), "", "", "");
		}
		catch (ParseException e) {       
			respuesta= Respuesta.prepMensajeError("PTV05", e.toString(), "", "", "");
		}
		catch (ClassCastException e) {                                          
			respuesta= Respuesta.prepMensajeError("PTV05", e.toString(), "", "", "");
		}
		writer.write(respuesta.toString());
	}
	
}
