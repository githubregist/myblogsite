<%-- 
    Document   : vfriend
    Created on : 2010-4-29, 1:36:25
    Author     : user
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="gbk"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=gbk">
        <link href="style.css" rel="stylesheet" type="text/css" />
        <script language="javascript" src="notespage.js" type="text/javascript"></script>
        <title>好友</title>
    </head>
    <body>
        <div id="container">
            <div id="header">
                <jsp:include page="/bloghead.jsp?cid=${param.cid}" />
            </div>
            <div id="content">
                <div id="friend-headline">
                    <h4>好友</h4>
                </div>
                <div id="friend-nav">
                    <p /><a id="friend-info-a" href="#" class="current-tab" onclick="showFriendInfo()">他(她)好友</a>
                    <a id="friend-request-a" href="#" class="not-current-tab" onclick="showFriendRequest()">好友请求</a>
                    <a id="friend-track-a" href="#" class="not-current-tab" onclick="showFriendTrack()">最近访客</a>
                    <a id="my-track-a" href="#" class="not-current-tab" onclick="showMyTrack()">他(她)的足迹</a>
                </div>
                <div id="friend-info" class="show">
                    <c:forEach items="${requestScope.friends}" var="friend">
                        <p>
                            <a href="home.do?cid=${friend['cid']}" target="_blank">${friend["username"]}</a>
                            <c:if test="${param.cid == sessionScope.user['cid']}">
                                <a class="edit" href="managerfriend.do?cid=${friend['cid']}&&method=delete" >删除</a>
                            </c:if>
                        </p>
                        <p>
                            ${friend["sex"]}
                            ${friend["location"]}
                            ${friend["university"]}
                        </p>
                    </c:forEach>
                </div>
                <div id="friend-request" class="hide">
                    <p>其他人的好友请求(${requestScope.pfriendsnum})</p>
                    <c:forEach items="${requestScope.pfriends}" var="pfriend">
                        <p>
                            <a href="home.do?cid=${pfriend['cid']}" target="_blank">${pfriend["username"]}</a>
                        </p>
                        <p class="endline">
                            ${pfriend["sex"]}
                            ${pfriend["location"]}
                            ${pfriend["university"]}
                        </p>
                    </c:forEach>
                    <p>待回复的好友请求(${requestScope.wfriendsnum})</p>
                    <c:forEach items="${requestScope.wfriends}" var="wfriend">
                        <p>
                            <a href="home.do?cid=${wfriend['cid']}" target="_blank">${wfriend["username"]}</a>
                        </p>
                        <p class="endline">
                            ${wfriend["sex"]}
                            ${wfriend["location"]}
                            ${wfriend["university"]}
                        </p>
                    </c:forEach>
                </div>
                <div id="friend-track" class="hide">
                    <c:forEach items="${requestScope.friendtrack}" var="friendtrack">
                        <p>
                            <a href="home.do?cid=${friendtrack['cid']}" target="_blank">${friendtrack["username"]}</a>
                            <c:forEach items="${sessionScope.nfriends}" var="nmyfriend">
                                <c:if test="${nmyfriend.cid == friendtrack.cid}">
                                    <a class="edit" href="managerfriend.do?cid=${friendtrack['cid']}&&method=add">加为好友</a>
                                </c:if>
                            </c:forEach>
                        </p>
                        <p class="endline"/>${friendtrack["visittime"]}
                    </c:forEach>
                </div>
                <div id="my-track" class="hide">
                    <c:forEach items="${requestScope.mytrack}" var="mytrack">
                        <p>
                            <a href="home.do?cid=${mytrack['cid']}" target="_blank">${mytrack["username"]}</a>
                            <c:forEach items="${sessionScope.nfriends}" var="nmyfriend">
                                <c:if test="${nmyfriend.cid == mytrack.cid}">
                                    <a class="edit" href="managerfriend.do?cid=${mytrack['cid']}&&method=add">加为好友</a>
                                </c:if>
                            </c:forEach>
                        </p>
                        <p class="endline"/>${mytrack["visittime"]}
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
