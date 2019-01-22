angular.module('app').controller('GTransactionsController',
    ['$scope', '$http', 'GridManager', 'ModalManager', function ($scope, $http, GridManager, ModalManager) {

        angular.extend($scope, {
            transList: 'transactions/list',
            exportLink: 'transactions/export',
            transactionList:[
                {id:"GENERATED", name:"დაგენერირდა"},
                {id:"FINISHED_SUCCESS", name:"დასრულდა წარმატებით"},
                {id:"FINISHED_FAIL", name:"დასრულდა შეცდომით"}
            ],
            init: {}
        });

        GridManager.givePowerTo($scope);
        ModalManager.enableModals($scope);

        $scope.filter = function () {
            var objCopy = angular.copy($scope.object);
            $scope.url = $scope.transList + "?" + $.param(objCopy);
            $scope.AmfTable.openPage(0);
        };

        $scope.view = function (item) {
            $("#showInvoice").modal("show");
            $scope.obj = {};
            var obj = angular.copy(item);
            $scope.obj = obj;
        };

        $scope.exportData = function () {
            var objCopy = angular.copy($scope.object);
            $scope.downloadAll([
                $scope.exportLink + "?" + $.param(objCopy)
            ]);
        };

        $scope.downloadAll = function(urls) {
            var link = document.createElement('a');
            link.setAttribute('download', null);
            link.style.display = 'none';
            document.body.appendChild(link);
            for (var i = 0; i < urls.length; i++) {
                link.setAttribute('href', urls[i]);
                link.click();
            }
            document.body.removeChild(link);
        };

    }]);