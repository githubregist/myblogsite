<%-- 
    Document   : login
    Created on : 2010-3-9, 17:06:44
    Author     : user
    version :0.1 articles, comments(basically complished) 2010/4/11
    version: 0.5 (mul_user, friend, register) 2010/4/19
    version: 0.6 (notes, track, friend manager(add,del,rej,can),mood,head) 2010/4/22
                        (replynotes, gui ) 2010/4/23
    version: 0.6.1 (replynotes，delete notes and response, write and show moods) 2010/4/25
    version: 0.6.2 (news type manager) 2010/4/26-27
    version: 0.6.5 (add,motify,preview,news) 2010/4/27 -----千辛万苦啊
                        (motiry info, improve gui)2010/4/27
    version:0.6.8(note,news page, improve gui, make pages more elegant) 2010/4/28
    version:0.6.9(elegant page, CSS,encounter the 'famous' IE bug! )2010/4/29
    version:0.7 (overcome the IE bug,马马虎虎，完成了其他页面,
                        2010年五一前最后一天了，抓紧完成吧)2010/4/30
--------数据验证未有，页面出错处理未做，警告和提示未有
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
        <title>个人空间-登录</title>
    </head>
    <body>
        <div id="login-head"></div>
        <div id="login">
            <h4>登录</h4>
            <html:form action="/login">
                <p />用户名: <html:text property="username" />
                <p />密码:<html:password property="password" />
                <p /><html:submit value="登录" />
            </html:form>
            <a href="register.do">用户注册</a>
        </div>
        <div id="footer">
            <jsp:include page="/foot.jsp" />
        </div>
    </body>
</html:html>
