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

import org.bson.Document;
import org.json.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import es.loyola.iitv.ptv.Connection.ManagerMongoDB;
import es.loyola.iitv.ptv.DAO.User;

@WebServlet(urlPatterns="/inicioSesion")
public class inicioSesionServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/plain");//TODO cambiar a JSON
		PrintWriter writer= resp.getWriter();
		
		String test = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		//Conexion desde otro servlet
		
		//TODO añadir try catch para excepcion del constructor
		User user= new User();
		user.getLoginData(test);

		//TODO Debe devolver un User
		Document docLoginData= ManagerMongoDB.getLoginData(user.getUsuario());
		if(docLoginData != null) {
			if(docLoginData.getString("Dni").equals(user.getUsuario()) && docLoginData.getString("Password").equals(user.getPassword())) {
				writer.write("{\"status\": \"Login Correcto\"}");
			}else {
				writer.write("{\"status\": \"Login Incorrecto\"}");
			}
		}else {
			
			writer.write("{\"status\": \"Login Incorrecto\"}");
		}
	}
	
	
}
