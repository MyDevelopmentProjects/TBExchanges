angular.module('app').controller('UserController',
    ['$scope', '$http', 'GridManager', 'ModalManager', 'FileUploader',
        function ($scope, $http, GridManager, ModalManager, FileUploader) {

            angular.extend($scope, {
                url: 'user/list',
                saveURL: 'user/put',
                deleteURL: 'user/delete',
                roleURL: 'role/list',
                imageUploaded: false,
                init: {},
                cities: [],
                users: [],
                tags: []
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
                var userCopy = angular.copy($scope.object);

                if (userCopy.newpassword && userCopy.newpassword.length >= 8) {
                    userCopy.password = userCopy.newpassword;
                    delete userCopy.newpassword;
                    delete userCopy.verifynewpassword;
                }
                delete userCopy.verify_password;
                userCopy.role = { id : 2 }
                userCopy.fullName = userCopy.firstName + " " + userCopy.lastName
                $http.post($scope.saveURL, userCopy).success(function (response) {
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
                    scope.$apply(function () {
                        scope.app.fabScope = $scope;
                        $scope.fabMenuItems = scope.app.fabMenuItems = [
                            {
                                callback: add,
                                "fa": "plus",
                                "title": "Add"
                            }
                        ];
                    });
                }, 2000);
            };

            $scope.createFabMenuItems();

        }]);
