//Coding by Jason
package com.kaijung.dao;

import java.sql.*;
import java.util.*;
import java.lang.System;

import javax.persistence.*;

import org.openxava.calculators.*;
import org.openxava.jpa.*;

import com.kaijung.calculators.*;
import com.kaijung.jpa.*;

import common.*;

public class OrderStoreNewDAO { // 應為 OrderStoreDAO
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

	public boolean isSaved( String oid ){ //此訂貨單編號是否已儲存
		EntityManager em = XPersistence.getManager();
		Long count;
		Boolean rtnVal = false;
		Query query = null;
		try {
			query = em.createQuery("SELECT COUNT(*) FROM OrderStore o WHERE o.oid = :oid");
			query.setParameter("oid", oid);
			count = (Long) query.getSingleResult();
			logger.debug("OrderStoreNewDAO.isSaved: count: "+ count);
			if( count > 0 ){ rtnVal = true; }
		} catch (Exception e) {
			logger.error("OrderStoreNewDAO.isSaved: " + e);
		}
		return rtnVal;
	}
	
	/* 寫入 OrderStore */
	public int insert(OrderStore bean){
		XPersistence.getManager().persist( bean );
		XPersistence.commit(); // 若 UUID 重複，會有例外: org.hibernate.PersistentObjectException: detached entity passed to persist: com.kaijung.jpa.OrderStoreD
		return 1;
	}

