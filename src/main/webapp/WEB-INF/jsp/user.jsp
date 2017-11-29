<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<div id="userBtns" style="padding: 5px 0;">
	<shiro:hasPermission name="system:user:add">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-add-s1'" onclick="openUserWindow()">新增用户</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="system:user:update">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit-s1'" onclick="upadteUserWindow()">编辑用户</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="system:user:lockUser">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-lock'" onclick="lockUser();">锁定用户</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="system:user:updatePassword">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-application-key'" onclick="updatePassword();">修改密码</a>
	</shiro:hasPermission>
</div>
<table id="usersgrid" title="用户列表" style="width: auto;">
</table>
<div id="userDialog" class="easyui-dialog" title="新增" 
data-options="modal:true,closed:true,iconCls:'icon-document'"
style="width: 500px; padding: 10px;">
	<form id="userForm" method="post">
		<input type="hidden" name="id" />
		<div>用户名称:</div>
		<br /> <input class="easyui-textbox" name="username"
			data-options="prompt:'输入用户名...',validType:'name',required:true"
			style="width: 100%; height: 32px">
		<p></p>
		<div id="passDiv"><div>用户密码:</div>
		<br /> <input class="easyui-textbox" name="password" type="password"
			data-options="validType:'name',required:true"
			style="width: 100%; height: 32px">
			</div>
		<p></p>
		<div>用户昵称:</div>
		<br /> <input class="easyui-textbox" name="nickName"
			data-options="prompt:'输入用户昵称...',validType:'name',required:true"
			style="width: 100%; height: 32px">
		<p></p>
		<div>角色选择</div>
		<br /> <input id="userRole" name="roleIds" style="width: 100%;">
		<p></p>
		<div>用户企业选择：</div>
		<input type="hidden" name="user_company_Ids"  />
		<br/><select id="user_company_ids_text" name="userCompanyIdsText" style="width:100%;"></select>
		<p></p>
	</form>
	<div style="margin: 10px 0;">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
			style="width: 100%; height: 32px"
			onclick="saveUser('userForm','userDialog','usersgrid')">确认提交</a>
	</div>
</div>
<div id="queryusers_user_company_ids" class="easyui-dialog" title="用户企业选择" 
	data-options="modal:true,closed:true,iconCls:'icon-document'"
	style="width: 600px; padding: 2px;">
		&nbsp;企业名称：&nbsp;
		<input id="companyname_user_company_ids" class="easyui-textbox" style="width: 200px"> 
		<a href="#" class="easyui-linkbutton" data-options="iconcls:'icon-search'" onclick="query_user_company_ids()">搜索</a>
		<table id="queryusers_user_company_ids_table"></table>
		<div style="margin: 10px 0;">
			<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
			style="width: 100%; height: 32px"
			onclick="submit_user_company_ids()">确定选择</a>
		</div>
	</div>
