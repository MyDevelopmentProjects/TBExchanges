// lazyload config
(function() {
    'use strict';
    angular
      .module('app')
      .constant('MODULE_CONFIG', [
          /*{
              name: 'mgcrea.ngStrap',
              module: true,
              serie: true,
              files: [
                  'resources/assets/angular-motion/dist/angular-motion.min.css',
                  'resources/assets/bootstrap-additions/dist/bootstrap-additions.min.css',
                  'resources/libs/angular/angular-strap/dist/angular-strap.js',
                  'resources/libs/angular/angular-strap/dist/angular-strap.tpl.js'
              ]
          },*/
     /*     {
              name: 'ui.bootstrap',
              module: true,
              serie: true,
              files: [
                  'resources/libs/angular/angular-bootstrap/ui-bootstrap-tpls.min.js',
                  'resources/libs/angular/angular-bootstrap/ui-bootstrap-tpls.js'
              ]
          },*/
          {
              name: 'ui.select',
              module: true,
              files: [
                  'admin/libs/angular/angular-ui-select/dist/select.min.js',
                  'admin/libs/angular/angular-ui-select/dist/select.min.css'
              ]
          },
          {
              name: 'vr.directives.slider',
              module: true,
              files: [
                  'resources/libs/angular/venturocket-angular-slider/build/angular-slider.min.js',
                  'resources/libs/angular/venturocket-angular-slider/angular-slider.css'
              ]
          },
          {
              name: 'angularBootstrapNavTree',
              module: true,
              files: [
                  'resources/libs/angular/angular-bootstrap-nav-tree/dist/abn_tree_directive.js',
                  'resources/libs/angular/angular-bootstrap-nav-tree/dist/abn_tree.css'
              ]
          },
          {
              name: 'angularFileUpload',
              module: true,
              files: [
                  'resources/libs/angular/angular-file-upload/angular-file-upload.js'
              ]
          },
          {
              name: 'ngImgCrop',
              module: true,
              files: [
                  'resources/libs/angular/ngImgCrop/compile/minified/ng-img-crop.js',
                  'resources/libs/angular/ngImgCrop/compile/minified/ng-img-crop.css'
              ]
          },
          {
              name: 'smart-table',
              module: true,
              files: [
                  'resources/libs/angular/angular-smart-table/dist/smart-table.min.js'
              ]
          },
          {
              name: 'ui.map',
              module: true,
              files: [
                  'libs/angular/angular-ui-map/ui-map.js'
              ]
          },
          {
              name: 'ui.grid',
              module: true,
              files: [
                  'resources/libs/angular/angular-ui-grid/ui-grid.min.js',
                  'resources/libs/angular/angular-ui-grid/ui-grid.min.css',
                  'resources/libs/angular/angular-ui-grid/ui-grid.bootstrap.css'
              ]
          },
          {
              name: 'xeditable',
              module: true,
              files: [
                  'resources/libs/angular/angular-xeditable/dist/js/xeditable.min.js',
                  'resources/libs/angular/angular-xeditable/dist/css/xeditable.css'
              ]
          },
          {
              name: 'smart-table',
              module: true,
              files: [
                  'resources/libs/angular/angular-smart-table/dist/smart-table.min.js'
              ]
          },
          {
              name:'ui.calendar',
              module: true,
              files: ['/resources/libs/angular/angular-ui-calendar/src/calendar.js']
          },
          {
              name:'ui.calendarNew',
              module: true,
              files: []
          },
          {
              name:'summernote',
              module: true,
              files: [
                'libs/jquery/summernote/dist/summernote.css',
               /* 'libs/jquery/summernote/dist/summernote.js',*/
                'libs/angular/angular-summernote/dist/angular-summernote.js'
              ]
          },
          {
              name: 'dataTable',
              module: false,
              files: [
                  'resources/libs/jquery/datatables/media/js/jquery.dataTables.min.js',
                  'resources/libs/jquery/plugins/integration/bootstrap/3/dataTables.bootstrap.js',
                  'resources/libs/jquery/plugins/integration/bootstrap/3/dataTables.bootstrap.css'
              ]
          },
          {
              name: 'footable',
              module: false,
              files: [
                  'resources/libs/jquery/footable/dist/footable.all.min.js',
                  'resources/libs/jquery/footable/css/footable.core.css'
              ]
          },
          {
              name: 'easyPieChart',
              module: false,
              files: [
                  'resources/libs/jquery/jquery.easy-pie-chart/dist/jquery.easypiechart.fill.js'
              ]
          },
          /*{
              name: 'sparkline',
              module: false,
              files: [
                  'resources/libs/jquery/jquery.sparkline/dist/jquery.sparkline.retina.js'
              ]
          },*/
          {
              name: 'plot',
              module: false,
              files: [
                  'admin/libs/jquery/flot/jquery.flot.js',
                  'admin/libs/jquery/flot/jquery.flot.resize.js',
                  'admin/libs/jquery/flot/jquery.flot.pie.js',
                  'admin/libs/jquery/flot.tooltip/js/jquery.flot.tooltip.min.js',
                  'admin/libs/jquery/flot-spline/js/jquery.flot.spline.min.js',
                  'admin/libs/jquery/flot.orderbars/js/jquery.flot.orderBars.js'
              ]
          },
          {
              name: 'icon-picker',
              module: true,
              files:[
                  'libs/angular/angular-ui-icon-picker/ui-iconpicker.css',
                  'libs/angular/angular-ui-icon-picker/ui-iconpicker.min.js'
              ]

          },
          {
              name: 'vectorMap',
              module: false,
              files: [
                  'resources/libs/jquery/bower-jvectormap/jquery-jvectormap-1.2.2.min.js',
                  'resources/libs/jquery/bower-jvectormap/jquery-jvectormap.css', 
                  'resources/libs/jquery/bower-jvectormap/jquery-jvectormap-world-mill-en.js',
                  'resources/libs/jquery/bower-jvectormap/jquery-jvectormap-us-aea-en.js'
              ]
          },
          /*{
              name: 'moment',
              module: false,
              files: [
                  'resources/libs/js/moment/moment.js'
              ]
          },
          {
              name: 'fullcalendar',
              module: false,
              files: [
                  'resources/libs/jquery/fullcalendar/dist/fullcalendar.min.js',
              ]
          },*/
          {
              name: 'sortable',
              module: false,
              files: [
                  'resources/libs/jquery/html.sortable/dist/html.sortable.min.js'
              ]
          },
          {
              name: 'nestable',
              module: false,
              files: [
                  'resources/libs/jquery/nestable/jquery.nestable.css',
                  'resources/libs/jquery/nestable/jquery.nestable.js'
              ]
          },
          {
              name: 'chart',
              module: false,
              files: [
                  'resources/libs/js/echarts/build/dist/echarts-all.js',
                  'resources/libs/js/echarts/build/dist/theme.js',
                  'resources/libs/js/echarts/build/dist/jquery.echarts.js'
              ]
          }
        ]
      )
      .config(['$ocLazyLoadProvider', 'MODULE_CONFIG', function($ocLazyLoadProvider, MODULE_CONFIG) {
          $ocLazyLoadProvider.config({
              debug: false,
              events: false,
              modules: MODULE_CONFIG
          });
      }]);
})();

