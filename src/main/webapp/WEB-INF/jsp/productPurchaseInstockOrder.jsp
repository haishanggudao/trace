<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<link rel="stylesheet" type="text/css"
	href="static/css/datagrid_util.css">
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center',border:false">
		<div id="productPurchaseInstockOrder-toolbar">
			<div class="wu-toolbar-button">
				<shiro:hasPermission name="trans:productPurchaseInstockOrder:add">
					<a href="#" class="easyui-linkbutton" iconCls="icon-add-s1"
						onclick="openproductPurchaseInstockOrder()" plain="true">新增产品采购入库单</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="trans:productPurchaseInstockOrder:edit">
					<a href="#" class="easyui-linkbutton" iconCls="icon-edit-s1"
						onclick="editproductPurchaseInstockOrder()" plain="true">修改产品采购入库单</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="trans:productPurchaseInstockOrder:delete">
					<a href="#" class="easyui-linkbutton" iconCls="icon-delete-s1"
						onclick="delproductPurchaseInstockOrder()" plain="true">删除产品采购入库单</a>
				</shiro:hasPermission>
			</div>
		</div>
		<table id=productPurchaseInstockOrderList title="产品采购入库单列表"></table>
	</div>
</div>
<div id="productPurchaseInstockOrderEditDialog"
	style="width: 835px; padding: 10px;">
	<form id="productPurchaseInstockOrderForm" method="post">
		<div style="margin-left: 20px;">
			<input type="hidden" name="purchaseOrderId"
				id="purchaseOrderId_ProductPurchaseInstock"> <input
				type="hidden" name="instockMainId" id="instockMainId_ProductPurIn" />
			<br />
			<div class="fitem">
				<label class="fitemlabel">采购单号:</label> <input
					id="purchaseOrderNo_ProductPurIn" name="purchaseOrderNo"
					style="width: 200px;"> <label class="fitemlabel ">&nbsp;供
					应 商:</label> <select id="supplierId_ProductPurIn" name="supplierId"
					style="width: 200px;"></select>

			</div>
			<div class="fitem">
				<label class="fitemlabel">入库单号:</label> <input
					class="easyui-textbox" id="instockNum_ProductPurIn"
					name="instockNum" style="width: 200px;"
					data-options="prompt:'入库单号',validType:'name',required:true">
				<label class="fitemlabel">入库批次:</label> <input
					class="easyui-textbox" id="instockBatchNum_ProductPurIn"
					name="instockBatchNum" style="width: 200px;"
					data-options="prompt:'入库批次号',validType:'name',required:true">
			</div>
			<div class="fitem">
				<label class="fitemlabe">登 记 人:</label> <input
					class="easyui-textbox" id="registrant_ProductPurIn"
					name="registrant" style="width: 200px;"
					data-options="prompt:'登记人',validType:'name',required:true">
				<label class="fitemlabel" style="margin-left: 5px">登记日期:</label> <input
					class="easyui-datetimebox" id="registDate_ProductPurIn"
					name="registDate" style="width: 200px;"
					data-options="prompt:'登记日期',validType:'name',required:true">
			</div>
			<div class="fitem">
				<label class="fitemlabel">收 货 人:</label> <input
					class="easyui-textbox" id="consignee_ProductPurIn" name="consignee"
					style="width: 200px;"
					data-options="prompt:'收货人',validType:'name',required:true">
				<label class="fitemlabel" style="margin-left: 5px">入库日期:</label> <input
					class="easyui-datetimebox" id="instockDate_ProductPurIn"
					name="instockDate" style="width: 200px;"
					data-options="prompt:'入库日期',validType:'name',required:true">
			</div>
			<div class="fitem">
				<label class="fitemlabel">入库备注:</label> <input
					class="easyui-textbox" id="remark_ProductPurIn" name="remark"
					style="width: 705px; height: 38px"
					data-options="multiline:true,prompt:'备注',validType:'length[1,500]'">


			</div>
		</div>
		<div style="margin-left: 20px;">

			<table id="productPurchaseInstockOrderItemTable"
				class="easyui-datagrid" title="采购入库单明细"
				style="width: 760px; height: 265px;"
				data-options="
		                iconCls: 'icon-edit',
		                singleSelect: true,
		                toolbar: '#tb_ProductPurIn',
		                onClickCell: onClickCell_productPurchaseInstockOrder,
		                onEndEdit: onEndEdit_productPurchaseInstockOrder,
		                url: 'productPurchaseInstockOrder/findAllItemlist',
		                fitColumns : true,
						singleSelect : true,
						rownumbers : true,
						pagination : true,
						pageSize : 5,
						pageList : [ 5, 10, 20 ],
						loadMsg : '页面正在加载....',
						height : 'auto',
		             	onBeforeLoad: function(param){ 
							param.purchaseOrderId = $('#purchaseOrderId_ProductPurchaseInstock').val();
		             }
		            ">
				<thead>
					<tr>
						<th width="23%"
							data-options="field:'productId',
	                		formatter:function(value,row){
		                            return row.productNameMarketCode;
	                        },
	                        editor:{
	                            type:'combobox',
	                            options:{
	                                valueField:'productId',
	                                textField:'productNameMarketCode',
	                                method:'post',
	                                url:'product/list',
	                                required:true,
	                                editable:true,
	                                mode:'remote',
	                                onBeforeLoad:function(param){
	                                	param.companyId=$('#index_user_companys').combobox('getValue');
	                                	param.productType=1;
	                                	if(param.q){
	                                		param.productName=param.q;
	                                		param.marketCode=param.q;
	                                	}
	                                },
	                                onSelect:function(data){
		                                var row = $('#productPurchaseInstockOrderItemTable').datagrid('getSelected');
		      							var rowIndex = $('#productPurchaseInstockOrderItemTable').datagrid('getRowIndex', row);
		      							var detail = $('#productPurchaseInstockOrderItemTable').datagrid('getEditor', { index: rowIndex,field: 'productStandardDetailId'});
		      						    var rows = $('#productPurchaseInstockOrderItemTable').datagrid('getRows');
		      						    if(data.productId!=undefined || data.productId!=''){
		      						 	    $(detail.target).combobox('clear');
		      								$(detail.target).combobox('reload','product_standard_detail/list?productId='+data.productId);
										}
										var area_detail = $('#productPurchaseInstockOrderItemTable').datagrid('getEditor', { index: rowIndex,field: 'areaInfoId'});
		      						    if(data.productId!=undefined || data.productId!=''){
		      						        if(data.madein){
		      						 	    $(area_detail.target).combobox('setValue',data.madein);
		      						 	    }else{
		      						 	    <!-- if(rows.length>0||rows[0].areaInfoId)-->
		      						 	    if(rows[0].areaInfoId){
		      						 	    data.madein=rows[0].areaInfoId;
		      						 	   $(area_detail.target).combobox('setValue',data.madein);
		      						 	    
		      						 	    }else{
		      						 	     data.madein='上海市闵行区';
		      						 	    $(area_detail.target).combobox('setValue',data.madein);
		      						 	    }
									}
									}	
	                            }
	                        }}">产品名称-市场商品码</th>
						<th width="10%"
							data-options="field:'productStandardDetailId',
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
									editable:false,
									onLoadSuccess: function(data){
											//默认选中第一行
											if(data.length >0){
												$(this).combobox('select',data[0].productStandardDetailId);
 												var row = $('#productPurchaseInstockOrderItemTable').datagrid('getSelected'); 
 		      									var rowIndex = $('#productPurchaseInstockOrderItemTable').datagrid('getRowIndex', row); 
												var ed = $('#productPurchaseInstockOrderItemTable').datagrid('getEditor', {index:rowIndex,field:'instockNum'});
 												$(ed.target).next('span').find('input').focus(); 
											}
									}
	                            }
	                
	                }">规格明细</th>
						<th width="7%"
							data-options="field:'instockNum',
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

						<th width="15%"
							data-options="field:'goodsBatch',
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
							data-options="field:'areaInfoId',
	                		formatter:function(value,row){
	                            return row.catgName;
	                        },
	                        editor:{
	                            type:'combobox',
	                            options:{
	                                valueField:'id',
	                                textField:'catgName',
	                                method:'post',
	                                url:'productPurchaseInstockOrder/areaList',
	                                required:false,
	                                editable:true
	                        }}">产地</th>
						<th width="20%"
							data-options="field:'slaughterhouseId',
	                		formatter:function(value,row){
	                            return row.slaughterhouseName;
	                        },
	                        editor:{
	                            type:'combobox',
	                            options:{
	                                valueField:'slaughterhouseId',
	                                textField:'slaughterhouseName',
	                                method:'post',
	                                url:'slaughterhouse/list',
	                                required:false,
	                                editable:true,
	                               onBeforeLoad:function(param){
	                                	param.companyId=$('#index_user_companys').combobox('getValue');
	                              }
	                        }}">屠宰场</th>

					</tr>
				</thead>
			</table>
		</div>
		<div id="tb_ProductPurIn" style="height: auto">
			<!--         <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="appendProductPurchaseInstockItem(0)">空白新增</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="appendProductPurchaseInstockItem(1)">复制新增</a>
         -->
			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-add',plain:true"
				onclick="appendProductPurchaseInstockItem(2)">新增</a> <a
				href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-remove',plain:true"
				onclick="removeProductPurchaseItem()">删除</a>
		</div>
	</form>
	<div style="margin: 10px 20px;">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
			style="width: 760px; height: 32px;"
			onclick="submitProductPurchaseItemForm()">确认提交</a>
	</div>
