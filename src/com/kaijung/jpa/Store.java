package com.kaijung.jpa;

import java.io.Serializable;
import javax.persistence.*;

import org.openxava.annotations.*;

import java.util.Date;


/**
 * The persistent class for the Store database table.
 * 
 */
@Entity
@Views({
	@View(members=
	    "store [ name, status; address; country, area; level, mall; remark; modifyBy, modifyTime; createBy, createTime ]"
	)
})
public class Store implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @Hidden
	@TableGenerator(
	    name="SequenceGenerator", table="SequenceGen", 
	    pkColumnName="oid", valueColumnName="value", 
	    pkColumnValue="store.oid", initialValue=1, allocationSize=1
	)
	@GeneratedValue(strategy = GenerationType.TABLE, generator="SequenceGenerator")
	private int oid;

	private String address;

//	private int areaId;

	@ManyToOne(
		optional=false
	)
	@DescriptionsList//(descriptionProperties="name")
	private Country country;

	@ManyToOne(
		optional=false
	)
	@DescriptionsList//(descriptionProperties="name")
	private Area area;

	@ManyToOne(
		optional=false
	)
	@DescriptionsList(descriptionProperties="name")
	private StoreLevel level;

	@ManyToOne(
		optional=false
	)
	@DescriptionsList//(descriptionProperties="name")
	private Mall mall;
	
//	private byte countryId;

	private int createBy;

    @Temporal( TemporalType.TIMESTAMP)
	private Date createTime;

//	private int distId;

    @Temporal( TemporalType.TIMESTAMP)
	private Date eTime;

	private String levelId;

	private int mallId;

	private int modifyBy;

    @Temporal( TemporalType.TIMESTAMP)
	private Date modifyTime;

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

	private int status;

    @Temporal( TemporalType.TIMESTAMP)
	private Date sTime;

    @DisplaySize(28)
	private String name;

    public Store() {
    }

	public int getOid() {
		return this.oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

//	public int getAreaId() {
//		return this.areaId;
//	}
//
//	public void setAreaId(int areaId) {
//		this.areaId = areaId;
//	}

//	public byte getCountryId() {
//		return this.countryId;
//	}
//
//	public void setCountryId(byte countryId) {
//		this.countryId = countryId;
//	}

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

//	public int getDistId() {
//		return this.distId;
//	}
//
//	public void setDistId(int distId) {
//		this.distId = distId;
//	}

	public Date getETime() {
		return this.eTime;
	}

	public void setETime(Date eTime) {
		this.eTime = eTime;
	}

	public String getLevelId() {
		return this.levelId;
	}

	public void setLevelId(String levelId) {
		this.levelId = levelId;
	}

	public int getMallId() {
		return this.mallId;
	}

	public void setMallId(int mallId) {
		this.mallId = mallId;
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

	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}

	public Area getArea() {
		return area;
	}
	public void setArea(Area area) {
		this.area = area;
	}

	public StoreLevel getLevel() {
		return level;
	}
	public void setLevel(StoreLevel level) {
		this.level = level;
	}

	public Mall getMall() {
		return mall;
	}
	public void setMall(Mall mall) {
		this.mall = mall;
	}

	public Date geteTime() {
		return eTime;
	}
	public void seteTime(Date eTime) {
		this.eTime = eTime;
	}

	public Date getsTime() {
		return sTime;
	}
	public void setsTime(Date sTime) {
		this.sTime = sTime;
	}

}