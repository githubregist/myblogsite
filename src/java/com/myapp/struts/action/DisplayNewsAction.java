/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.action;

import com.myapp.struts.dao.CommentDao;
import com.myapp.struts.dao.NewsDao;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.entity.NewsEntity;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 *
 * @author user
 */
public class DisplayNewsAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
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
        //移除修改日志时的session里的news
        session.removeAttribute("news");
        String newsID = request.getParameter("newsID");
        NewsEntity news = new NewsEntity();
        NewsDao newsDao = new NewsDao();
        news = newsDao.queryNews(newsID);
        // list all comments
        ArrayList commentList = new ArrayList();
        CommentDao commentDao = new CommentDao();
        commentList = commentDao.queryAllComments(newsID);
        int comment_num = commentDao.commentCount(newsID);
        int index = Integer.parseInt(newsID);
        if (news != null && commentList != null) {
            int browser_num = news.getBrowser_num();
            Hashtable view = (Hashtable) session.getAttribute("view");
            if (view.get(index).toString().equals("new")) {
                browser_num++;
                view.remove(index);
                view.put(index, "view");
                session.setAttribute("view", view);
            }
            news.setBrowser_num(browser_num);
            newsDao.updateNewsBrowser(browser_num, newsID);
            // update browser number fails !
            request.setAttribute("commentList", commentList);
            request.setAttribute("comment_num", comment_num);
            request.setAttribute("news", news);
            return mapping.findForward(SUCCESS);
        } else {
            return mapping.findForward(FAILURE);
        }
    }
}
