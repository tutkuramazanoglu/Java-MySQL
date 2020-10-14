package controller;

import java.util.Scanner;

import com.cestar.dao.EmpDao;

public class UserMenuCreate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EmpDao obj =new EmpDao();
		Scanner keyboard=new Scanner(System.in);
		int choice=0;
		while(choice!=5)
		{
			System.out.println("Please enter a number between 1 to five");
			System.out.println("1-Dsplay,2- insert,3-update-,4Delete,5-exit");
			choice=keyboard.nextInt();
			switch(choice) {
			case 1:
				obj.display();
				break;
			case 2:
				obj.insert();
				break;
			case 3: 
				obj.update();
				break;
			case 4: 
				obj.delete();
				break;
			case 5:
				
				System.exit(0);
			default:
				System.out.println("please enter valid number");
				
			}
				
		}

	}

}
