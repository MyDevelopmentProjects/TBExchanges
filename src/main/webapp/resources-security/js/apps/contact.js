(function () {
    'use strict';
    angular
        .module('app')
        .controller('ContactCtrl', ContactCtrl);

    function ContactCtrl($scope, $http, $filter) {

        $scope.items = [];
        $scope.filter = '';
        $scope.selectOne = function () {
            $scope.items.length && selectItem($filter('orderBy')($scope.items, 'first')[0]);
        };
        $scope.getMembers = function () {
            $http.get('member/list').then(function (resp) {
                $scope.items = resp.data.results;
                $scope.selectOne();
            });
        };
        $scope.checkItem = function (obj, arr, key) {
            var i = 0;
            angular.forEach(arr, function (item) {
                if (item[key].indexOf(obj[key]) == 0) {
                    var j = item[key].replace(obj[key], '').trim();
                    if (j) {
                        i = Math.max(i, parseInt(j) + 1);
                    } else {
                        i = 1;
                    }
                }
            });
            return obj[key] + (i ? ' ' + i : '');
        }
        $scope.selectItem = function(item) {
            angular.forEach($scope.items, function (item) {
                item.selected = false;
                item.editing = false;
            });
            $scope.item = item;
            $scope.item.selected = true;
        }
        $scope.deleteItem = function(item) {
            $scope.items.splice($scope.items.indexOf(item), 1);
            selectOne();
        }
        $scope.createItem = function() {
            var item = {
                avatar: '../assets/images/a0.jpg'
            };
            $scope.items.push(item);
            $scope.selectItem(item);
            $scope.item.editing = true;
        }
        $scope.editItem = function(item) {
            if (item && item.selected) {
                item.editing = true;
            }
        }
        $scope.doneEditing = function(item) {
            item.editing = false;
        }
        $scope.getMembers();

    }
})();
