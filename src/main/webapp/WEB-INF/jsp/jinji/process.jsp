<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<div id="processBar" style="padding: 5px 0;">
	<shiro:hasPermission name="operation:process:add">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-add-s1'" onclick="openProcessWindow()">新增记录</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="operation:process:edit">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit-s1'"
			onclick="openEditProcesssWindow('1')">编辑记录</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="operation:process:delete">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-delete-s1'" onclick="delProcess()">删除记录</a>
	</shiro:hasPermission>
</div>
<div id="flag" style="display: none;">1</div>
	<table id="processList" title="加工记录列表"
		style="width: auto; height: 350px">
	</table>
<div id="addProcessMainDialog" style="width: 560px; padding: 10px;">
	<form id="processForm" method="post">
		<input type="hidden" name="processMainId" id="processMainId" />
		<table style="width: 100%">
			<tr>
				<td width="70" align="right">产品：</td>
				<td><input class="easyui-combobox" id="productId_process"
					name="goods.productId" style="width: 266px;"></td>
			</tr>
			<tr>
				<td width="70" align="right">规格：</td>
				<td><input id="productStandardDetailId_process"
					name="goods.productStandardDetailId" style="width: 266px;">
				</td>
			</tr>
			<tr>
				<td width="70" align="right">加工者：</td>
				<td><input id="processId_process"
					name="processor.id" style="width: 266px;">
				</td>
			</tr>
			<tr id="goodsBatch_tr">
				<td width="70" align="right">商品批次：</td>
				<td><input class="easyui-combobox" id="goodsBatch_process"
					name="goods.goodsBatch" style="width: 266px;"
					data-options="valueField:'goodsBatch',textField:'goodsBatch'">
				</td>
			</tr>
			<tr>
				<td width="70" align="right">加工时间：</td>
				<td><input class="easyui-datetimebox" id="processTimeStr"
					name="processTimeStr" style="width: 150px" data-options="">
				</td>
			</tr>
			<tr>
				<td width="70" align="right">加工批次：</td>
				<td><input class="easyui-textbox" id="processBatch_process"
					name="processBatch" style="width: 200px"
					data-options="required:true" /></td>
			</tr>
			<tr>
				<td width="70" align="right">加工数量：</td>
				<td><input type="text" class="easyui-numberbox"
					id="processQuantity_process" name="processQuantity"
					style="text-align: right;" maxlength="12" size="12"
					data-options="min:0,
						max:99999.99,
						precision:2,
						required:true">
				</td>
			</tr>
			<tr>
				<td colspan="2" width="100%">
					<table id="templateItemTable" title="加工原料"
						style="width: 96%; height: auto">
					</table>
				</td>
			</tr>
			<!-- 			<tr id="tr_processRealBatch_process"> -->
			<!-- 				<td width="70" align="right">实际批次：</td> -->
			<!-- 				<td><input class="easyui-combobox" -->
			<!-- 					id="processRealBatch_process" name="processRealBatch" -->
			<!-- 					style="width: 266px;" -->
			<!-- 					data-options="valueField:'goodsBatch',textField:'goodsBatch',prompt:'实际加工批次（生产信息补录信息）'"> -->
			<!-- 				</td> -->
			<!-- 			</tr> -->
		</table>
	</form>
</div>

