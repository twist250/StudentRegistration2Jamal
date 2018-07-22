/*
 * Created by JFormDesigner on Tue Jun 26 02:21:37 CAT 2018
 */

package UI;

import java.awt.event.*;
import DAO.GenericDAO;
import Domain.Course;
import Domain.Department;
import Domain.Student;

import java.awt.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.table.DefaultTableModel;

/**
 * @author IRADUKUNDA Olivier
 */
public class StudentReg extends JFrame {

	//Initialization - Variables Declaration
	private static Student student;
	private GenericDAO dao=new GenericDAO();
	private Department department;
	private Set<Course> courseList;
	public Set<Course> selectedCourses;
	public int totalCredits=0;


	public StudentReg(Student s) {
		initComponents();
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		StudentReg.student=s;
		idLabel.setText(student.getId()+"");
		nameLabel.setText(student.getFirstName()+" "+student.getLastName());
		this.department = student.getDepartment();
		facultyLabel.setText(department.getFaculty().toString());
		departmentLabel.setText(department.toString());
		departmentCoursesLabel.setText(departmentCoursesLabel.getText()+" "+student.getDepartment());
		courseList=department.getCourses();
		selectedCourses = student.getCourses();
		selectedCourseslist.setListData(selectedCourses.toArray());
		if (!student.getCourses().isEmpty()){
			student.getCourses().forEach(c->totalCredits+=c.getCredits());
		}
		totalCreditsLabel.setText(totalCredits+"");
		view();
	}

	private void view() {
		String titles[] = {"COURSE_CODE","COURSE_NAME","CREDITS"};
		selectedCourses.forEach(c->courseList.remove(c));
		Object[][] data=new Object[courseList.size()][3];
		int i=0;
		for (Object o : courseList) {
			data[i][0] = ((Course)o).getCode();
			data[i][1] = ((Course)o).getName();
			data[i][2] = ((Course)o).getCredits();
			i++;
		}
		coursesTable.setModel(new DefaultTableModel(data,titles));
	}

	private void coursesTableMouseClicked(MouseEvent e) {
			int row =coursesTable.getSelectedRow();
			String code = (String) coursesTable.getValueAt(row, 0);
			Course course= (Course) dao.getOne(Course.class, code);
			totalCredits += course.getCredits();
		if (totalCredits<=18) {
			selectedCourses.add(course);
			courseList.remove(course);
			selectedCourseslist.setListData(selectedCourses.toArray());
			totalCreditsLabel.setText(totalCredits+"");
			view();
		} else {
			totalCredits -= course.getCredits();
			JOptionPane.showMessageDialog(rootPane, "Total Credits Exceeds 18!!");
		}
	}

	private void acceptAllMouseClicked(MouseEvent e) {
		student.setCourses(selectedCourses);
		dao.update(student);

	}

	private void removeLabelMouseClicked(MouseEvent e) {
		List<Course> list=selectedCourseslist.getSelectedValuesList();
		for (Course course1 : list) {
			selectedCourses.remove(course1);
			courseList.add(course1);
			totalCredits -= course1.getCredits();
			totalCreditsLabel.setText(totalCredits+"");
		}
		selectedCourseslist.setListData(selectedCourses.toArray());
		view();
	}

