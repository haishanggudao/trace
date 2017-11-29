<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<link rel="stylesheet" type="text/css"
	href="static/css/datagrid_util.css">
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center',border:false">
		<div id="splitTemplate-toolbar">
			<div class="wu-toolbar-button">
				<shiro:hasPermission name="operation:splitTemplate:add">
					<a href="#" class="easyui-linkbutton" iconCls="icon-add-s1"
						onclick="opensplitTemplate()" plain="true">新增拆分模版</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="operation:splitTemplate:edit">
					<a href="#" class="easyui-linkbutton" iconCls="icon-edit-s1"
						onclick="editsplitTemplate()" plain="true">修改拆分模版</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="operation:splitTemplate:delete">
					<a href="#" class="easyui-linkbutton" iconCls="icon-delete-s1"
						onclick="delsplitTemplate()" plain="true">删除拆分模版</a>
				</shiro:hasPermission>
			</div>
		</div>
		<table id=splitTemplateList title="拆分模版列表"></table>
	</div>
</div>
<div id="splitTemplateEditDialog"
	style="width: 535px; padding: 10px;">
	<form id="splitTemplateForm" method="post">
		<div style="margin-left: 10px;">
			<input type="hidden" name="processTemplateId" id="splitTemplate_processTemplateId" />
			<br/>
			<div class="fitem">
				<label class="fitemlabel" >产品类型:</label> 
					<input class="easyui-combobox" id="splitTemplate_productType" name="productType" style="width: 80px;">
				<label class="fitemlabel" style="margin-left: 5px">拆分产品:</label> 
					<input class="easyui-combobox" id="splitTemplate_productId" name="product.productId" style="width: 140px;">
				<label class="fitemlabel" style="margin-left: 5px">产品规格:</label> 
					<input class="easyui-combobox" id="splitTemplate_productStandardDetailId" name="standardDetail.productStandardDetailId" style="width: 80px;">
			</div>
			<div class="fitem">
				<label class="fitemlabel" >模版名称:</label> 
					<input  class="easyui-textbox" id="splitTemplate_templateName"  
					data-options="prompt:'选中产品后自动会填写产品名为模版名称',required:true,validType:['name',length[1,255]]" 
					name="templateName" style="width: 425px;">
			</div>
			
			
			
			
		</div>
		<div style="margin-left: 10px;">

			<table id="splitTemplateItemTable"
				class="easyui-datagrid" title="产品拆分明细"
				style="width: 480px; height: 265px;"
				data-options="
		                iconCls: 'icon-edit',
		                singleSelect: true,
		                toolbar: '#tb_splitTemplate',
		                onClickCell: onClickCell_splitTemplate,
		                onEndEdit: onEndEdit_splitTemplate,
		                url: 'splitTemplate/findAllItemlist',
		                fitColumns : true,
						singleSelect : true,
						rownumbers : true,
						pagination : true,
						pageSize : 500,
						pageList : [ 500],
						loadMsg : '页面正在加载....',
						height : 'auto',
		             	onBeforeLoad: function(param){ 
							param.templateId  = $('#splitTemplate_processTemplateId').val();
		            	}
		            ">
				<thead>
					<tr>
					<th width="15%"
							data-options="field:'productType',
	                		formatter:function(value,row){
	                            return row.productTypeName;
	                        },
	                        editor:{
	                            type:'combobox',
	                            options:{
	                                valueField:'productType',
	                                textField:'productTypeName',
	                                method:'post',
	                                url:'getProductype',
	                                required:true,
	                                editable:false,
	                                onLoadSuccess : function(record) {
	                                	var row = $('#splitTemplateItemTable').datagrid('getSelected');
		      							var rowIndex = $('#splitTemplateItemTable').datagrid('getRowIndex', row);
		      							var detail = $('#splitTemplateItemTable').datagrid('getEditor', { index: rowIndex,field: 'productId'});
		      							//解决选中后产品选项为空问题
										if(record.length >0 && $(detail.target).combobox('getValue')==''){
											$(this).combobox('select',record[0].productType);
										}
									},
	                                onSelect:function(data){
		                                var row = $('#splitTemplateItemTable').datagrid('getSelected');
		      							var rowIndex = $('#splitTemplateItemTable').datagrid('getRowIndex', row);
		      							var detail = $('#splitTemplateItemTable').datagrid('getEditor', { index: rowIndex,field: 'productId'});
		      						    if(data.productType!=undefined || data.productType!=''){
			      						 	$(detail.target).combobox('clear');
	      									var companyId = $('#index_user_companys').combobox('getValue');
											var url = 'product/list?companyId='+ companyId+'&productType='+data.productType;
											$(detail.target).combobox('reload',url);
	                               		 }
	                            	}
	                        }}">产品类型</th>
						<th width="30%"
							data-options="field:'productId',
	                		formatter:function(value,row){
	                            return row.productName;
	                        },
	                        editor:{
	                            type:'combobox',
	                            options:{
	                                valueField:'productId',
	                                textField:'productName',
	                                method:'post',
	                                required:true,
	                                editable:true,
	                                onSelect:function(data){
		                                var row = $('#splitTemplateItemTable').datagrid('getSelected');
		      							var rowIndex = $('#splitTemplateItemTable').datagrid('getRowIndex', row);
		      							var detail = $('#splitTemplateItemTable').datagrid('getEditor', { index: rowIndex,field: 'productStandardDetailId'});
		      						    if(data.productId!=undefined || data.productId!=''){
		      						 	    $(detail.target).combobox('clear');
		      								$(detail.target).combobox('reload','product_standard_detail/list?productId='+data.productId);
										}
	                            }
	                        }}">产品</th>
						<th width="20%"
							data-options="field:'productStandardDetailId',
		                	formatter:function(value,row){
		                            return row.fullStandardName;
		                    },
		                	editor:{
		                			type:'combobox',
		                            options:{
										method : 'post',
										valueField : 'productStandardDetailId',
										textField : 'fullStandardName',
										required : true,
										editable:false,
										onLoadSuccess: function(data){
												//默认选中第一行
												var detailVal=$(this).combobox('getValue');
												if(!detailVal&&data.length >0){
													$(this).combobox('select',data[0].productStandardDetailId);
												}
												var row = $('#splitTemplateItemTable').datagrid('getSelected'); 
	 		      								var rowIndex = $('#splitTemplateItemTable').datagrid('getRowIndex', row); 
	 		      								var edtype = $('#splitTemplateItemTable').datagrid('getEditor', {index:rowIndex,field:'type'});
	 		      								//自动选择主料
												if($(edtype.target).combobox('getValue')==''){
	 		      									$(edtype.target).combobox('select',0);
	 		      								}
												var ed = $('#splitTemplateItemTable').datagrid('getEditor', {index:rowIndex,field:'num'});
 												$(ed.target).next('span').find('input').focus(); 
										}
		                            }
	                }">规格明细</th>
	                <th width="15%"
							data-options="field:'type',
		                	formatter:function(value,row){
		                            return row.typeName;
		                    },
		                	editor:{
		                			type:'combobox',
		                            options:{
										method : 'post',
										url:'getProcessItemType',
										valueField : 'type',
										textField : 'typeName',
										required : true,
										editable:false,
										onSelect: function(data){
	 												var row = $('#splitTemplateItemTable').datagrid('getSelected'); 
	 		      									var rowIndex = $('#splitTemplateItemTable').datagrid('getRowIndex', row); 
													var ed = $('#splitTemplateItemTable').datagrid('getEditor', {index:rowIndex,field:'num'});
	 												$(ed.target).next('span').find('input').focus(); 
												
										}
		                            }
	                }">类型</th>
						<th width="10%" data-options="field:'num',
	                	editor:{
	                	type:'numberbox',
	                	options:{
	                		required:true
	                }}">数量</th>
					</tr>
				</thead>
			</table>
		</div>
		<div id="tb_splitTemplate" style="height: auto">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-add',plain:true"
				onclick="appendSplitTemplateItem(2)">新增</a> <a
				href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-remove',plain:true"
				onclick="removeSplitTemplateItem()">删除</a>
		</div>
	</form>
	<div style="margin: 10px 10px;">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
			style="width: 480px; height: 32px;"
			onclick="submitSpliteTemplateItemForm()">确认提交</a>
	</div>
