package com.kaijung.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the SqlGroup database table.
 * 
 */
@Entity
public class SqlGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int sqlid;

	private int groupid;

    public SqlGroup() {
    }

	public int getSqlid() {
		return this.sqlid;
	}

	public void setSqlid(int sqlid) {
		this.sqlid = sqlid;
	}

	public int getGroupid() {
		return this.groupid;
	}

	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}

}