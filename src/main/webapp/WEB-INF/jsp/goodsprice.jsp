<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<link rel="stylesheet" type="text/css" href="static/css/datagrid_util.css">
<div id="goodspriceBar" style="padding: 5px 0;">
	<shiro:hasPermission name="base:goodsprice:add">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-add-s1'" onclick="opengoodspriceWindow()">新增商品价格</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="base:goodsprice:edit">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit-s1'" onclick="editgoodsprice()">编辑商品价格</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="base:goodsprice:delete">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-delete-s1'" onclick="delgoodsprice();">删除商品价格</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="base:goodsprice:list">
		&nbsp;产品分类：&nbsp;<select id="searchProductCategoryId_productPrice" name="productCategoryId" style="width: 150px;"></select>
		&nbsp;产品名称：&nbsp;<input id="productName_productPrice" name="productName" class="easyui-textbox" style="width: 200px">
		<a href="#" class="easyui-linkbutton" iconcls="icon-search" onclick="loadgoodspriceData()">搜索</a>
	</shiro:hasPermission> 
	
</div>


<table id="goodspricelist" title="商品价格列表" style="width: auto; height: 350px">
</table>
<div id="addgoodsprice" class="easyui-dialog" title="新增" 
data-options="modal:true,closed:true,iconCls:'icon-document'"
style="width: 315px;padding: 20px;" >
	<form id="goodspriceForm" method="post">
		<input type="hidden" name="productStandardDetailId" id ="productStandardDetailId_goodsprice">
		<input type="hidden" id="hidden_productId_goodsprice" name="productId">
		<div class="fitem">
		          <label class="fitemlabel">产品名称:</label>
		          <select id="productName_goodsprice"  class="easyui-textbox"
					style="width: 200px;"></select>&nbsp;
		</div>
		<div class="fitem">
		          <label class="fitemlabel">产品规格:</label>
		          <select id="productStandardId_goodsprice"  class="easyui-combobox"   name="productStandardId"
					style="width: 200px;"></select>&nbsp;
		</div>
		<!--  
		<div class="fitem">
		          <label class="fitemlabel">规格数量:</label>
		          <select id="productStandardNum_goodsprice"  class="easyui-combobox"   name="productStandardNum"
					style="width: 200px;"></select>&nbsp;
		</div>		-->
		<shiro:hasPermission name="base:productStock:purchasePrice">
			<div class="fitem">
	
			          <label class="fitemlabel">采购价格:</label>
			          <input id="purchasePrice_goodsprice" name="purchasePrice" data-options="precision:2,required:true"  class="easyui-numberbox" style="width: 180px;">&nbsp;元
					  <input  id="purchasePrice_goodsprice_h" type="hidden">
			</div>
		</shiro:hasPermission>
		<div class="fitem">
		          <label class="fitemlabel">销售价格:</label>
		          <input  id="salePrice_goodsprice" name="salePrice" class="easyui-numberbox"   data-options="precision:2,required:true" style="width: 180px;">&nbsp;元
		</div>	
		<div class="fitem">
		          <label class="fitemlabel">零售价格:</label>
		          <input  id="retailPrice_goodsprice" name="retailPrice" class="easyui-numberbox"   data-options="precision:2,required:true" style="width: 180px;">&nbsp;元
		</div>		
	</form>
	
	
	
	
	<div style="margin: 10px 0;">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
			style="width: 255px; height: 32px" onclick="submitgoodspriceForm()">确认提交</a>
	</div>
</div>

<div id="queryProducts_goodsprice" class="easyui-dialog" title="选择产品" 
	data-options="modal:true,closed:true,iconCls:'icon-document'"
	style="width: 600px; padding: 2px;" >
&nbsp;产品名称：&nbsp;
<input id="productName_product_goodsprice" name="productName" class="easyui-textbox" style="width: 200px">
<a href="#" class="easyui-linkbutton" iconcls="icon-search" onclick="loadProduct_goodsprice()">搜索</a>
<table id="productList_goodsprice" ></table>
<br/><br/>
</div>

