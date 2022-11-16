import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class AdminConsole {
	void adminLogin()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter admin's email:");
		String email = sc.nextLine();
		
		System.out.println("Please enter password:");
		String password = sc.nextLine();
		try {
			String sql="select admin_email,password from admins where admin_email=? and password=?";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject","root","Minal@09021998");
			PreparedStatement stmt=con.prepareStatement(sql); 
			stmt.setString(1, email);
			stmt.setString(2, password);
			ResultSet rs=stmt.executeQuery();
			if(rs.next())
			{
				if(rs.getString(1).equals(email)&&rs.getString(2).equals(password))
				{
					System.out.println("Authentication Successfull.");
					operations();
				}
			}
			else {
			System.out.println("Authentication Unsuccessfull.");
			adminLogin();
			}
			con.close();
			stmt.close();
		}	catch(Exception e){ System.out.println(e);} 
		
	}
	
	void operations()
	{
		int choice=0;
		Scanner sc=new Scanner(System.in);
		System.out.println("****************************************************");
		System.out.println("**************WELCOME TO ADMINCONSOLE***************");
		System.out.println("****************************************************");
		while(choice!=4)
		{
			System.out.println("1. Check Inventory");
			System.out.println("2. Check Users List");
			System.out.println("3. Check Purchase History");
			System.out.println("4. Logout");
			choice=sc.nextInt();
			switch(choice)
			{
			case 1:
				getInventory();
				break;
			case 2:
				getUsers();
				break;
			case 3:
				getUserHistory();
				break;
			case 4:
				System.out.println("Thank you!!!");
				break;
			default:
				System.out.println("Invalid Choice");
				break;
			}
		}
	}
	
	void getInventory()
	{
		Scanner sc=new Scanner(System.in);
		int choice=0;
		int product_id=0;
		while(choice!=2)
		{
		System.out.println("1. Enter Product id to check then inventory: ");
		System.out.println("2. Exit ");
		choice=sc.nextInt();
	
		if(choice==1)
		{
			System.out.println("Product_ID: ");
			product_id=sc.nextInt();
			if(product_id<11)
			{
		try {
			String sql="select * from products where product_id=?;";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject","root","Minal@09021998");
			PreparedStatement stmt=con.prepareStatement(sql);
			stmt.setInt(1, product_id);
			ResultSet rs=stmt.executeQuery();
			
			System.out.println("______________________________________________________________________________________________________");
			
			System.out.println(String.format("%-12s","|"+"Product Id")+String.format("%-20s","|"+"Product Name")+String.format("%-45s","|"+"Description")+String.format("%-12s","|"+"Price")+String.format("%-12s","|"+"Quantity"+"   |"));
			
			System.out.println("______________________________________________________________________________________________________");
			
			while(rs.next())
			{
				//System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4)+" "+rs.getString(5));
				System.out.println(String.format("%-12s","|"+rs.getInt(1))+String.format("%-20s","|"+rs.getString(2))+String.format("%-45s","|"+rs.getString(3))+String.format("%-12s","|"+rs.getInt(4))+String.format("%-12s","|"+rs.getInt(5))+"|");
			}
			con.close();
			stmt.close();
			System.out.println("______________________________________________________________________________________________________");
		}	catch(Exception e){ System.out.println(e);}
			}
			else {
				System.out.println("Invalid Product ID");
			}
		}
		else {
			System.out.println("Invalid Choice: Retry");
		}
		}
	}
	void getUsers()
	{
		try {
			String sql="select * from users;";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject","root","Minal@09021998");
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			
			System.out.println("_____________________________________________________________________________");
			
			System.out.println(String.format("%-20s", "|"+"Name")+String.format("%-40s", "|"+"Email")+String.format("%-40s", "|"+"Contact Details"+"|"));
			
			System.out.println("_____________________________________________________________________________");
			
			while(rs.next())
			{
				System.out.println(String.format("%-20s", "|"+rs.getString(1))+String.format("%-40s", "|"+rs.getString(2))+String.format("%-40s", "|"+rs.getLong(3)+"     |"));
			}
			
			System.out.println("_____________________________________________________________________________");
			con.close();
			stmt.close();
		}	catch(Exception e){ System.out.println(e);}
	}
	
	void getUserHistory()
	{
		try {
			String sql="select * from purchase_history;";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject","root","Minal@09021998");
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			System.out.println("______________________________________________________________");
			System.out.println(String.format("%-40s", "|"+"Email")+String.format("%-12s", "|"+"Product Id")+String.format("%-10s", "|"+"Quantity"+"|"));
			System.out.println("______________________________________________________________");
			while(rs.next())
			{
				System.out.println(String.format("%-40s", "|"+rs.getString(1))+String.format("%-12s", "|"+rs.getInt(2))+String.format("%-10s", "|"+rs.getInt(3)+"       |"));
			}
			System.out.println("______________________________________________________________");
			con.close();
			stmt.close();
		}	catch(Exception e){ System.out.println(e);}
	}

}
