package com.kaijung.jpa;

import java.io.Serializable;
import javax.persistence.*;

import org.openxava.annotations.*;

import java.util.Date;


/**
 * The persistent class for the Warehouse database table.
 * 
 */
@Entity
@Views({
	@View(members=
	    "warehouse [ name, status; ceiling, barcode; namingId, originalId; " +
	    "handler, storeId; sTime, eTime; " +
	    "remark; modifyBy, modifyTime; createBy, createTime ]"
	)
})
@Tab(name="Latest", properties="oid, name, status, ceiling, remark, modifyBy, modifyTime", 
	defaultOrder="${oid} desc"
)
public class Warehouse implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @Hidden
	@TableGenerator(
	    name="SequenceGenerator", table="SequenceGen", 
	    pkColumnName="oid", valueColumnName="value", 
	    pkColumnValue="warehouse.oid", initialValue=1, allocationSize=1
	)
	@GeneratedValue(strategy = GenerationType.TABLE, generator="SequenceGenerator")
	private int oid;

    @DisplaySize(20)
	private String barcode;

	private int createBy;

    @Temporal( TemporalType.TIMESTAMP)
	private Date createTime;

	@Temporal( TemporalType.TIMESTAMP)
	private Date eTime;

	private int handler;

	private int ceiling;

	private int modifyBy;

    @Temporal( TemporalType.TIMESTAMP)
	private Date modifyTime;

    @DisplaySize(28)
	private String name;

    @DisplaySize(20)
	private String namingId;

    @DisplaySize(20)
	private String originalId;

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

	private String sName;

	private int status;

    @Temporal( TemporalType.TIMESTAMP)
	private Date sTime;

	private int storeId;

	public Warehouse() {
    }

	public int getOid() {
		return this.oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public String getBarcode() {
		return this.barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
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

	public Date getETime() {
		return this.eTime;
	}

	public void setETime(Date eTime) {
		this.eTime = eTime;
	}

	public int getHandler() {
		return this.handler;
	}

	public void setHandler(int handler) {
		this.handler = handler;
	}

	public int getCeiling() {
		return this.ceiling;
	}

	public void setCeiling(int ceiling) {
		this.ceiling = ceiling;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNamingId() {
		return this.namingId;
	}

	public void setNamingId(String namingId) {
		this.namingId = namingId;
	}

	public String getOriginalId() {
		return this.originalId;
	}

	public void setOriginalId(String originalId) {
		this.originalId = originalId;
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

	public String getSName() {
		return this.sName;
	}

	public void setSName(String sName) {
		this.sName = sName;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getSTime() {
		return this.sTime;
	}

	public void setSTime(Date sTime) {
		this.sTime = sTime;
	}

	public int getStoreId() {
		return this.storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

    public Date geteTime() {
		return eTime;
	}
	public void seteTime(Date eTime) {
		this.eTime = eTime;
	}

	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}

	public Date getsTime() {
		return sTime;
	}
	public void setsTime(Date sTime) {
		this.sTime = sTime;
	}
}