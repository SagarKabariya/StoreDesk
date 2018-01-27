package Mix;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

public class Reciept_modi extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reciept_modi frame = new Reciept_modi();
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
	public Reciept_modi() {
		setBounds(100, 100, 450, 300);

	}

}
