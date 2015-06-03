package gui;

import agent.*;
import product.*;
import jadex.base.Starter;
import jadex.bridge.IComponentIdentifier;
import jadex.bridge.IExternalAccess;
import jadex.bridge.service.RequiredServiceInfo;
import jadex.bridge.service.search.SServiceProvider;
import jadex.bridge.service.types.cms.IComponentManagementService;
import jadex.commons.future.IFuture;
import jadex.commons.future.ThreadSuspendable;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Market extends JFrame {

	private static final long serialVersionUID = 1L;
	private static JPanel contentPane;
	
	public static String operatorPath = "../bin/agent/OperatorAgentBDI.class";
	public static String buyerPath = "../bin/agent/BuyerAgentBDI.class";
	public static String sellerPath = "../bin/agent/SellerAgentBDI.class";
	public static String testPath = "../bin/agent/TestAgentBDI.class";
	
	public static OperatorAgentBDI operator;
	public static List<BuyerAgentBDI> buyers = new ArrayList<BuyerAgentBDI>();
	public static List<SellerAgentBDI> sellers = new ArrayList<SellerAgentBDI>();
	public static List<Transaction> transactions = new ArrayList<Transaction>();
	
	private static ThreadSuspendable sus;
	private static IExternalAccess pl;
	private static IComponentManagementService cms;
	private static JTable buyerlist;
	private static JTable sellerlist;
	private static JTable transactionlist;


	public static void initJadex() {
		String[] params = new String[2];
		params[0] = "-gui";
		params[1] =  "false";
		IFuture<IExternalAccess> platfut	= Starter.createPlatform(params);
		sus	= new ThreadSuspendable();
		pl = platfut.get(sus);
		cms = SServiceProvider.getService(pl.getServiceProvider(), IComponentManagementService.class, RequiredServiceInfo.SCOPE_PLATFORM).get(sus);
	}
	
	public static void initOperator() {
		@SuppressWarnings("unused")
		IComponentIdentifier hwm = cms.createComponent(operatorPath, null).getFirstResult(sus);
	}
	
	public static void createBuyer() {
		@SuppressWarnings("unused")
		IComponentIdentifier hwm = cms.createComponent(buyerPath, null).getFirstResult(sus);
	}
	
	public static void createSeller() {
		@SuppressWarnings("unused")
		IComponentIdentifier hwm = cms.createComponent(sellerPath, null).getFirstResult(sus);
	}
	
	public static void createRandom() {
		Random rn = new Random();
	    switch(rn.nextInt(2)) {
	    	case 0: createBuyer();
	    		break;
	    	case 1: createSeller();
	    		break;
	    }
	}
	
	public static void init() {
		initJadex();
		while(true) {
			if(operator != null) {
				buyers.clear();
				sellers.clear();
				break;
			}
			else {
				initOperator();
				try {
				    Thread.sleep(1000);
				} catch(InterruptedException ex) {
				    Thread.currentThread().interrupt();
				}
			}
		}
	}
	
	public static void writeLog(String msg) {
		System.out.println(msg);
		updateGUI();
	}

	public static void updateGUI() {
		contentPane.repaint();
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
		addBuyer.setBounds(12, 5, 100, 35);
		addBuyer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				createBuyer();
			}
		});
		contentPane.add(addBuyer);
		JButton addRandom = new JButton("Random");
		addRandom.setBounds(232, 5, 100, 35);
		addRandom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				createRandom();
			}
		});
		contentPane.add(addRandom);
		JButton addSeller = new JButton("Seller");
		addSeller.setBounds(122, 5, 100, 35);
		addSeller.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				createSeller();
			}
		});
		contentPane.add(addSeller);
		
		buyerlist = new JTable();
		buyerlist.setBounds(43, 165, 42, -66);
		DefaultTableModel bdtm = new DefaultTableModel();
		bdtm.addColumn("Buyer");
		bdtm.addColumn("Product");
		bdtm.addColumn("Bid");
        buyerlist.setModel(bdtm);
		JScrollPane scrollPaneB = new JScrollPane();
		scrollPaneB.setLocation(12, 51);
        scrollPaneB.setSize(320, 168);
        scrollPaneB.setViewportView(buyerlist);
        contentPane.add(scrollPaneB);
		
		sellerlist = new JTable();
		sellerlist.setBounds(84, 265, 1, 1);
		DefaultTableModel sdtm = new DefaultTableModel();
		sdtm.addColumn("Seller");
		sdtm.addColumn("Product");
		sdtm.addColumn("Ask");
        sellerlist.setModel(sdtm);
		JScrollPane scrollPaneS = new JScrollPane();
		scrollPaneS.setLocation(12, 233);
        scrollPaneS.setSize(320, 168);
        scrollPaneS.setViewportView(sellerlist);
        contentPane.add(scrollPaneS);

		transactionlist = new JTable();
		transactionlist.setBounds(84, 265, 1, 1);
		DefaultTableModel tdtm = new DefaultTableModel();
		tdtm.addColumn("Seller");
		tdtm.addColumn("Buyer");
		tdtm.addColumn("Product");
		tdtm.addColumn("Value");
        transactionlist.setModel(tdtm);
		JScrollPane scrollPaneT = new JScrollPane();
		scrollPaneT.setLocation(342, 51);
        scrollPaneT.setSize(382, 350);
        scrollPaneT.setViewportView(transactionlist);
        contentPane.add(scrollPaneT);

        fillLists();
        
		setContentPane(contentPane);
	}
	
	public static void fillLists() {
		DefaultTableModel bdtm = (DefaultTableModel) buyerlist.getModel();
		bdtm.setRowCount(0);
		for(int i = 0; i < buyers.size(); i++) {
			bdtm.addRow(new Object[]{buyers.get(i).toString(),buyers.get(i).getProduct(),buyers.get(i).getPrice()});
		}
		DefaultTableModel sdtm = (DefaultTableModel) sellerlist.getModel();
		sdtm.setRowCount(0);
		for(int j = 0; j < sellers.size(); j++) {
			sdtm.addRow(new Object[]{sellers.get(j).toString(),sellers.get(j).getProduct(),sellers.get(j).getPrice()});
		}
		DefaultTableModel tdtm = (DefaultTableModel) transactionlist.getModel();
		tdtm.setRowCount(0);
		for(int k = 0; k < transactions.size(); k++) {
			tdtm.addRow(new Object[]{transactions.get(k).getSeller(),transactions.get(k).getBuyer(),transactions.get(k).getProduct(),transactions.get(k).getValue()});
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					init();
					Market frame = new Market();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}