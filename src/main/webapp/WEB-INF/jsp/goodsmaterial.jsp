<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<!-- layout  -->
<div class="easyui-layout" style="width:100%; " data-options="fit:true">
	<div id="p_goodsMaterial" data-options="region:'west',split:true" title="类别" style="width:20%; ">
		<table class="easyui-treegrid" 
            data-options="
                url: 'product_category/list',
                method: 'get',
                rownumbers: true,
                idField: 'productCategoryId',
                treeField: 'productCategoryName',
                fit:true,
                onBeforeLoad : function(row, param ) {
					param.companyId = $('#index_user_companys').combobox('getValue'); 
					param.level = 1;
				},
				onClickRow : function(row) {
					// console.info(row);
					loadGoodsMaterialByCategory(row.productCategoryId);
				}
            ">
	        <thead>
	            <tr>
	                <th data-options="field:'productCategoryName'" width="80%"></th>
	                <!-- <th data-options="field:'level'" width="20%" align="right">level</th> --> 
	            </tr>
	        </thead>
    	</table>
	</div>
	<div data-options="region:'center'" title="商品原料列表">
		<div id="goodsmaterialBar">
			<div class="goodsmaterialBarButton">
				<shiro:hasPermission name="base:goodsmaterial:add">
					<a href="#" class="easyui-linkbutton" iconCls="icon-add-s1"
						onclick="opengoodsmaterialAdd()" plain="true">新增商品原料</a>
					<span style="color:#D3D3D3 " >| </span>
				</shiro:hasPermission>
				<shiro:hasPermission name="base:goodsmaterial:edit">
					<a href="#" class="easyui-linkbutton" iconCls="icon-edit-s1"
						onclick="opengoodsmaterialEdit()" plain="true">修改商品原料</a>
					<span style="color:#D3D3D3 " >| </span>
				</shiro:hasPermission>
				<shiro:hasPermission name="base:goodsmaterial:delete">
					<a href="#" class="easyui-linkbutton" iconCls="icon-delete-s1"
						onclick="removegoodsmaterial()" plain="true">删除商品原料</a> 
					<span style="color:#D3D3D3 " >| </span>
				</shiro:hasPermission>
				<shiro:hasPermission name="base:goodsmaterial:usable">
					<a href="#" class="easyui-linkbutton" iconCls="icon-remove"
						onclick="setUsable()" plain="true">设置为已用完</a>
				</shiro:hasPermission>
				&nbsp;产品分类：&nbsp; 
				<select id="searchProductCategoryId_goodsmaterial" style="width: 150px;"></select> 
				&nbsp;产品名称：&nbsp; 
				<input id="productName_goodsmaterial" class="easyui-textbox" style="width: 200px"> 
				&nbsp;&nbsp;&nbsp;
				<a href="#" class="easyui-linkbutton" iconcls="icon-search" onclick="loadgoodsmaterial(1)">搜索</a>			
			</div> 
		</div>
		<!-- table 商品原料列表 -->
		<table id='goodsmaterialList' ></table>
		<!-- Begin of easyui-dialog -->
		<div id="goodsmaterialEditDialog" class="easyui-dialog fm" title="编辑" 
		data-options="modal:true,closed:true,iconCls:'icon-document'" 
		style="width: 420px; padding: 20px;" >		
		
			<form id="goodsmaterialForm" method="post">
				<input type="hidden" name="goodsId">
				<table>
					<tr height="35">
						<td width="70" align="right">原料：</td>
						<td><select id="productId_goodsmaterial" name="productId"
							style="width: 266px;"></select></td>
					</tr>
					<tr height="35">
						<td align="right">规格明细：</td>
						<td><select id="productStandardDetailId_goodsmaterial"
							name="productStandardDetailId" style="width: 266px;"></select></td>
					</tr>
					<tr height="35">
						<td align="right">批次：</td>
						<td><input id="goodsmaterialBatch_goodsmaterial" name="goodsBatch" type="text"
							class="easyui-textbox" style="width: 266px"
							data-options="required:true" /></td>
					</tr>
					<tr height="35">
						<td align="right">数量：</td>
						<td><input name="num" id="num2" type="text"
							class="easyui-numberbox" style="text-align: right; width: 266px;"
							data-options="required:true"></td>
					</tr>
					<tr height="35">
						<td align="right">标签类型：</td>
						<td><input name="type" type="text" class="easyui-textbox"
							style="width: 266px" /></td>
					</tr>
					<tr height="35">
						<td align="right">标签编码：</td>
						<td><input name="code" type="text" class="easyui-textbox"
							style="width: 266px"  /></td>
					</tr>
					<tr height="35">
						<td align="right">标签密钥：</td>
						<td><input name="secretKey" type="text" class="easyui-textbox"
							style="width: 266px" /></td>
					</tr>
				</table>
			</form>
			<div id="buttons-Edistgoodsmaterial"  style="text-align: center;display: none;">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="addgoodsmaterial()" style="width:120px;height: 32px">确认提交</a>
			</div>
		</div>
	</div>
