angular.module('app').directive('imperioTimepicker', function () {
    return {
        restrict: 'E',
        templateUrl: 'resources-security/includes/timepicker.jsp',
        scope: {
            ngModel: '=',
            placeholder: '@'
        },
        link: function (scope, elm, attrs) {
            elm.clockpicker({
                placement: 'top',
                donetext: 'არჩევა',
                autoclose: true
            }).on('change', function (ev) {
                $(this).find('input').val()
                scope.$apply();
            });
        }
    };
});