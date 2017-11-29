<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<link rel="stylesheet" type="text/css" href="static/css/datagrid_util.css">
<div id="InstockMainBtns" style="padding: 5px 0;">
	<shiro:hasPermission name="trans:instockmain:add">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-add-s1'" onclick="addInstockMain()">新增商品入库</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="trans:instockmain:edit">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit-s1'" onclick="editInstockMain()">编辑商品入库</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="trans:instockmain:delete">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-delete-s1'" onclick="removeInstockMain()">删除商品入库</a>
	</shiro:hasPermission>
</div>
<table id="instockMainsgrid" title="商品入库列表" style="width: 100%;"></table>
<div id="InstockMainDialog" data-options="modal:true,closed:true,iconCls:'icon-document'"
	style="width: 490px; padding: 10px;">
	<form id="InstockMainForm" method="post">
		<div   style="margin-left: 10px;">
				<input type="hidden" name="instockMainId"  id="instockMainId_im"/>
				 </br>
				 <div class="fitem">
				          <label class="fitemlabel width_70">采购单号：</label>
				          <select id="purchaseOrderId_im" name="purchaseOrderId" style="width: 350px;"></select>&nbsp;
				 </div>
				 <div class="fitem">
				          <label class="fitemlabel width_70">入库单号：</label>
				          <input class="easyui-textbox" name="instockNum" id="instockNum_im" 
				           	 data-options="prompt:'入库单号',validType:'length[1,255]',required:true" style="width: 135px;">&nbsp;
				          <label class="fitemlabel width_70">入库批次：</label>
				          <input class="easyui-textbox" name="instockBatchNum" id="instockBatchNum_im"
				          	 data-options="prompt:'入库批次号',validType:'length[1,255]',required:true" style="width: 135px;">&nbsp;
				 </div>	
				 <div class="fitem">
				 		  <label class="fitemlabel width_70">&nbsp;收 货 人：</label>
				          <input class="easyui-textbox" name="consignee" id="consignee_im"
				         	 data-options="prompt:'收货人',validType:'length[1,80]',required:true" style="width: 135px;">&nbsp;
				 		  <label class="fitemlabel width_70">&nbsp;登 记 人：</label>
				          <input class="easyui-textbox" name="registrant" id="registrant_im" 
				          	 data-options="prompt:'登记人',validType:'length[1,80]',required:true" style="width: 135px;">&nbsp;
				 </div>
				 <div class="fitem">
				 		  <label class="fitemlabel width_70">登记日期：</label>
				          <input class="easyui-datebox" name="registDate" id="registDate_im"
				          	 data-options="prompt:'登记日期',validType:'name',required:true" style="width: 135px;">&nbsp;
						  <label class="fitemlabel width_70">入库日期：</label>
				          <input class="easyui-datebox" name="instockDate" id="instockDate_im" 
				          	 data-options="prompt:'入库日期',validType:'name',required:true" style="width: 135px;">&nbsp;
				 </div>
				 <div class="fitem" >
				 		<label class="fitemlabel width_70">入库备注：</label>
				          <input class="easyui-textbox" id="remark_im" name="remark" style="width: 350px; height: 38px"
							 data-options="multiline:true,prompt:'备注',validType:'length[1,500]'" > 
							 
				 </div>				 
		</div>
		<div  style="margin-left: 20px;">
				<table id="InstocksItemDatagrid" class="easyui-datagrid" title="商品入库明细列表" style="width:420px;height:265px"
			          data-options="
			           iconCls: 'icon-edit',
	                   toolbar: '#InstockItemBtns',
		           	   fitColumns : true,
					   singleSelect : true,
					   rownumbers : true,
					   pagination : true,
					   pageSize : 5,
					   pageList : [ 5, 10, 20 ],
					   loadMsg : '页面正在加载....',
					   height : 'auto',
					   onClickCell: onClickCell_InstockMain,
	                   onEndEdit: onEndEdit_InstockMain
			          ">
			         <thead>
			            <tr>
			                 <th data-options="field:'goodsId',width:210,
			                		formatter:function(value,row){
			                            return row.goodsName;
			                        },
			                        editor:{
			                            type:'combobox',
			                            options:{
			                                valueField:'goodsId',
			                                textField:'goodsName',
			                                method:'post',
			                                url:'goods/findAllGoodsName',
			                                required:true,
			                                editable:true,
			                                onBeforeLoad:function(param){
			                                	param.companyId=$('#index_user_companys').combobox('getValue');
			                                },
				                            onSelect:function(data){
				                            	    var row = $('#InstocksItemDatagrid').datagrid('getSelected'); 
	 		      									var rowIndex = $('#InstocksItemDatagrid').datagrid('getRowIndex', row); 
													var ed = $('#InstocksItemDatagrid').datagrid('getEditor', {index:rowIndex,field:'instockNum'});
	 												$(ed.target).next('span').find('input').focus(); 
				                            }
			                        	}
			                        }">商品</th>
			                <th data-options="
			                    field:'instockNum',width:50,
			                	editor:{
			                		type:'numberbox',
			                		options:{
			                			required:true
			                		}
			                	}
			                ">数量</th>
			            </tr>
			        </thead>
				</table>
		</div>
		<div id="InstockItemBtns" style="height:auto">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					data-options="iconCls:'icon-add',plain:true" onclick="appendInstockItem_im()">新增</a>
				<a href="javascript:void(0)" class="easyui-linkbutton"
					data-options="iconCls:'icon-delete',plain:true" onclick="removeInstockMainItems()">删除</a>
		</div>
		<div style="margin: 10px 20px;">
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
				style="width: 420px; height: 32px"
				onclick="submitInstockMainForm()">确认提交</a>
		</div>
	</form>
