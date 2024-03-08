<%-- 
    Document   : register
    Created on : Mar 8, 2024, 1:38:47 PM
    Author     : baodu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div>Dang ki</div>
        <form action="DispatchServlet" method="POST">
            <c:set var="errors" value="${requestScope.CREATE_ERRORS}"/>
            Username* <input type="text" name="txtUsername" 
                             value="${param.txtUsername}" /> (6-20 chars)<br/>
            <c:if test="${not empty errors.usernameLengErr}">
                <font color="red">
                    ${errors.usernameLengErr}
                </font> <br/>
            </c:if>
            Pass* <input type="text" name="txtPass" value="" /> <br/>
            <c:if test="${not empty errors.passLengErr}">
                <font color="red">
                    ${errors.passLengErr}
                </font> <br/>
            </c:if>
            ConfirmPass* <input type="text" name="txtConfirmPass" value="" /> <br/>
            <c:if test="${not empty errors.confirmPassNotMatched}">
                <font color="red">
                    ${errors.confirmPassNotMatched}
                </font> <br/>
            </c:if>
            fullname* <input type="text" name="txtfullname" value="${param.txtfullname}" /> <br/>
            <c:if test="${not empty errors.fullnameLengErr}">
                <font color="red">
                    ${errors.fullnameLengErr}
                </font> <br/>
            </c:if>
            <input type="submit" value="Create new account" name="btnAction" />
            <input type="reset" value="Reset"/>
            
            
        </form>
    </body>
</html>
