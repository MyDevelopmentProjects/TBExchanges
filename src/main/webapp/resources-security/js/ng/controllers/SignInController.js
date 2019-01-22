
angular.module('app').controller('SignInController',
    ['$scope', '$http', '$state', 'Auth', 'userProfile', function ($scope, $http, $state, Auth, userProfile) {

        $scope.auth = function () {
            var formData = {
                username: $scope.auth.username,
                password: $scope.auth.password
            };

            Auth.auth(formData).then(function(response){
                if(!response.data || !response.data.source.token){
                    var message = "";
                    if(response.data.message !== undefined){
                        message = response.data.message;
                        swal({
                            title:"",
                            type:"error",
                            text:message
                        });
                    }
                    else if(response.data.errors !== undefined && response.data.errors.length > 0){
                        var errorMessage = "";
                        for (var s in response.data.errors) {
                            errorMessage += response.data.errors[s] + ",<br> ";
                        }
                        swal({
                            title:"",
                            type:"error",
                            text:errorMessage,
                            html:true
                        });
                    }
                }  else if(response.data.source.token !== undefined){
                    userProfile.$refresh().then(function () {
                        $state.go("app.dashboard");
                    });
                }
            });
        };


    }]);