</div>
<script type="text/javascript">
	var editIndex_InstockMain = undefined;
	
	$(function(){ 
		loadInstockMain();
	});
	
	function loadInstockMainSelectData() {
		$("#purchaseOrderId_im").combogrid({
			panelWidth : 320,
			url : 'purchase_order/findAllPurchaseOrderId',
			method : 'post',
            onBeforeLoad:function(param){
            	param.companyId=$('#index_user_companys').combobox('getValue');
            },
			//multiple : true,
			required : true,
			prompt : '采购单',
			idField : 'purchaseOrderId',
			textField : 'purchaseOrderNo',
			fitColumns : true,
			editable : true,
			columns : [ [ {
				field : 'orderTime',
				title : '采购日期',
				width : '45%'
			},{
				field : 'purchaseOrderNo',
				title : '采购单号',
				width : '45%'
			}
			] ]

		});
	}
	
	function loadInstockMain(){
		<shiro:hasPermission name="trans:instockmain:edit">
			 $('#instockMainsgrid').datagrid({
					onDblClickCell: function(index,field,value){
						editInstockMain()
					}
			 });
		</shiro:hasPermission>
		$('#instockMainsgrid').datagrid({
			url : 'instockmain/findAllList',
			rownumbers : true,
			pagination : true,
			onBeforeLoad : function(param) {
				param.companyId = $("#index_user_companys").combobox('getValue');
			},
			pageSize : 10,
			pageList : [ 10, 20, 30 ],
			singleSelect : true,
			fitColumns : true,
			fit : true,
			toolbar : '#InstockMainBtns',
			columns : [ [ {
				field : 'instockMainId',
				title : '入库单ID',
				hidden : true
			}, {
				field : 'purchaseOrderId',
				title : '采购单',
				hidden : true
			}, {
				field : 'companyId',
				title : '用户企业ID',
				hidden : true
			}, {
				field : 'instockNum',
				title : '入库单号',
				width : "10%"
			}, {
				field : 'instockBatchNum',
				title : '入库批次号',
				width : "10%"
			}, {
				field : 'consignee',
				title : '收货人',
				width : "10%"
			}, {
				field : 'registrant',
				title : '登记人',
				width : "10%"
			}, {
				field : 'instockDate',
				title : '入库日期',
				width : "15%"
			}, {
				field : 'registDate',
				title : '登记日期',
				width : "15%"
			}, {
				field : 'updateTime',
				title : '修改日期',
				width : "15%",
				formatter : function(value, row) {
					return convertTimeStamp(row.updateTime);
				}
			}]] ,
			view: detailview,
			detailFormatter:function(index,row){
	               return '<div style="padding:2px"><table class="ddv"></table></div>';
	           },
	           onExpandRow: function(index,row){
	               var ddv = $(this).datagrid('getRowDetail',index).find('table.ddv');
	               ddv.datagrid({
	                   url:'instockitem/findAllList',
		           	   fitColumns : true,
					   singleSelect : true,
					   rownumbers : true,
					   pagination : true,
					   pageSize : 5,
					   pageList : [ 5, 10, 20 ],
					   loadMsg : '页面正在加载....',
					   height : 'auto',
					   onBeforeLoad : function(param) {
							param.instockMainId = row.instockMainId;
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
							field : 'instockNum',
							title : '入库数量',
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
	                       $('#instockMainsgrid').datagrid('fixDetailRowHeight',index);
	                   },
	                   onLoadSuccess:function(){
	                       setTimeout(function(){
	                           $('#instockMainsgrid').datagrid('fixDetailRowHeight',index);
	                       },0);
	                   }
	               });
	               $('#instockMainsgrid').datagrid('fixDetailRowHeight',index);
	           }
		});
		
	}
	//清空gridview入库详细
	function delInstockItemsRow() {
		var rows = $('#InstocksItemDatagrid').datagrid('getRows');
		for (var i = rows.length - 1; i > -1; i--) {
			$('#InstocksItemDatagrid').datagrid('cancelEdit', i).datagrid('deleteRow', i);
		}
	}
	//打开新增商品入库窗口
	function addInstockMain() {
		delInstockItemsRow();//清空gridview入库详细
		loadInstockMainSelectData();//加载采购单号
		var time = new Date().format("yyyy-MM-dd");
		$('#InstockMainForm').form('clear').form('disableValidation');
		$("#instockNum_im").textbox('setValue',getPurchaseOrderNo());//入库单号
		$("#instockBatchNum_im").textbox('setValue',getPurchaseOrderNo());//入库批次
		$("#consignee_im").textbox('setValue',$("#index_userName").val());//收 货 人
		$("#registrant_im").textbox('setValue',$("#index_userName").val());//登 记 人
		$("#registDate_im").textbox('setValue',time);//登记日期
		$("#instockDate_im").textbox('setValue',time);//登记日期
		//解决添加数据后修改会出现分页为空 默认设置分页为5页 
		$('#InstocksItemDatagrid').datagrid({pageSize : 5,url:''});
		$('#InstockMainDialog').dialog({
			closed : false,
			modal : true,
			title : "新增商品入库单",
			iconCls : 'icon-document'
		});
	}
	//编辑商品入库
	function editInstockMain() {
		var row = $('#instockMainsgrid').datagrid('getSelected');
		if (row) {
			$('#InstockMainForm').form('clear').form('disableValidation');
			editIndex_InstockMain = undefined;
			loadInstockMainSelectData();//加载采购单号
			//解决添加数据后修改会出现分页为空 默认设置分页为5页 
			$('#InstocksItemDatagrid').datagrid({pageSize : 5});
			
			$('#InstockMainForm').form('load', {
				instockMainId : row.instockMainId,
				registrant : row.registrant,
				consignee : row.consignee,
				registDate : row.registDate,
				instockDate : row.instockDate,
				remark:row.remark,
				instockBatchNum : row.instockBatchNum,
				instockNum : row.instockNum
			});
			$("#purchaseOrderId_im").combogrid("setValues",
					row.purchaseOrderId.split(','));
			//加载商品入库详细数据
			$('#InstocksItemDatagrid').datagrid({
			   url:'instockitem/findAllList',
			   onBeforeLoad : function(param) {
					param.instockMainId = $('#instockMainId_im').val();
			   }
			}); 
			$('#InstocksItemDatagrid').datagrid('reload');
			$('#InstockMainDialog').dialog({
				closed : false,
				modal : true,
				title : "编辑商品入库单",
				iconCls : 'icon-document'
			});

		} else {
			$.messager.alert('信息提示', '请选择一行记录！', 'info');
		}
	}
	function onClickCell_InstockMain(index, field) {
		if (editIndex_InstockMain != index) {
			if (endEditing_InstockItem_im()) {
				$('#InstocksItemDatagrid').datagrid('selectRow', index).datagrid('beginEdit',index);
				var ed = $('#InstocksItemDatagrid').datagrid(
						'getEditor', {
							index : index,
							field : field
				});
				if (ed) {
					($(ed.target).data('textbox') ? $(ed.target).textbox(
							'textbox') : $(ed.target)).focus();
					editIndex_InstockMain = index;
					return;
				}
				editIndex_InstockMain = index;
			} else {
				setTimeout(function() {
					$('#InstocksItemDatagrid').datagrid('selectRow',
							editIndex_InstockMain);
				}, 0);
			}
		}
	}
	function onEndEdit_InstockMain(index, row) {
		var ed = $(this).datagrid('getEditor', {
			index : index,
			field : 'goodsId'
		});
		row.goodsName = $(ed.target).combobox('getText');
	}
	function appendInstockItem_im() {
		var bend = endEditing_InstockItem_im();
		if (bend) {
			$('#InstocksItemDatagrid').datagrid('appendRow', {
				goodsBatch : getPurchaseOrderNo()
			});
			editIndex_InstockMain = $('#InstocksItemDatagrid').datagrid('getRows').length - 1;
			$('#InstocksItemDatagrid').datagrid('selectRow',editIndex_InstockMain)
				.datagrid('beginEdit', editIndex_InstockMain);
		}
	}
	
	function endEditing_InstockItem_im() {
		if (editIndex_InstockMain == undefined) {return true}
		if ($('#InstocksItemDatagrid').datagrid('validateRow',editIndex_InstockMain)) {
			$('#InstocksItemDatagrid').datagrid('endEdit', editIndex_InstockMain);
			editIndex_InstockMain = undefined;
			return true;
		} else {
			return false;
		}
	}
	function saveInstockMain( editWindow, InstockMainGrid) {
		$('#InstockMainForm').form('submit', {
			url : 'instockmain/save',
			onSubmit : function(param) {
				var companyId=$("#index_user_companys").combobox('getValue');
				param.companyId=companyId;
				return $(this).form('enableValidation').form('validate');
			},
			success : function(data) {
				var msg = eval('(' + data + ')');
				$.messager.alert('信息提示', msg.msg, 'info');
				if (msg.code == 1) {
					$('#' + editWindow).window('close');
					loadInstockMainSelectData();
					$('#InstocksItemDatagrid').datagrid('reload');
				}
			}
		});
	}
	function removeInstockMainItems() {
	 	var row = $('#InstocksItemDatagrid').datagrid('getSelected');
	 	if(row == undefined || row.instockItemId==undefined){
			if (editIndex_InstockMain == undefined){return}
		        $('#InstocksItemDatagrid').datagrid('cancelEdit', editIndex_InstockMain)
		                .datagrid('deleteRow', editIndex_InstockMain);
		        editIndex_InstockMain = undefined;
		 	return;
	 	}
	 	
	 	
	 	if (row) {
	 		$.messager.confirm('信息提示', '确定要删除这条信息吗？', function(result) {
	 			if (result) {
	 				$.post("instockitem/delete",{
	 					instockItemId:row.instockItemId,
	 				},function(data){
	 					var msg = eval('(' + data + ')');
	 					$.messager.alert('信息提示', msg.msg, 'info');
	 					if (msg.code == 1) {
	 						if (editIndex_InstockMain == undefined){return}
	 				        $('#InstocksItemDatagrid').datagrid('cancelEdit', editIndex_InstockMain)
	 				                .datagrid('deleteRow', editIndex_InstockMain);
	 				        editIndex_InstockMain = undefined;
	 				        $('#instockMainsgrid').datagrid('reload');
	 				    	$('#InstocksItemDatagrid').datagrid('reload');
	 					}
	 				});
	 				
	 			}
	 		});
	 	} else {
	 		$.messager.alert('信息提示', '亲,请选择一行信息！', 'info');
	 	}
	}
	
	function removeInstockMain() {
		var row = $('#instockMainsgrid').datagrid('getSelected');
		if (row) {
	 		$.messager.confirm('信息提示', '确定要删除这条信息吗？', function(result) {
	 			if (result) {
					$.ajax({
						url : 'instockmain/delete',
						data : {
							instockMainId : row.instockMainId
						},
						success : function(data) {
							data = eval('(' + data + ')');
							if (data.code == '1') {
								$.messager.alert('信息提示', '删除成功', 'info');
								$('#instockMainsgrid').datagrid('reload');
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

	
	function submitInstockMainForm(){
 		if(!$('#InstockMainForm').form('enableValidation').form('validate')){
 			$.messager.alert('信息提示', '亲,您输入的数据不全哦！', 'info');
 			return;
 		}
		if($('#purchaseOrderId_im').combogrid('grid').datagrid('getSelected')==null){
			$.messager.alert('信息提示', "亲...采购单号必须要选择才可以,不可以乱输入的！", 'info');
			return;
		}
 		
 		
 		
		$('#InstocksItemDatagrid').datagrid('endEdit', editIndex_InstockMain);
		editIndex_InstockMain = undefined;
		var rows=$("#InstocksItemDatagrid").datagrid("getRows");
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
			vinstockItems[i] = instockItem;
		}
		//console.info($("#purchaseOrderId_im").combobox("getValues"));
		var instockmain={
				purchaseOrderId:$("#purchaseOrderId_im").combobox("getValues").join(","),
				
				instockMainId:$("#instockMainId_im").val(),
				companyId: $("#index_user_companys").combobox("getValue"),//用户企业ID
				instockNum:$.trim($("#instockNum_im").val()),//入库单号
				instockBatchNum:$.trim($("#instockBatchNum_im").val()),//入库批次号
				consignee:$.trim($("#consignee_im").val()),//收货人
				registrant:$.trim($("#registrant_im").val()), //登记人
				remark:$.trim($("#remark_im").val()), //备注
				instockType:2,//设置入库类型为商品
				instockDate:$('#instockDate_im').datebox('getValue'),//入库日期 
				registDate:$('#registDate_im').datebox('getValue'),//登记日期 
				instockitems:vinstockItems
		};
		$.ajax({
			type : 'post',
			url : 'instockmain/save',
			dataType : "json",
			contentType : "application/json",
			data : JSON.stringify(instockmain),
			success : function(data) {
				$.messager.alert('信息提示', data.msg, 'info');
				if (data.code == 1) {
					$('#InstockMainDialog').window('close');
					$('#instockMainsgrid').datagrid('reload');
				}
			}
		});
	}
</script>