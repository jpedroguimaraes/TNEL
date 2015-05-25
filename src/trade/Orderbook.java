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
	
	public void bid(Bid b){
		int i = 0;
		while(Bids.size() > i){
			if(Bids.get(i).getPrice() < b.getPrice()){
				Bids.add(i-1,b);
				break;
			}			
		}
	}
	
	public void ask(Ask a){
		int i = 0;
		while(Asks.size() > i){
			if(Asks.get(i).getPrice() > a.getPrice()){
				Asks.add(i-1,a);
				break;
			}			
		}
	}
	
	public void regularMatch(){
		int i = 0;
		int j = 0;
		Bid b;
		Ask a;
		Match m;
		float price;
		
		
		
		while(Bids.size() > i){
			b = Bids.get(i);
			
			while(Asks.size() > j){
				a = Asks.get(j);
				
				//if(b.getPrice() >= a.getPrice())
					//m = new Match(b,a);
					
			}
		}
	}
	
}
