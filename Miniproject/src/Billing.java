import java.util.Map;
import java.time.*;
import java.util.Date;
import java.sql.Timestamp;

public class Billing {
	void generateInvoice(ProductDetails p,String email)
	{
		Date date= new Date();
		long time = date.getTime();
		Timestamp ts = new Timestamp(time);
		
		int productID=0;
		Instant timestamp = Instant.now();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("_________________________________________________________");
		System.out.println("\t\tINVOICE\t\t");
		System.out.println("_________________________________________________________");
		System.out.println("Email:  "+email);
		System.out.println("Total Amount:   "+p.totalammount);
		System.out.println("Date&Time: " + ts);
		System.out.println("_________________________________________________________");
		System.out.println("\t\tPurchase Details\t\t");
		System.out.println("_________________________________________________________");
		System.out.println(String.format("%-12s","|"+"Product Id")+String.format("%-20s","|"+"Product Name")+String.format("%-30s","|"+"Quantity"));
		for(Map.Entry m : p.addtocart.entrySet()){ 
			productID=(int) m.getKey();
			System.out.println(String.format("%-12s","|"+m.getKey())+String.format("%-20s","|"+p.getNamefromDB(productID))+String.format("%-30s","|"+m.getValue()));
		    
		   }
		System.out.println("_________________________________________________________");
	}

}
