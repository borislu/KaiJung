/*Codeing By Jason*/
package com.kaijung.dao;
import com.kaijung.jpa.*;
import common.*;


import java.util.*;

public class OrderMarkDwr {
		private static Logger logger = Logger.getLogger(OrderMarkDwr.class);
	  
		public void update(String oid, String quantity, String memo){
	      logger.debug("OrderMarkDwr.update: quantity: "+ quantity);
			OrderMarkDDAO dao = new OrderMarkDDAO();
			dao.update(Integer.parseInt(oid), quantity, memo);
		}
		@SuppressWarnings("unchecked")
		public HashSet <ImportD> getImportByBarcode(String barcode){//以條碼(=商品代號)取得該商品的進貨記錄
			 logger.debug("OrderMarkDwr.getImportByBarcode: barcode: "+ barcode );
			 Collection c = new OrderMarkDDAO().getImportByBarcode( barcode );
			 HashSet <ImportD> beans = new HashSet (c);
			 logger.debug("OrderMarkDwr.getImportByBarcode: beans: "+ beans );
			 return beans;
		 }
		 
	public Item getByBarcode(String barcode){//以條碼找出商品
		return new ItemDAO().getItemByBarcode(barcode);
	}
//	public Item getByArticleno(String articleno) throws SQLException{//以貨號找出商品
//		return new OrderMarkDAO().getItemByArticleno(articleno);
//	}
//	 public String getColorName(String colorId )throws SQLException{
//			OrderMarkDAO osdn1DAO = new OrderMarkDAO();
//			String colorName = osdn1DAO.getColorNameById(colorId);
//			return colorName;
//	 }
//	 public Set <OrderSuggestD> findSuggestList(int wareId){
//		 logger.debug("OrderMark.findSuggestList: wareId: "+ wareId );
//		 Collection <OrderSuggestD> cl = new OrderMarkDAO().findSuggestD( wareId );
//		 logger.debug("OrderMark.findSuggestList: Collection: "+ new HashSet <OrderSuggestD>( cl ).toString() );
//		 return new HashSet <OrderSuggestD>( cl );
//	 }
}
