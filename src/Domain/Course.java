package Domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.*;

@Entity
@Cacheable
public class Course implements Comparable{
	@Id
	private String code;
	private String name;
	private int credits;

	@ManyToMany(mappedBy = "courses")
	private Set<Student> students=new HashSet<>();

	@ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinTable(name = "course_department",
			joinColumns = @JoinColumn(name = "course_code"),
			inverseJoinColumns = @JoinColumn(name = "department_code"))
	private Set<Department>departments=new HashSet<>();

	public Set getDepartment() {
		return departments;
	}

	public void addDepartment(Department department) {
		this.departments.add(department);
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	@Override
	public String toString() {
		return name ;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Course course = (Course) o;
		return Objects.equals(code, course.code);
	}

	@Override
	public int hashCode() {

		return Objects.hash(code);
	}

	@Override
	public int compareTo(Object o) {
		return this.getCode().compareTo(((Course)o).getCode());
	}
}
