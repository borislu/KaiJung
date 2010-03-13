package com.kaijung.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the OrderStoreCombine database table.
 * 
 */
@Entity
public class OrderStoreCombine implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int oid;

	private String orderStoreSrc;

	private String orderStoreTar;

	private String remark;

	private String reserve1;

	private String reserve2;

	private String reserve3;

	private String reserve4;

	private String reserve5;

	private int status;

    public OrderStoreCombine() {
    }

	public int getOid() {
		return this.oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public String getOrderStoreSrc() {
		return this.orderStoreSrc;
	}

	public void setOrderStoreSrc(String orderStoreSrc) {
		this.orderStoreSrc = orderStoreSrc;
	}

	public String getOrderStoreTar() {
		return this.orderStoreTar;
	}

	public void setOrderStoreTar(String orderStoreTar) {
		this.orderStoreTar = orderStoreTar;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getReserve1() {
		return this.reserve1;
	}

	public void setReserve1(String reserve1) {
		this.reserve1 = reserve1;
	}

	public String getReserve2() {
		return this.reserve2;
	}

	public void setReserve2(String reserve2) {
		this.reserve2 = reserve2;
	}

	public String getReserve3() {
		return this.reserve3;
	}

	public void setReserve3(String reserve3) {
		this.reserve3 = reserve3;
	}

	public String getReserve4() {
		return this.reserve4;
	}

	public void setReserve4(String reserve4) {
		this.reserve4 = reserve4;
	}

	public String getReserve5() {
		return this.reserve5;
	}

	public void setReserve5(String reserve5) {
		this.reserve5 = reserve5;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}