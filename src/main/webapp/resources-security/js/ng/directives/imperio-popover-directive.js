angular.module('app').directive('ngPopoverConfirm', function () {
    return {
        restirct: 'A',
        link: function (scope, element, attrs) {
            var placement = attrs['ngPopoverPlacement'] ? attrs['ngPopoverPlacement'] : 'left';
            element.popover({
                html: true,
                placement: placement,
                trigger: 'manual',
                content: "<span data-popover-confirm-container> \
		          <span class='btn btn-success popover-confirm-btn'> \
		            Yes \
		          </span> \
		          <span class='btn btn-default popover-cancel-btn'> \
		            No \
		          </span> \
		        </span>"
            });
            $(document).off('click', '[ng-popover-confirm]');
            $(document).on('click', '[ng-popover-confirm]', function () {
                $('.popover').remove();
                callback = $(this).attr('ng-popover-confirm');
                var btn = $(this),
                    popover = btn.next();
                btn.popover('toggle');
            });
            $(document).off('click', '.popover-confirm-btn');
            $(document).on('click', '.popover-confirm-btn', function () {
                result = eval('element.scope().' + callback);
                $('.popover').remove();
            });
            $(document).off('click', '.popover-cancel-btn');
            $(document).on('click', '.popover-cancel-btn', function () {
                $('.popover').remove();
            });
        }
    };
});