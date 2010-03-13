package com.kaijung.jpa;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class WarehouseD implements Serializable {
	@Id
	@Column(name="ware_did")
	private int wareDid;

	private int wareid;

	private int productid;

	private String shelf;

	private String remark;

	private int status;

	private static final long serialVersionUID = 1L;

	public WarehouseD() {
		super();
	}

	public int getWareDid() {
		return this.wareDid;
	}

	public void setWareDid(int wareDid) {
		this.wareDid = wareDid;
	}

	public int getWareid() {
		return this.wareid;
	}

	public void setWareid(int wareid) {
		this.wareid = wareid;
	}

	public int getProductid() {
		return this.productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}

	public String getShelf() {
		return this.shelf;
	}

	public void setShelf(String shelf) {
		this.shelf = shelf;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
