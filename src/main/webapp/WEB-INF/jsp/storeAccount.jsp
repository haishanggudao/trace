<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<div id="storeAccountBar" style="padding: 5px 0;">
	<shiro:hasPermission name="system:storeAccount:add">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-add-s1'" onclick="openstoreAccountDialog()"  plain="true">新增账户</a>
		<span style="color:#D3D3D3 " >| </span>
			
	</shiro:hasPermission>
	<shiro:hasPermission name="system:storeAccount:edit">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit-s1'" onclick="editstoreAccountDialog()"  plain="true">编辑账户</a>
		<span style="color:#D3D3D3 " >| </span>
	</shiro:hasPermission>
	<shiro:hasPermission name="system:storeAccount:del">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-delete-s1'" onclick="deleteStoreAccount()"  plain="true">删除账户</a>
		<span style="color:#D3D3D3 " >| </span>
	</shiro:hasPermission>
</div>

<!-- start of table list -->
<table id="storeAccountList" title="门店账户列表"
	style="width: auto; height: 350px" singleSelect="true"
	fitColumns="true">
</table>
<!-- end of table list -->

<!-- begin of adding dialog -->
<div id="addstoreAccountDialog" class="easyui-dialog" title="编辑" 
data-options="modal:true,closed:true,iconCls:'icon-document'" 
style="width: 420px;padding: 10px;" buttons="#dlg_storeAccount-buttons">
	<form id="storeAccountForm" method="post">
		<input type="hidden" name="id">
		<table>
			<tr height="35">
				<td width="90" align="right">用户名：</td>
				<td><input id="storeAccountName" name="username"
					type="text" class="easyui-textbox" style="width: 266px" data-options="prompt:'输入用户名',required:true"/></td>
			</tr>
			<tr height="35">
				<td width="90" align="right">密码：</td>
				<td><input name="password" type="text" class="easyui-textbox" style="width: 266px" data-options="prompt:'输入门店账户密码',required:true"/></td>
			</tr>
			<tr height="35">
				<td width="90" align="right">门店名：</td>
				<td><input name="nickName" type="text" class="easyui-textbox" style="width: 266px" data-options="prompt:'输入门店名',required:true"/></td>
			</tr>
			<tr height="35">
				<td width="90" align="right">所属门店：</td>
				<td><input type="hidden" id="customerId_storeAccount" name="companyId"><select id="customerName_storeAccount" name="companyName"  style="width:100%;"></select></td>
			</tr>
		</table>
	</form>
</div>
<!-- begin of adding dialog -->
<div id="dlg_storeAccount-buttons"  style="text-align: center;">
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="submitstoreAccount()" style="width:180px;height: 26px">确认提交</a>

</div>

<div id="queryStore_storeAccount" class="easyui-dialog" title="选择门店" 
	data-options="modal:true,closed:true,iconCls:'icon-document'"
	style="width: 600px; padding: 2px;" >
