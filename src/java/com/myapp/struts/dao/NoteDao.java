/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.dao;

import com.myapp.struts.base.DBConnection;
import com.myapp.struts.entity.NoteEntity;
import com.myapp.struts.entity.ReNoteEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class NoteDao {

    DBConnection conn = new DBConnection();

    public NoteDao() {
    }

    public int notesCount(String uid) {
        int num = 0;
        String sql = "select count(*) from note where uid='" + uid + "'";
        ResultSet rs = conn.executeQuery(sql);
        try {
            while (rs.next()) {
                num = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NoteDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.close();
        }
        return num;
    }

    public ArrayList queryNotes(String uid, int current_page) {
        int PAGE_SIZE = 5;
        int skipBegin = (current_page - 1) * PAGE_SIZE;
        int skipEnd = current_page * PAGE_SIZE;
        ArrayList notesList = new ArrayList();
        String sql = "select * from note n,user u where n.uid='" + uid + "' and n.vid=u.cid order by nid desc";
        ResultSet rs = null;
        rs = conn.executeQuery(sql);
        try {
            if (!rs.absolute(skipBegin + 1)) {
                /**
                 * 当skipBegin为0时，定位0时，rs.next()将取不到第一行
                 */
                rs.absolute(0);
            } else {
                NoteEntity note = new NoteEntity();
                note.setNid(rs.getString("nid"));
                note.setUid(rs.getString("uid"));
                note.setVid(rs.getString("vid"));
                note.setVname(rs.getString("vname"));
                note.setDate(rs.getString("date"));
                note.setContent(rs.getString("content"));
                //留言者信息
                note.setSex(rs.getString("sex"));
                note.setBirthday(rs.getString("birthday"));
                note.setLocation(rs.getString("location"));
                note.setUniversity(rs.getString("university"));
                note.setBlogname(rs.getString("blogname"));
                note.setSignature(rs.getString("signature"));
                note.setLevel(rs.getInt("level"));
                note.setVisitnum(rs.getInt("visitnum"));
                notesList.add(note);
                skipBegin++;
            }
            while (rs.next()) {
                if (skipBegin < skipEnd) {
                    NoteEntity note = new NoteEntity();
                    note.setNid(rs.getString("nid"));
                    note.setUid(rs.getString("uid"));
                    note.setVid(rs.getString("vid"));
                    note.setVname(rs.getString("vname"));
                    note.setDate(rs.getString("date"));
                    note.setContent(rs.getString("content"));
                    //留言者信息
                    note.setSex(rs.getString("sex"));
                    note.setBirthday(rs.getString("birthday"));
                    note.setLocation(rs.getString("location"));
                    note.setUniversity(rs.getString("university"));
                    note.setBlogname(rs.getString("blogname"));
                    note.setSignature(rs.getString("signature"));
                    note.setLevel(rs.getInt("level"));
                    note.setVisitnum(rs.getInt("visitnum"));
                    notesList.add(note);
                    if (skipBegin == skipEnd - 1) {
                        break;
                    }
                }
                skipBegin++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(NoteDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.close();
        }
        return notesList;
    }

    public NoteEntity queryNoteById(String nid) {
        NoteEntity note = new NoteEntity();
        String sql = "select * from note n where nid='" + nid + "'";
        ResultSet rs = null;
        rs = conn.executeQuery(sql);
        try {
            while (rs.next()) {
                note.setNid(rs.getString("nid"));
                note.setUid(rs.getString("uid"));
                note.setVid(rs.getString("vid"));
                note.setVname(rs.getString("vname"));
                note.setDate(rs.getString("date"));
                note.setContent(rs.getString("content"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NoteDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.close();
        }
        return note;
    }

    public ArrayList queryRecentNotes(String uid) {
        ArrayList list = new ArrayList();
        String sql = "select * from note where uid='" + uid + "' order by nid desc LIMIT 3";
        ResultSet rs = conn.executeQuery(sql);
        try {
            while (rs.next()) {
                NoteEntity note = new NoteEntity();
                note.setNid(rs.getString("nid"));
                note.setUid(rs.getString("uid"));
                note.setVid(rs.getString("vid"));
                note.setVname(rs.getString("vname"));
                note.setDate(rs.getString("date"));
                note.setContent(rs.getString("content"));
                list.add(note);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NoteDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.close();
        }
        return list;
    }

    public int addNote(NoteEntity note) {
        int num = 0;
        String uid = note.getUid();
        String vid = note.getVid();
        String vname = note.getVname();
        String content = note.getContent();
        String date = note.getDate();
        String sql = "insert into note (uid,vid,vname,date,content) values ('" + uid + "','" + vid + "','" + vname + "','" + date + "','" + content + "')";
        num = conn.executeUpdate(sql);
        conn.close();
        return num;
    }

    public int deleteNote(String nid) {
        int num = 0;
        String sql = "delete from note where nid='" + nid + "'";
        num = conn.executeUpdate(sql);
        conn.close();
        return num;
    }

    public int addReNote(ReNoteEntity renote) {
        int num = 0;
        String nid = renote.getNid();
        String uid = renote.getUid();
        String vid = renote.getVid();
        String vname = renote.getVname();
        String content = renote.getContent();
        String date = renote.getDate();
        String sql = "insert into renote (nid,uid,vid,vname,date,content) values ('" + nid + "','" + uid + "','" + vid + "','" + vname + "','" + date + "','" + content + "')";
        num = conn.executeUpdate(sql);
        conn.close();
        return num;
    }

    public ArrayList queryReNotes(String uid) {
        ArrayList renoteList = new ArrayList();
        String sql = "select * from renote where uid='" + uid + "' order by rnid ASC";
        ResultSet rs = null;
        rs = conn.executeQuery(sql);
        try {
            while (rs.next()) {
                ReNoteEntity renote = new ReNoteEntity();
                renote.setRnid(rs.getString("rnid"));
                renote.setNid(rs.getString("nid"));
                renote.setUid(rs.getString("uid"));
                renote.setVid(rs.getString("vid"));
                renote.setVname(rs.getString("vname"));
                renote.setDate(rs.getString("date"));
                renote.setContent(rs.getString("content"));
                renoteList.add(renote);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NoteDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.close();
        }
        return renoteList;
    }

    public int deletereNote(String rnid) {
        int num = 0;
        String sql = "delete from renote where rnid='" + rnid + "'";
        num = conn.executeUpdate(sql);
        conn.close();
        return num;
    }
}
