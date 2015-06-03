package agent.communication;

import java.util.HashMap;
import java.util.Map;

import trade.Ask;
import trade.Bid;
import agent.BuyerAgentBDI;
import agent.OperatorAgentBDI;
import agent.SellerAgentBDI;
import jadex.bridge.service.annotation.Service;
import jadex.commons.future.IFuture;

@Service
public interface MessageService {
	
	public IFuture<Void> serviceBody(int value);
	
	public Map<String, SellerAgentBDI> sellerList = new HashMap<String, SellerAgentBDI>();
	public Map<String, BuyerAgentBDI> buyerList = new HashMap<String, BuyerAgentBDI>();
	
	public void start(OperatorAgentBDI m);
	public OperatorAgentBDI getMarket();
	
	public void register(BuyerAgentBDI b,String id);
	public void register(SellerAgentBDI s, String id);
	
	public void bid(Bid b);
	public void ask(Ask a);
}
