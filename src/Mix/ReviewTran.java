package Mix;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.sql.ResultSet;

import javax.swing.JInternalFrame;
import javax.swing.JDesktopPane;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import Connect.ConnectionManager;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.table.DefaultTableModel;

public class ReviewTran extends JInternalFrame {
	private Connection con = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	private JTable table;
	private JTextField textField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReviewTran frame = new ReviewTran();
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
	public ReviewTran() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int wid = screenSize.width;
		int hei = screenSize.height;
		setBounds(0,0,wid,hei);
		
		JDesktopPane desktopPane = new JDesktopPane();
		getContentPane().add(desktopPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBounds(0,0,wid-20,hei-30);
		desktopPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, (hei/2)-100, wid-20, (hei/2)+40);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Item ID", "Name", "Packing", "Quantity", "Discount", "Final Price"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(70);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(4).setPreferredWidth(70);
		table.getColumnModel().getColumn(5).setPreferredWidth(50);
		scrollPane.setViewportView(table);
		
		JLabel lblBillNo = new JLabel("Bill No");
		lblBillNo.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblBillNo.setBounds(10, 11, 137, 26);
		panel.add(lblBillNo);
		
		JLabel lblTotalItems = new JLabel("Total Items");
		lblTotalItems.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblTotalItems.setBounds(10, 48, 137, 26);
		panel.add(lblTotalItems);
		
		JLabel lblTotalAmount = new JLabel("Total Amount");
		lblTotalAmount.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblTotalAmount.setBounds(10, 85, 137, 26);
		panel.add(lblTotalAmount);
		
		JLabel lblTotalDiscount = new JLabel("Total Discount");
		lblTotalDiscount.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblTotalDiscount.setBounds(10, 122, 137, 26);
		panel.add(lblTotalDiscount);
		
		JLabel lblTotalTax = new JLabel("Total Tax");
		lblTotalTax.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblTotalTax.setBounds(10, 159, 137, 26);
		panel.add(lblTotalTax);
		
		JLabel lblDateAndTime = new JLabel("Date And Time");
		lblDateAndTime.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblDateAndTime.setBounds(339, 11, 137, 26);
		panel.add(lblDateAndTime);
		
		JLabel lblEmployeeName = new JLabel("Employee Name");
		lblEmployeeName.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblEmployeeName.setBounds(339, 48, 137, 26);
		panel.add(lblEmployeeName);
		
		JLabel label = new JLabel("");
		label.setFont(new Font("Arial Black", Font.PLAIN, 14));
		label.setBounds(157, 48, 137, 26);
		panel.add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.setFont(new Font("Arial Black", Font.PLAIN, 14));
		label_1.setBounds(157, 85, 137, 26);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("");
		label_2.setFont(new Font("Arial Black", Font.PLAIN, 14));
		label_2.setBounds(157, 122, 137, 26);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("");
		label_3.setFont(new Font("Arial Black", Font.PLAIN, 14));
		label_3.setBounds(157, 159, 137, 26);
		panel.add(label_3);
		
		textField = new JTextField();
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				
			}
		});
		textField.setBounds(157, 11, 137, 31);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel label_4 = new JLabel("");
		label_4.setFont(new Font("Arial Black", Font.PLAIN, 14));
		label_4.setBounds(486, 11, 137, 26);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("");
		label_5.setFont(new Font("Arial Black", Font.PLAIN, 14));
		label_5.setBounds(486, 48, 137, 26);
		panel.add(label_5);

		con = (Connection) ConnectionManager.getConnection();
	}
}
