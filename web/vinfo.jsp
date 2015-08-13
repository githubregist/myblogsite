<%-- 
    Document   : vpersonalinfo
    Created on : 2010-4-27, 22:26:52
    Author     : user
--%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="gbk"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=gbk">
        <link href="style.css" rel="stylesheet" type="text/css" />
        <script language="javascript" src="notespage.js" type="text/javascript"></script>
        <title>个人档</title>
    </head>
    <body>
        <div id="container">
            <div id="header">
                <jsp:include page="/bloghead.jsp?cid=${param.cid}" />
            </div>
            <div id="sidebar-left">
                <div id="info-nav">
                    <h4>个人档</h4>
                    <p /><a href="#" onclick="showBasicInfo()">基础资料</a>
                    <p /><a href="#" onclick="showBlogInfo()">空间资料</a>
                </div>
            </div>
            <div id="main-right">
                <div id="basic-info" class="show">
                    <h4>基础资料</h4>
                        <p />名字：${sessionScope.user['username']}
                        <p />性别：${sessionScope.user['sex']}"
                        <p />生日："${sessionScope.user['birthday']}"
                        <p />电子邮箱：${sessionScope.user['email']}"
                        <p />故乡：${sessionScope.user['hometown']}"
                        <p />现居地：${sessionScope.user['location']}"
                        <p />学校：${sessionScope.user['university']}"
                        <p />专业：￥{sessionScope.user['profession']}"
                        <p />公司：${sessionScope.user['company']}"
                        <p />职业：${sessionScope.user['occupation']}"
                </div>
                <div id="blog-info" class="hide">
                    <h4>空间资料</h4>
                        <p />空间名称：${sessionScope.user['blogname']}"
                        <p />空间说明：${sessionScope.user['blogshow']}"
                        <p />签名档：${sessionScope.user['signature']}
                </div>
            </div>
            <div id="clear"></div>
            <div id="footer">
                <jsp:include page="/foot.jsp" />
            </div>
        </div>
    </body>
</html>

