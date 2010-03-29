package com.kaijung.jpa;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.jpa.*;

import com.kaijung.dao.*;
import common.*;


/**
 * The persistent class for the OrderSuggestD database table.
 * 
 */
@Entity
public class OrderSuggestD implements Serializable {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(OrderStoreNewDAO.class);

	@Id @Hidden
	@TableGenerator(
	    name="SequenceGenerator", table="SequenceGen", 
	    pkColumnName="oid", valueColumnName="value", 
	    pkColumnValue="suggestd.oid", initialValue=1, allocationSize=1
	)
	@GeneratedValue(strategy = GenerationType.TABLE, generator="SequenceGenerator")
	private int oid;

//	private int itemid;
	@ManyToOne 
	@JoinColumn(name="itemid",referencedColumnName="oid")// name:本表格的fk，但物件內不用宣告；referencedColumnName:對應表格的pk
	private Item item;

//	private int orderSuggest_oid;
	@ManyToOne 
	@JoinColumn(name="orderSuggest_oid",referencedColumnName="oid")// name:本表格的fk，但物件內不用宣告；referencedColumnName:對應表格的pk
	private OrderSuggest orderSuggest;

	private String reason;

	private String remark;

	private String reserve1;

	private String reserve10;

	private String reserve2;

	private String reserve3;

	private String reserve4;

	private String reserve5;

	private String reserve6;

	private String reserve7;

	private String reserve8;

	private String reserve9;

	private String status;

	private String suggestQty;

	public Collection <OrderSuggestD> findSuggestD( int wareId ){
			EntityManager em = XPersistence.getManager();
			Query query = null;
			Collection <OrderSuggestD> resultList = null;
			try{
				
//				Query query = XPersistence.getManager().createQuery("from Carrier c where " +
//						"c.warehouse.zoneNumber = :zone AND " + 
//						"c.warehouse.number = :warehouseNumber AND " + 
//						"NOT (c.number = :number) ");
//					query.setParameter("zone", getWarehouse().getZoneNumber());
//					query.setParameter("warehouseNumber", getWarehouse().getNumber());
//					query.setParameter("number",  getNumber());

				
				query = em.createQuery(
						"SELECT osd FROM OrderSuggestD AS osd, Item AS im"
						+ " WHERE osd.item.oid = im.oid"
//						+ " , Item as im"
//						+ " , ItemColor as ic"
//						+ " join osd.item.oid as o"
//						+ " join o. as o"
//						+ " where osd.item.oid = :item"
//						+ " and osd.item.color.oid = :color"
						);
//				query.setParameter( "item", getItem().getOid() );
//				query.setParameter( "item", 2 );
//				query.setParameter( "color", 2 );
				resultList = query.getResultList();
		      logger.debug("OrderSuggestD.findSuggestD: result: "+ query.getResultList());
			}catch( Exception e ){
			    logger.error("OrderSuggestD.findSuggestD: "+ e );
			}
			return resultList;
	}

	public OrderSuggestD() {
    }

	public int getOid() {
		return this.oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getReserve1() {
		return this.reserve1;
	}

	public void setReserve1(String reserve1) {
		this.reserve1 = reserve1;
	}

	public String getReserve10() {
		return this.reserve10;
	}

	public void setReserve10(String reserve10) {
		this.reserve10 = reserve10;
	}

	public String getReserve2() {
		return this.reserve2;
	}

	public void setReserve2(String reserve2) {
		this.reserve2 = reserve2;
	}

	public String getReserve3() {
		return this.reserve3;
	}

	public void setReserve3(String reserve3) {
		this.reserve3 = reserve3;
	}

	public String getReserve4() {
		return this.reserve4;
	}

	public void setReserve4(String reserve4) {
		this.reserve4 = reserve4;
	}

	public String getReserve5() {
		return this.reserve5;
	}

	public void setReserve5(String reserve5) {
		this.reserve5 = reserve5;
	}

	public String getReserve6() {
		return this.reserve6;
	}

	public void setReserve6(String reserve6) {
		this.reserve6 = reserve6;
	}

	public String getReserve7() {
		return this.reserve7;
	}

	public void setReserve7(String reserve7) {
		this.reserve7 = reserve7;
	}

	public String getReserve8() {
		return this.reserve8;
	}

	public void setReserve8(String reserve8) {
		this.reserve8 = reserve8;
	}

	public String getReserve9() {
		return this.reserve9;
	}

	public void setReserve9(String reserve9) {
		this.reserve9 = reserve9;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSuggestQty() {
		return this.suggestQty;
	}

	public void setSuggestQty(String suggestQty) {
		this.suggestQty = suggestQty;
	}

	public OrderSuggest getOrderSuggest() {
		return orderSuggest;
	}

	public void setOrderSuggest(OrderSuggest orderSuggest) {
		this.orderSuggest = orderSuggest;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

}