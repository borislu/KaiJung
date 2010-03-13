/**
 * 
 */
package org.zkoss.jspdemo.ui;

import java.io.IOException;
import java.io.Writer;

import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Script;

/**
 * @author ian
 *
 */
public class GmapImportScript extends Script implements AfterCompose {
	
	private String gmapKey = null;
	
	
	@SuppressWarnings("static-access")
	public void afterCompose() 
	{
		if(gmapKey==null)gmapKey=lookupGmapKey();
		if (gmapKey!=null)
		{			
			
			if(org.zkoss.zk.Version.UID.valueOf(0) == "3")
				this.setSrc("http://maps.google.com/maps?file=api&v=2&key="+gmapKey);//for zk3
			this.setContent("zk.googleAPIkey='"+gmapKey+"'");//for zk 5
			this.setType("text/javascript");
		}
	}
	
	
	/**
	 * 
	 * @return
	 */
	private static  String lookupGmapKey()
	{
		//We have to decide the key of Google Maps since we have a demo using it.
		//This key is used by zkdemo/userguide/index.zul to generate a proper script
		final Execution exec = Executions.getCurrent();
		final String sn = exec.getServerName();
		final int sp = exec.getServerPort();
		Script a;
		//To add more keys: http://www.google.com/apis/maps/signup.html
		String gkey = null;
		
		if (sn.indexOf("www.potix.com") >= 0) { // http://www.potix.com/
			gkey = "ABQIAAAAmGxmYR57XDAbAumS9tV5fxRYCo_4ZGj_-54kHesWSk0nMkbs4xTpq0zo9O75_ZqvsSLGY2YkC7jjNg";
		} else if (sn.indexOf("www.zkoss.org") >= 0) { // http://www.zkoss.org/
			gkey = "ABQIAAAAmGxmYR57XDAbAumS9tV5fxQXyylOlR69a1vFTcUcpV6DXdesOBSMEHfkewcSzwEwBT7UzVx8ep8vjA";
		} else if (sn.indexOf("zkoss.org") >= 0) { // http://www.zkoss.org/
			gkey = "ABQIAAAAakIm31AXAvNGFHV8i1Tx8RSF4KLGEmvBsS1z1zAsQZvbQceuNRQBsm65qGaXpTWjZsc2bl-hm2Vyfw";
		} else if (sn.indexOf("localhost") >= 0) { //localhost
			if (sp == 80) // http://localhost/
				gkey = "ABQIAAAAmGxmYR57XDAbAumS9tV5fxT2yXp_ZAY8_ufC3CFXhHIE1NvwkxRUITTZ-rzsyEVih16Hn3ApyUpSkA";
			else if (sp == 8080) // http://localhost:8080
				gkey = "ABQIAAAAmGxmYR57XDAbAumS9tV5fxTwM0brOpm-All5BF6PoaKBxRWWERSynObNOWSyMNmLGAMZAO1WkDUubA";
			else if (sp == 7799)
				gkey = "ABQIAAAAmGxmYR57XDAbAumS9tV5fxTT6-Op-9nAQgn7qnDG0QjE8aldaBRU1BQK2ADNWCt1BR2yg4ghOM6YIA";
		}
		return gkey;
	}
	/**
	 * 
	 * @return
	 */
	public String getGmapKey() {
		return gmapKey;
	}
	/**
	 * 
	 * @param gmapKey
	 */
	public void setGmapKey(String gmapKey) {
		this.gmapKey = gmapKey;
	}


	public void redraw(Writer out) throws IOException {
		if(this.gmapKey!=null)
			super.redraw(out);
	}



}//end of class...
