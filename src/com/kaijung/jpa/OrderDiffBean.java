package com.kaijung.jpa;

import java.io.Serializable;
import javax.persistence.*;

import org.openxava.annotations.*;

import com.kaijung.calculators.*;

import java.util.*;


/**
 * 訂貨差異分析(無對應資料庫表格)
 * 
 */
public class OrderDiffBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String wareName; // 專櫃名稱
	private String articleno; // 貨號
	private String colorName; // 顏色簡稱
	private String stockQty; // 庫位尺寸及數量(json)
	private String salesQty; // 銷售速度尺寸及數量(json)
	private String orderQty; // 訂貨尺寸及數量(json)
	private String isCustOrder; //是否客訂
	private String sendQty; // 出貨尺寸及數量(json)
	private String placeQty; // 庫架尺寸及數量(json)
	private String markQty; // 備貨尺寸及數量(json)
	private String preMarkQty; // 待備貨尺寸及數量(json)
	private boolean noSize; // 是否斷碼
	private int salesRank; // 銷貨排名
	private int orderRank; // 訂貨排名
	private int storeSalesRank; // 專櫃銷貨排名
	private int storeOrderRank; // 專櫃訂貨排名
	
	public String getWareName() {
		return wareName;
	}
	public void setWareName(String wareName) {
		this.wareName = wareName;
	}
	public String getArticleno() {
		return articleno;
	}
	public void setArticleno(String articleno) {
		this.articleno = articleno;
	}
	public String getColorName() {
		return colorName;
	}
	public void setColorName(String colorName) {
		this.colorName = colorName;
	}
	public String getStockQty() {
		return stockQty;
	}
	public void setStockQty(String stockQty) {
		this.stockQty = stockQty;
	}
	public String getSalesQty() {
		return salesQty;
	}
	public void setSalesQty(String salesQty) {
		this.salesQty = salesQty;
	}
	public String getOrderQty() {
		return orderQty;
	}
	public void setOrderQty(String orderQty) {
		this.orderQty = orderQty;
	}
	public String getSendQty() {
		return sendQty;
	}
	public void setSendQty(String sendQty) {
		this.sendQty = sendQty;
	}
	public String getPlaceQty() {
		return placeQty;
	}
	public void setPlaceQty(String placeQty) {
		this.placeQty = placeQty;
	}
	public String getMarkQty() {
		return markQty;
	}
	public void setMarkQty(String markQty) {
		this.markQty = markQty;
	}
	public String getPreMarkQty() {
		return preMarkQty;
	}
	public void setPreMarkQty(String preMarkQty) {
		this.preMarkQty = preMarkQty;
	}
	public boolean isNoSize() {
		return noSize;
	}
	public void setNoSize(boolean noSize) {
		this.noSize = noSize;
	}
	public int getSalesRank() {
		return salesRank;
	}
	public void setSalesRank(int salesRank) {
		this.salesRank = salesRank;
	}
	public int getOrderRank() {
		return orderRank;
	}
	public void setOrderRank(int orderRank) {
		this.orderRank = orderRank;
	}
	public int getStoreSalesRank() {
		return storeSalesRank;
	}
	public void setStoreSalesRank(int storeSalesRank) {
		this.storeSalesRank = storeSalesRank;
	}
	public int getStoreOrderRank() {
		return storeOrderRank;
	}
	public void setStoreOrderRank(int storeOrderRank) {
		this.storeOrderRank = storeOrderRank;
	}
	public String getIsCustOrder() {
		return isCustOrder;
	}
	public void setIsCustOrder(String isCustOrder) {
		this.isCustOrder = isCustOrder;
	}
}