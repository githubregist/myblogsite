/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.action;

import com.myapp.struts.dao.FriendsDao;
import com.myapp.struts.dao.UserDao;
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
public class ManagerFriendAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" 
    private static final String SUCCESS = "success";*/
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
        String fid = request.getParameter("cid");
        String method = request.getParameter("method");
        UserDao userDao = new UserDao();
        UserEntity visitor = userDao.queryUserById(fid);
        UserEntity user = (UserEntity) session.getAttribute("user");
        String uid = user.getCid();
        FriendsDao friendsDao = new FriendsDao();
        int num = 0;
        if (method.equals("add")) {
            num = friendsDao.requestFriend(visitor, user);
        }
        if (method.equals("cancel")) {
            num = friendsDao.cancelFriend(fid, uid);
        }
        if (method.equals("confirm")) {
            num = friendsDao.confirmFriend(visitor, user);
        }
        if (method.equals("delete")) {
            num = friendsDao.deleteFriend(fid, uid);
        }
        if (method.equals("reject")) {
            num = friendsDao.rejectFriend(fid, uid);
        }
        if (num != 0) {
            response.sendRedirect("friend.do?cid=" + user.getCid());
        }
        return null;
        //return mapping.findForward (SUCCESS);
    }
}
