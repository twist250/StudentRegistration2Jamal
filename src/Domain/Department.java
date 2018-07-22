package Domain;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Cacheable
public class Department {
	@Id
	private String code;
	private String name;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "faculity_code")
	private Faculty faculty;

	@ManyToMany(mappedBy = "departments",fetch = FetchType.EAGER)
	private Set<Course> courses=new HashSet<>();

	public Department(String code, String name, Faculty faculty) {
		this.code = code;
		this.name = name;
		this.faculty = faculty;
	}

	public Department() {
	}

	public Set<Course> getCourses() {
		return courses;
	}

	public void addCourse(Course course) {
		this.courses.add(course);
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

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}
	@Override
	public String toString() {
		return name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Department that = (Department) o;
		return Objects.equals(code, that.code);
	}

	@Override
	public int hashCode() {

		return Objects.hash(code);
	}
}
