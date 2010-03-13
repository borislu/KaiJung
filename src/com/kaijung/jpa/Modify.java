package com.kaijung.jpa;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Modify implements Serializable {
	@Id
	private int modifyid;

	private String desc;

	private int status;

	private static final long serialVersionUID = 1L;

	public Modify() {
		super();
	}

	public int getModifyid() {
		return this.modifyid;
	}

	public void setModifyid(int modifyid) {
		this.modifyid = modifyid;
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
