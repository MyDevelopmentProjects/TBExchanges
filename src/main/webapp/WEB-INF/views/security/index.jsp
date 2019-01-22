<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title>TBExchange Admin Panel</title>
    <meta name="google-site-verification" content="sz8kyr_K86S4G6ZN-J1F7G7vVMxwFpNkBq8Z9iK7Sg0"/>
    <meta name="description" content="Admin, Dashboard, Bootstrap, Bootstrap 4, Angular, AngularJS"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimal-ui"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- for ios 7 style, multi-resolution icon of 152x152 -->
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-barstyle" content="black-translucent">
    <link rel="apple-touch-icon" href="/admin/assets/images/logo.png">
    <meta name="apple-mobile-web-app-title" content="TBExchange Admin Panel">
    <!-- for Chrome on Android, multi-resolution icon of 196x196 -->
    <meta name="mobile-web-app-capable" content="yes">
    <link rel="shortcut icon" sizes="196x196" href="/admin/assets/images/logo.png">

    <!-- style -->
    <link rel="stylesheet" href="/admin/assets/animate.css/animate.min.css" type="text/css"/>
    <link rel="stylesheet" href="/admin/assets/glyphicons/glyphicons.css" type="text/css"/>
    <link rel="stylesheet" href="/admin/assets/font-awesome/css/font-awesome.min.css" type="text/css"/>
    <link rel="stylesheet" href="/admin/assets/material-design-icons/material-design-icons.css" type="text/css"/>

    <link rel="stylesheet" href="/admin/assets/bootstrap/dist/css/bootstrap.min.css" type="text/css"/>
    <!-- build:css ../assets/styles/app.min.css -->
    <link rel="stylesheet" href="/admin/assets/styles/app.css" type="text/css"/>
    <!-- endbuild -->
    <link rel="stylesheet" href="/admin/assets/styles/font.css" type="text/css"/>
    <link rel="stylesheet" href="/admin/assets/fonts/geo/font.css" type="text/css"/>
    <link rel="stylesheet" href="/admin/assets/styles/clockpicker.css" type="text/css"/>

    <!-- Events -->
    <link rel="stylesheet" href="/admin/assets/angular-motion/dist/angular-motion.min.css" type="text/css"/>
    <link rel="stylesheet" href="/admin/assets/bootstrap-additions/dist/bootstrap-additions.min.css" type="text/css"/>
    <link rel="stylesheet" type="text/css" media="screen" href="/admin/assets/styles/colorpicker.css">
    <link rel="stylesheet" href="/admin/libs/angular/angular-charts/angular-chart.css">
    <link rel="stylesheet" href="/admin/assets/styles/bootstrap-datepicker.min.css">
    <link rel="stylesheet" href="/admin/libs/jquery/swal/sweetalert.css" type="text/css"/>
    <link rel="stylesheet" href="/admin/libs/jquery/summernote/dist/summernote.css" type="text/css"/>
    <link rel="stylesheet" href="/admin/assets/styles/angular-ui-tree.min.css" type="text/css"/>


</head>
<body ng-app="app" ng-controller="AppCtrl" class="{{app.setting.bg}}"
      ng-class="{'container': app.setting.boxed, 'ie': isIE,'smart': isSmart}">
<div class="loader" id="loaderDiv" loader></div>

<div class="app" ui-view></div>
<!-- build:js scripts/app.angular.js -->
<!-- jQuery -->
<script data-cfasync="false" src="/admin/libs/jquery/jquery/dist/jquery.js"></script>
<script data-cfasync="false" src="/admin/libs/jquery/jquery/dist/jquery.ui.js"></script>
<!-- Bootstrap -->
<script data-cfasync="false" src="/admin/libs/jquery/tether/dist/js/tether.min.js"></script>
<script data-cfasync="false" src="/admin/libs/jquery/bootstrap/dist/js/bootstrap.js"></script>
<script data-cfasync="false" src="/admin/libs/jquery/bootstrap/dist/js/notify.min.js"></script>
<script data-cfasync="false" src="/admin/libs/jquery/swal/sweetalert.min.js"></script>
<script data-cfasync="false" src="/admin/libs/jquery/PACE/pace.min.js"></script>
<script data-cfasync="false" src="/admin/libs/jquery/summernote/dist/summernote.js"></script>
<script data-cfasync="false" src="/admin/libs/jquery/summernote/dist/summernote-gallery-extension.js"></script>
<!-- Angular -->
<script data-cfasync="false" src="/admin/libs/angular/angular/angular.js"></script>
<script data-cfasync="false" src="/admin/libs/angular/angular-animate/angular-animate.js"></script>
<script data-cfasync="false" src="/admin/libs/angular/angular-resource/angular-resource.js"></script>
<script data-cfasync="false" src="/admin/libs/angular/angular-sanitize/angular-sanitize.js"></script>
<script data-cfasync="false" src="/admin/libs/angular/angular-touch/angular-touch.js"></script>
<script data-cfasync="false" src="/admin/libs/angular/angular/ui-bootstrap-custom-tpls-0.11.0.js"></script>
<script data-cfasync="false" src="/admin/libs/angular/angular-summernote/dist/angular-summernote.js"></script>


