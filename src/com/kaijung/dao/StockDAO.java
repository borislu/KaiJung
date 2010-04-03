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
import org.zkoss.zk.ui.*;

import com.kaijung.jpa.*;

import common.*;

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
			acc += beans.next().getVolume();
		}
		
		return acc; 
	}

}
