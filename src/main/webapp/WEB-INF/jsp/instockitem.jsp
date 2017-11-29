<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<div id="InstockItemBtns" style="padding: 5px 0;">
	<shiro:hasPermission name="base:instockitem:add">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-add-s1'" onclick="addInstockItem()">新增</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="base:instockitem:edit">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit-s1'" onclick="editInstockItem()">编辑</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="base:instockitem:delete">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-delete-s1'" onclick="removeInstockItem()">删除</a>
	</shiro:hasPermission>
</div>
<table id="InstockItemsgrid" title="商品入库明细列表" style="width: 100%;"></table>
<div id="editInstockItem" class="easyui-window" title="新增"
	data-options="modal:true,closed:true,iconCls:'icon-document'"
	style="width: 500px; padding: 10px;">
	<form id="InstockItemForm" method="post">
		<!-- 入库明细ID -->
		<input type="hidden" name="instockItemId" />
		<div>入库单:</div>
		<br /><select id="instockMainId" name="instockMainId" style="width:100%;"></select>
		<p></p>
		<div>商品:</div>
		<br /><select id="goodsId" name="goodsId" style="width:100%;"></select>
		<p></p>
		<div>入库单号:</div>
		<br /><input class="easyui-textbox" name="instockNum"
			data-options="prompt:'入库单号',validType:'name',required:true"
			style="width: 100%; height: 24px">
		<p></p>
	</form>
	<div style="margin: 10px 0;">
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
			style="width: 100%; height: 24px"
			onclick="saveInstockItem('InstockItemForm','editInstockItem','InstockItemsgrid')">确认提交</a>
	</div>
</div>
<script type="text/javascript">
	
	$(document).ready(function() {
		loadSelectData();
		$('#InstockItemsgrid').datagrid({
			url : 'instockitem/list',
			rownumbers : true,
			pagination : true,
			pageSize : 10,
			pageList : [ 10, 20, 30 ],
			singleSelect : true,
			fitColumns : true,
			fit : true,
			toolbar : '#InstockItemBtns',
			columns : [ [ {
				field : 'instockItemId',
				title : '入库明细ID',
				hidden : true
			}, {
				field : 'instockMainId',
				title : '入库单ID',
				hidden : true
			}, {
				field : 'goodsId',
				title : '商品ID',
				hidden : true
			}, {
				field : 'instockNum',
				title : '入库单号',
				width : '90%'
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
	function addInstockItem() {
		$("#InstockItemForm").form('clear').form('disableValidation');
		$("#editInstockItem").window('open');
	}

	/**
	 *打开修改用户窗口
	 */
	function editInstockItem() {
		var row = $('#InstockItemsgrid').datagrid('getSelected');
		if (row) {
			$("#InstockItemForm").form('clear').form('disableValidation');
			$('#InstockItemForm').form('load', {
				instockItemId:row.instockItemId,
				instockMainId:row.instockMainId,
				goodsId:row.goodsId,
				instockNum:row.instockNum,
				createTime:row.createTime,
				createBy:row.createBy,
				updateTime:row.updateTime,
				updateBy:row.updateBy
			});
			$("#instockMainId").combogrid("setValues", row.instockMainId);
			$("#goodsId").combogrid("setValues", row.goodsId);
			$("#editInstockItem").window('open');
		} else {
			$.messager.alert('信息提示', '请选择一行记录！', 'info');
		}
	}

	function removeInstockItem() {
		var row = $('#InstockItemsgrid').datagrid('getSelected');
		if (row) {
			$.ajax({
				url:'instockitem/delete',
				data:{id:row.instockItemId},
				success:function(data){
					data = eval('('+data+')');
					if(data.code == '1') {
						$.messager.alert('信息提示','删除成功','info');
						loadSelectData();
						$('#InstockItemsgrid').datagrid('reload');
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
		$("#instockMainId").combogrid({
			panelWidth : 500,
			url : 'instockitem/getinstockmains',
			method : 'post',
			multiple : false,
			required:true,
			prompt : '供应商信息',
			idField : 'instockMainId',
			textField : 'instocknum',
			fitColumns : true,
			editable : false,
			columns : [ [ {
				field : 'consignee',
				title : '收货人',
				width : '49%'
			}, {
				field : 'registrant',
				title : '登记人',
				width : '49%'
			} ] ]
		});
		$("#goodsId").combogrid({
			panelWidth : 500,
			url : 'instockitem/getgoods',
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
	function saveInstockItem(editForm, editWindow, InstockItemGrid) {
		$('#' + editForm).form('submit', {
			url : 'instockitem/save',
			onSubmit : function() {
				return $(this).form('enableValidation').form('validate');
			},
			success : function(data) {
				var msg = eval('(' + data + ')');
				$.messager.alert('信息提示', msg.msg, 'info');
				if (msg.code == 1) {
					$('#' + editWindow).window('close');
					loadSelectData();
					$('#' + InstockItemGrid).datagrid('reload');
				}
			}
		});
	}
</script>