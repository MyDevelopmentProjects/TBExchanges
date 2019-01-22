angular.module('app').controller('TTransfersController',
    ['$rootScope', '$scope', '$http', 'GridManager', 'ModalManager', 'FileUploader',
        function ($rootScope, $scope, $http, GridManager, ModalManager, FileUploader) {

            angular.extend($scope, {
                url: 'transfers/list',
                saveURL: 'transfers/put',
                deleteURL: 'transfers/delete',
                ccyListUrl: 'ccy/list',
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
                if($scope.uploader.queue.length > 0){
                    uploader.uploadAll();
                } else {
                    $scope.sendSave();
                }
            };

            $scope.sendSave = function () {
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

            $scope.doFilter = function () {

                var params = [];
                if ($scope.filter !== undefined) {

                    if ($scope.filter.tid) {
                        params.push({key: "tid", value: $scope.filter.tid});
                    } else {
                        if ($scope.filter.userId) {
                            params.push({key: "userId", value: $scope.filter.userId});
                        }

                        if ($scope.filter.clientId) {
                            params.push({key: "clientId", value: $scope.filter.clientId});
                        }

                        if ($scope.filter.companyId) {
                            params.push({key: "companyId", value: $scope.filter.companyId});
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

                }

                $scope.AmfTable.customFilters = params;
                $scope.AmfTable.openPage(0);
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

            $http.get('company/list').success(function (response) {
                $scope.companies = response.results
            });

            $rootScope.$on('customerSelected', function (e, value) {
                $scope.object.customer = value
            });

            
            
            

            var uploader = $scope.uploader = new FileUploader({
                url: 'upload/file'
            });

            // CALLBACKS
            uploader.onBeforeUploadItem = function(item) {
                item.formData.push({type: 'USER_IMG'});
            };

            uploader.onCompleteItem = function(fileItem, response, status, headers) {
                debugger
                if(response.success){
                    if(response.source.length > 0){
                        $scope.object.fileUrl = response.source[0];
                    }
                }
            };

            uploader.onCompleteAll = function() {
                // Clear File Input
                $("input[type='file']").val('').clone(true);
                $scope.sendSave();
            };

            $('#showAddEdit').on("hidden.bs.modal", function(){
                uploader.queue = [];
                // Clear File Input
                $("input[type='file']").val('').clone(true);
            });


        }]);