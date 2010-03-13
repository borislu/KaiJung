package com.kaijung.jpa;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the CustomerCounter database table.
 * 
 */
@Embeddable
public class CustomerCounterPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int customerid;

	private int counterid;

    public CustomerCounterPK() {
    }
	public int getCustomerid() {
		return this.customerid;
	}
	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}
	public int getCounterid() {
		return this.counterid;
	}
	public void setCounterid(int counterid) {
		this.counterid = counterid;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CustomerCounterPK)) {
			return false;
		}
		CustomerCounterPK castOther = (CustomerCounterPK)other;
		return 
			(this.customerid == castOther.customerid)
			&& (this.counterid == castOther.counterid);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.customerid;
		hash = hash * prime + this.counterid;
		
		return hash;
    }
}