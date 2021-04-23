package es.loyola.iitv.ptv.Connection;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class ManagerConnection {
	private static String URL = "mongodb://localhost:27017";
	
	public ManagerConnection() {
		
	}
	 
	
	public static MongoClient doConnectionMongo() {
		
		MongoClient client = MongoClients.create(URL);
		return client;
	}
}
