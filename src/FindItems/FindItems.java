package FindItems;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JDesktopPane;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Statement;

import Connect.ConnectionManager;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FindItems extends JInternalFrame {
	private JTextField txt_upc;
	private JTable table_list;
	Connection conn=null;
	PreparedStatement st=null,s2,s3,s4;
	java.sql.Statement st1;
	Statement st2,st3;
	ResultSet rs=null,r2,r3,r4;
	private JTextField txt_itemno;
	private JTextField txt_name;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FindItems frame = new FindItems();
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
	public FindItems() {
		
		try{
	    	conn = (Connection) ConnectionManager.getConnection();
				 }catch(Exception e1){
					 e1.printStackTrace();
				 }
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int wid = screenSize.width;
		int hei = screenSize.height;
		
		setBounds(0, 0, wid, hei);
		
		JDesktopPane desktopPane = new JDesktopPane();
		getContentPane().add(desktopPane, BorderLayout.CENTER);
		desktopPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, wid-20, hei-20);
		desktopPane.add(panel);
		panel.setLayout(null);
		
		txt_upc = new JTextField();
		txt_upc.setBounds(10, 11, 86, 20);
		panel.add(txt_upc);
		
		txt_upc.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 50, wid-50, hei-100);
		panel.add(scrollPane);
		
		table_list = new JTable();
		
		table_list.setRowHeight(20);
		table_list.setColumnSelectionAllowed(true);
		table_list.setCellSelectionEnabled(false);
	
		scrollPane.setViewportView(table_list);
		
		txt_itemno = new JTextField();
		
		txt_itemno.setBounds(106, 11, 130, 20);
		panel.add(txt_itemno);
		txt_itemno.setColumns(10);
		
		txt_name = new JTextField();
		
		txt_name.setBounds(246, 11, 163, 20);
		panel.add(txt_name);
		txt_name.setColumns(10);
		txt_name.requestFocusInWindow();
	}
	
}
