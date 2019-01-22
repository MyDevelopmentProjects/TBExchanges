angular.module('app').factory('httpInterceptor', function ($q, $rootScope, $localStorage) {
        var numLoadings = 0;
        return {
            request: function (config) {
                numLoadings++;
                $rootScope.$broadcast("loader_show");

                config.headers = config.headers || {};
                //delete $localStorage.token;
                if ($localStorage.token) {
                    config.headers['Authorization'] = $localStorage.token;
                    $rootScope.loggedIn = true;
                } else {
                    $rootScope.loggedIn = false;
                }
                return config;
            },
            response: function (response) {
                if ((--numLoadings) === 0) {
                    $rootScope.$broadcast("loader_hide");
                }
                return response || $q.when(response);
            },
            responseError: function(rejection) {
                if (!(--numLoadings)) {
                    $rootScope.$broadcast("loader_hide");
                }
                // otherwise, default behaviour
                return $q.reject(rejection);
            }
        };
    })
    .config(function ($httpProvider) {
        $httpProvider.interceptors.push('httpInterceptor');
    })
    .directive("loader", function ($rootScope) {
        return function ($scope, element, attrs) {
            $scope.$on("loader_show", function () {
                return element.show();
            });
            return $scope.$on("loader_hide", function () {
                return element.hide();
            });
        };
    });