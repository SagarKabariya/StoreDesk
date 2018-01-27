package Mix;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

public class Report_desk extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Report_desk frame = new Report_desk();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Report_desk() {
		setBounds(100, 100, 450, 300);

	}

}
