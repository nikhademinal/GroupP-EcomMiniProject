import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registration {
	
	void register_user()
	{
		String name="";
		String email="";
		String password=null;
		String phoneNumber="";
		long temp=0;
		boolean flag1=false;
		boolean flag2=false;
		Pattern p1= Pattern.compile("^[1-9][0-9]{9}");
		Pattern p2= Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE );
		
		System.out.println("                                                                          ");
		System.out.println("                      =============================                 ");
		System.out.println("                     ||"+"         REGISTER          "+"||                      ");
		System.out.println("                      =============================                 ");
		System.out.println("                                                                          ");
		
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter your Name:");
		name=sc.nextLine();
		
		while(flag1==false)
		{
		System.out.println("Enter your Email:");
		email=sc.nextLine();
		if(p2.matcher(email).matches())
		{
			flag1=true;
		}
		else {
			flag1=false;
			System.out.println("Invalid Email Address");
		}
		}
		boolean b=isDuplicateAccount(email);
		if(b==false)
		{
		System.out.println("Enter Password");
		password=sc.nextLine();		
		
		while(flag2==false) {
		System.out.println("Enter your 10 Digit Phone Number:");
		phoneNumber=sc.nextLine();
		if(p1.matcher(phoneNumber).matches())
		{
			flag2=true;
		}
		else {
			flag2=false;
			System.out.println("Invalid Phone Number");
		}
		}
		if(flag1==true&&flag2==true)
		{
		try {
			String sql="insert into users(Name,Email,Phone_Number,password) values(?,?,?,?)";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject","root","Minal@09021998");
			PreparedStatement stmt=con.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setString(2, email);
			stmt.setString(3, phoneNumber);
			stmt.setString(4, password);
			stmt.executeUpdate();
			System.out.println("Registration Successfull.");
			
			con.close();
			stmt.close();
		}	catch(Exception e){ System.out.println(e);} 
		}
		
		System.out.println("");
		System.out.println("TO LOGIN.");
		Login l = new Login();
		l.login();
		}
		else
		{
			System.out.println("Account is already registered with the given email!");
			System.out.println("PLEASE REGISTER TO CONTINUE");
			register_user();
		}
	}
	
	boolean isDuplicateAccount(String email_id)
	{
		boolean flag=false;
		try {
			String sql="select email from users where email=?";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject","root","Minal@09021998");
			PreparedStatement stmt=con.prepareStatement(sql); 
			stmt.setString(1, email_id);
			ResultSet rs=stmt.executeQuery();
			if(rs.next())
			{
				if(rs.getString(1).equals(email_id))
				{
					flag=true;
				}
				else {
					flag=false;
				}
			}
			con.close();
			stmt.close();
		}	catch(Exception e){ System.out.println(e);}
		return flag;
	}
}
