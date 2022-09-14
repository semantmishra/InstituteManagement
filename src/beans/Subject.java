package beans;

public class Subject {
	private int id,course_Id;
	private String subject;
	public Subject(int id, int course_Id, String subject) {
		super();
		this.id = id;
		this.course_Id = course_Id;
		this.subject = subject;
	}
	public Subject() {}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCourse_Id() {
		return course_Id;
	}
	public void setCourse_Id(int course_Id) {
		this.course_Id = course_Id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	
}
