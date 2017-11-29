<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<link rel="stylesheet" type="text/css" href="static/css/datagrid_util.css">

<!-- layout  -->
<div class="easyui-layout" style="width:100%; " data-options="fit:true">
	<div id="p_productStock" data-options="region:'west',split:true" title="销售门店" style="width:20%; ">
		<table class="easyui-treegrid" 
            data-options="
                url: 'customers/findCustomerListIncludeCompany',
                method: 'get',
                rownumbers: true,
                idField: 'customerId',
                treeField: 'customerAlias',
                fit:true,
                onBeforeLoad : function(row, param ) {
					param.companyId = $('#index_user_companys').combobox('getValue');  
				},
				onClickRow : function(row) {
					if(row.customerId==$('#index_user_companys').combobox('getValue')){
						loadProductStock(row.customerId);
					}else{
						loadStoreProductStock(row.customerId);
					}
					
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
	<div data-options="region:'center'" title="产品库存列表">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center',border:false">
				<div id="productStock-toolbar">
					<div class="wu-toolbar-button"> 
						&nbsp;
						<shiro:hasPermission name="trans:product_stock:list"> 
							&nbsp;产品名称：&nbsp;
							<input id="productName_productStock" class="easyui-textbox" style="width: 200px" >
							<a href="#" class="easyui-linkbutton" iconcls="icon-search" onclick="search_productStock()">搜索</a>
						</shiro:hasPermission> 
					</div>
				</div>
				<table id="productStockList" title="产品库存列表"></table>
			</div>
		</div>
	</div>
</div> 


<script>
$(function() {
	
	loadProductStock(); 
	
	$('#productName_productStock').textbox({
	    inputEvents:$.extend({},$.fn.textbox.defaults.inputEvents,{
	    	keypress: function (e) {
	    		if (e.keyCode == 13) {
	    			search_productStock();
	    		}
	    	}
	    })
	})
});

function loadProductStock(customerId) {
	$('#productStockList').datagrid({
		method:'post',
		url:'productStock/findAllList',
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
			if(customerId){
				companyId=customerId;
			}
			param.companyId=companyId;
		},
		onLoadSuccess: function(){
            $(this).datagrid('showColumn','_expander');
		},
        detailFormatter:function(index,row){
            return '<div style="padding:2px"><table class="ddv"></table></div>';
        },
        toolbar : '#productStock-toolbar',
		columns : [ [ {
			field : 'productId',
			title : '产品',
			width : 100,
			sortable : true,
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
		}, {
			field : 'stockNum',
			title : '库存数量',
			width : 100,
			sortable : true
		} ] ],
		onExpandRow: function(index,row){
            var ddv = $(this).datagrid('getRowDetail',index).find('table.ddv');
            ddv.datagrid({
                url:'goodsStock/findAllItemlist',
        		fitColumns : true,
				singleSelect : true,
				rownumbers : true,
				pagination : true,
				pageSize : 5,
				pageList : [ 5, 10, 20 ],
				loadMsg : '页面正在加载....',
				height : 'auto',
				onBeforeLoad : function(param) {
					param.productId = row.productId;
					param.productStandardDetailId = row.productStandardDetailId;
				},
                columns:[[
                    {
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
                    } 
                ]],
                onResize : function() {
					$('#productStockList').datagrid('fixDetailRowHeight',index);
				},
				onLoadSuccess : function() {
					setTimeout(function() {
								$('#productStockList').datagrid('fixDetailRowHeight',index);
					}, 0);
				}
            });
            $('#productStockList').datagrid('fixDetailRowHeight',index);
        }
    }); 
}


function loadStoreProductStock(customerId) {
	$('#productStockList').datagrid({
		method:'post',
		url:'productStock/findAllList',
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
		onLoadSuccess: function(){
            $(this).datagrid('hideColumn','_expander');
		},
		onBeforeLoad:function(param){
			var companyId=$("#index_user_companys").combobox('getValue');
			if(customerId){
				companyId=customerId;
			}
			param.companyId=companyId;
		},
        toolbar : '#productStock-toolbar',
		columns : [ [ {
			field : 'productId',
			title : '产品',
			width : 100,
			sortable : true,
			formatter : function(val, row, index) {
				return row.product.productName;
			}
		},{
			field : 'productStandardNum',
			title : '规格明细',
			width : 100,
			sortable : true,
			formatter : function(val, row, index) {
				return "L";
			}
		}, {
			field : 'stockNum',
			title : '库存数量',
			width : 100,
			sortable : true
		} ] ]
    });
}

//advanced query
function search_productStock(){
	$('#productStockList').datagrid({  
	    url:'productStock/findAllQueryList',  
	    queryParams:{
	    	'productName': $("#productName_productStock").val(), 
        	'company.companyid': $("#index_user_companys").combobox('getValue'),
	    } 
	});
}
</script>