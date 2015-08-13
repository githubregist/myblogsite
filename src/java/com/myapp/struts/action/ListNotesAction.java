/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.action;

import com.myapp.struts.dao.NoteDao;
import com.myapp.struts.entity.UserEntity;
import java.util.ArrayList;
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
public class ListNotesAction extends org.apache.struts.action.Action {

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
        HttpSession session = request.getSession();
        UserEntity user = (UserEntity) session.getAttribute("user");
        String uid = request.getParameter("cid");
//        if (uid == null) {
//            /**
//             * 因为从添加回复返回时没传送cid
//             */
//            uid = ((UserEntity) session.getAttribute("user")).getCid();
//        }
        /**
         *  当前页数
         */
        int current_page = 1;
        if (request.getParameter("page") == null) {
            current_page = 1;
        } else {
            current_page = Integer.parseInt(request.getParameter("page"));
        }
        ArrayList notesList = new ArrayList();
        ArrayList renoteList = new ArrayList();
        NoteDao noteDao = new NoteDao();
        notesList = noteDao.queryNotes(uid, current_page);
        renoteList = noteDao.queryReNotes(uid);
        request.setAttribute("notesList", notesList);
        request.setAttribute("renoteList", renoteList);
        int notesnum;
        notesnum = noteDao.notesCount(uid);
        request.setAttribute("notesnum", notesnum);
        int total_page = (notesnum + 4) / 5;
        request.setAttribute("total_page", total_page);
        //response.sendRedirect("notes.jsp?cid=" + uid);
        if (uid.equals(user.getCid())) {
            return mapping.findForward("note");
        }
        if (!uid.equals(user.getCid())) {
            return mapping.findForward("vnote");
        }
        return null;
    }
}
