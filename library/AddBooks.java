package com.library;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.dao.books;
import com.dao.booksdao;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AddBooks extends JFrame {

	private JPanel contentPane;
	private JTextField bookname;
	private JTextField noofbook;
	private JTextField aurthorname;
	private JTextField bookedition;
	private JButton cancelbtn;
	private JLabel labelforbookno;
	JLabel lblforwarning;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddBooks frame = new AddBooks();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public AddBooks() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(330, 120, 600, 500);
		setUndecorated(true);
//		setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 255), 2, true));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("ADD BOOK SECTION");
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBackground(new Color(0, 128, 0));
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(0, 0, 600, 61);
		
		contentPane.add(lblNewLabel_1);
	
		
		JLabel lblNewLabel = new JLabel("Book Number Starting From :");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel.setBounds(35, 76, 220, 48);
		contentPane.add(lblNewLabel);
		
		JLabel lblEnterBookName = new JLabel("ENTER BOOK NAME :");
		lblEnterBookName.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblEnterBookName.setBounds(35, 135, 171, 34);
		contentPane.add(lblEnterBookName);
		
		JLabel lblEnterBook = new JLabel("ENTER NO. OF BOOKS :");
		lblEnterBook.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblEnterBook.setBounds(35, 195, 213, 34);
		contentPane.add(lblEnterBook);
		
		JLabel lblEnterAurtherName = new JLabel("ENTER AURTHOR NAME :");
		lblEnterAurtherName.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblEnterAurtherName.setBounds(35, 249, 204, 34);
		contentPane.add(lblEnterAurtherName);
		
		JLabel lblEnterEditionOf = new JLabel("ENTER EDITION OF BOOKS :");
		lblEnterEditionOf.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblEnterEditionOf.setBounds(35, 306, 213, 34);
		contentPane.add(lblEnterEditionOf);
		
		bookname = new JTextField();
		bookname.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		bookname.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e)
			{
				int position = bookname.getCaretPosition();
				bookname.setText(bookname.getText().toUpperCase());
				bookname.setCaretPosition(position);
			}
		});
		bookname.setColumns(10);
		bookname.setBounds(287, 135, 256, 34);
		contentPane.add(bookname);
		
		noofbook = new JTextField();
		noofbook.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		noofbook.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) 
			{
				char c = e.getKeyChar();
				if(Character.isLetter(c))
				{
					noofbook.setEditable(false);
					lblforwarning.setText("Please Enter Number Only");
				}else {
					noofbook.setEditable(true);
					lblforwarning.setText("");
				}
			}
		});
		
		noofbook.setColumns(10);
		noofbook.setBounds(287, 195, 256, 34);
		contentPane.add(noofbook);
		
		aurthorname = new JTextField();
		aurthorname.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		aurthorname.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) 
			{
				int position = aurthorname.getCaretPosition();
				aurthorname.setText(aurthorname.getText().toUpperCase());
				aurthorname.setCaretPosition(position);
			}
		});
		aurthorname.setColumns(10);
		aurthorname.setBounds(287, 250, 256, 34);
		contentPane.add(aurthorname);
		
		bookedition = new JTextField();
		bookedition.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		bookedition.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) 
			{
				int position = bookedition.getCaretPosition();
				bookedition.setText(bookedition.getText().toUpperCase());
				aurthorname.setCaretPosition(position);
			}
		});
		bookedition.setColumns(10);
		bookedition.setBounds(287, 306, 256, 34);
		contentPane.add(bookedition);
		
		JButton submitbtn = new JButton("SUBMIT");
		submitbtn.setBackground(new Color(0, 255, 0));
		submitbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
