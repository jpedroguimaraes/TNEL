package agent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import product.Transaction;

import gui.Market;
import trade.Ask;
import trade.Bid;
import trade.Match;
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
	public Map<String,Orderbook> orderbook;
	
	@AgentBody
	public void body() {
		Market.operator = this;
		orderbook = new HashMap<String, Orderbook>();
		System.out.println("Operator joined the market!");
	}
	
	@Plan(trigger=@Trigger(factchangeds="currentTime"))
	protected void printAddedFact(ChangeEvent event, RPlan rplan)
	{
		// Matching;
		// TODO Add ability to choose matching type (Regular/Max Volume)
		
		ArrayList<Transaction> Transactions = new ArrayList<Transaction>();
		
		for(Orderbook ob : orderbook.values()){
			ob.regularMatch();
			Transactions.addAll(ob.getTransactions());
		}
		
		Market.transactions.clear();
		Market.transactions.addAll(Transactions);
	}
	
	public void bid(Bid b){
		String id = b.getProd().getName();
		if(!orderbook.containsKey(id)){
			Orderbook newProduct = new Orderbook();
			orderbook.put(id, newProduct);
		}
		orderbook.get(id).bid(b);
	}
	
	public void ask(Ask a){
		String id = a.getProd().getName();
		if(!orderbook.containsKey(id)){
			Orderbook newProduct = new Orderbook();
			orderbook.put(id, newProduct);
		}
		orderbook.get(id).ask(a);
	}
}