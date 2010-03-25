/*Codeing By Jason*/
package com.kaijung.dao;
import com.kaijung.jpa.*;
import com.kaijung.zk.controller.*;
import common.*;

import java.lang.reflect.*;
import java.sql.*;
import java.util.*;


public class OrderStoreNew {
		private static Logger logger = Logger.getLogger(OrderStoreNew.class);
	  
		public void insert(String barcode, String quantity, String modifyid, String isCustOrder, String memo, String orderStoreOid){
	      logger.debug("OrderStoreNew.insert: quantity: "+ quantity);
			OrderStoreNewDAO osdnDAO = new OrderStoreNewDAO();
			//在這裡生成OrderStoreD的UUID
			UUID uuid = UUID.randomUUID();
			String oid = uuid.toString().replaceAll("-", ""); //str.substring(0,8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
			osdnDAO.insert(oid, barcode, quantity, modifyid, isCustOrder, memo, orderStoreOid);
		}
		public Item getByBarcode(String barcode) throws SQLException{//以條碼找出商品
			return new OrderStoreNewDAO().getItemByBarcode(barcode);
		}
		public Item getByArticleno(String articleno) throws SQLException{//以貨號找出商品
			return new OrderStoreNewDAO().getItemByArticleno(articleno);
		}
		 public String getColorName(String colorId )throws SQLException{
				OrderStoreNewDAO osdn1DAO = new OrderStoreNewDAO();
				String colorName = osdn1DAO.getColorNameById(colorId);
				return colorName;
		 }
		 public Set <OrderSuggestD> findSuggestList(int wareId){
			 logger.debug("OrderStoreNew.findSuggestList: wareId: "+ wareId );
			 Collection <OrderSuggestD> cl = new OrderStoreNewDAO().findSuggestD( wareId );
			 logger.debug("OrderStoreNew.findSuggestList: Collection: "+ new HashSet <OrderSuggestD>( cl ).toString() );
			 return new HashSet <OrderSuggestD>( cl );
		 }
		 public Set <OrderStoreD> getOrderD(int headId){
			 logger.debug("OrderStoreNew.getOrderD: headId: "+ headId );
			 Collection <OrderStoreD> cl = new OrderStoreNewDAO().getOrderD( headId );
			 logger.debug("OrderStoreNew.getOrderD: Collection: "+ new HashSet <OrderStoreD>( cl ).toString() );
			 return new HashSet <OrderStoreD>( cl );
		 }
}
