<%-- 
    Document   : previewnews
    Created on : 2010-4-27, 14:34:17
    Author     : user
--%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="gbk"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=gbk">
        <link href="style.css" rel="stylesheet" type="text/css" />
        <title> 日志预览</title>
    </head>
    <body>
        <div id="container">
            <div id="header">
                <jsp:include page="/bloghead.jsp?cid=${param.cid}" />
            </div>
            <div id="content">
                <div id="preview">
                    <h4>日志预览</h4>
                    <p />${sessionScope.prenews.name}&nbsp;&nbsp;
                    <p />${sessionScope.prenews.type}&nbsp;&nbsp;
                    ${sessionScope.prenews.date}
                    <p />${sessionScope.prenews.content}
                </div>
                <c:if test="${param.method == 'M'}">
                    <a href="motifynews.do?cid=${param.cid}&&newsID=${sessionScope.prenews.newsID}&&method=preview">发表日志</a>
                    <a href="premotifynews.do?cid=${param.cid}&&newsID=${sessionScope.prenews.newsID}">返回编辑</a>
                    <a href="displaynews.do?cid=${param.cid}&&newsID=${sessionScope.prenews.newsID}">取消</a>
                </c:if>
                <c:if test="${param.method != 'M'}">
                    <a href="addnews.do?cid=${param.cid}&&method=preview">发表日志</a>
                    <a href="preaddnews.do?cid=${param.cid}">返回编辑</a>
                    <a href="log.do?cid=${param.cid}">取消</a>
                </c:if>
            </div>
            <div id="clear"></div>
            <div id="footer">
                <jsp:include page="/foot.jsp" />
            </div>
        </div>
    </body>
</html:html>
