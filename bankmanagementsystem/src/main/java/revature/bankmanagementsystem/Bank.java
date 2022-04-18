package revature.bankmanagementsystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Bank
{
	public static void main(String args[]) //main class of bank
	        throws IOException
	    {
	 
	        BufferedReader sc = new BufferedReader(
	            new InputStreamReader(System.in));
	        String login_id = "";
	        String password = "";
	        String name = "";
	        String mob_no = "";
	        int acc_no,balance,emp_no;
	        int ch;
	        String emp_id = "";
        	String emp_pass = "";
	 
	        while (true) {
	            System.out.println(
	                "\n ->||    Welcome to Revature Bank    ||<- \n");
	            System.out.println("1)Create Customer Account");
	            System.out.println("2)Login Customer Account");
	            System.out.println("3)Employee Login");
	           
	            
	 
	            try {
	                System.out.print("\n    Enter Input:"); //user input
	                ch = Integer.parseInt(sc.readLine());
	 
	                switch (ch) {
	                case 1:
	                    try {
	                        System.out.print(
	                            "Enter Unique UserName:");
	                        login_id = sc.readLine();
	                        System.out.print(
	                            "Enter New Password:");
	                        password = sc.readLine();
	                        System.out.print(
		                            "Enter New name:");
	                        name = sc.readLine();
	                        System.out.print(
		                            "Enter mobile number:");
	                        mob_no = sc.readLine();
	                        System.out.print(
		                            "Enter account  number:");
	                        acc_no = Integer.parseInt(
	                                sc.readLine());
	                        
	                        System.out.print(
		                            "Enter Initial  balance amt:");
	                        balance = Integer.parseInt(
	                                sc.readLine());	
	                        
	 
	                        if (Bankmanagement.createAccount(name,
	                                login_id, password,mob_no, acc_no, balance)) {
	                            System.out.println(
	                                "MSG : Account Created Successfully!\n");
	                        }
	                        else {
	                            System.out.println(
	                                "ERR : Account Creation Failed!\n");
	                        }
	                    }
	                    catch (Exception e) {
	                        System.out.println(
	                            " ERR : Enter Valid Data::Insertion Failed!\n");
	                    }
	                    break;
	 
	                case 2:
	                    try {

	                        System.out.print(
	                            "Enter  Login ID:");
	                        login_id = sc.readLine();
	                        System.out.print(
	                            "Enter  Password:");
	                        password = sc.readLine();
	                        System.out.print(
		                            "Enter  account number:");
	                        acc_no = Integer.parseInt(
	                                sc.readLine());	
	                        
	 
	                        if (login_id != "" || password != "") {
	                            System.out.println(
	                                "MSG : Logged in  Successfully!\n");
	                            Bankmanagement.loginAccount(login_id, password,acc_no);
	                            
	                        }
	                        else {
	                            System.out.println(
	                                "ERR : login Failed!\n");
	                        }
	                    }
	                    catch (Exception e) {
	                        System.out.println(
	                            " ERR : Enter Valid Data::Login Failed!\n");
	                    }
	 
	                    break;
	                case 3:
	                	
	                	System.out.print(
	                            "Enter employee Login ID:");
	                        emp_id = sc.readLine();
	                        System.out.print(
	                            "Enter  Password:");
	                        emp_pass = sc.readLine();
	                        System.out.print(
		                            "Enter  employee  number:");
	                        emp_no = Integer.parseInt(
	                                sc.readLine());	
	                        if (emp_id != "" || emp_pass != "") {
	                            System.out.println(
	                                "MSG : Logged in  Successfully!\n");
	                            Bankmanagement.empLoginAccount(emp_id, emp_pass, emp_no);
	                            
	                        }
	                        else {
	                            System.out.println(
	                                "ERR : login Failed!\n");
	                        }
	                        break;
	                        
	                	
	                	
	 
	                default:
	                    System.out.println("Invalid Entry!\n");
	                }
	 
	                if (ch == 5) {
	                    System.out.println(
	                        "Exited Successfully!\n\n Thank You :)");
	                    break;
	                }
	            }
	            catch (Exception e) {
	                System.out.println("Enter Valid Entry!");
	            }
	        }
	        sc.close();
	    }
}
