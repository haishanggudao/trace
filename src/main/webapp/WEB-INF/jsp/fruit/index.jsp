<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="copyright" content="All Rights Reserved, Copyright (C) 2013, Wuyeguo, Ltd." />
<link rel="shortcut icon" href="static/img/favicon.ico" type="image/x-icon" />
<title>上海市果品行业协会</title>

<link rel="stylesheet" type="text/css" href="static/easyui/themes/default/easyui.css" id="traceProduct">
<link rel="stylesheet" type="text/css" href="static/css/wu.css?v=20160115" >
<link rel="stylesheet" type="text/css" href="static/css/icon.css" >

<script type="text/javascript" src="static/js/jquery.min.js"></script>
<script type="text/javascript" src="static/js/jquery.cookie.js"></script>
<script type="text/javascript" src="static/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="static/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="static/js/datagrid-detailview.js"></script>
<script type="text/javascript" src="static/js/dateutil.js"></script>
<script type="text/javascript" src="static/js/tokenUtil.js"></script>
<script type="text/javascript" src="static/js/easyuiExtend.js?v=20160727"></script>
<script type="text/javascript" src="static/js/jquery.PrintArea.js"></script>
<script type="text/javascript" src="static/js/searchDate.js"></script>
</head>
<body class="easyui-layout" >
	<!-- begin of header -->
	<div class="wu-header" data-options="region:'north',border:false,split:true">
    	<div class="wu-header-left">
        	<h1 style="font-family:微软雅黑" id="head_title">上海市果品行业协会</h1>
        </div>
        <div class="wu-header-right">
        	<input type="hidden" id="index_token" name="token" value="${token }">
        	
     	   	<input id="index_userName" type="hidden" value="${userInfo.nickName }">
        	<p style="float: left"><strong class="easyui-tooltip" style="margin-right: 10px;" title="2条未读消息">${userInfo.nickName }</strong><input id="index_user_companys">，欢迎您！</p>
            <p style="float: left"><a href="/trace/">网站首页</a>|<a href="logout">安全退出</a>
            	   <select id="cb-theme" style="width:120px;height:25px"></select>
            </p>

	
        </div>
        <div class="wu-header-logo">
            <img src="static/images/logo.png" width="98" height="50" alt="">
        </div>
    </div>
    <!-- end of header -->
    <!-- begin of sidebar -->
	<div class="wu-sidebar" data-options="region:'west',split:true,border:true,title:'主功能选项'"> 
    	<div class="easyui-accordion" data-options="border:false,fit:true"> 
    	<c:forEach items="${menus }" var="menu">
    		<div title="${menu.name }" data-options="iconCls:'${menu.icon }'" style="padding:5px;">
    			<ul class="easyui-tree wu-side-tree">
    				<c:forEach items="${menu.children }" var="sub">
    					<li iconCls="${sub.icon }"><a data-icon="${sub.icon }"  data-link="${pageContext.request.contextPath}/${sub.url }" iframe="0"><span></span>${sub.name }</a></li>
    				</c:forEach>
    			</ul>
    		</div>
    	</c:forEach>
        </div>
    </div>	
    <div class="wu-main" data-options="region:'center'">
        <div id="wu-tabs" class="easyui-tabs" data-options="border:false,fit:true,tools:'#tab-tools'">  
        <!-- data-options="href:'temp/layout-1  .html',closable:false,iconCls:'icon-tip',cls:'pd3'" -->
            <div title="欢迎进入"> 
                <img id="bg_cont" width="100%" height="100%" alt="" src="static/images/bg_fruit.jpg">                          
            </div>


        </div>
        
    </div>
    
    <!-- end of main --> 
    <!-- begin of footer -->
	<div class="wu-footer" data-options="region:'south',border:true,split:true">
    	&copy; 2015 Wu All Rights Reserved
    </div>
    <!-- end of footer -->  
     <!-- 菜单栏目 -->
    <div id="mm" class="easyui-menu"  style="width:200px;">
        <div data-options="name:'new',iconCls:'icon-arrow-refresh'" onclick="refresh()">刷新</div>
        <div data-options="name:'print',iconCls:'icon-close'" onclick="removeTab()">关闭</div>
        <div data-options="name:'save'" onclick="closeAll()">全部关闭</div>
        <div id="closeother">除此之外全部关闭</div>
        <div id="closeright">关闭右侧标签页</div>
        <div id="closeleft">关闭左侧标签页</div>
        <div class="menu-sep"></div>
        <div data-options="name:'exit'">退出</div>
    </div>

    <script type="text/javascript">
		$(function(){
			$("#index_user_companys").combobox({
				url:'company/getCompanys',
				method:'post',
				valueField:'companyid',
				textField:'name',
				editable:false,
				onLoadSuccess:function(data){
					if(data){
						if(window.localStorage&&localStorage.getItem('currentCompany')){
								if(isstoreCompanyValid(data)){
					        		$("#index_user_companys").combobox("setValue",localStorage.getItem('currentCompany'));
								}else{
									$("#index_user_companys").combobox("setValue",data[0].companyid);
								}
				        }else{
							var companyId=getQueryString("companyId");
							if(companyId){
								$("#index_user_companys").combobox("setValue",companyId);
							}else{
								$("#index_user_companys").combobox("setValue",data[0].companyid);
							}
				        }
					}
				},
				onSelect:function(data){
// 					location.href="/trace/?companyId="+$("#index_user_companys").combobox('getValue');
					if(window.localStorage){
			        	localStorage.setItem('currentCompany',data.companyid);
			        	$.cookie('currentCompany', data.companyid, { expires: 7 });
			        }
					refresh();
				}
			});
	        var themes = [{
	            value: 'default',
	            text: 'Default',
	            group: '风格'
	        },
	        {
	            value: 'gray',
	            text: 'Gray',
	            group: '风格'
	        },
	        {
	            value: 'metro',
	            text: 'Metro',
	            group: '风格'
	        },
	        {
	            value: 'bootstrap',
	            text: 'Bootstrap',
	            group: '风格'
	        },
	        {
	            value: 'black',
	            text: 'Black',
	            group: '风格'
	        },{
	        	value:'pepper-grinder',
	        	text:'pepper-grinder',
	        	 group: '风格'
	        },{
	        	value:'cupertino',
	        	text:'cupertino',
	        	 group: '风格'
	        },{
	        	value:'sunny',
	        	text:'sunny',
	        	 group: '风格'
	        }];
	        $('#cb-theme').combobox({
	            groupField: 'group',
	            data: themes,
	            editable: false,
	            panelHeight: 'auto',
	            onChange: onChangeTheme,
	            onLoadSuccess: function() {
	            	if(window.localStorage){
	            		if(localStorage.getItem('theme')){
			        		$(this).combobox('setValue', localStorage.getItem('theme'));
	            		}else{
	            			$(this).combobox('setValue', 'default');
	            		}
			        }else{
	                	$(this).combobox('setValue', 'default');
			        }
	            }
	        });
		    function onChangeTheme(theme) {
		        $('#traceProduct').attr('href', 'static/easyui/themes/' + theme + '/easyui.css');
		        if(window.localStorage){
		        	localStorage.setItem('theme',theme);
		        }
		    }
			$('.wu-side-tree li').bind("click",function(){
				var title = $(this).text();
				var a=$(this).find("a");
				var url = a.attr('data-link')+"?companyId="+$("#index_user_companys").combobox('getValue')+"&url="+a.attr('data-link');
				var iconCls = a.attr('data-icon');
				var iframe = a.attr('iframe')==1?true:false;
				addTab(title,url,iconCls,iframe);
			});	
		})
		
         $('#wu-tabs').tabs({
            onContextMenu:function(e){
                e.preventDefault();
                $('#mm').menu('show', {
                    left: e.pageX,
                    top: e.pageY
                });
            }
        })

        function isstoreCompanyValid(data){
			var current=localStorage.getItem('currentCompany');
			for(index in data){
				if(data[index].companyid==current){
					return true;
				}
			}
			return false;
		}
        
       // tab刷新
       function refresh(){
//         var currTab =  self.parent.$('#wu-tabs').tabs('getSelected'); //获得当前tab
        var tabs =  $('#wu-tabs').tabs('tabs'); //获得所有tab
        for(var i=0;i<tabs.length;i++){
        	tabs[i].tabs('myClose',tabs[i].panel('options').title);
        	tabs[i].panel("refresh");
        }
//         var url = currTab.panel('options').href; // 相应的标签页（tab）对象
//         console.info(url);
        //var url = $(currTab.panel('options').content).attr('src');
//         var content = '<iframe scrolling="auto" frameborder="0"  src="'+ url +'"  style="width:100%;height:100%;"></iframe>';
        //console.log(tab);
//         currTab.panel("refresh");
//         $('#wu-tabs').tabs('update', {
//           tab : currTab,
//           options : {

//             content:content
//           }
//          });
       }

       //关闭非当前标签页（先关闭右侧，再关闭左侧）
        $("#closeother").bind("click",function(){
            var tablist = $('#wu-tabs').tabs('tabs');
            var tab = $('#wu-tabs').tabs('getSelected');
            var index = $('#wu-tabs').tabs('getTabIndex',tab);
            for(var i=tablist.length-1;i>index;i--){
                $('#wu-tabs').tabs('close',i);
            }
            var num = index-1;
            for(var i=num;i>=0;i--){
                $('#wu-tabs').tabs('close',0);
            }
        });

        //关闭当前标签页右侧标签页
        $("#closeright").bind("click",function(){
            var tablist = $('#wu-tabs').tabs('tabs');
            var tab = $('#wu-tabs').tabs('getSelected');
            var index = $('#wu-tabs').tabs('getTabIndex',tab);
            for(var i=tablist.length-1;i>index;i--){
                $('#wu-tabs').tabs('close',i);
            }
        });
        //关闭当前标签页左侧标签页
        $("#closeleft").bind("click",function(){
            var tab = $('#wu-tabs').tabs('getSelected');
            var index = $('#wu-tabs').tabs('getTabIndex',tab);
            var num = index-1;
            for(var i=0;i<=num;i++){
                $('#wu-tabs').tabs('close',0);
            }
        });
        

		//关闭所有的tab
        function closeAll(){

            $.messager.confirm('消息提醒', '确认关闭所有窗口?', function(r){
                    if (r){
                          var tiles = new Array();
                          var tabs = $('#wu-tabs').tabs('tabs');    
                          var len =  tabs.length;         
                          if(len>0){
                            for(var j=0;j<len;j++){
                                var a = tabs[j].panel('options').title;             
                                tiles.push(a);
                            }
                            for(var i=1;i<tiles.length;i++){                
                                $('#wu-tabs').tabs('close', tiles[i]);
                            }

                          }
                    }
            });
             
        }
        // 按钮组

        $('#wu-tabs').tabs({
            tools:[{
                iconCls:'icon-refresh',
                handler:function(){
                    refresh();
                }
            },{
                iconCls:'icon-cancel',
                handler:function(){
                    closeAll();
                }
            }]
        });

		/**
		* Name 添加菜单选项
		* Param title 名称
		* Param href 链接
		* Param iconCls 图标样式
		* Param iframe 链接跳转方式（true为iframe，false为href）
		*/	
		function addTab(title, href, iconCls, iframe){
			var tabPanel = $('#wu-tabs');
			var opentabs = 8; //允许打开的TAB数量
            var tabCount = $('#wu-tabs').tabs('tabs').length;
            if (tabCount > 20) {
                var msg = '<b>您当前打开了太多的页面，如果继续打开，会造成程序运行缓慢，无法流畅操作！</b>';
                $.messager.confirm("系统提示", msg, function (r) {
                    if(r){
                         return true;
                    }
                   
                });
                return false;
            }
			if(!tabPanel.tabs('exists',title)){
				var content = '<iframe scrolling="auto" frameborder="0"  src="'+ href +'" style="width:100%;height:100%;"></iframe>';
				if(iframe){
					tabPanel.tabs('add',{
						title:title,
						content:content,
						iconCls:iconCls,
						fit:true,
						cls:'pd3',
						closable:true
					});
				}
				else{
					tabPanel.tabs('add',{
						title:title,
						href:href,
						iconCls:iconCls,
						fit:true,
						cls:'pd3',
						closable:true
					});
				}
			}
			else
			{
				tabPanel.tabs('select',title);
			}
		}


		/**
		* Name 移除菜单选项
		*/
		function removeTab(){
			var tabPanel = $('#wu-tabs');
			var tab = tabPanel.tabs('getSelected');
			if (tab){
				var index = tabPanel.tabs('getTabIndex', tab);
				tabPanel.tabs('close', index);
			}
		}
		function getQueryString(name) {
			var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
			var r = window.location.search.substr(1).match(reg);
			if (r != null) {
				return unescape(r[2]);
			}
			return null;
		}
	</script> 
</body>
</html>
