/*Codeing By Jason*/
package com.kaijung.dao;
import com.kaijung.zk.controller.*;
import common.*;
import java.util.UUID;


public class OrderStoreNewD {
		public void insert(int quantity, String modifyid, String isCustOrder, String memo){
		System.out.println("Jason OrderStore start!");
		OrderStoreDNewDAO osdnDAO = new OrderStoreDNewDAO();
		//在這裡生成OrderStoreD的UUID
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString();
		String oid = str.substring(0,8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
		osdnDAO.insert(oid, quantity, modifyid, isCustOrder, memo);
	}
	

}
