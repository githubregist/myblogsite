<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="gbk"%>

<c:if test="${sessionScope.user.cid != param.cid}">
    <c:if test="${sessionScope.visitor == null}">
        <%--�жϽ������ҳ�����˵Ļ��������˵�, �������˵ģ�����session�ﴢ����cid--%>
        <jsp:forward page="/firstvisit.do?cid=${param.cid}" />
    </c:if>
    <c:if test="${sessionScope.visitor != null && sessionScope.visitor['cid'] != param.cid}">
        <%--�����µ�һ����ҳ--%>
        <jsp:forward page="/firstvisit.do?cid=${param.cid}" />
    </c:if>
</c:if>

<div id="home-top">
    <p align="right">
        <c:forEach items="${sessionScope.nfriends}" var="nmyfriend">
            <c:if test="${nmyfriend.cid == param.cid}">
                <a href="friendMrg.do?cid=${param.cid}&&method=add">��Ϊ����</a>
            </c:if>
        </c:forEach>
        <a href="home.do?cid=${sessionScope.user['cid']}">��ҳ</a>
        <a href="logout.do">�˳�</a>
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
        <h3>�ռ�����</h3>
        </c:if>
        <c:if test="${(sessionScope.user.blogshow == '') || (sessionScope.user.blogshow == null)}">
        <h4>��Ŀռ�����,�򵥽���һ����Ŀռ�</h4>
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
        <h3>�ռ�����</h3>
        </c:if>
        <c:if test="${(sessionScope.visitor.blogshow == '') || (sessionScope.visitor.blogshow == null)}">
        <h4>��Ŀռ�����,�򵥽���һ����Ŀռ�</h4>
        </c:if>
    </c:if>
</div>
<div id="home-nav">
    <a href="home.do?cid=${param.cid}">��ҳ</a>
    <a href="info.do?cid=${param.cid}">���˵�</a>
    <a href="log.do?cid=${param.cid}">��־</a>
    <a href="note.do?cid=${param.cid}">����</a>
    <a href="mood.do?cid=${param.cid}">����</a>
    <a href="photo.do?cid=${param.cid}">���</a>
    <a href="friend.do?cid=${param.cid}">����</a>
</div>
