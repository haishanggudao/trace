<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<div id="clientUserBar" style="padding: 5px 0;">
	<shiro:hasPermission name="system:client_user:add">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-add-s1'" onclick="openClientUserDialog()"  plain="true">新增设备</a>
		<span style="color:#D3D3D3 " >| </span>
			
	</shiro:hasPermission>
	<shiro:hasPermission name="system:client_user:edit">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit-s1'" onclick="editClientUserDialog()"  plain="true">编辑设备</a>
		<span style="color:#D3D3D3 " >| </span>
	</shiro:hasPermission>
	<shiro:hasPermission name="system:client_user:delete">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-delete-s1'" onclick="delClientUser();"  plain="true">删除设备</a>
		<span style="color:#D3D3D3 " >| </span>
	</shiro:hasPermission>
	<shiro:hasPermission name="system:client_user:setPassword">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit-s1'" onclick="setPasswordWindow_cu()"  plain="true">设置密码</a>
	</shiro:hasPermission>
	<!-- begin of query -->
	<!-- end of query -->
</div>

<!-- start of table list -->
<table id="clientUserList" title="设备用户列表"
	style="width: auto; height: 350px" singleSelect="true"
	fitColumns="true">
</table>
<!-- end of table list -->

<!-- begin of adding dialog -->
<div id="addClientUserDialog" class="easyui-dialog" title="编辑" 
data-options="modal:true,closed:true,iconCls:'icon-document'" 
style="width: 420px;padding: 10px;" buttons="#dlg_ClientUser-buttons">
	<form id="clientUserForm" method="post">
		<input type="hidden" name="clientUserId">
		<table>
			<tr height="35">
				<td width="90" align="right">设备用户名：</td>
				<td><input id="clientUserName" name="clientUserName"
					type="text" class="easyui-textbox" style="width: 266px" /></td>
			</tr>
			<tr height="35">
				<td width="90" align="right">设备类型：</td>
				<td><select id="clientType" name="clientType" style="width: 266px"></select></td>
			</tr>
			<tr height="35">
				<td width="90" align="right">设备权限：</td>
				<td><input id="clientUserRole" name="clientRoleIds"
					style="width: 266px;"></td>
			</tr>
			<tr height="35">
				<td width="90" align="right">设备用户密码：</td>
				<td><input id="clientPassword" name="clientPassword"
					class="easyui-textbox" type="password" style="width: 266px"
					data-options="iconCls:'icon-lock',iconWidth:38,validType:{length:[4,20]}">
				</td>
			</tr>
			<tr height="35">
				<td width="90" align="right">用户密码确认：</td>
				<td><input id="clientPasswordAgain" name="clientPasswordAgain"
					class="easyui-textbox" type="password" style="width: 266px"
					data-options="iconCls:'icon-lock',iconWidth:38,validType:{length:[4,20]}">
				</td>
			</tr>
		</table>
	</form>
</div>
<!-- begin of adding dialog -->
<div id="dlg_ClientUser-buttons"  style="text-align: center;">
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="submitClientUser()" style="width:180px;height: 26px">确认提交</a>

</div>
<!-- 设置权限 -->
<div id="setPassword_cu" class="easyui-window" title="设置密码"
	data-options="modal:true,closed:true,iconCls:'icon-save'"
	style="padding: 10px; height: 40%; width: 25%;">

	<table>
		<tr height="35">
			<td width="90" align="right">新的用户密码：</td>
			<td><input id="clientPassword_sp_cu" name="clientPassword"
				class="easyui-textbox" type="password" style="width: 266px"
				data-options="iconCls:'icon-lock',iconWidth:38,validType:{length:[4,20]}">
			</td>
		</tr>
		<tr height="35">
			<td width="90" align="right">用户密码确认：</td>
			<td><input id="clientPasswordAgain_sp_cu"
				name="clientPasswordAgain" class="easyui-textbox" type="password"
				style="width: 266px"
				data-options="iconCls:'icon-lock',iconWidth:38,validType:{length:[4,20]}">
			</td>
		</tr>
	</table>

	<div style="padding: 5px 0;">

		<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
			onclick="savePermissions_cu()">确认</a>
	</div>

</div>

