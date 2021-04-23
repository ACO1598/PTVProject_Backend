package es.loyola.iitv.ptv.Connection;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

//TODO heredar de ManagerConnection
public class ManagerMongoDB {
	//TODO poner los nombres de bases y colecciones por variables
	
	public ManagerMongoDB() {
		
	}
	
	//TODO Pasar ambas funciones a una nueva clase de consulta
	public static Document getLoginData(String dni) {
		//TODO Convertir estas 3 lineas en funcion
		MongoClient client= ManagerConnection.doConnectionMongo();
		MongoDatabase database = client.getDatabase("examples");
		MongoCollection<org.bson.Document> logindata = database.getCollection("logindata");
		
		BasicDBObject dbo = new BasicDBObject("Dni",dni);
		Document doc= logindata.find(dbo).first();
		
		return doc;
	}
	
	//TODO le pasas un User, devuelve un boolean
	public static void setLoginData(Document doc) {
		MongoClient client= ManagerConnection.doConnectionMongo();
		MongoDatabase database = client.getDatabase("examples");
		MongoCollection<org.bson.Document> logindata = database.getCollection("logindata");
		
		logindata.insertOne(doc);
	}
	
	
}
