/*Codeing By Jason*/
package com.kaijung.dao;
import com.kaijung.jpa.*;
import common.*;


import java.util.*;

public class WarehouseDwr {
	private static Logger logger = Logger.getLogger(WarehouseDwr.class);
  
	 public String getName( int oid ){
			WarehouseDAO dao = new WarehouseDAO();
			String name = dao.getName( oid);
			return name;
	 }
}
