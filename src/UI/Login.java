/*
 * Created by JFormDesigner on Tue Jun 26 02:20:31 CAT 2018
 */

package UI;

import DAO.GenericDAO;
import Domain.Credential;
import Domain.Student;


import java.awt.*;
import java.awt.event.*;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author IRADUKUNDA Olivier
 */
public class Login extends JFrame {
	private static GenericDAO dao=new GenericDAO();
	private static List<Student> studentList=new ArrayList<>();
	private static Thread thread1;
	private static Thread thread;
	private List<Credential> credentials=new ArrayList<>();

	public Login() {
		initComponents();
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}


	public static void main(String[] args) {
		thread1=new Thread(()->studentList = dao.getAll(Student.class));
		thread1.start();
		thread=new Thread(()->new Login().setVisible(true));
		thread.run();
	}

	private void loginButtonActionPerformed(ActionEvent e) {
		try {
			Student student = new Student();
			student.setId(Long.parseLong(idTextField.getText()));
			Student student1 =studentList.get(studentList.indexOf(student));


			if(student1.getCredential().getPassword().equals(passwordField.getText())){
				JOptionPane.showMessageDialog(rootPane, "Welcome!!!");
				StudentReg student_reg = new StudentReg(student1);
				student_reg.setVisible(true);
				this.dispose();
			}else{
				JOptionPane.showMessageDialog(rootPane, "Wrong password!!!");
			}
		} catch (ArrayIndexOutOfBoundsException e1) {
			JOptionPane.showMessageDialog(rootPane, "Wrong Student Id!!");
		}catch (Exception e1){
			JOptionPane.showMessageDialog(rootPane, e1.getMessage());
		}
	}

	private void signUpLabelMouseClicked(MouseEvent e) {
			this.dispose();
			thread.stop();
			thread1.stop();
			SwingUtilities.invokeLater(()->new SignUP().setVisible(true));
		}

	private void passwordFieldKeyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			try {
				Student student = new Student();
				student.setId(Long.parseLong(idTextField.getText()));
				Student student1 =studentList.get(studentList.indexOf(student));


				if(student1.getCredential().getPassword().equals(passwordField.getText())){
					JOptionPane.showMessageDialog(rootPane, "Welcome!!!");
					StudentReg student_reg = new StudentReg(student1);
					student_reg.setVisible(true);
					this.dispose();
				}else{
					JOptionPane.showMessageDialog(rootPane, "Wrong password!!!");
				}
			} catch (ArrayIndexOutOfBoundsException e1) {
				JOptionPane.showMessageDialog(rootPane, "Wrong Student Id!!");
			}catch (Exception e1){
				JOptionPane.showMessageDialog(rootPane, e1.getMessage());
			}
		}
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		label1 = new JLabel();
		label2 = new JLabel();
		idTextField = new JTextField();
		loginButton = new JButton();
		signUpLabel = new JLabel();
		passwordField = new JPasswordField();

		//======== this ========
		setTitle("STUDENT REGISTRATION");
		Container contentPane = getContentPane();

		//---- label1 ----
		label1.setText("ID");

		//---- label2 ----
		label2.setText("PASSWORD");

		//---- loginButton ----
		loginButton.setText("LOGIN");
		loginButton.addActionListener(e -> loginButtonActionPerformed(e));

		//---- signUpLabel ----
		signUpLabel.setText("SignUp");
		signUpLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				signUpLabelMouseClicked(e);
			}
		});

		//---- passwordField ----
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				passwordFieldKeyPressed(e);
			}
		});

		GroupLayout contentPaneLayout = new GroupLayout(contentPane);
		contentPane.setLayout(contentPaneLayout);
		contentPaneLayout.setHorizontalGroup(
			contentPaneLayout.createParallelGroup()
				.addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
					.addGap(0, 422, Short.MAX_VALUE)
					.addComponent(signUpLabel, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE))
				.addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
					.addContainerGap(102, Short.MAX_VALUE)
					.addGroup(contentPaneLayout.createParallelGroup()
						.addComponent(label1, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
						.addComponent(label2, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))
					.addGap(42, 42, 42)
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
						.addComponent(idTextField, GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
						.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE))
					.addGap(74, 74, 74))
				.addGroup(contentPaneLayout.createSequentialGroup()
					.addGap(197, 197, 197)
					.addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(220, Short.MAX_VALUE))
		);
		contentPaneLayout.setVerticalGroup(
			contentPaneLayout.createParallelGroup()
				.addGroup(contentPaneLayout.createSequentialGroup()
					.addContainerGap(81, Short.MAX_VALUE)
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(label1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(idTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(39, 39, 39)
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(label2, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(46, 46, 46)
					.addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addGap(23, 23, 23)
					.addComponent(signUpLabel)
					.addContainerGap())
		);
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel label1;
	private JLabel label2;
	private JTextField idTextField;
	private JButton loginButton;
	private JLabel signUpLabel;
	private JPasswordField passwordField;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
