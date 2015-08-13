<%-- 
    Document   : home
    Created on : 2010-4-12, 12:17:29
    Author     : guohengj
    һ�����⣬������������ҳ������ʾ����
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
        <title>������ҳ</title>
    </head>
    <body>
        <c:if test="${sessionScope.user == null}">
            <jsp:forward page="/login.jsp" />
        </c:if>
        <div id="container">
            <div id="header">
                <jsp:include page="/bloghead.jsp?cid=${param.cid}" />
            </div>
            <div id="main-left">
                <div id="main-left-left">
                    <div id="home-about">
                        <h4>������</h4>
                        <p />������${sessionScope.user["username"]}
                        <p />��������${sessionScope.user["visitnum"]}
                        <p />�ȼ���${sessionScope.user["level"]}
                    </div>
                    <div id="home-recommend-friend">
                        <h4>�������ʶ��(��)��</h4>
                        <c:forEach items="${sessionScope.nfriends}" var="nfriend">
                            <dl>
                                <dt>
                                    <a href="home.do?cid=${nfriend['cid']}" target="_blank">${nfriend["username"]}</a>
                                </dt>
                                <dd class="img">
                                    <img src="img/Azul.gif" width="40" height="40" alt="img here" />
                                </dd>
                                <dd>${nfriend.sex}&nbsp;${nfriend.location}</dd>
                                <dd>
                                    <a href="managerfriend.do?cid=${nfriend['cid']}&&method=add">��Ϊ����</a>
                                </dd>
                            </dl>
                        </c:forEach>
                    </div>
                    <div id="recent-note">
                        <h4>��������</h4>
                        <c:forEach items="${requestScope.urnote}" var="urnote">
                            <dl>
                                <dt><a href="home.do?cid=${urnote['vid']}" target="_blank">${urnote.vname}</a></dt>
                                <dd class="img">
                                    <img src="img/Azul.gif" width="40" height="40" alt="img here" />
                                </dd>
                                <dd>${urnote.date}</dd>
                                <dd>${urnote.content}</dd>
                            </dl>
                        </c:forEach>
                    </div>
                </div>
                <div id="main-left-right">
                    <div id="home-mood">
                        <div id="user"><img src="img/Azul.gif" width="40" height="40" alt="${sessionScope.user["username"]}" /></div>
                        <div id="home-write-mood">
                            <html:form action="moodMrg.do?cid=${param.cid}&&method=add" method="post">
                                <html:textarea property="moodcontent" rows="1" cols="40" value=""/>
                                <html:submit value="д����" />
                            </html:form>
                            <p />��ǰ����:"${requestScope.currentmood.content}"&nbsp;${requestScope.currentmood.date}
                        </div>
                    </div>
                    <div id="recent-log">
                        <h4>������־</h4>
                        <c:forEach items="${requestScope.user_recent_news}" var="recent_news">
                            <p>[${recent_news.type}]<a href="displaynews.do?newsID=${recent_news.newsID}">${recent_news.name}</a>
                                &nbsp;&nbsp;������${recent_news.date}&nbsp;&nbsp;
                                (�Ķ�${recent_news["browser_num"]}&nbsp;����${recent_news["comment_num"]})
                            </p>
                        </c:forEach>
                    </div>
                    <div id="dynamic-message">
                        <h4>��������</h4>
                        <c:forEach items="${requestScope.urnote}" var="urnote">
                            <p /><a href="home.do?cid=${vrnote['vid']}" target="_blank">${urnote.vname}</a>&nbsp;&nbsp;${urnote.date}
                            <p />${urnote.content}
                        </c:forEach>
                    </div>
                </div>
            </div>
            <div id="sidebar-right">
                <div id="home-message">
                    <h4>������</h4>
                    <p><a href="friend.do?cid=${param.cid}">��������(${requestScope.pfriendsnum})</a></p>
                    <p><a href="friend.do?cid=${param.cid}">���ظ�(${requestScope.wfriendsnum})</a></p>
                </div>
                <div id="home-track">
                    <h4>���ʼ�¼</h4>
                    <a href="#" onclick="showHomeTrack()">����ÿ�</a>
                    <a href="#" onclick="showMyHomeTrack()">�ҵ��㼣</a>
                    <div id="home-friend-track" class="show">
                        <c:forEach items="${requestScope.utrackList}" var="utrack">
                            <dl>
                                <dt><a href="home.do?cid=${utrack['cid']}" target="_blank">${utrack["username"]}</a>
                                </dt>
                                <dd class="img">
                                    <img src="img/Azul.gif" width="40" height="40" alt="img here" />
                                </dd>
                                <dd> ${utrack["visittime"]}</dd>
                                <dd>
                                    <c:forEach items="${sessionScope.nfriends}" var="nmyfriend">
                                        <c:if test="${nmyfriend.cid == utrack.cid}">
                                            <a href="addfriend.do?cid=${utrack['cid']}&&method=add">��Ϊ����</a>
                                        </c:if>
                                    </c:forEach>
                                </dd>
                            </dl>
                        </c:forEach>
                    </div>
                    <div id="home-my-track" class="hide">
                        <c:forEach items="${requestScope.mytrack}" var="mytrack">
                            <dl>
                                <dt>
                                    <a href="home.do?cid=${mytrack['cid']}" target="_blank">${mytrack["username"]}</a>
                                </dt>
                                <dd class="img">
                                    <img src="img/Azul.gif" width="40" height="40" alt="img here" />
                                </dd>
                                <dd>${mytrack["visittime"]}</dd>
                                <dd>
                                    <c:forEach items="${sessionScope.nfriends}" var="nmyfriend">
                                        <c:if test="${nmyfriend.cid == mytrack.cid}">
                                            &nbsp;&nbsp;<a href="managerfriend.do?cid=${mytrack['cid']}&&method=add">��Ϊ����</a>
                                        </c:if>
                                    </c:forEach>
                                </dd>
                            </dl>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <div id="clear"></div>
            <div id="footer">
                <jsp:include page="/foot.jsp" />
            </div>
        </div>
    </body>
</html>