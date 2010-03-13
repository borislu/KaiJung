package com.kaijung.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the ProductLoss database table.
 * 
 */
@Entity
public class ProductLoss implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int lossid;

	private int employeeid;

    @Temporal( TemporalType.TIMESTAMP)
	private Date ontime;

	private int productid;

	private int status;

    public ProductLoss() {
    }

	public int getLossid() {
		return this.lossid;
	}

	public void setLossid(int lossid) {
		this.lossid = lossid;
	}

	public int getEmployeeid() {
		return this.employeeid;
	}

	public void setEmployeeid(int employeeid) {
		this.employeeid = employeeid;
	}

	public Date getOntime() {
		return this.ontime;
	}

	public void setOntime(Date ontime) {
		this.ontime = ontime;
	}

	public int getProductid() {
		return this.productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}