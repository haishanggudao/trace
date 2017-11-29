<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<link rel="stylesheet" type="text/css" href="static/css/datagrid_util.css">
<div class="easyui-layout" data-options="fit:true">
	<div id="p_purcharseOrder" data-options="region:'west',split:true" title="销售门店" style="width:20%; ">
		<table class="easyui-treegrid" 
            data-options="
                url: 'customers/findCustomerListIncludeCompany',
                method: 'get',
                rownumbers: true,
                idField: 'custCompanyId',
                treeField: 'customerAlias',
                fit:true,
                onBeforeLoad : function(row, param ) {
					param.companyId = $('#index_user_companys').combobox('getValue');  
				},
				onClickRow : function(row) {
					// console.info(row);
					loadPurchaseOrders(row.custCompanyId);
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
	<div data-options="region:'center',border:false">
		<div id="purchaseOrder-toolbar">
			<div class="wu-toolbar-button">
				<shiro:hasPermission name="trans:purchase_order:add">
					<a href="#" class="easyui-linkbutton" iconCls="icon-add-s1" onclick="openPurchaseOrder()" >新增采购单</a> 
				</shiro:hasPermission>			
				<shiro:hasPermission name="trans:purchase_order:edit">
					<a href="#" class="easyui-linkbutton" iconCls="icon-edit-s1" onclick="editPurchaseOrder()" >修改采购单</a> 
				</shiro:hasPermission>			
				<shiro:hasPermission name="trans:purchase_order:delete">
					<a href="#" class="easyui-linkbutton" iconCls="icon-delete-s1" onclick="delPurchaseOrder()" >删除采购单</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="trans:purchase_order:list">
					<!-- <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="openpurchaseElectronTableDialog()">电子随附单</a> -->
					&nbsp;
					<a href="#" class="easyui-linkbutton" iconcls="icon-search" onclick="openSelectingDialog_purchaseOrder()">高级搜索</a>
				</shiro:hasPermission>				
				

			</div>
		</div>
		<table id=purchaseOrderList title="采购单列表"></table>
	</div>

</div>
<div id="purchaseOrderEditDialog"
	style="width: 470px; padding: 20px;">
	<form id="purchaseOrderForm" method="post">
		<input type="hidden" name="purchaseOrderId" id="purchaseOrderId_purchase">
		 <div class="fitem">
            <label class="fitemlabel ">采购单号:</label>
          	<input id="purchaseOrderNo_purchaseOrder" name="purchaseOrderNo" style="width: 355px">&nbsp;
        </div>
		 <div class="fitem">
            <label class="fitemlabel ">&nbsp;供   应   商:</label>
          	<select id="supplierId_pOrder" name="supplierId" style="width: 355px;"></select>&nbsp;
        </div>
        <div style="width: 100%; text-align: left; padding: 5px 0px 5px 0px;">
        	<span style="width: 15%;">订单日期:</span> 
        		<input class="easyui-datetimebox" id="orderTime_purchaseOrder" name="orderTime" style="width: 35%;" data-options="prompt:'订单日期',validType:'name',required:true">
			<span style="width: 15%;">&nbsp;登 记 人:</span> 
				<input class="easyui-textbox" id="registrant_purchaseOrder" name="registrant" style="width: 35%;" data-options="prompt:'登记人',validType:'name',required:true">
		</div>
		<table id="purchaseItemTable" class="easyui-datagrid" title="采购单明细" style="width:100%;height:265px"
            data-options="
                iconCls: 'icon-edit',
                singleSelect: true,
                toolbar: '#tb',
				rownumbers : true,
				pagination : true,
				pageSize : 500,
				pageList : [ 500],
				loadMsg : '页面正在加载....',
				height : 'auto',
                onClickCell: onClickCell_purchaseOrder,
                onEndEdit: onEndEdit_purchaseOrder,
                url: 'purchase_order/findAllItemlist',
             	onBeforeLoad: function(param){ 
					param.purchaseOrderId = $('#purchaseOrderId_purchase').val();
             }
            ">
        <thead>
            <tr>
                <th data-options="field:'productId',width:180,
                		formatter:function(value,row){
                            return row.productName;
                        },
                        editor:{
                            type:'combobox',
                            options:{
                                valueField:'productId',
                                textField:'productName',
                                method:'post',
                                url:'product/findProductInfoAreDetailed',
                                required:true,
                                editable:false,
                                onBeforeLoad:function(param){
                                	param.companyId=$('#index_user_companys').combobox('getValue');
                                	param.productType=1;
                                },
                                onSelect:function(data){
	                                var row = $('#purchaseItemTable').datagrid('getSelected');
	      							var rowIndex = $('#purchaseItemTable').datagrid('getRowIndex', row);
	      							var detail = $('#purchaseItemTable').datagrid('getEditor', { index: rowIndex,field: 'productStandardDetailId'});
	      							$(detail.target).combobox('clear');
	      							$(detail.target).combobox('reload','product_standard_detail/list?productId='+data.productId);
                            }
                        }}">产品</th>
                <th data-options="field:'productStandardDetailId',width:130,
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
									if(data.length >0){
										$(this).combobox('select',data[0].productStandardDetailId);
										var row = $('#purchaseItemTable').datagrid('getSelected'); 
	      								var rowIndex = $('#purchaseItemTable').datagrid('getRowIndex', row); 
										var ed = $('#purchaseItemTable').datagrid('getEditor', {index:rowIndex,field:'quantity'});
										$(ed.target).next('span').find('input').focus(); 
									}
								}
                            }
                
                }">规格明细</th>
                <th data-options="field:'quantity',width:50,editor:{
                	type:'numberbox',
                	options:{
                		precision:2,
                		required:true
                }}">数量</th>
            </tr>
        </thead>
    </table>
 
    <div id="tb" style="height:auto">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add-s1',plain:true" onclick="appendPurchaseItem()">新增</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-delete-s1',plain:true" onclick="removePurchaseItem()">删除</a>
    </div>
	</form>
	<div style="margin: 10px 0;">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok" style="width: 100%; height: 32px;" onclick="submitPurchaseItemForm()">确认提交</a>
	</div>
