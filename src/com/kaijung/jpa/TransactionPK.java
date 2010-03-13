package com.kaijung.jpa;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the Transactions database table.
 * 
 */
@Embeddable
public class TransactionPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int tranid;

	private int counterid;

    public TransactionPK() {
    }
	public int getTranid() {
		return this.tranid;
	}
	public void setTranid(int tranid) {
		this.tranid = tranid;
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
		if (!(other instanceof TransactionPK)) {
			return false;
		}
		TransactionPK castOther = (TransactionPK)other;
		return 
			(this.tranid == castOther.tranid)
			&& (this.counterid == castOther.counterid);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.tranid;
		hash = hash * prime + this.counterid;
		
		return hash;
    }
}