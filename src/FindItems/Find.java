package FindItems;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

public class Find extends JDialog {

	private final JPanel contentPanel = new JPanel();
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
			Find dialog = new Find();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Find() {
		
		try{
	    	conn = (Connection) ConnectionManager.getConnection();
				 }catch(Exception e1){
					 e1.printStackTrace();
				 }
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int wid = screenSize.width;
		int hei = screenSize.height;
		
		setBounds(0, 0, wid, hei);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		txt_upc = new JTextField();
		txt_upc.addActionListener(new ActionListener(){

	        public void actionPerformed(ActionEvent e){
	        	String temp=txt_upc.getText();
				if(temp!=null)
				{
					searchtable(Integer.valueOf(temp),1);
				}	        	
	        }});
		txt_upc.setBounds(10, 11, 86, 20);
		contentPanel.add(txt_upc);
		txt_upc.setColumns(10);
		
		txt_number = new JTextField();
		txt_number.setBounds(106, 11, 86, 20);
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
		txt_name.setBounds(202, 11, 246, 20);
		txt_name.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				bindtablename();
			}
		});
		contentPanel.add(txt_name);
		txt_name.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 46, 730, 356);
		contentPanel.add(scrollPane);
		
		table_list = new JTable();
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
				sw.FinditemNumber(Integer.valueOf(value));
				dispose();
			}
		});
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		scrollPane.setViewportView(table_list);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
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
