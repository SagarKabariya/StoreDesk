package Mix;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class Express extends JInternalFrame implements KeyListener{
	private JTextField textField_Name;
	private JTextField textField_Barcode;
	PreparedStatement st=null,s2,s3,s4;
	ResultSet rs=null,r2,r3,r4;
	private JTable table_find;
	Connection conn=null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Express frame = new Express();
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
	public Express() {
		 addKeyListener(this);
		try{
			Class.forName("com.mysql.jdbc.Driver");
	    	conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/storedb?useSSL=false","root","");
	    	
				 }catch(Exception e1){
					 e1.printStackTrace();
				 }
		
		setResizable(false);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int wid = screenSize.width;
		int hei = screenSize.height;
		setBounds(100, 100, wid, hei);
		
		JDesktopPane desktopPane = new JDesktopPane();
		getContentPane().add(desktopPane, BorderLayout.CENTER);
		
		JPanel panel_find = new JPanel();
		panel_find.setBounds(0, 0,  wid, hei);
		desktopPane.add(panel_find);
		panel_find.setLayout(null);

		
		JButton btnGoBack = new JButton("go back");
		btnGoBack.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnGoBack.setBounds(10, 11, 112, 59);
		panel_find.add(btnGoBack);
		
		textField_Name = new JTextField();
		textField_Name.setFont(new Font("Tahoma", Font.BOLD, 15));
		textField_Name.setBounds(233, 49, 268, 34);
		panel_find.add(textField_Name);
		textField_Name.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblName.setBounds(146, 55, 77, 20);
		panel_find.add(lblName);
		
		JLabel lblBarcode = new JLabel("Barcode");
		lblBarcode.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblBarcode.setBounds(146, 13, 77, 20);
		panel_find.add(lblBarcode);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(134, 0, 2, 99);
		panel_find.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(511, 0, 2, 99);
		panel_find.add(separator_1);
		
		JLabel lblBrandName = new JLabel("Brand Name");
		lblBrandName.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblBrandName.setBounds(523, 13, 103, 20);
		panel_find.add(lblBrandName);
		
		JLabel lblType = new JLabel("Type");
		lblType.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblType.setBounds(523, 50, 77, 20);
		panel_find.add(lblType);
		
		JScrollPane scrollBar_Find = new JScrollPane();
		scrollBar_Find.setBounds(10, 110, 1330, 325);
		panel_find.add(scrollBar_Find);
		
		table_find = new JTable();
		table_find.setFont(new Font("Tahoma", Font.BOLD, 15));
		table_find.setShowGrid(false);
		table_find.setShowHorizontalLines(false);
		table_find.setShowVerticalLines(false);
		table_find.setRowHeight(30);
		table_find.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"ID", "Item Name", "Stocks", "Retail", "Cost"
				}
			));
			table_find.getColumnModel().getColumn(1).setPreferredWidth(350);
			table_find.getColumnModel().getColumn(1).setMinWidth(300);
			table_find.getColumnModel().getColumn(4).setPreferredWidth(50);
			table_find.getColumnModel().getColumn(4).setMinWidth(50);
			scrollBar_Find.setViewportView(table_find);
			
		
		textField_Barcode = new JTextField();
		textField_Barcode.setBounds(233, 11, 268, 34);
		panel_find.add(textField_Barcode);
		textField_Barcode.setColumns(10);
		
		textField_Barcode.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				try {
					int itid=Integer.valueOf(textField_Barcode.getText());
					st=conn.prepareStatement("select item_number,item_name from items where item_number ="+itid);
					rs=st.executeQuery();
		
					DefaultTableModel model = (DefaultTableModel)table_find.getModel();
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
		
		
		textField_Name.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				try {
					String name = textField_Name.getText();
					s4=conn.prepareStatement("select item_number,item_name from items where item_name like '%"+name+"%'");
					r3=s4.executeQuery();
	
					DefaultTableModel model = (DefaultTableModel)table_find.getModel();
					model.setRowCount(0);
			    	try {
						while(r3.next())
						{
						model.addRow(new Object[]{r3.getString("item_number"),r3.getString("item_name")});
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
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "aaa");
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, e.getKeyCode());
	}
}
