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
public class NewsTypeEntity implements Serializable{

    public NewsTypeEntity(){}

    String tid;
    String uid;
    String type;
    int news_num;

    public int getNews_num() {
        return news_num;
    }

    public void setNews_num(int news_num) {
        this.news_num = news_num;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }


}
