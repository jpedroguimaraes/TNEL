package trade;

import java.sql.Timestamp;
import java.util.Date;

public class Shout {

	private float price;
	private float quantity;
	private float total;
	private int prodID;
	private Timestamp time;
	private String authorID;
	
	public Shout(float p, float q, int pID, String aID){
		this.price = p;
		this.quantity = q;
		this.total = price*quantity;
		this.prodID = pID;
		this.authorID = aID;
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

	public int getProdID() {
		return prodID;
	}
	
	public Timestamp getTime(){
		return time;
	}
	
	public String getAuthor(){
		return authorID;
	}
}
