<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<link rel="stylesheet" type="text/css"
	href="static/css/datagrid_util.css">
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center',border:false">
		<div id="splitProduct-toolbar">
			<div class="wu-toolbar-button">
				<shiro:hasPermission name="operation:splitProduct:add">
					<a href="#" class="easyui-linkbutton" iconCls="icon-add-s1"
						onclick="opensplitProduct()" plain="true">新增拆分管理</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="operation:splitProduct:edit">
					<a href="#" class="easyui-linkbutton" iconCls="icon-edit-s1"
						onclick="editsplitProduct()" plain="true">修改拆分管理</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="operation:splitProduct:delete">
					<a href="#" class="easyui-linkbutton" iconCls="icon-delete-s1"
						onclick="delsplitProduct()" plain="true">删除拆分管理</a>
				</shiro:hasPermission>
			</div>
		</div>
		<table id=splitProductList title="拆分管理列表"></table>
	</div>
</div>
<div id="splitProductEditDialog"
	style="width: 685px; padding: 10px;">
	<form id="splitProductForm" method="post">
		<div style="margin-left: 10px;">
			<input type="hidden" name="processTemplateId" id="splitProduct_processTemplateId" />
			<input type="hidden" name="processMainId" id="splitProduct_processMainId" />
			<br/>
			<div class="fitem">
				<label class="fitemlabel" >拆分模版:</label>
					<input class="easyui-combobox" id="splitProduct_templateName" name="templateName" style="width: 360px;">
				<label class="fitemlabel" >产品类型:</label> 
					<input class="easyui-combobox" id="splitProduct_productType" name="productType" style="width: 150px;">	
			</div>
			
			<div class="fitem">
				<label class="fitemlabel" >拆分产品:</label> 
					<input class="easyui-combobox" id="splitProduct_productId" name="goods.productId" style="width: 150px;">
				<label class="fitemlabel" >产品规格:</label> 
					<input class="easyui-combobox" id="splitProduct_productStandardDetailId" name="goods.productStandardDetailId" style="width: 150px;">	
				<label class="fitemlabel" >商品批次:</label> 
					<input class="easyui-combobox" id="splitProduct_goodsBatch" name="goods.goodsBatch" style="width: 150px;" >	
			</div>
			<div class="fitem">
				<label class="fitemlabel" >拆分时间:</label> 
					<input class="easyui-datetimebox" id="splitProduct_processTimeStr" name="processTimeStr" style="width: 150px" data-options="">
				<label class="fitemlabel" >拆分批次:</label> 
					<input class="easyui-textbox" id="splitProduct_processBatch" name="processBatch" style="width: 150px" data-options="required:true" />
				<label class="fitemlabel" >拆分数量:</label> 
					<input type="text" class="easyui-numberbox" id="splitProduct_processQuantity" name="processQuantity" style="width: 150px"
					style="text-align: right;" maxlength="12" size="12"
					data-options="min:0,
						max:99999.99,
						precision:2,
						required:true">
					
			</div>
			
		</div>
		<div style="margin-left: 10px;">
			<table id="splitProductItemTable"
				class="easyui-datagrid" title="产品拆分明细"
				style="width: 630px; height: 265px;"
				data-options="
						iconCls: 'icon-edit',
			            singleSelect: true,
			            toolbar: '#tb_splitProduct',
			            onClickCell: onClickCell_splitProduct,
			            onEndEdit: onEndEdit_splitProduct,
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
					<th width="10%"
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
	                                	var row = $('#splitProductItemTable').datagrid('getSelected');
		      							var rowIndex = $('#splitProductItemTable').datagrid('getRowIndex', row);
		      							var detail = $('#splitProductItemTable').datagrid('getEditor', { index: rowIndex,field: 'productId'});
		      							//解决选中后产品选项为空问题
										if(record.length >0 && $(detail.target).combobox('getValue')==''){
											$(this).combobox('select',record[0].productType);
										}
									},
	                                onSelect:function(data){
		                                var row = $('#splitProductItemTable').datagrid('getSelected');
		      							var rowIndex = $('#splitProductItemTable').datagrid('getRowIndex', row);
		      							var detail = $('#splitProductItemTable').datagrid('getEditor', { index: rowIndex,field: 'productId'});
		      						    if(data.productType!=undefined || data.productType!=''){
			      						 	$(detail.target).combobox('clear');
	      									var companyId = $('#index_user_companys').combobox('getValue');
											var url = 'product/list?companyId='+ companyId+'&productType='+data.productType;
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
		                                var row = $('#splitProductItemTable').datagrid('getSelected');
		      							var rowIndex = $('#splitProductItemTable').datagrid('getRowIndex', row);
		      							var detail = $('#splitProductItemTable').datagrid('getEditor', { index: rowIndex,field: 'productStandardDetailId'});
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
												var detailVal=$(this).combobox('getValue');
												if(!detailVal&&data.length >0){
													$(this).combobox('select',data[0].productStandardDetailId);
												}
	 												var row = $('#splitProductItemTable').datagrid('getSelected'); 
	 		      									var rowIndex = $('#splitProductItemTable').datagrid('getRowIndex', row); 
													var ed = $('#splitProductItemTable').datagrid('getEditor', {index:rowIndex,field:'num'});
	 												$(ed.target).next('span').find('input').focus(); 
										},
										onSelect:function(data){
				                                var row = $('#splitProductItemTable').datagrid('getSelected');
				      							var rowIndex = $('#splitProductItemTable').datagrid('getRowIndex', row);
				      							var rowproductId = $('#splitProductItemTable').datagrid('getEditor', { index: rowIndex,field: 'productId'});
				      							var detail = $('#splitProductItemTable').datagrid('getEditor', { index: rowIndex,field: 'goodsBatch'});
				      						    if(data.product!= undefined && data.product.productId!= undefined && data.product.productId!= '' ){
				      						 	    $(detail.target).combobox('clear');
													$(detail.target).combobox('reload', 'goods/getgoods?productId=' +data.product.productId);
												}
			                            }
										
		                            }
	                }">规格明细</th>
	                <th width="22%" data-options="field:'goodsBatch',
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
								editable:true,
								onLoadSuccess: function(data){
										var row = $('#splitProductItemTable').datagrid('getSelected');
		      							var rowIndex = $('#splitProductItemTable').datagrid('getRowIndex', row);
		      							var detail = $('#splitProductItemTable').datagrid('getEditor', { index: rowIndex,field: 'goodsBatch'});
		      							var edtype = $('#splitProductItemTable').datagrid('getEditor', {index:rowIndex,field:'type'});
	      								//自动选择主料
										if($(edtype.target).combobox('getValue')==''){
		      									$(edtype.target).combobox('select',0);
		      							}
		      							//解决选中后产品选项为空问题
										if(data.length >0 && $(detail.target).combobox('getValue')==''){
											$(detail.target).combobox('select',data[0].goodsBatch);
										}
								},
								onSelect: function(data){
	 												var row = $('#splitProductItemTable').datagrid('getSelected'); 
	 		      									var rowIndex = $('#splitProductItemTable').datagrid('getRowIndex', row); 
													var ed = $('#splitProductItemTable').datagrid('getEditor', {index:rowIndex,field:'num'});
	 												$(ed.target).next('span').find('input').focus(); 
												
								}
                            }
	                }">批次</th>
	                <th width="12%"
							data-options="field:'type',
		                	formatter:function(value,row){
		                            return row.typeName;
		                    },
		                    hidden : true,
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
	 												var row = $('#splitProductItemTable').datagrid('getSelected'); 
	 		      									var rowIndex = $('#splitProductItemTable').datagrid('getRowIndex', row); 
													var ed = $('#splitProductItemTable').datagrid('getEditor', {index:rowIndex,field:'num'});
	 												$(ed.target).next('span').find('input').focus(); 
												
										}
		                            }
	                }">类型</th>
					<th width="8%" data-options="field:'num',
	                	editor:{
	                	type:'numberbox',
	                	options:{
	                		required:true
	                }}">数量</th>
					</tr>
				</thead>
			</table>
		</div>
		<div id="tb_splitProduct" style="height: auto">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-add',plain:true"
				onclick="appendsplitProductItem(2)">新增</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-remove',plain:true"
				onclick="removesplitProductItem()">删除</a>
			
			<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-arrow-rotate-anticlockwise',plain:true"
			onclick="createGoodsBatch()">生成批次</a>		
			
		</div>
	</form>
	<div style="margin: 10px 10px;">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
			style="width: 630px; height: 32px;"
			onclick="submitSplitProductItemForm()">确认提交</a>
	</div>
