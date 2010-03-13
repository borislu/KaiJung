package com.kaijung.jpa;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CheckorderD implements Serializable {
	@Id
	@Column(name="checkorder_did")
	private int checkorderDid;

	private int productid;

	private int quantity;

	private String remark;

	private int status;

	private static final long serialVersionUID = 1L;

	public CheckorderD() {
		super();
	}

	public int getCheckorderDid() {
		return this.checkorderDid;
	}

	public void setCheckorderDid(int checkorderDid) {
		this.checkorderDid = checkorderDid;
	}

	public int getProductid() {
		return this.productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
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
