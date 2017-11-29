<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<div id="saleorderoutstockmainBar" style="padding: 5px 0;">
	<shiro:hasPermission name="trans:saleorderoutstockmain:add">
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add-s1'"
			onclick="opensaleorderoutstockmainDialog()">新增销售出库</a> 
	</shiro:hasPermission>
	<shiro:hasPermission name="trans:saleorderoutstockmain:edit">
		<a href="#"class="easyui-linkbutton" data-options="iconCls:'icon-edit-s1'"
			onclick="editsaleorderoutstockmainDialog()">编辑销售出库</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="trans:saleorderoutstockmain:delete">
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-delete-s1'"
			onclick="delsaleorderoutstockmain();">删除销售出库</a> 
	</shiro:hasPermission>
	<shiro:hasPermission name="trans:saleorderoutstockmain:qrCode">
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-codeqr'"
			onclick="displaysaleorderoutstockmainQRcode()">二维码</a> 
	</shiro:hasPermission>			
	<shiro:hasPermission name="trans:saleorderoutstockmain:count">
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
			onclick="countOutstockDetail()">统计销售出库</a>
	</shiro:hasPermission>	
</div>

<!-- start of table list -->
<table id="saleorderoutstockmainList" title="销售单列表"
	style="width: auto; height: 350px">
</table>
<!-- end of table list -->
<div id="outstockCountDialog" style="width: 50%;">
	<table id="outstockCountgrid" style="width: 100%;"></table>
</div>
<!-- begin of adding dialog -->
<div id="addsaleorderoutstockmainDialog"
	style="width: 600px; padding: 20px;">
	<form id="saleorderoutstockmainForm" method="post">
		<input type="hidden" id="saleorderoutstockmainId_so"
			name="saleOrderId" />
		<table>
			<tr>
				<td align="right">销售单编号：</td>
				<td><input class="easyui-textbox"
					id="saleorderoutstockmainNo_so" name="saleOrderNo"
					style="width: 266px" data-options="required:true" /></td>
			</tr>
			<!-- <tr>
				<td align="right">客户：</td>
				<td><input id="customerId_so2" name="customerId"
					style="width: 266px;"></td>
			</tr> -->
			<tr>
				<td align="right">物流企业：</td>
				<td><input id="logisticsId_so2" name="logisticsId"
					style="width: 266px;"></td>
			</tr>
			<tr>
				<td align="right">下单时间：</td>
				<td><input type="text" id="orderTime_so2" name="orderTime"
					style="width: 266px;"></td>
			</tr>
			<!-- <tr>
				<td align="right">出库时间：</td>
				<td><input type="text" id="outstockdate_so2"
					name="outstockDate" style="width: 266px;"></td>
			</tr> -->
			<tr>
				<td align="right">追溯码：</td>
				<td><input class="easyui-textbox" id="traceCode_so2"
					name="traceCode" style="width: 266px" data-options="required:true" />
				</td>
			</tr>
		</table>
	</form>
	<div id="OutstockItemsDiv" style="width: 100%; height: 200px;">
		<table id="OutstockItemsgrid" title="商品出库明细列表" style="width: 100%;"></table>
	</div>
</div>
<!-- end of adding dialog -->


