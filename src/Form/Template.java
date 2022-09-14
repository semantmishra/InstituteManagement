package Form;
import javax.swing.JFrame;
public abstract class Template extends JFrame {
private boolean isOk;
	public Template(String title) {
		super(title);
		this.setLayout(null);
		this.setSize(1000, 500);
		this.setLocation(200, 50);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);this.setVisible(true);
	}
	public boolean isOk() {
		return isOk;
	}
	public void setOk(boolean isOk) {
		this.isOk = isOk;
	}
	
}
