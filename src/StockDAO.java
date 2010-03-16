import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.zkoss.idom.input.SAXBuilder;
import org.zkoss.zk.ui.Executions;

/* StockDAO.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		2009/7/10 �W�� 11:21:33 , Created by robbiecheng
}}IS_NOTE

Copyright (C) 2009 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under GPL Version 2.0 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
 */

/**
 * @author robbiecheng
 *
 */
public class StockDAO {

	private List<Stock> stocks = new LinkedList<Stock>();
	
	/**
	 * 
	 */
	public StockDAO() {
		super();
		init();
	}	
	
	/**
	 * @param parseInt
	 * @return
	 */
	public Stock getStock(int id) {
		for (Iterator<Stock> iterator = stocks.iterator(); iterator.hasNext();) {
			Stock st = (Stock) iterator.next();
			if (st.getId() == id)
				return st;		
		}
		return new Stock();
	}
	
	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findAll() {
		return new LinkedList((Collection) stocks); 
	}
	
	private void init(){
		try {
			String file = Executions.getCurrent().getDesktop().getWebApp().getRealPath("/") + "data.xml";
//	        File fileObject = new File(file);
			SAXBuilder builder = new SAXBuilder(false, false);
			Document doc;		
			doc = builder.build(file);			
			Element elm = XMLs.queryElement(doc.getDocumentElement(),"/data");			
			NodeList nodes = XMLs.queryNodeList(elm,"/data/item");
			for (int i = 1; i <= nodes.getLength(); i++) {
				Stock stock = new Stock();
				Node id = XMLs.queryNode(elm, "/data/item["+ i +"]/id");				
				Node name = XMLs.queryNode(elm, "/data/item["+ i +"]/name");				
				NodeList prices = XMLs.queryNodeList(elm, "/data/item[" + i + "]/price/data");
				List<Price> priceItems = new ArrayList<Price>();
				for (int j = 0; j < prices.getLength(); j++) {				
					String[] split =prices.item(j).getFirstChild().getNodeValue().split(",");
					Price p = new Price();
					p.setDate(split[0]);
					p.setOpen(Double.parseDouble(split[1]));
					p.setHigh(Double.parseDouble(split[2]));
					p.setLow(Double.parseDouble(split[3]));
					p.setClose(Double.parseDouble(split[4]));
					p.setVolumn(Integer.parseInt(split[5]));
					priceItems.add(p);
				}				
				stock.setId(Integer.parseInt(id.getFirstChild().getNodeValue()));
				stock.setName(name.getFirstChild().getNodeValue());
				stock.setPriceItems(priceItems);				
				stocks.add(stock);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	

}