//				booksdao bkdao = new booksdao();
				
			//  Checking all fields are field are fill or not
				if(labelforbookno.getText().equals("") || bookname.getText().equals("") || noofbook.getText().equals("") || aurthorname.getText().equals("") || bookedition.getText().equals(""))
				{
					JOptionPane.showMessageDialog(getParent(), "Please fill all fields!");
				}
				else 
				{
					
				//	Checking the field BookNo or No.of Book is Numberic or Not
					if(isNumeric(noofbook.getText()))
					{
						
						booksdao bkdao = new booksdao();
					
						int maxbookno = bkdao.GettingLastBookNumber();
						String maxbooknostring = String.valueOf(maxbookno);
						
						if(maxbookno != 0)
						{
							String bookn = labelforbookno.getText();
							String nofbooks = noofbook.getText();
							
							int BookNo = Integer.parseInt(bookn);
							String BookName = bookname.getText();
							int NoOfBooks = Integer.parseInt(nofbooks);
							String AurthorName = aurthorname.getText();
							String Edition = bookedition.getText();
							
							if(bkdao.CheckingPrimarykey(BookNo)) 
							{
								JOptionPane.showMessageDialog(getParent(), "Please Enter Valid Book No Becouse This is already in the Database!");
								labelforbookno.setText("");
							}
							else 
							{
								books bk = new books();
								bk.setBookNo(BookNo);
								bk.setBookName(BookName);
								bk.setNoOfBooks(NoOfBooks);
								bk.setAurthor(AurthorName);
								bk.setEdition(Edition);
							
								Boolean status = false;
								
								status = bkdao.Addbook(bk);
								
								if(status)
								{
									labelforbookno.setText("");
									bookname.setText("");
									noofbook.setText("");
									aurthorname.setText("");
									bookedition.setText("");
									JOptionPane.showMessageDialog(getParent(), "Book Add Successfully!");
//									LibrarianMenuPage lmp = new LibrarianMenuPage();
//									lmp.setVisible(true);
									dispose();
								}
								else {
									labelforbookno.setText("");
									bookname.setText("");
									noofbook.setText("");
									aurthorname.setText("");
									bookedition.setText("");
									JOptionPane.showMessageDialog(getParent(), "Please Enter a Valid Input !");
								}
							}
						}
						else {
							JOptionPane.showMessageDialog(getParent(), "Database Not work properly!");
						}
					}
					else {
						JOptionPane.showMessageDialog(getParent(), "Please Enter Book Number or No.of Books in Numeric Form!");
						labelforbookno.setText("");
						noofbook.setText("");
					}
				}
				
			}
		});
		submitbtn.setFont(new Font("Tahoma", Font.BOLD, 16));
		submitbtn.setBounds(339, 395, 131, 34);
		contentPane.add(submitbtn);
		
		cancelbtn = new JButton("CLOSE");
		cancelbtn.setBackground(new Color(255, 0, 0));
		cancelbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
//				LibrarianMenuPage mp = new LibrarianMenuPage();
//				mp.setVisible(true);
//				mp.show();
				dispose();
			}
		});
		cancelbtn.setFont(new Font("Tahoma", Font.BOLD, 16));
		cancelbtn.setBounds(117, 395, 131, 34);
		contentPane.add(cancelbtn);
		
		labelforbookno = new JLabel("");
		labelforbookno.setFont(new Font("Times New Roman", Font.BOLD, 15));
		labelforbookno.setHorizontalAlignment(SwingConstants.CENTER);
		labelforbookno.setBounds(287, 86, 256, 34);
		contentPane.add(labelforbookno);
		booksdao bkkdao = new booksdao();
		
		int maxbookno = bkkdao.GettingLastBookNumber();
		String maxbooknostring = String.valueOf(maxbookno);
		labelforbookno.setText(maxbooknostring);
		
		lblforwarning = new JLabel("");
		lblforwarning.setForeground(new Color(255, 0, 0));
		lblforwarning.setBounds(287, 227, 256, 14);
		contentPane.add(lblforwarning);
		
		
	}
	
	public boolean isNumeric(String str) { 
		  try {  
		    Double.parseDouble(str);  
		    return true;
		  } catch(NumberFormatException e){  
		    return false;  
		  }  
	}
}
