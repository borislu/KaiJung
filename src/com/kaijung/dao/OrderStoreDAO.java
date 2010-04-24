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

public class OrderStoreDAO { // 應為 OrderStoreDAO
	private static Logger logger = Logger.getLogger(OrderStoreDAO.class);
	
	@SuppressWarnings("unchecked")
	public List<OrderStore> getOrderDetails( int wareid ){
		
		Query query = XPersistence.getManager()
		.createQuery("SELECT o FROM OrderStoreD d, OrderStore o" 
				+" WHERE d.orderStore.oid = o.oid" 
				+" AND d.orderStore.warehouse.oid = :wareid" 
		); //JPQL query
		query.setParameter("wareid", wareid );
		List<OrderStore> list = query.getResultList();
		logger.debug( "OrderDiffDAO.getOrderDetails: OrderStore: "+ (OrderStore)list.get( 0 ) );
		return list;
	}

}
