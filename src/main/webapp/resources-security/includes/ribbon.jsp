<span class="ribbon-button-alignment"> <span id="refresh"
     class="btn btn-ribbon" data-action="resetWidgets" data-title="refresh"
                                             rel="tooltip" data-placement="bottom"
                                             data-original-title="<i class='text-warning fa fa-warning'></i> <span >Warning! This will reset all your widget settings.</span>"
                                             data-html="true"
                                             data-reset-msg="Would you like to RESET all your saved widgets and clear LocalStorage?"><i
        class="fa fa-refresh"></i></span>
</span>


<breadcrumb> <!-- This is auto generated --> </breadcrumb>
<div class="pull-right">

    <div id="hide-menu" class="btn-header pull-right">
        <span>
            <a href="javascript:void(0);" data-action="toggleMenu" title="Collapse Menu">
                <i class="fa fa-reorder"></i>
            </a>
        </span>
    </div>

    <div id="logout" class="btn-header transparent pull-right">
        <span>
            <a href="logout" title="Sign Out" data-action="userLogout" data-logout-msg="">
                <i class="fa fa-sign-out"></i>
            </a>
        </span>
    </div>

    <div id="fullscreen" class="btn-header transparent pull-right">
        <span>
            <a href="javascript:void(0);" data-action="launchFullscreen" title="Full Screen">
                <i class="fa fa-arrows-alt"></i>
            </a>
        </span>
    </div>

    <span data-ng-controller="ActivityDemoCtrl" style="display: none;">
        <activity data-onrefresh="refreshCallback">
            <activity:button data-icon="fa fa-user" data-total="total"/>
            <activity:content data-footer="footerContent">
                <activity:item data-src="item.src" data-onload="item.onload" data-active="item.active"
                               data-ng-repeat="item in items">
                    <span data-localize="{{ item.title }}">{{ item.title }}</span> ({{ item.count }})
                </activity:item>
            </activity:content>
        </activity>
    </span>

    <div class="btn-header transparent pull-right" style="margin-top: -10px; display: none;">
        <ul data-lang-menu="" class="header-dropdown-list hidden-xs" data-ng-controller="LangController">
            <li>
                <a href="" class="dropdown-toggle" data-toggle="dropdown">
                    <img alt="" class="flag flag-{{ currentLang.flagCode }}" src="${basePath}resources/img/blank.gif">
                    <span> {{ currentLang.translation }} </span>
                    <i class="fa fa-angle-down"></i>
                </a>
                <ul class="dropdown-menu pull-right">
                    <li data-ng-class="{active: lang == currentLang}" data-ng-repeat="lang in languages">
                        <a href="" data-ng-click="setLang(lang)"><img class="flag flag-{{ lang.flagCode }}"
                                                                      src="${basePath}resources/img/blank.gif"/> {{
                            lang.language }}
                            ({{ lang.translation }}) </a>
                    </li>
                </ul>
            </li>
        </ul>
    </div>

</div>
