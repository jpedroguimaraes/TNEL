package agent;

import gui.Market;
import jadex.bdiv3.BDIAgent;
import jadex.bdiv3.annotation.Belief;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;
import jadex.micro.annotation.Description;

@Agent
@Description("This is the Market Maker Agent.")
public class OperatorAgentBDI {

	@Agent
	BDIAgent operator;
	
	@Belief(updaterate=1000)
	public long currentTime = System.currentTimeMillis();
	
	@AgentBody
	public void body() {
		Market.operator = this;
		System.out.println("Operator joined the market!");
	}
	
}