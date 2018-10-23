package com.connor.basic.bean;

public class Person {
	
	public Person(){};
	
	public Person(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
	}
	
	private String name;
	private Integer age;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Person){
			Person other = (Person)obj;
			if (other.getAge().equals(this.getAge()) && other.getName().equals(this.getName())){
				return true;
			}
		}
		return false;
	}
	@Override
	public int hashCode() {
		return name.hashCode()+age.hashCode();
	}
	
	
	
}
