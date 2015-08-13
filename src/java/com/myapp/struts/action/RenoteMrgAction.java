/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.action;

import com.myapp.struts.dao.NoteDao;
import com.myapp.struts.entity.NoteEntity;
import com.myapp.struts.entity.ReNoteEntity;
import com.myapp.struts.entity.UserEntity;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class RenoteMrgAction extends org.apache.struts.action.Action {

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
        String cid = request.getParameter("cid");
        String method = request.getParameter("method");
        NoteDao noteDao = new NoteDao();
        if (method.equals("add")) {
            String nid = request.getParameter("nid");
            NoteEntity note = new NoteEntity();
            note = noteDao.queryNoteById(nid);
            String uid = note.getUid();
            String vid = ((UserEntity) session.getAttribute("user")).getCid();
            String vname = ((UserEntity) session.getAttribute("user")).getUsername();
            String renotecontent = request.getParameter("renotecontent");
            Date current = new Date();
            SimpleDateFormat matter = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            String date = matter.format(current);
            ReNoteEntity renote = new ReNoteEntity();
            renote.setNid(nid);
            renote.setUid(uid);
            renote.setVid(vid);
            renote.setVname(vname);
            renote.setContent(renotecontent);
            renote.setDate(date);
            int num = noteDao.addReNote(renote);
        }
        if (method.equals("delete")) {
            String rnid = request.getParameter("rnid");
            int num = noteDao.deletereNote(rnid);
        }
//        RequestDispatcher rd = request.getRequestDispatcher("note.do?cid=" + cid);
//        rd.forward(request, response);
        response.sendRedirect("note.do?cid=" + cid);
        return null;
    }
}
