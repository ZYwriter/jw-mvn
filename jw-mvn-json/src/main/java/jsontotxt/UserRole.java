package jsontotxt;

import java.io.Serializable;

public class UserRole implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4425290314608626708L;
	private String name;
	private int age;

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

	@Override
	public String toString() {
		return "UserRole [name=" + name + ", age=" + age + "]";
	}
	
}
