<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="gbk"%>

<c:if test="${sessionScope.user.cid != param.cid}">
    <c:if test="${sessionScope.visitor == null}">
        <%--判断进入的主页是主人的还是其他人的, 若是他人的，则在session里储存其cid--%>
        <jsp:forward page="/firstvisit.do?cid=${param.cid}" />
    </c:if>
    <c:if test="${sessionScope.visitor != null && sessionScope.visitor['cid'] != param.cid}">
        <%--访问新的一个主页--%>
        <jsp:forward page="/firstvisit.do?cid=${param.cid}" />
    </c:if>
</c:if>

<div id="home-top">
    <p align="right">
        <c:forEach items="${sessionScope.nfriends}" var="nmyfriend">
            <c:if test="${nmyfriend.cid == param.cid}">
                <a href="friendMrg.do?cid=${param.cid}&&method=add">加为好友</a>
            </c:if>
        </c:forEach>
        <a href="home.do?cid=${sessionScope.user['cid']}">主页</a>
        <a href="logout.do">退出</a>
    </p>
</div>
<div id="blog-name">
    <c:if test="${sessionScope.user.cid == param.cid}">
        <c:if test="${(sessionScope.user.blogname != '') && (sessionScope.user.blogname != null)}">
        <h3>${sessionScope.user.blogname}</h3>
        </c:if>
        <c:if test="${(sessionScope.user.blogshow != '') && (sessionScope.user.blogshow != null)}">
        <h4>${sessionScope.user.blogshow}</h4>
        </c:if>
        <c:if test="${(sessionScope.user.blogname == '') || (sessionScope.user.blogname == null)}">
        <h3>空间名称</h3>
        </c:if>
        <c:if test="${(sessionScope.user.blogshow == '') || (sessionScope.user.blogshow == null)}">
        <h4>你的空间描述,简单介绍一下你的空间</h4>
        </c:if>
    </c:if>
    <c:if test="${sessionScope.user.cid != param.cid}">
        <c:if test="${(sessionScope.visitor.blogname != '') && (sessionScope.visitor.blogname != null)}">
        <h3>${sessionScope.visitor.blogname}</h3>
        </c:if>
        <c:if test="${(sessionScope.visitor.blogshow != '') && (sessionScope.visitor.blogshow != null)}">
        <h4>${sessionScope.visitor.blogshow}</h4>
        </c:if>
        <c:if test="${(sessionScope.visitor.blogname == '') || (sessionScope.visitor.blogname == null)}">
        <h3>空间名称</h3>
        </c:if>
        <c:if test="${(sessionScope.visitor.blogshow == '') || (sessionScope.visitor.blogshow == null)}">
        <h4>你的空间描述,简单介绍一下你的空间</h4>
        </c:if>
    </c:if>
</div>
<div id="home-nav">
    <a href="home.do?cid=${param.cid}">主页</a>
    <a href="info.do?cid=${param.cid}">个人档</a>
    <a href="log.do?cid=${param.cid}">日志</a>
    <a href="note.do?cid=${param.cid}">留言</a>
    <a href="mood.do?cid=${param.cid}">心情</a>
    <a href="photo.do?cid=${param.cid}">相册</a>
    <a href="friend.do?cid=${param.cid}">好友</a>
</div>
