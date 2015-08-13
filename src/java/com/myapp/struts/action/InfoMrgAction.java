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

/**
 *
 * @author user
 */
public class InfoMrgAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" 
    private static final String SUCCESS = "success";*/
    
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
        UserForm userForm = (UserForm)form;
        UserEntity user = new UserEntity();
        UserDao userDao = new UserDao();
        String cid = request.getParameter("cid");
        String method = request.getParameter("method");
        if(method.equals("basic")) {
            user.setCid(userForm.getCid());
            user.setUsername(userForm.getUsername());
            user.setSex(userForm.getSex());
            user.setBirthday(userForm.getBirthday());
            user.setEmail(userForm.getEmail());
            user.setHometown(userForm.getHometown());
            user.setLocation(userForm.getLocation());
            user.setUniversity(userForm.getUniversity());
            user.setProfession(userForm.getProfession());
            user.setCompany(userForm.getCompany());
            user.setOccupation(userForm.getOccupation());
            int num = userDao.motifyUserBasic(user);
        }
        if(method.equals("blog")) {
            user.setCid(userForm.getCid());
            user.setBlogname(userForm.getBlogname());
            user.setBlogshow(userForm.getBlogshow());
            user.setSignature(userForm.getSignature());
            int num = userDao.motifyUserBlog(user);
        }
        user = userDao.queryUserById(cid);
        session.setAttribute("user", user);
        RequestDispatcher rd = request.getRequestDispatcher("info.do?cid=" + cid);
            rd.forward(request, response);
        //response.sendRedirect("info.do?cid=" + cid);
        //return mapping.findForward(SUCCESS);
        return null;
    }
}
