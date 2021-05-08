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
			
			User user= new User();
			String email, password, lastAction, rol, token;
			
			if(docLogin != null) {
				email= docLogin.getString("email");
				password= docLogin.getString("Password");
				lastAction= docLogin.getString("LastAction");
				rol= docLogin.getString("LastAction");
				token= docLogin.getString("LastAction");
				System.out.println("datos doc: " + email + " " + password);
				
				user= new User(email, password, lastAction, rol, token);
				
			}else {
				throw new ClassCastException("User not found in loginData");
			}
			
			return user;
		}
		
		public static User getUserData(String emailUser) {
			MongoCollection<org.bson.Document> userdata = ManagerMongoDB.getusersCollection();
			BasicDBObject dbo = new BasicDBObject("email",emailUser);
			Document docUser= userdata.find(dbo).first();
			String firstname, lastname, dni;
			
			if(docUser != null) {
				firstname= docUser.getString("Firstname");
				lastname= docUser.getString("LastName");
				dni= docUser.getString("Dni");
			}else {
				throw new ClassCastException("User not found in Users");
			}
			
			User user= new User(dni, "", firstname, lastname, emailUser, "", "", "");
			return user;
		}

		public static boolean updateLoginDoc(User userlogindata) {
			MongoCollection<org.bson.Document> logindata = ManagerMongoDB.getloginCollection();
			
			String emailuser= userlogindata.getEmail();
			String currentTime= LocalDate.now().toString();
			String token= userlogindata.getToken();
			logindata.updateOne(eq("email", emailuser), set("Token", token));
			UpdateResult result= logindata.updateOne(eq("email", emailuser), set("LastAction", currentTime));
			if(result.wasAcknowledged()) {
				return true;
			}else {
				return false;
			}
		}
		
}
