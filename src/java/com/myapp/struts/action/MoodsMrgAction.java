/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.action;

import com.myapp.struts.dao.MoodDao;
import com.myapp.struts.entity.MoodEntity;
import com.myapp.struts.entity.UserEntity;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 *
 * @author user
 */
public class MoodsMrgAction extends org.apache.struts.action.Action {

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
        String uid = request.getParameter("cid");
        String method = request.getParameter("method");
        HttpSession session = request.getSession();
        UserEntity user = (UserEntity) session.getAttribute("user");
        String uname = user.getUsername();
        MoodDao moodDao = new MoodDao();
        MoodEntity mood = new MoodEntity();
        if (method.equals("add")) {
            DynaValidatorForm dyform = (DynaValidatorForm) form;
            String moodcontent = dyform.getString("moodcontent");
            mood.setUid(uid);
            mood.setUname(uname);
            mood.setContent(moodcontent);
            Date current = new Date();
            SimpleDateFormat matter = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            String date = matter.format(current);
            mood.setDate(date);
            int num = moodDao.addMood(mood);
            if (num != 0) {
                response.sendRedirect("mood.do?cid=" + uid);
            }
        }
        if (method.equals("delete")) {
            String mid = request.getParameter("mid");
            uid = request.getParameter("cid");
            int num = moodDao.deleteMood(mid);
            if (num != 0) {
                response.sendRedirect("mood.do?cid=" + uid);
            }
        }
        return null;
    }
}
