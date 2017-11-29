<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<div id="variableBtns" style="padding: 5px 0;">
	<shiro:hasPermission name="system:variable:edit">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit-s1'" onclick="updateVariableWindow()">编辑参数</a>
	</shiro:hasPermission>
</div>
<table id="variablegrid" title="系统参数列表" style="width: auto;">
</table>
<div id="updateVariable" class="easyui-window" title="修改系统参数"
	data-options="modal:true,closed:true,iconCls:'icon-save'"
	style="width: 500px; padding: 10px;">
	<form id="variableForm" method="post">
		<input type="hidden" name="variableId">
		<div>系统参数键:</div>
		<br /> <input class="easyui-textbox" name="varkey"
			data-options="prompt:'输入系统参数键...',readonly:true"
			style="width: 100%; height: 32px">
		<p></p>
		<div>
			<div>系统参数值:</div>
			<br /> <input class="easyui-textbox" name="varValue"
				data-options="required:true"
				style="width: 100%; height: 32px">
		</div>
		<p></p>
	</form>


	<div style="margin: 10px 0;">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
			style="width: 100%; height: 32px"
			onclick="submitVariable()">确认提交</a>
	</div>

</div>
<script type="text/javascript">
	<shiro:hasPermission name="system:variable:edit">
		$('#variablegrid').datagrid({
			onDblClickCell: function(index,field,value){
				updateVariableWindow()
			}
		});
	</shiro:hasPermission>
	$('#variablegrid').datagrid({
		url : 'variable/findAllList',
		rownumbers : true,
		pagination : true,
		pageSize : 10,
		pagePosition : 'bottom',
		pageList : [ 10, 20, 50 ],
		multiSort : true,
		singleSelect : true,
		fitColumns : true,
		fit : true,
		toolbar : '#variableBtns',
		columns : [ [

		{
			field : 'varkey',
			title : '参数名',
			width : 100
		}, {
			field : 'varValue',
			title : '参数值',
			width : 200
		} ] ],

	});
	/**
	 *打开修改系统参数窗口
	 */
	function updateVariableWindow() {
		var row = $('#variablegrid').datagrid('getSelected');
		if (row) {
			$("#variableForm").form('clear').form('disableValidation');
			$('#variableForm').form('load', {
				variableId:row.variableId,
				varkey : row.varkey,
				varValue : row.varValue,
			});
			$("#updateVariable").window('open');
		} else {
			$.messager.alert('信息提示', '请选择一行记录！', 'info');
		}
	}
	/**
	 *保存系统参数
	 */
	function submitVariable() {
		$('#variableForm').form('submit', {
			url : 'variable/updateVariable',
			onSubmit : function() {
				return $(this).form('enableValidation').form('validate');
			},
			success : function(data) {
				var msg = eval('(' + data + ')');
				$.messager.alert('信息提示', msg.msg, 'info');
				if (msg.code == 1) {
					$('#updateVariable').window('close');
					$('#variablegrid').datagrid('reload');
				}
			}
		});
	}
</script>