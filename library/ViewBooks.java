package com.library;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.dao.books;
import com.dao.booksdao;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Toolkit;

public class ViewBooks extends JFrame {

	
	
	
	private JPanel contentPane;
	private JTable bookstable;
	private JTextField searchbar;
	
	
	public static Connection con;
	public static PreparedStatement ps;
	public static ResultSet rs;
	public static String ad;
	
	public static void connectionwithdb() {
		ad = "";
		
		
		
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			String connectonurl = "jdbc:jtds:sqlserver://"+ ad +":1433;"+"databasename= library;user= s a;password=1234;";
			con = DriverManager.getConnection(connectonurl);
			
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Faild");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewBooks frame = new ViewBooks();
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
	public ViewBooks() {
		
		connectionwithdb();
		
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\MINI-PROJECT\\LIBRARY ACCESS\\src\\com\\library\\img\\icon.png"));
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1920, 1080);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(104, 82, 1063, 475);
		contentPane.add(scrollPane);
		
		bookstable = new JTable();
		scrollPane.setViewportView(bookstable);
		
		JButton backbtn = new JButton("BACK");
		backbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				LibrarianMenuPage mp = new LibrarianMenuPage();
				mp.setVisible(true);
				dispose();
			}
		});
		backbtn.setFont(new Font("Tahoma", Font.BOLD, 16));
		backbtn.setBounds(505, 583, 194, 47);
		contentPane.add(backbtn);
		
		searchbar = new JTextField();
		searchbar.setBounds(105, 40, 217, 31);
		contentPane.add(searchbar);
		searchbar.setColumns(10);
		
		JButton searchbtn = new JButton("Search");
		searchbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{

//				booksdao bkdao = new booksdao();
				String booknum = searchbar.getText();
				
				
				if(isNumeric(booknum))
				{
					try {
						int bookNo = Integer.parseInt(booknum);
						
						DefaultTableModel dtm = (DefaultTableModel) bookstable.getModel();      
						dtm.setRowCount(0);																	// For clear Table
						
						ps = con.prepareStatement("SELECT * FROM ALLBOOKS WHERE BOOK_NUMBER = ?");
						ps.setInt(1, bookNo);
						rs = ps.executeQuery();
						
						ResultSetMetaData rsmd =  (ResultSetMetaData) rs.getMetaData();
						DefaultTableModel model = (DefaultTableModel) bookstable.getModel();
						
						int bookno;
						String bookname, aurthor, edition;
						
						while(rs.next())
						{												// set rows of table 
							bookno = rs.getInt(1);
							bookname = rs.getString(2);
							aurthor = rs.getString(3);
							edition = rs.getString(4);
							
							Object[] row = { bookno, bookname, aurthor, edition};
							model.addRow(row);
						}
						
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
				}
				else
				{
					JOptionPane.showMessageDialog(getParent(), "please Enter Book Number is Numaric form!");
					searchbar.setText("");
				}
				
				
				
			}
		});
		searchbtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		searchbtn.setBounds(356, 40, 140, 31);
		contentPane.add(searchbtn);
		
		JLabel searchlbl = new JLabel("Search By Book No :");
		searchlbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		searchlbl.setBounds(104, 11, 217, 25);
		contentPane.add(searchlbl);
	
	
		try {
			ps = con.prepareStatement("SELECT * FROM ALLBOOKS");
			rs = ps.executeQuery();
			
			
			ResultSetMetaData rsmd =  (ResultSetMetaData) rs.getMetaData();
			DefaultTableModel model = (DefaultTableModel) bookstable.getModel();
			
			int cols = rsmd.getColumnCount();		// calculating columns of table 

			String[] colname = new String[cols];
			
			
			for( int i=0;i<cols;i++)
			{
				colname[i] = rsmd.getColumnName(i+1);		// print heading of columns 
				
			}
			model.setColumnIdentifiers(colname);				//seting colums in page 
			
			int bookno;
			String bookname, aurthor, edition;
			
			while(rs.next())
			{												// set rows of table 
				bookno = rs.getInt(1);
				bookname = rs.getString(2);
				aurthor = rs.getString(3);
				edition = rs.getString(4);
				
				Object[] row = { bookno, bookname, aurthor, edition};
				model.addRow(row);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
