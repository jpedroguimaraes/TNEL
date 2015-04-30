package gui;

import agent.*;
import jadex.base.Starter;
import jadex.bridge.IComponentIdentifier;
import jadex.bridge.IExternalAccess;
import jadex.bridge.service.RequiredServiceInfo;
import jadex.bridge.service.search.SServiceProvider;
import jadex.bridge.service.types.cms.IComponentManagementService;
import jadex.commons.future.ThreadSuspendable;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Market extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	public static String operatorPath = "../bin/agent/OperatorAgentBDI.class";
	public static String buyerPath = "../bin/agent/BuyerAgentBDI.class";
	public static String sellerPath = "../bin/agent/SellerAgentBDI.class";
	
	private ThreadSuspendable sus;
	private IExternalAccess pl;
	private IComponentManagementService cms;
	
	private OperatorAgentBDI operator;
	private Vector<BuyerAgentBDI> buyers;
	private Vector<SellerAgentBDI> sellers; 

	public void initJadex()
	{
		sus = new ThreadSuspendable();
		pl = Starter.createPlatform(new String[0]).get(sus);
		cms = SServiceProvider.getService(pl.getServiceProvider(),IComponentManagementService.class, RequiredServiceInfo.SCOPE_PLATFORM).get(sus);
	}
	
	public void initOperator()
	{
		@SuppressWarnings("unused")
		IComponentIdentifier hwm = cms.createComponent(operatorPath, null).getFirstResult(sus);
	}
	
	public void addBuyer()
	{
		@SuppressWarnings("unused")
		IComponentIdentifier hwm = cms.createComponent(buyerPath, null).getFirstResult(sus);
	}
	
	public void addSeller()
	{
		@SuppressWarnings("unused")
		IComponentIdentifier hwm = cms.createComponent(sellerPath, null).getFirstResult(sus);
	}
	
	public void init()
	{
		initJadex();
		while(true) {
			initOperator();
			if(operator != null) {
				buyers.clear();
				sellers.clear();
				break;
			}
		}
	}
	
	public Market() {
		setTitle("TNEL Stock Exchange");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 740, 440);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(((dim.width/2)-(getSize().width/2)),((dim.height/2)-(getSize().height/2)));
		
		contentPane = new JPanel();
		contentPane.setBorder(null);
		contentPane.setLayout(null);
		JButton addBuyer = new JButton("Buyer");
		addBuyer.setBounds(30, 5, 100, 35);
		contentPane.add(addBuyer);
		JButton addRandom = new JButton("New Trader");
		addRandom.setBounds(320, 5, 100, 35);
		contentPane.add(addRandom);
		JButton addSeller = new JButton("Seller");
		addSeller.setBounds(635, 5, 100, 35);
		contentPane.add(addSeller);
		setContentPane(contentPane);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Market frame = new Market();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}