package MiniProject.MiniProjectWithHibernate;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

@Entity
@Table(name="product")
public class ProductDetails {
	
	int totalammount;
	@Id
	private int id;
	private String name;
	private String description;
	private int price;
	private int quantity;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getdescription() {
		return description;
	}
	public void setdescription(String description) {
		this.description = description;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getquantity() {
		return quantity;
	}
	public void setquantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "ProductDetails [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price + ", quantity=" + quantity
				+ "]";
	}
	
	void viewProduct()
	{
		Configuration cfg = new Configuration();
		cfg=cfg.configure("hibernate.cfg.xml");
		SessionFactory session=cfg.buildSessionFactory();
		Session s=session.openSession();
		Transaction tran=s.beginTransaction();
		Query q = s.createQuery("from ProductDetails");
		List<ProductDetails> pd = q.list();
		
		Iterator<ProductDetails> itr = pd.iterator();
		while(itr.hasNext())
		{
			System.out.println(itr.next());
		}
	}
	
	void choice(String email)
	{
		int choice=0;
		System.out.println("1. Purchase Products");
		System.out.println("2. Exit");
		Scanner sc = new Scanner(System.in);
		choice =sc.nextInt();
		
		if(choice==1)
		{
			buy(email);
		}
		else if(choice==2)
		{
			System.out.println("Thank you");
		}
		else
		{
			System.out.println("Invalid");
			choice(email);
		}
		
	}
	
