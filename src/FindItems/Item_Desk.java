package FindItems;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JInternalFrame;
import javax.swing.JDesktopPane;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;

public class Item_Desk extends JInternalFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	private JTable table;
	Connection conn=null;
	PreparedStatement st=null;
	ResultSet rs=null;
	private JTable table_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Item_Desk frame = new Item_Desk();
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
	public Item_Desk() {
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
	    	conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/storedb?useSSL=false","root","");
	    	
				 }catch(Exception e1){
					 e1.printStackTrace();
				 }
		
		  putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
		    getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		    ((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		    this.setBorder(null);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int wid = screenSize.width;
		int hei = screenSize.height;
		setBounds(0,0, wid,(hei-60));
		
		JDesktopPane desktopPane = new JDesktopPane();
		getContentPane().add(desktopPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBounds(0,0,wid,hei);
		desktopPane.add(panel);
		panel.setLayout(null);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			dispose();
			}
		});
		btnClose.setBounds(714, 11, 118, 52);
		panel.add(btnClose);
		
		textField = new JTextField();
		textField.setBounds(138, 11, 174, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblBarcode = new JLabel("Barcode");
		lblBarcode.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblBarcode.setBounds(10, 11, 82, 17);
		panel.add(lblBarcode);
		
		JLabel lblProductName = new JLabel("Product Name");
		lblProductName.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblProductName.setBounds(10, 54, 118, 17);
		panel.add(lblProductName);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField_1.setColumns(10);
		textField_1.setBounds(10, 75, 308, 23);
		panel.add(textField_1);
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setFont(new Font("Arial", Font.BOLD, 14));
		lblCategory.setBounds(323, 14, 71, 14);
		panel.add(lblCategory);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(401, 11, 188, 20);
		panel.add(comboBox);
		
		JLabel lblVendor = new JLabel("Vendor");
		lblVendor.setFont(new Font("Arial", Font.BOLD, 14));
		lblVendor.setBounds(322, 44, 56, 14);
		panel.add(lblVendor);
		
		JLabel lblCountry = new JLabel("Country");
		lblCountry.setFont(new Font("Arial", Font.BOLD, 14));
		lblCountry.setBounds(323, 69, 71, 14);
		panel.add(lblCountry);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(401, 39, 188, 20);
		panel.add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(401, 67, 188, 20);
		panel.add(comboBox_2);
		
		JLabel lblProductNumber = new JLabel("Product Number");
		lblProductNumber.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblProductNumber.setBounds(10, 31, 132, 17);
		panel.add(lblProductNumber);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(138, 31, 174, 20);
		panel.add(textField_2);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 101, wid, 2);
		panel.add(separator);
		
		JButton btnAllItems = new JButton("All Items");
		btnAllItems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				try {
					st=conn.prepareStatement("select item_number,item_name from items");
					rs=st.executeQuery();
		
					DefaultTableModel model = (DefaultTableModel)table_1.getModel();
					model.setRowCount(0);
			    	try {
						while(rs.next())
						{
						model.addRow(new Object[]{rs.getString("item_number"),rs.getString("item_name")});
						}
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});
		btnAllItems.setBounds(613, 11, 89, 23);
		panel.add(btnAllItems);
		
		JButton btnClearAll = new JButton("clear All");
		btnClearAll.setBounds(613, 41, 89, 23);
		panel.add(btnClearAll);
		
		JScrollPane scrollBar = new JScrollPane();
		scrollBar.setBounds(10, 114, (wid-25), 332);
		panel.add(scrollBar);
		
		table_1 = new JTable();
		table_1.setColumnSelectionAllowed(true);
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				int row = table_1.getSelectedRow();
				int col = table_1.getSelectedColumn();
				
				String value=(String) table_1.getModel().getValueAt(row, col);
				JOptionPane.showMessageDialog(null,value);
				
				
			}
		});
		table_1.setCellSelectionEnabled(true);
		scrollBar.setViewportView(table_1);
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Item_id", "Item_name", "Category", "Cost", "Retail Price", "Stock"
			}
		));
		table_1.getColumnModel().getColumn(0).setPreferredWidth(50);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(150);
		table_1.getColumnModel().getColumn(0).setPreferredWidth(50);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(50);
		table_1.getColumnModel().getColumn(0).setPreferredWidth(50);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(50);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{textField, textField_1, comboBox, getContentPane(), desktopPane, panel, btnClose, lblBarcode, lblProductName, lblCategory, lblVendor, lblCountry, comboBox_1, comboBox_2, lblProductNumber, textField_2, separator, btnAllItems, btnClearAll, scrollBar, table_1}));

	}
}
