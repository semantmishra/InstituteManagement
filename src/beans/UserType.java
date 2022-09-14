package beans;

public class UserType {
	public static final int ADMIN = 1,SUPER_ADMIN=2;
	private int id ;
	private String dsec;
	public UserType(int id, String dsec) {
		super();
		this.id = id;
		this.dsec = dsec;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDsec() {
		return dsec;
	}
	public void setDsec(String dsec) {
		this.dsec = dsec;
	}
	
	@Override
	public String toString() {
		return dsec;
	}
}
