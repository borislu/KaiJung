package com.kaijung.jpa;

import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class TrandPK implements Serializable {
	private int tranid;

	private int counterid;

	private static final long serialVersionUID = 1L;

	public TrandPK() {
		super();
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

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if ( ! (o instanceof TrandPK)) {
			return false;
		}
		TrandPK other = (TrandPK) o;
		return (this.tranid == other.tranid)
			&& (this.counterid == other.counterid);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.tranid;
		hash = hash * prime + this.counterid;
		return hash;
	}

}
