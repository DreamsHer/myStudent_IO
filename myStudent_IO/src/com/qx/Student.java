package com.qx;
/*
 * 学生类
 */
public class Student {
	//学号
	private String id;
	//姓名
	private String name;
	//年龄
	private String age;
	//居住地
	private String address;
	
	//无参构造方法
	//代码区域右键 -- Source -- Generate Constructors from Superclass...
	public Student() {
		
	}
	
	//有参构造方法
	//代码区域右键 -- Source -- Generate Constructor using Fields....
	public Student(String id, String name, String age, String address) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.address = address;
	}

	//get()与set()方法
	//代码区域右键 -- Source -- Generate Getters and Setters.....
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
