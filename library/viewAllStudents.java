package com.library;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.dao.booksdao;
import com.dao.student;
import com.dao.studentDao;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class viewAllStudents extends JFrame {

	private JPanel contentPane;
	private JTable allstudenttable;
	private JScrollBar scrollBar;
	private JScrollPane scrollPane;

	
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
					viewAllStudents frame = new viewAllStudents();
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
	public viewAllStudents() {
		
		connectionwithdb();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setBounds(200, 120, 850, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 255, 64), 2));

		
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("All Students");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(0, 0, 850, 61);
		contentPane.add(lblNewLabel);
		
		
		allstudenttable = new JTable();
		allstudenttable.setBounds(10, 72, 830, 359);
		contentPane.add(allstudenttable);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 72, 830, 359);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(allstudenttable);
		
		
//		studentDao stdao = new studentDao();
		
		try {
//			stdao.ps = stdao.con.prepareStatement("SELECT * FROM STUDENTS");
			ps = con.prepareStatement("SELECT ROLLNUMBER, STUDENTFIRSTNAME, STUDENTLASTNAME, DEPARTMENTNAME, YEAR, DATEOFBIRTH, NOOFBOOKSISSUED FROM STUDENTS");
			rs = ps.executeQuery();
			
			ResultSetMetaData rsmd =  (ResultSetMetaData) rs.getMetaData();
			DefaultTableModel model = (DefaultTableModel) allstudenttable.getModel();
			
//			int cols = rsmd.getColumnCount();		// calculating columns of table 

//			String[] colname = new String[cols];
			String[] colname = new String[7];
			
//			for( int i=0;i<cols;i++)
//			{
//				colname[i] = rsmd.getColumnName(i+1);		// print heading of columns 
//			
//			}
			
			colname[0] = "SRNO";
			colname[1] = "ROLLNUMBER";
			colname[2] = "FIRSTNAME";
			colname[3] = "LASTNAME";
			colname[4] = "DEPARTMENTNAME";
			colname[5] = "YEAR";
			colname[6] = "DATEOFBIRTH";
//			colname[7] = "BOOKISSUED";
			model.setColumnIdentifiers(colname);
			
			int year, flag = 1,srNo;
			
			String rollNumber, firstName, lastName, departmentName, dateOfBirth, noOfBookIssued;
			
			while(rs.next())
			{
				srNo = flag;
				rollNumber = rs.getString(1);
				firstName = rs.getString(2);
				lastName = rs.getString(3);
				departmentName = rs.getString(4);
				year = rs.getInt(5);
				dateOfBirth = rs.getString(6);
//				noOfBookIssued = stdao.rs.getString(7);
				
				Object[] row = { srNo, rollNumber, firstName, lastName, departmentName,year, dateOfBirth };
				model.addRow(row);
				flag += 1;
			}
			
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		JButton Close = new JButton("Close");
		Close.setFont(new Font("Times New Roman", Font.BOLD, 20));
		Close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
			}
		});
		Close.setBounds(364, 452, 145, 37);
		contentPane.add(Close);
		
		
		
		
		
		
		
		
		
		
	}
}
