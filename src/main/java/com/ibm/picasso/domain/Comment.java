package com.ibm.picasso.domain;

import java.util.Date;

public class Comment {
    private Long id;
    private User uid;
    private Message mid;
    private String comment;
    private Date createtime;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getUid() {
		return uid;
	}
	public void setUid(User uid) {
		this.uid = uid;
	}
	public Message getMid() {
		return mid;
	}
	public void setMid(Message mid) {
		this.mid = mid;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Comment() {
		super();
	}

    
}
