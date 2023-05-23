package com.library;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import com.dao.AdminDao;
import com.dao.admin;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AddLibrarianPage extends JFrame {

	private JPanel contentPane;
	private JTextField librarianid;
	private JPasswordField librarianpass;
	private JTextField librarianname;
	private JTextField librarianemail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddLibrarianPage frame = new AddLibrarianPage();
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
	public AddLibrarianPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setBounds(330, 120, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(255, 0, 0), 2));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter Librarian Id :");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel.setBounds(89, 130, 141, 26);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Enter Librarian Password :");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_1.setBounds(89, 180, 194, 26);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Enter Librarian Name :");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_2.setBounds(89, 229, 178, 26);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Enter Librarian Email :");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_3.setBounds(89, 285, 178, 26);
		contentPane.add(lblNewLabel_3);
		
		librarianid = new JTextField();
		librarianid.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		librarianid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				int position = librarianid.getCaretPosition();
				librarianid.setText(librarianid.getText().toUpperCase());
				librarianid.setCaretPosition(position);
			}
		});
		librarianid.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				Border border1 = new LineBorder(Color.gray, 2, true);
				librarianid.setBorder(border1);
			}
			@Override
			public void focusLost(FocusEvent e) {
				Border border1 = new LineBorder(Color.white, 2, true);
				librarianid.setBorder(border1);
			}
		});
		librarianid.setBounds(334, 130, 163, 26);
		contentPane.add(librarianid);
		librarianid.setColumns(10);
		
		librarianpass = new JPasswordField();
		librarianpass.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		librarianpass.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				Border border1 = new LineBorder(Color.GRAY, 2, true);
				librarianpass.setBorder(border1);
			}
			@Override
			public void focusLost(FocusEvent e) {
				Border border1 = new LineBorder(Color.WHITE, 2, true);
				librarianpass.setBorder(border1);
			}
		});
		librarianpass.setBounds(334, 180, 163, 26);
		contentPane.add(librarianpass);
		
		librarianname = new JTextField();
		librarianname.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				int position = librarianname.getCaretPosition();
				librarianname.setText(librarianname.getText().toUpperCase());
				librarianname.setCaretPosition(position);
			}
		});
		librarianname.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		librarianname.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				Border border1 = new LineBorder(Color.GRAY, 2, true);
				librarianname.setBorder(border1);
			}
			@Override
			public void focusLost(FocusEvent e) {
				Border border1 = new LineBorder(Color.WHITE, 2, true);
				librarianname.setBorder(border1);
			}
		});
		librarianname.setColumns(10);
		librarianname.setBounds(334, 229, 163, 26);
		contentPane.add(librarianname);
		
		librarianemail = new JTextField();
		librarianemail.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		librarianemail.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				Border border1 = new LineBorder(Color.GRAY, 2, true);
				librarianemail.setBorder(border1);
			}
			@Override
			public void focusLost(FocusEvent e) {
				Border border1 = new LineBorder(Color.WHITE, 2, true);
				librarianemail.setBorder(border1);
			}
		});
		librarianemail.setColumns(10);
		librarianemail.setBounds(334, 285, 163, 26);
		contentPane.add(librarianemail);
		
		JButton librarianregisterbtn = new JButton("Add Librarian");
		librarianregisterbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				AdminDao adao = new AdminDao();
				
		
				String LibrarianId = librarianid.getText();
				String LibrarianPass = new String(librarianpass.getPassword());
				String LibrarianName = librarianname.getText();
				String LibrarianEmail = librarianemail.getText();
				
				
					if(!(LibrarianId.equals("") || LibrarianPass.equals("") || LibrarianName.equals("") || LibrarianEmail.equals("")))
					{
						Boolean status1 = false;
						status1 = adao.isLibrarianIdExist(LibrarianId);
						
						if(!status1)
						{
							int result = JOptionPane.showConfirmDialog(null, "Do you want to Add New Librarian !! ", "Confirmation", JOptionPane.YES_NO_CANCEL_OPTION);
							
							if(result == JOptionPane.CANCEL_OPTION)
							{
								AdminMainMenu amm = new AdminMainMenu();
								amm.setVisible(true);
								dispose();
							}
							else if (result == JOptionPane.YES_OPTION) 
							{
								admin ad = new admin();
								ad.setLibrarianid(LibrarianId);
								ad.setLibrarianpass(LibrarianPass);
								ad.setLibrarianname(LibrarianName);
								ad.setLibrarianemail(LibrarianEmail);
								
								
								boolean status = false;
								status = adao.AddLibrarian(ad);
								
								if(status)
								{
									JOptionPane.showMessageDialog(getParent(), "New Librarian Add Successfully!");
									dispose();
								}
								else {
									JOptionPane.showMessageDialog(getParent(), "Something Went Wrong Please Fill Again!");
									librarianid.setText("");
									librarianpass.setText("");
									librarianname.setText("");
									librarianemail.setText("");
								}
							}
						}
						else {
							JOptionPane.showMessageDialog(getParent(), "Librarian Id Already Exist Please fill different Id !");
							librarianid.setText("");
						}
						
					}
					else
					{
						JOptionPane.showMessageDialog(getParent(), "Please fill all the fields !");
					}
				
				
				
			}
		});
		librarianregisterbtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		librarianregisterbtn.setBounds(313, 348, 153, 35);
		contentPane.add(librarianregisterbtn);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				librarianid.setText("");
				librarianpass.setText("");
				librarianname.setText("");
				librarianemail.setText("");
			}
		});
		btnReset.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnReset.setBounds(114, 348, 153, 35);
		contentPane.add(btnReset);
		
		JLabel lblNewLabel_4 = new JLabel("ADD LIBRARIAN SECTION");
		Border blackline = BorderFactory.createLineBorder(Color.RED,2);
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(0, 0, 600, 59);
		lblNewLabel_4.setBorder(blackline);
		contentPane.add(lblNewLabel_4);
		
		JButton backbtn = new JButton("Close");
		backbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
			}
		});
		backbtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		backbtn.setBounds(234, 394, 107, 35);
		contentPane.add(backbtn);
	}
}
