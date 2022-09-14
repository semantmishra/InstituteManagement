package beans;
public class Course {
	int id,active;
	String name;
	float fee;
	
	public Course() {}
	
	public Course(int id, int active, String name, float fee) {
		super();
		this.id = id;
		this.active = active;
		this.name = name;
		this.fee = fee;
	}
	


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getFee() {
		return fee;
	}

	public void setFee(float fee) {
		this.fee = fee;
	}

	@Override
	public String toString() {
		return  name ;
	}

}
