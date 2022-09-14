package utilis;

import java.awt.Image;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

public interface Icons {
	public static final int HTML = 1;
	public static final int CSV = 2;
	
	
	String camera = System.getProperty("user.dir")+"/src/icon/camera.jfif";
	String first = System.getProperty("user.dir")+"/src/icon/first.png";
	String next = System.getProperty("user.dir")+"/src/icon/next.png";
	String last = System.getProperty("user.dir")+"/src/icon/last.png";
	String previous = System.getProperty("user.dir")+"/src/icon/previous.png";
	String search = System.getProperty("user.dir")+"/src/icon/search.png";
	
	
	String deleteIcon = System.getProperty("user.dir")+"/src/icon/Delete.png";
	String smallDeleteIcon = System.getProperty("user.dir")+"/src/icon/Delete_smal.png";
	String NO_IMAGE = System.getProperty("user.dir")+"/src/icon/no-image.jpg";
	String addIcon= System.getProperty("user.dir")+"/src/icon/add.png";
	
	String successIcon = System.getProperty("user.dir")+"/src/icon/check.png";
	public static InputStream openImage(JLabel label) {
		InputStream file = null;
		JFileChooser chooser= new JFileChooser();
		if( chooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION )
		{
			try {
				file =new  FileInputStream( chooser.getSelectedFile().getAbsoluteFile());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 label.setIcon(new ImageIcon(Icons.getImage(chooser.getSelectedFile().getAbsolutePath()).getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT)));
		}
		return file;
	}
	
	public static void openImage(JLabel label,String fileName) {
	
		if( fileName!=null )
		{
			 label.setIcon(new ImageIcon(Icons.getImage( fileName ).getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT)));
		}
		
	}
	static ImageIcon smallDeleteIcon ( ) {
		return new ImageIcon(smallDeleteIcon);
	}

	static ImageIcon getImage ( String inconPath) {
		return new ImageIcon(inconPath);
	}
	static void getImage (JLabel label, String inconPath) {
		label.setIcon(new ImageIcon(Icons.getImage(inconPath).getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT)));
	}
	
	static void getImage (JLabel label, byte[] icon) {
		label.setIcon(new ImageIcon(new ImageIcon(icon).getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT)));
	}
	
	static ImageIcon getImage (byte[] icon) {
		return new ImageIcon(icon);		
	}
	
	
	static ImageIcon noImage() {
		return new ImageIcon(NO_IMAGE);
	}

}
