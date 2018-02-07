package Product;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
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
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.border.BevelBorder;

public class Product_Modify extends JFrame {
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
	JComboBox cmb_main_cat = new JComboBox();
	JComboBox cmb_cate = new JComboBox();
	private JTextField txt_retail_price;
	private JTextField txt_cost_price;
	private JTextField textField_2;
	private JTable table_alt;
	private JTable table_matrix;
	private JTextField txtdivide;
	private JTextField txtrpriceMat;
	JPanel Matrix_panel;
	JComboBox cmbsize;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Product_Modify frame = new Product_Modify();		
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
	public Product_Modify() {
		//setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		//setClosable(true);
		try{
	    	conn = (Connection) ConnectionManager.getConnection();
				 }catch(Exception e1){
					 e1.printStackTrace();
				 }
		Rectangle winSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();	 
		int wid = winSize.width;
		int hei=winSize.height;
		
		//Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, wid, hei);
		getContentPane().setLayout(null);
		contentPane = new JPanel();
		contentPane.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.BLACK));
		setContentPane(contentPane);
		contentPane.setLayout(null);
				
		ButtonGroup buttonGroup = new ButtonGroup();
			
		JPanel panel_find = new JPanel();
		panel_find.setBounds(0, 0, wid, hei-30);
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
		scrollPane.setBounds(10, 83, wid-20, panel_find.getHeight()-100);
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
		tabbedPane.setLocation(0, 0);
		tabbedPane.setSize(wid, hei);
		contentPane.add(tabbedPane);
		tabbedPane.setVisible(false);
		tabbedPane.setFont(new Font("Arial",20,20));
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Name & Category", null, panel_1, null);
		panel_1.setLayout(null);
		
		JButton btnFindAgain_1 = new JButton("Find Again");
		btnFindAgain_1.setBounds(20, panel_find.getHeight()-147, 118, 87);
		panel_1.add(btnFindAgain_1);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds((wid-350)-((wid*50)/100), 388, 700, 2);
		panel_1.add(separator_1);
		
		JPanel panel = new JPanel();
		panel.setBounds((wid-414)-((wid*50)/100), 412, 829, 99);
		panel_1.add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("Company Name");
		label.setBounds(0, 4, 142, 26);
		panel.add(label);
		label.setFont(new Font("Arial", Font.BOLD, 14));
		
		JLabel label_2 = new JLabel("Change Company");
		label_2.setBounds(0, 47, 142, 41);
		panel.add(label_2);
		label_2.setFont(new Font("Arial", Font.BOLD, 14));
		
		cmb_comp = new JComboBox();
		cmb_comp.setBounds(152, 47, 280, 41);
		panel.add(cmb_comp);
		cmb_comp.setFont(new Font("Arial", Font.BOLD, 14));
		
		lbl_company = new JLabel("");
		lbl_company.setBounds(152, 0, 280, 30);
		panel.add(lbl_company);
		lbl_company.setFont(new Font("Arial", Font.BOLD, 16));
		
		JButton button = new JButton("Change ");
		button.setBounds(448, 46, 96, 42);
		panel.add(button);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds((wid-413)-((wid*50)/100), 28, 827, 332);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblName_1 = new JLabel("Main Category");
		lblName_1.setBounds(12, 13, 118, 26);
		panel_2.add(lblName_1);
		lblName_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblSubCategory = new JLabel("Sub Category");
		lblSubCategory.setBounds(312, 13, 118, 26);
		panel_2.add(lblSubCategory);
		lblSubCategory.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblItemName = new JLabel("Item Name");
		lblItemName.setBounds(12, 186, 118, 41);
		panel_2.add(lblItemName);
		lblItemName.setFont(new Font("Tahoma", Font.BOLD, 14));
		cmb_main_cat.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cmb_main_cat.setBounds(84, 100, 220, 41);
		panel_2.add(cmb_main_cat);
		cmb_cate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cmb_cate.setBounds(362, 100, 240, 41);
		panel_2.add(cmb_cate);
		
		txt_itemname = new JTextField();
		txt_itemname.setBounds(160, 186, 442, 41);
		panel_2.add(txt_itemname);
		txt_itemname.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txt_itemname.setColumns(10);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(669, 186, 96, 55);
		panel_2.add(btnUpdate);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updatename();
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblItemDescripation = new JLabel("Item Descripation");
		lblItemDescripation.setBounds(12, 240, 142, 74);
		panel_2.add(lblItemDescripation);
		lblItemDescripation.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(160, 240, 442, 74);
		panel_2.add(scrollPane_1);
		
		txt_desc = new JTextArea();
		scrollPane_1.setViewportView(txt_desc);
		txt_desc.setRows(3);
		txt_desc.setLineWrap(true);
		
		JLabel lblItemNumber = new JLabel("Item Number");
		lblItemNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemNumber.setBounds(614, 13, 196, 26);
		panel_2.add(lblItemNumber);
		lblItemNumber.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		lbl_itemno = new JLabel("");
		lbl_itemno.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_itemno.setBounds(614, 52, 196, 37);
		panel_2.add(lbl_itemno);
		lbl_itemno.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		lbl_main_c = new JLabel("");
		lbl_main_c.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_main_c.setBounds(84, 48, 220, 39);
		panel_2.add(lbl_main_c);
		
		lbl_sub_cate = new JLabel("");
		lbl_sub_cate.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_sub_cate.setBounds(362, 52, 240, 35);
		panel_2.add(lbl_sub_cate);
		
		JLabel lblChange = new JLabel("change");
		lblChange.setBounds(12, 100, 62, 41);
		panel_2.add(lblChange);
		lblChange.setFont(new Font("Tahoma", Font.BOLD, 14));
		cmb_main_cat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updatecompany();
			}
		});
		
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
		separator_2.setBounds(10, 169, 700, 2);
		panel_3.add(separator_2);
		
		JPanel panel_Matrix = new JPanel();
		tabbedPane.addTab("Matrix & Alternative", null, panel_Matrix, null);
		panel_Matrix.setLayout(null);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(10, 41, 320, 198);
		panel_Matrix.add(scrollPane_4);
		
		table_alt = new JTable();
		table_alt.setFont(new Font("Tahoma", Font.PLAIN, 20));
		table_alt.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Alternative Barcode"
			}
		));
		table_alt.setCellSelectionEnabled(true);
		table_alt.setRowHeight(20);
		scrollPane_4.setViewportView(table_alt);
		
		JLabel lblAlternativeKey = new JLabel("Alternative Key");
		lblAlternativeKey.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlternativeKey.setFont(new Font("Arial", Font.BOLD, 14));
		lblAlternativeKey.setBounds(10, 11, 320, 24);
		panel_Matrix.add(lblAlternativeKey);
		
		JButton btnNewButton_1 = new JButton("Add Key");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int Alter = Integer.parseInt(JOptionPane.showInputDialog("Enter New Barcode for this Product"));						                      
					 update_alt(1,Alter);
				} catch (Exception z) { 
						JOptionPane.showMessageDialog(null, "Please Enter Only Numbers in Barcode. NO CHARCTERS ALLOW",
								"Barcode error", JOptionPane.ERROR_MESSAGE);
						return;
					}
			}
		});
		btnNewButton_1.setBounds(10, 245, 102, 51);
		panel_Matrix.add(btnNewButton_1);
		
		JButton btnUpdate_2 = new JButton("Update");
		btnUpdate_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if(table_alt.isRowSelected(table_alt.getSelectedRow()))
			{
				update_alt(2,(int) table_alt.getValueAt(table_alt.getSelectedRow(), 0));
			}
			else
			{
				JOptionPane.showMessageDialog(null, "First Select Bacode from Table which you want to Update.");
			}
			}
		});
		btnUpdate_2.setBounds(122, 245, 102, 51);
		panel_Matrix.add(btnUpdate_2);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table_alt.isRowSelected(table_alt.getSelectedRow()))
				{
					update_alt(3,(int) table_alt.getValueAt(table_alt.getSelectedRow(), 0));
				}
			}
		});
		btnRemove.setBounds(233, 245, 97, 51);
		panel_Matrix.add(btnRemove);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(342, 10, 460, 307);
		panel_Matrix.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblMatrix = new JLabel("Matrix");
		lblMatrix.setBounds(0, 0, 429, 24);
		panel_4.add(lblMatrix);
		lblMatrix.setHorizontalAlignment(SwingConstants.CENTER);
		lblMatrix.setFont(new Font("Arial", Font.BOLD, 14));
		
		JButton btnAddMatrix = new JButton("Add Matrix");
		btnAddMatrix.setBounds(0, 234, 136, 51);
		panel_4.add(btnAddMatrix);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(0, 30, 429, 198);
		panel_4.add(scrollPane_5);
		
		table_matrix = new JTable();
		table_matrix.setFont(new Font("Tahoma", Font.PLAIN, 20));
		table_matrix.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Matrix_id","Item_id","size_id", "Packing Name", "Part by", "Retail Price"
			}
		));
		table_matrix.setCellSelectionEnabled(true);		
		scrollPane_5.setViewportView(table_matrix);
		table_matrix.getColumnModel().getColumn(0).setWidth(0);
		table_matrix.getColumnModel().getColumn(0).setMinWidth(0);
		table_matrix.getColumnModel().getColumn(0).setMaxWidth(0); 
		
		table_matrix.getColumnModel().getColumn(1).setWidth(0);
		table_matrix.getColumnModel().getColumn(1).setMinWidth(0);
		table_matrix.getColumnModel().getColumn(1).setMaxWidth(0);
		
		table_matrix.getColumnModel().getColumn(2).setWidth(0);
		table_matrix.getColumnModel().getColumn(2).setMinWidth(0);
		table_matrix.getColumnModel().getColumn(2).setMaxWidth(0);
		table_matrix.setRowHeight(25);
		
		JButton btnUpdateMatrix = new JButton("Update Matrix");
		btnUpdateMatrix.setBounds(147, 234, 136, 51);
		panel_4.add(btnUpdateMatrix);
		
		JButton btnRemoveMatrix = new JButton("Remove Matrix");
		btnRemoveMatrix.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(table_matrix.isRowSelected(table_matrix.getSelectedRow()))
				{
				int ans=JOptionPane.showConfirmDialog(null,"Are you sure you want to Delete this Matrix?");
					if(ans==0)
					{
						update_matrix(3, table_matrix.getSelectedRow());
					}
				}else {
					JOptionPane.showMessageDialog(null, "Select row for update value");
				}
			}
		});
		btnRemoveMatrix.setBounds(293, 234, 136, 51);
		panel_4.add(btnRemoveMatrix);
		
		Matrix_panel = new JPanel();
		Matrix_panel.setBounds(342, 330, 371, 211);
		panel_Matrix.add(Matrix_panel);
		Matrix_panel.setLayout(null);
		Matrix_panel.setVisible(false);
		
		JLabel lblSelectSize = new JLabel("Select Size");
		lblSelectSize.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSelectSize.setBounds(12, 13, 115, 34);
		Matrix_panel.add(lblSelectSize);
		
		cmbsize = new JComboBox();
		cmbsize.setFont(new Font("Tahoma", Font.PLAIN, 17));
		cmbsize.setBounds(139, 13, 214, 34);
		Matrix_panel.add(cmbsize);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnAdd.getText()=="Add")
				{				
				update_matrix(1,1);
				}else {
					update_matrix(2, table_matrix.getSelectedRow());
				}
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAdd.setBounds(139, 162, 208, 34);
		Matrix_panel.add(btnAdd);
		
		txtdivide = new JTextField();
		txtdivide.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtdivide.setBounds(139, 60, 214, 34);
		Matrix_panel.add(txtdivide);
		txtdivide.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Divide by");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(12, 60, 115, 34);
		Matrix_panel.add(lblNewLabel);
		
		JLabel lblRetailPrice = new JLabel("Retail Price");
		lblRetailPrice.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblRetailPrice.setBounds(12, 107, 115, 34);
		Matrix_panel.add(lblRetailPrice);
		
		txtrpriceMat = new JTextField();
		txtrpriceMat.setBounds(139, 107, 214, 34);
		Matrix_panel.add(txtrpriceMat);
		txtrpriceMat.setColumns(10);
		
		btnUpdateMatrix.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table_matrix.isRowSelected(table_matrix.getSelectedRow()))
				{
					btnAdd.setText("Update");
					Matrix_panel.setVisible(true);
					String query = "select item_packing_type from item_packing";
					bindcategory(query,4);
					cmbsize.setSelectedItem(table_matrix.getValueAt(table_matrix.getSelectedRow(),3));
					txtdivide.setText(table_matrix.getValueAt(table_matrix.getSelectedRow(), 4).toString());
					txtrpriceMat.setText(table_matrix.getValueAt(table_matrix.getSelectedRow(), 5).toString());
					
				}else {
					JOptionPane.showMessageDialog(null, "Select row for update value");
				}
			}
		});
		btnAddMatrix.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtdivide.setText("");
				txtrpriceMat.setText("");
				btnAdd.setText("Add");
				Matrix_panel.setVisible(true);
				String query = "select item_packing_type from item_packing";
				bindcategory(query,4);
			}
		});
		btnFindAgain_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((DefaultTableModel) table_list.getModel()).setRowCount(0);
				((DefaultTableModel) table_list.getModel()).setRowCount(0);
				txt_itemno.setEnabled(true);
				txt_itemno.setText("");
				txt_upc.setText("");
				txt_name.setText("");
				txtupdatestock.setText("");
					tabbedPane.setVisible(false);
					tabbedPane.setEnabled(false);
				//	panel_find.setVisible(true);
			}
		});
		
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

	
	protected void update_matrix(int i, int rowvalue) {
			try{
				if(i==1)
				{
					String size=cmbsize.getSelectedItem().toString();
					
					//i==1 means add items
					s2=conn.prepareStatement("INSERT INTO `item_quantity_matrix`(`Size_id`,`item_id`, `Devide_By`, `Price`) "
							+ "VALUES ((select item_packing_id from item_packing where Item_packing_type like '"+size+"'),"+MainItemNo+","+txtdivide.getText()+","+txtrpriceMat.getText()+")");
					//System.out.println(s2);
					s2.execute();
					Matrix_panel.setVisible(false);
					FillMatrix(MainItemNo);
					
				}
				else if(i==2)
				{
					int matrix_id=(Integer) table_matrix.getValueAt(rowvalue, 0);
					
					st1 = conn.createStatement();
					String sql = "UPDATE item_quantity_matrix set `Size_id`=(select item_packing_id from item_packing where Item_packing_type like '"+cmbsize.getSelectedItem()+"'), "
							+ "`Devide_By`="+txtdivide.getText()+", `Price`="+txtrpriceMat.getText()+" WHERE item_quantity_matrix.Matrix_id = "+matrix_id;
					//System.out.println(st1);
					st1.executeUpdate(sql);
					JOptionPane.showMessageDialog(null, "Matrix Changed", "ALERT", 1);
					FillMatrix(MainItemNo);
					Matrix_panel.setVisible(false);
				}
				else if(i==3)
				{
					PreparedStatement pp1 = conn.prepareStatement("DELETE FROM `item_quantity_matrix` WHERE `Matrix_id`="+table_matrix.getValueAt(rowvalue, 0));
					pp1.executeUpdate();
					FillMatrix(MainItemNo);
				}
				txtdivide.setText("");
				txtrpriceMat.setText("");
			} catch (SQLException e1) {
					e1.printStackTrace();
			}
	}

	protected void update_alt(int i, int Alter) {
		try {
			if(i==1)
			{
				//i==1 means add items
				s2=conn.prepareStatement("INSERT INTO `alternative`(`item_id`, `Alt_barcode`) "
						+ "VALUES ("+MainItemNo+","+Alter+")");
				s2.execute();
				FillAlter(MainItemNo);
			}
			else if(i==2)
			{
				int NewBa;
				try {
					NewBa = Integer.parseInt(JOptionPane.showInputDialog("Enter New Barcode for this Product"));						                      
					} catch (Exception z) { 
						JOptionPane.showMessageDialog(null, "Please Enter Only Numbers in Barcode. NO CHARCTERS ALLOW",
								"Barcode error", JOptionPane.ERROR_MESSAGE);
						return;
					}
				
				st1 = conn.createStatement();
				String sql = "UPDATE `alternative` SET `Alt_barcode`="+NewBa+" WHERE  Alt_barcode="+table_alt.getValueAt(table_alt.getSelectedRow(),0);
				st1.executeUpdate(sql);
				FillAlter(MainItemNo);
				JOptionPane.showMessageDialog(null, "Barcode is Changed", "ALERT", 1);
			}
			else if(i==3)
			{
				PreparedStatement pp1 = conn.prepareStatement("DELETE FROM `alternative` WHERE `Alt_barcode` = "+Alter);
				pp1.executeUpdate();
				FillAlter(MainItemNo);
			}
		} catch (SQLException e1) {
					e1.printStackTrace();
		}
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
			else if(device==3) 
			{
				cmb_comp.setModel(model);
			}
			else if(device==4) {
				cmbsize.setModel(model);
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
			
			//Matrix and alternative filiing
			FillAlter(MainItemNo);
			FillMatrix(MainItemNo);
		} catch (SQLException e3) {
			e3.printStackTrace();
		}
	}

	private void FillAlter(int itnumber) {
		String query = "SELECT `Alt_barcode` FROM `alternative` WHERE `item_id` = "+MainItemNo; 
	//	String query2 = "SELECT sub_category_name FROM `sub_category`"; 
		PreparedStatement n1;
		ResultSet n2;
		DefaultTableModel model = (DefaultTableModel)table_alt.getModel();
		model.setRowCount(0);
		try {
			s4=conn.prepareStatement(query);
			r3=s4.executeQuery();						
			while(r3.next()){
				model.addRow(new Object[]{r3.getInt("Alt_barcode")});
			}
		} catch (SQLException e3) {
			e3.printStackTrace();
		}
	}
	private void FillMatrix(int itnumber) {
		String query = "SELECT item_quantity_matrix.Matrix_id,item_quantity_matrix.item_id,item_quantity_matrix.size_id,item_packing.item_packing_type,item_quantity_matrix.Devide_By, item_quantity_matrix.Price from Item_packing,item_quantity_matrix "
							+ "where item_packing.item_packing_id=item_quantity_matrix.Size_id AND item_quantity_matrix.item_id="+MainItemNo; 
		PreparedStatement n1;
		ResultSet n2;
		DefaultTableModel model = (DefaultTableModel)table_matrix.getModel();
		model.setRowCount(0);
		try {
			s4=conn.prepareStatement(query);
			r3=s4.executeQuery();						
			while(r3.next()){
				model.addRow(new Object[]{r3.getInt(1),r3.getInt(2),r3.getInt(3),r3.getString(4),r3.getInt(5),r3.getDouble(6)});
			}
		} catch (SQLException e3) {
			e3.printStackTrace();
		}
	}
}
