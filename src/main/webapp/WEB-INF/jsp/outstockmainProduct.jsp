<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<link rel="stylesheet" type="text/css" href="static/css/datagrid_util.css">
<div id="OutstockMainProductBtns" style="padding: 5px 0;">
	<shiro:hasPermission name="trans:outstockmainProduct:add">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-add-s1'" onclick="openOutstockMainProduct()">新增产品出库</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="trans:outstockmainProduct:edit">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit-s1'" onclick="editOutstockMainProduct()">编辑产品出库</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="trans:outstockmainProduct:delete">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-delete-s1'" onclick="removeOutstockMainProduct()">删除产品出库</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="trans:outstockmainProduct:list">
		<a href="#" class="easyui-linkbutton" iconcls="icon-search" onclick="openSelectingDialog_outstockmainProduct()">高级搜索</a>
	</shiro:hasPermission>
</div>
<div id="searchDialog_outstockmainProduct" class="easyui-dialog fm" title="高级搜索" 
data-options="modal:true,closed:true,iconCls:'icon-search'"  
style="width:680px;padding:20px" buttons="#searchDialog_buttons_saleOrder">
	<form id="frmSearch_outstockmainProduct" method="post">
		<div class="ftitle">高级搜索信息</div>
		<div class="fitem">
        	<label class="fitemlabel width_70">出库单号:</label>
            <input id="s_outstockNum_outstockmainProduct" class="easyui-textbox" style="width:150px;" data-options="prompt:'出库单号',validType:length[1,255]">
       		<label class="fitemlabel width_70">出库批次:</label>
		    <input id="s_outstockBatchNum_outstockmainProduct" class="easyui-textbox" style="width:310px;" data-options="prompt:'出库批次',validType:length[1,255]">
        </div>
 		<div class="fitem">
 		    <label class="fitemlabel width_70">登记人:</label>
            <input id="s_registrant_outstockmainProduct" class="easyui-textbox" style="width:150px;" data-options="prompt:'登记人',validType:length[1,255]">
            <label class="fitemlabel width_70">登记日期:</label>
           	<select class="easyui-combobox"  data-options="editable:false," id="select_registDate_outstockmainProduct"  style="width:100px;">
			    <option value="all">全部</option>
                <option value="day">今天</option>
                <option value="week">最近一周</option>
                <option value="month">最近一个月</option>
                <option value="quarter">最近三个月</option>
           	</select>
			<input id="s_registDateStart_outstockmainProduct" class="easyui-datebox" style="width:100px;" data-options="prompt:'开始日期',validType:length[1,255]">-
          	<input id="s_registDateEnd_outstockmainProduct" class="easyui-datebox" style="width:100px;" data-options="prompt:'结束日期',validType:length[1,255]">
       </div>
  	   <div class="fitem">
 		    <label class="fitemlabel width_70">收货人:</label>
            <input id="s_consignor_outstockmainProduct" class="easyui-textbox" style="width:150px;" data-options="prompt:'收货人',validType:length[1,255]">
            <label class="fitemlabel width_70">出库日期:</label>
           	<select class="easyui-combobox"  data-options="editable:false," id="select_outstockDate_outstockmainProduct"  style="width:100px;">
			    <option value="all">全部</option>
                <option value="day">今天</option>
                <option value="week">最近一周</option>
                <option value="month">最近一个月</option>
                <option value="quarter">最近三个月</option>
           	</select>
			<input id="s_outstockDateStart_outstockmainProduct" class="easyui-datebox" style="width:100px;" data-options="prompt:'开始日期',validType:length[1,255]">-
          	<input id="s_outstockDateEnd_outstockmainProduct" class="easyui-datebox" style="width:100px;" data-options="prompt:'结束日期',validType:length[1,255]">
       </div>             
       <div class="fitem">
            <label class="fitemlabel width_70">商品名称:</label>
             	<input id="s_goodsName_outstockmainProduct" class="easyui-textbox" style="width:150px;" data-options="prompt:'商品名称',validType:length[1,255]">
          	<label class="fitemlabel width_70">物流企业:</label>
            	<input id="s_logisticsId_outstockmainProduct"   style="width: 310px;"  >&nbsp;
        </div>
	</form>
