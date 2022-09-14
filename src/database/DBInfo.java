package database;
import java.sql.DriverManager;
public interface DBInfo {
	
		
		//conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sem","root","");
		public static final String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";
		public static final String DABABASE_URL = "jdbc:mysql://localhost:3306/jdbcgui";
		public static final String DATABASE_USER = "root";
		public static final String DATABASE_PASSWORD = "root";
}
