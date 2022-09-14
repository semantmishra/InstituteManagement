package beans;

public class SecurityQustion {
	private int id;
	private String qustion;
	public SecurityQustion() {}
	public SecurityQustion(int id, String qustion) {
		super();
		this.id = id;
		this.qustion = qustion;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getQustion() {
		return qustion;
	}
	public void setQustion(String qustion) {
		this.qustion = qustion;
	}
	
	@Override
	public String toString() {
		return qustion;
	}
	
}