	void buy(String email)
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
			int availableQuantity = getQuantityFromDB(product_id);
			if(availableQuantity==0)
			{
				System.out.println("Selected Product is OUT OF STOCK!");
				buy(email);
			}
			else if(quantity<=availableQuantity && quantity>0)
			{	Cart c=new Cart();
				c.add_to_cart(product_id,quantity,email);
				purchaseAnotherProduct(email);
			}
			else if(quantity==0)
			{
				System.out.println("Quantity cant be 0");
				System.out.println("Please retry!");
				buy(email);
			}
			else if(quantity<0)
			{
				System.out.println("Quantity cant be -ve");
				System.out.println("Please retry!");
				buy(email);
			}
			else {
				System.out.println("Available Stock for the selected product is "+getQuantityFromDB(product_id));
				System.out.println("Please retry!");
				buy(email);
			}
		}
		else
		{
			System.out.println("Invalid product Id!!!!");
		}
	}
	
	 int getQuantityFromDB(int product_id)
	 {
		 int quantity=0;
		Configuration cfg = new Configuration();
		cfg=cfg.configure("hibernate.cfg.xml");
		SessionFactory session=cfg.buildSessionFactory();
		Session s=session.openSession();
		Transaction tran=s.beginTransaction();
		
		Query q = s.createQuery("select quantity from ProductDetails where id=:product_id");
		q.setParameter("product_id", product_id);
		List<Integer> list=q.list();  
		quantity=list.get(0);  
		
		return quantity;
	}
	 void purchaseAnotherProduct(String email)
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
				invoice(email);
				getbill(email);
				updateInventory(email);
				break;
			case 2:
				buy(email);
				break;
			case 3:
 				getbill(email);
				System.out.println("1. Checkout");
				System.out.println("2. Continue Shopping");
				option=sc.nextInt();
				switch(option)
				{
				case 1 :
					invoice(email);
					getbill(email);
					updateInventory(email);
					break;
				case 2:
					buy(email);
					break;
				default:
					System.out.println("Invalid Input");
					purchaseAnotherProduct(email);
				break;
					
				}
				break;
			
			default:
				System.out.println("Invalid input");
				purchaseAnotherProduct(email);
				break;
			}
			
		}
	 void getbill(String email)
		{
//			Billing bill=new Billing();
//			bill.generateInvoice(this,email);
//			inventoryAdjustment(this,email);
		 	Configuration cfg=new Configuration();
		 	cfg=cfg.configure("Hibernate.cfg.xml");
		 	SessionFactory sf=cfg.buildSessionFactory();
		 	Session s=sf.openSession();
		 	Query q=s.createQuery("from Cart where email=:email");
		 	q.setParameter("email", email);
		 	List<Cart> productlist=q.getResultList();
		 	String productName=null;
			Integer price=0;
			String cemail=null;
			Integer cq=0;
			Integer id=0;

			System.out.println("___________________________________________________________________________________________________________________");
			System.out.println(String.format("%-12s","|"+"Product Name")+String.format("%-25s","|"+"Product Price")+String.format("%-30s","|"+"Email")+String.format("%-20s", "|"+"Quantity")+String.format("%-20s", "|"+"Product Id"));
			System.out.println("___________________________________________________________________________________________________________________");

			for (Cart c : productlist) {
				productName = c.getName();
			    price = c.getPrice();
			    cemail=c.getEmail();
			    cq=c.getQuantity();
			    id=c.getId();
			    
			    totalammount=totalammount+(price*cq); 
			     
			    System.out.println(String.format("%-12s","|"+productName)+String.format("%-25s","|"+price)+String.format("%-30s","|"+cemail)+String.format("%-20s", "|"+cq)+String.format("%-20s", "|"+id));
			    
			     
			}
			
			System.out.println("Total Bill:  "+totalammount);
		 
		 
			System.out.println();
			System.out.println();
			System.out.println("Thank you for shopping with us!!!!");
			System.out.println();
			System.out.println();
			System.out.println();
			
		}
	 
	 void invoice(String email)
	 {
		 Date date= new Date();
			long time = date.getTime();
			Timestamp ts = new Timestamp(time);
			
			int productID=0;
			Instant timestamp = Instant.now();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("Here your Billl!!!!");
			System.out.println();
			System.out.println();
			System.out.println("_________________________________________________________");
			System.out.println("\t\tINVOICE\t\t");
			System.out.println("_________________________________________________________");
			System.out.println("Email:  "+email);
			System.out.println("Date&Time: " + ts);
			System.out.println("_________________________________________________________");
	 }
	 
	 void updateInventory(String email)
	 {
		 	Configuration cfg=new Configuration();
		 	cfg=cfg.configure("Hibernate.cfg.xml");
		 	SessionFactory sf=cfg.buildSessionFactory();
		 	Session s=sf.openSession();
		 	Query q=s.createQuery("from Cart where email=:email");
		 	q.setParameter("email", email);
		 	List<Cart> productlist=q.getResultList();
		 	Integer id=0;
		 	Integer cq=0;
		 	for (Cart c : productlist) {
			    cq=c.getQuantity();
			    id=c.getId();
			    updateProducts(id,cq);
			    updatecart(id,email);
			}
	 }
	 
	 void updateProducts(int productId,int purchasedquantity)
	 {
		 	Configuration cfg=new Configuration();
		 	cfg=cfg.configure("Hibernate.cfg.xml");
		 	SessionFactory sf=cfg.buildSessionFactory();
		 	Session s=sf.openSession();
		 	Transaction t=s.beginTransaction();
		 	Query q=s.createQuery("From ProductDetails pd where pd.id=:productId");
		 	q.setParameter("productId", productId);
		 	ProductDetails p=(ProductDetails) q.uniqueResult();
		 	int availableQuantity=p.getquantity();
		 	System.out.println(availableQuantity);
		 	availableQuantity=availableQuantity-purchasedquantity;
		 	System.out.println(availableQuantity);
		 	q=s.createQuery("update ProductDetails set quantity=:availableQuantity where id=:productId");
		 	q.setParameter("productId", productId);
		 	q.setParameter("availableQuantity", availableQuantity);
		 	q.executeUpdate();
		 	t.commit();
	 }
	 void updatecart(int productId,String email)
	 {
		 	Configuration cfg=new Configuration();
		 	cfg=cfg.configure("Hibernate.cfg.xml");
		 	SessionFactory sf=cfg.buildSessionFactory();
		 	Session s=sf.openSession();
		 	Transaction t=s.beginTransaction();
		 	Query q=s.createQuery("delete from Cart where email=:email and id=:productId");
		 	q.setParameter("productId", productId);
		 	q.setParameter("email", email);
		 	q.executeUpdate();
		 	t.commit();
	 }
}