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

            .flight-name-container {
                display: flex;
                align-items: center;
                margin-top: 5px;
            }

            .vn-input {
                width: 50px;
                text-align: center;
                margin-right: 5px;
            }
            .fields {
                display: flex;
                justify-content: space-between;
                margin-bottom: 20px;
            }

            .input-field {
                flex-basis: calc(50% - 10px); /* Updated */
                margin-bottom: 20px; /* Added */
            }

            .input-field label {
                display: block;
                margin-bottom: 5px;
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
                height: 87px;
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
                left: 8px;
            }
            .exit:after {
                right: 8px;
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
                height: 640px;
            }
            .form-group {
                margin-bottom: 15px;
            }

            .error{
                color: red;
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
    
        <section class="container-fluid bg">
            <section class="row justify-content-center">
                <div class="row">
                    <div class="col">
                        <h1 align="center" style="color:black;"><b>Add Flight</b></h1>
                    </div>
                </div>

                <form action="addflight" method="post">
                    <div class="col-sm-3 col-md-3" style="margin-left: 400px; margin-top: 20px">
                        <div class="form-group">
                            <label for="flightname">Flight Name</label>
                            <div class="flight-name-container">
                                <input name="vn" class="vn-input form-control" value="VN" readonly>
                                <input type="number" id="fname" name="fname" value="${fname}" class="form-control" placeholder="Flight Name" required>
                            </div>
                            <c:if test="${not empty errorMessage1}">
                                <p class="error">${errorMessage1}</p>
                            </c:if>
                            <c:if test="${not empty errorMessage6}">
                                <p class="error">${errorMessage6}</p>
                            </c:if>

                        </div>
                        <div class="input-field">
                            <label>Aircraft Name</label>
                            <select name="arname" class="form-control" required>
                                <c:forEach items="${arlist}" var="a">
                                    <option value="${a.aircraftID}" <c:if test="${arname eq a.aircraftID}">selected="selected"</c:if>>${a.aircraftName}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="source">Arrival City</label>
                            <input type="hidden" id="selectedCity"  value="${arrcity}">
                            <select class="form-control" id="city" name="arrcity" aria-label=".form-select-sm" required>
                                <option value="" selected>City</option>           
                            </select>
                        </div>

                        <div class="input-field">
                            <label>Arrival Time</label>
                            <input type="datetime-local" class="form-control" value="${arrtime}" required name="arrtime">
                            <c:if test="${not empty errorMessage4}">
                                <p class="error">${errorMessage4}</p>
                            </c:if>
                        </div>

                        <div class="form-group">
                            <label for="source">Departure City</label>
                            <input type="hidden" id="selectedCity1"  value="${decity}">
                            <select class="form-control" id="city1" name="decity"  aria-label=".form-select-sm" required>
                                <option value="" selected>City</option>           
                            </select>
                            <c:if test="${not empty errorMessage2}">
                                <p class="error">${errorMessage2}</p>
                            </c:if>
                        </div>

                        <div class="input-field">
                            <label>Departure Time</label>
                            <input type="datetime-local" name="detime" value="${detime}" required class="form-control" >
                            <c:if test="${not empty errorMessage3}">
                                <p class="error">${errorMessage3}</p>
                            </c:if>
                        </div>



                        <div class="input-field">
                            <label>Ticket Price</label>
                            <input type="number" name="price" required class="form-control" value="${price}">

                        </div>
                        <c:if test="${not empty errorMessage7}">
                            <p class="error">${errorMessage7}</p>
                        </c:if>
                        <script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.21.1/axios.min.js"></script>
                        <script>
                            var citis = document.getElementById("city");
                            var selectedCity = document.getElementById("selectedCity").value;
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
                                if (selectedCity) {
                                    citis.value = selectedCity;

                                }
                            }
                        </script>
                        <script>
                            var citis1 = document.getElementById("city1");
                            var selectedCity1 = document.getElementById("selectedCity1").value;
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
                                if (selectedCity1) {
                                    citis1.value = selectedCity1;

                                }
                            }
                        </script>

                        <div class="footer" style="margin-top: 30px" align="center">
                            FlyAway by Truong Thi Thanh Hoa
                        </div>
                    </div>
                    <div class="col-sm-3 col-md-3" style="margin-left: 30px" >

                        <div class="form-group plane " style="margin-left: 30px;" >
                            <div class="exit"></div>
                            <table style="margin-left: 30px; margin-top: 60px;">
                                <c:forEach items="${seats}" var="s" varStatus="status">
                                    <c:if test="${status.index % 6 == 3}">
                                        <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                                        </c:if>

                                    <c:if test="${status.index % 6 == 0}">
                                        <tr>
                                        </c:if>
                                        <td>
                                    <li class="seat">
                                        <input type="checkbox" value="${s}" name="seatname${s}" id="${s}" 
                                               <c:forEach items="${tickets}" var="t" >
                                                   <c:if test="${t.seatName eq s}">
                                                       checked="checked"
                                                   </c:if>
                                               </c:forEach>                                               
                                               /> 
                                        <label for="${s}">${s}</label>
                                    </li>

                                    </td>
                                    <c:if test="${status.index % 6 == 5 || status.last}">
                                        </tr>
                                    </c:if>
                                </c:forEach>
                            </table>
                            <div class="exit"></div>
                        </div>
                        <br>
                        <c:if test="${not empty errorMessage5}">
                            <p class="error">${errorMessage5}</p>
                        </c:if>
                        <div >
                            <button type="submit" class="btn btn-success btn-block" value="Submit">Submit</button>
                        </div>
                    </div>
                </form>

            </section>
        </section>





    </body>
</html>