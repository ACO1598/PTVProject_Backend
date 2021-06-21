package es.loyola.iitv.ptv;

//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.io.StringWriter;
//import java.util.List;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.json.JSONObject;
//import org.junit.jupiter.api.Test;
//
//import es.loyola.iitv.ptv.Connection.ConsultasMongoDB;
//import es.loyola.iitv.ptv.DAO.User;
//import es.loyola.iitv.ptv.servlets.ListadoAlumnosServlet;
//import es.loyola.iitv.ptv.servlets.ListadoCursosServlet;

public class ListadoAlumnosTests {

//	@Test
//	public void buscarAlumnostest() {
//		List<User> usuarios= ConsultasMongoDB.buscarAlumnos("FisicaI-20-21", "Ingeniería Electromecánica");
//		for(User usuario: usuarios) {
//			System.out.println("Usuario: " + usuario.getEmail());
//		}
//	}
//	
//	@Test
//	public void mainTest() throws IOException, ServletException{
//		HttpServletRequest request= mock(HttpServletRequest.class);
//		HttpServletResponse response= mock(HttpServletResponse.class);
//		
//		JSONObject jsonRequest= new JSONObject();
//		jsonRequest.put("Curso", "FisicaI-20-21");
//		jsonRequest.put("Grupo", "Ingeniería Electromecánica");
//		when(request.getParameter("data")).thenReturn(jsonRequest.toString());
//		
//		StringWriter stringWriter= new StringWriter();
//		PrintWriter writer= new PrintWriter(stringWriter);
//		
//		when(response.getWriter()).thenReturn(writer);
//		
//		new ListadoAlumnosServlet().doPost(request, response);
//		
//		System.out.println("Lista Cursos " + stringWriter.toString());
//	}

}
