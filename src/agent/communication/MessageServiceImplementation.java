package agent.communication;

import jadex.bridge.service.annotation.ServiceComponent;
import jadex.commons.future.IFuture;
import trade.Ask;
import trade.Bid;
import trade.Match;
import agent.BuyerAgentBDI;
import agent.OperatorAgentBDI;
import agent.SellerAgentBDI;

public class MessageServiceImplementation implements MessageService {

	@ServiceComponent
	protected OperatorAgentBDI market;
	
	@Override
	public IFuture<Void> serviceBody(int value) {
		return null;
	}

	@Override
	public void start(OperatorAgentBDI m) {
		market = m;
		
	}

	@Override
	public OperatorAgentBDI getMarket() {
		return market;
	}

	@Override
	public void register(BuyerAgentBDI b, String id) {
		this.buyerList.put(id, b);		
	}

	@Override
	public void register(SellerAgentBDI s, String id) {
		this.sellerList.put(id,s);
		
	}

	@Override
	public void bid(Bid b) {		 
		market.bid(b);
	}

	@Override
	public void ask(Ask a) {
		market.ask(a);
	}

	@Override
	public void notify(Match m) {
		String bID = m.getBid().getAuthor();
		// TODO
		//this.buyerList.get(bID).notifyMatch()
		
		String aID = m.getAsk().getAuthor();
		// TODO
		//this.sellerList.get(aID).notifyMatch();
	}
	
	
}
