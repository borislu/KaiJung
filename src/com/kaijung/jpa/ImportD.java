package com.kaijung.jpa;

import java.io.Serializable;
import javax.persistence.*;


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

	private String cartonno;

//	private int importOid;
	@ManyToOne 
	@JoinColumn(name="import_oid",referencedColumnName="oid")// name:本表格的fk，但物件內不用宣告；referencedColumnName:對應表格的pk
	private Import import1;

	private int itemid;

	private String memo;

	private int quantity;

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

	public String getCartonno() {
		return this.cartonno;
	}

	public void setCartonno(String cartonno) {
		this.cartonno = cartonno;
	}

	public int getItemid() {
		return this.itemid;
	}

	public void setItemid(int itemid) {
		this.itemid = itemid;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
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

}