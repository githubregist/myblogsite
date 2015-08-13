/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.dao;

import com.myapp.struts.base.DBConnection;
import com.myapp.struts.entity.UserEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class UserDao {

    DBConnection conn = new DBConnection();
    //ResultSet rs = null;

    public UserDao() {
    }

    public boolean checkLogin(String username, String password) {
        boolean logon = false;
        ResultSet rs = null;
        String sql = "select * from user where username ='" + username + "'";
        try {
            rs = conn.executeQuery(sql);
            while (rs.next()) {
                if (rs.getString("password").equals(password)) {
                    logon = true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.close();
        }
        return logon;
    }

    public UserEntity queryUser(String username) {
        UserEntity user = new UserEntity();
        String sql = "select * from user where username='" + username + "'";
        ResultSet rs = null;
        try {
            rs = conn.executeQuery(sql);
            while (rs.next()) {
                user.setCid(rs.getString("cid").toString());
                user.setUsername(rs.getString("username"));
                //user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setSex(rs.getString("sex"));
                user.setBirthday(rs.getString("birthday"));
                user.setHometown(rs.getString("hometown"));
                user.setLocation(rs.getString("location"));
                user.setUniversity(rs.getString("university"));
                user.setProfession(rs.getString("profession"));
                user.setCompany(rs.getString("company"));
                user.setOccupation(rs.getString("occupation"));
                user.setBlogname(rs.getString("blogname"));
                user.setBlogshow(rs.getString("blogshow"));
                user.setSignature(rs.getString("signature"));
                user.setVisitnum(rs.getInt("visitnum"));
                user.setLevel(rs.getInt("level"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.close();
        }
        return user;
    }

    public UserEntity queryUserById(String cid) {
        UserEntity user = new UserEntity();
        String sql = "select * from user where cid='" + cid + "'";
        ResultSet rs = null;
        try {
            rs = conn.executeQuery(sql);
            while (rs.next()) {
                user.setCid(rs.getString("cid").toString());
                user.setUsername(rs.getString("username"));
                //user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setSex(rs.getString("sex"));
                user.setBirthday(rs.getString("birthday"));
                user.setHometown(rs.getString("hometown"));
                user.setLocation(rs.getString("location"));
                user.setUniversity(rs.getString("university"));
                user.setProfession(rs.getString("profession"));
                user.setCompany(rs.getString("company"));
                user.setOccupation(rs.getString("occupation"));
                user.setBlogname(rs.getString("blogname"));
                user.setBlogshow(rs.getString("blogshow"));
                user.setSignature(rs.getString("signature"));
                user.setVisitnum(rs.getInt("visitnum"));
                user.setLevel(rs.getInt("level"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.close();
        }
        return user;
    }

    public int addUser(UserEntity user) {
        int num = 0;
        String username = user.getUsername();
        String password = user.getPassword();
        String email = user.getEmail();
        String sql = "insert into user (username,password,email) values('" + username + "','" + password + "','" + email + "')";
        num = conn.executeUpdate(sql);
        conn.close();
        return num;
    }

    public int motifyUserBasic(UserEntity user) {
        int num = 0;
        String cid = user.getCid();
        String username = user.getUsername();
        String sex = user.getSex();
        String birthday = user.getBirthday();
        String email = user.getEmail();
        String hometown = user.getHometown();
        String location = user.getLocation();
        String university = user.getUniversity();
        String profession = user.getProfession();
        String company = user.getCompany();
        String occupation = user.getOccupation();
        String sql = "update user set username='" + username + "',sex='" + sex + "',birthday='"
                + birthday + "',email='" + email + "',hometown='" + hometown + "',location='" + location + "',university='"
                + university + "',profession='" + profession + "',company='" + company + "',occupation='" + occupation + "' where cid='" + cid + "'";
        num = conn.executeUpdate(sql);
        conn.close();
        return num;
    }

    public  int motifyUserBlog(UserEntity user) {
        int num = 0;
        String cid = user.getCid();
        String blogname = user.getBlogname();
        String blogshow = user.getBlogshow();
        String signature = user.getSignature();
        String sql = "update user set blogname='" + blogname + "',blogshow='" + blogshow + "',signature='" + signature + "' where cid='" + cid + "'";
        num = conn.executeUpdate(sql);
        conn.close();
        return num;
    }
}
