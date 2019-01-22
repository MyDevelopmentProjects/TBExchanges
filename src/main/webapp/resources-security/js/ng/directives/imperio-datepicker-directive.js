angular.module('app').directive('imperioDatepicker', function () {
    return {
        restrict: 'A',
        require: 'ngModel',
        link: function (scope, element, attrs, ctrl) {
            $(element).datepicker({
                format: "yyyy-mm-dd",
                autoclose: true
            });
        }
    };
});