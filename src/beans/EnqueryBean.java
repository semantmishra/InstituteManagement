package beans;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;

public class EnqueryBean extends Parson {
	private int id;
	private SessionBean session;
	private Course course;
	public EnqueryBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EnqueryBean(int id, String name, String fatherName, String mothername, String email, String mobile1,
			String mobile2, java.sql.Date dOB, int gender, Address address, InputStream photo, int id2,
			SessionBean session, Course course) {
		super(id, name, fatherName, mothername, email, mobile1, mobile2, dOB, gender, address, photo);
		id = id2;
		this.session = session;
		this.course = course;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public SessionBean getSession() {
		return session;
	}
	public void setSession(SessionBean session) {
		this.session = session;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	
	
}
