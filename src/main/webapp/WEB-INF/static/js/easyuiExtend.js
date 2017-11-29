$.extend($.fn.dialog.defaults, {
			    onClose: function(jq){
			    	var tabs=$("#wu-tabs").tabs('getSelected');
					$(this).addClass(tabs.panel('options').title);
			    }
			});
$.extend($.fn.window.defaults, {
    onClose: function(jq){
    	var tabs=$("#wu-tabs").tabs('getSelected');
		$(this).addClass(tabs.panel('options').title);
    }
});

$.extend($.fn.tabs.defaults, {
    onBeforeClose: function(jq){
    	var title=$(this).tabs('getSelected').panel('options').title;
    	$("."+title).panel('destroy'); 
    }
});

$.extend($.fn.tabs.methods, {
    myClose: function(jq,title){
    	$("."+title).panel('destroy');
    }
});

$.extend($.fn.filebox.methods, {
     imgVerify: function (jq,opts) {
         opts = jq.extend({
             imgType: ["gif", "jpeg", "jpg", "bmp", "png"],
             verifyImgUrl:jq.filebox('getText')
         }, opts || {});
         if (!RegExp("\.(" + opts.imgType.join("|") + ")$", "i").test(opts.verifyImgUrl)) {
         	$.messager.alert('图片格式错误', "选择文件错误,图片类型必须是"+ opts.imgType.join("，") + "中的一种", 'info');
             this.value = "";
             return false;
         }else{
         	return true;
         }
     }
 });
 $.extend($.fn.filebox.methods, {
     uploadPreview: function (jq,opts) {
    	 var fileid = jq.next('span').find('input[type="file"]').attr('id');
         var _self = document.getElementById(fileid);
         opts = jq.extend({
             imgId: "imgId",
             imgType: ["gif", "jpeg", "jpg", "bmp", "png"],
             
         }, opts || {});
         var url = "";
         if(jq.filebox('imgVerify',{imgType:opts.imgType})){
            	if (navigator.userAgent.indexOf("MSIE")>=1) { // IE 
            		url = _self.value;
            	} else if(navigator.userAgent.indexOf("Firefox")>0) { // Firefox 
            		url = window.URL.createObjectURL(_self.files.item(0)); 
            	} else if(navigator.userAgent.indexOf("Chrome")>0) { // Chrome 
            		url = window.URL.createObjectURL(_self.files.item(0)); 
            	}
            	$("#" + opts.imgId).attr('src',url);
         }else{
         	$("#" + opts.imgId).attr('src',"");
         }
     }
 });
 
//	(function($){
//		function getCacheContainer(t){
//			var view = $(t).closest('div.datagrid-view');
//			var c = view.children('div.datagrid-editor-cache');
//			if (!c.length){
//				c = $('<div class="datagrid-editor-cache" style="position:absolute;display:none"></div>').appendTo(view);
//			}
//			return c;
//		}
//		function getCacheEditor(t, field){
//			var c = getCacheContainer(t);
//			return c.children('div.datagrid-editor-cache-' + field);
//		}
//		function setCacheEditor(t, field, editor){
//			var c = getCacheContainer(t);
//			c.children('div.datagrid-editor-cache-' + field).remove();
//			var e = $('<div class="datagrid-editor-cache-' + field + '"></div>').appendTo(c);
//			e.append(editor);
//		}
//		
//		var editors = $.fn.datagrid.defaults.editors;
//		for(var editor in editors){
//			var opts = editors[editor];
//			(function(){
//				var init = opts.init;
//				opts.init = function(container, options){
//					var field = $(container).closest('td[field]').attr('field');
//					var ed = getCacheEditor(container, field);
//					if (ed.length){
//						ed.appendTo(container);
//						return ed.find('.datagrid-editable-input');
//					} else {
//						return init(container, options);
//					}
//				}
//			})();
//			(function(){
//				var destroy = opts.destroy;
//				opts.destroy = function(target){
//					if ($(target).hasClass('datagrid-editable-input')){
//						var field = $(target).closest('td[field]').attr('field');
//						setCacheEditor(target, field, $(target).parent().children());
//					} else if (destroy){
//						destroy(target);
//					}
//				}
//			})();
//		}
//	})(jQuery);
