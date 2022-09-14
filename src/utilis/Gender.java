package utilis;

public interface Gender{
	int MALE=1,FEMALE=2;
	
	public static String getGender(int gender) {
		return gender==Gender.MALE?"Male":"Female";
	}
}


