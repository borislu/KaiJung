package com.kaijung.zk.controller;

/**
 * Event Controller.
 * 
 * @author robbiecheng
 */

import java.util.List;
import java.util.UUID;

import org.apache.commons.logging.*;
import org.apache.commons.logging.Log;
import org.zkoss.util.logging.*;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Listbox;

import com.kaijung.dao.*;
import com.kaijung.jpa.*;
import common.*;

public class OrderStoreDController extends GenericForwardComposer {
	/**
	 * 
	 */
	private static Logger logger = Logger.getLogger(OrderStoreDController.class);
	private static final long serialVersionUID = 1L;
	OrderStoreDDAO daoD = new OrderStoreDDAO();
	OrderStoreD beanD = new OrderStoreD();
	Listbox box;
	
//	public List getAllEvents() {
//		return daoD.findAll();
//	}	
	public void onClick$batchsave() { // insert multiple records into database		
		logger.debug( "OrderStoreDController.onClick$batchsave: quantity: "+ beanD.getQuantity() );
		// orderStore.oid = ff8081812725f779012725f7a8590001
		daoD.insert( beanD );		
	}	
//	public void onClick$update() {		
//		if (box.getSelectedItem() != null) {
//			// update database
//			daoD.update((OrderStoreD) box.getSelectedItem().getValue());
//		}
//	}
//	public void onClick$delete() {		
//		if (box.getSelectedItem() != null) {
//			daoD.delete((OrderStoreD) box.getSelectedItem().getValue());
//		}
//	}
	public OrderStoreDDAO getDaoD() {
		return daoD;
	}
	public void setDaoD(OrderStoreDDAO daoD) {
		this.daoD = daoD;
	}
	public OrderStoreD getBeanD() {
		return beanD;
	}
	public void setBeanD(OrderStoreD beanD) {
		this.beanD = beanD;
	}
}
