<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<link rel="stylesheet" type="text/css" href="static/css/datagrid_util.css">

<!-- layout  -->
<div class="easyui-layout" style="width:100%; " data-options="fit:true">
	<div id="p_purchaseManager" data-options="region:'west',split:true" title="销售门店" style="width:20%; ">
		<table class="easyui-treegrid" 
            data-options="
                url: 'customers/findCustomerListIncludeCompany',
                method: 'get',
                rownumbers: true,
                idField: 'customerId',
                treeField: 'customerAlias',
                fit:true,
                onBeforeLoad : function(row, param ) {
					param.companyId = $('#index_user_companys').combobox('getValue');  
				},
				onClickRow : function(row) {
					// console.info(row);
					loadpurchase_manager(row.customerId);
				}
            ">
	        <thead>
	            <tr>
	                <th data-options="field:'customerAlias'" width="80%"></th>
	                <!-- <th data-options="field:'level'" width="20%" align="right">level</th> --> 
	            </tr>
	        </thead>
    	</table>
	</div>
	<div data-options="region:'center'" title="进货管理列表">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center',border:false">
				<div id="purchase_manager-toolbar">
					<div class="wu-toolbar-button">
						<shiro:hasPermission name="trans:purchase_manager:add">
							<a href="#" class="easyui-linkbutton" iconCls="icon-add-s1"
								onclick="openpurchase_manager()" plain="true">新增进货登记</a>
						</shiro:hasPermission>
					</div>
					<div class="wu-toolbar-button">
						<label class="fitemlabel">进货批次:</label>
						<input class="easyui-textbox" id="purchase_manager_searchBatch">
						<label>进货时间:</label>
						<input id="purchase_manager_searchOrderStartTime">
						<label>至</label>
						<input id="purchase_manager_searchOrderEndTime">
						<label>产品名称:</label>
						<input id="purchase_manager_searchProductName" class="easyui-textbox">
						<a href="#" class="easyui-linkbutton" iconcls="icon-search" onclick="loadpurchase_manager()">搜索</a>
					</div>
				</div>
				<table id="purchase_managerList" ></table>
			</div>
		</div>
	</div> <!-- end of region:'center' -->
</div>


