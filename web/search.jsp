<%-- 
    Document   : search
    Created on : Feb 2, 2024, 12:45:38 PM
    Author     : baodu
--%>

<%@page import="duytb.users.UsersDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <font color="blue">
        welcome,${sessionScope.USER_INFO.fullname};
        </font>
        <form action="DispatchServlet">
            search valued <input type="text" name="txtSearchValue" 
                                 value="${param.txtSearchValue}"/> <br/>
            <input type="submit" name="btnAction" value="Search" />
        </form>
        <br/>
        <c:set var="searchedValue" value="${param.txtSearchValue}"/>
        <c:if test="${not empty searchedValue}">
            <c:set var="result" value="${requestScope.SEARCH_RESULT}"/>
            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Name</th>
                            <th>Pass</th>
                            <th>Full Name</th>
                            <th>Role</th>
                            <th>delete</th>
                            
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${result}" varStatus="counter">
                        <form action="DispatchServlet" method="POST">
                            <tr>
                                <td>
                                    ${counter.count}
                                </td>
                                <td>
                                    ${dto.username}
                                    <input type="hidden" name="txtUsername" 
                                           value="${dto.username}" />
                                </td>
                                <td>
                                    ${dto.password}
                                    <input type="hidden" name="txtPassword" 
                                           value="${dto.password}" />
                                </td>
                                <td>
                                    ${dto.fullname}
                                </td>
                                <td>
                                    ${dto.role}
                                    <input type="checkbox" name="chkAdmin" value="ON"
                                           <c:if test="${dto.role}">
                                               checked="checked"
                                           </c:if>
                                           />
                                </td>
                                <td>
                                    <c:url var="deleteLink" value="DispatchServlet">
                                        <c:param name="btnAction" value="delete"/>
                                        <c:param name="pk" value="${dto.username}"/>
                                        <c:param name="lastSearchValue" value="${param.txtSearchValue}"/>
                                    </c:url>
                                    <a href="${deleteLink}">Delete</a>
                                </td>
                                <td>
                                    <input type="submit" value="Update" name="btnAction" />
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                </tbody>
            </table>

        </c:if>
        <c:if test="${empty result}">
            No reco is math!
        </c:if>
    </c:if>
    <%-- <% 
        Cookie[] cookie = request.getCookies();
        if (cookie != null){
            Cookie lastCk = cookie[cookie.length - 1];
            String username = lastCk.getName();
        
        %> 
            <font color="blue">
            welcome, <%= username %>
            </font>
    <%
        }
    %>
    <h1> Welcome to DB servlet</h1>
    <form action="DispatchServlet">
        search valued <input type="text" name="txtSearchValue" value="<%= request.getParameter("txtSearchValue")%>"/> <br/>
        <input type="submit" name="btnAction" value="Search" />
    </form>

        <br/>
        <%
//            lay lai gia tri ngta search
            String searchValue = request.getParameter("txtSearchValue");
            /*
            có khả năng tphan của page aka form chưa được kích hoạt nên server
            servlet obj chưa có nên có thể null != với bên servlet server
            
             */
            if (searchValue != null) {
                // nó đc đặt ở đâu, kiểu gì
                // sau đó attribute sẽ trả ra kiểu serial obj, nên phải ép kiểu lại
                List<UsersDTO> result
                        = (List<UsersDTO>) request.getAttribute("SEARCH_RESULT");
                if (result != null) { // search has result
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>username</th>
                    <th>password</th>
                    <th>full name</th>
                    <th>role</th>
                    <th>delete</th>
                    <th>update</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 0;
                    for (UsersDTO dto : result) {
                        String urlRewriting = "DispatchServlet"
                                + "?btnAction=delete"
                                + "&pk=" + dto.getUsername()
                                + "&lastSearchValue=" + request.getParameter("txtSearchValue");
                %>
            <form action="DispatchServlet" method="POST">
            
                <tr>
                    <td>
                        <%= ++count%>
                        .</td>
                    <td>
                        <%= dto.getUsername()%>
                        <input type="hidden" name="txtUsername" 
                               value="<%= dto.getUsername()%>" />
                    </td>
                    <td>
                        <input type="text" name="txtPassword" 
                               value="<%= dto.getPassword()%>" />
                    </td>
                    <td>
                        <%= dto.getFullname()%>
                    </td>
                    <td>
                        <input type="checkbox" name="chkIsAdmin" value="ON" 
                               <%
                                   if (dto.isRole()) {
                               %>
                               checked="checked"
                               <%
                                   }
                               %>
                               />
                    </td>
                    <td>
                        <a href="<%= urlRewriting%>">Delete</a>
                    </td>
                    <td>
                        <input type="submit" value="update" name="btnAction" />
                        <input type="hidden" name="lastSearchValue" 
                               value="<%= request.getParameter("txtSearchValue") %>" />
                    </td>
                </tr>
            </form> 
                <%
                    } // get each dto to process
                %>
            </tbody>
        </table>

        <%
        } else { // if not
        %> 
        <h2>
            <font color="blue">
            No record matched
            </font>
        </h2>
        <%
                }
            }//end search
%>
    --%>    
</body>
</html>
