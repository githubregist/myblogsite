<%-- 
    Document   : notes
    Created on : 2010-4-22, 12:50:14
    Author     : guohengj
    Version  : 0.1
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
        <script language="javascript" src="notespage.js" type="text/javascript">
        </script>
        <title>留言</title>
    </head>
    <body>
        <div id="container">
            <div id="header">
                <jsp:include page="/bloghead.jsp?cid=${param.cid}" />
            </div>
            <div id="content">
                <div id="note-table">
                    <h4>留言板</h4>
                    <c:if test="${param.page == null}">
                        <c:set value="1" var="current_page"/>
                    </c:if>
                    <c:if test="${param.page != null}">
                        <c:set value="${param.page}" var="current_page" />
                    </c:if>
                    <p />共${requestScope.notesnum}条留言，第${current_page}页/共${requestScope.total_page}页
                    <c:if test="${requestScope.notesnum >= 5}">
                        <a href="note.do?cid=${param.cid}&&page=${requestScope.total_page}">尾页</a>&nbsp;&nbsp;
                        <c:if test="${current_page < (requestScope.total_page)}">
                            <a href="note.do?cid=${param.cid}&&page=${current_page + 1}">下一页</a>&nbsp;&nbsp;
                        </c:if>
                        <c:if test="${current_page > 1}">
                            <a href="note.do?cid=${param.cid}&&page=${current_page - 1}">上一页</a>&nbsp;&nbsp;
                        </c:if>
                        <a href="note.do?cid=${param.cid}&&page=1">首页</a>
                    </c:if>
                    <hr>
                    <table align="center" cellspacing="0">
                        <tr>
                            <th width="20%" align="center">留言者</th>
                            <th width="80%" align="center">留言内容</th>
                        </tr>
                        <c:forEach items="${requestScope.notesList}" var="note">
                            <tr>
                                <td width="20%">
                                    <p ><a href="home.do?cid=${note['vid']}" target="_blank">${note["vname"]}</a></p>
                                    <p>${note["sex"]}&nbsp;${note["location"]}</p>
                                    <p>来自:${note["university"]}</p>
                                    <p>等级:${note["level"]}</p>
                                </td>
                                <td width="80%">
                                    <div id="note">
                                        <p />${note["date"]}
                                        <c:if test="${sessionScope.user.cid ==note.uid || sessionScope.user.cid == note.vid}">
                                            <a name="${note.nid}" href="#" onclick="replyNote(this.name)">回复</a>
                                        </c:if>
                                        <c:if test="${param.cid == sessionScope.user.cid}">
                                            <a href="noteMrg.do?nid=${note.nid}&&cid=${param.cid}&&method=delete">删除</a>
                                        </c:if>
                                        <p />${note["content"]}
                                        <div id="reply-note">
                                            <c:if test="${requestScope.renoteList != null}">
                                                <c:forEach items="${requestScope.renoteList}" var="renote">
                                                    <c:if test="${note.nid == renote.nid}">
                                                        <div class="show-reply-note">
                                                            <c:if test="${renote.vid == note.vid && renote.vid != note.uid}">
                                                                <p />${note.vname}
                                                            </c:if>
                                                            <c:if test="${renote.vid == note.uid}">
                                                                主人:
                                                            </c:if>
                                                            ${renote.date}
                                                            <c:if test="${param.cid == sessionScope.user.cid}">
                                                                <a href="renoteMrg.do?cid=${param.cid}&&rnid=${renote.rnid}&&method=delete">删除</a>
                                                            </c:if>
                                                            <c:if test="${sessionScope.user.cid ==note.uid || sessionScope.user.cid == note.vid}">
                                                                <a name="${note.nid}" href="#" onclick="replyNote(this.name)">回复</a>
                                                            </c:if>
                                                            <p />${renote.content}
                                                        </div>
                                                    </c:if>
                                                </c:forEach>
                                            </c:if>
                                            <div id="${note.nid}" class="hide-reply-note">
                                                <form name="${note.nid}" action="renoteMrg.do?nid=${note.nid}&&cid=${param.cid}&&method=add" method="post">
                                                    <textarea name="renotecontent" rows="2" cols="50"></textarea>
                                                    <p />
                                                    <input name="${note.nid}" type="submit" value="提交" onclick="confirmReNote(this.name)"/>
                                                    <a name ="${note.nid}" href="#" onclick="cancelReNote(this.name)">取消</a>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <p />共${requestScope.notesnum}条留言，第${current_page}页/共${requestScope.total_page}页
                    <c:if test="${requestScope.notesnum >= 5}">
                        <a href="note.do?cid=${param.cid}&&page=${requestScope.total_page}">尾页</a>&nbsp;&nbsp;
                        <c:if test="${current_page < (requestScope.total_page)}">
                            <a href="note.do?cid=${param.cid}&&page=${current_page + 1}">下一页</a>&nbsp;&nbsp;
                        </c:if>
                        <c:if test="${current_page > 1}">
                            <a href="note.do?cid=${param.cid}&&page=${current_page - 1}">上一页</a>&nbsp;&nbsp;
                        </c:if>
                        <a href="note.do?cid=${param.cid}&&page=1">首页</a>
                    </c:if>
                </div>

                <div id="write-note">
                    <h4>我的留言:</h4>
                    <html:form action="noteMrg.do?cid=${param.cid}&&method=add">
                        <html:textarea property="notecontent" rows="2" cols="100" value=""/>
                        <p /><html:submit value="发表" />
                    </html:form>
                </div>
            </div>
            <div id="clear"></div>
            <div id="footer">
                <jsp:include page="/foot.jsp" />
            </div>
        </div>
    </body>
</html>
