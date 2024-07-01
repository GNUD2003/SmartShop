<%-- 
    Document   : admin
    Created on : Mar 5, 2022, 6:58:11 PM
    Author     : PAsus
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <%@include file="components/adminHeadComponent.jsp" %>
        <title>Admin - Dashboard</title>
    </head>
    <body class="sb-nav-fixed">
        <!-- Admin navbar -->
        <%@include file="components/adminNavBarComponent.jsp" %>
        <div id="layoutSidenav">
            <!-- Admin Slidenav -->
            <%@include file="components/adminSlideNavComponent.jsp" %>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">Charts and Statistical</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active">Overview</li>
                        </ol>
                        <div class="row">
                            <div class="col-xl-6">
                                <div class="card mb-4">
                                    <div class="card-header">
                                        <i class="fas fa-chart-area me-1"></i>
                                        Line chart
                                    </div>
                                    <div class="card-body"><canvas id="lineChart" style="width:100%;max-width:600px"></canvas></canvas></div>
                                    <ul class="list-group">
                                        <li class="list-group-item">Top Sale Months</li>
                                            <c:forEach items="${requestScope.topSale}" var="sale">
                                            <li class="list-group-item">${sale.month} - Sale: ${sale.totalPrice}</li>
                                            </c:forEach>
                                    </ul>
                                </div>
                            </div>
                            <div class="col-xl-6">
                                <div class="card mb-4">
                                    <div class="card-header">
                                        <i class="fas fa-chart-bar me-1"></i>
                                        Pie Chart Example
                                    </div>
                                    <div class="card-body"><canvas id="myChart" style="width:100%;max-width:600px"></canvas></div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xl-6">
                                <h2>Top 5 account expenditures</h2>
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th scope="col">Id</th>
                                            <th scope="col">Name</th>
                                            <th scope="col">Expense</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${topAccountExpense}" var="ae">
                                            <tr>
                                                <td>${ae.accId}</td>
                                                <td>${ae.fullName}</td>
                                                <td>${ae.totalExpense}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>

                            <div class="col-xl-6">
                                <h2>Top 5 selling items</h2>
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th scope="col">Image</th>
                                            <th scope="col">Name</th>
                                            <th scope="col">Expense</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${topPlantSale}" var="ps">
                                            <tr>
                                                <td><img src="${ps.imgPath}" width="50px" ></td>
                                                <td>${ps.platName}</td>
                                                <td>${ps.totalQuantity}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </main>
                <!-- Footer -->
                <jsp:include page="components/adminFooter.jsp"></jsp:include>
                </div>
            </div>

            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
            <script src="js/scripts.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
            <script src="demo/chart-area-demo.js"></script>
            <script src="demo/chart-bar-demo.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
            <script src="js/datatables-simple-demo.js"></script>
            <script>
                var test = <%=request.getAttribute("categoriesSell")%>;
                var month = <%=request.getAttribute("monthSale")%>;
                console.log(month);
                var xValues = test.map(e => e.cateName);
                var yValues = test.map(e => e.total);
                console.log("2",xValues);
                console.log("4",yValues);
                var barColors = [
                    "#b91d47",
                    "#00aba9",
                    "#2b5797",
                    "#e8c3b9",
                    "#1e7145"
                ];
                
                new Chart("myChart", {
                    type: "pie",
                    data: {
                        labels: xValues,
                        datasets: [{
                                backgroundColor: barColors,
                                data: yValues
                            }]
                    },
                    options: {
                        title: {
                            display: true,
                            text: "Selling of categories"
                        }
                    }
                });
                
                const months = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12];
                const monthsValue = month.map((e) => e.totalPrice);
                console.log(monthsValue);
                new Chart("lineChart", {
                    type: "line",
                    data: {
                        labels: months,
                        datasets: [{
                                data: monthsValue,
                                borderColor: "blue",
                                fill: false
                            }]
                    },
                    options: {
                        legend: {display: false}
                    }
                });
        </script>
    </body>
</html>
