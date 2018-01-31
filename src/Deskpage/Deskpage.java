package Deskpage;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import Employees.Employee_modi;
import Mix.Reciept_modi;
import Mix.Report_desk;
import Product.Product_History;
import Product.Product_Modify;
import Product.Product_Receive;
import Register.SecondWindow;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;

public class Deskpage extends JInternalFrame implements ActionListener {

	private SimpleDateFormat dateFormatDay = new SimpleDateFormat("h:mm a");
	private SimpleDateFormat dateFormatTime = new SimpleDateFormat("EEE, MMM d, ''yyyy");
	private JLabel clock;
	private JLabel Lday;
	int h;
	int w;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Deskpage frame = new Deskpage();
					frame.setVisible(true);
					frame.pack();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	 public void actionPerformed(ActionEvent e) {
		        updateClock();
	 }
	 private void updateClock() {
		 clock.setText(dateFormatTime.format(Calendar.getInstance().getTime()));
		 Lday.setText(dateFormatDay.format(Calendar.getInstance().getTime()));
	 }
	public Deskpage() {
		
		setClosable(true);
		 putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
		    getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		    ((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		 
		this.setBorder(null);
		    
		Rectangle winSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int wid = winSize.width;
		int hei = winSize.height;
		
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
		panel.setBounds(0, 0, wid, hei);
		desktopPane.add(panel);
		w=panel.getWidth();
		h=panel.getHeight();
		
		
		JButton btnText_1 = new JButton("Log Out");
		btnText_1.setBounds(screenSize.width-200, 11, 136, 50);
		btnText_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			dispose();			
			}
		});
		panel.setLayout(null);
		panel.add(btnText_1);
		new Timer(1000, this).start();
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.ORANGE);
		panel_1.setBounds((wid-423)-((wid*50)/100), 43, 846, 760);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label = new JLabel("StoreDesk ");
		label.setBounds(12, 13, 824, 102);
		panel_1.add(label);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Trajan Pro", Font.PLAIN, 60));
		
		JLabel label_1 = new JLabel("Point-of-Sale Software Company");
		label_1.setBounds(12, 94, 824, 42);
		panel_1.add(label_1);
		label_1.setBackground(Color.ORANGE);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Caviar Dreams", Font.PLAIN, 35));
		
		clock = new JLabel();
		clock.setBounds(12, 149, 824, 51);
		panel_1.add(clock);
		clock.setHorizontalAlignment(SwingConstants.CENTER);
		clock.setFont(new Font("Tahoma", Font.PLAIN, 35));
		Lday = new JLabel("");
		Lday.setBounds(12, 202, 824, 31);
		panel_1.add(Lday);
		Lday.setHorizontalAlignment(SwingConstants.CENTER);
		Lday.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		
		JButton btnText = new JButton("Register Desk");
		btnText.setBounds(287, 398, 266, 116);
		panel_1.add(btnText);
		btnText.setBackground(new Color(102, 204, 0));
		btnText.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 25));
		
		JButton btnRegister = new JButton("Employee");
		btnRegister.setBounds(12, 527, 266, 116);
		panel_1.add(btnRegister);
		btnRegister.setBackground(new Color(255, 102, 0));
		btnRegister.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 25));
		
		JButton btnProductHistory = new JButton("Product History");
		btnProductHistory.setBounds(12, 269, 266, 116);
		panel_1.add(btnProductHistory);
		btnProductHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Product_History ph = new Product_History();
				ph.setSize(w,h);
				desktopPane.add(ph);
				ph.setVisible(true);
			}
		});
		
		btnProductHistory.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 25));
		btnProductHistory.setBackground(SystemColor.textHighlight);
		
		JButton btnModify = new JButton("Modify Items");
		btnModify.setBounds(12, 398, 266, 116);
		panel_1.add(btnModify);
		btnModify.setBackground(new Color(102, 204, 153));
		btnModify.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 25));
		
		JButton btnItemDesk = new JButton("Item Receive");
		btnItemDesk.setBounds(287, 269, 266, 116);
		panel_1.add(btnItemDesk);
		btnItemDesk.setBackground(new Color(102, 204, 204));
		btnItemDesk.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 25));
		
		JButton btnInvoiceDesk = new JButton("Receipt Format");
		btnInvoiceDesk.setBounds(565, 401, 266, 110);
		panel_1.add(btnInvoiceDesk);
		btnInvoiceDesk.setBackground(new Color(102, 255, 204));
		btnInvoiceDesk.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 25));
		
		JButton btnReportDesk = new JButton("Report Desk");
		btnReportDesk.setBounds(565, 269, 266, 116);
		panel_1.add(btnReportDesk);
		btnReportDesk.setBackground(new Color(204, 51, 0));
		btnReportDesk.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 25));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(1, hei-30, wid-20, 30);
		panel.add(panel_2,BorderLayout.LINE_END);
		btnReportDesk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Report_desk rd = new Report_desk();
				desktopPane.add(rd);
				rd.setVisible(true);
			}
		});
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{btnText, getContentPane(), desktopPane, panel, btnText_1, btnInvoiceDesk, btnReportDesk, btnItemDesk, btnModify, btnRegister, label, label_1}));
		btnInvoiceDesk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Reciept_modi rm = new Reciept_modi();
				desktopPane.add(rm);
				rm.setVisible(true);
			}
		});
		btnItemDesk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			Product_Receive pr= new Product_Receive();
			desktopPane.add(pr);  
			pr.setVisible(true);
			
			}
		});
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Product_Modify pm = new Product_Modify();
				pm.setVisible(true);
			}
		});
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Employee_modi em = new Employee_modi();
				desktopPane.add(em);
				em.setVisible(true);
			}
		});
		btnText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SecondWindow sw = new SecondWindow();
				sw.setVisible(true);
			}
		});
		
		updateClock();

	}
}
