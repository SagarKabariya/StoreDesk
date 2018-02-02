package Register;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import com.mysql.jdbc.Statement;

import Cashier.Cashier;
import Connect.ConnectionManager;
import FindItems.Find;
import LogInOut.Logindailog;
import Mix.ReviewTran;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;

import java.awt.CardLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;

import javax.swing.JScrollBar;
import java.awt.event.KeyAdapter;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JCheckBox;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;


public class SecondWindow extends JFrame implements KeyListener{
	DecimalFormat df = new DecimalFormat("#.###"); 
	public static double remaining_change,entered_amount,cash_recieve_main=0,card_recieve_main=0,other_recieve_main=0;
	public static int option_payment;
	public int voidcounter=0,emp_id, numberfromfind;
	public double final_total_amount;
	public static boolean invdone=false;
	private char c,a ;
	public Integer total_quantity=0;
	public static Integer tm=0,xx;
	public boolean tcounter=false;
	public int rownumber=0, listcounter=-1,counter=0;
	double abc;
	static String left, bb=" ";
	public static double reminder;
	Integer invoice_id;
	public static int transection_status = 0;
	public Object[][] voidlist = new Object[10][10];
	double[] discountamountlist =new double[500];
	int[] discounttype =new int[500];
	double[] discount =new double[500];
	double[] taxamountlist =new double[500];	
	double[] taxamountlistfinal =new double[500];	
	String[] receipt_data= new String[20];
	int[] taxidlist =new int[100];	
	int[] Itemlist =new int[100];
	
	
	JButton Btnlogin;
	private JPanel contentPane,panel_history,panel_remain;
	JTextField Itemno = new JTextField();
	JTextField Price = new JTextField();
	Object[] message = {
		    "Item NO:", Itemno,
		    "Price:", Price
		};
	public JLabel lbl_last_tot_itm, lbl_last_tot_amt, lbl_change, lbl_last_BNO,lbl_total_qua ;
	JLabel lbl_final_remain, lbl_final_card_pay, lbl_final_cash_pay,lbl_final_total_qua, lbl_final_tax_tot, lbl_final_sub_tot,lbl_final_total;
	
	private JTable table_register,table_void, table_2;
	private JTextField item_no,textField_focus, textField_focus_2;
	Calendar cal = Calendar.getInstance();
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Date today = new Date();
	java.util.Date date = new java.util.Date();
	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	ArrayList data = new ArrayList();
	
	Connection conn=null;
	PreparedStatement st=null,s2,s3,s4,s1;
	ResultSet rs=null,r2,r3,r4,r1;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SecondWindow frame = new SecondWindow();
					 frame.setUndecorated(true);
					frame.setVisible(true);
					frame.setAlwaysOnTop(false);
			    	
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public SecondWindow() {
		setResizable(false);
		
		setAlwaysOnTop(false);
		addKeyListener(this);
		try{
	    	conn = (Connection) ConnectionManager.getConnection();
				 }catch(Exception e1){
					 JOptionPane.showMessageDialog(null, "Daabase not Connected,Close Software and Enter Again");		
					 dispose();
					 }
		Bind_receipt_data();
				
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int wid = screenSize.width;
		int hei = screenSize.height;
		int wid_bt = (screenSize.width/10);

		setTitle("SoterDesk Point-of-Sale Register Module");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0, screenSize.width, screenSize.height);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JDesktopPane desktopPane = new JDesktopPane();
		contentPane.add(desktopPane, BorderLayout.CENTER);
		desktopPane.setLayout(new CardLayout(0, 0));
		
		JPanel panel_register = new JPanel();
		panel_register.setBackground(new Color(30, 144, 255));
		desktopPane.add(panel_register, "name_376006313536450");
		panel_register.setBounds(0, 0, wid, hei);
		panel_register.setLayout(null);
		
