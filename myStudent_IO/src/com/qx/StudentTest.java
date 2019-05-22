package com.qx;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * 这是我的学生管理系统的主类（对学生信息进行增删查改）
 * 
 * 步骤如下：
 * A:定义学生类
 * 
 * B:学生管理系统的主界面的代码编写
 * 
 * C:学生管理系统的查看所有学生的代码编写
 * 
 * D:学生管理系统的添加学生的代码编写
 * 
 * E:学生管理系统的删除学生的代码编写
 * 
 * F:学生管理系统的修改学生的代码编写
 */
public class StudentTest {
	public static void main(String[] args) throws IOException {
		
		//自定义文件路径（自己在当前项目的目录下新建一个空文本.txt格式；不建议使用绝对路径）       
		String fileName = "Student.txt"; 
		
		//为了让程序能够回到这里来，我们使用循环
		while (true) {
			//这是学生管理系统的主界面
			System.out.println("--------欢迎来到学生管理系统--------");
			System.out.println("1 查看所有学生");
			System.out.println("2 添加学生");
			System.out.println("3 删除学生");
			System.out.println("4 修改学生");
			System.out.println("5 退出");
			
			System.out.println("请输入你要操作的功能(1-5)：");			
			//创建键盘录入对象		
			Scanner sc = new Scanner(System.in);
			//输入后进行换行
			String choiceString = sc.nextLine();
			
			//用switch语句实现选择
			switch (choiceString) {
			case "1":
				//查看所有学生
				findAllStudent(fileName);
				break;
			case "2":
				//添加学生
				addStudent(fileName);
				break;
			case "3":
				//删除学生
				deleteStudent(fileName);
				break;
			case "4":
				//修改学生
				updateStudent(fileName);
				break;
			case "5":
			default:
				System.out.println("谢谢你的使用");
				//JVM退出程序
				System.exit(0);
				break;
			}
		}
	}
	
	//从文件中读取集合信息
	public static void readData(String fileName ,ArrayList<Student> array) throws IOException{
		
		//创建输入缓冲流对象
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		
		String line;
		//数组循环以,切割读取数据
		while((line=br.readLine())!=null){
			String[] datas =line.split(",");
			Student s =new Student();
			s.setId(datas[0]);
			s.setName(datas[1]);
			s.setAge(datas[2]);
			s.setAddress(datas[3]);
			array.add(s);
		}
		//释放资源
		br.close();		
	}
	
	//从集合中的数据写入文件
	public static void writeData(String fileName, ArrayList<Student> array) throws IOException {
		
		//创建输出缓冲流对象
		BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
		//循环遍历
		for (int x = 0; x < array.size(); x++) {			
			Student s = array.get(x);
			StringBuilder sb = new StringBuilder();
			//链式编程
			sb.append(s.getId()).append(",").append(s.getName()).append(",").append(s.getAge()).append(",").append(s.getAddress());			
			bw.write(sb.toString());
			bw.newLine(); //换行
			bw.flush(); //刷新
		}
		//释放资源
		bw.close();
	}
	
	//添加学生
	public static void addStudent(String fileName) throws IOException {
		
		//创建集合对象
		ArrayList<Student> array = new ArrayList<Student>();
		
		//从文件中把数据读取到集合中
		readData(fileName, array);

		//创建键盘录入对象
		Scanner sc = new Scanner(System.in);

		//为了让id能够被访问到，我们就把id定义在了循环的外面
		String id;

		//为了让代码能够回到这里，用循环
		while (true) {
			
			System.out.println("请输入学生学号：");			
			id = sc.nextLine();

			//判断学号有没有被人占用   //定义标记			
			boolean flag = false;
			
			//遍历集合，得到每一个学生
			for (int x = 0; x < array.size(); x++) {
				Student s = array.get(x);
				//获取该学生的学号，和键盘录入的学号进行比较
				if (s.getId().equals(id)) {
					flag = true; //说明学号被占用了
					break;
				}
			}

			if (flag) {
				System.out.println("你输入的学号已经被占用,请重新输入");
			} 
			else {
				break; //结束循环
			}
		}

		System.out.println("请输入学生姓名：");
		String name = sc.nextLine();
		System.out.println("请输入学生年龄：");
		String age = sc.nextLine();
		System.out.println("请输入学生居住地：");
		String address = sc.nextLine();

		//创建学生对象
		Student s = new Student();
		s.setId(id);
		s.setName(name);
		s.setAge(age);
		s.setAddress(address);

		//把学生对象作为元素添加到集合
		array.add(s);
		
		//把集合中的数据重新写回到文件
		writeData(fileName, array);

		//给出提示
		System.out.println("添加学生成功");
	}
	
