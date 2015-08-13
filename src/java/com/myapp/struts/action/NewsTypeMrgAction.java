/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.action;

import com.myapp.struts.dao.NewsDao;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 *
 * @author user
 */
public class NewsTypeMrgAction extends org.apache.struts.action.Action {

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
        String cid = request.getParameter("cid");
        String method = request.getParameter("method");
        NewsDao newsDao = new NewsDao();
        int num;
        if (method.equals("show")) {
            /**
             * 请求进入日志分类管理界面
             */
            ArrayList typeList = new ArrayList();
            typeList = newsDao.queryNewsType(cid);
            request.setAttribute("typeList", typeList);
            //用户的全部文章数
            int news_num = newsDao.userNewsCount(cid);
            request.setAttribute("news_num", news_num);
            /**
             * 重定向到newstype页面，相当于在 struts-config.xml 利用 forward 来跳转
             */
            RequestDispatcher rd = request.getRequestDispatcher("newstype.jsp?cid=" + cid);
            rd.forward(request, response);
        }
        if (method.equals("add")) {
            /**
             * 增加分类
             */
            DynaValidatorForm dyform = (DynaValidatorForm) form;
            String type = dyform.getString("type");
            num = newsDao.addNewsType(cid, type);
            if (num != 0) {
                RequestDispatcher rd = request.getRequestDispatcher("newstypeMrg.do?cid=" + cid + "&&method=show");
                rd.forward(request, response);
                //response.sendRedirect("newstypeMrg.do?cid=" + cid + "&&method=show");
            }
        }
        if (method.equals("edit")) {
            /**
             * 修改分类
             */
            DynaValidatorForm dyform = (DynaValidatorForm) form;
            String tid = request.getParameter("tid");
            String type = dyform.getString("edittype");
            num = newsDao.updateNewsType(tid, type);
            if (num != 0) {
                RequestDispatcher rd = request.getRequestDispatcher("newstypeMrg.do?cid=" + cid + "&&method=show");
                rd.forward(request, response);
                //response.sendRedirect("newstypeMrg.do?cid=" + cid + "&&method=show");
            }
        }
        if (method.equals("delete")) {
            /**
             * 删除分类
             */
            String tid = request.getParameter("tid");
            num = newsDao.deleteNewsType(tid);
            if (num != 0) {
//                RequestDispatcher rd = request.getRequestDispatcher("newstypeMrg.do?cid=" + cid + "&&method=show");
//                rd.forward(request, response);
                response.sendRedirect("newstypeMrg.do?cid=" + cid + "&&method=show");
            }
        }
        return null;
    }
}
