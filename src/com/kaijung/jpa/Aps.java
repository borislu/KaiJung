package com.kaijung.jpa;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Aps implements Serializable {
	@Id
	private int apid;

	private String localname;

	private String enname;

	private String desc;

	private int status;

	private static final long serialVersionUID = 1L;

	public Aps() {
		super();
	}

	public int getApid() {
		return this.apid;
	}

	public void setApid(int apid) {
		this.apid = apid;
	}

	public String getLocalname() {
		return this.localname;
	}

	public void setLocalname(String localname) {
		this.localname = localname;
	}

	public String getEnname() {
		return this.enname;
	}

	public void setEnname(String enname) {
		this.enname = enname;
	}

	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
