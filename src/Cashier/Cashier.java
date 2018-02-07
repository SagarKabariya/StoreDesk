package Cashier;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import Register.SecondWindow;

import java.awt.Component;
import javax.swing.JSeparator;
import java.awt.Color;

public class Cashier extends JDialog {

	private final JPanel contentPanel = new JPanel();
	static int a; 
	static double b=11.21;
	static int mm;
	private JTextField textField;
	private JButton btn2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Cashier dialog = new Cashier(a, b,mm);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setAlwaysOnTop(true);
			dialog.setFocusable(true);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Cashier(int a, double b,int method) {
		setAlwaysOnTop(true);
		setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
		setTitle("Cash Changer");
		setBounds(100, 100, 652, 657);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 633, 597);
		contentPanel.add(panel);
		panel.setLayout(null);
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.BOLD, 50));
		textField.setBounds(10, 47, 612, 63);
		panel.add(textField);
		textField.setColumns(10);
		JButton btnCancel = new JButton("cancel");
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnCancel.setBounds(489, 414, 133, 176);
		panel.add(btnCancel);
		
		JButton btnOk = new JButton("OK");
		btnOk.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnOk.setBounds(489, 219, 133, 179);
		panel.add(btnOk);
		
		JLabel lblTotalItems = new JLabel("Total Items");
		lblTotalItems.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblTotalItems.setBounds(10, 11, 133, 23);
		panel.add(lblTotalItems);
		
		JLabel lblTotalAmount = new JLabel("Total Amount");
		lblTotalAmount.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblTotalAmount.setBounds(342, 11, 133, 23);
		panel.add(lblTotalAmount);
		
		JLabel lblQ = new JLabel("");
		lblQ.setBounds(176, 13, 108, 23);
		panel.add(lblQ);
		
		JLabel lblM = new JLabel("");
		lblM.setBounds(508, 11, 114, 23);
		panel.add(lblM);
		
		lblQ.setText(String.valueOf(a));
		lblM.setText(String.valueOf(b));
		
		JButton btn1 = new JButton("1");
		btn1.setFont(new Font("Tahoma", Font.BOLD, 30));
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addno("1");
			}
		});
		btn1.setBounds(10, 219, 142, 83);
		panel.add(btn1);
		
		btn2 = new JButton("2");
		btn2.setFont(new Font("Tahoma", Font.BOLD, 30));
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addno("2");
			}
		});
		btn2.setBounds(164, 219, 149, 83);
		panel.add(btn2);
		
		JButton btn3 = new JButton("3");
		btn3.setFont(new Font("Tahoma", Font.BOLD, 30));
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addno("3");
			}
		});
		btn3.setBounds(325, 219, 152, 79);
		panel.add(btn3);
		
		JButton btn4 = new JButton("4");
		btn4.setFont(new Font("Tahoma", Font.BOLD, 30));
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addno("4");
			}
		});
		btn4.setBounds(10, 315, 142, 83);
		panel.add(btn4);
		
		JButton btn5 = new JButton("5");
		btn5.setFont(new Font("Tahoma", Font.BOLD, 30));
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addno("5");
			}
		});
		btn5.setBounds(164, 315, 149, 83);
		panel.add(btn5);
		
		JButton btn6 = new JButton("6");
		btn6.setFont(new Font("Tahoma", Font.BOLD, 30));
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addno("6");
			}
		});
		btn6.setBounds(324, 315, 153, 83);
		panel.add(btn6);
		
		JButton btn7 = new JButton("7");
		btn7.setFont(new Font("Tahoma", Font.BOLD, 30));
		btn7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addno("7");
			}
		});
		btn7.setBounds(10, 411, 142, 83);
		panel.add(btn7);
		
		JButton btn8 = new JButton("8");
		btn8.setFont(new Font("Tahoma", Font.BOLD, 30));
		btn8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addno("8");
			}
		});
		btn8.setBounds(164, 414, 149, 80);
		panel.add(btn8);
		
		JButton btn9 = new JButton("9");
		btn9.setFont(new Font("Tahoma", Font.BOLD, 30));
		btn9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addno("9");
			}
		});
		btn9.setBounds(324, 414, 153, 80);
		panel.add(btn9);
		
		JButton btn0 = new JButton("0");
		btn0.setFont(new Font("Tahoma", Font.BOLD, 30));
		btn0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addno("0");
			}
		});
		btn0.setBounds(164, 507, 149, 83);
		panel.add(btn0);
		
		JButton Dyn1 = new JButton("");
		Dyn1.setFont(new Font("Tahoma", Font.BOLD, 25));
		Dyn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SecondWindow sw = new SecondWindow();
				Double w = Double.valueOf(Dyn1.getText());
				JOptionPane.showMessageDialog(null, "btn = "+w);
				sw.invoiceStepOne(w,method,b);
				dispose();
			}
		});
		Dyn1.setBounds(20, 123, 115, 83);
		panel.add(Dyn1);
		
		JButton dyn2 = new JButton("");
		dyn2.setFont(new Font("Tahoma", Font.BOLD, 25));
		dyn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SecondWindow sw = new SecondWindow();
				Double w = Double.valueOf(dyn2.getText());
				JOptionPane.showMessageDialog(null, "btn = "+w);
				sw.invoiceStepOne(w,method,b);
				dispose();
			}
		});
		dyn2.setBounds(139, 123, 115, 83);
		panel.add(dyn2);
		
		JButton dyn3 = new JButton("");
		dyn3.setFont(new Font("Tahoma", Font.BOLD, 25));
		dyn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SecondWindow sw = new SecondWindow();
				Double w = Double.valueOf(dyn3.getText());
				sw.invoiceStepOne(w,method,b);
				dispose();
			}
		});
		dyn3.setBounds(259, 123, 115, 83);
		panel.add(dyn3);
		
		JButton dyn4 = new JButton("");
		dyn4.setFont(new Font("Tahoma", Font.BOLD, 25));
		dyn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SecondWindow sw = new SecondWindow();
				Double w = Double.valueOf(dyn4.getText());
				sw.invoiceStepOne(w,method,b);
				dispose();
			}
		});
		dyn4.setBounds(379, 123, 115, 83);
		panel.add(dyn4);
		
		JButton dyn5 = new JButton("");
		dyn5.setFont(new Font("Tahoma", Font.BOLD, 25));
		dyn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SecondWindow sw = new SecondWindow();
				Double w = Double.valueOf(dyn5.getText());
				sw.invoiceStepOne(w,method,b);
				dispose();
			}
		});
		dyn5.setBounds(499, 123, 115, 82);
		panel.add(dyn5);
		
		JButton button = new JButton(".");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			addno(".");
			}
		});
		button.setFont(new Font("Tahoma", Font.BOLD, 30));
		button.setBounds(324, 507, 153, 83);
		panel.add(button);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{textField}));
		
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SecondWindow sw = new SecondWindow();
				Double w = b;
				if(textField.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null,"null="+b);
					sw.invoiceStepOne(b,method,b);
					dispose();
				}else{
					JOptionPane.showMessageDialog(null,"not null="+textField.getText());
					
					sw.invoiceStepOne(Double.valueOf(textField.getText()),method,b);
					dispose();
				}
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		int st=(int) Math.ceil(b);
		Dyn1.setText(String.valueOf(st));
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBounds(0, 214, 650, 2);
		panel.add(separator);
		for(int i=st;i<=(st+5);i++) {
			int x=i % 5;
			if(x == 0) {
				dyn2.setText(String.valueOf(i)); }
		}
		
		for(int i=(st+5);i<=(st+15);i++)
		{
			int x=i % 10;
			if(x == 0) {
				dyn3.setText(String.valueOf(i)); }
		}
		
		for(int i=st+15;i<=(st+35);i++)
		{
			int x=i % 20;
			if(x == 0) {
				dyn4.setText(String.valueOf(i)); }
		}
		for(int i=st+20;i<=(st+70);i++)
		{
			int x=i % 50;
			if(x == 0) {
				dyn5.setText(String.valueOf(i)); }
		}
	}
	public void addno(String a)
	{
		String bb=textField.getText();
		String b;
		if(bb==null)
		{
			textField.setText(a);
		}else{
			b=bb+a;
			textField.setText(b);
		}	
	}
}
