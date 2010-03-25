package com.kaijung.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the SeqGenOrderStore database table.
 * 
 */
@Entity
public class SeqGenOrderStore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String oid;

	private int value;

    public SeqGenOrderStore() {
    }

	public String getOid() {
		return this.oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public int getValue() {
		return this.value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}