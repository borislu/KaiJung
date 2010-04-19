package com.kaijung.test;

import java.sql.*;

import org.openxava.tests.*;
import org.openxava.util.*;

import com.kaijung.dao.*;
import common.*;

public class OrderMarkDAOTest extends ModuleTestBase {
	private static Logger logger = Logger.getLogger(OrderMarkDAOTest.class);
//	private IConnectionProvider provider;
	
//	public OrderMarkDAOTest () {
//	}
	
	public OrderMarkDAOTest ( String testName ) {
		super( testName, "KaiJung", "OrderMark"); // 1
	}

	public void testRead() throws Exception {
		setValue("oid", "3236b747162d5f292afd989b11f2c51c");
		execute("CRUD.search");
		assertValue("oid", "3236b747162d5f292afd989b11f2c51c");
	}

//	public void readMark(){
//		Connection conn;
//		Statement stmt;
//		IConnectionProvider provider;		
//
//		try{
//			provider = DataSourceConnectionProvider.createByComponent("OrderMark");
//			conn = provider.getConnection();
//			logger.debug("conn = " + conn );
//			stmt = conn.createStatement();
//		
//			ResultSet st = stmt
//					.executeQuery("select * from OrderMark as o where o.oid='3236b747162d5f292afd989b11f2c51c'");
//			while (st.next()) {
//				logger.debug("oid = " + st.getString("oid"));
//			}
//		}catch(Exception e){
//			logger.error( e );
//		}
//	}
//	
//	public static void main( String[] args ){
//		new OrderMarkDAOTest().readMark();
//	}
}