<script type="text/javascript">
var bSelectOne_standardDetail = undefined;
$(function(){
	$("#searchProductCategoryId_productPrice").combobox({
		url:'product_category/listForSelecting',
		method:'post',
		valueField:'productCategoryId',
		textField:'productCategoryName',
		onBeforeLoad : function(param) {
			param.companyId = $("#index_user_companys").combobox('getValue'); 
		},
		filter: function(q, row){
			var opts = $(this).combobox('options');
			return row[opts.textField].indexOf(q) == 0;
		},
		onLoadSuccess: function() {
			$('#searchProductCategoryId_productPrice').combobox('setValue', null);
		}
	}); 
	
	loadgoodspriceData();
});

	//新增或修改产品规格，确认提交
	function submitgoodspriceForm() {
		$('#goodspriceForm').form('submit', {
			url : 'goodsprice/addgoodsprice',
			onSubmit : function(param) {
				return $(this).form('enableValidation').form('validate');
			},
			success : function(data) {
				var msg = eval('(' + data + ')');
				$.messager.alert('信息提示', msg.msg, 'info');
				if (msg.code == 1) {
					$('#addgoodsprice').window('close');
					$('#goodspricelist').datagrid('reload');
				}
			}
		});
	}

	// 删除规格明细
	function delgoodsprice() {
		var row = $('#goodspricelist').datagrid('getSelected');
		if (row) {
			$.messager.confirm('删除商品价格', '确认删除?', function(r) {
				if (r) {
					$.post("goodsprice/delGoodsPrice", {
						productStandardDetailId : row.productStandardDetailId
					}, function(data) {
						var msg = eval('(' + data + ')');
						$.messager.alert('信息提示', msg.msg, 'info');
						if (msg.code == 1) {
							$('#goodspricelist').datagrid('reload');
						}
					})
				}
			});
		} else {
			$.messager.alert('信息提示', '请选择一行记录！', 'info');
		}

	}
	//打开新增规格明细窗口
	function opengoodspriceWindow() {
		
		bSelectOne_standardDetail = 0;
		
		$("#goodspriceForm").form('clear').form('disableValidation');
		
		$('#purchasePrice_goodsprice').numberbox('setValue', 0.00);
		$('#salePrice_goodsprice').numberbox('setValue', 0.00);
		$('#retailPrice_goodsprice').numberbox('setValue', 0.00);
		
		initWindowcomponent_standard_detail();
		$('#addgoodsprice').window({
			modal:true,
			closed:true,
			iconCls:'icon-document',
			title:'新增商品价格'
		});


		
		$('#addgoodsprice').window('open');
	}

	function initWindowcomponent_standard_detail(){
		
		$("#productStandardId_goodsprice").combobox({url:""});
		$("#productStandardId_goodsprice").combobox({data:[]});
		
		

		$("#productName_goodsprice").textbox({
			editable:false,
			required:true,
			disabled:false,
			icons:[{
				iconCls:'icon-search',
				handler: function(e){
                    $('#productName_product_goodsprice').textbox('clear');
                    
					$('#queryProducts_goodsprice').window('open'); 
					
					loadProduct_goodsprice();
				}
			}]
		});
		$("#productStandardId_goodsprice").combobox({
			method : 'post',
			editable:false,
			disabled:false,
			valueField: 'productStandardId',
			textField: 'productStandardFullName',
			required:true,
			onLoadSuccess: function(data){
				//默认选中第一行
				if(data.length >0 ){
					if( bSelectOne_standardDetail != undefined){
						$(this).combobox('select',data[0].productStandardId);
					}
				}
			},
			onSelect:function(data){
				$("#productStandardDetailId_goodsprice").val(data.productStandardDetailId);
			}
			
		});

		
	}
	
	function loadProduct_goodsprice(){
		$('#productList_goodsprice').datagrid(
				{
					url : "goodsprice/findProductIdList",
					method : 'post',
					rownumbers : true,
					pageSize : 10,
					pageList : [ 10, 20, 50 ],
					pagination : true,
					pagePosition:'bottom',
					multiSort : true,
					fitColumns : true,
					iconCls : 'icon-ok',
					singleSelect : true,
					onBeforeLoad : function(param) {
						param.companyId = $("#index_user_companys").combobox("getValue"); 
						param.productName = $("#productName_product_goodsprice").val();
					},
					onDblClickRow: function(index,row){
						$('#productName_goodsprice').textbox('setValue', row.productName);
						$('#hidden_productId_goodsprice').val(row.productId);
						$('#queryProducts_goodsprice').window('close');
						
						$("#productStandardId_goodsprice").combobox("clear");
						var url = 'goodsprice/findProductStandardIdList?companyId='+ $("#index_user_companys").combobox('getValue')+'&productId='+row.productId;
						$("#productStandardId_goodsprice").combobox('reload',url);
					},
					columns : [ [ {
						field : 'productId',
						title : '产品ID',
						hidden : true
					},{
						field : 'productName',
						title : '产品名称',
						width : 100,
						sortable : true 
					}  ] ]
				});
	}
	
	//编辑商品价格
	function editgoodsprice() {
		var row = $('#goodspricelist').datagrid('getSelected');
		if (row) {
			bSelectOne_standardDetail = undefined;
			$('#goodspriceForm').form('clear');
			initWindowcomponent_standard_detail();
			
			$('#goodspriceForm').form('load', {
				productStandardDetailId:row.productStandardDetailId,
				'salePrice': row.salePrice,
				'purchasePrice':row.purchasePrice,
				'retailPrice':row.retailPrice
			});
			
			$("#productName_goodsprice").combobox({disabled:true});		
			$('#productName_goodsprice').textbox('setValue', row.product.productName);
			
			$("#productStandardId_goodsprice").combobox({disabled:true});		
			$('#productStandardId_goodsprice').combobox('setValue', row.productStandard.productStandardName);
			
			$("#productStandardNum_goodsprice").combobox({disabled:true});		
			$('#productStandardNum_goodsprice').combobox('setValue',row.productStandardNum);			
			

			$('#addgoodsprice').window({
				modal:true,
				closed:true,
				iconCls:'icon-document',
				title:'修改商品价格'
			});


			
			
			$('#addgoodsprice').window('open');
		} else {
			$.messager.alert('信息提示', '请选择一行记录！', 'info');
		}
	}
	/**
	 * Name 载入数据
	 */
	function loadgoodspriceData(){
	   <shiro:hasPermission name="base:goodsprice:edit">
			 $('#goodspricelist').datagrid({
				onDblClickCell: function(index,field,value){
					editgoodsprice();
				}
			 });
		</shiro:hasPermission>
		
		$('#goodspricelist').datagrid({
			url : 'goodsprice/findAllList',
			rownumbers : true,
			pageSize : 10,
			pageList : [ 10, 20, 50 ],
			pagination : true,
			singleSelect : true,
			multiSort : true,
			fitColumns : true,
			fit : true,
			toolbar : '#goodspriceBar',
			onBeforeLoad : function(param) {
				param.companyId = $('#index_user_companys').combobox('getValue');
				param.salePrice="0";
				param.productCategoryId = $("#searchProductCategoryId_productPrice").combobox('getValue');
				param.productName = $("#productName_productPrice").val();
			},
			columns : [ [
			 			{
			 				field : 'productName',
			 				title : '产品名称',
			 				width : 100,
			 				sortable : true,
			 				formatter : function(val, row, index) {
								return row.product.productName;
							}
			 			},
			 			{
			 				field : 'productStandardNum',
			 				title : '产品规格数量',
			 				width : 100,
			 				sortable : true
			 			},
			 			{
			 				field : 'productStandardName',
			 				title : '产品规格名称',
			 				width : 100,
			 				sortable : true,
			 				formatter : function(val, row, index) {
								return row.productStandard.productStandardName;
							}
			 			},
			 			{
			 				field : 'purchasePrice',
			 				title : '采购价格',
			 				width : 100,
			 				hidden: true,
			 				sortable : true,
			 				formatter : function(val, row, index) {
								return row.purchasePrice+"元";
							}
			 			},
			 			{
			 				field : 'salePrice',
			 				title : '销售价格',
			 				width : 100,
			 				sortable : true,
			 				formatter : function(val, row, index) {
								return row.salePrice+"元";
							}
			 			},
			 			{
			 				field : 'retailPrice',
			 				title : '零售价格',
			 				width : 100,
			 				sortable : true,
			 				formatter : function(val, row, index) {
								return row.retailPrice + "元";
							}
			 			}

			 			] ]
		});
		if($('#purchasePrice_goodsprice_h').length > 0){
			$("#goodspricelist").datagrid('showColumn', 'purchasePrice');
		}
	}
	
	
	
</script>