package com.qx;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * �����ҵ�ѧ������ϵͳ�����ࣨ��ѧ����Ϣ������ɾ��ģ�
 * 
 * �������£�
 * A:����ѧ����
 * 
 * B:ѧ������ϵͳ��������Ĵ����д
 * 
 * C:ѧ������ϵͳ�Ĳ鿴����ѧ���Ĵ����д
 * 
 * D:ѧ������ϵͳ�����ѧ���Ĵ����д
 * 
 * E:ѧ������ϵͳ��ɾ��ѧ���Ĵ����д
 * 
 * F:ѧ������ϵͳ���޸�ѧ���Ĵ����д
 */
public class StudentTest {
	public static void main(String[] args) throws IOException {
		
		//�Զ����ļ�·�����Լ��ڵ�ǰ��Ŀ��Ŀ¼���½�һ�����ı�.txt��ʽ��������ʹ�þ���·����       
		String fileName = "Student.txt"; 
		
		//Ϊ���ó����ܹ��ص�������������ʹ��ѭ��
		while (true) {
			//����ѧ������ϵͳ��������
			System.out.println("--------��ӭ����ѧ������ϵͳ--------");
			System.out.println("1 �鿴����ѧ��");
			System.out.println("2 ���ѧ��");
			System.out.println("3 ɾ��ѧ��");
			System.out.println("4 �޸�ѧ��");
			System.out.println("5 �˳�");
			
			System.out.println("��������Ҫ�����Ĺ���(1-5)��");			
			//��������¼�����		
			Scanner sc = new Scanner(System.in);
			//�������л���
			String choiceString = sc.nextLine();
			
			//��switch���ʵ��ѡ��
			switch (choiceString) {
			case "1":
				//�鿴����ѧ��
				findAllStudent(fileName);
				break;
			case "2":
				//���ѧ��
				addStudent(fileName);
				break;
			case "3":
				//ɾ��ѧ��
				deleteStudent(fileName);
				break;
			case "4":
				//�޸�ѧ��
				updateStudent(fileName);
				break;
			case "5":
			default:
				System.out.println("лл���ʹ��");
				//JVM�˳�����
				System.exit(0);
				break;
			}
		}
	}
	
	//���ļ��ж�ȡ������Ϣ
	public static void readData(String fileName ,ArrayList<Student> array) throws IOException{
		
		//�������뻺��������
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		
		String line;
		//����ѭ����,�и��ȡ����
		while((line=br.readLine())!=null){
			String[] datas =line.split(",");
			Student s =new Student();
			s.setId(datas[0]);
			s.setName(datas[1]);
			s.setAge(datas[2]);
			s.setAddress(datas[3]);
			array.add(s);
		}
		//�ͷ���Դ
		br.close();		
	}
	
	//�Ӽ����е�����д���ļ�
	public static void writeData(String fileName, ArrayList<Student> array) throws IOException {
		
		//�����������������
		BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
		//ѭ������
		for (int x = 0; x < array.size(); x++) {			
			Student s = array.get(x);
			StringBuilder sb = new StringBuilder();
			//��ʽ���
			sb.append(s.getId()).append(",").append(s.getName()).append(",").append(s.getAge()).append(",").append(s.getAddress());			
			bw.write(sb.toString());
			bw.newLine(); //����
			bw.flush(); //ˢ��
		}
		//�ͷ���Դ
		bw.close();
	}
	
	//���ѧ��
	public static void addStudent(String fileName) throws IOException {
		
		//�������϶���
		ArrayList<Student> array = new ArrayList<Student>();
		
		//���ļ��а����ݶ�ȡ��������
		readData(fileName, array);

		//��������¼�����
		Scanner sc = new Scanner(System.in);

		//Ϊ����id�ܹ������ʵ������ǾͰ�id��������ѭ��������
		String id;

		//Ϊ���ô����ܹ��ص������ѭ��
		while (true) {
			
			System.out.println("������ѧ��ѧ�ţ�");			
			id = sc.nextLine();

			//�ж�ѧ����û�б���ռ��   //������			
			boolean flag = false;
			
			//�������ϣ��õ�ÿһ��ѧ��
			for (int x = 0; x < array.size(); x++) {
				Student s = array.get(x);
				//��ȡ��ѧ����ѧ�ţ��ͼ���¼���ѧ�Ž��бȽ�
				if (s.getId().equals(id)) {
					flag = true; //˵��ѧ�ű�ռ����
					break;
				}
			}

			if (flag) {
				System.out.println("�������ѧ���Ѿ���ռ��,����������");
			} 
			else {
				break; //����ѭ��
			}
		}

		System.out.println("������ѧ��������");
		String name = sc.nextLine();
		System.out.println("������ѧ�����䣺");
		String age = sc.nextLine();
		System.out.println("������ѧ����ס�أ�");
		String address = sc.nextLine();

		//����ѧ������
		Student s = new Student();
		s.setId(id);
		s.setName(name);
		s.setAge(age);
		s.setAddress(address);

		//��ѧ��������ΪԪ����ӵ�����
		array.add(s);
		
		//�Ѽ����е���������д�ص��ļ�
		writeData(fileName, array);

		//������ʾ
		System.out.println("���ѧ���ɹ�");
	}
	
