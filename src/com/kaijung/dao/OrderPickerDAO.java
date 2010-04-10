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

import com.kaijung.calculators.*;
import com.kaijung.jpa.*;

import common.*;

/*
 * author Boris.lds@gmail.com 
 */
public class OrderPickerDAO {
	private static Logger logger = Logger.getLogger(OrderPickerDAO.class);

	private List<OrderPicker> pickList ;
	
//	private String url = "jdbc:mysql://localhost:3306/KaiJung?useUnicode=true&amp;characterEncoding=utf8";//	private String url = "jdbc:hsqldb:file:/hsqldb/event";
//	private String user = "ldstw";
//	private String pwd = "ldstw";

	public OrderPickerDAO() {
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
		selectAll();
	}

	@SuppressWarnings("unchecked")
	private void selectAll(){
		EntityManagerFactory f =
			Persistence.createEntityManagerFactory("default");//You need an EntityManagerFactory to create a manager
		EntityManager manager = f.createEntityManager(); //You create the manager
		manager.getTransaction().begin(); //You have to start a transaction		

		pickList = XPersistence.getManager()
		.createQuery(
		"from OrderPicker)") //JPQL query
		.getResultList();
	}
	
	public List<OrderPicker> findAll(){
		logger.debug("OrderPickerDAO.findAll pickList: "+ pickList);
		return pickList; 
	}

	public OrderPicker getPicker(int id) {
		for (Iterator<OrderPicker> iterator = pickList.iterator(); iterator.hasNext();) {
			OrderPicker st = (OrderPicker) iterator.next();
			if (st.getOid() == id)
				return st;		
		}
		return new OrderPicker();
	}
	/* 將變更寫入揀貨單，同時產生撥出單 */
	@SuppressWarnings("unchecked")
	public int submit ( String pickid, int empid, String wareid ) throws Exception{
		Query query = XPersistence.getManager().createQuery(
				"FROM OrderPickerD WHERE orderPicker.oid = :pickid"
			); //JPQL query
		query.setParameter("pickid", pickid);
		logger.debug("OrderPickerDAO.submit: pickid: "+ pickid );
		List <OrderPickerD> orderPickerDs = query.getResultList();
		logger.debug("OrderPickerDAO.submit: orderPickerDs: "+ orderPickerDs );
		
		ReadCodeGenerator rg = new ReadCodeGenerator();//組合撥出單的可讀碼
		rg.setDocType("C");
		rg.setTableName("SeqGenOrderStore");
		rg.setWareId( wareid);
		
		Employee user = new Employee();
		user.setOid(empid);
		
		java.util.Date today = new java.util.Date();
		
		OrderSender orderSender = new OrderSender();
		orderSender.setSender( user);
		orderSender.setModifier( user);
		orderSender.setModifyTime( today);
		orderSender.setCreateTime( today);
		orderSender.setReadCode( (String)rg.calculate() );
		XPersistence.getManager().persist( orderSender );
		XPersistence.commit(); // 若 ID 重複，會有例外: org.hibernate.PersistentObjectException: detached entity passed to persist: com.kaijung.jpa.OrderStoreD
		int sendId = orderSender.getOid();
		logger.debug("OrderPickerDAO.submit: orderSender.oid: "+ sendId );
		
		for( int i=0; i<orderPickerDs.size(); i++ ){
			OrderSenderD p = new OrderSenderD();
			OrderPickerD d = (OrderPickerD) orderPickerDs.get(i);
			p.setOrderSender( orderSender );
			p.setItem	( d.getItem());
//			p.setQuantity( d.getQuantity());
			XPersistence.getManager().persist( p );
			XPersistence.commit(); // 若 ID 重複，會有例外: org.hibernate.PersistentObjectException: detached entity passed to persist: com.kaijung.jpa.OrderStoreD
		}
		
		OrderPickSend rel = new OrderPickSend();
		rel.setOrderDid( pickid);
		rel.setPickDid( sendId);
		rel.setWareId( Integer.parseInt( wareid));
		XPersistence.getManager().persist( rel );
		XPersistence.commit(); // 若 ID 重複，會有例外: org.hibernate.PersistentObjectException: detached entity passed to persist: com.kaijung.jpa.OrderStoreD
		
		return 1;
	}

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

//	public boolean insert(OrderStoreD beanD){
//		XPersistence.getManager().persist( beanD );
//		XPersistence.commit(); // 若 UUID 重複，會有例外: org.hibernate.PersistentObjectException: detached entity passed to persist: com.kaijung.jpa.OrderStoreD
//		//XHibernate.commit();		
//		return true;
//	}

	
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
