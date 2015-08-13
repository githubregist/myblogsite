/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.action;

import com.myapp.struts.dao.NewsDao;
import com.myapp.struts.entity.NewsEntity;
import com.myapp.struts.entity.UserEntity;
import java.util.ArrayList;
import java.util.Hashtable;
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
public class ListNewsAction extends org.apache.struts.action.Action {

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

        HttpSession session = request.getSession();
        session.removeAttribute("prenews");
        NewsDao newsDao = new NewsDao();
        ArrayList newsList = new ArrayList();
        UserEntity user = (UserEntity) session.getAttribute("user");
        /**
         * 这是从请求地址获取cid
         */
        //String cid = session.getAttribute("cid").toString();
        String cid = request.getParameter("cid");
        if (cid == null) {
            /**
             * 因为从删除日志返回时没传送cid
             */
            cid = user.getCid();
        }
        /**
         *  当前页数
         */
        int current_page = 1;
        if (request.getParameter("page") == null) {
            current_page = 1;
        } else {
            current_page = Integer.parseInt(request.getParameter("page"));
        }
        //读取某一用户的全部文章，记录数目
        int news_num = newsDao.userNewsCount(cid);
        newsList = newsDao.queryUserNews(cid, current_page);
        request.setAttribute("newsList", newsList);
        request.setAttribute("news_num", news_num);
        int total_page = (news_num + 4) / 5;
        request.setAttribute("total_page", total_page);
        if (newsList != null) {
            Hashtable view;
            view = (Hashtable) session.getAttribute("view");
            if (view == null) {
                view = new Hashtable();
            }
            // 对于user
            for (int i = 0; i < newsList.size(); i++) {
                //将该用户的所有文章标记为未读
                String newsID = ((NewsEntity) newsList.get(i)).getNewsID();
                int index = Integer.parseInt(newsID);
                //index 与newsID 相对应
                if ((view == null) || (view != null && !view.containsKey(index))) {
                    view.put(index, "new");
                }
            }
            session.setAttribute("view", view);
        }
        /**
         * 读取栏目信息
         */
        ArrayList typeList = new ArrayList();
        typeList = newsDao.queryNewsType(cid);
        request.setAttribute("typeList", typeList);
        if (cid.equals(user.getCid())) {
            return mapping.findForward("news");
        }
        if (!cid.equals(user.getCid())) {
            return mapping.findForward("vnews");
        }
        return null;
    }
}
