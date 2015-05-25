package trade;

public class Match {

	Bid bid;
	Ask ask;
	float price;
	
	public Match(Bid b, Ask a){
		this.bid = b;
		this.ask = a;		
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Bid getBid() {
		return bid;
	}

	public Ask getAsk() {
		return ask;
	}
}
