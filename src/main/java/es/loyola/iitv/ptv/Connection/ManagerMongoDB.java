package es.loyola.iitv.ptv.Connection;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class ManagerMongoDB extends ManagerConnection{
	static String databaseMongoDB= "examples";
	static String collectionLoginData= "logindata";
	static String collectionUsers= "users";
	
	public ManagerMongoDB() {
		
	}
	
	public static MongoCollection<org.bson.Document> getloginCollection() {
		MongoClient client= ManagerConnection.doConnectionMongo();
		MongoDatabase database = client.getDatabase(databaseMongoDB);
		MongoCollection<org.bson.Document> logindata = database.getCollection(collectionLoginData);
		
		return logindata;
	}
	
	public static MongoCollection<org.bson.Document> getusersCollection(){
		MongoClient client= ManagerConnection.doConnectionMongo();
		MongoDatabase database = client.getDatabase(databaseMongoDB);
		MongoCollection<org.bson.Document> data = database.getCollection(collectionUsers);
		
		return data;
	}
	
	public static boolean setLoginData(Document doc) {
		MongoClient client= ManagerConnection.doConnectionMongo();
		MongoDatabase database = client.getDatabase(databaseMongoDB);
		MongoCollection<org.bson.Document> logindata = database.getCollection(collectionLoginData);
		
		if(!doc.isEmpty() && doc.containsKey("email") && doc.containsKey("Password")
				&& doc.containsKey("Rol") && doc.containsKey("Token") && doc.containsKey("LastAction")) {
			logindata.insertOne(doc);
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean setUserData(Document doc) {
		MongoClient client= ManagerConnection.doConnectionMongo();
		MongoDatabase database = client.getDatabase(databaseMongoDB);
		MongoCollection<org.bson.Document> userdata = database.getCollection(collectionUsers);
		
		if(!doc.isEmpty() && doc.containsKey("FirstName") && doc.containsKey("LastName") && doc.containsKey("email")) {
			userdata.insertOne(doc);
			return true;
		}else {
			return false;
		}
	}
}
