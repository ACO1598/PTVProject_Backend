package es.loyola.iitv.ptv;

//import javax.swing.text.Document;

import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import es.loyola.iitv.ptv.Connection.ConsultasMongoDB;
import es.loyola.iitv.ptv.DAO.User;
import es.loyola.iitv.ptv.servlets.InicioSesionServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InicioSesionTest {
/*
	@Test
	public void test() throws IOException, ServletException{
		HttpServletRequest request= mock(HttpServletRequest.class);
		HttpServletResponse response= mock(HttpServletResponse.class);
		
		JSONObject jsonRequest= new JSONObject();
		jsonRequest.put("usuario", "emailProfesor1@gmail.com");
		jsonRequest.put("password", "NombreAlumno3");
		when(request.getParameter("data")).thenReturn(jsonRequest.toString());
		
		StringWriter stringWriter= new StringWriter();
		PrintWriter writer= new PrintWriter(stringWriter);
		
		when(response.getWriter()).thenReturn(writer);
		
		new InicioSesionServlet().doPost(request, response);
		
		System.out.println("Inicio sesion: " + stringWriter.toString());
		
		JSONObject resp= new JSONObject(stringWriter.toString());
		
		String code= resp.optString("code");
		if(code.equalsIgnoreCase("ok")) {
			assertEquals("El correo no corresponde", "emailProfesor1@gmail.com", resp.optString("user"));
			assertEquals("El nombre del usuario no corresponde", "Profesor1", resp.optString("FirstName"));
		}
	}
	
	@Test
	public void testToken() {
		User usuario= new User();
		String token= usuario.generarToken();
		
		System.out.println(token);
		if(token.length() != 27) {
			fail("length is not correct");
		}
	}
	
	@Test 
	public void testupdateloginData() {
		User usuario= new User("emailProfesor1@gmail.com", "NombreAlumno3", "", "", "");
		
		Boolean result= ConsultasMongoDB.updateLoginDoc(usuario);
		
		assertEquals(true, result);
	}
	
	@Test
	public void testgetLoginData() {
		String emailUser= "emailProfesor1@gmail.com";
		
		User usuario= ConsultasMongoDB.getLoginData(emailUser);
		
		assertEquals("emailProfesor1@gmail.com", usuario.getEmail());
		assertEquals("NombreAlumno3", usuario.getPassword());
	}
	*/
}
