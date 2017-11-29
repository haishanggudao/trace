<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<div id="clientUserResourceBar" style="padding: 5px 0;">
	<shiro:hasPermission name="system:client_user_resource:add">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-add-s1'"
			onclick="openClientUserResourceDialog()"  plain="true">新增权限</a>
		<span style="color:#D3D3D3 " >| </span>
	</shiro:hasPermission>
	<shiro:hasPermission name="system:client_user_resource:edit">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit-s1'"
			onclick="editClientUserResourceDialog()"  plain="true">编辑权限</a>
		<span style="color:#D3D3D3 " >| </span>
	</shiro:hasPermission>
	<shiro:hasPermission name="system:client_user_resource:delete">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-delete-s1'"
			onclick="delClientUserResource();"  plain="true">删除权限</a>
	</shiro:hasPermission>
	<!-- begin of query -->
	<!-- end of query -->
</div>

<!-- start of table list -->
<table id="clientUserResourceList" title="设备用户列表"
	style="width: auto;">
</table>
<!-- end of table list -->

<!-- begin of adding dialog -->
<div id="addClientUserResourceDialog" class="easyui-dialog" title="编辑" 
data-options="modal:true,closed:true,iconCls:'icon-document'" 
style="width: 420px;padding: 10px;" buttons="#dlg_ClientUserResource-buttons">
	<form id="clientUserResourceForm" method="post">
		<input type="hidden" name="resourceId">
		<table>
			<tr height="35">
				<td width="90" align="right">权限标识符：</td>
				<td><input id="resourceNum_clientUserResource"
					name="resourceNum" class="easyui-textbox" style="width: 266px" />
				</td>
			</tr>
			<tr>
				<td width="90" align="right">设备权限描述：</td>
				<td><input id="description_clientUserResource"
					name="description" class="easyui-textbox"
					style="width: 266px; height: 100px" /></td>
			</tr>
		</table>
	</form>
</div>
<!-- begin of adding dialog -->


<div id="dlg_ClientUserResource-buttons"  style="text-align: center;">
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="submitClientUserResource()" style="width:180px;height: 26px">确认提交</a>

</div>



<script type="text/javascript">
	var initFlag_clientUserResource = 0;

	$(function() {
		loadClientUserResource();
	});

	//***** begin of function ***** 
	/**
	 * function => UI => initialize the view => the data of loading the client users
	 */
	function loadClientUserResource() {
		<shiro:hasPermission name="system:client_user_resource:edit">
			 $('#clientUserResourceList').datagrid({
					onDblClickCell: function(index,field,value){
						editClientUserResourceDialog()
					}
			 });
		</shiro:hasPermission>
		$('#clientUserResourceList').datagrid({
			method : 'post',
			url : 'client_user_resource/findAllList',
			rownumbers : true,
			pageSize : 10,
			pagePosition : 'bottom',
			pageList : [ 10, 20, 50 ],
			pagination : true,
			multiSort : true,
			fitColumns : true,
			fit : true,
			iconCls : 'icon-ok',
			singleSelect : true,
			toolbar : '#clientUserResourceBar',
			onBeforeLoad : function(param) {
				//param.productName = $("#searchProductName_goods").val();
				//var myUserCompanyId = $('#index_user_companys').combobox('getValue');
				//param.companyId = myUserCompanyId;
			},
			columns : [ [ {
				field : 'resourceId',
				title : 'clientUserId',
				hidden : true
			}, {
				field : 'resourceNum',
				title : '设备权限标识符',
				width : 100,
				sortable : true
			}, {
				field : 'description',
				title : '设备权限描述',
				width : 100
			} ] ]
		});
	}

	/**
	 * function => UI => initialize the dialog for adding
	 */
	function initDialog_clientUserResource() {
		// edit the #clientUserName
		$('#description_clientUserResource').textbox({
			multiline : true
		});
	}

	/**
	 * function => action => submit the resource of client user
	 */
	function submitClientUserResource() {
		$('#clientUserResourceForm').form('submit', {
			url : 'client_user_resource/addClientUserResource',
			onSubmit : function(param) {
				// param.companyId = myUserCompanyId; 
				return $(this).form('enableValidation').form('validate');
			},
			success : function(data) {
				//console.info('client_user/addClientUser:end');
				//console.info(data);
				var msg = eval('(' + data + ')');
				$.messager.alert('信息提示', msg.msg, 'info');
				if (msg.code == 1) {
					$('#addClientUserResourceDialog').window('close');
					$('#clientUserResourceList').datagrid('reload');
				}
			}
		});
	}

	/**
	 * button menu => show the dialog of insert
	 */
	function openClientUserResourceDialog() {
		if (initFlag_clientUserResource == 0) {
			initDialog_clientUserResource();
			initFlag_clientUserResource = 1;
		}

		$("#clientUserResourceForm").form('clear').form('disableValidation');

		$('#addClientUserResourceDialog').dialog({
			closed : false,
			modal : true,
			title : "新增设备用户"
		});
	}

	/**
	 * button menu => show the dialog of update
	 */
	function editClientUserResourceDialog() {
		var row = $('#clientUserResourceList').datagrid('getSelected');

		if (row) {
			$('#clientUserResourceForm').form('clear')
					.form('disableValidation');

			if (initFlag_clientUserResource == 0) {
				initDialog_clientUserResource();
				initFlag_clientUserResource = 1;
			}

			$('#clientUserResourceForm').form('load', {
				resourceId : row.resourceId,
				resourceNum : row.resourceNum,
				description : row.description
			});

			$('#addClientUserResourceDialog').dialog({
				closed : false,
				modal : true,
				title : "编辑设备用户"
			});
		} else {
			$.messager.alert('信息提示', '请选择一行记录！', 'info');
		}
	}

	/**
	 * button menu => delete the resource of client user
	 */
	function delClientUserResource() {
		var row = $('#clientUserResourceList').datagrid('getSelected');
		if (row) {
			$.messager.confirm('删除设备资源信息', '确认删除?', function(r) {
				if (r) {
					$.post("client_user_resource/delClientUserResource", {
						resourceId : row.resourceId
					}, function(data) {
						var msg = eval('(' + data + ')');
						$.messager.alert('信息提示', msg.msg, 'info');
						if (msg.code == 1) {
							$('#clientUserResourceList').datagrid('reload');
						}
					});
				}
			});
		} else {
			$.messager.alert('信息提示', '请选择一行记录！', 'info');
		}
	}
	//***** end of function *****
</script>