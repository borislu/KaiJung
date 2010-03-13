package com.kaijung.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the Benchmark database table.
 * 
 */
@Entity
public class Benchmark implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int infoid;

	private String key;

    @Temporal( TemporalType.TIMESTAMP)
	private Date ondate;

	private String remark;

	private String value;

    public Benchmark() {
    }

	public int getInfoid() {
		return this.infoid;
	}

	public void setInfoid(int infoid) {
		this.infoid = infoid;
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Date getOndate() {
		return this.ondate;
	}

	public void setOndate(Date ondate) {
		this.ondate = ondate;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}