</div>
<div id="searchDialog_buttons_saleOrder"  style="text-align: center;">
	<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-search" onclick="searchform_outstockmainProduct()" style="width:90px">搜索</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" onclick="javascript:$('#frmSearch_outstockmainProduct').form('clear')" style="width:90px">重置</a>
</div>


<table id="outstockMainProductList" title="产品出库信息列表" style="width: 100%;"></table>
<div id="OutstockMainProductDialog" data-options="modal:true,closed:true,iconCls:'icon-document'"
	style="width: 540px; padding: 10px;">
	<form id="OutstockMainProductForm" method="post">
		<div   style="margin-left: 10px;">
			 <input type="hidden" name="outstockMainId"  id="outstockMainId_OutstockMainProduct"/>
			 </br>
			 <div class="fitem">
			          <label class="fitemlabel width_70">销售单号：</label>
			          <select id="saleOrderId_OutstockMainProduct" name="saleOrderId" style="width:400px;"></select>&nbsp;
			 </div>
			 <div class="fitem">
			          <label class="fitemlabel width_70">物流企业：</label>
			          <input id="logisticsId_outstockMainProduct" name="logisticsId" style="width:400px;"  >&nbsp;
			 </div>
			 <div class="fitem">
			          <label class="fitemlabel width_70">出库单号：</label>
			          <input class="easyui-textbox" name="outstockNum" id="outstockNum_OutstockMainProduct"
							data-options="prompt:'出库单号',validType:'name',required:true" style="width:160px;">&nbsp;
					  <label class="fitemlabel width_70">出库批次：</label>
			          <input class="easyui-textbox" name="outstockBatchNum" id="outstockBatchNum_OutstockMainProduct"
							data-options="prompt:'出库批次号',validType:'name',required:true" style="width:160px;">&nbsp;		
			 </div>
			 <div class="fitem">
			          <label class="fitemlabel width_70">&nbsp;登 记 人：</label>
			          <input class="easyui-textbox" name="registrant"  id="registrant_OutstockMainProduct"
							data-options="prompt:'登记人',validType:'name',required:true" style="width:160px;">&nbsp;
					  <label class="fitemlabel width_70">登记日期：</label>
			          <input class="easyui-datebox" name="registDate"  id="registDate_OutstockMainProduct"
							data-options="prompt:'登记日期',validType:'name',required:true" style="width:160px;">&nbsp;		
			          
			 </div>	 	 
			 <div class="fitem" >
			          <label class="fitemlabel width_70">&nbsp;发货 人：</label>
			          <input class="easyui-textbox" name="consignor" id="consignor_OutstockMainProduct"
							data-options="prompt:'发货人',validType:'name',required:true" style="width:160px;">&nbsp;
					  <label class="fitemlabel width_70">出库日期：</label>
			          <input class="easyui-datebox" name="outstockDate"  id="outstockDate_OutstockMainProduct"
							data-options="prompt:'出库日期',validType:'name',required:true" style="width:160px;">&nbsp;		
			 </div>
		</div>
		<div  style="margin-left: 20px;">
			<table id="OutstockProductItemsgrid" class="easyui-datagrid" title="产品出库明细列表" style="width:470px;height:265px">
			</table>
		</div>
		<div id="tb_outstockProduct" style="height:auto">
			<div id="tb_outstockProductDiv">
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append_outstockProduct()">新增</a>
		    	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="remove_outstockProduct()">删除</a> 
	    	</div>
		</div>
		<div style="margin: 10px 20px;">
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
				style="width: 470px; height: 32px"
				onclick="submitOutstockMainProductForm()">确认提交</a>
		</div>
	</form>
