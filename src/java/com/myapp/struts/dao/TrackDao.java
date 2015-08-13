/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.dao;

import com.myapp.struts.base.DBConnection;
import com.myapp.struts.entity.UserEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class TrackDao {

    DBConnection conn = new DBConnection();

    public TrackDao() {
    }

    public int updateTrackNum(String cid) {
        int num = 0;
        int visitnum = 0;
        ResultSet rs = null;
        String sql = "select visitnum from user where cid=" + cid;
        rs = conn.executeQuery(sql);
        try {
            while (rs.next()) {
                visitnum = rs.getInt("visitnum");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TrackDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        visitnum++;
        sql = "update user set visitnum='" + visitnum + "' where cid=" + cid;
        num = conn.executeUpdate(sql);
        conn.close();
        return num;
    }

    public ArrayList queryTrack(String uid) {
        ArrayList visitorList = new ArrayList();
        String sql = "select * from visitor v,user u where v.uid ='" + uid + "' and u.cid=v.vid";
        ResultSet rs = conn.executeQuery(sql);
        try {
            while (rs.next()) {
                UserEntity visitor = new UserEntity();
                visitor.setCid(rs.getString("vid"));
                visitor.setUsername(rs.getString("vname"));
                visitor.setVisittime(rs.getString("vdate"));
                visitor.setEmail(rs.getString("email"));
                visitor.setSex(rs.getString("sex"));
                visitor.setBirthday(rs.getString("birthday"));
                visitor.setHometown(rs.getString("hometown"));
                visitor.setLocation(rs.getString("location"));
                visitor.setUniversity(rs.getString("university"));
                visitor.setProfession(rs.getString("profession"));
                visitor.setCompany(rs.getString("company"));
                visitor.setOccupation(rs.getString("occupation"));
                visitor.setBlogname(rs.getString("blogname"));
                visitor.setBlogshow(rs.getString("blogshow"));
                visitor.setSignature(rs.getString("signature"));
                visitor.setVisitnum(rs.getInt("visitnum"));
                visitor.setLevel(rs.getInt("level"));
                visitorList.add(visitor);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.close();
        }
        return visitorList;
    }

        public ArrayList queryMyTrack(String vid) {
        ArrayList visitorList = new ArrayList();
        String sql = "select * from visitor v,user u where v.vid ='" + vid + "' and u.cid=v.uid";
        ResultSet rs = conn.executeQuery(sql);
        try {
            while (rs.next()) {
                UserEntity visitor = new UserEntity();
                visitor.setCid(rs.getString("cid"));
                visitor.setUsername(rs.getString("username"));
                visitor.setVisittime(rs.getString("vdate"));
                visitor.setEmail(rs.getString("email"));
                visitor.setSex(rs.getString("sex"));
                visitor.setBirthday(rs.getString("birthday"));
                visitor.setHometown(rs.getString("hometown"));
                visitor.setLocation(rs.getString("location"));
                visitor.setUniversity(rs.getString("university"));
                visitor.setProfession(rs.getString("profession"));
                visitor.setCompany(rs.getString("company"));
                visitor.setOccupation(rs.getString("occupation"));
                visitor.setBlogname(rs.getString("blogname"));
                visitor.setBlogshow(rs.getString("blogshow"));
                visitor.setSignature(rs.getString("signature"));
                visitor.setVisitnum(rs.getInt("visitnum"));
                visitor.setLevel(rs.getInt("level"));
                visitorList.add(visitor);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.close();
        }
        return visitorList;
    }

    public int existsTrack(String uid, String vid) {
        int num = 0;
        String sql = "select count(*) from visitor where uid ='" + uid + "' and vid ='" + vid + "'";
        ResultSet rs = conn.executeQuery(sql);
        try {
            while (rs.next()) {
                num = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TrackDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.close();
        }
        return num;
    }

    public int insertTrack(String uid, UserEntity visitor) {
        int num = 0;
        String vid = visitor.getCid();
        String vname = visitor.getUsername();
        Date current = new Date();
        SimpleDateFormat matter = new SimpleDateFormat("MM月dd日 hh:mm");
        String vdate = matter.format(current);
        String sql = "insert into visitor (uid,vid,vname,vdate) values ('" + uid + "','" + vid + "','" + vname + "','" + vdate + "')";
        num = conn.executeUpdate(sql);
        conn.close();
        return num;
    }

    public int updateTrack(String uid, UserEntity visitor) {
        int num = 0;
        String vid = visitor.getCid();
        String vname = visitor.getUsername();
        Date current = new Date();
        System.out.println(current.toString());
        SimpleDateFormat matter = new SimpleDateFormat("MM月dd日 hh:mm");
        String vdate = matter.format(current);
        String sql = "update visitor set uid='" + uid + "',vid='" + vid + "',vname='" + vname + "',vdate='" + vdate + "' where uid='" + uid + "' and vid='" + vid + "'";
        num = conn.executeUpdate(sql);
        conn.close();
        return num;
    }
}
