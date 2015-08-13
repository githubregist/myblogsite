/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.action;

import com.myapp.struts.dao.FriendsDao;
import com.myapp.struts.dao.TrackDao;
import com.myapp.struts.entity.UserEntity;
import java.util.ArrayList;
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
public class ListFriendsAction extends org.apache.struts.action.Action {

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
        HttpSession session = request.getSession();
        String cid = request.getParameter("cid");
        UserEntity user = (UserEntity) session.getAttribute("user");
        ArrayList friends = new ArrayList();
        FriendsDao friendsDao = new FriendsDao();
        friends = friendsDao.listFriends(cid);
        int friendsnum = friendsDao.friendsCount(cid);
        if (friends != null) {
            request.setAttribute("friends", friends);
            request.setAttribute("friendsnum", friendsnum);
        }
        /**
         * 其他人的好友请求
         */
        ArrayList pfriends = new ArrayList();
        pfriends = friendsDao.listPreFriends(cid);
        int pfriendsnum = friendsDao.pfriendsCount(cid);
        if (pfriends != null) {
            request.setAttribute("pfriends", pfriends);
            request.setAttribute("pfriendsnum", pfriendsnum);
        }
        /**
         * 待回复的好友请求
         */
        ArrayList wfriends = new ArrayList();
        wfriends = friendsDao.listWaitFriends(cid);
        int wfriendsnum = friendsDao.wfriendsCount(cid);
        if (wfriends != null) {
            request.setAttribute("wfriends", wfriends);
            request.setAttribute("wfriendsnum", wfriendsnum);
        }
        /**
         * 获取访客和我的足迹
         */
//        if (user.getCid().equals(cid)) {
            TrackDao trackDao = new TrackDao();
            ArrayList mytrack = new ArrayList();
            mytrack = trackDao.queryMyTrack(cid);
            ArrayList friendtrack = new ArrayList();
            friendtrack = trackDao.queryTrack(cid);
            request.setAttribute("mytrack", mytrack);
            request.setAttribute("friendtrack", friendtrack);
//        }
        /**
         * 列出可能感兴趣的用户
         * 非主人的主页不列
         * session以辨别好友
         */
        ArrayList otherusers = new ArrayList();
        otherusers = friendsDao.queryNotFriends(cid);
        session.setAttribute("nfriends", otherusers);
        if (cid.equals(user.getCid())) {
            return mapping.findForward("friend");
        }
        if (!cid.equals(user.getCid())) {
            return mapping.findForward("vfriend");
        }
        return null;
    }
}
