package es.loyola.iitv.ptv;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;

import es.loyola.iitv.ptv.Connection.ConsultasMongoDB;
import es.loyola.iitv.ptv.Connection.ManagerMongoDB;
import es.loyola.iitv.ptv.DAO.User;
import jdk.internal.org.jline.reader.Parser;

class ListadoCursosTests {

	@Test
	void test() {
		MongoCollection<org.bson.Document> userdata = ManagerMongoDB.getusersCollection();
		BasicDBObject dbo = new BasicDBObject("email","emailProfesor3@gmail.com");
		Document docUser= userdata.find(dbo).first();
		
		List<Document> cursos= docUser.getList("Courses", Document.class);
		for(Document curso:cursos){
			System.out.println(curso);
			List<Document> grupos= curso.getList("CourseGroups", Document.class);
			System.out.println(grupos);
			
			for(Document grupo:grupos) {
				String groupName= grupo.getString("GroupName");
				System.out.println(groupName);
			}
		}
	}

}
