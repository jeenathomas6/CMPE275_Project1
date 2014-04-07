package mongodbAppTest;

public class connectionClass {
	public static void main(String args[]) {
		System.out.println("This is MongoDbtest program!");
		CourseDetails course = new CourseDetails();
		DBConnection dbcon = new DBConnection();
		boolean success = dbcon.obtainConnection("localhost", 27017);

		if (success) {
			System.out.println("Connection Successful");
			course = dbcon.getCoursebyId("cmpe277");
			if (course != null) {
				System.out.println("After the getCourse()");
				System.out.println(course.getCourseId() +"\t" +course.getCourseDescp() +"\t"
				  +course.getCourseProf());
			}
		}
		else {
			System.out
					.println("Invalid Server or port name.\nConnection with the database could not be established.");
		}
	}
	
	
}
