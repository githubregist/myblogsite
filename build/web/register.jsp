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
        <title>个人空间-注册</title>
    </head>
    <body>
        <div id="register">
            <h4>填写以下信息</h4>
            <html:form action="checkregister.do">
                <p />用户名:<html:text property="username" value="" />
                <p />密码:<html:password property="password" value="" />
                <p />确认密码:<html:password property="con_password" value="" />
                <p />电子邮箱:<html:text property="email" value="" />
                <p />验证码:<html:text property="validate" value="" />
                <p /><html:submit value="注册" />
                <a href="login.jsp">取消</a>
            </html:form>
        </div>
        <div id="footer">
            <jsp:include page="/foot.jsp" />
        </div>
    </body>
</html>
