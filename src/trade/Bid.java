package trade;

import agent.BuyerAgentBDI;
import product.Product;

public class Bid extends Shout {

	BuyerAgentBDI buyer;
	
	public Bid(float p, float q, Product prod, BuyerAgentBDI b){
		super(p,q,prod);
		this.buyer = b;
	}
	
	public BuyerAgentBDI getBuyer(){
		return buyer;
	}
}
