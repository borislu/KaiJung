package com.kaijung.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.*;

import org.apache.commons.logging.*;
import org.apache.commons.logging.impl.*;
import org.openxava.hibernate.*;
import org.openxava.jpa.*;

import com.kaijung.jpa.*;

import common.*;


public class OrderStoreDDAO {
	private static Logger logger = Logger.getLogger(OrderStoreDDAO.class);


	public boolean insert(OrderStoreD beanD){
		XPersistence.getManager().persist( beanD );
		XPersistence.commit(); // 若 UUID 重複，會有例外: org.hibernate.PersistentObjectException: detached entity passed to persist: com.kaijung.jpa.OrderStoreD
		//XHibernate.commit();		
		return true;
	}

	@SuppressWarnings("unchecked")
	public List selectAll( String none ){
		logger.debug("OrderStoreDDAO.selectAll: " );
		List<OrderStoreD> list = XPersistence.getManager()
		.createQuery(
		"FROM OrderStoreD)") //JPQL query
		.getResultList();
		logger.debug("OrderStoreDDAO.selectAll: list: "+ list );
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<OrderStoreD> getListByWareid( int wareid ){
		
		Query query = XPersistence.getManager()
		.createQuery("SELECT d FROM OrderStoreD d, OrderStore o" 
				+" WHERE d.orderStore.oid = o.oid" 
				+" AND d.orderStore.warehouse.oid = :wareid" 
		); //JPQL query
		query.setParameter("wareid", wareid );
		List<OrderStoreD> list = query.getResultList();
		logger.debug( "OrderDiffDAO.getOrderDetails: OrderStoreD: "+ (OrderStoreD)list.get( 0 ) );
		return list;
	}
	

}
