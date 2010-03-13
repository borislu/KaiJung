package com.kaijung.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ProdSup database table.
 * 
 */
@Entity
public class ProdSup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int oid;

	private int productid;

	private int supplierid;

    public ProdSup() {
    }

	public int getOid() {
		return this.oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public int getProductid() {
		return this.productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}

	public int getSupplierid() {
		return this.supplierid;
	}

	public void setSupplierid(int supplierid) {
		this.supplierid = supplierid;
	}

}