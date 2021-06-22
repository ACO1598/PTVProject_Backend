package es.loyola.iitv.ptv;

//import static com.mongodb.client.model.Filters.eq;
//import static com.mongodb.client.model.Updates.set;
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.time.LocalDate;
//import java.util.Date;
//import java.util.concurrent.TimeUnit;
//
//import org.bson.Document;
//import org.junit.jupiter.api.Test;
//
//import com.mongodb.BasicDBObject;
//import com.mongodb.client.MongoCollection;
//
//import es.loyola.iitv.ptv.Connection.ManagerMongoDB;

public class ConsultasMongoTests {

//	@Test
//	public void test() throws ParseException {
//		MongoCollection<org.bson.Document> logindata = ManagerMongoDB.getloginCollection();
//		BasicDBObject dbo = new BasicDBObject("Token", "rz1izBhIDp8ScbzBwyB7lWTIC9Q");
//		Document docUser= logindata.find(dbo).first();
//		boolean check= false;
//		
//		if(docUser != null) {
//			Date date= new Date();
//			SimpleDateFormat format= new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//			String currentTime= format.format(date);
//			System.out.println(currentTime);
//			
//			String stringToken= docUser.getString("LastAction");
//			Date tokenDate= format.parse(stringToken);
//			
//			System.out.println(format.format(tokenDate));
//			
//			long diff= date.getTime() - tokenDate.getTime();
//			TimeUnit time= TimeUnit.MINUTES;
//			diff= time.convert(diff, TimeUnit.MILLISECONDS);
//			
//			System.out.println(diff);
//			
//			if(diff >= 5) {
//				check= false;
//			}{
//				check= true;
//				logindata.updateOne(eq("Token", "rz1izBhIDp8ScbzBwyB7lWTIC9Q"), set("LastAction", currentTime));
//			}
//		}
//	}

}
