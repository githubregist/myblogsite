<%-- 
    Document   : register
    Created on : 2010-4-20, 0:06:30
    Author     : user
--%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@page contentType="text/html" pageEncoding="gbk"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=gbk">
        <link href="style.css" rel="stylesheet" type="text/css" />
        <title>���˿ռ�-ע��</title>
    </head>
    <body>
        <div id="register">
            <h4>��д������Ϣ</h4>
            <html:form action="checkregister.do">
                <p />�û���:<html:text property="username" value="" />
                <p />����:<html:password property="password" value="" />
                <p />ȷ������:<html:password property="con_password" value="" />
                <p />��������:<html:text property="email" value="" />
                <p />��֤��:<html:text property="validate" value="" />
                <p /><html:submit value="ע��" />
                <a href="login.jsp">ȡ��</a>
            </html:form>
        </div>
        <div id="footer">
            <jsp:include page="/foot.jsp" />
        </div>
    </body>
</html>
