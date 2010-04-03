/*Codeing By Jason*/
package com.kaijung.dao;
import com.kaijung.jpa.*;
import com.kaijung.zk.controller.*;
import common.*;

import java.lang.reflect.*;
import java.sql.*;
import java.util.*;


public class OrderPlaceDwr {
		private static Logger logger = Logger.getLogger(OrderPlaceDwr.class);
	  
		public void update(String oid, String quantity, String memo){
	      logger.debug("OrderPlaceDwr.update: quantity: "+ quantity);
			OrderPlaceDDAO dao = new OrderPlaceDDAO();
			dao.update(Integer.parseInt(oid), quantity, memo);
		}
//		public int update(String oid, String quantity, String modifyid, String isCustOrder, String memo){
//		      logger.debug("OrderPlace.update: quantity: "+ quantity);
//				OrderPlaceDAO osdnDAO = new OrderPlaceDAO();
//				return osdnDAO.update(oid, quantity, modifyid, isCustOrder, memo);
//			}
//		public Item getByBarcode(String barcode) throws SQLException{//以條碼找出商品
//			return new OrderPlaceDAO().getItemByBarcode(barcode);
//		}
//		public Item getByArticleno(String articleno) throws SQLException{//以貨號找出商品
//			return new OrderPlaceDAO().getItemByArticleno(articleno);
//		}
//		 public String getColorName(String colorId )throws SQLException{
//				OrderPlaceDAO osdn1DAO = new OrderPlaceDAO();
//				String colorName = osdn1DAO.getColorNameById(colorId);
//				return colorName;
//		 }
//		 public Set <OrderSuggestD> findSuggestList(int wareId){
//			 logger.debug("OrderPlace.findSuggestList: wareId: "+ wareId );
//			 Collection <OrderSuggestD> cl = new OrderPlaceDAO().findSuggestD( wareId );
//			 logger.debug("OrderPlace.findSuggestList: Collection: "+ new HashSet <OrderSuggestD>( cl ).toString() );
//			 return new HashSet <OrderSuggestD>( cl );
//		 }
		 public Set <OrderStoreD> getOrderDByPick(String pickId){//以揀貨單的oid查出對應的訂貨明細關連檔
			 logger.debug("OrderPlaceDwr.getOrderDByPick: pickId: "+ pickId );
			 int intPickId = 0;
			 try{ intPickId = Integer.parseInt(pickId); }catch(Exception e){};
			 Collection <OrderStoreD> cl = new OrderPlaceDDAO().getOrderDByPick( intPickId );
			 logger.debug("OrderPlaceDwr.getOrderDByPick: Collection: "+ new HashSet <OrderStoreD>( cl ).toString() );
			 return new HashSet <OrderStoreD>( cl );
		 }
}
