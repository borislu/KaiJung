package com.kaijung.actions;

import org.openxava.actions.*;

/**
 * 
 * @author Javier Paniza
 */

public class FilterHide extends TabBaseAction {
	
	public void execute() throws Exception {
		getTab().setRowsHidden(true);
		getTab().goPage(1); 
	}

}
