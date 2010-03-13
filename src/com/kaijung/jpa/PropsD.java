package com.kaijung.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the PropsD database table.
 * 
 */
@Entity
public class PropsD implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int pdid;

	private int productid;

	private int propsid;

	private int quantity;

	private int xyz;

	private String y;

	private String z;

    public PropsD() {
    }

	public int getPdid() {
		return this.pdid;
	}

	public void setPdid(int pdid) {
		this.pdid = pdid;
	}

	public int getProductid() {
		return this.productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}

	public int getPropsid() {
		return this.propsid;
	}

	public void setPropsid(int propsid) {
		this.propsid = propsid;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getXyz() {
		return this.xyz;
	}

	public void setXyz(int xyz) {
		this.xyz = xyz;
	}

	public String getY() {
		return this.y;
	}

	public void setY(String y) {
		this.y = y;
	}

	public String getZ() {
		return this.z;
	}

	public void setZ(String z) {
		this.z = z;
	}

}