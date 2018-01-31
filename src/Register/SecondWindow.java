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


public class SecondWindow extends JFrame implements KeyListener{
	DecimalFormat df = new DecimalFormat("#.###"); 
	public static double remaining_change,entered_amount;
	public static int option_payment;
	public Object[][] voidlist = new Object[10][10];
	public int voidcounter=0;
	JLabel lbl_total_qua;
	public int emp_id;
	public int numberfromfind;
	public double final_total_amount;
	public static boolean invdone=false;
	JButton Btnlogin;
	double[] discountamountlist =new double[500];
	int[] discounttype =new int[500];
	double[] discount =new double[500];
	double[] taxamountlist =new double[500];	
	double[] taxamountlistfinal =new double[500];	
	String[] receipt_data= new String[20];
	int[] taxidlist =new int[100];	
	int[] Itemlist =new int[100];
	private char c ;
	public Integer total_quantity=0;
	public static Integer tm=0,xx;
	public boolean tcounter=false;
	public int rownumber=0;
	double abc;
	static int listcounter=-1;
	private JPanel contentPane;
	JTextField Itemno = new JTextField();
	JTextField Price = new JTextField();
	Object[] message = {
		    "Item NO:", Itemno,
		    "Price:", Price
		};
	static String left;
	public static double reminder;
	public JLabel lbl_last_tot_itm ;
	public JLabel lbl_last_tot_amt ;
	public JLabel lbl_change ;
	public JLabel lbl_last_BNO ;
	private JTable table;
	Integer invoice_id;
	Connection conn=null;
	PreparedStatement st=null,s2,s3,s4,s1;
	ResultSet rs=null,r2,r3,r4,r1;
	private JTable table_register;
	private JTextField item_no;
	public char a ;
	public int counter=0;
	String bb=" ";
	Calendar cal = Calendar.getInstance();
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Date today = new Date();
	java.util.Date date = new java.util.Date();
	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	ArrayList data = new ArrayList();
	private JTextField textField_focus;
	private JTextField textField_focus_2;
	JPanel panel_history;
	private JTable table_change;
	public static int transection_status = 0;
	private JTable table_void;
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
		
		JPanel panel_register = new JPanel();
		panel_register.setBackground(new Color(70, 130, 180));
		desktopPane.add(panel_register, "name_376006313536450");
		panel_register.setBounds(0, 0, wid, hei);
		panel_register.setLayout(null);
		
		JScrollPane scrollBar = new JScrollPane();
		scrollBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				textField_focus.requestFocus();
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.setBounds(10, hei-200, 200, 180);
		panel_register.add(scrollPane);
		
