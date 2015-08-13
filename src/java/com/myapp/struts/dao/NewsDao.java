/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.dao;

import java.util.*;
import com.myapp.struts.base.DBConnection;
import com.myapp.struts.entity.NewsEntity;
import com.myapp.struts.entity.NewsTypeEntity;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author guohengj
 */
public class NewsDao {

    //ArrayList newsList = new ArrayList();
    DBConnection conn = new DBConnection();
    ResultSet rs = null;

    public NewsDao() {
    }

    public NewsEntity queryNews(String newsID) {
        NewsEntity news = new NewsEntity();
        String sql = "select * from news where newsID=" + newsID;
        ResultSet rs = conn.executeQuery(sql);
        try {
            if (rs.next()) {
                news.setCid(rs.getString("cid"));
                news.setUsername(rs.getString("username"));
                news.setNewsID(String.valueOf(rs.getString("newsID")));
                news.setName(String.valueOf(rs.getString("name")));
                news.setDate(rs.getString("date"));
                news.setTid(rs.getString("tid"));
                news.setType(rs.getString("type"));
                news.setContent(rs.getString("content"));
                news.setBrowser_num(rs.getInt("browser_num"));
                news.setComment_num(rs.getInt("comment_num"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NewsDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.close();
        }
        return news;
    }

    public ArrayList queryAllNews() {
        ArrayList list = new ArrayList();
        String sql = "select * from news";
        ResultSet rs = conn.executeQuery(sql);
        try {
            while (rs.next()) {
                NewsEntity news = new NewsEntity();
                news.setCid(rs.getString("cid"));
                news.setUsername(rs.getString("username"));
                news.setNewsID(String.valueOf(rs.getString("newsID")));
                news.setName(String.valueOf(rs.getString("name")));
                news.setDate(rs.getString("date"));
                news.setType(rs.getString("type"));
                news.setContent(rs.getString("content"));
                news.setBrowser_num(rs.getInt("browser_num"));
                news.setComment_num(rs.getInt("comment_num"));
                list.add(news);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NewsDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.close();
        }
        return list;
    }

    public ArrayList queryUserNews(String cid, int current_page) {
        int PAGE_SIZE = 5;
        int skipBegin = (current_page - 1) * PAGE_SIZE;
        int skipEnd = current_page * PAGE_SIZE;
        ArrayList list = new ArrayList();
        String sql = "select * from news where cid='" + cid + "' order by newsID desc";
        ResultSet rs = conn.executeQuery(sql);
        try {
            //定位到开始位置
            if (!rs.absolute(skipBegin + 1)) {
                /**
                 * 当skipBegin为0时，定位0时，rs.next()将取不到第一行
                 */
                rs.absolute(0);
            } else {
                NewsEntity news = new NewsEntity();
                news.setCid(rs.getString("cid"));
                news.setUsername(rs.getString("username"));
                news.setNewsID(String.valueOf(rs.getString("newsID")));
                news.setName(String.valueOf(rs.getString("name")));
                news.setDate(rs.getString("date"));
                news.setTid(rs.getString("tid"));
                news.setType(rs.getString("type"));
                news.setContent(rs.getString("content"));
                news.setBrowser_num(rs.getInt("browser_num"));
                news.setComment_num(rs.getInt("comment_num"));
                list.add(news);
                skipBegin++;
            }
            while (rs.next()) {
                if (skipBegin < skipEnd) {
                    NewsEntity news = new NewsEntity();
                    news.setCid(rs.getString("cid"));
                    news.setUsername(rs.getString("username"));
                    news.setNewsID(String.valueOf(rs.getString("newsID")));
                    news.setName(String.valueOf(rs.getString("name")));
                    news.setDate(rs.getString("date"));
                    news.setTid(rs.getString("tid"));
                    news.setType(rs.getString("type"));
                    news.setContent(rs.getString("content"));
                    news.setBrowser_num(rs.getInt("browser_num"));
                    news.setComment_num(rs.getInt("comment_num"));
                    list.add(news);
                    if (skipBegin == skipEnd - 1) {
                        break;
                    }
                }
                skipBegin++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(NewsDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.close();
        }
        return list;
    }

    public ArrayList queryNewsByType(String tid) {
        ArrayList list = new ArrayList();
        String sql = "select * from news where tid='" + tid + "' order by newsID desc";
        ResultSet rs = conn.executeQuery(sql);
        try {
            while (rs.next()) {
                NewsEntity news = new NewsEntity();
                news.setCid(rs.getString("cid"));
                news.setUsername(rs.getString("username"));
                news.setNewsID(String.valueOf(rs.getString("newsID")));
                news.setName(String.valueOf(rs.getString("name")));
                news.setDate(rs.getString("date"));
                news.setTid(rs.getString("tid"));
                news.setType(rs.getString("type"));
                news.setContent(rs.getString("content"));
                news.setBrowser_num(rs.getInt("browser_num"));
                news.setComment_num(rs.getInt("comment_num"));
                list.add(news);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NewsDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.close();
        }
        return list;
    }

    public ArrayList queryRecentNews(String cid) {
        ArrayList list = new ArrayList();
        String sql = "select * from news where cid='" + cid + "' order by newsID desc LIMIT 3";
        ResultSet rs = conn.executeQuery(sql);
        try {
            while (rs.next()) {
                NewsEntity news = new NewsEntity();
                news.setCid(rs.getString("cid"));
                news.setUsername(rs.getString("username"));
                news.setNewsID(String.valueOf(rs.getString("newsID")));
                news.setName(String.valueOf(rs.getString("name")));
                news.setDate(rs.getString("date"));
                news.setTid(rs.getString("tid"));
                news.setType(rs.getString("type"));
                news.setContent(rs.getString("content"));
                news.setBrowser_num(rs.getInt("browser_num"));
                news.setComment_num(rs.getInt("comment_num"));
                list.add(news);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NewsDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.close();
        }
        return list;
    }

    public int userNewsCount(String cid) {
        int num = 0;
        String sql = "select count(*) from news where cid='" + cid + "'";
        ResultSet rs = conn.executeQuery(sql);
        try {
            if (rs.next()) {
                num = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NewsDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.close();
        }
        return num;
    }

    public int allNewsCount() {
        int num = 0;
        String sql = "select count(*) from news";
        ResultSet rs = conn.executeQuery(sql);
        try {
            if (rs.next()) {
                num = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NewsDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.close();
        }
        return num;
    }

    public int countNewsByType(String tid) {
        int num = 0;
        String sql = "select count(*) from news where tid='" + tid + "'";
        ResultSet rs = conn.executeQuery(sql);
        try {
            if (rs.next()) {
                num = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NewsDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.close();
        }
        return num;
    }

    public int insertNews(NewsEntity news) {
        int num = 0;
        String sql = "insert into news(name, content,date,tid,type,cid,username) " +
                "values('" + news.getName() + "','" + news.getContent() + "','" + news.getDate() + "','" + news.getTid() + "','" + news.getType() + "','" + news.getCid() + "','" + news.getUsername() + "')";
        num = conn.executeUpdate(sql);
        conn.close();
        return num;
    }

    public int updateNews(NewsEntity news) {
        int num = 0;
        String sql = "update news set name='" + news.getName() + "',tid='" + news.getTid() + "',type='" + news.getType() + "',content='" + news.getContent() + "'where newsID='" + news.getNewsID() + "'";
        num = conn.executeUpdate(sql);
        conn.close();
        return num;
    }

    public int updateNewsBrowser(int browser_num, String newsID) {
        int num = 0;
        String sql = "update news set browser_num='" + browser_num + "'where newsID='" + newsID + "'";
        num = conn.executeUpdate(sql);
        conn.close();
        return num;
    }

    public int updateNewsComment(int comment_num, String newsID) {
        int num = 0;
        String sql = "update news set comment_num='" + comment_num + "'where newsID='" + newsID + "'";
        num = conn.executeUpdate(sql);
        conn.close();
        return num;
    }

    public int deleteNews(String newsID) {
        int num = 0;
        String sql = "delete from news where newsID='" + newsID + "'";
        num = conn.executeUpdate(sql);
        conn.close();
        return num;
    }

    public int updateColumnID(int newindex, int oldindex) {
        int num = 0;
        String sql = "update news set newsID='" + newindex + "'where newsID='" + oldindex + "'";
        num = conn.executeUpdate(sql);
        return num;
    }

    public ArrayList queryNewsType(String uid) {
        ArrayList typeList = new ArrayList();
        ResultSet rs;
        String sql = "select * from newstype where uid='" + uid + "'";
        rs = conn.executeQuery(sql);
        try {
            while (rs.next()) {
                NewsTypeEntity newsType = new NewsTypeEntity();
                newsType.setTid(rs.getString("tid"));
                newsType.setUid(rs.getString("uid"));
                newsType.setType(rs.getString("type"));
                typeList.add(newsType);
            }
            for (int i = 0; i < typeList.size(); i++) {
                NewsTypeEntity newsType = (NewsTypeEntity) typeList.get(i);
                int news_num = this.countNewsByType(newsType.getTid());
                newsType.setNews_num(news_num);
                typeList.set(i, newsType);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NewsDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.close();
        }
        return typeList;
    }

    public String queryTypeById(String tid) {
        String type = "";
        String sql = "select type from newstype where tid='" + tid + "'";
        ResultSet rs = conn.executeQuery(sql);
        try {
            while (rs.next()) {
                type = rs.getString("type");
            }
        } catch (SQLException ex) {
            Logger.getLogger(NewsDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.close();
        }
        return type;
    }

    public int addDefaultType(String cid) {
        int num = 0;
        String sql = "insert into newstype (uid,type) values('" + cid + "','个人日志'";
        num = conn.executeUpdate(sql);
        return num;
    }

    public int addNewsType(String uid, String type) {
        int num = 0;
        String sql = "insert into newstype (uid,type) values('" + uid + "','" + type + "')";
        num = conn.executeUpdate(sql);
        conn.close();
        return num;
    }

    public int deleteNewsType(String tid) {
        int num = 0;
        String sql = "delete from newstype where tid='" + tid + "'";
        num = conn.executeUpdate(sql);
        conn.close();
        return num;
    }

    public int updateNewsType(String tid, String type) {
        int num = 0;
        String sql = "update newstype set type='" + type + "' where tid='" + tid + "'";
        num = conn.executeUpdate(sql);
        conn.close();
        return num;
    }
}
