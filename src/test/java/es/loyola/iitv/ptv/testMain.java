package es.loyola.iitv.ptv;

//import javax.swing.text.Document;

import com.mongodb.ConnectionString;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import com.mongodb.ServerAddress;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import org.bson.Document;
import java.util.Arrays;

import com.mongodb.BasicDBObject;
import com.mongodb.Block;

import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.result.DeleteResult;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult;

import es.loyola.iitv.ptv.Connection.ManagerConnection;

import java.util.ArrayList;
import java.util.List;

public class testMain {

	public static void main(String[] args) {
		MongoClient client = ManagerConnection.doConnectionMongo();
		
		MongoDatabase database = client.getDatabase("examples");
		MongoCollection<org.bson.Document> inventory = database.getCollection("inventory");
		MongoCollection<org.bson.Document> users = database.getCollection("users");
		
		
		BasicDBObject dbo = new BasicDBObject("FirstName","NombreAlumno1");
		Document doc= users.find(dbo).first();
		
		Document myDoc = inventory.find().first();
		
		
		//FindIterable<Document> findPublisher= inventory.find(new Document());
		//Document yoyo = inventory.find
		
		//System.out.println(doc.toString());
		//System.out.println(myDoc.toString());
	}
}
