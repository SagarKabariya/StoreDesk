package LogInOut;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Statement;

import Connect.ConnectionManager;

import javax.swing.JDesktopPane;
import java.awt.CardLayout;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JToggleButton;
import java.awt.Window.Type;

public class Logindailog extends JDialog {
	private JTextField textField;
	public int id;
	JLabel lblend;
	JLabel lblstart;
	JLabel lblname;
	JPanel panel_main;
	JPanel panel_time;
	JToggleButton onoff;
	JButton btnSignOut;
	JButton btnSignIn;
	Connection conn=null;
	PreparedStatement s1=null,s2,s3,s4;
	ResultSet r1=null,r2,r3,r4;
	Statement st2,st3;
	JLabel lblPleaseEnterId;
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Calendar cal = Calendar.getInstance();
	Date today = new Date();
	java.util.Date date = new java.util.Date();
	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	public static void main(String[] args) {
		try {
			Logindailog dialog = new Logindailog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setAlwaysOnTop(true);
			dialog.setFocusable(true);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Logindailog() {
		super((java.awt.Frame) null, true);
		setResizable(false);
		setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
		setAlwaysOnTop(true);
		try{
	    	conn = (Connection) ConnectionManager.getConnection();
				 }catch(Exception e1){
					 JOptionPane.showMessageDialog(null, "Daabase not Connected,Close Software and Enter Again");		
					 dispose();
					 }
		
		setTitle("User/Employee ID");
		setBounds(100, 100, 322, 478);
		getContentPane().setLayout(new BorderLayout());
		{
			JDesktopPane desktopPane = new JDesktopPane();
			getContentPane().add(desktopPane, BorderLayout.CENTER);
			desktopPane.setLayout(new CardLayout(0, 0));
			
			panel_main = new JPanel();
			desktopPane.add(panel_main, "name_76845052445468");
			panel_main.setLayout(null);
			
			textField = new JTextField();
			textField.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					check();
				}
			});
			textField.setFont(new Font("Arial", Font.BOLD, 16));
			textField.setBounds(10, 11, 188, 55);
			panel_main.add(textField);
			textField.setColumns(10);
			
			JButton btnOk = new JButton("Ok");
			btnOk.setFont(new Font("Arial", Font.BOLD, 14));
			btnOk.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					check();
				}
			});
			btnOk.setBounds(162, 373, 135, 55);
			panel_main.add(btnOk);
			
			JButton btnNewButton = new JButton("1");
			btnNewButton.setFont(new Font("Arial", Font.BOLD, 14));
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				addno("1");
				}
			});
			btnNewButton.setBounds(10, 109, 89, 55);
			panel_main.add(btnNewButton);
			
			JButton button = new JButton("2");
			button.setFont(new Font("Arial", Font.BOLD, 14));
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					addno("2");
				}
			});
			button.setBounds(109, 109, 89, 55);
			panel_main.add(button);
			
			JButton button_1 = new JButton("3");
			button_1.setFont(new Font("Arial", Font.BOLD, 14));
			button_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					addno("3");
				}
			});
			button_1.setBounds(208, 109, 89, 55);
			panel_main.add(button_1);
			
			JButton button_2 = new JButton("4");
			button_2.setFont(new Font("Arial", Font.BOLD, 14));
			button_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					addno("4");
				}
			});
			button_2.setBounds(10, 173, 89, 55);
			panel_main.add(button_2);
			
			JButton button_3 = new JButton("5");
			button_3.setFont(new Font("Arial", Font.BOLD, 14));
			button_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					addno("5");
				}
			});
			button_3.setBounds(109, 175, 89, 55);
			panel_main.add(button_3);
			
			JButton button_4 = new JButton("6");
			button_4.setFont(new Font("Arial", Font.BOLD, 14));
			button_4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					addno("6");
				}
			});
			button_4.setBounds(208, 175, 89, 55);
			panel_main.add(button_4);
			
			JButton button_5 = new JButton("7");
			button_5.setFont(new Font("Arial", Font.BOLD, 14));
			button_5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					addno("7");
				}
			});
			button_5.setBounds(10, 239, 89, 55);
			panel_main.add(button_5);
			
			JButton button_6 = new JButton("8");
			button_6.setFont(new Font("Arial", Font.BOLD, 14));
			button_6.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					addno("8");
				}
			});
			button_6.setBounds(109, 241, 89, 55);
			panel_main.add(button_6);
			
			JButton button_7 = new JButton("9");
			button_7.setFont(new Font("Arial", Font.BOLD, 14));
			button_7.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					addno("9");
				}
			});
			button_7.setBounds(208, 241, 89, 55);
			panel_main.add(button_7);
			
			JButton button_8 = new JButton("0");
			button_8.setFont(new Font("Arial", Font.BOLD, 14));
			button_8.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					addno("0");
				}
			});
			button_8.setBounds(109, 307, 89, 55);
			panel_main.add(button_8);
			
			lblPleaseEnterId = new JLabel("Please Enter ID");
			lblPleaseEnterId.setForeground(Color.RED);
			lblPleaseEnterId.setBounds(10, 77, 287, 21);
			panel_main.add(lblPleaseEnterId);
			
			onoff = new JToggleButton("Timing");
			onoff.setFont(new Font("Arial", Font.BOLD, 14));
			onoff.setBounds(10, 373, 135, 55);
			panel_main.add(onoff);
			
			JButton btnNewButton_1 = new JButton("Clear");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					textField.setText("");
				}
			});
			btnNewButton_1.setBounds(208, 11, 89, 55);
			panel_main.add(btnNewButton_1);
			lblPleaseEnterId.setVisible(false);
			
			panel_time = new JPanel();
			desktopPane.add(panel_time, "name_76857593023235");
			panel_time.setLayout(null);
			
			JLabel lblEmployeeName = new JLabel("Employee Name");
			lblEmployeeName.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblEmployeeName.setBounds(10, 11, 108, 19);
			panel_time.add(lblEmployeeName);
			
			lblname = new JLabel("");
			lblname.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblname.setBounds(165, 11, 108, 19);
			panel_time.add(lblname);
			
			JLabel lblNewLabel = new JLabel("Timing");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNewLabel.setBounds(10, 68, 263, 25);
			panel_time.add(lblNewLabel);
			
			
			btnSignIn = new JButton("Sign In");
			btnSignIn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					signinfun();
					
				}
			});
			btnSignIn.setBounds(29, 222, 89, 48);
			panel_time.add(btnSignIn);
			btnSignIn.setVisible(false);
			
			btnSignOut = new JButton("Sign Out");
			btnSignOut.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					signoutfun();
					
				}
			});
			btnSignOut.setBounds(184, 222, 89, 48);
			panel_time.add(btnSignOut);
			btnSignOut.setVisible(false);
			
			JLabel lblNewLabel_1 = new JLabel("Start Timing");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblNewLabel_1.setBounds(10, 104, 96, 25);
			panel_time.add(lblNewLabel_1);
			
			JLabel lblEndtiming = new JLabel("End Timing");
			lblEndtiming.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblEndtiming.setBounds(10, 140, 96, 25);
			panel_time.add(lblEndtiming);
			
			lblstart = new JLabel("");
			lblstart.setBounds(116, 104, 175, 25);
			panel_time.add(lblstart);
			
			lblend = new JLabel("");
			lblend.setBounds(116, 140, 175, 25);
			panel_time.add(lblend);
		}
	}

	protected void signoutfun() {
		try {
			s1=conn.prepareStatement("UPDATE `employee_session` SET `End_time`='"+sdf.format(date)+"',`status`=1 "
					+ "WHERE `Employee_id` ="+Integer.valueOf(textField.getText()));
			s1.executeUpdate();
			
			s1=conn.prepareStatement("UPDATE `employee_session` SET `affected`=0");
			s1.executeUpdate();
			
			s1=conn.prepareStatement("UPDATE `employee_session` SET `affected`=1 WHERE `Employee_id` ="+Integer.valueOf(textField.getText()));
			s1.executeUpdate();
			
			
			panel_time.setVisible(false);
			panel_main.setVisible(true);
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	protected void signinfun() {
		try {
			s1=conn.prepareStatement("INSERT INTO `employee_session`(`Employee_id`, `Date`, `Start_Time`, `status`) "
					+ "VALUES ("+Integer.valueOf(textField.getText())+",'"+dateFormat.format(date)+"','"+sdf.format(date)+"',0)");
			s1.execute();
			s1=conn.prepareStatement("UPDATE `employee_session` SET `affected`=0");
			s1.executeUpdate();
			s1=conn.prepareStatement("UPDATE `employee_session` SET `affected`=1 WHERE `Employee_id` ="+Integer.valueOf(textField.getText()));
			s1.executeUpdate();
			
			dispose();
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void addno(String a)
	{
		String bb=textField.getText();
		String b;
		if(bb==null)
		{
			textField.setText(a);
		}else{
			b=bb+a;
			textField.setText(b);
		}	
	}
	public static Date removeTimeFromDate(Date date) {
		 
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
 
	protected void check() {
		if(textField.getText().equals("")){
			lblPleaseEnterId.setVisible(true);
			textField.requestFocus();
		}
		else
		{
				try {
					
				s1=conn.prepareStatement("Select * from employee where Employee_id="+Integer.valueOf(textField.getText()));
				r1=s1.executeQuery();
				if (!r1.isBeforeFirst() ) 
				{
					lblPleaseEnterId.setVisible(true);
					lblPleaseEnterId.setText("Wrong ID");
				}else{
					r1.first();
					lblname.setText(r1.getString("Emp_name"));
					if(onoff.isSelected())
					{
						s2=conn.prepareStatement("Select * from employee_session where status=0 and Employee_id="+r1.getInt("Employee_id"));
						r2=s2.executeQuery();
					
						if (!r2.isBeforeFirst() ) 						{
							r2.first();
							lblstart.setText(dateFormat.format(cal.getTime()));
							btnSignIn.setVisible(true);
						}else{
							
							r2.first();
							lblstart.setText(r2.getString("Date")+"  "+r2.getString("Start_time"));
							lblend.setText(dateFormat.format(date)+"  "+sdf.format(date));
							btnSignOut.setVisible(true);
						}
						panel_main.setVisible(false);
						panel_time.setVisible(true);
					}else{
						s2=conn.prepareStatement("Select employee_id from employee_session where status=0 and Employee_id="+Integer.valueOf(textField.getText()));
						r2=s2.executeQuery();
					
						if (!r2.isBeforeFirst() ) 						{
							signinfun();
						}else{
							
							s1=conn.prepareStatement("UPDATE `employee_session` SET `affected`=0");
							s1.executeUpdate();
							
							s1=conn.prepareStatement("UPDATE `employee_session` SET `affected`=1 WHERE `Employee_id` ="+Integer.valueOf(textField.getText()));
							s1.executeUpdate();
							dispose();
						}
					}
					
				}
				} catch (SQLException e) {
					e.printStackTrace();
				}	
			
		}
	}
}
