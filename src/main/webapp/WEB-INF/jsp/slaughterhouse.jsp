<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<link rel="stylesheet" type="text/css" href="static/css/datagrid_util.css">
<div id="SlaughterhouseBtns" style="padding: 5px 0;">
	<shiro:hasPermission name="base:slaughterhouse:add">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-add-s1'" onclick="addSlaughterhouse()">新增屠宰场</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="base:slaughterhouse:edit">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit-s1'" onclick="editSlaughterhouse()">编辑屠宰场</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="base:slaughterhouse:delete">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-delete-s1'" onclick="removeSlaughterhouse()">删除屠宰场</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="base:slaughterhouse:import">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-excel'" onclick="importSlaughterhouse();">屠宰场信息导入</a>
	</shiro:hasPermission>
</div>
	<table id="Slaughterhousesgrid" title="屠宰场信息列表" style="width: 100%;"></table>
<div id="editSlaughterhouseDialog" class="easyui-dialog" title="编辑" 
data-options="modal:true,closed:true,iconCls:'icon-document'"
style="width: 440px;padding: 10px;" >
	<form id="SlaughterhouseForm" method="post">
		<!-- 所属领域id -->
		<input type="hidden" name="slaughterhouseId" />
		<br/>
		<div class="fitem">
            <label class="fitemlabel width_100">屠宰场名称:</label>
            <select id="slaughterhouseCompanyId" name="slaughterhouseCompanyId"
			style="width: 260px;"></select>&nbsp;
        </div>
        
		<div class="fitem">
            <label class="fitemlabel width_100">联系人:</label>
            <input class="easyui-textbox" name="contact"
			data-options="prompt:'联系人',validType:'name'"
			style="width: 260px; height: 24px">&nbsp;
        </div>
		
		<div class="fitem">
            <label class="fitemlabel width_100">联系人别名:</label>
            <input class="easyui-textbox" name="altContact"
			data-options="prompt:'联系人别名',validType:'name'"
			style="width: 260px; height: 24px">&nbsp;
        </div>

		<div class="fitem">
            <label class="fitemlabel width_100">电话:</label>
            <input class="easyui-textbox" name="tel"
			data-options="prompt:'电话',validType:'name'"
			style="width: 260px; height: 24px">&nbsp;
        </div>
		<div class="fitem">
            <label class="fitemlabel width_100">屠宰场地址:</label>
            <input name="slaughterhouseAddress" class="easyui-textbox"
			data-options="multiline:true"
			style="width: 260px; height: 62px;">&nbsp;
        </div>
        <div class="fitem">
            <label class="fitemlabel width_100">屠宰方式:</label>
            <input name="mode" class="easyui-textbox" data-options="prompt:'屠宰方式'" style="width: 260px; height: 62px;">
            &nbsp;
        </div>
		<div class="fitem">
            <label class="fitemlabel width_100">备注:</label>
            <input name="remark" class="easyui-textbox"
			data-options="multiline:true"
			style="width: 260px; height: 62px;">&nbsp;
        </div>
	</form>
	<div id="buttons-edtSlaughterhouse"  style="text-align: center;display: none;">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveSlaughterhouse()" style="width:120px;height: 32px">确认提交</a>
	</div>

</div>
<div id="importSlaughterhouseDiv" style="width: 400px; padding: 10px;">
	<form id="importSlaughterhouseForm" method="post"
		action="slaughterhouse/importslaughterhouse"
		enctype="multipart/form-data">
		<input class="easyui-filebox" style="width: 300px;"
			name="uploadImportFile"
			data-options="prompt:'屠宰场信息导入文件',required:true,buttonText: '选择文件',buttonAlign: 'right'" />
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-ok'"
			onclick="importSlaughterhouseSubmit();">提交</a> <a href="#"
			target="_blank" id="importSlaughterhouseSubmitTempl"
			class="easyui-linkbutton" data-options="iconCls:'icon-download-s1'">模板下载</a>
	</form>
