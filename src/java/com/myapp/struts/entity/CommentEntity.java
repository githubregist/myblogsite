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
public class CommentEntity implements Serializable {

    private String commentID;
    private String comment;
    private String date;
    private String cid;
    private String username;
    private String newsID;

    public CommentEntity() { }

    public void setCommentID(String commentID) {
        this.commentID=commentID;
    }
    public String getCommentID() {
        return this.commentID;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }  
    public String getNewsID() {
        return newsID;
    }
    public void setNewsID(String newsID) {
        this.newsID = newsID;
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
}
