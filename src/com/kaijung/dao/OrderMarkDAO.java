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
import com.kaijung.jpa.*;
import common.*;

/*
 * author Boris.lds@gmail.com 
 */
public class OrderMarkDAO {
	private static Logger logger = Logger.getLogger(OrderMarkDAO.class);

	public OrderMarkDAO() {
	}

	@SuppressWarnings("unchecked")
	public List selectAll(){
		List<OrderMark> markList = XPersistence.getManager()
		.createQuery(
		"from OrderMark)") //JPQL query
		.getResultList();
		return markList;
	}
	
	public OrderMark getMark(int id) {
		for (Iterator<OrderMark> iterator = selectAll().iterator(); iterator.hasNext();) {
			OrderMark st = (OrderMark) iterator.next();
			if (st.getOid() == id)
				return st;		
		}
		return new OrderMark();
	}

	public boolean insert(OrderStoreD beanD){
		XPersistence.getManager().persist( beanD );
		XPersistence.commit(); // 若 ID 重複，會有例外: org.hibernate.PersistentObjectException: detached entity passed to persist: com.kaijung.jpa.OrderStoreD
		return true;
	}

}
