/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.action;

import com.myapp.struts.dao.CommentDao;
import com.myapp.struts.dao.NewsDao;
import com.myapp.struts.entity.UserEntity;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author user
 */
public class DeleteCommentAction extends org.apache.struts.action.Action {

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
        UserEntity user = new UserEntity();
        String cid = user.getCid();
        String newsID = request.getParameter("newsID");
        String commentID = request.getParameter("commentID").toString();
        CommentDao commentDao = new CommentDao();
        if (commentDao.deleteComment(commentID) != 0) {
            NewsDao newsDao = new NewsDao();
            int comment_num = newsDao.queryNews(newsID).getComment_num();
            comment_num--;
            newsDao.updateNewsComment(comment_num, newsID);
        }
        // deleted success or not, it should return back to news displaying page using newsID
//        RequestDispatcher rd = request.getRequestDispatcher("displaynews.do?newsID=" + newsID + "&&cid=" + cid);
//        rd.forward(request, response);
        response.sendRedirect("displaynews.do?newsID=" + newsID + "&&cid=" + cid);
        return null;
    }
}
