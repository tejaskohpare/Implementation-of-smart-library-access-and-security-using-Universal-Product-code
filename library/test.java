package com.library;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class test extends JFrame {

	private JPanel contentPane;
	private JTextField t1;
	private JTextField t2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test frame = new test();
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
	public test() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		t1 = new JTextField();
		t1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				Border border = new LineBorder(Color.RED, 1, true);
				t1.setBorder(border);
			}
			@Override
			public void focusLost(FocusEvent e) {
				Border border = new LineBorder(Color.ORANGE, 1, true);
				t1.setBorder(border);
			}
		});
		t1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				System.out.println("Key clicked");
				
			}
//			@Override
//			public void mouseReleased(MouseEvent e) 
//			{
//				System.out.println("Mouse Release");
//			}
//			@Override
//			public void mouseExited(MouseEvent e) {
//				System.out.println("Mouse Exited");
//			}
//			@Override
//			public void mousePressed(MouseEvent e) {
//				System.out.println("MousePrease");
//			}
//			@Override
//			public void mouseEntered(MouseEvent e) {
//				System.out.println("Mouse Entered");
//			}
		});
		t1.setBounds(86, 94, 258, 45);
		contentPane.add(t1);
		t1.setColumns(10);
		
		t2 = new JTextField();
		t2.setText("");
		t2.setBounds(86, 216, 258, 52);
		contentPane.add(t2);
		t2.setColumns(10);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(129, 330, 124, 45);
		contentPane.add(btnNewButton);
	}
}
