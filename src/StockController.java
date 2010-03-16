import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Include;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;

/* StockController.java

 {{IS_NOTE
 Purpose:

 Description:

 History:
 2009/7/10 ¤W¤È 9:29:25 , Created by robbiecheng
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
public class StockController extends GenericForwardComposer {	
	Textbox searchBox;
	Listbox itemList;
	Include detail;
	StockDAO dao = new StockDAO();
	private static String DETAIL_URL = "price.zul";
	
	public void onCreate$main(){		
		itemList.setSelectedIndex(0);		
		Events.postEvent(new Event(Events.ON_SELECT, itemList));
	}
	
	public void onSelect$itemList(){
		int id = ((Stock)itemList.getSelectedItem().getValue()).getId();
		detail.setSrc(DETAIL_URL + "?id=" + id);
	}	
	
	public void onChanging$searchBox(InputEvent event) {
		String key = event.getValue();
		LinkedList item = new LinkedList();
		List items = dao.findAll();

		if (key.trim().length() != 0) {
			for (Iterator iterator = items.iterator(); iterator.hasNext();) {
				Stock st = (Stock) iterator.next();
				if (st.getName().toLowerCase()
						.indexOf(key.toLowerCase()) != -1)
					item.add(st);
				
			}
			itemList.setModel(new ListModelList(item));
		} else itemList.setModel(new ListModelList(items));
	}
	
	public List getStocks(){
		return dao.findAll();
	}
}
