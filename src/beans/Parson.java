package beans;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;

public abstract class Parson {
private int id;
protected  String  name,fatherName,mothername,Email,mobile1,mobile2;
protected java.sql.Date DOB;
int gender;
protected Address address;

protected InputStream photo;
public Parson() {}
public Parson(int id, String name, String fatherName, String mothername, String email, String mobile1, String mobile2,
		java.sql.Date dOB, int gender, Address address, InputStream photo) {
	super();
	this.id = id;
	this.name = name;
	this.fatherName = fatherName;
	this.mothername = mothername;
	Email = email;
	this.mobile1 = mobile1;
	this.mobile2 = mobile2;
	DOB = dOB;
	this.gender = gender;
	this.address = address;
	this.photo = photo;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getFatherName() {
	return fatherName;
}
public void setFatherName(String fatherName) {
	this.fatherName = fatherName;
}
public String getMothername() {
	return mothername;
}
public void setMothername(String mothername) {
	this.mothername = mothername;
}
public String getEmail() {
	return Email;
}
public void setEmail(String email) {
	Email = email;
}
public String getMobile1() {
	return mobile1;
}
public void setMobile1(String mobile1) {
	this.mobile1 = mobile1;
}
public String getMobile2() {
	return mobile2;
}
public void setMobile2(String mobile2) {
	this.mobile2 = mobile2;
}
public java.sql.Date getDOB() {
	return DOB;
}
public void setDOB(java.sql.Date dOB) {
	DOB = dOB;
}
public int getGender() {
	return gender;
}
public void setGender(int gender) {
	this.gender = gender;
}
public Address getAddress() {
	return address;
}
public void setAddress(Address address) {
	this.address = address;
}
public InputStream getPhoto() {
	return photo;
}
public void setPhoto(InputStream photo) {
	this.photo = photo;
}







}
