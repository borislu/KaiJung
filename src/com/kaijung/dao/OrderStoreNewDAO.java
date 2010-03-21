//Coding by Jason
package com.kaijung.dao;

import java.sql.*;
import java.util.*;
import java.lang.System;
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
	  
	  public void insert(String oid, int quantity, String modifyid, String isCustOrder, String memo, String orderStoreOid){
		    Connection conn = null;
		    Statement stmt = null;
		    try {
		      conn = DriverManager.getConnection(url, user, pwd);
		      stmt = conn.createStatement();
		      logger.debug("OrderStoreNewDAO.insert: isCustOrder: "+ isCustOrder);
		      stmt.executeUpdate("insert into OrderStoreD(oid,quantity,modifyid,isCustOrder,remark,orderStore_oid) " +
		          "values ('" + oid + "','" + quantity + "','" + modifyid + "','" + isCustOrder + "','" + memo+ "','"+ orderStoreOid +"')");
		    } catch (SQLException e) {
		      e.printStackTrace();
		    } finally {
		      try {
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
	  public Item getElementById(String barcode)throws SQLException{
		  Connection conn = null;
		  Statement stmt = null;
		  Item item = new Item();
		  conn = DriverManager.getConnection(url, user, pwd);
		  stmt = conn.createStatement();
		      
		  ResultSet st = stmt.executeQuery("select * from Item where barcode='"+barcode+"'");
		  while(st.next()){
		   	  item.setArticleno(st.getString("articleno"));
		   	  System.out.println("articleno="+st.getString("articleno"));
		   	  item.setPrice(st.getFloat("price"));
		   	  System.out.println("price="+st.getString("price"));
		   	  System.out.println("colorId="+st.getInt("colorId"));		   	  
//		   	  item.getColor().setOid(1);
//		   	  item.setColorId(st.getInt("colorid"));
		   	  System.out.println("colorId="+item.getColor().getOid());
		   	  System.out.println("item="+item);
		  }
		  return item;
	  }
	  //利用COLORID找出顏色的名稱
	  public String getColorNameById(String colorId)throws SQLException{
		  Connection conn = null;
		  Statement stmt = null;
		  String colorName = null;;
		  conn = DriverManager.getConnection(url, user, pwd);
		  stmt = conn.createStatement();
		  
		  ResultSet st = stmt.executeQuery("select * from ItemColor where oid='"+colorId+"'");
		  while(st.next()){
			  colorName = st.getString("name");
		   	  System.out.println("colorName="+st.getString("name"));			  
		  }
		  return colorName;
	  }
	  
	  public List <OrderSuggest> findSuggestOid() throws SQLException{
		  Connection conn = null;
		  Statement stmt = null;
		  List<OrderSuggest> list = new ArrayList();
		  conn = DriverManager.getConnection(url, user, pwd);
		  stmt = conn.createStatement();

		  ResultSet st = stmt.executeQuery("select * from OrderSuggest");//找建議訂單單頭的編號
		  System.out.println("in DAO");
		  while(st.next()){
			  System.out.println("st : "+st);
			  OrderSuggest orderSuggest = new OrderSuggest();
			  orderSuggest.setOid(st.getInt("oid"));
			  list.add(orderSuggest);
			  System.out.println("Oid="+orderSuggest.getOid());
		  }		  
		  return list;
	  }
}
		      
			  
		  
