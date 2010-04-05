package com.kaijung.jpa;

import java.io.Serializable;
import javax.persistence.*;

import org.openxava.annotations.*;

import java.util.Date;


/**
 * The persistent class for the OrderPlaceD database table.
 * 
 */
@Entity
@Tab(name = "Latest", defaultOrder = "${oid} desc")
public class OrderPlaceD implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @Hidden
	@TableGenerator(
	    name="SequenceGenerator", table="SequenceGen", 
	    pkColumnName="oid", valueColumnName="value", 
	    pkColumnValue="orderPlaceD.oid", initialValue=1, allocationSize=1
	)
	@GeneratedValue(strategy = GenerationType.TABLE, generator="SequenceGenerator")
	private int oid;

//	private int itemid;
	@ManyToOne @DescriptionsList(descriptionProperties = "name")
	@JoinColumn(name="itemid",referencedColumnName="oid")// name:本表格的fk，但物件內不用宣告；referencedColumnName:對應表格的pk
	private Item item;

//	private int orderPlace_oid;
	@ManyToOne 
	@JoinColumn(name="orderPlace_oid",referencedColumnName="oid")// name:本表格的fk，但物件內不用宣告；referencedColumnName:對應表格的pk
	private OrderPlace orderPlace;

	private int presetQty;

	private int realQty;

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

	private String shelf;

	private String status;

//	private int wareid;
	@ManyToOne @DescriptionsList(descriptionProperties = "name")
	@JoinColumn(name="wareid",referencedColumnName="oid")// name:本表格的fk，但物件內不用宣告；referencedColumnName:對應表格的pk
	private Warehouse warehouse;

	private int x;

	private int y;

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

	@DisplaySize(6) @Transient
	public String getRate() { return ""; } // 銷貨速度，無資料庫對應

	@DisplaySize(6) @Transient
	public String getSum2() { return ""; } // 小計，無資料庫對應

	@DisplaySize(3) @Transient
	public String getGo() { return ""; } // 上架checkbox，無資料庫對應

   public OrderPlaceD() {
    }

	public int getOid() {
		return this.oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public int getPresetQty() {
		return this.presetQty;
	}

	public void setPresetQty(int presetQty) {
		this.presetQty = presetQty;
	}

	public int getRealQty() {
		return this.realQty;
	}

	public void setRealQty(int realQty) {
		this.realQty = realQty;
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

	public String getShelf() {
		return this.shelf;
	}

	public void setShelf(String shelf) {
		this.shelf = shelf;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public OrderPlace getOrderPlace() {
		return orderPlace;
	}

	public void setOrderPlace(OrderPlace orderPlace) {
		this.orderPlace = orderPlace;
	}

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

}