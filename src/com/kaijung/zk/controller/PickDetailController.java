package com.kaijung.zk.controller;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.*;

import com.kaijung.dao.*;
import com.kaijung.jpa.*;

/**
 * @author Boris@de-lian.com
 * 
 */
public class PickDetailController extends GenericForwardComposer {	
	private OrderPickerDDAO dao = new OrderPickerDDAO();
	private CategoryModel cateModel;			
	private List items;
 
	public PickDetailController() {
		init();
	}
 
	public void init() {
		//get stock id
//		int id = Integer.parseInt((String) Executions.getCurrent().getParameter("id"));		
//		Stock stock = dao.getStock(id);
//		items = stock.getPriceItems();		
//		//create category model for chart
//		cateModel = new SimpleCategoryModel();
//		for (Iterator iterator = items.iterator(); iterator.hasNext();) {
//			Price price = (Price) iterator.next();
//			cateModel.setValue(stock.getName(), price.getDate(), price.getClose());
//		}
	}
	public List getPrices(){
		return items;
	}
	public CategoryModel getCateModel() {
		return cateModel;
	}	

}
