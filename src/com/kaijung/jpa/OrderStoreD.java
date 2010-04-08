package com.kaijung.jpa;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;
import javax.persistence.Entity;

import org.hibernate.annotations.*;
import org.openxava.annotations.*;
import org.openxava.jpa.*;

import com.kaijung.dao.*;
import common.*;

/**
 * The persistent class for the OrderStoreD database table.
 * 
 */
@Entity
@Views( { @View(name = "Simple", members = "name, status; enname; barcode, brandid;") })
@Tabs( {
		@Tab(properties = "item.barcode, item.articleno, item.color.name, isCustOrder, item.name, quantity, remark, status", defaultOrder = "${oid} desc"),
		@Tab(name = "Latest", properties = "item.barcode, item.articleno, item.color.name, isCustOrder, item.name, quantity, remark, status", defaultOrder = "${oid} desc") })
public class OrderStoreD implements Serializable {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(OrderStoreD.class);

	@Id @Hidden @Column(length = 32)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String oid;

	// private String orderStore_oid;
	@ManyToOne
	@JoinColumn(name = "orderStore_oid", referencedColumnName = "oid")
	// name:本表格的fk，但物件內不用宣告；referencedColumnName:對應表格的pk
	private OrderStore orderStore;

	// private int itemid;
	@ManyToOne
	@JoinColumn(name = "itemid", referencedColumnName = "oid")
	// name:本表格的fk，但物件內不用宣告；referencedColumnName:對應表格的pk
	private Item item;

	private String isCustOrder;

	private String modifyid;

	private String quantity;

	private String remark;

	@Hidden
	private String reserve1;
	@Hidden
	private String reserve10;
	@Hidden
	private String reserve2;
	@Hidden
	private String reserve3;
	@Hidden
	private String reserve4;
	@Hidden
	private String reserve5;
	@Hidden
	private String reserve6;
	@Hidden
	private String reserve7;
	@Hidden
	private String reserve8;
	@Hidden
	private String reserve9;

	@ReadOnly
	private String status;

	@DisplaySize(6)
	@Transient
	public String get24() {
		return "";
	} // 尺寸，無資料庫對應

	@DisplaySize(6)
	@Transient
	public String get26() {
		return "";
	} // 尺寸，無資料庫對應

	@DisplaySize(6)
	@Transient
	public String get28() {
		return "";
	} // 尺寸，無資料庫對應

	@DisplaySize(6)
	@Transient
	public String get30() {
		return "";
	} // 尺寸，無資料庫對應

	@DisplaySize(6)
	@Transient
	public String get32() {
		return "";
	} // 尺寸，無資料庫對應

	@DisplaySize(6)
	@Transient
	public String getSum() {
		return "";
	} // 小計，無資料庫對應

	@DisplaySize(8)
	@Transient
	public String getAmount() {
		return "";
	} // 金額，無資料庫對應

	@DisplaySize(6)
	@Transient
	public String getModifyId() {
		return "";
	} // 修改單號，無資料庫對應

	public Collection<OrderStoreD> getOrderD(String headId) {
		EntityManager em = XPersistence.getManager();
		Query query = null;
		Collection<OrderStoreD> resultList = null;
		try {
			// Query query =
			// XPersistence.getManager().createQuery("from Carrier c where " +
			// "c.warehouse.zoneNumber = :zone AND " +
			// "c.warehouse.number = :warehouseNumber AND " +
			// "NOT (c.number = :number) ");
			// query.setParameter("zone", getWarehouse().getZoneNumber());
			// query.setParameter("warehouseNumber",
			// getWarehouse().getNumber());
			// query.setParameter("number", getNumber());
			query = em.createQuery("SELECT osd FROM OrderStoreD AS osd"
					+ " WHERE osd.orderStore_oid = " + headId);
			// query.setParameter( "item", getItem().getOid() );
			// query.setParameter( "item", 2 );
			resultList = query.getResultList();
			logger.debug("OrderStoreD.getOrderD: result: " + resultList);
		} catch (Exception e) {
			logger.error("OrderStoreD.getOrderD: " + e);
		}
		return resultList;
	}

