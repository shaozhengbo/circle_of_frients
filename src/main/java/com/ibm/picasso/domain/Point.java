package com.ibm.picasso.domain;

import java.util.Date;

public class Point {
    private Long id;

    private Message mid;

    private User uid;

    private Date createtime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Message getMid() {
        return mid;
    }

    public void setMid(Message mid) {
        this.mid = mid;
    }

    public User getUid() {
        return uid;
    }

    public void setUid(User uid) {
        this.uid = uid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}