<%-- 
    Document   : photo
    Created on : 2010-4-30, 22:40:38
    Author     : user
--%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="gbk"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=gbk">
        <link href="style.css" rel="stylesheet" type="text/css" />
        <script language="javascript" src="notespage.js" type="text/javascript"></script>
        <title>��Ƭ</title>
    </head>
    <body>
        <div id="container">
            <div id="header">
                <jsp:include page="/bloghead.jsp?cid=${param.cid}" />
            </div>
            <div id="content">
                <div id="write-mood">
                    <h4>��Ƭ</h4>
                    <p />��Ƭû�ҵ����ǲ��Ǻ�ʧ���أ�
                    <p />����������̫���ˣ�����Ƭͦ���İ���������ؼ�һ�㡪������ҳ�ϴ���ͼƬ�һ����Ǻ������ء�
                </div>
            </div>
            <div id="clear"></div>
            <div id="footer">
                <jsp:include page="/foot.jsp" />
            </div>
        </div>
    </body>
</html>
