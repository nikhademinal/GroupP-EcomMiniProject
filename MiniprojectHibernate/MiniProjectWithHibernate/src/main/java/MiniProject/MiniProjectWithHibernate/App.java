package MiniProject.MiniProjectWithHibernate;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class App 
{
    public static void main( String[] args )
    {
    	
    	int choice=0;
		Scanner sc=new Scanner(System.in);
		while(true)
		{
		System.out.println("                                                                          ");
		System.out.println("                           ====================                     ");
		System.out.println("                          ||"+"WELCOME TO CLOUD9 "+"||                          ");
		System.out.println("                           ====================                     ");
		System.out.println("                                                                          ");
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		System.out.println("                ----------                        ----------");
		System.out.println("                |1: Login|                        |2:Signup|");
		System.out.println("                ----------                        ----------");
		System.out.println();
		System.out.println("                               ---------------                       ");
		System.out.println("                               |3:Admin Login|");
		System.out.println("                               ---------------                       ");
		System.out.println("                                  ---------                             ");
		System.out.println("                                  |4: Exit|"                             );
		System.out.println("                                  ---------                             ");
		
		
		System.out.println("======================");
		System.out.println("| Enter your choice: |");
		System.out.println("======================");
		choice=sc.nextInt();
		switch(choice)
		{
		case 1: 
			Login l=new Login();
			l.login();
			break;
		case 2:
			Configuration cfg = new Configuration();
			cfg=cfg.configure("hibernate.cfg.xml");
			SessionFactory session=cfg.buildSessionFactory();
			Session s=session.openSession();
			Transaction tran=s.beginTransaction();
			Registration r=new Registration();
			r.register_new_user();
			s.save(r);
			tran.commit();
			s.close();
			Login l1=new Login();
			l1.login();
			break;
		case 3:
//			AdminConsole admin=new AdminConsole();
//			admin.adminLogin();
			break;
		case 4:
			System.out.println("Thank you for visiting us!");
			break;
		default:
			System.out.println("Incorrect Choice!!");
			break;
		}
		
	}
	}
    
}
