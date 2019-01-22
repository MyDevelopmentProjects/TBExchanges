<nav class="menu fabmenu" ng-class="{'menu-folded' : !app.setting.folded}">
    <input type="checkbox" href="#" class="menu-open" name="menu-open" id="menu-open"/>
    <label class="menu-open-button" for="menu-open">
        <span class="hamburger hamburger-1"></span>
        <span class="hamburger hamburger-2"></span>
        <span class="hamburger hamburger-3"></span>
        <span class="hamburger hamburger-4"></span>
    </label>
    <a class="menu-item" ng-click="AmfTable.exportToExcel('.table.table-bordered')"> <i class="fa fa-file-excel-o"></i> </a>
    <a class="menu-item" data-toggle="modal" ng-repeat="item in app.fabMenuItems" ng-click="app.evalFabFunc($index)"
       ui-toggle-class="fade-right-big" ui-target="#animate"> <i class="fa fa-{{item.fa}}"></i>
    </a>
</nav>