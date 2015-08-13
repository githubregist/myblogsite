/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.entity;

import java.io.Serializable;

/**
 *
 * @author user
 */
public class NewsEntity implements Serializable {

    private String newsID;
    private String name;
    private String content;
    private String tid;
    private String type;
    private String date;
    private int browser_num;
    private int comment_num;
    private String cid;
    private String username;

    public NewsEntity() {
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setNewsID(String newsID) {
        this.newsID = newsID;
    }

    public String getNewsID() {
        return this.newsID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return this.date;
    }

    public void setBrowser_num(int browser_num) {
        this.browser_num = browser_num;
    }

    public int getBrowser_num() {
        return this.browser_num;
    }

    public void setComment_num(int comment_num) {
        this.comment_num = comment_num;
    }

    public int getComment_num() {
        return this.comment_num;
    }
}
