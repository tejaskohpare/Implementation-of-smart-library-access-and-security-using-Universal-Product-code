package com.library;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.dao.AdminDao;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class AdminLoginPage extends JFrame {

	private JPanel contentPane;
	private JTextField adminuid;
	private JPasswordField adminpass;
	private JButton backbtn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminLoginPage frame = new AdminLoginPage();
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
	public AdminLoginPage() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\MINI-PROJECT\\LIBRARY ACCESS\\src\\com\\library\\img\\icon.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1920, 1080);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 64));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		adminuid = new JTextField();
		adminuid.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) 
			{
				Border border = new LineBorder(Color.RED, 2, true);
				adminuid.setBorder(border);
			}
			@Override
			public void focusLost(FocusEvent e) {
				Border border = new LineBorder(Color.GREEN, 2, true);
				adminuid.setBorder(border);
			}
		});
		adminuid.setHorizontalAlignment(SwingConstants.CENTER);
		adminuid.setFont(new Font("Times New Roman", Font.BOLD, 20));
		Border border = new LineBorder(Color.GREEN, 2, true);
		adminuid.setBorder(border);
		adminuid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e)
			{
				int position = adminuid.getCaretPosition();
				adminuid.setText(adminuid.getText().toUpperCase());
				adminuid.setCaretPosition(position);
			}
		});
		adminuid.setBounds(807, 126, 309, 53);
		contentPane.add(adminuid);
		adminuid.setColumns(10);
		
		adminpass = new JPasswordField();
		adminpass.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e)
			{
				Border border1 = new LineBorder(Color.RED, 2, true);
				adminpass.setBorder(border1);
			}
			@Override
			public void focusLost(FocusEvent e) {
				Border border1 = new LineBorder(Color.GREEN, 2, true);
				adminpass.setBorder(border1);
			}
		});
		adminpass.setHorizontalAlignment(SwingConstants.CENTER);
		adminpass.setFont(new Font("Times New Roman", Font.BOLD, 20));
		adminpass.setBounds(807, 252, 309, 53);
		Border border1 = new LineBorder(Color.GREEN, 2, true);
		adminpass.setBorder(border1);
		contentPane.add(adminpass);
		
		JButton loginbtn = new JButton("Login");
		loginbtn.setBackground(new Color(0, 255, 0));
		loginbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String adminid = adminuid.getText();
				String adminpsw = new String(adminpass.getPassword());
			
				if(adminid.equals("") || adminpsw.equals(""))
				{
					JOptionPane.showMessageDialog(getParent(), "Please fill all the fields !");
				}
				else
				{
					AdminDao ad = new AdminDao();
					Boolean status = false;
					status = ad.CheckAdminIdPassword(adminid, adminpsw);
					
					if(status)
					{
//						JOptionPane.showMessageDialog(getParent(), "Login successfully!");
						AdminMainMenu amm = new AdminMainMenu();
						amm.setVisible(true);
						dispose();
					}
					else {
						adminuid.setText("");
						adminpass.setText("");
						JOptionPane.showMessageDialog(getParent(), "Either Id or password is incorrect !");
					}
					
				}
				
			}
		});
		loginbtn.setFont(new Font("Tahoma", Font.BOLD, 16));
		loginbtn.setBounds(966, 364, 150, 42);
		contentPane.add(loginbtn);
		
		backbtn = new JButton("Back");
		backbtn.setBackground(new Color(255, 0, 0));
		backbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				MainPageFirst mp = new MainPageFirst();
				mp.setVisible(true);
				dispose();
			}
		});
		backbtn.setFont(new Font("Tahoma", Font.BOLD, 16));
		backbtn.setBounds(187, 364, 150, 42);
		contentPane.add(backbtn);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 64, 64));
		panel.setBounds(116, 89, 1000, 461);
		contentPane.add(panel);
		
		JLabel lblNewLabel = new JLabel("ADMIN LOGIN");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(116, 54, 322, 36);
		contentPane.add(lblNewLabel);
	}
}
