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
 * The persistent class for the OrderSender database table.
 * 
 */
@Entity
@Views({
	@View(name = "DetailOnly" , members=
	     "order [ orderId; orderTime; warehouse; orderBy  ]"
		 +"picker [ pickerId; pickerTime; pickerBy ]"
	    +"sender [ readCode; createTime; sender; oid ] details"
	)
})
@Tab(name="Latest", defaultOrder="${oid} desc"
		,properties="orderId, orderTime, warehouse, orderBy, pickerId, pickerTime, pickerBy, status, remark" 
)
public class OrderSender implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id //@Hidden
	@TableGenerator(
	    name="SequenceGenerator", table="SequenceGen", 
	    pkColumnName="oid", valueColumnName="value", 
	    pkColumnValue="orderSender.oid", initialValue=1, allocationSize=1
	)
	@GeneratedValue(strategy = GenerationType.TABLE, generator="SequenceGenerator")
	private int oid;

	@OneToMany(mappedBy="orderSender", cascade=CascadeType.REMOVE) //@AsEmbedded
	@ListProperties("item.articleno, item.price, item.color.sName, 24,26,28,30,32"
	+ ", item.itemUnit.name, sum, cargo, remark, status, oid"
	)
	private Collection<OrderSenderD> details ;// = new ArrayList<OrderStoreD>(); 

//	private int createBy; //撥出人員
	@ManyToOne @DescriptionsList(descriptionProperties = "name")
	@JoinColumn(name = "createBy", referencedColumnName = "oid") // name:本表格的fk，但物件內不用宣告；referencedColumnName:對應表格的pk
	private Employee sender; // 撥出人員

   @Temporal( TemporalType.TIMESTAMP)
	private Date createTime; //撥出時間

	@DefaultValueCalculator(value = ReadCodeGenerator.class, properties = {
		@PropertyValue(name = "docType", value = "C") // Required, 由基本檔取出
		, @PropertyValue(name = "wareId", value = "1") // Required, 改由 session 取出
		, @PropertyValue(name = "tableName", value = "SeqGenOrderStore") // Required, SeqGenOrderStore:記錄流水號的表格
	})
	@ReadOnly @DisplaySize(20)
   private String readCode;
   
   @Temporal( TemporalType.TIMESTAMP)
	private Date logisticsTime;

//	private int modifyBy;
	@ManyToOne @DescriptionsList(descriptionProperties = "name")
	@JoinColumn(name = "modifyBy", referencedColumnName = "oid") // name:本表格的fk，但物件內不用宣告；referencedColumnName:對應表格的pk
	private Employee modifier; // 撥出人員

    @Temporal( TemporalType.TIMESTAMP)
	private Date modifyTime;

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

	private int supplierAgentId;

	@DisplaySize(18) @Transient
	public String getOrderId() { return ""; } // 訂貨單編號，無資料庫對應

	@DisplaySize(11) @Transient
	public String getOrderTime() { return ""; } // 訂貨日期，無資料庫對應

	@DisplaySize(10) @Transient
	public String getOrderBy() { return ""; } // 訂貨人員，無資料庫對應

	@DisplaySize(10) @Transient
	public String getWarehouse() { return ""; } // 訂貨專櫃，無資料庫對應

	@DisplaySize(18) @Transient
	public String getPickerId() { return ""; } // calculated property 無資料庫對應

	@DisplaySize(11) @Transient
	public String getPickerTime() { return ""; } // calculated property 無資料庫對應

	@DisplaySize(10) @Transient
	public String getPickerBy() { return ""; } // calculated property 無資料庫對應
	
	public OrderSender() {
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

	public Date getLogisticsTime() {
		return this.logisticsTime;
	}

	public void setLogisticsTime(Date logisticsTime) {
		this.logisticsTime = logisticsTime;
	}

	public Date getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
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

	public int getSupplierAgentId() {
		return this.supplierAgentId;
	}

	public void setSupplierAgentId(int supplierAgentId) {
		this.supplierAgentId = supplierAgentId;
	}

	public String getReadCode() {
		return readCode;
	}

	public void setReadCode(String readCode) {
		this.readCode = readCode;
	}

	public Collection<OrderSenderD> getDetails() {
		return details;
	}

	public void setDetails(Collection<OrderSenderD> details) {
		this.details = details;
	}

	public Employee getSender() {
		return sender;
	}

	public void setSender(Employee sender) {
		this.sender = sender;
	}

	public Employee getModifier() {
		return modifier;
	}

	public void setModifier(Employee modifier) {
		this.modifier = modifier;
	}

}