/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.action;

import com.myapp.struts.dao.UserDao;
import com.myapp.struts.dao.TrackDao;
import com.myapp.struts.entity.UserEntity;
import javax.servlet.RequestDispatcher;
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
public class FirstVisitAction extends org.apache.struts.action.Action {

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
        String cid = request.getParameter("cid");
        UserDao userDao = new UserDao();
        TrackDao visitorDao = new TrackDao();
        visitorDao.updateTrackNum(cid);
        UserEntity visitor = new UserEntity();
        UserEntity user = new UserEntity();
        /*
         * 进入一个新主页，或切换一个新的主页
         **/
        //String uid = ((UserEntity)session.getAttribute("user")).getCid();
        if (session.getAttribute("visitor") == null) {
            user = (UserEntity) session.getAttribute("user");
            String uid = user.getCid();
            int num = 0;
            if (visitorDao.existsTrack(cid, uid) != 0) {
                //访客已来过
                num = visitorDao.updateTrack(cid, user);
            } else {
                //第一次访问
                num = visitorDao.insertTrack(cid, user);
            }
        } else {
            //访问另一个主页
            UserEntity v = (UserEntity) session.getAttribute("visitor");
            String vvid = v.getCid();
            if (!vvid.equals(cid)) {
                user = (UserEntity) session.getAttribute("user");
                String uid = user.getCid();
                int num = 0;
                if (visitorDao.existsTrack(cid, uid) != 0) {
                    //访客已来过
                    num = visitorDao.updateTrack(cid, user);
                } else {
                    //第一次访问
                    num = visitorDao.insertTrack(cid, user);
                }
            }
        }
        visitor = userDao.queryUserById(cid);
        session.setAttribute("visitor", visitor);
//        String url = request.getRequestURL().toString();
//        RequestDispatcher rd = request.getRequestDispatcher(url);
//        rd.forward(request, response);
        response.sendRedirect("home.do?cid=" + cid);
        return null;
    }
}
