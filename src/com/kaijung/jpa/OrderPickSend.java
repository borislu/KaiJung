package com.kaijung.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the OrderPickSend database table.
 * 
 */
@Entity
public class OrderPickSend implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int opsid;

	private String orderDid;

	private int pickDid;

	private String reserve1;

	private int sendDid;

	private int wareId;

    public OrderPickSend() {
    }

	public int getOpsid() {
		return this.opsid;
	}

	public void setOpsid(int opsid) {
		this.opsid = opsid;
	}

	public String getOrderDid() {
		return this.orderDid;
	}

	public void setOrderDid(String orderDid) {
		this.orderDid = orderDid;
	}

	public int getPickDid() {
		return this.pickDid;
	}

	public void setPickDid(int pickDid) {
		this.pickDid = pickDid;
	}

	public String getReserve1() {
		return this.reserve1;
	}

	public void setReserve1(String reserve1) {
		this.reserve1 = reserve1;
	}

	public int getSendDid() {
		return this.sendDid;
	}

	public void setSendDid(int sendDid) {
		this.sendDid = sendDid;
	}

	public int getWareId() {
		return this.wareId;
	}

	public void setWareId(int wareId) {
		this.wareId = wareId;
	}

}