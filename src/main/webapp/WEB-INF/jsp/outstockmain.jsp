<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<link rel="stylesheet" type="text/css" href="static/css/datagrid_util.css">
<div id="OutstockMainBtns" style="padding: 5px 0;">
	<shiro:hasPermission name="trans:outstockmain:add">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-add-s1'" onclick="openOutstockMain()">新增商品出库</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="trans:outstockmain:edit">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit-s1'" onclick="editOutstockMain()">编辑商品出库</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="trans:outstockmain:delete">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-delete-s1'" onclick="removeOutstockMain()">删除商品出库</a>
	</shiro:hasPermission>
</div>
<table id="OutstockMainsgrid" title="商品出库信息列表" style="width: 100%;"></table>
<div id="OutstockMainDialog" data-options="modal:true,closed:true,iconCls:'icon-document'"
	style="width: 590px; padding: 10px;">
	<form id="OutstockMainForm" method="post">
		<div   style="margin-left: 10px;">
			 <input type="hidden" name="outstockMainId"  id="outstockMainId_outstockMain"/>
			 </br>
			 <div class="fitem">
			          <label class="fitemlabel width_70">销售单号：</label>
			          <select id="saleOrderId_OutstockMain" name="saleOrderId" style="width:450px;"></select>&nbsp;
			 </div>
			 <!-- 
			 <div class="fitem">
			          <label class="fitemlabel width_70">物流企业：</label>
			          <input id="logisticsId_outstockMain" name="logisticsId" style="width:450px;"  >&nbsp;
			 </div>
			  -->
			 <div class="fitem">
			          <label class="fitemlabel width_70">出库单号：</label>
			          <input class="easyui-textbox" name="outstockNum" id="outstockNum_outstockMain"
							data-options="prompt:'出库单号',validType:'name',required:true" style="width:185px;">&nbsp;
					  <label class="fitemlabel width_70">出库批次：</label>
			          <input class="easyui-textbox" name="outstockBatchNum" id="outstockBatchNum_outstockMain"
							data-options="prompt:'出库批次号',validType:'name',required:true" style="width:185px;">&nbsp;		
			 </div>
			 <div class="fitem">
			          <label class="fitemlabel width_70">&nbsp;登 记 人：</label>
			          <input class="easyui-textbox" name="registrant"  id="registrant_outstockMain"
							data-options="prompt:'登记人',validType:'name',required:true" style="width:185px;">&nbsp;
			          <label class="fitemlabel width_70">&nbsp;发 货 人：</label>
			          <input class="easyui-textbox" name="consignor" id="consignor_outstockMain"
							data-options="prompt:'发货人',validType:'name',required:true" style="width:185px;">&nbsp;
			 </div>	 	 
			 <div class="fitem">
			          <label class="fitemlabel width_70">出库日期：</label>
			          <input class="easyui-datebox" name="outstockDate"  id="outstockDate_outstockMain"
							data-options="prompt:'出库日期',validType:'name',required:true" style="width:185px;">&nbsp;
			          <label class="fitemlabel width_70">登记日期：</label>
			          <input class="easyui-datebox" name="registDate"  id="registDate_outstockMain"
							data-options="prompt:'登记日期',validType:'name',required:true" style="width:185px;">&nbsp;
			 </div>
		</div>
		<div  style="margin-left: 20px;">
			<table id="OutstockItemsgrid" class="easyui-datagrid" title="商品出库明细列表" style="width:520px;height:265px"
		          data-options="
		           iconCls: 'icon-edit',
	           	   fitColumns : true,
				   singleSelect : true,
				   rownumbers : true,
				   pagination : true,
				   pageSize : 500,
				   pageList : [ 500],
				   loadMsg : '页面正在加载....',
				   height : 'auto',
				   onClickCell: onClickCell_OutstockMain,
	               onEndEdit: onEndEdit_OutstockMain
		          ">
		           
			</table>
		</div>
		<div style="margin: 10px 20px;">
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
				style="width: 520px; height: 32px"
				onclick="submitOutstockMainForm()">确认提交</a>
		</div>
	</form>
