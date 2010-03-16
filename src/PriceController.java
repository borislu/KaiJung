import java.util.Iterator;
import java.util.List;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericAutowireComposer;
import org.zkoss.zul.CategoryModel;
import org.zkoss.zul.SimpleCategoryModel;

/* PriceController.java

 {{IS_NOTE
 Purpose:

 Description:

 History:
 2009/7/10 ¤W¤È 11:09:55 , Created by robbiecheng
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
public class PriceController extends GenericAutowireComposer {
	
	private StockDAO dao = new StockDAO();
	private CategoryModel cateModel;			
	private List items;
	
	public PriceController() {
		init();
	}
	
	public void init() {
		//get stock id
		int id = Integer.parseInt((String) Executions.getCurrent().getParameter("id"));		
		Stock stock = dao.getStock(id);
		items = stock.getPriceItems();		
		//create category model for chart
		cateModel = new SimpleCategoryModel();
		for (Iterator iterator = items.iterator(); iterator.hasNext();) {
			Price price = (Price) iterator.next();
			cateModel.setValue(stock.getName(), price.getDate(), price.getClose());
		}
	}
	public List getPrices(){
		return items;
	}
	public CategoryModel getCateModel() {
		return cateModel;
	}	
}