	private void label3MouseClicked(MouseEvent e) {
		new Login().setVisible(true);
		this.dispose();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		label1 = new JLabel();
		label2 = new JLabel();
		idLabel = new JLabel();
		scrollPane1 = new JScrollPane();
		selectedCourseslist = new JList();
		scrollPane2 = new JScrollPane();
		coursesTable = new JTable();
		label4 = new JLabel();
		label5 = new JLabel();
		nameLabel = new JLabel();
		facultyLabel = new JLabel();
		departmentLabel = new JLabel();
		label9 = new JLabel();
		totalCreditsLabel = new JLabel();
		removeLabel = new JLabel();
		acceptAll = new JLabel();
		label3 = new JLabel();
		departmentCoursesLabel = new JLabel();
		selectedLabel = new JLabel();

		//======== this ========
		setTitle("STUDENT REGISTRATION");
		Container contentPane = getContentPane();

		//---- label1 ----
		label1.setText("ID");

		//---- label2 ----
		label2.setText("NAMES");

		//---- idLabel ----
		idLabel.setText("text");
		idLabel.setEnabled(false);
		idLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));

		//======== scrollPane1 ========
		{

			//---- selectedCourseslist ----
			selectedCourseslist.setInheritsPopupMenu(true);
			scrollPane1.setViewportView(selectedCourseslist);
		}

		//======== scrollPane2 ========
		{

			//---- coursesTable ----
			coursesTable.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					coursesTableMouseClicked(e);
				}
			});
			scrollPane2.setViewportView(coursesTable);
		}

		//---- label4 ----
		label4.setText("FACULTY");

		//---- label5 ----
		label5.setText("DEPARTMENT");

		//---- nameLabel ----
		nameLabel.setText("text");
		nameLabel.setEnabled(false);
		nameLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));

		//---- facultyLabel ----
		facultyLabel.setText("text");
		facultyLabel.setEnabled(false);
		facultyLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));

		//---- departmentLabel ----
		departmentLabel.setText("text");
		departmentLabel.setEnabled(false);
		departmentLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));

		//---- label9 ----
		label9.setText("Total Credits");

		//---- totalCreditsLabel ----
		totalCreditsLabel.setText("text");
		totalCreditsLabel.setEnabled(false);

		//---- removeLabel ----
		removeLabel.setText("Remove");
		removeLabel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 12));
		removeLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				removeLabelMouseClicked(e);
			}
		});

		//---- acceptAll ----
		acceptAll.setText("Accept All");
		acceptAll.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 12));
		acceptAll.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				acceptAllMouseClicked(e);
			}
		});

		//---- label3 ----
		label3.setText("logOut");
		label3.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 12));
		label3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				label3MouseClicked(e);
			}
		});

		//---- departmentCoursesLabel ----
		departmentCoursesLabel.setText("COURSES IN ");
		departmentCoursesLabel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 14));

		//---- selectedLabel ----
		selectedLabel.setText("SELECTED COURSES:");
		selectedLabel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 14));

		GroupLayout contentPaneLayout = new GroupLayout(contentPane);
		contentPane.setLayout(contentPaneLayout);
		contentPaneLayout.setHorizontalGroup(
			contentPaneLayout.createParallelGroup()
				.addGroup(contentPaneLayout.createSequentialGroup()
					.addGroup(contentPaneLayout.createParallelGroup()
						.addGroup(contentPaneLayout.createSequentialGroup()
							.addGap(23, 23, 23)
							.addComponent(label9, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(totalCreditsLabel, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
						.addGroup(contentPaneLayout.createSequentialGroup()
							.addGap(17, 17, 17)
							.addGroup(contentPaneLayout.createParallelGroup()
								.addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
								.addComponent(selectedLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
							.addGroup(contentPaneLayout.createParallelGroup()
								.addComponent(removeLabel, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
								.addComponent(acceptAll, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(697, Short.MAX_VALUE))
				.addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
						.addGroup(contentPaneLayout.createSequentialGroup()
							.addContainerGap(908, Short.MAX_VALUE)
							.addComponent(label3, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))
						.addGroup(contentPaneLayout.createSequentialGroup()
							.addGap(32, 32, 32)
							.addGroup(contentPaneLayout.createParallelGroup()
								.addGroup(contentPaneLayout.createSequentialGroup()
									.addComponent(label1, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
									.addGap(41, 41, 41)
									.addComponent(idLabel, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE))
								.addGroup(contentPaneLayout.createSequentialGroup()
									.addGroup(contentPaneLayout.createParallelGroup()
										.addGroup(contentPaneLayout.createParallelGroup()
											.addComponent(label2, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
											.addComponent(label4, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE))
										.addComponent(label5))
									.addGap(26, 26, 26)
									.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
										.addComponent(departmentLabel, GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
										.addComponent(facultyLabel, GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
										.addComponent(nameLabel, GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE))))
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
							.addGroup(contentPaneLayout.createParallelGroup()
								.addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 584, GroupLayout.PREFERRED_SIZE)
								.addComponent(departmentCoursesLabel, GroupLayout.PREFERRED_SIZE, 556, GroupLayout.PREFERRED_SIZE))))
					.addGap(19, 19, 19))
		);
		contentPaneLayout.setVerticalGroup(
			contentPaneLayout.createParallelGroup()
				.addGroup(contentPaneLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(departmentCoursesLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addGroup(contentPaneLayout.createParallelGroup()
						.addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 296, GroupLayout.PREFERRED_SIZE)
						.addGroup(contentPaneLayout.createSequentialGroup()
							.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(label1)
								.addComponent(idLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
							.addGap(22, 22, 22)
							.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(label2)
								.addComponent(nameLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
							.addGap(26, 26, 26)
							.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(label4)
								.addComponent(facultyLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
							.addGap(30, 30, 30)
							.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(label5)
								.addComponent(departmentLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
							.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
								.addGroup(contentPaneLayout.createSequentialGroup()
									.addGap(29, 29, 29)
									.addComponent(selectedLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(removeLabel, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
									.addGap(18, 18, 18)
									.addComponent(acceptAll, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
									.addGap(45, 45, 45)))))
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(label9, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(totalCreditsLabel))
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
					.addComponent(label3)
					.addContainerGap())
		);
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel label1;
	private JLabel label2;
	private JLabel idLabel;
	private JScrollPane scrollPane1;
	private JList selectedCourseslist;
	private JScrollPane scrollPane2;
	private JTable coursesTable;
	private JLabel label4;
	private JLabel label5;
	private JLabel nameLabel;
	private JLabel facultyLabel;
	private JLabel departmentLabel;
	private JLabel label9;
	private JLabel totalCreditsLabel;
	private JLabel removeLabel;
	private JLabel acceptAll;
	private JLabel label3;
	private JLabel departmentCoursesLabel;
	private JLabel selectedLabel;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
