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
public class FriendsDao {

    DBConnection conn = new DBConnection();
    ResultSet rs = null;

    public FriendsDao() {
    }

    public int friendsCount(String cid) {
        int num = 0;
        String sql = "select count(*) from friends where cid1='" + cid + "' and state='TRUE'";
        ResultSet rs = conn.executeQuery(sql);
        try {
            while (rs.next()) {
                num = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FriendsDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.close();
        }
        return num;
    }

        public int pfriendsCount(String cid) {
        int num = 0;
        String sql = "select count(*) from friends where cid2='" + cid + "' and state='FALSE'";
        ResultSet rs = conn.executeQuery(sql);
        try {
            while (rs.next()) {
                num = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FriendsDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.close();
        }
        return num;
    }

        public int wfriendsCount(String cid) {
        int num = 0;
        String sql = "select count(*) from friends where cid1='" + cid + "' and state='FALSE'";
        ResultSet rs = conn.executeQuery(sql);
        try {
            while (rs.next()) {
                num = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FriendsDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.close();
        }
        return num;
    }

    public ArrayList listFriends(String cid) {
        ArrayList friends = new ArrayList();
        String sql = "select * from friends f,user u where f.cid1='" + cid + "'and state='TRUE' and f.cid2=u.cid";
        rs = conn.executeQuery(sql);
        try {
            while (rs.next()) {
                UserEntity friend = new UserEntity();
                friend.setCid(rs.getString("cid").toString());
                friend.setUsername(rs.getString("username"));
                friend.setEmail(rs.getString("email"));
                friend.setSex(rs.getString("sex"));
                friend.setBirthday(rs.getString("birthday"));
                friend.setHometown(rs.getString("hometown"));
                friend.setLocation(rs.getString("location"));
                friend.setUniversity(rs.getString("university"));
                friend.setProfession(rs.getString("profession"));
                friend.setCompany(rs.getString("company"));
                friend.setOccupation(rs.getString("occupation"));
                friend.setBlogname(rs.getString("blogname"));
                friend.setBlogshow(rs.getString("blogshow"));
                friend.setSignature(rs.getString("signature"));
                friend.setVisitnum(rs.getInt("visitnum"));
                friend.setLevel(rs.getInt("level"));
                friends.add(friend);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FriendsDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.close();
        }
        return friends;
    }

    public ArrayList queryNotFriends(String cid) {
        ArrayList usersList = new ArrayList();
        String sql = "select * from user where cid <>'" + cid + "' and cid not in (select cid2 from friends where cid1='" + cid + "')";
        ResultSet rs = null;
        try {
            rs = conn.executeQuery(sql);
            while (rs.next()) {
                UserEntity user = new UserEntity();
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
                usersList.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FriendsDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.close();
        }
        return usersList;
    }

    public ArrayList listPreFriends(String cid) {
        ArrayList friends = new ArrayList();
        String sql = "select * from friends f,user u where f.cid2='" + cid + "'and state='FALSE' and f.cid1=u.cid";
        rs = conn.executeQuery(sql);
        try {
            while (rs.next()) {
                UserEntity friend = new UserEntity();
                friend.setCid(rs.getString("cid").toString());
                friend.setUsername(rs.getString("username"));
                friend.setEmail(rs.getString("email"));
                friend.setSex(rs.getString("sex"));
                friend.setBirthday(rs.getString("birthday"));
                friend.setHometown(rs.getString("hometown"));
                friend.setLocation(rs.getString("location"));
                friend.setUniversity(rs.getString("university"));
                friend.setProfession(rs.getString("profession"));
                friend.setCompany(rs.getString("company"));
                friend.setOccupation(rs.getString("occupation"));
                friend.setBlogname(rs.getString("blogname"));
                friend.setBlogshow(rs.getString("blogshow"));
                friend.setSignature(rs.getString("signature"));
                friend.setVisitnum(rs.getInt("visitnum"));
                friend.setLevel(rs.getInt("level"));
                friends.add(friend);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FriendsDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.close();
        }
        return friends;
    }

    public ArrayList listWaitFriends(String cid) {
        ArrayList friends = new ArrayList();
        String sql = "select * from friends f,user u where f.cid1='" + cid + "'and state='FALSE' and f.cid2=u.cid";
        rs = conn.executeQuery(sql);
        try {
            while (rs.next()) {
                UserEntity friend = new UserEntity();
                friend.setCid(rs.getString("cid").toString());
                friend.setUsername(rs.getString("username"));
                friend.setEmail(rs.getString("email"));
                friend.setSex(rs.getString("sex"));
                friend.setBirthday(rs.getString("birthday"));
                friend.setHometown(rs.getString("hometown"));
                friend.setLocation(rs.getString("location"));
                friend.setUniversity(rs.getString("university"));
                friend.setProfession(rs.getString("profession"));
                friend.setCompany(rs.getString("company"));
                friend.setOccupation(rs.getString("occupation"));
                friend.setBlogname(rs.getString("blogname"));
                friend.setBlogshow(rs.getString("blogshow"));
                friend.setSignature(rs.getString("signature"));
                friend.setVisitnum(rs.getInt("visitnum"));
                friend.setLevel(rs.getInt("level"));
                friends.add(friend);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FriendsDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.close();
        }
        return friends;
    }

    public int requestFriend(UserEntity visitor, UserEntity user) {
        int num = 0;
        String fid = visitor.getCid();
        String uid = user.getCid();
        String fname = visitor.getUsername();
        String uname = user.getUsername();
        String sql = "insert into friends (cid1,name1,cid2,name2,state) values('" + uid + "','" + uname + "','" + fid + "','" + fname + "','FALSE')";
        num = conn.executeUpdate(sql);
        conn.close();
        return num;
    }

    public int confirmFriend(UserEntity visitor, UserEntity user) {
        int num = 0;
        String fid = visitor.getCid();
        String uid = user.getCid();
        String fname = visitor.getUsername();
        String uname = user.getUsername();
        String sql = "insert into friends (cid1,name1,cid2,name2,state) values('" + uid + "','" + uname + "','" + fid + "','" + fname + "','TRUE')";
        num = conn.executeUpdate(sql);
        sql = "update friends set state='TRUE' where cid1='" + fid + "'and cid2='" + uid + "'";
        num += conn.executeUpdate(sql);
        conn.close();
        return num;
    }

    public int cancelFriend(String fid, String uid) {
        int num = 0;
        String sql = "delete from friends where cid1='" + uid + "' and cid2='" + fid + "'and state='FALSE'";
        num = conn.executeUpdate(sql);
        conn.close();
        return num;
    }

    public int rejectFriend(String fid, String uid) {
        int num = 0;
        String sql = "delete from friends where cid2='" + uid + "' and cid1='" + fid + "'and state='FALSE'";
        num = conn.executeUpdate(sql);
        conn.close();
        return num;
    }

    public int deleteFriend(String fid, String uid) {
        int num = 0;
        String sql = "delete from friends where cid2='" + uid + "' and cid1='" + fid + "'and state='TRUE'";
        num = conn.executeUpdate(sql);
        sql = "delete from friends where cid1='" + uid + "' and cid2='" + fid + "'and state='TRUE'";
        num += conn.executeUpdate(sql);
        conn.close();
        return num;
    }
}
