package com.library;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import com.dao.AdminDao;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;

public class DeleteLibrarian extends JFrame {

	private JPanel contentPane;
	private JTextField librarianidtodelete;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteLibrarian frame = new DeleteLibrarian();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DeleteLibrarian() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\MINI-PROJECT\\LIBRARY ACCESS\\src\\com\\library\\img\\icon.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(330, 120, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Delete Librarian Data");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 586, 53);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Enter Librarian Id to Delete ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_1.setBounds(0, 78, 586, 32);
		contentPane.add(lblNewLabel_1);
		
		librarianidtodelete = new JTextField();
		librarianidtodelete.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				int position = librarianidtodelete.getCaretPosition();
				librarianidtodelete.setText(librarianidtodelete.getText().toUpperCase());
				librarianidtodelete.setCaretPosition(position);
			}
		});
		librarianidtodelete.setHorizontalAlignment(SwingConstants.CENTER);
		librarianidtodelete.setBounds(196, 137, 191, 37);
		contentPane.add(librarianidtodelete);
		librarianidtodelete.setColumns(10);
		
		JButton btnNewButton = new JButton("Delete Librarian");
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				String LibrarianIdToDelete = librarianidtodelete.getText();
				AdminDao ad = new AdminDao();
				AdminMainMenu amm = new AdminMainMenu();
				boolean status = false;
				status = ad.CheckLibrarianExistOrNot(LibrarianIdToDelete);
				if(status)
				{
					JPasswordField pf = new JPasswordField();
					int okCxl = JOptionPane.showConfirmDialog(null, pf, "Enter Admin Password", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

					if (okCxl == JOptionPane.YES_OPTION)
					{
					  String password = new String(pf.getPassword());

					 if(password.equals("1234"))
					 {
						  Boolean status1 = false;
						  status1 = ad.DeleteLibrarianMethod(LibrarianIdToDelete);
						  if(status1)
						  {
							  JOptionPane.showMessageDialog(getParent(), "Librarian Data Delete Successfully...");
							  amm.setVisible(true);
							  dispose();
							  
						  }
						  else 
						  {
							  JOptionPane.showMessageDialog(getParent(), "Something Went Wrong ...!!");
						  }
					 }
					 else {
						 JOptionPane.showMessageDialog(getParent(), "Password of Admin is Incorrect!");
					 }
					}
					else if (okCxl == JOptionPane.CANCEL_OPTION)
					{
						dispose();
					}
				}
				else {
					JOptionPane.showMessageDialog(getParent(), "Librarian Id Not Exist Please Enter Valid Id!");
					librarianidtodelete.setText("");
				}
			}
		});
		btnNewButton.setBounds(223, 204, 144, 43);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Close");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				dispose();
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton_1.setBounds(247, 287, 99, 32);
		contentPane.add(btnNewButton_1);
	}
}