<!-- router -->
<script data-cfasync="false" src="/admin/libs/angular/angular-ui-router/release/angular-ui-router.js"></script>
<!-- storage -->
<script data-cfasync="false" src="/admin/libs/angular/ngstorage/ngStorage.js"></script>
<!-- utils -->
<script data-cfasync="false" src="/admin/libs/angular/angular-ui-utils/ui-utils.js"></script>
<!-- lazyload -->
<script data-cfasync="false" src="/admin/libs/angular/oclazyload/dist/ocLazyLoad.js"></script>

<script data-cfasync="false" src="/admin/libs/jquery/pickers/clockpicker.min.js" type="text/javascript"></script>


<!-- App -->
<script data-cfasync="false" src="/admin/js/ng/app.js"></script>
<script data-cfasync="false" src="/admin/js/ng/config.js"></script>
<script data-cfasync="false" src="/admin/js/ng/config.lazyload.js"></script>
<script data-cfasync="false" src="/admin/js/ng/config.router.js"></script>
<script data-cfasync="false" src="/admin/js/ng/app.ctrl.js"></script>
<script data-cfasync="false" src="/admin/js/ng/services/utils.js"></script>
<script data-cfasync="false" src="/admin/libs/angular/angular/ng-file-upload-shim.min.js"></script>
<script data-cfasync="false" src="/admin/libs/angular/angular/ng-file-upload.min.js"></script>
<script data-cfasync="false" src="/admin/libs/angular/angular/angular-file-upload.min.js"></script>
<!-- App -->

<script data-cfasync="false" src="/admin/libs/js/moment/moment.js"></script>

<script data-cfasync="false" src="/admin/libs/angular/angular-strap/dist/angular-strap.js"></script>
<script data-cfasync="false" src="/admin/libs/angular/angular-strap/dist/angular-strap.tpl.js"></script>
<script data-cfasync="false" src="/admin/libs/jquery/pickers/datepicker.min.js"></script>


<!-- directives -->
<script data-cfasync="false" src="/admin/js/ng/directives/ui-jp.js"></script>
<script data-cfasync="false" src="/admin/js/ng/directives/ui-nav.js"></script>
<script data-cfasync="false" src="/admin/js/ng/directives/ui-fullscreen.js"></script>
<script data-cfasync="false" src="/admin/js/ng/directives/ui-scroll-to.js"></script>
<script data-cfasync="false" src="/admin/js/ng/directives/ui-toggle-class.js"></script>
<script data-cfasync="false" src="/admin/js/ng/directives/ui-include.js"></script>

<!-- My -->
<script data-cfasync="false" src="/admin/js/ng/directives/imperio-thumb.js"></script>
<script data-cfasync="false" src="/admin/libs/jquery/fullcalendar/dist/fullcalendarNew.min.js"></script>
<script data-cfasync="false" src="/admin/libs/angular/angular-ui-calendar/src/scheduler.min.js"></script>

<script data-cfasync="false" src="/admin/js/ng/directives/imperio-calendar-directive.js"></script>
<script data-cfasync="false" src="/admin/js/ng/directives/imperio-export-directive.js"></script>
<script data-cfasync="false" src="/admin/js/ng/directives/imperio-search-directive.js"></script>
<script data-cfasync="false" src="/admin/js/ng/directives/imperio-pagination-directive.js"></script>
<script data-cfasync="false" src="/admin/js/ng/directives/imperio-fab-directive.js"></script>
<script data-cfasync="false" src="/admin/js/ng/directives/imperio-popover-directive.js"></script>
<script data-cfasync="false" src="/admin/js/ng/directives/imperio-datepicker-directive.js"></script>
<script data-cfasync="false" src="/admin/js/ng/directives/imperio-timepicker-directive.js"></script>
<script data-cfasync="false" src="/admin/js/ng/directives/imperio-date-cud-directive.js"></script>
<script data-cfasync="false" src="/admin/js/ng/directives/imperio-meta-directive.js"></script>
<script data-cfasync="false" src="/admin/js/ng/directives/imperio-upload-directive.js"></script>
<script data-cfasync="false" src="/admin/js/ng/directives/imperio-file-uploader-input-directive.js"></script>
<script data-cfasync="false" src="/admin/js/ng/directives/imperio-file-uploader-input-multiple-directive.js"></script>
<script data-cfasync="false" src="/admin/js/ng/directives/imperio-sortable-by-directive.js"></script>
<script data-cfasync="false" src="/admin/js/ng/directives/imperio-colorpicker-directive.js"></script>
<script data-cfasync="false" src="/admin/js/ng/directives/imperio-pattern-directive.js"></script>
<script data-cfasync="false" src="/admin/js/ng/directives/imperio-youtube-directive.js"></script>
<!-- directives -->

<!-- services -->
<script data-cfasync="false" src="/admin/js/ng/services/ngstore.js"></script>
<script data-cfasync="false" src="/admin/js/ng/services/ui-load.js"></script>
<script data-cfasync="false" src="/admin/js/ng/services/palette.js"></script>
<script data-cfasync="false" src="/admin/js/ng/services/gridManager.js"></script>
<script data-cfasync="false" src="/admin/js/ng/services/modalManager.js"></script>
<script data-cfasync="false" src="/admin/js/ng/services/httpInterceptor.js"></script>
<script data-cfasync="false" src="/admin/js/ng/services/utils.js"></script>
<script data-cfasync="false" src="/admin/js/ng/services/angular-ui-tree.min.js"></script>
<!-- services -->

<script src="/admin/libs/jquery/select2/dist/js/select2.js"></script>
</body>
</html>


