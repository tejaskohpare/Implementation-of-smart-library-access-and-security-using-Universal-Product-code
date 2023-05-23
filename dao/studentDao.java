package com.dao;

import java.awt.geom.FlatteningPathIterator;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputFilter.Status;
import java.lang.foreign.SymbolLookup;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class studentDao 
{
//	public Connection con;
//	public PreparedStatement ps;
//	public ResultSet rs;
//	
//	public studentDao()
//	{
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//		
//			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","1234");
//		} catch (ClassNotFoundException | SQLException e1) {
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
	
	public boolean checkingRollNumberExistOrNot(String rollNumber)
	{
		connectionwithdb();
		boolean status = false;
		try {
			ps = con.prepareStatement("SELECT * FROM STUDENTS WHERE ROLLNUMBER = ?");
			ps.setString(1, rollNumber);
			rs = ps.executeQuery();
			if(rs.next())
			{
				status = true;
			}
			
//			con.close();
//			ps.close();
//			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return status;
	}
	
	public boolean addStudent(student st) 
	{
		connectionwithdb();
		boolean status = false;
		int flag = 0;
	
		try {
			File file = new File(st.getPhotoPath());
			FileInputStream fis = new FileInputStream(file);
			
			ps = con.prepareStatement("INSERT INTO STUDENTS(ROLLNUMBER, STUDENTFIRSTNAME, STUDENTLASTNAME, DEPARTMENTNAME, YEAR, DATEOFBIRTH, IMAGE, PASSWORD) VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, st.getRollNumber());
			ps.setString(2, st.getStudentFirstName());
			ps.setString(3, st.getStudentLastName());
			ps.setString(4, st.getDepartmentNumber());
			ps.setInt(5, st.getYearOfStudy());
			ps.setString(6, st.getDateOfBirth());
			ps.setBinaryStream(7, fis, (int) file.length());
			ps.setString(8, st.getPassword());
			flag = ps.executeUpdate();
		
			
			
			
			
//			ps = con.prepareStatement("INSERT INTO IMAGE_TABLE(ROLLNUMBER, IMAGE) VALUES(?,?)");
//			ps.setString(1, st.getRollNumber());
//			ps.setBinaryStream(2, fis, (int) file.length());
//			flag1 = ps.executeUpdate();


			if(flag == 1)
			{
				status = true;
				
			}
//			con.close();
//			ps.close();
//			rs.close();
		} catch (SQLException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return status;
	}
	
}
