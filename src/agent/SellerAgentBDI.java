package agent;

import agent.communication.MessageService;
import gui.Market;
import product.Product;
import jadex.bdiv3.BDIAgent;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;
import jadex.micro.annotation.Binding;
import jadex.micro.annotation.Description;
import jadex.micro.annotation.RequiredService;
import jadex.micro.annotation.RequiredServices;
import jadex.bdiv3.annotation.Belief;
import jadex.bdiv3.annotation.Goal;
import jadex.bdiv3.annotation.GoalCreationCondition;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.Trigger;
import jadex.bdiv3.runtime.ChangeEvent;
import jadex.bdiv3.runtime.IPlan;
import jadex.bdiv3.runtime.impl.RPlan;

@Agent
@Description("This is a Seller Agent.")
@RequiredServices({
	@RequiredService(name="MessageServiceImplementation", type=MessageService.class, 
			binding=@Binding(scope=Binding.SCOPE_PLATFORM))
})
public class SellerAgentBDI {

	public static int id = 1;
	
	public int idseller;

	@Agent
	BDIAgent seller;
	
	@Belief
	Product product;
	
	@Belief
	private boolean sold = false;
	
	@Belief
	private boolean selling = false;

	@Belief(updaterate=5000)
	protected long currentTime = System.currentTimeMillis();
	
	@AgentBody
	public void body() {
		idseller = id;
		id++;
		Market.writeLog(this.toString() + " joined the market!");
		product = new Product();
		Market.sellers.add(this);
		Market.fillLists();
		Market.updateGUI();
	}	
	
	@Goal(recur=true)
	public class SellGoal {
		
		@GoalCreationCondition(beliefs="product")
		public SellGoal() {
			sold = false;
		}
		
	}
	
	@Plan(trigger=@Trigger(goals=SellGoal.class))
	public void initiateSelling(SellGoal s, IPlan iplan) {
		System.out.println("Plan");
		selling = true;
	}
	
	@Plan(trigger=@Trigger(factchangeds="currentTime"))
	protected void tryingToSell(ChangeEvent event, RPlan rplan)
	{
		if(selling) {
			if(sold) {
				exitMarket();
			} else {
				boolean i = false;
				if(i) {
					exitMarket();
				} else {
					product.setPrice(Math.floor(((product.getPrice() - (product.getPrice() * 0.2)) * 100)) / 100);
					System.out.println(product.getPrice());
				}
			}
			System.out.println(currentTime);
		}
	}	
	
	public boolean exitMarket() {
		int i = 0;
		do {
			if(idseller == Market.sellers.get(i).getID()) {
				Market.buyers.remove(i);
				Market.writeLog(this.toString() + " left the market.");
				selling = false;
				return true;
			}
			i++;
		} while(i < Market.sellers.size());
		return false;
	}
	
	public String getProduct() {
		return product.getName();
	}
	
	public double getPrice() {
		return product.getPrice();
	}
	
	public int getID() {
		return idseller;
	}

	public String toString() {
		return "Seller" + idseller;
	}
	
	public boolean isBuyer() {
		return false;
	}
	
	public boolean isSeller() {
		return true;
	}
	
}