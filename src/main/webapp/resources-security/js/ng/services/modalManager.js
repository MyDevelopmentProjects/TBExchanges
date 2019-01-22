angular.module('app').service('ModalManager', function ($rootScope, $sce) {

    this.enableModals = function ($scope) {
        $scope.showSuccessAlert = this.showSuccessAlert;
        $scope.showErrorModal = this.showErrorModal;
        setTimeout(function () {
            $('.ui-datepicker').hide();
        }, 200);
    };

    this.showSuccessAlert = function (title) {
        $.notify(title, "success");
    };

    this.showErrorModal = function (title) {
        $.notify(title, "error");
    };

});