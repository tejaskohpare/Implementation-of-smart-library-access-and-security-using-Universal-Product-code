package com.library;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;

public class LibrarianMenuPage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LibrarianMenuPage frame = new LibrarianMenuPage();
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
	public LibrarianMenuPage() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\MINI-PROJECT\\LIBRARY ACCESS\\src\\com\\library\\img\\icon.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1920, 1080);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 218, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton addbookbtn = new JButton("ADD BOOK");
		addbookbtn.setFont(new Font("Times New Roman", Font.BOLD, 15));
		addbookbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				AddBooks adbk = new AddBooks();
				adbk.setVisible(true);
//				dispose();
			}
		});
		addbookbtn.setBounds(209, 164, 216, 52);
		contentPane.add(addbookbtn);
		
		JButton issuereturnbookbtn = new JButton("ISSUE/RETURN BOOK");
		issuereturnbookbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				returnissuepage irbp = new returnissuepage();
				irbp.setVisible(true);
				dispose();
			}
		});
		issuereturnbookbtn.setFont(new Font("Times New Roman", Font.BOLD, 15));
		issuereturnbookbtn.setBounds(819, 164, 216, 52);
		contentPane.add(issuereturnbookbtn);
		
		JButton viewbookbtn = new JButton("VIEW ALL BOOKS");
		viewbookbtn.setFont(new Font("Times New Roman", Font.BOLD, 15));
		viewbookbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				ViewBooks vbk = new ViewBooks();
				vbk.setVisible(true);
				dispose();
				
			}
		});
		viewbookbtn.setBounds(209, 332, 216, 52);
		contentPane.add(viewbookbtn);
		
		JButton viewishhuedbooksbtn = new JButton("VIEW ISSHUED BOOKS");
		viewishhuedbooksbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				ViewIssuedBooks vib = new ViewIssuedBooks();
				vib.setVisible(true);
			}
		});
		viewishhuedbooksbtn.setFont(new Font("Times New Roman", Font.BOLD, 15));
		viewishhuedbooksbtn.setBounds(819, 332, 216, 52);
		contentPane.add(viewishhuedbooksbtn);
		
		JButton logoutbtn = new JButton("LOGOUT");
		logoutbtn.setFont(new Font("Times New Roman", Font.BOLD, 15));
		logoutbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				LibrarianLoginPage lg = new LibrarianLoginPage();
				lg.setVisible(true);
				dispose();
			}
		});
		logoutbtn.setBounds(492, 494, 216, 52);
		contentPane.add(logoutbtn);
		
		JButton addbookbtn_1 = new JButton("ADD STUDENT");
		addbookbtn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				addStudentPage asp = new addStudentPage();
				asp.setVisible(true);
				
			}
		});
		addbookbtn_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		addbookbtn_1.setBounds(492, 164, 216, 52);
		contentPane.add(addbookbtn_1);
		
		JButton viewbookbtn_1 = new JButton("VIEW STUDENTS");
		viewbookbtn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				viewAllStudents vas = new viewAllStudents();
				vas.setVisible(true);
			}
		});
		viewbookbtn_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		viewbookbtn_1.setBounds(492, 332, 216, 52);
		contentPane.add(viewbookbtn_1);
	}
}
