;
 
(function ($) {
    var settings = {
        spanClass: "tagspan",
        deleteClass: "deleteTag icon-remove-circle",
        inputCls:"noborder",
        list: [],
        inputName: "tags",
        valueString: "",
        maxCount: 5,
        maxTagLength:10
    };
    $.fn.tags = function (options) {
        var me = this;
        settings = $.extend({}, settings, options);
        if (settings.list.length == 0) {
            settings.list = settings.valueString.split(',');
        }
        var tagsCount = function () {
            return me.find("." + settings.spanClass).length;
        };
        var createItem = function (item) {
            if (item == '') return '';
            var spanItem = $('<span></span>').addClass(settings.spanClass).html(item).attr('itemValue', item);
            var lnk = $('<a href="javascript:;" mode="add,edit"></a>').addClass(settings.deleteClass).bind("click", function () {
                $(this).parent().remove();
                calcValue();
                $("#inputTag").show();
            });
            spanItem.append(lnk);
            return spanItem;
        };
        var calcValue = function () {
            var value1 = "";
            $(me).find('.' + settings.spanClass).each(function (idx) {
                if (idx == 0) value1 += $(this).attr('itemValue');
                else value1 += "," + $(this).attr('itemValue');
            });
            $(me).find('input[type="hidden"]').val(value1);
        };
        var valueInput = $('<input type="hidden"/>').attr("name", settings.inputName).attr("id",settings.inputName);
        $(me).append(valueInput);
        $.each(settings.list, function (i, n) {
            $(me).append(createItem(n));
            calcValue();
        });
        calcValue();
        var generateTag = function () {
            if (tagsCount() >= settings.maxCount) {
                return;
            }
            var tag = $("#inputTag").val().replace(",", "").replace(" ", "");
            if (tag != '') {
                $("#inputTag").before(createItem(tag));
            }
            $("#inputTag").val('');
            calcValue();
            if (tagsCount() == settings.maxCount) {
                $("#inputTag").hide();
            }
        };
        var inputElement = $('<input type="text" size="2" id="inputTag" maxlength="'+settings.maxTagLength+'"/>').addClass(settings.inputCls)
            .bind("keypress", function (e) {
                e = e || event;
                var currKey = e.keyCode || e.which || e.charCode;
                if (String.fromCharCode(currKey) == " " || String.fromCharCode(currKey) == "," || currKey == 13) {
                    e.preventDefault();
                    generateTag();
                    return false;
                }
                if (this.value.length >= settings.maxTagLength) {
                    e.preventDefault();
                    return false;
                }
            })
            .bind("blur", function () {
                generateTag();
            })
            .bind("keyup", function() {
                var length= this.value.replace(/[^\u0000-\u00ff]/g,"aa").length;
                this.size = length + 2;
            });
        $(me).append(inputElement);
        if (tagsCount() == settings.maxCount) {
                $("#inputTag").hide();
        }
        $(me).click(function () {
            inputElement.focus();
        });
        
    };
})(jQuery);
function checkLength(which) { 
var maxchar=20; 
var oTextCount = document.getElementById("char"); 
iCount = which.value.replace(/[^\u0000-\u00ff]/g,"aa").length; 
if(iCount<=maxchar) 
{ 
oTextCount.innerHTML = "<font color=#FF0000>"+ iCount+"</font>"; 
which.style.border = '1px dotted #FF0000'; 
which.size=iCount+2; 
} 
else alert("�벻Ҫ����"+maxchar); 
} 