<div id="purchase_managerEditDialog"
	style="width: 735px; padding: 10px;">
	<form id="purchase_managerForm" method="post">
		<div style="margin-left: 10px;">
			<input type="hidden" name="instockMainId" id="purchase_manager_instockMainId" />
			<br/>
			<div class="fitem">
				<label class="fitemlabel" style="margin-right: 8px;">&nbsp;采购单:</label>
				<input class="easyui-combobox" id="purchase_manager_order" name="templateName" style="width: 325px;margin-left: 5px;">
				<label class="fitemlabel" style="margin-right: 8px;">&nbsp;供应商:</label>
				<input class="easyui-textbox" name="supplierName" style="width: 235px;" data-options="editable:false">
			</div>
								
			<div class="fitem">
					<label class="fitemlabel" style="margin-right: 8px;">&nbsp;登记人:</label>
					<input class="easyui-textbox" name="registrant" id="purchase_manager_registrant" style="width: 325px;">
					<label class="fitemlabel" >登记时间:</label> 
					<input class="easyui-datetimebox" id="purchase_manager_registrantTimeStr" style="width: 235px" >
			</div>
			<div class="fitem">
				<label class="fitemlabel" >进货批次:</label> 
				<input class="easyui-textbox" id="purchase_manager_Batch" style="width: 325px" data-options="required:true" />
				<label class="fitemlabel" >进货时间:</label> 
				<input class="easyui-datetimebox" id="purchase_manager_orderTimeStr" style="width: 235px" data-options="required:true">				
			</div>
		</div>
		<div style="margin-left: 10px;">
			<table id="purchase_managerItemTable"
				class="easyui-datagrid" title="进货明细"
				style="width: 680px; height: 265px;"
				data-options="
						iconCls: 'icon-edit',
			            singleSelect: true,
			            toolbar: '#tb_purchase_manager',
			            onClickCell: onClickCell_purchase_manager,
			            onEndEdit: onEndEdit_purchase_manager,
			            fitColumns : true,
						singleSelect : true,
						rownumbers : true,
						pagination : true,
						pageSize : 500,
						pageList : [ 500 ],
						loadMsg : '页面正在加载....',
						height : 'auto'
		            ">
				<thead>
					<tr>
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
	                                url:'product/list?productType=1&companyId='+ $('#index_user_companys').combobox('getValue'),
	                                onSelect:function(data){
		                                var row = $('#purchase_managerItemTable').datagrid('getSelected');
		      							var rowIndex = $('#purchase_managerItemTable').datagrid('getRowIndex', row);
		      							var detail = $('#purchase_managerItemTable').datagrid('getEditor', { index: rowIndex,field: 'productStandardDetailId'});
		      						 	$(detail.target).combobox('clear');
		      						    if(data.productId!=undefined || data.productId!=''){
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
												var detailVal=$(this).combobox('getValue');
												if(!detailVal&&data.length >0){
													$(this).combobox('select',data[0].productStandardDetailId);
												}
	 												var row = $('#purchase_managerItemTable').datagrid('getSelected'); 
	 		      									var rowIndex = $('#purchase_managerItemTable').datagrid('getRowIndex', row); 
													var ed = $('#purchase_managerItemTable').datagrid('getEditor', {index:rowIndex,field:'instockNum'});
	 												$(ed.target).next('span').find('input').focus(); 
										},
										onSelect:function(data){
											var row = $('#purchase_managerItemTable').datagrid('getSelected'); 
	 		      							var rowIndex = $('#purchase_managerItemTable').datagrid('getRowIndex', row); 
											var ed = $('#purchase_managerItemTable').datagrid('getEditor', {index:rowIndex,field:'goodsBatch'});
											$(ed.target).textbox('setValue',$('#purchase_manager_Batch').textbox('getValue'));
										}
										
		                            }
	                }">规格明细</th>
	                <th width="20%" data-options="field:'goodsBatch',
	                	formatter:function(value,row){
	                            return row.goodsBatch;
	                    },
	                	editor:{
	                		type:'textbox',
                            options:{
								required : true
                            }
	                }">批次</th>
					<th width="10%" data-options="field:'quantity'">采购数量</th>
	                <th width="10%" data-options="field:'instockNum',
	                	editor:{
	                	type:'numberbox',
	                	options:{
	                		required:true
	                }}">进货数量</th>
	               
		            <shiro:hasPermission name=" trans:purchase_manager:purchasePrice">
						 <th width="10%" data-options="field:'purchasePrice',
						editor:{
		                	type:'numberbox',
		                	options:{
		                		required:true,
		                		min:0,
		                		precision:2
		                	}
		                }
		                ">采购价格</th>
					</shiro:hasPermission>
	               
	                
					</tr>
				</thead>
			</table>
		</div>
		<div id="tb_purchase_manager" style="height: auto">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-add',plain:true"
				onclick="appendpurchase_managerItem(2)">新增</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-remove',plain:true"
				onclick="removepurchase_managerItem()">删除</a>
		</div>
	</form>
	<div style="margin: 10px 10px;">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
			style="width: 680px; height: 32px;"
			onclick="submitpurchase_managerItemForm()">确认提交</a>
	</div>
