<div class="row">
    <section class="col col-md-12">
        <input type="file"  nv-file-select uploader="uploaderMultiple" multiple/><br/>
    </section>
    <section ng-if="uploaderMultiple.queue.length > 0">
        <p>სურათების ზომა: {{ uploaderMultiple.queue.length }}</p>
        <table class="table">
            <thead>
            <tr>
                <th width="50%">სურათი</th>
                <th ng-show="uploaderMultiple.isHTML5">ზომა</th>
                <th ng-show="uploaderMultiple.isHTML5">პროგრესი</th>
                <th>სტატუსი</th>
                <th>ქმედება</th>
            </tr>
            </thead>
            <tbody>
            <tr ng-repeat="item in uploaderMultiple.queue">
                <td>
                    <strong></strong>
                    <!-- Image preview -->
                    <!--auto height-->
                    <!--<div ng-thumb="{ file: item.file, width: 100 }"></div>-->
                    <!--auto width-->
                    <div ng-show="uploaderMultiple.isHTML5" ng-thumb="{ file: item._file, height: 100 }"></div>
                    <!--fixed width and height -->
                    <!--<div ng-thumb="{ file: item.file, width: 100, height: 100 }"></div>-->
                </td>
                <td ng-show="uploaderMultiple.isHTML5" nowrap>{{ item.file.size/1024/1024|number:2 }} MB</td>
                <td ng-show="uploaderMultiple.isHTML5">
                    <div class="progress" style="margin-bottom: 0;">
                        <div class="progress-bar" role="progressbar" ng-style="{ 'width': item.progress + '%' }"></div>
                    </div>
                </td>
                <td class="text-center">
                    <span ng-show="item.isSuccess"><i class="glyphicon glyphicon-ok"></i></span>
                    <span ng-show="item.isError"><i class="glyphicon glyphicon-remove"></i></span>
                </td>
                <td nowrap>
                    <button type="button" class="btn btn-danger btn-xs" ng-click="item.remove()">
                        <span class="glyphicon glyphicon-trash"></span> წაშლა
                    </button>
                </td>
            </tr>
            </tbody>
        </table>
    </section>
</div>
