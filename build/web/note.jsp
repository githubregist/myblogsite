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
        <title>����</title>
    </head>
    <body>
        <div id="container">
            <div id="header">
                <jsp:include page="/bloghead.jsp?cid=${param.cid}" />
            </div>
            <div id="content">
                <div id="note-table">
                    <h4>���԰�</h4>
                    <c:if test="${param.page == null}">
                        <c:set value="1" var="current_page"/>
                    </c:if>
                    <c:if test="${param.page != null}">
                        <c:set value="${param.page}" var="current_page" />
                    </c:if>
                    <p />��${requestScope.notesnum}�����ԣ���${current_page}ҳ/��${requestScope.total_page}ҳ
                    <c:if test="${requestScope.notesnum >= 5}">
                        <a href="note.do?cid=${param.cid}&&page=${requestScope.total_page}">βҳ</a>&nbsp;&nbsp;
                        <c:if test="${current_page < (requestScope.total_page)}">
                            <a href="note.do?cid=${param.cid}&&page=${current_page + 1}">��һҳ</a>&nbsp;&nbsp;
                        </c:if>
                        <c:if test="${current_page > 1}">
                            <a href="note.do?cid=${param.cid}&&page=${current_page - 1}">��һҳ</a>&nbsp;&nbsp;
                        </c:if>
                        <a href="note.do?cid=${param.cid}&&page=1">��ҳ</a>
                    </c:if>
                    <hr>
                    <table align="center" cellspacing="0">
                        <tr>
                            <th width="20%" align="center">������</th>
                            <th width="80%" align="center">��������</th>
                        </tr>
                        <c:forEach items="${requestScope.notesList}" var="note">
                            <tr>
                                <td width="20%">
                                    <p ><a href="home.do?cid=${note['vid']}" target="_blank">${note["vname"]}</a></p>
                                    <p>${note["sex"]}&nbsp;${note["location"]}</p>
                                    <p>����:${note["university"]}</p>
                                    <p>�ȼ�:${note["level"]}</p>
                                </td>
                                <td width="80%">
                                    <div id="note">
                                        <p />${note["date"]}
                                        <c:if test="${sessionScope.user.cid ==note.uid || sessionScope.user.cid == note.vid}">
                                            <a name="${note.nid}" href="#" onclick="replyNote(this.name)">�ظ�</a>
                                        </c:if>
                                        <c:if test="${param.cid == sessionScope.user.cid}">
                                            <a href="noteMrg.do?nid=${note.nid}&&cid=${param.cid}&&method=delete">ɾ��</a>
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
                                                                ����:
                                                            </c:if>
                                                            ${renote.date}
                                                            <c:if test="${param.cid == sessionScope.user.cid}">
                                                                <a href="renoteMrg.do?cid=${param.cid}&&rnid=${renote.rnid}&&method=delete">ɾ��</a>
                                                            </c:if>
                                                            <c:if test="${sessionScope.user.cid ==note.uid || sessionScope.user.cid == note.vid}">
                                                                <a name="${note.nid}" href="#" onclick="replyNote(this.name)">�ظ�</a>
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
                                                    <input name="${note.nid}" type="submit" value="�ύ" onclick="confirmReNote(this.name)"/>
                                                    <a name ="${note.nid}" href="#" onclick="cancelReNote(this.name)">ȡ��</a>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <p />��${requestScope.notesnum}�����ԣ���${current_page}ҳ/��${requestScope.total_page}ҳ
                    <c:if test="${requestScope.notesnum >= 5}">
                        <a href="note.do?cid=${param.cid}&&page=${requestScope.total_page}">βҳ</a>&nbsp;&nbsp;
                        <c:if test="${current_page < (requestScope.total_page)}">
                            <a href="note.do?cid=${param.cid}&&page=${current_page + 1}">��һҳ</a>&nbsp;&nbsp;
                        </c:if>
                        <c:if test="${current_page > 1}">
                            <a href="note.do?cid=${param.cid}&&page=${current_page - 1}">��һҳ</a>&nbsp;&nbsp;
                        </c:if>
                        <a href="note.do?cid=${param.cid}&&page=1">��ҳ</a>
                    </c:if>
                </div>

                <div id="write-note">
                    <h4>�ҵ�����:</h4>
                    <html:form action="noteMrg.do?cid=${param.cid}&&method=add">
                        <html:textarea property="notecontent" rows="2" cols="100" value=""/>
                        <p /><html:submit value="����" />
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
