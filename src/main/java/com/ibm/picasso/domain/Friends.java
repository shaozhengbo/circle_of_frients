package com.ibm.picasso.domain;

import java.util.Date;

public class Friends {
    private Long id;

    private User uid1;

    private User uid2;

    private Integer state;

    private Date createtime;

    private Date updatetime;
    
    public Friends() {
		super();
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUid1() {
        return uid1;
    }

    public void setUid1(User uid1) {
        this.uid1 = uid1;
    }

    public User getUid2() {
        return uid2;
    }

    public void setUid2(User uid2) {
        this.uid2 = uid2;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}