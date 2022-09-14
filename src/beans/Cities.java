package beans;

public class Cities {
	private int id,state_id;
	private String cities;
	public Cities() {
		// TODO Auto-generated constructor stub
	}
	public Cities(int id, int state_id, String cities) {
		super();
		this.id = id;
		this.state_id = state_id;
		this.cities = cities;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getState_id() {
		return state_id;
	}
	public void setState_id(int state_id) {
		this.state_id = state_id;
	}
	public String getCities() {
		return cities;
	}
	public void setCities(String cities) {
		this.cities = cities;
	}
	@Override
	public String toString() {
		return cities ;
	}	

}
