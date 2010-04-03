package com.kaijung.jpa;

import java.io.Serializable;
import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;

import org.hibernate.annotations.*;
import org.openxava.annotations.*;
import org.openxava.calculators.*;

import com.kaijung.calculators.*;

import java.util.*;

/**
 * The persistent class for the OrderStore database table.
 * 
 */
@Entity
@Views( {
		@View(members = "order [ readCode; createTime; warehouse; orderman ]"
				+ "picker [ pickerId; pickerTime; pickerBy ]"
				+ "sender [ senderId; senderTime; senderBy ]"
				+ "details"),
		@View(name = "HeadOnly", members = "order [ readCode; createTime; warehouse; orderman ]"
				+ "picker [ pickerId; pickerTime; pickerBy; ]"
				+ "sender [ senderId; senderTime; senderBy; oid ]"),
		@View(name = "DetailOnly", members = "order [ readCode; createTime; warehouse; orderman ]"
			+ "picker [ pickerId; pickerTime; pickerBy ]"
			+ "sender [ senderId; senderTime; senderBy ]"
			+ "details"),
})
@Tab( name = "Latest", defaultOrder = "${oid} desc"
		,properties="readCode, createTime, warehouse.name, orderman.name, totalQty, pickerId, pickerTime, pickerBy, senderId, senderTime, senderBy, inTime, remark, status" 
)
public class OrderStore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @Column(length = 32) //@Hidden
//	@GeneratedValue(generator = "system-uuid")
//	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String oid;

	// private int createBy;
	@ManyToOne @DescriptionsList(descriptionProperties = "name")
	@JoinColumn(name = "createBy", referencedColumnName = "oid") // name:本表格的fk，但物件內不用宣告；referencedColumnName:對應表格的pk
	private Employee orderman; // 訂貨人員
	
	@OneToMany(mappedBy="orderStore", cascade=CascadeType.REMOVE) //@AsEmbedded
	@ListProperties("item.articleno, item.price, item.color.sName, 24,26,28,30,32"
	+ ", sum, amount, isCustOrder, modifyid, remark, status, oid"
	)
	private Collection<OrderStoreD> details ;// = new ArrayList<OrderStoreD>(); 

//	@OneToMany
//	private Collection<OrderPickSend> orderPickers;

	@Temporal(TemporalType.TIMESTAMP) @DefaultValueCalculator(CurrentDateCalculator.class)
	private Date createTime;

//	private int modifyBy;
	@ManyToOne @DescriptionsList(descriptionProperties = "name")
	@JoinColumn(name = "modifyBy", referencedColumnName = "oid") // name:本表格的fk，但物件內不用宣告；referencedColumnName:對應表格的pk
	private Employee modifier; // 修改人員

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyTime;

	@DefaultValueCalculator(value = ReadCodeGenerator.class, properties = {
			@PropertyValue(name = "docType", value = "A") // Required, 由基本檔取出
			, @PropertyValue(name = "wareId", value = "1") // Required, 改由 session 取出
			, @PropertyValue(name = "tableName", value = "SeqGenOrderStore") // Required, SeqGenOrderStore:記錄流水號的表格
	})
	@ReadOnly @DisplaySize(20)
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

	// private int wareId;
	@ManyToOne @DescriptionsList(descriptionProperties = "name")
	@JoinColumn(name = "wareId", referencedColumnName = "oid")// name:本表格的fk，但物件內不用宣告；referencedColumnName:對應表格的pk
	private Warehouse warehouse;

	@DisplaySize(8) @Transient
	public String getTotalQty() { return ""; } // 總件數，無資料庫對應
	
	@DisplaySize(10) @Transient
	public String getInTime() { return ""; } // 撥入日期，無資料庫對應
	
	
	@Transient @ManyToOne
	//@JoinColumn(name="itemid",referencedColumnName="oid")// name:本表格的fk，但物件內不用宣告；referencedColumnName:對應表格的pk
//	private Item item;
	public Item getItem() { // calculated property 無資料庫對應
		return null;
	}

	@DisplaySize(18)
	@Transient
	public String getPickerId() { // calculated property 無資料庫對應
		return "";
	}

	@DisplaySize(11)
	@Transient
	public String getPickerTime() { // calculated property 無資料庫對應
		return "";
	}

	@DisplaySize(10)
	@Transient
	public String getPickerBy() { // calculated property 無資料庫對應
		return "";
	}

	@DisplaySize(18)
	@Transient
	public String getSenderId() { // calculated property 無資料庫對應
		return "";
	}

	@DisplaySize(11)
	@Transient
	public String getSenderTime() { // calculated property 無資料庫對應
		return "";
	}

	@DisplaySize(10)
	@Transient
	public String getSenderBy() { // calculated property 無資料庫對應
		return "";
	}

	public OrderStore() {
	}

	public String getOid() {
		return this.oid;
	}

	public void setOid(String oid) {
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

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	public Collection<OrderStoreD> getDetails() {
		return details;
	}

	public void setDetails(Collection<OrderStoreD> details) {
		this.details = details;
	}

//    public Collection<OrderPickSend> getOrderPickerss() { return orderPickers; }
//
//	public Collection<OrderPickSend> getOrderPickers() {
//		return orderPickers;
//	}
//
//	public void setOrderPickers(Collection<OrderPickSend> orderPickers) {
//		this.orderPickers = orderPickers;
//	}

	public void add(OrderPickSend association) {
		// TODO Auto-generated method stub
		
	}

	public Employee getOrderman() {
		return orderman;
	}

	public void setOrderman(Employee orderman) {
		this.orderman = orderman;
	}

	public Employee getModifier() {
		return modifier;
	}

	public void setModifier(Employee modifier) {
		this.modifier = modifier;
	}

}