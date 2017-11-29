<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center',border:false">
		<div id="purchaseInstockOrder-toolbar">
			<div class="wu-toolbar-button">
				<shiro:hasPermission name="trans:purchaseInstockOrder:add">
					<a href="#" class="easyui-linkbutton" iconCls="icon-add-s1"
						onclick="openPurchaseInstockOrder()" plain="true">新增采购入库单</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="trans:purchaseInstockOrder:edit">
					<a href="#" class="easyui-linkbutton" iconCls="icon-edit-s1"
						onclick="editPurchaseInstockOrder()" plain="true">修改采购入库单</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="trans:purchaseInstockOrder:delete">
					<a href="#" class="easyui-linkbutton" iconCls="icon-delete-s1"
						onclick="delPurchaseInstockOrder()" plain="true">删除采购入库单</a>
				</shiro:hasPermission>
			</div>
		</div>
		<table id=purchaseInstockOrderList title="采购入库单列表"></table>
	</div>
</div>
<div id="purchaseInstockOrderEditDialog"
	style="width: 600px; padding: 20px;">
	<form id="purchaseInstockOrderForm" method="post">
		<input type="hidden" name="purchaseOrderId"
			id="purchaseOrderId_purchaseInstock"> <input type="hidden"
			name="instockMainId" id="instockMainId_PurIn" />

		<div style="width: 100%; text-align: left; padding: 5px 0px 5px 0px;">
			<span style="width: 15%;">采购单号:</span> <input
				id="purchaseOrderNo_PurIn" name="purchaseOrderNo"
				style="width: 85%;">
		</div>
		<div style="width: 100%; text-align: left; padding: 5px 0px 5px 0px;">
			<span style="width: 15%;">&nbsp;供 应 商:</span> <select
				id="supplierId_PurIn" name="supplierId" style="width: 85%;"></select>
		</div>
		<div style="width: 100%; text-align: left; padding: 5px 0px 5px 0px;">
			<span style="width: 15%;">&nbsp;登 记 人:</span> <input
				class="easyui-textbox" id="registrant_PurIn" name="registrant"
				style="width: 35%;"
				data-options="prompt:'登记人',validType:'name',required:true">
			<span style="width: 15%;">&nbsp;&nbsp;收 货 人:</span> <input
				class="easyui-textbox" id="consignee_PurIn" name="consignee"
				style="width: 35%;"
				data-options="prompt:'收货人',validType:'name',required:true">
		</div>
		<div style="width: 100%; text-align: left; padding: 5px 0px 5px 0px;">
			<span style="width: 15%;">登记日期:</span> <input class="easyui-datebox"
				id="registDate_PurIn" name="registDate" style="width: 35%;"
				data-options="prompt:'登记日期',validType:'name',required:true">
			<span style="width: 15%;">入库日期:</span> <input class="easyui-datebox"
				id="instockDate_PurIn" name="instockDate" style="width: 35%;"
				data-options="prompt:'入库日期',validType:'name',required:true">
		</div>
		<div style="width: 100%; text-align: left; padding: 5px 0px 15px 0px;">
			<span style="width: 15%;">入库单号:</span> <input class="easyui-textbox"
				id="instockNum_PurIn" name="instockNum" style="width: 35%;"
				data-options="prompt:'入库单号',validType:'name',required:true">
			<span style="width: 15%;">入库批次:</span> <input class="easyui-textbox"
				id="instockBatchNum_PurIn" name="instockBatchNum"
				style="width: 35%;"
				data-options="prompt:'入库批次号',validType:'name',required:true">
		</div>
		<table id="purchaseInstockOrderItemTable" class="easyui-datagrid"
			title="采购入库单明细" style="width: 100%; height: 265px;"
			data-options="
						iconCls: 'icon-edit',
		                singleSelect: true,
		                toolbar: '#tb_PurIn',
		                onClickCell: onClickCell_purchaseInstockOrder,
		                onEndEdit: onEndEdit_purchaseInstockOrder,
		                url: 'purchaseInstockOrder/findAllItemlist',
		                fitColumns : true,
						singleSelect : true,
						rownumbers : true,
						pagination : true,
						pageSize : 5,
						pageList : [ 5, 10, 20 ],
						loadMsg : '页面正在加载....',
						height : 'auto',
		             	onBeforeLoad: function(param){ 
							param.purchaseOrderId = $('#purchaseOrderId_purchaseInstock').val();
	            		}
	            ">
			<thead>
				<tr>
					<th
						data-options="field:'productId',width:150,
                		formatter:function(value,row){
                            return row.productName;
                        },
                        editor:{
                            type:'combobox',
                            options:{
                                valueField:'productId',
                                textField:'productName',
                                method:'post',
                                url:'product/list',
                                required:true,
                                onBeforeLoad:function(param){
                                	param.companyId=$('#index_user_companys').combobox('getValue');
                                	param.productType=2;
                                },
                                onSelect:function(data){
	                                var row = $('#purchaseInstockOrderItemTable').datagrid('getSelected');
	      							var rowIndex = $('#purchaseInstockOrderItemTable').datagrid('getRowIndex', row);
	      							var detail = $('#purchaseInstockOrderItemTable').datagrid('getEditor', { index: rowIndex,field: 'productStandardDetailId'});
	      							$(detail.target).combobox('clear');
	      							$(detail.target).combobox({
				                    		url:'product_standard_detail/list', 
						                    onBeforeLoad: function(param){ 
						                    	param.productId = data.productId;
						                    },
											onLoadSuccess : function(data) {
												var detailVal=$(this).combobox('getValue');
												if(!detailVal&&data.length >0){
													$(this).combobox('select',data[0].productStandardDetailId);
												}
											}
										});
                            },
                            onLoadSuccess : function(data) {
										var selectedrow = $('#purchaseInstockOrderItemTable').datagrid('getSelected');
				        				var rowIndex = $('#purchaseInstockOrderItemTable').datagrid('getRowIndex', selectedrow);
				                    	var ed = $('#purchaseInstockOrderItemTable').datagrid('getEditor', {index:rowIndex,field:'productStandardDetailId'});
				                    	$(ed.target).combobox({
											url:'product_standard_detail/list', 
						                    onBeforeLoad: function(param){ 
						                    	param.productId = selectedrow.productId;
						                    },
						                    onLoadSuccess : function(data) {
													$(this).combobox('select',selectedrow.productStandardDetailId);
											}
										});
										
										
									}
                        }}">原料</th>
					<th
						data-options="field:'productStandardDetailId',width:100,
                	formatter:function(value,row){
                	        var val = '';
                			if(row.purchaseItem!=undefined){
                				val = row.fullStandardName;
                			}else{
                				val = row.fullStandardName;
                			}
                            return val;
                           
                        },
                	editor:{
                			type:'combobox',
                            options:{
								method : 'post',
								valueField : 'productStandardDetailId',
								textField : 'fullStandardName',
								required : true,
								editable:false
                            }
                
                }">规格明细</th>
					<th
						data-options="field:'instockNum',width:100,
                	formatter:function(value,row){
                	        var val = '';
                			if(row.purchaseItem!=undefined){
                				val = row.instockNum;
                			}else{
                				val = row.instockNum;
                			}
                            return val;
                	},
                	editor:{
                	type:'numberbox',
                	options:{
                		required:true
                }}">数量</th>
					<th
						data-options="field:'goodsBatch',width:150,
                	formatter:function(value,row){
                	        var val = '';
                			if(row.goodsBatch!=undefined){
                				val = row.goodsBatch;
                			}
                            return val;
                	},
                	editor:{
	                	type:'textbox',
	                	options:{
	                		required:true
                 }}">批次号</th>
                 <th width="20%"
							data-options="field:'originId',
	                		formatter:function(value,row){
	                            return row.originName;
	                        },
	                        editor:{
	                            type:'combobox',
	                            options:{
	                                valueField:'originId',
	                                textField:'originName',
	                                method:'post',
	                                url:'origin/list',
	                                required:false,
	                                editable:true,
	                               onBeforeLoad:function(param){
	                                	param.companyId=$('#index_user_companys').combobox('getValue');
	                              }
	                        }}">源头信息</th>
					<th
						data-options="field:'goodsId',width:1,
                	formatter:function(value,row){
                            var val = '';
                			if(row.goodsId!=undefined){
                				val = row.goodsId;
                			}
                            return val;
                	},hidden:true">goodsId</th>
					<th
						data-options="field:'instockItemId',width:1,
                	formatter:function(value,row){
                	        var val = '';
                			if(row.instockItemId!=undefined){
                				val = row.instockItemId;
                			}
                            return val;
                	},hidden:true">instockItemId</th>
					<th
						data-options="field:'purchaseItemId',width:1,
                	formatter:function(value,row){
                	        var val = '';
                			if(row.purchaseItemId!=undefined){
                				val = row.purchaseItemId;
                			}
                            return val;
                	},hidden:true">purchaseItemId</th>
				</tr>
			</thead>
		</table>

		<div id="tb_PurIn" style="height: auto">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-add',plain:true"
				onclick="appendPurchaseInstockOrderItem()">新增</a> <a
				href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-remove',plain:true"
				onclick="removePurchaseInstockOrderItem()">删除</a>
		</div>
	</form>
	<div style="margin: 10px 0;">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
			style="width: 100%; height: 32px;"
			onclick="submitPurchaseInstockOdersItemForm()">确认提交</a>
	</div>
