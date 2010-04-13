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
import org.zkoss.zk.ui.*;

import com.kaijung.jpa.*;

import common.*;

/*
 * author Boris.lds@gmail.com
 */
@SuppressWarnings("unchecked")
public class OrderMarkDDAO {
	private static Logger logger = Logger.getLogger(OrderMarkDDAO.class);

	public OrderMarkDDAO() {
	}

	public Collection <OrderMarkD> getMarksById( int markid ){ //以備貨單編號找出備貨單明細
		Query query = XPersistence.getManager()
		.createQuery("FROM OrderMarkD o WHERE o.orderMark.oid = :markid"); //JPQL query
		query.setParameter("markid", markid );
		Collection details = query.getResultList();
		
		logger.debug("OrderMarkDDAO.getMarksById details: "+ details);
		
		return details; 
	}

	public boolean insert(OrderStoreD beanD){
		XPersistence.getManager().persist( beanD );
		XPersistence.commit(); // 若 UUID 重複，會有例外: org.hibernate.PersistentObjectException: detached entity passed to persist: com.kaijung.jpa.OrderStoreD
		//XHibernate.commit();		
		return true;
	}

	public int update(int oid, String quantity, String memo){ //更新揀貨單的揀貨數量
		logger.debug("OrderMarkDDAO.update: " +
				"pickId: " + oid 
				+ ", quantity: "	+ quantity
				+ ", memo: "	+ memo
				);

		EntityManager em = XPersistence.getManager();
		OrderMarkD bean = em.find ( OrderMarkD.class, oid );
		logger.debug("OrderMarkD.update: orderMarkD: " + bean );
		if( bean != null ){
//			bean.setQuantity(quantity);
			bean.setRemark(memo);
			try {
				em.merge( bean );
				XPersistence.commit();
			} catch (Exception e) {
				logger.error("OrderMarkD.update: " + e);
			}
			return 1; // 1:成功
		}
		return 0; // 0:失敗
	}

}
