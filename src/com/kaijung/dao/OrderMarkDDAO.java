package com.kaijung.dao;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.persistence.*;

import org.apache.commons.logging.*;
import org.apache.commons.logging.impl.*;
import org.openxava.hibernate.*;
import org.openxava.jpa.*;
import org.openxava.util.*;
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
	
	private Connection getConn (){
	   Connection conn = null;
		try {
			conn = DataSourceConnectionProvider.getByComponent("OrderMark").getConnection();
	   } catch (Exception e1) {
			logger.error("OrderMarkDDAO.getConn: " + e1 );
	    }
	   return conn;
	}

	public Collection <OrderMarkD> getMarksById( String markid ){ //以備貨單編號找出備貨單明細
		Query query = XPersistence.getManager()
		.createQuery("FROM OrderMarkD o WHERE o.orderMark.oid = :markid"); //JPQL query
		query.setParameter("markid", markid );
		Collection details = query.getResultList();
		
		logger.debug("OrderMarkDDAO.getMarksById details: "+ details);
		
		return details; 
	}

	public boolean isSaved( String oid ){ //此補貨單編號是否已儲存
		EntityManager em = XPersistence.getManager();
		Long count;
		Boolean rtnVal = false;
		Query query = null;
		try {
			query = em.createQuery("SELECT COUNT(*) FROM OrderMark o WHERE o.oid = :oid");
			query.setParameter("oid", oid);
			count = (Long) query.getSingleResult();
			logger.debug("OrderMarkDDAO.isSaved: count: "+ count);
			if( count > 0 ){ rtnVal = true; }
		} catch (Exception e) {
			logger.error("OrderMarkDDAO.isSaved: " + e);
		}
		return rtnVal;
	}
	
	public boolean insert( String oid, String quantity, String memo){
		EntityManager em = XPersistence.getManager();
		logger.debug("OrderMarkDDAO.insert oid: "+ oid);
//		OrderMark o = null ;//(OrderMark) em.find( OrderMark.class, oid );
		try {
//			XPersistence.getManager().flush();
//			for( int i=0; i<10; i++ ){
//				o = (OrderMark) new OrderMarkDAO().findMark( oid );
//				if( o != null ){
//					break;
//				}else{
//					Thread.sleep( 500 );
//				}
//			}
//			logger.debug("OrderMarkDDAO.insert OrderMark: "+ o );
	   } catch (Exception e1) {
			logger.error("OrderMarkDDAO.insert: " + e1 );
	    }
		OrderMarkD d = new OrderMarkD();
		d.setPresetQty( quantity );
		d.setRemark( memo );
//		d.setOrderMark( o );
		XPersistence.getManager().persist( d );
		XPersistence.commit(); // 若 UUID 重複，會有例外: org.hibernate.PersistentObjectException: detached entity passed to persist: com.kaijung.jpa.OrderStoreD
		logger.debug("OrderMarkDDAO.insert: oid: " + d.getOid() );
		updateColumn( d.getOid(), "orderMark_oid", oid );
		return true;
	}

	public int updateColumn (int oid, String columnName, String value ){ //更新備貨單的備貨數量
		if( columnName != null ){
			PreparedStatement pstmt = null;
			Connection conn = getConn();
			try {
				pstmt = conn.prepareStatement(
		        "UPDATE OrderMarkD SET "+ columnName +" = ? WHERE oid = ?");
				pstmt.setString(1, value); 
				pstmt.setInt(2, oid); 
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return 1;
	}
	
	public int update(String oid, String quantity, String memo){ //更新備貨單的備貨數量
		logger.debug("OrderMarkDDAO.update: " +
				"OrderMarkD oid: " + oid 
				+ ", quantity: "	+ quantity
				+ ", memo: "	+ memo
				);

		EntityManager em = XPersistence.getManager();
		OrderMarkD bean = em.find ( OrderMarkD.class, oid );
		logger.debug("OrderMarkDDAO.update: orderMarkD: " + bean );
		if( bean != null ){
			bean.setRealQty( quantity);
			bean.setRemark(memo);
			try {
				em.merge( bean );
				XPersistence.commit();
			} catch (Exception e) {
				logger.error("OrderMarkDDAO.update: " + e);
			}
			return 1; // 1:成功
		}
		return 0; // 0:失敗
	}

}
