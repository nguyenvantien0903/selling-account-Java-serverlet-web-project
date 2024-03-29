<%@page import="model.Item"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="model.Plan"%> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta
            name="viewport"
            content="width=device-width, initial-scale=1, shrink-to-fit=no"
        />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Register</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" integrity="sha512-9usAa10IRO0HhonpyAIVpjrylPvoDwiPUiKdWk5t3PyolY1cOd4DSE0Ga+ri4AuTroPR5aQvXU9xC6qOPnzFeg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
            crossorigin="anonymous"
        />
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.8.1/font/bootstrap-icons.min.css"
            integrity="sha512-Oy+sz5W86PK0ZIkawrG0iv7XwWhYecM3exvUtMKNJMekGFJtVAhibhRPTpmyTj8+lJCkmWfnpxKgT2OopquBHA=="
            crossorigin="anonymous"
            referrerpolicy="no-referrer"
        />
        <style>
            * {
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                margin: 0px;
                padding: 0px;
                box-sizing: border-box;
            }
    
            body,
            html {
                height: 100%;
            }
    
            /*---------------------------------------------*/
            a {
                text-decoration: none;
                font-size: 14px;
                line-height: 1.7;
                color: #666666;
                margin: 0px;
                transition: all 0.4s;
                -webkit-transition: all 0.4s;
                -o-transition: all 0.4s;
                -moz-transition: all 0.4s;
            }
    
            a:focus {
                outline: none !important;
            }
    
            a:hover {
                text-decoration: none;
                color: #c80000;
            }
    
            /*---------------------------------------------*/
            h1,
            h2,
            h3,
            h4,
            h5,
            h6 {
                margin: 0px;
            }
    
            p {
                font-size: 14px;
                line-height: 1.7;
                color: #666666;
                margin: 0px;
            }
    
            ul,
            li {
                margin: 0px;
                list-style-type: none;
            }
    
            /*---------------------------------------------*/
            input {
                outline: none;
                border: none;
            }
    
            textarea {
                outline: none;
                border: none;
            }
    
            textarea:focus,
            input:focus {
                border-color: transparent !important;
            }
    
            input:focus::-webkit-input-placeholder {
                color: transparent;
            }
            input:focus:-moz-placeholder {
                color: transparent;
            }
            input:focus::-moz-placeholder {
                color: transparent;
            }
            input:focus:-ms-input-placeholder {
                color: transparent;
            }
    
            textarea:focus::-webkit-input-placeholder {
                color: transparent;
            }
            textarea:focus:-moz-placeholder {
                color: transparent;
            }
            textarea:focus::-moz-placeholder {
                color: transparent;
            }
            textarea:focus:-ms-input-placeholder {
                color: transparent;
            }
    
            input::-webkit-input-placeholder {
                color: #999999;
            }
            input:-moz-placeholder {
                color: #999999;
            }
            input::-moz-placeholder {
                color: #999999;
            }
            input:-ms-input-placeholder {
                color: #999999;
            }
    
            textarea::-webkit-input-placeholder {
                color: #999999;
            }
            textarea:-moz-placeholder {
                color: #999999;
            }
            textarea::-moz-placeholder {
                color: #999999;
            }
            textarea:-ms-input-placeholder {
                color: #999999;
            }
    
            /*---------------------------------------------*/
            button {
                outline: none !important;
                border: none;
                background: transparent;
            }
    
            button:hover {
                cursor: pointer;
            }
    
            iframe {
                border: none !important;
            }
    
            /*//////////////////////////////////////////////////////////////////
    [ Utility ]*/
            .txt1 {
                font-size: 13px;
                line-height: 1.5;
                color: #999999;
            }
    
            .txt2 {
                font-size: 13px;
                line-height: 1.5;
                color: #666666;
            }
    
            /*//////////////////////////////////////////////////////////////////
    [ login ]*/
    
            .limiter {
                width: 100%;
                margin: 0 auto;
            }
    
            .container-login100 {
                width: 100%;
                min-height: 100vh;
                display: -webkit-box;
                display: -webkit-flex;
                display: -moz-box;
                display: -ms-flexbox;
                display: flex;
                flex-wrap: wrap;
                justify-content: center;
                align-items: center;
                padding: 15px;
                background: url('./assests/img/cover.jpg');
                background-size: cover;
                background-repeat: no-repeat;
                background-position: top;
                box-shadow: inset 0 0 0 2000px rgb(0 0 0 / 50%);
            }
    
            .symbol-input100 {
            font-size: 15px;

            display: -webkit-box;
            display: -webkit-flex;
            display: -moz-box;
            display: -ms-flexbox;
            display: flex;
            align-items: center;
            position: absolute;
            border-radius: 25px;
            bottom: 0;
            left: 0;
            width: 100%;
            height: 100%;
            padding-left: 35px;
            padding-top: 3px;
            pointer-events: none;
            color: #666666;

            -webkit-transition: all 0.4s;
            -o-transition: all 0.4s;
            -moz-transition: all 0.4s;
            transition: all 0.4s;
        }

            .wrap-login100 {
                width: 960px;
                background: #fff;
                border-radius: 10px;
                overflow: hidden;
    
                display: -webkit-box;
                display: -webkit-flex;
                display: -moz-box;
                display: -ms-flexbox;
                display: flex;
                flex-wrap: wrap;
                align-items: center;
                justify-content: space-evenly;
                padding: 64px 0px;
            }
    
            /*------------------------------------------------------------------
    [  ]*/
            .login100-pic {
                width: 316px;
            }
    
            .login100-pic img {
                max-width: 100%;
            }
    
            /*------------------------------------------------------------------
    [  ]*/
            .login100-form {
                width: 290px;
            }
    
            .login100-form-title {
                font-size: 24px;
                color: #333333;
                line-height: 1.2;
                text-align: center;
    
                /* margin-top: -60px; */
                width: 100%;
                display: block;
                padding-bottom: 54px;
            }
    
            /*---------------------------------------------*/
            .wrap-input100 {
                position: relative;
                width: 100%;
                z-index: 1;
                margin-bottom: 10px;
            }
    
            .input100 {
                font-size: 15px;
                line-height: 1.5;
                color: #666666;
    
                display: block;
                width: 100%;
                background: #e6e6e6;
                height: 50px;
                border-radius: 25px;
                padding: 0 30px 0 68px;
            }
    
            /*------------------------------------------------------------------
    [ Focus ]*/
            .focus-input100 {
                display: block;
                position: absolute;
                border-radius: 25px;
                bottom: 0;
                left: 0;
                z-index: -1;
                width: 100%;
                height: 100%;
                box-shadow: 0px 0px 0px 0px;
                color: #c80000;
            }
    
            .input100:focus + .focus-input100 {
                -webkit-animation: anim-shadow 0.5s ease-in-out forwards;
                animation: anim-shadow 0.5s ease-in-out forwards;
            }
            /*------------------------------------------------------------------
    [ Button ]*/
            .container-login100-form-btn {
                width: 100%;
                display: -webkit-box;
                display: -webkit-flex;
                display: -moz-box;
                display: -ms-flexbox;
                display: flex;
                flex-wrap: wrap;
                justify-content: center;
                padding-top: 20px;
            }
    
            .login100-form-btn {
                font-size: 15px;
                line-height: 1.5;
                color: #fff;
                text-transform: uppercase;
    
                width: 100%;
                height: 50px;
                border-radius: 25px;
                display: -webkit-box;
                display: -webkit-flex;
                display: -moz-box;
                display: -ms-flexbox;
                display: flex;
                justify-content: center;
                align-items: center;
                padding: 0 25px;
    
                -webkit-transition: all 0.4s;
                -o-transition: all 0.4s;
                -moz-transition: all 0.4s;
                transition: all 0.4s;
            }
    
            .login100-form-btn:hover {
                background: #333333;
            }
    
            /*------------------------------------------------------------------
    [ Responsive ]*/
    
            @media (max-width: 992px) {
                .wrap-login100 {
                    padding: 177px 90px 33px 85px;
                }
    
                .login100-pic {
                    width: 35%;
                }
    
                .login100-form {
                    width: 50%;
                }
            }
    
            @media (max-width: 768px) {
                .wrap-login100 {
                    padding: 100px 80px 33px 80px;
                }
    
                .login100-pic {
                    display: none;
                }
    
                .login100-form {
                    width: 100%;
                }
            }
    
            @media (max-width: 576px) {
                .wrap-login100 {
                    padding: 100px 15px 33px 15px;
                }
            }
    
            /*------------------------------------------------------------------
    [ Alert validate ]*/
    
            .validate-input {
                position: relative;
            }
    
            .alert-validate::before {
                content: attr(data-validate);
                position: absolute;
                max-width: 70%;
                background-color: white;
                border: 1px solid #c80000;
                border-radius: 13px;
                padding: 4px 25px 4px 10px;
                top: 50%;
                -webkit-transform: translateY(-50%);
                -moz-transform: translateY(-50%);
                -ms-transform: translateY(-50%);
                -o-transform: translateY(-50%);
                transform: translateY(-50%);
                right: 8px;
                pointer-events: none;
    
                color: #c80000;
                size: 13px;
                line-height: 1.4;
                text-align: left;
    
                visibility: hidden;
                opacity: 0;
    
                -webkit-transition: opacity 0.4s;
                -o-transition: opacity 0.4s;
                -moz-transition: opacity 0.4s;
                transition: opacity 0.4s;
            }
    
            .alert-validate::after {
                content: '\f06a';
                font-family: FontAwesome;
                display: block;
                position: absolute;
                color: #c80000;
                font-size: 15px;
                top: 50%;
                -webkit-transform: translateY(-50%);
                -moz-transform: translateY(-50%);
                -ms-transform: translateY(-50%);
                -o-transform: translateY(-50%);
                transform: translateY(-50%);
                right: 13px;
            }
    
            .alert-validate:hover:before {
                visibility: visible;
                opacity: 1;
            }
    
            @media (max-width: 992px) {
                .alert-validate::before {
                    visibility: visible;
                    opacity: 1;
                }
            }
            #create-account {
                margin-top: 64px;
            }
            #forgot{
                margin-top: 16px;
            }
            #err{
                font-size: 16px;
                margin-bottom: 28px;
                margin-top: -28px;
                color: #dc3545;
            }
        </style>
        <body>
            <div class="limiter">
                <div class="container-login100">
                    <div class="wrap-login100">
                        <div class="login100-pic js-tilt" data-tilt>
                            <img src="./assests/img/img-01.png" alt="IMG" />
                        </div>
    
                        <form class="login100-form validate-form" method="post" action="../../account/register">
                            <span class="login100-form-title"> Member Register </span>
                            <p class="text-center danger" id="err">
                            <%
                                String err;
                                try {
                                    err = request.getAttribute("err").toString();
                                    if(err != null){
                            %>
                             <%= err %> 
                            <%        
                                    }
                                } catch (Exception e) {
                                    System.out.println(e);
                                }
                            %></p>
                            <!-- Username -->
                            <div
                                class="wrap-input100 validate-input"
                            >
                                <input
                                    class="input100"
                                    type="text"
                                    name="username"
                                    placeholder="Username"
                                    <%
                                        if(request.getAttribute("username") != null){
                                    %>
                                    value="<%= request.getAttribute("username").toString() %>"
                                    <% } %>
                                    required
                                />
                                <span class="focus-input100"></span>
                                <span class="symbol-input100">
                                    <i class="fa-solid fa-circle-user"></i>
                                </span>
                            </div>
                            <!-- Password -->
                            <div
                                class="wrap-input100 validate-input"
                            >
                                <input
                                    class="input100"
                                    type="password"
                                    name="password"
                                    id="password"
                                    placeholder="Password"
                                    required
                                />
                                <span class="focus-input100"></span>
                                <span class="symbol-input100">
                                    <i class="fa fa-lock" aria-hidden="true"></i>
                                </span>
                            </div>
                            <!-- Re-password -->
                            <div
                                class="wrap-input100 validate-input"
                            >
                                <input
                                    class="input100"
                                    type="password"
                                    name="repassword"
                                    id="repassword"
                                    placeholder="Re-enter password"
                                    required
                                    oninput="checkRepass()"
                                />
                                <span class="focus-input100"></span>
                                <span class="symbol-input100">
                                    <i class="fa fa-lock" aria-hidden="true"></i>
                                </span>
                            </div>
                            <!-- Email -->
                            <div
                                class="wrap-input100 validate-input"
                            >
                                <input
                                    class="input100"
                                    type="email"
                                    name="email"
                                    placeholder="Email"
                                    <%
                                        if(request.getAttribute("email") != null){
                                    %>
                                    value="<%= request.getAttribute("email").toString() %>"
                                    <% } %>
                                    required
                                />
                                <span class="focus-input100"></span>
                                <span class="symbol-input100"><i class="fa-solid fa-envelope"></i>
                                </span>
                            </div>
                            <!-- Phone -->
                            <div
                                class="wrap-input100 validate-input"
                            >
                                <input
                                    class="input100"
                                    type="text"
                                    name="phone"
                                    placeholder="Phone"
                                    <%
                                        if(request.getAttribute("phone") != null){
                                    %>
                                    value="<%= request.getAttribute("phone").toString() %>"
                                    <% } %>
                                    required
                                />
                                <span class="focus-input100"></span>
                                <span class="symbol-input100"><i class="fa-solid fa-mobile-button"></i>
                                </span>
                            </div>
    
                            <div class="container-login100-form-btn">
                                <button  type="submit" class="btn btn-danger login100-form-btn">Create Account</button>
                            </div>
                           
                            <div class="text-center p-t-136" id="create-account">
                                <a class="txt2" href="/login">
                                    <i
                                        class="fa fa-long-arrow-left m-l-5"
                                        aria-hidden="true"
                                    ></i>
                                    Already have account? Login
                                </a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
    
        <!-- Bootstrap core JS-->
        <script>
            checkRepass = () => {
                let pass = document.getElementById("password").value;
                let repass = document.getElementById("repassword").value;
                let err = document.getElementById("err");
                for(var i = 0; i < repass.length; i++){
                    if(repass[i] != pass[i]){
                        err.innerHTML = 'Re-enterd password not match!'
                        return;
                    }
                }
                err.innerHTML = ''
            }
        </script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        </body>
    </html>
    