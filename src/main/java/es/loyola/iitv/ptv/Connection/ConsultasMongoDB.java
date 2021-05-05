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
	
		public static User getLoginData(String dniUser) {
			MongoCollection<org.bson.Document> logindata = ManagerMongoDB.getloginCollection();
			
			BasicDBObject dbo = new BasicDBObject("Dni",dniUser);
			Document doc= logindata.find(dbo).first();
			
			String email= doc.get("email").toString();
			String password= doc.getString("Password");
			
			User user= new User(email, password);
			
			return user;
		}

		public static boolean updateLoginDoc(User userlogindata) {
			MongoCollection<org.bson.Document> logindata = ManagerMongoDB.getloginCollection();
			
			String emailuser= userlogindata.getEmail();
			String currentTime= LocalDate.now().toString();
			UpdateResult result= logindata.updateOne(eq("email", emailuser), set("LastAction", currentTime));
			if(result.wasAcknowledged()) {
				return true;
			}else {
				return false;
			}
		}
		
}
