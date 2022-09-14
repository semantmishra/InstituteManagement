package beans;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;

public class EmployeeBean extends Parson {
	private EmployeeType employeeType;
	private java.sql.Date doj;
	private Subject subject;
	public EmployeeBean() {}
	public EmployeeBean(int id, String name, String fatherName, String mothername, String email, String mobile1,
			String mobile2, java.sql.Date dOB, int gender, Address address, InputStream photo,
			EmployeeType employeeType, java.sql.Date doj, Subject subject) {
		super(id, name, fatherName, mothername, email, mobile1, mobile2, dOB, gender, address, photo);
		this.employeeType = employeeType;
		this.doj = doj;
		this.subject = subject;
	}
	public EmployeeType getEmployeeType() {
		return employeeType;
	}
	public void setEmployeeType(EmployeeType employeeType) {
		this.employeeType = employeeType;
	}
	public java.sql.Date getDoj() {
		return doj;
	}
	public void setDoj(java.sql.Date doj) {
		this.doj = doj;
	}
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	
	
}
