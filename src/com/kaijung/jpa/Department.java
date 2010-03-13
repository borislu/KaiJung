package com.kaijung.jpa;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Department implements Serializable {
	@Id
	private int depid;

	private String name;

	private int areaid;

	private int status;

	private static final long serialVersionUID = 1L;

	public Department() {
		super();
	}

	public int getDepid() {
		return this.depid;
	}

	public void setDepid(int depid) {
		this.depid = depid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAreaid() {
		return this.areaid;
	}

	public void setAreaid(int areaid) {
		this.areaid = areaid;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