<script type="text/javascript">

	var queryusers_user_company_ids = new Set();;
	var queryusers_user_company_names = new Set();;

	<shiro:hasPermission name="system:user:update">
	$('#usersgrid').datagrid({
		onDblClickCell : function(index, field, value) {
			upadteUserWindow()
		}
	});
	</shiro:hasPermission>
	$('#usersgrid').datagrid({
		url : 'user/findAllList',
		rownumbers : true,
		pagination : true,
		pageSize : 10,
		pagePosition : 'bottom',
		pageList : [ 10, 20, 50 ],
		multiSort : true,
		singleSelect : true,
		fitColumns : true,
		fit : true,
		toolbar : '#userBtns',
		columns : [ [
		{
			field : 'id',
			title : '编号',
			width : 80
		}, {
			field : 'username',
			title : '用户名',
			width : 100
		}, {
			field : 'nickName',
			title : '用户昵称',
			width : 100
		}, {
			field : 'locked',
			title : '状态',
			width : 80,
			formatter : function(val, row, index) {
				if (val) {
					return "已锁定";
				} else {
					return "未锁定";
				}
			}
		} ] ]
	});
	
	function submit_user_company_ids(){
		var _ids = '';
		var _names = '';
		queryusers_user_company_ids.forEach(function(item, sameItem, s) {
			if(_ids != '') {
				_ids += ',';
			}
			_ids += item;
		});
		queryusers_user_company_names.forEach(function(item, sameItem, s) {
			if(_names != '') {
				_names += ',';
			}
			_names += item;
		});
		$("#userForm input[name='user_company_Ids']").val(_ids);
		$("#user_company_ids_text").textbox('setValue',_names);
		$('#queryusers_user_company_ids').window('close');
	}
	
	$(function(){
		$("#user_company_ids_text").textbox({
			editable:false,
			required:true,
			icons:[{
				iconCls:'icon-search',
				handler: function(e){
					$('#queryusers_user_company_ids').window('open');
					$('#queryusers_user_company_ids').window('center');
					query_user_company_ids();
				}
			}]
		});
	});
	/**
	 *打开新增用户窗口
	 */
	function openUserWindow() {
		loadSelectData();
		$("#passDiv").find("input").prop("disabled", false);
		$("#passDiv").show();
		$("#userForm").form('clear').form('disableValidation');
		$('#userDialog').dialog({
			closed : false,
			modal : true,
			title : "新增用户",
			iconCls : 'icon-document'
		});
	}
	/**
	 *打开修改用户窗口
	 */
	function upadteUserWindow() {
		var row = $('#usersgrid').datagrid('getSelected');
		if (row) {
			loadSelectData();
			$("#userForm").form('clear').form('disableValidation');
			$("#passDiv").find("input").prop("disabled", true);
			$("#passDiv").hide();
			$('#userForm').form('load', {
				id : row.id,
				username : row.username,
				nickName : row.nickName,
				userCompanyIdsText : row.userCompanyIdsText
			});
			queryusers_user_company_ids.clear();
			queryusers_user_company_names.clear();
			if(row.userCompanyIdsText != '') {
				var names = row.userCompanyIdsText.split(',');
				for(var i in names){
					queryusers_user_company_names.add(names[i]);
				}
			}
			$.post("user/roleRelation", {
				userId : row.id
			}, function(data) {
				var json = eval("(" + data + ")");
				var array = [];
				for (var i = 0; i < json.length; i++) {
					array[i] = json[i].id;
				}
				$("#userRole").combobox("setValues", array);
			});
			$.post("user/getCompanyRelation", {
				userId : row.id
			}, function(data) {
				var json = eval("(" + data + ")");
				for(var i in json){
					queryusers_user_company_ids.add(json[i]);
				}
			});
			$('#userDialog').dialog({
				closed : false,
				modal : true,
				title : "编辑用户",
				iconCls : 'icon-document'
			});
		} else {
			$.messager.alert('信息提示', '请选择一行记录！', 'info');
		}
	}

	function loadSelectData() {
		$("#userRole").combobox({
			url : 'role/combobox',
			method : 'post',
			valueField : 'id',
			textField : 'role_name',
			multiple : true,
			panelHeight : 'auto',
			required : true
		});
	}
	
	function queryusers_user_company_ids_table_onselect(index, row) {
		queryusers_user_company_ids.add(row.companyid);
		queryusers_user_company_names.add(row.name);
	}
	
	function queryusers_user_company_ids_table_onunselect(index, row) {
		queryusers_user_company_ids.delete(row.companyid);
		queryusers_user_company_names.delete(row.name);
	}
	
	function queryusers_user_company_ids_table_onloadsuccess(data) {
		$('#queryusers_user_company_ids_table').datagrid('clearSelections');
		queryusers_user_company_ids.forEach(function(item, sameItem, s) {
			$('#queryusers_user_company_ids_table').datagrid('selectRecord', item);
		});
	}

	function query_user_company_ids() {
		$('#queryusers_user_company_ids_table')
				.datagrid(
						{
							url : 'company/findAllList',
							method : 'post',
							idField:'companyid',
							rownumbers : true,
							height : 300,
							pageNumber:1,
							pageSize : 10,
							pageList : [ 10, 20, 30 ],
							pagination : true,
							pagePosition : 'bottom',
							fitColumns : true,
							iconCls : 'icon-ok',
							singleSelect : false,
							onSelect : queryusers_user_company_ids_table_onselect,
							onUnselect : queryusers_user_company_ids_table_onunselect,
							onLoadSuccess : queryusers_user_company_ids_table_onloadsuccess,
							onBeforeLoad : function(param) {
								param.name = $("#companyname_user_company_ids")
										.textbox('getValue');
							},
							columns : [ [ {
								field : 'name',
								title : '企业名称',
								width : 100,
								sortable : true
							}, {
								field : 'contact',
								title : '联系人',
								hidden : true
							},
							] ]
						});
	}
	/**
	 *保存用户
	 */
	function saveUser(userForm, userWindow, userGrid) {
		$('#' + userForm).form('submit', {
			url : 'user/addUser',
			onSubmit : function() {
				return $(this).form('enableValidation').form('validate');
			},
			success : function(data) {
				var msg = eval('(' + data + ')');
				$.messager.alert('信息提示', msg.msg, 'info');
				if (msg.code == 1) {
					$('#' + userWindow).window('close');
					$('#' + userGrid).datagrid('reload');
				}
			}
		});
	}
	/**
	 *锁定用户
	 */
	function lockUser() {
		var row = $('#usersgrid').datagrid('getSelected');
		if (row) {
			$.messager.confirm('提示', '确认锁定所选用户吗?', function(r) {
				if (r) {
					$.post("user/lockUser", {
						id : row.id,
						locked : row.locked
					}, function(data) {
						var msg = eval('(' + data + ')');
						$.messager.alert('信息提示', msg.msg, 'info');
						if (msg.code == 1) {
							$('#usersgrid').datagrid('reload');
						}
					});
				}
			});
		} else {
			$.messager.alert('信息提示', '请选择一行记录！', 'info');
		}
	}

	/*
	 *修改密码
	 */
	function updatePassword() {
		var row = $('#usersgrid').datagrid('getSelected');
		if (row) {
			$.messager.prompt('修改密码', '请输入新的密码', function(r) {
				if (r) {
					$.post("user/updatePassword", {
						id : row.id,
						username : row.username,
						password : r
					}, function(data) {
						var msg = eval('(' + data + ')');
						$.messager.alert('信息提示', msg.msg, 'info');
					});
				} else {
					$.messager.alert("修改密码", "密码不能为空", "error");
				}
			});
		} else {
			$.messager.alert('信息提示', '请选择一行记录！', 'info');
		}
	}
</script>