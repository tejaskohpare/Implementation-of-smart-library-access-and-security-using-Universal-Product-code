package com.library;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import com.dao.librariandao;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;

public class LibrarianLoginPage extends JFrame {
	private JTextField admin_uid;
	private JPasswordField admin_pass;
	private JButton submitbtn;
	private JButton resetbtn;
	private JPanel contentPane;
	private JButton backbtn;
	private final Action action = new SwingAction();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LibrarianLoginPage frame = new LibrarianLoginPage();
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
	public LibrarianLoginPage() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\MINI-PROJECT\\LIBRARY ACCESS\\src\\com\\library\\img\\icon.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1920, 1080);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		admin_uid = new JTextField();
		admin_uid.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) 
			{
				Border border = new LineBorder(Color.RED, 2, true);
				admin_uid.setBorder(border);
			}
			@Override
			public void focusLost(FocusEvent e) 
			{
				Border border = new LineBorder(Color.GREEN, 1, true);
				admin_uid.setBorder(border);
			}
		});
		admin_uid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) 
			{
				int position = admin_uid.getCaretPosition();
				admin_uid.setText(admin_uid.getText().toUpperCase());
				admin_uid.setCaretPosition(position);
			}
			
		});
		admin_uid.setFont(new Font("Times New Roman", Font.BOLD, 20));
		admin_uid.setBorder(new LineBorder(new Color(255, 0, 0)));
		
		admin_uid.setHorizontalAlignment(SwingConstants.CENTER);
		
		admin_uid.setBounds(745, 216, 305, 53);
		contentPane.add(admin_uid);
		admin_uid.setColumns(10);
		
		admin_pass = new JPasswordField();
		admin_pass.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) 
			{
				Border border = new LineBorder(Color.RED, 2, true);
				admin_pass.setBorder(border);
			}
			@Override
			public void focusLost(FocusEvent e)
			{
				Border border = new LineBorder(Color.GREEN, 1, true);
				admin_pass.setBorder(border);
			}
		});
		admin_pass.setFont(new Font("Times New Roman", Font.BOLD, 20));
		admin_pass.setBorder(new LineBorder(new Color(255, 0, 0), 1, true));
		
		
		admin_pass.setHorizontalAlignment(SwingConstants.CENTER);
		admin_pass.setBounds(745, 318, 305, 53);
		contentPane.add(admin_pass);
		
		submitbtn = new JButton("Login");

		submitbtn.setBackground(new Color(128, 255, 0));
		submitbtn.setFont(new Font("Times New Roman", Font.BOLD, 20));
		submitbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				
//				Getting values of Admin id and password 
				String adminid = admin_uid.getText();
				String adminpass = new String(admin_pass.getPassword());

//				Checking id and password are valid or not
				librariandao ad = new librariandao();
				boolean status = false;
				status = ad.CheckIdPassOFAdmin(adminid, adminpass);
				
				if(status)
				{
					admin_uid.setText("");
					admin_pass.setText("");
//					JOptionPane.showMessageDialog(getParent(), "Login Successfully");
					LibrarianMenuPage mp = new LibrarianMenuPage();
					mp.setVisible(true);
					dispose();
				}
				else {
					admin_uid.setText("");
					admin_pass.setText("");
					JOptionPane.showMessageDialog(getParent(), "Either id or password not correct !");
				}
			}
		});
		submitbtn.setBounds(909, 420, 141, 34);
		contentPane.add(submitbtn);
		
		resetbtn = new JButton("Reset");
		resetbtn.setBackground(new Color(255, 128, 128));
		resetbtn.setFont(new Font("Times New Roman", Font.BOLD, 20));
		resetbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				admin_uid.setText("");
				admin_pass.setText("");
			}
		});
		resetbtn.setBounds(745, 420, 141, 34);
		contentPane.add(resetbtn);
		
		
		backbtn = new JButton("Back");
		backbtn.setBackground(new Color(128, 128, 192));
		backbtn.setFont(new Font("Times New Roman", Font.BOLD, 20));
		backbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				MainPageFirst mp = new MainPageFirst();
				mp.setVisible(true);
				dispose();
			}
		});
		backbtn.setBounds(149, 420, 141, 34);
		contentPane.add(backbtn);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(55, 136, 130));
		panel.setBorder(new LineBorder(new Color(0, 0, 128), 2));
		panel.setBounds(127, 132, 926, 466);
		contentPane.add(panel);
		
		JLabel librarynamelbl = new JLabel("Librarian Login");
		librarynamelbl.setForeground(new Color(255, 255, 255));
		librarynamelbl.setFont(new Font("Tahoma", Font.BOLD, 24));
		librarynamelbl.setBounds(127, 84, 285, 42);
		contentPane.add(librarynamelbl);
		
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(55, 136, 130));
		panel_1.setBorder(new LineBorder(Color.RED, 1, true));
		panel_1.setBounds(0, 0, 1283, 652);
		contentPane.add(panel_1);
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
