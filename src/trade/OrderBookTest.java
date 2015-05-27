package trade;

import static org.junit.Assert.*;

import org.junit.Test;

public class OrderBookTest {

	@Test
	public void test() {
		
		Orderbook orderbook = new Orderbook();
		
		//////////Setup Bids//////////
		
		orderbook.bid(1,12);
		orderbook.bid(1,12);
		orderbook.bid(1,12);
		orderbook.bid(2,12);
		orderbook.bid(2,12);
		orderbook.bid(3,12);
		
		fail("Not yet implemented");
	}

}
