package com.kaijung.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ApGroup database table.
 * 
 */
@Entity
public class ApGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int authorityid;

	private int apid;

	private int groupid;

	private int status;

    public ApGroup() {
    }

	public int getAuthorityid() {
		return this.authorityid;
	}

	public void setAuthorityid(int authorityid) {
		this.authorityid = authorityid;
	}

	public int getApid() {
		return this.apid;
	}

	public void setApid(int apid) {
		this.apid = apid;
	}

	public int getGroupid() {
		return this.groupid;
	}

	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}