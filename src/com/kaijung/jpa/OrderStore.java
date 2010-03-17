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
 * The persistent class for the OrderStore database table.
 * 
 */
@Entity
@Views( {
		@View(members = "order [ readCode; createTime; warehouse; employee ]"
				+ "picker [ pickerId; pickerTime; pickerBy ]"
				+ "sender [ senderId; senderTime; senderBy ]"
				+ "details"),
		@View(name = "HeadOnly", members = "order [ readCode; createTime; warehouse; employee ]"
				+ "picker [ pickerId; pickerTime; pickerBy ]"
				+ "sender [ senderId; senderTime; senderBy ]"),
		@View(name = "DetailOnly", members = "order [ readCode; createTime; warehouse; employee ]"
			+ "picker [ pickerId; pickerTime; pickerBy ]"
			+ "sender [ senderId; senderTime; senderBy ]"
			+ "details"),
})
@Tab(name = "Latest", defaultOrder = "${oid} desc")
public class OrderStore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @Hidden @Column(length = 32)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String oid;

	// private int createBy;
	@DescriptionsList(descriptionProperties = "name") @ManyToOne
	@JoinColumn(name = "createBy", referencedColumnName = "oid") // name:本表格的fk，但物件內不用宣告；referencedColumnName:對應表格的pk
	private Employee employee; // 訂貨人員
	
	@OneToMany(mappedBy="orderStore", cascade=CascadeType.REMOVE) //@AsEmbedded
	private Collection<OrderStoreD> details ;// = new ArrayList<OrderStoreD>(); 
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	private int modifyBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyTime;

	@DefaultValueCalculator(value = ReadCodeGenerator.class, properties = {
			@PropertyValue(name = "dateCode", value = "100315") // Required,
			, @PropertyValue(name = "docType", value = "A") // Required, 由基本檔取出
			, @PropertyValue(name = "wareId", value = "1") // Required, 改由 session 取出
			, @PropertyValue(name = "tableName", value = "SeqGenOrderStore") // Required,
																				// 記錄流水號的表格
	})
	@ReadOnly
	@DisplaySize(20)
	private String readCode;

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

	// private int wareId;
	@DescriptionsList(descriptionProperties = "name")
	@ManyToOne
	@JoinColumn(name = "wareId", referencedColumnName = "oid")
	// name:本表格的fk，但物件內不用宣告；referencedColumnName:對應表格的pk
	private Warehouse warehouse;

//	
//	@Transient @ManyToOne
//	//@JoinColumn(name="itemid",referencedColumnName="oid")// name:本表格的fk，但物件內不用宣告；referencedColumnName:對應表格的pk
////	private Item item;
//	public Item getItem() { // calculated property 無資料庫對應
//		return null;
//	}

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

	public int getModifyBy() {
		return this.modifyBy;
	}

	public void setModifyBy(int modifyBy) {
		this.modifyBy = modifyBy;
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

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
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

}