package agent;

import gui.Market;
import jadex.bdiv3.BDIAgent;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;

@Agent
public class OperatorAgentBDI {

	@Agent
	BDIAgent operator;
	
	@AgentBody
	public void body() {
		Market.operator = this;
		System.out.println("Operator joined the market!");
	}
	
	
}