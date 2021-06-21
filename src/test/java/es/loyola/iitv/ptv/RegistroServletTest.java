package es.loyola.iitv.ptv;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.junit.Test;

import es.loyola.iitv.ptv.servlets.RegistroServlet;

public class RegistroServletTest {
/*
	@Test
	public void test() throws IOException, ServletException{
		HttpServletRequest request= mock(HttpServletRequest.class);
		HttpServletResponse response= mock(HttpServletResponse.class);
		
		JSONObject jsonRequest= new JSONObject();
		jsonRequest.put("FirstName", "Alan");
		jsonRequest.put("LastName", "Clech");
		jsonRequest.put("password", "testing");
		jsonRequest.put("Dni", "87654321");
		jsonRequest.put("user", "alanclecholiveros@gamil.com");
		when(request.getParameter("request")).thenReturn(jsonRequest.toString());
		
		StringWriter stringWriter= new StringWriter();
		PrintWriter writer= new PrintWriter(stringWriter);
		
		when(response.getWriter()).thenReturn(writer);
		
		new RegistroServlet().doPost(request, response);
		
		System.out.println("Registro: " + stringWriter.toString());
	}
*/
}
