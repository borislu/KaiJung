package com.kaijung.jpa;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Props implements Serializable {
	@Id
	private int propsid;

	private String name;

	private String size;

	private int x;

	private int y;

	private int z;

	private String remark;

	private int status;

	private static final long serialVersionUID = 1L;

	public Props() {
		super();
	}

	public int getPropsid() {
		return this.propsid;
	}

	public void setPropsid(int propsid) {
		this.propsid = propsid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSize() {
		return this.size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return this.z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
