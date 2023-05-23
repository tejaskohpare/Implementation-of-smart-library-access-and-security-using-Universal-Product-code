package com.library;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.dao.returnIssueDao;


import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Toolkit;

public class returnissuepage extends JFrame {

	private JPanel contentPane;
	private JTable issuedbookstable;
	private JScrollPane scrollPane;
	private JLabel studentphoto;
	private JLabel rollnumber;
	private JLabel nameofstudent;
	private JLabel yearofstudent;
	private JLabel branchofstudent;
	private JTextField rollnum;
	private JTextField booknum;
	Byte b[];
	Blob blob;
	int letidofcurrent = 0;  // 0 = session is not running / 1 = session is running
	String rollNumber;
	
	public static Connection con;
	public static PreparedStatement ps;
	public static ResultSet rs;
	public static String ad;
	
	public static void connectionwithdb() {
		ad = "";
		
		
		
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			String connectonurl = "jdbc:jtds:sqlserver://"+ ad +":1433;"+"databasename=library;user=sa;password=1234;";
			con = DriverManager.getConnection(connectonurl);
			
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Faild");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					returnissuepage frame = new returnissuepage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public returnissuepage() {
		
		connectionwithdb();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\MINI-PROJECT\\LIBRARY ACCESS\\src\\com\\library\\img\\icon.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1920, 1080);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		
		returnIssueDao rid = new returnIssueDao();
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ISSUE / RETURN BOOKS");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 0, 1263, 37);
		contentPane.add(lblNewLabel);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 371, 699, 219);
		contentPane.add(scrollPane);
		
		
		issuedbookstable = new JTable();
		scrollPane.setViewportView(issuedbookstable);
		issuedbookstable.setRowHeight(issuedbookstable.getRowHeight() + 10);
		Font font = new Font("Times New Roman", Font.PLAIN, 14);
		issuedbookstable.setFont(font);
		issuedbookstable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		

		
		studentphoto = new JLabel("");
		studentphoto.setBounds(40, 108, 187, 203);
		Border border = BorderFactory.createLineBorder(Color.BLACK, 3);
		studentphoto.setBorder(border);
		contentPane.add(studentphoto);
		
		rollnumber = new JLabel("Id :");
		rollnumber.setFont(new Font("Times New Roman", Font.BOLD, 20));
		rollnumber.setBounds(270, 97, 102, 30);
		contentPane.add(rollnumber);
		
		nameofstudent = new JLabel("Name :");
		nameofstudent.setFont(new Font("Times New Roman", Font.BOLD, 20));
		nameofstudent.setBounds(270, 154, 102, 30);
		contentPane.add(nameofstudent);
		
		yearofstudent = new JLabel("Year :");
		yearofstudent.setFont(new Font("Times New Roman", Font.BOLD, 20));
		yearofstudent.setBounds(270, 212, 102, 30);
		contentPane.add(yearofstudent);
		
		branchofstudent = new JLabel("Branch :");
		branchofstudent.setFont(new Font("Times New Roman", Font.BOLD, 20));
		branchofstudent.setBounds(270, 276, 102, 30);
		contentPane.add(branchofstudent);
		
		rollnum = new JTextField();
		rollnum.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				Border border1 = new LineBorder(Color.RED, 1, true);
				rollnum.setBorder(border1);

			}
			@Override
			public void focusLost(FocusEvent e) {
				Border border1 = new LineBorder(Color.GRAY, 2, true);
				rollnum.setBorder(border1);

			}
		});
		rollnum.setFont(new Font("Times New Roman", Font.BOLD, 16));
		Border border111 = new LineBorder(Color.GRAY, 2, true);
		rollnum.setBorder(border111);
		rollnum.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) 
			{
				int position = rollnum.getCaretPosition();
				rollnum.setText(rollnum.getText().toUpperCase());
				rollnum.setCaretPosition(position);
			}
		});
		rollnum.setBounds(365, 96, 196, 30);
		
		contentPane.add(rollnum);
		rollnum.setColumns(10);
		
		JLabel namelbl = new JLabel("");
		namelbl.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		namelbl.setBounds(365, 154, 356, 30);
		contentPane.add(namelbl);
		
		JLabel yearlbl = new JLabel("");
		yearlbl.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		yearlbl.setBounds(365, 212, 241, 30);
		contentPane.add(yearlbl);
		
		JLabel branchlbl = new JLabel("");
		branchlbl.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		branchlbl.setBounds(365, 276, 356, 30);
		contentPane.add(branchlbl);
		
		JLabel nameofstudent_1 = new JLabel("Enter Borrower Id :");
		nameofstudent_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		nameofstudent_1.setBounds(370, 69, 133, 24);
		contentPane.add(nameofstudent_1);
		
		JLabel lblNewLabel_1 = new JLabel("Enter Book Number :");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_1.setBounds(756, 124, 172, 30);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Book Name :");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(756, 170, 172, 30);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Aurthor Name");
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_1_2.setBounds(756, 228, 172, 30);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Edition :");
		lblNewLabel_1_3.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_1_3.setBounds(756, 280, 172, 30);
		contentPane.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Transition Date :");
		lblNewLabel_1_4.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_1_4.setBounds(756, 338, 172, 30);
		contentPane.add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_5 = new JLabel("Return Date :");
		lblNewLabel_1_5.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_1_5.setBounds(756, 403, 172, 30);
		contentPane.add(lblNewLabel_1_5);
		
		booknum = new JTextField();
		booknum.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				Border border1 = new LineBorder(Color.RED, 1, true);
				booknum.setBorder(border1);
			}
			@Override
			public void focusLost(FocusEvent e) {
				Border border1 = new LineBorder(Color.GRAY, 2, true);
				booknum.setBorder(border1);
			}
		});
		booknum.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		Border border1 = new LineBorder(Color.GRAY, 2, true);
		booknum.setBorder(border1);
		booknum.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) 
			{
				char c = e.getKeyChar();
				if(Character.isLetter(c))
				{
					booknum.setEditable(false);
				}else {
					booknum.setEditable(true);
				}
			}
		});
		booknum.setColumns(10);
		booknum.setBounds(938, 124, 187, 30);
		contentPane.add(booknum);
		
		JLabel nameofbooklbl = new JLabel("");
		nameofbooklbl.setBounds(938, 170, 239, 30);
		contentPane.add(nameofbooklbl);
		
		JLabel aurthornamelbl = new JLabel("");
		aurthornamelbl.setFont(new Font("Times New Roman", Font.BOLD, 16));
		aurthornamelbl.setBounds(938, 228, 239, 30);
		contentPane.add(aurthornamelbl);
		
		JLabel editionlbl = new JLabel("");
		editionlbl.setFont(new Font("Times New Roman", Font.BOLD, 16));
		editionlbl.setBounds(938, 280, 239, 30);
		contentPane.add(editionlbl);
		
		JLabel transitiondatelbl = new JLabel("");
		transitiondatelbl.setFont(new Font("Times New Roman", Font.BOLD, 16));
		transitiondatelbl.setBounds(938, 338, 239, 30);
		contentPane.add(transitiondatelbl);
		
		JLabel deudatelbl = new JLabel("");
		deudatelbl.setFont(new Font("Times New Roman", Font.BOLD, 16));
		deudatelbl.setBounds(938, 403, 239, 30);
		contentPane.add(deudatelbl);
		
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon("D:\\MINI-PROJECT\\LIBRARY ACCESS\\src\\com\\library\\img\\searchbook.png"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(letidofcurrent == 0)
				{
					
					
					rollNumber = rollnum.getText();
//					returnIssueDao rid = new returnIssueDao();
//					returnissuepage ri = new returnissuepage();           //setter getter class
					boolean flag = false;
					flag = rid.checkingRollNumber(rollNumber);
					if(flag && !(rollNumber.equals("")))
					{
//						DefaultTableModel model = (DefaultTableModel) issuedbookstable.getModel();
//						model.setRowCount(0);
						
						rid.rs = rid.studentInformation(rollNumber);
						try {
							if(rid.rs.next())
							{
								namelbl.setText(rid.rs.getString("STUDENTFIRSTNAME")+" "+ rid.rs.getString("STUDENTLASTNAME"));
								String yr = String.valueOf(rid.rs.getInt("YEAR"));
								yearlbl.setText(yr + " year");
								branchlbl.setText(rid.rs.getString("DEPARTMENTNAME"));
								
								blob=rid.rs.getBlob("IMAGE");
								
//								
								ImageIcon image = new ImageIcon();
								Image im = image.getImage();
								Image myimg = im.getScaledInstance(studentphoto.getWidth(), studentphoto.getHeight(), Image.SCALE_SMOOTH);
								ImageIcon  newimage = new ImageIcon(myimg);
								studentphoto.setIcon(newimage);
								

								letidofcurrent = 1;           // for running session
								
								
							}
							
							
							try {
//								ldao.ps = ldao.con.prepareStatement("SELECT * FROM ISSHUEDBOOKS WHERE ROLLNUMBER = ?");
								ps = con.prepareStatement("SELECT ROLLNUMBER, BOOKNUMBER, ISHHUEDATE, RETURNDATE,BOOK_NAME FROM ISSHUEDBOOKS WHERE ROLLNUMBER = ?");
								ps.setString(1, rollNumber);
								rs = ps.executeQuery();
								
								ResultSetMetaData rsmd =  (ResultSetMetaData) rs.getMetaData();
								DefaultTableModel model = (DefaultTableModel) issuedbookstable.getModel();
								
								int cols = rsmd.getColumnCount();		// calculating columns of table 
					//
//								String[] colname = new String[cols];
//								
//								for( int i=0;i<cols;i++)
//								{
//									colname[i] = rsmd.getColumnName(i+1);		// print heading of columns 
//								}
								String[] colname = new String[5];
								colname[0] ="ROLL NUMBER";
								colname[1] ="BOOK NUMBER";
								colname[2] ="TRANSITION DATE";
								colname[3] ="RETURN DATE";
								colname[4] ="BOOK NAME";
								model.setColumnIdentifiers(colname);			// Adding Headings of Table 
								
								issuedbookstable.getColumnModel().getColumn(2).setPreferredWidth(100);
								issuedbookstable.getColumnModel().getColumn(4).setPreferredWidth(250);

								
								String Rollnumber, booknumber, issuedate, returndate, bookname;
								
								while(rs.next())
								{
									Rollnumber = rs.getString(1);
									booknumber = rs.getString(2);
									issuedate = rs.getString(3);
									returndate = rs.getString(4);
									bookname = rs.getString("BOOK_NAME");
									Object[] row = {Rollnumber, booknumber, issuedate, returndate, bookname };
									model.addRow(row);
								}
								
								
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else {
						JOptionPane.showMessageDialog(getParent(), "Please Enter Roll Number Or Roll Number not Exist!");
					}
				}
				else {
					
					JOptionPane.showMessageDialog(getParent(), "Please End Session First!");
				}
				
				
				
				
			}
		});
		btnNewButton.setBounds(557, 97, 53, 30);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setIcon(new ImageIcon("D:\\MINI-PROJECT\\LIBRARY ACCESS\\src\\com\\library\\img\\searchbook.png"));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
//				String getName = namelbl.getText();
				if(!(namelbl.getText().equals("")))
				{
					String bookNumber = booknum.getText();
					boolean flag = false;
					flag = isNumeric(bookNumber);
					if(flag)
					{
//						returnIssueDao rid = new returnIssueDao();
						int bookNum = Integer.parseInt(bookNumber);
						boolean status = false, status1 = false;
						status = rid.checkingBookisIssuedOrNot(bookNum);
						status1 = rid.checkingBookExistInLibrary(bookNum);
						if(status && status1 )
						{
						
							rid.rs = rid.getDataOfBooks(bookNum);
							try {
								if(rid.rs.next())
								{
									nameofbooklbl.setText(rid.rs.getString("BOOK_NAME"));
									aurthornamelbl.setText(rid.rs.getString("AURTHOR"));
									editionlbl.setText(rid.rs.getString("EDITION"));
									LocalDate today = LocalDate.now();							// get todays date from sys
									String todaysdate  = today.toString();
									String after15daysdate = today.plusDays(15).toString();		// After 15 days date 
									transitiondatelbl.setText(todaysdate);
									deudatelbl.setText(after15daysdate);
									
								}
								
							} catch (SQLException e1) {
								JOptionPane.showMessageDialog(getParent(), "Something went Wrong please Do again!");
								e1.printStackTrace();
							}
							
						}
						else {
							JOptionPane.showMessageDialog(getParent(), "Book is Not Available in Library!");
						}
					}
					else {
						JOptionPane.showMessageDialog(getParent(), "Please Enter Book Number in Number Format!");
					}
					
					
				}
				else {
					JOptionPane.showMessageDialog(getParent(), "Please Enter Strudent Roll Number First!");
				}
			}
		});
		btnNewButton_1.setBounds(1124, 124, 53, 30);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2_1 = new JButton("ISSUE");
		btnNewButton_2_1.setBackground(new Color(0, 255, 64));
		btnNewButton_2_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
