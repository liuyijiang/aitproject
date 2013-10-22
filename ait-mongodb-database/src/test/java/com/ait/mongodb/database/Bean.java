package com.ait.mongodb.database;

import org.springframework.data.annotation.Id;

public class Bean {
  
	@Id
	private String id;
	private String name;
    private int age;
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	
	
}
