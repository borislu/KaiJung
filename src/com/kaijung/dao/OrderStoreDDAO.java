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
import org.apache.commons.logging.*;
import org.apache.commons.logging.impl.*;
import org.openxava.hibernate.*;
import org.openxava.jpa.*;
import com.kaijung.jpa.*;
import common.*;


public class OrderStoreDDAO {
	private static Logger logger = Logger.getLogger(OrderStoreDDAO.class);

	private String url = "jdbc:mysql://localhost:3306/KaiJung?useUnicode=true&amp;characterEncoding=utf8";//	private String url = "jdbc:hsqldb:file:/hsqldb/event";
	private String user = "ldstw";
	private String pwd = "ldstw";

	public OrderStoreDDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
//	public List findAll(){
//		Statement stmt = null;
//		Connection conn = null;
//		List allOrderStoreDs = new ArrayList();
//		try {
//			// get connection
//			conn = DriverManager.getConnection(url, user, pwd);
//			stmt = conn.createStatement();
//			ResultSet rs = stmt.executeQuery("select * from event");
//
//			// fetch all events from database
//			OrderStoreD evt;
//			while (rs.next()) {
//				evt = new OrderStoreD();
//				evt.setId(rs.getString(1));
//		        evt.setName(rs.getString(2));
//				evt.setPriority(rs.getInt(3));
//				evt.setDate(rs.getDate(4));
//
//				allOrderStoreDs.add(evt);
//			}
//			
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
//		return allOrderStoreDs;
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
