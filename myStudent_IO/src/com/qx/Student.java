package com.qx;
/*
 * ѧ����
 */
public class Student {
	//ѧ��
	private String id;
	//����
	private String name;
	//����
	private String age;
	//��ס��
	private String address;
	
	//�޲ι��췽��
	//���������Ҽ� -- Source -- Generate Constructors from Superclass...
	public Student() {
		
	}
	
	//�вι��췽��
	//���������Ҽ� -- Source -- Generate Constructor using Fields....
	public Student(String id, String name, String age, String address) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.address = address;
	}

	//get()��set()����
	//���������Ҽ� -- Source -- Generate Getters and Setters.....
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}	
	
}
