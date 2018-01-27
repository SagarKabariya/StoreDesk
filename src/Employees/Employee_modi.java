package Employees;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

public class Employee_modi extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Employee_modi frame = new Employee_modi();
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
	public Employee_modi() {
		setBounds(100, 100, 450, 300);

	}

}
