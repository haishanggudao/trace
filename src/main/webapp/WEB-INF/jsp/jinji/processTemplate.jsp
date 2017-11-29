<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<div id="processTemplateBar" style="padding: 5px 0;">
	<shiro:hasPermission name="operation:processTemplate:add">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-add-s1'"
			onclick="openProcessTemplateWindow()">新增模版</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="operation:processTemplate:edit">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit-s1'"
			onclick="openEditProcesssTemplateWindow()">编辑模版</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="operation:processTemplate:delete">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-delete-s1'" onclick="delProcessTemplate()">删除模版</a>
	</shiro:hasPermission>
</div>
<div id="flagTemplate" style="display: none;">1</div>
	<table id="processTemplateList" title="加工模板列表"
		style="width: auto; height: 350px">
	</table>
<div id="addProcessTemplateDialog" style="width: 800px; padding: 10px;">
	<form id="processTemplateForm" method="post">
		<input type="hidden" name="processTemplateId" />
		<table>
			<tr>
				<td width="70" align="right">产品：</td>
				<td><input class="easyui-combobox"
					id="productId_process_template" name="product.productId"
					style="width: 266px;"></td>
			</tr>
			<tr>
				<td width="70" align="right">规格：</td>
				<td><input id="productStandardDetailId_template"
					name="standardDetail.productStandardDetailId" style="width: 266px;">
				</td>
			</tr>
			<tr>
				<td width="70" align="right">加工者：</td>
				<td><input id="processTemplate_processor"
					name="processor.id" style="width: 266px;">
				</td>
			</tr>
			<tr>
				<td width="70" align="right">加工原料：</td>
				<td><span id="addmaterials_span"><a href="#"
						class="easyui-linkbutton" data-options="iconCls:'icon-add'"
						onclick="addTemplate('templateTable',null,null,0,null)"> 增加原料
					</a></span></td>
			</tr>
			<tr>
				<td align="right">&nbsp;</td>
				<td>
					<table id="templateTable">
						<tr>
							<td><select id="process_template_items"
								name="templateItems[0].productId" style="width: 130px"
								data-options="required:true,editable:true">
							</select></td>
							<td><select id="process_template_standard"
								name="templateItems[0].standardDetail.productStandardDetailId"
								style="width: 80px" data-options="required:true,editable:true">
							</select></td>
							<td><select id="type_template_item"
								name="templateItems[0].type" style="width: 60px;">
									<option value="0">主料</option>
									<option value="1">辅料</option>
							</select></td>
							<td><label>数量：</label><input id="num_template_item"
								name="templateItems[0].num" class="easyui-numberbox"
								data-options="required:true" /></td>
							<td></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>
</div>