//				returnIssueDao rid = new returnIssueDao();
				
				if(!(nameofbooklbl.getText().equals("")))
				{
					int numberOfBooksIssued = rid.howManyBookBorrow(rollNumber);
					if(numberOfBooksIssued <3)
					{
						String bookNum = booknum.getText();
						int bookNumber = Integer.parseInt(bookNum);
						String issueDate = transitiondatelbl.getText();
						String returnDate = deudatelbl.getText();
					
						
						boolean flag = false;
						flag = rid.checkingBookisIssuedOrNot(bookNumber);
						if(flag)
						{
							boolean status = false;
							String bookname = nameofbooklbl.getText();
							status = rid.addToIssueTable(rollNumber, bookNumber, issueDate, returnDate, bookname);
							if (status)
							{
								JOptionPane.showMessageDialog(getParent(), "Book Issued Successfully...");
								
								DefaultTableModel model = (DefaultTableModel) issuedbookstable.getModel();
								
								Object[] row = {rollNumber, bookNumber, issueDate, returnDate, bookname };
								model.addRow(row);
								
							} else {
								JOptionPane.showMessageDialog(getParent(), "Something Went Wrong!");
							}
						}else {
							JOptionPane.showMessageDialog(getParent(), "Book Already Issued!");
						}
						
					}else {
						JOptionPane.showMessageDialog(getParent(), "You cannot Borrow More than 3 Books!");
					}
					
				}else {
					JOptionPane.showMessageDialog(getParent(), "Please Enter Book Number First!");
				}
			}
		});
		btnNewButton_2_1.setBounds(1035, 489, 142, 37);
		contentPane.add(btnNewButton_2_1);
		
		JButton btnNewButton_2_2 = new JButton("END SESSION");
		btnNewButton_2_2.setBackground(new Color(255, 0, 0));
		btnNewButton_2_2.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnNewButton_2_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				rollnum.setText("");
				namelbl.setText("");
				yearlbl.setText("");
				branchlbl.setText("");
				studentphoto.setIcon(null);
				booknum.setText("");
