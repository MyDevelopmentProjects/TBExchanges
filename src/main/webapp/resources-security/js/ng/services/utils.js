var app = {
    timeToDateLong: function (time) {
        //TODO instead of ka-GE here we should use the current language chosen by the organisation
        return new Date(time).toLocaleDateString('ka-GE', {
            year: 'numeric',
            month: '2-digit',
            day: '2-digit',
            hour: '2-digit',
            minute: '2-digit',
            second: '2-digit'
        }).replace(/\./g, '/');
    },
    timeToDateLongWithFormat: function (time) {
        //TODO instead of ka-GE here we should use the current language chosen by the organisation
        return new Date(time).toLocaleDateString('ka-GE', {
            year: 'numeric',
            month: '2-digit',
            day: '2-digit',
            hour: '2-digit',
            minute: '2-digit'
        });
    },
    timeToDateShort: function (time) {
        //TODO instead of ka-GE here we should use the current language chosen by the organisation
        return new Date(time).toLocaleDateString('ka-GE', {
            year: 'numeric',
            month: '2-digit',
            day: '2-digit'
        }).replace(/\./g, '/');
    },
    dateToTime: function (time) {
        var d = new Date(time);
        return d.getHours() + ":" + d.getMinutes()
    },
    initCrop: function ($id) {
        var $currentImage = {};
        $($id).Jcrop({
            bgFade: true,
            bgOpacity: .2,
            //aspectRatio: 1,  //If you want to keep aspectRatio
            boxWidth: 650,   //Maximum width you want for your bigger images
            boxHeight: 400,  //Maximum Height for your bigger images
            setSelect: [60, 130, 540, 330],
            onSelect: this.getCroppedCoords
        }, function () {
            $currentImage = this;
        });
        $('#fadetog').change(function () {
            $currentImage.setOptions({
                bgFade: this.checked
            });
        }).attr('checked', 'checked');
        $('#shadetog').change(function () {
            if (this.checked)
                $('#shadetxt').slideDown();
            else
                $('#shadetxt').slideUp();
            $currentImage.setOptions({
                shade: this.checked
            });
        }).attr('checked', false);
        var sections = {
            bgc_buttons: 'Background Color',
            bgo_buttons: 'Opacity'
        };
        var bgo = {
            Low: .2,
            Mid: .5,
            High: .8,
            Full: 1
        };
        var bgc = {
            R: '#900',
            B: '#4BB6F0',
            Y: '#F0B207',
            G: '#46B81C',
            W: 'white',
            K: 'black'
        };
        for (i in sections)insertSection(i, sections[i]);
        function create_btn(c) {
            var $o = $('<button />').addClass('btn btn-default btn-small');
            if (c)
                $o.append(c);
            return $o;
        }

        for (i in bgo) {
            $('#bgo_buttons .btn-group').append(create_btn(i).click(setoptHandler('bgOpacity', bgo[i])), ' ');
        }
        for (i in bgc) {
            $('#bgc_buttons .btn-group').append(create_btn(i).css({
                background: bgc[i],
                color: ((i == 'K') || (i == 'R')) ? 'white' : 'black'
            }).click(setoptHandler('bgColor', bgc[i])), ' ');
        }
        function insertSection(k, v) {
            $('#interface').prepend($('<fieldset></fieldset>').attr('id', k).append($('<legend></legend>').append(v), '<div class="btn-toolbar"><div class="btn-group"></div></div>'));
        };
        function setoptHandler(k, v) {
            return function (e) {
                $(e.target).closest('.btn-group').find('.active').removeClass('active');
                $(e.target).addClass('active');
                var opt = {};
                opt[k] = v;
                $currentImage.setOptions(opt);
                return false;
            };
        };
        $('#bgo_buttons .btn:first,#bgc_buttons .btn:last').addClass('active');
        $('#interface').show();
    },
    getCroppedCoords: function (c) {

    },
    getListOfLocales: function () {
        return [
            {key: "uk_UK", value: "Ukrainian (Ukraine)"},
            {key: "ru_RU", value: "Russian (Russia)"},
            {key: "en_US", value: "English (United States)"},
            {key: "ka_GE", value: "Georgian (Georgia)"},
            {key: "ar_AR", value: "Armenian (Armenia)"},
            {key: "az_AZ", value: "Azerbaijani (Latin)"}
        ]
    },
    treeify: function (list, idAttr, parentAttr, childrenAttr) {
        if (!idAttr) idAttr = 'id';
        if (!parentAttr) parentAttr = 'parent';
        if (!childrenAttr) childrenAttr = 'children';
        var treeList = [];
        var lookup = {};
        list.forEach(function (obj) {
            lookup[obj[idAttr]] = obj;
            obj[childrenAttr] = [];
        });
        list.forEach(function (obj) {
            if (obj[parentAttr] != null) {
                lookup[obj[parentAttr]][childrenAttr].push(obj);
            } else {
                treeList.push(obj);
            }
        });
        return treeList;
    },
    generateString: function (length) {
        if (length == null || length < 5) length = 5;
        var text = "";
        var chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

        for (var i = 0; i < 5; i++)
            text += chars.charAt(Math.floor(Math.random() * chars.length));

        return text;
    },
    registerPickers: function () {
        function addMonth(selectedDate) {
            var CurrentDate = new Date(selectedDate);
            CurrentDate.setMonth(CurrentDate.getMonth() + 1);
            return CurrentDate;
        }

        function subtract(selectedDate) {
            var CurrentDate = new Date(selectedDate);
            CurrentDate.setMonth(CurrentDate.getMonth() - 1);
            return CurrentDate;
        }

        $("#from").datepicker({
            defaultDate: "+1w",
            dateFormat: "yy-mm-dd",
            changeMonth: true,
            numberOfMonths: 2,
            prevText: '<i class="fa fa-chevron-left"></i>',
            nextText: '<i class="fa fa-chevron-right"></i>',
            onClose: function (selectedDate) {
                $("#to").datepicker("option", "maxDate", addMonth(selectedDate));
                $("#to").datepicker("option", "minDate", selectedDate);
            }
        });
        $("#to").datepicker({
            defaultDate: "+1w", changeMonth: true, numberOfMonths: 2, dateFormat: "yy-mm-dd",
            prevText: '<i class="fa fa-chevron-left"></i>', nextText: '<i class="fa fa-chevron-right"></i>',
            onClose: function (selectedDate) {
                $("#from").datepicker("option", "maxDate", subtract(selectedDate));
            }
        });
    },
    removejscssfile: function (filename, filetype) {
        var targetelement = (filetype == "js") ? "script" : (filetype == "css") ? "link" : "none" //determine element type to create nodelist from
        var targetattr = (filetype == "js") ? "src" : (filetype == "css") ? "href" : "none" //determine corresponding attribute to test for
        var allsuspects = document.getElementsByTagName(targetelement)
        for (var i = allsuspects.length; i >= 0; i--) { //search backwards within nodelist for matching elements to remove
            if (allsuspects[i] && allsuspects[i].getAttribute(targetattr) != null && allsuspects[i].getAttribute(targetattr).indexOf(filename) != -1)
                allsuspects[i].parentNode.removeChild(allsuspects[i]) //remove element by calling parentNode.removeChild()
        }
    }

};
var colorContrast = {
    DARK: '000000',
    LIGHT: 'FFFFFF',
    getContrastYIQ: function (hexcolor) {
        var r = parseInt(hexcolor.substr(0, 2), 16);
        var g = parseInt(hexcolor.substr(2, 2), 16);
        var b = parseInt(hexcolor.substr(4, 2), 16);
        var yiq = ((r * 299) + (g * 587) + (b * 114)) / 1000;
        return (yiq >= 128) ? this.DARK : this.LIGHT;
    },
    stripNumberSign: function (color) {
        if (color[0] === "#") {
            color = color.substring(1, color.length);
        }
        return color;
    },
    init: function (color) {
        color = color.replace("#", "");
        color = this.stripNumberSign(color);
        return "#" + this.getContrastYIQ(color);
    }
};
var colorUtils = {
    randomColor: function () {
        var letters = '0123456789ABCDEF';
        var color = '#';
        for (var i = 0; i < 6; i++ ) {
            color += letters[Math.floor(Math.random() * 16)];
        }
        return color;
    }
};
var StringUtils = {
    format: function (formatString, replacementArray) {
        return formatString.replace(
            /\{(\d+)\}/g, // Matches placeholders, e.g. '{1}'
            function formatStringReplacer(match, placeholderIndex) {
                // Convert String to Number
                placeholderIndex = Number(placeholderIndex);

                // Make sure that index is within array bounds
                if (
                    placeholderIndex < 0 ||
                    placeholderIndex > replacementArray.length - 1
                ) {
                    return placeholderIndex;
                }

                // Replace placeholder with value from replacement array
                return replacementArray[placeholderIndex];
            }
        );
    },
    isEmpty: function (value) {
        return angular.isUndefined(value) || value === '' || value === null || value !== value;
    }

};