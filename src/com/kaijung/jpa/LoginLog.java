package com.kaijung.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the LoginLog database table.
 * 
 */
@Entity
public class LoginLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int logid;

	private String loginid;

    @Temporal( TemporalType.TIMESTAMP)
	private Date ontime;

	private String password;

    public LoginLog() {
    }

	public int getLogid() {
		return this.logid;
	}

	public void setLogid(int logid) {
		this.logid = logid;
	}

	public String getLoginid() {
		return this.loginid;
	}

	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}

	public Date getOntime() {
		return this.ontime;
	}

	public void setOntime(Date ontime) {
		this.ontime = ontime;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}