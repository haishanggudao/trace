<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<div id="roleBar" style="padding: 5px 0;">
	<shiro:hasPermission name="system:role:create">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-add-s1'" onclick="openRoleWindow()">新增角色</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="system:role:update">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit-s1'" onclick="editRole()">编辑角色</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="system:role:del">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-delete-s1'" onclick="delRole();">删除角色</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="system:role:permissions">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit-s1'" onclick="setPermissions()">设置权限</a>
	</shiro:hasPermission>
</div>


<table id="rolelist" title="角色列表" style="width: auto; height: 350px">
	<thead>
		<tr>
			<th data-options="field:'id',width:80">编号</th>
			<th data-options="field:'role_name',width:100">角色名称</th>
			<th data-options="field:'description',width:80">描述</th>
		</tr>
	</thead>
</table>
<div id="roleDialog" class="easyui-dialog" title="新增" 
data-options="modal:true,closed:true,iconCls:'icon-document'"
style="width: 500px; padding: 10px;">
	<form id="roleForm" method="post">
		<input type="hidden" name="id">
		<div>角色名称:</div>
		<br /> <input class="easyui-textbox" name="role_name"
			data-options="prompt:'输入角色名称...',validType:'name',required:true"
			style="width: 100%; height: 32px">
		<p></p>
		<div>描述内容:</div>
		<br /> <input class="easyui-textbox" name="description"
			data-options="multiline:true,validType:'name',required:true"
			style="width: 100%; height: 62px">
	</form>
	<div style="margin: 10px 0;">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
			style="width: 100%; height: 32px" onclick="submitForm()">确认提交</a>
	</div>
</div>



<!-- 设置权限 -->
<div id="setpermissions" class="easyui-window" title="设置权限"
	data-options="modal:true,closed:true,iconCls:'icon-save'"
	style="padding: 10px;height: 70%;width: 70%;">

	<table id="setgrid" title="权限设置" style="width: auto; height: auto;">

	</table>
	<div style="padding: 5px 0;">

			<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="savePermissions()">确认</a>
	</div>

</div>


<script type="text/javascript">
	//新增角色
	function submitForm() {
		$('#roleForm').form('submit', {
			url : 'role/addRole',
			onSubmit : function() {
				return $(this).form('enableValidation').form('validate');
			},
			success : function(data) {
				var msg = eval('(' + data + ')');
				$.messager.alert('信息提示', msg.msg, 'info');
				if (msg.code == 1) {
					$('#roleDialog').window('close');
					$('#rolelist').datagrid('reload');
				}
			}
		});
	}

	// 删除角色
	function delRole() {
		var row = $('#rolelist').datagrid('getSelected');
		if (row) {
			$.messager.confirm('删除角色', '确认删除?', function(r) {
				if (r) {
					$.post("role/delRole", {
						roleId : row.id
					}, function(data) {
						var msg = eval('(' + data + ')');
						$.messager.alert('信息提示', msg.msg, 'info');
						if (msg.code == 1) {
							$('#rolelist').datagrid('reload');
						}
					})
				}
			});
		} else {
			$.messager.alert('信息提示', '请选择一行记录！', 'info');
		}

	}
	//打开新增角色窗口
	function openRoleWindow() {
		$("#roleForm").form('clear').form('disableValidation');
		$('#roleDialog').dialog({
			closed : false,
			modal : true,
			title : "新增角色",
			iconCls : 'icon-document'
		});
	}

	//编辑角色
	function editRole() {
		var row = $('#rolelist').datagrid('getSelected');
		if (row) {
			$('#roleForm').form('clear');
			$('#roleForm').form('load', {
				id : row.id,
				role_name : row.role_name,
				description : row.description
			});
			$('#roleDialog').dialog({
				closed : false,
				modal : true,
				title : "编辑角色",
				iconCls : 'icon-document'
			});
		} else {
			$.messager.alert('信息提示', '请选择一行记录！', 'info');
		}
	}
	/**
	 *设置权限
	 */
	function setPermissions() {
		var row = $('#rolelist').datagrid('getSelected');
		if (row) {
			$("#setgrid").tree({
				url : 'resource/list?roleId='+row.id,
				method:'post',
				fitColumns: true,
				rownumbers: true,
				checkbox:true,
			});
			$("#setpermissions").window('open');
		} else {
			$.messager.alert('信息提示', '请选择一行记录！', 'info');
		}
	}
	
	/**
	*保存角色对应的权限
	*/
	
	function savePermissions(){
		var result=new Array();
		var array=new Array();
		var permissions=$("#setgrid").tree("getChecked");
		for(var i=0;i<permissions.length;i++){
			var children=permissions[i].children;
			if(children!=null){
				$.merge(array,children);
				if($.inArray(permissions[i],array)==-1){
					result.push(permissions[i].id);
				}
			}else{
				if($.inArray(permissions[i],array)==-1){
					result.push(permissions[i].id);
				}
			}
		}
		var row = $('#rolelist').datagrid('getSelected');
		$.post("resource/saveRoleRelation",{roleId:row.id,resourceIds:result.toString()},function(data){
			var msg = eval('(' + data + ')');
			$.messager.alert('信息提示', msg.msg, 'info');
			if (msg.code == 1) {
				$("#setpermissions").window('close');
			}
		});
	}
	/**
	 * Name 载入数据
	 */
    <shiro:hasPermission name="system:role:update">
	 $('#rolelist').datagrid({
		onDblClickCell: function(index,field,value){
			editRole()
		}
	 });
	</shiro:hasPermission>
	$('#rolelist').datagrid({
		url : 'role/findAllList',
		rownumbers : true,
		pageSize : 10,
		pageList : [ 10, 20, 50 ],
		pagination : true,
		singleSelect : true,
		multiSort : true,
		fitColumns : true,
		fit : true,
		toolbar : '#roleBar'
	});
</script>