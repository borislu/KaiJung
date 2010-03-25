//Coding by Jason
package com.kaijung.dao;

import java.sql.*;
import java.util.*;
import java.lang.System;

import javax.persistence.*;

import org.openxava.jpa.*;

import com.kaijung.jpa.*;

import common.*;

public class OrderStoreNewDAO {
	  private final String url = "jdbc:mysql://localhost:3306/KaiJung?useUnicode=true&amp;characterEncoding=utf8";
	  private final String user = "ldstw";
	  private final String pwd = "ldstw";
	  private static Logger logger = Logger.getLogger(OrderStoreNewDAO.class);
	  
	  public OrderStoreNewDAO() {
	    try {
	      // 載入MYSQLDB 的 JDBC 驅動程式
	      Class.forName("com.mysql.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	      e.printStackTrace();
	    }
	  }
	  
	  public void insert(String oid, String barcode, String quantity, String modifyid, String isCustOrder, String memo, String orderStoreOid){
		    Connection conn = null;
		    Statement stmt2 = null;
		    Statement stmt = null;
		    try {
		      conn = DriverManager.getConnection(url, user, pwd);
		      stmt2 = conn.createStatement();
		      ResultSet rs = stmt2.executeQuery("select i.oid from Item i where i.barcode = '" + barcode + "'");
		      rs.next();
		      int itemid = rs.getInt("oid");
		      stmt = conn.createStatement();
		      logger.debug("OrderStoreNewDAO.insert: quantity: "+ quantity);
		      stmt.executeUpdate("insert into OrderStoreD(oid,itemid,quantity,modifyid,isCustOrder,remark,orderStore_oid) " +
		          "values ('" + oid + "','" + itemid + "','" + quantity + "','" + modifyid + "','" + isCustOrder + "','" + memo+ "','"+ orderStoreOid +"')");
		    } catch (SQLException e) {
		      e.printStackTrace();
		    } finally {
		      try {
		    	  stmt2.close();
		        stmt.close();
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
	  //用條碼去找出貨號顏色價錢
	public Item getItemByBarcode(String barcode) throws SQLException{
				EntityManager em = XPersistence.getManager();
				Query query = null;
				Item result = null;
				try{
			      logger.debug("OrderStoreNewDAO.getItemByBarcode: barcode: "+ barcode );
					query = em.createQuery(
							"SELECT i FROM Item AS i"
							+ " WHERE i.barcode = '"+ barcode +"'"
							);
					result = (Item)query.setMaxResults(1).getSingleResult();
			      logger.debug("OrderStoreNewDAO.getItemByBarcode: result: "+ result );
				}catch( Exception e ){
				    logger.error("OrderStoreNewDAO.getItemByBarcode: "+ e );
				}
				return result;
	}
	public Item getItemByArticleno(String articleno) throws SQLException{
		EntityManager em = XPersistence.getManager();
		Query query = null;
		Item result = null;
		try{
	      logger.debug("OrderStoreNewDAO.getItemByArticleno: articleno: "+ articleno );
			query = em.createQuery(
					"SELECT i FROM Item AS i"
					+ " WHERE i.articleno = '"+ articleno +"'"
					);
			result = (Item)query.setMaxResults(1).getSingleResult();
	      logger.debug("OrderStoreNewDAO.getItemByArticleno: result: "+ result );
		}catch( Exception e ){
		    logger.error("OrderStoreNewDAO.getItemByArticleno: "+ e );
		}
		return result;
}

	//利用COLORID找出顏色的名稱
	  public String getColorNameById(String colorId) throws SQLException{
		  Connection conn = null;
		  Statement stmt = null;
		  String colorName = null;;
		  conn = DriverManager.getConnection(url, user, pwd);
		  stmt = conn.createStatement();
		  
		  ResultSet st = stmt.executeQuery("select ic from ItemColor as ic where ic.oid='"+colorId+"'");//select i from Invoice as i where i.customer.number=1
		  while(st.next()){
			  colorName = st.getString("name");
		   	  logger.debug("colorName="+st.getString("name"));			  
		  }
		  return colorName;
	  }
	  
	  public Collection <OrderSuggestD> findSuggestD( int wareId ){
			return new OrderSuggestD().findSuggestD(wareId);
	  }

	  public Collection <OrderStoreD> getOrderD( int headId ){
			return new OrderStoreD().getOrderD( headId );
	  }
}
		      
			  
		  
