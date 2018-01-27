package Mix;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JTable;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;

public class Register extends JInternalFrame {
	private JTable table;
	Connection conn=null;
	PreparedStatement st=null;
	ResultSet rs=null;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
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
	public Register() {
		getContentPane().setBackground(new Color(153, 153, 204));
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
		int wid_bt = (screenSize.width/10);
		setBounds(0,0, wid,hei);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 153, 204));
		panel.setBounds(0, 11, 1362, 115);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			dispose();
			}
		});
		btnClose.setBounds(10, 11, 80, 93);
		panel.add(btnClose);
		
		JButton btnNewButton = new JButton("print");
		btnNewButton.setBounds(123, 11, wid_bt, 39);
		panel.add(btnNewButton);
		
		JButton btnOpenDrawer = new JButton("open Drawer");
		btnOpenDrawer.setBounds(123, 65, wid_bt, 39);
		panel.add(btnOpenDrawer);
		
		JButton btnClearWholeList = new JButton("Clear Whole List");
		btnClearWholeList.setBounds(279, 65, wid_bt, 39);
		panel.add(btnClearWholeList);
		
		JButton btnScanBarcode = new JButton("Scan Barcode");
		btnScanBarcode.setBounds(279, 11, wid_bt, 39);
		panel.add(btnScanBarcode);
		
		JButton button_3 = new JButton("New button");
		button_3.setBounds(438, 65, wid_bt, 39);
		panel.add(button_3);
		
		JButton btnFindItem = new JButton("Find Item");
		btnFindItem.setBounds(438, 11, wid_bt, 39);
		panel.add(btnFindItem);
		
		JButton button_5 = new JButton("New button");
		button_5.setBounds(602, 65, wid_bt, 39);
		panel.add(button_5);
		
		JButton button_6 = new JButton("New button");
		
		button_6.setBounds(602, 11, wid_bt, 39);
		panel.add(button_6);
		
		JToggleButton tglbtnClick = new JToggleButton("click");
		tglbtnClick.setBounds(755, 11, 128, 93);
		panel.add(tglbtnClick);
		tglbtnClick.setSelected(true);
		
		if(tglbtnClick.isSelected()==true)
		{
			JOptionPane.showConfirmDialog(null, "clicked");
		}
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tglbtnClick.isSelected()==false)
				{
					JOptionPane.showConfirmDialog(null, "not");
				}
			}
		});
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(173, 216, 230));
		panel_1.setBounds(0, 137, wid, 290);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 438, wid, 120);
		getContentPane().add(panel_2);

	}
}
