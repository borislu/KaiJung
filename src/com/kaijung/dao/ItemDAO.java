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
public class ItemDAO {
	private static Logger logger = Logger.getLogger(ItemDAO.class);

	public ItemDAO() {
	}

	// 用條碼去找出貨號顏色價錢
	public Item getByBarcode(String barcode) {
		EntityManager em = XPersistence.getManager();
		Query query = null;
		Item result = null;
		try {
			logger.debug("ItemDAO.getByBarcode: barcode: "+ barcode);
			query = em.createQuery("SELECT i FROM Item AS i"
					+ " WHERE i.barcode = '" + barcode + "'");
			result = (Item) query.getSingleResult();
			logger.debug("ItemDAO.getByBarcode: result: "+ result);
		} catch (Exception e) {
			logger.error("ItemDAO.getByBarcode: " + e);
		}
		return result;
	}

}