</div>
<script type="text/javascript">
	//数据导入
	function importSlaughterhouse() {
		$('#importSlaughterhouseDiv').window({
			modal : true,
			closed : true,
			iconCls : 'icon-excel',
			title : '屠宰场信息导入'
		});
		$('#importSlaughterhouseDiv').window('open');
	}

	//数据导入
	function importSlaughterhouseSubmit() {
		$('#importSlaughterhouseForm').form(
				'submit',
				{
					url : 'slaughterhouse/importslaughterhouse',
					type : 'post',
					onSubmit : function(param) {
						param.companyId = $('#index_user_companys').combobox(
								'getValue');
						return $(this).form('enableValidation')
								.form('validate');
					},
					success : function(data) {
						var msg = eval('(' + data + ')');
						$.messager.alert('信息提示', msg.msg, 'info');
						if (msg.code == '1') {
							$('#importSlaughterhouseDiv').window('close');
							$('#Slaughterhousesgrid').datagrid('reload');
						}
					}
				});
	}

	$(document).ready(
			function() {
				$.get('logistics/getdownloadurl', function(data) {
					data = eval('(' + data + ')');
					if (data.url) {
						$('#importSlaughterhouseSubmitTempl').prop('href',
								data.url + '/slaughterhouseTemplate.xls');
					}
				});
			  	<shiro:hasPermission name="base:slaughterhouse:edit">
					 $('#Slaughterhousesgrid').datagrid({
						onDblClickCell: function(index,field,value){
							editSlaughterhouse()
						}
					 });
				</shiro:hasPermission>
				$('#Slaughterhousesgrid').datagrid(
						{
							url : 'slaughterhouse/findAllList?companyId=' + $('#index_user_companys').combobox('getValue'),
							rownumbers : true,
							pagination : true,
							pageSize : 10,
							pageList : [ 10, 20, 30 ],
							singleSelect : true,
							fitColumns : true,
							fit : true,
							toolbar : '#SlaughterhouseBtns',
							columns : [ [ {
								field : 'slaughterhouseName',
								title : '屠宰场名称',
								width : '25%'
							}, {
								field : 'contact',
								title : '联系人',
								width : '15%'
							}, {
								field : 'altContact',
								title : '联系人别名',
								width : '15%'
							}, {
								field : 'tel',
								title : '电话',
								width : '15%'
							}, {
								field : 'slaughterhouseAddress',
								title : '屠宰场地址',
								width : '30%'
							} ] ]
						});
			});

	/**
	 *打开新增用户窗口
	 */
	function addSlaughterhouse() {
		loadSelectData();
		$("#SlaughterhouseForm").form('clear').form('disableValidation');
		
		$('#editSlaughterhouseDialog').dialog({
			closed : false,
			modal : true,
			title : "新增屠宰场",
			buttons : '#buttons-edtSlaughterhouse'
		});
		
		
	}

	/**
	 *打开修改用户窗口
	 */
	function editSlaughterhouse() {
		var row = $('#Slaughterhousesgrid').datagrid('getSelected');
		if (row) {
			$("#SlaughterhouseForm").form('clear').form('disableValidation');
			loadSelectData();
			$('#SlaughterhouseForm').form('load', {
				slaughterhouseId : row.slaughterhouseId,
				slaughterhouseCompanyId : row.slaughterhouseCompanyId,
				contact : row.contact,
				altContact : row.altContact,
				tel : row.tel,
				slaughterhouseAddress : row.slaughterhouseAddress,
				remark : row.remark,
				mode: row.mode
			});
			$('#editSlaughterhouseDialog').dialog({
				closed : false,
				modal : true,
				title : "编辑屠宰场",
				buttons : '#buttons-edtSlaughterhouse'
			});
		} else {
			$.messager.alert('信息提示', '请选择一行记录！', 'info');
		}
	}

	function removeSlaughterhouse() {
		var row = $('#Slaughterhousesgrid').datagrid('getSelected');
		if (row) {
			$.ajax({
				url : 'slaughterhouse/delete',
				data : {
					id : row.slaughterhouseId
				},
				success : function(data) {
					data = eval('(' + data + ')');
					if (data.code == '1') {
						$.messager.alert('信息提示', '删除成功', 'info');
						$('#Slaughterhousesgrid').datagrid('reload');
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
		$("#slaughterhouseCompanyId").combobox({
			url : 'slaughterhouse/getslaughterhouseCompanys',
			method : 'post',
			required : true,
			valueField : 'slaughterhouseCompanyId',
			textField : 'slaughterhouseName',
			hasDownArrow : false,
			onBeforeLoad:function(param){
				param.companyId=$('#index_user_companys').combobox('getValue');
			}
		});
	}

	/**
	 *保存
	 */
	function saveSlaughterhouse() {
		$('#SlaughterhouseForm').form(
				'submit',
				{
					url : 'slaughterhouse/save',
					onSubmit : function(param) {
						param.companyName = $("#slaughterhouseCompanyId")
								.combobox('getText');
						param.companyId = $('#index_user_companys').combobox(
								'getValue');
						return $(this).form('enableValidation')
								.form('validate');
					},
					success : function(data) {
						var msg = eval('(' + data + ')');
						$.messager.alert('信息提示', msg.msg, 'info');
						if (msg.code == 1) {
							$('#editSlaughterhouseDialog').window('close');
							$('#Slaughterhousesgrid').datagrid('reload');
						}
					}
				});
	}
</script>