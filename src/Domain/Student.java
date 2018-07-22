package Domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
@Entity
@Cacheable
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "id_seq")
	@SequenceGenerator(name = "id_seq",sequenceName = "student_id_seq")
	private long id;
	private String firstName;
	private String lastName;

	@Enumerated(value = EnumType.STRING)
	private Gender gender;
	private LocalDate dob;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "department_code")
	private Department department;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "student_course",
				joinColumns = @JoinColumn(name = "student_id"),
			inverseJoinColumns =@JoinColumn(name = "course_code") )
	private Set<Course> courses=new HashSet<>();
	@OneToOne(mappedBy = "student")
	private Credential credential;

	public Credential getCredential() {
		return credential;
	}

	public void setCredential(Credential credential) {
		this.credential = credential;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	@Override
	public String toString() {
		return
		 firstName +" "+lastName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Student student = (Student) o;
		return Objects.equals(id, student.id);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id);
	}
}
