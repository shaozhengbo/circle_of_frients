package com.ibm.picasso.pojo;

public class MessagePojo {

	private String msg;
	private Object object;
	
	public MessagePojo() {
		super();
	}
	
	
	public MessagePojo(Object object, String msg) {
		super();
		this.object = object;
		this.msg = msg;
	}

	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	@Override
	public String toString() {
		return "Message [object=" + object + ", msg=" + msg + "]";
	}
}
