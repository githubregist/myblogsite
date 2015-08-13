/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.action;

import com.myapp.struts.dao.NewsDao;
import com.myapp.struts.entity.NewsEntity;
import com.myapp.struts.entity.UserEntity;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
public class AddNewsAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    private static final String FAILURE = "failure";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        request.setCharacterEncoding("gbk");
        response.setCharacterEncoding("gbk");
        NewsDao newsDao = new NewsDao();
        HttpSession session = request.getSession();
//        NewsForm newsBean = (NewsForm) form;
        NewsEntity news = new NewsEntity();
        if (request.getParameter("method") != null &&request.getParameter("method").equals("preview")) {
            news = (NewsEntity) session.getAttribute("prenews");
        } else {
            String name = request.getParameter("name");
            String content = request.getParameter("content");
            String tid = request.getParameter("tid");
            news.setName(name);
            news.setContent(content);
            news.setTid(tid);
            String type = newsDao.queryTypeById(tid);
            news.setType(type);
            Date current = new Date();
            System.out.println(current.toString());
            SimpleDateFormat matter = new SimpleDateFormat("yyyy-MM-dd");
            //there is a problem here, that's it can't get the right month
            //solution: m respects mimute, and M respects month
            String date = matter.format(current);
            news.setDate(date);
        }

        UserEntity user = new UserEntity();
        user = (UserEntity) session.getAttribute("user");
        //String cid = user.getCid();
        String cid = request.getParameter("cid");
        String username = user.getUsername();
        news.setCid(cid);
        news.setUsername(username);
        if (newsDao.insertNews(news) != 0) {
            int newindex = newsDao.allNewsCount();
            news = (NewsEntity) newsDao.queryAllNews().get(newindex - 1);
            int oldindex = Integer.parseInt(news.getNewsID());
            int num = 0;
            num = newsDao.updateColumnID(newindex, oldindex);
            if (num != 0) {
                //重新查询编号后的文章
                session.removeAttribute("prenews");
                news = (NewsEntity) newsDao.queryAllNews().get(newindex - 1);
                int index = Integer.parseInt(news.getNewsID());
                Hashtable view = (Hashtable) session.getAttribute("view");
                view.put(index, "new");
                session.setAttribute("view", view);
                /**
                 * 首页最新日志
                 */
                ArrayList user_recent_news = new ArrayList();
                user_recent_news = newsDao.queryRecentNews(cid);
                session.setAttribute("user_recent_news", user_recent_news);
                int news_num = 3;
                /**
                 * 首先对最新新闻的状态设置
                 */
                if (user_recent_news.size() != 0) {
                    //Hashtable view;
                    view = (Hashtable) session.getAttribute("view");
                    if (view == null) {
                        view = new Hashtable();
                    }
                    // 对于user
                    for (int i = 0; i < news_num; i++) {
                        //将该用户的所有文章标记为未读
                        String newsID = ((NewsEntity) user_recent_news.get(i)).getNewsID();
                        int index0 = Integer.parseInt(newsID);
                        //index 与newsID 相对应
                        if ((view == null) || (view != null && !view.containsKey(index0))) {
                            view.put(index0, "new");
                        }
                    }
                    session.setAttribute("view", view);
                }
                //add success, listnews.do
            }
//            RequestDispatcher rd = request.getRequestDispatcher("log.do?cid=" + cid);
//            rd.forward(request, response);
            response.sendRedirect("log.do?cid=" + cid);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("addnews.jsp?cid=" + cid);
            rd.forward(request, response);
            //fail, addnews.jsp
            }
        return null;
    }
}