</div>
<script type="text/javascript">
	var editIndex_OutstockMainProduct = undefined;
	$(function(){ 
	 	$("#s_logisticsId_outstockmainProduct").combobox({ 
			url : 'logistics/findByUserCompanyId',
			valueField : 'logisticsId',
			textField : 'logisticsCompanyName',
			editable:false,
			onBeforeLoad:function(param){
				var companyId=$("#index_user_companys").combobox('getValue');
				param.companyId=companyId;
			}
	 	}); 	
	 	$("#s_logisticsId_outstockmainProduct").combobox('clear');
	 	selectDate("select_registDate_outstockmainProduct","s_registDateStart_outstockmainProduct","s_registDateEnd_outstockmainProduct");
	 	selectDate("select_outstockDate_outstockmainProduct","s_outstockDateStart_outstockmainProduct","s_outstockDateEnd_outstockmainProduct");
		loadOutstockMainProduct();
	});
	

	function remove_outstockProduct(){
	    var row = $('#OutstockProductItemsgrid').datagrid('getSelected');
	    if (row == undefined ||editIndex_OutstockMainProduct == undefined) {
			return;
		}
    	editIndex_OutstockMainProduct == undefined
		$('#OutstockProductItemsgrid').datagrid('cancelEdit',editIndex_OutstockMainProduct)
		.datagrid('deleteRow',editIndex_OutstockMainProduct);
		return;
		
	}
	
	function append_outstockProduct(){
	    if (endEditing_OutstockItemProduct()){
	        $('#OutstockProductItemsgrid').datagrid('appendRow',{quantity:'1.00'});
	        editIndex_OutstockMainProduct = $('#OutstockProductItemsgrid').datagrid('getRows').length-1;
	        $('#OutstockProductItemsgrid').datagrid('selectRow', editIndex_OutstockMainProduct)
	        .datagrid('beginEdit', editIndex_OutstockMainProduct);
	    }
	} 
	
	//清空gridview入库详细
	function delOutstockProductItemsRow() {
		var rows = $('#OutstockProductItemsgrid').datagrid('getRows');
		for (var i = rows.length - 1; i > -1; i--) {
			$('#OutstockProductItemsgrid').datagrid('cancelEdit', i).datagrid('deleteRow', i);
		}
	}
	//加载数据
	function loadOutstockMainProductelectData() {
		$("#saleOrderId_OutstockMainProduct").combogrid({
			panelWidth : 440,
			url : 'sale_order/findByStatus?companyId='
				+ $("#index_user_companys").combobox('getValue')
				+'&status=0',
			method : 'post',
			multiple : false,
			required : true,
			disabled:false,
			prompt : '销售单',
			idField : 'saleOrderId',
			textField : 'saleOrderNo',
			fitColumns : true,
			editable : false,
			columns : [ [ 
			{
				field : 'customerName',
				title : '客户名称',
				width : '35%'
			},             
			{
				field : 'orderTime',
				title : '订单时间',
				width : '30%'
			}, {
				field : 'saleOrderNo',
				title : '销售单编号',
				width : '25%'
			} ] ],
			onSelect:function(data){
				loadSaleOrderItemsInfo();
			}
		});
		$("#logisticsId_outstockMainProduct").combobox({  
			url : 'logistics/findByUserCompanyId',
			valueField : 'logisticsId',
			textField : 'logisticsCompanyName',
			editable : true,
			onBeforeLoad:function(param){
				var companyId=$("#index_user_companys").combobox('getValue');
				param.companyId=companyId;
			}
	 	}); 	
	}
	
	/*读取销售单号的明细*/
	function loadSaleOrderItemsInfo(){
		$('#OutstockProductItemsgrid').datagrid({
			   url:'sale_order/findAllOutstockItems',
			   onBeforeLoad : function(param) {
				    param.saleOrderId = $("#saleOrderId_OutstockMainProduct").combo('getValues').join(",");
			   },
			   onLoadSuccess: function(data){
				   editIndex_OutstockMainProduct = undefined;
			   },
		}); 
	}
	//编辑结束
	function endEditing_OutstockItemProduct() {
		if (editIndex_OutstockMainProduct == undefined) {return true}
		if ($('#OutstockProductItemsgrid').datagrid('validateRow',editIndex_OutstockMainProduct)) {
			$('#OutstockProductItemsgrid').datagrid('endEdit', editIndex_OutstockMainProduct);
			editIndex_OutstockMainProduct = undefined;
			return true;
		} else {
			return false;
		}
	}
	function onClickCell_OutstockMainProduct(index, field) {
		
		 if (editIndex_OutstockMainProduct != index){
		        if (endEditing_OutstockItemProduct()){
					$('#OutstockProductItemsgrid').datagrid('selectRow', index).datagrid('beginEdit', index);
					var productId = $('#OutstockProductItemsgrid').datagrid('getEditor', {index : index,field : 'productId'});
					var productStandardDetailId = $('#OutstockProductItemsgrid').datagrid('getEditor', {index : index,field : 'productStandardDetailId'});
					var ed = $('#OutstockProductItemsgrid').datagrid('getEditor', {index : index,	field : field});
					var selectedrow = $('#OutstockProductItemsgrid').datagrid('getSelected');
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
							editIndex_OutstockMainProduct = index;
							return;
					}
					editIndex_OutstockMainProduct = index;
		        } else {
		            setTimeout(function(){
		                $('#OutstockProductItemsgrid').datagrid('selectRow', editIndex_OutstockMainProduct);
		            },0);
		        }
		 }
	}
	//编辑结束
	function onEndEdit_OutstockMainProduct(index, row) {
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
	//编辑信息
	function editOutstockMainProduct() {
		
		$('#tb_outstockProductDiv').css("display","none");
		var row = $('#outstockMainProductList').datagrid('getSelected');
		if (row) {
			createToken();//创建token
			$('#OutstockMainProductForm').form('clear').form('disableValidation');
			editIndex_OutstockMainProduct = undefined;
			loadOutstockMainProductelectData();//加载销售单号
			$('#OutstockMainProductForm').form('load', {
				outstockMainId : row.outstockMainId,
				registrant : row.registrant,
				consignor : row.consignor,
				registDate : row.registDate,
				outstockDate : row.outstockDate,
				//remark:row.remark,
				outstockBatchNum : row.outstockBatchNum,
				outstockNum : row.outstockNum
			});
			$("#saleOrderId_OutstockMainProduct").combobox({disabled:true});		
			$("#saleOrderId_OutstockMainProduct").combogrid("setValues",row.saleOrderId.split(','));
			$("#logisticsId_outstockMainProduct").combobox("setValues",row.logisticsId.split(',')); 
			//加载商品入库详细数据
			$('#OutstockProductItemsgrid').datagrid({
			    url:'outstockitem/findAllProductItems',
			    pageSize : 500,
				pageList : [ 500],
	            //iconCls: 'icon-edit',
	            fitColumns : true,
			    singleSelect : true,
			    rownumbers : true,
			    pagination : true,
			    toolbar: '#tb_outstockProduct',
			    loadMsg : '页面正在加载....',
			    onBeforeLoad : function(param) {
				   param.outstockMainId = row.outstockMainId;
			    },
			    onLoadSuccess: function(data){
			    	editIndex_OutstockMainProduct = undefined;
			    },
			    columns : [ [
							{
								field : 'productName',
								title : '产品名称',
								width : '50%',
							},
							{
								field : 'fullStandardName',
								title : '产品规格',
								width : '25%',
							},
							{
								field : 'outstockNum',
								title : '出库数量',
								width : '15%',
							}
				]],			
			});
			$('#OutstockProductItemsgrid').datagrid('reload');
			$('#OutstockMainProductDialog').dialog({
				closed : false,
				modal : true,
				title : "编辑产品出库单",
				iconCls : 'icon-document'
			});

		} else {
			$.messager.alert('信息提示', '请选择一行记录！', 'info');
		}
	}
	//打开新增信息
	function openOutstockMainProduct() {
		createToken();//创建token
		delOutstockProductItemsRow();//清空gridview出库详细
		loadOutstockMainProductelectData();//加载销售单号
		var time = new Date().format("yyyy-MM-dd");
		$('#OutstockMainProductForm').form('clear').form('disableValidation');
		$("#outstockNum_OutstockMainProduct").textbox('setValue',getPurchaseOrderNo());//出库单号
		$("#outstockBatchNum_OutstockMainProduct").textbox('setValue',getPurchaseOrderNo());//出库批次
		$("#registrant_OutstockMainProduct").textbox('setValue',$("#index_userName").val());//登 记 人
		$("#consignor_OutstockMainProduct").textbox('setValue',$("#index_userName").val());//发 货 人
		$("#outstockDate_OutstockMainProduct").textbox('setValue',time);//出库日期
		$("#registDate_OutstockMainProduct").textbox('setValue',time);//登记日期
	    $("#logisticsId_outstockMainProduct").combobox({
	    	onLoadSuccess: function(data){
	    		if(data.length >0){
	    			$(this).combobox('select',data[0].logisticsId);
	    		}
	   		}
	    });
		console.info($('#tb_outstockProductDiv').css("display"));
			$('#tb_outstockProductDiv').css("display","block");
		console.info($('#tb_outstockProductDiv').css("display"));
		
		//解决添加数据后修改会出现分页为空 默认设置分页为5页 
		$('#OutstockProductItemsgrid').datagrid({
			pageSize : 500,
			pageList : [ 500],
			url:'',
            //iconCls: 'icon-add',
            toolbar: '#tb_outstockProduct',
            fitColumns : true,
		    singleSelect : true,
		    rownumbers : true,
		    pagination : true,
		    loadMsg : '页面正在加载....',
		    onClickCell: onClickCell_OutstockMainProduct,
            onEndEdit: onEndEdit_OutstockMainProduct,
		    onLoadSuccess: function(data){
		    	editIndex_OutstockMainProduct = undefined;
		    },
		    columns : [ [
						{
							field : 'productId',
							title : '产品名称',
							formatter:function(value, row, index){
								return row.productName;
							},
							width:190,
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
				                    }, 
				                    onSelect: function(data){ 
				                    	var selectedrow = $('#OutstockProductItemsgrid').datagrid('getSelected');
				        				var rowIndex = $('#OutstockProductItemsgrid').datagrid('getRowIndex', selectedrow);
				                    	var ed = $('#OutstockProductItemsgrid').datagrid('getEditor', {index:rowIndex,field:'productStandardDetailId'});
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
										var selectedrow = $('#OutstockProductItemsgrid').datagrid('getSelected');
				        				var rowIndex = $('#OutstockProductItemsgrid').datagrid('getRowIndex', selectedrow);
				                    	var ed = $('#OutstockProductItemsgrid').datagrid('getEditor', {index:rowIndex,field:'productStandardDetailId'});
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
						},
						{
							field : 'productStandardDetailId',
							title : '产品规格',
							width:100,
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
						},
						{
							field : 'quantity',
							title : '采购数量',
							width:80
						},
						{
							field : 'outstockNum',
							title : '出库数量',
							width:80,
					       	editor:{
					       		type:'numberbox',
					       		options:{
					       			required:true
					       		}
					       	}
						}
			]],
		
		});

		$('#OutstockMainProductDialog').dialog({
			closed : false,
			modal : true,
			title : "新增产品出库单",
			iconCls : 'icon-document'
		});
	}
	
	function submitOutstockMainProductForm(){
 		if(!$('#OutstockMainProductForm').form('enableValidation').form('validate')){
 			$.messager.alert('信息提示', '亲,您输入的数据不全哦！', 'info');
 			return;
 		}
		//获取更新更改的行的集合  
		endEditing_OutstockItemProduct();
	    row = $("#OutstockProductItemsgrid").datagrid('getRows'); 
		$('#OutstockMainProductForm').form('submit', {
		    
			url : 'outstockmainProduct/save?token='+$('#index_token').val(), 
			onSubmit : function(param) {
				param.companyId = $('#index_user_companys').combobox('getValue');
				param.outstockItems = JSON.stringify(row);
				param.outstockType = "1";
				return $(this).form('enableValidation').form('validate');
			},
			success : function(data) {
				var msg = eval('(' + data + ')');
				$.messager.alert('信息提示', msg.msg, 'info');
				if (msg.code == 1) {
					$('#OutstockMainProductDialog').window('close');
				   $('#outstockMainProductList').datagrid('reload');
				}else{
					//创建token
					createToken();
					
				}
			}
		});

	}
	
	function loadOutstockMainProduct(){
		<shiro:hasPermission name="trans:outstockmainProduct:edit">
			 $('#outstockMainProductList').datagrid({
					onDblClickCell: function(index,field,value){
						editOutstockMainProduct()
					}
			 });
		</shiro:hasPermission>
		$('#outstockMainProductList').datagrid({
			url : 'outstockmainProduct/findAllList',
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
			toolbar : '#OutstockMainProductBtns',
			columns : [ [ 
			{
				field : 'outstockNum',
				title : '出库单号',
				width : "15%"
			}, {
				field : 'outstockBatchNum',
				title : '出库批次号',
				width : "15%"
			},{
				field : 'logisticsAlias',
				title : '物流企业',
				width : "25%"
			},{
				field : 'registrant',
				title : '登记人',
				width : "8%"
			}, {
				field : 'registDate',
				title : '登记日期',
				width : "12%",
				formatter : function(value, row) {
					return row.registDate.substring(0, 10);
				}
			},{
				field : 'consignor',
				title : '收货 人',
				width : "8%",
				//hidden : true
			},  {
				field : 'outstockDate',
				title : '出库日期',
				width : "12%",
				formatter : function(value, row) {
					return row.outstockDate.substring(0, 10);
				}
			}, {
				field : 'outstockType',
				title : '出库单类型',
				hidden : true
			} ] ],
			view: detailview,
			detailFormatter:function(index,row){
	               return '<div style="padding:2px"><table class="ddv"></table></div>';
	           },
	           onExpandRow: function(index,row){
	               var ddv = $(this).datagrid('getRowDetail',index).find('table.ddv');
	               ddv.datagrid({
	            	   url : 'outstockitem/findAllProductItems',
		           	   fitColumns : true,
					   singleSelect : true,
					   rownumbers : true,
					   pagination : true,
					   pageSize : 10,
					   pageList : [ 10, 20, 30 ],
					   loadMsg : '页面正在加载....',
					   height : 'auto',
					   onBeforeLoad : function(param) {
							param.outstockMainId = row.outstockMainId;
					   },
					   columns : [ [
						{
							field : 'productName',
							title : '产品名称',
							width : '20%'
						},{
							field : 'fullStandardName',
							title : '规格',
							width : '12%'
						}, {
							field : 'outstockNum',
							title : '出库数量',
							width : '10%'
						}, {
							field : 'createTime',
							title : '创建时间',
							width : '14%',
							formatter : function(value, row) {
								return convertTimeStamp(row.createTime);
							}
						}, {
							field : 'createBy',
							title : '创建人',
							width : '14%'
						}, {
							field : 'updateTime',
							title : '修改时间',
							width : '14%',
							formatter : function(value, row) {
								return convertTimeStamp(row.updateTime);
							}
						}, {
							field : 'updateBy',
							title : '修改人',
							width : '14%'
						}]],
	                   onResize:function(){
	                       $('#outstockMainProductList').datagrid('fixDetailRowHeight',index);
	                   },
	                   onLoadSuccess:function(){
	                       setTimeout(function(){
	                           $('#outstockMainProductList').datagrid('fixDetailRowHeight',index);
	                       },0);
	                   }
	               });
	               $('#outstockMainProductList').datagrid('fixDetailRowHeight',index);
	           }
		});
	}
	
	function openSelectingDialog_outstockmainProduct(){  
		 	$('#searchDialog_outstockmainProduct').dialog('open');
	}	
    function searchform_outstockmainProduct(){
    	$('#outstockMainProductList').datagrid({  
    		url : 'outstockmainProduct/findAllList', 
		    queryParams:{
		    	outstockNum: $("#s_outstockNum_outstockmainProduct").val(),
		    	outstockBatchNum: $("#s_outstockBatchNum_outstockmainProduct").val(),
		    	registrant: $("#s_registrant_outstockmainProduct").val(),
		    	consignor: $("#s_consignor_outstockmainProduct").val(),
		    	registDateStart: $("#s_registDateStart_outstockmainProduct").datebox("getValue"),
		    	registDateEnd: $("#s_registDateEnd_outstockmainProduct").datebox("getValue"),
		    	outstockDateStart: $("#s_outstockDateStart_outstockmainProduct").datebox("getValue"),
		    	outstockDateEnd: $("#s_outstockDateEnd_outstockmainProduct").datebox("getValue"),		    	
	        	productName: $("#s_goodsName_outstockmainProduct").val(),
	        	logisticsId:$("#s_logisticsId_outstockmainProduct").combobox('getValue'),
	        	companyId: $("#index_user_companys").combobox('getValue'),
		    }
		});
    	$('#searchDialog_outstockmainProduct').dialog('close');
    }

</script>