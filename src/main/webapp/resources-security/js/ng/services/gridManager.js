angular.module('app').service('GridManager', function ($http, Excel, $cacheFactory) {

    this.clearCache = function (q) {
        var $httpDefaultCache = $cacheFactory.get('$http');
        $httpDefaultCache.remove(q);
    };

    this.givePowerTo = function ($scope, pageSize) {
        setTimeout(function () {
            var scope = angular.element($(".top-menu-items")).scope();
            if (scope) {
                scope.app.topMenuItems = [];
            }
        }, 1100);
        $scope.AmfTable = {};
        if (pageSize != undefined) {
            $scope.AmfTable.pageSize = pageSize;
        } else {
            $scope.AmfTable.pageSize = 10;
        }
        $scope.AmfTable.openPage = this.openPage;
        $scope.AmfTable.pageChanged = this.pageChanged;
        $scope.AmfTable.sortBy = this.sortBy;
        $scope.AmfTable.reloadData = this.reloadData;
        $scope.AmfTable.invalidateCache = this.invalidateCache;
        $scope.AmfTable.textSearch = this.textSearch;
        $scope.AmfTable.textSearchExecute = this.textSearchExecute;
        $scope.AmfTable.pageSetUp = this.pageSetUp;
        $scope.AmfTable.setSelected = this.setSelected;
        $scope.AmfTable.selected = 0;
        $scope.AmfTable.scope = $scope;
        $scope.AmfTable.exportToExcel = this.exportToExcel;
        $scope.selected = 0;
        $scope.meta = [];
    };

    this.exportToExcel = function (tableId) {
        var exportHref = Excel.tableToExcel(tableId, 'sheet name');
        setTimeout(function () {
            location.href = exportHref;
        }, 100);
    };

    this.setSelected = function ($index) {
        this.scope.selected = $index;
    };

    this.pageChanged = function (page) {
        var table = this.scope;
        table.AmfTable.openPage(page - 1);
    };

    this.openPage = function (page) {
        var table = this.scope;
        table.AmfTable.page = page;
        table.AmfTable.currentPage = page + 1;
        table.AmfTable.reloadData();
    };

    this.textSearch = function (typeTimeOut) {
        var table = this.scope;
        if (typeTimeOut == undefined) {
            typeTimeOut = 300;
        }
        table.AmfTable.lastTypeTime = (new Date()).getTime();
        var searchStr = angular.copy(table.AmfTable.searchString);
        setTimeout(function () {
            table.AmfTable.textSearchExecute(table, typeTimeOut, searchStr);
        }, typeTimeOut + 50);
    };

    this.textSearchExecute = function (table, typeTimeOut, searchString) {
        if (this.searchString != searchString) return;
        var curTime = (new Date()).getTime();
        if (curTime - table.AmfTable.lastTypeTime >= typeTimeOut) {
            table.AmfTable.page = 0;
            table.AmfTable.currentPage = 1;
            table.AmfTable.reloadData();
        }
    };

    this.sortBy = function (sortBy, isAsc, ignore) {
        var table = this.scope;
        if (table.AmfTable.sortColumn == sortBy) {
            if(!ignore){
                table.AmfTable.sortDir = table.AmfTable.sortDir == 'asc' ? 'desc' : 'asc';
            } else {
                table.AmfTable.sortDir = (isAsc)? 'asc' : 'desc';
            }
        } else {
            table.AmfTable.sortColumn = sortBy;
            if(!ignore){
                table.AmfTable.sortDir = table.AmfTable.sortDir == 'asc' ? 'desc' : 'asc';
            } else {
                table.AmfTable.sortDir = (isAsc)? 'asc' : 'desc';
            }
        }
        table.AmfTable.page = 0;
        table.AmfTable.currentPage = 1;
        table.AmfTable.reloadData(true);
    };

    this.pageSetUp = function () {
        pageSetUp();
    };

    this.invalidateCache = function () {
        $cacheFactory.get('$http').removeAll();
    };

    this.reloadData = function (clear) {
        var table = this.scope;
        var query = [];
        query.pageNumber = table.AmfTable.page >= 0 ? table.AmfTable.page : 0;
        query.sortField = table.AmfTable.sortColumn;
        query.isAscending = (table.AmfTable.sortDir === 'asc');
        query.searchExpression = table.AmfTable.searchString;
        query.pageSize = table.AmfTable.pageSize;
        if (table.AmfTable.customFilters !== undefined) {
            var customFilters = table.AmfTable.customFilters;
            for (var p in customFilters)
                query[customFilters[p].key] = customFilters[p].value;
        }
        if (clear) {
            table.data = [];
            var q = table.url;
            if (this.scope.AmfTable.customFilters !== undefined) {
                var customFilters = this.scope.AmfTable.customFilters;
                q += '?';
                for (var p in customFilters)
                    q = customFilters[p].key + '=' + customFilters[p].value + '&';
            } else {
                q = q + '?';
            }
            q += 'isAscending=' + query.isAscending + '&pageNumber=' + query.pageNumber + '&pageSize=' + query.pageSize + '&sortField=' + query.sortField;
            var str = q.substr(0, q.indexOf('&sortField'));
            var cf = $cacheFactory.get('$http');
            cf.remove(q.split('?')[0]);
            cf.remove(q);
            cf.remove(str);
        }
        $http({
            url: table.url,
            method: 'GET',
            params: query
            /*cache: true*/
        }).success(function (data) {
            if (data.results && data.results.length === 0 && table.AmfTable.page > 0) {
                table.AmfTable.page = 0;
                table.AmfTable.reloadData();
                if (data.results && data.results.length === 0) {

                }
            } else if (data.results && data.results.length === 0) {
                table.AmfTable.page = -1;
                table.AmfTable.totalItems = 0;
                table.data = data;
            } else {
                table.data = data;
                table.AmfTable.totalItems = data.maxPages * table.AmfTable.pageSize;
                if (table.AmfTable.refreshBulkActions !== undefined) {
                    table.AmfTable.refreshBulkActions();
                }
            }
            if (table.AmfTable.doOnReload !== undefined) {
                table.AmfTable.doOnReload();
            }
            table.AmfTable.currentPage = table.AmfTable.page + 1;
        });
    };

    this.loadMetaData = function ($scope, meta) {
        if (meta)
            $scope.meta = JSON.parse(meta);
    };

});