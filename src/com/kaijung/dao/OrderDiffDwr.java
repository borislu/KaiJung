
package com.kaijung.dao;
import com.kaijung.jpa.*;
import com.kaijung.zk.controller.*;
import common.*;

import java.lang.reflect.*;
import java.sql.*;
import java.util.*;

import org.openxava.jpa.*;


public class OrderDiffDwr {
		private static Logger logger = Logger.getLogger(OrderDiffDwr.class);
		
		public Set <OrderDiffBean> getOrderDiff( String wareid ){//查出本櫃的所有訂單明細
			 Collection <OrderDiffBean> cl = new OrderDiffDAO().getOrderDiff( Integer.parseInt( wareid ) );
			 return new HashSet <OrderDiffBean>( cl );
		}

}
