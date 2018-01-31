package Main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Connect.ConnectionManager;
import Deskpage.Deskpage;
import Product.Product_Modify;
import Register.SecondWindow;
import javax.swing.JDesktopPane;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.SwingConstants;



public class Storedesk extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_lg_ID;
	private JTextField textField_lg_pwd;
	PreparedStatement st=null,s2,s3,s4;
	ResultSet rs=null,r2,r3,r4;
	Connection conn=null;
	int w,h;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Storedesk frame = new Storedesk(); 
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					//frame.setOpacity(12);
					frame.setVisible(true);
					//frame.setUndecorated(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Storedesk() {

		h=this.getHeight();
		w=this.getWidth();
		conn=ConnectionManager.getConnection();
		setResizable(false);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int wid = screenSize.width;
		int hei = screenSize.height;
		hei=hei-50;
		setTitle("StoreDesk POS System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0, wid, hei);		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JDesktopPane desktopPane = new JDesktopPane();
		contentPane.add(desktopPane, BorderLayout.CENTER);
		desktopPane.setLayout(new CardLayout(0, 0));
		
		JPanel login_panel = new JPanel();
		login_panel.setForeground(new Color(173, 216, 230));
		login_panel.setBackground(new Color(175, 238, 238));
		desktopPane.add(login_panel, "name_304373847137140");
		login_panel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(new Color(175, 238, 238));
		panel.setBounds((wid-(wid*50)/100)-298, 266, 596, 429);
		login_panel.add(panel);
		panel.setLayout(null);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setForeground(new Color(0, 0, 0));
		btnLogin.setBackground(new Color(255, 160, 122));
		btnLogin.setBounds(437, 283, 147, 71);
		panel.add(btnLogin);
		
		textField_lg_ID = new JTextField();
		textField_lg_ID.setFont(new Font("Tahoma", Font.PLAIN, 30));
		textField_lg_ID.setBounds(164, 282, 261, 59);
		panel.add(textField_lg_ID);
		textField_lg_ID.setColumns(10);
		
		textField_lg_pwd = new JTextField();
		textField_lg_pwd.setFont(new Font("Tahoma", Font.PLAIN, 30));
		textField_lg_pwd.setBounds(164, 352, 261, 59);
		panel.add(textField_lg_pwd);
		textField_lg_pwd.setColumns(10);
		
		JButton btnDemo = new JButton("demo");
		btnDemo.setBounds(477, 184, 89, 23);
		panel.add(btnDemo);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(12, 282, 155, 59);
		panel.add(lblId);
		lblId.setFont(new Font("Arial Black", Font.BOLD, 22));
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(12, 354, 155, 57);
		panel.add(lblPassword);
		lblPassword.setFont(new Font("Arial Black", Font.BOLD, 22));
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setFont(new Font("Caviar Dreams", Font.BOLD, 30));
		lblLogin.setBounds(12, 220, 554, 49);
		panel.add(lblLogin);
		
		JLabel lblStoredesk = new JLabel("StoreDesk ");
		lblStoredesk.setBounds(12, 13, 554, 102);
		panel.add(lblStoredesk);
		lblStoredesk.setHorizontalAlignment(SwingConstants.CENTER);
		lblStoredesk.setFont(new Font("Trajan Pro", Font.PLAIN, 60));
		
		JLabel lblPointofsaleSoftwareCompany = new JLabel("Point-of-Sale Software Company");
		lblPointofsaleSoftwareCompany.setBounds(12, 104, 554, 42);
		panel.add(lblPointofsaleSoftwareCompany);
		lblPointofsaleSoftwareCompany.setHorizontalAlignment(SwingConstants.CENTER);
		lblPointofsaleSoftwareCompany.setFont(new Font("Caviar Dreams", Font.PLAIN, 35));
		
		JButton btnCloseApplication = new JButton("Close Application");
		btnCloseApplication.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCloseApplication.setBackground(new Color(255, 0, 0));
		btnCloseApplication.setBounds(437, 367, 147, 44);
		panel.add(btnCloseApplication);
		
		JButton btnOpen = new JButton("open");
		btnOpen.setBounds(12, 183, 97, 25);
		panel.add(btnOpen);
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Product_Modify em = new Product_Modify();
				desktopPane.add(em);
				em.setVisible(true);
			}
		});
		btnCloseApplication.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			System.exit(0);
			}
		});
		btnDemo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				
				Deskpage dk = new Deskpage();
				//dk.setLayout(manager);
				dk.setSize(w,h);
				desktopPane.add(dk);
				dk.setVisible(true);
				new SecondWindow();
				
			}
		});
		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				conn=ConnectionManager.getConnection();
				try {
					String ename = textField_lg_ID.getText();
					textField_lg_ID.getText();
					
						s2=conn.prepareStatement("SELECT count(`Employee_id`),Emp_name FROM `employee` WHERE emp_name='"+ename+"'");
					r2=s2.executeQuery();
					r2.next();
					int c=r2.getInt(1);
					if(c<1)
					{	
						JOptionPane.showMessageDialog(null, "No Record found");
					}
					else
					{
						Deskpage dk = new Deskpage();
						desktopPane.add(dk);
						dk.setVisible(true);
						SecondWindow sw = new SecondWindow();
						sw.setVisible(true);
					}
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}				
			}
		});
		
		
	}
}
