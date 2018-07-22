/*
 * Created by JFormDesigner on Tue Jun 26 02:20:47 CAT 2018
 */

package UI;

import DAO.*;
import Domain.*;
import org.hibernate.Session;

import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

/**
 * @author IRADUKUNDA Olivier
 */
public class SignUP extends JFrame {
	Thread thread;
	private GenericDAO dao=new GenericDAO();

	public SignUP() {
		initComponents();
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setBackground(Color.DARK_GRAY);

		thread=new Thread(()->{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			facultyComboBox.setModel(new DefaultComboBoxModel(dao.getAll(Faculty.class).toArray()));
			genderComboBox.setModel(new DefaultComboBoxModel(Gender.values()));

		});
		thread.start();
	}
	private void signUpButtonActionPerformed(ActionEvent e) {
		try {
			Student student=new Student();
			Credential credential=new Credential();
			credential.setPassword(passwordField.getText());
			Department department = (Department) departmentComboBox.getSelectedItem();
//			student.setId(Long.parseLong(idTextField.getText()));
			student.setFirstName(firstNameTextField.getText());
			student.setLastName(lastNameTextField.getText());
			student.setGender((Gender) genderComboBox.getSelectedItem());
			student.setDob(LocalDate.parse(dobTextField.getText(),DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			student.setDepartment(department);
			Serializable id=StudentDAO.register(student);
			credential.setStudent(student);
			dao.create(credential);
			JOptionPane.showMessageDialog(rootPane,  student+"Well saved!! "+"and your Id is: "+id);
			clear();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}

	private void clear() {
		passwordField.setText("");
		firstNameTextField.setText("");
		lastNameTextField.setText("");
		dobTextField.setText("");
		departmentComboBox.setModel(new DefaultComboBoxModel(Arrays.asList(new Department()).toArray()));
	}

	private void facultyComboBoxActionPerformed(ActionEvent e) {
		Faculty faculty = (Faculty) facultyComboBox.getSelectedItem();
		List<Department> list = faculty.getDepartments();
		departmentComboBox.setModel(new DefaultComboBoxModel(list.toArray()));
	}

	private void dobTextFieldFocusGained(FocusEvent e) {
		try {
			dobTextField.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("##/##/####")));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
	}

	private void loginButtonActionPerformed(ActionEvent e) {
		this.dispose();
		thread.stop();
		SwingUtilities.invokeLater(()->new Login().setVisible(true));
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		label2 = new JLabel();
		label3 = new JLabel();
		label4 = new JLabel();
		label5 = new JLabel();
		label6 = new JLabel();
		label7 = new JLabel();
		label8 = new JLabel();
		firstNameTextField = new JTextField();
		lastNameTextField = new JTextField();
		genderComboBox = new JComboBox();
		facultyComboBox = new JComboBox();
		departmentComboBox = new JComboBox();
		passwordField = new JPasswordField();
		dobTextField = new JFormattedTextField();
		signUpButton = new JButton();
		loginButton = new JButton();
		separator1 = new JSeparator();

		//======== this ========
		setTitle("STUDENT REGISTRATION");
		setBackground(Color.black);
		Container contentPane = getContentPane();

		//---- label2 ----
		label2.setText("FIRSTNAME");

		//---- label3 ----
		label3.setText("LASTNAME");

		//---- label4 ----
		label4.setText("GENDER");

		//---- label5 ----
		label5.setText("DOB");

		//---- label6 ----
		label6.setText("FACULTY");

		//---- label7 ----
		label7.setText("DEPARTMENT");

		//---- label8 ----
		label8.setText("PASSWORD");

		//---- facultyComboBox ----
		facultyComboBox.addActionListener(e -> facultyComboBoxActionPerformed(e));

		//---- dobTextField ----
		dobTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				dobTextFieldFocusGained(e);
			}
		});

		//---- signUpButton ----
		signUpButton.setText("SIGNUP");
		signUpButton.addActionListener(e -> signUpButtonActionPerformed(e));

