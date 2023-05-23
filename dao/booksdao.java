package com.dao;

import java.awt.geom.FlatteningPathIterator;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;



public class booksdao 
{
//	public static Connection con;
//	public static PreparedStatement ps;
//	public static ResultSet rs;
//	
//	public booksdao() 
//	{
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//		
//			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","1234");
//		} catch (ClassNotFoundException | SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//	}
	
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
	
	public boolean Addbook(books bk)
	{
		connectionwithdb();
		boolean status = false;
		int temp = 0;
		try {
			
//			ps = con.prepareStatement("INSERT INTO BOOKS(BOOK_NUMBER, BOOK_NAME, NO_OF_BOOK, AURTHOR, EDITION) VALUES(?,?,?,?,?)");
//			ps.setInt(1, bk.getBookNo());
//			ps.setString(2, bk.getBookName());
//			ps.setInt(3, bk.getNoOfBooks());
//			ps.setString(4, bk.getAurthor());
//			ps.setString(5, bk.getEdition());
//			
//			temp = ps.executeUpdate();
			
			
			int BookNo = bk.getBookNo();
			int NoOfCopies = bk.getNoOfBooks();
			for(int i=0;i<NoOfCopies;i++)
			{
				ps = con.prepareStatement("INSERT INTO ALLBOOKS(BOOK_NUMBER, BOOK_NAME, AURTHOR, EDITION) VALUES (?, ?, ?, ?)");
				ps.setInt(1, BookNo+i);
				ps.setString(2, bk.getBookName());
				ps.setString(3, bk.getAurthor());
				ps.setString(4, bk.getEdition());
				
				temp = ps.executeUpdate();
				
			}
			
			
			if(temp == 1)
			{
				status = true;
			}
			ps.close();
			con.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return status;
	}
	
	
	public boolean CheckingPrimarykey(int bookno)
	{
		connectionwithdb();
		boolean status = false;
		
		try {
			ps = con.prepareStatement("SELECT * FROM ALLBOOKS WHERE BOOK_NUMBER = ?");
			ps.setInt(1, bookno);
			rs = ps.executeQuery();
			if(rs.next())
			{
				status = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}
	
	
	public int GettingLastBookNumber() 
	{
		connectionwithdb();
		int bookno = 0;
		try {
			ps = con.prepareStatement("SELECT MAX(BOOK_NUMBER) FROM ALLBOOKS");
			rs = ps.executeQuery();
			if(rs.next())
			{
				bookno = rs.getInt(1);
				bookno = bookno+1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookno;
	}

}