	/* 寫入 OrderStoreD (應該搬到OrderStoreDDAO)*/
	public void insert(String oid, String barcode, String quantity,
			String modifyid, String isCustOrder, String memo,
			String orderStoreOid) { 
		logger.debug("OrderStoreNewDAO.insert: barcode: "+ barcode + ", quantity: " + quantity );
		Connection conn = null;
		int itemid = 0;
		Statement stmt2 = null;
		Statement stmt = null;
		try {
			conn = DriverManager.getConnection(url, user, pwd);
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

	public int deleteDetails ( String headid ){
			logger.debug("OrderPlaceDDAO.deleteAll: headid: " + headid );
//			XPersistence.getManager().remove(XPersistence.getManager().find(Seller.class, number));		

			Query query = XPersistence.getManager()
			.createQuery("DELETE FROM OrderStoreD d WHERE d.orderStore.oid = :headid "); //JPQL query
			query.setParameter("headid", headid);
			int rtn = query.executeUpdate();
			XPersistence.commit(); 
			return rtn; // 1:成功  0:失敗
	}

	public int update(String oid, String quantity, String modifyid, String isCustOrder, String memo) {
		return new OrderStoreD().update( oid, quantity, modifyid, isCustOrder, memo );
	}
	
	// 用條碼去找出貨號顏色價錢
	public Item getItemByBarcode(String barcode) throws SQLException { // 應使用 ItemDAO.getItemByBarcode
		EntityManager em = XPersistence.getManager();
		Query query = null;
		Item result = null;
		try {
			logger.debug("OrderStoreNewDAO.getItemByBarcode: barcode: "
					+ barcode);
			query = em.createQuery("SELECT i FROM Item AS i"
					+ " WHERE i.barcode = '" + barcode + "'");
			result = (Item) query.setMaxResults(1).getSingleResult();
			logger.debug("OrderStoreNewDAO.getItemByBarcode: result: "
							+ result);
		} catch (Exception e) {
			logger.error("OrderStoreNewDAO.getItemByBarcode: " + e);
		}
		return result;
	}

	public Item getItemByArticleno(String articleno) throws SQLException {
		EntityManager em = XPersistence.getManager();
		Query query = null;
		Item result = null;
		try {
			logger.debug("OrderStoreNewDAO.getItemByArticleno: articleno: "
					+ articleno);
			query = em.createQuery("SELECT i FROM Item AS i"
					+ " WHERE i.articleno = '" + articleno + "'");
			result = (Item) query.setMaxResults(1).getSingleResult();
			logger.debug("OrderStoreNewDAO.getItemByArticleno: result: "
					+ result);
		} catch (Exception e) {
			logger.error("OrderStoreNewDAO.getItemByArticleno: " + e);
		}
		return result;
	}

	// 利用COLORID找出顏色的名稱
	public String getColorNameById(String colorId) throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		String colorName = null;

		conn = DriverManager.getConnection(url, user, pwd);
		stmt = conn.createStatement();

		ResultSet st = stmt
				.executeQuery("select ic from ItemColor as ic where ic.oid='"
						+ colorId + "'");// select i from Invoice as i where
											// i.customer.number=1
		while (st.next()) {
			colorName = st.getString("name");
			logger.debug("colorName=" + st.getString("name"));
		}
		return colorName;
	}

	@SuppressWarnings("unchecked")
	public Collection<OrderSuggestD> findSuggestD(int wareId) {
				Query query = null;
				Collection <OrderSuggestD> resultList = null;
				try{
					query = XPersistence.getManager().createQuery(
//							"SELECT s FROM OrderSuggestD s, Item im"
//							+ " WHERE s.item.oid = im.oid"
//							);
							"SELECT s FROM OrderSuggestD s, Item im"
							+ " WHERE s.orderSuggest.warehouse.oid = :wareId"
							);
					query.setParameter( "wareId", wareId );
					resultList = query.getResultList();
			      logger.debug("OrderSuggestD.findSuggestD: result: "+ query.getResultList());
				}catch( Exception e ){
				    logger.error("OrderSuggestD.findSuggestD: "+ e );
				}
				return resultList;
	}

	public Collection<OrderStoreD> getOrderD(String headId) {
		return new OrderStoreD().getOrderD(headId);
	}
	
	public Collection<OrderStoreD> getDetailSet(String oid) {
		return new OrderStoreD().getDetailSet(oid);
	}
	
	/* 把原本儲存在本地端的訂單送出至伺服器，目前是模擬，所以直接寫入揀貨單 */
	@SuppressWarnings("unchecked")
	public int submit ( String orderid, int empid, String wareid ) throws Exception{
		Query query = XPersistence.getManager().createQuery(
				"FROM OrderStoreD WHERE orderStore.oid = :orderid"
			); //JPQL query
		query.setParameter("orderid", orderid);
		logger.debug("OrderStoreNewDAO.submit: orderid: "+ orderid );
		List <OrderStoreD> orderStoreDs = query.getResultList();
		logger.debug("OrderStoreNewDAO.submit: orderStoreDs: "+ orderStoreDs );
		
		ReadCodeGenerator rg = new ReadCodeGenerator();//設定揀貨單的可讀碼
		rg.setDocType("B");
		rg.setTableName("SeqGenOrderStore");
		rg.setWareId( wareid);
		
		Employee user = new Employee();
		user.setOid(empid);
		
		java.util.Date today = new java.util.Date();
		
		OrderPicker orderPicker = new OrderPicker();
		orderPicker.setPicker( user);
		orderPicker.setModifier( user);
		orderPicker.setModifyTime( today);
		orderPicker.setCreateTime( today);
		orderPicker.setReadCode( (String)rg.calculate() );
		XPersistence.getManager().persist( orderPicker );
		XPersistence.commit(); // 若 ID 重複，會有例外: org.hibernate.PersistentObjectException: detached entity passed to persist: com.kaijung.jpa.OrderStoreD
		int pickId = orderPicker.getOid();
		logger.debug("OrderStoreNewDAO.submit: orderPicker.oid: "+ pickId );
		
		for( int i=0; i<orderStoreDs.size(); i++ ){
			OrderPickerD p = new OrderPickerD();
			OrderStoreD d = (OrderStoreD) orderStoreDs.get(i);
			p.setOrderPicker( orderPicker );
			p.setItem	( d.getItem());
//			p.setQuantity( d.getQuantity());
			XPersistence.getManager().persist( p );
			XPersistence.commit(); // 若 ID 重複，會有例外: org.hibernate.PersistentObjectException: detached entity passed to persist: com.kaijung.jpa.OrderStoreD
		}
		
		OrderPickSend rel = new OrderPickSend();
		rel.setOrderDid( orderid);
		rel.setPickDid( pickId);
		rel.setWareId( Integer.parseInt( wareid));
		XPersistence.getManager().persist( rel );
		XPersistence.commit(); // 若 ID 重複，會有例外: org.hibernate.PersistentObjectException: detached entity passed to persist: com.kaijung.jpa.OrderStoreD
		
		return 1;
	}

}
