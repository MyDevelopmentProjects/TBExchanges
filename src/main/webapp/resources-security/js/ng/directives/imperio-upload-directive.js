angular.module('app').directive('imperioUpload', function ($rootScope, upload) {
    return {
        restrict: 'A',
        scope: true,
        link: function (scope, element, attr) {
            element.bind('change', function () {
                var formData = new FormData();
                for (var file in element[0].files) {
                    formData.append('file', element[0].files[file]);
                }
                var type = $(element).data('type');
                var org = $(element).data('org');
                formData.append('type', type);
                if (org) formData.append('organisation', org);
                else formData.append('organisation', "");
                upload('upload/file', formData, function (callback) {
                    $rootScope.$broadcast('upload-finished', {data: callback});
                });
            });
        }
    };
});
angular.module('app').factory('upload', function ($http) {
    return function (file, data, callback) {
        $http({
            url: file,
            method: "POST",
            data: data,
            headers: {'Content-Type': undefined}
        }).success(function (response) {
            callback(response);
        });
    };
});
angular.module('app').directive("compareTo", function () {
    return {
        require: "ngModel",
        scope: {
            otherModelValue: "=compareTo"
        },
        link: function (scope, element, attributes, ngModel) {

            ngModel.$validators.compareTo = function (modelValue) {
                return modelValue == scope.otherModelValue;
            };

            scope.$watch("otherModelValue", function () {
                ngModel.$validate();
            });
        }
    };
});
