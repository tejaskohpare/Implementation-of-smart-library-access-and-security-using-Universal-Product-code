package com.library;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;

public class MainPageFirst extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPageFirst frame = new MainPageFirst();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainPageFirst() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\MINI-PROJECT\\LIBRARY ACCESS\\src\\com\\library\\img\\icon.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1920, 1080);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(214, 211, 254));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton adminloginbtn = new JButton("Admin Login");
		adminloginbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				AdminLoginPage alp = new AdminLoginPage();
				alp.setVisible(true);
				dispose();
			}
		});
		adminloginbtn.setFont(new Font("Times New Roman", Font.BOLD, 25));
		adminloginbtn.setBounds(524, 129, 259, 69);
		contentPane.add(adminloginbtn);
		
		JButton librarianloginbtn = new JButton("Librarian Login");
		librarianloginbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
//				librarianlogin ll = new librarianlogin();
//				ll.setVisible(true);
//				setVisible(false);
				new LibrarianLoginPage().setVisible(true);
				dispose();
			}
		});
		librarianloginbtn.setFont(new Font("Times New Roman", Font.BOLD, 25));
		librarianloginbtn.setBounds(524, 280, 259, 69);
		contentPane.add(librarianloginbtn);
		
		JLabel lblNewLabel = new JLabel("LIBRARY MANAGEMENT SYSTEM");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 1283, 69);
		Border border = new LineBorder(Color.BLACK, 3, true);
		lblNewLabel.setBorder(border);
		contentPane.add(lblNewLabel);
	}

}
