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
//@SqlResultSetMapping(name="detailsAndOrder", 
//	entities={
//		@EntityResult ( entityClass = OrderStore.class) 
//		,@EntityResult ( entityClass = OrderStoreD.class)
//		,@EntityResult ( entityClass = Warehouse.class)
//		,@EntityResult ( entityClass = Item.class)
//		,@EntityResult ( entityClass = Stock.class)
//	}
//)

@Entity
//@Views( {
//	@View(members = 
//		"header[ oid ]"
//	)
//})
@Tab(properties="ware, articleno, color, 24, 26, 28, 30, 32, 24, 26, 28, 30, 32, 24, 26, 28, 30, 32, sum, isCustOrder" +
	", 24, 26, 28, 30, 32, sum, 24, 26, 28, 30, 32, sum, 24, 26, 28, 30, 32, sum, 24, 26, 28, 30, 32, sum" +
	", broken, salesRank, orderRank, storeSalesRank, storeOrderRank" 
)
public class OrderDiff implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id //@Hidden
//	@TableGenerator(
//	    name="SequenceGenerator", table="SequenceGen", 
//	    pkColumnName="difid", valueColumnName="value", 
//	    pkColumnValue="orderDiff.oid", initialValue=1, allocationSize=1
//	)
//	@GeneratedValue(strategy = GenerationType.TABLE, generator="SequenceGenerator")
	private int difid;

	@DisplaySize(6) @Transient
	public String getWare() { return ""; } // 專櫃，無資料庫對應
	
	@DisplaySize(6) @Transient
	public String getArticleno() { return ""; } // 貨號，無資料庫對應
	
	@DisplaySize(6) @Transient
	public String getColor() { return ""; } // 顏色，無資料庫對應

	@DisplaySize(6) @Transient
	public String get24() { return ""; } // 尺寸，無資料庫對應

	@DisplaySize(6) @Transient
	public String get26() { return ""; } // 尺寸，無資料庫對應

	@DisplaySize(6) @Transient
	public String get28() { return ""; } // 尺寸，無資料庫對應

	@DisplaySize(6) @Transient
	public String get30() { return ""; } // 尺寸，無資料庫對應

	@DisplaySize(6) @Transient
	public String get32() { return ""; } // 尺寸，無資料庫對應

	@DisplaySize(6) @Transient
	public String getSum() { return ""; } // 小計，無資料庫對應

	@DisplaySize(6) @Transient
	public String getIsCustOrder() { return ""; } // 客訂，無資料庫對應

	@DisplaySize(6) @Transient
	public String getBroken() { return ""; } // 斷貨，無資料庫對應

	@DisplaySize(6) @Transient
	public String getSalesRank() { return ""; } // 銷貨排名，無資料庫對應

	@DisplaySize(6) @Transient
	public String getOrderRank() { return ""; } // 訂貨排名，無資料庫對應

	@DisplaySize(6) @Transient
	public String getStoreSalesRank() { return ""; } // 專櫃銷貨排名，無資料庫對應

	@DisplaySize(6) @Transient
	public String getStoreOrderRank() { return ""; } // 專櫃訂貨排名，無資料庫對應

	public int getDifid() {
		return difid;
	}

	public void setDifid(int difid) {
		this.difid = difid;
	}

}