</div>
<div id="purchaseElectronTableDialog" class="easyui-dialog" data-options="modal:true,closed:true" style="width: 50%;">
	<table id="purchaseElectronTable" style="width: 100%;">
		<tr align="center"><th>时间</th><th>单号</th></tr>
		<tr align="center"><td >2016-06-13</td><td>20160613</td></tr>
	</table>
</div>

<div id="searchDialog_purchaseOrder" class="easyui-dialog fm" title="高级搜索" 
data-options="modal:true,closed:true,iconCls:'icon-search'"  
style="width:800px;padding:20px" buttons="#searchDialog_buttons_purchaseOrder">
	<form id="frm_purchaseSearch_purchaseOrder" method="post">
		<div class="ftitle">高级搜索信息</div>
		<div class="fitem">
        	<label class="fitemlabel width_100">采购单号:</label>
            <input id="s_purchaseOrderNo_purchaseOrder" class="easyui-textbox" style="width:180px;" data-options="prompt:'采购单号',validType:length[1,255]">
            &nbsp;
            <label class="fitemlabel width_100">订单日期:</label> 
            <input id="s_orderTimeStart_purchaseOrder" class="easyui-datebox" style="width:100px;" data-options="prompt:'订单日期',validType:length[1,255]">
            <input id="s_orderTimeEnd_purchaseOrder" class="easyui-datebox" style="width:100px;" data-options="prompt:'订单日期',validType:length[1,255]">
        </div>
        <div class="fitem">
        	<label class="fitemlabel width_100">供应商名称:</label>
            <input id="s_supplierName_purchaseOrder" class="easyui-textbox" style="width:180px;" data-options="prompt:'供应商名称',validType:length[1,255]">
            &nbsp;
            <label class="fitemlabel width_100">商品名称:</label>
            <input id="s_goodsName_purchaseOrder" class="easyui-textbox" style="width:180px;" data-options="prompt:'商品名称',validType:length[1,255]">
        </div>
	</form>
</div>
<div id="searchDialog_buttons_purchaseOrder"  style="text-align: center;">
	<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-search" onclick="searchform_purchaseOrder()" style="width:90px">搜索</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" onclick="javascript:$('#frm_purchaseSearch_purchaseOrder').form('clear')" style="width:90px">重置</a>
