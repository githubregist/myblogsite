<%-- 
    Document   : newsList
    Created on : 2010-3-12, 10:18:55
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
        <title>日志</title>
    </head>
    <body>
        <%--<c:set var="cid" value="${param.cid}" scope="session"/>--%>
        <%-- <c:if test="${param.cid != sessionScope.user['cid']}">
             <c:if test="${sessionScope.visitor == null}">
                 判断进入的主页是主人的还是其他人的, 若是他人的，则在session里储存其cid
                 <jsp:forward page="/firstvisit.do?cid=${param.cid}" />
                 <jsp:forward page="/log.do?cid=${param.cid}" />
             </c:if>
             <c:if test="${sessionScope.visitor['cid'] != param.cid}">
                 访问新的一个主页
                 <jsp:forward page="/firstvisit.do?cid=${param.cid}" />
                 <jsp:forward page="/log.do?cid=${param.cid}" />
             </c:if>
         </c:if>--%>
        <div id="container">
            <div id="header">
                <jsp:include page="/bloghead.jsp?cid=${param.cid}" />
            </div>
            <div id="main-left">
                <div id="news-list">
                    <h4>日志
                        <c:if test="${param.cid == sessionScope.user['cid']}">
                            <a href="preaddnews.do?cid=${param.cid}">写日志</a>
                        </c:if>
                    </h4>
                    <c:forEach items="${requestScope.newsList}" var="news">
                        <p>
                            <a href="listtypenews.do?tid=${news.tid}&&cid=${param.cid}">[${news.type}]</a>
                            <a href="displaynews.do?newsID=${news.newsID}">${news.name}</a>
                            <span id="news-info">发表于${news.date}&nbsp;&nbsp;(阅读${news["browser_num"]}评论${news["comment_num"]})</span>
                        </p>
                    </c:forEach>
                    <c:if test="${param.page == null}">
                        <c:set value="1" var="current_page"/>
                    </c:if>
                    <c:if test="${param.page != null}">
                        <c:set value="${param.page}" var="current_page" />
                    </c:if>
                    <c:if test="${requestScope.news_num >= 5 && requestScope.type_news_num == null}">
                        <p id="news-total-page"/>共${requestScope.news_num}篇，${requestScope.total_page}页，第${current_page}页
                        <p id="news-find-page"/><a href="log.do?cid=${param.cid}&&page=1">首页</a>&nbsp;&nbsp;
                        <c:if test="${current_page > 1}">
                            <a href="log.do?cid=${param.cid}&&page=${current_page - 1}">上一页</a>&nbsp;&nbsp;
                        </c:if>
                        <c:if test="${current_page < (requestScope.total_page)}">
                            <a href="log.do?cid=${param.cid}&&page=${current_page + 1}">下一页</a>&nbsp;&nbsp;
                        </c:if>
                        <a href="log.do?cid=${param.cid}&&page=${requestScope.total_page}">尾页</a>
                    </c:if>
                </div>
            </div>
            <div id="sidebar-right">
                <div id="news-type">
                    <h4>日志分类&nbsp;&nbsp;<a href="newstypeMrg.do?cid=${param.cid}&&method=show">管理分类</a></h4>
                    <p><a href="log.do?cid=${param.cid}">全部日志(${requestScope.news_num})</a></p>
                    <c:forEach items="${requestScope.typeList}" var="type">
                        <p><a href="listtypenews.do?tid=${type.tid}&&cid=${param.cid}">${type.type}(${type.news_num})</a></p>
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
