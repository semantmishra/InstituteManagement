package dao;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import utilis.Icons;

public class MyDao {
	public boolean delete( String className,JTable table,String sql ) {
		int confirm = JOptionPane.showConfirmDialog(table, "Are you sure Delete this Record", "Delete Record", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE,new ImageIcon(Icons.deleteIcon));//
		
		if(confirm==JOptionPane.YES_OPTION) {
			int index = table.getSelectedRow();
			Class c;
			Dao dao=null;
			try {
				c = Class.forName(className);
			
			 dao = (Dao)c.newInstance();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int delId = Integer.valueOf(table.getValueAt(index, 0).toString()); 
			if(dao.delete(delId,sql)) {
				JOptionPane.showMessageDialog(table, "Record Delete Success . . .","Delete Record" , JOptionPane.OK_OPTION, new ImageIcon(Icons.successIcon));
				return true;
			}else {
				JOptionPane.showMessageDialog(table, "Record Delete faild . . .","Delete Record" , JOptionPane.OK_OPTION, new ImageIcon(Icons.deleteIcon));
				return false;
			}
			
		}
		return false;
	}
}
