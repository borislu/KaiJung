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
		 public HashSet <Stock> getStocksByPlaceId(String placeid){//商品代號取得該商品所在的庫存記錄(庫架、數量等)
			 logger.debug("OrderPlaceDwr.getStockByItem: placeid: "+ placeid );
			 int intPlaceId = 0;
			 try{ intPlaceId = Integer.parseInt(placeid); }catch(Exception e){};
			 Collection c = new OrderPlaceDDAO().getStocksByPlaceId( intPlaceId );
			 HashSet <Stock> beans = new HashSet (c);
			 logger.debug("OrderPlaceDwr.getStocksByPlaceId: beans: "+ beans );
			 return beans;
		 }
		
		 public Stock getStockByItem(String itemid){//商品代號取得該商品所在的庫存記錄(庫架、數量等)
			 logger.debug("OrderPlaceDwr.getStockByItem: itemid: "+ itemid );
			 int intItemId = 0;
			 try{ intItemId = Integer.parseInt(itemid); }catch(Exception e){};
			 Stock stock = new OrderPlaceDDAO().getStockByItem( intItemId );
			 logger.debug("OrderPlaceDwr.getStockByItem: stock: "+ stock );
			 return stock;
		 }
}
