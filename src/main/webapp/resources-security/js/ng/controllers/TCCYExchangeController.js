angular.module('app').controller('TCCYExchangeController',
    ['$scope', '$http', 'GridManager', 'ModalManager', '$rootScope',
    function ($scope, $http, GridManager, ModalManager, $rootScope) {

        angular.extend($scope, {
            url: 'ccyexchange/list',
            saveURL: 'ccyexchange/put',
            deleteURL: 'ccyexchange/delete',
            ccyListUrl: 'ccy/list',
            ccyOnline: 'ccy/rss',
            init: {},
            countries: []
        });

        GridManager.givePowerTo($scope);

        ModalManager.enableModals($scope);

        $scope.AmfTable.openPage(0);

        $scope.showAddEdit = function (item) {
            $scope.object = {};
            if (item) {
                $scope.object = angular.copy(item);
            }
            $('#showAddEdit').modal('show');
        };

        $scope.getSelectedCCYTo = function (id) {
            if (!$scope.ccyList) return "";
            for (var i = 0; i < $scope.ccyList.length; i++) {
                if ($scope.ccyList[i].id === id) {
                    return $scope.ccyList[i].title
                }
            }
            return "";
        };

        $scope.calculate = function () {
            if (!$scope.object ||
                !$scope.object.amount ||
                !$scope.object.fromRate ||
                /*!$scope.object.toRate ||*/
                !$scope.object.ccyFrom ||
                !$scope.object.ccyTo) return 0;

            //IF BOTH ARE THE SAME
            if ($scope.object.ccyFrom.id === $scope.object.ccyTo.id) return $scope.object.amount;


            //IF NONE OF THE CCYs is GEL
            if ($scope.object.ccyFrom && $scope.object.ccyTo &&
                $scope.object.ccyFrom.id != $scope.object.ccyTo.id &&
                $scope.object.ccyFrom.id !== 4 && $scope.object.ccyTo.id !== 4) {
                var lari = $scope.object.amount * $scope.object.fromRate;
                return (lari / $scope.object.toRate).toFixed(2)
            }


            //IF SOURCE IS NOT GEL
            if ($scope.object.ccyFrom.id !== 4 && $scope.object.ccyTo.id === 4) {
                return ($scope.object.amount * $scope.object.fromRate).toFixed(2)
            } else {
                return ($scope.object.amount / $scope.object.fromRate).toFixed(2)
            }

        };

        $scope.save = function () {

            var objectCopy = angular.copy($scope.object);

            if (objectCopy.customer) {
                objectCopy.customer = {id: objectCopy.customer.id}
            }

            if (objectCopy.user) {
                objectCopy.user = {id: objectCopy.user.id}
            }

            if (objectCopy.ccyFrom) {
                objectCopy.ccyFrom = {id: objectCopy.ccyFrom.id}
            }

            if (objectCopy.ccyTo) {
                objectCopy.ccyTo = {id: objectCopy.ccyTo.id}
            }

            $http.post($scope.saveURL, objectCopy).success(function (response) {
                if (!response.success) {
                    return;
                }
                $scope.showSuccessAlert("Success");
                $scope.AmfTable.reloadData(true);
                $('#showAddEdit').modal('hide');
            });
        };

        $scope.delete = function (itemId) {
            $http.post($scope.deleteURL, itemId).success(function (data) {
                if (!data.success) {
                    if (response.errorMessage == "RECORD_IS_USED_IN_OTHER_TABLES") {
                        $scope.showErrorModal("მოცემული ჩანაწერის წაშლა შეუძლებელია რადგან ის ფიქსირდება სხვა ცხრილშიც.")
                    }
                    return;
                }
                $scope.AmfTable.reloadData(true);
            });
        };

        $http.get($scope.ccyListUrl).success(function (response) {
            $scope.ccyList = response.results
        });

        $http.get($scope.ccyOnline).success(function (response) {
            $scope.ccyOnlineList = response
        });

        $rootScope.$on('customerSelected', function (e, value) {
            $scope.object.customer = value
        });

        $scope.doFilter = function () {

            var params = [];
            if ($scope.filter !== undefined) {

                if ($scope.filter.userId) {
                    params.push({key: "userId", value: $scope.filter.userId});
                }

                if ($scope.filter.customerId) {
                    params.push({key: "clientId", value: $scope.filter.customerId});
                }

                if ($scope.filter.ccyFrom) {
                    params.push({key: "ccyFrom", value: $scope.filter.ccyFrom});
                }

                if ($scope.filter.ccyTo) {
                    params.push({key: "ccyTo", value: $scope.filter.ccyTo});
                }

                if ($scope.filter.dateFrom && $scope.filter.dateTo) {
                    params.push({key: "dateFrom", value: $scope.filter.dateFrom});
                    params.push({key: "dateTo", value: $scope.filter.dateTo});
                }
            }

            $scope.AmfTable.customFilters = params;
            $scope.AmfTable.openPage(0);
        };

        $scope.createFabMenuItems = function () {
            setTimeout(function () {
                var add = $scope.showAddEdit;
                var scope = angular.element($(".fabmenu")).scope();
                if (scope !== undefined) {
                    scope.$apply(function () {
                        scope.app.fabScope = $scope;
                        $scope.fabMenuItems = scope.app.fabMenuItems = [{callback: add, "fa": "plus", "title": "Add"}];
                    });
                }
            }, 1000);
        };

        $scope.createFabMenuItems();

    }]);