<script type="text/javascript">
	var initFlag_process = 0;
	var editIndex_template = undefined;
	var mainCount = 0;
	var rowsCatch = [];
	$(function() {
		loadProcessData();
	});

	/**
	 * 载入加工记录信息
	 */
	function loadProcessData() {
		<shiro:hasPermission name="operation:process:edit">
			 $('#processList').datagrid({
					onDblClickCell: function(index,field,value){
						openEditProcesssWindow('1');
					}
			 });
		</shiro:hasPermission>
		$('#processList').datagrid(
				{
					url : 'process/findAllList',
					rownumbers : true,
					pageSize : 10,
					pageList : [ 10, 20, 50 ],
					pagination : true,
					singleSelect : true,
					multiSort : true,
					fitColumns : true,
					fit : true,
					toolbar : '#processBar',
					onBeforeLoad : function(param) {
						var myUserCompanyId = $('#index_user_companys')
								.combobox('getValue');
						param.companyId = myUserCompanyId;
						param.type=0;
					},
					columns : [ [ {
						field : 'productStandardDetailId',
						title : '规格明细ID',
						hidden : true,
						formatter : function(val, row, index) {
							return row.goods.productStandardDetailId;
						}
					}, {
						field : 'goodsBatch',
						title : '商品批次',
						hidden : true,
						formatter : function(val, row, index) {
							return row.goods.goodsBatch;
						}
					}, {
						field : 'goodsId',
						title : '商品ID',
						hidden : true
					}, {
						field : 'processMainId',
						title : '加工ID',
						hidden : true
					}, {
						field : 'productName',
						title : '成品',
						width : 100,
						sortable : true,
						formatter : function(val, row, index) {
							return row.goods.productName;
						}
					}, {
						field : 'processTimeStr',
						title : '加工时间',
						width : 100
					}, {
						field : 'processBatch',
						title : '加工批次号',
						width : 100
					}, {
						field : 'processQuantity',
						title : '数量',
						width : 100
					} ] ]
				});
	}

	/**
	 * button => 打开新增加工窗口
	 */
	function openProcessWindow() {
		$('#flag').html('1');
		mainCount = 0;
		$("#goodsBatch_tr").hide();
		if (initFlag_process == 0) {
			initDialogComponent_process();
		}
		editIndex_template = undefined;
		$(".clear").remove();
		delprocessTemplateItemsRow();
		$("#productId_process").combobox('enable');
		$("#productStandardDetailId_process").combobox('enable');
		$("#goodsBatch_process").combobox('enable');
		$("#processQuantity_process").numberbox('enable');
		$('#processForm').form('clear').form('disableValidation');
		// controller-加工批次号
		var dt = new Date();
		var myBatchNo = '' + dt.getFullYear() + '' + (dt.getMonth() + 1) + ''
				+ dt.getDate() + '' + dt.getHours() + '' + dt.getMinutes() + ''
				+ dt.getSeconds();
		$('#processBatch_process').textbox('setValue', myBatchNo);

		// 加工时间
		var time = dt.getFullYear() + '-' + (dt.getMonth() + 1) + '-'
				+ dt.getDate() + ' ' + dt.getHours() + ':' + dt.getMinutes()
				+ ':' + dt.getSeconds();
		$('#processTimeStr').datetimebox('setValue', time);

		$("#type_process_item").combobox();
		$("#type_process_item").combobox("setValue", "0");
		$('#addProcessMainDialog').dialog({
			closed : false,
			modal : true,
			title : "新增加工",
			buttons : [ {
				text : '确定',
				iconCls : 'icon-ok',
				handler : submitProcess
			}, {
				text : '取消',
				iconCls : 'icon-cancel',
				handler : function() {
					$('#addProcessMainDialog').dialog('close');
				}
			} ]
		});
		// 		dosomething();
	}

	/**
	 * button => 打开修改加工窗口
	 */
	 
	function openEditProcesssWindow(flag) {
		$('#flag').html(flag);
		var row = $('#processList').datagrid('getSelected');
		if (row) {
			$("#goodsBatch_tr").show();
			if (initFlag_process == 0) {
				initDialogComponent_process();
			}
			editIndex_template = undefined;
			delprocessTemplateItemsRow();
			var url = 'product_standard_detail/list?productId='
					+ row.goods.productId;
			$('#productStandardDetailId_process').combobox('reload', url);

			url = 'goods/getgoods?productId=' + row.goods.productId;
			$('#goodsBatch_process').combobox('reload', url);
			$('#processRealBatch_process').combobox('reload', url);
			// 			dosomething();
			$(".clear").remove();
			var processRealBatchValue = row.processRealBatch;
			if (processRealBatchValue == null
					|| processRealBatchValue.length == 0) {
				processRealBatchValue = row.goods.goodsBatch;
			}
			// console.info(row);
			var processorId=row.processor==null?null:row.processor.id;
			$('#processForm')
					.form(
							'load',
							{
								processMainId : row.processMainId,
								goodsId : row.goodsId,
								processTimeStr : row.processTimeStr,
								processBatch : row.processBatch,
								processRealBatch : processRealBatchValue,
								processQuantity : row.processQuantity,
								'goods.productId' : row.goods.productId,
								'goods.productStandardDetailId' : row.goods.productStandardDetailId,
								'goods.goodsBatch' : row.goods.goodsBatch,
								'processor.id':processorId
							});
			$("#templateItemTable").datagrid('reload','process/findAllItemList?processMainId='+row.processMainId);
			$("#productId_process").combobox('disable');
			$("#productStandardDetailId_process").combobox('disable');
			$("#goodsBatch_process").combobox('disable');
			$("#processQuantity_process").numberbox('disable');
			$('#processForm').form('disableValidation');
			$('#addProcessMainDialog').dialog({
				closed : false,
				modal : true,
				title : "修改加工信息",
				buttons : [ {
					text : '确定',
					iconCls : 'icon-ok',
					handler : submitProcess
				}, {
					text : '取消',
					iconCls : 'icon-cancel',
					handler : function() {
						$('#addProcessMainDialog').dialog('close');
					}
				} ]
			});
		} else {
			$.messager.alert('信息提示', '请选择加工记录！', 'info');
		}
	}

	/**
	 * action => 保存加工记录
	 */
	function submitProcess() {
		$('#processForm').form(
				'submit',
				{
					url : 'process/addProcess',
					onSubmit : function(param) {
						endEditing_processTemplate();
						var rows = $("#templateItemTable").datagrid('getRows');
						param.processDetail = JSON.stringify(rows);
						param.companyId = $('#index_user_companys').combobox(
								'getValue');
						return $(this).form('enableValidation')
								.form('validate');
					},
					success : function(data) {
						var msg = eval('(' + data + ')');
						$.messager.alert('信息提示', msg.msg, 'info');
						if (msg.code == 1) {
							$('#addProcessMainDialog').dialog('close');
							$('#processList').datagrid('reload');
						}
					}
				});
	}

	/**
	 * button onClick => action => 删除加工记录
	 */
	function delProcess() {
		var row = $('#processList').datagrid('getSelected');

		if (row) {
			$.messager.confirm('信息提示', '确定要删除该记录？', function(result) {
				if (result) {
					$.post("process/delProcess", {
						processMainId : row.processMainId
					}, function(data) {
						var msg = eval('(' + data + ')');
						$.messager.alert('信息提示', msg.msg, 'info');
						if (msg.code == 1) {
							$('#processList').datagrid('reload');
						}
					});
				}
			});
		} else {
			$.messager.alert('信息提示', '请选择加工记录！', 'info');
		}
	}

	// iniitalize the component of dialog
	function initDialogComponent_process() {
		//产品选择
		$("#productId_process").combobox({
			url : 'product/list',
			valueField : 'productId',
			textField : 'productName',
			onBeforeLoad : function(param) {
				param.companyId = $('#index_user_companys')
						.combobox('getValue');
				param.productType = 1;
			},
			required : true,
			onSelect : function(record) {
				// console.info(record);
				$('#productStandardDetailId_process').combobox('clear');
				$('#goodsBatch_process').combobox('clear');

				var url = 'product_standard_detail/list?productId='+ record.productId;
				$('#productStandardDetailId_process').combobox('reload', url);
				// $('#productStandardDetailId_process').combobox('setValue', record.productId);

// 				url = 'goods/getgoods?productId='+ record.productId;
// 				$('#goodsBatch_process').combobox('reload', url);
				delprocessTemplateItemsRow();
			}
		});
		//规格选择
		$('#productStandardDetailId_process').combobox({
			valueField : 'productStandardDetailId',
			textField : 'fullStandardName',
			editable : false,
			required : true,
			onSelect : function(data) {
				$.post("processTemplate/findTemplateByDetailId",{standardDetailId:data.productStandardDetailId},function(data){
					if(data&&data.processorId){
						$("#processId_process").combobox('setValue',data.processorId);
					}
				})
				
				$("#templateItemTable").datagrid('reload',
						'processTemplate/findProcessTemplateItemsByProduct?processMainId='
								+ $("#processMainId").val());
			},
			onLoadSuccess : function(data) {
				if (data != null && data.length == 1) {
					$('#productStandardDetailId_process')
							.combobox('select',data[0].productStandardDetailId);
					
				}
			}
		});
		
//  		$("#goodsId_process_item").combobox({
// 			url : 'goods/getgoods',
// 			valueField : '',
// 			textField : 'goodsName',
// 			onBeforeLoad : function(param) {
// 				param.companyId = $('#index_user_companys').combobox(
// 						'getValue');
// 			}
// 		});

		// 加工时间
		$("#processTimeStr").datetimebox({
			editable : false,
			required : true,
		});
		$("#templateItemTable").datagrid({
			iconCls : 'icon-edit',
			singleSelect : true,
			fitColumns:true,
			onClickCell: onClickCell_processTemplate,
            onEndEdit: onEndEdit_processTemplate,
			onBeforeLoad : function(param) {
				param.productId = $("#productId_process").combobox('getValue');
				param.standardDetailId = $("#productStandardDetailId_process").combobox('getValue');
			},
			onLoadSuccess : function(data) {
				rowsCatch = data;
			},
			columns : [ [
					{
						field : 'productId',
						title : '产品',
						width : 140,
						formatter : function(value, row) {
							return row.productName;
						}
					},
					{
						field : 'productStandardDetailId',
						width : 80,
						title : '规格明细',
						formatter : function(value, row) {
							return row.standardDetail.fullStandardName;
						}
					},
					{
						field : 'type',
						width : 80,
						title : '类型',
						formatter : function(value, row) {
							if (value == 0) {
								return '主料';
							}
							return '辅料';
						}
					},
					{
						field : 'num',
						title : '数量',
						width : 50
					},
					{
						field : 'goodsId',
						title : '批次',
						width : 130,
						formatter : function(value, row) {
							return row.goodsBatch;
						},
						editor : {
							type : 'combobox',
							options : {
								valueField : 'goodsId',
								textField : 'goodsBatch',
								method : 'post',
								url : 'goods/findGoodsByStandardDetailIdLimit',
								required : true,
								editable : false,
								onBeforeLoad : function(param) {
									var row = $('#templateItemTable').datagrid('getSelected');
									var rowIndex = $('#templateItemTable').datagrid('getRowIndex',row);
									var itemRow = (rowsCatch.rows)[rowIndex];
									param.productStandardDetailId = itemRow.standardDetail.productStandardDetailId;
									param.goodsId = itemRow.goodsId;
									param.goodsBatch = itemRow.goodsBatch;
									param.limit=4;
								}
							}
						}
					} ] ]
			});
		
		////加工者
		$("#processId_process").combobox({
			url:'processor/list',
			method:'post',
			valueField : 'id',
			textField : 'name',
			editable : false,
			onBeforeLoad : function(param) {
				param.companyId = $("#index_user_companys").combobox("getValue");
			},
		});
		initFlag_process = 1;
	}
	function onClickCell_processTemplate(index, field) {
		if (editIndex_template != index) {
			if (endEditing_processTemplate()) {
				$('#templateItemTable').datagrid('selectRow', index).datagrid('beginEdit', index);
				editIndex_template = index;
				var ed = $('#templateItemTable').datagrid('getEditor', {index : index,	field : field});
				if (ed) {
						($(ed.target).data('textbox') ? $(ed.target).textbox('textbox') : $(ed.target)).focus();
						return;
				}
			} else {
					$('#templateItemTable').datagrid('selectRow', editIndex_template);
			}
		}else{
			$('#templateItemTable').datagrid('selectRow', index).datagrid('beginEdit', index);
		}
	}
	
	function onEndEdit_processTemplate(index, row) {
		var ed = $('#templateItemTable').datagrid('getEditor', {
			index : index,
			field : 'goodsId'
		});
		row.goodsBatch =$(ed.target).combobox('getText') ;
	}
	
	function endEditing_processTemplate() {
		if (editIndex_template == undefined) {
			return true
		}
		if ($('#templateItemTable').datagrid('validateRow',editIndex_template)) {
			$('#templateItemTable').datagrid('endEdit',editIndex_template);
			editIndex_template = undefined;
			return true;
		} else {
			return false;
		}
	}
	function delprocessTemplateItemsRow() {
		var rows = $('#templateItemTable').datagrid('getRows');
		for (var i = rows.length - 1; i > -1; i--) {
			$('#templateItemTable').datagrid('cancelEdit', i).datagrid(
					'deleteRow', i);
		}
	}
</script>
