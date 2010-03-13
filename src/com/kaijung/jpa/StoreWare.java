package com.kaijung.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the StoreWare database table.
 * 
 */
@Entity
public class StoreWare implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int warecounterid;

	private int counterid;

	private int wareid;

    public StoreWare() {
    }

	public int getWarecounterid() {
		return this.warecounterid;
	}

	public void setWarecounterid(int warecounterid) {
		this.warecounterid = warecounterid;
	}

	public int getCounterid() {
		return this.counterid;
	}

	public void setCounterid(int counterid) {
		this.counterid = counterid;
	}

	public int getWareid() {
		return this.wareid;
	}

	public void setWareid(int wareid) {
		this.wareid = wareid;
	}

}