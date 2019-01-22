<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<c:set var="basePath" scope="session"
       value="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/"/>
<!DOCTYPE html>
<html lang="en-us" id="extr-page">
<head>
    <meta charset="utf-8">
    <title> TBExchange </title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta name="google-site-verification" content="sz8kyr_K86S4G6ZN-J1F7G7vVMxwFpNkBq8Z9iK7Sg0"/>
    <!-- #CSS Links -->
    <!-- Basic Styles -->
    <link rel="stylesheet" type="text/css" media="screen" href="/resources/login/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" media="screen" href="/resources/login/font-awesome.min.css">

    <!-- SmartAdmin Styles : Caution! DO NOT change the order -->
    <link rel="stylesheet" type="text/css" media="screen" href="/resources/login/smartadmin-production-plugins.min.css">
    <link rel="stylesheet" type="text/css" media="screen" href="/resources/login/smartadmin-production.min.css">

    <!-- SmartAdmin RTL Support -->
    <link rel="stylesheet" type="text/css" media="screen" href="/resources/login/smartadmin-rtl.min.css">

    <!-- Demo purpose only: goes with demo.js, you can delete this css when designing your own WebApp -->
    <link rel="stylesheet" type="text/css" media="screen" href="/resources/login/demo.min.css">

    <!-- #FAVICONS -->
    <link rel="shortcut icon" href="/resources/img/favicon/favicon.ico" type="image/x-icon">
    <link rel="icon" href="/resources/img/favicon/favicon.ico" type="image/x-icon">

    <!-- #GOOGLE FONT -->
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,700italic,300,400,700">

    <!-- #APP SCREEN / ICONS -->
    <!-- Specifying a Webpage Icon for Web Clip
         Ref: https://developer.apple.com/library/ios/documentation/AppleApplications/Reference/SafariWebContent/ConfiguringWebApplications/ConfiguringWebApplications.html -->
    <link rel="apple-touch-icon" href="/resources/img/splash/sptouch-icon-iphone.png">
    <link rel="apple-touch-icon" sizes="76x76" href="/resources/img/splash/touch-icon-ipad.png">
    <link rel="apple-touch-icon" sizes="120x120" href="/resources/img/splash/touch-icon-iphone-retina.png">
    <link rel="apple-touch-icon" sizes="152x152" href="/resources/img/splash/touch-icon-ipad-retina.png">

    <!-- iOS web-app metas : hides Safari UI Components and Changes Status Bar Appearance -->
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">

    <!-- Startup image for web apps -->
    <link rel="apple-touch-startup-image" href="/img/splash/ipad-landscape.png"
          media="screen and (min-device-width: 481px) and (max-device-width: 1024px) and (orientation:landscape)">
    <link rel="apple-touch-startup-image" href="/img/splash/ipad-portrait.png"
          media="screen and (min-device-width: 481px) and (max-device-width: 1024px) and (orientation:portrait)">
    <link rel="apple-touch-startup-image" href="/img/splash/iphone.png"
          media="screen and (max-device-width: 320px)">

    <style>
        @font-face {
            font-family: 'BGMtavr';
            src: url(/resources/fonts/bpg_banner_extrasquare.woff);
        url(/resources/fonts/bpg_banner_extrasquare.woff) format('woff')
        }
        #extr-page h4.paragraph-header {
            font-size: 13px;
            width: 408px;
        }

        .about-heading {
            font-family: 'BGMtavr';
        }
    </style>
</head>
<body class="animated fadeInDown">

<header id="header">

    <div id="logo-group">
        <span id="logo"> <img src="/img/logo.png" alt="TBExchange"> </span>
    </div>

</header>

<div id="main" role="main">
    <div id="content" class="container">

        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-7 col-lg-8 hidden-xs hidden-sm">
                <h1 class="txt-color-red login-header-big">TBExchange</h1>
                <div class="hero">

                    <div class="pull-left login-desc-box-l">
                        <h4 class="paragraph-header">ჩვენი მიზანია ქართულ ბაზარზე ვთქვათ ახალი სიტყვა და დაწერილი
                            აპლიკაციებით, გავუმარტივოთ ბიზნესს ფუნქციონირება, მივეხმაროთ განვითარებაში.</h4>
                        <div class="login-app-icons">
                            <a href="javascript:void(0);" class="btn btn-danger btn-sm">პროეკტის შესახებ</a>
                            <a href="javascript:void(0);" class="btn btn-danger btn-sm">ჩვენს შესახებ</a>
                        </div>
                    </div>

                    <img src="/img/iphoneview.png" class="pull-right display-image" alt=""
                         style="width:210px">

                </div>

                <div class="row">
                    <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                        <h5 class="about-heading">კომპანიის შესახებ</h5>
                        <p>
                            როდესაც საქმე ეხება თანამედროვე ტექნოლოგიებს, iOS, Android და WEB დეველოპმენტს, ჩვენ ვამბობთ
                            რომ შეუძლებელი არაფერია, მზად ვართ განვიხილოთ ნებისმიერი კომპანიის მოთხოვნა და დაგიმზადოთ
                            თქვენზე მორგებული აპლიკაციები, გაგიმარტივოთ საქმის კეთება, დაგიზოგოთ დრო, ეს ყველაფერი
                            მოგცემთ საშუალებას გამოიმუშავოთ მეტი თანხა და განავითაროთ ბიზნესი.
                        </p>
                    </div>
                    <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                        <!-- HERE SHOULD GO ADSENSE -->
                        <script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
                        <!-- MGL Login Screen -->
                        <ins class="adsbygoogle"
                             style="display:inline-block;width:300px;height:250px"
                             data-ad-client="ca-pub-2724445824083168"
                             data-ad-slot="2504974819"></ins>
                        <script>
                            (adsbygoogle = window.adsbygoogle || []).push({});
                        </script>
                    </div>
                </div>

            </div>
            <div class="col-xs-12 col-sm-12 col-md-5 col-lg-4">
                <div class="well no-padding">