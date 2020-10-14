package com.cestar.dao;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.cestar.model.Employee;

public class EmpDao {


		Scanner keyboard=new Scanner(System.in);
		public  Connection getConnection() {
			String url="jdbc:mysql://localhost:3306/cestar";
			String user="root";
			String password="tutkuutku";
			Connection con=null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection(url,user,password);
				System.out.print("Connected.");
			}
			catch(ClassNotFoundException|SQLException e){
				e.printStackTrace();
				
			}
			return con;
		}
		
		public List <Employee> display(){
			Connection con=getConnection();
			String sql="select*from employees";
			List <Employee> empEmpty=new ArrayList<Employee>();
			try {
				Statement stmt = con.createStatement(); //static Sql statement at runtime. cannot accept input.

				ResultSet rs = stmt.executeQuery(sql);

				while (rs.next()) {

					Employee emp = new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getString(5));

					empEmpty.add(emp);

				}
				System.out.println(empEmpty);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return empEmpty;
			}

		public void insert() {
			System.out.println("Please enter the id of Employee!");

			int id = keyboard.nextInt();
			keyboard.nextLine(); // just to consume space

			System.out.println("Please enter the name of Employee!");
			String name = keyboard.nextLine();

			System.out.println("Please enter the city of Employee!");
			String city = keyboard.nextLine();

			System.out.println("Please enter the department of Employee!");
			String deptarment = keyboard.nextLine();

			System.out.println("Please enter the email of Employee!");
			String email = keyboard.nextLine();
			
			Connection con=getConnection();
			String sql="insert into employees values(?,?,?,?,?)";
			
			try { 
				PreparedStatement prStat=con.prepareStatement(sql); //PreparedStatment accepts input in runtime(dynamic)
				prStat.setInt(1, id);
				prStat.setString(2, name);
				prStat.setString(3,city);
				prStat.setString(4, deptarment);
				prStat.setString(5, email);
				int status=prStat.executeUpdate();
				if(status>0) {
					System.out.println("Record interted seuccessfully!");
					display();
					con.close();
				}
				
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
	
		public void delete() {
			System.out.println("Please enter the id of Employee to delete record!:");
			int id=keyboard.nextInt();
			
			Connection con=getConnection();
			String sql="delete from employees where id=?";
			try {
				PreparedStatement prStat=con.prepareStatement(sql);
				prStat.setInt(1, id);
				int status=prStat.executeUpdate();
				if(status>0) {
					System.out.println("Record deleted successfully!");
					display();
					con.close();
					prStat.close();
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}

		public void update() {
			System.out.println("Please eneter the id of the Employee to be updated!");	
			int old_id = keyboard.nextInt();
			
			System.out.println("Please enter the new Id of the Employee");		
			int new_id = keyboard.nextInt();		
			keyboard.nextLine();
			
			System.out.println("Please enter the new name of the Employee!");		
			String n_name = keyboard.nextLine();
			
			System.out.println("Please enter the new city of the Employee!");		
			String n_city = keyboard.nextLine();
			
			System.out.println("Please enter the new department of Employee!");	
			String n_dept = keyboard.nextLine();
			
			System.out.println("Please enter the new Email of Employee!");	
			String n_email = keyboard.nextLine();
			
			Connection con=getConnection();
			String sql="update employees set id=?', name=?, city=?, department=?,email=? where id=?";
			try {
				
				PreparedStatement prStat=con.prepareStatement(sql);
				prStat.setInt(1, new_id);
				prStat.setString(2, n_name);
				prStat.setString(3, n_city);		
				prStat.setString(4, n_dept);
				prStat.setString(5, n_email);
				
				int status=prStat.executeUpdate();
				if(status>0) {
					System.out.println("Record updated successufuly!");
					display();
					con.close();
					prStat.close();
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
		
	



