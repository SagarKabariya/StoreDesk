package Product;

import java.awt.EventQueue;
import java.awt.Font;
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
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.SQLError;
import com.mysql.jdbc.Statement;

import Connect.ConnectionManager;

import javax.swing.JDesktopPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

public class Product_Receive extends JInternalFrame {
	private JPanel contentPane;
	private JTextField txt_upc;
	private JTextField txt_itemno;
	private JTextField txt_name;
	JTextArea txt_desc;
	private JTable table_list;
	public int MainItemNo;
	JLabel lbl_itemno;
	JLabel lbl_current_stock;
	JLabel lbl_last_r_date;
	JLabel lbl_last_r_quan;
	JLabel lbl_last_r_by ;
	JLabel lbl_sub_cate;
	JLabel lbl_main_c;
	JLabel lbl_company;
	JLabel lbl_tax_amount;
	JLabel lbl_tax_name;
	JComboBox cmb_comp;
	JComboBox comboBox;
	Connection conn=null;
	PreparedStatement st=null,s2,s3,s4;
	java.sql.Statement st1;
	Statement st2,st3;
	ResultSet rs=null,r2,r3,r4;
	private JTextField txt_itemname;
	private JTextField txtupdatestock;
	private JTable table_history;
	JComboBox cmb_main_cat = new JComboBox();
	JComboBox cmb_cate = new JComboBox();
	private JTable table_stock;
	private JTextField txt_retail_price;
	private JTextField txt_cost_price;
	private JTextField textField_2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Product_Receive frame = new Product_Receive();		
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
	public Product_Receive() {
		try{
	    	conn = (Connection) ConnectionManager.getConnection();
				 }catch(Exception e1){
					 e1.printStackTrace();
				 }
			 
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, screenSize.width, screenSize.height);
		int wid = screenSize.width;
		int hei=screenSize.height;
		getContentPane().setLayout(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		 ButtonGroup buttonGroup = new ButtonGroup();
		
	
		
		JPanel panel_find = new JPanel();
		panel_find.setBounds(0, 0, screenSize.width, screenSize.height-50);
		contentPane.add(panel_find);
		panel_find.setLayout(null);
		
		JLabel lblUpc = new JLabel("UPC");
		lblUpc.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblUpc.setBounds(10, 11, 55, 25);
		panel_find.add(lblUpc);
		
		JLabel lblItemNo = new JLabel("Item No");
		lblItemNo.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblItemNo.setBounds(207, 11, 86, 25);
		panel_find.add(lblItemNo);
		
		txt_upc = new JTextField();
		txt_upc.setFont(new Font("Tahoma", Font.BOLD, 13));
		txt_upc.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e){
	        	String temp=txt_upc.getText();
				if(temp!=null)
				{
					searchtable(Integer.valueOf(temp),1);
				}
	        }});
		txt_upc.setBounds(55, 11, 122, 24);
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
		txt_itemno.setFont(new Font("Tahoma", Font.BOLD, 13));
		txt_itemno.setBounds(279, 12, 111, 24);
		panel_find.add(txt_itemno);
		txt_itemno.setColumns(10);
		
		txt_name = new JTextField();
		txt_name.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				bindtablename();
			}
		});
		txt_name.setFont(new Font("Tahoma", Font.BOLD, 13));
		txt_name.setBounds(479, 11, 222, 25);
		panel_find.add(txt_name);
		txt_name.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblName.setBounds(425, 11, 55, 25);
		panel_find.add(lblName);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 47, screenSize.width-40, screenSize.height-70);
		panel_find.add(scrollPane);
		
		table_list = new JTable();
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
		btnClose.setBounds(711, 11, 89, 23);
		panel_find.add(btnClose);
		
		table_list.getColumnModel().getColumn(0).setPreferredWidth(50);
		table_list.getColumnModel().getColumn(1).setPreferredWidth(200);
		table_list.getColumnModel().getColumn(2).setPreferredWidth(150);
		table_list.getColumnModel().getColumn(3).setPreferredWidth(100);
		table_list.getColumnModel().getColumn(4).setPreferredWidth(50);
		
		
		
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setLocation(0, 0);
		tabbedPane.setSize(screenSize.width, screenSize.height-50);
		contentPane.add(tabbedPane);
		tabbedPane.setVisible(false);
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Name & Category", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblName_1 = new JLabel("Main Category");
		lblName_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblName_1.setBounds(10, 11, 118, 26);
		panel_1.add(lblName_1);
		
		JLabel lblSubCategory = new JLabel("Sub Category");
		lblSubCategory.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSubCategory.setBounds(310, 11, 118, 26);
		panel_1.add(lblSubCategory);
		
		JLabel lblItemName = new JLabel("Item Name");
		lblItemName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblItemName.setBounds(10, 84, 118, 26);
		panel_1.add(lblItemName);
		cmb_main_cat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		
		
		cmb_main_cat.setBounds(138, 45, 142, 20);
		panel_1.add(cmb_main_cat);
		
		
		cmb_cate.setBounds(436, 45, 142, 20);
		panel_1.add(cmb_cate);
		
		txt_itemname = new JTextField();
		txt_itemname.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txt_itemname.setBounds(138, 76, 442, 41);
		panel_1.add(txt_itemname);
		txt_itemname.setColumns(10);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updatename();
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnUpdate.setBounds(612, 127, 96, 55);
		panel_1.add(btnUpdate);
		
		JLabel lblItemDescripation = new JLabel("Item Descripation");
		lblItemDescripation.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblItemDescripation.setBounds(10, 141, 142, 26);
		panel_1.add(lblItemDescripation);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(138, 118, 442, 74);
		panel_1.add(scrollPane_1);
		
		txt_desc = new JTextArea();
		scrollPane_1.setViewportView(txt_desc);
		txt_desc.setRows(3);
		txt_desc.setLineWrap(true);
		
		JLabel lblItemNumber = new JLabel("Item Number");
		lblItemNumber.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblItemNumber.setBounds(612, 11, 128, 26);
		panel_1.add(lblItemNumber);
		
		lbl_itemno = new JLabel("");
		lbl_itemno.setFont(new Font("Arial", Font.BOLD, 16));
		lbl_itemno.setBounds(612, 33, 96, 37);
		panel_1.add(lbl_itemno);
		
		lbl_main_c = new JLabel("");
		lbl_main_c.setBounds(138, 11, 142, 22);
		panel_1.add(lbl_main_c);
		
		lbl_sub_cate = new JLabel("");
		lbl_sub_cate.setBounds(438, 11, 142, 22);
		panel_1.add(lbl_sub_cate);
		
		JLabel lblChange = new JLabel("change");
		lblChange.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblChange.setBounds(66, 41, 62, 25);
		panel_1.add(lblChange);
		
		JButton btnFindAgain_1 = new JButton("Find Again");
		btnFindAgain_1.setBounds(10, hei-140, 118, 87);
		panel_1.add(btnFindAgain_1);
		
		JLabel label = new JLabel("Company Name");
		label.setFont(new Font("Arial", Font.BOLD, 14));
		label.setBounds(10, 225, 142, 21);
		panel_1.add(label);
		
		lbl_company = new JLabel("");
		lbl_company.setFont(new Font("Arial", Font.BOLD, 16));
		lbl_company.setBounds(162, 221, 280, 30);
		panel_1.add(lbl_company);
		
		JLabel label_2 = new JLabel("Change Company");
		label_2.setFont(new Font("Arial", Font.BOLD, 14));
		label_2.setBounds(10, 267, 142, 21);
		panel_1.add(label_2);
		
		cmb_comp = new JComboBox();
		cmb_comp.setFont(new Font("Arial", Font.BOLD, 14));
		cmb_comp.setBounds(162, 268, 280, 30);
		panel_1.add(cmb_comp);
		
		JButton button = new JButton("Change ");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updatecompany();
			}
		});
		button.setBounds(458, 267, 89, 30);
		panel_1.add(button);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 212, 700, 2);
		panel_1.add(separator_1);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Stock & Updates", null, panel_3, null);
		panel_3.setLayout(null);
		
		JLabel lblItemStock = new JLabel("Item Stock");
		lblItemStock.setFont(new Font("Arial", Font.PLAIN, 14));
		lblItemStock.setBounds(10, 11, 81, 28);
		panel_3.add(lblItemStock);
		
		JLabel lblLastRecieveQuantity = new JLabel("Last Recieve Quantity");
		lblLastRecieveQuantity.setFont(new Font("Arial", Font.PLAIN, 14));
		lblLastRecieveQuantity.setBounds(10, 50, 151, 28);
		panel_3.add(lblLastRecieveQuantity);
		
		JLabel lblLastRecieveDate = new JLabel("Last Recieve Date");
		lblLastRecieveDate.setFont(new Font("Arial", Font.PLAIN, 14));
		lblLastRecieveDate.setBounds(10, 89, 151, 28);
		panel_3.add(lblLastRecieveDate);
		
		JLabel lblLastRecieveBy = new JLabel("Last Recieve By");
		lblLastRecieveBy.setFont(new Font("Arial", Font.PLAIN, 14));
		lblLastRecieveBy.setBounds(10, 128, 151, 28);
		panel_3.add(lblLastRecieveBy);
		
		lbl_last_r_quan = new JLabel("");
		lbl_last_r_quan.setFont(new Font("Arial", Font.PLAIN, 14));
		lbl_last_r_quan.setBounds(176, 50, 163, 28);
		panel_3.add(lbl_last_r_quan);
		
		lbl_last_r_date = new JLabel("");
		lbl_last_r_date.setFont(new Font("Arial", Font.PLAIN, 14));
		lbl_last_r_date.setBounds(176, 89, 163, 28);
		panel_3.add(lbl_last_r_date);
		
		lbl_last_r_by = new JLabel("");
		lbl_last_r_by.setFont(new Font("Arial", Font.PLAIN, 14));
		lbl_last_r_by.setBounds(176, 128, 163, 28);
		panel_3.add(lbl_last_r_by);
		
		lbl_current_stock = new JLabel("");
		lbl_current_stock.setFont(new Font("Arial", Font.PLAIN, 14));
		lbl_current_stock.setBounds(176, 11, 163, 28);
		panel_3.add(lbl_current_stock);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(359, 11, 2, 402);
		panel_3.add(separator);
		
		JLabel lblAddStock = new JLabel("Update Stock");
		lblAddStock.setFont(new Font("Arial", Font.PLAIN, 14));
		lblAddStock.setBounds(371, 11, 117, 28);
		panel_3.add(lblAddStock);
		
		txtupdatestock = new JTextField();
		txtupdatestock.setBounds(498, 12, 140, 28);
		panel_3.add(txtupdatestock);
		txtupdatestock.setColumns(10);
		
		JButton btnUpdate_1 = new JButton("Update");
		btnUpdate_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updatestock();
			}
		});
		btnUpdate_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnUpdate_1.setBounds(369, 89, 96, 55);
		panel_3.add(btnUpdate_1);
		
		JButton btnSetZero = new JButton("Set Zero");
		btnSetZero.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSetZero.setBounds(498, 89, 96, 55);
		panel_3.add(btnSetZero);
		
		JLabel label_1 = new JLabel("Cost Price");
		label_1.setFont(new Font("Arial", Font.BOLD, 14));
		label_1.setBounds(10, 242, 111, 27);
		panel_3.add(label_1);
		
		JLabel label_3 = new JLabel("Retail Price");
		label_3.setFont(new Font("Arial", Font.BOLD, 14));
		label_3.setBounds(10, 280, 111, 27);
		panel_3.add(label_3);
		
		txt_retail_price = new JTextField();
		txt_retail_price.setFont(new Font("Arial Black", Font.BOLD, 14));
		txt_retail_price.setColumns(10);
		txt_retail_price.setBounds(131, 279, 167, 27);
		panel_3.add(txt_retail_price);
		
		txt_cost_price = new JTextField();
		txt_cost_price.setFont(new Font("Arial Black", Font.BOLD, 14));
		txt_cost_price.setColumns(10);
		txt_cost_price.setBounds(131, 241, 167, 27);
		panel_3.add(txt_cost_price);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Arial Black", Font.BOLD, 14));
		textField_2.setColumns(10);
		textField_2.setBounds(131, 317, 167, 27);
		panel_3.add(textField_2);
		
		JLabel label_4 = new JLabel("Profit");
		label_4.setFont(new Font("Arial", Font.BOLD, 14));
		label_4.setBounds(10, 318, 111, 27);
		panel_3.add(label_4);
		
		JButton button_1 = new JButton("Update");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateprice();
			}
		});
		button_1.setFont(new Font("Arial Black", Font.PLAIN, 14));
		button_1.setBounds(131, 355, 111, 58);
		panel_3.add(button_1);
		
		JLabel label_5 = new JLabel("Taxes");
		label_5.setFont(new Font("Arial", Font.BOLD, 14));
		label_5.setBounds(371, 242, 111, 27);
		panel_3.add(label_5);
		
		
		lbl_tax_name = new JLabel("");
		lbl_tax_name.setFont(new Font("Arial", Font.BOLD, 14));
		lbl_tax_name.setBounds(371, 280, 111, 27);
		panel_3.add(lbl_tax_name);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"MD tax", "Alcohol Tax", "NO TAX"}));
		comboBox.setBounds(371, 366, 111, 29);
		panel_3.add(comboBox);
		
		JLabel label_7 = new JLabel("Change Tax Type");
		label_7.setFont(new Font("Arial", Font.BOLD, 14));
		label_7.setBounds(371, 328, 159, 27);
		panel_3.add(label_7);
		
		lbl_tax_amount = new JLabel("");
		lbl_tax_amount.setFont(new Font("Arial", Font.BOLD, 14));
		lbl_tax_amount.setBounds(522, 280, 111, 27);
		panel_3.add(lbl_tax_amount);
		
		JButton button_2 = new JButton("Apply");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			updatetax();
			}
		});
		button_2.setFont(new Font("Arial", Font.BOLD, 13));
		button_2.setBounds(527, 369, 111, 29);
		panel_3.add(button_2);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 195, 700, 2);
		panel_3.add(separator_2);
		
		JPanel panel_5 = new JPanel();
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
		btnAll.setFont(new Font("Arial", Font.BOLD, 14));
		btnAll.setBounds(163, 11, 143, 36);
		panel_5.add(btnAll);
		
		JButton btnChangeOrder = new JButton("Asce/Des.");
		btnChangeOrder.setFont(new Font("Arial", Font.BOLD, 14));
		btnChangeOrder.setBounds(316, 11, 143, 36);
		panel_5.add(btnChangeOrder);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 58, screenSize.width-50, 200);
		panel_5.add(scrollPane_2);
		
		table_history = new JTable();
		table_history.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Invoice ID","Date/Time","Employee","Quantity","Disocount","Sold Price",""
			}
		));
		scrollPane_2.setViewportView(table_history);
		
		JPanel Extra = new JPanel();
		tabbedPane.addTab("Stock Details", null, Extra, null);
		Extra.setLayout(null);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 40, wid-40, hei-150);
		Extra.add(scrollPane_3);
		
		table_stock = new JTable();
		table_stock.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Date of Recieve", "Quantity","Cost Price", "Employee","Vendor"
			}
		));
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
		});
		
		btnFindAgain_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((DefaultTableModel) table_list.getModel()).setRowCount(0);
				((DefaultTableModel) table_history.getModel()).setRowCount(0);
				((DefaultTableModel) table_list.getModel()).setRowCount(0);
				txt_itemno.setEnabled(true);
				txt_itemno.setText("");
				txt_upc.setText("");
				txt_name.setText("");
				txtupdatestock.setText("");
					tabbedPane.setVisible(false);
					tabbedPane.setEnabled(false);
					panel_find.setVisible(true);
			}
		});;
	}
	
	
	

	void updatestock()
	{
		try {
			if(txtupdatestock.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "Where is the Number?");
			}else{	
				st1 = conn.createStatement();
				String sql = "update item_stock set `Item_stock`="+Integer.valueOf(txtupdatestock.getText())+" where `Item_id` ="+MainItemNo;
				st1.executeUpdate(sql);
				JOptionPane.showMessageDialog(null, "Stock Changed", "ALERT", 1);
			}
				} catch (SQLException e1) {
						e1.printStackTrace();
				}
	}
	protected void updateprice() {
		try {
			if((txt_cost_price.getText().equals("")) || (txt_retail_price.getText().equals("")))
			{
				JOptionPane.showMessageDialog(null, "Where is the price?");
			}else if((Double.valueOf(txt_cost_price.getText())<0.1) || (Double.valueOf(txt_retail_price.getText())<0.1)){
				JOptionPane.showMessageDialog(null, "Seriously price is than 0.1, Enter Proper");
			}else{	
				st1 = conn.createStatement();
				String sql = "UPDATE `item_price` SET `Item_cost_price`="+Double.valueOf(txt_cost_price.getText())+","
						+ "`Item_retail_price`="+Double.valueOf(txt_retail_price.getText())+" where `Item_id` = "+MainItemNo;
				st1.executeUpdate(sql);
				JOptionPane.showMessageDialog(null, "Priced Changed", "ALERT", 1);
			}
				} catch (SQLException e1) {
						e1.printStackTrace();
				}
	}

	protected void updatecompany() {
		try {
			int venid=getidfromcmb(String.valueOf(cmb_comp.getSelectedItem()), 3);
			st1 = conn.createStatement();
			 String sql = "UPDATE items set `Item_vendor_id` = "+venid+" where `Item_id` = "+MainItemNo;
             st1.executeUpdate(sql);
             JOptionPane.showMessageDialog(null, "Database Updated", "ALERT", 1);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	protected void updatetax() {
		try {
				int idn=getidfromcmb(String.valueOf(comboBox.getSelectedItem()), 4);
				
				st1 = conn.createStatement();
				String sql = "update items set Tax_id="+idn+" where Item_id ="+MainItemNo;
				st1.executeUpdate(sql);
				JOptionPane.showMessageDialog(null, "Tax Changed", "ALERT", 1);
			
				} catch (SQLException e1) {
						e1.printStackTrace();
				}
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
	
private void updatename() {
	try {
		int cateid=getidfromcmb(String.valueOf(cmb_cate.getSelectedItem()), 1);
		st1 = conn.createStatement();
		 String sql = "UPDATE `items` SET `sub_category_id`="+cateid+",`Item_Name` = '"+txt_itemname.getText()+"',`Item_desc` ='"+txt_desc.getText()+"' where `Item_id` = "+MainItemNo;
         st1.executeUpdate(sql);
         JOptionPane.showMessageDialog(null, "Category Changed", "ALERT", 1);
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
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
	
	void bindcategory(String query,int device)
	{
		try {	
			ArrayList<String> groupNames = new ArrayList<String>(); 
			PreparedStatement stm = conn.prepareStatement(query); 
			ResultSet rs = stm.executeQuery(query); 
			rs.next();
			while (rs.next()) { 
			    String groupName = rs.getString(1); 
			    groupNames.add(groupName);			}
			rs.close();
			DefaultComboBoxModel model = new DefaultComboBoxModel(groupNames.toArray());
			if(device==1)
			{
				cmb_main_cat.setModel(model);
			}
			else if(device==2)
			{
				cmb_cate.setModel(model);
			}
			else 
			{
				cmb_comp.setModel(model);
			}
		} catch (SQLException e3) {
			e3.printStackTrace();
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
		String query = "SELECT Category_name FROM `category`"; 
		String query2 = "SELECT sub_category_name FROM `sub_category`"; 
		String query3 = "SELECT `Vendor_name` FROM `vendors`";
		bindcategory(query,1);
		bindcategory(query2,2);
		bindcategory(query3,3);
		PreparedStatement n1;
		ResultSet n2;
	    //String query = "SELECT `Item_Name` FROM `items` WHERE `Item_number` =79";
		
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
				 txt_cost_price.setText(r3.getString("Item_cost_price"));
				 txt_retail_price.setText(r3.getString("Item_retail_price"));
				 lbl_company.setText(r3.getString("Vendor_name"));
				 lbl_tax_name.setText(r3.getString("Type"));
				 lbl_tax_amount.setText(r3.getString("Per"));
			}
		} catch (SQLException e3) {
			e3.printStackTrace();
		}
	}
}
