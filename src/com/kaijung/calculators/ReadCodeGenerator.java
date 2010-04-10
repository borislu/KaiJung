package com.kaijung.calculators;
import java.text.*;
import java.util.*;

import javax.persistence.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openxava.calculators.*;
import org.openxava.jpa.*;

import com.kaijung.dao.*;
import com.kaijung.jpa.*;

import common.*;

/*
 * 產生日期碼後接流水碼，ex. 10015-1-1
 * @author Boris Lu 2009/02/15
 */
public class ReadCodeGenerator implements ICalculator{
	
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(ReadCodeGenerator.class);
	
//	private String dateCode; // ex. 100215
	private String docType; // ex. A
	private String wareId; // ex. 1
	private String tableName; // ex. SeqGenOrderStore
	
	public Object calculate() throws Exception {
		String mixId = getDateCode() + wareId ;
//		logger.debug("ReadCodeGenerator.calculate: mixId: "+ mixId );
		int currNo = 0;

		Object result = querySN( mixId );
		if( result == null ){ // 若今天還沒有記錄，先寫入一筆
			SeqGenOrderStore bean = new SeqGenOrderStore();
			bean.setOid( mixId );
			bean.setValue(1);
			try {
				XPersistence.getManager().persist( bean );
				XPersistence.commit(); // 若 id 重複，會有例外: org.hibernate.PersistentObjectException: detached entity passed to persist: com.kaijung.jpa.OrderStoreD
			}
			catch (RollbackException ex) {
				logger.error( ex );
			}
			result = querySN( mixId );
		}else{
			currNo = Integer.parseInt( ""+ result );
			logger.debug( "ReadCodeGenerator.calculate: currNo: "+ currNo );
			// 流水號 +1
			Query query2 = XPersistence.getManager().createQuery(
					"update "+ tableName +" s set s.value = :newNo where oid='"+ mixId +"'"
					);//update SeqGenOrderStore s set s.value = 2 where oid='1002161'
			query2.setParameter( "newNo", currNo + 1 );
			int updated = query2.executeUpdate();
			logger.debug( "ReadCodeGenerator.calculate: query2 return: "+ updated );
		}
		
		logger.debug("ReadCodeGenerator.calculate: return: "+ docType + getDateCode() + "-" + wareId + "-" + currNo );
		// 加上目前的流水號後回傳
		return docType + getDateCode() + "-" + wareId + "-" + currNo ;
	}
	private Object querySN( String mixId ){
		Query query = XPersistence.getManager().createQuery(
				"select max(seq.value) from "+ tableName +" seq where "
				+ "seq.oid = :mixId "
				);
		query.setParameter( "mixId", mixId );
		return query.getSingleResult();
	}
	
//	public Object calculate() throws Exception {
//		SeqGenOrderStore orderGen = XPersistence.getManager().find( SeqGenOrderStore.class, dateCode );
//		return orderGen.getValue();
//	}
   private String getDateCode() {
        DateFormat dateFormat = new SimpleDateFormat("yyMMdd");
        Date date = new Date();
        return dateFormat.format(date);
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
