angular.module('app').controller('TCompanyController', ['$scope', '$http', 'GridManager', 'ModalManager',
    function ($scope, $http, GridManager, ModalManager) {
    angular.extend($scope, {
        url: 'company/list',
        saveURL: 'company/put',
        deleteURL: 'company/delete',
        init: {},
        countries: []
    });
    GridManager.givePowerTo($scope);
    ModalManager.enableModals($scope);
    $scope.AmfTable.openPage(0);
    $scope.showAddEdit = function (item) {
        $scope.init.action = item ? 'რედაქტირება' : 'დამატება';
        $scope.object = {};
        if (item) {
            $scope.object = angular.copy(item);
        }
        $('#showAddEdit').modal('show');
    };
    $scope.save = function () {
        var objectCopy = angular.copy($scope.object);
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