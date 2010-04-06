package com.kaijung.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.persistence.*;
import org.apache.commons.logging.*;
import org.apache.commons.logging.impl.*;
import org.openxava.hibernate.*;
import org.openxava.jpa.*;
//import org.zkforge.json.simple.*;
//import org.zkforge.json.simple.parser.*;
import org.zkoss.zk.ui.*;
import com.kaijung.jpa.*;
import common.*;

import org.json.simple.*;
import org.json.simple.parser.*;
/*
 * author Boris.lds@gmail.com
 */
@SuppressWarnings("unchecked")
public class StockDAO {
	private static Logger logger = Logger.getLogger(StockDAO.class);

	public StockDAO() {
	}

	public int getTotalStock ( int itemid ){ // 用 商品代號 查出所有庫位的 存貨量
		int acc = 0; 
		Query query = XPersistence.getManager()
		.createQuery("select t.volume FROM Stock t where t.itemid = :itemid )"); //JPQL query
		query.setParameter("itemid", itemid);

		Iterator <Stock> beans = (Iterator) query.getResultList();
		
		logger.debug("StockDAO.getTotalStock beans: "+ beans);
		
		while( beans.hasNext() ){
			String jsonQty = beans.next().getQuantity();


			  JSONParser parser = new JSONParser();
			  ContainerFactory containerFactory = new ContainerFactory(){
			    public List creatArrayContainer() {
			      return new LinkedList();
			    }

			    public Map createObjectContainer() {
			      return new LinkedHashMap();
			    }
			                        
			  };
			                
			  try{
			    Map json = (Map)parser.parse(jsonQty, containerFactory);
			    Iterator iter = json.entrySet().iterator();
			    logger.debug("==iterate result==");
			    while(iter.hasNext()){
			      Map.Entry entry = (Map.Entry)iter.next();
			      logger.debug(entry.getKey() + "=>" + entry.getValue());
			    }
			                        
			    logger.debug("==toJSONString()==");
			    logger.debug(JSONValue.toJSONString(json));
			  }
			  catch(Exception pe){
				  logger.error(pe);
			  }			
			
//			acc += ;
		}
		
		return acc; 
	}

}
