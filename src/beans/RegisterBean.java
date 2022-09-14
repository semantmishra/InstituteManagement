package beans;

public class RegisterBean {
	private int id,isAction,userType;
	private SecurityQustion securityQustion;
	private String name,email,mobile,password,securityA;
	public RegisterBean(int id, int gender, int isAction, SecurityQustion securityQustion, String name, String email,
			String mobile, String password, int userType, String securityA) {
		super();
		this.id = id;
		this.isAction = isAction;
		this.securityQustion = securityQustion;
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.password = password;
		this.userType = userType;
		this.securityA = securityA;
	}
	
	public RegisterBean() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIsAction() {
		return isAction;
	}
	public void setIsAction(int isAction) {
		this.isAction = isAction;
	}
	public SecurityQustion getSecurityQustion() {
		return securityQustion;
	}
	public void setSecurityQustion(SecurityQustion securityQustion) {
		this.securityQustion = securityQustion;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public String getSecurityA() {
		return securityA;
	}
	public void setSecurityA(String securityA) {
		this.securityA = securityA;
	}
	
}
