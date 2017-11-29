<%@ page contentType="text/html;charset=UTF-8" language="java"%> 
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<link rel="stylesheet" type="text/css" href="static/css/datagrid_util.css">
<link rel="stylesheet" type="text/css" href="static/css/print.css">
<script type="text/javascript" src="static/js/jquery-barcode.js"></script>

<!-- layout  -->
<div class="easyui-layout" style="width:100%; " data-options="fit:true">
<!-- 	<div id="p_saleOrder" data-options="region:'west',split:true" title="销售门店" style="width:20%; "> -->
<!-- 		<table class="easyui-treegrid"  -->
<!--             data-options=" -->
<!--                 url: 'customers/findCustomerListIncludeCompany', -->
<!--                 method: 'get', -->
<!--                 rownumbers: true, -->
<!--                 idField: 'customerId', -->
<!--                 treeField: 'customerAlias', -->
<!--                 fit:true, -->
<!--                 onBeforeLoad : function(row, param ) { -->
<!-- 					param.companyId = $('#index_user_companys').combobox('getValue');   -->
<!-- 				}, -->
<!-- 				onClickRow : function(row) { -->
<!-- 					// console.info(row); -->
<!-- 					loadSaleOrdersByCategory(row.customerId); -->
<!-- 				} -->
<!--             "> -->
<!-- 	        <thead> -->
<!-- 	            <tr> -->
<!-- 	                <th data-options="field:'customerAlias'" width="80%"></th> -->
<!-- 	                <th data-options="field:'level'" width="20%" align="right">level</th>  -->
<!-- 	            </tr> -->
<!-- 	        </thead> -->
<!--     	</table> -->
<!-- 	</div> -->
	<div data-options="region:'center'" title="销售单列表">
		<div id="saleOrderBar" style="padding: 5px 0;">
			<shiro:hasPermission name="trans:sale_order:add">
				<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add-s1'" onclick="openSaleOrderDialog()">新增销售单</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="trans:sale_order:edit">
				<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit-s1'" onclick="editSaleOrderDialog()">编辑销售单</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="trans:sale_order:downQRCode">
				<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-codeqr'" onclick="displaySaleOrderQRcode()">二维码</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="trans:sale_order:delete">
				<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-delete-s1'" onclick="delSaleOrder();">删除销售单</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="trans:sale_order:list">
				<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="openSalOrder_electronTableDialog()">电子随附单</a>
				<a href="#" class="easyui-linkbutton" iconcls="icon-search" onclick="openSelectingDialog_saleOrder()">高级搜索</a>
			</shiro:hasPermission>
		</div>
		<!-- start of table list --> 
		<table id="saleOrderList" style="width:auto;height:350px" singleSelect="true" fitColumns="true"></table>
		<!-- end of table list -->
		<div id="searchDialog_saleOrder" class="easyui-dialog fm" title="高级搜索" 
		data-options="modal:true,closed:true,iconCls:'icon-search'"  
		style="width:590px;padding:20px" buttons="#searchDialog_buttons_saleOrder">
			<form id="frmSearch_saleOrder" method="post">
				<div class="ftitle">高级搜索信息</div>
				<div class="fitem">
		        	<label class="fitemlabel width_70">销售单号:</label>
		            <input id="s_saleOrderNo_saleOrder" class="easyui-textbox" style="width:150px;" data-options="prompt:'销售单号',validType:length[1,255]">
		       		<label class="fitemlabel width_70">物流企业:</label>
				    <input id="s_logisticsId_saleOrder"   style="width: 210px;"  >&nbsp;
		        </div>
		        <div class="fitem">
		        	<label class="fitemlabel width_70">下单客户:</label>
		            <input id="s_customerName_saleOrder" class="easyui-textbox" style="width:150px;" data-options="prompt:'下单客户',validType:length[1,255]">
		             <label class="fitemlabel width_70">商品名称:</label>
		            <input id="s_goodsName_saleOrder" class="easyui-textbox" style="width:210px;" data-options="prompt:'商品名称',validType:length[1,255]">
		        </div>
		 		<div class="fitem">
		            <label class="fitemlabel width_70">日期范围:</label>
					<select class="easyui-combobox"  data-options="editable:false," id="select_orderTime_saleOrder"  style="width:150px;">
					    <option value="all">全部</option>
		                <option value="day">今天</option>
		                <option value="week">最近一周</option>
		                <option value="month">最近一个月</option>
		                <option value="quarter">最近三个月</option>
		            </select>
		            
					 <label class="fitemlabel width_70">下单日期:</label>
		            <input id="s_orderTimeStart_saleOrder" class="easyui-datebox" style="width:100px;" data-options="prompt:'开始日期',validType:length[1,255]">-
		          	<input id="s_orderTimeEnd_saleOrder" class="easyui-datebox" style="width:100px;" data-options="prompt:'结束日期',validType:length[1,255]">
		       </div> 
		       <div class="fitem">
		            <label class="fitemlabel width_70">订单状态:</label>
		            <input id="s_status_saleOrder" style="width: 150px;">&nbsp;
		       </div> 
		       
			</form>
		</div>
		<div id="searchDialog_buttons_saleOrder"  style="text-align: center;">
			<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-search" onclick="searchform_saleOrder()" style="width:90px">搜索</a>
		    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" onclick="javascript:$('#frmSearch_saleOrder').form('clear')" style="width:90px">重置</a>
		</div>
		<!-- begin of adding dialog -->
		<div id="addSaleOrderDialog" style="width: 625px; padding: 10px;" class="easyui-dialog" data-options="modal:true,closed:true">
		<form id="saleOrderForm" method="post">
		
			<div   style="margin-left: 10px;">
				<input type="hidden" id="saleOrderId_saleOrder" name="saleOrderId" >
				 <!-- </br>
				 <div class="fitem">
				          <label class="fitemlabel width_70">物流企业：</label>
				          <input id="logisticsId_saleOrder" name="logisticsId"   style="width: 480px;"  >&nbsp;
				 </div>	 -->		 
				 <div class="fitem">
				          <label class="fitemlabel width_70">销售单号：</label>
				          <input class="easyui-textbox" id="saleOrderNo_saleOrder" name="saleOrderNo" style="width: 200px" data-options="required:true" />&nbsp;
				          <label class="fitemlabel width_70">下单客户：</label>
				          <input id="customerId_saleOrder" name="customerId" style="width: 200px;"  data-options="required:true"  >&nbsp;
				 </div>
				 <div class="fitem">
						  <label class="fitemlabel width_70">下单时间：</label>
				          <input type="text" id="orderTime_saleOrder" name="orderTime" style="width: 200px;">&nbsp;
				          <!-- <label class="fitemlabel width_70">订单状态：</label>
				          <input id="status_saleOrder" name="status" style="width: 150px;">&nbsp; -->
						  <!--  <label class="fitemlabel width_70">&nbsp;追&nbsp;溯&nbsp;码：</label>-->
				          <input type="hidden" id="traceCode_saleOrder" name="traceCode" style="width: 200px" data-options="required:true" />&nbsp;
				 </div>
				 
				 
			 </div>
			 
			 <div  style="margin-left: 20px;">
					<table id="saleOrderItemTable" class="easyui-datagrid" title="销售单明细" style="width:550px;height:265px"
				          data-options="
					         iconCls: 'icon-edit',
			                 singleSelect: true,
			                 toolbar: '#tb_saleOrder',
							 rownumbers : true,
							 pagination : true,
							 pageSize : 500,
							 pageList : [500],
							 loadMsg : '页面正在加载....',
							 height : 'auto',
				             url: 'sale_order/findAllItems',
				             onBeforeLoad: function(param){ 
								param.saleOrderId = $('#saleOrderId_saleOrder').val();
				             }, 
				            onClickCell: onClickCell_saleOrder,
				            onEndEdit: onEndEdit_saleOrder
				          ">
					<thead>
		
					<tr>
						<th data-options="field:'productId',width:175,
							formatter:function(value, row, index){
								return row.productName;
							},
							editor:{
								type:'combobox',
								options:{
									required:true,
									valueField:'productId',
				                    textField:'productName',
				                    method:'get',
				                    editable:true,
				                    url:'product/findProductInfoAreDetailed', 
				                    onBeforeLoad: function(param){ 
				                    	param.companyId = $('#index_user_companys').combobox('getValue');
				                    	param.productType = 1;
				                    }, 
				                    onSelect: function(data){ 
				                    	var selectedrow = $('#saleOrderItemTable').datagrid('getSelected');
				        				var rowIndex = $('#saleOrderItemTable').datagrid('getRowIndex', selectedrow);
				                    	var ed = $('#saleOrderItemTable').datagrid('getEditor', {index:rowIndex,field:'productStandardDetailId'});
				                    	$(ed.target).combobox('clear');
				                    	$(ed.target).combobox({
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
										var selectedrow = $('#saleOrderItemTable').datagrid('getSelected');
				        				var rowIndex = $('#saleOrderItemTable').datagrid('getRowIndex', selectedrow);
				                    	var ed = $('#saleOrderItemTable').datagrid('getEditor', {index:rowIndex,field:'productStandardDetailId'});
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
								}
							}
						">产品
						</th>
						<th data-options="field:'productStandardDetailId',width:140,
							formatter:function(value,row, index){
								return row.fullStandardName;
							},
							editor:{
								type:'combobox',
								options:{
									required:true,
									editable:false,
									valueField:'productStandardDetailId',
				                    textField:'fullStandardName',
				                    method:'get',
				                    
								}
							}
						">
							规格
						</th>
						<th data-options="field:'quantity',width:80,
							editor:{
								type:'numberbox',
								options:{
									precision:2,
									required:true
								}
							}">
							数量
						</th>
						<shiro:hasPermission name="trans:sale_order:salePrice">
									<th data-options="field:'salePrice',width:80,
										editor:{
											type:'numberbox',
											options:{
												precision:2,
												required:true
											}
										}">
										价格
									</th>
						</shiro:hasPermission>
						
					</tr>
					</thead>
					</table>
			 </div>	
			<div id="tb_saleOrder" style="height:auto">
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append_sale()">新增</a>
		    	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit_sale()">删除</a> 
			</div>
			<div style="margin: 10px 20px;">
				<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
					style="width: 550px; height: 32px;" onclick="submitSaleOrder()">确认提交</a>
			</div>
		</form>
		
		</div>
		<div id="SalOrder_electronTableDialog" class="tablebox easyui-dialog" data-options="modal:true,closed:true">
				<h1>酒类流通随附单</h1><div id="saleOrderNumCode" style="float: right;margin-right: 30px;margin-bottom: 10px;"></div>
				<ul>
					<li><span id="yearText" style="vertical-align: middle;"></span><label for="" class="datelist">年</label><span id="monthText" style="vertical-align: middle;"></span><label for="" class="datelist">月</label><span id="dayText" style="vertical-align: middle;"></span><label class="datelist" for="">日</label></li>
					<li id="saleOrderNum"></li>
				</ul>
		
				<div class="tableMain clearfix">
					<table width="100%" cellspacing=0 style="border-collapse:collapse">
					  <tr>
					    <td width="10%">购买单位：</td>
					    <td colspan="3" id="purchaseCustomer"></td>
					    <td width="11%">联系人：</td>
					    <td width="9%" id="purchaseContact"></td>
					    <td width="7%">电话：</td>
					    <td width="19%" id="customerPhone"></td>
					  </tr>
					  <tr id="productHeadtr">
					    <td align="center">品名</td>
					    <td width="7%" align="center">规格</td>
					    <td width="5%" align="center">单位</td>
					    <td width="5%" align="center">数量</td>
					    <td width="7%" align="center">单价（元）</td>
					    <td width="7%" align="center">金额（元）</td>
					    <td width="15%" align="center">产地</td>
					    <td width="11%" align="center">生产批号或生产日期</td>
					  </tr>
					  <tr>
					    <td>售货单位盖章：</td>
					    <td colspan="3"></td>
					    <td colspan="2">许可证号：</td>
					    <td colspan="2" id="saleBusinesslicense"></td>
					    </tr>
					  <tr>
					    <td>售货单位：</td>
					    <td colspan="3" id="saleCompany"></td>
					    <td colspan="2">电话传真：</td>
					    <td colspan="2" id="saleCompanyTel"></td>
					    </tr>
					</table>
		
					<div class="tableBar">
							<ul style="float:left">
								<li><span>发货人：</span><span id="consignor"></span></li>
								<li><span>承运人：</span><span id="carrier"></span></li>
								<li><span>车牌号：</span><span id="plateNumber"></span></li>
							</ul>
					</div>
			  </div>
		</div>
	</div>
</div>


<script type="text/javascript">
var initFlag_sale=0;
var editIndex_saleOrder = undefined;
$(function(){ 
	
	selectDate("select_orderTime_saleOrder","s_orderTimeStart_saleOrder","s_orderTimeEnd_saleOrder");
	
 	$("#s_status_saleOrder").combobox({ 
		url : 'commonVariable/getVariablesByGroup',
		valueField : 'varName',
		textField : 'varValue',
		editable:false,
		onBeforeLoad:function(param){
			param.varGroup='saleOrderStatus';
		}
 	}); 
 	$("#s_status_saleOrder").combobox('clear');
 	

 	
 	$("#s_logisticsId_saleOrder").combobox({ 
		url : 'logistics/findByUserCompanyId',
		valueField : 'logisticsId',
		textField : 'logisticsCompanyName',
		editable:false,
		onBeforeLoad:function(param){
			var companyId=$("#index_user_companys").combobox('getValue');
			param.companyId=companyId;
		}
 	}); 	
 	$("#s_logisticsId_saleOrder").combobox('clear');
 	
 	
	loadSaleOrders();
});

function openSalOrder_electronTableDialog(){
var row = $('#saleOrderList').datagrid('getSelected');
	if(row){
		$("#saleOrderNumCode").barcode(row.saleOrderNo, "code128",{ showHRI:true,output:'bmp'});
// 		$("#saleOrderNum").text(row.saleOrderNo);
		$("#purchaseCustomer").text(row.company.name);
		$("#purchaseContact").text(row.customers.contact);
		$("#customerPhone").text(row.customers.tel);
		$("#saleBusinesslicense").text(row.cliquorbusinesslicense);
		$("#saleCompanyTel").text(row.companyTel);
		$(".dataAppend").remove();
		var date=new Date();
		$("#yearText").text(date.getFullYear());
		$("#monthText").text(date.getMonth()+1);
		$("#dayText").text(date.getDate());
		var companyName=$('#index_user_companys').combobox('getText');
		$("#saleCompany").text(companyName);
		$("#consignor").text($("#index_userName").val());	//目前取当前用户名
		$("#carrier").text($("#index_userName").val());		//目前取当前用户名
		$("#plateNumber").text("沪A.QW677");		//目前写死
		$.post("sale_order/getAllItems?saleOrderId="+row.saleOrderId,function(data){
			for(var i=0;i<data.length;i++){
				var tr=$("<tr align='center' class='dataAppend'></tr>");
				tr.append("<td>"+data[i].product.productName+"</td>");
				tr.append("<td>"+data[i].fullStandardName+"</td>");
				tr.append("<td>桶</td>");	//目前写死
				tr.append("<td>"+data[i].quantity+"</td>");
				tr.append("<td>"+data[i].standardDetail.salePrice+"</td>");
				tr.append("<td>"+(data[i].standardDetail.salePrice*data[i].quantity)+"</td>");
				var catgName="";
				(data[i].catgName) ? catgName=data[i].catgName :catgName = "";
				tr.append("<td>"+catgName+"</td>");
				
				tr.append("<td>2016年03月12日</td>");	//目前写死
				$("#productHeadtr").after(tr);
			}
		});
// 		$("#electronTable").datagrid({
// 			url:"sale_order/findAllItems?saleOrderId="+row.saleOrderId,
// 			 fitColumns : true,
// 			   singleSelect : true,
// 			   loadMsg : '页面正在加载....',
// 			   height : 'auto',
// 	         columns:[[
// 				   {
// 	          	   field: 'productName', 
// 	          	   title: '产品', 
// 	          	   width:'40%',
// 	          	 resizable:false,
// 	                 formatter : function(val, row, index) {
// 							return row.product.productName;
// 					   }
// 	             },
// 	             {
// 	          	   field: 'quantity', 
// 	          	   title:'数量', 
// 	          	   width:'30%', 
// 	          	   align:'right',
// 	          	 resizable:false
// 	             }, 
// 	             {
// 	          	   field: 'fullStandardName', 
// 	          	   title:'规格', 
// 	          	   width: '30%', 
// 	          	   align:'right',
// 	          	   resizable:false
// 	             }
// 	         ]]
// 		});
		
		$('#SalOrder_electronTableDialog').dialog({
			closed : false,
			modal : true,
			title : "电子随附单",
			iconCls : 'icon-save',
			buttons : [ {
				text : '打印',
				iconCls : 'icon-print',
				handler : function(){
					$("#SalOrder_electronTableDialog").printArea();
				}
			}, {
				text : '取消',
				iconCls : 'icon-cancel',
				handler : function() {
					$('#SalOrder_electronTableDialog').dialog('close');
				}
			} ]
		});
		
	}else{
		$.messager.alert('信息提示', '请选择销售单！', 'info');
	}
	
}

function loadSaleOrders(){
	<shiro:hasPermission name="trans:sale_order:edit">
		 $('#saleOrderList').datagrid({
			onDblClickCell: function(index,field,value){
				editSaleOrderDialog();
			}
		 });
	</shiro:hasPermission>
	
	
	 $('#saleOrderList').datagrid({
		url : 'sale_order/findAllList',
		rownumbers : true,
		pageSize : 10,
		pageList : [ 10, 20, 50 ],
		pagination : true,
		singleSelect : true,
		multiSort : true,
		fitColumns : true,
		fit : true,
		toolbar : '#saleOrderBar',
		onBeforeLoad : function(param) {
			var myUserCompanyId = $('#index_user_companys').combobox('getValue');
			param.companyId = myUserCompanyId;
		},
		columns : [ [ 
			{
				field : 'customerId', 
				title : '客户ID', 
				hidden: true
			}, 
			{
				field : 'companyId', 
				title : '用户企业ID', 
				hidden: true
			},
			{ 
				field : 'saleOrderId', 
				title : '销售单ID', 
				hidden: true 
			},
			{ 
				field : 'logisticsId', 
				title : '物流企业ID', 
				hidden: true 
			},
			{ 
				field : 'saleOrderNo', 
				title : '销售单编号', 
				width : 100 
			},
			{ 
				field: 'name', 
				title : '客户',
				width : 100 ,
				formatter : function(val, row, index) {
					return row.company.name;
				}
			},
			/* { 
				field : 'logisticsCompanyName',
				title : '物流企业', 
				width : 100 
			}, */
			{ 
				field : 'orderTime', 
				title : '下单时间', 
				width : 100 
			},
			{ 
				field : 'statusName', 
				title : '订单状态',
				width : 100 
			}
			
		] ],
		view: detailview,
		detailFormatter:function(index,row){
               return '<div style="padding:2px"><table class="ddv"></table></div>';
           },
           onExpandRow: function(index,row){
               var ddv = $(this).datagrid('getRowDetail',index).find('table.ddv');
               ddv.datagrid({
                   url:'sale_order/findAllItems?saleOrderId='+row.saleOrderId,
	           	   fitColumns : true,
				   singleSelect : true,
				   rownumbers : true,
				   pagination : true,
				   pageSize : 10,
				   pageList : [ 10, 20, 30 ],
				   loadMsg : '页面正在加载....',
				   height : 'auto',
                   columns:[[
                       {
                    	   field: 'saleItemId',
                    	   title:'销售单ID',
                    	   hidden: true
                       },
                       {
                    	   field: 'saleOrderId', 
                    	   title:'明细ID', 
                    	   hidden: true
                       },
                       {
                    	   field: 'productId',
                    	   title:'产品ID', 
                    	   hidden: true
                       },
                       {
                    	   field: 'productStandardDetailId', 
                    	   title:'规格明细ID',
                    	   hidden: true
                       },
         			   {
                    	   field: 'productName', 
                    	   title: '产品', 
                    	   width:50,
	                       formatter : function(val, row, index) {
								return row.productName;
						   }
                       },
                       {
                    	   field: 'quantity', 
                    	   title:'数量', 
                    	   width:20, 
                    	   align:'right'
                       }, 
                       {
                    	   field: 'fullStandardName', 
                    	   title:'规格', 
                    	   width: 20, 
                    	   align:'right'
                       }
                   ]],
                   onResize:function(){
                       $('#saleOrderList').datagrid('fixDetailRowHeight',index);
                   },
                   onLoadSuccess:function(){
                       setTimeout(function(){
                           $('#saleOrderList').datagrid('fixDetailRowHeight',index);
                       },0);
                   }
               });
               $('#saleOrderList').datagrid('fixDetailRowHeight',index);
           }
	 });
}
 
/**
 * function => UI => initialize the dialog for adding
 */
function initDialog_saleOrder(){
	 
	 
	
 	$("#logisticsId_saleOrder").combobox({ 
		url : 'logistics/findByUserCompanyId',
		valueField : 'logisticsId',
		textField : 'logisticsCompanyName',
		editable:false,
		required:true,
		onBeforeLoad:function(param){
			var companyId=$("#index_user_companys").combobox('getValue');
			param.companyId=companyId;
		}
 	}); 		 
 	$("#customerId_saleOrder").combobox({ 
		url : 'customers/findCustomerList',
		valueField : 'customerId',
		textField : 'customerAlias',
		editable:false,
		onBeforeLoad : function(param) {
			param.companyId = $("#index_user_companys").combobox('getValue'); 
		}
 	});
 	
 	$("#status_saleOrder").combobox({ 
		url : 'commonVariable/getVariablesByGroup',
		valueField : 'varName',
		textField : 'varValue',
		required:true,
		editable:false,
		onBeforeLoad:function(param){
			param.varGroup='saleOrderStatus';
		}
 	}); 	 
	$("#orderTime_saleOrder").datetimebox({
		showSeconds: true,
		editable:false
	}); 
}
 

function removeit_sale(){
    var row = $('#saleOrderItemTable').datagrid('getSelected');
    if (row == undefined ||editIndex_saleOrder == undefined) {
			return;
	}
    if (row.saleItemId == undefined) {
	    editIndex_saleOrder == undefined
		$('#saleOrderItemTable').datagrid('cancelEdit',editIndex_saleOrder).datagrid('deleteRow',editIndex_saleOrder);
		return;
	}
	if (row) {
		$.messager.confirm('信息提示', '确定要删除这条信息吗？', function(result) {
			if (result) {
				$.post("sale_order/delSaleOrderItems", {
					saleItemId : row.saleItemId
				}, function(data) {
					var msg = eval('(' + data + ')');
					$.messager.alert('信息提示', msg.msg, 'info');
					if (msg.code == 1) {
						$('#saleOrderItemTable').datagrid('cancelEdit',editIndex_saleOrder).datagrid('deleteRow',editIndex_saleOrder);
						editIndex_saleOrder = undefined;
						$('#saleOrderList').datagrid('reload');

					}
				});

			}
		});
	} else {
		$.messager.alert('信息提示', '亲,请选择一行信息！', 'info');
	}
}
 
function clearItemsDataGrid(){
	var rows = $('#saleOrderItemTable').datagrid('getRows');
	for(var i = rows.length - 1; i> -1; i--){
		$('#saleOrderItemTable').datagrid('deleteRow', i);
	} 
}



function endEditing_sale(){
    if (editIndex_saleOrder == undefined){return true}
    if ($('#saleOrderItemTable').datagrid('validateRow', editIndex_saleOrder)){
        $('#saleOrderItemTable').datagrid('endEdit', editIndex_saleOrder);
        editIndex_saleOrder = undefined;
        return true;
    } else {
        return false;
    }
}
 
/**
 * function => action => submit the sale order
 */
function submitSaleOrder(){
	endEditing_sale();
	//获取更新更改的行的集合  
    row = $("#saleOrderItemTable").datagrid('getRows'); 
	var myUserCompanyId = $('#index_user_companys').combobox('getValue');
	$('#saleOrderForm').form('submit', {
		url : 'sale_order/addOrUpdate', 
		onSubmit : function(param) {
			param.companyId = myUserCompanyId;
			param.saleItemList = JSON.stringify(row);
			return $(this).form('enableValidation').form('validate');
		},
		success : function(data) {
			var msg = eval('(' + data + ')');
			$.messager.alert('信息提示', msg.msg, 'info');
			if (msg.code == 1) {
				$('#addSaleOrderDialog').dialog('close');
				$('#saleOrderList').datagrid('reload');
			}
		}
	});
} 
 
function myformatter(date){
	var y = date.getFullYear();
	var m = date.getMonth()+1;
	var d = date.getDate();
	return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
}

function myparser(s){
	if (!s) return new Date();
	var ss = (s.split('-'));
	var y = parseInt(ss[0],10);
	var m = parseInt(ss[1],10);
	var d = parseInt(ss[2],10);
	if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
		return new Date(y,m-1,d);
	} else {
		return new Date();
	}
} 
// ***** end of function *****

// ***** begin of event *****
/**
 * event => cell onClick 
 */
function onClickCell_saleOrder(index, field){

     
     
    if (editIndex_saleOrder != index){
        if (endEditing_sale()){
			$('#saleOrderItemTable').datagrid('selectRow', index).datagrid('beginEdit', index);
			var productId = $('#saleOrderItemTable').datagrid('getEditor', {index : index,field : 'productId'});
			var productStandardDetailId = $('#saleOrderItemTable').datagrid('getEditor', {index : index,field : 'productStandardDetailId'});
			var ed = $('#saleOrderItemTable').datagrid('getEditor', {index : index,	field : field});
			
			var selectedrow = $('#saleOrderItemTable').datagrid('getSelected');
			
			if ( productId  && productStandardDetailId) {
				var vproductId = $(productId.target).combobox('getValue');
				$(productStandardDetailId.target).combobox({
					url:'product_standard_detail/list', 
                    onBeforeLoad: function(param){ 
                    	param.productId = vproductId;
                    },
                    onLoadSuccess : function(data) {
							$(this).combobox('select',selectedrow.productStandardDetailId);
					}
				});
				
				
			}
			if (ed) {
					($(ed.target).data('textbox') ? $(ed.target).textbox('textbox') : $(ed.target)).focus();
					editIndex_saleOrder = index;
					return;
			}
			editIndex_saleOrder = index;
        } else {
            setTimeout(function(){
                $('#saleOrderItemTable').datagrid('selectRow', editIndex_saleOrder);
            },0);
        }
    }
    
   
    
}

function onEndEdit_saleOrder(index, row){
    var ed = $(this).datagrid('getEditor', {
        index: index,
        field: 'productId'
    });
    row.productName = $(ed.target).combobox('getText');
    
    var ed1 = $(this).datagrid('getEditor', {
        index: index,
        field: 'productStandardDetailId'
    });
    row.fullStandardName = $(ed1.target).combobox('getText');
} 



function append_sale(){
	var myCustomerID = $('#customerId_saleOrder').combobox('getValue');
    if (endEditing_sale()){
        $('#saleOrderItemTable').datagrid('appendRow',{quantity:'1.00'});
        editIndex_saleOrder = $('#saleOrderItemTable').datagrid('getRows').length-1;
        $('#saleOrderItemTable').datagrid('selectRow', editIndex_saleOrder)
                .datagrid('beginEdit', editIndex_saleOrder);
    }
} 

function openSaleOrderDialog() {
	 
	if(initFlag_sale==0){
		initDialog_saleOrder();
		initFlag_sale=1;
	}
	$("#saleOrderForm").form('clear').form('disableValidation');
	clearItemsDataGrid();
	var time = new Date().format("yyyy-MM-dd hh:mm:ss"); 			
    $('#orderTime_saleOrder').datetimebox('setValue', time);
    $.post("sale_order/getSaleOrderNo",function(data){
    	 $('#saleOrderNo_saleOrder').textbox('setValue', data);
    	 $('#traceCode_saleOrder').val(data);
    });
    $("#logisticsId_saleOrder").combobox({
    	onLoadSuccess: function(data){
    		if(data.length >0){
    			$(this).combobox('select',data[0].logisticsId);
    		}
   		}
    });
    $("#status_saleOrder").combobox({
    	onLoadSuccess: function(data){
    		if(data.length >0){
    			$(this).combobox('select',data[0].varName);
    		}
   		}
    });
	$('#addSaleOrderDialog').dialog({
		closed : false,
		modal : true,
		title : "新增销售单",
		iconCls : 'icon-document'
	});
}  

/**
 * button menu => edit the selected sale order
 */
function editSaleOrderDialog(){
	var row = $('#saleOrderList').datagrid('getSelected'); 
	
	if(row){
		$('#saleOrderForm').form('clear').form('disableValidation');  
		
		editIndex_saleOrder = undefined;
		
		if(initFlag_sale==0){
			// call: initialize the dialog for adding
			initDialog_saleOrder();
			initFlag_sale=1;
		}
		//解决添加数据后修改会出现分页为空 默认设置分页为5页 
		$('#saleOrderItemTable').datagrid({pageSize : 500});
		$('#saleOrderForm').form('load', {
			saleOrderId : row.saleOrderId,
			saleOrderNo : row.saleOrderNo,
			customerId : row.customerId,
			orderTime : row.orderTime,
			traceCode : row.traceCode,
			logisticsId : row.logisticsId,
			status : row.status
		}); 
		$("#status_saleOrder").combobox("select",row.status); 
		
		// 销售单号不可编辑
		$("#saleOrderNo_saleOrder").textbox('readonly',true);
		
		$('#saleOrderItemTable').datagrid('reload');    // reload the current page data 
		
		$('#addSaleOrderDialog').dialog({
			closed : false,
			modal : true,
			title : "编辑销售单",
			iconCls : 'icon-document'
		});
		 
	} else {
		$.messager.alert('信息提示', '请选择一行记录！', 'info');
	}
} 
 
/**
 * button menu => display the QR code
 */ 
function displaySaleOrderQRcode(){
	// alert('display the qr code');
	
	var row = $('#saleOrderList').datagrid('getSelected'); 
	if(row){
		// console.log(window.open); 
		// window.open("qrservlet?qrtext="+row.saleOrderNo);//location.href实现客户端页面的跳转 
		// saleOrderId
		window.open("sale_order/downloadqc?saleorderid="+row.saleOrderId);
	}  
} 
 

/**
 * button menu => delete the sale order
 */
function delSaleOrder(){
	var row = $('#saleOrderList').datagrid('getSelected');
	if (row) {
		$.messager.confirm('删除销售单信息', '确认删除?', function(r) {
			if (r) {
				$.post("sale_order/delSaleOrder",
					{ saleOrderId : row.saleOrderId },
					function(data){
						var msg = eval('(' + data + ')');
						$.messager.alert('信息提示', msg.msg, 'info');
						if (msg.code == 1) {
							$('#saleOrderList').datagrid('reload');
						}
				});
			}
		});
	} else {
		$.messager.alert('信息提示', '请选择一行记录！', 'info');
	}	 
} 
 
	 // open the dialog for selecting
	 function openSelectingDialog_saleOrder(){  
	 	$('#searchDialog_saleOrder').dialog('open');
	 }
     // ***** end of button menu ***** 
    // advanced query
    function searchform_saleOrder(){
    	$('#saleOrderList').datagrid({  
		    url:'sale_order/findAllList',  
		    queryParams:{
		    	saleOrderNo: $("#s_saleOrderNo_saleOrder").val(),
		    	orderTimeStart: $("#s_orderTimeStart_saleOrder").datebox("getValue"),
		    	orderTimeEnd: $("#s_orderTimeEnd_saleOrder").datebox("getValue"),
	        	'customerName': $("#s_customerName_saleOrder").val(),
	        	productName: $("#s_goodsName_saleOrder").val(),
	        	status:$("#s_status_saleOrder").combobox('getValue'),
	        	logisticsId:$("#s_logisticsId_saleOrder").combobox('getValue'),
	        	companyId: $("#index_user_companys").combobox('getValue'),
		    } 
		});
    	$('#searchDialog_saleOrder').dialog('close');
    }

    // query by customerID; added by jie.jia at 2016-07-06 17:28
    function loadSaleOrdersByCategory(customerId) {
		 // console.info(customerId);
		 var mineCompanyId = $("#index_user_companys").combobox('getValue');
		 
		 if(mineCompanyId === customerId){ 
			 
			 $('#saleOrderList').datagrid({  
				    url:'sale_order/findAllList',  
				    queryParams:{  
			        	companyId: $("#index_user_companys").combobox('getValue'),
				    } 
				}); 
		 } else { 
			 
			 $('#saleOrderList').datagrid({  
				    url:'sale_order/findAllList?customerId='+customerId,  
				    queryParams:{  
			        	companyId: $("#index_user_companys").combobox('getValue'),
				    } 
				}); 
		 }
		 
		 
	}  
</script>