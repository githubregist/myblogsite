/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.action;

import com.myapp.struts.dao.FriendsDao;
import com.myapp.struts.dao.MoodDao;
import com.myapp.struts.dao.NewsDao;
import com.myapp.struts.dao.NoteDao;
import com.myapp.struts.dao.TrackDao;
import com.myapp.struts.entity.MoodEntity;
import com.myapp.struts.entity.NewsEntity;
import com.myapp.struts.entity.UserEntity;
import java.util.ArrayList;
import java.util.Hashtable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author user
 */
public class HomeAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        /**
         * 首页最新日志
         */
        HttpSession session = request.getSession();
        String cid = request.getParameter("cid");
        String uid = ((UserEntity) session.getAttribute("user")).getCid();
        /**
         * 主人的当前心情
         */
        MoodDao moodDao = new MoodDao();
        MoodEntity currentmood = new MoodEntity();
        currentmood = moodDao.queryCurrentMood(cid);
        if (currentmood != null) {
            request.setAttribute("currentmood", currentmood);
        }
        /**
         * 最近日志
         */
        NewsDao newsDao = new NewsDao();
        ArrayList recent_news = new ArrayList();
        recent_news = newsDao.queryRecentNews(cid);
        if (cid.equals(uid)) {
            request.setAttribute("user_recent_news", recent_news);
        } else {
            request.setAttribute("visitor_recent_news", recent_news);
        }
        /**
         * 首先对最新新闻的状态设置,
         * 显示最新3篇
         */
        int news_num = 3;
        if (newsDao.userNewsCount(cid) < 3) {
            news_num = newsDao.userNewsCount(cid);
        }
        if (recent_news != null && recent_news.size() != 0) {
            Hashtable view;
            view = (Hashtable) session.getAttribute("view");
            if (view == null) {
                view = new Hashtable();
            }
            // 对于user
            for (int i = 0; i < news_num; i++) {
                //将该用户的所有文章标记为未读
                String newsID;
                newsID = ((NewsEntity) recent_news.get(i)).getNewsID();
                int index = Integer.parseInt(newsID);
                //index 与newsID 相对应
                if ((view == null) || (view != null && !view.containsKey(index))) {
                    view.put(index, "new");
                }
            }
            session.setAttribute("view", view);
        }
        /**
         * 最新留言
         */
        ArrayList recent_note = new ArrayList();
        NoteDao noteDao = new NoteDao();
        recent_note = noteDao.queryRecentNotes(cid);
        if (cid.equals(uid)) {
            request.setAttribute("urnote", recent_note);
        } else {
            request.setAttribute("vrnote", recent_note);
        }
        /**
         * 列出可能感兴趣的用户
         * 非主人的主页不列
         * session以辨别好友
         */
        ArrayList otherusers = new ArrayList();
        FriendsDao friendsDao = new FriendsDao();
        otherusers = friendsDao.queryNotFriends(cid);
        session.setAttribute("nfriends", otherusers);

        /**
         * 其他人的好友请求 待回复的好友请求，主人页面才可见
         */
        ArrayList pfriends = new ArrayList();
        pfriends = friendsDao.listPreFriends(cid);
        int pfriendsnum = friendsDao.pfriendsCount(cid);
        if (pfriends != null) {
            request.setAttribute("pfriendsnum", pfriendsnum);
        }
        ArrayList wfriends = new ArrayList();
        wfriends = friendsDao.listWaitFriends(cid);
        int wfriendsnum = friendsDao.wfriendsCount(cid);
        if (wfriends != null) {
            request.setAttribute("wfriendsnum", wfriendsnum);
        }
        /**
         * 我的足迹(主人页面可见)
         */
//        if (user.getCid().equals(cid)) {
        TrackDao trackDao = new TrackDao();
        ArrayList mytrack = new ArrayList();
        mytrack = trackDao.queryMyTrack(cid);
        request.setAttribute("mytrack", mytrack);
        /**
         * 访客记录
         */
        ArrayList trackList = new ArrayList();
        trackList =  trackDao.queryTrack(cid);
        if (cid.equals(uid)) {
            request.setAttribute("utrackList", trackList);
        } else {
            request.setAttribute("vtrackList", trackList);
        }

        if (cid.equals(uid)) {
            return mapping.findForward("home");
        } else {
            return mapping.findForward("vhome");
        }
    }
}
