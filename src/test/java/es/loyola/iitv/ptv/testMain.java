package es.loyola.iitv.ptv;

//import javax.swing.text.Document;

import com.mongodb.ConnectionString;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import com.mongodb.ServerAddress;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import org.bson.Document;
import org.json.JSONObject;
import org.junit.Test;

import java.util.Arrays;

import com.mongodb.BasicDBObject;
import com.mongodb.Block;

import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.result.DeleteResult;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import static org.mockito.Mockito.when;

import es.loyola.iitv.ptv.Connection.ManagerConnection;
import es.loyola.iitv.ptv.DAO.User;
import es.loyola.iitv.ptv.servlets.inicioSesionServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class testMain {

	@Test
	public void test() throws IOException, ServletException{
		HttpServletRequest request= mock(HttpServletRequest.class);
		HttpServletResponse response= mock(HttpServletResponse.class);
		
//		when(request.getParameter("email")).thenReturn("testing@gmail.com");
//		when(request.getParameter("password")).thenReturn("");
		JSONObject jsonRequest= new JSONObject();
		jsonRequest.put("usuario", "testing");
		jsonRequest.put("password", "1234");
		when(request.getParameter("request")).thenReturn(jsonRequest.toString());
		
		StringWriter stringWriter= new StringWriter();
		PrintWriter writer= new PrintWriter(stringWriter);
		
		when(response.getWriter()).thenReturn(writer);
		
		new inicioSesionServlet().doPost(request, response);
		
		System.out.println("Inicio sesion: " + stringWriter.toString());
	}
	
	@Test
	public void testToken() {
		User usuario= new User();
		String token= usuario.generarToken();
		
		System.out.println(token);
	}
	
	@Test 
	public void testupdateloginData() {
		
	}
}
