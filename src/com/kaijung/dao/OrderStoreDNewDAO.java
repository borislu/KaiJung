//Coding by Jason
package com.kaijung.dao;

import java.sql.*;
import java.util.*;

import com.kaijung.zk.controller.*;
import common.*;

public class OrderStoreDNewDAO {
	  private final String url = "jdbc:mysql://localhost:3306/KaiJung?useUnicode=true&amp;characterEncoding=utf8";
	  private final String user = "ldstw";
	  private final String pwd = "ldstw";
	  private static Logger logger = Logger.getLogger(OrderStoreDNewDAO.class);
	  
	  public OrderStoreDNewDAO() {
	    try {
	      // 載入MYSQLDB 的 JDBC 驅動程式
	      Class.forName("com.mysql.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	   	  System.out.println("ERROR: failed to load MYSQLDB JDBC driver.");
	      e.printStackTrace();
	    }
	  }
	  
	  public void insert(String oid, int quantity, String modifyid, String isCustOrder, String memo){
		    Connection conn = null;
		    Statement stmt = null;
		    try {
		      conn = DriverManager.getConnection(url, user, pwd);
		      stmt = conn.createStatement();
		      logger.debug("isCustOrder inDAO:"+isCustOrder);
		      stmt.executeUpdate("insert into OrderStoreD(oid,quantity,modifyid,isCustOrder,remark) " +
		                             "values ('" + oid + "','" + quantity + "','" + modifyid + "','" + isCustOrder + "','" + memo +"')");
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

}