		JScrollPane scrollPane_regi = new JScrollPane();
		scrollPane_regi.setBounds((wid*11)/100, 163, (wid*88)/100, (hei*55)/100);
		panel_register.add(scrollPane_regi);
		
		
		table_register = new JTable();
		scrollPane_regi.setViewportView(table_register);
		table_register.setShowVerticalLines(false);
		table_register.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Quantity", "Item Name", "Price", "Discount", "Final Price"
			}
		));
		table_register.getColumnModel().getColumn(0).setPreferredWidth(60);
		table_register.getColumnModel().getColumn(1).setPreferredWidth(700);
		table_register.getColumnModel().getColumn(2).setPreferredWidth(80);
		table_register.getColumnModel().getColumn(3).setPreferredWidth(80);
		table_register.getColumnModel().getColumn(4).setPreferredWidth(80);
		table_register.setFont(new Font("Tahoma", Font.BOLD, 16));
		table_register.setBackground(new Color(102, 204, 153));
		table_register.setRowHeight(40);
		table_register.setBorder(new MatteBorder(5, 1, 1, 0, (Color) new Color(0, 0, 0)));
		
		table_register.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = table_register.getSelectedRow();
				int col = table_register.getSelectedColumn();
				Boolean rropt=false;
				Double no=0.0;
				DefaultTableModel model = (DefaultTableModel)table_register.getModel();
				
				String[] buttons = { "Edit Quantity", "Edit Discount","Remove Item", "OK","Cancel"}; 
				
				int returnValue = JOptionPane.showOptionDialog(null, "Special Change Model", "Select Properly",
				        JOptionPane.WARNING_MESSAGE, 0, null, buttons,buttons[0]);
				if(returnValue==0)
				{
					String qu=checkdouble("Enter Quantity");
					model.setValueAt(Integer.parseInt(qu), row, 0);
					Double q=Double.parseDouble(String.valueOf(model.getValueAt(row, 2)));
					q=q*Double.parseDouble(qu);
					model.setValueAt(df.format(q), row, 4);
					lbl_total_qua.setText(String.valueOf(Integer.valueOf(getquan())));
				}
				else if(returnValue==2)
				{
					JDialog.setDefaultLookAndFeelDecorated(true);
				    int response = JOptionPane.showConfirmDialog(null, "Are you sure for remove this ITEM", "Confirm",
				        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				    if (response == JOptionPane.YES_OPTION) {
				    	voidlist[voidcounter][0]=Itemlist[row];
				    	voidlist[voidcounter][1]=model.getValueAt(row, 0);
				    	voidlist[voidcounter][2]=discountamountlist[row];
				    	voidlist[voidcounter][3]=taxamountlistfinal[row];
				    	voidlist[voidcounter][4]=model.getValueAt(row, 4);
				    	voidcounter++;
				    	DefaultTableModel model_void = (DefaultTableModel)table_void.getModel();
				    	model_void.addRow(new Object[]{model.getValueAt(row, 1)});
				     model.removeRow(row);
				     rropt=true;
				     listcounter--;
				     lbl_total_qua.setText(String.valueOf(Integer.valueOf(getquan())));
				    } 
				}
				else if(returnValue==1)
				{
					String[] buttons_d = { "Dollar","Percentage", "OK","Cancel"};    
					int returnValue_d = JOptionPane.showOptionDialog(null, "Special Change Model", "Select Properly",
					        JOptionPane.WARNING_MESSAGE, 0, null, buttons_d,buttons_d[1]);
					if(returnValue_d==0)					{
						boolean inputAccepted = false;
						while(!inputAccepted) {
							  try {
								  	no = Double.valueOf(JOptionPane.showInputDialog("Enter Discount Amount"));
									  	 if (no > Double.valueOf(String.valueOf(model.getValueAt(row, 4)))) {
									  	      JOptionPane.showMessageDialog(null, "Discount Not More Than Amount");
									  	    } else { inputAccepted = true;	
									  	  discounttype[row]=1;
											discount[row]=no; }
									  	 
							  } catch(NumberFormatException e) {}
							  
						}
						
					}
					if(returnValue_d==1)					{
						boolean inputAccepted = false;
						while(!inputAccepted) {
							  try {
								  	no = Double.parseDouble(JOptionPane.showInputDialog("Enter Discount Amount"));
									  	 if (no > 99.99) {
									  	      JOptionPane.showMessageDialog(null, "Maximum 100%");
									  	    } else { inputAccepted = true;	
									  	  discounttype[row]=2;
											discount[row]=no;}
									  	  
							  } catch(NumberFormatException e) {}
						}
						
					}
					Double q=Double.parseDouble(String.valueOf(model.getValueAt(row, 0)));
					Double p=Double.parseDouble(String.valueOf(model.getValueAt(row, 2)));
					Double pp=q*p;
					if(returnValue_d==0)
					{
						model.setValueAt(no, row, 3);
						p=pp-no;
						model.setValueAt(df.format(p), row, 4);
					}
					else
					{
						double ft=Double.valueOf(model.getValueAt(row, 4).toString());
						double ft2=ft*no/100;
						ft=ft-ft2;
						model.setValueAt(df.format(ft), row, 4);
						model.setValueAt(df.format(ft2), row, 3);
					}
				}	
				
				if(rropt==true)
				{
					remove_row(row);
					rropt=false;
				}else{
					refresh(row);
				}
				}
		});
		
		JPanel panel_btn = new JPanel();
		panel_btn.setBackground(new Color(0, 102, 204));
		panel_btn.setBorder(new MatteBorder(8, 0, 0, 0, (Color) new Color(0, 0, 0)));
		panel_btn.setBounds(871, 767, 442, 263);
		panel_register.add(panel_btn);
		panel_btn.setLayout(null);
		
		JButton btnByCash = new JButton("Cash");
		btnByCash.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnByCash.setBackground(new Color(255, 0, 255));
		btnByCash.setBounds(233, 25, 189, 112);
		panel_btn.add(btnByCash);
		
		
		JButton btnByCard = new JButton("Credit Card");
		btnByCard.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnByCard.setBackground(new Color(255, 255, 0));
		btnByCard.setBounds(22, 158, 400, 92);
		panel_btn.add(btnByCard);
		
		JButton btnCheque = new JButton("Cheque");
		btnCheque.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnCheque.setBounds(22, 25, 189, 112);
		panel_btn.add(btnCheque);
		
		btnByCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(transection_status==0)
				{
					if(listcounter == -1)
					{
						JOptionPane.showMessageDialog(null, "First ring up Items then I will Work");
					}
					else if(Btnlogin.getText()=="Login"){
						JOptionPane.showMessageDialog(null, "First Login then you can do any transection");
					}
					else{
					panel_register.setRequestFocusEnabled(false);
					Cashier ch = new Cashier((int) getquan(),Double.valueOf(df.format(final_total_amount)),0);
					ch.setVisible(true);
					invoiceStepTwo();
					invoicemake();
					panel_register.setRequestFocusEnabled(true);
					}
				}else if(transection_status==1){
					panel_register.setRequestFocusEnabled(false);
					Cashier ch = new Cashier((int) getquan(),remaining_change,0);
					ch.setVisible(true);
					invoiceStepTwo();
					invoicemake();
					panel_register.setRequestFocusEnabled(true);
				}
			}
		});
		
		btnByCash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(transection_status==0)
				{
					if(listcounter == -1)
					{
						JOptionPane.showMessageDialog(null, "First ring up Items then I will Work");
					}
					else if(Btnlogin.getText()=="Login"){
						JOptionPane.showMessageDialog(null, "First Login then you can do any transection");
					}
					else{
					panel_register.setRequestFocusEnabled(false);
					Cashier ch = new Cashier((int) getquan(),Double.valueOf(df.format(final_total_amount)),1);
					ch.setVisible(true);
					invoiceStepTwo();
					invoicemake();
					panel_register.setRequestFocusEnabled(true);
					}
				}else if(transection_status==1){
					panel_register.setRequestFocusEnabled(false);
					Cashier ch = new Cashier((int) getquan(),remaining_change,1);
					ch.setVisible(true);
					invoiceStepTwo();
					invoicemake();
					panel_register.setRequestFocusEnabled(true);
				}
			}
		});		
		
		JPanel panel_history = new JPanel();
		panel_history.setBorder(new MatteBorder(0, 3, 0, 3, (Color) new Color(0, 0, 0)));
		panel_history.setBackground(new Color(0, 204, 255));
		panel_history.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				panel_history.setVisible(false);
			}
		});
		panel_history.setVisible(false);
		
		JPanel panel_name = new JPanel();
		panel_name.setBorder(new MatteBorder(0, 3, 0, 3, (Color) new Color(0, 0, 0)));
		panel_name.setBounds(10, 13, 430, 137);
		panel_register.add(panel_name);
		panel_name.setLayout(null);
		
		
		JLabel lblHandcraftedLabPvt = new JLabel("");
		lblHandcraftedLabPvt.setText("<html><p><center>"+"StoreDesk POS System <br>January 31th, 2018<br>4:55PM</center></p></html>");
		lblHandcraftedLabPvt.setHorizontalAlignment(SwingConstants.CENTER);
		lblHandcraftedLabPvt.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblHandcraftedLabPvt.setBounds(0, 0, 430, 137);
		panel_name.add(lblHandcraftedLabPvt);
		panel_history.setBounds(12, 13, 430, 137);
		panel_register.add(panel_history);
		panel_history.setLayout(null);
		
		
		JLabel lblTotalItems = new JLabel("Total Items");
		lblTotalItems.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTotalItems.setBounds(10, 0, 122, 29);
		panel_history.add(lblTotalItems);
		
		JLabel lblTotalAmount = new JLabel("Total Amount");
		lblTotalAmount.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTotalAmount.setBounds(10, 31, 122, 31);
		panel_history.add(lblTotalAmount);
		
		JLabel lblBillNo = new JLabel("Bill No");
		lblBillNo.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblBillNo.setBounds(10, 64, 122, 31);
		panel_history.add(lblBillNo);
		
		lbl_last_BNO= new JLabel();
		lbl_last_BNO.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_last_BNO.setFont(new Font("Tahoma", Font.BOLD, 20));
		lbl_last_BNO.setBounds(144, 64, 141, 31);
		panel_history.add(lbl_last_BNO);
		
		lbl_last_tot_amt = new JLabel();
		lbl_last_tot_amt.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_last_tot_amt.setBounds(144, 31, 141, 31);
		panel_history.add(lbl_last_tot_amt);
		lbl_last_tot_amt.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		lbl_last_tot_itm= new JLabel();
		lbl_last_tot_itm.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_last_tot_itm.setBounds(144, 0, 141, 29);
		panel_history.add(lbl_last_tot_itm);
		lbl_last_tot_itm.setBackground(Color.LIGHT_GRAY);
		lbl_last_tot_itm.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JLabel label = new JLabel("Total Quantity");
		label.setBounds(10, 95, 136, 29);
		panel_history.add(label);
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		lbl_total_qua = new JLabel("");
		lbl_total_qua.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_total_qua.setBounds(144, 95, 136, 29);
		panel_history.add(lbl_total_qua);
		lbl_total_qua.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JLabel lblChange = new JLabel("Change");
		lblChange.setHorizontalAlignment(SwingConstants.CENTER);
		lblChange.setBounds(297, 13, 133, 29);
		panel_history.add(lblChange);
		lblChange.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		lbl_change = new JLabel();
		lbl_change.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_change.setBounds(289, 64, 141, 39);
		panel_history.add(lbl_change);
		lbl_change.setFont(new Font("Arial Black", Font.BOLD, 20));
		
		JPanel panel_leftAllBtn = new JPanel();
		panel_leftAllBtn.setBackground(new Color(30, 144, 255));
		panel_leftAllBtn.setBounds(0, 163, (wid*10)/100, hei-190);
		panel_register.add(panel_leftAllBtn);
		panel_leftAllBtn.setLayout(null);
		
		JButton btnClearList = new JButton("Clear List");
		btnClearList.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnClearList.setBackground(new Color(60, 179, 113));
		btnClearList.setBounds(12, 375, 180, 62);
		panel_leftAllBtn.add(btnClearList);
		
		JButton btnSuspandrecall = new JButton("Save/Recall");
		btnSuspandrecall.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnSuspandrecall.setBackground(new Color(222, 184, 135));
		btnSuspandrecall.setBounds(12, 450, 180, 62);
		panel_leftAllBtn.add(btnSuspandrecall);
		
		JButton btnReprintReciept = new JButton("Pre-Quantity");
		btnReprintReciept.setBackground(SystemColor.activeCaption);
		btnReprintReciept.setBounds(12, 260, 180, 107);
		panel_leftAllBtn.add(btnReprintReciept);
		btnReprintReciept.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JButton button = new JButton("+");
		button.setBackground(new Color(51, 204, 0));
		button.setFont(new Font("Stencil Std", Font.PLAIN, 40));
		button.setBounds(12, 0, 180, 117);
		panel_leftAllBtn.add(button);
		
		JButton btnCloseRegister = new JButton("Close Register");
		btnCloseRegister.setBackground(new Color(255, 0, 0));
		btnCloseRegister.setBounds(12, 750, 180, 107);
		panel_leftAllBtn.add(btnCloseRegister);
		btnCloseRegister.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JButton btnVoidLine = new JButton("Void Line");
		btnVoidLine.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnVoidLine.setBackground(new Color(204, 102, 0));
		btnVoidLine.setBounds(12, 130, 180, 117);
		panel_leftAllBtn.add(btnVoidLine);
		
		JButton btnManagerMenu = new JButton("Manager Menu");
		btnManagerMenu.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnManagerMenu.setBackground(new Color(244, 164, 96));
		btnManagerMenu.setBounds(12, 525, 180, 62);
		panel_leftAllBtn.add(btnManagerMenu);
		
		JButton btnSetting = new JButton("Setting");
		btnSetting.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnSetting.setBackground(new Color(123, 104, 238));
		btnSetting.setBounds(12, 600, 180, 62);
		panel_leftAllBtn.add(btnSetting);
		
		JButton btnDiscount = new JButton("Discount");
		btnDiscount.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnDiscount.setBackground(new Color(0, 255, 127));
		btnDiscount.setBounds(12, 675, 180, 62);
		panel_leftAllBtn.add(btnDiscount);
		
		btnCloseRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Calendar cal = Calendar.getInstance();
				Date today = new Date();
				java.util.Date date = new java.util.Date();
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
				try {
					s1=conn.prepareStatement("UPDATE `employee_session` SET `End_time`='"+sdf.format(date)+"',`status`=1 ");
					s1.executeUpdate();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				dispose();
			}
		});
		
		btnReprintReciept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnSuspandrecall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int trno=getnum("Enter Number for Store Transectoin");
				
			}
		});
		
		btnClearList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int r =JOptionPane.showConfirmDialog(null, "Are you sure to clear Whole Register");
				if(r==0){
					clearsale();
					((DefaultTableModel) table_register.getModel()).setRowCount(0);
				clearpage();
				
				}
			}
		});
		
		JPanel panel_Top_btn = new JPanel();
		panel_Top_btn.setBackground(new Color(30, 144, 255));
		panel_Top_btn.setBounds(1343, 13, 554, 137);
		panel_register.add(panel_Top_btn);
		panel_Top_btn.setLayout(null);
		
		JButton btnEnterQuantity = new JButton("UPC");
		btnEnterQuantity.setBounds(0, 0, 153, 62);
		panel_Top_btn.add(btnEnterQuantity);
		btnEnterQuantity.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JButton btnNewButton = new JButton("Product Number");
		btnNewButton.setBounds(165, 0, 224, 62);
		panel_Top_btn.add(btnNewButton);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		textField_focus = new JTextField();
		textField_focus.setBounds(0, 75, 389, 62);
		panel_Top_btn.add(textField_focus);
		textField_focus.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_focus.setToolTipText("");
		textField_focus.setBackground(Color.WHITE);
		textField_focus.addActionListener(new ActionListener(){

	        public void actionPerformed(ActionEvent e){
	        	
	        	funbarcode(textField_focus.getText());
	        	textField_focus.setText(null);	        	
	        }});
		textField_focus.setColumns(10);
		//textField_focus.setVisible(false);
		
		textField_focus_2 = new JTextField();
		textField_focus_2.setBounds(0, 75, 389, 62);
		panel_Top_btn.add(textField_focus_2);
		textField_focus_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_focus_2.setForeground(SystemColor.inactiveCaption);
		textField_focus_2.setBackground(Color.WHITE);
		
		textField_focus_2.addActionListener(new ActionListener(){

	        public void actionPerformed(ActionEvent e){
	        	
	             funbarcode(textField_focus_2.getText());
	             textField_focus_2.setText(null);
	        }});
		textField_focus_2.setColumns(10);
		
		JButton btnName = new JButton("Name");
		btnName.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnName.setBounds(401, 2, 153, 62);
		panel_Top_btn.add(btnName);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnSearch.setBounds(401, 75, 153, 63);
		panel_Top_btn.add(btnSearch);
		
		JPanel panel_final_cal = new JPanel();
		panel_final_cal.setBorder(new MatteBorder(8, 0, 0, 0, (Color) new Color(0, 0, 0)));
		panel_final_cal.setBackground(new Color(0, 102, 204));
		int hei_temp=scrollPane_regi.getHeight()+scrollPane_regi.getBounds().y;
		panel_final_cal.setBounds(1325,767,574,152);
		panel_register.add(panel_final_cal);
		panel_final_cal.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sub Total");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(35, 74, 119, 30);
		panel_final_cal.add(lblNewLabel);
		
		JLabel lblTax = new JLabel("Tax");
		lblTax.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTax.setBounds(35, 117, 119, 30);
		panel_final_cal.add(lblTax);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblTotal.setBounds(323, 31, 241, 30);
		panel_final_cal.add(lblTotal);
		
		lbl_final_sub_tot = new JLabel("0.00");
		lbl_final_sub_tot.setFont(new Font("Tahoma", Font.BOLD, 20));
		lbl_final_sub_tot.setBounds(166, 74, 145, 30);
		panel_final_cal.add(lbl_final_sub_tot);
		
		lbl_final_tax_tot = new JLabel("0.00");
		lbl_final_tax_tot.setFont(new Font("Tahoma", Font.BOLD, 20));
		lbl_final_tax_tot.setBounds(166, 117, 145, 30);
		panel_final_cal.add(lbl_final_tax_tot);
		
		lbl_final_total = new JLabel("0.00");
		lbl_final_total.setForeground(new Color(255, 255, 255));
		lbl_final_total.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_final_total.setFont(new Font("Tahoma", Font.BOLD, 40));
		lbl_final_total.setBounds(323, 62, 241, 85);
		panel_final_cal.add(lbl_final_total);
		
		JLabel lblTotalItems_1 = new JLabel("Total Items");
		lblTotalItems_1.setForeground(new Color(255, 255, 255));
		lblTotalItems_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTotalItems_1.setBounds(25, 13, 129, 48);
		panel_final_cal.add(lblTotalItems_1);
		
		lbl_final_total_qua = new JLabel("0");
		lbl_final_total_qua.setForeground(new Color(255, 255, 255));
		lbl_final_total_qua.setFont(new Font("Tahoma", Font.BOLD, 20));
		lbl_final_total_qua.setBounds(166, 13, 145, 48);
		panel_final_cal.add(lbl_final_total_qua);
		
		JScrollPane scrollpane_void = new JScrollPane();
		scrollpane_void.setBounds(211, 767, 648, 263);
		panel_register.add(scrollpane_void);
		scrollpane_void.setBorder(new MatteBorder(8, 0, 0, 0, (Color) new Color(0, 0, 0)));
		table_void = new JTable();
		table_void.setFillsViewportHeight(true);
		table_void.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Items"
			}
		));
		table_void.setBackground(Color.WHITE);
		table_void.setRowSelectionAllowed(false);
		table_void.setShowGrid(false);
		table_void.setShowHorizontalLines(false);
		table_void.setTableHeader(null);
		table_void.setFont(new Font("Serif", Font.BOLD, 25));
		scrollpane_void.setViewportView(table_void);
		
		Btnlogin = new JButton("Login");
		Btnlogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				Btnlogin.setBackground(new Color(255, 102, 0));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Btnlogin.setBackground(new Color(255, 153, 51));
			}
		});
		Btnlogin.setBounds(628, 13, 180, 137);
		panel_register.add(Btnlogin);
		Btnlogin.setBackground(new Color(255, 153, 51));
		
		Btnlogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			getuserdata();
			textField_focus.requestFocus();	
			}
		});
		Btnlogin.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JToggleButton tglbtnDrawer = new JToggleButton("Drawer");
		tglbtnDrawer.setBounds(454, 13, 162, 137);
		panel_register.add(tglbtnDrawer);
		tglbtnDrawer.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if(tglbtnDrawer.isSelected()){
					
					tglbtnDrawer.setText("Print");
				
				}else{
					tglbtnDrawer.setText("Drawer");
				}
			}
		});
		tglbtnDrawer.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JButton btnReview = new JButton("Last Sale");
		btnReview.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnReview.setBounds(821, 13, 162, 137);
		panel_register.add(btnReview);
		
		JButton btn_find = new JButton("Find");
		btn_find.setFont(new Font("Tahoma", Font.BOLD, 20));
		btn_find.setBounds(995, 13, 162, 137);
		panel_register.add(btn_find);
		
		JButton btnQuickView = new JButton("Quick View");
		btnQuickView.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnQuickView.setBounds(1169, 13, 162, 137);
		panel_register.add(btnQuickView);
		
		panel_remain = new JPanel();
		panel_remain.setBorder(new MatteBorder(0, 4, 0, 4, (Color) new Color(0, 0, 0)));
		panel_remain.setBackground(new Color(51, 102, 204));
		panel_remain.setBounds(1325, 923, 575, 99);
		panel_register.add(panel_remain);
		panel_remain.setLayout(null);
		panel_remain.setVisible(false);
		
		JLabel label_1 = new JLabel("By Cash ");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_1.setBounds(12, 15, 119, 30);
		panel_remain.add(label_1);
		
		lbl_final_cash_pay = new JLabel("0.00");
		lbl_final_cash_pay.setFont(new Font("Tahoma", Font.BOLD, 20));
		lbl_final_cash_pay.setBounds(143, 15, 145, 30);
		panel_remain.add(lbl_final_cash_pay);
		
		lbl_final_card_pay = new JLabel("0.00");
		lbl_final_card_pay.setFont(new Font("Tahoma", Font.BOLD, 20));
		lbl_final_card_pay.setBounds(143, 58, 145, 30);
		panel_remain.add(lbl_final_card_pay);
		
		JLabel label_4 = new JLabel("By Card");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_4.setBounds(12, 58, 119, 30);
		panel_remain.add(label_4);
		
		JLabel label_5 = new JLabel("Remaning");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setFont(new Font("Tahoma", Font.BOLD, 25));
		label_5.setBounds(300, 13, 241, 30);
		panel_remain.add(label_5);
		
		lbl_final_remain = new JLabel("0.00");
		lbl_final_remain.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_final_remain.setForeground(Color.WHITE);
		lbl_final_remain.setFont(new Font("Tahoma", Font.BOLD, 30));
		lbl_final_remain.setBounds(300, 51, 241, 43);
		panel_remain.add(lbl_final_remain);
		btn_find.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Find fi = new Find();
				fi.requestFocus();
				fi.setVisible(true);
			}
		});
		btnReview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			ReviewTran rt = new ReviewTran();
			desktopPane.add(rt);
			rt.setVisible(true);
			}
		});
		textField_focus_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg2) {
				if(arg2.getKeyCode()==032)
				{
					int no;
					no=getnum("Enter Item Number");
					function109(no);
					textField_focus_2.setText(null);
				}
			}
		});
		
		textField_focus.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				
				if(arg0.getKeyCode()==032)
				{
					int no;
					no=getnum("Enter Item Number");
					function109(no);
					textField_focus.setText(null);
			    }
			}
		});
		
	}

	protected void void_line(int invoice) {
		//i=row number
		try {
			for(int i=0;i<voidcounter;i++){
				Double sum=Double.valueOf(voidlist[i][3].toString())+Double.valueOf(voidlist[i][4].toString());
					s1=conn.prepareStatement("INSERT INTO `void_line`(`invoice_id`, `item_id`, `quantity`, `disocount`, `tax_per`, `final_amount`) "
						+ "VALUES ("+invoice+","+voidlist[i][0]+","+voidlist[i][1]+","+voidlist[i][2]+","+voidlist[i][3]+","+sum+")");
				s1.execute();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected void clearsale() {
		try {
			for(int i=0;i<=listcounter;i++){
				Double sum=Double.valueOf(table_register.getValueAt(i, 4).toString())+taxamountlistfinal[i];
				
					s1=conn.prepareStatement("INSERT INTO `clear_sale`(`employee_id`,`item_id`, `quantity`, `Discount`, `Tax_per`, `final_amount`) "
						+ "VALUES ("+emp_id+","+Itemlist[i]+","+table_register.getValueAt(i, 0)+","+table_register.getValueAt(i, 3)+","+taxamountlistfinal[i]+","+sum+")");
				s1.execute();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void Bind_receipt_data() {
		
		try {
			s4=conn.prepareStatement("SELECT * FROM `config_store` WHERE `status` = 1");
			r3=s4.executeQuery();
			while(r3.next())
			{
				receipt_data[0]=r3.getString(1);
				receipt_data[1]=r3.getString(2);
				receipt_data[2]=r3.getString(3);
				receipt_data[3]=r3.getString(4);
				receipt_data[4]=r3.getString(5);
				receipt_data[5]=r3.getString(6);
				receipt_data[6]=r3.getString(7);
				receipt_data[7]=r3.getString(8);
				receipt_data[8]=r3.getString(9);
				receipt_data[9]=r3.getString(10);
				receipt_data[10]=r3.getString(11);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	

	
	protected void invoicemake() {
		JOptionPane.showMessageDialog(null, "in making");
		if(invdone==true){
			JOptionPane.showMessageDialog(null, "in making in==true");
			lbl_last_tot_itm.setText(String.valueOf(getquan()));
		lbl_last_tot_amt.setText(String.valueOf(df.format(final_total_amount)));
		lbl_change.setText(String.valueOf(df.format(reminder)));
		transection_status=0;
		insert_invoice();
		filewriter();
		clearpage();
		}
	}

	private void insert_invoice() {
		
		try {
			invoice_id =insertQueryGetId();
			void_line(invoice_id);
			this.lbl_last_BNO.setText(String.valueOf(invoice_id));
			for(int i=0;i<=listcounter;i++){
				Double sum=Double.valueOf(table_register.getValueAt(i, 4).toString())+taxamountlistfinal[i];
				s1=conn.prepareStatement("INSERT INTO `invoice_details`(`Invoice_id`, `Item_id`, `Item_price`, `Quantity`, `Extra_Discount`, `Final_tax`, `Final_items_amount`) "
					+ "VALUES ("+invoice_id+","+Itemlist[i]+","+table_register.getValueAt(i, 2)+","+table_register.getValueAt(i, 0)+","+table_register.getValueAt(i, 3)+","+taxamountlistfinal[i]+","+sum+")");
				s1.execute();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Integer insertQueryGetId() {
		Integer numero=0;
	    Integer risultato=-1;
		double total_discount=0.0;
		int ccquan=listcounter+1;
		for(int i=0;i<=listcounter;i++){
			total_discount=total_discount+discountamountlist[i];
		}
		String query="INSERT INTO `invoice`(`Employee_id`, `Total_Quantity`, `Total_discount`, `Total_tax`, `Final_amount`, `Cash_Recieve`, `Card_payment`, `Other_recieve`, `Remain_change`) "
				+ "VALUES ("+emp_id+","+ccquan+","+total_discount+","+lbl_final_tax_tot.getText()+","+lbl_final_total.getText()+",)";
		try {
			
		    PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		    numero = stmt.executeUpdate();

		    ResultSet rs = stmt.getGeneratedKeys();
		    if (rs.next()){
		        risultato=rs.getInt(1);
		    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  return risultato;
	}
	public String setdoubleQuote(String myText) {
	    String quoteText = "";
	    if (!myText.isEmpty()) {
	        quoteText = "\"" + myText + "\"";
	    }
	    return quoteText;
	}
	
	private void filewriter() {
		BufferedWriter bfw=null;
	    try {
	    	String fname=receipt_data[10];
	    	fname=setdoubleQuote(fname);
	    		    	bfw= new BufferedWriter(new FileWriter("D:/Data.txt"));
						String line="        "+receipt_data[0];
						bfw.write(line);
						bfw.newLine();
						line="             "+receipt_data[1];
						bfw.write(line);
						bfw.newLine();
						line="          "+receipt_data[2];
						bfw.write(line);
						bfw.newLine();
						line="         "+receipt_data[3];
						bfw.write(line);
						bfw.newLine();
						line="       "+receipt_data[4];
						bfw.write(line);
						bfw.newLine();
						line=receipt_data[5]+"        "+receipt_data[6]+"        "+receipt_data[7];
						bfw.write(line);
						bfw.newLine();
						line="___________________________________________";
						bfw.write(line);
	            for (int i = 0; i <= listcounter; i++) {
	                bfw.newLine();
	                for (int j = 0; j <=listcounter ; j++) {
	                        String name = String.valueOf((table_register.getValueAt(i, j)));
	                        name.trim();  
	                        String spaces = "";
                            int diff = 6 - name.length();
                            while (diff > 0) {
                                spaces = spaces + " ";
                                diff--;
                            name = name.concat(spaces);
                        }
                        bfw.write(name);
	                       
	                }
	                for (int j = 1; j < 2; j++) {
                        String name = String.valueOf((Itemlist[i]));
                        name.trim();  
                        String spaces = "";
                        int diff = 8 - name.length();
                        while (diff > 0) {
                            spaces = spaces + " ";
                            diff--;
                        name = name.concat(spaces);
                    }
                    bfw.write(name);
                }
	                for (int j = 4; j < 5; j++) {
                        String name = String.valueOf((table_register.getValueAt(i, j)));
                    bfw.write(name);
                }
	              bfw.newLine();
	              for (int j = 1; j < 2; j++) {
                      String name = String.valueOf((table_register
                              .getValueAt(i, j)));
                          String spaces = "";
                          int diff = 30 - name.length();
                          while (diff > 0) {
                              spaces = spaces + "&";
                              diff--;
                          name = name.concat(spaces);
                      }
                      bfw.write(name);
	            }
	            }
	            bfw.newLine();
	            bfw.newLine();
				bfw.newLine();
				bfw.newLine();
				bfw.newLine();
				line="TOTAL QUANTITY ";
				bfw.write(line);
				
				 String name = String.valueOf(listcounter+1);
                 name.trim();  
                 String spaces = " ";
                 int diff = 5 - name.length();
                 while (diff > 0) {
                     spaces = spaces + " ";
                     diff--;
                 name = spaces.concat(":");
                 name = spaces.concat(String.valueOf(listcounter+1));
                 }
				bfw.write(name);
				bfw.newLine();
				line="SUB TOTAL : "+lbl_final_sub_tot.getText();
				bfw.write(line);
				bfw.newLine();
				line="TAX : "+lbl_final_tax_tot.getText();
				bfw.write(line);
				bfw.newLine();
				line="Total Amount : "+lbl_final_total.getText();
				bfw.write(line);
				bfw.newLine();
				line="Credit Sale : "+lbl_final_card_pay.getText();
				bfw.write(line);
				bfw.newLine();
				line="Cash Sale : "+lbl_final_cash_pay.getText();
				bfw.write(line);
				bfw.newLine();
				line="CHANGE : "+lbl_change.getText();
				bfw.write(line);
				bfw.newLine();
				bfw.newLine();
				bfw.newLine();
				DateFormat dateFormat2 = new SimpleDateFormat("MM-dd-yyyy");
				line="Date   :  "+dateFormat2.format(date)+" "+sdf.format(date);
				bfw.write(line);
				bfw.newLine();
				line="Invoice Number   :"+invoice_id;
				bfw.write(line);
				bfw.newLine();
				line="Employee Number    :   "+emp_id;
				bfw.write(line);
				bfw.newLine();
				line="----------------"+receipt_data[8]+"--------------------";
				bfw.write(line);
				bfw.newLine();
				line="--------"+receipt_data[9]+"-------";
				bfw.write(line);
				bfw.newLine();
				bfw.newLine();
	            
				
				
	    } catch (IOException e) {
	        System.err.println(e);
		} finally {
	        if (bfw != null) {
	            try {
	                bfw.close();
	            } catch (IOException e) {
	                System.err.println(e);
	            }
	        }
	    }
	}

	void getuserdata() {
		try {
			Logindailog ld = new Logindailog();
			ld.setVisible(true);
			s2=conn.prepareStatement("select employee.Emp_name, employee.Employee_id from employee, employee_session "
					+ "WHERE employee.Employee_id=employee_session.Employee_id and employee_session.affected=1 "
					+ "and employee_session.status=0 order by employee_session.Session_id desc LIMIT 1");
			r2=s2.executeQuery();			
			if (!r2.isBeforeFirst() ) 	{
				getuserdata();
			}
			
				r2.first();
				emp_id=r2.getInt("Employee_id");
				Btnlogin.setText(r2.getString("emp_name"));
				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	@Override
	public void keyPressed(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
	
	
	public String checkdouble(String msg)	{
		String str="";
		boolean inputAccepted = false;
		while(!inputAccepted) {
			  try {
				  str=JOptionPane.showInputDialog(msg);
				  String[] dotSub=str.split("\\.|,");
			        if(dotSub.length==2){
			             if(dotSub[1].length()<=2){
			            	 inputAccepted = true;
			             }else{
			            	 inputAccepted = false;
			            	 JOptionPane.showMessageDialog(null,"not a valid Data");
			             }
			        }
			        if(Integer.valueOf(str)<1)			        {
			        	inputAccepted = false;
		            	 JOptionPane.showMessageDialog(null,"not a valid Data");
			        }else {
			        	inputAccepted = true;  }
				  } catch(NumberFormatException e) {}
		}
		return str;
	}
	public void invoiceStepTwo()
	{
		if(invdone==false) {
			
		
			double rmvalue;
		 if(option_payment==0)
			{
			     rmvalue=Double.valueOf(lbl_final_card_pay.getText());
				 double t1=rmvalue+entered_amount;
				 lbl_final_card_pay.setText(String.valueOf(t1));
			}else
			{
				 rmvalue=Double.valueOf(lbl_final_cash_pay.getText());
				 lbl_final_cash_pay.setText(String.valueOf(rmvalue+entered_amount));
			}
		 lbl_final_remain.setText(String.valueOf(df.format(remaining_change)));
		}
	}
	public void invoiceStepOne(double payment_recieve_cashier,int method_pay,double final_recieve_amount)
	{
		if(payment_recieve_cashier == final_recieve_amount)
		{
			JOptionPane.showMessageDialog(null, "rr=mm=final==="+payment_recieve_cashier+"==="+method_pay+"==="+final_recieve_amount);
			invdone=true;
			entered_amount=payment_recieve_cashier;
			remaining_change=final_recieve_amount-payment_recieve_cashier;
			option_payment=method_pay;
			this.lbl_last_tot_amt.setText(String.valueOf(final_recieve_amount));
			this.lbl_last_tot_itm.setText(String.valueOf(getquan()));
			reminder=final_recieve_amount-payment_recieve_cashier;	
		}
		else if(payment_recieve_cashier > final_recieve_amount)
		{
			JOptionPane.showMessageDialog(null, "rr=mm=final==="+payment_recieve_cashier+"==="+method_pay+"==="+final_recieve_amount);
			invdone=true;
			entered_amount=payment_recieve_cashier;
			remaining_change=payment_recieve_cashier-final_recieve_amount;
			option_payment=method_pay;
			this.lbl_last_tot_amt.setText(String.valueOf(final_recieve_amount));
			this.lbl_last_tot_itm.setText(String.valueOf(getquan()));
			reminder=payment_recieve_cashier-final_recieve_amount;
		}else if(payment_recieve_cashier < final_recieve_amount)
		{
			JOptionPane.showMessageDialog(null, "rr=mm=final==="+payment_recieve_cashier+"==="+method_pay+"==="+final_recieve_amount);
			entered_amount=payment_recieve_cashier;
			remaining_change=final_recieve_amount-payment_recieve_cashier;
			option_payment=method_pay;
			transection_status=1;
			reminder=final_recieve_amount-payment_recieve_cashier;
		}
	}
	public double getamount()	{
		abc=(float) 0.0;
		for(int i=0;i<table_register.getModel().getRowCount();i++)		{
			abc=abc+Double.valueOf(table_register.getModel().getValueAt(i, 4).toString());
		}		return abc;
	}
	public int getquan()	{
		int qq=0;
		for(int i=0;i<table_register.getModel().getRowCount();i++)		{
			int tmp= Integer.parseInt(table_register.getValueAt(i, 0).toString());
			qq=qq+(tmp);		}
		return qq;		
	}
	public int getnum(String infor)	{
			int no=0;
			boolean inputAccepted = false;
			while(!inputAccepted) {
				  try {
					  	no = Integer.parseInt(JOptionPane.showInputDialog(infor));
						  	 if (no < 1) {
						  	      JOptionPane.showMessageDialog(null, "Enter Proper Data");
						  	    } else { inputAccepted = true;	    }
					  } catch(NumberFormatException e) {}
			}			return no;
	}

	public void refresh(int row)
	{
		      DefaultTableModel model = (DefaultTableModel)table_register.getModel();
		      double rfprice=Double.valueOf(model.getValueAt(row, 2).toString());
				double rffprice=Double.valueOf(model.getValueAt(row, 4).toString());
				double dis=Double.valueOf(model.getValueAt(row, 3).toString());
				int rfqua= Integer.parseInt(table_register.getValueAt(row, 0).toString());
				if(discounttype[row]==1)
				{
					discountamountlist[row]=dis;
				}
				if(discounttype[row]==2)
				{
					discountamountlist[row]=dis;
				}
				taxamountlistfinal[row]=((rffprice)*(taxamountlist[row]/100));
				lablefill();
	}
	public void remove_row(int row)
	{
		if(listcounter==-1)		{
			for(int i=0;i<=listcounter;i++){
				Itemlist[i]=0;
				taxamountlist[i]=0;
			    taxamountlistfinal[i]=0;
			    taxidlist[i]=0;
			    discountamountlist[i]=0;
			}		  
		    lbl_final_total_qua.setText("0");
		}
		else{	
			for(int i=row;i<=listcounter;i++)
			{
				Itemlist[i]=Itemlist[i+1];
				taxamountlist[i]=taxamountlist[i+1];
			    taxamountlistfinal[i]=taxamountlistfinal[i+1];
			    taxidlist[i]= taxidlist[i+1];
			    discountamountlist[i]= discountamountlist[i+1];
				discounttype[i]=discounttype[i+1];
				discount[i]=discount[i+1];
			}
			int temp_qua = Integer.valueOf(lbl_total_qua.getText())-1;
		    lbl_final_total_qua.setText(String.valueOf(temp_qua));
		lablefill();	
		}
	}
	public void clearpage()
	{
		invdone=false;
		((DefaultTableModel) table_void.getModel()).setRowCount(0);
		lbl_total_qua.setText(null);
		lbl_final_card_pay.setText("0.00");
	    lbl_final_cash_pay.setText("0.00");
	    lbl_final_remain.setText("0.00");
	    lbl_final_sub_tot.setText("0.00");
	    lbl_final_tax_tot.setText("0.00");
	    lbl_final_total.setText("0.00");
	    final_total_amount=0;
		listcounter=-1;	
		((DefaultTableModel) table_register.getModel()).setRowCount(0);
		textField_focus.requestFocus();
	}
	
	public void lablefill()
	{
		double taxcal=0.0;
		double discal=0.0;
		for(int i=0;i<=listcounter;i++){
			taxcal=taxcal+taxamountlistfinal[i];
		}
		double total_a=Double.valueOf(getamount());
		final_total_amount=total_a+Double.valueOf(df.format(taxcal));
		textField_focus.requestFocus();
		lbl_total_qua.setText(String.valueOf(Integer.valueOf(getquan())));
		
		lbl_final_sub_tot.setText(String.valueOf(df.format(getamount())));
		lbl_final_tax_tot.setText(df.format(taxcal));
		lbl_final_total.setText(String.valueOf(df.format(final_total_amount)));
	}
	public void function109(int no)
	{		try{
				double pp= 0.0;
				double finalp=0.0;
				int lastQ=0;
				DefaultTableModel model = (DefaultTableModel)table_register.getModel();
				if(tcounter==true)
				{
					lastQ=Integer.valueOf(String.valueOf(table_register.getModel().getValueAt(table_register.getRowCount()-1, 0)));
					
					model.removeRow(table_register.getRowCount()-1);
				}				else				{
					lastQ=1;
				}
				tcounter=false;
				s4=conn.prepareStatement("SELECT items.Item_id,items.Item_Name,item_packing.Item_packing_type, item_price.Item_retail_price,Items.Tax_id, tax.Type,tax.Per "
						+ "FROM items left outer join `item_quantity_matrix` on items.Item_id = item_quantity_matrix.item_id left outer join item_price  on items.Item_id = item_price.Item_id LEFT OUTER JOIN tax on items.Tax_id = tax.Tax_id left outer join item_packing on item_quantity_matrix.Size_id=item_packing.Item_packing_id WHERE items.`item_id` ="+no);
				r3=s4.executeQuery();
				Object[] list = new Object[10]; 
				int listi=0;
				while(r3.next())
				{
					list[listi]=r3.getString("Item_packing_type");
					listi++;
				}
				int returnValue = JOptionPane.showOptionDialog(null, "Select Size", "Sizes",
				        JOptionPane.WARNING_MESSAGE, 0, null, list, null);
				if(returnValue==1)
				r3.beforeFirst();
				
				if(returnValue==0)
				{}
					
						double pri =Double.parseDouble(r3.getString("Item_retail_price"));
						if(pri==0 || pri == 0.0)
						{ 	
							pp =Double.valueOf(checkdouble("Enter Price"));
							finalp=pp*lastQ;
							model.addRow(new Object[]{lastQ,r3.getString("item_name"),pp,0.0,df.format(finalp)});
						}else
						{
							finalp=Double.valueOf(r3.getString("Item_retail_price"))*lastQ;
							model.addRow(new Object[]{lastQ,r3.getString("item_name"),r3.getString("Item_retail_price"),0.0,df.format(finalp)});
						}
						listfiller(Integer.valueOf(r3.getString("Item_id")), Integer.valueOf(r3.getString("Tax_id")), 0.0,finalp,Double.valueOf(r3.getString("Per")));
					
					Itemno.setText(null);
					Price.setText(null);
        		
			} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
	catch(Exception d){
		JOptionPane.showMessageDialog(null, d.getMessage());
	}
	lablefill();
	}
	public void listupdater()
	{
		 DefaultTableModel model = (DefaultTableModel)table_register.getModel();
		for(int i=0;i<=listcounter;i++){
		      double rfprice=Double.valueOf(model.getValueAt(i, 2).toString());
				double rffprice=Double.valueOf(model.getValueAt(i, 4).toString());
				double dis=Double.valueOf(model.getValueAt(i, 3).toString());
				int rfqua= Integer.parseInt(table_register.getValueAt(i, 0).toString());
				if(discounttype[i]==1)
				{
					discountamountlist[i]=dis;
				}
				if(discounttype[i]==2)
				{
					discountamountlist[i]=dis;
				}
				taxamountlistfinal[i]=((rffprice)*(taxamountlist[i]/100));
		}
	}
	public void listfiller(int itnumber,int taxid,double Dicount,Double itamount,Double Taxper)
	{
		try {
				listcounter++;
				//Listcounter will increse by one
				Itemlist[listcounter]=itnumber;
				taxidlist[listcounter]=taxid;
				taxamountlist[listcounter]=Taxper;
				taxamountlistfinal[listcounter]=Math.round(Taxper*itamount)/100.0;
				discountamountlist[listcounter]=Dicount;
		} catch (NumberFormatException e) {e.printStackTrace();
		} 
	}
	
	public void FinditemNumber(int itnumber)
	{
		numberfromfind=itnumber;
		function109(numberfromfind);
	}
	public void funbarcode(String barcode_tex)
	{		
		try{
			String temp=barcode_tex;
			if(temp!=null)
			{
			float pp=(float) 0.0;
			double finalp=0.0;
			int lastQ=0;
			if(tcounter==true)
			{
				lastQ=Integer.valueOf(String.valueOf(table_register.getModel().getValueAt(table_register.getRowCount()-1, 0)));
				DefaultTableModel model = (DefaultTableModel)table_register.getModel();
				model.removeRow(table_register.getRowCount()-1);
			}
			else
			{
				lastQ=1;
			}
			tcounter=false;
    		DefaultTableModel model = (DefaultTableModel)table_register.getModel();
			s4=conn.prepareStatement("SELECT items.Item_id,items.Item_Name,item_quantity_matrix.*, item_price.Item_retail_price,Items.Tax_id, tax.Type,tax.Per "
					+ "FROM items left outer join `item_quantity_matrix` on items.Item_id = item_quantity_matrix.item_id "
					+ "left outer join item_price  on items.Item_id = item_price.Item_id LEFT OUTER JOIN tax on items.Tax_id = tax.Tax_id "
					+ "WHERE items.`item_id` in (Select item_id from alternative where Alt_barcode='"+temp+"') or items.item_id in "
					+ "( select item_id from barcode where Barcode='"+temp+"')");
			r3=s4.executeQuery();
			
			if(r3.isBeforeFirst()) {
					boolean b = r3.last();
			        int numberOfRecords = 0;
			        numberOfRecords = r3.getRow();
			        r3.beforeFirst();
			        if(numberOfRecords==1){
			        	JOptionPane.showMessageDialog(null, numberOfRecords);
			        }else{
			        	JOptionPane.showMessageDialog(null, "More then one record found = "+numberOfRecords);
			        }
				while(r3.next())
				{
					finalp=Double.valueOf(r3.getString("Item_retail_price"))*lastQ;
					model.addRow(new Object[]{lastQ,r3.getString("item_name"),r3.getDouble("Item_retail_price"),0.0,df.format(finalp)});
					listfiller(Integer.valueOf(r3.getString("Item_id")), Integer.valueOf(r3.getString("Tax_id")), 0.0,finalp,Double.valueOf(r3.getString("per")));
				}
				}else {
					JOptionPane.showMessageDialog(null, "Item Not Found");
				}
				
				Itemno.setText(null);
				Price.setText(null);
			}
		} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		lablefill();
	}
}
