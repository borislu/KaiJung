package com.kaijung.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the TranModifyLog database table.
 * 
 */
@Entity
public class TranModifyLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="tran_modifyid")
	private int tranModifyid;

	private int counterid;

	private String loginid;

    @Temporal( TemporalType.TIMESTAMP)
	private Date modifytime;

	private int newtranid;

	private int status;

	private int tranid;

    public TranModifyLog() {
    }

	public int getTranModifyid() {
		return this.tranModifyid;
	}

	public void setTranModifyid(int tranModifyid) {
		this.tranModifyid = tranModifyid;
	}

	public int getCounterid() {
		return this.counterid;
	}

	public void setCounterid(int counterid) {
		this.counterid = counterid;
	}

	public String getLoginid() {
		return this.loginid;
	}

	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}

	public Date getModifytime() {
		return this.modifytime;
	}

	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}

	public int getNewtranid() {
		return this.newtranid;
	}

	public void setNewtranid(int newtranid) {
		this.newtranid = newtranid;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getTranid() {
		return this.tranid;
	}

	public void setTranid(int tranid) {
		this.tranid = tranid;
	}

}