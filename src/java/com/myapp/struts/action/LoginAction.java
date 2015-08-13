/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.action;

import com.myapp.struts.dao.UserDao;
import com.myapp.struts.entity.UserEntity;
import com.myapp.struts.form.UserForm;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.Action;

/**
 *
 * @author user
 */
public class LoginAction extends Action {

    /* forward name="success" path="" 
    private static final String SUCCESS = "success";*/
    private final static String FAILURE = "failure";

    /**
     * This is the action called from the Struts framework.
     * @ param mapping The ActionMapping used to select this instance.
     * @ param form The optional ActionForm bean for this request.
     * @ param request The HTTP Request we are processing.
     * @ param response The HTTP Response we are processing.
     * @ throws java.lang.Exception
     * @ return
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // extract user data
        HttpSession session = request.getSession();
        UserForm formBean = (UserForm) form;
        UserEntity user = new UserEntity();
        String username = formBean.getUsername();
        String password = formBean.getPassword();
        UserDao userDao = new UserDao();
        if (userDao.checkLogin(username, password)) {
            user = userDao.queryUser(username);
            session.setAttribute("user", user);
            /**
             *  登录成功后转到主页数据初始化
             *  cid为唯一标识不同用户
             */
//            RequestDispatcher rd = request.getRequestDispatcher("home.do?cid=" + user.getCid());
//            rd.forward(request, response);
            response.sendRedirect("home.do?cid=" + user.getCid());
        } else {
            return mapping.findForward(FAILURE);
        }
        return null;
    }
}
