package com.kaijung.zk.controller;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.zkoss.zk.ui.*;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.*;

import com.kaijung.dao.*;
import com.kaijung.jpa.*;

/**
 * @author Boris.lds@gmail.com
 * 
 */
@SuppressWarnings("serial")
public class PickDetailController extends GenericForwardComposer {	
	private OrderPickerDDAO dao = new OrderPickerDDAO();
	private CategoryModel cateModel;			
	private List<OrderPickerD> items;
 
	public PickDetailController() {
		init();
	}
 
	public void init() {
		//get Picker id
		int id = Integer.parseInt((String) Executions.getCurrent().getParameter("id"));		
		OrderPicker stock = dao.getPicker(id);
//		items = (List<OrderPickerD>) stock.getDetails();	
	}
	public List<OrderPickerD> getPickDList(){
		return items;
	}

}
