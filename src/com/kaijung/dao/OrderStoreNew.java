/*Codeing By Jason*/
package com.kaijung.dao;
import com.kaijung.jpa.*;
import com.kaijung.zk.controller.*;
import common.*;

import java.lang.reflect.*;
import java.sql.*;
import java.util.*;

import org.openxava.jpa.*;


public class OrderStoreNew {
		private static Logger logger = Logger.getLogger(OrderStoreNew.class);

		/* 寫入OrderStoreD */
		public void insert(String barcode, String quantity, String modifyid, String isCustOrder, String memo, String orderStoreOid){
	      logger.debug("OrderStoreNew.insert: quantity: "+ quantity);
			OrderStoreNewDAO dao = new OrderStoreNewDAO();
			//在這裡生成OrderStoreD的UUID，但似乎可以在OrderStoreD裡面用JPA生成即可。
			UUID uuid = UUID.randomUUID();
			String oid = uuid.toString().replaceAll("-", ""); //str.substring(0,8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
			dao.insert(oid, barcode, quantity, modifyid, isCustOrder, memo, orderStoreOid);
		}
		/* 寫入OrderStore  */
		public void insert2( String oid, String warehouseId){
		      logger.debug("OrderStoreNew.insert2: headId: "+ oid);
				OrderStoreNewDAO dao = new OrderStoreNewDAO();
				OrderStore orderStore = new OrderStore();
				orderStore.setOid( oid);
				try{
			      int wareId = Integer.parseInt( warehouseId);
					Warehouse warehouse = XPersistence.getManager().find ( Warehouse.class, wareId );
					orderStore.setWarehouse( warehouse);
				}catch( Exception e){
			      logger.error("OrderStoreNew.insert2: e: "+ e);
				}
				dao.insert( orderStore);
		}
		public int submit( String headid ){ // 訂單新增和修改共用
			OrderStoreNewDAO dao = new OrderStoreNewDAO();
			int rtn = 0;
			int empid = 1; //oid
			String wareid = null;
			try{
				wareid = "1"; //oid
				rtn = dao.submit( headid, empid, wareid );
			}catch(Exception e){
				logger.error(e);
			}
			return rtn;
		}
		public int update(String oid, String quantity, String modifyid, String isCustOrder, String memo){
	      logger.debug("OrderStoreNew.update: quantity: "+ quantity);
			OrderStoreNewDAO osdnDAO = new OrderStoreNewDAO();
			return osdnDAO.update(oid, quantity, modifyid, isCustOrder, memo);
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
		@SuppressWarnings("unchecked")
		public Set <OrderSuggestD> findSuggestList(int wareId){
			 logger.debug("OrderStoreNew.findSuggestList: wareId: "+ wareId );
			 Collection <OrderSuggestD> cl = new OrderStoreNewDAO().findSuggestD( wareId );
			 HashSet set = null;
			 if( cl != null ){
				 set = new HashSet <OrderSuggestD>( cl );
			 }
			 logger.debug("OrderStoreNew.findSuggestList: HashSet: "+ set );
			 return set;
		}
		public Set <OrderStoreD> getOrderD(String headId){//以主檔(單頭)的oid，查出所有的明細檔
			 logger.debug("OrderStoreNew.getOrderD: headId: "+ headId );
			 Collection <OrderStoreD> cl = new OrderStoreNewDAO().getOrderD( headId );
			 logger.debug("OrderStoreNew.getOrderD: Collection: "+ new HashSet <OrderStoreD>( cl ).toString() );
			 return new HashSet <OrderStoreD>( cl );
		}
		public Set <OrderStoreD> getDetailSet(String oid){//以明細檔的oid(任何一個)，查出所有的明細檔
			 logger.debug("OrderStoreNew.getDetailSet: oid: "+ oid );
			 Collection <OrderStoreD> cl = new OrderStoreNewDAO().getDetailSet( oid );
			 logger.debug("OrderStoreNew.getDetailSet: Collection: "+ new HashSet <OrderStoreD>( cl ).toString() );
			 return new HashSet <OrderStoreD>( cl );
		}
		public boolean isSaved ( String headid ){
			boolean rtn = new OrderStoreNewDAO().isSaved( headid );
			logger.debug("OrderStoreNew.isSaved: rtn: "+ rtn );
			return rtn;
		}
		public int deleteDetails ( String headId ){
			return new OrderStoreNewDAO().deleteDetails( headId );
		}
}
