/*
 * Created by JFormDesigner on Mon Jun 25 15:18:48 CAT 2018
 */

package UI;

import DAO.GenericDAO;
import DAO.HibernatUtil;
import Domain.Course;
import Domain.Department;
import Domain.Faculty;
import org.hibernate.Session;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author IRADUKUNDA Olivier
 */
public class faculty extends JFrame {
	private GenericDAO dao=new GenericDAO();
	private JComboBox departmentComboBox;
	private JTextField c_codeTextField;
	private JTextField c_nameTextField;
	private JButton c_saveButton;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JTextField c_creditsTextField;
	public faculty() {
		initComponents();
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//		HibernatUtil.getSession();
//		HibernatUtil.shutDown();
		/*dao.create(new Faculty("IT", "INFORMATION TECHNOLOGY"));
		dao.create(new Faculty("BA", "BUSINESS ADMINISTRATION"));
		dao.create(new Faculty("THEO", "THEOLOGY"));
		dao.create(new Department("NET", "NETWORKING AND COMMUNICATION SYS", (Faculty) dao.getOne(Faculty.class, "IT")));
		dao.create(new Department("SO", "SOFTWARE ENGINEERING", (Faculty) dao.getOne(Faculty.class, "IT")));
		dao.create(new Department("INFO", "INFO MANAGEMENT", (Faculty) dao.getOne(Faculty.class, "IT")));
		dao.create(new Department("FI", "FINANCE", (Faculty) dao.getOne(Faculty.class, "BA")));
		dao.create(new Department("MARK", "MARKETING", (Faculty) dao.getOne(Faculty.class, "BA")));
		dao.create(new Department("NEW", "NEW TESTAMENT", (Faculty) dao.getOne(Faculty.class, "THEO")));
		dao.create(new Department("OLD", "OLD TESTAMENT", (Faculty) dao.getOne(Faculty.class, "THEO")));
		departmentComboBox.setModel(new DefaultComboBoxModel(dao.getAll(Department.class).toArray()));*/
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(()->new faculty().setVisible(true));
	}

	private void c_saveButtonActionPerformed(ActionEvent e)  {
		Course course=new Course();
		course.setCode(c_codeTextField.getText());
		course.setName(c_nameTextField.getText());
		course.setCredits(Integer.parseInt(c_creditsTextField.getText()));
		course.addDepartment((Department) departmentComboBox.getSelectedItem());
		Course course1= (Course) dao.getOne(Course.class, c_codeTextField.getText());
		Department department = (Department) dao.getOne(Department.class, ((Department) departmentComboBox.
				getSelectedItem()).getCode());
		if (course1 != null) {
		department.addCourse(course1);
		course1.addDepartment(department);
		dao.update(course1);
		}else {
			course.addDepartment(department);
			dao.create(course);
		}


	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		departmentComboBox = new JComboBox();
		c_codeTextField = new JTextField();
		c_nameTextField = new JTextField();
		c_saveButton = new JButton();
		label1 = new JLabel();
		label2 = new JLabel();
		label3 = new JLabel();
		c_creditsTextField = new JTextField();

		//======== this ========
		Container contentPane = getContentPane();

		//---- c_saveButton ----
		c_saveButton.setText("save department");
		c_saveButton.addActionListener(e -> c_saveButtonActionPerformed(e));

		//---- label1 ----
		label1.setText("CODE");

		//---- label2 ----
		label2.setText("NAME");

		//---- label3 ----
		label3.setText("CREDITS");

		GroupLayout contentPaneLayout = new GroupLayout(contentPane);
		contentPane.setLayout(contentPaneLayout);
		contentPaneLayout.setHorizontalGroup(
			contentPaneLayout.createParallelGroup()
				.addGroup(contentPaneLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(contentPaneLayout.createParallelGroup()
						.addGroup(contentPaneLayout.createSequentialGroup()
							.addGroup(contentPaneLayout.createParallelGroup()
								.addComponent(label1, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
								.addComponent(label2, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))
							.addGap(60, 60, 60)
							.addGroup(contentPaneLayout.createParallelGroup()
								.addComponent(c_saveButton)
								.addGroup(contentPaneLayout.createParallelGroup()
									.addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
										.addComponent(departmentComboBox)
										.addComponent(c_codeTextField, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
									.addComponent(c_nameTextField, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
									.addComponent(c_creditsTextField, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))))
						.addComponent(label3, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(422, Short.MAX_VALUE))
		);
		contentPaneLayout.setVerticalGroup(
			contentPaneLayout.createParallelGroup()
				.addGroup(contentPaneLayout.createSequentialGroup()
					.addGap(28, 28, 28)
					.addComponent(departmentComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18, 18, 18)
					.addGroup(contentPaneLayout.createParallelGroup()
						.addGroup(contentPaneLayout.createSequentialGroup()
							.addComponent(c_codeTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(61, 61, 61)
							.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(c_nameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label2, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)))
						.addComponent(label1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addGap(45, 45, 45)
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(label3, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(c_creditsTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(46, 46, 46)
					.addComponent(c_saveButton)
					.addContainerGap(250, Short.MAX_VALUE))
		);
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
