<%-- 
    Document   : viewCart
    Created on : Feb 27, 2024, 2:35:23 PM
    Author     : baodu
--%>

<%@page import="java.util.Map"%>
<%@page import="duytb.cart.CartObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Cart</title>
    </head>
    <body>
        <h1>CARC</h1>
        <%
            //1. cust goes to his/her cart
            if (session != null){
                //2. cust gets cart
                CartObject cart = (CartObject)session.getAttribute("CART");
                if (cart != null){
                    //3. cust gets items
                    Map<String, Integer> items = cart.getItems();
                    if (items != null){
                        //4. show all
                        %>
                        <form action="DispatchServlet">
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>NO.</th>
                                    <th>NAME.</th>
                                    <th>QUANTITY</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% 
                                int count = 0;
                                for (String key : items.keySet()){
                                    %>
                                    <tr>
                                        <td>
                                            <%= ++count %>
                                        .</td>
                                        <td>
                                            <%= key %>
                                        </td>
                                        <td>
                                            <%= items.get(key) %>
                                        </td>
                                        <td>
                                            <input type="checkbox" name="chkItem" 
                                                   value="<%= key %>" />
                                        </td>
                                    </tr>
                                <%
                                }// duyet map
                                %>
<!--                                merge 3 cot-->
                                <tr>
                                    <td colspan="3">
                                        <a href="product.html">Go buy more</a>
                                    </td>
                                    <td>
                                        <input type="submit" 
                                               value="Remove selected itmes" 
                                               name="btnAction" />
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        </form>
        <%
                        return;
                    }
                }
            }//session exist
        %>
        <h2>
            <font color="red">
            Ko co CARC
            </font>
        </h2>
            
    </body>
</html>
