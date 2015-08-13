/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.form;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 * @date 2010/3/13
 * @author guohengj
 */
public class NewsForm extends org.apache.struts.action.ActionForm {
    
    private String name;
    private String content;
    private String tid;
    private String date;
    private String error;

    public NewsForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getError() {
        return error;
    }
    public void setError() {
        this.error =
                "<span style='color:red'>Please provide valid entries for every field</span>";
    }
    /**
     * @return
     */
    public String getName() {
        return name;
    }
    /**
     * @param string
     */
    public void setName(String string) {
        name = string;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getTid() {
        return tid;
    }
    public void setTid(String tid) {
        this.tid = tid;
    }
    public String getDate() {
        return this.date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request,HttpServletResponse response) {
        ActionErrors errors = new ActionErrors();
        if (getName() == null || getName().length() < 1) {
            errors.add("name", new ActionMessage("error.name.required"));
            // TODO: add 'error.name.required' key to your resources
        }
        return errors;
    }
    @Override
    public void reset(ActionMapping mapping,HttpServletRequest request) {
        super.reset(mapping, request);
        String page = request.getParameter("page");
        if(page == null || page.equals("add")) {
            setName(null);
            setTid(null);
            setContent(null);
        } else {

        }
    }
}
