<%-- 
    Document   : personalinfo
    Created on : 2010-4-12, 12:24:55
    Author     : user
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
        <title>���˵�</title>
    </head>
    <body>
        <div id="container">
            <div id="header">
                <jsp:include page="/bloghead.jsp?cid=${param.cid}" />
            </div>
            <div id="sidebar-left">
                <div id="info-nav">
                    <h4>���˵�</h4>
                    <p /><a href="#" onclick="showBasicInfo()">��������</a>
                    <p /><a href="#" onclick="showBlogInfo()">�ռ�����</a>
                    <p /><a href="#" onclick="showPreviewInfo()">Ԥ��</a>
                </div>
            </div>
            <div id="main-right">
                <div id="basic-info" class="show">
                    <h4>��������</h4>
                    <html:form action="infoMrg.do?cid=${param.cid}&&method=basic">
                        <p />���֣�<html:text property="username" value="${sessionScope.user['username']}" />
                        <p />�Ա�<html:text property="sex" value="${sessionScope.user['sex']}" />
                        <p />���գ�<html:text property="birthday" value="${sessionScope.user['birthday']}" />
                        <p />�������䣺<html:text property="email" value="${sessionScope.user['email']}" />
                        <p />���磺<html:text property="hometown" value="${sessionScope.user['hometown']}" />
                        <p />�־ӵأ�<html:text property="location" value="${sessionScope.user['location']}" />
                        <p />ѧУ��<html:text property="university" value="${sessionScope.user['university']}" />
                        <p />רҵ��<html:text property="profession" value="${sessionScope.user['profession']}" />
                        <p />��˾��<html:text property="company" value="${sessionScope.user['company']}" />
                        <p />ְҵ��<html:text property="occupation" value="${sessionScope.user['occupation']}" />
                        <p /><html:submit value="�ύ" />
                    </html:form>
                </div>
                <div id="blog-info" class="hide">
                    <h4>�ռ�����</h4>
                    <html:form action="infoMrg.do?cid=${param.cid}&&method=blog">
                        <p />�ռ����ƣ�<html:text property="blogname" value="${sessionScope.user['blogname']}" />
                        <p />�ռ�˵����<html:text property="blogshow" value="${sessionScope.user['blogshow']}" />
                        <p />ǩ������
                        <p /><html:textarea property="signature" value="${sessionScope.user['signature']}" rows="3" cols="30"/>
                        <p /><html:submit value="�ύ" />
                    </html:form>
                </div>
                <div id="preview-info" class="hide">
                    <h4>���˵�Ԥ��</h4>
                    <p>���֣�${sessionScope.user["username"]}</p>
                    <p>�Ա�${sessionScope.user["sex"]}</p>
                    <p>���գ�${sessionScope.user["birthday"]}</p>
                    <p>�������䣺${sessionScope.user["email"]}</p>
                    <p>���磺${sessionScope.user['hometown']}</p>
                    <p>�־ӵأ�${sessionScope.user['location']}</p>
                    <p>ѧУ��${sessionScope.user['university']}</p>
                    <p>רҵ��${sessionScope.user['profession']}</p>
                    <p>��˾��${sessionScope.user['company']}</p>
                    <p>ְҵ��${sessionScope.user['occupation']}</p>
                    <p>�ռ����ƣ�${sessionScope.user['blogname']}</p>
                    <p>�ռ�˵����${sessionScope.user['blogshow']}</p>
                    <p>ǩ������${sessionScope.user['signature']}</p>
                </div>
            </div>
            <div id="clear"></div>
            <div id="footer">
                <jsp:include page="/foot.jsp" />
            </div>
        </div>
    </body>
</html>
