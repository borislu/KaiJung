package com.kaijung.jpa;

import java.io.Serializable;
import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;

import org.hibernate.annotations.*;
import org.openxava.annotations.*;

import com.kaijung.calculators.*;

import java.util.*;


/**
 * The persistent class for the OrderPicker database table.
 * 
 */
@Entity
@Views( {
	@View(name = "DetailOnly", members = "order [ orderId; orderTime; wareId; orderBy ]"
		+ "picker [ readCode; createTime; picker; oid ]"
		+ "sender [ senderId; senderTime; senderBy ] details"
	)
})
@Tab(name = "Latest", defaultOrder = "${oid} desc",
	properties="orderId, orderTime, orderBy, readCode, createTime, status, remark" //
)
public class OrderPicker implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id //@Hidden
	@TableGenerator(
	    name="SequenceGenerator", table="SequenceGen", 
	    pkColumnName="oid", valueColumnName="value", 
	    pkColumnValue="orderPicker.oid", initialValue=1, allocationSize=1
	)
	@GeneratedValue(strategy = GenerationType.TABLE, generator="SequenceGenerator")
	private int oid;
	
//	@OneToMany
//	private Collection<OrderPickSend> orderStores;
	
	@OneToMany(mappedBy="orderPicker", cascade=CascadeType.REMOVE) //@AsEmbedded
	@ListProperties("item.articleno, item.price, item.color.name, 24,26,28,30,32,"
	+"sum, amount, isCustOrder, modifyId, remark, " // 此2個 remark 不同，前者要從修改單的記錄取出
	+"warehouse, item.stock.shelf, col, row, 24,26,28,30,32,"
	+"sum2, amount2, remark, status"
	)
	private Collection<OrderPickerD> details ;// = new ArrayList<OrderStoreD>(); 

//	private int createBy;
	@ManyToOne @DescriptionsList(descriptionProperties = "name")
	@JoinColumn(name = "createBy", referencedColumnName = "oid") // name:本表格的fk，但物件內不用宣告；referencedColumnName:對應表格的pk
	private Employee picker; // 揀貨人員

    @Temporal( TemporalType.TIMESTAMP)
	private Date createTime;

//	private int modifyBy;
	@ManyToOne @DescriptionsList(descriptionProperties = "name")
	@JoinColumn(name = "modifyBy", referencedColumnName = "oid") // name:本表格的fk，但物件內不用宣告；referencedColumnName:對應表格的pk
	private Employee modifier; // 修改人員

    @Temporal( TemporalType.TIMESTAMP)
	private Date modifyTime;

	@DefaultValueCalculator(value = ReadCodeGenerator.class, properties = {
		@PropertyValue(name = "docType", value = "B") // Required, 由基本檔取出
		, @PropertyValue(name = "wareId", value = "1") // Required, 改由 session 取出
		, @PropertyValue(name = "tableName", value = "SeqGenOrderStore") // Required,
																			// 記錄流水號的表格
	})
	@ReadOnly
	@DisplaySize(20)
	private String readCode;

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

	private String status;

//	public void addOrder(OrderStore orderStore, boolean teamLead) {
//	    OrderPickSend association = new OrderPickSend();
//	    association.setOrderStore(orderStore);
//	    association.setOrderPicker(this);
//	    association.setOrderSN(orderStore.getOid());
//	    association.setPickId(this.getOid());
//
//	    orderStore.add(association);
//	}

	@DisplaySize(18) @Transient
	public String getOrderId() { return ""; } // calculated property 無資料庫對應

	@DisplaySize(11) @Transient
	public String getOrderTime() { return ""; } // calculated property 無資料庫對應

	@DisplaySize(11) @Transient
	public String getWareId() { return ""; } // calculated property 無資料庫對應

	@DisplaySize(10) @Transient
	public String getOrderBy() { return ""; } // calculated property 無資料庫對應

	@DisplaySize(18) @Transient
	public String getSenderId() { return ""; } // calculated property 無資料庫對應

	@DisplaySize(11) @Transient
	public String getSenderTime() { return ""; } // calculated property 無資料庫對應

	@DisplaySize(10) @Transient
	public String getSenderBy() { return ""; } // calculated property 無資料庫對應
	
	public OrderPicker() {
    }

	public int getOid() {
		return this.oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getReadCode() {
		return this.readCode;
	}

	public void setReadCode(String readCode) {
		this.readCode = readCode;
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

	public Collection<OrderPickerD> getDetails() {
		return details;
	}

	public void setDetails(Collection<OrderPickerD> details) {
		this.details = details;
	}

	public Employee getPicker() {
		return picker;
	}

	public void setPicker(Employee picker) {
		this.picker = picker;
	}

	public Employee getModifier() {
		return modifier;
	}

	public void setModifier(Employee modifier) {
		this.modifier = modifier;
	}

}