	//查看所有学生
	public static void findAllStudent(String fileName) throws IOException {
		//创建集合对象
		ArrayList<Student> array = new ArrayList<Student>();
		
		//从文件中把数据读取到集合中
		readData(fileName, array);

		//首先来判断集合中是否有数据，如果没有数据，就给出提示，并让该方法不继续往下执行
		if (array.size() == 0) {
			System.out.println("不好意思,目前没有学生信息可供查询,请回去重新选择你的操作");
			return;
		}

		// \t 其实就是一个tab键的位置
		System.out.println("学号\t姓名\t年龄\t居住地");
		for (int x = 0; x < array.size(); x++) {
			Student s = array.get(x);
			System.out.println(s.getId() + "\t" + s.getName() + "\t" + s.getAge() + "\t" + s.getAddress());
		}
	}

	//修改学生
	public static void updateStudent(String fileName) throws IOException {
		
		//修改学生的思路：键盘录入一个学号，到集合中去查找，看是否有学生使用的是该学号，如果有就修改该学生
		
		//创建集合对象
		ArrayList<Student> array = new ArrayList<Student>();
		
		//从文件中把数据读取到集合中
		readData(fileName, array);
				
		//创建键盘录入对象
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入你要修改的学生的学号：");
		String id = sc.nextLine();

		//定义一个索引
		int index = -1;

		//遍历集合
		for (int x = 0; x < array.size(); x++) {
			//获取每一个学生对象
			Student s = array.get(x);
			//拿学生对象的学号和键盘录入的学号进行比较
			if (s.getId().equals(id)) {
				index = x;
				break;
			}
		}

		if (index == -1) {
			System.out.println("不好意思,你要修改的学号对应的学生信息不存在,请回去重新你的选择");
		} 
		else {
			System.out.println("请输入学生新姓名：");
			String name = sc.nextLine();
			System.out.println("请输入学生新年龄：");
			String age = sc.nextLine();
			System.out.println("请输入学生新居住地：");
			String address = sc.nextLine();

			//创建学生对象
			Student s = new Student();
			s.setId(id);
			s.setName(name);
			s.setAge(age);
			s.setAddress(address);

			//修改集合中的学生对象
			array.set(index, s);
			
			//把集合中的数据重新写回到文件			
			writeData(fileName, array);
			
			//给出提示
			System.out.println("修改学生成功");
		}
	}

	//删除学生
	public static void deleteStudent(String fileName) throws IOException {
		
		//创建集合对象
		ArrayList<Student> array = new ArrayList<Student>();
		
		//从文件中把数据读取到集合中
		readData(fileName, array);

		//删除学生的思路：键盘录入一个学号，到集合中去查找，看是否有学生使用的是该学号，如果有就删除该学生
		
		//创建键盘录入对象
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入你要删除的学生的学号：");
		String id = sc.nextLine();

		//我们必须给出学号不存在的时候的提示
		//定义一个索引
		int index = -1;

		//遍历集合
		for (int x = 0; x < array.size(); x++) {
			//获取到每一个学生对象
			Student s = array.get(x);
			//拿这个学生对象的学号和键盘录入的学号进行比较
			if (s.getId().equals(id)) {
				index = x;
				break;
			}
		}

		if (index == -1) {
			System.out.println("不好意思,你要删除的学号对应的学生信息不存在,请回去重新你的选择");
		} 
		else {
			array.remove(index);
			//把集合中的数据重新写回到文件
			writeData(fileName, array);
			System.out.println("删除学生成功");
		}

	}
	
}
	
	
	

