/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.action;

import com.myapp.struts.dao.NewsDao;
import com.myapp.struts.entity.NewsEntity;
import com.myapp.struts.entity.UserEntity;
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
public class MotifyNewsAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" 
    private static final String SUCCESS = "success";*/
    private static final String FAILURE = "failure";

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
        HttpSession session = request.getSession();
        UserEntity user = (UserEntity)session.getAttribute("user");
        NewsDao newsDao = new NewsDao();
        NewsEntity news = new NewsEntity();
        String newsID = request.getParameter("newsID");
        String method = request.getParameter("method");
        if (method != null && method.equals("preview")) {
            //如果是从预览中发表的
            news = (NewsEntity) session.getAttribute("prenews");
        } else {
            //如果是从表单页面提交
            String name = request.getParameter("name");
            String content = request.getParameter("content");
            String tid = request.getParameter("tid");
            news.setNewsID(newsID);
            news.setName(name);
            news.setContent(content);
            news.setTid(tid);
            String type = newsDao.queryTypeById(tid);
            news.setType(type);
        }
        //news.setDate(newsBean.getDate());
        //do not motify the date
        if (newsDao.updateNews(news) != 0) {
            //成功修改后，移除修改日志时的session里的news
            session.removeAttribute("news");
//            RequestDispatcher rd = request.getRequestDispatcher("displaynews.do?newsID=" + news.getNewsID());
//            rd.forward(request, response);
            response.sendRedirect("displaynews.do?newsID=" + news.getNewsID() + "&&cid=" + user.getCid());
        } else {
            return mapping.findForward(FAILURE);
        }
        return null;
    }
}
