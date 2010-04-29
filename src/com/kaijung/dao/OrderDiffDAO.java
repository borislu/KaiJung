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

public class OrderDiffDAO {
	private static Logger logger = Logger.getLogger(OrderDiffDAO.class);

	public OrderDiffDAO() {
	}

	@SuppressWarnings("unchecked")
	public List<OrderDiffBean> getOrderDiff( int wareid ){
		
		List list = new ArrayList();

		boolean isExisted = false; 
		
		String wareCond = null;  
		if( new WarehouseDAO().isExisted( wareid ) ){
			isExisted = true;
			wareCond = " AND o.warehouse.oid = w.oid = :wareid AND k.warehouse.oid = :wareid"; // 若庫位(專櫃)存在才加條件，若不存在則不分專櫃全選
		}else{
			wareCond = " AND o.warehouse.oid = w.oid "; // 若庫位(專櫃)不存在，不分專櫃全選
		}
		
		Query query = XPersistence.getManager()
		.createQuery(
		"SELECT d, k, sd, pd, md"
		+" FROM OrderStore o, OrderStoreD d, Warehouse w, Item i, Stock k, OrderSender s, OrderSenderD sd, OrderPickSend ops"
		+" ,OrderPlaceD pd, OrderMarkD md"
		+" WHERE d.orderStore.oid = o.oid"
		+" AND ops.orderDid = d.oid"
		+" AND ops.sendDid = sd.oid"
		+ wareCond
		+" AND d.item.oid = i.oid" 
		+" AND d.item.oid = k.item.oid" 
		+" AND d.item.oid = pd.item.oid" 
		+" AND d.item.oid = md.item.oid" 
		); //
		if( isExisted ){
			query.setParameter("wareid", wareid );
		}
		/*
		.createNativeQuery(
		"SELECT o.oid"
		+", o.remark, o.readCode, o.wareId, o.createTime, o.createBy, o.modifyTime, o.modifyBy"
		+", d.itemid, d.quantity, d.isCustOrder, d.orderStore_oid "
		+", k.itemid, k.quantity, k.wareId "
		+" FROM OrderStore o, OrderStoreD d, Warehouse w, Item i, Stock k" 
		+" WHERE d.orderStore_oid = o.oid "
		+ wareCond
		+" AND d.itemid = i.oid" 
		+" AND d.itemid = k.itemid" 
		, "detailsAndOrder"); //

			SELECT DISTINCT * 
			FROM OrderStore o, OrderStoreD d, Warehouse w, Item i, Stock k
			WHERE d.orderStore_oid = o.oid 
			AND o.wareId = w.oid = 1 
			AND d.itemid = i.oid 
			AND d.itemid = k.itemid
			AND k.wareId = 1 
		 */
		
		List <Object[]> orderDList = query.getResultList();
		logger.debug( "OrderDiffDAO.getOrderDiff: orderList size: " + orderDList.size() );

		for (Object[] objAry : orderDList) {
			logger.debug( "OrderDiffDAO.getOrderDiff: objAry length: " + objAry.length );
			OrderDiffBean diff = new OrderDiffBean();
			
			OrderStoreD orderD = (OrderStoreD) objAry[0];
			OrderStore order = orderD.getOrderStore();
			Stock stock = (Stock) objAry[1];
			OrderSenderD senderD = (OrderSenderD) objAry[2];
			OrderPlaceD placeD = (OrderPlaceD) objAry[3];
			OrderMarkD markD = (OrderMarkD) objAry[4];
			
			
			diff.setWareName( order.getWarehouse().getName() );
			diff.setArticleno( orderD.getItem().getArticleno() );
			diff.setColorName( orderD.getItem().getColor().getSname() );
			diff.setOrderQty( orderD.getQuantity() );//訂貨尺寸及數量
			diff.setIsCustOrder( orderD.getIsCustOrder() );
			diff.setStockQty( stock.getQuantity() ) ;//庫位尺寸及數量
			diff.setSendQty( senderD.getComfirmQty() );//出貨尺寸及數量
			diff.setPlaceQty( placeD.getRealQty() );//庫架尺寸及數量
			diff.setMarkQty( markD.getRealQty() );//備貨尺寸及數量
			diff.setPreMarkQty( markD.getPresetQty() );//待備貨尺寸及數量
			list.add( diff );
	    }

//		for (Object[] objAry : orderList) {
//			logger.debug( "OrderDiffDAO.getOrderDiff: objAry length: " + objAry.length );
//			OrderDiffBean diff = new OrderDiffBean();
//			
//			OrderStore order = (OrderStore) objAry[0];
//			OrderStoreD orderD = (OrderStoreD) objAry[1];
//			Stock stock = (Stock) objAry[2];
//			
//			diff.setWareName( order.getWarehouse().getName() );
//			diff.setArticleno( orderD.getItem().getArticleno() );
//			diff.setColorName( orderD.getItem().getColor().getSname() );
//			diff.setStockQty( stock.getQuantity() ) ;
//			diff.setOrderQty( orderD.getQuantity() );
//			list.add( diff );
//		}
		return list;
	}

	/*
	 * SELECT d FROM OrderStoreD d, OrderStore o, Warehouse w, Item i" 
				+" WHERE d.orderStore.oid = o.oid" 
				+" AND o.warehouse.oid = w.oid"
				+" AND d.item.oid = i.oid"
				+" AND o.warehouse.oid = :wareid
	 */
}