</div>
<script>
	var initFlag_splitTemplate = 0;
	var editIndex_splitTemplate = undefined;
	$(function() {
		loadsplitTemplate();
	});

	function submitSpliteTemplateItemForm() {

		var bvalidate = $('#splitTemplateForm').form('enableValidation').form('validate');
		if (!bvalidate) {
			$.messager.alert('信息提示', '亲,您输入的数据不全哦！', 'info');
			return;
		}

		$('#splitTemplateItemTable').datagrid('endEdit',editIndex_splitTemplate);
		editIndex_splitTemplate = undefined;
		var rows = $("#splitTemplateItemTable").datagrid("getRows");
		var lstTemplateItems = [];
		var vcompanyId = $("#index_user_companys").combobox("getValue");
		for (var i = 0; i < rows.length; i++) {
			var templateItem = {};
			templateItem["templateItemId"] = rows[i].templateItemId; 
			templateItem["processTemplateId"] = rows[i].processTemplateId; 
			templateItem["productId"] = rows[i].productId;
			templateItem["productType"] = rows[i].productType;
			var ProductStandardDetail = {};
			ProductStandardDetail["productStandardDetailId"] = rows[i].productStandardDetailId;
			templateItem["standardDetail"] = ProductStandardDetail; 
			templateItem["num"] = rows[i].num; 
			templateItem["type"] = rows[i].type; // 类型：0-主料，1-辅料
			lstTemplateItems[i] = templateItem;
		}
		var vproduct = {
				productId:$("#splitTemplate_productId").combobox('getValue')
		};
		var vstandardDetail = {
				productStandardDetailId:$("#splitTemplate_productStandardDetailId").combobox('getValue')
		};
		
		var param = {
			product:vproduct,
			productType:$("#splitTemplate_productType").combobox('getValue'),
			standardDetail:vstandardDetail,
			processTemplateId :$("#splitTemplate_processTemplateId").val(),
			templateItems:lstTemplateItems,
			type:'1',
			templateName:$("#splitTemplate_templateName").val(),
			companyId:vcompanyId
		};
		$.ajax({
			type : 'post',
			url : 'splitTemplate/addSplitTemplate?token='+$('#index_token').val(),
			dataType : "json",
			contentType : "application/json",
			data : JSON.stringify(param),
			success : function(data) {
				//创建token
				createToken();
				$.messager.alert('信息提示', data.msg, 'info');
				if (data.code == 1) {
					$('#splitTemplateEditDialog').window('close');
					$('#splitTemplateList').datagrid('reload');
				}
			}
		});
	
	}

	function opensplitTemplate() {
		delSplitTemplateItemsRow();
		if (initFlag_splitTemplate == 0) {
			initDialogSplitTemplate();
			initFlag_splitTemplate = 1;
		}
		var time = new Date().format("yyyy-MM-dd");
		$('#splitTemplateForm').form('clear').form('disableValidation');
		
		$('#splitTemplateEditDialog').dialog({
			closed : false,
			modal : true,
			title : "新增拆分模版",
			iconCls : 'icon-document'
		});
		$('#splitTemplate_productType').combobox({
			onLoadSuccess : function(record) {
				if(record.length > 1){
					$(this).combobox('select',record[1].productType);
				}
			}
		});
		
	}
	function initDialogSplitTemplate() {
		
		
		
		$("#splitTemplate_productType").combobox({
			url : 'getProductype',
			valueField : 'productType',
			textField : 'productTypeName',
			editable : false,
			onSelect : function(record) {
				$('#splitTemplate_productId').combobox('clear');
				var companyId = $('#index_user_companys').combobox('getValue');
				var url = 'product/list?companyId='+ companyId+"&productType="+record.productType;
				$('#splitTemplate_productId').combobox('reload', url);
			}
		});
		
		$("#splitTemplate_productId").combobox({
			valueField : 'productId',
			textField : 'productName',
			editable : true,
			onSelect : function(record) {

				$('#splitTemplate_templateName').textbox('setValue',record.productName);
				
				$('#splitTemplate_productStandardDetailId').combobox('clear');
				var url = 'product_standard_detail/list?productId='+ record.productId;
				$('#splitTemplate_productStandardDetailId').combobox('reload', url);
			}
		});
		$("#splitTemplate_productStandardDetailId").combobox({
			valueField : 'productStandardDetailId',
			textField : 'fullStandardName',
			editable : false,
			onLoadSuccess : function(record) {
				if (record != null && record.length == 1) {
					$('#splitTemplate_productStandardDetailId').combobox('setValue',record[0].productStandardDetailId);
				}
			}
		
	
		
		});
	}
	function delsplitTemplate() {
		var row = $('#splitTemplateList').datagrid('getSelected');
		if (row) {
			$.messager.confirm('删除拆分模版', '确认删除?', function(r) {
				if (r) {
					$.post("splitTemplate/delSplitTemplate", {
						processTemplateId : row.processTemplateId
					}, function(data) {
						$.messager.alert('信息提示', data.msg, 'info');
						if (data.code == 1) {
							$('#splitTemplateList').datagrid('reload');
						}
					})
				}
			});
		} else {
			$.messager.alert('信息提示', '请选择一行记录！', 'info');
		}
	}
	function delSplitTemplateItemsRow() {
		var rows = $('#splitTemplateItemTable').datagrid('getRows');
		for (var i = rows.length - 1; i > -1; i--) {
			$('#splitTemplateItemTable').datagrid('cancelEdit', i).datagrid('deleteRow', i);
		}
	}

	function editsplitTemplate() {
		var row = $('#splitTemplateList').datagrid('getSelected');
		if (row) {
			$('#splitTemplateForm').form('clear').form('disableValidation');
			editIndex_splitTemplate = undefined;
			if (initFlag_splitTemplate == 0) {
				initDialogSplitTemplate();
				initFlag_splitTemplate = 1;
			}
			//解决添加数据后修改会出现分页为空 默认设置分页为5页 
			$('#splitTemplateItemTable').datagrid({
				pageSize : 500
			});
			$('#splitTemplateForm').form('load', {
				processTemplateId : row.processTemplateId,
				'product.productId' : row.product.productId,
				productType:row.productType,
				'standardDetail.productStandardDetailId' : row.standardDetail.productStandardDetailId,
				templateName : row.templateName
			});
			
			
			var companyId = $('#index_user_companys').combobox('getValue');
			var url = 'product/list?companyId='+ companyId+"&productType="+row.productType;
			$('#splitTemplate_productId').combobox('reload', url);
			
			var url = 'product_standard_detail/list?productId='+ row.product.productId;
			$('#splitTemplate_productStandardDetailId').combobox('reload', url);
			
			$('#splitTemplateItemTable').datagrid('reload'); // reload the current page data 
			$('#splitTemplateEditDialog').dialog({
				closed : false,
				modal : true,
				title : "修改拆分模版",
				iconCls : 'icon-document'
			});
		} else {
			$.messager.alert('信息提示', '请选择一行记录！', 'info');
		}
	}
	/**
	 * 载入采购单信息
	 */
	function loadsplitTemplate() {
	  	<shiro:hasPermission name="operation:splitTemplate:edit">
			 $('#splitTemplateList').datagrid({
				onDblClickCell: function(index,field,value){
					editsplitTemplate();
				}
			 });
		</shiro:hasPermission>
		$('#splitTemplateList').datagrid({
			method : 'post',
			url : 'splitTemplate/findAllList',
			view : detailview,
			rownumbers : true,
			pageSize : 10,
			pagePosition : 'bottom',
			pageList : [ 10, 20, 30 ],
			pagination : true,
			multiSort : true,
			fitColumns : true,
			fit : true,
			iconCls : 'icon-ok',
			singleSelect : true,
			onBeforeLoad : function(param) {
				var companyId = $("#index_user_companys").combobox('getValue');
				param.companyId = companyId;
				param.type=1;
			},
			detailFormatter : function(index, row) {
				return '<div style="padding:2px"><table class="ddv"></table></div>';
			},
			toolbar : '#splitTemplate-toolbar',
			columns : [ [ 
				{
					field : 'templateName',
					title : '模版名称',
					width : 100,
					sortable : true
				},
				{
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
				},
				{	
					field : 'createTime',
					title : '创建时间',
					width : 50,
					formatter : function(value, row) {
						if(row.createTime!=undefined){
							return convertTimeStamp(row.createTime);
						}
					}
				},
				{
					field : 'createBy',
					title : '创建人',
					width : 50,
					formatter : function(value, row) {
						return row.createBy;
					}
				},
				{
					field : 'updateTime',
					title : '修改时间',
					width : 50,
					formatter : function(value, row) {
						if(row.updateTime!=undefined){
							return convertTimeStamp(row.updateTime);
						}
					}
				},
				{
					field : 'updateBy',
					title : '修改人',
					width : 50,
					formatter : function(value, row) {
						return row.updateBy;
					}
				}
			] ],
			onExpandRow : function(index, row) {
				var ddv = $(this).datagrid('getRowDetail',index).find('table.ddv');
					ddv.datagrid({
							url : 'splitTemplate/findAllItemlist',
							fitColumns : true,
							singleSelect : true,
							rownumbers : true,
							pagination : true,
							pageSize : 10,
							pageList : [ 10, 20, 30 ],
							loadMsg : '页面正在加载....',
							height : 'auto',
							onBeforeLoad : function(param) {
								param.templateId = row.processTemplateId;
							},
							columns : [ [
									{
										field : 'productName',
										title : '产品',
										width : 20,
										formatter : function(value, row) {
											return row.product.productName;
										}
									},
									{
										field : 'productStandardName',
										title : '产品规格',
										width : 15,
										formatter : function(value, row) {
											return row.standardDetail.fullStandardName;
										}
									},
									{
										field : 'num',
										title : '数量',
										width : 5,
										formatter : function(value, row) {
											return row.num;
										}
									},{
										field : 'type',
										title : '类型',
										width : 5,
										formatter : function(value, row) {
											return row.typeName;
										}
									},
									{	
										field : 'createTime',
										title : '创建时间',
										width : 15,
										formatter : function(value, row) {
											if(row.createTime!=undefined){
												return convertTimeStamp(row.createTime);
											}
										}
									},
									{
										field : 'createBy',
										title : '创建人',
										width : 10,
										formatter : function(value, row) {
											return row.createBy;
										}
									},
									{
										field : 'updateTime',
										title : '修改时间',
										width : 15,
										formatter : function(value, row) {
											if(row.updateTime!=undefined){
												return convertTimeStamp(row.updateTime);
											}
										}
									},
									{
										field : 'updateBy',
										title : '修改人',
										width : 10,
										formatter : function(value, row) {
											return row.updateBy;
										}
									}

							] ],
							onResize : function() {
								$('#splitTemplateList').datagrid('fixDetailRowHeight',index);
							},
							onLoadSuccess : function() {
								setTimeout(
										function() {
											$('#splitTemplateList').datagrid('fixDetailRowHeight',index);
										}, 0);
							}
						});
				$('#splitTemplateList').datagrid(
						'fixDetailRowHeight', index);
			}
		});
	}

	function endEditing_splitTemplate() {
		if (editIndex_splitTemplate == undefined) {
			return true
		}
		if ($('#splitTemplateItemTable').datagrid('validateRow',
				editIndex_splitTemplate)) {
			$('#splitTemplateItemTable').datagrid('endEdit',
					editIndex_splitTemplate);
			editIndex_splitTemplate = undefined;
			return true;
		} else {
			return false;
		}
	}
	function onClickCell_splitTemplate(index, field) {
		if (editIndex_splitTemplate != index) {
			if (endEditing_splitTemplate()) {
				$('#splitTemplateItemTable').datagrid('selectRow', index).datagrid('beginEdit', index);
				
				var productId = $('#splitTemplateItemTable').datagrid('getEditor', {index : index,field : 'productId'});
				var productType = $('#splitTemplateItemTable').datagrid('getEditor', {index : index,field : 'productType'});
				var type = $('#splitTemplateItemTable').datagrid('getEditor', {index : index,field : 'type'});
				
				
				var productStandardDetailId = $('#splitTemplateItemTable').datagrid('getEditor', {index : index,field : 'productStandardDetailId'});
				var ed = $('#splitTemplateItemTable').datagrid('getEditor', {index : index,	field : field});
				if ( productType && productId  && productStandardDetailId && type) {
					$(productType.target).combobox('reload');
					
					var companyId = $('#index_user_companys').combobox('getValue');
					var vproductType = $(productType.target).combobox('getValue');
					var url = 'product/list?companyId='+ companyId+"&productType="+vproductType;
					$(productId.target).combobox('reload',url);
					
					var vproductId = $(productId.target).combobox('getValue');
					$(productStandardDetailId.target).combobox('reload','product_standard_detail/list?productId='+ vproductId);
					
					$(type.target).combobox('reload');
					
				}
				if (ed) {
						($(ed.target).data('textbox') ? $(ed.target).textbox('textbox') : $(ed.target)).focus();
						editIndex_splitTemplate = index;
						return;
				}
				editIndex_splitTemplate = index;
			} else {
				setTimeout(function() {
					$('#splitTemplateItemTable').datagrid(
							'selectRow', editIndex_splitTemplate);
				}, 0);
			}
		}
	}
	function onEndEdit_splitTemplate(index, row) {
		var ed = $(this).datagrid('getEditor', {
			index : index,
			field : 'productId'
		});
		row.productName = $(ed.target).combobox('getText');
		
		var ed1 = $(this).datagrid('getEditor', {
			index : index,
			field : 'productType'
		});
		row.productTypeName = $(ed1.target).combobox('getText');
		
		var ed2 = $(this).datagrid('getEditor', {
			index : index,
			field : 'productStandardDetailId'
		});
		row.fullStandardName = $(ed2.target).combobox('getText');
		
		var ed3 = $(this).datagrid('getEditor', {
			index : index,
			field : 'type'
		});
		row.typeName = $(ed3.target).combobox('getText');

	}
	function appendSplitTemplateItem(addType) {
		var bend = endEditing_splitTemplate();
		if (bend) {
			$('#splitTemplateItemTable').datagrid('appendRow', {});
			editIndex_splitTemplate = $('#splitTemplateItemTable').datagrid('getRows').length - 1;
			$('#splitTemplateItemTable').datagrid(
					'selectRow', editIndex_splitTemplate).datagrid(
					'beginEdit', editIndex_splitTemplate);
		}
	}

	function removeSplitTemplateItem() {
		var row = $('#splitTemplateItemTable').datagrid('getSelected');
		if (row == undefined || row.templateItemId == undefined) {
			if (editIndex_splitTemplate == undefined) {
				return
			}
			$('#splitTemplateItemTable').datagrid('cancelEdit',
					editIndex_splitTemplate).datagrid('deleteRow',
					editIndex_splitTemplate);
			editIndex_splitTemplate = undefined;
			return;
		}
		
		if (row) {
			$.messager.confirm('信息提示', '确定要删除这条信息吗？', function(result) {
				if (result) {
					
					$.post("splitTemplate/delTemplateItem", {
						templateId : row.templateItemId,
					}, function(data) {
						$.messager.alert('信息提示', data.msg, 'info');
						if (data.code == 1) {
							if (editIndex_splitTemplate == undefined) {
								return
							}
							$('#splitTemplateItemTable').datagrid('cancelEdit',editIndex_splitTemplate)
									.datagrid('deleteRow',editIndex_splitTemplate);
								editIndex_splitTemplate = undefined;
							$('#splitTemplateList').datagrid('reload');

						}
					});

				}
			});
		} else {
			$.messager.alert('信息提示', '亲,请选择一行信息！', 'info');
		}

	}
</script>