package com.kaijung.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TranD database table.
 * 
 */
@Entity
public class TranD implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TranDPK id;

	private float discount;

	private float price;

	private int productid;

	private int quantity;

    public TranD() {
    }

	public TranDPK getId() {
		return this.id;
	}

	public void setId(TranDPK id) {
		this.id = id;
	}
	
	public float getDiscount() {
		return this.discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
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

}