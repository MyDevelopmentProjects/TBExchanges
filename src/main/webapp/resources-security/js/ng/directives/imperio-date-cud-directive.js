angular.module('app').directive('imperioDateCud', function () {
    return {
        restrict: 'E',
        templateUrl: 'resources-security/includes/date-cud.jsp',
        replace: true,
        scope: {
            ngModel: '='
        }
    };
});