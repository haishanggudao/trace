<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<div id="CompanyFieldBtns" style="padding: 5px 0;">
	<shiro:hasPermission name="system:companyfield:add">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-add-s1'" onclick="addCompanyField()">新增领域</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="system:companyfield:edit">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit-s1'" onclick="editCompanyField()">编辑领域</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="system:companyfield:delete">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-delete-s1'" onclick="removeCompanyField()">删除选中</a>
	</shiro:hasPermission>
</div>
<table id="CompanyFieldsgrid" title="企业领域列表" style="width: 100%;"></table>
<div id="companyFieldDialog" class="easyui-dialog" title="新增" 
data-options="modal:true,closed:true,iconCls:'icon-document'"
style="width: 500px; padding: 10px;">
	<form id="CompanyFieldForm" method="post">
		<!-- 所属领域id -->
		<input type="hidden" name="companyFieldId" />
		<div>企业领域名称:</div>
		<br /><input class="easyui-textbox" name="companyFieldName"
			data-options="prompt:'企业领域名称',validType:'name',required:true"
			style="width: 100%; height: 24px" />
		<p></p>
		<div>分类等级:</div>
		<br /><input class="easyui-numberbox" name="level"
			data-options="prompt:'分类等级',validType:'name',required:true,precision:0"
			style="width: 100%; height: 24px">
		<p></p>
		<div>父领域ID:</div>
		<br /><select id="parentFieldId" name="parentFieldId" style="width:100%;"></select>
		<p></p>
		<div>企业领域简介:</div>
		<br /><input class="easyui-textbox" name="companyFieldDesc"
			data-options="prompt:'企业领域简介',validType:'name',required:true"
			style="width: 100%; height: 24px">
		<p></p>
	</form>
	<div style="margin: 10px 0;">
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
			style="width: 100%; height: 24px"
			onclick="saveCompanyField('CompanyFieldForm','companyFieldDialog','CompanyFieldsgrid')">确认提交</a>
	</div>
</div>
<script type="text/javascript">
	
	$(document).ready(function() {
		loadSelectData();
		<shiro:hasPermission name="system:companyfield:edit">
		 $('#CompanyFieldsgrid').datagrid({
			onDblClickCell: function(index,field,value){
				editCompanyField()
			}
		 });
		</shiro:hasPermission>
		$('#CompanyFieldsgrid').datagrid({
			url : 'companyfield/findAllList',
			rownumbers : true,
			pagination : true,
			pageSize : 10,
			pageList : [ 10, 20, 30 ],
			singleSelect : true,
			fitColumns : true,
			fit : true,
			toolbar : '#CompanyFieldBtns',
			columns : [ [ {
				field : 'companyFieldId',
				title : '所属领域ID',
				hidden : true
			}, {
				field : 'companyFieldName',
				title : '企业领域名称',
				width : '50%'
			}, {
				field : 'level',
				title : '分类等级',
				width : '50%'
			}, {
				field : 'parentFieldId',
				title : '父领域ID',
				hidden : true
			}, {
				field : 'status',
				title : '状态',
				hidden : true
			} , {
				field : 'createTime',
				title : '创建时间',
				hidden : true
			}, {
				field : 'createBy',
				title : '创建者',
				hidden : true
			}, {
				field : 'updateTime',
				title : '更新时间',
				hidden : true
			}, {
				field : 'updateBy',
				title : '更新者',
				hidden : true
			}, {
				field : 'companyFieldDesc',
				title : '企业领域简介',
				hidden : true
			}] ]
		});
	});

	/**
	 *打开新增用户窗口
	 */
	function addCompanyField() {
		$("#CompanyFieldForm").form('clear').form('disableValidation');
		$('#companyFieldDialog').dialog({
			closed : false,
			modal : true,
			title : "新增领域",
			iconCls : 'icon-document'
		});
		
		
		
	}

	/**
	 *打开修改用户窗口
	 */
	function editCompanyField() {
		var row = $('#CompanyFieldsgrid').datagrid('getSelected');
		// console.info(row);
		if (row) {
			// console.info( row.companyFieldDesc == null);
			// 父领域ID
			var theRowParentFieldId = '';
			if (row.parentFieldId == null) {
				theRowParentFieldId = '';
			} else {
				theRowParentFieldId = row.parentFieldId;
			}
			
			// 企业领域简介
			var theRowParentFieldDesc = '';
			if (row.companyFieldDesc == null) {
				theRowParentFieldDesc = '';
			} else {
				theRowParentFieldDesc = row.companyFieldDesc;
			}
			
			$("#CompanyFieldForm").form('clear').form('disableValidation');
			$('#CompanyFieldForm').form('load', {
				companyFieldId:row.companyFieldId,
				companyFieldName:row.companyFieldName,
				level:row.level,
				parentFieldId: theRowParentFieldId,
				status:row.status,
				createTime:row.createTime,
				createBy:row.createBy,
				updateTime:row.updateTime,
				updateBy:row.updateBy,
				companyFieldDesc:theRowParentFieldDesc
			});
			$("#parentFieldId").combogrid("setValues", theRowParentFieldId);
			$('#companyFieldDialog').dialog({
				closed : false,
				modal : true,
				title : "编辑领域",
				iconCls : 'icon-document'
			});
		} else {
			$.messager.alert('信息提示', '请选择一行记录！', 'info');
		}
	}

	function removeCompanyField() {
		var row = $('#CompanyFieldsgrid').datagrid('getSelected');
		if (row) {
			$.ajax({
				url:'companyfield/delete',
				data:{id:row.companyFieldId},
				success:function(data){
					data = eval('('+data+')');
					if(data.code == '1') {
						$.messager.alert('信息提示','删除成功','info');
						loadSelectData();
						$('#CompanyFieldsgrid').datagrid('reload');
					} else {
						$.messager.alert('信息提示',data.msg,'warn');
					}
				}
			});
		} else {
			$.messager.alert('信息提示', '请选择一行记录！', 'info');
		}
	}

	function loadSelectData() {
		$("#parentFieldId").combogrid({
			panelWidth : 500,
			url : 'companyfield/getcompanyfields?exceptid=' + $('#companyFieldId').val(),
			method : 'post',
			multiple : false,
			prompt : '父领域ID',
			idField : 'companyFieldId',
			textField : 'companyFieldName',
			fitColumns : true,
			editable : false,
			columns : [ [ {
				field : 'companyFieldName',
				title : '企业领域名称',
				width : '50%'
			}, {
				field : 'level',
				title : '分类等级',
				width : '50%'
			} ] ]

		});
	}

	/**
	 *保存
	 */
	function saveCompanyField(editForm, editWindow, CompanyFieldGrid) {
		$('#' + editForm).form('submit', {
			url : 'companyfield/save',
			onSubmit : function() {
				return $(this).form('enableValidation').form('validate');
			},
			success : function(data) {
				var msg = eval('(' + data + ')');
				$.messager.alert('信息提示', msg.msg, 'info');
				if (msg.code == 1) {
					$('#' + editWindow).window('close');
					loadSelectData();
					$('#' + CompanyFieldGrid).datagrid('reload');
				}
			}
		});
	}
</script>