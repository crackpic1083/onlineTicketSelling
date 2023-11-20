<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="DateTimeHelper" class="util.DateTimeHelper"/>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Search Results - FlyAway Airlines</title>
        <link rel = "shortcut icon" type = "image/png" href = "img/favicon.png" >
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <style>
            body,h1,h2,h3,h4,h5,h6 {
                font-family: "Raleway", sans-serif
            }
            body, html {
                height: 100%;
                line-height: 1.8;
                background-color: #6D8EB9;
            }
            .bgimg-1 {
                background-position: center;
                background-size: cover;
                background-image: url("img/plane.png");
                min-height: 100%;
            }
            .w3-bar .w3-button {
                padding: 16px;
            }

        </style>
    </head>

    <body>
        <div class="w3-bar w3-white w3-card" id="myNavbar">
            <a href="${pageContext.request.contextPath}" class="w3-bar-item w3-button w3-wide"><img src="img/favicon.png" class="img-fluid" alt="image" width="18%">  FlyAway</a>
            <div class="w3-right w3-hide-small">
                <a href="${pageContext.request.contextPath}/login" class="w3-bar-item w3-button"><i class="fa fa-gear"></i> Login</a>
                <a href="${pageContext.request.contextPath}/search" class="w3-bar-item w3-button"><i class="fa fa-search"></i> Search Flight</a>  
                <a href="${pageContext.request.contextPath}/cart" class="w3-bar-item w3-button"><i class="fa fa-shopping-cart"></i>Cart</a>
            </div>
        </div>
        <div class="container">
            <h2 align="center"><span><strong>Flight Schedule</strong></span></h2>
            <p align="center"><span><strong>
                        Showing available flights <br>


                        </p>
                        <table class="table table-hover table-striped">
                            <thead>
                                <tr>
                                    <th scope="col">Flight</th>
                                    <th scope="col">Aircraft Name</th>
                                    <th scope="col">Arrival</th>
                                    <th scope="col">Departure</th>
                                    <th scope="col">Arrival Time</th>
                                    <th scope="col">Departure Time</th>
                                    <th scope="col">Available Ticket</th>
                                    <th scope="col">Ticket Price</th>
                                    <th scope="col">Action</th>
                                </tr>
                            </thead>
                            <tbody>
                            <br/><br/>
                            <!--for(Flight f:flight)-->  
                            <c:forEach items="${flight}" var="f" varStatus="index">
                                <c:if test="${DateTimeHelper.compare(from,f.arrivalTime) eq 0}">
                                    <tr bgcolor="#F5F5F5">
                                        <td scope="col">${f.flightName}</td>
                                        <td scope="col">${f.aircraftID.aircraftName}</td>
                                        <td scope="col">${f.arrivalCity}</td>
                                        <td scope="col">${f.departureCity}</td>
                                        <td scope="col">${DateTimeHelper.getDayNameofWeek(f.arrivalTime)}<br/><fmt:formatDate value="${f.arrivalTime}" pattern="dd-MM-yyyy 'at' HH:mm"/> </td>  
                                        <td scope="col">${DateTimeHelper.getDayNameofWeek(f.departureTime)}<br/><fmt:formatDate value="${f.departureTime}" pattern="dd-MM-yyyy 'at' HH:mm"/></td>
                                        <td scope="col">${f.availableTicket}</td>
                                        <td scope="col">${f.price}</td>
                                        <c:if test="${DateTimeHelper.compare1(today,f.arrivalTime) le 0}">
                                            <td scope="col" class="btn btn-success"><a href="book?f=${f.flightID}">Book This Flight</a></td>
                                        </c:if>
                                    </tr>
                                </c:if>
                            </c:forEach>
                            </tbody>
                        </table>
                        </div>
                        <div class="footer" align="center">
                            FlyAway by Truong Thi Thanh Hoa
                        </div>
                        </body>
                        </html>