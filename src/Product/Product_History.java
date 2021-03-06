package Product;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.SQLError;
import com.mysql.jdbc.Statement;

import Connect.ConnectionManager;

import javax.swing.JDesktopPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JCheckBox;
import java.awt.SystemColor;
import java.awt.Color;


public class Product_History extends JInternalFrame {
	private JPanel contentPane;
	JPanel panel_find;
	private JTextField txt_upc;
	private JTextField txt_itemno;
	private JTextField txt_name;
	JTextArea txt_desc;
	private JTable table_list;
	public int MainItemNo;
	JLabel lbl_itemno;
	JLabel lbl_sub_cate;
	JLabel lbl_main_c;
	JLabel lbl_company;
	JLabel lbl_current_stock;
	JLabel lbl_last_r_date;
	JLabel lbl_last_r_quan;
	JLabel lbl_last_r_by;
	JLabel lbl_tax_name;
	JLabel lbl_tax_amount;
	Connection conn=null;
	PreparedStatement st=null,s2,s3,s4;
	java.sql.Statement st1;
	Statement st2,st3;
	ResultSet rs=null,r2,r3,r4;
	private JTextField txt_itemname;
	private JTable table_history;
	private JTable table_stock;
	SimpleDateFormat dateFormatTime = new SimpleDateFormat("yyyy-mm-dd");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Product_History frame = new Product_History();		
					frame.setVisible(true);
					//BasicInternalFrameUI bi = (BasicInternalFrameUI)frame.getUI();
					//bi.setNorthPane(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Product_History() {
		
