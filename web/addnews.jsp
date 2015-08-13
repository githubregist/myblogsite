<%-- 
    Document   : addnews
    Created on : 2010-3-12, 9:39:19
    Author     : guohengj
--%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="gbk"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=gbk">
        <link href="style.css" rel="stylesheet" type="text/css" />
        <script language="javascript" src="notespage.js" type="text/javascript"></script>
        <title>д��־</title>
    </head>
    <body>
        <div id="container">
            <div id="header">
                <jsp:include page="/bloghead.jsp?cid=${param.cid}" />
            </div>
            <div id="content">
                <div id="add-news">
                    <h4>д��־</h4>
                    <form name="newsform" action="addnews.do?cid=${param.cid}" method="post">
                    <input type="hidden" name="page" value="add" />
                        <c:if test="${sessionScope.prenews == null}">
                            <p />����:<input type="text" name="name" value="" />
                            <p /><textarea name="content" rows="6" cols="100"></textarea>
                            <p />��־����<select name="tid">
                                <c:forEach items="${requestScope.typeList}" var="type">
                                    <option value="${type.tid}">${type.type}</option>
                                </c:forEach>
                            </select>
                        </c:if>
                        <c:if test="${sessionScope.prenews != null}">
                            <p />����:<input type="text" name="name" value="${sessionScope.prenews.name}" />
                            <p /><textarea name="content" rows="10" cols="110">${sessionScope.prenews.content}</textarea>
                            <p />��־����&nbsp;&nbsp;<select name="tid">
                                <c:forEach items="${requestScope.typeList}" var="type">
                                    <option value="${type.tid}">${type.type}</option>
                                </c:forEach>
                            </select>
                        </c:if>
                        <p /><input type="submit" name="${param.cid}" value="����" onclick="addSubmit(this.name)"/>
                        <input type="submit" name="${param.cid}" value="Ԥ��" onclick="previewSubmit(this.name)"/>
                        <%--<a name="${param.cid}" href="#" onclick="previewSubmit(this.name)">Ԥ��</a>--%>
                        <a href="log.do?cid=${param.cid}">ȡ��</a>
                    </form>
                </div>
            </div>
            <div id="clear"></div>
            <div id="footer">
                <jsp:include page="/foot.jsp" />
            </div>
        </div>
    </body>
</html:html>