</div>
<script>
	var initFlag_PurIn = 0;
	var editIndex_PurIn = undefined;
	$(function() {
		loadpurchaseInstockOrders();
	});

	function submitPurchaseInstockOdersItemForm() {

		var bvalidate = $('#purchaseInstockOrderForm').form('enableValidation')
				.form('validate');
		if (!bvalidate) {
			$.messager.alert('信息提示', '亲,您输入的数据不全哦！', 'info');
			return;
		}
		
		$('#purchaseInstockOrderItemTable').datagrid('endEdit', editIndex_PurIn);
		editIndex_PurIn = undefined;
		var rows = $("#purchaseInstockOrderItemTable").datagrid("getRows");
		var purchaseItems = [];
		var vinstockItems = [];
		var lstGoods = [];
		var vcompanyId = $("#index_user_companys").combobox("getValue");
		//instockBatchNum
		for (var i = 0; i < rows.length; i++) {
			var instockItem = {};
			var purchaseItem = {};
			var product = {};
			var productStandardDetail = {};
			var goods = {};
			goods["productId"] = rows[i].productId;
			goods["companyId"] = vcompanyId;
			goods["productStandardDetailId"] = rows[i].productStandardDetailId;
			goods["goodsBatch"] = $.trim(rows[i].goodsBatch);
			goods["num"] = rows[i].instockNum;
			goods["goodsId"] = rows[i].goodsId;
			lstGoods[i] = goods;

			product['productId'] = rows[i].productId;
			purchaseItem['product'] = product;
			purchaseItem['quantity'] = rows[i].instockNum;
			productStandardDetail['productStandardDetailId'] = rows[i].productStandardDetailId;
			purchaseItem['standardDetail'] = productStandardDetail;
			purchaseItem['purchaseItemId'] = rows[i].purchaseItemId;
			purchaseItems[i] = purchaseItem;

			instockItem["instockItemId"] = rows[i].instockItemId; //入库明细ID
			instockItem["productId"] = rows[i].productId; //产品ID
			instockItem["goodsId"] = rows[i].goodsId; //商品ID
			instockItem["instockNum"] = rows[i].instockNum; //入库数量
			instockItem["originId"] = rows[i].originId; //源头信息
			vinstockItems[i] = instockItem;

		}
		var instock = {
			instockMainId : $("#instockMainId_PurIn").val(),
			companyId : $("#index_user_companys").combobox("getValue"),//用户企业ID
			instockNum : $.trim($("#instockNum_PurIn").val()),//入库单号
			instockBatchNum : $.trim($("#instockBatchNum_PurIn").val()),//入库批次号
			consignee : $.trim($("#consignee_PurIn").val()),//收货人
			registrant : $.trim($("#registrant_PurIn").val()), //登记人
			instockType : 2,//设置入库类型为原料
			instockDate : $('#instockDate_PurIn').datebox('getValue'),//入库日期 
			registDate : $('#registDate_PurIn').datebox('getValue'),//登记日期 
			instockitems : vinstockItems
		};

		var purchase = {
			purchaseOrderId : $("#purchaseOrderId_purchaseInstock").val(),
			purchaseOrderNo : $("#purchaseOrderNo_PurIn").val(),
			companyId : vcompanyId,
			supplierId : $("#supplierId_PurIn").combobox('getValue'),
			purchaseItems : purchaseItems
		};
		//alert(JSON.stringify(purchaseItems));
		var param = {
			instockMain : instock,
			purchaseOrder : purchase,
			lstgoods : lstGoods 
		};
		console.info($('#index_token').val());
		$.ajax({
			type : 'post',
			url : 'purchaseInstockOrder/save?token='+$('#index_token').val(),
			dataType : "json",
			contentType : "application/json",
			data : JSON.stringify(param),
			success : function(data) {
				$.messager.alert('信息提示', data.msg, 'info');
				if (data.code == 1) {
					$('#purchaseInstockOrderEditDialog').window('close');
					$('#purchaseInstockOrderList').datagrid('reload');
				}else{
					//创建token
					createToken();
					
				}
			}
		});

	}

	function openPurchaseInstockOrder() {   
		delPurchaseInstockItemsRow();
		if (initFlag_PurIn == 0) {
			initDialogComponent_purchaseInstock_order();
			// 			initFlag_PurIn = 1;
		}
		var time = new Date().format("yyyy-MM-dd");
		//var time = new Date().format("YYYY-MM-dd hh:mm:ss");
		$('#purchaseInstockOrderForm').form('clear').form('disableValidation');
		$("#purchaseOrderNo_PurIn").textbox('enable');
		$("#purchaseOrderNo_PurIn").textbox('setValue', getPurchaseOrderNo());
		$("#registrant_PurIn").textbox('setValue', $("#index_userName").val());
		$("#consignee_PurIn").textbox('setValue', $("#index_userName").val());
		$("#registDate_PurIn").textbox('setValue', time);//登记日期
		$('#instockDate_PurIn').textbox('setValue',time);//入库日期
		$("#instockNum_PurIn").textbox('setValue', getPurchaseOrderNo());//入库单号
		$("#instockBatchNum_PurIn").textbox('setValue', getPurchaseOrderNo());//入库批次

		$('#purchaseInstockOrderEditDialog').dialog({
			closed : false,
			modal : true,
			title : "新增采购入库单",
			iconCls : 'icon-document'
		});
		createToken();
	}
	function initDialogComponent_purchaseInstock_order() {
		$("#supplierId_PurIn").combobox(
				{
					url : 'supplier/list?companyId='
							+ $("#index_user_companys").combobox('getValue'),
					method : 'post',
					valueField : 'supplierId',
					textField : 'supplierAlias',
					required : true,
					editable : false,
					onSelect : function(data) {
						$('#purchaseInstockOrderForm')
								.form('disableValidation');
						//delPurchaseInstockItemsRow();
					}
				});
		$("#purchaseOrderNo_PurIn").textbox();
	}

	function delPurchaseInstockOrder() {
		var row = $('#purchaseInstockOrderList').datagrid('getSelected');
		if (row) {
			$.messager.confirm('删除采购入库单', '确认删除?', function(r) {
				if (r) {
					$.post("purchaseInstockOrder/delete", {
						purchaseOrderId : row.purchaseOrderId
					}, function(data) {
						var msg = eval('(' + data + ')');
						$.messager.alert('信息提示', msg.msg, 'info');
						if (msg.code == 1) {
							$('#purchaseInstockOrderList').datagrid('reload');
						}
					})
				}
			});
		} else {
			$.messager.alert('信息提示', '请选择一行记录！', 'info');
		}
	}
	function delPurchaseInstockItemsRow() {
		var rows = $('#purchaseInstockOrderItemTable').datagrid('getRows');
		for (var i = rows.length - 1; i > -1; i--) {
			$('#purchaseInstockOrderItemTable').datagrid('cancelEdit', i)
					.datagrid('deleteRow', i);
		}
	}

	function editPurchaseInstockOrder() {
		var row = $('#purchaseInstockOrderList').datagrid('getSelected');

		if (row) {
			$('#purchaseInstockOrderForm').form('clear').form(
					'disableValidation');
			editIndex_PurIn = undefined;
			if (initFlag_PurIn == 0) {
				// call: initialize the dialog for adding
				initDialogComponent_purchaseInstock_order();
				initFlag_PurIn = 1;
			}
			//解决添加数据后修改会出现分页为空 默认设置分页为5页 
			$('#purchaseInstockOrderItemTable').datagrid({
				pageSize : 5
			});
			$('#purchaseInstockOrderForm').form('load', {
				purchaseOrderId : row.purchaseOrder.purchaseOrderId,
				instockMainId : row.instockMain.instockMainId,
				purchaseOrderNo : row.purchaseOrder.purchaseOrderNo,
				supplierId : row.purchaseOrder.supplierId,
				registrant : row.instockMain.registrant,
				consignee : row.instockMain.consignee,
				registDate : row.instockMain.registDate,
				instockDate : row.instockMain.instockDate,
				instockNum : row.instockMain.instockNum,
				instockBatchNum : row.instockMain.instockBatchNum
			});
			$("#purchaseOrderNo_PurIn").textbox('disable');
			$('#purchaseInstockOrderItemTable').datagrid('reload'); // reload the current page data 
			$('#purchaseInstockOrderEditDialog').dialog({
				closed : false,
				modal : true,
				title : "修改采购入库单",
				iconCls : 'icon-document'
			});
			createToken();
		} else {
			$.messager.alert('信息提示', '请选择一行记录！', 'info');
		}
	}
	/**
	 * 载入采购单信息
	 */
	function loadpurchaseInstockOrders() {
	    <shiro:hasPermission name="trans:purchaseInstockOrder:edit">
		 $('#purchaseInstockOrderList').datagrid({
			onDblClickCell: function(index,field,value){
				editPurchaseInstockOrder()
			}
		 });
		</shiro:hasPermission>
		$('#purchaseInstockOrderList').datagrid({
			method : 'post',
			url : 'purchaseInstockOrder/findAllList',
			view : detailview,
			rownumbers : true,
			pageSize : 10,
			pagePosition : 'bottom',
			pageList : [ 10, 20, 50 ],
			pagination : true,
			multiSort : true,
			fitColumns : true,
			fit : true,
			iconCls : 'icon-ok',
			singleSelect : true,
			onBeforeLoad : function(param) {
				var companyId = $("#index_user_companys")
						.combobox('getValue');
				param.companyId = companyId;
				param.instockType = 2;//设置入库类型为原料
			}, 
			toolbar : '#purchaseInstockOrder-toolbar',
			columns : [ [
					{
						field : 'purchaseOrderNo',
						title : '采购单号',
						width : 100,
						sortable : true,
						formatter : function(value, row) {
							return row.purchaseOrder.purchaseOrderNo;
						}
					},
					{
						field : 'supplierAlias',
						title : '供应商',
						width : 150,
						sortable : true,
						formatter : function(value, row) {
							return row.purchaseOrder.supplierAlias;
						}
					},
					{
						field : 'instockBatchNum',
						title : '入库批次号',
						width : 90,
						sortable : true,
						formatter : function(value, row) {
							return row.instockMain.instockBatchNum;
						}
					}, {
						field : 'instockNum',
						title : '入库单号',
						width : 90,
						sortable : true,
						formatter : function(value, row) {
							return row.instockMain.instockNum;
						}
					}, {
						field : 'consignee',
						title : '收货人',
						width : 60,
						sortable : true,
						formatter : function(value, row) {
							return row.instockMain.consignee;
						}
					}, {
						field : 'registrant',
						title : '登记人',
						width : 60,
						formatter : function(value, row) {
							return row.instockMain.registrant;
						}
					}, {
						field : 'instockDate',
						title : '入库日期',
						width : 60,
						formatter : function(value, row) {
							return row.instockMain.instockDate.substring(0,10);
						}
					}, {
						field : 'registDate',
						title : '登记日期',
						width : 60,
						formatter : function(value, row) {
							return row.instockMain.registDate.substring(0,10);
						}
					} ] ],
			detailFormatter : function(index, row) {
				return '<div style="padding:2px"><table class="ddv"></table></div>';
			},
			onExpandRow : function(index, row) {
				var ddv = $(this).datagrid('getRowDetail',
						index).find('table.ddv');
				ddv
						.datagrid({
							url : 'purchaseInstockOrder/findAllItemlist?purchaseOrderId='
									+ row.purchaseOrderId,
							fitColumns : true,
							singleSelect : true,
							rownumbers : true,
							loadMsg : '',
							height : 'auto',
							columns : [ [
									{
										field : 'product',
										title : '原料',
										width : 80,
										formatter : function(
												value, row) {
											return row.product.productName;
										}
									},
									{
										field : 'standard',
										title : '产品规格',
										width : 80,
										formatter : function(
												value, row) {
											return row.standardDetail.fullStandardName;
										}
									},
									{
										field : 'instockNum',
										title : '采购数量',
										width : 50,
										formatter : function(
												value, row) {
											return row.instockNum;
										}
									},
									{
										field : 'goodsBatch',
										title : '批次号',
										width : 100,
										formatter : function(
												value, row) {
											return row.goodsBatch;
										}
									},
									{
										field : 'originName',
										title : '源头信息',
										width : 50
									}

							] ],
							onResize : function() {
								$('#purchaseInstockOrderList')
										.datagrid(
												'fixDetailRowHeight',
												index);
							},
							onLoadSuccess : function() {
								setTimeout(
										function() {
											$(
													'#purchaseInstockOrderList')
													.datagrid(
															'fixDetailRowHeight',
															index);
										}, 0);
							}
						});
				$('#purchaseInstockOrderList').datagrid(
						'fixDetailRowHeight', index);
			}
		});
	}

	function endEditing_purchaseInstockOrder() {
		if (editIndex_PurIn == undefined) {
			return true
		}
		if ($('#purchaseInstockOrderItemTable').datagrid('validateRow',
				editIndex_PurIn)) {
			$('#purchaseInstockOrderItemTable').datagrid('endEdit',
					editIndex_PurIn);
			editIndex_PurIn = undefined;
			return true;
		} else {
			return false;
		}
	}
	function onClickCell_purchaseInstockOrder(index, field) {
		if (editIndex_PurIn != index) {
			if (endEditing_purchaseInstockOrder()) {
				$('#purchaseInstockOrderItemTable').datagrid('selectRow', index).datagrid('beginEdit',index);
				var ed = $('#purchaseInstockOrderItemTable').datagrid(
						'getEditor', {
							index : index,
							field : field
						});

					if (ed) {
						($(ed.target).data('textbox') ? $(ed.target).textbox(
								'textbox') : $(ed.target)).focus();
						editIndex_PurIn = index;
						return;
					}
				}

				editIndex_PurIn = index;
			} else {
				setTimeout(function() {
					$('#purchaseInstockOrderItemTable').datagrid('selectRow',
							editIndex_PurIn);
				}, 0);
			}
	}
	function onEndEdit_purchaseInstockOrder(index, row) {
		var ed = $(this).datagrid('getEditor', {
			index : index,
			field : 'productId'
		});
		row.productName = $(ed.target).combobox('getText');
		var ed2 = $(this).datagrid('getEditor', {
			index : index,
			field : 'productStandardDetailId'
		});
		row.fullStandardName = $(ed2.target).combobox('getText');
		var ed3 = $(this).datagrid('getEditor', {
			index : index,
			field : 'originId'
		});
		row.originName=$(ed3.target).combobox('getText');
	}
	function appendPurchaseInstockOrderItem() {
		var supplier = $("#supplierId_PurIn").combobox("getValue");
		if (supplier) {
			var bend = endEditing_purchaseInstockOrder();
			if (bend) {
				$('#purchaseInstockOrderItemTable').datagrid('appendRow', {
					goodsBatch : getPurchaseOrderNo()
				});
				editIndex_PurIn = $('#purchaseInstockOrderItemTable').datagrid(
						'getRows').length - 1;
				$('#purchaseInstockOrderItemTable').datagrid('selectRow',
						editIndex_PurIn).datagrid('beginEdit', editIndex_PurIn);
			}
		} else {
			$.messager.alert('信息提示', '请选择供应商！', 'info');
		}

	}

	function removePurchaseInstockOrderItem() {
		var row = $('#purchaseInstockOrderItemTable').datagrid('getSelected');
		if (row == undefined || row.instockItemId == undefined) {
			if (editIndex_PurIn == undefined) {
				return
			}
			$('#purchaseInstockOrderItemTable').datagrid('cancelEdit',
					editIndex_PurIn).datagrid('deleteRow', editIndex_PurIn);
			editIndex_PurIn = undefined;
			return;
		}

		if (row) {
			$.messager.confirm('信息提示', '确定要删除这条信息吗？', function(result) {
				if (result) {
					$.post("purchaseInstockOrder/deleteitem", {
						purchaseItemId : row.purchaseItemId,
						instockItemId : row.instockItemId,
						goodsId : row.goodsId,
						num : row.instockNum
					}, function(data) {
						var msg = eval('(' + data + ')');
						$.messager.alert('信息提示', msg.msg, 'info');
						if (msg.code == 1) {
							if (editIndex_PurIn == undefined) {
								return
							}
							$('#purchaseInstockOrderItemTable').datagrid('cancelEdit',editIndex_PurIn)
							.datagrid('deleteRow',editIndex_PurIn);
							editIndex_PurIn = undefined;
							$('#purchaseInstockOrderList').datagrid('reload');

						}
					});

				}
			});
		} else {
			$.messager.alert('信息提示', '亲,请选择一行信息！', 'info');
		}

	}
</script>