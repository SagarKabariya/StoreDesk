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
import Register.SecondWindow;
import javax.swing.JDesktopPane;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
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



public class main extends JFrame {

	private JPanel contentPane;
	private JTextField textField_lg_ID;
	private JTextField textField_lg_pwd;
	PreparedStatement st=null,s2,s3,s4;
	ResultSet rs=null,r2,r3,r4;
	Connection conn=null;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main frame = new main(); 
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					 frame.setUndecorated(true);
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
	public main() {

		conn=ConnectionManager.getConnection();
		setResizable(false);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int wid = screenSize.width;
		int hei = screenSize.height;
		setTitle("Shiv Liquior & Wine");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0, screenSize.width, screenSize.height);		
		
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
		
		JButton btnCloseApplication = new JButton("Close Application");
		btnCloseApplication.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			System.exit(0);
			}
		});
		btnCloseApplication.setBounds((wid-200), (hei-100), 140, 56);
		login_panel.add(btnCloseApplication);
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(new Color(175, 238, 238));
		panel.setBounds(wid-((wid*65)/100), hei-((hei*60)/100), 578, 294);
		login_panel.add(panel);
		panel.setLayout(null);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setForeground(new Color(0, 0, 0));
		btnLogin.setBackground(new Color(255, 160, 122));
		btnLogin.setBounds(437, 141, 129, 71);
		panel.add(btnLogin);
		
		textField_lg_ID = new JTextField();
		textField_lg_ID.setFont(new Font("Tahoma", Font.PLAIN, 30));
		textField_lg_ID.setBounds(164, 112, 261, 59);
		panel.add(textField_lg_ID);
		textField_lg_ID.setColumns(10);
		
		textField_lg_pwd = new JTextField();
		textField_lg_pwd.setFont(new Font("Tahoma", Font.PLAIN, 30));
		textField_lg_pwd.setBounds(164, 182, 261, 59);
		panel.add(textField_lg_pwd);
		textField_lg_pwd.setColumns(10);
		
		JButton btnDemo = new JButton("demo");
		btnDemo.setBounds(447, 234, 89, 23);
		panel.add(btnDemo);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(12, 112, 155, 59);
		panel.add(lblId);
		lblId.setFont(new Font("Arial Black", Font.BOLD, 22));
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(12, 184, 155, 57);
		panel.add(lblPassword);
		lblPassword.setFont(new Font("Arial Black", Font.BOLD, 22));
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setFont(new Font("Caviar Dreams", Font.BOLD, 30));
		lblLogin.setBounds(12, 50, 554, 49);
		panel.add(lblLogin);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(wid-((wid*65)/100), hei-((hei*75)/100), 578, 145);
		login_panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblStoredesk = new JLabel("StoreDesk ");
		lblStoredesk.setBounds(12, 0, 554, 102);
		panel_1.add(lblStoredesk);
		lblStoredesk.setHorizontalAlignment(SwingConstants.CENTER);
		lblStoredesk.setFont(new Font("Trajan Pro", Font.PLAIN, 60));
		
		JLabel lblPointofsaleSoftwareCompany = new JLabel("Point-of-Sale Software Company");
		lblPointofsaleSoftwareCompany.setBounds(12, 91, 554, 42);
		panel_1.add(lblPointofsaleSoftwareCompany);
		lblPointofsaleSoftwareCompany.setHorizontalAlignment(SwingConstants.CENTER);
		lblPointofsaleSoftwareCompany.setFont(new Font("Caviar Dreams", Font.PLAIN, 35));
		btnDemo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				
				Deskpage dk = new Deskpage();
				desktopPane.add(dk);
				dk.setVisible(true);
				SecondWindow sw = new SecondWindow();
				
			}
		});
		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				conn=ConnectionManager.getConnection();
				try {
					String ename = textField_lg_ID.getText();
					String epassword = textField_lg_ID.getText();
					
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