<script type="text/javascript">
	var initFlag_clientUser = 0;

	$(function() {
		loadClientUsers();
	});

	//***** begin of function ***** 
	/**
	 * function => UI => initialize the view => the data of loading the client users
	 */
	function loadClientUsers() {
		<shiro:hasPermission name="system:client_user:edit">
			 $('#clientUserList').datagrid({
				onDblClickCell: function(index,field,value){
					editClientUserDialog();
				}
			 });
		</shiro:hasPermission>
		$('#clientUserList').datagrid({
			method : 'post',
			url : 'client_user/findAllList',
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
			toolbar : '#clientUserBar',
			onBeforeLoad : function(param) {
				var myUserCompanyId = $('#index_user_companys').combobox('getValue');
				param.companyId = myUserCompanyId;
			},
			columns : [ [ {
				field : 'clientUserId',
				title : 'clientUserId',
				hidden : true
			}, {
				field : 'clientUserName',
				title : '设备用户名',
				width : 100,
				sortable : true
			}, {
				field : 'token',
				title : 'token',
				width : 100,
				sortable : true
			}, {
				field : 'mac',
				title : 'mac',
				width : 100
			}, {
				field : 'lastLogin',
				title : 'lastLogin',
				width : 100
			}, {
				field : 'state',
				title : '状态',
				width : 100
			} ] ]
		});
	}

	/**
	 * function => UI => initialize the dialog for adding
	 */
	function initDialog_clientUser() {
		// edit the #clientUserName
		$('#clientUserName').textbox({
			required : true,
			validType : {
				length : [ 4, 20 ]
			},
			editable : true
		});

		$("#clientUserRole").combobox({
			url : 'client_user/findPermissions',
			method : 'post',
			valueField : 'resourceId',
			textField : 'description',
			multiple : true,
			panelHeight : 'auto',
			required : true
		});
		$('#clientType').combobox({
		    url:'commonVariable/getVariablesByGroup',
		    valueField:'varId',
		    textField:'varValue',
		    editable : false,
		    onBeforeLoad:function(param){
		    	param.companyId=$("#index_user_companys").combobox('getValue');
		    	param.varGroup="clientType";
		    },
		    onLoadSuccess:function(data){
		    	if (data != null && data.length == 1) {
		    		$('#clientType').combobox('setValue',data[0].varId);
		    	}
		    } 
		});
	}

	/**
	 * button menu => show the dialog of insert
	 */
	function openClientUserDialog() {
		if (initFlag_clientUser == 0) {
			initDialog_clientUser();
			initFlag_clientUser = 1;
		}

		$("#clientUserForm").form('clear').form('disableValidation');

		$('#addClientUserDialog').dialog({
			closed : false,
			modal : true,
			title : "新增设备用户"
		});
	}

	/**
	 * button menu => show the dialog of update
	 */
	function editClientUserDialog() {
		var row = $('#clientUserList').datagrid('getSelected');

		if (row) {
			$('#clientUserForm').form('clear').form('disableValidation');

			if (initFlag_clientUser == 0) {
				initDialog_clientUser();

				initFlag_clientUser = 1;
			}

			$('#clientUserForm').form('load', {
				clientUserId : row.clientUserId,
				clientUserName : row.clientUserName,
				clientType:row.clientType
			});

			// edit the #clientUserName
			$('#clientUserName').textbox({
				editable : false
			});

			$.post("client_user_resource/findByClientUserId", {
				clientUserId : row.clientUserId
			}, function(data) {
				var json = eval("(" + data + ")");
				//console.info('***'+json);
				var array = [];
				for (var i = 0; i < json.length; i++) {
					array[i] = json[i].resourceId;
				}
				$("#clientUserRole").combobox("setValues", array);
			});

			$('#addClientUserDialog').dialog({
				closed : false,
				modal : true,
				title : "编辑设备用户"
			});
		} else {
			$.messager.alert('信息提示', '请选择一行记录！', 'info');
		}
	}

	/**
	 * function => action => submit the client user
	 */
	function submitClientUser() {
		$('#clientUserForm').form('submit', {
			url : 'client_user/addClientUser',
			onSubmit : function(param) {
				param.companyId = $("#index_user_companys").combobox("getValue");
				return $(this).form('enableValidation').form('validate');
			},
			success : function(data) {
				//console.info('client_user/addClientUser:end');
				//console.info(data);
				var msg = eval('(' + data + ')');
				$.messager.alert('信息提示', msg.msg, 'info');
				if (msg.code == 1) {
					$('#addClientUserDialog').window('close');
					$('#clientUserList').datagrid('reload');
				}
			}
		});
	}

	/**
	 * button menu => delete the sale order
	 */
	function delClientUser() {
		var row = $('#clientUserList').datagrid('getSelected');
		if (row) {
			$.messager.confirm('删除设备用户信息', '确认删除?', function(r) {
				if (r) {
					$.post("client_user/delClientUser", {
						clientUserId : row.clientUserId
					}, function(data) {
						var msg = eval('(' + data + ')');
						$.messager.alert('信息提示', msg.msg, 'info');
						if (msg.code == 1) {
							$('#clientUserList').datagrid('reload');
						}
					});
				}
			});
		} else {
			$.messager.alert('信息提示', '请选择一行记录！', 'info');
		}
	}

	/**
	 *设置权限
	 */
	function setPasswordWindow_cu() {
		var row = $('#clientUserList').datagrid('getSelected');
		if (row) {
			$('#clientPassword_sp_cu').textbox('clear');
			$('#clientPasswordAgain_sp_cu').textbox('clear');

			$("#setPassword_cu").window('open');
		} else {
			$.messager.alert('信息提示', '请选择一行记录！', 'info');
		}
	}

	/**
	 *保存角色对应的权限
	 */
	function savePermissions_cu() {
		var row = $('#clientUserList').datagrid('getSelected');
		if (row) {
			$.post("client_user/saveNewPassword",
					{
						clientUserId : row.clientUserId,
						clientPassword : $('#clientPassword_sp_cu').textbox(
								'getValue'),
						clientPasswordAgain : $('#clientPasswordAgain_sp_cu')
								.textbox('getValue')
					}, function(data) {
						var msg = eval('(' + data + ')');
						$.messager.alert('信息提示', msg.msg, 'info');
						if (msg.code == 1) {
							$("#setPassword_cu").window('close');
						}
					});
		} else {
			$.messager.alert('信息提示', '请选择一行记录！', 'info');
		}
	}
	//***** end of function *****
</script>