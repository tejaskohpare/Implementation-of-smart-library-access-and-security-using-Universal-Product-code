package com.library;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.dao.returnIssueDao;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;

public class ViewIssuedBooks extends JFrame {

	private JPanel contentPane;
	private JTable showbooktable;

	
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
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewIssuedBooks frame = new ViewIssuedBooks();
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
	public ViewIssuedBooks() {
		
		connectionwithdb();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setBounds(330, 120, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 255, 0), 2, true));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ALL ISSUED BOOKS");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(new Color(0, 64, 64));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 600, 45);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 79, 580, 333);
		contentPane.add(scrollPane);
		
		showbooktable = new JTable();
		scrollPane.setViewportView(showbooktable);
		showbooktable.setBorder(new LineBorder(new Color(135, 62, 35), 2, true));


		
		JButton btnNewButton = new JButton("CLOSE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				dispose();
			}
		});
		btnNewButton.setBackground(new Color(255, 0, 0));
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnNewButton.setBounds(228, 423, 132, 38);
		contentPane.add(btnNewButton);
		
		returnIssueDao rid = new returnIssueDao();

		try {
			ps = con.prepareStatement("SELECT * FROM ISSHUEDBOOKS");
			rs = ps.executeQuery();
			
			ResultSetMetaData rsmd =  (ResultSetMetaData) rs.getMetaData();
			DefaultTableModel model = (DefaultTableModel) showbooktable.getModel();
			
			int cols = rsmd.getColumnCount();		// calculating columns of table 
//
//			String[] colname = new String[cols];
//			
//			for( int i=0;i<cols;i++)
//			{
//				colname[i] = rsmd.getColumnName(i+1);		// print heading of columns 
//			}
			String[] colname = new String[4];
			colname[0] ="ROLL NUMBER";
			colname[1] ="BOOK NUMBER";
			colname[2] ="TRANSITION DATE";
			colname[3] ="RETURN DATE";
			model.setColumnIdentifiers(colname);			// Adding Headings of Table 
			
			String Rollnumber, booknumber, issuedate, returndate;
			
			while(rs.next())
			{
				Rollnumber = rs.getString(1);
				booknumber = rs.getString(2);
				issuedate = rs.getString(3);
				returndate = rs.getString(4);
				
				Object[] row = {Rollnumber, booknumber, issuedate, returndate };
				model.addRow(row);
			}
			
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			dispose();
			e1.printStackTrace();
		}
		
	}
}
