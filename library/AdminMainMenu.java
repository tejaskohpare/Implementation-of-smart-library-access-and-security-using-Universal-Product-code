package com.library;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Toolkit;

public class AdminMainMenu extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminMainMenu frame = new AdminMainMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AdminMainMenu() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\MINI-PROJECT\\LIBRARY ACCESS\\src\\com\\library\\img\\icon.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1920, 1080);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(187, 186, 239));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton addlibrarianbtn = new JButton("Add Librarian");
		addlibrarianbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				AddLibrarianPage addlib = new AddLibrarianPage();
				addlib.setVisible(true);
			}
		});
		addlibrarianbtn.setFont(new Font("Times New Roman", Font.BOLD, 20));
		addlibrarianbtn.setBounds(541, 121, 195, 48);
		contentPane.add(addlibrarianbtn);
		
		JButton viewlibrarianbtn = new JButton("View Librarians");
		viewlibrarianbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				ViewLibrarian vl = new ViewLibrarian();
				vl.setVisible(true);
			}
		});
		viewlibrarianbtn.setFont(new Font("Times New Roman", Font.BOLD, 20));
		viewlibrarianbtn.setBounds(541, 240, 195, 48);
		contentPane.add(viewlibrarianbtn);
		
		JButton deletelibrarianbtn = new JButton("Delete Librarian");
		deletelibrarianbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				DeleteLibrarian dl = new DeleteLibrarian();
				dl.setVisible(true);
			}
		});
		deletelibrarianbtn.setFont(new Font("Times New Roman", Font.BOLD, 20));
		deletelibrarianbtn.setBounds(541, 346, 195, 48);
		contentPane.add(deletelibrarianbtn);
		
		JButton logoutbtn = new JButton("Logout");
		logoutbtn.setBackground(new Color(253, 181, 191));
		logoutbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				MainPageFirst mp = new MainPageFirst();
				mp.setVisible(true);
				dispose();
			}
		});
		logoutbtn.setFont(new Font("Times New Roman", Font.BOLD, 20));
		logoutbtn.setBounds(1063, 121, 125, 48);
		contentPane.add(logoutbtn);
	}

}
