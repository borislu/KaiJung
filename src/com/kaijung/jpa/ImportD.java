package com.kaijung.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ImportD database table.
 * 
 */
@Entity
public class ImportD implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int idid;

	private int importid;

	private int productid;

	private int quantity;

	private String remark;

	private int status;

	private int unitid;

    public ImportD() {
    }

	public int getIdid() {
		return this.idid;
	}

	public void setIdid(int idid) {
		this.idid = idid;
	}

	public int getImportid() {
		return this.importid;
	}

	public void setImportid(int importid) {
		this.importid = importid;
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

	public int getUnitid() {
		return this.unitid;
	}

	public void setUnitid(int unitid) {
		this.unitid = unitid;
	}

}