</div> 

<script type="text/javascript">
	var initFlag_goodsmaterial = 0;

	$(function() {
		$("#searchProductCategoryId_goodsmaterial").combobox(
				{
					url : 'product_category/listForSelecting',
					method : 'post',
					valueField : 'productCategoryId',
					textField : 'productCategoryName',
					onBeforeLoad : function(param) {
						param.companyId = $("#index_user_companys").combobox('getValue');
					},
					filter : function(q, row) {
						var opts = $(this).combobox('options');
						return row[opts.textField].indexOf(q) == 0;
					},
					onLoadSuccess : function() {
						$('#searchProductCategoryId_goodsmaterial').combobox('setValue', null);
					}
				});
		loadgoodsmaterial();
	});


	/**
	 * button onClick => 删除商品原料
	 */
	function removegoodsmaterial() {
		var row = $('#goodsmaterialList').datagrid('getSelected');
		if (row) {
			$.messager.confirm('信息提示', '确定要删除该商品原料？', function(result) {
				if (result) {
					$.post('goods/delgoods', {
						goodsId : row.goodsId
					}, function(data) {
						var msg = eval('(' + data + ')');
						$.messager.alert('信息提示', msg.msg, 'info');
						if (msg.code == 1) {
							$('#goodsmaterialList').datagrid('reload');
						}
					});
				}
			});
		} else {
			$.messager.alert('信息提示', '请选择商品原料！', 'info');
		}

	}

	/**
	 * button onClick => 打开添加窗口
	 */
	function opengoodsmaterialAdd() {

		if (initFlag_goodsmaterial == 0) {
			initDialogComponent_goodsmaterial();
// 			initFlag_goodsmaterial = 1;
		}

		$('#goodsmaterialForm').form('clear').form('disableValidation');

		$.post("goods/getGoodsBatchNo",function(data){
	    	 $('#goodsmaterialBatch_goodsmaterial').textbox('setValue', data);
	    });

		$('#goodsmaterialEditDialog').dialog({
			closed : false,
			modal : true,
			title : "新增商品原料",
			iconCls : 'icon-document',
			buttons : '#buttons-Edistgoodsmaterial'
		});
	}

	/**
	 * button onClick => 打开修改窗口
	 */
	function opengoodsmaterialEdit() {

		if (initFlag_goodsmaterial == 0) {
			initDialogComponent_goodsmaterial();
			initFlag_goodsmaterial = 1;
		}

		var row = $('#goodsmaterialList').datagrid('getSelected');
		if (row) {
			$('#goodsmaterialForm').form('clear').form('disableValidation');
			$("#productStandardDetailId_goodsmaterial").combobox(
					'reload',
					"product_standard_detail/list?productId="
							+ (row.product.productId));
			$('#goodsmaterialForm').form('load', {
				goodsId : row.goodsId,
				productId : row.product.productId,
				processMainId : row.processMainId,
				companyId : row.companyId,
				num : row.num,
				productStandardDetailId : row.productStandardDetailId,
				goodsBatch : row.goodsBatch,
				type : row.type,
				code : row.code,
				secretKey : row.secretKey
			});

			$('#goodsmaterialEditDialog').dialog({
				closed : false,
				modal : true,
				title : "修改商品原料",
				buttons : '#buttons-Edistgoodsmaterial'
			});
		} else {
			$.messager.alert('信息提示', '请选择商品原料！', 'info');
		}
	}

	/**
	 * handler => action: 新增或修改商品原料
	 */
	function addgoodsmaterial() {

		var myUserCompanyId = $('#index_user_companys').combobox('getValue');
		console.info('goods/addgoods:start');
		$('#goodsmaterialForm').form('submit', {
			url : 'goods/addgoods',
			onSubmit : function(param) {

				param.companyId = myUserCompanyId;

				return $(this).form('enableValidation').form('validate');
			},
			success : function(data) {
				console.info('goods/addgoods:end');
				console.info(data);
				var msg = eval('(' + data + ')');
				$.messager.alert('信息提示', msg.msg, 'info');
				if (msg.code == 1) {
					$('#goodsmaterialEditDialog').window('close');
					$('#goodsmaterialList').datagrid('reload');
				}
			}
		});
	}
	
	function setUsable() {
		var row = $('#goodsmaterialList').datagrid('getSelected');
		if (row) {
			$.post('goods/setusable',{
				goodsId : row.goodsId
			},function(data){
				var msg = eval('(' + data + ')');
				$.messager.alert('信息提示', msg.msg, 'info');
				if (msg.code == 1) {
					$('#goodsmaterialList').datagrid('reload');
				}
			});
		} else {
			$.messager.alert('信息提示', '请选择商品原料！', 'info');
		}
	}

	/**
	 * initialize the component of the dialog
	 */
	function initDialogComponent_goodsmaterial() {
		// 产品
		$("#productId_goodsmaterial").combobox(
				{
					url : 'product/list',
					method : 'post',
					valueField : 'productId',
					textField : 'productName',
					onBeforeLoad : function(param) {
						param.companyId = $("#index_user_companys").combobox(
								'getValue');
						param.productType = '2';
					},
					required : true,
					editable : false,
					onSelect : function(data) {
						$("#productStandardDetailId_goodsmaterial").combobox('clear');
						$("#productStandardDetailId_goodsmaterial").combobox(
								'reload',
								"product_standard_detail/list?productId="
										+ (data.productId));
					}
				});
		// 规格明细
		$("#productStandardDetailId_goodsmaterial").combobox({
// 			url : 'product_standard_detail/list',
			method : 'post',
			valueField : 'productStandardDetailId',
			textField : 'fullStandardName',
			required : true,
			editable : false
		});
	}

	/**
	 * 载入商品原料信息 & 查询商品原料信息；
	 */
	function loadgoodsmaterial(loadType) {
		var querylist = '';
		if (loadType === 1) {
			querylist = 'goods/findAllQueryList';
		} else {
			querylist = 'goods/findAllList';
		}
		<shiro:hasPermission name="base:goodsmaterial:edit">
		 $('#goodsmaterialList').datagrid({
			onDblClickCell: function(index,field,value){
				opengoodsmaterialEdit()
			}
		 });
		</shiro:hasPermission>
		$('#goodsmaterialList')
				.datagrid(
						{
							method : 'post',
							url : 'goods/findAllList',
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
							toolbar : '#goodsmaterialBar',
							onBeforeLoad : function(param) {
								param.productName = $(
										"#searchProductName_goodsmaterial").val();
								var myUserCompanyId = $('#index_user_companys')
										.combobox('getValue');
								param.companyId = myUserCompanyId;
								param.producttype = '2';
								param.productName = $("#productName_goodsmaterial").val();
								param['product.productCategoryId'] = $("#searchProductCategoryId_goodsmaterial").combobox('getValue');
							},
							columns : [ [
									{
										field : 'productName',
										title : '原料',
										width : 100,
										sortable : true,
										formatter : function(val, row, index) {
											return row.product.productName;
										}
									},{
										field : 'usable',
										title : '消耗',
										width : 100,
										sortable : true,
										formatter : function(val, row, index) {
											if(val=='1'){
												return '耗完';
											} else {
												return '-';
											}
										}
									},
									{
										field : 'productStandardNum',
										title : '产品规格明细',
										width : 50,
										sortable : true,
										formatter : function(val, row, index) {
											return row.productStandardDetail.fullStandardName;
										}
									}, {
										field : 'goodsBatch',
										title : '商品原料批次',
										width : 50
									}, {
										field : 'name',
										title : '企业',
										width : 0,
										hidden : true,
										formatter : function(val, row, index) {
											return row.company.name;
										}
									}, {
										field : 'num',
										title : '数量',
										width : 20
									},{
										field : 'createTime',
										title : '创建时间',
										width : 40,
										formatter : function(val, row, index) {
											return convertTimeStamp(row.createTime);
										}
									}, {
										field : 'type',
										title : '标签类型',
										width : 40
									}, {
										field : 'code',
										title : '标签编码',
										width : 40
									}, {
										field : 'secretKey',
										title : '标签密钥',
										width : 40
									}, ] ]
						});
	}
	
	function loadGoodsMaterialByCategory(productCategoryId) {
		 //console.info(productCategoryId);
		 $('#goodsmaterialList').datagrid({
				queryParams: {
					'product.category.productCategoryId': productCategoryId,
					companyId: $("#index_user_companys").combobox("getValue")
				}
			}); 
	}  
</script>