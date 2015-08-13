<%-- 
    Document   : newstype
    Created on : 2010-4-26, 1:33:47
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
        <title>日志分类</title>
    </head>
    <body>
        <div id="container">
            <div id="header">
                <jsp:include page="/bloghead.jsp?cid=${param.cid}" />
            </div>
            <div id="main-left">
                <div id="mrg-news-type-headline">
                    <h4>管理分类</h4>
                </div>
                <div id="edit-news-type">
                    <p>个人日志（默认分类，不支持修改或删除）</p>
                    <c:forEach items="${requestScope.typeList}" var="type">
                        <c:if test="${type.type != '个人日志'}">
                            <div id="pretype${type.tid}" class="show-edit-type">
                                ${type.type}<a href="newstypeMrg.do?tid=${type.tid}&&cid=${param.cid}&&method=delete">删除</a>
                                <a name="type${type.tid}" href="#" onclick="showEditType(this.name)">编辑</a>
                            </div>
                            <div id="type${type.tid}" class="hide-edit-type" >
                                <form name="form${type.tid}" action="newstypeMrg.do?tid=${type.tid}&&cid=${param.cid}&&method=edit" method="post">
                                    <input type="text" name="edittype" value="${type.type}"/>
                                    <input name="form${type.tid}" type="submit" value="确定" />
                                    <a id="cancel-edit" name ="type${type.tid}" href="#" onclick="cancelEditType(this.name)">取消</a>
                                </form>
                            </div>
                        </c:if>
                    </c:forEach>
                </div>
                <div id="add-news-type" >
                    <div id="show-add-news-type" class="hide-add-type">
                        <form name="addform" action="newstypeMrg.do?cid=${param.cid}&&method=add" method="post">
                            <input type="text" name="type" />
                            <input type="submit" value="确定" />
                            <a href="#" onclick="cancelAddType();">取消</a>
                        </form>
                    </div>
                    <p /><a href="#" onclick="showAddType();">添加</a>
                    <a class="return" href="log.do?cid=${param.cid}">返回列表</a>
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
