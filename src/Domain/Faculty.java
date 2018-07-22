package Domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Cacheable
public class Faculty {
	@Id
	private String code;
	private String name;
	@OneToMany(mappedBy = "faculty",fetch = FetchType.EAGER)
	private List<Department> departments=new ArrayList<>();

	public Faculty(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public Faculty() {
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

	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	@Override
	public String toString() {
		return name;
	}
}
