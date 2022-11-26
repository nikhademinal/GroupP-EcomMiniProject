import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class Login {
	
	String email="";
	
	void login()
	{
		Scanner sc1 = new Scanner(System.in);
		
		Scanner sc=new Scanner(System.in);
		String password=null;
		
		System.out.println("Please enter registered Email: ");
		email=sc.nextLine();
		System.out.println("Please enter Password to sign in: ");
		password=sc.nextLine();
		try {
			String sql="select email,password from users where email=?";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject","root","Minal@09021998");
			PreparedStatement stmt=con.prepareStatement(sql); 
			stmt.setString(1, email);
			ResultSet rs=stmt.executeQuery();
			if(rs.next())
			{
				if(rs.getString(1).equals(email) && rs.getString(2).equals(password))
				{
					System.out.println("Authentication Successfull.");
					
					System.out.println("Welcome!!!");
					System.out.println();
					System.out.println("1. View Product");
					System.out.println("2. Logout");
					int a = sc1.nextInt();
					
					switch(a)
					{
					case 1 :
						ProductDetails pd = new ProductDetails();
						pd.viewProductDetails();
						pd.choice(email);
						
						break;
					case 2:
						System.out.println("Thank you for visiting us!!!");
						break;
					default :
							System.out.println("Ops, please select correct option");
							System.out.println("Logging Out");
							login();
					}
				}
			}
			else {
				
				System.out.println("Authentication Unsuccessfull.");
				System.out.println("Invalid Credentials..");
				System.out.println("Select choice:");
				System.out.println("1. Login Again");
				System.out.println("2. Register");
				
				int b = sc.nextInt();
				
				if(b==1)
				{
					login();
				}
				else if (b==2)
				{
				Registration r = new Registration();
				r.register_user();
				}
				else
				{
					System.out.println("Invalid options..");
				}
			}
			con.close();
			stmt.close();
		}	
		catch(Exception e){ System.out.println(e);} 
	}	
}
