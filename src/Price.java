/* Price.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		2009/7/10 ¤W¤È 10:25:21 , Created by robbiecheng
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
public class Price {
	private String _date;
	private double _open;
	private double _high;
	private double _low;
	private double _close;
	private int _volumn;
	
	/**
	 * @param date
	 * @param open
	 * @param high
	 * @param low
	 * @param close
	 * @param volumn
	 */
	public Price(String date, double open, double high, double low, double close, int volumn) {
		super();
		_date = date;
		_open = open;
		_high = high;
		_low = low;
		_close = close;
		_volumn = volumn;
	}
	/**
	 * 
	 */
	public Price() {
		// TODO Auto-generated constructor stub
	}	
	/**
	 * @return the date
	 */
	public String getDate() {
		return _date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		_date = date;
	}
	/**
	 * @return the open
	 */
	public double getOpen() {
		return _open;
	}
	/**
	 * @param open the open to set
	 */
	public void setOpen(double open) {
		_open = open;
	}
	/**
	 * @return the high
	 */
	public double getHigh() {
		return _high;
	}
	/**
	 * @param high the high to set
	 */
	public void setHigh(double high) {
		_high = high;
	}
	/**
	 * @return the low
	 */
	public double getLow() {
		return _low;
	}
	/**
	 * @param low the low to set
	 */
	public void setLow(double low) {
		_low = low;
	}
	/**
	 * @return the close
	 */
	public double getClose() {
		return _close;
	}
	/**
	 * @param close the close to set
	 */
	public void setClose(double close) {
		_close = close;
	}
	/**
	 * @return the volumn
	 */
	public int getVolumn() {
		return _volumn;
	}
	/**
	 * @param volumn the volumn to set
	 */
	public void setVolumn(int volumn) {
		_volumn = volumn;
	}
	
	
}
