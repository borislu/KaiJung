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
public class OrderPickerDDAO {
	private static Logger logger = Logger.getLogger(OrderPickerDDAO.class);

	private List pickDList;

	public OrderPickerDDAO() {
	}

	public List<OrderPickerD> findAll( int orderPicker_oid ){
		EntityManagerFactory f =
			Persistence.createEntityManagerFactory("default");//You need an EntityManagerFactory to create a manager
		EntityManager manager = f.createEntityManager(); //You create the manager
		manager.getTransaction().begin(); //You have to start a transaction		

		Query query = XPersistence.getManager()
//		.createQuery("from OrderPickerD)"); //JPQL query
//		.createQuery("from OrderPickerD as d where d.orderPicker_oid = :orderPicker_oid )"); //JPQL query
//		query.setParameter("orderPicker_oid", orderPicker_oid);
		.createNativeQuery("select * from OrderPickerD where orderPicker_oid = '"+ orderPicker_oid +"'");

		logger.debug("OrderPickerDDAO.findAll query: "+ query);

		pickDList = query.getResultList();
		
//		OrderPickerD opd = new OrderPickerD();
//		opd.setItemid(85);
//		pickDList.add(opd);
//		opd.setItemid(86);
//		pickDList.add(opd);

		logger.debug("OrderPickerDDAO.findAll pickDList: "+ pickDList);
		for( int i=0; i<pickDList.size(); i++ ){
			OrderPickerD opd = new OrderPickerD();
//			logger.debug( pickDList.get(i) );
//			logger.debug( "OrderPickerDDAO.findAll query: "+ (Object[])pickDList.get(i) ) ;
			try{
				logger.debug( "OrderPickerDDAO.findAll query: i: "+ i + ", " + (Object[])pickDList.get(i) ) ;
				Object[] strArr = (Object[])pickDList.get(i);
//				opd.setOid ( Integer.parseInt( ""+ strArr[0] ) );
//				opd.setItemid( Integer.parseInt( (String)strArr[1] ) );
//				opd.setQuantity( Integer.parseInt( (String)strArr[2] ) );
				opd.setStatus( (String)strArr[3] );
				opd.setRemark( (String)strArr[4] );
//				opd.setReserve1( (String)strArr[5] );
//				opd.setReserve2( (String)strArr[6] );
//				opd.setReserve3( (String)strArr[7] );
//				opd.setReserve4( (String)strArr[8] );
//				opd.setReserve5( (String)strArr[9] );
//				opd.setReserve6( (String)strArr[10] );
//				opd.setReserve7( (String)strArr[11] );
//				opd.setReserve8( (String)strArr[12] );
//				opd.setReserve9( (String)strArr[13] );
//				opd.setReserve10( (String)strArr[14] );
//				opd.setOrderPicker_oid( Integer.parseInt( (String)strArr[15] ) );
				
				pickDList.set( i , opd );
			}catch( Exception e ){
				logger.error( "OrderPickerDDAO.findAll query: "+ e ) ;
			}
		}
		return pickDList; 
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
	
//	public boolean update(OrderStoreD beanD){
//		Connection conn = null;
//		Statement stmt = null;
//		boolean result = false;
//		try {
//			// get connection
//			conn = DriverManager.getConnection(url, user, pwd);
//		    stmt = conn.createStatement();				
//			if (stmt.executeUpdate("update event set name = '" + beanD.getName() + 
//					"', priority = " + beanD.getPriority() + ", date = '" + 
//					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(beanD.getDate())+ 
//					"' where id = '" + beanD.getId() + "'") > 0);
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

}
