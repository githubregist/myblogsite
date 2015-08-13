/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.action;

import com.myapp.struts.dao.MoodDao;
import com.myapp.struts.entity.MoodEntity;
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
public class ListMoodAction extends org.apache.struts.action.Action {

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
        String uid = request.getParameter("cid");
        UserEntity user = (UserEntity) session.getAttribute("user");
//        if (uid == null) {
//            /**
//             * 因为从添加回复返回时没传送cid
//             */
//            uid = ((UserEntity) session.getAttribute("user")).getCid();
//        }
        ArrayList moodsList = new ArrayList();
        MoodDao moodDao = new MoodDao();
        moodsList = moodDao.queryMoods(uid);
        /**
         * 主人的心情列表 
         */
        if (moodsList != null) {
            request.setAttribute("mymoodsList", moodsList);
        }
        /**
         * 主人的当前心情
         */
        MoodEntity currentmood = new MoodEntity();
        currentmood = moodDao.queryCurrentMood(uid);
        if (currentmood != null) {
            request.setAttribute("mycurrentmood", currentmood);
        }
        /**
         * 好友心情
         */
        ArrayList fmood = new ArrayList();
        fmood = moodDao.queryFriendMoods(uid);
        if (currentmood != null) {
            request.setAttribute("fmood", fmood);
        }
        if (uid.equals(user.getCid())) {
            return mapping.findForward("mood");
        }
        if (!uid.equals(user.getCid())) {
            return mapping.findForward("vmood");
        }
        return null;
    }
}