</div>
<script>
	var initFlag_purchase_manager = 0;
	var editIndex_purchase_manager = undefined;
	$(function() {
		$('#purchase_manager_searchOrderStartTime').datebox({
			prompt:'开始日期',
			onSelect:function(data){
				$('#purchase_manager_searchOrderEndTime').datebox('calendar').calendar({
					validator: function(date){
							return date>=data;
					}
				});
			}
		});
		$("#purchase_manager_searchOrderEndTime").datebox({
			prompt:'结束日期',
			onSelect:function(data){
				$('#purchase_manager_searchOrderStartTime').datebox('calendar').calendar({
					validator: function(date){
							return date<=data;
					}
				});
			}
		});
		

		

		loadpurchase_manager();
	});
	
		
	function submitpurchase_managerItemForm() {
		
		var bvalidate = $('#purchase_managerForm').form('enableValidation').form('validate');
		if (!bvalidate) {
			$.messager.alert('信息提示', '亲,您输入的数据不全哦！', 'info');
			return;
		}
		
		$('#purchase_managerItemTable').datagrid('endEdit',editIndex_purchase_manager);
		editIndex_purchase_manager = undefined;
		var rows = $("#purchase_managerItemTable").datagrid("getRows");
		var  vinstockItems = [];
		var  vcompanyId = $("#index_user_companys").combobox("getValue");
		//instockBatchNum
		for(var i=0;i<rows.length;i++){
			var instockItem = {};
			var productStandardDetail={};
			instockItem["instockItemId"] = rows[i].instockItemId;  //商品入库明细ID
			instockItem["goodsId"] = rows[i].goodsId;  //商品ID
			instockItem["instockMainId"] = rows[i].instockMainId;  //商品入库ID
			instockItem["instockNum"] = rows[i].instockNum; //入库数量
			instockItem["productId"] = rows[i].productId; 
			instockItem["productStandardDetailId"] = rows[i].productStandardDetailId; 
			instockItem["goodsBatch"] = rows[i].goodsBatch; 
			instockItem["purchasePrice"] = rows[i].purchasePrice; 
			vinstockItems[i] = instockItem;
		}
		var instockmain={
				purchaseOrderId:$("#purchase_manager_order").combobox("getValue"),
				instockMainId:$("#purchase_manager_instockMainId").val(),
				companyId: $("#index_user_companys").combobox("getValue"),//用户企业ID
				instockNum:$.trim($("#purchase_manager_Batch").val()),//入库单号
				instockBatchNum:$.trim($("#purchase_manager_Batch").val()),//进货批次号
				consignee:$.trim($("#purchase_manager_registrant").val()),//收货人
				registrant:$.trim($("#purchase_manager_registrant").val()), //登记人
				instockType:3,//设置入库类型为商品
				instockDate:$('#purchase_manager_orderTimeStr').datebox('getValue'),//入库日期 
				registDate:$('#purchase_manager_registrantTimeStr').datebox('getValue'),//登记日期 
				instockitems:vinstockItems
		};
		$.ajax({
			type : 'post',
			url : 'purchase_manager/save?token='+$('#index_token').val(),
			dataType : "json",
			contentType : "application/json",
			data : JSON.stringify(instockmain),
			success : function(data) {
				$.messager.alert('信息提示', data.msg, 'info');
				if (data.code == 1) {
					$('#purchase_managerEditDialog').window('close');
					$('#purchase_managerList').datagrid('reload');
				}else{
					//创建token
					createToken();
				}
			}
		});
	}
	function createGoodsBatch() {
		
		var strGoodsBatch = $("#purchase_manager_Batch").textbox('getValue');
		$('#purchase_managerItemTable').datagrid('acceptChanges');
		editIndex_purchase_manager = undefined;
		var rows = $("#purchase_managerItemTable").datagrid("getRows");
		for (var i = 0; i < rows.length; i++) {
			rows[i].goodsBatch=strGoodsBatch;//防止编辑状态下的值获取不到
            var td=$('#purchase_managerEditDialog .datagrid-body td[field="goodsBatch"]')[i];
            var div = $(td).find('div')[0];
            $(div).text(strGoodsBatch);
		}
	}
	function openpurchase_manager() {
		initFlag_purchase_manager = 1;
		editIndex_purchase_manager = undefined;
		delpurchase_managerItemsRow();
		$('#purchase_managerForm').form('clear');
		initDialogpurchase_manager();
		var time = new Date().format("yyyy-MM-dd");
		$('#purchase_managerForm').form('disableValidation');
		//创建token
		createToken();
		initPurchaseManagerData();//初始化数据
		
		$('#purchase_managerEditDialog').dialog({
			closed : false,
			modal : true,
			title : "新增进货管理",
			iconCls : 'icon-document'
		});
		
		
	}
	function initPurchaseManagerData() {
		var time = new Date().format("yyyy-MM-dd hh:mm:ss");
		$("#purchase_manager_Batch").textbox('setValue',getPurchaseOrderNo());//进货批次
		$("#purchase_manager_registrantTimeStr").textbox('setValue', time);//登记时间
		$("#purchase_manager_orderTimeStr").textbox('setValue', time);//登记时间
		$("#purchase_manager_registrant").textbox('setValue', $("#index_userName").val());//登记人
	}
	
	function initDialogpurchase_manager() {
		
		$("#purchase_manager_order").combobox({
			url : 'purchase_order/list',
			valueField : 'purchaseOrderId',
			textField : 'purchaseOrderNo',
			onBeforeLoad : function(param) {
				var companyId = $("#index_user_companys").combobox('getValue');
				param.companyId = companyId;
			},
			onLoadSuccess : function(record) {
				/*if(record.length >0 && $('#purchase_manager_productId').combobox('getValue')==''){
					$(this).combobox('select',record[0].processTemplateId);
				}*/
			},
			onSelect : function(record) {
						initFlag_purchase_manager = 0;
						$('#purchase_managerForm').form('disableValidation');
						initPurchaseManagerData();//初始化数据
						$('#purchase_managerForm').form('load', {
							supplierName:record.supplierAlias
						});
						
						var companyId = $('#index_user_companys').combobox('getValue');
						
						$('#purchase_managerItemTable').datagrid({
							url: 'purchase_order/findAllItemlist?purchaseOrderId='+record.purchaseOrderId,
							onLoadSuccess : function(data) {
								createGoodsBatch();
							}
						});
						
						
			}
		});
		
		

	}
	
	
	function delpurchase_managerItemsRow() {
		var rows = $('#purchase_managerItemTable').datagrid('getRows');
		for (var i = rows.length - 1; i > -1; i--) {
			$('#purchase_managerItemTable').datagrid('cancelEdit', i).datagrid('deleteRow', i);
		}
	}

	function formateDate(val){
		var date=new Date(val);
		return date.format("yyyy-MM-dd hh:mm:ss");
	}
	/**
	 * 载入采购单信息
	 */
	function loadpurchase_manager(customerId) {
		$('#purchase_managerList').datagrid({
			url : 'purchase_manager/findAllList',
			rownumbers : true,
			pagination : true,
			onBeforeLoad : function(param) {
				if (customerId) {
					param['instockMain.companyId'] = customerId;
				} else {
					param['instockMain.companyId'] = $("#index_user_companys").combobox('getValue');
				}
				
				param['instockMain.instockBatchNum']=$("#purchase_manager_searchBatch").val();
				param.startDate=$("#purchase_manager_searchOrderStartTime").datebox("getValue");
				if($("#purchase_manager_searchOrderEndTime").datebox("getValue")){
					param.endDate=$("#purchase_manager_searchOrderEndTime").datebox("getValue")+" 23:59:59";
				}
				param['standardDetail.product.productName']=$("#purchase_manager_searchProductName").val();
			},
			pageSize : 10,
			pageList : [ 10, 20, 30 ],
			singleSelect : true,
			fitColumns : true,
			fit : true,
			toolbar : '#purchase_manager-toolbar',
			columns : [ [ {
				field : 'instockBatchNum',
				title : '进货批次号',
				width : "10%",
				formatter : function(value, row) {
					return row.instockMain.instockBatchNum;
				}
			},{
				field : 'productName',
				title : '产品名称',
				width : "12%",
				formatter : function(value, row) {
					return row.standardDetail.product.productName;
				}
			},{
				field : 'fullStandardName',
				title : '规格',
				width : "8%",
				formatter : function(value, row) {
					return row.standardDetail.fullStandardName;
				}
			},{
				field : 'instockNum',
				title : '数量',
				width : "8%"
			},{
				field : 'supplierAlias',
				title : '供应商',
				width : "12%"
			},  {
				field : 'registrant',
				title : '登记人',
				width : "20%",
				formatter : function(value, row) {
					return row.instockMain.registrant;
				}
			}, {
				field : 'instockDate',
				title : '进货时间',
				width : "15%",
				formatter : function(value, row) { 
					return row.instockMain.instockDate.substring(0,row.instockMain.instockDate.length-2);
				}
			},{
					field : 'registDate',
					title : '登记时间',
					width : "15%",
					formatter : function(value, row) {
						return row.instockMain.registDate.substring(0,row.instockMain.registDate.length-2);
					}
			}]]
		});
	}

	function endEditing_ProductpurchaseInstock() {
		if (editIndex_purchase_manager == undefined) {
			return true
		}
		if ($('#purchase_managerItemTable').datagrid('validateRow',
				editIndex_purchase_manager)) {
			$('#purchase_managerItemTable').datagrid('endEdit',
					editIndex_purchase_manager);
			editIndex_purchase_manager = undefined;
			return true;
		} else {
			return false;
		}
	}
	function onClickCell_purchase_manager(index, field) {
		if (editIndex_purchase_manager != index) {
			if (endEditing_ProductpurchaseInstock()) {
				$('#purchase_managerItemTable').datagrid('selectRow', index).datagrid('beginEdit', index);
				
				var productId = $('#purchase_managerItemTable').datagrid('getEditor', {index : index,field : 'productId'});
				var productStandardDetailId = $('#purchase_managerItemTable').datagrid('getEditor', {index : index,field : 'productStandardDetailId'});
				var ed = $('#purchase_managerItemTable').datagrid('getEditor', {index : index,	field : field});
				if ( productId  && productStandardDetailId) {
					
					var vproductId = $(productId.target).combobox('getValue');
					var data=$(productStandardDetailId.target).combobox('getData');
					$(productStandardDetailId.target).combobox('reload','product_standard_detail/list?productId='+ vproductId);
					
				}
				if (ed) {
						($(ed.target).data('textbox') ? $(ed.target).textbox('textbox') : $(ed.target)).focus();
						editIndex_purchase_manager = index;
						return;
				}
				editIndex_purchase_manager = index;
			} else {
				setTimeout(function() {
					$('#purchase_managerItemTable').datagrid(
							'selectRow', editIndex_purchase_manager);
				}, 0);
			}
		}
	}
	function onEndEdit_purchase_manager(index, row) {
		var ed = $('#purchase_managerItemTable').datagrid('getEditor', {
			index : index,
			field : 'productId'
		});
		row.productName = $(ed.target).combobox('getText');
		
		var ed2 = $('#purchase_managerItemTable').datagrid('getEditor', {
			index : index,
			field : 'productStandardDetailId'
		});
		row.fullStandardName = $(ed2.target).combobox('getText');
	}
	function appendpurchase_managerItem(addType) {
		var bend = endEditing_ProductpurchaseInstock();
		if (bend) {
			$('#purchase_managerItemTable').datagrid('appendRow', {});
			editIndex_purchase_manager = $('#purchase_managerItemTable').datagrid('getRows').length - 1;
			$('#purchase_managerItemTable').datagrid(
					'selectRow', editIndex_purchase_manager).datagrid(
					'beginEdit', editIndex_purchase_manager);
			
			
		}
	}

	function removepurchase_managerItem() {
		var row = $('#purchase_managerItemTable').datagrid('getSelected');
		if (row == undefined || row.processItemId == undefined) {
			if (editIndex_purchase_manager == undefined) {
				return
			}
			$('#purchase_managerItemTable').datagrid('cancelEdit',
					editIndex_purchase_manager).datagrid('deleteRow',
					editIndex_purchase_manager);
			editIndex_purchase_manager = undefined;
			return;
		}
		
		if (row) {
			$.messager.confirm('信息提示', '确定要删除这条信息吗？', function(result) {
				if (result) {
					
					$.post("process/delProcessItem", {
						processItemId : row.processItemId,
					}, function(data) {
						var msg = eval('(' + data + ')');
						$.messager.alert('信息提示', msg.msg, 'info');
						if (msg.code == 1) {
							if (editIndex_purchase_manager == undefined) {
								return
							}
							$('#purchase_managerItemTable').datagrid('cancelEdit',editIndex_purchase_manager)
									.datagrid('deleteRow',editIndex_purchase_manager);
								editIndex_purchase_manager = undefined;
							$('#purchase_managerList').datagrid('reload');

						}
					});
				}
			});
		} else {
			$.messager.alert('信息提示', '亲,请选择一行信息！', 'info');
		}

	}
	 
</script>