		((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
		setClosable(true);
		try{
	    	conn = (Connection) ConnectionManager.getConnection();
				 }catch(Exception e1){
					 e1.printStackTrace();
				 }
			 
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		int wid = screenSize.width;
		wid=wid-20;
		int hei=screenSize.height;
		hei=hei-((hei*10)/100);
		
		setBounds(0, 0, wid, hei);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		panel_find = new JPanel();
		panel_find.setBackground(Color.WHITE);
		panel_find.setBounds(0, 0, wid, hei-50);
		contentPane.add(panel_find);
		panel_find.setLayout(null);
		
		JLabel lblUpc = new JLabel("UPC");
		lblUpc.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblUpc.setBounds(10, 13, 55, 38);
		panel_find.add(lblUpc);
		
		JLabel lblItemNo = new JLabel("Item No");
		lblItemNo.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblItemNo.setBounds(321, 13, 86, 38);
		panel_find.add(lblItemNo);
		
		txt_upc = new JTextField();
		txt_upc.setFont(new Font("Tahoma", Font.BOLD, 20));
		txt_upc.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e){
	        	String temp=txt_upc.getText();
				if(temp!=null)
				{
					searchtable(Integer.valueOf(temp),1);
				}
	        }});
		txt_upc.setBounds(77, 13, 212, 38);
		panel_find.add(txt_upc);
		txt_upc.setColumns(10);
		
		txt_itemno = new JTextField();
		txt_itemno.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(txt_itemno.getText().equals("")){}else
				{
					String temp=txt_itemno.getText();
					searchtable(Integer.valueOf(temp),2);
				}
			}
		});
		txt_itemno.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
			}
		});
		txt_itemno.setFont(new Font("Tahoma", Font.BOLD, 20));
		txt_itemno.setBounds(404, 13, 212, 38);
		panel_find.add(txt_itemno);
		txt_itemno.setColumns(10);
		
		txt_name = new JTextField();
		txt_name.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				bindtablename();
			}
		});
		txt_name.setFont(new Font("Tahoma", Font.BOLD, 20));
		txt_name.setBounds(849, 13, 336, 38);
		panel_find.add(txt_name);
		txt_name.setColumns(10);
		
		JLabel lblName = new JLabel("Name of Product");
		lblName.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblName.setBounds(672, 13, 165, 38);
		panel_find.add(lblName);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 83, wid-20, hei-150);
		panel_find.add(scrollPane);
		
		table_list = new JTable();
		table_list.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table_list.setColumnSelectionAllowed(true);
		table_list.setCellSelectionEnabled(true);
		table_list.setShowGrid(false);
		table_list.setRowHeight(30);
		table_list.setShowHorizontalLines(false);
		scrollPane.setViewportView(table_list);
		table_list.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Item Name", "Vendor", "Stock", "Retail", "Cost"
			}
		));
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			dispose();
			}
		});
		btnClose.setBounds(1766, 13, 117, 57);
		panel_find.add(btnClose);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.WHITE);
		tabbedPane.setLocation(0, 0);
		tabbedPane.setSize(wid, hei-50);
		contentPane.add(tabbedPane);
		tabbedPane.setFont(new Font("Arial",20,20));
		
		tabbedPane.setVisible(false);
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		tabbedPane.addTab("Name & Category", null, panel_1, null);
		panel_1.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(12, 13, wid-20, tabbedPane.getHeight()-50);
		panel_1.add(panel);
		panel.setLayout(null);
		
		JLabel lblName_1 = new JLabel("Main Category");
		lblName_1.setBounds(63, 21, 145, 41);
		panel.add(lblName_1);
		lblName_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel lblSubCategory = new JLabel("Sub Category");
		lblSubCategory.setBounds(63, 86, 145, 42);
		panel.add(lblSubCategory);
		lblSubCategory.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel lblItemName = new JLabel("Item Name");
		lblItemName.setBounds(63, 169, 118, 26);
		panel.add(lblItemName);
		lblItemName.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		txt_itemname = new JTextField();
		txt_itemname.setBounds(220, 161, 442, 41);
		panel.add(txt_itemname);
		txt_itemname.setEditable(false);
		txt_itemname.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txt_itemname.setColumns(10);
		
		JLabel lblItemDescripation = new JLabel("Item Descripation");
		lblItemDescripation.setBounds(66, 263, 142, 26);
		panel.add(lblItemDescripation);
		lblItemDescripation.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(220, 234, 442, 74);
		panel.add(scrollPane_1);
		
		txt_desc = new JTextArea();
		txt_desc.setEditable(false);
		scrollPane_1.setViewportView(txt_desc);
		txt_desc.setRows(3);
		txt_desc.setLineWrap(true);
		
		JLabel lblItemNumber = new JLabel("Item Number");
		lblItemNumber.setBounds(507, 21, 198, 41);
		panel.add(lblItemNumber);
		lblItemNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemNumber.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		lbl_itemno = new JLabel("");
		lbl_itemno.setBounds(507, 76, 198, 52);
		panel.add(lbl_itemno);
		lbl_itemno.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_itemno.setFont(new Font("Tahoma", Font.BOLD, 25));
		
		lbl_main_c = new JLabel("");
		lbl_main_c.setBounds(220, 21, 243, 41);
		panel.add(lbl_main_c);
		lbl_main_c.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		lbl_sub_cate = new JLabel("");
		lbl_sub_cate.setBounds(220, 86, 243, 41);
		panel.add(lbl_sub_cate);
		lbl_sub_cate.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel label = new JLabel("Company Name");
		label.setBounds(65, 336, 142, 41);
		panel.add(label);
		label.setFont(new Font("Arial", Font.BOLD, 14));
		
		lbl_company = new JLabel("");
		lbl_company.setBounds(217, 336, 280, 41);
		panel.add(lbl_company);
		lbl_company.setFont(new Font("Arial", Font.BOLD, 16));
		
		JButton btnFindAgain = new JButton("Find Again");
		btnFindAgain.setBounds(834, 311, 154, 87);
		panel.add(btnFindAgain);
		btnFindAgain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tabbedPane.setVisible(false);
				table_list.repaint();
				panel_find.setVisible(true);
				panel_find.setEnabled(true);
				txt_itemno.setEnabled(true);
			}
		});
		btnFindAgain.setFont(new Font("Tahoma", Font.BOLD, 17));
		
		JButton btnCloseWindow = new JButton("Close Window");
		btnCloseWindow.setBounds(1004, 311, 212, 87);
		panel.add(btnCloseWindow);
		btnCloseWindow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCloseWindow.setFont(new Font("Tahoma", Font.BOLD, 17));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setLayout(null);
		panel_2.setBounds(842, 13, 543, 291);
		panel_1.add(panel_2);
		
		JLabel label_1 = new JLabel("Last Recieve Quantity");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_1.setBounds(22, 67, 214, 28);
		panel_2.add(label_1);
		
		JLabel label_2 = new JLabel("Last Recieve Date");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_2.setBounds(22, 106, 214, 28);
		panel_2.add(label_2);
		
		JLabel label_3 = new JLabel("Last Recieve By");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_3.setBounds(22, 145, 214, 28);
		panel_2.add(label_3);
		
		lbl_last_r_quan = new JLabel("");
		lbl_last_r_quan.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbl_last_r_quan.setBounds(248, 67, 258, 28);
		panel_2.add(lbl_last_r_quan);
		
		lbl_last_r_date = new JLabel("");
		lbl_last_r_date.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbl_last_r_date.setBounds(248, 106, 258, 28);
		panel_2.add(lbl_last_r_date);
		
		lbl_last_r_by = new JLabel("");
		lbl_last_r_by.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbl_last_r_by.setBounds(248, 145, 258, 28);
		panel_2.add(lbl_last_r_by);
		
		JLabel label_7 = new JLabel("Taxe Type");
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_7.setBounds(22, 196, 111, 27);
		panel_2.add(label_7);
		
		lbl_tax_name = new JLabel("");
		lbl_tax_name.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbl_tax_name.setBounds(160, 196, 111, 27);
		panel_2.add(lbl_tax_name);
		
		lbl_tax_amount = new JLabel("");
		lbl_tax_amount.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbl_tax_amount.setBounds(148, 236, 111, 27);
		panel_2.add(lbl_tax_amount);
		
		JLabel label_10 = new JLabel("Tax %");
		label_10.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_10.setBounds(22, 241, 81, 22);
		panel_2.add(label_10);
		
		JLabel label_11 = new JLabel("Item Stock");
		label_11.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_11.setBounds(22, 28, 214, 28);
		panel_2.add(label_11);
		
		lbl_current_stock = new JLabel("");
		lbl_current_stock.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbl_current_stock.setBounds(248, 28, 258, 28);
		panel_2.add(lbl_current_stock);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		tabbedPane.addTab("Product History", null, panel_5, null);
		panel_5.setLayout(null);
		
		JButton btnToday = new JButton("Today");
		btnToday.setFont(new Font("Arial", Font.BOLD, 14));
		btnToday.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tablehistoryfill(1);
			}
		});
		btnToday.setBounds(10, 11, 143, 36);
		panel_5.add(btnToday);
		
		JButton btnAll = new JButton("All");
		btnAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tablehistoryfill(2);
			}
		});
		btnAll.setFont(new Font("Arial", Font.BOLD, 14));
		btnAll.setBounds(163, 11, 143, 36);
		panel_5.add(btnAll);
		
		JButton btnChangeOrder = new JButton("Asce/Des.");
		btnChangeOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnChangeOrder.setFont(new Font("Arial", Font.BOLD, 14));
		btnChangeOrder.setBounds(316, 11, 143, 36);
		panel_5.add(btnChangeOrder);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 58, screenSize.width-50, 200);
		panel_5.add(scrollPane_2);
		
		table_history = new JTable();
		table_history.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table_history.setRowHeight(20);
		table_history.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Invoice ID","Date/Time","Employee","Quantity","Disocount","Sold Price",""
			}
		));
		scrollPane_2.setViewportView(table_history);
		
		JPanel Extra = new JPanel();
		Extra.setBackground(Color.WHITE);
		tabbedPane.addTab("Stock Details History", null, Extra, null);
		Extra.setLayout(null);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 40, wid-40, hei-150);
		Extra.add(scrollPane_3);
		
		table_stock = new JTable();
		table_stock.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table_stock.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Date of Recieve", "Quantity","Cost Price", "Employee","Vendor"
			}
		));
		table_stock.setRowHeight(20);
		
		scrollPane_3.setViewportView(table_stock);
		
		JButton btnNewButton = new JButton("Load");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					DefaultTableModel model = (DefaultTableModel)table_stock.getModel();
					model.setRowCount(0);
					try {
						s4=conn.prepareStatement("select item_receive.date_time,item_receive.Quantity,item_receive.cost_price,employee.Emp_name,vendors.Vendor_name "
								+ "from item_receive,vendors,employee,items "
								+ "where items.Item_id=item_receive.Item_id and item_receive.Employee_id=employee.Employee_id "
								+ "and item_receive.vendor_id=vendors.Vendor_id and items.Item_id = "+MainItemNo);
						r3=s4.executeQuery();						
						while(r3.next()){
							model.addRow(new Object[]{r3.getString("date_time"),r3.getString("Quantity"),r3.getString("cost_price"),r3.getString("Emp_name"),r3.getString("Vendor_name")});
						}
					} catch (SQLException e3) {
						e3.printStackTrace();
					}
			}
		});
		btnNewButton.setBounds(10, 0, 106, 39);
		Extra.add(btnNewButton);
		
		table_list.getColumnModel().getColumn(0).setPreferredWidth(50);
		table_list.getColumnModel().getColumn(1).setPreferredWidth(200);
		table_list.getColumnModel().getColumn(2).setPreferredWidth(150);
		table_list.getColumnModel().getColumn(3).setPreferredWidth(100);
		table_list.getColumnModel().getColumn(4).setPreferredWidth(50);
		
		
		table_list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = table_list.getSelectedRow();
				String value=(String) table_list.getModel().getValueAt(row, 0);
				filldata(Integer.valueOf(value));	
				panel_find.setVisible(false);
				panel_find.setEnabled(false);
				tabbedPane.setVisible(true);
				tabbedPane.setEnabled(true);
				txt_itemno.setEnabled(false);
			}
		});;
	}

	
	int getidfromcmb(String name,int opt)
	{
		int no=0;
		String query;
		if(opt==1)
		{
			query="SELECT `sub_category_id` FROM `sub_category` WHERE `sub_category_name` like '"+name+"'";
		}else if(opt==2){
			query="SELECT `Category_id` FROM `category` WHERE `Category_name` like '"+name+"'";
		}else if(opt==3){
			query="SELECT `Vendor_id` FROM `vendors` WHERE `Vendor_name` like '"+name+"'";
		}else{
			query="SELECT `Tax_id` FROM `tax` WHERE `Type` like '"+name+"'";
		}
	
		try {
			s4=conn.prepareStatement(query);
			r3=s4.executeQuery();
			r3.next();
			no=Integer.valueOf(r3.getString(1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}						
		return no;
	}

	protected void bindtablename() {
		String temp=txt_name.getText();
		if(temp!=null)
		{
			DefaultTableModel model = (DefaultTableModel)table_list.getModel();
			model.setRowCount(0);
			try {
				s4=conn.prepareStatement("select items.Item_number,items.Item_Name,vendors.Vendor_name,item_stock.Item_stock,"
						+ "item_price.Item_retail_price,item_price.Item_cost_price "
						+ "FROM items left outer join item_stock on items.Item_id = item_stock.Item_id "
						+ "left OUTER join item_receive on items.Item_id=item_receive.Item_id "
						+ "left OUTER join vendors on items.Item_vendor_id=vendors.Vendor_id "
						+ "left OUTER join item_price on items.Item_id=item_price.Item_id "
						+ "left OUTER join barcode on items.Item_id=barcode.Item_id "
						+ "WHERE items.Item_Name like '%"+temp+"%'");
				r3=s4.executeQuery();						
				while(r3.next()){
					model.addRow(new Object[]{r3.getString("Item_number"),r3.getString("item_name"),r3.getString("Vendor_name"),r3.getString("Item_stock"),r3.getString("Item_retail_price"),r3.getString("Item_cost_price")});
				}
			} catch (SQLException e3) {
				e3.printStackTrace();
			}
		}
		
	}

protected void tablehistoryfill(int i) {
	DefaultTableModel model = (DefaultTableModel)table_history.getModel();
	model.setRowCount(0);
	if(i==1)
	{
		//ALL Record 
			try {
				s4=conn.prepareStatement("SELECT invoice.Invoice_id, invoice.DateTime, employee.Emp_name, invoice_details.Quantity, invoice_details.Extra_Discount, "
						+ "invoice_details.Item_price, invoice_details.Final_items_amount "
						+ "from invoice,invoice_details,employee where invoice.Invoice_id=invoice_details.Invoice_id "
						+ "and invoice.Employee_id=employee.Employee_id and invoice_details.Item_id="+MainItemNo);
				r3=s4.executeQuery();						
				while(r3.next()){
					model.addRow(new Object[]{r3.getString("Invoice_id"),r3.getString("DateTime"),r3.getString("Emp_name"),r3.getString("Quantity"),r3.getString("Extra_Discount"),r3.getString("Item_price"),r3.getString("Final_items_amount")});
				}
			} catch (SQLException e3) {
				e3.printStackTrace();
			}
	} 
	else if (i==2) {
		try {
			//Today Only Record
			s4=conn.prepareStatement("SELECT invoice.Invoice_id, invoice.DateTime, employee.Emp_name, invoice_details.Quantity, invoice_details.Extra_Discount, "
					+ "invoice_details.Item_price, invoice_details.Final_items_amount "
					+ "from invoice,invoice_details,employee where invoice.Invoice_id=invoice_details.Invoice_id "
					+ "and invoice.Employee_id=employee.Employee_id AND invoice.DateTime ='"+dateFormatTime.format(Calendar.getInstance().getTime())+"' AND invoice_details.Item_id="+MainItemNo);
			r3=s4.executeQuery();						
			while(r3.next()){
				model.addRow(new Object[]{r3.getString("Invoice_id"),r3.getString("DateTime"),r3.getString("Emp_name"),r3.getString("Quantity"),r3.getString("Extra_Discount"),r3.getString("Item_price"),r3.getString("Final_items_amount")});
			}
		} catch (SQLException e3) {
			e3.printStackTrace();
		}
	}
	}
	void searchtable(int temp,int opt)
	{
		DefaultTableModel model = (DefaultTableModel)table_list.getModel();
		model.setRowCount(0);
		try {
			if(opt==1)
			{
			s4=conn.prepareStatement("select items.Item_number,items.Item_Name,vendors.Vendor_name,item_stock.Item_stock,item_price.Item_retail_price,item_price.Item_cost_price "
					+ "from items left outer join  item_stock on items.Item_id = item_stock.Item_id "
					+ "left OUTER join item_receive on items.Item_id=item_receive.Item_id "
					+ "left OUTER join vendors on items.Item_vendor_id=vendors.Vendor_id "
					+ "left OUTER join item_price on items.Item_id=item_price.Item_id "
					+ "left OUTER join barcode on items.Item_id=barcode.Item_id "
					+ "WHERE barcode.Barcode like '"+temp+"'");
			}else{
			s4=conn.prepareStatement("select items.Item_number,items.Item_Name,vendors.Vendor_name,item_stock.Item_stock,item_price.Item_retail_price,item_price.Item_cost_price "
					+ "from items left outer join  item_stock on items.Item_id = item_stock.Item_id "
					+ "left OUTER join item_receive on items.Item_id=item_receive.Item_id "
					+ "left OUTER join vendors on items.Item_vendor_id=vendors.Vendor_id "
					+ "left OUTER join item_price on items.Item_id=item_price.Item_id "
					+ "WHERE items.Item_number like '"+temp+"%'");
			}
			r3=s4.executeQuery();						
			while(r3.next()){
			model.addRow(new Object[]{r3.getString("Item_number"),r3.getString("item_name"),r3.getString("Vendor_name"),r3.getString("Item_stock"),r3.getString("Item_retail_price"),r3.getString("Item_cost_price")});
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	void filldata(int itemno)
	{
		PreparedStatement n1;
		ResultSet n2;
		try {
			s4=conn.prepareStatement("select employee.Emp_name,items.Item_id,items.Item_number,items.Item_Name,vendors.Vendor_name,item_stock.Item_stock,"
					+ "item_price.Item_retail_price, item_price.Item_cost_price,sub_category.sub_category_name, category.Category_name,tax.Type,tax.Per, "
					+ "barcode.Barcode,items.Item_desc,item_receive.date_time,item_receive.Quantity from category,sub_category,items "
					+ "left outer join  item_stock on items.Item_id = item_stock.Item_id "
					+ "left OUTER join item_receive on items.Item_id=item_receive.Item_id "
					+ "left OUTER join vendors on items.Item_vendor_id=vendors.Vendor_id "
					+ "left OUTER join item_price on items.Item_id=item_price.Item_id "
					+ "left OUTER join barcode on items.Item_id=barcode.Item_id "
					+ "left OUTER join tax on items.Tax_id=tax.Tax_id "
					+ "left OUTER join employee on item_receive.Employee_id=employee.Employee_id "
					+ "WHERE items.sub_category_id=sub_category.sub_category_id and sub_category.category_id=category.Category_id "
					+ "and items.Item_number ="+itemno);
			r3=s4.executeQuery();						
			while(r3.next()){
				MainItemNo=Integer.valueOf(r3.getString("Item_id"));
				 txt_itemname.setText(r3.getString("item_name"));
				 lbl_itemno.setText(String.valueOf(itemno));
				 lbl_current_stock.setText(r3.getString("item_stock"));
				 lbl_last_r_date.setText(r3.getString("date_time"));
				 lbl_last_r_quan.setText(r3.getString("Quantity"));
				 lbl_last_r_by.setText(r3.getString("Emp_name"));
				 lbl_main_c.setText(r3.getString("Category_name"));
				 lbl_sub_cate.setText(r3.getString("sub_category_name"));
				 lbl_company.setText(r3.getString("Vendor_name"));
				 lbl_tax_name.setText(r3.getString("Type"));
				 lbl_tax_amount.setText(r3.getString("Per"));
			}
		} catch (SQLException e3) {
			e3.printStackTrace();
		}
	}
}
