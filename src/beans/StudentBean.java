package beans;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class StudentBean extends Parson {

private java.sql.Date doa;
private SessionBean session;
private Course course;
private List<FeeBean> fees;
private List<Document> documents;
String myId;
public StudentBean() {
	super();
}
public StudentBean(int id, String name, String fatherName, String mothername, String email, String mobile1,
		String mobile2, java.sql.Date dOB, int gender, Address address, InputStream photo, java.sql.Date doa,
		SessionBean session, Course course, List<FeeBean> fees, List<Document> documents) {
	super(id, name, fatherName, mothername, email, mobile1, mobile2, dOB, gender, address, photo);
	this.doa = doa;
	this.session = session;
	this.course = course;
	this.fees = fees;
	this.documents = documents;
}

public String getMyId() {
	return myId;
}
public void setMyId(String myId) {
	this.myId = myId;
}
public java.sql.Date getDoa() {
	return doa;
}
public void setDoa(java.sql.Date doa) {
	this.doa = doa;
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
public List<FeeBean> getFees() {
	return fees;
}
public void setFees(List<FeeBean> fees) {
	this.fees = fees;
}
public List<Document> getDocuments() {
	return documents;
}
public void setDocuments(List<Document> documents) {
	this.documents = documents;
}
}
