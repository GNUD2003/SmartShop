<%-- 
    Document   : about
    Created on : Mar 7, 2022, 7:42:05 PM
    Author     : PAsus
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <title>About us</title>
        <!-- Favicon -->
        <link rel="icon" type="image/x-icon" href="./images/favicon.png"/>
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
        <link href="css/my-styles.css" rel="stylesheet">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="fonts/iconic/css/material-design-iconic-font.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/animsition/css/animsition.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/slick/slick.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="css/util.css">
        <link rel="stylesheet" type="text/css" href="css/main.css">
    </head>

    <body class="animsition">
        <!-- Home Menu -->
        <%@include file="components/headerComponent.jsp" %>
        <!-- About -->
        <div class="bg-white">
            <div class="container py-3">
                <div class="row align-items-center">
                    <div class="col-lg-5 px-5 mx-auto"><img src="https://bootstrapious.com/i/snippets/sn-about/img-2.jpg" alt="" class="img-fluid mb-4 mb-lg-0"></div>
                    <div class="col-lg-6"><i class="fa fa-leaf fa-2x mb-3 text-primary"></i>
                        <h2 class="text-black">Lorem ipsum dolor sit amet</h2>
                        <p class="font-italic text-muted mt-3 mb-4">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p><a href="#" class="btn btn-light px-5 rounded-pill shadow-sm">Learn More</a>
                    </div>
                </div>
                <div class="row align-items-center mb-5">
                    <div class="col-lg-6 order-2 order-lg-1"><i class="fa fa-bar-chart fa-2x mb-3 text-primary"></i>
                        <h2 class="text-black">FPT University Ha Noi campus</h2>
                        <p class="font-italic text-muted mt-3 mb-4">The mission of ✴️ FPT University is to ✴️ train high-quality human resources in the integration period, ✴️ provide global competitiveness for people...</p>
                        <a href="https://daihoc.fpt.edu.vn/" class="btn btn-light px-5 rounded-pill shadow-sm">Learn More</a>
                    </div>
                    <div class="col-lg-5 px-5 mx-auto order-1 order-lg-2"><iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3724.4912728193935!2d105.52392847503097!3d21.013020180632406!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3135abc60e7d3f19%3A0x2be9d7d0b5abcbf4!2zVHLGsOG7nW5nIMSQ4bqhaSBI4buNYyBGUFQ!5e0!3m2!1svi!2s!4v1698742416698!5m2!1svi!2s" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe></div>
                </div>
            </div>
        </div>
        <div class="bg-light">
            <div class="container py-3">
                <div class="row mb-4">
                    <div class="col-lg-5">
                        <h2 class="display-4 text-black">Our team</h2>
                        <p class="font-italic text-muted">Lorem ipsum dolor sit amet, consectetur adipisicing elit.</p>
                    </div>
                </div>
                <div class="row text-center">
                    <!-- Team item-->
                    <div class="col-xl-3 col-sm-6 mb-5">
                        <div class="bg-white rounded shadow-sm py-5 px-4"><img src="https://avatars.githubusercontent.com/u/77594830?v=4" alt="" width="100" class="img-fluid rounded-circle mb-3 img-thumbnail shadow-sm">
                            <h5 class="mb-0 text-black">Nguyen Manh Dung</h5><span class="small text-uppercase text-muted">CEO - Founder</span>
                            <ul class="social mb-0 list-inline mt-3">
                                <li class="list-inline-item"><a href="https://www.facebook.com/profile.php?id=100056112458895" class="social-link"><i class="bi bi-facebook"></i></a></li>
                                <li class="list-inline-item"><a href="" class="social-link"><i class="bi bi-github"></i></a></li>
                                <li class="list-inline-item"><a href="https://www.instagram.com/nguyen261m/?hl=en" class="social-link"><i class="bi bi-instagram"></i></a></li>
                                <li class="list-inline-item"><a href="#" class="social-link"><i class="bi bi-linkedin"></i></a></li>
                            </ul>
                        </div>
                    </div>
                    <!-- End-->
                </div>
            </div>
        </div>
        <!-- Subscribe News Letter -->
        <%@include file="components/subscribeNewsLetterComponent.jsp" %>
        <!-- Footer -->
        <%@include file="components/footerComponent.jsp" %>
        <!-- Bootstrap core JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Jquery -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <!-- Toast Alert script -->
        <script src="js/toast-alert.js"></script>
        <script src="js/subscribe-newsletter.js"></script>
        <!-- Home Slider JS -->
        <script src="vendor/slick/slick.min.js"></script>
        <script src="js/slick-custom.js"></script>
        <!--===============================================================================================-->
        <script src="vendor/animsition/js/animsition.min.js"></script>
        <!--===============================================================================================-->
        <script src="vendor/perfect-scrollbar/perfect-scrollbar.min.js"></script>
        <script>
                                                    $('.js-pscroll').each(function () {
                                                        $(this).css('position', 'relative');
                                                        $(this).css('overflow', 'hidden');
                                                        var ps = new PerfectScrollbar(this, {
                                                            wheelSpeed: 1,
                                                            scrollingThreshold: 1000,
                                                            wheelPropagation: false,
                                                        });

                                                        $(window).on('resize', function () {
                                                            ps.update();
                                                        })
                                                    });
        </script>
        <!-- Main -->
        <script src="js/main.js"></script>
    </body>
</html>

