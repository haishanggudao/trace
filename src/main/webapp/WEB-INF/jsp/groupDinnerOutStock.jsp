<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<link rel="stylesheet" type="text/css"
	href="static/css/datagrid_util.css">
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center',border:false">
		<div id="groupDinnerOutStock-toolbar">
			<div class="wu-toolbar-button">
				<shiro:hasPermission name="trans:groupDinnerOutStock:add">
					<a href="#" class="easyui-linkbutton" iconCls="icon-add-s1"
						onclick="opengroupDinnerOutStock()" plain="true">新增团餐出库管理</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="trans:groupDinnerOutStock:edit">
					<a href="#" class="easyui-linkbutton" iconCls="icon-edit-s1"
						onclick="editgroupDinnerOutStock()" plain="true">修改团餐出库管理</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="trans:groupDinnerOutStock:qrCode">
					<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-codeqr'"
						onclick="groupDinnerOutStockQRcode()" plain="true">生成二维码</a> 
				</shiro:hasPermission>		
				<shiro:hasPermission name="trans:groupDinnerOutStock:delete">
					<a href="#" class="easyui-linkbutton" iconCls="icon-delete-s1"
						onclick="delgroupDinnerOutStock()" plain="true">删除团餐出库管理</a>
				</shiro:hasPermission>
			</div>
		</div>
		<table id=groupDinnerOutStockList title="团餐出库管理列表"></table>
	</div>
