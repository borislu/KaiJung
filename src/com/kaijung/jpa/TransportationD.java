package com.kaijung.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TransportationD database table.
 * 
 */
@Entity
public class TransportationD implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="trans_did")
	private int transDid;

	private String cartonno;

	private int productid;

	private int quantity;

	private String remark;

	private int status;

    public TransportationD() {
    }

	public int getTransDid() {
		return this.transDid;
	}

	public void setTransDid(int transDid) {
		this.transDid = transDid;
	}

	public String getCartonno() {
		return this.cartonno;
	}

	public void setCartonno(String cartonno) {
		this.cartonno = cartonno;
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