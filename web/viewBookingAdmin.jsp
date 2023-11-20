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
        </style>
    </head>

    <body>
        <div class="w3-bar w3-white w3-card" id="myNavbar">
            <a href="${pageContext.request.contextPath}" class="w3-bar-item w3-button w3-wide"><img src="img/favicon.png" class="img-fluid" alt="image" width="18%">  FlyAway</a>
            <div class="w3-right w3-hide-small">
                <a href="${pageContext.request.contextPath}/checkcart" class="w3-bar-item w3-button"><i class="fa fa-shopping-cart"></i> Check Order</a>  
                <a href="${pageContext.request.contextPath}/adminviewschedule" class="w3-bar-item w3-button"><i class="fa fa-search"></i> Search Flight</a>  
                <a href="${pageContext.request.contextPath}/addflight" class="w3-bar-item w3-button"><i class="fa fa-plane"></i> Add Flight</a>  
            </div>
        </div>
        <div class="container">
            <h2 align="center"><span><strong>Past Bookings</strong></span></h2>
            <br>
            <p align="center"><span><strong>Showing all bookings</strong></span></p><br>
        </div>
        <div class="container">
            <h2 align="center"><span><strong>Flight Schedule</strong></span></h2>
            <p align="center"><span><strong>
                        Showing available flights <br>


                        </p>
                        <table class="table table-hover table-striped">
                            <thead>
                                <tr>
                                    <th scope="col">Ticket Name</th>
                                    <th scope="col">Flight Name</th>
                                    <th scope="col">Aircraft Name</th>
                                    <th scope="col">Arrival</th>
                                    <th scope="col">Departure</th>
                                    <th scope="col">Arrival Time</th>
                                    <th scope="col">Departure Time</th>
                                    <th scope="col">Ticket Price</th>
                                    <th scope="col">Seat Name</th>
                                    <th scope="col">Passenger Name</th>
                                    <th scope="col">Passenger Email</th>
                                    <th scope="col">Passenger Phone</th>
                                    <th scope="col">Status</th>
                                    <th scope="col" colspan="2">Action</th>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td>
                                        <button class="btn btn-primary">Confirm</button>
                                        <button class="btn btn-danger">Reject</button>
                                    </td>
                                </tr>
                            </thead>
                            <tbody>
                            <br/><br/>
                            <!--for(Flight f:flight)-->  
                            
                            </tbody>
                        </table>
                        </div>

                        </body>
                        </html>

                        <!-- 1. Xong front end gi? hàng (gi?ng search result thôi nh?ng tên các c?t khác: có tên vé, tên chuy?n bay,
                          tên máy bay ??a ?i?m bay ?i, bay ??n, tgian ?i, ??n, giá vé, tên ghé, tên khách hàng, email khách hàng, s? ?i?n tho?i, 
                          tr?ng thái ??n ??n (dài quá màn hình thì cho thanh cu?n kéo sang ngang) và Action. C?t action có 2 nút Update và Delete
                        2. Code back end gi? hàng, show booking c?a khách hàng ?ã ??ng nh?p
                        3. Code giao di?n View Booking c?a admin, t??ng t? nh? Cart, khác m?i action có 2 nút là Confirm và Reject
                        4. Code back end show bookinh c?a admin v?i view t?t c? booking c?a t?t c? khách hàng -->