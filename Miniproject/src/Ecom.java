import java.util.Scanner;

public class Ecom{

	public static void main(String[] args) {
		int choice=0;
		Scanner sc=new Scanner(System.in);
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
			Registration r=new Registration();
			r.register_user();
			break;
		case 3:
			AdminConsole admin=new AdminConsole();
			admin.adminLogin();
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
