package beans;

public class EmployeeType {
private	int id;
private String EmpType;
private float salaray;
public EmployeeType() {}
public EmployeeType(int id, String empType, float salaray) {
	super();
	this.id = id;
	EmpType = empType;
	this.salaray = salaray;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getEmpType() {
	return EmpType;
}
public void setEmpType(String empType) {
	EmpType = empType;
}
public float getSalaray() {
	return salaray;
}
public void setSalaray(float salaray) {
	this.salaray = salaray;
}
@Override
	public String toString() {
		// TODO Auto-generated method stub
		return EmpType;
	}
}
