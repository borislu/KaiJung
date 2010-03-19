package com.kaijung.jpa;

import java.io.Serializable;

import javax.persistence.*;
import javax.persistence.Entity;

import org.hibernate.annotations.*;
import org.openxava.annotations.*;

/**
 * The persistent class for the OrderStoreD database table.
 * 
 */
@Entity
@Views({
	@View( name ="Simple", members=
	    "name, status; enname; barcode, brandid;"
	)
})
@Tabs({
	@Tab(properties="item.barcode, item.articleno, item.color.name, isCustOrder, item.name, quantity, remark, status",
		defaultOrder="${oid} desc"
	),
	@Tab(name="Latest", properties="item.barcode, item.articleno, item.color.name, isCustOrder, item.name, quantity, remark, status",
		defaultOrder="${oid} desc"
	)
})
public class OrderStoreD implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(generator="system-uuid") @Hidden
	@GenericGenerator(name="system-uuid", strategy="uuid")
	@Column(length=32)
	private String oid;

	private boolean isCustOrder;

	@ManyToOne 
	@JoinColumn(name="orderStore_oid",referencedColumnName="oid")// name:本表格的fk，但物件內不用宣告；referencedColumnName:對應表格的pk
	private OrderStore orderStore;
	
	@ManyToOne 
	@JoinColumn(name="itemid",referencedColumnName="oid")// name:本表格的fk，但物件內不用宣告；referencedColumnName:對應表格的pk
	private Item item;

	private int modifyid;

//	private String orderStore_oid;

	private int quantity;

	private String remark;

	@Hidden
	private String reserve1;
	@Hidden
	private String reserve10;
	@Hidden
	private String reserve2;
	@Hidden
	private String reserve3;
	@Hidden
	private String reserve4;
	@Hidden
	private String reserve5;
	@Hidden
	private String reserve6;
	@Hidden
	private String reserve7;
	@Hidden
	private String reserve8;
	@Hidden
	private String reserve9;
	@ReadOnly
	private String status;

	@DisplaySize(6) @Transient
	public String get24() { return ""; } // 尺寸，無資料庫對應

	@DisplaySize(6) @Transient
	public String get26() { return ""; } // 尺寸，無資料庫對應

	@DisplaySize(6) @Transient
	public String get28() { return ""; } // 尺寸，無資料庫對應

	@DisplaySize(6) @Transient
	public String get30() { return ""; } // 尺寸，無資料庫對應

	@DisplaySize(6) @Transient
	public String get32() { return ""; } // 尺寸，無資料庫對應

	@DisplaySize(6) @Transient
	public String getSum() { return ""; } // 小計，無資料庫對應

	@DisplaySize(8) @Transient
	public String getAmount() { return ""; } // 金額，無資料庫對應
	
	@DisplaySize(6) @Transient
	public String getModifyId() { return ""; } // 修改單號，無資料庫對應

   public OrderStoreD () {
    }
    public OrderStoreD( String oid, boolean iscustOrder, int quantity ) {
    	setOid( oid );
    	setIsCustOrder(isCustOrder);
    	setQuantity(quantity);
    }

	public String getOid() {
		return this.oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public boolean getIsCustOrder() {
		return this.isCustOrder;
	}

	public void setIsCustOrder(boolean isCustOrder) {
		this.isCustOrder = isCustOrder;
	}

	public int getModifyid() {
		return this.modifyid;
	}

	public void setModifyid(int modifyid) {
		this.modifyid = modifyid;
	}

//	public String getOrderStore_oid() {
//		return this.orderStore_oid;
//	}
//
//	public void setOrderStore_oid(String orderStore_oid) {
//		this.orderStore_oid = orderStore_oid;
//	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
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

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public void setCustOrder(boolean isCustOrder) {
		this.isCustOrder = isCustOrder;
	}

	public OrderStore getOrderStore() {
		return orderStore;
	}

	public void setOrderStore(OrderStore orderStore) {
		this.orderStore = orderStore;
	}

}