package es.loyola.iitv.ptv.Connection;

import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;

import java.time.LocalDate;

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
					firstname= docUser.getString("Firstname");
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
			String currentTime= LocalDate.now().toString();
			String token= userlogindata.getToken();
			if((emailuser != null && emailuser != "") || (token != null && token != "")) {
				logindata.updateOne(eq("email", emailuser), set("Token", token));
				UpdateResult result= logindata.updateOne(eq("email", emailuser), set("LastAction", currentTime));
				
				return result.wasAcknowledged();
			}else {
				return false;
			}
		}
		
}
