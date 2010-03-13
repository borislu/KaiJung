package com.kaijung.jpa;

import java.io.Serializable;
import javax.persistence.*;

import org.openxava.annotations.*;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the Item database table.
 * 
 */
@Entity
@Views({
	@View(members=
	    "item [ name, status; enname; barcode, brandid; ]"
	)
})
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

	private String acce1;

	private String acce2;

	private String acce3;

	private byte allowdisc;

	@DisplaySize(20)
	private String articleno;

	private byte asksno;

	@DisplaySize(15)
	private String barcode;

	private byte blockpurchase;

	private byte blocksales;

	private int brandid;

//	private int colorid;
	@DescriptionsList(descriptionProperties="name")
	@ManyToOne 
	@JoinColumn(name="colorid",referencedColumnName="oid")// name:本表格的fk，但物件內不用宣告；referencedColumnName:對應表格的pk
	private ItemColor color;

	private BigDecimal costcurrent;

	private byte costmethod;

	private BigDecimal costpur;

	private BigDecimal coststd;

	private BigDecimal costuse;

	private int countryid;

	private int createBy;

    @Temporal( TemporalType.TIMESTAMP)
	private Date createTime;

	private String curtype;

	private String discgroup;

    @Temporal( TemporalType.TIMESTAMP)
	private Date edate;

	private String enname;

	private int incid;

	private byte issample;

	private int itcid;

	private String itemgroup;

	private String itemno;

	private String itemstatus;

	private int ittid;

	private String memo;

	private String modelno;

	private int modifyBy;

    @Temporal( TemporalType.TIMESTAMP)
	private Date modifyTime;

	private String name;

	private byte negativesales;

	private byte nonstock;

	private byte openprice;

	private int pack;

	@Column(name="pack_unit")
	private int packUnit;

	private int packvolume;

    @Temporal( TemporalType.TIMESTAMP)
	private Date paend;

    @Temporal( TemporalType.TIMESTAMP)
	private Date pastart;

	private String pc;

	private int pntaward;

	private int pntredeem;

    @Temporal( TemporalType.TIMESTAMP)
	private Date prend;

	private String pricegroup;

    @Temporal( TemporalType.TIMESTAMP)
	private Date prstart;

	private float recorderlvl;

	private float recorderqty;

	private String rel1;

	private String rel2;

	private String rel3;

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

	private int salestypeid;

    @Temporal( TemporalType.TIMESTAMP)
	private Date sdate;

	private String sect;

	private String shortdesc;

	private int sizeid;

	private byte staffdisc;

	private int status;

	private int stocktype;

	private String style;

    @Lob()
	private String text;

	private int themeid;

	private int typeid;

	private int unitid;

	private int varcount;

	private String vatgroup;

	private String vendor;

	private String vendoritemno;

	private byte vipdisc;

    public Item() {
    }

	public int getOid() {
		return this.oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public String getAcce1() {
		return this.acce1;
	}

	public void setAcce1(String acce1) {
		this.acce1 = acce1;
	}

	public String getAcce2() {
		return this.acce2;
	}

	public void setAcce2(String acce2) {
		this.acce2 = acce2;
	}

	public String getAcce3() {
		return this.acce3;
	}

	public void setAcce3(String acce3) {
		this.acce3 = acce3;
	}

	public byte getAllowdisc() {
		return this.allowdisc;
	}

	public void setAllowdisc(byte allowdisc) {
		this.allowdisc = allowdisc;
	}

	public String getArticleno() {
		return this.articleno;
	}

	public void setArticleno(String articleno) {
		this.articleno = articleno;
	}

	public byte getAsksno() {
		return this.asksno;
	}

	public void setAsksno(byte asksno) {
		this.asksno = asksno;
	}

	public String getBarcode() {
		return this.barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public byte getBlockpurchase() {
		return this.blockpurchase;
	}

	public void setBlockpurchase(byte blockpurchase) {
		this.blockpurchase = blockpurchase;
	}

	public byte getBlocksales() {
		return this.blocksales;
	}

	public void setBlocksales(byte blocksales) {
		this.blocksales = blocksales;
	}

	public int getBrandid() {
		return this.brandid;
	}

	public void setBrandid(int brandid) {
		this.brandid = brandid;
	}

	public ItemColor getColor() {
		return color;
	}

	public void setColor(ItemColor color) {
		this.color = color;
	}

	public BigDecimal getCostcurrent() {
		return this.costcurrent;
	}

	public void setCostcurrent(BigDecimal costcurrent) {
		this.costcurrent = costcurrent;
	}

	public byte getCostmethod() {
		return this.costmethod;
	}

	public void setCostmethod(byte costmethod) {
		this.costmethod = costmethod;
	}

	public BigDecimal getCostpur() {
		return this.costpur;
	}

	public void setCostpur(BigDecimal costpur) {
		this.costpur = costpur;
	}

	public BigDecimal getCoststd() {
		return this.coststd;
	}

	public void setCoststd(BigDecimal coststd) {
		this.coststd = coststd;
	}

	public BigDecimal getCostuse() {
		return this.costuse;
	}

	public void setCostuse(BigDecimal costuse) {
		this.costuse = costuse;
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

	public String getCurtype() {
		return this.curtype;
	}

	public void setCurtype(String curtype) {
		this.curtype = curtype;
	}

	public String getDiscgroup() {
		return this.discgroup;
	}

	public void setDiscgroup(String discgroup) {
		this.discgroup = discgroup;
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

	public int getIncid() {
		return this.incid;
	}

	public void setIncid(int incid) {
		this.incid = incid;
	}

	public byte getIssample() {
		return this.issample;
	}

	public void setIssample(byte issample) {
		this.issample = issample;
	}

	public int getItcid() {
		return this.itcid;
	}

	public void setItcid(int itcid) {
		this.itcid = itcid;
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

	public int getIttid() {
		return this.ittid;
	}

	public void setIttid(int ittid) {
		this.ittid = ittid;
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

	public byte getNegativesales() {
		return this.negativesales;
	}

	public void setNegativesales(byte negativesales) {
		this.negativesales = negativesales;
	}

	public byte getNonstock() {
		return this.nonstock;
	}

	public void setNonstock(byte nonstock) {
		this.nonstock = nonstock;
	}

	public byte getOpenprice() {
		return this.openprice;
	}

	public void setOpenprice(byte openprice) {
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

	public Date getPaend() {
		return this.paend;
	}

	public void setPaend(Date paend) {
		this.paend = paend;
	}

	public Date getPastart() {
		return this.pastart;
	}

	public void setPastart(Date pastart) {
		this.pastart = pastart;
	}

	public String getPc() {
		return this.pc;
	}

	public void setPc(String pc) {
		this.pc = pc;
	}

	public int getPntaward() {
		return this.pntaward;
	}

	public void setPntaward(int pntaward) {
		this.pntaward = pntaward;
	}

	public int getPntredeem() {
		return this.pntredeem;
	}

	public void setPntredeem(int pntredeem) {
		this.pntredeem = pntredeem;
	}

	public Date getPrend() {
		return this.prend;
	}

	public void setPrend(Date prend) {
		this.prend = prend;
	}

	public String getPricegroup() {
		return this.pricegroup;
	}

	public void setPricegroup(String pricegroup) {
		this.pricegroup = pricegroup;
	}

	public Date getPrstart() {
		return this.prstart;
	}

	public void setPrstart(Date prstart) {
		this.prstart = prstart;
	}

	public float getRecorderlvl() {
		return this.recorderlvl;
	}

	public void setRecorderlvl(float recorderlvl) {
		this.recorderlvl = recorderlvl;
	}

	public float getRecorderqty() {
		return this.recorderqty;
	}

	public void setRecorderqty(float recorderqty) {
		this.recorderqty = recorderqty;
	}

	public String getRel1() {
		return this.rel1;
	}

	public void setRel1(String rel1) {
		this.rel1 = rel1;
	}

	public String getRel2() {
		return this.rel2;
	}

	public void setRel2(String rel2) {
		this.rel2 = rel2;
	}

	public String getRel3() {
		return this.rel3;
	}

	public void setRel3(String rel3) {
		this.rel3 = rel3;
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

	public String getSect() {
		return this.sect;
	}

	public void setSect(String sect) {
		this.sect = sect;
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

	public byte getStaffdisc() {
		return this.staffdisc;
	}

	public void setStaffdisc(byte staffdisc) {
		this.staffdisc = staffdisc;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public int getUnitid() {
		return this.unitid;
	}

	public void setUnitid(int unitid) {
		this.unitid = unitid;
	}

	public int getVarcount() {
		return this.varcount;
	}

	public void setVarcount(int varcount) {
		this.varcount = varcount;
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

	public byte getVipdisc() {
		return this.vipdisc;
	}

	public void setVipdisc(byte vipdisc) {
		this.vipdisc = vipdisc;
	}

}