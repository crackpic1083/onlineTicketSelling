<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="DateTimeHelper" class="util.DateTimeHelper"/>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Past Bookings - FlyAway Airlines</title>
        <link rel = "shortcut icon" type = "image/png" href = "img/favicon.png" >
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
            .container1{
                width: 90%;
                height: 90%;
                margin-left: 77px;
            }
            .btn-custom1 {
                background-color: orange; /* Màu sắc tùy chỉnh, ví dụ: đỏ (#ff0000) */
                color: whitesmoke; /* Màu chữ tùy chỉnh, ví dụ: trắng (#ffffff) */
            }
            .btn-custom2 {
                background-color: #00CD66; /* Màu sắc tùy chỉnh, ví dụ: đỏ (#ff0000) */
                color: whitesmoke; /* Màu chữ tùy chỉnh, ví dụ: trắng (#ffffff) */
            }
            .btn-custom3 {
                background-color: #CD4F39; /* Màu sắc tùy chỉnh, ví dụ: đỏ (#ff0000) */
                color: whitesmoke; /* Màu chữ tùy chỉnh, ví dụ: trắng (#ffffff) */
            }
            .btn-custom4 {
                background-color:#555555; /* Màu sắc tùy chỉnh, ví dụ: đỏ (#ff0000) */
                color: whitesmoke; /* Màu chữ tùy chỉnh, ví dụ: trắng (#ffffff) */
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
        <div class="container1">
            <h2 align="center"><span><strong>Past Bookings</strong></span></h2>
            <br>
            <p align="center"><span><strong>Showing all bookings</strong></span></p><br>
            <form action="cart" method="post">
                <div style="display: flex; justify-content: center;">
                    <button class="btn btn-custom1" type="submit" name="mod" value="1">Pending</button>
                    <button class="btn btn-custom2" type="submit" name="mod" value="2">Approved</button>
                    <button class="btn btn-custom3" type="submit" name="mod" value="3">Cancel</button>
                    <button class="btn btn-custom4" type="submit" name="mod" value="4">Rejected</button>
                </div>





                <div class="table-responsive">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Flight Name</th>
                                <th>Ticket Name</th>
                                <th>Aircraft Name</th>
                                <th>Arrival</th>
                                <th>Departure</th>
                                <th>Arrival Time</th>
                                <th>Departure Time</th>
                                <th>Ticket Price</th>
                                <th>Seat Name</th>
                                <th>Booking Date</th>
                                <th>Passenger Name</th>
                                <th>Passenger Email</th>
                                <th>Passenger Phone</th>
                                <th>Status</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                        <br/><br/>
                        <c:forEach items="${cart}" var="c">
                            <tr bgcolor="#F5F5F5">
                                <td scope="col">${c.fid.flightName}</td>
                                <td scope="col">${c.tid.ticketName}</td>
                                <td scope="col">${c.aid.aircraftName}</td>
                                <td scope="col">${c.fid.arrivalCity}</td>
                                <td scope="col">${c.fid.departureCity}</td>
                                <td scope="col">${DateTimeHelper.getDayNameofWeek(c.fid.arrivalTime)}<br/><fmt:formatDate value="${c.fid.arrivalTime}" pattern="dd-MM-yyyy 'at' HH:mm"/> </td>  
                                <td scope="col">${DateTimeHelper.getDayNameofWeek(c.fid.departureTime)}<br/><fmt:formatDate value="${c.fid.departureTime}" pattern="dd-MM-yyyy 'at' HH:mm"/></td>
                                <td scope="col">${c.tid.price}</td>
                                <td scope="col">${c.tid.seatName}</td>
                                <td scope="col">${DateTimeHelper.getDayNameofWeek(c.bookingDate)}<br/><fmt:formatDate value="${c.bookingDate}" pattern="dd-MM-yyyy 'at' HH:mm"/> </td>  
                                <td scope="col" name="name">${c.name}</td>
                                <td scope="col" name="email">${c.email}</td>
                                <td scope="col" name="phone">${c.phone}</td>
                                <c:if test="${c.status eq 1}">
                                    <td>Pending</td>
                                </c:if>
                                <c:if test="${c.status eq 2}">
                                    <td>Approved</td>
                                </c:if>
                                <c:if test="${c.status eq 3}">
                                    <td>Cancel</td>
                                </c:if>
                                <c:if test="${c.status eq 4}">
                                    <td>Rejected</td>
                                </c:if>
                                <td>
                                    <c:if test="${c.status eq 1}">
                                        <button class="btn btn-primary" name="mod" value="5">Update</button>
                                        <button class="btn btn-danger" name="mod" value="6">Cancel</button>
                                    </c:if>
                                </td>
                            </tr>
                            <input type="hidden" name="id" value="${c.bookingID}">
                            <input type="hidden" name="tid" value="${c.tid.ticketID}">
                            <input type="hidden" name="fid" value="${c.fid.flightID}">
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </form>
        </div>

    </body>
</html>

<!-- 1. Xong front end gi? hàng (gi?ng search result thôi nh?ng tên các c?t khác: có tên vé, tên chuy?n bay,
  tên máy bay ??a ?i?m bay ?i, bay ??n, tgian ?i, ??n, giá vé, tên ghé, tên khách hàng, email khách hàng, s? ?i?n tho?i, 
  tr?ng thái ??n ??n (dài quá màn hình thì cho thanh cu?n kéo sang ngang) và Action. C?t action có 2 nút Update và Delete
2. Code back end gi? hàng, show booking c?a khách hàng ?ã ??ng nh?p
3. Code giao di?n View Booking c?a admin, t??ng t? nh? Cart, khác m?i action có 2 nút là Confirm và Reject
4. Code back end show bookinh c?a admin v?i view t?t c? booking c?a t?t c? khách hàng -->