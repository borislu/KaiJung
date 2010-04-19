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

	public Collection <Stock> getStocksByPlaceId( int placeid ){ // 用上架單編號(單頭)查出明細檔
		Query query = XPersistence.getManager()
		.createQuery("FROM Stock o WHERE o.item.oid IN (SELECT p.item.oid FROM OrderPlaceD p WHERE p.orderPlace.oid = :placeid ORDER BY p.oid DESC) )"); //JPQL query
		query.setParameter("placeid", placeid);
		//select * FROM Stock o WHERE o.itemid IN (SELECT p.itemid FROM OrderPlaceD p WHERE p.orderPlace_oid = '1' ORDER BY p.oid DESC)
		Collection stocks = query.getResultList();
		
		logger.debug("OrderPlaceDDAO.getStocksByPlaceId stocks: "+ stocks);
		
		return stocks; 
	}

	public Stock getStockByItem ( int itemid ){ // 用 商品編號 查出 該商品所在的某一貨架
		Query query = XPersistence.getManager()
		.createQuery(
				"FROM Stock o WHERE o.item.oid = :itemid order by o.volume desc"
			); //JPQL query
		query.setParameter("itemid", itemid);

		List <Stock> beans = query.getResultList() ;
		logger.debug("OrderPlaceDDAO.getStockByItem beans: "+ beans);
		
		return beans.get(0); 
	}

//	public OrderPlaceD getPlaceD(int id) {
//		for (Iterator<OrderPlaceD> iterator = pickDList.iterator(); iterator.hasNext();) {
//			OrderPlaceD st = (OrderPlaceD) iterator.next();
//			if (st.getOid() == id)
//				return st;		
//		}
//		return new OrderPlaceD();
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
		logger.debug("OrderPlaceDDAO.update: " +
				"pickId: " + oid 
				+ ", quantity: "	+ quantity
				+ ", memo: "	+ memo
				);

		EntityManager em = XPersistence.getManager();
		OrderPlaceD bean = em.find ( OrderPlaceD.class, oid );
		logger.debug("OrderPlaceD.update: orderPlaceD: " + bean );
		if( bean != null ){
//			bean.setQuantity(quantity);
			bean.setRemark(memo);
			try {
				em.merge( bean );
				XPersistence.commit();
			} catch (Exception e) {
				logger.error("OrderPlaceD.update: " + e);
			}
			return 1; // 1:成功
		}
		return 0; // 0:失敗
	}

}
