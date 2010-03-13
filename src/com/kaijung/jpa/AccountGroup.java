package com.kaijung.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the AccountGroup database table.
 * 
 */
@Entity
public class AccountGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int relid;

	private int accountid;

	private int groupid;

    public AccountGroup() {
    }

	public int getRelid() {
		return this.relid;
	}

	public void setRelid(int relid) {
		this.relid = relid;
	}

	public int getAccountid() {
		return this.accountid;
	}

	public void setAccountid(int accountid) {
		this.accountid = accountid;
	}

	public int getGroupid() {
		return this.groupid;
	}

	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}

}