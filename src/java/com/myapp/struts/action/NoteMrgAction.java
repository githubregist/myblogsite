/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.action;

import com.myapp.struts.dao.NoteDao;
import com.myapp.struts.entity.NoteEntity;
import com.myapp.struts.entity.UserEntity;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
public class NoteMrgAction extends org.apache.struts.action.Action {

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
        HttpSession session = request.getSession();
        String method = request.getParameter("method");
        NoteDao noteDao = new NoteDao();
        String uid = request.getParameter("cid");
        if (method.equals("add")) {
            DynaValidatorForm dyform = (DynaValidatorForm) form;
            //留言者为已登录用户
            String vid = ((UserEntity) session.getAttribute("user")).getCid();
            String vname = ((UserEntity) session.getAttribute("user")).getUsername();
            String notecontent = dyform.getString("notecontent");
            Date current = new Date();
            System.out.println(current.toString());
            SimpleDateFormat matter = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            String date = matter.format(current);
            NoteEntity note = new NoteEntity();
            note.setUid(uid);
            note.setVid(vid);
            note.setVname(vname);
            note.setContent(notecontent);
            note.setDate(date);
            int num = noteDao.addNote(note);
            if (num != 0) {
                /**
                 * 所访问主页的最新留言
                 */
                ArrayList recent_note = new ArrayList();
                recent_note = noteDao.queryRecentNotes(uid);
                session.setAttribute("vrnote", recent_note);
            }
        }
        if(method.equals("delete")) {
            String nid = request.getParameter("nid");
            int num = noteDao.deleteNote(nid);
             if (num != 0) {
                /**
                 * 所访问主页的最新留言
                 */
                ArrayList recent_note = new ArrayList();
                recent_note = noteDao.queryRecentNotes(uid);
                session.setAttribute("vrnote", recent_note);
            }
        }
        response.sendRedirect("note.do?cid=" + uid);
        return null;
    }
}
