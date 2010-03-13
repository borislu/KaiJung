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
	private int pikSendId;

	private int orderSN;

	private int orderWare;

	private int pickId;

	private int sendId;

    public OrderPickSend() {
    }

	public int getPikSendId() {
		return this.pikSendId;
	}

	public void setPikSendId(int pikSendId) {
		this.pikSendId = pikSendId;
	}

	public int getOrderSN() {
		return this.orderSN;
	}

	public void setOrderSN(int orderSN) {
		this.orderSN = orderSN;
	}

	public int getOrderWare() {
		return this.orderWare;
	}

	public void setOrderWare(int orderWare) {
		this.orderWare = orderWare;
	}

	public int getPickId() {
		return this.pickId;
	}

	public void setPickId(int pickId) {
		this.pickId = pickId;
	}

	public int getSendId() {
		return this.sendId;
	}

	public void setSendId(int sendId) {
		this.sendId = sendId;
	}

}