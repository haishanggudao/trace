<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<link rel="stylesheet" type="text/css" href="static/css/datagrid_util.css">
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center',border:false">
		<div id="purchaseReport-toolbar">
			<div class="wu-toolbar-button">  
				<a href="#" class="easyui-linkbutton" iconcls="icon-search" onclick="openSelectingDialog_purchaseReport()">高级搜索</a> 
				
				<a href="#" class="easyui-linkbutton" iconcls="icon-search" onclick="exportExcel_purchaseReport()">输出</a>
			</div>
		</div>
		<table id="purchaseReportList" title="采购单列表"></table>
	</div>
</div>

<div id="searchDialog_purchaseReport" class="easyui-dialog fm" title="高级搜索" 
data-options="modal:true,closed:true,iconCls:'icon-search'"  
style="width:800px;padding:20px" buttons="#searchDialog_buttons_purchaseReport">
	<form id="frm_purchaseSearch_purchaseReport" method="post">
		<div class="ftitle">高级搜索信息</div>
		<div class="fitem">
        	<label class="fitemlabel width_100">采购单号:</label>
            <input id="s_purchaseOrderNo_purchaseReport" class="easyui-textbox" style="width:180px;" data-options="prompt:'采购单号',validType:length[1,255]">
            &nbsp;
            <label class="fitemlabel width_100">订单日期:</label> 
            <input id="s_orderTimeStart_purchaseReport" class="easyui-datebox" style="width:100px;" data-options="prompt:'订单日期',validType:length[1,255]">
            <input id="s_orderTimeEnd_purchaseReport" class="easyui-datebox" style="width:100px;" data-options="prompt:'订单日期',validType:length[1,255]">
        </div>
        <div class="fitem">
        	<label class="fitemlabel width_100">供应商名称:</label>
            <input id="s_supplierName_purchaseReport" class="easyui-textbox" style="width:180px;" data-options="prompt:'供应商名称',validType:length[1,255]">
            &nbsp;
            <label class="fitemlabel width_100">商品名称:</label>
            <input id="s_goodsName_purchaseReport" class="easyui-textbox" style="width:180px;" data-options="prompt:'商品名称',validType:length[1,255]">
        </div>
	</form>
</div>
<div id="searchDialog_buttons_purchaseReport"  style="text-align: center;">
	<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-search" onclick="searchform_purchaseReport()" style="width:90px">搜索</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" onclick="javascript:$('#frm_purchaseSearch_purchaseReport').form('clear')" style="width:90px">重置</a>
</div>

<script>
var initFlag_purchaseReport=0;
var editIndex_purchaseReport = undefined;

$(function() {
	loadPurchaseReport();
});

/**
 * 载入采购明细信息
 */
function loadPurchaseReport() {
	$('#purchaseReportList').datagrid({
		method:'post',
		url:'purchaseReport/findAllList',
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
			param.companyId=companyId;
		},
        detailFormatter:function(index,row){
            return '<div style="padding:2px"><table class="ddv"></table></div>';
        },
        toolbar : '#purchaseReport-toolbar',
		columns : [ [ {
			field : 'purchaseOrderNo',
			title : '采购单号',
			width : 100,
			sortable : true,
			formatter:function(value,row){
				return row.purchaseOrder.purchaseOrderNo;
			}
		},{
			field : 'productName',
			title : '产品名称',
			width : 100,
			sortable : true 
		}, {
			field : 'orderTime',
			title : '下单时间',
			width : 100,
			sortable : true,
			formatter:function(value,row){
				return row.purchaseOrder.orderTime;
			}
		} ] ],
		onExpandRow: function(index,row){
            var ddv = $(this).datagrid('getRowDetail',index).find('table.ddv');
            ddv.datagrid({
                url:'purchase_order/findAllItemlist',
        		fitColumns : true,
				singleSelect : true,
				rownumbers : true,
				pagination : true,
				pageSize : 5,
				pageList : [ 5, 10, 20 ],
				loadMsg : '页面正在加载....',
				height : 'auto',
				onBeforeLoad : function(param) {
					param.purchaseOrderId = row.purchaseOrderId;
				},
                columns:[[
                    {
                    	field:'product',
                    	title:'产品',
                    	width:200,
                    	formatter:function(value,row){
                    		return row.product.productName;
                    	}
                    },
                    {
                    	field:'standard',
                    	title:'产品规格',
                    	width:200,
                    	formatter:function(value,row){
                    		return row.standardDetail.fullStandardName;
                    	}	
                    },
                    {field:'quantity',title:'采购数量',width:100}
                ]],
                onResize : function() {
					$('#purchaseReportList').datagrid('fixDetailRowHeight',index);
				},
				onLoadSuccess : function() {
					setTimeout(function() {
								$('#purchaseReportList').datagrid('fixDetailRowHeight',index);
					}, 0);
				}
            });
            $('#purchaseReportList').datagrid('fixDetailRowHeight',index);
        }
    });
}

//open the dialog for selecting
function openSelectingDialog_purchaseReport(){  
	$('#searchDialog_purchaseReport').dialog('open'); 
}

//advanced query
function searchform_purchaseReport(){
	$('#purchaseReportList').datagrid({  
	    url:'purchaseReport/findAllList',  
	    queryParams:{
	    	companyId : $("#index_user_companys").combobox('getValue')
		}});
		$('#searchDialog_purchaseReport').dialog('close');
	}

	function exportExcel_purchaseReport() {
		location.href = "purchaseReport/excelExport";
	}
</script>