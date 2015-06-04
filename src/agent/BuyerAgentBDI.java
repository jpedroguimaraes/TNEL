package agent;

import agent.communication.MessageService;
import product.Product;
import gui.Market;
import jadex.bdiv3.BDIAgent;
import jadex.bdiv3.annotation.Belief;
import jadex.bdiv3.annotation.Goal;
import jadex.bdiv3.annotation.GoalCreationCondition;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.Trigger;
import jadex.bdiv3.runtime.ChangeEvent;
import jadex.bdiv3.runtime.IPlan;
import jadex.bdiv3.runtime.impl.RPlan;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;
import jadex.micro.annotation.Binding;
import jadex.micro.annotation.Description;
import jadex.micro.annotation.RequiredService;
import jadex.micro.annotation.RequiredServices;

@Agent
@Description("This is a Buyer Agent.")
@RequiredServices({
	@RequiredService(name="MessageServiceImplementation", type=MessageService.class, 
			binding=@Binding(scope=Binding.SCOPE_PLATFORM))
})
public class BuyerAgentBDI {

	public static int id = 1;
	
	public int idbuyer;
	
	@Agent
	BDIAgent buyer;
	
	@Belief
	private Product product;
	
	@Belief
	private boolean bought = false;
	
	@Belief
	private boolean buying = false;

	@Belief(updaterate=5000)
	protected long currentTime = System.currentTimeMillis();
	
	@AgentBody
	public void body() {
		idbuyer = id;
		id++;
		Market.writeLog(this.toString() + " joined the market!");
		product = new Product();
		Market.buyers.add(this);
		Market.fillLists();
		Market.updateGUI();
	}	
	
	@Goal(recur=true)
	public class BuyGoal {
		
		@GoalCreationCondition(beliefs="product")
		public BuyGoal() {
			bought = false;
		}
		
	}
	
	@Plan(trigger=@Trigger(goals=BuyGoal.class))
	public void initateBuying(BuyGoal b, IPlan iplan) {
		System.out.println("Plan");
		buying = true;
	}
	
	@Plan(trigger=@Trigger(factchangeds="currentTime"))
	protected void tryingToBuy(ChangeEvent event, RPlan rplan)
	{
		if(buying) {
			if(bought) {
				exitMarket();
			} else {
				//bid
				boolean i = false; //change to response to bidding
				if(i) {
					exitMarket();
				} else {
					product.setPrice(Math.floor(((product.getPrice() + (product.getPrice() * 0.2)) * 100)) / 100);
					System.out.println(product.getPrice());
				}
			}
			System.out.println(currentTime);
		}
	}
	
	public boolean exitMarket() {
		int i = 0;
		do {
			if(idbuyer == Market.buyers.get(i).getID()) {
				Market.buyers.remove(i);
				Market.writeLog(this.toString() + " left the market.");
				buying = false;
				return true;
			}
			i++;
		} while(i < Market.buyers.size());
		return false;
	}
	
	public String getProduct() {
		return product.getName();
	}
	
	public double getPrice() {
		return product.getPrice();
	}
	
	public int getID() {
		return idbuyer;
	}

	public String toString() {
		return "Buyer" + idbuyer;
	}
	
	public boolean isBuyer() {
		return true;
	}
	
	public boolean isSeller() {
		return false;
	}
	
}