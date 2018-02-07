package FindItems;
import Connect.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.stream.IntStream;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Statement;

import Connect.ConnectionManager;
import Register.SecondWindow;

import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;

public class Find extends JDialog {

	private final JPanel contentPanel = new JPanel();
	static String name;
	Connection conn=null;
	PreparedStatement st=null,s2,s3,s4;
	java.sql.Statement st1;
	Statement st2,st3;
	ResultSet rs=null,r2,r3,r4;
	private JTextField txt_upc;
	private JTextField txt_number;
	private JTextField txt_name;
	private JTable table_list;
	private JScrollPane scrollPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Find dialog = new Find(name);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @param string 
	 */
	public Find(String name) {
		
		conn=ConnectionManager.getConnection();
		
		setBounds(0, 0, 1364, 722);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		txt_upc = new JTextField();
		txt_upc.setFont(new Font("Tahoma", Font.PLAIN, 25));
		txt_upc.addActionListener(new ActionListener(){

	        public void actionPerformed(ActionEvent e){
	        	String temp=txt_upc.getText();
				if(temp!=null)
				{
					searchtable(Integer.valueOf(temp),1);
				}	        	
	        }});
		txt_upc.setBounds(10, 35, 289, 58);
		contentPanel.add(txt_upc);
		txt_upc.setColumns(10);
		
		txt_number = new JTextField();
		txt_number.setFont(new Font("Tahoma", Font.PLAIN, 25));
		txt_number.setBounds(357, 35, 289, 58);
		txt_number.addKeyListener(new KeyAdapter() {
			
			
			@Override
			public void keyReleased(KeyEvent e) {
				if(txt_number.getText().equals("")){}else
				{
					String temp=txt_number.getText();
					searchtable(Integer.valueOf(temp),2);
					
				}
			}
		});
		contentPanel.add(txt_number);
		txt_number.setColumns(10);
		
		txt_name = new JTextField();
		txt_name.setFont(new Font("Tahoma", Font.PLAIN, 25));
		txt_name.setBounds(726, 35, 585, 58);
		txt_name.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				bindtablename();
			}
		});
		contentPanel.add(txt_name);
		//JOptionPane.showMessageDialog(null, name);
		txt_name.setColumns(10);
		
		
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 106, 1302, 476);
		contentPanel.add(scrollPane);
		
		table_list = new JTable();
		table_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_list.setFont(new Font("Tahoma", Font.PLAIN, 20));
		table_list.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"ID", "Item Name", "Vendor", "Stock", "Retail", "Cost"
				}
			));
		table_list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = table_list.getSelectedRow();
				String value=(String) table_list.getModel().getValueAt(row, 0);
				SecondWindow sw = new SecondWindow();
				setBounds(0,0,0,0);
				sw.FinditemNumber(Integer.valueOf(value));
				dispose();
			}
		});
		table_list.setRowHeight(30);
		scrollPane.setViewportView(table_list);
		
		JButton btnSelectProduct = new JButton("Select Product");
		btnSelectProduct.setBounds(10, 595, 198, 67);
		contentPanel.add(btnSelectProduct);
		
		JButton btnClose = new JButton("Close");
		btnClose.setBounds(225, 595, 198, 67);
		contentPanel.add(btnClose);
		
		JLabel lblSearchByName = new JLabel("Search by Name");
		lblSearchByName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSearchByName.setBounds(726, 6, 329, 25);
		contentPanel.add(lblSearchByName);
		
		JLabel lblSearchByProduct = new JLabel("Search by Product ID");
		lblSearchByProduct.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSearchByProduct.setBounds(357, 6, 225, 25);
		contentPanel.add(lblSearchByProduct);
		
		JLabel lblSearchByUpc = new JLabel("Search by UPC Code");
		lblSearchByUpc.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSearchByUpc.setBounds(10, 6, 225, 23);
		contentPanel.add(lblSearchByUpc);
		
		
		int a=name.length();
		if(a>0){
			txt_name.setText(name);
			bindtablename();
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
			s4=conn.prepareStatement("select items.Item_number, items.Item_Name, vendors.Vendor_name, item_stock.Item_stock, item_price.Item_retail_price, "
					+ "item_price.Item_cost_price from items left outer join item_stock on items.Item_id = item_stock.Item_id "
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
}
