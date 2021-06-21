package es.loyola.iitv.ptv;

//import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;

import es.loyola.iitv.ptv.Connection.ConsultasMongoDB;
import es.loyola.iitv.ptv.Connection.ManagerMongoDB;
import es.loyola.iitv.ptv.DAO.Curso;
import es.loyola.iitv.ptv.DAO.User;
import es.loyola.iitv.ptv.servlets.ListadoCursosServlet;
import jdk.internal.org.jline.reader.Parser;

public class ListadoCursosTests {

//	@Test
//	public void test() throws IOException, ServletException{
//		HttpServletRequest request= mock(HttpServletRequest.class);
//		HttpServletResponse response= mock(HttpServletResponse.class);
//		
//		JSONObject jsonRequest= new JSONObject();
//		jsonRequest.put("usuario", "emailProfesor1@gmail.com");
//		when(request.getParameter("data")).thenReturn(jsonRequest.toString());
//		
//		StringWriter stringWriter= new StringWriter();
//		PrintWriter writer= new PrintWriter(stringWriter);
//		
//		when(response.getWriter()).thenReturn(writer);
//		
//		new ListadoCursosServlet().doPost(request, response);
//		
//		System.out.println("Lista Cursos " + stringWriter.toString());
//	}
//	
//	@Test
//	public void getCoursesDataTest() {
//		List<Curso> cursos= ConsultasMongoDB.getCoursesData("emailProfesor1@gmail.com");
//		System.out.println(cursos);
//	}

}
