import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class ProductDetails {
	
	String email=null;
	int totalammount;
	HashMap<Integer,Integer> addtocart=new HashMap<Integer,Integer>();
	 void viewProductDetails()
	{
		System.out.println("------------------------------------------================---------------------------------------------");
		System.out.println("------------------------------------------|Product Catlog|---------------------------------------------");
		System.out.println("------------------------------------------================---------------------------------------------");
		
		System.out.println();
		
		System.out.println("______________________________________________________________________________________________________");
		
		System.out.println(String.format("%-12s","|"+"Product Id")+String.format("%-20s","|"+"Product Name")+String.format("%-45s","|"+"Description")+String.format("%-12s","|"+"Price")+String.format("%-12s","|"+"Quantity"+"   |"));
		

		System.out.println("______________________________________________________________________________________________________");
		
		try {
			String sql="select * from products order by Price;";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject","root","Minal@09021998");
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				System.out.println(String.format("%-12s","|"+rs.getInt(1))+String.format("%-20s","|"+rs.getString(2))+String.format("%-45s","|"+rs.getString(3))+String.format("%-12s","|"+rs.getInt(4))+String.format("%-12s","|"+rs.getInt(5))+"|");
			}
			con.close();
			stmt.close();
		}	catch(Exception e){ System.out.println(e);} 
		
		System.out.println("______________________________________________________________________________________________________");
		
		System.out.println();
	}
	
	void choice()
	{
		int choice=0;
		System.out.println("1. Purchase Products");
		System.out.println("2. Exit");
		Scanner sc = new Scanner(System.in);
		choice =sc.nextInt();
		
		if(choice==1)
		{
			buy();
		}
		else if(choice==2)
		{
			System.out.println("Thank you");
		}
		else
		{
			System.out.println("Invalid");
			choice();
		}
		
	}
	
	void buy()
	{
		int quantity=0;
		
		Scanner sc = new Scanner(System.in);
		int price =0;
		
		System.out.println("Enter product id to buy:");
		int product_id=sc.nextInt();
		if(product_id<=10)
		{
			System.out.println("Enter Quantity: ");
			quantity=sc.nextInt();	
			
			int availableQuantity=getQuantityfromDB(product_id);
			if(availableQuantity==0)
			{	
				System.out.println("Selected Product is OUT OF STOCK!");
				buy();
			}
			else if(quantity<=availableQuantity && quantity>0)
			{
			addtocart.put(product_id,quantity);
			try {
					String sql="select price from products where Product_Id=?";
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject","root","Minal@09021998");
					PreparedStatement stmt=con.prepareStatement(sql); 
					stmt.setInt(1,product_id);
					ResultSet rs=stmt.executeQuery();
					if(rs.next())
					{
						price=rs.getInt(1);
					}
		
					con.close();
					stmt.close();
			}	
			catch(Exception e){ System.out.println(e);
			} 
			int intermid=price*quantity;
			totalammount+=intermid;
	
	
			System.out.println("Total Ammount to be Paid: "+totalammount);
			
			purchaseAnotherProduct();
			}
			else if(quantity==0)
			{
				System.out.println("Quantity cant be 0");
				System.out.println("Please retry!");
				buy();
			}
			else if(quantity<0)
			{
				System.out.println("Quantity cant be -ve");
				System.out.println("Please retry!");
				buy();
			}
			else {
				System.out.println("Available Stock for the selected product is "+getQuantityfromDB(product_id));
				System.out.println("Please retry!");
				buy();
			}
		}		
	}
	
	void purchaseAnotherProduct()
	{
		Scanner sc = new Scanner(System.in);
		int exit=0;
		int option=0;
		System.out.println("1. Checkout");
		System.out.println("2. Continue");
		System.out.println("3. View Cart");
	
		exit = sc.nextInt();
		
		switch(exit)
		{
		
		case 1 :
			
			getbill();
			break;
		case 2:
			buy();
			break;
		case 3:
			viewcart();
			System.out.println("1. Checkout");
			System.out.println("2. Continue Shopping");
			option=sc.nextInt();
			switch(option)
			{
			case 1 :
				getbill();
				break;
			case 2:
				buy();
				break;
			default:
				System.out.println("Invalid Input");
				purchaseAnotherProduct();
			break;
				
			}
			break;
		
		default:
			System.out.println("Invalid input");
			purchaseAnotherProduct();
			break;
		}
		
	}
	
	void viewcart()
	{
		int productID=0;
		System.out.println("Total Amount: "+totalammount);
		System.out.println();
		System.out.println("                        Cart");
		System.out.println();
		System.out.println("_________________________________________________________");
		System.out.println(String.format("%-12s","|"+"Product Id")+String.format("%-20s","|"+"Product Name")+String.format("%-30s","|"+"Quantity"));
		System.out.println("_________________________________________________________");
		for(Map.Entry m : addtocart.entrySet()){    
			productID=(int) m.getKey();
			System.out.println(String.format("%-12s","|"+m.getKey())+String.format("%-20s","|"+getNamefromDB(productID))+String.format("%-30s","|"+m.getValue()));
			
		   }
		System.out.println("_________________________________________________________");
	}
	
	void getbill()
	{
		Billing bill=new Billing();
		bill.generateInvoice(this,email);
		inventoryAdjustment(this,email);
		
	}
	
	void inventoryAdjustment(ProductDetails p, String email)
	{
			Set productid=p.addtocart.keySet();
			Iterator pid=productid.iterator();
		while(pid.hasNext())
		{
			int id=(int) pid.next();
			int quant=p.addtocart.get(id);
			int dbquant=getQuantityfromDB(id);
			dbquant-=quant;
			try {
				String sql="update products set quantity=? where product_id=?;";
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject","root","Minal@09021998");
				PreparedStatement stmt=con.prepareStatement(sql);  
				stmt.setInt(1, dbquant);
				stmt.setInt(2, id);
				stmt.executeUpdate();
				con.close();
				stmt.close();
			}	catch(Exception e){ System.out.println(e);}
			try {
				String sql="insert into purchase_history values(?,?,?);";
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject","root","Minal@09021998");
				PreparedStatement stmt=con.prepareStatement(sql);  
				stmt.setString(1, email);
				stmt.setInt(2, id);
				stmt.setInt(3, quant);
				stmt.executeUpdate();
				con.close();
				stmt.close();
			}	catch(Exception e){ System.out.println(e);}
		}
	}
	
	int getQuantityfromDB(int productid)
	{
		int quantity=0;
		try {
			String sql="select quantity from products where product_id=?";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject","root","Minal@09021998");
			PreparedStatement stmt=con.prepareStatement(sql); 
			stmt.setInt(1, productid);
			ResultSet rs=stmt.executeQuery();
			if(rs.next())
			{
				quantity=rs.getInt(1);
			}
			con.close();
			stmt.close();
		}	catch(Exception e){ System.out.println(e);} 
		return quantity;
	}
	
	String getNamefromDB(int productid)
	{
		String productname="";
		try {
			String sql="select product_name from products where product_id=?";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject","root","Minal@09021998");
			PreparedStatement stmt=con.prepareStatement(sql); 
			stmt.setInt(1, productid);
			ResultSet rs=stmt.executeQuery();
			if(rs.next())
			{
				productname=rs.getString(1);
			}
			con.close();
			stmt.close();
		}	catch(Exception e){ System.out.println(e);} 
		return productname;
	}
	
	
	
