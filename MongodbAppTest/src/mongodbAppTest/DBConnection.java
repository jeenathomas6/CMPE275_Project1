package mongodbAppTest;

import java.net.UnknownHostException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

public class DBConnection {

	boolean success = false;
	MongoClient mongoClient;
	DB db;
	DBCollection collection;
	DBObject course;

	@SuppressWarnings("finally")
	public boolean obtainConnection(String host, int port) {
		try {
			mongoClient = new MongoClient(new ServerAddress(host, port));
			db = mongoClient.getDB("courses");
			collection = db.getCollection("courseList");
			// DBObject course = collection.findOne();
			// System.out.println(course);
			success = true;
		}
		catch (UnknownHostException e) {
			success = false;
			System.out.println(e);
			e.printStackTrace();
		}
		finally {
			return success;
		}
	}
	public CourseDetails getCoursebyId(String courseId){
		return getCourse("c_id",courseId);
	}
	
	public CourseDetails getCoursebyDesc(String courseDesc){
		return getCourse("c_descp",courseDesc);
	}
	
	public CourseDetails getCoursebyProf(String courseProf){
		return getCourse("c_prof",courseProf);
	}
	
	@SuppressWarnings("finally")
	public CourseDetails getCourse(String key, String value) {
		 CourseDetails course = new CourseDetails();
		// DBObject course = collection.findOne();
		// System.out.println(course);

		BasicDBObject query = new BasicDBObject(key, value);

		DBCursor cursor = collection.find(query);

		try {
			while (cursor.hasNext()) {
				
				
				String cour = cursor.next().toString();
				System.out.println(cour);
				JSONParser parser = new JSONParser();
				
				JSONObject jsonObject = (JSONObject) parser.parse(cour);

				String c_id = (String) jsonObject.get("c_id");
				course.setCourseId(c_id);

				String c_descp = (String) jsonObject.get("c_descp");
				course.setCourseDescp(c_descp);

				
				String c_prof = (String) jsonObject.get("c_prof");
				course.setCourseProf(c_prof);
			}
		}
		finally {
			cursor.close();
			return course;
		}
	}
}
