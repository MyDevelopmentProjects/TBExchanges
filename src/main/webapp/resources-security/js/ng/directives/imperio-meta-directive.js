angular.module('app').directive('imperioMeta', function () {
    return {
        restrict: 'E',
        templateUrl: 'resources-security/includes/meta.jsp',
        replace: true,
        link: function (scope, elm, attrs) {
            scope.addMetaItem = function () {
                scope.meta.push({});
            }
            scope.removeMeta = function (pos) {
                scope.meta.splice(pos, 1);
            }
        }
    };
});