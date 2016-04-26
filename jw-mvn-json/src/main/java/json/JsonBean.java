package json;

import java.util.Date;

public class JsonBean {

	private String name;
	private int age;
	private Date born;

	public JsonBean() {
		super();
	}

	public JsonBean(String name, int age, Date born) {
		super();
		this.name = name;
		this.age = age;
		this.born = born;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getBorn() {
		return born;
	}

	public void setBorn(Date born) {
		this.born = born;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((born == null) ? 0 : born.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JsonBean other = (JsonBean) obj;
		if (age != other.age)
			return false;
		if (born == null) {
			if (other.born != null)
				return false;
		} else if (!born.equals(other.born))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "JsonBean [name=" + name + ", age=" + age + ", born=" + born + "]";
	}

}
