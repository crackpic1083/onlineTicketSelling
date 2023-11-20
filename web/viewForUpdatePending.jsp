<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Booking Details - FlyAway Airlines</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/style/global.css">
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

            .select {
                height: 250px;
                position: relative;
                overflow: hidden;
                text-align: center;
            }

            .select h1 {
                width: 60%;
                margin: 100px auto 35px auto;
            }

            .exit {
                position: relative;
                height: 90px;
                margin-top: 0px;
            }
            .exit:before, .exit:after {
                content: "EXIT";
                font-size: 14px;
                line-height: 18px;
                padding: 0px 2px 2px 2px;
                display: block;
                position: absolute;
                background: red;
                color: white;
                top: 120%;
                transform: translate(0, -50%);
                border-radius: 5px;
            }
            .exit:before {
                left: 12px;
            }
            .exit:after {
                right: 12px;
            }

            ol {
                list-style: none;
                padding: 0;
                margin: 0;
            }

            .seats {
                display: flex;
                flex-direction: row;
                flex-wrap: nowrap;
                justify-content: flex-start;
            }

            .seat {
                display: flex;
                flex: 0 0 14.28%;
                padding: 5px;
                position: relative;
            }

            .seat label {
                display: block;
                position: relative;
                width: 100%;
                text-align: center;
                font-size: 14px;
                font-weight: bolder;
                line-height: 1.5rem;
                padding: 4px 0;
                background: #5bfc60;
                border-radius: 5px;
                color: black;
            }

            .seat label:hover {
                cursor: pointer;
                box-shadow: 0 0 0px 2px green;
            }

            .seat:nth-child(3) {
                margin-right: 14%;
            }
            .seat input[type=checkbox] {
                position: absolute;
            }
            .seat input[type=checkbox]:checked + label {
                background: #656e65;
            }
            .form-container{

                top: 20px;
                left: 572px;
                background: #f8f8f8;
                border-radius: 10px;
                box-shadow: 0px 0px 10px 0px rgba(47, 47, 47, 0.85);
                width: 100%;

            }
            .plane {
                max-width: 300px;
                background: #6D8EB9;
                border-top-left-radius: 50%;
                border-top-right-radius: 50%;
                height: 500px;
            }

        </style>
    </head>

    <body>
        <div class="w3-bar w3-white w3-card" id="myNavbar">
            <a href="/index.jsp" class="w3-bar-item w3-button w3-wide"><img src="img/favicon.png" class="img-fluid" alt="image" width="18%">  FlyAway</a>
            <div class="w3-right w3-hide-small">
                <a href="${pageContext.request.contextPath}/search" class="w3-bar-item w3-button"><i class="fa fa-search"></i> Search Flight</a>  
                <a href="${pageContext.request.contextPath}/cart" class="w3-bar-item w3-button"><i class="fa fa-shopping-cart"></i>Cart</a>
            </div>
        </div>
        <section class="container-fluid bg">
            <section class="row justify-content-center">
                <div class="row">
                    <div class="col">
                        <h1 align="center" style="color:black;"><b>Booking Details</b></h1>
                        <p align="center" style="color:black;"><span><strong>Enter Passenger Details</strong></span></p><br>
                    </div>
                </div>

                <form action="cart" method="post" class="form-container" style="margin-left: 380px; ">
                    <div class="col-sm-3 col-md-3">

                        <div class="form-group">
                            <label for="pname">Passenger Name</label>
                            <input type="text" class="form-control" id="pname" name="name" value="${view.name}" placeholder="Enter name of the/any passenger(s)" required >

                        </div>
                        <div class="form-group">
                            <label for="email">Email address</label>
                            <input type="email" name="email" class="form-control" id="email" aria-describedby="emailHelp" placeholder="Enter email" value="${view.email}" required>
                            <c:if test="${not empty errorMessage1}">
                                <p class="error">${errorMessage1}</p>
                            </c:if>
                        </div>
                        <div class="form-group">
                            <label for="phone">Phone</label>
                            <input type="text" class="form-control" id="phone" name="phone" value="${view.phone}" placeholder="Enter phone number"required>
                            <c:if test="${not empty errorMessage2}">
                                <p class="error">${errorMessage2}</p>
                            </c:if>
                        </div>

                        <div class="footer" style="margin-top: 50px" align="center">
                            FlyAway by Truong Thi Thanh Hoa
                        </div>

                    </div>
                    <div class="col-sm-3 col-md-3">
                        <label>&emsp;&emsp;&emsp;&emsp;&emsp;&nbsp;&nbsp;&nbsp;Please select a seat</label>
                        <div class="form-group plane" style="margin-top: 20px">

                            <div class="exit"></div>
                            <table style="margin-left: 30px; margin-top: 20px;">
                                <c:forEach items="${seats}" var="s" varStatus="status">
                                    <c:if test="${status.index % 6 == 3}">
                                        <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                                        </c:if>

                                    <c:if test="${status.index % 6 == 0}">
                                        <tr>
                                        </c:if>
                                        <td>

                                            <c:forEach items="${ti}" var="t">
                                                <c:if test="${t.seatName eq s}">
                                            <li class="seat">
                                                <input type="radio" name="seat" value="${t.ticketID}" id="${s}" <c:if test="${t.ticketID eq view.tid.ticketID}">checked</c:if>/> 
                                                <label for="${s}">${s}</label>
                                            </li>
                                        </c:if>
                                    </c:forEach>

                                    </td>
                                    <c:if test="${status.index % 6 == 5 || status.last}">
                                        </tr>
                                    </c:if>
                                </c:forEach>
                            </table>
                            <div class="exit"></div>
                        </div>
                        <br>
                        <div >
                            <button type="submit" class="btn btn-success btn-block" name="mod" value="7">Update</button>
                        </div>
                    </div>
                    <input type="hidden" name="id" value="${view.bookingID}">
                </form>

            </section>
        </section>





    </body>
</html>