//	void buy()
//	{
//		int choice=0;
//		int exit = 0;
//		int price=0;
//
//			System.out.println("Enter 1:   To Select the product id's to add the product to cart: ");
//			System.out.println("Enter 100: To exit ");
//			Scanner sc=new Scanner(System.in);
//			choice=sc.nextInt();
//			while(choice!=100)
//			{	
//				if(exit==2)
//				{
//					break;
//				}
//				
//				System.out.println("Enter product id to buy:");
//				int product_id=sc.nextInt();
//				if(product_id<10)
//				{
//					System.out.println("Enter Quantity: ");
//					int quantity=sc.nextInt();	
//					addtocart.put(product_id,quantity);
//			try {
//				String sql="select price from products where Product_Id=?";
//				Class.forName("com.mysql.cj.jdbc.Driver");
//				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject","root","Minal@09021998");
//				PreparedStatement stmt=con.prepareStatement(sql); 
//				stmt.setInt(1,product_id);
//				ResultSet rs=stmt.executeQuery();
//				if(rs.next())
//				{
//					price=rs.getInt(1);
//				}
//				
//				con.close();
//				stmt.close();
//			}	catch(Exception e){ System.out.println(e);} 
//			int intermid=price*quantity;
//			totalammount+=intermid;
//			
//			
//			System.out.println("Total Ammount to be Paid: "+totalammount);
//			//System.out.println("1. Checkout");
//			//System.out.println("2. Continue");
//			
//			//exit = sc.nextInt();
//			while(exit>2||exit==0)
//			{
//			System.out.println("1. Checkout");
//			System.out.println("2. Continue");
//			
//			exit = sc.nextInt();
//			
//			if(exit==1)
//			{
//				System.out.println("Total Amount: "+totalammount);
//				System.out.println("||Product_id||"+" "+"Quantity||");
//				for(Map.Entry m : addtocart.entrySet()){    
//				    System.out.println("    "+m.getKey()+"  	       "+m.getValue());    
//				   }
//				break;
//			}
//			else if(exit>2||exit==0)
//			{
//				System.out.println("Invalid Choice, Try Again!!!");
//			}
//			}
//			if(exit==1)
//			{
//				break;
//			}
//			}
//			else 
//			{
//				System.out.println("Incorrect product id");
//			}
//				
//		}
//	}
//	

}
