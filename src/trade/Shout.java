package trade;

import java.sql.Timestamp;
import java.util.Date;

import product.Product;

public class Shout {

	private float price;
	private float quantity;
	private float total;
	private Product product;
	private Timestamp time;
	private String authorID;
	
	public Shout(float p, float q, Product prod){
		this.price = p;
		this.quantity = q;
		this.total = price*quantity;
		this.product = prod;
		Date date = new Date();
		time = new Timestamp(date.getTime()); 
	}

	public float getPrice() {
		return price;
	}

	public float getQuantity() {
		return quantity;
	}

	public float getTotal() {
		return total;
	}

	public Product getProd() {
		return product;
	}
	
	public Timestamp getTime(){
		return time;
	}
	
	public String getAuthor(){
		return authorID;
	}
}
