package com.kaijung.jpa;

import java.io.Serializable;
import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.calculators.*;

import java.util.Date;


/**
 * The persistent class for the Item database table.
 * 
 */
@Entity
@Views( {
	@View(name = "DetailOnly", members = "name, createTime; articleno, createBy;"
		+ "barcode, color; itemstatus; remark"
	)
})
@Tab(name = "Latest", defaultOrder = "${createTime} desc"
	,properties="name, articleno, barcode, color.sName, createTime, createBy, remark, itemstatus" 
)
public class Item implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @Hidden
	@TableGenerator(
	    name="SequenceGenerator", table="SequenceGen", 
	    pkColumnName="oid", valueColumnName="value", 
	    pkColumnValue="item.oid", initialValue=1, allocationSize=1
	)
	@GeneratedValue(strategy = GenerationType.TABLE, generator="SequenceGenerator")
	private int oid;

   @DisplaySize(20)
	private String articleno;

   @DisplaySize(20)
	private String barcode;

	private int brandid;

//	private int colorid;
	@ManyToOne @DescriptionsList(descriptionProperties="sname") 
	@JoinColumn(name="colorid",referencedColumnName="oid")// name:本表格的fk，但物件內不用宣告；referencedColumnName:對應表格的pk
	private ItemColor color;

	private int countryid;

	private int createBy;

   @Temporal( TemporalType.TIMESTAMP) @DefaultValueCalculator(CurrentDateCalculator.class)
	private Date createTime;

   @Temporal( TemporalType.TIMESTAMP)
	private Date edate;

	private String enname;

	private String issample;

	private String itemgroup;

	private String itemno;

   @DisplaySize(2)
	private String itemstatus;

	private String memo;

	private String modelno;

	private int modifyBy;

   @Temporal( TemporalType.TIMESTAMP) @DefaultValueCalculator(CurrentDateCalculator.class)
	private Date modifyTime;

   @DisplaySize(20)
	private String name;

	private String nonstock;

	private String openprice;

	private int pack;

	@Column(name="pack_unit")
	private int packUnit;

	private int packvolume;

	private float price;

	private String pricegroup;

	private String remark;

	private String reserve1;

	private String reserve10;

	private String reserve2;

	private String reserve3;

	private String reserve4;

	private String reserve5;

	private String reserve6;

	private String reserve7;

	private String reserve8;

	private String reserve9;

	private int salestypeid;

   @Temporal( TemporalType.TIMESTAMP)
	private Date sdate;

	private String shortdesc;

	private int sizeid;

//	private int stockid;
	@ManyToOne @DescriptionsList(descriptionProperties = "shelf")
	@JoinColumn(name = "stockid", referencedColumnName = "oid") // name:本表格的fk，但物件內不用宣告；referencedColumnName:對應表格的pk
	private Stock stock;

	private int stocktype;

	private String style;

    @Lob()
	private String text;

	private int themeid;

	private int typeid;

//	private int unitid;
	@ManyToOne @DescriptionsList(descriptionProperties = "name")
	@JoinColumn(name = "unitid", referencedColumnName = "oid")// name:本表格的fk，但物件內不用宣告；referencedColumnName:對應表格的pk
	private ItemUnit itemUnit;

	private String vatgroup;

	private String vendor;

	private String vendoritemno;
	
	private int salesRank;
	
	private int orderRank;
	

   public Item() {
    }

	public int getOid() {
		return this.oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public String getArticleno() {
		return this.articleno;
	}

	public void setArticleno(String articleno) {
		this.articleno = articleno;
	}

	public String getBarcode() {
		return this.barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public int getBrandid() {
		return this.brandid;
	}

	public void setBrandid(int brandid) {
		this.brandid = brandid;
	}

	public int getCountryid() {
		return this.countryid;
	}

	public void setCountryid(int countryid) {
		this.countryid = countryid;
	}

	public int getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(int createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getEdate() {
		return this.edate;
	}

	public void setEdate(Date edate) {
		this.edate = edate;
	}

	public String getEnname() {
		return this.enname;
	}

	public void setEnname(String enname) {
		this.enname = enname;
	}

	public String getIssample() {
		return this.issample;
	}

	public void setIssample(String issample) {
		this.issample = issample;
	}

	public String getItemgroup() {
		return this.itemgroup;
	}

	public void setItemgroup(String itemgroup) {
		this.itemgroup = itemgroup;
	}

	public String getItemno() {
		return this.itemno;
	}

	public void setItemno(String itemno) {
		this.itemno = itemno;
	}

	public String getItemstatus() {
		return this.itemstatus;
	}

	public void setItemstatus(String itemstatus) {
		this.itemstatus = itemstatus;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getModelno() {
		return this.modelno;
	}

	public void setModelno(String modelno) {
		this.modelno = modelno;
	}

	public int getModifyBy() {
		return this.modifyBy;
	}

	public void setModifyBy(int modifyBy) {
		this.modifyBy = modifyBy;
	}

	public Date getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNonstock() {
		return this.nonstock;
	}

	public void setNonstock(String nonstock) {
		this.nonstock = nonstock;
	}

	public String getOpenprice() {
		return this.openprice;
	}

	public void setOpenprice(String openprice) {
		this.openprice = openprice;
	}

	public int getPack() {
		return this.pack;
	}

	public void setPack(int pack) {
		this.pack = pack;
	}

	public int getPackUnit() {
		return this.packUnit;
	}

	public void setPackUnit(int packUnit) {
		this.packUnit = packUnit;
	}

	public int getPackvolume() {
		return this.packvolume;
	}

	public void setPackvolume(int packvolume) {
		this.packvolume = packvolume;
	}

	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getPricegroup() {
		return this.pricegroup;
	}

	public void setPricegroup(String pricegroup) {
		this.pricegroup = pricegroup;
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

	public int getSalestypeid() {
		return this.salestypeid;
	}

	public void setSalestypeid(int salestypeid) {
		this.salestypeid = salestypeid;
	}

	public Date getSdate() {
		return this.sdate;
	}

	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}

	public String getShortdesc() {
		return this.shortdesc;
	}

	public void setShortdesc(String shortdesc) {
		this.shortdesc = shortdesc;
	}

	public int getSizeid() {
		return this.sizeid;
	}

	public void setSizeid(int sizeid) {
		this.sizeid = sizeid;
	}

	public int getStocktype() {
		return this.stocktype;
	}

	public void setStocktype(int stocktype) {
		this.stocktype = stocktype;
	}

	public String getStyle() {
		return this.style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getThemeid() {
		return this.themeid;
	}

	public void setThemeid(int themeid) {
		this.themeid = themeid;
	}

	public int getTypeid() {
		return this.typeid;
	}

	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}

	public String getVatgroup() {
		return this.vatgroup;
	}

	public void setVatgroup(String vatgroup) {
		this.vatgroup = vatgroup;
	}

	public String getVendor() {
		return this.vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getVendoritemno() {
		return this.vendoritemno;
	}

	public void setVendoritemno(String vendoritemno) {
		this.vendoritemno = vendoritemno;
	}

	public ItemColor getColor() {
		return color;
	}

	public void setColor(ItemColor color) {
		this.color = color;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public ItemUnit getItemUnit() {
		return itemUnit;
	}

	public void setItemUnit(ItemUnit itemUnit) {
		this.itemUnit = itemUnit;
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

}