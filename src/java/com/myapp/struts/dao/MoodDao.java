/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.dao;

import com.myapp.struts.base.DBConnection;
import com.myapp.struts.entity.MoodEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class MoodDao {

    DBConnection conn = new DBConnection();

    public MoodDao() {
    }

    public int moodsCount(String uid) {
        int num = 0;
        String sql = "select count(*) from mood where uid='" + uid + "'";
        ResultSet rs = conn.executeQuery(sql);
        try {
            while (rs.next()) {
                num = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MoodDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.close();
        }
        return num;
    }

    public int addMood(MoodEntity mood) {
        int num = 0;
        String uid = mood.getUid();
        String uname = mood.getUname();
        String content = mood.getContent();
        String date = mood.getDate();
        String sql = "insert into mood (uid,uname,date,content) values('" + uid + "','" + uname + "','" + date + "','" + content + "')";
        num = conn.executeUpdate(sql);
        conn.close();
        return num;
    }

    public MoodEntity queryMoodByID(String mid) {
        MoodEntity mood = new MoodEntity();
        String sql = "select * from mood where mid='" + mid + "'";
        ResultSet rs = null;
        rs = conn.executeQuery(sql);
        try {
            while (rs.next()) {
                mood.setMid(rs.getString("mid"));
                mood.setUid(rs.getString("uid"));
                mood.setUname(rs.getString("uname"));
                mood.setDate(rs.getString("date"));
                mood.setContent(rs.getString("content"));
                mood.setRe_num(rs.getInt("re_num"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MoodDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.close();
        }
        return mood;
    }

    public MoodEntity queryCurrentMood(String uid) {
        MoodEntity mood = new MoodEntity();
        String sql = "select * from mood where uid='" + uid + "' order by mid desc LIMIT 1";
        ResultSet rs = null;
        rs = conn.executeQuery(sql);
        try {
            while (rs.next()) {
                mood.setMid(rs.getString("mid"));
                mood.setUid(rs.getString("uid"));
                mood.setUname(rs.getString("uname"));
                mood.setDate(rs.getString("date"));
                mood.setContent(rs.getString("content"));
                mood.setRe_num(rs.getInt("re_num"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MoodDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.close();
        }
        return mood;
    }

    public ArrayList queryMoods(String uid) {
        ArrayList moodsList = new ArrayList();
        String sql = "select * from mood m,user u where m.uid='" + uid + "' and m.uid=u.cid order by mid desc";
        ResultSet rs = null;
        rs = conn.executeQuery(sql);
        try {
            while (rs.next()) {
                MoodEntity mood = new MoodEntity();
                mood.setMid(rs.getString("mid"));
                mood.setUid(rs.getString("uid"));
                mood.setUname(rs.getString("uname"));
                mood.setDate(rs.getString("date"));
                mood.setContent(rs.getString("content"));
                mood.setRe_num(rs.getInt("re_num"));
                moodsList.add(mood);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MoodDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.close();
        }
        return moodsList;
    }

    public ArrayList queryFriendMoods(String uid) {
        ArrayList friendmoods = new ArrayList();
        String sql = "select * from login.mood where uid in (select cid2 from login.friends where cid1='" + uid + "') order by mid desc";
        ResultSet rs = null;
        rs = conn.executeQuery(sql);
        try {
            while (rs.next()) {
                MoodEntity mood = new MoodEntity();
                mood.setMid(rs.getString("mid"));
                mood.setUid(rs.getString("uid"));
                mood.setUname(rs.getString("uname"));
                mood.setDate(rs.getString("date"));
                mood.setContent(rs.getString("content"));
                mood.setRe_num(rs.getInt("re_num"));
                friendmoods.add(mood);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MoodDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.close();
        }
        return friendmoods;
    }

    public int deleteMood(String mid) {
        int num = 0;
        String sql = "delete from mood where mid='" + mid + "'";
        num = conn.executeUpdate(sql);
        return num;
    }
}
