package ctrl;

import java.util.*;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.ListModelArray;
import org.zkoss.zul.Listbox;

public class Composer extends GenericForwardComposer {
	
	private static Listbox listbox1;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);		
		
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 30; i++) {
			list.add("item" + i);
		}		
		listbox1.setModel(new ListModelArray(list));
	}
	
	public static Listbox getListbox(){
		return listbox1;
	}
}
