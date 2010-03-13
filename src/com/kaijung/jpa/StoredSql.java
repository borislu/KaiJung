package com.kaijung.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the StoredSql database table.
 * 
 */
@Entity
public class StoredSql implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int sqlid;

	private String name;

    @Temporal( TemporalType.TIMESTAMP)
	private Date ontime;

	private String sql;

    public StoredSql() {
    }

	public int getSqlid() {
		return this.sqlid;
	}

	public void setSqlid(int sqlid) {
		this.sqlid = sqlid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getOntime() {
		return this.ontime;
	}

	public void setOntime(Date ontime) {
		this.ontime = ontime;
	}

	public String getSql() {
		return this.sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

}