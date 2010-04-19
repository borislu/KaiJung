/*Codeing By Jason*/
package com.kaijung.dao;
import com.kaijung.jpa.*;
import common.*;


import java.util.*;

public class OrderMarkDwr {
	private static Logger logger = Logger.getLogger(OrderMarkDwr.class);
  
	public void insert(String oid, String quantity, String memo){//只有寫入明細，單頭是用 openxava 的預設方式寫入
      logger.debug("OrderMarkDwr.insert: quantity: "+ quantity);
		OrderMarkDDAO dao = new OrderMarkDDAO();
		dao.insert( oid, quantity, memo );
	}
	public void update(String oid, String quantity, String memo){
      logger.debug("OrderMarkDwr.update: quantity: "+ quantity);
		OrderMarkDDAO dao = new OrderMarkDDAO();
		dao.update(oid, quantity, memo);
	}
	@SuppressWarnings("unchecked")
	public HashSet <OrderMarkD> getMarksById(String markid){//以備貨單編號找出備貨單明細
		 logger.debug("OrderMarkDwr.getMarksById: markid: "+ markid );
		 Collection c = new OrderMarkDDAO().getMarksById( markid );
		 HashSet <OrderMarkD> beans = new HashSet (c);
		 logger.debug("OrderMarkDwr.getMarksById: beans: "+ beans );
		 return beans;
	}
		 
	public ImportD getImportDByBarcode(String barcode){//以條碼找出對應的進貨明細
		return new ImportDDAO().getByBarcode(barcode);
	}
	
	public Item getItemByBarcode(String barcode){//以條碼找出商品
		return new ItemDAO().getByBarcode(barcode);
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
