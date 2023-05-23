package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class returnIssueDao 
{
//	public Connection con;
//	public PreparedStatement ps;
//	public ResultSet rs;
//	
//	public returnIssueDao() 
//	{
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
////			System.out.println("Driver found");
//			
//			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","1234");
////			System.out.println("Connection Created");
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
	
	public boolean checkingRollNumber(String rollnum)
	{
		connectionwithdb();
		boolean flag = false;
		try {
			ps = con.prepareStatement("SELECT * FROM STUDENTS WHERE ROLLNUMBER = ?");
			ps.setString(1, rollnum);
			rs = ps.executeQuery();
			if(rs.next())
			{
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	
	public ResultSet studentInformation(String rollnum)
	{
		connectionwithdb();
		try {
			ps = con.prepareStatement("SELECT * FROM STUDENTS WHERE ROLLNUMBER = ?");
			ps.setString(1, rollnum);
			rs = ps.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public boolean checkingBookisIssuedOrNot(int booknumber)
	{
		connectionwithdb();
		boolean flag = false;
		try {
			ps = con.prepareStatement("SELECT * FROM ISSHUEDBOOKS WHERE BOOKNUMBER = ?");
			ps.setInt(1, booknumber);
			rs = ps.executeQuery();
			if(rs.next())
			{
				flag = false;
			}
			else {
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	
	public boolean checkingBookExistInLibrary(int booknumber)
	{
		connectionwithdb();
		boolean flag = false;
		try {
			ps = con.prepareStatement("SELECT * FROM ALLBOOKS WHERE BOOK_NUMBER = ?");
			ps.setInt(1, booknumber);
			rs = ps.executeQuery();
			if(rs.next())
			{
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	
	public int howManyBookBorrow(String rollnum)
	{
		connectionwithdb();
		int num = 0;
		try {
			ps = con.prepareStatement("SELECT * FROM ISSHUEDBOOKS WHERE ROLLNUMBER = ?");
			ps.setString(1, rollnum);
			rs = ps.executeQuery();
			while(rs.next())
			{
				num += 1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}
	
	public ResultSet getDataOfBooks(int booknumber)
	{
		connectionwithdb();
		try {
			ps = con.prepareStatement("SELECT * FROM ALLBOOKS WHERE BOOK_NUMBER = ?");
			ps.setInt(1, booknumber);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public boolean addToIssueTable(String rollnumber, int booknumber, String issuedate, String returndate, String bookname)
	{
		connectionwithdb();
		boolean status = false;
		int flag = 0;
		try {
			ps = con.prepareStatement("INSERT INTO ISSHUEDBOOKS(ROLLNUMBER, BOOKNUMBER, ISHHUEDATE, RETURNDATE, BOOK_NAME) VALUES(?, ?, ?, ?, ?)");
			ps.setString(1, rollnumber);
			ps.setInt(2, booknumber);
			ps.setString(3, issuedate);
			ps.setString(4, returndate);
			ps.setString(5, bookname);
			
			
			flag = ps.executeUpdate();
			if(flag == 1)
			{
				status = true;
			}
//			ps = con.prepareStatement(returndate)
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}
	
	public boolean deleteIssuedBook(String rollnum, int booknum)
	{
		connectionwithdb();
		boolean status = false;
		int flag = 0;
		try {
			ps = con.prepareStatement("DELETE FROM ISSHUEDBOOKS WHERE ROLLNUMBER = ? AND BOOKNUMBER = ?");
			ps.setString(1, rollnum);
			ps.setInt(2, booknum);
			flag = ps.executeUpdate();
			if(flag == 1)
			{
				status = true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}
}
