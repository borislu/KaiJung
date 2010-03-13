package com.kaijung.calculators;
import java.text.*;

import javax.persistence.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openxava.calculators.*;
import org.openxava.jpa.*;

/*
 * 產生日期碼後接流水碼，ex. 2009021500001
 * @author Boris Lu 2009/02/15
 */
public class ReadCodeGenerator implements ICalculator{
	
	private static final long serialVersionUID = 1L;
	private static Log log = LogFactory.getLog(ReadCodeGenerator.class);
	
	private String dateCode; // ex. 100215
	private String docType; // ex. A
	private String wareId; // ex. 1
	private String tableName; // ex. SeqGenOrderStore
	
	public Object calculate() throws Exception {
		EntityManager em = XPersistence.getManager();
		String mixId = dateCode + wareId ;
		log.info(" mixId: "+ mixId );

		Query query = em.createQuery(
				"select max(seq.value) from "+ tableName +" seq where "
				+ "seq.oid = :mixId "
				);
		query.setParameter( "mixId", mixId );
		Object result = query.getSingleResult();
		if( result == null ){
			return docType + mixId + "1";
		}else{
			int currNo = Integer.parseInt( ""+ result );
			log.info( "currNo: "+ currNo );
			// 流水號 +1
			Query query2 = em.createQuery(
					"update "+ tableName +" s set s.value = :newNo where oid='"+ mixId +"'"
					);//update SeqGenOrderStore s set s.value = 2 where oid='201002160001'
			query2.setParameter( "newNo", currNo + 1 );
			int updated = query2.executeUpdate();
			log.info( "query2 return: "+ updated );
			
			// 加上目前的流水號後回傳
			return docType + dateCode + "-" + wareId + "-" + currNo ;
		}
	}
//	public Object calculate() throws Exception {
//		SeqGenOrderStore orderGen = XPersistence.getManager().find( SeqGenOrderStore.class, dateCode );
//		return orderGen.getValue();
//	}
	public String getDateCode() {
		return dateCode;
	}
	public void setDateCode(String dateCode) {
		this.dateCode = dateCode;
	}

	public String getDocType() {
		return docType;
	}
	public void setDocType(String docType) {
		this.docType = docType;
	}

	public String getWareId() {
		return wareId;
	}
	public void setWareId(String wareId) {
		this.wareId = wareId;
	}

	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
}
