package org.zkoss.jspdemo;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.*;

public class MyComposer2 extends GenericForwardComposer {
	Button btn;

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		System.out.println("doAfterCompose = " + btn);
	}

	@Override
	public void doFinally() throws Exception {
		super.doFinally();
		System.out.println("doFinally = " + btn);
	}
}
