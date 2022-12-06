package MiniProject.MiniProjectWithHibernate;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
@Entity
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sr_no;
	private int id;
	private String name;
	private int quantity;
	private int price;
	private String email;
	public int getSr_no() {
		return sr_no;
	}
	public void setSr_no(int sr_no) {
		this.sr_no = sr_no;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
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
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Cart [id=" + id + ", name=" + name + ", quantity=" + quantity + ", price=" + price + "]";
	}
	
	void add_to_cart(int product_id,int quantity,String email)
	{
		Cart c =new Cart();
		c.setEmail(email);
		c.setId(product_id);
		Configuration cfg=new Configuration();
		cfg=cfg.configure("Hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session s=sf.openSession();
		Transaction t=s.beginTransaction();
		Query q=s.createQuery("select name,price from ProductDetails where id=:product_id");
		q.setParameter("product_id", product_id);
		List<Object[]> list1=q.getResultList();
		String productName=null;
		Integer price=0;
		System.out.println(list1.get(0));
		for (Object[] row : list1) {
			productName = (String) row[0];
		    price = (Integer) row[1];
//		    System.out.println(name + " is " + price + " price");
		}
		c.setName(productName);
		c.setQuantity(quantity);
		c.setPrice(price*quantity);
		s.save(c);
		t.commit();
		sf.close();
		s.close();
	}

}
