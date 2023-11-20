<%-- 
    Document   : registrationForm.jsp
    Created on : Jun 20, 2023, 6:40:28 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!DOCTYPE html>
<!--=== Coding by CodingLab | www.codinglabweb.com === -->
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!----======== CSS ======== -->
        <link rel="stylesheet" href="styleRegistration.css">

        <!----===== Iconscout CSS ===== -->
        <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">

        <title>Responsive Regisration Form </title> 
        <style>
            .select {
                font-family: 'Raleway', sans-serif;
                font-size: 14px;
                font-weight: 400;
                color: #333;
            }
            .error{
                color: red;
                font-family: 'Raleway', sans-serif;
                font-size: 14px;
                font-weight: 400;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <header>Registration</header>
            <form action="register" method="post">
                <div class="form first" style="margin-top: 10px">
                    <div class="details personal">

                        <div class="fields">
                            <div class="input-field">
                                <label>Full Name</label>
                                <input type="text" name="name" placeholder="Enter your name" value="${raw_name}" required>
                                <c:if test="${not empty errorMessage1}">
                                    <p class="error">${errorMessage1}</p>
                                </c:if>
                            </div>

                            <div class="input-field">
                                <label>Email</label>
                                <input type="text" name="email" placeholder="Enter your email" value="${raw_email}"required>
                                <c:if test="${not empty errorMessage2}">
                                    <p class="error">${errorMessage2}</p>
                                </c:if>
                            </div>

                            <div class="input-field">
                                <label>Mobile Number</label>
                                <input type="text" name="phone" placeholder="Enter mobile number" value="${raw_phone}" required>
                                <c:if test="${not empty errorMessage3}">
                                    <p class="error">${errorMessage3}</p>
                                </c:if>
                            </div>

                            <div class="input-field">
                                <label>Date of Birth</label>
                                <input type="date" name="dob" placeholder="Enter birth date" value="${raw_dob}" required>
                                <c:if test="${not empty errorMessage4}">
                                    <p class="error">${errorMessage4}</p>
                                </c:if>
                            </div>

                            <div class="input-field">
                                <label>Gender</label>
                                <select name="gender" required  >
                                    <option value="male"  <c:if test="${raw_gender eq 'male'}">selected = "selected"</c:if>>Male</option>
                                    <option value="female" <c:if test="${raw_gender eq 'female'}">selected = "selected"</c:if>>Female</option>
                                    </select>
                                </div>

                                <div class="input-field">
                                    <label>Citizen identification number</label>
                                    <input type="text" name="cccd" placeholder="Enter number" value="${raw_cccd}" required>
                                <c:if test="${not empty errorMessage6}">
                                    <p class="error">${errorMessage6}</p>
                                </c:if>
                            </div>

                            <div>
                                <label class="input-field select">Address</label>
                                <div>
                                    <select class="form-select form-select-sm mb-3 select" id="city" name="city"  aria-label=".form-select-sm" required>
                                        <option value="" selected>City</option>           
                                    </select>

                                    <select class="form-select form-select-sm mb-3 select" id="district" name="district"  aria-label=".form-select-sm" required>
                                        <option value="" selected>District</option>
                                    </select>

                                    <select class="form-select form-select-sm mb-3 select" id="ward" name="ward"  aria-label=".form-select-sm" required>
                                        <option value="" selected>Ward</option>
                                    </select>
                                    <c:if test="${not empty errorMessage5}">
                                        <p class="error">${errorMessage5}</p>
                                    </c:if>
                                </div>    


                                <script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.21.1/axios.min.js"></script>
                                <script>
                                    var citis = document.getElementById("city");
                                    var districts = document.getElementById("district");
                                    var wards = document.getElementById("ward");
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
                                        citis.onchange = function () {
                                            district.length = 1;
                                            ward.length = 1;
                                            if (this.value != "") {
                                                const result = data.filter(n => n.Name === this.value);

                                                for (const k of result[0].Districts) {
                                                    district.options[district.options.length] = new Option(k.Name, k.Name);
                                                }
                                            }
                                        };
                                        district.onchange = function () {
                                            ward.length = 1;
                                            const dataCity = data.filter((n) => n.Name === citis.value);
                                            if (this.value != "") {
                                                const dataWards = dataCity[0].Districts.filter(n => n.Name === this.value)[0].Wards;

                                                for (const w of dataWards) {
                                                    wards.options[wards.options.length] = new Option(w.Name, w.Name);
                                                }
                                            }
                                        };
                                    }


                                </script>
                            </div>



                        </div>
                    </div>

                    <div class="details ID">

                        <div class="fields">
                            <div class="input-field">
                                <label>Username</label>
                                <input type="text" name="username" placeholder="Enter username" value="${raw_username}" required>
                                <c:if test="${not empty errorMessage7}">
                                    <p class="error">${errorMessage7}</p>
                                </c:if>
                            </div>

                            <div class="input-field">
                                <label>Password</label>
                                <input type="password" name="password" placeholder="Enter password" value="${raw_password}" required>
                                <c:if test="${not empty errorMessage8}">
                                    <p class="error">${errorMessage8}</p>
                                </c:if>
                            </div>

                            <div class="input-field">
                                <label>Repeat password</label>
                                <input type="password" name="rpassword" placeholder="Enter repeat password" value="${raw_rpassword}" required>
                                <c:if test="${not empty errorMessage9}">
                                    <p class="error">${errorMessage9}</p>
                                </c:if>
                            </div>
                        </div>

                        <button type="submit">
                            <span>Create Account</span>
                            <i class="uil uil-navigator"></i>
                        </button>
                    </div> 
                </div>
            </form>
        </div>
    </body>
</html>
