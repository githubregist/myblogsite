<%-- 
    Document   : vmood
    Created on : 2010-4-29, 1:24:30
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
        <title>心情</title>
    </head>
    <body>
        <div id="container">
            <div id="header">
                <jsp:include page="/bloghead.jsp?cid=${param.cid}" />
            </div>
            <div id="content">
                <div id="write-mood">
                    <h4>心情</h4>
                    <p />他(她):"${requestScope.mycurrentmood.content}"&nbsp;&nbsp;
                    ${requestScope.mycurrentmood.date}
                </div>
                <div id="mood-nav">
                    <p><a id="my-mood-a" href="#" class="current-tab" onclick="showMyMood()">他(她)的心情</a>
                        <a id="friend-mood-a" href="#" class="not-current-tab" onclick="showFriendMood()">好友心情</a>
                    </p>
                </div>
                <div id="friend-mood" class="hide">
                    <c:forEach items="${requestScope.fmood}" var="fmood">
                        <p /><a href="home.do?cid=${param.cid}" target="_blank">${fmood.uname}</a>&nbsp;${fmood.content}
                        <p class="date">${fmood.date}</p>
                        <%--<p />评论(${fmood.re_num})
                        <a href="remoodMrg.do?mid=${fmood.mid}&&uid=${fmood.uid}&&method=add">评论心情</a>--%>
                    </c:forEach>
                </div>
                <div id="my-mood" class="show">
                    <c:forEach items="${requestScope.mymoodsList}" var="mymood">
                        <p />${mymood.content}
                        <p class="date">${mymood.date}</p>
                    </c:forEach>
                </div>
            </div>
            <div id="clear"></div>
            <div id="footer">
                <jsp:include page="/foot.jsp" />
            </div>
        </div>
    </body>
</html>
