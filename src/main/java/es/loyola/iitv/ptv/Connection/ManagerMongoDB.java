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
		MongoDatabase database = client.getDatabase("examples");
		MongoCollection<org.bson.Document> logindata = database.getCollection("logindata");
		
		logindata.insertOne(doc);
		
		return true;
	}
	
	
}
