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

public class Cashier extends JDialog {

	private final JPanel contentPanel = new JPanel();
	static int a; 
	static int b;
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
		setBounds(100, 100, 357, 438);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 340, 399);
		contentPanel.add(panel);
		panel.setLayout(null);
		textField = new JTextField();
		textField.setBounds(10, 79, 320, 31);
		panel.add(textField);
		textField.setColumns(10);
		JButton btnCancel = new JButton("cancel");
		btnCancel.setBounds(256, 338, 74, 55);
		panel.add(btnCancel);
		
		JButton btnOk = new JButton("OK");
		btnOk.setBounds(10, 338, 74, 55);
		panel.add(btnOk);
		
		JLabel lblTotalItems = new JLabel("Total Items");
		lblTotalItems.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblTotalItems.setBounds(10, 11, 133, 23);
		panel.add(lblTotalItems);
		
		JLabel lblTotalAmount = new JLabel("Total Amount");
		lblTotalAmount.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblTotalAmount.setBounds(10, 45, 133, 23);
		panel.add(lblTotalAmount);
		
		JLabel lblQ = new JLabel("");
		lblQ.setBounds(176, 13, 108, 23);
		panel.add(lblQ);
		
		JLabel lblM = new JLabel("");
		lblM.setBounds(176, 45, 114, 23);
		panel.add(lblM);
		
		lblQ.setText(String.valueOf(a));
		lblM.setText(String.valueOf(b));
		
		JButton btn1 = new JButton("1");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addno("1");
			}
		});
		btn1.setBounds(76, 164, 56, 48);
		panel.add(btn1);
		
		btn2 = new JButton("2");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addno("2");
			}
		});
		btn2.setBounds(142, 164, 56, 48);
		panel.add(btn2);
		
		JButton btn3 = new JButton("3");
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addno("3");
			}
		});
		btn3.setBounds(208, 164, 56, 48);
		panel.add(btn3);
		
		JButton btn4 = new JButton("4");
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addno("4");
			}
		});
		btn4.setBounds(76, 223, 56, 48);
		panel.add(btn4);
		
		JButton btn5 = new JButton("5");
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addno("5");
			}
		});
		btn5.setBounds(142, 223, 56, 48);
		panel.add(btn5);
		
		JButton btn6 = new JButton("6");
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addno("6");
			}
		});
		btn6.setBounds(208, 223, 56, 48);
		panel.add(btn6);
		
		JButton btn7 = new JButton("7");
		btn7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addno("7");
			}
		});
		btn7.setBounds(76, 282, 56, 48);
		panel.add(btn7);
		
		JButton btn8 = new JButton("8");
		btn8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addno("8");
			}
		});
		btn8.setBounds(142, 282, 56, 48);
		panel.add(btn8);
		
		JButton btn9 = new JButton("9");
		btn9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addno("9");
			}
		});
		btn9.setBounds(208, 282, 56, 48);
		panel.add(btn9);
		
		JButton btn0 = new JButton("0");
		btn0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addno("0");
			}
		});
		btn0.setBounds(110, 341, 56, 48);
		panel.add(btn0);
		
		JButton Dyn1 = new JButton("");
		Dyn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SecondWindow sw = new SecondWindow();
				Double w = b-Integer.valueOf(Dyn1.getText());
				sw.invoicedone(w,method,b);
				dispose();
			}
		});
		Dyn1.setBounds(10, 110, 62, 48);
		panel.add(Dyn1);
		
		JButton dyn2 = new JButton("");
		dyn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SecondWindow sw = new SecondWindow();
				Double w = b-Integer.valueOf(dyn2.getText());
				sw.invoicedone(w,method,b);
				dispose();
			}
		});
		dyn2.setBounds(76, 110, 56, 48);
		panel.add(dyn2);
		
		JButton dyn3 = new JButton("");
		dyn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SecondWindow sw = new SecondWindow();
				Double w = b-Integer.valueOf(dyn3.getText());
				sw.invoicedone(w,method,b);
				dispose();
			}
		});
		dyn3.setBounds(142, 110, 56, 48);
		panel.add(dyn3);
		
		JButton dyn4 = new JButton("");
		dyn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SecondWindow sw = new SecondWindow();
				Double w = b-Integer.valueOf(dyn4.getText());
				sw.invoicedone(w,method,b);
				dispose();
			}
		});
		dyn4.setBounds(208, 110, 56, 48);
		panel.add(dyn4);
		
		JButton dyn5 = new JButton("");
		dyn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SecondWindow sw = new SecondWindow();
				Double w = b-Integer.valueOf(dyn5.getText());
				sw.invoicedone(w,method,b);
				dispose();
			}
		});
		dyn5.setBounds(274, 110, 56, 48);
		panel.add(dyn5);
		
		JButton button = new JButton(".");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			addno(".");
			}
		});
		button.setFont(new Font("Arial Black", Font.BOLD, 20));
		button.setBounds(176, 341, 56, 48);
		panel.add(button);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{textField}));
		
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SecondWindow sw = new SecondWindow();
				Double w = b;
				if(textField.getText().equals(""))
				{
					sw.invoicedone(b,method,b);
					dispose();
				}else{
					sw.invoicedone(Double.valueOf(textField.getText()),method,b);
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
