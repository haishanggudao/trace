<%@ page contentType="text/html;charset=UTF-8" language="java"%> 
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<link rel="stylesheet" type="text/css" href="static/css/datagrid_util.css">


<style type="text/css">
.productImg ul{
    display: block;
    list-style-type: disc;
    -webkit-margin-before: 1em;
    -webkit-margin-after: 1em;
    -webkit-margin-start: 0px;
    -webkit-margin-end: 0px;
    -webkit-padding-start: 40px;
    margin-top: 0;
    margin-bottom: 10px;
}
.productImg ul.items li img {
    width: 160px;
    height: 160px;
}
.productImg ul.items li {
    position: relative;
    padding: 0px;
    width: 160px;
    float:left;
    margin: 0px 15px 30px 15px;
    border: 1px solid #e8e8e8;
    cursor: pointer;
    overflow: hidden;
    display: list-item;
    text-align: -webkit-match-parent;    
}
.list-inline {
    padding-left: 0;
    margin-left: -5px;
    list-style: none;
}
.productImg ul.items li p {
    font-family: Arial,"宋体";
    font-size: 12px;
    text-align: center;
}
</style>

	<div id="storeProductbar" style="padding: 5px 0;">
	  		<shiro:hasPermission name="base:storeProduct:supplierProduct">
	 		<a href="#"	class="easyui-linkbutton" iconCls="icon-add-s1"	onclick="storeProductChoose()">选择供应商产品</a>
	 	</shiro:hasPermission> 
	 	<shiro:hasPermission name="base:storeProduct:delete">
	 		<a href="#"	class="easyui-linkbutton" iconCls="icon-delete-s1"	onclick="storeProductRemove()">删除产品</a>
	 	</shiro:hasPermission>  
		<shiro:hasPermission name="base:storeProduct:list">
			&nbsp;产品名称：&nbsp;<input id="storeProductName_storeProduct" name="storeProductName" class="easyui-textbox" style="width: 200px">
			<a href="#" class="easyui-linkbutton" iconcls="icon-search" onclick="loadstoreProduct(1)">搜索</a>
		</shiro:hasPermission> 
	</div>
	<table id="storeProductList" style="width: 100%;" title="产品列表"></table>
	
	<div id="supplierProductDialog" class="easyui-dialog" 
		data-options="closed:true,iconCls:'icon-document'" title="选择供应商产品" style="width: 500px;height:460px; padding: 10px;">
	<div class="easyui-layout" style="width:100%; " data-options="fit:true">
	    <div data-options="region:'west'" title="供应商" style="width:47%; ">
	        <table id="supplierTree_storeProduct" class="easyui-treegrid">
		        <thead>
		            <tr>
		                <th data-options="field:'supplierAlias'"></th>
		            </tr>
		        </thead>
	    		</table>
	    </div>
	    <div data-options="region:'center'" title="供应商产品">
	    	<table id="product_storeProduct"></table>
	    </div>
	    </div>
	</div>
<script type="text/javascript">
$(function() {

	loadstoreProduct();
	
});


function storeProductChoose(){
	$("#supplierTree_storeProduct").treegrid({
		url: 'supplier/list',
		method: 'post',
		idField: 'supplierId',
        treeField: 'supplierAlias',
        fit:true,
        onBeforeLoad : function(row, param ) {
			param.companyId = $('#index_user_companys').combobox('getValue'); 
		},
		onLoadSuccess:function(row,data){
			if(data.length!=0){
				$(this).treegrid('select',data[0].supplierId);
				loadChoooseSupplierProduct(data[0].supplierCompanyId);
			}
		},
		onClickRow : function(row) {
			loadChoooseSupplierProduct(row.supplierCompanyId);
		}
	});
	$("#supplierProductDialog").dialog({
		closed : false,
		modal : true,
		title : "新增供应商产品",
		buttons : [{
			text:'确定',
			iconCls : 'icon-ok',
			handler : function() {
				var res=$("#product_storeProduct").datagrid('getSelections');
				if(res.length!=0){
					var params={};
					params.companyId=$('#index_user_companys').combobox('getValue');
					params.products=JSON.stringify(res);
					$.post("productCompany/addProducts",params,function(data){
						$.messager.alert('信息提示', data.msg, 'info');
						if (data.code == 1) {
							$('#supplierProductDialog').window('close');
							$('#storeProductList').datagrid('reload');
						}
					});
				}else{
					$.messager.alert('信息提示', '请至少选择一个产品！', 'info');
				}
			}
		}, {
			text : '取消',
			iconCls : 'icon-cancel',
			handler : function() {
				$('#supplierProductDialog').dialog('close');
			}
		} ]
	});
}

	function storeProductRemove(){
		var row = $('#storeProductList').datagrid('getSelected');
		if (row) {
			$.messager.confirm('信息提示', '确定要删除该产品 ？', function(result) {
				if (result) {
					$.post("productCompany/delete", {
						productId : row.productId,
						companyId : $('#index_user_companys').combobox('getValue')
					}, function(data) {
						$.messager.alert('信息提示', data.msg, 'info');
						if (data.code == 1) {
							$('#storeProductList').datagrid('reload');
						}
					});
				}
			});
		}else {
			$.messager.alert('信息提示', '请选择产品！', 'info');
		}
	}

	function loadChoooseSupplierProduct(supplierCompanyId){
		$("#product_storeProduct").datagrid({
			url : 'productCompany/findAllNotCheckedProducts',
			method : 'post',
			rownumbers : true,
			pageSize : 10,
			checkbox:true,
			pageList : [ 10, 20, 50 ],
			pagination : true,
			pagePosition:'bottom',
			multiSort : true,
			fitColumns : true,
			fit:true,
			idField:'productId',
			iconCls : 'icon-ok',
			onBeforeLoad : function(param) {
				param.companyId = $('#index_user_companys').combobox('getValue');
				param.supplierCompanyId=supplierCompanyId;
			},
			columns : [ [ {
				field:'ck',
				checkbox:true
				},{
				field : 'productName',
				title : '产品名称',
				width : 100
			}  ] ]
		});
	}

	/**
	 * load the storeProducts; 
	 */
	function loadstoreProduct() {
		<shiro:hasPermission name="base:storeProduct:edit">
		 $('#storeProductList').datagrid({
				onDblClickCell: function(index,field,value){
					storeProductEdit()
				}
		 });
		</shiro:hasPermission>

		$('#storeProductList').datagrid(
				{
					url : 'storeProduct/findAllList',
					method : 'post',
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
					toolbar : '#storeProductbar',
					onBeforeLoad : function(param) {
						param.companyId = $("#index_user_companys").combobox(
								"getValue");
						param.productName = $("#storeProductName_storeProduct").val();
					},
					columns : [ [ {
						field : 'productId',
						title : '产品ID',
						hidden : true
					}, {
						field : 'productName',
						title : '产品名称',
						width : 100,
						sortable : true
					}, {
						field : 'productCode',
						title : '产品代码',
						width : 50,
						sortable : true
					}, {
						field : 'productShortName',
						title : '产品简称',
						width : 100
					}, {
						field : 'productCategoryName',
						title : '产品分类',
						width : 100,
						sortable : true,
						formatter : function(val, row, index) {
							return row.category.productCategoryName;
						}
					}, {
						field : 'productImageUrl',
						title : '图片URL',
						width : 100
					}, {
						field : 'status',
						title : '状态',
						width : 100,
						formatter : function(val, row, index) {
							if (val == 0) {
								return "启用";
							} else {
								return "禁用";
							}
						}
					} ] ]

				});
	}
</script>
