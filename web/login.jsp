<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel = "shortcut icon" type = "image/png" href = "img/favicon.png" >
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Login FlyAway Airlines</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/style/global.css">
        <link rel = "shortcut icon" type = "image/png" href = "img/favicon.png">
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
                background-image: url(img/plane.png);
                min-height: 100%;
            }
            .w3-bar .w3-button {
                padding: 16px;

            }
            .login{
                margin-top: 60px;
            }
            
        </style>
        <%
                String notice= "";
                if(request.getAttribute("notice")!=null)
                    notice = (String)request.getAttribute("notice");
        %>
    </head>
    <body>
        <div class="w3-bar w3-white w3-card" id="myNavbar">
            <a href="${pageContext.request.contextPath}" class="w3-bar-item w3-button w3-wide"><img src="img/favicon.png" class="img-fluid" alt="image" width="18%">  FlyAway</a>
            <div class="w3-right w3-hide-small">
                <a href="${pageContext.request.contextPath}/register" class="w3-bar-item w3-button"><i class="fa fa-gear"></i> Sign Up</a>
            </div>
        </div>
        <section class="container-fluid bg">
            <section class="row justify-content-center">
                <div class="row">
                    <div class="col">
                        <h1 align="center" style="color:whitesmoke;" class="login"><b>Login</b></h1>
                        <br>
                    </div>
                </divc>
                <section class="col-sm-6 col-md-3">
                    <form action="login" method="post" class="form-container">
                        <div class="form-group mb-2">
                            <label for="inputUsername" class="sr-only">Username</label>
                            <input type="text" name="username" class="form-control"   placeholder="Enter Username">

                        </div>
                        <div class="form-group mx-sm-3 mb-2">
                            <label for="inputPassword" class="sr-only">Password</label>
                            <input type="password" name="password" class="form-control" placeholder=" Enter Password">
                            <br><p style="color: red"><b><%=notice%></b></p>
                        </div>
                        <button type="submit" value="Submit" class="btn btn-block custom-btn">Login</button>
                    </form>
                </section>
            </section>
        </section>
        <div class="footer" align="center">
            FlyAway by Truong Thi Thanh Hoa
        </div>
    </body>
</html>
