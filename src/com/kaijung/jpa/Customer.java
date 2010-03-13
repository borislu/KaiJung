package com.kaijung.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the Customer database table.
 * 
 */
@Entity
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int oid;

    @Temporal( TemporalType.TIMESTAMP)
	private Date bdate;

	private int brasize;

	private int cupid;

	private int discount;

	private int education;

	private int gender;

	private String idno;

	private String loginid;

	private int marriage;

	private String name;

	private String nickname;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="offers_begin")
	private Date offersBegin;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="offers_end")
	private Date offersEnd;

	private int offerstype;

	private int points;

	private int preference;

	private int profession;

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

	private int status;

    @Temporal( TemporalType.TIMESTAMP)
	private Date stime;

	private int underwaresize;

    @Temporal( TemporalType.TIMESTAMP)
	private Date updatime;

    public Customer() {
    }

	public int getOid() {
		return this.oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public Date getBdate() {
		return this.bdate;
	}

	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}

	public int getBrasize() {
		return this.brasize;
	}

	public void setBrasize(int brasize) {
		this.brasize = brasize;
	}

	public int getCupid() {
		return this.cupid;
	}

	public void setCupid(int cupid) {
		this.cupid = cupid;
	}

	public int getDiscount() {
		return this.discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public int getEducation() {
		return this.education;
	}

	public void setEducation(int education) {
		this.education = education;
	}

	public int getGender() {
		return this.gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getIdno() {
		return this.idno;
	}

	public void setIdno(String idno) {
		this.idno = idno;
	}

	public String getLoginid() {
		return this.loginid;
	}

	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}

	public int getMarriage() {
		return this.marriage;
	}

	public void setMarriage(int marriage) {
		this.marriage = marriage;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Date getOffersBegin() {
		return this.offersBegin;
	}

	public void setOffersBegin(Date offersBegin) {
		this.offersBegin = offersBegin;
	}

	public Date getOffersEnd() {
		return this.offersEnd;
	}

	public void setOffersEnd(Date offersEnd) {
		this.offersEnd = offersEnd;
	}

	public int getOfferstype() {
		return this.offerstype;
	}

	public void setOfferstype(int offerstype) {
		this.offerstype = offerstype;
	}

	public int getPoints() {
		return this.points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getPreference() {
		return this.preference;
	}

	public void setPreference(int preference) {
		this.preference = preference;
	}

	public int getProfession() {
		return this.profession;
	}

	public void setProfession(int profession) {
		this.profession = profession;
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

	public Date getStime() {
		return this.stime;
	}

	public void setStime(Date stime) {
		this.stime = stime;
	}

	public int getUnderwaresize() {
		return this.underwaresize;
	}

	public void setUnderwaresize(int underwaresize) {
		this.underwaresize = underwaresize;
	}

	public Date getUpdatime() {
		return this.updatime;
	}

	public void setUpdatime(Date updatime) {
		this.updatime = updatime;
	}

}