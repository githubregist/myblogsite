<%-- 
    Document   : login
    Created on : 2010-3-9, 17:06:44
    Author     : user
    version :0.1 articles, comments(basically complished) 2010/4/11
    version: 0.5 (mul_user, friend, register) 2010/4/19
    version: 0.6 (notes, track, friend manager(add,del,rej,can),mood,head) 2010/4/22
                        (replynotes, gui ) 2010/4/23
    version: 0.6.1 (replynotes��delete notes and response, write and show moods) 2010/4/25
    version: 0.6.2 (news type manager) 2010/4/26-27
    version: 0.6.5 (add,motify,preview,news) 2010/4/27 -----ǧ����డ
                        (motiry info, improve gui)2010/4/27
    version:0.6.8(note,news page, improve gui, make pages more elegant) 2010/4/28
    version:0.6.9(elegant page, CSS,encounter the 'famous' IE bug! )2010/4/29
    version:0.7 (overcome the IE bug,�����������������ҳ��,
                        2010����һǰ���һ���ˣ�ץ����ɰ�)2010/4/30
--------������֤δ�У�ҳ�������δ�����������ʾδ��
--%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@page contentType="t butxext/html" pageEncoding="gbk"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=gbk">
        <link href="style.css" rel="stylesheet" type="text/css" />
        <title>���˿ռ�-��¼</title>
    </head>
    <body>
        <div id="login-head"></div>
        <div id="login">
            <h4>��¼</h4>
            <html:form action="/login">
                <p />�û���: <html:text property="username" />
                <p />����:<html:password property="password" />
                <p /><html:submit value="��¼" />
            </html:form>
            <a href="register.do">�û�ע��</a>
        </div>
        <div id="footer">
            <jsp:include page="/foot.jsp" />
        </div>
    </body>
</html:html>
