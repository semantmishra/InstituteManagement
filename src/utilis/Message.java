package utilis;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public interface Message {
	
	public static void success(Component com,String msg,String title) {
		JOptionPane.showMessageDialog(com, msg,title , JOptionPane.OK_OPTION, new ImageIcon(Icons.successIcon));
	}
	
	public static void error(Component com,String msg,String title) {
		JOptionPane.showMessageDialog(com, msg,title , JOptionPane.OK_OPTION, new ImageIcon(Icons.deleteIcon));
	}

}