</div>
<div id="groupDinnerOutStockEditDialog"
	style="width: 685px; padding: 10px;">
	<form id="groupDinnerOutStockForm" method="post">
		<div style="margin-left: 10px;">
			<input type="hidden" id="outstockMainId_gdos" name="outstockMainId" />
			<input type="hidden" id="saleOrderId_gdos" name="saleOrderId" /> <input
				type="hidden" name="consignor" id="consignor_gdos" /> <input
				type="hidden" name="registrant" id="registrant_gdos" /> <br />
			<div class="fitem">
				<label class="fitemlabel">销售单编号：</label> 
					<input class="easyui-textbox" id="outstockNum_gdos" name="outstockNum" style="width: 210px" data-options="required:true,editable:false" />
				<label class="fitemlabel" style="margin-left: 10px">物流企业：</label> 
					<input id="logisticsId_gdos" name="logisticsId" style="width: 262px;">
			</div>
			<div class="fitem">
				<label class="fitemlabel" style="margin-left: 22px">追溯码：</label> 
					<input class="easyui-textbox" id="traceCode_gdos" name="saleOrder.traceCode" style="width: 210px" data-options="required:true,editable:false" /> 
				<label class="fitemlabel" style="margin-left: 13px">下单时间：</label> 
			        <input type="text" id="orderTime_gdos" name="saleOrder.orderTime" style="width: 262px;">
			</div>
		</div>
		<div style="margin-left: 10px;">
			<table id="groupDinnerOutStockItemTable" class="easyui-datagrid"
				title="产品团餐出库明细" style="width: 630px; height: 265px;"
				data-options="
						iconCls: 'icon-edit',
			            singleSelect: true,
			            toolbar: '#tb_groupDinnerOutStock',
			            onClickCell: onClickCell_groupDinnerOutStock,
			            onEndEdit: onEndEdit_groupDinnerOutStock,
			            fitColumns : true,
						singleSelect : true,
						rownumbers : true,
						pagination : true,
						pageSize : 5,
						pageList : [ 5, 10, 20 ],
						loadMsg : '页面正在加载....',
						height : 'auto'
		            ">
				<thead>
					<tr>
						<th width="10%"
							data-options="field:'productType',
	                        hidden : true,
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
	                                	var row = $('#groupDinnerOutStockItemTable').datagrid('getSelected');
		      							var rowIndex = $('#groupDinnerOutStockItemTable').datagrid('getRowIndex', row);
		      							var detail = $('#groupDinnerOutStockItemTable').datagrid('getEditor', { index: rowIndex,field: 'productId'});
		      							//解决选中后产品选项为空问题
										if(record.length >0 && $(detail.target).combobox('getValue')==''){
											$(this).combobox('select',record[0].productType);
										}
										
									},
	                                onSelect:function(data){
		                                var row = $('#groupDinnerOutStockItemTable').datagrid('getSelected');
		      							var rowIndex = $('#groupDinnerOutStockItemTable').datagrid('getRowIndex', row);
		      							var detail = $('#groupDinnerOutStockItemTable').datagrid('getEditor', { index: rowIndex,field: 'productId'});
		      						    if(data.productType!=undefined || data.productType!=''){
			      						 	$(detail.target).combobox('clear');
	      									var companyId = $('#index_user_companys').combobox('getValue');
											var url = 'product/findProductListByGoodsVariable?companyId='+ companyId+'&varGroup=deliverType&varName=1';
											$(detail.target).combobox('reload',url);
	                               		 }
	                            	}
	                        }}">分类</th>
						<th width="25%"
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
		                                var row = $('#groupDinnerOutStockItemTable').datagrid('getSelected');
		      							var rowIndex = $('#groupDinnerOutStockItemTable').datagrid('getRowIndex', row);
		      							var detail = $('#groupDinnerOutStockItemTable').datagrid('getEditor', { index: rowIndex,field: 'productStandardDetailId'});
		      						    if(data.productId!=undefined || data.productId!=''){
		      						 	    $(detail.target).combobox('clear');
		      								$(detail.target).combobox('reload','product_standard_detail/list?productId='+data.productId);
		      								
		      								
										}
	                            }
	                        }}">产品</th>
						<th width="16%"
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
												if(data.length >0 && $(this).combobox('getValue')==''){
													$(this).combobox('select',data[0].productStandardDetailId);
												}
										},
										onSelect:function(data){
				                                var row = $('#groupDinnerOutStockItemTable').datagrid('getSelected');
				      							var rowIndex = $('#groupDinnerOutStockItemTable').datagrid('getRowIndex', row);
				      							var rowproductId = $('#groupDinnerOutStockItemTable').datagrid('getEditor', { index: rowIndex,field: 'productId'});
				      							var detail = $('#groupDinnerOutStockItemTable').datagrid('getEditor', { index: rowIndex,field: 'goodsBatch'});
				      						    if(data.product!= undefined && data.product.productId!= undefined && data.product.productId!= '' ){
				      						 	    $(detail.target).combobox('clear');
													$(detail.target).combobox('reload', 'goods/getgoods?productId=' +data.product.productId
													+'&productStandardDetailId='+data.productStandardDetailId);
												}
			                            }
										
		                            }
	                }">规格明细</th>
						<th width="22%"
							data-options="field:'goodsBatch',
	                	formatter:function(value,row){
	                            return row.goodsBatch;
	                    },
	                	editor:{
	                		type:'combobox',
                            options:{
								method : 'post',
								valueField : 'goodsBatch',
								textField : 'goodsBatch',
								required : true,
								editable:false,
								onLoadSuccess: function(data){
								
										var row = $('#groupDinnerOutStockItemTable').datagrid('getSelected');
		      							var rowIndex = $('#groupDinnerOutStockItemTable').datagrid('getRowIndex', row);
		      							var detail = $('#groupDinnerOutStockItemTable').datagrid('getEditor', { index: rowIndex,field: 'goodsBatch'});
		      							//解决选中后产品选项为空问题
										if(data.length >0 && $(detail.target).combobox('getValue')==''){
											$(detail.target).combobox('select',data[0].goodsBatch);
										}
								},
								onSelect: function(data){
								console.info(2222);
	 												var row = $('#groupDinnerOutStockItemTable').datagrid('getSelected'); 
	 		      									var rowIndex = $('#groupDinnerOutStockItemTable').datagrid('getRowIndex', row); 
													row.goodsId = data.goodsId;
													row.createTime = convertTimeStamp(data.createTime);
													
													var tdNum= $('#groupDinnerOutStockEditDialog .datagrid-body td[field=\'outstockNum\']')[rowIndex];
										            var divNum = $(tdNum).find('div')[0];
										            $(divNum).text(data.num);
										            row.outstockNum= data.num;
													
													var td= $('#groupDinnerOutStockEditDialog .datagrid-body td[field=\'createTime\']')[rowIndex];
										            var div = $(td).find('div')[0];
										            $(div).text(row.createTime);
										            
								}
                            }
	                }">批次</th>
						<th width="8%"
							data-options="field:'outstockNum'">数量</th>
						<th width="23%" data-options="
							field:'createTime',
							formatter:function(value,row){
								if(row.createTime){
									var rTime = convertTimeStamp(row.createTime);
									if(rTime.indexOf('NaN')==-1){
										return rTime;
									}else{
										return row.createTime;
									}
									
								}
								
								
	                    },">创建日期</th>
					</tr>
				</thead>
			</table>
		</div>
		<div id="tb_groupDinnerOutStock" style="height: auto">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-add',plain:true"
				onclick="appendgroupDinnerOutStockItem(2)">新增</a> <a
				href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-remove',plain:true"
				onclick="removegroupDinnerOutStockItem()">删除</a>


		</div>
	</form>
	<div style="margin: 10px 10px;">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
			style="width: 630px; height: 32px;"
			onclick="submitgroupDinnerOutStockItemForm()">确认提交</a>
	</div>
