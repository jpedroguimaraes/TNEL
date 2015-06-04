package agent;

import gui.Market;
import trade.Orderbook;
import jadex.bdiv3.BDIAgent;
import jadex.bdiv3.annotation.Belief;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.Trigger;
import jadex.bdiv3.runtime.ChangeEvent;
import jadex.bdiv3.runtime.impl.RPlan;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;
import jadex.micro.annotation.Description;

@Agent
@Description("This is the Market Maker Agent.")
public class OperatorAgentBDI {

	@Agent
	BDIAgent operator;
	
	@Belief(updaterate=5000)
	public long currentTime = System.currentTimeMillis();
	
	@Belief
	public Orderbook orderbook;
	
	@AgentBody
	public void body() {
		Market.operator = this;
		System.out.println("Operator joined the market!");
	}
	
	@Plan(trigger=@Trigger(factchangeds="currentTime"))
	protected void printAddedFact(ChangeEvent event, RPlan rplan)
	{
		// Matching;
	}
	
	
}