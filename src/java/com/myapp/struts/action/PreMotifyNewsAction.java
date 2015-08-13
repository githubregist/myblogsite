/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.action;

import com.myapp.struts.entity.NewsEntity;
import com.myapp.struts.dao.NewsDao;
import com.myapp.struts.entity.UserEntity;
import java.util.ArrayList;
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
public class PreMotifyNewsAction extends org.apache.struts.action.Action {

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
        UserEntity user = (UserEntity) session.getAttribute("user");
        String cid = user.getCid();
        String newsID = request.getParameter("newsID").toString();
        NewsEntity news = new NewsEntity();
        NewsDao newsDao = new NewsDao();
        news = newsDao.queryNews(newsID);
        if (news != null) {
            if (session.getAttribute("prenews") == null) {
                session.setAttribute("prenews", news);
            }
            /**
             * 读取栏目信息
             */
            ArrayList typeList = new ArrayList();
            typeList = newsDao.queryNewsType(cid);
            request.setAttribute("typeList", typeList);
            return mapping.findForward(SUCCESS);
        } else {
            //return mapping.findForward(FAILURE);
            RequestDispatcher rd = request.getRequestDispatcher("displaynews.do?newsID=" + newsID);
            rd.forward(request, response);
            //response.sendRedirect("displaynews.do?newsID=" + newsID);
            return null;
        }
    }
}
