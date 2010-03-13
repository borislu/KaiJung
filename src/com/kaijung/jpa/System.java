package com.kaijung.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the System database table.
 * 
 */
@Entity
public class System implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int sysid;

	private String loginid;

	private String name;

    @Temporal( TemporalType.TIMESTAMP)
	private Date updatime;

	private String value;

    public System() {
    }

	public int getSysid() {
		return this.sysid;
	}

	public void setSysid(int sysid) {
		this.sysid = sysid;
	}

	public String getLoginid() {
		return this.loginid;
	}

	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getUpdatime() {
		return this.updatime;
	}

	public void setUpdatime(Date updatime) {
		this.updatime = updatime;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}