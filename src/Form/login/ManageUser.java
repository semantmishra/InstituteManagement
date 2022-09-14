package Form.login;

import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;
import beans.RegisterBean;
import beans.UserType;
import dao.RegisterDao;

public class ManageUser extends JFrame {
			public JPopupMenu enqueryMenu,exportMenu;
			public JButton addNewUser;
			public JToolBar bar;
			public JTable table;
			//public JMenuItem deleteRecord,updateRecord,printPreview,pdf,csv,html,excel;
			
			public ManageUser(){
				super("Manage User");
				initComponet();	
				event();
				loadData();
			}
			private void loadData() {
				List<RegisterBean> lists = new RegisterDao().getAll();
				DefaultTableModel model = new DefaultTableModel();
				model.addColumn("Id");
				model.addColumn("Name");
				model.addColumn("Mobile");
				model.addColumn("Email");
				model.addColumn("UserType");
				model.addColumn("isActive");
				for (RegisterBean registerBean : lists) {
					Object data[]= {
							registerBean .getId(),
							registerBean.getName(),
							registerBean.getMobile(),
							registerBean .getEmail(),
							registerBean .getUserType()==UserType.ADMIN ?"Admin":"Super Admin" ,
							registerBean.getIsAction()==1?true:false,					
					};
					model.addRow(data);
				}
				table.setModel(model);
			}
			private void event() {
				addNewUser.addActionListener(e -> new RegisterForm1() );
			}
			private void initComponet() {
				bar = new JToolBar();
				table = utilis.Component.getJtable();

				bar.setBounds(0, 0, 1000, 50);
				addNewUser = new JButton("Create Account");
				table.setBounds(50, 100, 880, 450);
				enqueryMenu = new JPopupMenu();
				bar.add(addNewUser);
//				deleteRecord = new JMenuItem("Delete Record",Icons.smallDeleteIcon());
//				updateRecord = new JMenuItem("Update Record");
//				printPreview = new JMenuItem("Print Preview");
//				enqueryMenu.add(deleteRecord);
//				enqueryMenu.addSeparator();
//				enqueryMenu.add(updateRecord);
//				enqueryMenu.addSeparator();
//				enqueryMenu.add(printPreview);
				
//				exportMenu = new JPopupMenu();
//				csv =  new JMenuItem("CSV");
//				html= new JMenuItem("HTML");
//				excel = new JMenuItem("Excel");
//				pdf =  new JMenuItem("PDF");
//				exportMenu .add(pdf);
//				exportMenu .add(csv);
//				exportMenu .add(html);
//				exportMenu .add(excel);
				
//				export = new JButton("Export");
				
				
//				export .addActionListener(sl);
//				deleteRecord.addActionListener(sl );
//				updateRecord.addActionListener(sl );
//				printPreview.addActionListener(sl );
//				export.addActionListener(sl );
//				pdf.addActionListener(sl );
//				excel.addActionListener(sl );
//				html.addActionListener(sl );
//				csv.addActionListener(sl );
//				table.addMouseListener(sl);
//				addNewEmployee.addActionListener(sl);
//				bar.add(addNewEmployee);
//				bar.add(export);
//				bar.setFloatable(false);
//			
				this.add(bar);
				table.setBounds(200,100, 700 , 300);
				JScrollPane jsp = new JScrollPane(table);
				jsp.setBounds(200,100, 700 , 300);
				this.add(jsp);
				this.setLayout(null);
				this.setSize(1000, 500);
				this.setLocation(200, 50);
				this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				this.setVisible(true);
			}

}
