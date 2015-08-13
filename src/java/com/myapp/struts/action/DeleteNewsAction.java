/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.action;

import com.myapp.struts.dao.NewsDao;
import com.myapp.struts.entity.UserEntity;
import java.util.Hashtable;
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
public class DeleteNewsAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    //private static final String FAILURE = "failure";

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
        String newsID = request.getParameter("newsID").toString();
        NewsDao newsDao = new NewsDao();
        HttpSession session = request.getSession();
        UserEntity user = new UserEntity();
        user = (UserEntity) session.getAttribute("user");
        String cid = user.getCid();
        //该用户的文章数
        //int news_num = newsDao.newsCount(cid);
        //在阅读状态的索引（从1开始）
        int index = Integer.parseInt(newsID);
        if (newsDao.deleteNews(newsID) != 0) {
            //先删除该文章的阅读状态
            Hashtable view = (Hashtable) session.getAttribute("view");
            view.remove(index);
            session.setAttribute("view", view);
            //如果是删除了最后一篇文章，文章号不需自动减1
            if (newsDao.allNewsCount() == Integer.parseInt(newsID) - 1) {
                return mapping.findForward(SUCCESS);
            } else {
                //全部用户的全部文章数
                int all_news_num = newsDao.allNewsCount();
                int startindex = Integer.parseInt(newsID);
                int num = 0;
                for (int i = startindex + 1; i <= all_news_num + 1; i++) {
                    int newindex = i - 1;
                    int oldindex = i;
                    num += newsDao.updateColumnID(newindex, oldindex);
                    //删除一篇文章后ID自动减1
                }
                RequestDispatcher rd = request.getRequestDispatcher("log.do?cid=" + cid);
                rd.forward(request, response);
            }
        } else {
            //return mapping.findForward(FAILURE);
//            RequestDispatcher rd = request.getRequestDispatcher("displaynews.do?newsID=" + newsID);
//            rd.forward(request, response);
            response.sendRedirect("displaynews.do?newsID=" + newsID + "&&cid=" + cid);
        }
        return null;
    }
}
