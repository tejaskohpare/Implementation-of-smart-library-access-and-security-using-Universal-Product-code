package com.library;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.dao.student;
import com.dao.studentDao;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import com.toedter.calendar.JDateChooser;
import javax.swing.JPasswordField;

public class addStudentPage extends JFrame {

	private JPanel contentPane;
	private JTextField rollnumber;
	private JTextField firstname;
	private JTextField lastname;
	private JTextField departmentname;
	JLabel lblforphotoname;
	String path;
	String filename = "";
	File selectedFiles;
	private JPasswordField passwordforst;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addStudentPage frame = new addStudentPage();
//					frame.setUndecorated(true); 
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
	public addStudentPage() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setType(Type.UTILITY);
		setUndecorated(true);
		
		setBounds(330, 120, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 255, 0), 2));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ADD STUDENT TO LIBRARY DATABASE");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(0, 11, 600, 44);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Enter Student Roll Number :");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_1.setBounds(84, 58, 177, 34);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_6 = new JLabel("Password :");
		lblNewLabel_1_6.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_1_6.setBounds(84, 103, 177, 34);
		contentPane.add(lblNewLabel_1_6);
		
		JLabel lblNewLabel_1_1 = new JLabel("Enter Student First Name :");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_1_1.setBounds(84, 155, 177, 34);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Enter Student Last Name :");
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_1_2.setBounds(84, 205, 177, 34);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Enter Student Department Name :");
		lblNewLabel_1_3.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_1_3.setBounds(84, 258, 193, 34);
		contentPane.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Year of Study :");
		lblNewLabel_1_4.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_1_4.setBounds(84, 311, 177, 34);
		contentPane.add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_5 = new JLabel("Date of Birth :");
		lblNewLabel_1_5.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_1_5.setBounds(84, 352, 177, 34);
		contentPane.add(lblNewLabel_1_5);
		
		JDateChooser datechoser = new JDateChooser();
		datechoser.setBounds(289, 352, 208, 27);
		contentPane.add(datechoser);
		
		rollnumber = new JTextField();
		rollnumber.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e)
			{
				int position = rollnumber.getCaretPosition();
				rollnumber.setText(rollnumber.getText().toUpperCase());
				rollnumber.setCaretPosition(position);
			}
		});
		rollnumber.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		rollnumber.setBounds(289, 58, 208, 34);
		contentPane.add(rollnumber);
		rollnumber.setColumns(10);
		
		passwordforst = new JPasswordField();
		passwordforst.setBounds(289, 103, 208, 34);
		contentPane.add(passwordforst);
		
		firstname = new JTextField();
		firstname.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e)
			{
				int position = firstname.getCaretPosition();
				firstname.setText(firstname.getText().toUpperCase());
				firstname.setCaretPosition(position);
			}
		});
		firstname.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		firstname.setColumns(10);
		firstname.setBounds(289, 155, 208, 34);
		contentPane.add(firstname);
		
		lastname = new JTextField();
		lastname.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) 
			{
				int position = lastname.getCaretPosition();
				lastname.setText(lastname.getText().toUpperCase());
				lastname.setCaretPosition(position);
			}
		});
		lastname.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lastname.setColumns(10);
		lastname.setBounds(289, 205, 208, 34);
		contentPane.add(lastname);
		
		departmentname = new JTextField();
		departmentname.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) 
			{
				int position = departmentname.getCaretPosition();
				departmentname.setText(departmentname.getText().toUpperCase());
				departmentname.setCaretPosition(position);
			}
		});
		departmentname.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		departmentname.setColumns(10);
		departmentname.setBounds(287, 258, 208, 34);
		contentPane.add(departmentname);
		
		
		String[] year = {"select","1","2","3","4","5"};
		JComboBox yearofstudy = new JComboBox(year);
		yearofstudy.setBounds(289, 311, 208, 27);
		contentPane.add(yearofstudy);
		
		String[] dayOfBirthForCombobox = new String[32];
		for(int i=0;i<=31;i++)
		{
			dayOfBirthForCombobox[i] = String.valueOf(i);
		}
		
		String[] monthOfBirthForCombobox = new String[13];
		for(int i=0;i<=12;i++)
		{
			monthOfBirthForCombobox[i] = String.valueOf(i);
		}
		
		String[] yearOfBirthForCombobox = new String[55];
		int flag = 1970;
		yearOfBirthForCombobox[0] = "0000";
		for(int i=1;i<52;i++)
		{			
			yearOfBirthForCombobox[i] =  String.valueOf(flag);
			flag += 1;
		}
		

		JButton btnNewButton = new JButton("Add Student");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String rollNumber, firstName, lastName, departmentName, DOB, yearOfStudy;
				
				rollNumber = rollnumber.getText();
				firstName = firstname.getText();
				lastName = lastname.getText();
				departmentName = departmentname.getText();
				yearOfStudy = (String) yearofstudy.getSelectedItem();
				
				String password = new String(passwordforst.getPassword());

				Date date = datechoser.getDate();
				DOB = new SimpleDateFormat("yyyy-MM-dd").format(date);

	
				
				if(!(rollNumber.equals("") || firstName.equals("") || lastName.equals("") || 
						departmentName.equals("") || yearOfStudy.equals("select") || 
						DOB.equals("")))
				{
					if(!(filename.equals(""))|| (filename.endsWith(".jpg") || filename.endsWith(".JPG") || filename.endsWith(".png") ||filename.endsWith(".PNG")))
					{
						int yearOfStudynum = Integer.parseInt(yearOfStudy);
						path = selectedFiles.getAbsolutePath();
				
						student st = new student();
						studentDao sdao = new studentDao();
						st.setRollNumber(rollNumber);
						st.setPassword(password);
						st.setStudentFirstName(firstName);
						st.setStudentLastName(lastName);
						st.setDepartmentNumber(departmentName);
						st.setYearOfStudy(yearOfStudynum);
						st.setDateOfBirth(DOB);
						st.setPhotoPath(path);
						
						Boolean status = false;
						status = sdao.checkingRollNumberExistOrNot(rollNumber);
						
						if(!status)
						{
							Boolean statusOfAddStudent = false;
							statusOfAddStudent = sdao.addStudent(st);
							if(statusOfAddStudent)
							{
								JOptionPane.showMessageDialog(getParent(), "Student Data Add Successfully!");
								dispose();
							}
							else {
								JOptionPane.showMessageDialog(getParent(), "Something Went Wrong With DB!");
							}
						}
						else {
							JOptionPane.showMessageDialog(getParent(), "Roll Number Already Exist In database!");
							rollnumber.setText("");
						}
						
					}else {
						JOptionPane.showMessageDialog(rootPane,"please select png or jpg file ","Try again", 1 );
						
					}
						
				}
				else {
					JOptionPane.showMessageDialog(getParent(), "Please fill all the fields !");
				}
				
				
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnNewButton.setBounds(337, 455, 106, 34);
		contentPane.add(btnNewButton);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				dispose();
			}
		});
		btnClose.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnClose.setBounds(158, 455, 106, 34);
		contentPane.add(btnClose);
		
		JLabel lblNewLabel_1_5_1 = new JLabel("Select Photo :");
		lblNewLabel_1_5_1.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_1_5_1.setBounds(84, 398, 177, 34);
		contentPane.add(lblNewLabel_1_5_1);
		
		JButton btnforselectphoto = new JButton("select");
		btnforselectphoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				JFileChooser jfile = new JFileChooser();
				jfile.setCurrentDirectory(new File(System.getProperty("user.home")));
				
				FileNameExtensionFilter filter = new FileNameExtensionFilter("*.image", "jpg","png");
				jfile.addChoosableFileFilter(filter);
				
				int result = jfile.showSaveDialog(null);
//				System.out.println(":"+result);
				
				selectedFiles = jfile.getSelectedFile();
				filename = selectedFiles.getName();
				lblforphotoname.setText(filename);
//				System.out.println("Selected file : "+selectedFiles);
				
				
			}
		});
		btnforselectphoto.setBounds(289, 404, 106, 27);
		contentPane.add(btnforselectphoto);
		
		lblforphotoname = new JLabel("");
		lblforphotoname.setBounds(404, 408, 166, 24);
		contentPane.add(lblforphotoname);
		
		
		
		
		
		
	}
}
