<div class="col-sm-6 col-xs-12 hidden-xs" class="pagin-left" style="line-height: 40px;">
    <div class="dataTables_info" id="dt_basic_info" role="status" aria-live="polite" style="text-align: left;">
        გვერდი: <span class="txt-color-darken">{{AmfTable.currentPage}}. </span> ჩანაწერები გვერდზე: <span
            class="txt-color-darken"> {{AmfTable.pageSize}}</span>
        <span class="text-primary"></span>
    </div>
</div>
<div class="col-xs-12 col-sm-6 pagination-right">
    <pagination total-items="AmfTable.totalItems"
                page="AmfTable.currentPage"
                on-select-page="AmfTable.pageChanged(page)"
                max-size="5"
                class="pagination-sm"
                boundary-links="true"
                rotate="false"
                items-per-page="AmfTable.pageSize"
                num-pages="numPages">
    </pagination>
</div>
