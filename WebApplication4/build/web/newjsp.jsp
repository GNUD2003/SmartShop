<%-- 
    Document   : newjsp
    Created on : Jan 20, 2024, 12:53:58 PM
    Author     : PCASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <table id="plantsTable">
                                    <thead>
                                        <tr>
                                            <th>Id</th>
                                            <th>Name</th>
                                            <th style="width: 100px">Image</th>
                                            <th>Price</th>
                                            <th>Description</th>
                                            <th>Status</th>
                                            <th>Category</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tfoot>
                                        <tr>
                                            <th>Id</th>
                                            <th>Name</th>
                                            <th style="width: 100px">Image</th>
                                            <th>Price</th>
                                            <th>Description</th>
                                            <th>Status</th>
                                            <th>Category</th>
                                            <th>Action</th>
                                        </tr>
                                    </tfoot>
                                    <tbody>
                                        <c:forEach items="${requestScope.listPlants}" var="LP">
                                            <tr>
                                                <td>${LP.product_id}</td>
                                                <td>${LP.product_name}</td>
                                                <td style="width: 100px"><img src="${LP.imgPath}" style="width: 50%;"></td>
                                                <td>$${LP.price}</td>
                                                <td>${LP.description}</td>
                                                <td>${LP.quantity}</td>
                                                <td>
                                                    ${LP.brand_id}
                                                </td>
                                               

                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
    </body>
</html>
