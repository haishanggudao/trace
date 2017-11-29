<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<link rel="stylesheet" type="text/css" href="static/css/datagrid_util.css">


<table id="storeSaleOrderlist" title="门店销售记录列表" style="width: auto; height: 350px">
</table>



<script type="text/javascript">

	var initFlag_standard=0;
	$(function(){
		loadproductStandardData();
	});
	/**
	 * Name 载入数据
	 */
	function loadproductStandardData(){
		$('#storeSaleOrderlist').datagrid({
			url : 'storeSaleOrder/findAllList',
			view : detailview,
			rownumbers : true,			
			pageSize : 10,
			pagePosition : 'bottom',
			pageList : [ 10, 20, 30 ],
			pagination : true,
			multiSort : true,
			fitColumns : true,
			singleSelect : true,
			fit : true,
			//toolbar : '#productStandardBar',
			onBeforeLoad : function(param) {
	 			var myUserCompanyId = $('#index_user_companys').combobox('getValue');
				param.companyId = myUserCompanyId;
			},
			detailFormatter:function(index,row){
                return '<div style="padding:2px"><table class="ddv"></table></div>';
            },
			columns : [ [
						{
							field : 'customerAlias',
							title : '门店名称',
							width : 100,
							sortable : true
						},
			 			{
			 				field : 'storeSaleOrdeNo',
			 				title : '销售编号',
			 				width : 100,
			 				sortable : true
			 			},
			 			{
			 				field : 'orderTime',
			 				title : '订单日期',
			 				width : 100,
			 				sortable : true
			 			},
			 			{
			 				field : 'totalPrice',
			 				title : '订单总额',
			 				width : 100,
			 				sortable : true
			 			},
			 			{
			 				field : 'discountPrice',
			 				title : '折扣金额',
			 				width : 100,
			 				sortable : true
			 			},
			 			{
			 				field : 'payType',
			 				title : '付款类型',
			 				width : 100,
			 				sortable : true
			 			}
	
			 			] ],
			onExpandRow: function(index,row){
				var ddv = $(this).datagrid('getRowDetail',index).find('table.ddv');
				ddv.datagrid({
					url:'storeSaleOrder/findAllListItem',
            		fitColumns : true,
					singleSelect : true,
					rownumbers : true,
					pagination : true,
					pageSize : 5,
					pageList : [ 5, 10, 20 ],
					loadMsg : '页面正在加载....',
					height : 'auto',
					onBeforeLoad : function(param) {
						param.storeSaleOrderId = row.storeSaleOrderId;
					},
					columns:[[
		                        {
		                        	field:'productName',
		                        	title:'产品',
		                        	width:100,
		                        	formatter:function(value,row){
		                        		return row.productName;
		                        	}
		                        }, 
		                        {field:'quantity',title:'销售数量',width:100},
		                        {field:'unitName',title:'产品规格',width:100},
		                        {
		                        	field:'salePrice',
		                        	title:'单价',
		                        	width:100,
		                        	formatter:function(value,row){
		                        		return row.salePrice + '元';
		                        	}
		                        },
		                        {
		                        	field:'totalPrice',
		                        	title:'销售价格',
		                        	width:100,
		                        	formatter:function(value,row){
		                        		return row.totalPrice + '元';
		                        	}
		                        }
		                    ]],
                    onResize : function() {
						$('#storeSaleOrderlist').datagrid('fixDetailRowHeight',index);
					},
					onLoadSuccess : function() {
						setTimeout(function() {
							$('#storeSaleOrderlist').datagrid('fixDetailRowHeight',index);
						}, 0);
					}
				});
				$('#storeSaleOrderlist').datagrid('fixDetailRowHeight',index);
			}
		});
	}
	
	
	
</script>