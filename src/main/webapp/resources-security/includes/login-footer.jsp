</div>

<h5 class="text-center">შემოგვიერთდი</h5>

<ul class="list-inline text-center">
    <li>
        <a href="javascript:void(0);" class="btn btn-primary btn-circle"><i class="fa fa-facebook"></i></a>
    </li>
    <li>
        <a href="javascript:void(0);" class="btn btn-info btn-circle"><i class="fa fa-twitter"></i></a>
    </li>
    <li>
        <a href="javascript:void(0);" class="btn btn-warning btn-circle"><i class="fa fa-linkedin"></i></a>
    </li>
</ul>

</div>
</div>
</div>

</div>

<!--================================================== -->

<!-- PACE LOADER - turn this on if you want ajax loading to show (caution: uses lots of memory on iDevices)-->
<script src="/resources/js/plugin/pace/pace.min.js"></script>

<!-- Link to Google CDN's jQuery + jQueryUI; fall back to local -->
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script> if (!window.jQuery) {
    document.write('<script src="js/libs/jquery-2.1.1.min.js"><\/script>');
} </script>

<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
<script> if (!window.jQuery.ui) {
    document.write('<script src="js/libs/jquery-ui-1.10.3.min.js"><\/script>');
} </script>

<!-- IMPORTANT: APP CONFIG -->
<script src="/resources/js/app.config.js"></script>

<!-- JS TOUCH : include this plugin for mobile drag / drop touch events
<script src="js/plugin/jquery-touch/jquery.ui.touch-punch.min.js"></script> -->

<!-- BOOTSTRAP JS -->
<script src="/resources/js/bootstrap/bootstrap.min.js"></script>

<!-- JQUERY VALIDATE -->
<script src="/resources/js/plugin/jquery-validate/jquery.validate.min.js"></script>

<!-- JQUERY MASKED INPUT -->
<script src="/resources/js/plugin/masked-input/jquery.maskedinput.min.js"></script>

<!--[if IE 8]>

<h1>Your browser is out of date, please update your browser by going to www.microsoft.com/download</h1>

<![endif]-->

<!-- MAIN APP JS FILE -->
<script src="/resources/js/app.js"></script>

<script type="text/javascript">
    runAllForms();

    $(function () {
        // Validation
        $("#login-form").validate({
            // Rules for form validation
            rules: {
                email: {
                    required: true
                },
                password: {
                    required: true,
                    minlength: 3,
                    maxlength: 20
                }
            },

            // Messages for form validation
            messages: {
                email: {
                    required: 'გთხოვთ შეიყვანოთ მომხმარებლის სახელი'
                },
                password: {
                    required: 'გთხოვთ შეიყვანოთ პაროლი'
                }
            },

            // Do not change code below
            errorPlacement: function (error, element) {
                error.insertAfter(element.parent());
            }
        });
    });
</script>

<!-- Your GOOGLE ANALYTICS CODE Below -->

<script>
    (function (i, s, o, g, r, a, m) {
        i['GoogleAnalyticsObject'] = r;
        i[r] = i[r] || function () {
                    (i[r].q = i[r].q || []).push(arguments)
                }, i[r].l = 1 * new Date();
        a = s.createElement(o),
                m = s.getElementsByTagName(o)[0];
        a.async = 1;
        a.src = g;
        m.parentNode.insertBefore(a, m)
    })(window, document, 'script', 'https://www.google-analytics.com/analytics.js', 'ga');

    ga('create', 'UA-76811560-1', 'auto');
    ga('send', 'pageview');

</script>


</body>
</html>