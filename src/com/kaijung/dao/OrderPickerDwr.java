/*Codeing By Jason*/
package com.kaijung.dao;
import com.kaijung.jpa.*;
import com.kaijung.zk.controller.*;
import common.*;

import java.lang.reflect.*;
import java.sql.*;
import java.util.*;


public class OrderPickerDwr {
		private static Logger logger = Logger.getLogger(OrderPlaceDwr.class);
	  
		public void update(String oid, String quantity, String memo){
		      logger.debug("OrderPickerDwr.update: quantity: "+ quantity);
				new OrderPickerDDAO().update(Integer.parseInt(oid), quantity, memo);
		}
		public int submit( String pickid ){ // 揀貨單送出
			OrderPickerDAO dao = new OrderPickerDAO();
			int rtn = 0;
			int empid = 1; //oid
			String wareid = null;
			try{
				wareid = "1"; //oid
				rtn = dao.submit( pickid, empid, wareid );
			}catch(Exception e){
				logger.error(e);
			}
			return rtn;
		}
		public Set <OrderStoreD> getOrderDByPick (String pickId){//以揀貨單的oid查出對應的訂貨明細關連檔
			 logger.debug("OrderPickerDwr.getOrderDByPick: pickId: "+ pickId );
			 int intPickId = 0;
			 try{ intPickId = Integer.parseInt(pickId); }catch(Exception e){};
			 Collection <OrderStoreD> cl = new OrderPickerDDAO().getOrderDByPick( intPickId );
			 logger.debug("OrderPickerDwr.getOrderDByPick: Collection: "+ new HashSet <OrderStoreD>( cl ).toString() );
			 return new HashSet <OrderStoreD>( cl );
		}
		public Set <OrderPickerD> getPickerDByPick (String pickId){//以揀貨單的oid查出對應的訂貨明細關連檔
			 logger.debug("OrderPickerDwr.getPickerDByPick: pickId: "+ pickId );
			 int intPickId = 0;
			 try{ intPickId = Integer.parseInt(pickId); }catch(Exception e){};
			 Collection <OrderPickerD> cl = new OrderPickerDDAO().getPickerDByPick( intPickId );
			 logger.debug("OrderPickerDwr.getPickerDByPick: Collection: "+ new HashSet <OrderPickerD>( cl ).toString() );
			 return new HashSet <OrderPickerD>( cl );
		}
		
//			public int update(String oid, String quantity, String modifyid, String isCustOrder, String memo){
//			      logger.debug("OrderPicker.update: quantity: "+ quantity);
//					OrderPickerDAO osdnDAO = new OrderPickerDAO();
//					return osdnDAO.update(oid, quantity, modifyid, isCustOrder, memo);
//				}
//			public Item getByBarcode(String barcode) throws SQLException{//以條碼找出商品
//				return new OrderPickerDAO().getItemByBarcode(barcode);
//			}
//			public Item getByArticleno(String articleno) throws SQLException{//以貨號找出商品
//				return new OrderPickerDAO().getItemByArticleno(articleno);
//			}
//			 public String getColorName(String colorId )throws SQLException{
//					OrderPickerDAO osdn1DAO = new OrderPickerDAO();
//					String colorName = osdn1DAO.getColorNameById(colorId);
//					return colorName;
//			 }
//			 public Set <OrderSuggestD> findSuggestList(int wareId){
//				 logger.debug("OrderPicker.findSuggestList: wareId: "+ wareId );
//				 Collection <OrderSuggestD> cl = new OrderPickerDAO().findSuggestD( wareId );
//				 logger.debug("OrderPicker.findSuggestList: Collection: "+ new HashSet <OrderSuggestD>( cl ).toString() );
//				 return new HashSet <OrderSuggestD>( cl );
//			 }
}
