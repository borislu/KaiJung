package com.kaijung.jpa;

import java.io.Serializable;
import javax.persistence.*;

import org.openxava.annotations.*;


/**
 * The persistent class for the ImportD database table.
 * 
 */
@Entity
public class ImportD implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id //@Hidden
	@TableGenerator(
	    name="SequenceGenerator", table="SequenceGen", 
	    pkColumnName="oid", valueColumnName="value", 
	    pkColumnValue="importD.oid", initialValue=1, allocationSize=1
	)
	@GeneratedValue(strategy = GenerationType.TABLE, generator="SequenceGenerator")
	private int oid;

	private String cartno;
	
	private String batno;

//	private int importOid;
	@ManyToOne 
	@JoinColumn(name="import_oid",referencedColumnName="oid")// name:本表格的fk，但物件內不用宣告；referencedColumnName:對應表格的pk
	private Import import1;

//	private int itemid;
	@ManyToOne @DescriptionsList(descriptionProperties = "name")
	@JoinColumn(name="itemid",referencedColumnName="oid")// name:本表格的fk，但物件內不用宣告；referencedColumnName:對應表格的pk
	private Item item;

	private String memo;

	private String quantity;

	private String status;

	private int unitid;

    public ImportD() {
    }

	public int getOid() {
		return this.oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public String getCartno() {
		return cartno;
	}

	public void setCartno(String cartno) {
		this.cartno = cartno;
	}

	public String getBatno() {
		return batno;
	}

	public void setBatno(String batno) {
		this.batno = batno;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getQuantity() {
		return this.quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getUnitid() {
		return this.unitid;
	}

	public void setUnitid(int unitid) {
		this.unitid = unitid;
	}

	public Import getImport1() {
		return import1;
	}

	public void setImport1(Import import1) {
		this.import1 = import1;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

}