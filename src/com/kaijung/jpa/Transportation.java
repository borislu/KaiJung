package com.kaijung.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the Transportation database table.
 * 
 */
@Entity
public class Transportation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int transid;

	private String barcode;

	private int coorderid;

	private int getman;

    @Temporal( TemporalType.TIMESTAMP)
	private Date gettime;

	private int logisticsman;

    @Temporal( TemporalType.TIMESTAMP)
	private Date logisticstime;

    @Temporal( TemporalType.TIMESTAMP)
	private Date outman;

    @Temporal( TemporalType.TIMESTAMP)
	private Date outtime;

	private String remark;

	private int srcwareid;

	private int status;

	private int tarwareid;

    public Transportation() {
    }

	public int getTransid() {
		return this.transid;
	}

	public void setTransid(int transid) {
		this.transid = transid;
	}

	public String getBarcode() {
		return this.barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public int getCoorderid() {
		return this.coorderid;
	}

	public void setCoorderid(int coorderid) {
		this.coorderid = coorderid;
	}

	public int getGetman() {
		return this.getman;
	}

	public void setGetman(int getman) {
		this.getman = getman;
	}

	public Date getGettime() {
		return this.gettime;
	}

	public void setGettime(Date gettime) {
		this.gettime = gettime;
	}

	public int getLogisticsman() {
		return this.logisticsman;
	}

	public void setLogisticsman(int logisticsman) {
		this.logisticsman = logisticsman;
	}

	public Date getLogisticstime() {
		return this.logisticstime;
	}

	public void setLogisticstime(Date logisticstime) {
		this.logisticstime = logisticstime;
	}

	public Date getOutman() {
		return this.outman;
	}

	public void setOutman(Date outman) {
		this.outman = outman;
	}

	public Date getOuttime() {
		return this.outtime;
	}

	public void setOuttime(Date outtime) {
		this.outtime = outtime;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getSrcwareid() {
		return this.srcwareid;
	}

	public void setSrcwareid(int srcwareid) {
		this.srcwareid = srcwareid;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getTarwareid() {
		return this.tarwareid;
	}

	public void setTarwareid(int tarwareid) {
		this.tarwareid = tarwareid;
	}

}