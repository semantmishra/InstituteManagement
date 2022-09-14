package beans;

public class Address {
	private String adderss,pincode;
	private State state;
	private Cities district;

	public Address() {}

	public Address(String adderss, String pincode, State state, Cities district) {
		super();
		this.adderss = adderss;
		this.pincode = pincode;
		this.state = state;
		this.district = district;
	}

	public String getAdderss() {
		return adderss;
	}

	public void setAdderss(String adderss) {
		this.adderss = adderss;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Cities getDistrict() {
		return district;
	}

	public void setDistrict(Cities district) {
		this.district = district;
	}

	@Override
	public String toString() {
		return adderss+" "+pincode+" "+state+" "+district;
	}
	
	
}
