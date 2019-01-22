angular.module('app').directive('imperioSortableBy', function ($compile) {
    function link($scope, $element, $attrs) {
        var columnName = $element.attr('column-name') == undefined ? $element.html() : $element.attr('column-name');
        $element.attr('column-name', columnName);
        var sortIcon = '&nbsp;<i class="fa fa-sort imperio-sort-i"></i>';
        if ($scope.AmfTable.sortColumn == $attrs.imperioSortableBy) {
            if ($scope.AmfTable.sortDir == 'asc') {
                sortIcon = '&nbsp;<i class="fa fa-sort-desc imperio-sort-i"></i>';
            } else {
                sortIcon = '&nbsp;<i class="fa fa-sort-asc imperio-sort-i"></i>';
            }
        }
        $element.html(columnName + sortIcon);
        $element.off('click');
        $element.on('click', function () {
            $scope.AmfTable.sortBy($attrs.imperioSortableBy);
            $compile($($element))($scope);
        });
    }
    return {
        link: link
    };
});
