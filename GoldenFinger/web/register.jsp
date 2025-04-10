<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">

        <title>Register</title>
        <meta name="keywords" content="tailwindcss, ecommerce, piano">
        <meta name="description" content="Piano Shop">
        <meta name="author" content="">

        <!-- site Favicon -->
        <link rel="icon" href="assets/img/favicon/favicon.png" sizes="32x32">

        <!-- css Icon Font -->
        <link rel="stylesheet" href="assets/css/vendor/gicons.css">

        <!-- css All Plugins Files -->
        <link rel="stylesheet" href="assets/css/plugins/animate.css">
        <link rel="stylesheet" href="assets/css/plugins/swiper-bundle.min.css">
        <link rel="stylesheet" href="assets/css/plugins/owl.carousel.min.css">
        <link rel="stylesheet" href="assets/css/plugins/owl.theme.default.min.css">
        <link rel="stylesheet" href="assets/css/plugins/slick.min.css">
        <link rel="stylesheet" href="assets/css/plugins/nouislider.css">

        <!-- Tailwindcss -->
        <script src="assets/js/plugins/tailwindcss3.4.1"></script>

        <!-- Main Style -->
        <link rel="stylesheet" id="main_style" href="assets/css/style.css">
        <link rel="stylesheet" href="assets/css/responsive.css">

    </head>

    <body class="w-full h-full relative font-Poppins font-normal overflow-x-hidden">

        <!-- Register section -->
        <section class="gi-register py-[40px] max-[767px]:py-[30px]">
            <div
                class="flex flex-wrap justify-between items-center mx-auto min-[1600px]:max-w-[1600px] min-[1400px]:max-w-[1320px] min-[1200px]:max-w-[1140px] min-[992px]:max-w-[960px] min-[768px]:max-w-[720px] min-[576px]:max-w-[540px]">
                <div class="section-title-2 w-full mb-[20px] pb-[20px] flex flex-col justify-center items-center">
                    <h2
                        class="gi-title mb-[0] font-manrope text-[26px] font-semibold text-[#4b5966] relative inline p-[0] capitalize leading-[1]">
                        Register<span></span></h2>
                    <p class="max-w-[400px] mt-[15px] text-[14px] text-[#777] text-center leading-[23px]">Best place to buy
                        and sell digital piano</p>
                </div>
                <div class="flex flex-wrap w-full">
                    <div class="gi-register-wrapper max-w-[934px] my-[0] mx-auto px-[12px]">
                        <div
                            class="gi-register-container border-[1px] border-solid border-[#eee] p-[30px] text-left bg-[#fff] rounded-[5px] max-[575px]:p-[15px]">
                            <div class="gi-register-form">
                                <form action="register" method="post" class="flex flex-row flex-wrap mx-[-15px]" onsubmit="return validateForm()">

                                    <span class="gi-register-wrap gi-register-half w-[50%] px-[15px]">
                                        <label
                                            class="inline-block mb-[9px] text-[#4b5966] text-[15px] font-medium tracking-[0] leading-[1]">Username*</label>
                                        <input type="text" name="username" value="${param.username}" placeholder="Enter your username"
                                               class="w-full bg-transparent border-[1px] border-solid border-[#eee] text-[#777] text-[14px] mb-[26px] px-[15px] outline-[0] rounded-[5px] h-[50px]"
                                               required>
                                    </span>
                                    <span class="gi-register-wrap gi-register-half w-[50%] px-[15px]">
                                        <label
                                            class="inline-block mb-[9px] text-[#4b5966] text-[15px] font-medium tracking-[0] leading-[1]">Full Name
                                            *</label>
                                        <input type="text" name="fullname" value="${param.fullname}" placeholder="Enter your full name"
                                               class="w-full bg-transparent border-[1px] border-solid border-[#eee] text-[#777] text-[14px] mb-[26px] px-[15px] outline-[0] rounded-[5px] h-[50px]"
                                               required>
                                    </span>
                                    <span class="gi-register-wrap gi-register-half w-[50%] px-[15px]">
                                        <label
                                            class="inline-block mb-[9px] text-[#4b5966] text-[15px] font-medium tracking-[0] leading-[1]">Password*</label>
                                        <input type="password" name="password" id="password" placeholder="Enter your password" value="${param.password}"
                                               class="w-full bg-transparent border-[1px] border-solid border-[#eee] text-[#777] text-[14px] mb-[26px] px-[15px] outline-[0] rounded-[5px] h-[50px]"
                                               required>
                                    </span>
                                    <span class="gi-register-wrap gi-register-half w-[50%] px-[15px]">
                                        <label
                                            class="inline-block mb-[9px] text-[#4b5966] text-[15px] font-medium tracking-[0] leading-[1]">
                                            Confirm Password*</label>
                                        <input type="password" name="cpassword" id="cpassword" placeholder="Enter your comfirm password" value="${param.cpassword}"
                                               class="w-full bg-transparent border-[1px] border-solid border-[#eee] text-[#777] text-[14px] mb-[26px] px-[15px] outline-[0] rounded-[5px] h-[50px]"
                                               required>
                                    </span>
                                    <span class="gi-register-wrap gi-register-half w-[50%] px-[15px]">
                                        <label
                                            class="inline-block mb-[9px] text-[#4b5966] text-[15px] font-medium tracking-[0] leading-[1]">Email*</label>
                                        <input type="email" name="email" value="${param.email}" placeholder="Enter your email"
                                               class="w-full bg-transparent border-[1px] border-solid border-[#eee] text-[#777] text-[14px] mb-[26px] px-[15px] outline-[0] rounded-[5px] h-[50px]"
                                               required>
                                    </span>
                                    <span class="gi-register-wrap gi-register-half w-[50%] px-[15px]">
                                        <label
                                            class="inline-block mb-[9px] text-[#4b5966] text-[15px] font-medium tracking-[0] leading-[1]">Phone
                                            *</label>
                                        <input type="text" name="phone" placeholder="Enter your phone" value="${param.phone}"
                                               class="w-full bg-transparent border-[1px] border-solid border-[#eee] text-[#777] text-[14px] mb-[26px] px-[15px] outline-[0] rounded-[5px] h-[50px]"
                                               required>
                                    </span>
                                    <span class="gi-register-wrap gi-register-half w-[50%] px-[15px]">
                                        <label
                                            class="inline-block mb-[9px] text-[#4b5966] text-[15px] font-medium tracking-[0] leading-[1]">Birh Date*</label>
                                        <input type="date" name="birthdate" placeholder="Enter your username" value="${param.birthdate}"
                                               class="w-full bg-transparent border-[1px] border-solid border-[#eee] text-[#777] text-[14px] mb-[26px] px-[15px] outline-[0] rounded-[5px] h-[50px]"
                                               required>
                                    </span>
                                    <span class="gi-register-wrap gi-register-half w-[50%] px-[15px]">
                                        <label
                                            class="inline-block mb-[9px] text-[#4b5966] text-[15px] font-medium tracking-[0] leading-[1]">Address
                                            *</label>
                                        <input type="text" name="address" placeholder="Enter your address" value="${param.address}"
                                               class="w-full bg-transparent border-[1px] border-solid border-[#eee] text-[#777] text-[14px] mb-[26px] px-[15px] outline-[0] rounded-[5px] h-[50px]"
                                               required>
                                    </span>

                                    <c:if test="${not empty requestScope.error}">
                                        <div class="w-full px-[15px] mb-[15px]">
                                            <div class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded relative" role="alert">
                                                <strong class="font-bold">Error!</strong>
                                                <span class="block sm:inline">${requestScope.error}</span>
                                            </div>
                                        </div>
                                    </c:if>
                                    <span
                                        class="gi-register-wrap gi-register-btn flex flex-row justify-between items-center w-full px-[15px] max-[767px]:flex-col ">
                                        <span class="text-[#777] text-[14px] tracking-[0.02rem]">Already have an account?<a
                                                href="login"
                                                class="ml-[10px] text-[#4b5966] text-[14px] transition-all duration-[0.3s] ease-in-out hover:text-[#5caf90]">Login</a></span>
                                        <button type="submit"
                                                class="gi-btn-1 py-[8px] px-[15px] bg-[#4b5966] text-[#fff] border-[0] transition-all duration-[0.3s] ease-in-out overflow-hidden text-center text-[14px] font-semibold relative rounded-[5px] max-[767px]:mt-[15px] hover:bg-[#5caf90]">Register</button>
                                    </span>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Sample section End -->

        <!--valid form in jsp  -->
        <script>
            function validateForm() {
                var password = document.getElementById("password").value;
                var cpassword = document.getElementById("cpassword").value;

                if (password.length < 6) {
                    alert("Password must be at least 6 characters");
                    return false;
                }

                if (password !== cpassword) {
                    alert("Passwords do not match Comfirm Password");
                    return false;
                }

                return true;
            }
        </script>

        <!-- Plugins JS -->
        <script src="assets/js/plugins/jquery-3.7.1.min.js"></script>
        <script src="assets/js/plugins/popper.min.js"></script>
        <script src="assets/js/plugins/swiper-bundle.min.js"></script>
        <script src="assets/js/plugins/fontawesome.js"></script>
        <script src="assets/js/plugins/owl.carousel.min.js"></script>
        <script src="assets/js/plugins/infiniteslidev2.js"></script>
        <script src="assets/js/plugins/jquery.zoom.min.js"></script>
        <script src="assets/js/plugins/slick.min.js"></script>
        <script src="assets/js/plugins/nouislider.js"></script>
        <script src="assets/js/plugins/wow.js"></script>

        <!-- Main Js -->
        <script src="assets/js/main.js"></script>
    </body>

</html>
