package trade;

import java.util.ArrayList;
import java.util.Vector;

public class Orderbook {

	ArrayList<Bid> Bids;
	ArrayList<Ask> Asks;
	ArrayList<Match> Matches;
	
	public Orderbook(){
		Bids = new ArrayList<Bid>();
		Asks = new ArrayList<Ask>();
	}
	
	public void bid(float p, int pID){
		Bid b = new Bid(p,1,pID);
		int i = 0;
	
		boolean done = false;
		
		if(Bids.size()==0){			
			Bids.add(b);
			done = true;
		}
		else while(Bids.size() > i){
			if(Bids.get(i).getPrice() < b.getPrice()){
				Bids.add(i,b);
				done = true;
				break;
			}
			i++;
		}
		
		if(!done)
			Bids.add(b);
	}
	
	public void ask(float p, int pID){
		Ask a = new Ask(p,1,pID);
		int i = 0;
		
		boolean done = false;
		
		if(Asks.size()==0){
			Asks.add(a);
			done = true;
		}
		while(Asks.size() > i){
			if(Asks.get(i).getPrice() > a.getPrice()){
				Asks.add(i,a);
				break;
			}
			i++;
		}
		i++;
		if(!done)
			Asks.add(a);
	}
	
	public void regularMatch(){
		
		int quantity = calculateVolume();
		float price = calculatePrice(quantity);
		Match m;
		
		for(int i = 0; i <= quantity; i++){
			m = new Match(Bids.get(i),Asks.get(i));
			m.setPrice(price);
			Matches.add(m);
		}
	}
	
	
	public float calculatePrice(int quantity){
		float price = 0;
		
		for(int i = 0; i < quantity; i++){
			price+=Asks.get(i).getPrice();
			price+=Bids.get(i).getPrice();
		}
		
		price = price/(quantity*2);
		
		return price;
	}
	
	public int calculateVolume(){
		int volume = 0;
		
		float HighestBid = Bids.get(0).getPrice();
		float LowestAsk = Asks.get(0).getPrice();		
		int marginalBid=0, marginalAsk=0;
		
		if(HighestBid > LowestAsk){ 
			
			for(int i = 0; i < Asks.size();i++){
				if(HighestBid <= Asks.get(i).getPrice())
					marginalAsk = i;
			}
			
			for(int i = 0; i < Bids.size();i++){
				if(LowestAsk >= Bids.get(i).getPrice())
					marginalBid = i;
			}			
			
			volume = marginalAsk;
			if(volume > marginalBid)
				volume = marginalBid;
			
		}
		return volume-1;
	}
	
}
