package product;

import java.util.Random;

public class Product {

	public static String[] products = {"A","B","C"};
	public static double pricelimit = 5;
	
	public String name;
	public double price;
	
	public Product() {
		Random rn = new Random();
	    name = products[rn.nextInt(2)];
		Random rp = new Random();
	    price = Math.floor(pricelimit * rp.nextDouble() * 100) / 100;
	    System.out.println(name + " : " + price);
	}
	
	public String getName() {
		return name;
	}
	
	public double getPrice() {
		return price;
	}
	
}