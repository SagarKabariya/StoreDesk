package Deskpage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import Employees.Employee_modi;
import Mix.Reciept_modi;
import Mix.Report_desk;
import Product.Product_Modify;
import Product.Product_Receive;
import Register.SecondWindow;

public class Deskpage extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Deskpage frame = new Deskpage();
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
	public Deskpage() {
		
		 putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
		    getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		    ((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		    this.setBorder(null);
		    
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int wid_button = screenSize.width/10;
		int hei_button = 50;
		setFrameIcon(null);
		setBorder(null);
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, screenSize.width, screenSize.height);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(new Color(0, 0, 0));
		getContentPane().add(desktopPane, BorderLayout.CENTER);
		desktopPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.ORANGE);
		panel.setBounds(0, 0, screenSize.width, screenSize.height);
		desktopPane.add(panel);
		
		JButton btnText_1 = new JButton("Log Out");
		btnText_1.setBounds(screenSize.width-200, 11, 136, 50);
		btnText_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			dispose();			
			}
		});
		panel.setLayout(null);
		panel.add(btnText_1);
		
		
		JButton btnText = new JButton("Register Desk");
		btnText.setBounds(10, 11, 136, 50);
		btnText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SecondWindow sw = new SecondWindow();
				sw.setVisible(true);
			}
		});
		panel.add(btnText);
		
		JButton btnInvoiceDesk = new JButton("Receipt Format");
		btnInvoiceDesk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Reciept_modi rm = new Reciept_modi();
				desktopPane.add(rm);
				rm.setVisible(true);
			}
		});
		btnInvoiceDesk.setBounds(156, 67, 136, 50);
		panel.add(btnInvoiceDesk);
		
		JButton btnReportDesk = new JButton("Report Desk");
		btnReportDesk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Report_desk rd = new Report_desk();
				desktopPane.add(rd);
				rd.setVisible(true);
			}
		});
		btnReportDesk.setBounds(302, 67, 136, 50);
		panel.add(btnReportDesk);
		
		JButton btnItemDesk = new JButton("Item Receive");
		btnItemDesk.setBounds(10, 128, 136, 50);
		btnItemDesk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			Product_Receive pr= new Product_Receive();
			desktopPane.add(pr);  
			pr.setVisible(true);
			
			}
		});
		panel.add(btnItemDesk);
		
		JButton btnModify = new JButton("Modify Items");
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Product_Modify pm = new Product_Modify();
				desktopPane.add(pm);
				pm.setVisible(true);
			}
		});
		btnModify.setBounds(10, 67, 136, 50);
		panel.add(btnModify);
		
		JButton btnRegister = new JButton("Employee");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Employee_modi em = new Employee_modi();
				desktopPane.add(em);
				em.setVisible(true);
			}
		});
		btnRegister.setBounds(156, 128, 136, 50);
		panel.add(btnRegister);
		
		JButton btnQueryBox = new JButton("Query Box");
		btnQueryBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnQueryBox.setBounds(156, 11, 136, 50);
		panel.add(btnQueryBox);
		
		JButton btnDayClose = new JButton("Day Close");
		btnDayClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnDayClose.setBackground(SystemColor.inactiveCaption);
		btnDayClose.setBounds(302, 128, 136, 50);
		panel.add(btnDayClose);
		
		JButton btnMdReport = new JButton("MD Report");
		btnMdReport.setBackground(SystemColor.inactiveCaption);
		btnMdReport.setBounds(302, 11, 136, 50);
		panel.add(btnMdReport);

	}
}
