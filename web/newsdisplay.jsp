<%-- 
    Document   : newsdisplay
    Created on : 2010-3-12, 10:33:12
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
        <title>�鿴��־</title>
    </head>
    <body>
        <div id="container">
            <div id="header">
                <jsp:include page="/bloghead.jsp?cid=${requestScope.news['cid']}" />
            </div>
            <div id="content">
                <div id="news-display">
                    <h4>�鿴��־
                    <a class="return" href="log.do?cid=${requestScope.news['cid']}">�����б�</a>
                    </h4>
                    <p>${requestScope.news["name"]}
                        <c:if test="${requestScope.news['cid'] == sessionScope.user['cid']}">
                            <a class="edit" href="deletenews.do?cid=${requestScope.news.cid}&&newsID=${requestScope.news['newsID']}">ɾ��</a>
                            <a class="edit" href="premotifynews.do?cid=${requestScope.news.cid}&&newsID=${requestScope.news['newsID']}">�޸�</a>
                        </c:if>
                    </p>
                    <p id="news-info">������${requestScope.news.date}&nbsp;&nbsp;${requestScope.news.type}
                        �Ķ�${requestScope.news.browser_num}����${requestScope.news.comment_num}
                    </p>
                    <p id="content">${requestScope.news.content}</p>
                </div>
                <div id="comment">
                    <h4>��ǰ����</h4>
                    <p />����${requestScope["comment_num"]}������
                    <c:forEach items="${requestScope.commentList}" var="comment" varStatus="currentVarStatus">
                        <p>[${currentVarStatus.count}¥]&nbsp;
                            <c:if test="${comment.cid != 0}">
                                <a href="home.do?cid=${comment.cid}" target="_blank">${comment["username"]}</a>
                            </c:if>
                            &nbsp;${comment["date"]}
                            <c:if test="${requestScope.news['cid'] == sessionScope.user['cid']}">
                                <a class="edit" href="deletecomment.do?newsID=${requestScope.news['newsID']}&&commentID=${comment['commentID']}">ɾ��</a>
                            </c:if>
                        </p>
                        <p class="comment-content"/>"${comment["comment"]}"
                    </c:forEach>
                </div>
                <div id="write-comment">
                    <h4>����</h4>
                    <html:form action="addcomment.do?newsID=${requestScope.news['newsID']}">
                        <html:textarea property="comment" rows="2" cols="100" value="" />
                        <c:if test="${sessionScope.user == null}">
                            <p />��������:<html:text property="username" value="" />
                        </c:if>
                        <c:if test="${sessionScope.user != null}">
                            <p />�û���:<html:text property="username" value="${sessionScope.user['username']}" />
                        </c:if>
                        <p /><html:submit value="����" />
                    </html:form>
                </div>
                <div id="clear"></div>
                <div id="footer">
                    <jsp:include page="/foot.jsp" />
                </div>
            </div>
        </div>
    </body>
</html:html>