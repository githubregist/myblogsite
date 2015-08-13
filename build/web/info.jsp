<%-- 
    Document   : personalinfo
    Created on : 2010-4-12, 12:24:55
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
                    <p /><a href="#" onclick="showPreviewInfo()">预览</a>
                </div>
            </div>
            <div id="main-right">
                <div id="basic-info" class="show">
                    <h4>基础资料</h4>
                    <html:form action="infoMrg.do?cid=${param.cid}&&method=basic">
                        <p />名字：<html:text property="username" value="${sessionScope.user['username']}" />
                        <p />性别：<html:text property="sex" value="${sessionScope.user['sex']}" />
                        <p />生日：<html:text property="birthday" value="${sessionScope.user['birthday']}" />
                        <p />电子邮箱：<html:text property="email" value="${sessionScope.user['email']}" />
                        <p />故乡：<html:text property="hometown" value="${sessionScope.user['hometown']}" />
                        <p />现居地：<html:text property="location" value="${sessionScope.user['location']}" />
                        <p />学校：<html:text property="university" value="${sessionScope.user['university']}" />
                        <p />专业：<html:text property="profession" value="${sessionScope.user['profession']}" />
                        <p />公司：<html:text property="company" value="${sessionScope.user['company']}" />
                        <p />职业：<html:text property="occupation" value="${sessionScope.user['occupation']}" />
                        <p /><html:submit value="提交" />
                    </html:form>
                </div>
                <div id="blog-info" class="hide">
                    <h4>空间资料</h4>
                    <html:form action="infoMrg.do?cid=${param.cid}&&method=blog">
                        <p />空间名称：<html:text property="blogname" value="${sessionScope.user['blogname']}" />
                        <p />空间说明：<html:text property="blogshow" value="${sessionScope.user['blogshow']}" />
                        <p />签名档：
                        <p /><html:textarea property="signature" value="${sessionScope.user['signature']}" rows="3" cols="30"/>
                        <p /><html:submit value="提交" />
                    </html:form>
                </div>
                <div id="preview-info" class="hide">
                    <h4>个人档预览</h4>
                    <p>名字：${sessionScope.user["username"]}</p>
                    <p>性别：${sessionScope.user["sex"]}</p>
                    <p>生日：${sessionScope.user["birthday"]}</p>
                    <p>电子邮箱：${sessionScope.user["email"]}</p>
                    <p>故乡：${sessionScope.user['hometown']}</p>
                    <p>现居地：${sessionScope.user['location']}</p>
                    <p>学校：${sessionScope.user['university']}</p>
                    <p>专业：${sessionScope.user['profession']}</p>
                    <p>公司：${sessionScope.user['company']}</p>
                    <p>职业：${sessionScope.user['occupation']}</p>
                    <p>空间名称：${sessionScope.user['blogname']}</p>
                    <p>空间说明：${sessionScope.user['blogshow']}</p>
                    <p>签名档：${sessionScope.user['signature']}</p>
                </div>
            </div>
            <div id="clear"></div>
            <div id="footer">
                <jsp:include page="/foot.jsp" />
            </div>
        </div>
    </body>
</html>