</div>
<script>
	var initFlag_groupDinnerOutStock = 0;
	var editIndex_groupDinnerOutStock = undefined;
	$(function() {
		loadgroupDinnerOutStock();
	});

	function submitgroupDinnerOutStockItemForm() {

		var bvalidate = $('#groupDinnerOutStockForm').form('enableValidation').form('validate');
		if (!bvalidate) {
			$.messager.alert('信息提示', '亲,您输入的数据不全哦！', 'info');
			return;
		}

		$('#groupDinnerOutStockItemTable').datagrid('endEdit',editIndex_groupDinnerOutStock);
		editIndex_groupDinnerOutStock = undefined;
		var rows = $("#groupDinnerOutStockItemTable").datagrid("getRows");
		var vcompanyId = $("#index_user_companys").combobox("getValue");
		
		$("#consignor_gdos").val($("#index_userName").val());
		$("#registrant_gdos").val($("#index_userName").val());
		
		
		$('#groupDinnerOutStockForm').form('submit',{
			url : 'groupDinnerOutStock/add',
			onSubmit : function(param) {
				param.outstockDetail = JSON.stringify(rows);
				param.companyId = vcompanyId;
				return true;
			},
			success : function(data) {
				var msg = eval('(' + data + ')');
				$.messager.alert('信息提示', msg.msg, 'info');
				if (msg.code == 1) {
					$('#groupDinnerOutStockEditDialog').window('close');
					$('#groupDinnerOutStockList').datagrid('reload');
					
				}else{
					//创建token
					createToken();
					
				}
			}
	   });
	}
	function groupDinnerOutStockQRcode() {
		var row = $('#groupDinnerOutStockList').datagrid('getSelected');
		if (row) {
			window.open("sale_order/downloadqc?saleorderid=" + row.saleOrderId);
		}
	}

	function opengroupDinnerOutStock() {
		initFlag_groupDinnerOutStock = 1;
		editIndex_groupDinnerOutStock = undefined;
		delgroupDinnerOutStockItemsRow();
		$('#groupDinnerOutStockForm').form('clear').form('disableValidation');
		initDialoggroupDinnerOutStock();
		$('#groupDinnerOutStockEditDialog').dialog({
			closed : false,
			modal : true,
			title : "新增团餐出库管理",
			iconCls : 'icon-document'
		});
		var time = new Date().format("yyyy-MM-dd hh:mm:ss");
		$("#orderTime_gdos").datetimebox('setValue', time);//登记日期
		$('#outstockNum_gdos').textbox('setValue',"G"+getPurchaseOrderNo());
		$('#traceCode_gdos').textbox('setValue',"G"+getPurchaseOrderNo());
		//创建token
		createToken();
	}

	
	function initDialoggroupDinnerOutStock() {
		$("#logisticsId_gdos").combobox(
				{
					url : 'logistics/findByUserCompanyId',
					valueField : 'logisticsId',
					textField : 'logisticsCompanyName',
					editable : false,
					required:true,
					onBeforeLoad : function(param) {
						var companyId = $("#index_user_companys").combobox('getValue');
						param.companyId = companyId;
					},
					onLoadSuccess : function() {
						var data = $('#logisticsId_gdos').combobox('getData');
						if (data && data.length == 1) {
							$('#logisticsId_gdos').combobox('setValue',
									data[0].logisticsId);
						}
					}
				});
		$("#orderTime_gdos").datetimebox({
			showSeconds : true,
			editable : false
		});

		
		
		
		
	}

	
	
	
	
	function delgroupDinnerOutStock() {
		var row = $('#groupDinnerOutStockList').datagrid('getSelected');
		if (row) {
			$.messager.confirm('删除团餐出库管理', '确认删除?', function(r) {
				if (r) {
					$.post("groupDinnerOutStock/delete", {
						saleOrderId : row.saleOrderId,
						outstockMainId : row.outstockMainId
					}, function(data) {
						var msg = eval('(' + data + ')');
						$.messager.alert('信息提示', msg.msg, 'info');
						if (msg.code == 1) {
							$('#groupDinnerOutStockList').datagrid('reload');
						}
					})
				}
			});
		} else {
			$.messager.alert('信息提示', '请选择一行记录！', 'info');
		}
	}
	function delgroupDinnerOutStockItemsRow() {
		var rows = $('#groupDinnerOutStockItemTable').datagrid('getRows');
		for (var i = rows.length - 1; i > -1; i--) {
			$('#groupDinnerOutStockItemTable').datagrid('cancelEdit', i).datagrid('deleteRow', i);
		}
	}

	function editgroupDinnerOutStock() {
		var row = $('#groupDinnerOutStockList').datagrid('getSelected');
		if (row) {
			$('#groupDinnerOutStockForm').form('clear').form('disableValidation');
			editIndex_groupDinnerOutStock = undefined;
			initDialoggroupDinnerOutStock();
			//解决添加数据后修改会出现分页为空 默认设置分页为5页 
			$('#groupDinnerOutStockItemTable').datagrid({
				pageSize : 5
			});
			
			$('#groupDinnerOutStockForm').form('load', {
				saleOrderId : row.saleOrderId,
				outstockMainId : row.outstockMainId,
				saleOrderNo : row.saleOrderNo,
				'saleOrder.orderTime' : row.orderTime,
				outstockNum:row.outstockNum,
				outstockDate : row.outstockDate,
				'saleOrder.traceCode' : row.traceCode,
				logisticsId : row.logisticsId
			});
			$('#groupDinnerOutStockEditDialog').dialog({
				closed : false,
				modal : true,
				title : "修改团餐出库管理",
				iconCls : 'icon-document'
			});
			url = 'outstockitem/findAllList?outstockMainId='+row.outstockMainId;
			$('#groupDinnerOutStockItemTable').datagrid({
				url: url
			});
			//加载token
			createToken();
		} else {
			$.messager.alert('信息提示', '请选择一行记录！', 'info');
		}
		
	}
	/**
	 * 载入采购单信息
	 */
	function loadgroupDinnerOutStock() {
		<shiro:hasPermission name="trans:groupDinnerOutStock:edit">
			 $('#groupDinnerOutStockList').datagrid({
				onDblClickCell: function(index,field,value){
					editgroupDinnerOutStock();
				}
			 });
		</shiro:hasPermission>
		$('#groupDinnerOutStockList').datagrid({
			url : 'groupDinnerOutStock/findAllList',
			rownumbers : true,
			pagination : true,
			pageSize : 10,
			onBeforeLoad : function(param) {
				param.companyId = $("#index_user_companys").combobox('getValue');
			},
			pageList : [ 10, 20, 30 ],
			singleSelect : true,
			fitColumns : true,
			fit : true,
			toolbar : '#groupDinnerOutStock-toolbar',
			columns : [ [
			{
				field : 'outstockNum',
				title : '出库单号',
				width : "25%"
			}, {
				field : 'logisticsAlias',
				title : '物流企业',
				width : "25%"
			},{
				field : 'orderTime',
				title : '下单时间',
				width : "15%"
			}, {
				field : 'outstockDate',
				title : '出库时间',
				width : "15%"
			}, {
				field : 'traceCode',
				title : '追溯码',
				width : "15%"
			}] ],
			view: detailview,
			detailFormatter:function(index,row){
	               return '<div style="padding:2px"><table class="ddv"></table></div>';
	           },
	           onExpandRow: function(index,row){
	               var ddv = $(this).datagrid('getRowDetail',index).find('table.ddv');
	               ddv.datagrid({
	            	   url : 'outstockitem/findAllList',
		           	   fitColumns : true,
					   singleSelect : true,
					   rownumbers : true,
					   pagination : true,
					   pageSize : 5,
					   pageList : [ 5, 10, 20 ],
					   loadMsg : '页面正在加载....',
					   height : 'auto',
					   onBeforeLoad : function(param) {
							param.outstockMainId = row.outstockMainId;
					   },
					   columns : [ [
						{
							field : 'productName',
							title : '团餐商品',
							width : '20%',
							formatter : function(value, row) {
								return row.product.productName;
							}
						},{
							field : 'productStandardName',
							title : '规格明细',
							width : '15%',
							formatter : function(value, row) {
								return row.standardDetail.productStandard.productStandardName;
							}
						},{
							field : 'goodsBatch',
							title : '批次号',
							width : '15%'
						}, {
							field : 'outstockNum',
							title : '出库数量',
							width : '5%'
						}, {
							field : 'deliveryTime',
							title : '配送时间',
							width : '8%',
							formatter : function(value, row) {
								return row.deliveryTime;
							}
						}, {
							field : 'deliveryBy',
							title : '配送人',
							width : '6%'
						}, {
							field : 'createTime',
							title : '创建时间',
							width : '8%',
							formatter : function(value, row) {
								return convertTimeStamp(row.createTime);
							}
						}, {
							field : 'createBy',
							title : '创建人',
							width : '6%'
						}, {
							field : 'updateTime',
							title : '修改时间',
							width : '8%',
							formatter : function(value, row) {
								return convertTimeStamp(row.updateTime);
							}
						}, {
							field : 'updateBy',
							title : '修改人',
							width : '6%'
						}]],
	                   onResize:function(){
	                       $('#groupDinnerOutStockList').datagrid('fixDetailRowHeight',index);
	                   },
	                   onLoadSuccess:function(){
	                       setTimeout(function(){
	                           $('#groupDinnerOutStockList').datagrid('fixDetailRowHeight',index);
	                       },0);
	                   }
	               });
	               $('#groupDinnerOutStockList').datagrid('fixDetailRowHeight',index);
	           }
		});
			
	}

	function endEditing_ProductpurchaseInstock() {
		if (editIndex_groupDinnerOutStock == undefined) {
			return true
		}
		if ($('#groupDinnerOutStockItemTable').datagrid('validateRow',
				editIndex_groupDinnerOutStock)) {
			$('#groupDinnerOutStockItemTable').datagrid('endEdit',
					editIndex_groupDinnerOutStock);
			editIndex_groupDinnerOutStock = undefined;
			return true;
		} else {
			return false;
		}
	}
	function onClickCell_groupDinnerOutStock(index, field) {
		if (editIndex_groupDinnerOutStock != index) {
			if (endEditing_ProductpurchaseInstock()) {
				$('#groupDinnerOutStockItemTable').datagrid('selectRow', index).datagrid('beginEdit', index);
				
				var productId = $('#groupDinnerOutStockItemTable').datagrid('getEditor', {index : index,field : 'productId'});
				var productType = $('#groupDinnerOutStockItemTable').datagrid('getEditor', {index : index,field : 'productType'});
				var productStandardDetailId = $('#groupDinnerOutStockItemTable').datagrid('getEditor', {index : index,field : 'productStandardDetailId'});
				var goodsBatch  = $('#groupDinnerOutStockItemTable').datagrid('getEditor', {index : index,field : 'goodsBatch'});

				var ed = $('#groupDinnerOutStockItemTable').datagrid('getEditor', {index : index,	field : field});
				if ( productType && productId  && productStandardDetailId && goodsBatch) {
					var companyId = $('#index_user_companys').combobox('getValue');
					var vproductType = $(productType.target).combobox('getValue');
					var url = 'product/findProductListByGoodsVariable?companyId='+ companyId+'&varGroup=deliverType&varName=1';
					$(productId.target).combobox('reload',url);
					
					var vproductId = $(productId.target).combobox('getValue');
					$(productStandardDetailId.target).combobox('reload','product_standard_detail/list?productId='+ vproductId);
					
					$(goodsBatch.target).combobox('reload', 'goods/getgoods?productId=' +vproductId
							+'&productStandardDetailId='+$(productStandardDetailId.target).combobox('getValue'));					

				}
				if (ed) {
						($(ed.target).data('textbox') ? $(ed.target).textbox('textbox') : $(ed.target)).focus();
						editIndex_groupDinnerOutStock = index;
						return;
				}
				editIndex_groupDinnerOutStock = index;
			} else {
				setTimeout(function() {
					$('#groupDinnerOutStockItemTable').datagrid(
							'selectRow', editIndex_groupDinnerOutStock);
				}, 0);
			}
		}
	}
	function onEndEdit_groupDinnerOutStock(index, row) {
		var ed = $('#groupDinnerOutStockItemTable').datagrid('getEditor', {
			index : index,
			field : 'productId'
		});
		row.productName = $(ed.target).combobox('getText');
		
		var ed1 = $('#groupDinnerOutStockItemTable').datagrid('getEditor', {
			index : index,
			field : 'productType'
		});
		row.productTypeName = $(ed1.target).combobox('getText');
		
		var ed2 = $('#groupDinnerOutStockItemTable').datagrid('getEditor', {
			index : index,
			field : 'productStandardDetailId'
		});
		row.fullStandardName = $(ed2.target).combobox('getText');
		
		

		
		
		var ed4 = $('#groupDinnerOutStockItemTable').datagrid('getEditor', {
			index : index,
			field : 'goodsBatch'
		});
		row.goodsBatch = $(ed4.target).combobox('getText');
	}
	function appendgroupDinnerOutStockItem(addType) {
		var bend = endEditing_ProductpurchaseInstock();
		if (bend) {
			$('#groupDinnerOutStockItemTable').datagrid('appendRow', {});
			editIndex_groupDinnerOutStock = $('#groupDinnerOutStockItemTable').datagrid('getRows').length - 1;
			$('#groupDinnerOutStockItemTable').datagrid(
					'selectRow', editIndex_groupDinnerOutStock).datagrid(
					'beginEdit', editIndex_groupDinnerOutStock);
			
			
		}
	}

	function removegroupDinnerOutStockItem() {
		var row = $('#groupDinnerOutStockItemTable').datagrid('getSelected');
		if (row == undefined || row.outstockItemId == undefined) {
			if (editIndex_groupDinnerOutStock == undefined) {
				return
			}
			$('#groupDinnerOutStockItemTable').datagrid('cancelEdit',
					editIndex_groupDinnerOutStock).datagrid('deleteRow',
					editIndex_groupDinnerOutStock);
			editIndex_groupDinnerOutStock = undefined;
			return;
		}
		
		if (row) {
			$.messager.confirm('信息提示', '确定要删除这条信息吗？', function(result) {
				if (result) {
					
					$.post("outstockitem/delete", {
						outstockItemId : row.outstockItemId,
					}, function(data) {
						var msg = eval('(' + data + ')');
						$.messager.alert('信息提示', msg.msg, 'info');
						if (msg.code == 1) {
							if (editIndex_groupDinnerOutStock == undefined) {
								return
							}
							$('#groupDinnerOutStockItemTable').datagrid('cancelEdit',editIndex_groupDinnerOutStock)
									.datagrid('deleteRow',editIndex_groupDinnerOutStock);
								editIndex_groupDinnerOutStock = undefined;
							$('#groupDinnerOutStockList').datagrid('reload');

						}
					});
				}
			});
		} else {
			$.messager.alert('信息提示', '亲,请选择一行信息！', 'info');
		}

	}
</script>