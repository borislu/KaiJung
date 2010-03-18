package com.kaijung.zk.controller;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.zkoss.zk.ui.*;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Include;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;

import com.kaijung.dao.*;
import com.kaijung.jpa.*;

import common.*;

/**
 * @author Boris
 * 
 */
public class PickListController extends GenericForwardComposer {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(PickListController.class);
//	Textbox searchBox;
	Listbox itemList;
	Include detail;
	OrderPickerDAO dao = new OrderPickerDAO();
	private static String DETAIL_URL = "pickDetail.zul";
//	private List<OrderPicker> pickList;

	public void onCreate$main(){
		logger.debug("PickListController.onCreate$main: itemList: "+ itemList);
		itemList.setSelectedIndex(0); //若沒有值的話會造成畫面錯誤
		Events.postEvent(new Event(Events.ON_SELECT, itemList)); //承上，選擇第一筆作為預設值(呼叫onSelect$itemList)
	}
	
	public void onSelect$itemList(){
		int id = ((OrderPicker)itemList.getSelectedItem().getValue()).getOid();
		detail.setSrc(DETAIL_URL + "?id=" + id);
	}	
	
//	public void onChanging$searchBox(InputEvent event) {
//		String key = event.getValue();
//		LinkedList item = new LinkedList();
//		List items = dao.findAll();
//
//		if (key.trim().length() != 0) {
//			for (Iterator iterator = items.iterator(); iterator.hasNext();) {
//				Stock st = (Stock) iterator.next();
//				if (st.getName().toLowerCase()
//						.indexOf(key.toLowerCase()) != -1)
//					item.add(st);
//				
//			}
//			itemList.setModel(new ListModelList(item));
//		} else itemList.setModel(new ListModelList(items));
//	}
	
	public List<OrderPicker> getPickList(){
		return dao.findAll();
	}
}
