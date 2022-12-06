package MiniProject.MiniProjectWithHibernate;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Login {
	
private String email="";
private String password=null;
	
	void login() 
	{
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Please enter registered Email: ");
		email=sc.nextLine();
		System.out.println("Please enter Password to sign in: ");
		password=sc.nextLine();
		try {
		authenticate(email,password);
		}
		catch(Exception e)
		{
			System.out.println(e);
			authenticatefail();
		}
	}
	
	void authenticate(String email,String password)
	{
		Scanner sc = new Scanner(System.in);
        Configuration cfg=new Configuration();
        cfg=cfg.configure("Hibernate.cfg.xml");
        SessionFactory sf=cfg.buildSessionFactory();
        Session session=sf.openSession();
        
        Registration reg=session.get(Registration.class, email);
        
        if(reg.getEmail().equals(email) & reg.getPassword().equals(password))
        {
        	System.out.println("Authentication Sucessfull!!!");
        	
        	System.out.println("Welcome!!!");
			System.out.println();
			System.out.println("1. View Product");
			System.out.println("2. Logout");
			int a = sc.nextInt();
			
			switch(a)
			{
			case 1 :
				ProductDetails pd = new ProductDetails();
				pd.viewProduct();
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
        
        else
        {
        	//new NullPointerException();
        	authenticatefail();
        	
        }
		
	}
	
	void authenticatefail()
	{
		Scanner sc = new Scanner(System.in);
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
		r.register_new_user();
		}
		else
		{
			System.out.println("Invalid options..");
		}
		
	}

}