		table_change = new JTable();
		table_change.setBackground(new Color(70, 130, 180));
		table_change.setColumnSelectionAllowed(false);
		table_change.setRowSelectionAllowed(false);
		table_change.setShowGrid(false);
		table_change.setShowHorizontalLines(false);
		table_change.setShowVerticalLines(false);
		table_change.setTableHeader(null);
		table_change.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Field", "Amount"
			}
		));
		table_change.setEnabled(false);
		table_change.setRowHeight(25);
		table_change.setFont(new Font("Serif", Font.BOLD, 18));
		table_change.setCellSelectionEnabled(true);
		DefaultTableModel model = (DefaultTableModel)table_change.getModel();
		model.addRow(new Object[]{"Sub Total"});
		model.addRow(new Object[]{"Tax"});
		model.addRow(new Object[]{"Total"});
		model.addRow(new Object[]{"Credit Sale"});
		model.addRow(new Object[]{"Cash Sale"});
		model.addRow(new Object[]{"Remaining"});
		model.setValueAt(0, 3, 1);
		model.setValueAt(0, 4, 1);
		model.setValueAt(0, 5, 1);
		
		scrollPane.setViewportView(table_change);
		scrollBar.setEnabled(false);
		scrollBar.setBounds(10, 95, wid-40,(hei/2));
		scrollBar.setBorder(BorderFactory.createEmptyBorder());
		panel_register.add(scrollBar);
		
		JButton btnCloseRegister = new JButton("close Register");
		btnCloseRegister.setFont(new Font("Arial Black", Font.PLAIN, 12));
		btnCloseRegister.setBounds(wid-170, 11, 150, 45);
		panel_register.add(btnCloseRegister);
		
		table_register = new JTable();
		table_register.setFont(new Font("Tahoma", Font.BOLD, 12));
		table_register.setBackground(new Color(102, 204, 153));
		table_register.setShowGrid(false);
		table_register.setShowVerticalLines(false);
		table_register.setShowHorizontalLines(false);
		table_register.setRowHeight(20);
		table_register.setColumnSelectionAllowed(true);
		table_register.setCellSelectionEnabled(true);
		table_register.setBorder(null);
		table_register.setBounds(0, 0, wid, hei);
		scrollBar.setViewportView(table_register);
		
		table_register.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Quantity", "Item Name", "Price", "Discount", "Final Price"
			}
		));

		table_register.getColumnModel().getColumn(0).setPreferredWidth(30);
		table_register.getColumnModel().getColumn(1).setPreferredWidth(250);
		table_register.getColumnModel().getColumn(2).setPreferredWidth(30);
		table_register.getColumnModel().getColumn(3).setPreferredWidth(30);
		
		textField_focus = new JTextField();
		textField_focus.setBackground(Color.WHITE);
		textField_focus.addActionListener(new ActionListener(){

	        public void actionPerformed(ActionEvent e){
	        	
	        	funbarcode(textField_focus.getText());
	        	textField_focus.setText(null);	        	
	        }});
		textField_focus.setBounds(568, 595, 112, 23);
		panel_register.add(textField_focus);
		textField_focus.setColumns(10);
		//textField_focus.setVisible(false);
		
		textField_focus_2 = new JTextField();
		textField_focus_2.setForeground(SystemColor.inactiveCaption);
		textField_focus_2.setBackground(Color.WHITE);
		
		textField_focus_2.addActionListener(new ActionListener(){

	        public void actionPerformed(ActionEvent e){
	        	
	             funbarcode(textField_focus_2.getText());
	             textField_focus_2.setText(null);
	        }});
		textField_focus_2.setBounds(690, 596, 112, 22);
		panel_register.add(textField_focus_2);
		textField_focus_2.setColumns(10);
		
		JPanel panel_btn = new JPanel();
		panel_btn.setBorder(null);
		panel_btn.setBackground(new Color(70, 130, 180));
		panel_btn.setBounds(wid-470, hei-200, 455, 190);
		panel_register.add(panel_btn);
		panel_btn.setLayout(null);
		
		JButton btnClearList = new JButton("Clear List");
		btnClearList.setBounds(240, 1, 105, 50);
		panel_btn.add(btnClearList);
		
		JButton btnByCash = new JButton("By Cash");
		btnByCash.setBounds(0, 54, 110, 50);
		panel_btn.add(btnByCash);
		
		JButton btnReprintReciept = new JButton("Quantity");
		btnReprintReciept.setBounds(120, 54, 110, 50);
		panel_btn.add(btnReprintReciept);
		btnReprintReciept.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		
		JButton btnByCard = new JButton("By Card");
		btnByCard.setBounds(0, 1, 110, 50);
		panel_btn.add(btnByCard);
		
		JToggleButton tglbtnDrawer = new JToggleButton("Drawer");
		tglbtnDrawer.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if(tglbtnDrawer.isSelected()){
					
					tglbtnDrawer.setText("Print");
				
				}else{
					tglbtnDrawer.setText("Drawer");
				}
			}
		});
		tglbtnDrawer.setBounds(120, 1, 110, 50);
		panel_btn.add(tglbtnDrawer);
		tglbtnDrawer.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JButton btn_find = new JButton("Find");
		btn_find.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Find fi = new Find();
				fi.requestFocus();
				fi.setVisible(true);
			}
		});
		btn_find.setBounds(240, 54, 105, 50);
		panel_btn.add(btn_find);
		
		JButton btnReview = new JButton("RePrint");
		btnReview.setBounds(355, 54, 92, 50);
		panel_btn.add(btnReview);
		
		JButton btnSuspandrecall = new JButton("Save");
		btnSuspandrecall.setBounds(355, 1, 92, 50);
		panel_btn.add(btnSuspandrecall);
		
		JScrollPane scrollpane_void = new JScrollPane();
		scrollpane_void.setBounds(0, 115, 445, 50);
		panel_btn.add(scrollpane_void);
		scrollpane_void.setBorder(BorderFactory.createEmptyBorder());
		table_void = new JTable();
		table_void.setFillsViewportHeight(true);
		table_void.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Items"
			}
		));
		table_void.setBackground(new Color(70, 130, 180));
		table_void.setRowSelectionAllowed(false);
		table_void.setShowGrid(false);
		table_void.setShowHorizontalLines(false);
		table_void.setTableHeader(null);
		table_void.setFont(new Font("Serif", Font.BOLD, 12));
		scrollpane_void.setViewportView(table_void);
		btnSuspandrecall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int trno=getnum("Enter Number for Store Transectoin");
				
			}
		});
		btnReview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			ReviewTran rt = new ReviewTran();
			desktopPane.add(rt);
			rt.setVisible(true);
			}
		});
		
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
					invoice_remainging();
					invoicemake();
					panel_register.setRequestFocusEnabled(true);
					}
				}else if(transection_status==1){
					
					panel_register.setRequestFocusEnabled(false);
					Cashier ch = new Cashier((int) getquan(),remaining_change,0);
					ch.setVisible(true);
					invoice_remainging();
					invoicemake();
					panel_register.setRequestFocusEnabled(true);
				}
			}
		});
		
		btnReprintReciept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
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
					invoice_remainging();
					invoicemake();
					panel_register.setRequestFocusEnabled(true);
					}
				}else if(transection_status==1){
					panel_register.setRequestFocusEnabled(false);
					Cashier ch = new Cashier((int) getquan(),remaining_change,1);
					ch.setVisible(true);
					invoice_remainging();
					invoicemake();
					panel_register.setRequestFocusEnabled(true);
				}
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
		
		JPanel panel_history = new JPanel();
		panel_history.setBackground(new Color(70, 130, 180));
		panel_history.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				panel_history.setVisible(false);
			}
		});
		panel_history.setBounds(109, 11, 312, 75);
		panel_register.add(panel_history);
		panel_history.setLayout(null);
		
		JLabel lblChange = new JLabel("Change");
		lblChange.setBounds(214, 11, 76, 29);
		panel_history.add(lblChange);
		lblChange.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		lbl_change = new JLabel();
		lbl_change.setBounds(218, 33, 109, 39);
		panel_history.add(lbl_change);
		lbl_change.setFont(new Font("Arial Black", Font.BOLD, 20));
		
		JLabel label = new JLabel("Total Quantity");
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		label.setBounds(488, 11, 117, 29);
		panel_register.add(label);
		
		lbl_total_qua = new JLabel("");
		lbl_total_qua.setFont(new Font("Arial Black", Font.BOLD, 14));
		lbl_total_qua.setBounds(498, 45, 107, 35);
		panel_register.add(lbl_total_qua);
		
		JPanel panel_name = new JPanel();
		panel_name.setBounds(109, 11, 312, 75);
		panel_register.add(panel_name);
		panel_name.setLayout(null);
		
		JLabel lblHandcraftedLabPvt = new JLabel("HandCrafted Lab Pvt Ltd");
		lblHandcraftedLabPvt.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblHandcraftedLabPvt.setBounds(68, 22, 242, 29);
		panel_name.add(lblHandcraftedLabPvt);
		
		JPanel panel = new JPanel();
		panel.setBounds(738, 0, 663, 97);
		panel_register.add(panel);
		panel.setLayout(null);
		
		
		JLabel lblLastTransection = new JLabel("Last Transection");
		lblLastTransection.setBounds(90, 13, 109, 20);
		panel.add(lblLastTransection);
		
		JLabel lblTotalItems = new JLabel("Total Items");
		lblTotalItems.setBounds(12, 33, 85, 14);
		panel.add(lblTotalItems);
		
		JLabel lblTotalAmount = new JLabel("Total Amount");
		lblTotalAmount.setBounds(12, 49, 85, 14);
		panel.add(lblTotalAmount);
		
		JLabel lblBillNo = new JLabel("Bill No");
		lblBillNo.setBounds(12, 63, 46, 14);
		panel.add(lblBillNo);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(90, 31, 1, 46);
		panel.add(separator_1);
		separator_1.setOrientation(SwingConstants.VERTICAL);
		
		lbl_last_BNO= new JLabel();
		lbl_last_BNO.setBounds(100, 63, 99, 14);
		panel.add(lbl_last_BNO);
		
		lbl_last_tot_amt = new JLabel();
		lbl_last_tot_amt.setBounds(100, 48, 99, 14);
		panel.add(lbl_last_tot_amt);
		lbl_last_tot_amt.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		lbl_last_tot_itm= new JLabel();
		lbl_last_tot_itm.setBounds(100, 32, 99, 14);
		panel.add(lbl_last_tot_itm);
		lbl_last_tot_itm.setBackground(Color.LIGHT_GRAY);
		lbl_last_tot_itm.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		Btnlogin = new JButton("Login");
		Btnlogin.setBounds(10, 7, 89, 75);
		panel_register.add(Btnlogin);
		Btnlogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			getuserdata();
			textField_focus.requestFocus();	
			}
		});
		Btnlogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
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
	private void default_table_fill() {
		DefaultTableModel model = (DefaultTableModel)table_change.getModel();
		model.addRow(new Object[]{"Sub Total"});
		model.addRow(new Object[]{"Tax"});
		model.addRow(new Object[]{"Total"});
		model.addRow(new Object[]{"Credit Sale"});
		model.addRow(new Object[]{"Cash Sale"});
		model.addRow(new Object[]{"Remaining Balance"});
		model.setValueAt(0, 3, 1);
		model.setValueAt(0, 4, 1);
		model.setValueAt(0, 5, 1);
	}

	
	protected void invoicemake() {
		
		if(invdone==true){
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
		String query="INSERT INTO `invoice`(`Employee_id`, `Total_Quantity`, `Total_discount`, `Total_tax`, `Final_amount`) "
				+ "VALUES ("+emp_id+","+ccquan+","+total_discount+","+table_change.getValueAt(1, 1)+","+table_change.getValueAt(2, 1)+")";
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
				line="SUB TOTAL : "+String.valueOf((table_change.getValueAt(0, 1)));
				bfw.write(line);
				bfw.newLine();
				line="TAX : "+String.valueOf((table_change.getValueAt(1, 1)));
				bfw.write(line);
				bfw.newLine();
				line="Total Amount : "+String.valueOf((table_change.getValueAt(2, 1)));
				bfw.write(line);
				bfw.newLine();
				line="Credit Sale SALE : "+String.valueOf((table_change.getValueAt(3, 1)));
				bfw.write(line);
				bfw.newLine();
				line="Cash Sale : "+String.valueOf((table_change.getValueAt(4, 1)));
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
	public void invoice_remainging()
	{
		DefaultTableModel model = (DefaultTableModel)table_change.getModel();
		 if(option_payment==0)
			{
			    double rmvalue=Double.valueOf(model.getValueAt(3, 1).toString());
				 table_change.getModel().setValueAt(rmvalue+entered_amount,3, 1);
			}else
			{
				double rmvalue=Double.valueOf(model.getValueAt(4, 1).toString());
				 table_change.getModel().setValueAt(rmvalue+entered_amount,4, 1);
			}
		 model.setValueAt(Double.valueOf(df.format(remaining_change)), model.getRowCount()-1, 1);
	}
	public void invoicedone(double rr,int mm,double finalamt)
	{
		if(rr == finalamt)
		{
		invdone=true;
		entered_amount=rr;
		remaining_change=finalamt-rr;
		option_payment=mm;
		this.lbl_last_tot_amt.setText(String.valueOf(finalamt));
		this.lbl_last_tot_itm.setText(String.valueOf(getquan()));
		reminder=finalamt-rr;	
		}else if(rr > finalamt)
		{
			invdone=true;
			entered_amount=rr;
			remaining_change=finalamt-rr;
			option_payment=mm;
			this.lbl_last_tot_amt.setText(String.valueOf(finalamt));
			this.lbl_last_tot_itm.setText(String.valueOf(getquan()));
			reminder=rr-finalamt;
		}else if(rr < finalamt)
		{
			entered_amount=rr;
			remaining_change=finalamt-rr;
			option_payment=mm;
			transection_status=1;
		}
		invoice_remainging();
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
		    
		    DefaultTableModel model = (DefaultTableModel)table_change.getModel();
		    model.setValueAt(null, 0, 1);
		    model.setValueAt(null, 1, 1);
		    model.setValueAt(null, 1, 1);
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
			DefaultTableModel model = (DefaultTableModel)table_change.getModel();
		    model.setValueAt(null, 0, 1);
		    model.setValueAt(null, 1, 1);
		    model.setValueAt(null, 1, 1);
		lablefill();	
		}
	}
	public void clearpage()
	{
		invdone=false;
		((DefaultTableModel) table_change.getModel()).setRowCount(0);
		((DefaultTableModel) table_void.getModel()).setRowCount(0);
		lbl_total_qua.setText(null);
	    final_total_amount=0;
		listcounter=-1;	
		((DefaultTableModel) table_register.getModel()).setRowCount(0);
		default_table_fill();
		textField_focus.requestFocus();
		
	}
	public void lablefill()
	{
		double taxcal=0.0;
		double discal=0.0;
		for(int i=0;i<=listcounter;i++){
			taxcal=taxcal+taxamountlistfinal[i];
		}
		DefaultTableModel model = (DefaultTableModel)table_change.getModel();
		double total_a=Double.valueOf(getamount());
		final_total_amount=total_a+Double.valueOf(df.format(taxcal));
		textField_focus.requestFocus();
		lbl_total_qua.setText(String.valueOf(Integer.valueOf(getquan())));
		model.setValueAt(String.valueOf(df.format(getamount())), 0, 1);
		model.setValueAt(df.format(taxcal), 1, 1);
		model.setValueAt(String.valueOf(df.format(final_total_amount)), 2, 1);
	}
	public void function109(int no)
	{		try{
				double pp= 0.0;
				double finalp=10.5;
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
				Itemlist[listcounter]=itnumber;
				taxidlist[listcounter]=taxid;
				taxamountlist[listcounter]=Taxper;
				taxamountlistfinal[listcounter]=Math.round(Taxper*itamount)/100.0;
				discountamountlist[listcounter]=0.0;
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
			double finalp=10.5;
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
			        	JOptionPane.showMessageDialog(null, numberOfRecords);
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
