package es.loyola.iitv.ptv.Connection;

import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import es.loyola.iitv.ptv.DAO.Curso;
import es.loyola.iitv.ptv.DAO.Grupo;
import es.loyola.iitv.ptv.DAO.User;

public class ConsultasMongoDB {
	public ConsultasMongoDB() {
		
	}
	
		public static User getLoginData(String emailUser) {
			//Primero obtenemos los datos del login data
			MongoCollection<org.bson.Document> logindata = ManagerMongoDB.getloginCollection();
			
			BasicDBObject dbo = new BasicDBObject("email",emailUser);
			Document docLogin= logindata.find(dbo).first();
			
			User user= null;
			String email= "", password="", lastAction="", rol="", token="";
			
			if(docLogin != null && !docLogin.isEmpty()) {
				if(docLogin.containsKey("email")) {
					email= docLogin.getString("email");
				}
				if(docLogin.containsKey("Password")) {
					password= docLogin.getString("Password");
				}
				if(docLogin.containsKey("LastAction")) {
					lastAction= docLogin.getString("LastAction");
				}
				if(docLogin.containsKey("Rol")) {
					rol= docLogin.getString("Rol");
				}
				if(docLogin.containsKey("Token")) {
					token= docLogin.getString("Token");
				}
				
				user= new User(email, password, lastAction, rol, token);
			}
			
			return user;
		}
		
		public static User getUserData(String emailUser) {
			MongoCollection<org.bson.Document> userdata = ManagerMongoDB.getusersCollection();
			BasicDBObject dbo = new BasicDBObject("email",emailUser);
			Document docUser= userdata.find(dbo).first();
			String firstname="", lastname="", dni="";
			User user= null;
			
			if(docUser != null && !docUser.isEmpty()) {
				if(docUser.containsKey("FirstName")) {
					firstname= docUser.getString("FirstName");
				}
				if(docUser.containsKey("LastName")) {
					lastname= docUser.getString("LastName");
				}
				if(docUser.containsKey("Dni")) {
					dni= docUser.getString("Dni");
				}
				
				user= new User(dni, "", firstname, lastname, emailUser, "", "", "");
			}
			return user;
		}

		public static boolean updateLoginDoc(User userlogindata) {
			MongoCollection<org.bson.Document> logindata = ManagerMongoDB.getloginCollection();
			
			String emailuser= userlogindata.getEmail();
			Date date= new Date();
			SimpleDateFormat format= new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			String currentTime= format.format(date);
			String token= userlogindata.getToken();
			if((emailuser != null && emailuser != "") || (token != null && token != "")) {
				logindata.updateOne(eq("email", emailuser), set("Token", token));
				UpdateResult result= logindata.updateOne(eq("email", emailuser), set("LastAction", currentTime));
				
				return result.wasAcknowledged();
			}else {
				return false;
			}
		}
		
		public static List<Curso> getCoursesData(String emailuser) {
			MongoCollection<org.bson.Document> userdata = ManagerMongoDB.getusersCollection();
			BasicDBObject dbo = new BasicDBObject("email",emailuser);
			Document docUser= userdata.find(dbo).first();
			List<Curso> res= new LinkedList<Curso>();
			
			if(docUser != null ) {
				
					List<Document> cursos= docUser.getList("Courses", Document.class);
					for(Document curso:cursos){
						List<Document> grupos= curso.getList("CourseGroups", Document.class);
						List<Grupo> listgrupos= new LinkedList<Grupo>();
						
						for(Document grupo:grupos) {
							Grupo grup= new Grupo(grupo.getString("GroupName"), grupo.getString("GroupDescription"));
							listgrupos.add(grup);
						}
						
						if(curso.containsKey("CourseId") && curso.containsKey("CourseName")) {
							String id= curso.getString("CourseId");
							String name= curso.getString("CourseName");
							
							res.add(new Curso(id, name, emailuser, listgrupos));
						}
					}
				
			}
			return res;
		}
		
		public static List<User> buscarAlumnos(String CursoId, String GroupName){
			MongoCollection<org.bson.Document> userdata = ManagerMongoDB.getusersCollection();
			BasicDBObject dbo = new BasicDBObject("Courses.CourseId", CursoId);
			dbo.append("Courses.CourseGroups.GroupName", GroupName);
			List<Document> docUser= userdata.find(dbo).into(new ArrayList<Document>());
			List<User> Users= new LinkedList<User>();
			
			if(docUser != null) {
				for(Document Duser: docUser) {
					if(Duser.containsKey("FirstName") && Duser.containsKey("LastName") && Duser.containsKey("email")) {
						String name, lastname, email;
						name= Duser.getString("FirstName");
						lastname= Duser.getString("LastName");
						email= Duser.getString("email");
						User logindata= ConsultasMongoDB.getLoginData(email);

						if(logindata != null) {
							if(logindata.getRol().equalsIgnoreCase("Alumno")) {
								User newUser= new User(logindata.getDni(), "", name, lastname, email, "", "", "");
								Users.add(newUser);
							}
						}
					}
				}
			}
			
			return Users;
		}
		
		public static boolean comprobarToken(String token) throws ParseException {
			MongoCollection<org.bson.Document> logindata = ManagerMongoDB.getloginCollection();
			BasicDBObject dbo = new BasicDBObject("Token", token);
			Document docUser= logindata.find(dbo).first();
			boolean check= false;
			
			if(docUser != null) {
				Date date= new Date();
				SimpleDateFormat format= new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
				String currentTime= format.format(date);
				
				String stringToken= docUser.getString("LastAction");
				Date tokenDate= format.parse(stringToken);
				
				long diff= date.getTime() - tokenDate.getTime();
				TimeUnit time= TimeUnit.MINUTES;
				diff= time.convert(diff, TimeUnit.MILLISECONDS);
				
				if(diff <= 2) {
					check= false;
				}else{
					check= true;
					logindata.updateOne(eq("Token", token), set("LastAction", currentTime));
				}
			}
			
			return check;
		}
}
