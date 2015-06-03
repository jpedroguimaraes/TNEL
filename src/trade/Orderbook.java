package trade;

import java.util.ArrayList;

public class Orderbook {

	ArrayList<Bid> Bids;
	ArrayList<Ask> Asks;
	ArrayList<Match> Matches;
	
	public Orderbook(){
		Bids = new ArrayList<Bid>();
		Asks = new ArrayList<Ask>();
		Matches = new ArrayList<Match>();
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
		Bid b = Bids.get(0);
		Ask a = Asks.get(0);
		Match m;
		int i = 0;
		
		while(b.getPrice() >= a.getPrice()){
			m = new Match(b,a);
			Matches.add(m);
			i++;
			b = Bids.get(i);
			a = Asks.get(i);
		}
		
		float price = calculatePrice(i);
		for(int j = 0; j < Matches.size(); j++){
			Matches.get(j).setPrice(price);
		}
	}
	
	public void maximalVolumeMatch(){
		
		int volume = calculateVolume();		
		int i = 0, j = volume-1;
		float price;
		
		Bid b;
		Ask a;		
		Match m;
		
		while(j>=0){
			b = Bids.get(i);
			a = Asks.get(j);
			m = new Match(b,a);
			price = (b.getPrice()+a.getPrice())/2;			
			m.setPrice(price);
			Matches.add(m);
			i++;
			j--;
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
