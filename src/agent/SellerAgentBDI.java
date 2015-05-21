package agent;

import gui.Market;
import product.Product;
import jadex.bdiv3.BDIAgent;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;
import jadex.bdiv3.annotation.Belief;

@Agent
public class SellerAgentBDI {

	@Agent
	BDIAgent seller;
	
	@Belief
	Product product;
	
	@AgentBody
	public void body() {
		Market.writeLog(this.toString() + " joined the market!");
		product = new Product();
		Market.sellers.add(this);
	}	

	public String toString() {
		return seller.getComponentIdentifier().getLocalName();
	}
	
	public boolean isBuyer() {
		return false;
	}
	
	public boolean isSeller() {
		return true;
	}
	
}