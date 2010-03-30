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
import common.*;

/**
 * @author Boris.lds@gmail.com
 * 
 */
@SuppressWarnings("serial")
public class PickDetailController extends GenericForwardComposer {	
	private static Logger logger = Logger.getLogger(PickDetailController.class);
	private OrderPickerDDAO pDao = new OrderPickerDDAO();
	private List<OrderPickerD> pickDList;
 
	public PickDetailController() {
		init();
	}
 
	public void init() {
		//get Picker id
		logger.debug("PickDetailController.init: id: "+ Executions.getCurrent().getParameter("id"));
		int id;
		try{
			id = Integer.parseInt((String) Executions.getCurrent().getParameter("id"));
		}catch(Exception e){
			id = 0;
			logger.debug("PickDetailController.init: " + e );
		}
//		pickDList = pDao.getPickerDList(id);
		
		logger.debug("PickDetailController.init: pickDList.size: "+ pickDList.size() );
//		for ( int i=0; i<pickDList.size(); i++) {
//			OrderPickerD pickerD = pickDList.get(i);
//			logger.debug("PickDetailController.init: pickDList: "+ pickerD.getItemid() );
//		}
	}
	public List<OrderPickerD> getPickDList(){
		return pickDList;
	}

}