		//---- loginButton ----
		loginButton.setText("LOGIN");
		loginButton.addActionListener(e -> {
			loginButtonActionPerformed(e);
			loginButtonActionPerformed(e);
		});

		GroupLayout contentPaneLayout = new GroupLayout(contentPane);
		contentPane.setLayout(contentPaneLayout);
		contentPaneLayout.setHorizontalGroup(
			contentPaneLayout.createParallelGroup()
				.addGroup(contentPaneLayout.createSequentialGroup()
					.addGroup(contentPaneLayout.createParallelGroup()
						.addGroup(contentPaneLayout.createSequentialGroup()
							.addGap(57, 57, 57)
							.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
								.addGroup(contentPaneLayout.createSequentialGroup()
									.addComponent(label8)
									.addGap(32, 32, 32)
									.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))
								.addGroup(contentPaneLayout.createSequentialGroup()
									.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
										.addGroup(GroupLayout.Alignment.LEADING, contentPaneLayout.createSequentialGroup()
											.addGap(2, 2, 2)
											.addComponent(label4)
											.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
											.addComponent(genderComboBox, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE))
										.addGroup(GroupLayout.Alignment.LEADING, contentPaneLayout.createSequentialGroup()
											.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
												.addComponent(label2)
												.addComponent(label3))
											.addGap(27, 27, 27)
											.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
												.addComponent(firstNameTextField, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
												.addComponent(lastNameTextField))))
									.addGroup(contentPaneLayout.createParallelGroup()
										.addGroup(contentPaneLayout.createSequentialGroup()
											.addGap(89, 89, 89)
											.addGroup(contentPaneLayout.createParallelGroup()
												.addComponent(label6)
												.addComponent(label5, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
											.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addGroup(contentPaneLayout.createParallelGroup()
												.addComponent(facultyComboBox, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
												.addComponent(dobTextField, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)))
										.addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
											.addGap(72, 72, 72)
											.addComponent(label7)
											.addGap(18, 18, 18)
											.addComponent(departmentComboBox, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE))))))
						.addGroup(contentPaneLayout.createSequentialGroup()
							.addGap(204, 204, 204)
							.addComponent(signUpButton, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
							.addGap(114, 114, 114)
							.addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
						.addGroup(contentPaneLayout.createSequentialGroup()
							.addGap(36, 36, 36)
							.addComponent(separator1, GroupLayout.PREFERRED_SIZE, 645, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(102, Short.MAX_VALUE))
		);
		contentPaneLayout.setVerticalGroup(
			contentPaneLayout.createParallelGroup()
				.addGroup(contentPaneLayout.createSequentialGroup()
					.addGap(46, 46, 46)
					.addGroup(contentPaneLayout.createParallelGroup()
						.addGroup(contentPaneLayout.createSequentialGroup()
							.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(label2, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
								.addComponent(firstNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(62, 62, 62)
							.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(label3, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
								.addComponent(lastNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(contentPaneLayout.createSequentialGroup()
							.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(label5, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
								.addComponent(dobTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(53, 53, 53)
							.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(label6, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
								.addComponent(facultyComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addGap(51, 51, 51)
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
						.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(label4, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
							.addComponent(genderComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(label7, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(departmentComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
					.addComponent(separator1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addGap(18, 18, 18)
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(label8, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(61, 61, 61)
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(signUpButton)
						.addComponent(loginButton))
					.addGap(45, 45, 45))
		);
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JLabel label5;
	private JLabel label6;
	private JLabel label7;
	private JLabel label8;
	private JTextField firstNameTextField;
	private JTextField lastNameTextField;
	private JComboBox genderComboBox;
	private JComboBox facultyComboBox;
	private JComboBox departmentComboBox;
	private JPasswordField passwordField;
	private JFormattedTextField dobTextField;
	private JButton signUpButton;
	private JButton loginButton;
	private JSeparator separator1;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
