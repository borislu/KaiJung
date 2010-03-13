package ctrl;

import java.util.*;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelArray;
import org.zkoss.zul.Listbox;

public class Composer2 extends GenericForwardComposer {	
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);			
	}
	
	public void onClick$firstbtn(){
		Clients.scrollIntoView(Composer.getListbox().getFirstChild().getNextSibling());
	}
	
	public void onClick$lastbtn(){
		Clients.scrollIntoView(Composer.getListbox().getLastChild());
	}
	
}
