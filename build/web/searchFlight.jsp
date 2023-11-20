<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="DateTimeHelper" class="util.DateTimeHelper"/>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>FlyAway</title>
        <link rel = "shortcut icon" type = "image/png" href = "img/favicon.png" >
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/style/global.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <link href="global.css" rel="stylesheet" type="text/css"/>
        <style>
            body,h1,h2,h3,h4,h5,h6 {
                font-family: "Raleway", sans-serif
            }
            body, html {
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
            .ticketbooking{
                margin-top: 20px;
            }


        </style>
    </head>
    <body>
        <div class="w3-bar w3-white w3-card" id="myNavbar">
            <a href="${pageContext.request.contextPath}" class="w3-bar-item w3-button w3-wide"><img src="img/favicon.png" class="img-fluid" alt="image" width="18%">  FlyAway</a>
            <div class="w3-right w3-hide-small">
                <c:if test="${account eq null}">
                    <a href="${pageContext.request.contextPath}/login" class="w3-bar-item w3-button"><i class="fa fa-gear"></i> Login</a>
                </c:if>
                <a href="${pageContext.request.contextPath}/search" class="w3-bar-item w3-button"><i class="fa fa-search"></i> Search Flight</a>  
                
                <a href="${pageContext.request.contextPath}/cart" class="w3-bar-item w3-button"><i class="fa fa-shopping-cart"></i>Cart</a>
            </div>
        </div>
        <section class="container-fluid bg">
            <section class="row justify-content-center">
                <div class="row">
                    <div class="col">
                        <h1 align="center" style="color:whitesmoke;" class="ticketbooking"><b>FlyAway <br> Ticket Searching</b></h1>
                        <br>
                    </div>
                </div>
                <section class="col-sm-6 col-md-3">
                    <form action="search" class="form-container" method="post">
                        <div class="form-group">
                            <label for="date">Date</label>
                            <input type="date"  class="form-control" id="date" name="date" placeholder="Travel Date..">
                        </div>
                        <div class="form-group">
                            <label for="source">FLYING FROM</label>
                            <select class="form-control" id="city" name="arrivalCity"  aria-label=".form-select-sm" required>
                                <option value="" selected>City</option>           
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="source">FLYING TO</label>
                            <select class="form-control" id="city1" name="departureCity"  aria-label=".form-select-sm" required>
                                <option value="" selected>City</option>           
                            </select>
                        </div>
                        <br>
                        <button type="submit" class="btn btn-block custom-btn" value="Submit">Search Flights</button>
                    </form>
                </section>
            </section>
        </section>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.21.1/axios.min.js"></script>
        <script>
            var citis = document.getElementById("city");
            var Parameter = {
                url: "https://raw.githubusercontent.com/kenzouno1/DiaGioiHanhChinhVN/master/data.json",
                method: "GET",
                responseType: "application/json",
            };
            var promise = axios(Parameter);
            promise.then(function (result) {
                renderCity(result.data);
            });
            function renderCity(data) {
                for (const x of data) {
                    citis.options[citis.options.length] = new Option(x.Name, x.Name);
                }
            }
        </script>
        <script>
            var citis1 = document.getElementById("city1");
            var Parameter1 = {
                url: "https://raw.githubusercontent.com/kenzouno1/DiaGioiHanhChinhVN/master/data.json",
                method: "GET",
                responseType: "application/json",
            };
            var promise1 = axios(Parameter1);
            promise1.then(function (result) {
                renderCity1(result.data);
            });
            function renderCity1(data) {
                for (const x of data) {
                    citis1.options[citis1.options.length] = new Option(x.Name, x.Name);
                }
            }
        </script>

        <div class="footer" align="center" >
            FlyAway by Truong Thi Thanh Hoa
        </div>
    </body>
</html>