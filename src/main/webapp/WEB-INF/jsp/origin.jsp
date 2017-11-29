<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<link rel="stylesheet" type="text/css" href="static/css/datagrid_util.css">
<div id="OriginBtns" style="padding: 5px 0;">
	<shiro:hasPermission name="base:origin:add">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-add-s1'" onclick="addOrigin()">新增源头信息</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="base:origin:edit">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit-s1'" onclick="editOrigin()">编辑源头信息</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="base:origin:delete">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-delete-s1'" onclick="removeOrigin()">删除源头信息</a>
	</shiro:hasPermission>
</div>
	<table id="Origingrid" title="源头信息列表" style="width: 100%;"></table>
<div id="editOriginDialog" class="easyui-dialog" title="编辑" 
data-options="modal:true,closed:true,iconCls:'icon-document'"
style="width: 440px;padding: 10px;" >
	<form id="OriginForm" method="post">
		<input type="hidden" name="originId" />
		<br/>
		<div class="fitem">
            <label class="fitemlabel width_100">渔船名称:</label>
            <input class="easyui-textbox" name="originName"
			data-options="prompt:'渔船名称',validType:'name',required:true"
			style="width: 260px; height: 24px">&nbsp;
        </div>
		<div class="fitem">
            <label class="fitemlabel width_100">联系人:</label>
            <input class="easyui-textbox" name="contact"
			data-options="prompt:'联系人',validType:'name',required:true"
			style="width: 260px; height: 24px">&nbsp;
        </div>

		<div class="fitem">
            <label class="fitemlabel width_100">电话:</label>
            <input class="easyui-textbox" name="tel"
			data-options="prompt:'电话',validType:'name'"
			style="width: 260px; height: 24px">&nbsp;
        </div>
		<div class="fitem">
            <label class="fitemlabel width_100">地址:</label>
            <input name="address" class="easyui-textbox"
			data-options="prompt:'地址',validType:'name',required:true"
			style="width: 260px; height: 24px;">&nbsp;
        </div>
        <div class="fitem">
            <label class="fitemlabel width_100">渔船编号:</label>
            <input class="easyui-textbox" name="originNo"
			data-options="prompt:'渔船编号',validType:'name'"
			style="width: 260px; height: 24px">&nbsp;
        </div>
        <div class="fitem">
            <label class="fitemlabel width_100">渔船坐标:</label>
            <input name="coordinate" class="easyui-textbox"
			data-options="prompt:'渔船坐标',validType:'name'"
			style="width: 260px; height: 24px;">&nbsp;
        </div>
		<div class="fitem">
            <label class="fitemlabel width_100">备注:</label>
            <input name="remark" class="easyui-textbox"
			data-options="multiline:true"
			style="width: 260px; height: 62px;">&nbsp;
        </div>
	</form>
	<div id="buttons-edtOrigin"  style="text-align: center;display: none;">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveOrigin()" style="width:120px;height: 32px">确认提交</a>
	</div>

</div>
<script type="text/javascript">
	$(document).ready(
			function() {
			  	<shiro:hasPermission name="base:origin:edit">
					 $('#Origingrid').datagrid({
						onDblClickCell: function(index,field,value){
							editOrigin()
						}
					 });
				</shiro:hasPermission>
				$('#Origingrid').datagrid(
						{
							url : 'origin/findAllList?companyId=' + $('#index_user_companys').combobox('getValue'),
							rownumbers : true,
							pagination : true,
							pageSize : 10,
							pageList : [ 10, 20, 30 ],
							singleSelect : true,
							fitColumns : true,
							fit : true,
							toolbar : '#OriginBtns',
							columns : [ [ {
								field : 'originName',
								title : '渔船名称',
								width : '25%'
							}, {
								field : 'originNo',
								title : '渔船编号',
								width : '15%'
							}, {
								field : 'contact',
								title : '联系人',
								width : '15%'
							}, {
								field : 'tel',
								title : '电话',
								width : '15%'
							}, {
								field : 'address',
								title : '地址',
								width : '28%'
							} ] ]
						});
			});

	/**
	 *打开新增用户窗口
	 */
	function addOrigin() {
		loadSelectData();
		$("#OriginForm").form('clear').form('disableValidation');
		
		$('#editOriginDialog').dialog({
			closed : false,
			modal : true,
			title : "新增源头信息",
			buttons : '#buttons-edtOrigin'
		});
		
		
	}

	/**
	 *打开修改用户窗口
	 */
	function editOrigin() {
		var row = $('#Origingrid').datagrid('getSelected');
		if (row) {
			$("#OriginForm").form('clear').form('disableValidation');
			loadSelectData();
			$('#OriginForm').form('load', {
				originId : row.originId,
				companyId : row.companyId,
				originName : row.originName,
				contact : row.contact,
				tel : row.tel,
				address : row.address,
				originNo : row.originNo,
				coordinate : row.coordinate,
				remark : row.remark,
			});
			$('#editOriginDialog').dialog({
				closed : false,
				modal : true,
				title : "编辑源头信息",
				buttons : '#buttons-edtOrigin'
			});
		} else {
			$.messager.alert('信息提示', '请选择一行记录！', 'info');
		}
	}

	function removeOrigin() {
		var row = $('#Origingrid').datagrid('getSelected');
		if (row) {
			$.ajax({
				url : 'origin/delOrigin',
				method : 'post',
				data : {
					originId : row.originId
				},
				success : function(data) {
					data = eval('(' + data + ')');
					if (data.code == '1') {
						$.messager.alert('信息提示', '删除成功', 'info');
						$('#Origingrid').datagrid('reload');
					} else {
						$.messager.alert('信息提示', data.msg, 'warn');
					}
				}
			});
		} else {
			$.messager.alert('信息提示', '请选择一行记录！', 'info');
		}
	}

	function loadSelectData() {
		
	}

	/**
	 *保存
	 */
	function saveOrigin() {
		$('#OriginForm').form(
				'submit',
				{
					url : 'origin/addOrigin',
					onSubmit : function(param) {
						param.companyId = $('#index_user_companys').combobox(
								'getValue');
						return $(this).form('enableValidation')
								.form('validate');
					},
					success : function(data) {
						var msg = eval('(' + data + ')');
						$.messager.alert('信息提示', msg.msg, 'info');
						if (msg.code == 1) {
							$('#editOriginDialog').window('close');
							$('#Origingrid').datagrid('reload');
						}
					}
				});
	}
</script>