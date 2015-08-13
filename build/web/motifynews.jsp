<%-- 
    Document   : motifynewsjsp
    Created on : 2010-4-8, 13:17:55
    Author     : user
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
        <title>�޸���־</title>
    </head>
    <body>
        <div id="container">
            <div id="header">
                <jsp:include page="/bloghead.jsp?cid=${param.cid}" />
            </div>
            <div id="content">
                <h4>�޸���־</h4>
                <div id="add-news">
                    <form name="newsform" action="motifynews.do?cid=${param.cid}&&newsID=${sessionScope.prenews.newsID}" method="post">
                        <p />����:<input type="text" name="name" value="${sessionScope.prenews.name}" />
                        <p /><textarea name="content" rows="6" cols="100">${sessionScope.prenews.content}</textarea>
                        <p />��־����<select name="tid">
                            <%--<option value="0">������־</option>--%>
                            <c:forEach items="${requestScope.typeList}" var="type">
                                <option value="${type.tid}">${type.type}</option>
                            </c:forEach>
                        </select>
                            <%--onclick="morifySubmit(this.name)"--%>
                        <p /><input type="submit" name="${param.cid}" value="����" />
                        <input type="submit"  id="${sessionScope.prenews.newsID}" name="${param.cid}" value="Ԥ��" onclick="previewMSubmit(this.name,this.id)"/>
                        <%--<a name="${param.cid}" href="#" onclick="previewSubmit(this.name)">Ԥ��</a>--%>
                        <a href="displaynews.do?cid=${param.cid}&&newsID=${sessionScope.prenews.newsID}">ȡ��</a>
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
