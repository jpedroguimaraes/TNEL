package agent;

import product.Product;
import gui.Market;
import jadex.bdiv3.BDIAgent;
import jadex.bdiv3.annotation.Belief;
import jadex.bdiv3.annotation.Goal;
import jadex.bdiv3.annotation.GoalRecurCondition;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;
import jadex.micro.annotation.Description;

@Agent
@Description("This is a Buyer Agent.")
public class BuyerAgentBDI {

	@Agent
	BDIAgent buyer;
	
	@Belief
	private Product product;

	@Belief(updaterate=1000)
	public long currentTime = System.currentTimeMillis();
	
	@AgentBody
	public void body() {
		Market.writeLog(this.toString() + " joined the market!");
		product = new Product();
		Market.buyers.add(this);
		Market.fillLists();
		Market.updateGUI();
	}	
	
	@Goal(recur=true)
	public class BuyGoal {
		
		public boolean sold;
		
		public BuyGoal() {
			sold = false;
		}
		
		@GoalRecurCondition(beliefs="currentTime")
		public void verifyStatus() {
			if(sold) {
				System.out.println("Sold!.");
			} else {
				System.out.println("Still here.");
			}
			System.out.println(currentTime);
		}
		/*Product produtoAdicionado;
		List<SellerAgentBDI> vendedoresProdutoAdicionado;
		int numExecucoesPlano;


		@GoalCreationCondition(beliefs="productadded")
		public BuyGoal() {
			if (products.size() > 0) {
				produtoAdicionado = products.get(products.size()-1);
				numExecucoesPlano = 0;
				vaiBuscarSellersParaOProduto();
			}
			//In the beggining, this Goal is created when setup productadded variable/belief
			else 
				produtoAdicionado = null;
		}

		@GoalRecurCondition(beliefs="currentTime")
		public boolean vaiBuscarSellersParaOProduto() {
			vendedoresProdutoAdicionado = market.getSellersOfThisProduct(produtoAdicionado);
			return true;
		}*/
	}
	
	public String getProduct() {
		return product.getName();
	}
	
	public double getPrice() {
		return product.getPrice();
	}

	public String toString() {
		return buyer.getComponentIdentifier().getLocalName();
	}
	
	public boolean isBuyer() {
		return true;
	}
	
	public boolean isSeller() {
		return false;
	}
	
}