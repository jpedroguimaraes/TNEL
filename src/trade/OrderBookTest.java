package trade;

import junit.framework.TestCase;

public class OrderBookTest extends TestCase {

	public void testPricing1() {
		Orderbook ob = new Orderbook();
		
		///Setup the Bids
		ob.bid(1,12);
		ob.bid(1,12);
		ob.bid(1,12);
		ob.bid(2,12);
		ob.bid(2,12);
		ob.bid(3,12);
		
		assertEquals(ob.Bids.size(),6);

		
		//Setup the Asks
		ob.ask(1,12);
		ob.ask(2,12);
		ob.ask(2,12);
		ob.ask(3,12);
		ob.ask(3,12);
		ob.ask(3,12);
		
		assertEquals(ob.Asks.size(),6);
		
		
		assertEquals(ob.calculateVolume(),4);
		assertEquals(ob.calculatePrice(4),2.0);
	}
}