</div>
<script type="text/javascript">
	var editIndex_OutstockMain = undefined;
	$(function(){ 
		loadOutstockMain();
	});

	//加载数据
	function loadOutstockMainSelectData() {
		
		$("#saleOrderId_OutstockMain").combogrid({
			panelWidth : 440,
			url : 'sale_order/findByStatus?companyId='
				+ $("#index_user_companys").combobox('getValue')
				+'&status=0',
			method : 'post',
			multiple : true,
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
				loadSaleOrderItemsInfo_outStockMain();
			}
		});
		/* $("#logisticsId_outstockMain").combobox({  
			url : 'logistics/findByUserCompanyId',
			valueField : 'logisticsId',
			textField : 'logisticsCompanyName',
			editable : true,
			onBeforeLoad:function(param){
				var companyId=$("#index_user_companys").combobox('getValue');
				param.companyId=companyId;
			}
	 	}); */
	}
	
	/*读取销售单号的明细*/
	function loadSaleOrderItemsInfo_outStockMain(){
		$('#OutstockItemsgrid').datagrid({
			   url:'sale_order/findAllOutstockItems',
			   pageSize : 500,
			   onBeforeLoad : function(param) {
				    param.saleOrderId = $("#saleOrderId_OutstockMain").combo('getValues').join(",");
			   },
			   onLoadSuccess: function(data){
				   editIndex_OutstockMain = undefined;
			   },
		}); 
	}
	//编辑信息
	function editOutstockMain() {
		var row = $('#OutstockMainsgrid').datagrid('getSelected');
		if (row) {
			$('#OutstockMainForm').form('clear').form('disableValidation');
			loadOutstockMainSelectData();//加载销售单号
			$('#OutstockMainForm').form('load', {
				outstockMainId : row.outstockMainId,
				registrant : row.registrant,
				consignor : row.consignor,
				registDate : row.registDate,
				outstockDate : row.outstockDate,
				//remark:row.remark,
				outstockBatchNum : row.outstockBatchNum,
				outstockNum : row.outstockNum
			});
			
			$("#saleOrderId_OutstockMain").combobox({disabled:true});		
			$("#saleOrderId_OutstockMain").combogrid("setValues",row.saleOrderId.split(','));
			/* $("#logisticsId_outstockMain").combobox("setValues",row.logisticsId.split(',')); */ 
			//加载商品出库详细数据
			$('#OutstockItemsgrid').datagrid({
			   url:'outstockitem/findAllList',
			   pageSize : 500,
			   onBeforeLoad : function(param) {
					param.outstockMainId = $('#outstockMainId_outstockMain').val();
			   },
			   onLoadSuccess: function(data){
				   editIndex_OutstockMain = undefined;
			   },
			   columns : [ [
							{
								field : 'productName',
								title : '产品名称',
								width:140
							},
							{
								field : 'fullStandardName',
								title : '产品规格',
								width:100
							},
							{
								field : 'goodsBatch',
								title : '商品批次',
								width:140
							},
							{
								field : 'outstockNum',
								title : '出库数量',
								width:80
							},
							/* {
								field : 'stockNum',
								title : '库存数量',
								width:80
							}, */
				]],
			}); 
			$('#OutstockItemsgrid').datagrid('reload');
			$('#OutstockMainDialog').dialog({
				closed : false,
				modal : true,
				title : "编辑商品出库单",
				iconCls : 'icon-document'
			});

		} else {
			$.messager.alert('信息提示', '请选择一行记录！', 'info');
		}
	}
	//打开新增信息
	function openOutstockMain() {
		loadOutstockMainSelectData();//加载销售单号
		var time = new Date().format("yyyy-MM-dd");
		$('#OutstockMainForm').form('clear').form('disableValidation');
		$("#outstockNum_outstockMain").textbox('setValue',getPurchaseOrderNo());//出库单号
		$("#outstockBatchNum_outstockMain").textbox('setValue',getPurchaseOrderNo());//出库批次
		$("#registrant_outstockMain").textbox('setValue',$("#index_userName").val());//登 记 人
		$("#consignor_outstockMain").textbox('setValue',$("#index_userName").val());//发 货 人
		$("#outstockDate_outstockMain").textbox('setValue',time);//出库日期
		$("#registDate_outstockMain").textbox('setValue',time);//登记日期
		editIndex_splitProduct = undefined;
		//解决添加数据后修改会出现分页为空 默认设置分页为5页 
		$('#OutstockItemsgrid').datagrid({
			pageSize : 500,
			url:'',
			pagination : true,
		    columns : [ [
						{
							title : '产品名称',
							field:'productId',
				         	width:140,
							formatter:function(value, row, index){ 
								return row.productName;
							}
						},
						{
							title : '产品规格',
							field:'productStandardDetailId',
							width:100,
							formatter:function(value,row, index){
								return row.fullStandardName;
							}
						},
						{
							title : '商品批次',
						  	width:140,
							field:'goodsBatch',
						    width:160,
					        formatter:function(value,row, index){
								return row.goods.goodsBatch;
				            },
					        editor:{
					       		type:'combobox',
					               options:{
										method : 'post',
										valueField : 'goodsBatch',
										textField :  'goodsBatch',
										required : false,
										editable:true,
										url:'goods/findGoodsByStandardDetailId',
										onBeforeLoad : function(param) {
											var row = $('#OutstockItemsgrid').datagrid('getSelected');
											param.productId = row.productId;
											param.productStandardDetailId = row.productStandardDetailId;
										},
										onLoadSuccess: function(data){
												var row = $('#OutstockItemsgrid').datagrid('getSelected');
					 							var rowIndex = $('#OutstockItemsgrid').datagrid('getRowIndex', row);
					 							var detail = $('#OutstockItemsgrid').datagrid('getEditor', { index: rowIndex,field: 'goodsBatch'});
					 							//解决选中后产品选项为空问题
												if(data.length >0 && $(detail.target).combobox('getValue')==''){
													$(detail.target).combobox('select',data[0].goodsBatch);
												}
										},
										onSelect: function(data){
											var row = $('#OutstockItemsgrid').datagrid('getSelected'); 
											row.goodsId= data.goodsId;
											// row.stockNum = data.stockNum;
											var rowIndex = $('#OutstockItemsgrid').datagrid('getRowIndex', row); 
											var ed = $('#OutstockItemsgrid').datagrid('getEditor', {index:rowIndex,field:'outstockNum'});
											$(ed.target).next('span').find('input').focus(); 
											//var tdNum= $('#OutstockMainDialog .datagrid-body td[field=\'stockNum\']')[rowIndex];
										    //var divNum = $(tdNum).find('div')[0];
										    //$(divNum).text(data.stockNum);
								   		 }
								
					               }
					      	  }
						},
						{
							title : '出库数量',
							field:'outstockNum',
							width:80,
					       	editor:{
					       		type:'numberbox',
					       		options:{
					       			required:true
					       		}
					       	}
						},
						/* {
							field : 'stockNum',
							title : '库存数量',
							width:80
						}, */
			]],
		});
		
		
		$('#OutstockMainDialog').dialog({
			closed : false,
			modal : true,
			title : "新增商品出库单",
			iconCls : 'icon-document'
		});
	}
	function onClickCell_OutstockMain(index, field) {
		if (editIndex_OutstockMain != index) {
			if (endEditing_OutstockItem()) {
				$('#OutstockItemsgrid').datagrid('selectRow', index).datagrid('beginEdit',index);
				var ed = $('#OutstockItemsgrid').datagrid('getEditor', {
							index : index,
							field : field
				});
				if (ed) {
					($(ed.target).data('textbox') ? $(ed.target).textbox('textbox') : $(ed.target)).focus();
					editIndex_OutstockMain = index;
					return;
				}
				editIndex_OutstockMain = index;
			} else {
				setTimeout(function() {
					$('#OutstockItemsgrid').datagrid('selectRow',editIndex_OutstockMain);
				}, 0);
			}
		}
	}
	//编辑结束
	function onEndEdit_OutstockMain(index, row) {
		/*var ed = $(this).datagrid('getEditor', {
			index : index,
			field : 'goodsId'
		});
		row.goodsName = $(ed.target).combobox('getText');
		*/
	}
	//编辑结束
	function endEditing_OutstockItem() {
		if (editIndex_OutstockMain == undefined) {return true}
		if ($('#OutstockItemsgrid').datagrid('validateRow',editIndex_OutstockMain)) {
			$('#OutstockItemsgrid').datagrid('endEdit', editIndex_OutstockMain);
			editIndex_OutstockMain = undefined;
			return true;
		} else {
			return false;
		}
	}
	function submitOutstockMainForm(){
		 if(!$('#OutstockMainForm').form('enableValidation').form('validate')){
 			$.messager.alert('信息提示', '亲,您输入的数据不全哦！', 'info');
 			return;
 		} 
		//获取更新更改的行的集合  
	    
		row = $("#OutstockItemsgrid").datagrid('getRows'); 
		$('#OutstockMainForm').form('submit', {
			url : 'outstockmain/save', 
			onSubmit : function(param) {
				param.companyId = $('#index_user_companys').combobox('getValue');
				param.outstockItems = JSON.stringify(row);
				param.outstockType = "0";
				return $(this).form('enableValidation').form('validate');
			},
			success : function(data) {
				var msg = eval('(' + data + ')');
				if (msg.code == 1) {
					$('#OutstockMainDialog').window('close');
					$('#OutstockMainsgrid').datagrid('reload');
				}else{
					$.messager.alert('信息提示', msg.msg, 'info');
				}
			}
		});

	}
	function removeOutstockMain() {
		var row = $('#OutstockMainsgrid').datagrid('getSelected');
		if (row) {
	 		$.messager.confirm('信息提示', '确定要删除这条信息吗？', function(result) {
	 			if (result) {
					$.ajax({
						url : 'outstockmain/delete',
						data : {
							outstockMainId : row.outstockMainId
						},
						success : function(data) {
							data = eval('(' + data + ')');
							if (data.code == '1') {
								$.messager.alert('信息提示', '删除成功', 'info');
								loadOutstockMainSelectData();
								$('#OutstockMainsgrid').datagrid('reload');
							} else {
								$.messager.alert('信息提示', data.msg, 'warn');
							}
						}
					});
	 			}
	 		});
		} else {
			$.messager.alert('信息提示', '请选择一行记录！', 'info');
		}
	}
	function loadOutstockMain(){
	    <shiro:hasPermission name="trans:outstockmain:edit">
			 $('#OutstockMainsgrid').datagrid({
					onDblClickCell: function(index,field,value){
						editOutstockMain()
					}
			 });
		</shiro:hasPermission>
		$('#OutstockMainsgrid').datagrid({
			url : 'outstockmain/findAllList',
			rownumbers : true,
			pagination : true,
			pageSize : 10,
			onBeforeLoad : function(param) {
				param.companyId = $("#index_user_companys").combobox('getValue');
				param.outstockType = '0';
			},
			pageList : [ 10, 20, 30 ],
			singleSelect : true,
			fitColumns : true,
			fit : true,
			toolbar : '#OutstockMainBtns',
			columns : [ [ {
				field : 'outstockMainId',
				title : '出库单ID',
				hidden : true
			}, {
				field : 'saleOrderId',
				title : '销售单ID',
				hidden : true
			}, {
				field : 'companyId',
				title : '用户企业ID',
				hidden : true
			}, /* {
				field : 'logisticsId',
				title : 'logisticsId',
				hidden : true
			}, */
			{
				field : 'outstockNum',
				title : '出库单号',
				width : "15%"
			}, {
				field : 'outstockBatchNum',
				title : '出库批次号',
				width : "15%"
			}, {
				field : 'consignor',
				title : '出货人',
				hidden : true
			}, {
				field : 'registrant',
				title : '登记人',
				width : "15%"
			}, {
				field : 'outstockDate',
				title : '出库日期',
				width : "20%"
			}, {
				field : 'registDate',
				title : '登记日期',
				width : "20%"
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
							field : 'goodsId',
							title : '商品ID',
							hidden : true
						}, {
							field : 'goodsName',
							title : '商品名称',
							width : '30%'
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
	                       $('#OutstockMainsgrid').datagrid('fixDetailRowHeight',index);
	                   },
	                   onLoadSuccess:function(){
	                       setTimeout(function(){
	                           $('#OutstockMainsgrid').datagrid('fixDetailRowHeight',index);
	                       },0);
	                   }
	               });
	               $('#OutstockMainsgrid').datagrid('fixDetailRowHeight',index);
	           }
		});
		
	}
</script>