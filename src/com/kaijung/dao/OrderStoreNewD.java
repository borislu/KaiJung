/*Codeing By Jason*/
package com.kaijung.dao;
import com.kaijung.jpa.*;
import com.kaijung.zk.controller.*;
import common.*;

import java.lang.reflect.*;
import java.sql.*;
import java.util.*;


public class OrderStoreNewD {
		public void insert(int quantity, String modifyid, String isCustOrder, String memo){
		OrderStoreDNewDAO osdnDAO = new OrderStoreDNewDAO();
		//在這裡生成OrderStoreD的UUID
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString();
		String oid = str.substring(0,8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
		osdnDAO.insert(oid, quantity, modifyid, isCustOrder, memo);
	}
		public Item autoExport(String barcode) throws SQLException{
			OrderStoreDNewDAO osdnDAO = new OrderStoreDNewDAO();
			Item item = osdnDAO.getElementById(barcode);
			return item;
		}
		 public String getColorName(String colorId )throws SQLException{
				OrderStoreDNewDAO osdn1DAO = new OrderStoreDNewDAO();
				String colorName = osdn1DAO.getColorNameById(colorId);
				return colorName;
		 }
		 public List findSuggestOid(int q) throws SQLException{
			 OrderStoreDNewDAO osdnDAO = new OrderStoreDNewDAO();
			 List orderSuggest = osdnDAO.findSuggestOid();
			 return orderSuggest;
		 }
	

}
