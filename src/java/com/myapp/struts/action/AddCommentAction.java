/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.action;

import com.myapp.struts.dao.CommentDao;
import com.myapp.struts.dao.NewsDao;
import com.myapp.struts.entity.CommentEntity;
import com.myapp.struts.entity.NewsEntity;
import com.myapp.struts.entity.UserEntity;
import com.myapp.struts.form.CommentForm;
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
public class AddCommentAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" 
    private static final String SUCCESS = "success";
    private static final String FAILURE = "failure";*/

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
        HttpSession session = request.getSession();
        String cid = "";
        if(session.getAttribute("user") != null) {
            cid = ((UserEntity)session.getAttribute("user")).getCid();
        } else {
            /**
             * 过客
             */
            cid = "0";
        }
        CommentForm commentBean = (CommentForm) form;
        CommentEntity comment = new CommentEntity();
        comment.setComment(commentBean.getComment());
        comment.setUsername(commentBean.getUsername());
        comment.setNewsID(newsID);
        comment.setCid(cid);
        Date current = new Date();
        System.out.println(current.toString());
        SimpleDateFormat matter = new SimpleDateFormat("yyyy-MM-dd");
        String date = matter.format(current);
        comment.setDate(date);
        CommentDao commentDao = new CommentDao();
        if (commentDao.insertComment(comment) != 0) {
//        return mapping.findForward(SUCCESS);
            NewsDao newsDao = new NewsDao();
            NewsEntity newsEntity = new NewsEntity();
            int comment_num;
            newsEntity = newsDao.queryNews(newsID);
            comment_num = newsEntity.getComment_num();
            comment_num ++;
            newsEntity.setComment_num(comment_num);
            newsDao.updateNewsComment(comment_num, newsID);
            response.sendRedirect("displaynews.do?newsID=" + newsID);
        }
        return null;
    }
}
