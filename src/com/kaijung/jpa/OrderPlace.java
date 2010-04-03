package com.kaijung.jpa;

import java.io.Serializable;
import javax.persistence.*;

import org.openxava.annotations.*;

import java.util.*;


/**
 * The persistent class for the OrderPlace database table.
 * 
 */
@Entity
@Views( {
	@View(name = "DetailOnly" 
//		, members = 
//		  "order [ orderId; orderTime; wareId; orderBy ]"
//		+ "picker [ readCode; createTime; picker; oid ]"
//		+ "sender [ senderId; senderTime; senderBy ] details"
	)
})
@Tab(name = "Latest", defaultOrder = "${oid} desc"
//	,properties="orderId, orderTime, orderBy, readCode, createTime, status, remark" //
)
public class OrderPlace implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id //@Hidden
	@TableGenerator(
	    name="SequenceGenerator", table="SequenceGen", 
	    pkColumnName="oid", valueColumnName="value", 
	    pkColumnValue="orderPlace.oid", initialValue=1, allocationSize=1
	)
	@GeneratedValue(strategy = GenerationType.TABLE, generator="SequenceGenerator")
	private int oid;

	@OneToMany(mappedBy="orderPlace", cascade=CascadeType.REMOVE) //@AsEmbedded
//	@ListProperties("item.articleno, item.price, item.color.name, 24,26,28,30,32,"
//	+"sum, amount, isCustOrder, modifyId, remark, " // 此2個 remark 不同，前者要從修改單的記錄取出
//	+"warehouse, item.stock.shelf, col, row, 24,26,28,30,32,"
//	+"sum2, amount2, remark, status, oid"
//	)
	private Collection<OrderPlaceD> details ;// = new ArrayList<OrderStoreD>(); 
	
	private int createBy;

    @Temporal( TemporalType.TIMESTAMP)
	private Date createTime;

	private int modifyBy;

    @Temporal( TemporalType.TIMESTAMP)
	private Date modifyTime;

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

	private String status2;

    public OrderPlace() {
    }

	public int getOid() {
		return this.oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public int getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(int createBy) {
		this.createBy = createBy;
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

	public String getStatus2() {
		return this.status2;
	}

	public void setStatus2(String status2) {
		this.status2 = status2;
	}

	public Collection<OrderPlaceD> getDetails() {
		return details;
	}

	public void setDetails(Collection<OrderPlaceD> details) {
		this.details = details;
	}

}