	//�鿴����ѧ��
	public static void findAllStudent(String fileName) throws IOException {
		//�������϶���
		ArrayList<Student> array = new ArrayList<Student>();
		
		//���ļ��а����ݶ�ȡ��������
		readData(fileName, array);

		//�������жϼ������Ƿ������ݣ����û�����ݣ��͸�����ʾ�����ø÷�������������ִ��
		if (array.size() == 0) {
			System.out.println("������˼,Ŀǰû��ѧ����Ϣ�ɹ���ѯ,���ȥ����ѡ����Ĳ���");
			return;
		}

		// \t ��ʵ����һ��tab����λ��
		System.out.println("ѧ��\t����\t����\t��ס��");
		for (int x = 0; x < array.size(); x++) {
			Student s = array.get(x);
			System.out.println(s.getId() + "\t" + s.getName() + "\t" + s.getAge() + "\t" + s.getAddress());
		}
	}

	//�޸�ѧ��
	public static void updateStudent(String fileName) throws IOException {
		
		//�޸�ѧ����˼·������¼��һ��ѧ�ţ���������ȥ���ң����Ƿ���ѧ��ʹ�õ��Ǹ�ѧ�ţ�����о��޸ĸ�ѧ��
		
		//�������϶���
		ArrayList<Student> array = new ArrayList<Student>();
		
		//���ļ��а����ݶ�ȡ��������
		readData(fileName, array);
				
		//��������¼�����
		Scanner sc = new Scanner(System.in);
		System.out.println("��������Ҫ�޸ĵ�ѧ����ѧ�ţ�");
		String id = sc.nextLine();

		//����һ������
		int index = -1;

		//��������
		for (int x = 0; x < array.size(); x++) {
			//��ȡÿһ��ѧ������
			Student s = array.get(x);
			//��ѧ�������ѧ�źͼ���¼���ѧ�Ž��бȽ�
			if (s.getId().equals(id)) {
				index = x;
				break;
			}
		}

		if (index == -1) {
			System.out.println("������˼,��Ҫ�޸ĵ�ѧ�Ŷ�Ӧ��ѧ����Ϣ������,���ȥ�������ѡ��");
		} 
		else {
			System.out.println("������ѧ����������");
			String name = sc.nextLine();
			System.out.println("������ѧ�������䣺");
			String age = sc.nextLine();
			System.out.println("������ѧ���¾�ס�أ�");
			String address = sc.nextLine();

			//����ѧ������
			Student s = new Student();
			s.setId(id);
			s.setName(name);
			s.setAge(age);
			s.setAddress(address);

			//�޸ļ����е�ѧ������
			array.set(index, s);
			
			//�Ѽ����е���������д�ص��ļ�			
			writeData(fileName, array);
			
			//������ʾ
			System.out.println("�޸�ѧ���ɹ�");
		}
	}

	//ɾ��ѧ��
	public static void deleteStudent(String fileName) throws IOException {
		
		//�������϶���
		ArrayList<Student> array = new ArrayList<Student>();
		
		//���ļ��а����ݶ�ȡ��������
		readData(fileName, array);

		//ɾ��ѧ����˼·������¼��һ��ѧ�ţ���������ȥ���ң����Ƿ���ѧ��ʹ�õ��Ǹ�ѧ�ţ�����о�ɾ����ѧ��
		
		//��������¼�����
		Scanner sc = new Scanner(System.in);
		System.out.println("��������Ҫɾ����ѧ����ѧ�ţ�");
		String id = sc.nextLine();

		//���Ǳ������ѧ�Ų����ڵ�ʱ�����ʾ
		//����һ������
		int index = -1;

		//��������
		for (int x = 0; x < array.size(); x++) {
			//��ȡ��ÿһ��ѧ������
			Student s = array.get(x);
			//�����ѧ�������ѧ�źͼ���¼���ѧ�Ž��бȽ�
			if (s.getId().equals(id)) {
				index = x;
				break;
			}
		}

		if (index == -1) {
			System.out.println("������˼,��Ҫɾ����ѧ�Ŷ�Ӧ��ѧ����Ϣ������,���ȥ�������ѡ��");
		} 
		else {
			array.remove(index);
			//�Ѽ����е���������д�ص��ļ�
			writeData(fileName, array);
			System.out.println("ɾ��ѧ���ɹ�");
		}

	}
	
}
	
	
	

