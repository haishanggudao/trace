<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<link rel="stylesheet" type="text/css" href="static/css/datagrid_util.css">
	<div id="applicationbar" style="padding: 5px 0;">
		<shiro:hasPermission name="base:application:add">
			<a href="#" class="easyui-linkbutton" iconCls="icon-add-s1" onclick="applicationAdd()">新增</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="base:application:edit">
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit-s1" onclick="applicationEdit()">修改</a>
		</shiro:hasPermission>
	</div>
	<table id="applicationList" style="width: auto; height: 350px"></table>
	<div id="applicationDialog" class="easyui-dialog" style="width: 500px; padding: 10px;"
			data-options="closed:true,iconCls:'icon-document'">
			<form id="applicationForm" method="post" enctype="multipart/form-data">
					<input type="hidden" name="id" />
					<div style="float:left;width:370px;">
						<div class="fitem">
						          <label class="fitemlabel width_100">应用名：</label>
						          <input type="text" name="name" class="easyui-textbox wu-text"   style="width: 200px;"
						          		data-options="required:true,validType:['length[0,200]']" />
						</div>		
						<div class="fitem">
						          <label class="fitemlabel width_100">版本号：</label>
						          <input type="text" name="version" class="easyui-textbox wu-text"   style="width: 200px;"
						          		data-options="required:true,validType:['length[0,200]']" />
						</div>		
						<div class="fitem">
						          <label class="fitemlabel width_100">是否强制升级：</label>
						          <input id="status_application" type="text" name="status" class="easyui-switchbutton wu-text"   style="width: 160px;"/>
						</div>		
						<div class="fitem">
					          <label class="fitemlabel width_100">应用：</label>
					          <input id="applicationFilebox"  name="applicationfile" class="wu-text"  style="width: 200px;"/>
							  <input type="hidden" name="applicationPath" style="width: 160px;">
						</div>		
						<div class="fitem">
						          <label class="fitemlabel width_100"> 版本升级简介：</label>
						          <input type="text" name="versionIntroduction" class="easyui-textbox wu-text"   style="width: 200px;height: 92px;" 
						          data-options="prompt:'升级信息简介',multiline:true,validType:['length[0,400]']"/>
						</div>		
					</div>
					
			</form>
	</div>
<script type="text/javascript">
	$(function() {
		loadapplication();
	});


	/**
	 * button => 打开添加窗口
	 */

	function applicationAdd() {
		 initDialogComponent_application();
		$('#applicationFilebox').filebox('setValue', '');
		$('#applicationForm').form('clear').form('disableValidation');
		$("#status_application").switchbutton({checked: true});
		$('#status_application_hidden').attr('value','0');
		
		$('#applicationDialog').dialog({
			closed : false,
			modal : true,
			title : "新增应用",
			buttons : [ {
				text : '确定',
				iconCls : 'icon-ok',
				handler : submitapplication
			}, {
				text : '取消',
				iconCls : 'icon-cancel',
				handler : function() {
					$('#applicationDialog').dialog('close');
				}
			} ]
		});
	}
	 
	/**
	 * button => 打开修改窗口
	 */
	function applicationEdit() {
		var row = $('#applicationList').datagrid('getSelected');
		if (row) {
			$('#applicationForm').form('clear');
			initDialogComponent_application();
			$('#applicationForm').form('load', {
				id:row.id,
				name : row.name,
				applicationPath : row.applicationPath,
				version:row.version,
				versionIntroduction:row.versionIntroduction,
				status:row.status
			});
			
			$("#applicationFilebox").filebox("setText", row.applicationPath);
			if(row.status==1){
				$("#status_application").switchbutton({checked: true});
			}
			$('#applicationDialog').dialog({
				closed : false,
				modal : true,
				title : "修改加工者信息",
				buttons : [ {
					text : '确定',
					iconCls : 'icon-ok',
					handler : submitapplication
				}, {
					text : '取消',
					iconCls : 'icon-cancel',
					handler : function() {
						$('#applicationDialog').dialog('close');
					}
				} ]
			});
		} else {
			$.messager.alert('信息提示', '请选择原料！', 'info');
		}
	}

	/**
	 * action => 保存产品
	 */
	function submitapplication() {
		$('#applicationForm').form(
				'submit',
				{
					url : 'application/save',
					onSubmit : function(param) {
						param.companyId = $("#index_user_companys").combobox("getValue");
						return $(this).form('enableValidation').form('validate');
					},
					success : function(data) {
						var msg = eval('(' + data + ')');
						$.messager.alert('信息提示', msg.msg, 'info');
						if (msg.code == 1) {
							$('#applicationDialog').dialog('close');
							$('#applicationList').datagrid('reload');
						}
					}
				});
	}


	/**
	 * initialize the component of the dialog
	 */
	function initDialogComponent_application() {
		$("#applicationFilebox").filebox({
			prompt : '选择应用',
			buttonText:'选择应用',
		});
	}

	/**
	 * load the applications; 
	 */
	function loadapplication() {
		$('#applicationList').datagrid(
				{
					url : 'application/findAllList',
					method : 'post',
					rownumbers : true,
					pageSize : 10,
					pagePosition : 'bottom',
					pageList : [ 10, 20, 50 ],
					pagination : true,
					multiSort : true,
					fitColumns : true,
					fit : true,
					iconCls : 'icon-ok',
					title:"应用列表",
					singleSelect : true,
					toolbar : '#applicationbar',
					onBeforeLoad : function(param) {
						param.companyId = $("#index_user_companys").combobox("getValue");
					},
					columns : [ [ {
						field : 'name',
						title : '应用名',
						width : 150,
						sortable : true
					}, {
						field : 'version',
						title : '版本号',
						width : 100
					}, {
						field : 'applicationPath',
						title : '应用路径',
						width : 200
					}] ]

				});
	}
	 
</script>
