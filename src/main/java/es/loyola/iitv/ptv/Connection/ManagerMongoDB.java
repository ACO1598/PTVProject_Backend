package es.loyola.iitv.ptv.Connection;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class ManagerMongoDB extends ManagerConnection{
	//TODO poner los nombres de bases y colecciones por variables
	static String databaseMongoDB= "examples";
	static String collectionLoginData= "logindata";
	
	public ManagerMongoDB() {
		
	}
	
	public static MongoCollection<org.bson.Document> getloginCollection() {
		MongoClient client= ManagerConnection.doConnectionMongo();
		MongoDatabase database = client.getDatabase(databaseMongoDB);
		MongoCollection<org.bson.Document> logindata = database.getCollection(collectionLoginData);
		
		return logindata;
	}
	
	public static boolean setLoginData(Document doc) {
		MongoClient client= ManagerConnection.doConnectionMongo();
		MongoDatabase database = client.getDatabase("examples");
		MongoCollection<org.bson.Document> logindata = database.getCollection("logindata");
		
		logindata.insertOne(doc);
		
		return true;
	}
	
	
}
