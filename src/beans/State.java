package beans;

public class State {
	private int id;
	String state;
	
	public State(int id, String state) {
		super();
		this.id = id;
		this.state = state;
	}
	
	public State() {
		// TODO Auto-generated constructor stub
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return state ;
	}
	
	
}
