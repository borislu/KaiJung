package com.kaijung.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the Import database table.
 * 
 */
@Entity
public class Import implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int importid;

	private String cartonno;

    @Temporal( TemporalType.TIMESTAMP)
	private Date gtime;

	private float price;

	private int quantity;

    @Temporal( TemporalType.TIMESTAMP)
	private Date rtime;

	private int status;

    @Temporal( TemporalType.TIMESTAMP)
	private Date stime;

	private int supplierid;

	private String unit;

    public Import() {
    }

	public int getImportid() {
		return this.importid;
	}

	public void setImportid(int importid) {
		this.importid = importid;
	}

	public String getCartonno() {
		return this.cartonno;
	}

	public void setCartonno(String cartonno) {
		this.cartonno = cartonno;
	}

	public Date getGtime() {
		return this.gtime;
	}

	public void setGtime(Date gtime) {
		this.gtime = gtime;
	}

	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getRtime() {
		return this.rtime;
	}

	public void setRtime(Date rtime) {
		this.rtime = rtime;
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

	public int getSupplierid() {
		return this.supplierid;
	}

	public void setSupplierid(int supplierid) {
		this.supplierid = supplierid;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

}