//				booknamelbl.setText("");
				nameofbooklbl.setText("");
				aurthornamelbl.setText("");
				editionlbl.setText("");
				transitiondatelbl.setText("");
				deudatelbl.setText("");
				DefaultTableModel model = (DefaultTableModel) issuedbookstable.getModel();
				model.setRowCount(0);
				letidofcurrent = 0;
				rollNumber = null;
			}
		});
		btnNewButton_2_2.setBounds(1131, 56, 142, 37);
		contentPane.add(btnNewButton_2_2);
		
		issuedbookstable.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent e)
			{
				DefaultTableModel model = (DefaultTableModel) issuedbookstable.getModel();
				int rowindex = issuedbookstable.getSelectedRow();
				String rollnum = (String) model.getValueAt(rowindex, 0);
				String booknumm = (String) model.getValueAt(rowindex, 1);
				String issuedate = (String) model.getValueAt(rowindex, 2);
				String returndate = (String) model.getValueAt(rowindex, 3);
				
				int numOfBook = Integer.parseInt(booknumm);
				
				rid.rs = rid.getDataOfBooks(numOfBook);
				try {
					if(rid.rs.next())
					{
						String BookName = rid.rs.getString("BOOK_NAME");
						String Aurthor = rid.rs.getString("AURTHOR");
						String Edition = rid.rs.getString("EDITION");
						
						nameofbooklbl.setText(BookName);
						aurthornamelbl.setText(Aurthor);
						editionlbl.setText(Edition);
						booknum.setText(booknumm);
						transitiondatelbl.setText(issuedate);
						deudatelbl.setText(returndate);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		
		JButton btnNewButton_2 = new JButton("RETURN");
		btnNewButton_2.setBackground(new Color(255, 0, 0));
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				DefaultTableModel model = (DefaultTableModel) issuedbookstable.getModel();
				int rowindex = issuedbookstable.getSelectedRow();
				String rollnum = (String) model.getValueAt(rowindex, 0);
				String booknumm = (String) model.getValueAt(rowindex, 1);
				String issuedate = (String) model.getValueAt(rowindex, 2);
				String returndate = (String) model.getValueAt(rowindex, 3);
				
				int numOfBook = Integer.parseInt(booknumm);
				
				int selection = JOptionPane.showConfirmDialog(null, "Do You Want To Return This row" , "Confirme", JOptionPane.YES_NO_OPTION);
				if(selection == JOptionPane.YES_OPTION)
				{
					Boolean status = false;
					status = rid.deleteIssuedBook(rollnum, numOfBook);
					if(status)
					{
						model.removeRow(rowindex);
						JOptionPane.showMessageDialog(getParent(), "Book Return Successfully..");
					}
				}
			}
		});
		btnNewButton_2.setBounds(765, 489, 142, 37);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_2_1_1 = new JButton("BACK");
		btnNewButton_2_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				LibrarianMenuPage lmp = new LibrarianMenuPage();
				lmp.setVisible(true);
				dispose();
			}
		});
		btnNewButton_2_1_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnNewButton_2_1_1.setBackground(new Color(128, 128, 0));
		btnNewButton_2_1_1.setBounds(900, 569, 142, 37);
		contentPane.add(btnNewButton_2_1_1);
		
		
		
		
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

