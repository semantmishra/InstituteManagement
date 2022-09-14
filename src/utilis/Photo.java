package utilis;

import java.io.IOException;
import java.io.InputStream;

public class Photo {
	
	public byte[] getByte(InputStream in) {
		try {
			byte[] b = new byte[in.available()];
			in.read(b);
			return b;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
