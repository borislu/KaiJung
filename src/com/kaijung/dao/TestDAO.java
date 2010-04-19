//Coding by Jason
package com.kaijung.dao;

import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import java.util.*;
import java.lang.System;

import javax.persistence.*;

import org.openxava.calculators.*;
import org.openxava.jpa.*;

import com.kaijung.calculators.*;
import com.kaijung.jpa.*;

import common.*;

public class TestDAO { // 應為 OrderStoreDAO
	private static Logger logger = Logger.getLogger(TestDAO.class);

   Connection conn = null;
	public TestDAO() {
		try{
		    Context ctx = new InitialContext();
		    DataSource ds=(DataSource)ctx.lookup("java:comp/env/jdbc/KaiJungDS");
		    conn = ds.getConnection();
//		    conn.close();
		}
		catch(Exception e){
			logger.error( e );
		}	
	}



	/* */
	public void insert(String oid, String barcode, String quantity,
			String modifyid, String isCustOrder, String memo,
			String orderStoreOid) { 
		logger.debug("OrderStoreNewDAO.insert: barcode: "+ barcode + ", quantity: " + quantity );
		Connection conn = null;
		int itemid = 0;
		Statement stmt2 = null;
		Statement stmt = null;
		try {
			if( barcode != null ){
				stmt2 = conn.createStatement();
				ResultSet rs = stmt2
						.executeQuery("select i.oid from Item i where i.barcode = '"
								+ barcode + "'");
				rs.next();
				itemid = rs.getInt("oid");
			}
			stmt = conn.createStatement();
			stmt.executeUpdate("insert into OrderStoreD(oid,itemid,quantity,modifyid,isCustOrder,remark,orderStore_oid) "
							+ "values ('"
							+ oid
							+ "','"
							+ itemid
							+ "','"
							+ quantity
							+ "','"
							+ modifyid
							+ "','"
							+ isCustOrder
							+ "','" + memo + "','" + orderStoreOid + "')");
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



	// 
	public String getOrderMark (){
		Connection conn = null;
		Statement stmt = null;
		try{
			stmt = conn.createStatement();
	
			ResultSet st = stmt
					.executeQuery("select * from OrderMark as o where o.oid='"
							+ "3236b747162d5f292afd989b11f2c51c" 
							+ "'");
			while (st.next()) {
				logger.debug("oid: " + st.getString("oid"));
			}
		}catch( Exception e ){
			logger.error( e );
		}
		return "";
	}

	public static void main( String args[] ){
		new TestDAO().getOrderMark();
	}

}
