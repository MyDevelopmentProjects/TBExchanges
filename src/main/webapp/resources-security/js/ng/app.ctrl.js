(function () {
    'use strict';
    angular
        .module('app')
        .service("Auth", ["$http", "$rootScope", "$localStorage", "urls",
            function ($http, $rootScope, $localStorage, urls) {

                function urlBase64Decode(str) {
                    var output = str.replace('-', '+').replace('_', '/');
                    switch (output.length % 4) {
                        case 0:
                            break;
                        case 2:
                            output += '==';
                            break;
                        case 3:
                            output += '=';
                            break;
                        default:
                            throw 'Illegal base64url string!';
                    }
                    return window.atob(output);
                }

                function getClaimsFromToken() {
                    var token = $localStorage.token;
                    var user = {};
                    if (typeof token !== 'undefined') {
                        var encoded = token.split('.')[1];
                        user = JSON.parse(urlBase64Decode(encoded));
                    }
                    return user;
                }

                var tokenClaims = getClaimsFromToken();

                return {
                    me: function () {
                        return $http.get(urls.BASE_API + 'auth').success(function (response) {
                            if (!response.success) {
                                delete $localStorage.token;
                                $rootScope.loggedIn = false;
                            }
                        });
                    },
                    auth: function (data) {
                        return $http.post(urls.BASE_API + 'auth', data).success(function (response) {
                            delete $localStorage.token;
                            if (response.source.token) {
                                $localStorage.token = response.source.token;
                            }
                        }).error(function (response) {
                            if (response === null) {
                                $.notify("ERR_CONNECTION_REFUSED", "error");
                            }
                        });
                    },
                    logout: function () {
                        tokenClaims = {};
                        delete $localStorage.token;
                        return $http.get(urls.BASE_API + 'auth/logout').success(function (response) {
                            $rootScope.$broadcast("success:logout");
                        });
                    },
                    getTokenClaims: function () {
                        return tokenClaims;
                    },
                    isAuth: function () {
                        var data = getClaimsFromToken();
                        if (data.exp) {
                            return Math.round(new Date().getTime() / 1000) <= data.exp;
                        } else {
                            return false;
                        }
                    }
                };
            }])
        .factory("UserProfile", ["$http", "Auth", 'urls', function ($http, Auth, urls) {
            var userProfile = {};

            var fetchUserProfile = function () {
                return Auth.me().then(function (response) {
                    for (var prop in userProfile) {
                        if (userProfile.hasOwnProperty(prop)) {
                            delete userProfile[prop];
                        }
                    }
                    return angular.extend(userProfile, response.data.source.user, {
                        $refresh: fetchUserProfile,
                        $hasRole: function (role) {
                            if (userProfile.authorities === undefined) {
                                return false;
                            }
                            for (var i in userProfile.authorities) {
                                var roleName = userProfile.authorities[i].authority;
                                if (roleName === role) {
                                    return true;
                                }
                            }
                            return false;
                        },
                        $hasAnyRole: function (roles) {
                            if (userProfile.authorities === undefined) {
                                return false;
                            }
                            return !!userProfile.authorities.filter(function (item) {
                                return roles.indexOf(item.authority) >= 0;
                            }).length;
                        },
                        $isAuthenticated: function () {
                            return true;
                        }
                    });
                });
            };
            return fetchUserProfile();
        }])
        .factory("Access", ["$q", "UserProfile", function ($q, UserProfile) {
            var Access = {
                OK: 200,
                UNAUTHORIZED: 401,
                FORBIDDEN: 403,
                SERVER_IS_DOWN: 0,
                hasRole: function (role) {
                    return UserProfile.then(function (userProfile) {
                        if (userProfile.$hasRole(role)) {
                            return Access.OK;
                        } else if (userProfile.$isAuthenticated()) {
                            return $q.reject(Access.UNAUTHORIZED);
                        }
                    });
                },

                hasAnyRole: function (roles) {
                    return UserProfile.then(function (userProfile) {
                        if (userProfile.$hasAnyRole(roles)) {
                            return Access.OK;
                        } else if (userProfile.$isAuthenticated()) {
                            return $q.reject(Access.UNAUTHORIZED);
                        }
                    });
                }
            };
            return Access;
        }])
        .directive("hasRole", function (UserProfile) {
            return {
                link: function (scope, element, attrs) {
                    UserProfile.then(function (userProfile) {
                        if (userProfile.$hasRole(attrs.hasRole)) {
                            element.show();
                        } else {
                            element.hide();
                        }
                    });
                }
            }
        })
        .directive("hasAnyRole", function (UserProfile) {
            return {
                link: function (scope, element, attrs) {
                    UserProfile.then(function (userProfile) {
                        var roles = attrs.hasAnyRole.split(',');
                        if (userProfile.$hasAnyRole(roles)) {
                            element.show();
                        } else {
                            element.hide();
                        }
                    });
                }
            }
        })
        .controller('AppCtrl', AppCtrl)
    AppCtrl.$inject = ['$scope', '$localStorage', '$location', '$rootScope', '$anchorScroll', '$timeout', '$window', '$cacheFactory', '$state', '$q', 'Auth'];

    function AppCtrl($scope, $localStorage, $location, $rootScope, $anchorScroll, $timeout, $window, $cacheFactory, $state, $q, Auth) {
        var vm = $scope;
        vm.isIE = isIE();
        vm.isSmart = isSmart();
        // config
        vm.app = {
            name: 'TBExchange',
            version: '1.0.0',
            // for chart colors
            color: {
                'primary': '#0cc2aa',
                'accent': '#a88add',
                'warn': '#fcc100',
                'info': '#6887ff',
                'success': '#6cc788',
                'warning': '#f77a99',
                'danger': '#f44455',
                'white': '#ffffff',
                'light': '#f1f2f3',
                'dark': '#2e3e4e',
                'black': '#2a2b3c'
            },
            setting: {
                theme: {
                    primary: 'primary',
                    accent: 'accent',
                    warn: 'warn'
                },
                folded: false,
                boxed: false,
                container: false,
                themeID: 1,
                bg: ''
            },
            topMenuItems: [],
            fabMenuItems: [],
            outerScope: {},
            fabScope: {},
            evalFunc: function (i) {
                var item = this.outerScope.topMenuItems[i];
                item.callback(item.param);
            },
            evalFabFunc: function (i) {
                var item = this.fabScope.fabMenuItems[i];
                item.callback(item.param);
            }
        };

        $scope.invalidateCache = function () {
            $cacheFactory.get('$http').removeAll();
        };

        $scope.logout = function () {
            Auth.logout();
        };

        var setting = vm.app.name + '-Setting';
        // save settings to local storage
        if (angular.isDefined($localStorage[setting])) {
            vm.app.setting = $localStorage[setting];
        } else {
            $localStorage[setting] = vm.app.setting;
        }
        // watch changes
        $scope.$watch('app.setting', function () {
            $localStorage[setting] = vm.app.setting;
        }, true);

        getParams('bg') && (vm.app.setting.bg = getParams('bg'));

        vm.setTheme = setTheme;
        setColor();

        function setTheme(theme) {
            vm.app.setting.theme = theme.theme;
            setColor();
            if (theme.url) {
                $timeout(function () {
                    $window.location.href = theme.url;
                }, 100, false);
            }
        }

        function setColor() {
            vm.app.setting.color = {
                primary: getColor(vm.app.setting.theme.primary),
                accent: getColor(vm.app.setting.theme.accent),
                warn: getColor(vm.app.setting.theme.warn)
            };
        }

        function getColor(name) {
            return vm.app.color[name] ? vm.app.color[name] : palette.find(name);
        }

        $rootScope.$on('$stateChangeSuccess', openPage);

        function openPage() {
            // goto top
            $('.top-menu-items').hide();
            $location.hash('content');
            $anchorScroll();
            $location.hash('');
            // hide open menu
            $('#aside').modal('hide');
            $('body').removeClass('modal-open').find('.modal-backdrop').remove();
            $('.navbar-toggleable-sm').collapse('hide');
        }

        vm.goBack = function () {
            $window.history.back();
        };

        function isIE() {
            return !!navigator.userAgent.match(/MSIE/i) || !!navigator.userAgent.match(/Trident.*rv:11\./);
        }

        function isSmart() {
            // Adapted from http://www.detectmobilebrowsers.com
            var ua = $window['navigator']['userAgent'] || $window['navigator']['vendor'] || $window['opera'];
            // Checks for iOs, Android, Blackberry, Opera Mini, and Windows mobile devices
            return (/iPhone|iPod|iPad|Silk|Android|BlackBerry|Opera Mini|IEMobile/).test(ua);
        }

        function getParams(name) {
            name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
            var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
                results = regex.exec(location.search);
            return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
        }


    }
})();


/*
Options:
wordwise (boolean) - if true, cut only by words bounds,
max (integer) - max length of the text, cut to this number of chars,
tail (string, default: ' …') - add this string to the input string if the string was cut.
* */
angular.module('app').filter('cut', function () {
    return function (value, wordwise, max, tail) {
        if (!value) return '';

        max = parseInt(max, 10);
        if (!max) return value;
        if (value.length <= max) return value;

        value = value.substr(0, max);
        if (wordwise) {
            var lastspace = value.lastIndexOf(' ');
            if (lastspace !== -1) {
                //Also remove . and , so its gives a cleaner result.
                if (value.charAt(lastspace - 1) === '.' || value.charAt(lastspace - 1) === ',') {
                    lastspace = lastspace - 1;
                }
                value = value.substr(0, lastspace);
            }
        }

        return value + (tail || ' …');
    };
});