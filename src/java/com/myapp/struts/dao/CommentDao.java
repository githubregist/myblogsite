/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.dao;

import com.myapp.struts.base.DBConnection;
import com.myapp.struts.entity.CommentEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @date: 2010/4/10
 * @author: guohengj
 * @version: 1.0
 */
public class CommentDao {

    ArrayList newsList = new ArrayList();
    DBConnection conn = new DBConnection();
    ResultSet rs = null;

    public CommentDao() {
    }

    public CommentEntity queryComment(String newsID) {
        CommentEntity comment = new CommentEntity();
        String sql = "select * from comment where newsID=" + newsID + "order by commentID ASC";
        ResultSet rs = conn.executeQuery(sql);
        try {
            if (rs.next()) {
                comment.setCommentID(String.valueOf(rs.getString("commentID")));
                comment.setComment(String.valueOf(rs.getString("comment")));
                comment.setDate(String.valueOf(rs.getString("date")));
                comment.setUsername(String.valueOf(rs.getString("username")));
                comment.setNewsID(String.valueOf(rs.getString("newsID")));
                comment.setCid(String.valueOf(rs.getString("cid")));
            }
        }  catch (SQLException ex) {
            Logger.getLogger(CommentDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.close();
        }
        return comment;
    }

    public ArrayList queryAllComments(String newsID) {
        ArrayList commentlist = new ArrayList();
        String sql = "select * from comment where newsID=" + newsID;
        ResultSet rs = conn.executeQuery(sql);
        try {
            while (rs.next()) {
                CommentEntity comment = new CommentEntity();
                comment.setCommentID(String.valueOf(rs.getString("commentID")));
                comment.setComment(String.valueOf(rs.getString("comment")));
                comment.setDate(String.valueOf(rs.getString("date")));
                comment.setUsername(String.valueOf(rs.getString("username")));
                comment.setNewsID(String.valueOf(rs.getString("newsID")));
                comment.setCid(String.valueOf(rs.getString("cid")));
                commentlist.add(comment);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommentDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.close();
        }
        return commentlist;
    }

    public int commentCount(String newsID) {
        int num = 0;
        String sql = "select count(*) from comment where newsID=" + newsID;
        ResultSet rs = conn.executeQuery(sql);
        try {
            if (rs.next()) {
                num = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommentDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.close();
        }
        return num;
    }

    public int insertComment(CommentEntity comment) {
        int num = 0;
        String sql = "insert into comment(comment, date,username,newsID,cid) " +
                    "values('" + comment.getComment() + "','" + comment.getDate() + "','" + comment.getUsername() + "','" + comment.getNewsID() + "','" + comment.getCid() +  "')";
        num = conn.executeUpdate(sql);
        conn.close();
        return num;
    }

    public int deleteComment(String commentID) {
        int num = 0;
        String sql = "delete from comment where commentID=" + commentID;
        num = conn.executeUpdate(sql);
        conn.close();
        return num;
    }
}