<script type="text/javascript">
	function getBatchNo() {
		var dt = new Date();
		var month = dt.getMonth() + 1;
		if (month <= 9) {
			month = '0' + month;
		}

		var day = dt.getDate();
		if (day <= 9) {
			day = '0' + day;
		}

		var hour = dt.getHours();
		if (hour <= 9) {
			hour = '0' + hour;
		}

		var minute = dt.getMinutes();
		if (minute <= 9) {
			minute = '0' + minute;
		}

		var second = dt.getSeconds();
		if (second <= 9) {
			second = '0' + second;
		}

		var batchNo = dt.getFullYear() + '' + month + '' + day + '' + hour + ''
				+ minute + '' + second;
		return batchNo;
	}

	var initFlag_saleoutstock = 0;

	$(function() {
		loadsaleorderoutstockmains();
	});

	function countOutstockDetail() {
		var row = $('#saleorderoutstockmainList').datagrid('getSelected');
		if (row) {
			$("#outstockCountgrid").datagrid({
				url : 'saleorderoutstockmain/getClintCountInfo',
				rownumbers : true,
				singleSelect : true,
				multiSort : true,
				fitColumns : true,
				autoRowHeight:true,
// 				fit : true,
				onBeforeLoad:function(param){
					param.outstockMainId=row.outstockMainId;
				},
				columns : [ [{
					field : 'productName',
					title : '产品名称',
					width : 100
				},{
					field : 'fullStandName',
					title : '规格'
// 					formatter : function(val, row, index) {
// 						return row.company.name;
// 					}
				}, {
					field : 'goodsBatch',
					title : '商品批次',
					width : 100
				}, {
					field : 'deliveryBy',
					title : '送货员',
					width : 100
				}, {
					field : 'totalNum',
					title : '送货数量',
					width : 100
				}] ],
			});
			$("#outstockCountDialog").dialog({
				closed : false,
				modal : true,
				title : "商品出库明细统计",
			});
		}else{
			$.messager.alert('信息提示', '请选择一行记录！', 'info');
		}
	}

	// ***** begin of function *****
	/**
	 * function => UI => initialize the view => the data of loading the sale orders
	 */
	function loadsaleorderoutstockmains() {
	  	<shiro:hasPermission name="trans:saleorderoutstockmain:edit">
			 $('#saleorderoutstockmainList').datagrid({
				onDblClickCell: function(index,field,value){
					editsaleorderoutstockmainDialog()
				}
			 });
		</shiro:hasPermission>
		$('#saleorderoutstockmainList').datagrid({
			url : 'saleorderoutstockmain/findAllList',
			rownumbers : true,
			pageSize : 10,
			pageList : [ 10, 20, 50 ],
			pagination : true,
			singleSelect : true,
			multiSort : true,
			fitColumns : true,
			fit : true,
			toolbar : '#saleorderoutstockmainBar',
			onBeforeLoad : function(param) {
				var myUserCompanyId = $('#index_user_companys')
						.combobox('getValue');
				param.companyId = myUserCompanyId;
			},
			columns : [ [  {
				field : 'saleOrderNo',
				title : '销售单编号',
				width : 100
			}, {
				field : 'name',
				title : '客户',
				width : 100,
				hidden : true,
				formatter : function(val, row, index) {
					return row.company.name;
				}
			}, {
				field : 'logisticsCompanyName',
				title : '物流企业',
				width : 100
			}, {
				field : 'orderTime',
				title : '下单时间',
				width : 100
			}, {
				field : 'outstockDate',
				title : '出库时间',
				width : 100
			}, {
				field : 'traceCode',
				title : '追溯码',
				width : 100
			} ] ],
			view : detailview,
			detailFormatter : function(index, row) {
				return '<div style="padding:2px"><table class="ddv"></table></div>';
			},
			onExpandRow : function(index, row) {
				var ddv = $(this).datagrid('getRowDetail',
						index).find('table.ddv');
				ddv
						.datagrid({
							url : 'saleorderoutstockmain/findallitems?outstockMainId='
									+ row.outstockMainId,
							fitColumns : true,
							singleSelect : true,
							rownumbers : true,
							pagination : true,
							pageSize : 10,
							pageList : [ 10, 20, 30 ],
							loadMsg : '',
							height : 'auto',
							columns : [ [
									{
										field : 'productName',
										title : '产品',
										width : 100,
										formatter : function(
												val, row, index) {
											return row.product.productName;
										}
									},
									{
										field : 'fullStandardName',
										title : '规格明细',
										width : 80,
										formatter : function(
												val, row, index) {
											return row.standardDetail.fullStandardName;
										}
									}, {
										field : 'deliveryTime',
										title : '配送时间',
										width : 100
									} ] ],
							onResize : function() {
								$('#saleorderoutstockmainList')
										.datagrid(
												'fixDetailRowHeight',
												index);
							},
							onLoadSuccess : function() {
								setTimeout(
										function() {
											$(
													'#saleorderoutstockmainList')
													.datagrid(
															'fixDetailRowHeight',
															index);
										}, 0);
							}
						});
				$('#saleorderoutstockmainList').datagrid(
						'fixDetailRowHeight', index);
			}
		});
	}

	/**
	 * function => UI => initialize the dialog for adding
	 */
	function initDialog_saleoutstock() {
		$("#logisticsId_so2").combobox(
				{
					url : 'logistics/findByUserCompanyId',
					valueField : 'logisticsId',
					textField : 'logisticsCompanyName',
					editable : false,
					required:true,
					onBeforeLoad : function(param) {
						var companyId = $("#index_user_companys").combobox(
								'getValue');
						param.companyId = companyId;
					},
					onLoadSuccess : function() {
						var data = $('#logisticsId_so2').combobox('getData');
						if (data && data.length == 1) {
							$('#logisticsId_so2').combobox('setValue',
									data[0].logisticsId);
						}
					}
				});
		$("#orderTime_so2").datetimebox({
			showSeconds : true,
			editable : false
		});
	}

	/**
	 * function => action => submit the sale order
	 */
	function submitsaleorderoutstockmain() {

		$('#saleorderoutstockmainForm').form('submit', {
			url : 'saleorderoutstockmain/addsaleorder',
			onSubmit : function(param) {
				//param.companyId = myUserCompanyId;
				//param.saleItemList = JSON.stringify(row);
				// console.info(param);
				var companyId = $("#index_user_companys").combobox('getValue');
				param.companyId = companyId;
				param.customerId = companyId;

				return $(this).form('enableValidation').form('validate');
			},
			success : function(data) {
				var msg = eval('(' + data + ')');
				$.messager.alert('信息提示', msg.msg, 'info');
				if (msg.code == 1) {
					$('#addsaleorderoutstockmainDialog').dialog('close');
					$('#saleorderoutstockmainList').datagrid('reload');
				}
			}
		});
	}

	/**
	 * button menu => show the dialog
	 */
	function opensaleorderoutstockmainDialog() {
		$('#OutstockItemsDiv').hide();
		if (initFlag_saleoutstock == 0) {
			// call: initialize the dialog for adding
			initDialog_saleoutstock();
// 			initFlag_saleoutstock = 1;
		}
		$('#saleorderoutstockmainForm').form('clear').form('disableValidation');
		
		var dt = new Date();
		var time = dt.getFullYear() + '-' + (dt.getMonth() + 1) + '-'
				+ dt.getDate() + ' ' + dt.getHours() + ':' + dt.getMinutes()
				+ ':' + dt.getSeconds();
		$('#orderTime_so2').datetimebox('setValue', time);
		//$('#outstockdate_so2').datetimebox('setValue', time); 

		var mysaleorderoutstockmainNo = getBatchNo();
		$('#saleorderoutstockmainNo_so').textbox('setValue',
				mysaleorderoutstockmainNo);
		
		// assign value to the trace code
		$('#traceCode_so2').textbox('setValue', mysaleorderoutstockmainNo);
		
		$('#addsaleorderoutstockmainDialog').dialog({
			closed : false,
			modal : true,
			title : "销售单",
			iconCls : 'icon-save',
			buttons : [ {
				text : '确定',
				iconCls : 'icon-ok',
				handler : submitsaleorderoutstockmain
			}, {
				text : '取消',
				iconCls : 'icon-cancel',
				handler : function() {
					$('#addsaleorderoutstockmainDialog').dialog('close');
				}
			} ]
		});
	}

	/**
	 * button menu => edit the selected sale order
	 */
	function editsaleorderoutstockmainDialog() {
		var row = $('#saleorderoutstockmainList').datagrid('getSelected');

		if (row) {
			$('#OutstockItemsDiv').show();
			outstockItemsEditOpen(row.outstockMainId);
			$('#saleorderoutstockmainForm').form('clear').form(
					'disableValidation');

			if (initFlag_saleoutstock == 0) {
				// call: initialize the dialog for adding
				initDialog_saleoutstock();
				initFlag_saleoutstock = 1;
			}

			$('#saleorderoutstockmainForm').form('load', {
				saleOrderId : row.saleOrderId,
				saleOrderNo : row.saleOrderNo,
				customerId : row.customerId,
				orderTime : row.orderTime,
				outstockDate : row.outstockDate,
				traceCode : row.traceCode,
				logisticsId : row.logisticsId
			});

			/* $('#dg_so2').datagrid('reload'); */// reload the current page data 
			$('#addsaleorderoutstockmainDialog').dialog({
				closed : false,
				modal : true,
				title : "销售单",
				buttons : [ {
					text : '确定',
					iconCls : 'icon-ok',
					handler : submitsaleorderoutstockmain
				}, {
					text : '取消',
					iconCls : 'icon-cancel',
					handler : function() {
						$('#addsaleorderoutstockmainDialog').dialog('close');
					}
				} ]
			});

		} else {
			$.messager.alert('信息提示', '请选择一行记录！', 'info');
		}
	}

	/**
	 * button menu => display the QR code
	 */
	function displaysaleorderoutstockmainQRcode() {
		// alert('display the qr code');

		var row = $('#saleorderoutstockmainList').datagrid('getSelected');
		if (row) {
			// console.log(window.open); 
			// window.open("qrservlet?qrtext="+row.saleorderoutstockmainNo);//location.href实现客户端页面的跳转 
			// saleOrderId
			window.open("sale_order/downloadqc?saleorderid=" + row.saleOrderId);
		}
	}

	/**
	 * button menu => delete the sale order
	 */
	function delsaleorderoutstockmain() {
		var row = $('#saleorderoutstockmainList').datagrid('getSelected');
		if (row) {
			$.messager.confirm('删除销售单信息', '确认删除?', function(r) {
				if (r) {
					$.post("saleorderoutstockmain/delsaleorder", {
						saleOrderId : row.saleOrderId
					}, function(data) {
						var msg = eval('(' + data + ')');
						$.messager.alert('信息提示', msg.msg, 'info');
						if (msg.code == 1) {
							$('#saleorderoutstockmainList').datagrid('reload');
						}
					});
				}
			});
		} else {
			$.messager.alert('信息提示', '请选择一行记录！', 'info');
		}
	}
	// ***** end of button menu ***** 

	function outstockItemsEditOpen(outstockMainId) {
		$('#OutstockItemsgrid').datagrid(
				{
					url : 'saleorderoutstockmain/findallitems?outstockMainId='
							+ outstockMainId,
					rownumbers : true,
					pagination : true,
					pageSize : 10,
					pageList : [ 10, 20, 30 ],
					singleSelect : true,
					fitColumns : true,
					fit : true,
					toolbar : '#OutstockItemBtns',
					columns : [ [ {
						field : 'productName',
						title : '产品',
						width : 100,
						formatter : function(val, row, index) {
							return row.product.productName;
						}
					}, {
						field : 'fullStandardName',
						title : '规格明细',
						width : 80,
						formatter : function(val, row, index) {
							return row.standardDetail.fullStandardName;
						}
					}, {
						field : 'updateTime',
						title : '配送时间',
						width : 100
					} ] ]
				});
	};
</script>