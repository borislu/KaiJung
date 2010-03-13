package com.kaijung.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the PlanStore database table.
 * 
 */
@Entity
public class PlanStore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int plancounterid;

	private int counterid;

	private int planid;

    public PlanStore() {
    }

	public int getPlancounterid() {
		return this.plancounterid;
	}

	public void setPlancounterid(int plancounterid) {
		this.plancounterid = plancounterid;
	}

	public int getCounterid() {
		return this.counterid;
	}

	public void setCounterid(int counterid) {
		this.counterid = counterid;
	}

	public int getPlanid() {
		return this.planid;
	}

	public void setPlanid(int planid) {
		this.planid = planid;
	}

}