package revature.bankmanagementsystem;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;



 
public class Bankmanagement { // these class provides all
                              // bank method
 
	private static final int NULL = 0;
	 
    static Connection con = connection.createDBConnection();
    static String sql = "";
    public static boolean
    createAccount(String name, String
            login_id, String password,String mob_no, int acc_no, int balance) // create account function
    {
        try {
            // validation
            if (login_id == "" || password == "") {
                System.out.println("All Field Required!");
                return false;
            }
            // query
            Statement st = con.createStatement();
            sql = "insert into customer values('"+name+"','" + login_id + "','" + password + "','"  + mob_no + "','"+ acc_no + "','"  + balance + "')";
 
            // Execution
            if (st.executeUpdate(sql) == 1) {
                System.out.println(login_id
                                   + ", Now You Login!");
                return true;
            }
            // return
        }
        catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Username Not Available!");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public static void
    loginAccount(String login_id, String password, int acc_no) // login method
    {
    	 try {
    		 
    		 
    		 BufferedReader sc = new BufferedReader(
                   new InputStreamReader(System.in));
             // query
             sql = "select * from customer where acc_no="
                   + acc_no;
             PreparedStatement st
                 = con.prepareStatement(sql);
  
             ResultSet rs = st.executeQuery();
             System.out.println(
                 "-----------------------------------------------------------");
             System.out.printf("%12s %10s %10s\n",
                               "Account No", "Name",
                               "Login_id");
  
             // Execution
             int senderAc = acc_no;
  
             while (rs.next()) {
                 System.out.printf("%12d %10s %10s\n",
                                   rs.getInt("acc_no"),
                                   rs.getString("name"),
                                   rs.getString("login_id"));
             }
             System.out.println(
                 "-----------------------------------------------------------\n");
             while (true) {
             System.out.println(
                   "1)Transfer Money");
               System.out.println("2)View Balance");
               System.out.println("3)Deposit amount");
               System.out.println("4)Withdraw amount");
               
               System.out.println("5)LogOut");

               System.out.print("Enter Choice:");
               int ch = Integer.parseInt(
                   sc.readLine());
               if(ch == 1) {
            	   System.out.print(
                         "Enter Receiver  A/c No:");
                     int receiveAc = Integer.parseInt(
                         sc.readLine());
                     System.out.print(
                         "Enter Amount:");
                     int amt = Integer.parseInt(
                         sc.readLine());

                     if (Bankmanagement
                             .transferMoney(
                                 senderAc, receiveAc,
                                 amt)) {
                         System.out.println(
                             "MSG : Money Sent Successfully!\n");
                     }
                     else {
                         System.out.println(
                             "ERR :  Failed!\n");
                     }
            	   
               }
               else if (ch == 2) {
            	   
           
            	     Bankmanagement.getBalance(senderAc);
            	           }
               else if (ch == 3) {
            	   System.out.print(
                           "Enter Amount to Deposit:");
                       int amount = Integer.parseInt(
                           sc.readLine());
                   
          	     Bankmanagement.deposit(amount);
          	           }
               else if (ch == 4) {
            	   System.out.print(
                           "Enter Amount to Withdraw:");
                       int amount = Integer.parseInt(
                           sc.readLine());
                   
          	     Bankmanagement.withdraw(amount);
          	           }
               
               
               else if (ch == 5) {
                 break;
             }
             else {
                 System.out.println(
                     "Err : Enter Valid input!\n");
             }
             }   
         }
         catch (Exception e) {
             e.printStackTrace();
         }
    }
    
    public static void empLoginAccount(String emp_id, String emp_pass, int emp_no) {
    	try {
    	BufferedReader sc = new BufferedReader(
                new InputStreamReader(System.in));
    	sql = "select * from employee  where emp_no="
                + emp_no;
          PreparedStatement st
              = con.prepareStatement(sql);

          ResultSet rs = st.executeQuery();
          System.out.println(
              "-----------------------------------------------------------");
          System.out.printf("%12s %10s %10s\n",
                            "employee name", "employee id",
                            "employee mob_num");

          // Execution
    

          while (rs.next()) {
              System.out.printf("%12s %10s %10s\n",
                                rs.getString("emp_name"),
                                rs.getString("emp_id"),
                                rs.getString("emp_mob"));
          }
          System.out.println(
              "-----------------------------------------------------------\n");
    	
    	 while (true) {
             System.out.println(
                   "1)Approve new customer accounts");
               System.out.println("2)View customer details");
               System.out.println("3)View All transaction details");
               System.out.println("5)LogOut");

               System.out.print("Enter Choice:");
               int ch = Integer.parseInt(
                   sc.readLine());
               if(ch == 1) {
//            	   
                     PreparedStatement stt
                         = con.prepareStatement("SELECT * FROM customer;");
          
                     ResultSet rss = stt.executeQuery();
                     System.out.println(
                             "-----------------------------------------------------------");
                         
              
                         while (rss.next()) {
                        	 String id = rss.getString("login_id");
                             String  name = rss.getString("name");
                             String password = rss.getString("password");
                             String  mob_no = rss.getString("mob_no");
                             int balance = rss.getInt("balance");
                             int acc_no = rss.getInt("acc_no");
                             String status = rss.getString("status");
                             System.out.println( "ID = " + id );
                             System.out.println( "NAME = " + name );
                             System.out.println( "PASSWORD = " + password );
                             System.out.println( "MOBILE NUMBER = " + mob_no );
                             System.out.println( "BALANCE = " + balance );
                             System.out.println("ACCOUNT NUMBER = " + acc_no );
                             System.out.println("ACCOUNT STATUS = " + status );	
                             System.out.println();
                         }
                         System.out.println(
                             "-----------------------------------------------------------\n");
                         System.out.println("Enter the account number of customer to be approve: ");
                         int new_accno = Integer.parseInt(
	                                sc.readLine());	
                         String t="Update customer set status='active' where acc_no="+new_accno;
                         Statement stmt = con.createStatement();
                         stmt.executeUpdate(t);
                         System.out.println("Record Updated Successfully");
                         

                         
         
                         
                         
                         
                         
            	   
               }
               else if (ch == 2) {
            	   PreparedStatement stt
                   = con.prepareStatement("SELECT * FROM customer;");
    
               ResultSet rss = stt.executeQuery();
               System.out.println(
                       "-----------------------------------------------------------");
                   
        
                   while (rss.next()) {
                  	 String id = rss.getString("login_id");
                       String  name = rss.getString("name");
                       String password = rss.getString("password");
                       String  mob_no = rss.getString("mob_no");
                       int balance = rss.getInt("balance");
                       int acc_no = rss.getInt("acc_no");
                       String status = rss.getString("status");
                       System.out.println( "ID = " + id );
                       System.out.println( "NAME = " + name );
                       System.out.println( "PASSWORD = " + password );
                       System.out.println( "MOBILE NUMBER = " + mob_no );
                       System.out.println( "BALANCE = " + balance );
                       System.out.println("ACCOUNT NUMBER = " + acc_no );
                       System.out.println("ACCOUNT STATUS = " + status );	
                       System.out.println();
                   }
                   System.out.println(
                       "-----------------------------------------------------------\n");
            	           }
               else if (ch == 3) {
            	   PreparedStatement stt
                   = con.prepareStatement("SELECT * FROM transaction;");
    
               ResultSet rss = stt.executeQuery();
               System.out.println(
                       "-----------------------------------------------------------");
                   
        
                   while (rss.next()) {
                  	 int s_acc = rss.getInt("sender_acc");
                       int  r_acc = rss.getInt("receiver_acc");
                       int amount = rss.getInt("amount_transfer");
                       
                       System.out.println( "Sender's account number = " + s_acc );
                       System.out.println( "recevier's account number = " + r_acc );
                       System.out.println( "Amount transferred = " + amount );
                       	
                       System.out.println();
                   }
                   System.out.println(
                       "-----------------------------------------------------------\n");
               
               }
               
               else if (ch == 5) {
                 break;
             }
             else {
                 System.out.println(
                     "Err : Enter Valid input!\n");
             }
    	 }    
    	}
    	 catch (Exception e) {
             e.printStackTrace();
         }
}
    
     
    
    public static void  getBalance(int acc_no) // fetch balance method
    {
        try {
 
            // query
            sql = "select * from customer where acc_no="
                  + acc_no;
            PreparedStatement st
                = con.prepareStatement(sql);
 
            ResultSet rs = st.executeQuery();
            System.out.println(
                "-----------------------------------------------------------");
            System.out.printf("%12s %10s %10s\n",
                              "Account No", "Name",
                              "Balance");
 
            // Execution
 
            while (rs.next()) {
                System.out.printf("%12d %10s %10d.00\n",
                                  rs.getInt("acc_no"),
                                  rs.getString("name"),
                                  rs.getInt("balance"));
            }
            System.out.println(
                "-----------------------------------------------------------\n");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void deposit(int amount) {
    	
    	try {
    		BufferedReader sc = new BufferedReader(
                    new InputStreamReader(System.in));
        	 System.out.print(
                     "Enter account number:");
        	 int acc_no = Integer.parseInt(
                     sc.readLine());
        	 Statement st = con.createStatement();
			 sql = "update customer set balance=balance+"
	                  + amount + " where acc_no=" + acc_no;
			 if (st.executeUpdate(sql) == 1) {
	                System.out.println("Amount Debited!");
	            }
		     
		     } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	catch (Exception e) {
            e.printStackTrace();
        }
    }
public static void withdraw(int amount) {
    	
	try {
		BufferedReader sc = new BufferedReader(
                new InputStreamReader(System.in));
    	 System.out.print(
                 "Enter account number:");
    	 int acc_no = Integer.parseInt(
                 sc.readLine());
        con.setAutoCommit(false);
        sql = "select * from customer where acc_no="
              + acc_no;
        PreparedStatement ps
            = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            if (rs.getInt("balance") < amount) {
                System.out.println(
                    "Insufficient Balance!");
                
            }
        }

        Statement st = con.createStatement();

        // debit
        con.setSavepoint();

        sql = "update customer set balance=balance-"
              + amount + " where acc_no=" + acc_no;
        if (st.executeUpdate(sql) == 1) {
            System.out.println("Amount Debited!");
        }

        
       
        
    }
        catch (Exception e) {
            e.printStackTrace();
//            con.rollback();
        }
       
    }
    public static boolean transferMoney(int sender_ac,
                                        int reveiver_ac,
                                        int amount)
        throws SQLException // transfer money method
    {
    	 Statement stt = con.createStatement();
         sql = "insert into transaction values('"+sender_ac+"','" + reveiver_ac + "','" + amount + "')";
         stt.executeUpdate(sql);
             
        if (reveiver_ac == NULL || amount == NULL) {
            System.out.println("All Field Required!");
            return false;
        }
        try {
            con.setAutoCommit(false);
            sql = "select * from customer where acc_no="
                  + sender_ac;
            PreparedStatement ps
                = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
 
            if (rs.next()) {
                if (rs.getInt("balance") < amount) {
                    System.out.println(
                        "Insufficient Balance!");
                    return false;
                }
            }
 
            Statement st = con.createStatement();
 
            // debit
            con.setSavepoint();
 
            sql = "update customer set balance=balance-"
                  + amount + " where acc_no=" + sender_ac;
            if (st.executeUpdate(sql) == 1) {
                System.out.println("Amount Debited!");
            }
 
            // credit
            sql = "update customer set balance=balance+"
                  + amount + " where acc_no=" + reveiver_ac;
            st.executeUpdate(sql);
 
            con.commit();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            con.rollback();
        }
        // return
        return false;
    }
}