</div>

<script>
var initFlag_purchase=0;
var editIndex_purchaseOrder = undefined;
	$(function() {
		loadPurchaseOrders();
	});

	function openpurchaseElectronTableDialog(){
		$('#purchaseElectronTableDialog').dialog({
			closed : false,
			modal : true,
			title : "电子随附单",
			iconCls : 'icon-save',
			buttons : [ {
				text : '打印',
				iconCls : 'icon-print',
				handler : function(){
					$("#purchaseElectronTableDialog").printArea();
				}
			}, {
				text : '取消',
				iconCls : 'icon-cancel',
				handler : function() {
					$('#purchaseElectronTableDialog').dialog('close');
				}
			} ]
		});
		
	}
	
	function submitPurchaseItemForm(){
		var bvalidate = $('#purchaseOrderForm').form('enableValidation').form('validate');
		if (!bvalidate) {
			$.messager.alert('信息提示', '亲,您输入的数据不全哦！', 'info');
			return;
		}
		$('#purchaseItemTable').datagrid('endEdit', editIndex_purchaseOrder);
		editIndex_purchaseOrder = undefined;
		
		var rows=$("#purchaseItemTable").datagrid("getRows");
		
		$('#purchaseOrderForm').form('submit',{
			url : 'purchase_order/addOrUpdatePurchaseOrder?token='+$('#index_token').val(),
			onSubmit : function(param) {
				param.items = JSON.stringify(rows);
				param.companyId = $("#index_user_companys").combobox("getValue");
				return true;
			},
			success : function(data) {
				var msg = eval('(' + data + ')');
				$.messager.alert('信息提示', msg.msg, 'info');
				if (msg.code == 1) {
					$('#purchaseOrderEditDialog').window('close');
					$('#purchaseOrderList').datagrid('reload');
				}else{
					//创建token
					createToken();
				}
			}
	   });
		
		
	}
	
	function openPurchaseOrder() {
		delPurchaseItemsRow();
		
		if (initFlag_purchase == 0) {
			initDialogComponent_purchase_order();
			initFlag_purchase = 1;
		}
		
		$("#supplierId_pOrder").combobox({
			onLoadSuccess: function(data){
				// console.info(data);
				//默认选中第一行
				if(data.length >0){
					$(this).combobox('select',data[0].supplierId);
				}
			}
		});

		$('#purchaseOrderForm').form('clear').form('disableValidation');
		$("#purchaseOrderNo_purchaseOrder").textbox('enable');
		$("#purchaseOrderNo_purchaseOrder").textbox('setValue',getPurchaseOrderNo());
		
		var time = new Date().format("yyyy-MM-dd hh:mm:ss");
		$("#orderTime_purchaseOrder").textbox('setValue', time);//登记日期
		$("#registrant_purchaseOrder").textbox('setValue',$("#index_userName").val());	// 登记人
		
		$('#purchaseOrderEditDialog').dialog({
			closed : false,
			modal : true,
			title : "新增采购单",
			iconCls : 'icon-document'
		});
		//创建token
		createToken();
	}

	function initDialogComponent_purchase_order() {
		$("#supplierId_pOrder").combobox({
			url : 'supplier/list',
			method : 'post',
			valueField : 'supplierId',
			textField:'supplierAlias',
			required : true,
			editable:false,
			onBeforeLoad : function(param) {
				param.companyId = $("#index_user_companys").combobox('getValue'); 
			},
			onSelect:function(data){
				$('#purchaseOrderForm').form('disableValidation');
				//delPurchaseItemsRow();
			},
			onLoadSuccess: function(data){
				//默认选中第一行
				if(data.length >0){
					$(this).combobox('select',data[0].supplierId);
				}
			}
		});
		$("#purchaseOrderNo_purchaseOrder").textbox();
	}
	
	function delPurchaseOrder(){
		var row = $('#purchaseOrderList').datagrid('getSelected');
		if (row) {
			$.messager.confirm('删除采购单', '确认删除?', function(r) {
				if (r) {
					$.post("purchase_order/delPurchaseOrder", {
						purchaseOrderId : row.purchaseOrderId
					}, function(data) {
						var msg = eval('(' + data + ')');
						$.messager.alert('信息提示', msg.msg, 'info');
						if (msg.code == 1) {
							$('#purchaseOrderList').datagrid('reload');
						}
					})
				}
			});
		} else {
			$.messager.alert('信息提示', '请选择一行记录！', 'info');
		}
	}
	function delPurchaseItemsRow(){
		var rows=$('#purchaseItemTable').datagrid('getRows');
		for(var i=rows.length-1;i>-1;i--){
			$('#purchaseItemTable').datagrid('cancelEdit', i).datagrid('deleteRow',i);
		}
	}
	
	function editPurchaseOrder() {
		var row = $('#purchaseOrderList').datagrid('getSelected'); 
		
		if(row){
			$('#purchaseOrderForm').form('clear').form('disableValidation');  
			
			editIndex_purchaseOrder = undefined;
			
			if(initFlag_purchase==0){
				// call: initialize the dialog for adding
				initDialogComponent_purchase_order();
				initFlag_purchase=1;
			}
			
			//解决添加数据后修改会出现分页为空 默认设置分页为5页 
			$('#purchaseItemTable').datagrid({pageSize : 500});
			
			$('#purchaseOrderForm').form('load', {
				purchaseOrderId : row.purchaseOrderId,
				purchaseOrderNo : row.purchaseOrderNo,
				supplierId : row.supplierId,
				orderTime : row.orderTime,
				registrant : row.registrant,
			}); 
			
			$("#purchaseOrderNo_purchaseOrder").textbox('disable');
			
			$('#purchaseItemTable').datagrid('reload');    // reload the current page data 
			
			$('#purchaseOrderEditDialog').dialog({
				closed : false,
				modal : true,
				title : "修改采购单",
				iconCls : 'icon-document'
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
	function loadPurchaseOrders(custCompanyId) {
	    <shiro:hasPermission name="trans:purchase_order:edit">
		 $('#purchaseOrderList').datagrid({
			onDblClickCell: function(index,field,value){
				editPurchaseOrder()
			}
		 });
		</shiro:hasPermission>
		$('#purchaseOrderList').datagrid({
			method:'post',
			url:'purchase_order/findAllList',
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
			onBeforeLoad:function(param){
				var companyId=$("#index_user_companys").combobox('getValue');
				
				if(custCompanyId){
					param.companyId=custCompanyId;
				}else{
					param.companyId=companyId;
				}
			},
            detailFormatter:function(index,row){
                return '<div style="padding:2px"><table class="ddv"></table></div>';
            },
            toolbar : '#purchaseOrder-toolbar',
			columns : [ [ {
				field : 'purchaseOrderNo',
				title : '采购单号',
				width : 100,
				sortable : true
			},{
				field : 'companyName',
				title : '供应商',
				width : 100,
				sortable : true,
				formatter:function(value,row){
					return row.supplierAlias;
				}
			}, {
				field : 'orderTime',
				title : '下单时间',
				width : 100,
				sortable : true,
				formatter:function(value,row){
					return row.orderTime.substring(0,row.orderTime.length-2);
				}
				
			} ] ],
            onExpandRow: function(index,row){
                var ddv = $(this).datagrid('getRowDetail',index).find('table.ddv');
                ddv.datagrid({
                    url:'purchase_order/findAllItemlist',
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
                    columns:[[
                        {
                        	field:'product',
                        	title:'产品',
                        	width:200,
                        	formatter:function(value,row){
                        		return row.product.productName;
                        	}
                        },
                        {
                        	field:'standard',
                        	title:'产品规格',
                        	width:200,
                        	formatter:function(value,row){
                        		return row.standardDetail.fullStandardName;
                        	}	
                        },
                        {field:'quantity',title:'采购数量',width:100}
                    ]],
                    onResize : function() {
						$('#purchaseOrderList').datagrid('fixDetailRowHeight',index);
					},
					onLoadSuccess : function() {
						setTimeout(function() {
									$('#purchaseOrderList').datagrid('fixDetailRowHeight',index);
						}, 0);
					}
                });
                $('#purchaseOrderList').datagrid('fixDetailRowHeight',index);
            }
        });
	}
	
	
    function endEditing_purchaseOrder(){
        if (editIndex_purchaseOrder == undefined){return true}
        if ($('#purchaseItemTable').datagrid('validateRow', editIndex_purchaseOrder)){
            $('#purchaseItemTable').datagrid('endEdit', editIndex_purchaseOrder);
            editIndex_purchaseOrder = undefined;
            return true;
        } else {
            return false;
        }
    }
    function onClickCell_purchaseOrder(index, field){
        if (editIndex_purchaseOrder != index){
            if (endEditing_purchaseOrder()){
                $('#purchaseItemTable').datagrid('selectRow', index).datagrid('beginEdit', index);
                var productId = $('#purchaseItemTable').datagrid('getEditor', {index : index,field : 'productId'});
                var productStandardDetailId = $('#purchaseItemTable').datagrid('getEditor', {index : index,field : 'productStandardDetailId'});
                var ed = $('#purchaseItemTable').datagrid('getEditor', {index:index,field:field});
                if(productId && productStandardDetailId){
                	$(productStandardDetailId.target).combobox('reload','product_standard_detail/list?productId='+$(productId.target).combobox('getValue'));
                }
                if (ed){
                    ($(ed.target).data('textbox') ? $(ed.target).textbox('textbox') : $(ed.target)).focus();
                }
                editIndex_purchaseOrder = index;
            } else {
                setTimeout(function(){
                    $('#purchaseItemTable').datagrid('selectRow', editIndex_purchaseOrder);
                },0);
            }
        }
    }
    function onEndEdit_purchaseOrder(index, row){
        var ed = $(this).datagrid('getEditor', {
            index: index,
            field: 'productId'
        });
        row.productName = $(ed.target).combobox('getText');
        var ed2 = $(this).datagrid('getEditor', {
            index: index,
            field: 'productStandardDetailId'
        });
        row.fullStandardName = $(ed2.target).combobox('getText');
    }
    
    function appendPurchaseItem(){
    	var supplier=$("#supplierId_pOrder").combobox("getValue");
    	if(supplier){
	        if (endEditing_purchaseOrder()){
	        	// append a new row and initialize the quantity 
	            $('#purchaseItemTable').datagrid('appendRow',{quantity:'1.00'}); 
	            editIndex_purchaseOrder = $('#purchaseItemTable').datagrid('getRows').length-1;
	            $('#purchaseItemTable').datagrid('selectRow', editIndex_purchaseOrder).datagrid('beginEdit', editIndex_purchaseOrder);
	        }
    	}else{
    		$.messager.alert('信息提示', '请选择供应商！', 'info');
    	}
    }
    function removePurchaseItem(){
        if (editIndex_purchaseOrder == undefined){return}
        $('#purchaseItemTable').datagrid('cancelEdit', editIndex_purchaseOrder)
                .datagrid('deleteRow', editIndex_purchaseOrder);
        editIndex_purchaseOrder = undefined;
    }

    // open the dialog for selecting
    function openSelectingDialog_purchaseOrder(){  
    	$('#searchDialog_purchaseOrder').dialog('open'); 
    }
    
    // advanced query
    function searchform_purchaseOrder(){
    	// console.info($("#s_purchaseOrderNo_purchaseOrder").val());
    	$('#purchaseOrderList').datagrid({  
		    url:'purchase_order/findAllQueryList',  
		    queryParams:{
		    	purchaseOrderNo: $("#s_purchaseOrderNo_purchaseOrder").val(), 
		    	orderTimeStart: $("#s_orderTimeStart_purchaseOrder").datebox("getValue"),
		    	orderTimeEnd: $("#s_orderTimeEnd_purchaseOrder").datebox("getValue"),
		    	supplierAlias: $("#s_supplierName_purchaseOrder").val(),
	        	productName: $("#s_goodsName_purchaseOrder").val(),
	        	companyId: $("#index_user_companys").combobox('getValue'),
		    } 
		});
    	$('#searchDialog_purchaseOrder').dialog('close');
    }
</script>