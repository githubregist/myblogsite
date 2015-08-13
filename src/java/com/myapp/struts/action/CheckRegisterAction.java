/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.action;

import com.myapp.struts.dao.NewsDao;
import com.myapp.struts.dao.UserDao;
import com.myapp.struts.entity.UserEntity;
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
public class CheckRegisterAction extends org.apache.struts.action.Action {
    
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
        DynaValidatorForm dyform = ( DynaValidatorForm) form;
        String username = dyform.getString("username");
        String password = dyform.getString("password");
        String con_password = dyform.getString("con_password");
        String email = dyform.getString("email");
        if(!password.equals(con_password)) {
            return mapping.findForward("failure");
        } else {
            UserEntity user = new UserEntity();
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);
            UserDao userDao = new UserDao();
            if(userDao.addUser(user) != 0) {
                user = userDao.queryUser(username);
                NewsDao newsDao = new NewsDao();
                int num = newsDao.addDefaultType(user.getCid());
                return mapping.findForward(SUCCESS);
            } else {
                return mapping.findForward("failure");
            }
        }
    }
}