</div>
<script>
	var initFlag_ProductPurIn = 0;
	var editIndex_ProductPurIn = undefined;
	$(function() {
		loadProductPurchaseOrders();
	});

	function submitProductPurchaseItemForm() {

		var bvalidate = $('#productPurchaseInstockOrderForm').form(
				'enableValidation').form('validate');
		if (!bvalidate) {
			$.messager.alert('信息提示', '亲,您输入的数据不全哦！', 'info');
			return;
		}

		$('#productPurchaseInstockOrderItemTable').datagrid('endEdit',
				editIndex_ProductPurIn);
		editIndex_ProductPurIn = undefined;
		var rows = $("#productPurchaseInstockOrderItemTable").datagrid(
				"getRows");
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
			goods["areaInfoId"] = rows[i].areaInfoId;
			goods["slaughterhouseId"] = rows[i].slaughterhouseId;
			lstGoods[i] = goods;

			product['productId'] = rows[i].productId;
			purchaseItem['product'] = product;
			purchaseItem['quantity'] = rows[i].instockNum;
			productStandardDetail['productStandardDetailId'] = rows[i].productStandardDetailId;
			purchaseItem['standardDetail'] = productStandardDetail;
			purchaseItems[i] = purchaseItem;

			instockItem["instockItemId"] = rows[i].instockItemId; //商品ID
			instockItem["productId"] = rows[i].productId; //商品ID
			instockItem["goodsId"] = rows[i].goodsId; //商品ID
			instockItem["instockNum"] = rows[i].instockNum; //入库数量
			vinstockItems[i] = instockItem;

		}
		var instock = {
			instockMainId : $("#instockMainId_ProductPurIn").val(),
			companyId : vcompanyId,//用户企业ID
			instockNum : $.trim($("#instockNum_ProductPurIn").val()),//入库单号
			instockBatchNum : $.trim($("#instockBatchNum_ProductPurIn").val()),//入库批次号
			consignee : $.trim($("#consignee_ProductPurIn").val()),//收货人
			registrant : $.trim($("#registrant_ProductPurIn").val()), //登记人
			remark : $.trim($("#remark_ProductPurIn").val()), //备注
			instockType : 1,//设置入库类型为产品
			instockDate : $('#instockDate_ProductPurIn').datetimebox('getValue'),//入库日期 
			registDate : $('#registDate_ProductPurIn').datetimebox('getValue'),//登记日期 
			instockitems : vinstockItems
		};

		var purchase = {
				purchaseOrderId : $("#purchaseOrderId_ProductPurchaseInstock").val(),
				purchaseOrderNo : $("#purchaseOrderNo_ProductPurIn").val(),
				companyId : vcompanyId,
				orderTime : $('#instockDate_ProductPurIn').datetimebox('getValue'),//采购单日期 
				registrant : $.trim($("#registrant_ProductPurIn").val()), //登记人
				supplierId : $('#supplierId_ProductPurIn').combobox('getValue'),
				purchaseItems : purchaseItems
		};
		//alert(JSON.stringify(purchaseItems));
		var param = {
			instockMain : instock,
			purchaseOrder : purchase,
			lstgoods : lstGoods
		};
		$.ajax({
					type : 'post',
					url : 'productPurchaseInstockOrder/save?token='+$('#index_token').val(),
					dataType : "json",
					contentType : "application/json",
					data : JSON.stringify(param),
					success : function(data) {
						$.messager.alert('信息提示', data.msg, 'info');
						if (data.code == 1) {
							$('#productPurchaseInstockOrderEditDialog').window(
									'close');
							$('#productPurchaseInstockOrderList').datagrid(
									'reload');
						}else{
							//创建token
							createToken();
							
						}
					}
				});

	}

	function openproductPurchaseInstockOrder() {
		delProductPurchaseInstockItemsRow();
		if (initFlag_ProductPurIn == 0) {
			initDialogComponent_Product_purchase_order();
			initFlag_ProductPurIn = 1;
		}
		var time = new Date().format("yyyy-MM-dd hh:mm:ss");
		$('#productPurchaseInstockOrderForm').form('clear').form('disableValidation');
		//采购单可编辑
		$("#purchaseOrderNo_ProductPurIn").textbox('enable');
		$("#purchaseOrderNo_ProductPurIn").textbox('setValue',getPurchaseOrderNo());
		//入库单可编辑
		$("#instockNum_ProductPurIn").textbox('enable');
		$("#instockNum_ProductPurIn").textbox('setValue', getPurchaseOrderNo());//入库单号
		$("#registrant_ProductPurIn").textbox('setValue',$("#index_userName").val());
		$("#consignee_ProductPurIn").textbox('setValue',$("#index_userName").val());
		$("#registDate_ProductPurIn").textbox('setValue', time);//登记日期
		$("#instockDate_ProductPurIn").textbox('setValue', time);//登记日期
		$("#instockBatchNum_ProductPurIn").textbox('setValue',getPurchaseOrderNo());//入库批次
		$('#productPurchaseInstockOrderEditDialog').dialog({
			closed : false,
			modal : true,
			title : "新增采购入库单",
			iconCls : 'icon-document'
		});
		//创建token
		createToken();
		
	}
	function initDialogComponent_Product_purchase_order() {
		$("#supplierId_ProductPurIn").combobox(
				{
					url : 'supplier/list?companyId='
							+ $("#index_user_companys").combobox('getValue'),
					method : 'post',
					valueField : 'supplierId',
					textField : 'supplierAlias',
					required : true,
					editable : true,
					onSelect : function(data) {
						$('#productPurchaseInstockOrderForm').form(
								'disableValidation');
						//delProductPurchaseInstockItemsRow();
					}
				});
		$("#purchaseOrderNo_ProductPurIn").textbox();
	}

	function delproductPurchaseInstockOrder() {

		var row = $('#productPurchaseInstockOrderList').datagrid('getSelected');

		if (row) {
			$.messager.confirm('删除产品采购入库单', '确认删除?', function(r) {
				if (r) {
					$.post("productPurchaseInstockOrder/delete", {
						purchaseOrderId : row.purchaseOrderId
					}, function(data) {
						var msg = eval('(' + data + ')');
						$.messager.alert('信息提示', msg.msg, 'info');
						if (msg.code == 1) {
							$('#productPurchaseInstockOrderList').datagrid(
									'reload');
						}
					})
				}
			});
		} else {
			$.messager.alert('信息提示', '请选择一行记录！', 'info');
		}
	}
	function delProductPurchaseInstockItemsRow() {
		var rows = $('#productPurchaseInstockOrderItemTable').datagrid(
				'getRows');
		for (var i = rows.length - 1; i > -1; i--) {
			$('#productPurchaseInstockOrderItemTable')
					.datagrid('cancelEdit', i).datagrid('deleteRow', i);
		}
	}

	function editproductPurchaseInstockOrder() {
		var row = $('#productPurchaseInstockOrderList').datagrid('getSelected');
		if (row) {
			$('#productPurchaseInstockOrderForm').form('clear').form(
					'disableValidation');
			editIndex_ProductPurIn = undefined;
			if (initFlag_ProductPurIn == 0) {
				// call: initialize the dialog for adding
				initDialogComponent_Product_purchase_order();
				initFlag_ProductPurIn = 1;
			}
			//解决添加数据后修改会出现分页为空 默认设置分页为5页 
			$('#productPurchaseInstockOrderItemTable').datagrid({
				pageSize : 5
			});

			$('#productPurchaseInstockOrderForm').form('load', {
				purchaseOrderId : row.purchaseOrder.purchaseOrderId,
				instockMainId : row.instockMain.instockMainId,
				purchaseOrderNo : row.purchaseOrder.purchaseOrderNo,
				supplierId : row.purchaseOrder.supplierId,
				registrant : row.instockMain.registrant,
				consignee : row.instockMain.consignee,
				registDate : row.instockMain.registDate,
				remark : row.instockMain.remark,
				instockDate : row.instockMain.instockDate,
				instockNum : row.instockMain.instockNum,
				instockBatchNum : row.instockMain.instockBatchNum
			});
			$("#instockNum_ProductPurIn").textbox('disable');
			$("#purchaseOrderNo_ProductPurIn").textbox('disable');
			$('#productPurchaseInstockOrderItemTable').datagrid('reload'); // reload the current page data 
			$('#productPurchaseInstockOrderEditDialog').dialog({
				closed : false,
				modal : true,
				title : "修改产品采购入库单",
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
	function loadProductPurchaseOrders() {
	    <shiro:hasPermission name="trans:productPurchaseInstockOrder:edit">
		 $('#productPurchaseInstockOrderList').datagrid({
			onDblClickCell: function(index,field,value){
				editproductPurchaseInstockOrder()
			}
		 });
		</shiro:hasPermission>
		$('#productPurchaseInstockOrderList').datagrid({
			method : 'post',
			url : 'productPurchaseInstockOrder/findAllList',
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
				var companyId = $("#index_user_companys")
						.combobox('getValue');
				param.companyId = companyId;
				param.instockType = 1;
			},
			detailFormatter : function(index, row) {
				return '<div style="padding:2px"><table class="ddv"></table></div>';
			},
			toolbar : '#productPurchaseInstockOrder-toolbar',
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
					},
					{
						field : 'instockNum',
						title : '入库单号',
						width : 90,
						sortable : true,
						formatter : function(value, row) {
							return row.instockMain.instockNum;
						}
					},
					{
						field : 'consignee',
						title : '收货人',
						width : 60,
						sortable : true,
						formatter : function(value, row) {
							return row.instockMain.consignee;
						}
					},
					{
						field : 'registrant',
						title : '登记人',
						width : 60,
						formatter : function(value, row) {
							return row.instockMain.registrant;
						}
					},
					{
						field : 'instockDate',
						title : '入库日期',
						width : 60,
						formatter : function(value, row) {
							//return convertDate(row.instockMain.instockDate);
							return row.instockMain.instockDate
									.substring(0, 19);
						}
					},
					{
						field : 'registDate',
						title : '登记日期',
						width : 60,
						formatter : function(value, row) {
							//return convertDate(row.instockMain.registDate);
							return row.instockMain.registDate
									.substring(0, 19);
						}
					}, {
						field : 'remark',
						title : '备注',
						width : 100,
						hidden : true,
						sortable : true,
						formatter : function(value, row) {
							return row.instockMain.remark;
						}
					} ] ],
			onExpandRow : function(index, row) {
				var ddv = $(this).datagrid('getRowDetail',index).find('table.ddv');
				ddv.datagrid({
							url : 'productPurchaseInstockOrder/findAllItemlist',
							fitColumns : true,
							singleSelect : true,
							rownumbers : true,
							pagination : true,
							pageSize : 5,
							pageList : [ 5, 10, 20 ],
							loadMsg : '页面正在加载....',
							height : 'auto',
							onBeforeLoad : function(param) {
								param.purchaseOrderId = row.purchaseOrderId;
							},
							columns : [ [
									{
										field : 'product',
										title : '产品',
										width : 80,
										formatter : function(value, row) {
											return row.productName;
										}
									},
									{
										field : 'standard',
										title : '产品规格',
										width : 80,
										formatter : function(
												value, row) {
											return row.fullStandardName;
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
									}

							] ],
							onResize : function() {
								$('#productPurchaseInstockOrderList').datagrid('fixDetailRowHeight',index);
							},
							onLoadSuccess : function() {
								setTimeout(
										function() {
											$('#productPurchaseInstockOrderList').datagrid('fixDetailRowHeight',index);
										}, 0);
							}
						});
				$('#productPurchaseInstockOrderList').datagrid(
						'fixDetailRowHeight', index);
			}
			
		});
	}

	function endEditing_ProductpurchaseInstock() {
		if (editIndex_ProductPurIn == undefined) {
			return true
		}
		if ($('#productPurchaseInstockOrderItemTable').datagrid('validateRow',
				editIndex_ProductPurIn)) {
			$('#productPurchaseInstockOrderItemTable').datagrid('endEdit',
					editIndex_ProductPurIn);
			editIndex_ProductPurIn = undefined;
			return true;
		} else {
			return false;
		}
	}
	function onClickCell_productPurchaseInstockOrder(index, field) {
		if (editIndex_ProductPurIn != index) {
			if (endEditing_ProductpurchaseInstock()) {
				$('#productPurchaseInstockOrderItemTable').datagrid(
						'selectRow', index).datagrid('beginEdit', index);
				var detail = $('#productPurchaseInstockOrderItemTable')
						.datagrid('getEditor', {
							index : index,
							field : 'productId'
						});
				var vproductId = $(detail.target).combobox('getValue');
				var ed = $('#productPurchaseInstockOrderItemTable').datagrid(
						'getEditor', {
							index : index,
							field : field
						});
				var ed2 = $('#productPurchaseInstockOrderItemTable').datagrid(
						'getEditor', {
							index : index,
							field : 'productStandardDetailId'
						});
				if (ed2) {
					$(ed2.target).combobox(
							'reload',
							'product_standard_detail/list?productId='
									+ vproductId);
				}
				if (field == 'areaInfoId') {
					$(ed.target).combobox('reload',
							'productPurchaseInstockOrder/areaList');
				} else {
					if (ed) {
						($(ed.target).data('textbox') ? $(ed.target).textbox(
								'textbox') : $(ed.target)).focus();
						editIndex_ProductPurIn = index;
						return;
					}
				}
				editIndex_ProductPurIn = index;
			} else {
				setTimeout(function() {
					$('#productPurchaseInstockOrderItemTable').datagrid(
							'selectRow', editIndex_ProductPurIn);
				}, 0);
			}
		}
	}
	function onEndEdit_productPurchaseInstockOrder(index, row) {
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
			field : 'areaInfoId'
		});
		row.catgName = $(ed3.target).combobox('getText');
		var ed4 = $(this).datagrid('getEditor', {
			index : index,
			field : 'slaughterhouseId'
		});
		row.slaughterhouseName = $(ed4.target).combobox('getText');

	}
	function appendProductPurchaseInstockItem(addType) {
		var supplier = $("#supplierId_ProductPurIn").combobox("getValue");
		if (supplier) {
			var bend = endEditing_ProductpurchaseInstock();
			if (bend) {
				var areaInfoId = "";
				var slaughterhouseId = "";
				if (addType == 1) {
					var selectedRow = $("#productPurchaseInstockOrderItemTable")
							.datagrid("getSelected");
					if (selectedRow == null) {
						$.messager.alert('信息提示', '请选择一行数据后复制新增！', 'info');
						return;
					} else {
						areaInfoId = selectedRow.areaInfoId;
						slaughterhouseId = selectedRow.slaughterhouseId;
					}
				}
				if (addType == 2) {
					var rows = $("#productPurchaseInstockOrderItemTable")
							.datagrid("getRows");
					if (rows.length > 0) {
						areaInfoId = rows[0].areaInfoId;
						slaughterhouseId = rows[0].slaughterhouseId;
					}
				}
				if (addType == 0) {
					areaInfoId = "";
					slaughterhouseId = "";
				}

				$('#productPurchaseInstockOrderItemTable').datagrid(
						'appendRow', {
							areaInfoId : areaInfoId,
							slaughterhouseId : slaughterhouseId,
							goodsBatch : getPurchaseOrderNo()
						});
				editIndex_ProductPurIn = $(
						'#productPurchaseInstockOrderItemTable').datagrid(
						'getRows').length - 1;
				$('#productPurchaseInstockOrderItemTable').datagrid(
						'selectRow', editIndex_ProductPurIn).datagrid(
						'beginEdit', editIndex_ProductPurIn);
			}
		} else {
			$.messager.alert('信息提示', '请选择供应商！', 'info');
		}

	}

	function removeProductPurchaseItem() {
		var row = $('#productPurchaseInstockOrderItemTable').datagrid('getSelected');
		if (row == undefined || row.instockItemId == undefined) {
			
			if (editIndex_ProductPurIn == undefined) {
				return
			}
			$('#productPurchaseInstockOrderItemTable').datagrid('cancelEdit',
					editIndex_ProductPurIn).datagrid('deleteRow',
					editIndex_ProductPurIn);
			editIndex_ProductPurIn = undefined;
			return;
		}

		if (row) {
			$.messager.confirm('信息提示', '确定要删除这条信息吗？', function(result) {
				if (result) {
					$.post("productPurchaseInstockOrder/deleteitem", {
						instockItemId : row.instockItemId,
						goodsId : row.goodsId,
						num : row.instockNum
					}, function(data) {
						var msg = eval('(' + data + ')');
						$.messager.alert('信息提示', msg.msg, 'info');
						if (msg.code == 1) {
							if (editIndex_ProductPurIn == undefined) {
								return
							}
							$('#productPurchaseInstockOrderItemTable').datagrid('cancelEdit',editIndex_ProductPurIn)
							.datagrid('deleteRow',editIndex_ProductPurIn);
							editIndex_ProductPurIn = undefined;
							$('#productPurchaseInstockOrderList').datagrid('reload');

						}
					});

				}
			});
		} else {
			$.messager.alert('信息提示', '亲,请选择一行信息！', 'info');
		}

	}
</script>