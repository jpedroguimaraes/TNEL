package product;

import agent.*;

public class Transaction {

	private SellerAgentBDI seller;
	private BuyerAgentBDI buyer;
	private Product product;
	private double value;
	
	public Transaction(SellerAgentBDI s, BuyerAgentBDI b, Product p, double v) {
		seller = s;
		buyer = b;
		product = p;
		value = v;
	}
	
	public SellerAgentBDI getSeller() {
		return seller;
	}
	
	public BuyerAgentBDI getBuyer() {
		return buyer;
	}
	
	public Product getProduct() {
		return product;
	}
	
	public double getValue() {
		return value;
	}
	
}