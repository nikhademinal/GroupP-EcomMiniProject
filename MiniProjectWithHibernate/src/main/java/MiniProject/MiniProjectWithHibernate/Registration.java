package MiniProject.MiniProjectWithHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.cfg.Configuration;

import java.io.Serializable;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Registration {
	private String name;
	@Id
	private String email;
	private String password;
	private String phoneNumber;
	@Override
	public String toString() {
		return "Registration [name=" + name + ", email=" + email + ", password=" + password
				+ ", phoneNumber=" + phoneNumber + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	 void register_new_user()
	    {	boolean flag1=false; 		 	
	    	boolean flag2=false;
	    	Pattern p1= Pattern.compile("^[1-9][0-9]{9}");
			Pattern p2= Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE );
			
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
				 setEmail(email);
		         setName(name);
		         setPassword(password);
		         setPhoneNumber(phoneNumber);
		         
		         
			}
			}
			else
			{
				System.out.println("Account is already registered with the given email!");
				System.out.println("PLEASE REGISTER TO CONTINUE");
				register_new_user();
			}
	    }
	    
			boolean isDuplicateAccount(String email_id)
			{
				boolean flag=false;
		        Configuration cfg=new Configuration();
		        cfg=cfg.configure("Hibernate.cfg.xml");
		        SessionFactory sf=cfg.buildSessionFactory();
		        Session session=sf.openSession();
		        
		        Registration id=session.get(Registration.class, email_id);
		        try{
		        	flag=id.getEmail().equals(email_id);
		        	}
		        catch(Exception e)
		        {
		        	System.out.println("Duplication email check passed!");
		        }
		        
				if(flag) 
				{
					return true;
			    }
				else
				{
					return false;
				}
			}
	
}
