package agent;

import product.Product;
import gui.Market;
import jadex.bdiv3.BDIAgent;
import jadex.bdiv3.annotation.Belief;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;

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
		Market.buyers.add(this);
		Market.fillLists();
		Market.updateGUI();
	}	
	
	public String getProduct() {
		return product.getName();
	}
	
	public double getPrice() {
		return product.getPrice();
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