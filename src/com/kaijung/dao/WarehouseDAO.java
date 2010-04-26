package com.kaijung.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.persistence.*;

import org.apache.commons.logging.*;
import org.apache.commons.logging.impl.*;
import org.openxava.hibernate.*;
import org.openxava.jpa.*;

import com.kaijung.jpa.*;

import common.*;

/*
 * author Boris.lds@gmail.com 
 */
public class WarehouseDAO {
	private static Logger logger = Logger.getLogger(WarehouseDAO.class);

	public WarehouseDAO() {
	}

	@SuppressWarnings("unchecked")
	public List selectAll(){
		List<Warehouse> list = XPersistence.getManager()
		.createQuery(
		"FROM Warehouse)") //JPQL query
		.getResultList();
		return list;
	}
	
	public String getName(int oid) {
		EntityManager em = XPersistence.getManager();
		Warehouse warehouse = null;
		Query query = null;
		try {
			query = em.createQuery("SELECT o.name FROM Warehouse o WHERE o.oid = :oid");
			query.setParameter("oid", oid);
			warehouse = (Warehouse) query.getSingleResult();
			logger.debug("WarehouseDDAO.getName: warehouse: "+ warehouse);
		} catch (Exception e) {
			logger.error("WarehouseDDAO.warehouse: " + e);
		}
		return warehouse.getName();
	}

	public boolean isExisted( int oid ){ //此庫位(專櫃)是否存在
		EntityManager em = XPersistence.getManager();
		Long count;
		Boolean rtnVal = false;
		Query query = null;
		try {
			query = em.createQuery("SELECT COUNT(*) FROM Warehouse o WHERE o.oid = :oid");
			query.setParameter("oid", oid);
			count = (Long) query.getSingleResult();
			logger.debug("WarehouseDAO.isSaved: count: "+ count);
			if( count > 0 ){ rtnVal = true; }
		} catch (Exception e) {
			logger.error("WarehouseDAO.isSaved: " + e);
		}
		return rtnVal;
	}
	
	public boolean insert(OrderStoreD beanD){
		XPersistence.getManager().persist( beanD );
		XPersistence.commit(); // 若 ID 重複，會有例外: org.hibernate.PersistentObjectException: detached entity passed to persist: com.kaijung.jpa.OrderStoreD
		return true;
	}

}
