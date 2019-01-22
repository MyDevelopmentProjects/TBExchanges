(function () {
    'use strict';
    angular
        .module('app')
        .run(runBlock)
        .constant('urls', {
            BASE_API: '/'
        })
        .config(config);

    runBlock.$inject = ['$rootScope', '$state', '$stateParams', '$localStorage', 'Access'];

    function runBlock($rootScope, $state, $stateParams, $localStorage, Access) {
        $rootScope.$state = $state;
        $rootScope.$stateParams = $stateParams;

        $rootScope.$on('$stateChangeStart', function (event, toState, toParams, fromState, fromParams, error) {
            $state.current = toState;
            if ($rootScope.loggedIn && $localStorage.token && toState.name == "app.signin") {
                event.preventDefault();
                $state.go('app.dashboard');
            }
        });

        $rootScope.$on('$stateChangeSuccess', function (event, toState, toParams, fromState, fromParams) {
            $state.current = toState;
        });

        $rootScope.$on("$stateChangeError", function (event, toState, toParams, fromState, fromParams, error) {
            $state.current = toState;
            $state.current = toState;
            if (error === Access.UNAUTHORIZED) {
                event.preventDefault();
                delete $localStorage.token;
                $rootScope.loggedIn = false;
                $.notify("UNAUTHORIZED", "error");
                $state.go('app.signin');
            } else if (error <= Access.SERVER_IS_DOWN) {
                event.preventDefault();
                delete $localStorage.token;
                $rootScope.loggedIn = false;
                $.notify("SERVER_IS_DOWN", "error");
                $state.go('app.signin');
            }
        });

        $rootScope.$on("success:logout", function (event, data) {
            $state.go('app.signin');
        });

        $rootScope.$on("success:auth", function (event, data) {
            $state.go('app.dashboard');
        });
    }

    config.$inject = ['$stateProvider', '$urlRouterProvider', 'MODULE_CONFIG'];

    function config($stateProvider, $urlRouterProvider, MODULE_CONFIG) {

        $urlRouterProvider.otherwise('/app/dashboard');

        $stateProvider
            .state('app', {
                abstract: true,
                url: '/app',
                views: {
                    '': {
                        templateUrl: '/admin/view/Default/aside.html'
                    }
                }
            })
            .state('app.dashboard', {
                url: '/dashboard',
                templateUrl: '/admin/view/Default/index.html',
                data: {title: 'TBExchange Home Page'},
                controller: 'DefaultController',
                resolve: {
                    userProfile: "UserProfile",
                    access: ["Access", function (Access) {
                        return Access.hasRole("ADMIN");
                    }],
                    loadMyFiles: function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            name: "app",
                            files: [
                                "/admin/js/ng/controllers/DefaultController.js"
                            ]
                        });
                    }
                }
            })
            .state('app.signin', {
                url: '/signin',
                controller: 'SignInController',
                resolve: {
                    userProfile: "UserProfile",
                    loadMyFiles: function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            name: "app",
                            files: [
                                "/admin/js/ng/controllers/SignInController.js"
                            ]
                        });
                    }
                },
                templateUrl: '/admin/view/Login/index.html'
            })

            .state('app.TCCY', {
                url: '/TCCY',
                templateUrl: '/admin/view/TCCY/index.html',
                data: {title: 'ვალუტები'},
                controller: 'TCCYController',
                resolve: load(["admin/js/ng/controllers/TCCYController.js"])
            })

            .state('app.TCCYExchange', {
                url: '/TCCYExchange',
                templateUrl: '/admin/view/TCCYExchange/index.html',
                data: {title: 'გადაცვლა'},
                controller: 'TCCYExchangeController',
                resolve: load(
                    [
                        "admin/js/ng/controllers/TCCYExchangeController.js",
                        "admin/js/ng/controllers/TCustomerController.js"
                    ]
                )
            })

            .state('app.TCCYExchangeHistory', {
                url: '/TCCYExchangeHistory',
                templateUrl: '/admin/view/TCCYExchangeHistory/index.html',
                data: {title: 'ტრანსფერების ისტორია'},
                controller: 'TCCYExchangeHistoryController',
                resolve: load(["admin/js/ng/controllers/TCCYExchangeHistoryController.js"])
            })

            .state('app.TCompany', {
                url: '/TCompany',
                templateUrl: '/admin/view/TCompany/index.html',
                data: {title: 'კომპანიები'},
                controller: 'TCompanyController',
                resolve: load(["admin/js/ng/controllers/TCompanyController.js"])
            })

            .state('app.TCustomer', {
                url: '/TCustomer',
                templateUrl: '/admin/view/TCustomer/index.html',
                data: {title: 'კლიენტები'},
                controller: 'TCustomerController',
                resolve: load(["admin/js/ng/controllers/TCustomerController.js"])
            })

            .state('app.TTransfers', {
                url: '/TTransfers',
                templateUrl: '/admin/view/TTransfers/index.html',
                data: {title: 'ტრანსფერები'},
                controller: 'TTransfersController',
                resolve: load(["admin/js/ng/controllers/TTransfersController.js",
                               "admin/js/ng/controllers/TCustomerController.js"])
            })

            .state('app.main', {
                url: '/main',
                template: '<div ui-view></div>'
            })

            .state('app.user', {
                url: '/user',
                template: '<div ui-view></div>'
            })
            .state('app.user.list', {
                url: '/list',
                templateUrl: '/admin/view/User/index.html',
                data: {title: 'List Of Users'},
                controller: 'UserController',
                resolve: {
                    loadMyFiles: function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            name: "app",
                            files: [
                                "admin/js/ng/controllers/UserController.js"
                            ]
                        });
                    }
                }
            })
            .state('forbidden', {
                url: '/forbidden',
                templateUrl: '/resources/admin/view/Forbidden/index.html'
            })
            // ui router
            .state('app.layout', {
                url: '/layout',
                template: '<div ui-view></div>'
            })
            .state('app.ui', {
                url: '/ui',
                template: '<div ui-view></div>'
            })
            .state('app.form', {
                url: '/form',
                template: '<div ui-view></div>'
            })
            .state('app.table', {
                url: '/table',
                template: '<div ui-view></div>'
            })
            .state('access', {
                url: '/access',
                template: '<div class="dark bg-auto w-full"><div ui-view class="fade-in-right-big smooth pos-rlt"></div></div>'
            })


        function load(srcs, callback) {
            return {
                deps: ['$ocLazyLoad', '$q',
                    function ($ocLazyLoad, $q) {
                        var deferred = $q.defer();
                        var promise = false;
                        srcs = angular.isArray(srcs) ? srcs : srcs.split(/\s+/);
                        if (!promise) {
                            promise = deferred.promise;
                        }
                        angular.forEach(srcs, function (src) {
                            promise = promise.then(function () {
                                angular.forEach(MODULE_CONFIG, function (module) {
                                    if (module.name == src) {
                                        src = module.module ? module.name : module.files;
                                    }
                                });
                                return $ocLazyLoad.load(src);
                            });
                        });
                        deferred.resolve();
                        return callback ? promise.then(function () {
                            return callback();
                        }) : promise;
                    }]
            }
        }
    }
})();
