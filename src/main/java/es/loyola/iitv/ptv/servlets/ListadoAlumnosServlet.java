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
					if(ConsultasMongoDB.comprobarToken(jrequest.getString("Token"))) {
						if(jrequest.has("Curso") && jrequest.has("Grupo")){
							groupName= (String) jrequest.get("Grupo");
							cursoId= (String) jrequest.get("Curso");
							
							List<User> Users= ConsultasMongoDB.buscarAlumnos(cursoId, groupName);
							
							if(Users.isEmpty()) {
								respuesta= Respuesta.prepMensajeError("PTV01", "No hay alumnos con esta combinacion", "", jrequest.getString("Token"), "");
							}else {
								JSONObject jsonresult= new JSONObject();
								jsonresult.put("ListAlumnos", Users);
								respuesta= Respuesta.prepMensaje(jsonresult, "", jrequest.getString("Token"), "");
							}
							
						}else {
							throw new NullPointerException("empty request 1");
						}
					}else {
						respuesta= Respuesta.prepMensajeError("PTV02", "Token expirado", "", jrequest.getString("Token"), "");
					}
				}else {
					respuesta= Respuesta.prepMensajeError("PTV02", "Token expirado", "", jrequest.getString("Token"), "");
				}
			}
		}catch (NullPointerException e) {
			respuesta= Respuesta.prepMensajeError("PTV04", "Error", "", "", "");
		}
		catch (ClassCastException e) {       
			respuesta= Respuesta.prepMensajeError("PTV01", e.toString(), "", "", "");
		}
		catch (ParseException e) {
			respuesta= Respuesta.prepMensajeError("PTV04", "Error", "", "", "");
		}
		writer.write(respuesta.toString());
	}
}
