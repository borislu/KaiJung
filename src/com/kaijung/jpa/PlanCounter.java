package com.kaijung.jpa;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PlanCounter implements Serializable {
	@Id
	private int plancounterid;

	private int planid;

	private int counterid;

	private static final long serialVersionUID = 1L;

	public PlanCounter() {
		super();
	}

	public int getPlancounterid() {
		return this.plancounterid;
	}

	public void setPlancounterid(int plancounterid) {
		this.plancounterid = plancounterid;
	}

	public int getPlanid() {
		return this.planid;
	}

	public void setPlanid(int planid) {
		this.planid = planid;
	}

	public int getCounterid() {
		return this.counterid;
	}

	public void setCounterid(int counterid) {
		this.counterid = counterid;
	}

}
