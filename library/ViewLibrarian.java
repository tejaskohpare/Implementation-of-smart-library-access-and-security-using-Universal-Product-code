package com.library;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.dao.librariandao;

import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class ViewLibrarian extends JFrame {

	private JPanel contentPane;
	private JTable viewlibrariantable;
	private JScrollPane scrollPane;
	private JButton closebtn;
	
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
					ViewLibrarian frame = new ViewLibrarian();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ViewLibrarian() {
		connectionwithdb();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setBounds(330, 120, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(255, 0, 0), 2, true));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("All Librarians Data");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(0, 0, 600, 47);
		contentPane.add(lblNewLabel);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 76, 580, 325);
		contentPane.add(scrollPane);
		
		viewlibrariantable = new JTable();
		scrollPane.setViewportView(viewlibrariantable);
		
		closebtn = new JButton("Close");
		closebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
			}
		});
		closebtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		closebtn.setBounds(245, 424, 117, 37);
		contentPane.add(closebtn);
		
		
		try {
			ps = con.prepareStatement("SELECT * FROM LIBRARIAN");
			rs = ps.executeQuery();
			
			ResultSetMetaData rsmd =  (ResultSetMetaData) rs.getMetaData();
			DefaultTableModel model = (DefaultTableModel) viewlibrariantable.getModel();
			
			int cols = rsmd.getColumnCount();		// calculating columns of table 

			String[] colname = new String[cols];
			
			for( int i=0;i<cols;i++)
			{
				colname[i] = rsmd.getColumnName(i+1);		// print heading of columns 
			}
			model.setColumnIdentifiers(colname);			// Adding Headings of Table 
			
			String LibrarianId, LibrarianPassword, LibrarianName, LibrarianEmail;
			
			while(rs.next())
			{
				LibrarianId = rs.getString(1);
				LibrarianPassword = rs.getString(2);
				LibrarianName = rs.getString(3);
				LibrarianEmail = rs.getString(4);
				
				Object[] row = {LibrarianId, LibrarianPassword, LibrarianName, LibrarianEmail };
				model.addRow(row);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