&nbsp;门店名称：&nbsp;
<input id="storeName_storeAccount" class="easyui-textbox" style="width: 200px">
<a href="#" class="easyui-linkbutton" iconcls="icon-search" onclick="loadStore_StoreAccount()">搜索</a>
<table id="storeList_storeAccount" ></table>
<br/><br/>
</div>
<script type="text/javascript">
	$(function() {
		loadstoreAccounts();
	});

	function loadstoreAccounts() {
		<shiro:hasPermission name="system:storeAccount:edit">
			 $('#storeAccountList').datagrid({
				onDblClickCell: function(index,field,value){
					editstoreAccountDialog();
				}
			 });
		</shiro:hasPermission>
		$('#storeAccountList').datagrid({
			method : 'post',
			url : 'storeAccount/findAllList',
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
			toolbar : '#storeAccountBar',
			onBeforeLoad : function(param) {
				var myUserCompanyId = $('#index_user_companys').combobox('getValue');
				param.companyId = myUserCompanyId;
			},
			columns : [ [ {
				field : 'username',
				title : '用户名',
				width : 100,
			},{
				field : 'password',
				title : '密码',
				width : 50,
			},{
				field : 'nickName',
				title : '门店名',
				width : 50,
			},{
				field : 'customerAlias',
				title : '门店名称',
				width : 100,
				formatter : function(val, row, index) {
					return row.company.name;
				}
			}  ] ]
		});
	}
	/**
	 * function => UI => initialize the dialog for adding
	 */
	function initDialog_storeAccount() {
		$("#customerName_storeAccount").textbox({
			editable:false,
			required:true,
			icons:[{
				iconCls:'icon-search',
				handler: function(e){
                    $('#storeName_storeAccount').textbox('clear');
					loadStore_StoreAccount();
					$('#queryStore_storeAccount').window('open'); 
					
				}
			}]
		});
	}

	 function loadStore_StoreAccount(){
		 $("#storeList_storeAccount").datagrid(
					{
						url :"company/findAllList",
						method : 'post',
						rownumbers : true,
						pageSize : 10,
						pageList : [ 10, 20, 50 ],
						pagination : true,
						pagePosition:'bottom',
						multiSort : true,
						fitColumns : true,
						iconCls : 'icon-ok',
						singleSelect : true,
						onBeforeLoad : function(param) {
							param.name = $("#storeName_storeAccount").val();
						},
						onDblClickRow: function(index,row){
							$("#customerName_storeAccount").textbox('setValue',row.name);
							$("#customerId_storeAccount").val(row.companyid);
							$('#queryStore_storeAccount').window('close');
						},
						columns : [ [ {
							field : 'name',
							title : '门店名称',
							width : 100
						}  ] ]
					});
	 }
	/**
	 * button menu => show the dialog of insert
	 */
	function openstoreAccountDialog() {
		initDialog_storeAccount();
		$("#storeAccountForm").form('clear').form('disableValidation');

		$('#addstoreAccountDialog').dialog({
			closed : false,
			modal : true,
			title : "新增门店账户"
		});
	}

	/**
	 * button menu => show the dialog of update
	 */
	function editstoreAccountDialog() {
		var row = $('#storeAccountList').datagrid('getSelected');
		console.info('@'+row)
		if (row) {
			$('#storeAccountForm').form('clear').form('disableValidation');
            
			initDialog_storeAccount();
			$('#storeAccountForm').form('load', {
				id : row.id,
				username : row.username,
				password:row.password,
				nickName:row.nickName,
				companyId :row.company.companyid,
				companyName: row.company.name
			});

			$('#addstoreAccountDialog').dialog({
				closed : false,
				modal : true,
				title : "编辑账户"
			});
			console.info(row)
		} else {
			$.messager.alert('信息提示', '请选择一行记录！', 'info');
		}
	}

	/**
	 * function => action => submit the client user
	 */
	function submitstoreAccount() {
		$('#storeAccountForm').form('submit', {
			url : 'storeAccount/addstoreAccount',
			onSubmit : function(param) {
				return $(this).form('enableValidation').form('validate');
			},
			success : function(data) {
				var msg = eval('(' + data + ')');
				$.messager.alert('信息提示', msg.msg, 'info');
				if (msg.code == 1) {
					$('#addstoreAccountDialog').window('close');
					$('#storeAccountList').datagrid('reload');
				}
			}
		});
	}
	 /**
		 * button =>  删除账户
		 */
		function deleteStoreAccount() {
			 var row = $('#storeAccountList').datagrid('getSelected');
			if (row) {
				$.messager.confirm('信息提示', '确定要删除该记录？', function(result) {
					if (result) {
						$.post("storeAccount/deleteStoreAccount", {
							id : row.id
						}, function(data) {
							$.messager.alert('信息提示', data.msg, 'info');
							if (data.code == 1) {
								$('#storeAccountList').datagrid('reload');
							}
						});
					}
				});
			} else {
				$.messager.alert('信息提示', '请选择产品！', 'info');
			}
		}

</script>