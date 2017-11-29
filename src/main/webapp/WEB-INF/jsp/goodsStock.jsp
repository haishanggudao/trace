<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<link rel="stylesheet" type="text/css" href="static/css/datagrid_util.css">

<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center',border:false">
		<div id="goodsStock-toolbar">
			<div class="wu-toolbar-button">
				<shiro:hasPermission name="trans:goodsStock:edit">
					<a href="#" class="easyui-linkbutton" iconCls="icon-edit-s1" onclick="editGoodsStock()" >修改商品库存</a> 
				</shiro:hasPermission>			
				&nbsp;
				<label class="fitemlabel">商品名称:</label>
				<input class="easyui-textbox" id="goodsStock_searchName">
				<label class="fitemlabel">商品批次:</label>
				<input class="easyui-textbox" id="goodsStock_searchBatch">
				<a href="#" class="easyui-linkbutton" iconcls="icon-search" onclick="loadGoodsStock()">搜索</a>
			</div>
		</div>
		<table id="goodsStockList" title="商品库存列表"></table>
	</div>
</div>
<div id="goodsStockEditDialog" class="easyui-dialog" title="修改商品库存"
	data-options="modal:true,closed:true,iconCls:'icon-document'"
	style="width: 350px; padding: 20px;">
	<form id="goodsStockForm" method="post">
		<input type="hidden" name="goodsStockId">
		<table>
			<tr height="35">
				<td width="70" align="right">产品：</td>
				<td>
					<input class="easyui-textbox" style="width: 200px;" name="productName" data-options="disabled:true">
				</td>
			</tr>
			<tr height="35">
				<td align="right">规格明细：</td>
				<td>
					<input class="easyui-textbox" style="width: 200px;" name="standardDetailName" data-options="disabled:true">
				</td>
			</tr>
			<tr height="35">
				<td align="right">商品批次：</td>
				<td>
					<input class="easyui-textbox" style="width: 200px;" name="batch" data-options="disabled:true">
				</td>
			</tr>
			<tr height="35">
				<td align="right">库存数量：</td>
				<td>
					<input name="stockNum" class="easyui-numberbox" style="text-align: right; width: 200px;" min=1; data-options="required:true">
				</td>
			</tr>
		</table>
	</form>
</div>
<script>
$(function() {
	loadGoodsStock();
});

function editGoodsStock(){
	var row = $('#goodsStockList').datagrid('getSelected');
	if(row){
		$("#goodsStockForm").form('load',{
			goodsStockId:row.goodsStockId,
			productName:row.product.productName,
			standardDetailName:row.productStandardDetail.fullStandardName,
			batch:row.goodsBatch,
			stockNum:row.stockNum
		});
		$('#goodsStockEditDialog').dialog({
			closed : false,
			modal : true,
			title : "修改商品库存",
			buttons : [ {
				text : '确定',
				iconCls : 'icon-ok',
				handler : submitGoodsStock
			}, {
				text : '取消',
				iconCls : 'icon-cancel',
				handler : function() {
					$('#goodsStockEditDialog').dialog('close');
				}
			} ]
		});
	}else{
		$.messager.alert('信息提示', '请选择商品库存！', 'info');
	}
	
}
function submitGoodsStock(){
	$('#goodsStockForm').form(
			'submit',
			{
				url : 'goodsStock/updateGoodsStock',
				onSubmit : function(param) {
					param.companyId = $('#index_user_companys').combobox('getValue');
					return $(this).form('enableValidation').form('validate');
				},
				success : function(data) {
					var msg = eval('(' + data + ')');
					$.messager.alert('信息提示', msg.msg, 'info');
					if (msg.code == 1) {
						$('#goodsStockEditDialog').dialog('close');
						$('#goodsStockList').datagrid('reload');
					}
				}
			});
}
function loadGoodsStock() {
	<shiro:hasPermission name="trans:goodsStock:edit">
	 $('#goodsStockList').datagrid({
		onDblClickCell: function(index,field,value){
			editGoodsStock()
		},
	 });
	</shiro:hasPermission>
	$('#goodsStockList').datagrid({
		method:'post',
		url:'goodsStock/findAllItemlist',
		rownumbers : true,
		pagination : true,
		pageSize : 10,
		pagePosition : 'bottom',
		pageList : [ 10, 20, 50 ],
		multiSort : true,
		fitColumns : true,
		fit : true,
		iconCls : 'icon-ok',
		singleSelect : true,
		onBeforeLoad:function(param){
			var companyId=$("#index_user_companys").combobox('getValue');
			param.goodsBatch=$('#goodsStock_searchBatch').val();
			param['product.productName']=$('#goodsStock_searchName').val();
			param.companyId=companyId;
		},
        toolbar : '#goodsStock-toolbar',
		columns : [ [ {
        	field:'goodsId',
        	title:'产品',
        	width:100,
        	formatter : function(val, row, index) {
				return row.product.productName;
			}
        },{
			field : 'productStandardNum',
			title : '规格明细',
			width : 100,
			sortable : true,
			formatter : function(val, row, index) {
				return row.productStandardDetail.fullStandardName;
			}
		},{
			field: 'goodsBatch',
			title: '商品批次号',
        	width: 100 
		},
        {
        	field:'stockNum',
        	title:'库存数量',
        	width:100 
        } ] ]
    });
}
</script>