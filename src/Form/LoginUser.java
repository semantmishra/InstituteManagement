package Form;

import beans.RegisterBean;

public class LoginUser {
	public static RegisterBean session;
	
	public static RegisterBean getSession() {
		return session;
	}
	
	public static void setSession(RegisterBean session) {
		LoginUser.session = session;
	}
}