</div>
<script>
	var initFlag_splitProduct = 0;
	var editIndex_splitProduct = undefined;
	$(function() {
		loadsplitProduct();
	});

	function submitSplitProductItemForm() {

		var bvalidate = $('#splitProductForm').form('enableValidation').form('validate');
		if (!bvalidate) {
			$.messager.alert('信息提示', '亲,您输入的数据不全哦！', 'info');
			return;
		}

		$('#splitProductItemTable').datagrid('endEdit',editIndex_splitProduct);
		editIndex_splitProduct = undefined;
		var rows = $("#splitProductItemTable").datagrid("getRows");
		var lstTemplateItems = [];
		var vcompanyId = $("#index_user_companys").combobox("getValue");
		$('#splitProductForm').form('submit',{
			url : 'splitProduct/addSplit',
			onSubmit : function(param) {
				param.processDetail = JSON.stringify(rows);
				param.companyId = vcompanyId;
				param.token=$('#index_token').val();
				return true;
			},
			success : function(data) {
				var msg = eval('(' + data + ')');
				createToken();
				$.messager.alert('信息提示', msg.msg, 'info');
				if (msg.code == 1) {
					$('#splitProductEditDialog').window('close');
					$('#splitProductList').datagrid('reload');
				}
			}
	   });
	}
	function createGoodsBatch() {
		
		var strGoodsBatch = $("#splitProduct_goodsBatch").combobox('getValue');
		if (!strGoodsBatch) {
			$.messager.alert('信息提示', '请选择商品批次！', 'info');
			return;
		}
		
		var bvalidate = $('#splitProductForm').form('enableValidation').form('validate');
		if (!bvalidate) {
			$.messager.alert('信息提示', '亲,您输入完整数据！', 'info');
			return;
		}
		$('#splitProductItemTable').datagrid('acceptChanges');
		editIndex_splitProduct = undefined;
		var rows = $("#splitProductItemTable").datagrid("getRows");
		for (var i = 0; i < rows.length; i++) {
			rows[i].goodsBatch=strGoodsBatch;//防止编辑状态下的值获取不到
            var td=$('#splitProductEditDialog .datagrid-body td[field="goodsBatch"]')[i];
            var div = $(td).find('div')[0];
            $(div).text(strGoodsBatch);
		}
	}
	function opensplitProduct() {
		initFlag_splitProduct = 1;
		editIndex_splitProduct = undefined;
		delsplitProductItemsRow();
		$('#splitProductForm').form('clear');
		$('#splitProduct_productId').combobox('reload');
		$('#splitProduct_goodsBatch').combobox('reload');
		$('#splitProduct_productStandardDetailId').combobox('reload');

		initDialogsplitProduct();
		var time = new Date().format("yyyy-MM-dd");
		$('#splitProductForm').form('disableValidation');
		
		$('#splitProductEditDialog').dialog({
			closed : false,
			modal : true,
			title : "新增拆分管理",
			iconCls : 'icon-document'
		});
		initData();//初始化数据
		
		
	}
	function initData() {
		var time = new Date().format("yyyy-MM-dd hh:mm:ss");
		$("#splitProduct_processBatch").textbox('setValue',getPurchaseOrderNo());//拆分批次
		$("#splitProduct_processTimeStr").textbox('setValue', time);//拆分时间
		$("#splitProduct_processQuantity").textbox('setValue', "1.00");//拆分数量
	}
	
	function initDialogsplitProduct() {
		
		$("#splitProduct_templateName").combobox({
			url : 'splitTemplate/list',
			valueField : 'processTemplateId',
			textField : 'templateName',
			editable : false,
			onBeforeLoad : function(param) {
				var companyId = $("#index_user_companys").combobox('getValue');
				param.companyId = companyId;
				param.type=1;
			},
			onLoadSuccess : function(record) {
				/*if(record.length >0 && $('#splitProduct_productId').combobox('getValue')==''){
					$(this).combobox('select',record[0].processTemplateId);
				}*/
			},
			onSelect : function(record) {
				$.messager.confirm('使用模版数据', '确认使用模版数据吗?', function(r) {
					if (r) {
						initFlag_splitProduct = 0;
						$('#splitProductForm').form('clear').form('disableValidation');
						initData();//初始化数据
						
						$('#splitProductForm').form('load', {
							processTemplateId : record.processTemplateId,
							'goods.productId' : record.product.productId,
							productType:record.productType,
							'goods.productStandardDetailId' : record.standardDetail.productStandardDetailId,
							templateName : record.templateName
						});
						
						var companyId = $('#index_user_companys').combobox('getValue');
						var url = 'product/list?companyId='+ companyId+"&productType="+record.productType;
						$('#splitProduct_productId').combobox('reload', url);
						
						url = 'product_standard_detail/list?productId='+ record.product.productId;
						$('#splitProduct_productStandardDetailId').combobox('reload', url);
						
						
						url = 'splitTemplate/findAllItemlist?templateId='+$('#splitProduct_processTemplateId').val();
						$('#splitProductItemTable').datagrid({
							url: url,
							onLoadSuccess : function(data) {
								url = 'goods/getgoods?productId=' + record.product.productId+"&productStandardDetailId="+record.standardDetail.productStandardDetailId,
								$('#splitProduct_goodsBatch').combobox('reload', url);

							}
						});
						
						
						
						
						
						

						
						
						
						
					}
				});
				
				
				
				
			}
		});
		
		


		
		$("#splitProduct_productType").combobox({
			url : 'getProductype',
			valueField : 'productType',
			textField : 'productTypeName',
			editable : false,
			onSelect : function(record) {
				$('#splitProduct_productId').combobox('clear');
				$('#splitProduct_goodsBatch').combobox('clear');
				$('#splitProduct_productStandardDetailId').combobox('clear');
				var companyId = $('#index_user_companys').combobox('getValue');
				var url = 'product/list?companyId='+ companyId+"&productType="+record.productType;
				$('#splitProduct_productId').combobox('reload', url);
			}
		});
		
		$("#splitProduct_productId").combobox({
			valueField : 'productId',
			textField : 'productName',
			required:true,
			editable : true,
			onSelect : function(record) {
				$('#splitProduct_goodsBatch').combobox('clear');
				$('#splitProduct_productStandardDetailId').combobox('clear');
				var url = 'product_standard_detail/list?productId='+ record.productId;
				$('#splitProduct_productStandardDetailId').combobox('reload', url);
				
				
				
			}
		});
		$("#splitProduct_productStandardDetailId").combobox({
			valueField : 'productStandardDetailId',
			textField : 'fullStandardName',
			required:true,
			editable : false,
			onSelect : function(record) {
				$('#splitProduct_goodsBatch').combobox('clear');
				url = 'goods/getgoods?productId=' + record.product.productId+"&productStandardDetailId="+record.productStandardDetailId;
				$('#splitProduct_goodsBatch').combobox('reload', url);

				
			}
		
		});
		$("#splitProduct_goodsBatch").combobox({
			valueField : 'goodsBatch',
			textField : 'goodsBatch',
			required:true,
			editable : false,
			onSelect : function(record) {
				createGoodsBatch();
			},
			onLoadSuccess : function(data) {
				if(initFlag_splitProduct == 0){
					if(data.length >0){
						$(this).combobox('select',data[0].goodsBatch);
						createGoodsBatch();
					}
					
				}

			}
		});
		
	}
	
	function initTabel(initUrl){
		$('#splitProductItemTable').datagrid({
			url: initUrl,
			iconCls: 'icon-edit',
            singleSelect: true,
            toolbar: '#tb_splitProduct',
            onClickCell: onClickCell_splitProduct,
            onEndEdit: onEndEdit_splitProduct,
            fitColumns : true,
			singleSelect : true,
			rownumbers : true,
			pagination : true,
			pageSize : 500,
			pageList : [ 500],
			loadMsg : '页面正在加载....',
			height : 'auto'
		});
		
	}
	
	
	
	
	function delsplitProduct() {
		var row = $('#splitProductList').datagrid('getSelected');
		if (row) {
			$.messager.confirm('删除拆分管理', '确认删除?', function(r) {
				if (r) {
					$.post("splitProduct/delSplitProduct", {
						processMainId : row.processMainId
					}, function(data) {
						$.messager.alert('信息提示', data.msg, 'info');
						if (data.code == 1) {
							$('#splitProductList').datagrid('reload');
						}
					})
				}
			});
		} else {
			$.messager.alert('信息提示', '请选择一行记录！', 'info');
		}
	}
	function delsplitProductItemsRow() {
		var rows = $('#splitProductItemTable').datagrid('getRows');
		for (var i = rows.length - 1; i > -1; i--) {
			$('#splitProductItemTable').datagrid('cancelEdit', i).datagrid('deleteRow', i);
		}
	}

	function editsplitProduct() {
		initFlag_splitProduct = 1;
		var row = $('#splitProductList').datagrid('getSelected');
		if (row) {
			$('#splitProductForm').form('clear').form('disableValidation');
			editIndex_splitProduct = undefined;
			initDialogsplitProduct();
			//解决添加数据后修改会出现分页为空 默认设置分页为5页 
			$('#splitProductItemTable').datagrid({
				pageSize : 500
			});
			$('#splitProductForm').form('load', {
				processMainId : row.processMainId,
				processTemplateId : row.processTemplateId,
				'goods.productId' : row.goods.productId,
				productType:row.productType,
				'goods.productStandardDetailId' : row.goods.productStandardDetailId,
				'goods.goodsBatch' : row.goods.goodsBatch,
				processTimeStr:row.processTimeStr,
				processBatch:row.processBatch,
				processQuantity:row.processQuantity
				
			});
			
			var companyId = $('#index_user_companys').combobox('getValue');
			var url = 'product/list?companyId='+ companyId+"&productType="+row.productType;
			$('#splitProduct_productId').combobox('reload', url);
			
			var url = 'product_standard_detail/list?productId='+ row.goods.productId;
			$('#splitProduct_productStandardDetailId').combobox('reload', url);
			
			//console.info("row.goods.goodsBatch:"+row.goods.goodsBatch);

			url = 'splitProduct/findAllItemList?processMainId='+row.processMainId;
			$('#splitProductItemTable').datagrid({
				url: url,
				onLoadSuccess : function(data) {
					url = 'goods/getgoods?productId=' + row.goods.productId+"&productStandardDetailId="+row.goods.productStandardDetailId,
					$('#splitProduct_goodsBatch').combobox('reload', url);

				}
			});
			
			
			
			$('#splitProductEditDialog').dialog({
				closed : false,
				modal : true,
				title : "修改拆分管理",
				iconCls : 'icon-document'
			});
		} else {
			$.messager.alert('信息提示', '请选择一行记录！', 'info');
		}
	}
	/**
	 * 载入采购单信息
	 */
	function loadsplitProduct() {
	  	<shiro:hasPermission name="operation:splitProduct:edit">
			 $('#splitProductList').datagrid({
				onDblClickCell: function(index,field,value){
					editsplitProduct();
				}
			 });
		</shiro:hasPermission>
		$('#splitProductList').datagrid({
			method : 'post',
			url : 'splitProduct/findAllList',
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
			toolbar : '#splitProduct-toolbar',
			columns : [ [ 
				{
					field : 'productName',
					title : '成品',
					width : 100,
					formatter : function(value, row) {
							return row.goods.productName;
					},
					sortable : true
				}, {
					field : 'processTime',
					title : '加工时间',
					width : 100,
				}, {
					field : 'processBatch',
					title : '加工批次号',
					width : 100
				}, {
					field : 'processQuantity',
					title : '数量',
					width : 100
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
							url : 'splitProduct/findAllItemList',
							fitColumns : true,
							singleSelect : true,
							rownumbers : true,
							pagination : true,
							pageSize : 10,
							pageList : [ 10, 20, 30 ],
							loadMsg : '页面正在加载....',
							height : 'auto',
							onBeforeLoad : function(param) {
								param.processMainId = row.processMainId;
							},
							columns : [ [
									{
										field : 'productName',
										title : '产品',
										width : 80,
										formatter : function(value, row) {
											return row.product.productName;
										}
									},
									{
										field : 'productStandardName',
										title : '产品规格',
										width : 80,
										formatter : function(value, row) {
											return row.standardDetail.fullStandardName;
										}
									},
									{
										field : 'num',
										title : '数量',
										width : 20
									},
									{
										field : 'goodsBatch',
										title : '商品批次',
										width : 50
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
										width : 30,
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
										width : 30,
										formatter : function(value, row) {
											return row.updateBy;
										}
									}

							] ],
							onResize : function() {
								$('#splitProductList').datagrid('fixDetailRowHeight',index);
							},
							onLoadSuccess : function() {
								setTimeout(
										function() {
											$('#splitProductList').datagrid('fixDetailRowHeight',index);
										}, 0);
							}
						});
				$('#splitProductList').datagrid(
						'fixDetailRowHeight', index);
			}
		});
	}

	function endEditing_spliteProduct() {
		if (editIndex_splitProduct == undefined) {
			return true
		}
		if ($('#splitProductItemTable').datagrid('validateRow',
				editIndex_splitProduct)) {
			$('#splitProductItemTable').datagrid('endEdit',
					editIndex_splitProduct);
			editIndex_splitProduct = undefined;
			return true;
		} else {
			return false;
		}
	}
	function onClickCell_splitProduct(index, field) {
		console.info();
		if (editIndex_splitProduct != index) {
			if (endEditing_spliteProduct()) {
				$('#splitProductItemTable').datagrid('selectRow', index).datagrid('beginEdit', index);
				
				var productId = $('#splitProductItemTable').datagrid('getEditor', {index : index,field : 'productId'});
				var productType = $('#splitProductItemTable').datagrid('getEditor', {index : index,field : 'productType'});
				var productStandardDetailId = $('#splitProductItemTable').datagrid('getEditor', {index : index,field : 'productStandardDetailId'});
				var goodsBatch  = $('#splitProductItemTable').datagrid('getEditor', {index : index,field : 'goodsBatch'});
				var type = $('#splitProductItemTable').datagrid('getEditor', {index : index,field : 'type'});
				
				var ed = $('#splitProductItemTable').datagrid('getEditor', {index : index,	field : field});
				if ( productType && productId  && productStandardDetailId && type && goodsBatch) {
					$(productType.target).combobox('reload');
					
					var companyId = $('#index_user_companys').combobox('getValue');
					var vproductType = $(productType.target).combobox('getValue');
					var url = 'product/list?companyId='+ companyId+"&productType="+vproductType;
					$(productId.target).combobox('reload',url);
					
					var vproductId = $(productId.target).combobox('getValue');
					$(productStandardDetailId.target).combobox('reload','product_standard_detail/list?productId='+ vproductId);
					
					$(goodsBatch.target).combobox('reload','goods/getgoods?productId='+ vproductId);
										
					$(type.target).combobox('reload');
				}
				if (ed) {
						($(ed.target).data('textbox') ? $(ed.target).textbox('textbox') : $(ed.target)).focus();
						editIndex_splitProduct = index;
						return;
				}
				editIndex_splitProduct = index;
			} else {
				setTimeout(function() {
					$('#splitProductItemTable').datagrid(
							'selectRow', editIndex_splitProduct);
				}, 0);
			}
		}
	}
	function onEndEdit_splitProduct(index, row) {
		var ed = $('#splitProductItemTable').datagrid('getEditor', {
			index : index,
			field : 'productId'
		});
		row.productName = $(ed.target).combobox('getText');
		
		var ed1 = $('#splitProductItemTable').datagrid('getEditor', {
			index : index,
			field : 'productType'
		});
		row.productTypeName = $(ed1.target).combobox('getText');
		
		var ed2 = $('#splitProductItemTable').datagrid('getEditor', {
			index : index,
			field : 'productStandardDetailId'
		});
		row.fullStandardName = $(ed2.target).combobox('getText');
		
		var ed3 =$('#splitProductItemTable').datagrid('getEditor', {
			index : index,
			field : 'type'
		});
		
		row.typeName = $(ed3.target).combobox('getText');
		
		var ed4 = $('#splitProductItemTable').datagrid('getEditor', {
			index : index,
			field : 'goodsBatch'
		});
		row.goodsBatch = $(ed4.target).combobox('getText');
	}
	function appendsplitProductItem(addType) {
		var bend = endEditing_spliteProduct();
		if (bend) {
			$('#splitProductItemTable').datagrid('appendRow', {});
			editIndex_splitProduct = $('#splitProductItemTable').datagrid('getRows').length - 1;
			$('#splitProductItemTable').datagrid(
					'selectRow', editIndex_splitProduct).datagrid(
					'beginEdit', editIndex_splitProduct);
			
			
		}
	}

	function removesplitProductItem() {
		var row = $('#splitProductItemTable').datagrid('getSelected');
		if (row == undefined || row.processItemId == undefined) {
			if (editIndex_splitProduct == undefined) {
				return
			}
			$('#splitProductItemTable').datagrid('cancelEdit',
					editIndex_splitProduct).datagrid('deleteRow',
					editIndex_splitProduct);
			editIndex_splitProduct = undefined;
			return;
		}
		
		if (row) {
			$.messager.confirm('信息提示', '确定要删除这条信息吗？', function(result) {
				if (result) {
					
					$.post("splitProduct/delSplitItem", {
						processItemId : row.processItemId,
					}, function(data) {
						$.messager.alert('信息提示', data.msg, 'info');
						if (data.code == 1) {
							if (editIndex_splitProduct == undefined) {
								return
							}
							$('#splitProductItemTable').datagrid('cancelEdit',editIndex_splitProduct)
									.datagrid('deleteRow',editIndex_splitProduct);
								editIndex_splitProduct = undefined;
							$('#splitProductList').datagrid('reload');

						}
					});
				}
			});
		} else {
			$.messager.alert('信息提示', '亲,请选择一行信息！', 'info');
		}

	}
</script>