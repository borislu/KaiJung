/*Codeing By Jason*/
package com.kaijung.dao;
import com.kaijung.jpa.*;
import com.kaijung.zk.controller.*;
import common.*;

import java.lang.reflect.*;
import java.sql.*;
import java.util.*;


public class OrderSenderDwr {
		private static Logger logger = Logger.getLogger(OrderSenderDwr.class);
	  
		public void update(String oid, String quantity, String memo){
	      logger.debug("OrderSenderDwr.update: quantity: "+ quantity);
			OrderSenderDDAO dao = new OrderSenderDDAO();
			dao.update(Integer.parseInt(oid), quantity, memo);
		}
//		public int update(String oid, String quantity, String modifyid, String isCustOrder, String memo){
//		      logger.debug("OrderSender.update: quantity: "+ quantity);
//				OrderSenderDAO osdnDAO = new OrderSenderDAO();
//				return osdnDAO.update(oid, quantity, modifyid, isCustOrder, memo);
//			}
//		public Item getByBarcode(String barcode) throws SQLException{//以條碼找出商品
//			return new OrderSenderDAO().getItemByBarcode(barcode);
//		}
//		public Item getByArticleno(String articleno) throws SQLException{//以貨號找出商品
//			return new OrderSenderDAO().getItemByArticleno(articleno);
//		}
//		 public String getColorName(String colorId )throws SQLException{
//				OrderSenderDAO osdn1DAO = new OrderSenderDAO();
//				String colorName = osdn1DAO.getColorNameById(colorId);
//				return colorName;
//		 }
//		 public Set <OrderSuggestD> findSuggestList(int wareId){
//			 logger.debug("OrderSender.findSuggestList: wareId: "+ wareId );
//			 Collection <OrderSuggestD> cl = new OrderSenderDAO().findSuggestD( wareId );
//			 logger.debug("OrderSender.findSuggestList: Collection: "+ new HashSet <OrderSuggestD>( cl ).toString() );
//			 return new HashSet <OrderSuggestD>( cl );
//		 }
		 public HashSet <Stock> getDetailByHeadId(String senderid){//以撥出單編號找出所有明細數量(json字串)
			 logger.debug("OrderSenderDwr.getStocksBySenderId: senderid: "+ senderid );
			 int intSenderId = 0;
			 try{ intSenderId = Integer.parseInt(senderid); }catch(Exception e){};
			 Collection c = new OrderSenderDDAO().getDetailByHeadId( intSenderId );
			 HashSet <Stock> beans = new HashSet (c);
			 logger.debug("OrderSenderDwr.getStocksBySenderId: beans: "+ beans );
			 return beans;
		 }
		
}