<script type="text/javascript">
	var initFlag_template = 0;

	var mainCount_template = 0;

	$(function() {
		loadProcessTemplateData();
	});

	/**
	 * 载入加工记录信息
	 */
	function loadProcessTemplateData() {
		<shiro:hasPermission name="operation:processTemplate:edit">
		 $('#processTemplateList').datagrid({
				onDblClickCell: function(index,field,value){
					openEditProcesssTemplateWindow()
				}
		 });
		</shiro:hasPermission>
		$('#processTemplateList').datagrid(
				{
					url : 'processTemplate/findAllList',
					rownumbers : true,
					pageSize : 10,
					pageList : [ 10, 20, 50 ],
					pagination : true,
					singleSelect : true,
					multiSort : true,
					fitColumns : true,
					fit : true,
					toolbar : '#processTemplateBar',
					onBeforeLoad : function(param) {
						var myUserCompanyId = $('#index_user_companys')
								.combobox('getValue');
						param.type=0;
						param.companyId = myUserCompanyId;
					},
					columns : [ [ {
						field : 'productName',
						title : '产品名称',
						width : 100,
						sortable : true,
						formatter : function(val, row, index) {
							return row.product.productName;
						}
					}, {
						field : 'standardDetail',
						title : '产品规格',
						width : 100,
						sortable : true,
						formatter : function(val, row, index) {
							return row.standardDetail.fullStandardName;
						}
					} ] ]
				});
	}

	/**
	 * button => 打开新增加工窗口
	 */
	function openProcessTemplateWindow() {
		$('#flagTemplate').html('1');
		mainCount_template = 0;

		if (initFlag_template == 0) {
			initDialogComponent_template();
		}

		$(".clear").remove();

		$('#processTemplateForm').form('clear').form('disableValidation');

		$("#type_template_item").combobox("setValue", "0");

		$('#addProcessTemplateDialog').dialog({
			closed : false,
			modal : true,
			title : "新增加工模板",
			buttons : [ {
				text : '确定',
				iconCls : 'icon-ok',
				handler : submitProcessTemplate
			}, {
				text : '取消',
				iconCls : 'icon-cancel',
				handler : function() {
					$('#addProcessTemplateDialog').dialog('close');
				}
			} ]
		});
	}

	/**
	 * action => 保存加工模板记录
	 */
	function submitProcessTemplate() {
		$('#processTemplateForm').form(
				'submit',
				{
					url : 'processTemplate/addProcessTemplate',
					onSubmit : function(param) {
						param.companyId = $('#index_user_companys').combobox(
								'getValue');
						param.token=$('#index_token').val();
						return $(this).form('enableValidation')
								.form('validate');
					},
					success : function(data) {
						createToken();
						var msg=eval("("+data+")");
						$.messager.alert('信息提示', msg.msg, 'info');
						if (msg.code == 1) {
							$('#addProcessTemplateDialog').dialog('close');
							$('#processTemplateList').datagrid('reload');
						}
					}
				});
	}

	/**
	 * 显示原料（主料或者辅料）
	 */
	function appendFoodIngredient(processTemplateId) {

		$.post("processTemplate/findProcessTemplateItems", {
			templateId : processTemplateId
		}, function(data) {
			var msg = eval('(' + data + ')');
			for (var i = 0; i < msg.length; i++) {
				if (i == 0) {
					$("#process_template_items").combobox("setValue",
							msg[i].productId);
					$("#process_template_standard").combobox(
							"reload",
							'product_standard_detail/list?productId='
									+ msg[i].productId);
					$("#process_template_standard").combobox("setValue",
							msg[i].standardDetail.productStandardDetailId);
					$("#type_template_item").combobox("setValue", msg[i].type);
					$("#num_template_item").numberbox({
						value : msg[i].num
					});
				} else {
					addTemplate('templateTable', msg[i].productId,
							msg[i].standardDetail.productStandardDetailId,
							msg[i].type, msg[i].num);
				}
			}
		});
	}

	/**
	 * 增加原料（主料或者辅料）
	 */
	function addTemplate(obj, nameVal, standardVal, typeVal, numVal) {
		var index = 0;
		index = ++mainCount_template;
		var tr = $("<tr class='clear'></tr>");
		var td1 = $("<td></td>");
		var td2 = $("<td></td>");
		var td3 = $("<td></td>");
		var td4 = $("<td></td>");

		var select = $("<select></select>");
		select.css("width", "130px");
		select.attr("name", "templateItems[" + index + "].productId");
		select.attr("id", "process_template_items_" + index);

		var selectStandardDetail = $("<select></select>");
		selectStandardDetail.css("width", "80px");
		selectStandardDetail.attr("name", "templateItems[" + index
				+ "].standardDetail.productStandardDetailId");
		selectStandardDetail.attr("id", "process_template_standard_" + index);

		var selectType = $("<select></select>");
		selectType.css("width", "60px");
		selectType.attr("name", "templateItems[" + index + "].type");
		selectType.attr("id", "type_template_item_" + index);

		var label = $("<lalbel></label>");
		label.text("数量：");
		var input = $("<input>");
		input.attr("name", "templateItems[" + index + "].num");

		var delBtn = $("<a></a>");

		td1.append(select);
		td2.append(selectStandardDetail);
		td3.append(selectType);

		td4.append(label);
		td4.append(input);
		td4.append(delBtn);
		tr.append(td1);
		tr.append(td2);
		tr.append(td3);
		tr.append(td4);
		$("#" + obj).append(tr);
		var selectTypeData = [ {
			'id' : '0',
			'text' : '主料'
		}, {
			'id' : '1',
			'text' : '辅料'
		} ];
		selectStandardDetail.combobox({
			valueField : 'productStandardDetailId',
			textField : 'fullStandardName'
		});
		select.combobox({
			url : 'product/list?productType=2',
			valueField : 'productId',
			textField : 'productName',
			editable : false,
			required : true,
			readonly : false,
			onBeforeLoad : function(param) {
				param.companyId = $('#index_user_companys')
						.combobox('getValue');
			},
			onSelect : function(data) {
				selectStandardDetail.combobox("reload",
						'product_standard_detail/list?productId='
								+ data.productId);
			}
		});
		selectType.combobox({
			data : selectTypeData,
			textField : 'text',
			valueField : 'id',
			readonly : false
		});
		input.numberbox({
			required : true,
			value : numVal,
			readonly : false
		});
		if (nameVal) {
			select.combobox("setValue", nameVal);
			selectStandardDetail.combobox("reload",
					'product_standard_detail/list?productId=' + nameVal);
		}
		if (standardVal) {
			selectStandardDetail.combobox("setValue", standardVal);
		}
		if (typeVal != null) {
			selectType.combobox("setValue", typeVal);
		}
		delBtn.linkbutton({
			iconCls : 'icon-cancel'
		});
		delBtn.click(function() {
			tr.remove();
		});

		$("#processTemplateForm").form('disableValidation');
	}

	/**
	 * button => 打开修改加工窗口
	 */
	function openEditProcesssTemplateWindow() {
		var row = $('#processTemplateList').datagrid('getSelected');

		if (row) {
			if (initFlag_template == 0) {
				initDialogComponent_template();
			}
			mainCount_template = 0;
			$(".clear").remove();
			var processorId=row.processor==null?null:row.processor.id;
			$('#processTemplateForm')
					.form(
							'load',
							{
								processTemplateId : row.processTemplateId,
								'product.productId' : row.product.productId,
								'processor.id':processorId,
								'standardDetail.productStandardDetailId' : row.standardDetail.productStandardDetailId
							});
			$('#productStandardDetailId_template').combobox(
					'reload',
					'product_standard_detail/list?productId='
							+ row.product.productId);
			appendFoodIngredient(row.processTemplateId);

			$('#addProcessTemplateDialog').dialog({
				closed : false,
				modal : true,
				title : "修改加工模板",
				buttons : [ {
					text : '确定',
					iconCls : 'icon-ok',
					handler : submitProcessTemplate
				}, {
					text : '取消',
					iconCls : 'icon-cancel',
					handler : function() {
						$('#addProcessTemplateDialog').dialog('close');
					}
				} ]
			});
		} else {
			$.messager.alert('信息提示', '请选择加工记录！', 'info');
		}
	}
	/**
	 * button onClick => action => 删除加工记录
	 */
	function delProcessTemplate() {
		var row = $('#processTemplateList').datagrid('getSelected');
		if (row) {
			$.messager.confirm('信息提示', '确定要删除该记录？', function(result) {
				if (result) {
					$.post("processTemplate/delProcessTemplate", {
						processTemplateId : row.processTemplateId
					}, function(data) {
						$.messager.alert('信息提示', data.msg, 'info');
						if (data.code == 1) {
							$('#processTemplateList').datagrid('reload');
						}
					});
				}
			});
		} else {
			$.messager.alert('信息提示', '请选择加工模板！', 'info');
		}
	}

	// iniitalize the component of dialog
	function initDialogComponent_template() {
		$("#productId_process_template").combobox(
				{
					url : 'product/list',
					valueField : 'productId',
					textField : 'productName',
					onBeforeLoad : function(param) {
						param.companyId = $('#index_user_companys').combobox(
								'getValue');
						param.productType = 1;
					},
					onSelect : function(record) {
						// 								$('#productStandardDetailId_template').combobox('clear');
						var url = 'product_standard_detail/list?productId='
								+ record.productId;
						$('#productStandardDetailId_template').combobox(
								'reload', url);
					}
				});
		$("#productStandardDetailId_template").combobox(
				{
					valueField : 'productStandardDetailId',
					textField : 'fullStandardName',
					editable : false,
					onLoadSuccess : function(record) {
						if (record != null && record.length == 1) {
							$('#productStandardDetailId_template').combobox(
									'setValue',
									record[0].productStandardDetailId);
						}
					}
				});
		$("#process_template_items").combobox(
				{
					url : 'product/list?productType=2',
					valueField : 'productId',
					textField : 'productName',
					onBeforeLoad : function(param) {
						param.companyId = $('#index_user_companys').combobox(
								'getValue');
					},
					onSelect : function(data) {
						$("#process_template_standard").combobox(
								"reload",
								'product_standard_detail/list?productId='
										+ data.productId);
					}
				});

		$("#process_template_standard").combobox({
			valueField : 'productStandardDetailId',
			textField : 'fullStandardName'
		});
		$("#type_template_item").combobox();
		$("#processTemplate_processor").combobox({
			url:'processor/list',
			method:'post',
			valueField : 'id',
			textField : 'name',
			editable : false,
			onBeforeLoad : function(param) {
				param.companyId = $("#index_user_companys").combobox("getValue");
			}
		});
		// 		initFlag_template = 1;
	}
</script>
