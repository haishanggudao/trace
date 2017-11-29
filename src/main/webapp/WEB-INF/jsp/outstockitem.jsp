<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<div id="OutstockItemBtns" style="padding: 5px 0;">
	<shiro:hasPermission name="base:outstockitem:add">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-add-s1'" onclick="addOutstockItem()">新增</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="base:outstockitem:edit">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit-s1'" onclick="editOutstockItem()">编辑</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="base:outstockitem:delete">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-delete-s1'" onclick="removeOutstockItem()">删除</a>
	</shiro:hasPermission>
</div>
<table id="OutstockItemsgrid" title="商品出库明细列表" style="width: 100%;"></table>
<div id="editOutstockItem" class="easyui-window" title="新增"
	data-options="modal:true,closed:true,iconCls:'icon-document'"
	style="width: 500px; padding: 10px;">
	<form id="OutstockItemForm" method="post">
		<!-- 出库明细ID -->
		<input type="hidden" name="outstockItemId" />
		<input type="hidden" name="traceCode" />
		<div>出库单:</div>
		<br /><select id="outstockMainId" name="outstockMainId" style="width:100%;"></select>
		<p></p>
		<div>商品:</div>
		<br /><select id="goodsId" name="goodsId" style="width:100%;"></select>
		<p></p>
		<div>出库数量:</div>
		<br /><input class="easyui-textbox" name="outstockNum"
			data-options="prompt:'出库单号',validType:'name',required:true"
			style="width: 100%; height: 24px">
		<p></p>
	</form>
	<div style="margin: 10px 0;">
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
			style="width: 100%; height: 24px"
			onclick="saveOutstockItem('OutstockItemForm','editOutstockItem','OutstockItemsgrid')">确认提交</a>
	</div>
</div>
<script type="text/javascript">
	
	$(document).ready(function() {
		loadSelectData();
		$('#OutstockItemsgrid').datagrid({
			url : 'outstockitem/list',
			rownumbers : true,
			pagination : true,
			pageSize : 10,
			pageList : [ 10, 20, 30 ],
			singleSelect : true,
			fitColumns : true,
			fit : true,
			toolbar : '#OutstockItemBtns',
			columns : [ [ {
				field : 'outstockItemId',
				title : '--',
				hidden : true
			}, {
				field : 'outstockMainId',
				title : '--',
				hidden : true
			}, {
				field : 'outstockNum',
				title : '出库数量',
				width : '99%'
			}, {
				field : 'traceCode',
				title : '--',
				hidden : true
			}, {
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
			}] ]
		});
	});

	/**
	 *打开新增用户窗口
	 */
	function addOutstockItem() {
		$("#OutstockItemForm").form('clear').form('disableValidation');
		$("#editOutstockItem").window('open');
	}

	/**
	 *打开修改用户窗口
	 */
	function editOutstockItem() {
		var row = $('#OutstockItemsgrid').datagrid('getSelected');
		if (row) {
			$("#OutstockItemForm").form('clear').form('disableValidation');
			$('#OutstockItemForm').form('load', {
				outstockItemId:row.outstockItemId,
				outstockMainId:row.outstockMainId,
				outstockNum:row.outstockNum,
				traceCode:row.traceCode,
				createTime:row.createTime,
				createBy:row.createBy,
				updateTime:row.updateTime,
				updateBy:row.updateBy
			});
			$("#outstockMainId").combogrid("setValues", row.outstockMainId);
			$("#goodsId").combogrid("setValues", row.goodsId);
			$("#editOutstockItem").window('open');
		} else {
			$.messager.alert('信息提示', '请选择一行记录！', 'info');
		}
	}

	function removeOutstockItem() {
		var row = $('#OutstockItemsgrid').datagrid('getSelected');
		if (row) {
			$.ajax({
				url:'outstockitem/delete',
				data:{id:row.instockItemId},
				success:function(data){
					data = eval('('+data+')');
					if(data.code == '1') {
						$.messager.alert('信息提示','删除成功','info');
						loadSelectData();
						$('#OutstockItemsgrid').datagrid('reload');
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
		$("#outstockMainId").combogrid({
			panelWidth : 500,
			url : 'outstockitem/getoutstockmains',
			method : 'post',
			multiple : false,
			required:true,
			prompt : '商品出库信息',
			idField : 'outstockMainId',
			textField : 'outstockNum',
			fitColumns : true,
			editable : false,
			columns : [ [ {
				field : 'consignor',
				title : '出货人',
				width : '49%'
			}, {
				field : 'registrant',
				title : '登记人',
				width : '49%'
			} ] ]
		});
		$("#goodsId").combogrid({
			panelWidth : 500,
			url : 'outstockitem/getgoods',
			method : 'post',
			multiple : false,
			required:true,
			prompt : '商品',
			idField : 'goodsId',
			textField : 'num',
			fitColumns : true,
			editable : false,
			columns : [ [ {
				field : 'goodsBatch',
				title : '商品批次',
				width : '49%'
			}, {
				field : 'BigDecimal',
				title : '数量',
				width : '49%'
			} ] ]

		});
	}

	/**
	 *保存
	 */
	function saveOutstockItem(editForm, editWindow, OutstockItemGrid) {
		$('#' + editForm).form('submit', {
			url : 'outstockitem/save',
			onSubmit : function() {
				return $(this).form('enableValidation').form('validate');
			},
			success : function(data) {
				var msg = eval('(' + data + ')');
				$.messager.alert('信息提示', msg.msg, 'info');
				if (msg.code == 1) {
					$('#' + editWindow).window('close');
					loadSelectData();
					$('#' + OutstockItemGrid).datagrid('reload');
				}
			}
		});
	}
</script>