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
 * author Boris@de-lian.com 
 */
@SuppressWarnings("unchecked")
public class OrderPlaceDDAO {
	private static Logger logger = Logger.getLogger(OrderPlaceDDAO.class);

	public OrderPlaceDDAO() {
	}

	public Collection <OrderPickerD> findAll( int orderPicker_oid ){ // 用揀貨單編號(單頭)查出明細檔
		Query query = XPersistence.getManager()
		.createQuery("FROM OrderPickerD d where d.orderPicker_oid = :orderPicker_oid )"); //JPQL query
		query.setParameter("orderPicker_oid", orderPicker_oid);

		Collection pickDList = query.getResultList();
		
		logger.debug("OrderPickerDDAO.findAll pickDList: "+ pickDList);
		
		return pickDList; 
	}

	public Collection <OrderStoreD> getOrderDByPick ( int pickId ){ // 用 揀貨單編號 查出 訂單明細檔
		Query query = XPersistence.getManager()
		.createQuery(
				"FROM OrderStoreD o WHERE o.oid IN" +
				" (SELECT ops.orderDid FROM OrderPickSend ops WHERE ops.pickDid IN " +
				" (FROM OrderPickerD d WHERE d.orderPicker.oid = :pickId ))"
			); //JPQL query
		query.setParameter("pickId", pickId);

		Collection ops = query.getResultList();
		
		logger.debug("OrderPickerDDAO.findAll pickDList: "+ ops);
		
		return ops; 
	}

	public OrderPickSend getRelation ( int pickDid ){ // 用 揀貨單明細編號 查出和 訂單明細關連檔
		Query query = XPersistence.getManager()
		.createQuery("FROM OrderPickSend d where d.pickDid = :pickDid )"); //JPQL query
		query.setParameter("pickDid", pickDid);

		OrderPickSend ops = (OrderPickSend) query.getSingleResult();
		
		logger.debug("OrderPickerDDAO.findAll ops: "+ ops);
		
		return ops; 
	}

	public Collection <OrderPickSend> getRelations ( String pickId ){ // 用 揀貨單編號 查出 訂單明細關連檔
		Query query = XPersistence.getManager()
		.createQuery("FROM OrderPickSend d WHERE d.pickDid IN (FROM OrderPickerD where orderPicker.oid = :pickId )"); //JPQL query
		query.setParameter("pickId", pickId);

		Collection ops = query.getResultList();
		
		logger.debug("OrderPickerDDAO.getRelations pickDList: "+ ops);
		
		return ops; 
	}

//	public OrderPickerD getPickerD(int id) {
//		for (Iterator<OrderPickerD> iterator = pickDList.iterator(); iterator.hasNext();) {
//			OrderPickerD st = (OrderPickerD) iterator.next();
//			if (st.getOid() == id)
//				return st;		
//		}
//		return new OrderPickerD();
//	}

//	public boolean delete(OrderStoreD beanD){
//		Connection conn = null;
//		Statement stmt = null;
//		boolean result = false;
//		try {
//			// get connection
//			conn = DriverManager.getConnection(url, user, pwd);
//			stmt = conn.createStatement();
//			if (stmt.executeUpdate("delete from beanD where id = '" + beanD.getOid() + "'") > 0);
//			result = true;
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}finally {
//			try {
//				stmt.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//			try {
//				conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		return result;
//	}

	public boolean insert(OrderStoreD beanD){
		XPersistence.getManager().persist( beanD );
		XPersistence.commit(); // 若 UUID 重複，會有例外: org.hibernate.PersistentObjectException: detached entity passed to persist: com.kaijung.jpa.OrderStoreD
		//XHibernate.commit();		
		return true;
	}

	
//	public boolean insert(OrderStoreD beanD){
//		Connection conn = null;
//		Statement stmt = null;
//		boolean result = false;
//		try {
//			conn = DriverManager.getConnection(url, user, pwd);
//			stmt = conn.createStatement();	
//			log.debug( "UUID.randomUUID(): " + UUID.randomUUID());
////			java.lang.System.out.println( "insert into OrderStoreD(oid,quantity) " +
////					"values ('" + UUID.randomUUID().toString() + 
////					"','" + beanD.getQuantity() + "')" );
//			final String uuid = UUID.randomUUID().toString().replaceAll("-", "");
//			if (stmt.executeUpdate("insert into OrderStoreD(oid,quantity) " +
//					"values ('" + uuid + 
//					"','" + beanD.getQuantity() + "')") > 0);
//			result = true;
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}finally{
//			try {
//				stmt.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//			try {
//				conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		return result;
//	}
	
	public int update(int oid, String quantity, String memo){ //更新揀貨單的揀貨數量
		logger.debug("OrderPickerDDAO.update: " +
				"pickId: " + oid 
				+ ", quantity: "	+ quantity
				+ ", memo: "	+ memo
				);

		EntityManager em = XPersistence.getManager();
		OrderPickerD bean = em.find ( OrderPickerD.class, oid );
		logger.debug("OrderPickerD.update: orderPickerD: " + bean );
		if( bean != null ){
			bean.setQuantity(quantity);
			bean.setRemark(memo);
			try {
				em.merge( bean );
				XPersistence.commit();
			} catch (Exception e) {
				logger.error("OrderStoreD.update: " + e);
			}
			return 1; // 1:成功
		}
		return 0; // 0:失敗
	}

}
