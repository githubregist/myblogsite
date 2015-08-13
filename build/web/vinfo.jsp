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
        <title>���˵�</title>
    </head>
    <body>
        <div id="container">
            <div id="header">
                <jsp:include page="/bloghead.jsp?cid=${param.cid}" />
            </div>
            <div id="sidebar-left">
                <div id="info-nav">
                    <h4>���˵�</h4>
                    <p /><a href="#" onclick="showBasicInfo()">��������</a>
                    <p /><a href="#" onclick="showBlogInfo()">�ռ�����</a>
                </div>
            </div>
            <div id="main-right">
                <div id="basic-info" class="show">
                    <h4>��������</h4>
                        <p />���֣�${sessionScope.user['username']}
                        <p />�Ա�${sessionScope.user['sex']}"
                        <p />���գ�"${sessionScope.user['birthday']}"
                        <p />�������䣺${sessionScope.user['email']}"
                        <p />���磺${sessionScope.user['hometown']}"
                        <p />�־ӵأ�${sessionScope.user['location']}"
                        <p />ѧУ��${sessionScope.user['university']}"
                        <p />רҵ����{sessionScope.user['profession']}"
                        <p />��˾��${sessionScope.user['company']}"
                        <p />ְҵ��${sessionScope.user['occupation']}"
                </div>
                <div id="blog-info" class="hide">
                    <h4>�ռ�����</h4>
                        <p />�ռ����ƣ�${sessionScope.user['blogname']}"
                        <p />�ռ�˵����${sessionScope.user['blogshow']}"
                        <p />ǩ������${sessionScope.user['signature']}
                </div>
            </div>
            <div id="clear"></div>
            <div id="footer">
                <jsp:include page="/foot.jsp" />
            </div>
        </div>
    </body>
</html>

