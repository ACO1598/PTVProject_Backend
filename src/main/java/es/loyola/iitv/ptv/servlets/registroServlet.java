package es.loyola.iitv.ptv.servlets;

import java.io.IOException;
import java.io.PrintWriter;
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

import es.loyola.iitv.ptv.Connection.ManagerMongoDB;
import es.loyola.iitv.ptv.DAO.User;

@WebServlet(urlPatterns="/registro")
public class registroServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setHeader("Cache-Control", "no-cache");
		resp.setHeader("Cache-Contorl", "no-store");
		resp.setContentType("application/json");
		PrintWriter writer= resp.getWriter();
		
//		String test = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
//		User user= new User();
//		user.makeUser(test);
//
//		Document doc= ManagerMongoDB.getLoginData(user.getUsuario());
//		JSONObject respuesta= new JSONObject();
//		if (doc.isEmpty()){
//			Document newlogindata= new Document("Dni", user.getUsuario()).append("Password", user.getUsuario());
//			ManagerMongoDB.setLoginData(newlogindata);
//			
//			respuesta.put("code", "ok");
//			respuesta.put("message", user.getUsuario());
//			respuesta.put("result", "User registered");
//			writer.write(respuesta.toString());
//			
//		}else if (doc.get("Dni").toString().equals(user.getUsuario())) {
//			respuesta.put("code", "not ok");
//			respuesta.put("message", "There is another user with those credentials");
//			respuesta.put("result", "User not registered");
//			writer.write(respuesta.toString());
//		}
	}
}
