package trade;

import agent.SellerAgentBDI;
import product.Product;

public class Ask extends Shout {
	
	SellerAgentBDI seller;
	
	public Ask(float p, float q, Product prod, SellerAgentBDI s){
		super(p,q,prod);
		this.seller = s; 
	}
	
	public SellerAgentBDI getSeller(){
		return this.seller;
	}
}
