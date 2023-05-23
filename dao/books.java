package com.dao;

import java.util.ArrayList;

public class books 
{
	
	int SrNo, BookNo;
	String BookName;
	int NoOfBooks;
	String Aurthor, Edition;
	
	
	public int getBookNo() {
		return BookNo;
	}
	public void setBookNo(int bookNo) {
		BookNo = bookNo;
	}
	public int getNoOfBooks() {
		return NoOfBooks;
	}
	public void setNoOfBooks(int noOfBooks) {
		NoOfBooks = noOfBooks;
	}
	public String getBookName() {
		return BookName;
	}
	public void setBookName(String bookName) {
		BookName = bookName;
	}
	public String getAurthor() {
		return Aurthor;
	}
	public void setAurthor(String aurthor) {
		Aurthor = aurthor;
	}
	public String getEdition() {
		return Edition;
	}
	public void setEdition(String edition) {
		Edition = edition;
	}
	public int getSrNo() {
		return SrNo;
	}
	public void setSrNo(int srNo) {
		SrNo = srNo;
	}

}
