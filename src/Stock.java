import java.util.ArrayList;
import java.util.List;

/* Stock.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		2009/7/10 ¤W¤È 9:21:14 , Created by robbiecheng
}}IS_NOTE

Copyright (C) 2009 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under GPL Version 2.0 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
 */

/**
 * @author robbiecheng
 *
 */
public class Stock {
	private int _id;
	private String _name;
	private List _priceItems = new ArrayList();
	
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		_id = id;
	}
	/**
	 * @return the priceItems
	 */
	public List getPriceItems() {
		return _priceItems;
	}

	/**
	 * @param priceItems the priceItems to set
	 */
	public void setPriceItems(List priceItems) {
		_priceItems = priceItems;
	}

	/**
	 * @param string
	 */
	public Stock(int id, String name) {
		_id = id;
		_name = name;
	}

	/**
	 * 
	 */
	public Stock() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return _name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		_name = name;
	}

	/**
	 * @return
	 */
	public int getId() {
		return _id;
	}

}
