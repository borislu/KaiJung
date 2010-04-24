package com.kaijung.jpa;

import java.io.Serializable;
import javax.persistence.*;

import org.openxava.annotations.*;

import java.util.Date;


/**
 * The persistent class for the ItemColor database table.
 * 
 */
@Entity
@Views({
	@View(members=
	    "colorCode, sname, name; modifyBy, modifyTime; createBy, createTime"
	)
})
@Tabs({
	@Tab(properties="colorCode, sname, name, modifyBy, modifyTime, createBy, createTime", 
		defaultOrder="${modifyTime} desc"
	),
	@Tab(name="Latest", properties="colorCode, sname, name, modifyBy, modifyTime, createBy, createTime", 
		defaultOrder="${modifyTime} desc"
	)
})
public class ItemColor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @Hidden
	@TableGenerator(
	    name="SequenceGenerator", table="SequenceGen", 
	    pkColumnName="oid", valueColumnName="value", 
	    pkColumnValue="color.oid", initialValue=1, allocationSize=1
	)
	@GeneratedValue(strategy = GenerationType.TABLE, generator="SequenceGenerator")
	private int oid;
	
	@DisplaySize(5)
	private String colorCode;

	private int createBy;

    @Temporal( TemporalType.TIMESTAMP)
	private Date createTime;

	private int modifyBy;

    @Temporal( TemporalType.TIMESTAMP)
	private Date modifyTime;
	@DisplaySize(10)
	private String name;

	private int prnOrder;

	private String remark;

	private String reserve1;

	private String reserve2;

	private String reserve3;

	private String reserve4;

	private String reserve5;
	@DisplaySize(5)
	private String sname;

	public ItemColor() {
    }

	public int getOid() {
		return this.oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public String getColorCode() {
		return this.colorCode;
	}

	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}

	public int getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(int createBy) {
		this.createBy = createBy;
	}

	public int getModifyBy() {
		return this.modifyBy;
	}

	public void setModifyBy(int modifyBy) {
		this.modifyBy = modifyBy;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrnOrder() {
		return this.prnOrder;
	}

	public void setPrnOrder(int prnOrder) {
		this.prnOrder = prnOrder;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

   public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

}