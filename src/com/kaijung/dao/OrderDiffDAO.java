//Coding by Jason
package com.kaijung.dao;

import java.sql.*;
import java.util.*;
import java.lang.System;

import javax.persistence.*;

import org.openxava.calculators.*;
import org.openxava.jpa.*;

import com.kaijung.calculators.*;
import com.kaijung.jpa.*;

import common.*;

public class OrderDiffDAO {
	private static Logger logger = Logger.getLogger(OrderDiffDAO.class);

	public OrderDiffDAO() {
	}

	@SuppressWarnings("unchecked")
	public List<OrderDiffBean> getOrderDiff( int wareid ){
		
		List list = new ArrayList();

		boolean isExisted = false; 
		
		String wareCond = null;  
		if( new WarehouseDAO().isExisted( wareid ) ){
			isExisted = true;
			wareCond = " AND o.wareId = w.oid = :wareid "; // 若庫位(專櫃)存在才加條件，若不存在則不分專櫃全選
		}else{
			wareCond = " AND o.wareId = w.oid "; // 若庫位(專櫃)不存在，不分專櫃全選
		}
		
		Query query = XPersistence.getManager()
		.createNativeQuery("SELECT DISTINCT * "
		+" FROM OrderStore o, OrderStoreD d, Warehouse w, Item i" 
		+" WHERE d.orderStore_oid = o.oid "
		+ wareCond
		+" AND d.itemid = i.oid" 
		, "detailsAndOrder"); //
		if( isExisted ){
			query.setParameter("wareid", wareid );
		}
		/*
		SELECT DISTINCT * 
		FROM OrderStore o, OrderStoreD d, Warehouse w, Item i 
		WHERE d.orderStore_oid = o.oid 
		AND o.wareId = w.oid = 1 
		AND d.itemid = i.oid 
		 */
		
		List <Object[]> objAryList = query.getResultList();

		for (Object[] objAry : objAryList) {
			OrderDiffBean diff = new OrderDiffBean();
			
			OrderStore order = (OrderStore) objAry[0];
			OrderStoreD orderD = (OrderStoreD) objAry[1];
			
			diff.setWareName( order.getWarehouse().getName() );
			diff.setArticleno( orderD.getItem().getArticleno() );
			diff.setColorName( orderD.getItem().getColor().getSname() );
			diff.setOrderQty( orderD.getQuantity() );
			list.add( diff );
		}
		return list;
	}

	/*
	 * SELECT d FROM OrderStoreD d, OrderStore o, Warehouse w, Item i" 
				+" WHERE d.orderStore.oid = o.oid" 
				+" AND o.warehouse.oid = w.oid"
				+" AND d.item.oid = i.oid"
				+" AND o.warehouse.oid = :wareid
	 */
}
