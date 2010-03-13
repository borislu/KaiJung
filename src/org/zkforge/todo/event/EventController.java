package org.zkforge.todo.event;

/**
 * Event Controller.
 * 
 * @author robbiecheng
 */

import java.util.List;
import java.util.UUID;

import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Listbox;

public class EventController extends GenericForwardComposer {
	EventDAO evtdao = new EventDAO();
	TodoEvent current = new TodoEvent();
	Listbox box;
	
	public TodoEvent getCurrent() {
		return current;
	}
	public void setCurrent(TodoEvent current) {
		this.current = current;
	}
	public List getAllEvents() {
		return evtdao.findAll();
	}	
	public void onClick$add() {		
		// insert into database		
		TodoEvent newEvt = new TodoEvent(UUID.randomUUID().toString(), current.getName(),
			current.getPriority(), current.getDate());
		evtdao.insert(newEvt);		
	}	
	public void onClick$update() {		
		if (box.getSelectedItem() != null) {
			// update database
			evtdao.update((TodoEvent) box.getSelectedItem().getValue());
		}
	}
	public void onClick$delete() {		
		if (box.getSelectedItem() != null) {
			evtdao.delete((TodoEvent) box.getSelectedItem().getValue());
		}
	}
}
