package com.kaijung.jpa;

import java.io.Serializable;
import javax.persistence.*;

import org.openxava.annotations.*;

import java.util.*;


/**
 * The persistent class for the Import database table.
 * 
 */
@Entity
public class Import implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id //@Hidden
	@TableGenerator(
	    name="SequenceGenerator", table="SequenceGen", 
	    pkColumnName="oid", valueColumnName="value", 
	    pkColumnValue="import.oid", initialValue=1, allocationSize=1
	)
	@GeneratedValue(strategy = GenerationType.TABLE, generator="SequenceGenerator")
	private int oid;

	@OneToMany(mappedBy="import1", cascade=CascadeType.REMOVE) //@AsEmbedded
//	@ListProperties("item.articleno, item.color.name, item.price, 24,26,28,30,32"
//	+", sum, priority, 24,26,28,30,32, sum2"//, createBy"
//	+", warehouse.name, shelf, x, y, createTime, remark, status, oid"
//	)
	private Collection<ImportD> details ;

	@Temporal( TemporalType.TIMESTAMP)
	private Date gtime;

    @Temporal( TemporalType.TIMESTAMP)
	private Date rtime;

	private String status;

    @Temporal( TemporalType.TIMESTAMP)
	private Date stime;

	private int supplierid;

    public Import() {
    }

	public int getOid() {
		return this.oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public Date getGtime() {
		return this.gtime;
	}

	public void setGtime(Date gtime) {
		this.gtime = gtime;
	}

	public Date getRtime() {
		return this.rtime;
	}

	public void setRtime(Date rtime) {
		this.rtime = rtime;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
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

	public Collection<ImportD> getDetails() {
		return details;
	}

	public void setDetails(Collection<ImportD> details) {
		this.details = details;
	}

}