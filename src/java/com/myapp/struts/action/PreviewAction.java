/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.action;

import com.myapp.struts.dao.NewsDao;
import com.myapp.struts.entity.NewsEntity;
import com.myapp.struts.form.NewsForm;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class PreviewAction extends org.apache.struts.action.Action {

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
        request.setCharacterEncoding("gbk");
        response.setCharacterEncoding("gbk");
        String cid = request.getParameter("cid");
        String newsID = request.getParameter("newsID");
        NewsDao newsDao = new NewsDao();
        HttpSession session = request.getSession();
        NewsEntity news = new NewsEntity();
//        NewsForm newsForm = (NewsForm)form;
        String name = request.getParameter("name");
        String content = request.getParameter("content");
        news.setNewsID(newsID);
        news.setName(name);
        news.setContent(content);
        String tid = request.getParameter("tid");
        news.setTid(tid);
        String type = newsDao.queryTypeById(tid);
        news.setType(type);
        Date current = new Date();
        System.out.println(current.toString());
        SimpleDateFormat matter = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        String date = matter.format(current);
        news.setDate(date);
        session.setAttribute("prenews", news);
        return mapping.findForward(SUCCESS);
    }
}
