<div ng-if="meta.length > 0" class="row meta-container" ng-repeat="item in meta" style="margin-top: 10px;">
    <section class="col col-md-9" style=" margin: 0 0px 0px 12px;">
        <input type="text" class="form-control" placeholder="სასურველი ინფო"
               ng-model="item.value" required>
    </section>
    <section class="col col-md-2" style="padding-left: 0px; margin: 0 0px 0px 12px;">
        <a class="btn btn-sm red" ng-hide="item.editing" ng-click="removeMeta($index)" style="line-height: 24px;">წაშლა</a>
    </section>
</div>
