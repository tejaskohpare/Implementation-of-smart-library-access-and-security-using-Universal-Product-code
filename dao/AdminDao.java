package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDao 
{
//	public static Connection con;
//	public static PreparedStatement ps;
//	public static ResultSet rs;
	
//	public AdminDao() 
//	{
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
////			System.out.println(99);
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
	
	public boolean CheckAdminIdPassword(String id, String password)
	{
		connectionwithdb();
		boolean status = false;
		try {
			ps = con.prepareStatement("SELECT * FROM ADMIN WHERE ADMIN_ID = ? AND ADMIN_PASS = ?");
			ps.setString(1, id);
			ps.setString(2, password);
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
	
	public boolean isLibrarianIdExist(String librarianid)
	{
		connectionwithdb();
		boolean status = false;
		try {
			ps = con.prepareStatement("SELECT * FROM LIBRARIAN WHERE LIBRARY_ID = ?");
			ps.setString(1, librarianid);
			
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

	
	public boolean AddLibrarian(admin ad)
	{
		connectionwithdb();
		boolean status = false;
		int temp = 0;
		try {
			ps = con.prepareStatement("INSERT INTO LIBRARIAN VALUES(?, ?, ?, ?)");
			ps.setString(1, ad.getLibrarianid());
			ps.setString(2, ad.getLibrarianpass());
			ps.setString(3, ad.getLibrarianname());
			ps.setString(4, ad.getLibrarianemail());
			temp = ps.executeUpdate();
			
			if(temp == 1)
			{
				status = true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}
	
	public boolean CheckLibrarianExistOrNot(String id )
	{
		connectionwithdb();
		boolean status = false;
		
		try {
			ps = con.prepareStatement("SELECT * FROM LIBRARIAN WHERE LIBRARY_ID = ?");
			ps.setString(1, id);
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
	
	public boolean DeleteLibrarianMethod(String id)
	{
		connectionwithdb();
		boolean status = false;
		int temp = 0;
		try {
			ps = con.prepareStatement("DELETE FROM LIBRARIAN WHERE LIBRARY_ID = ?");
			ps.setString(1, id);
			temp = ps.executeUpdate();
			if(temp == 1)
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
