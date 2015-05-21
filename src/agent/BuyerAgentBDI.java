package agent;

import gui.Market;
import product.Product;
import jadex.bdiv3.BDIAgent;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;
import jadex.bdiv3.annotation.Belief;

@Agent
public class BuyerAgentBDI {

	@Agent
	BDIAgent buyer;
	
	@Belief
	Product product;
	
	@AgentBody
	public void body() {
		Market.writeLog(this.toString() + " joined the market!");
		product = new Product();
		Market.addBuyer(this);
	}	

	public String toString() {
		return buyer.getComponentIdentifier().getLocalName();
	}
	
	public boolean isBuyer() {
		return true;
	}
	
	public boolean isSeller() {
		return false;
	}
	
}