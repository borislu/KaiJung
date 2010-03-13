package org.zkforge.todo.event;

/**
 * Event.
 * 
 * @author robbiecheng
 */

import java.util.Date;

public class TodoEvent {
	private String id;
	private String name;
	private int priority;
	private Date date;
	
	public TodoEvent(){}
	public TodoEvent(String id,String name,int priority,Date date){
		this.id = id;
		this.name = name;
		this.priority = priority;
		this.date = date;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