	public Collection<OrderStoreD> getDetailSet(String oid) {//以明細檔的oid(任何一個)，查出所有的明細檔
		EntityManager em = XPersistence.getManager();
		Query query = null;
		Collection<OrderStoreD> resultList = null;
		String headId = getHeadId ( oid );
		logger.debug("OrderStoreD.getDetailSet: headId: " + headId);
		try {
			query = em.createQuery("SELECT osd FROM OrderStoreD AS osd"
					+ " WHERE osd.orderStore.oid = :headId");
			query.setParameter( "headId", headId );
			resultList = query.getResultList();
			logger.debug("OrderStoreD.getDetailSet: result: " + resultList);
		} catch (Exception e) {
			logger.error("OrderStoreD.getDetailSet: " + e);
		}
		return resultList;
	}

	public String getHeadId ( String oid ){//以明細檔的oid(任何一個)，查出主檔的oid
		EntityManager em = XPersistence.getManager();
		Query query = null;
		String headId = null;
//		logger.debug("OrderStoreD.getHeadId: oid: "+ oid);
		try {
			query = em.createQuery("SELECT DISTINCT osd FROM OrderStoreD osd"
					+ " WHERE oid = :oid");//SELECT DISTINCT a FROM Author a INNER JOIN a.books b WHERE b.publisher.name = 'XYZ Press'

			query.setParameter( "oid", oid );
			OrderStoreD orderD = (OrderStoreD) query.getSingleResult();
			headId = orderD.getOrderStore().getOid();
//			logger.debug("OrderStoreD.getHeadId: headId: "+ headId);
		} catch (Exception e) {
			logger.error("OrderStoreD.getHeadId: " + e);
		}
		return headId;
	}
	
	public int update(String oid, String quantity, String modifyid,
			String isCustOrder, String memo) {
		logger.debug("OrderStoreD.update: " +
				"oid: " + oid 
				+ ", quantity: "	+ quantity
				+ ", modifyid: "	+ modifyid
				+ ", isCustOrder: "	+ isCustOrder
				+ ", memo: "	+ memo
				);

		EntityManager em = XPersistence.getManager();
		OrderStoreD orderStoreD = em.find ( OrderStoreD.class, oid );
		logger.debug("OrderStoreD.update: orderStoreD: " + orderStoreD );
		if( orderStoreD != null ){
			orderStoreD.setQuantity(quantity);
			orderStoreD.setModifyid(modifyid);
			orderStoreD.setIsCustOrder(isCustOrder);
			orderStoreD.setRemark(memo);
			try {
				em.merge( orderStoreD );
				XPersistence.commit();
			} catch (Exception e) {
				logger.error("OrderStoreD.update: " + e);
			}
			return 1; // 1:成功
		}

		return 0; // 0:失敗
	}

	public OrderStoreD() {
	}

	public OrderStoreD(String oid, boolean iscustOrder, String quantity) {
		setOid(oid);
		setIsCustOrder(isCustOrder);
		setQuantity(quantity);
	}

	public String getOid() {
		return this.oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getIsCustOrder() {
		return this.isCustOrder;
	}

	public void setIsCustOrder(String isCustOrder) {
		this.isCustOrder = isCustOrder;
	}

	public String getModifyid() {
		return this.modifyid;
	}

	public void setModifyid(String modifyid) {
		this.modifyid = modifyid;
	}

	public String getQuantity() {
		return this.quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
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

	public String getReserve10() {
		return this.reserve10;
	}

	public void setReserve10(String reserve10) {
		this.reserve10 = reserve10;
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

	public String getReserve6() {
		return this.reserve6;
	}

	public void setReserve6(String reserve6) {
		this.reserve6 = reserve6;
	}

	public String getReserve7() {
		return this.reserve7;
	}

	public void setReserve7(String reserve7) {
		this.reserve7 = reserve7;
	}

	public String getReserve8() {
		return this.reserve8;
	}

	public void setReserve8(String reserve8) {
		this.reserve8 = reserve8;
	}

	public String getReserve9() {
		return this.reserve9;
	}

	public void setReserve9(String reserve9) {
		this.reserve9 = reserve9;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public OrderStore getOrderStore() {
		return orderStore;
	}

	public void setOrderStore(OrderStore orderStore) {
		this.orderStore = orderStore;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

}