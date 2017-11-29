<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<link rel="stylesheet" type="text/css" href="static/css/datagrid_util.css">
<div id="CustomerBtns" style="padding: 5px 0;">
	<shiro:hasPermission name="base:customers:add">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-add-s1'" onclick="addCustomer()">新增</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="base:customers:edit">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit-s1'" onclick="editCustomer()">编辑</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="base:customers:delete">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-delete-s1'" onclick="removeCustomer()">删除</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="base:customers:import">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-excel'" onclick="importCustomer()">导入信息</a>
	</shiro:hasPermission>
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-user-earth'" onclick="doqueryCustomer()">查询</a>
</div>
<table id="Customersgrid" title="零售门店列表" style="width: 100%;"></table>
<div id="dlg_editCustomer" class="easyui-dialog fm" title="编辑" 
data-options="modal:true,closed:true,iconCls:'icon-document'" 
style="width: 780px; padding: 10px;"  buttons="#dlg_Customers-buttons" >	 

	<form id="CustomerForm" method="post">
		<!-- 客户id -->
		<input type="hidden" name="customerId" />
		<input type="hidden" name="custCompanyId" />
		<div class="fitem">
			<label class="fitemlabel width_100">行政区域:</label> 
				<input class="easyui-combobox" name="cadministrativedivision" id="customer_cadministrativedivision"
				style="width: 260px;"
				data-options="prompt:'行政区域',valueField:'id',textField:'catgName',url:'productPurchaseInstockOrder/areaList'">&nbsp;
			<!-- <label class="fitemlabel width_100">门店编码:</label> <input
				class="easyui-textbox" name="custCode" style="width: 260px;"
				data-options="prompt:'零售门店编码',editable:false">&nbsp; -->
			<label class="fitemlabel width_100">身份证号:</label> <input
				class="easyui-textbox" name="cidnumb" id="customer_cidnumb"
				style="width: 260px;" data-options="prompt:'身份证号'">&nbsp;
		</div>
		<div class="fitem">
			<label class="fitemlabel width_100">门店名称:</label> <input
				id="customerCompanyId" name="customerCompanyId"
				style="width: 260px;" data-options="prompt:'零售门店名称',required:true,validType:[length[1,255]]">&nbsp;
			<!-- <label class="fitemlabel width_100">联 系 人:</label> <input
				name="contact" type="text" class="easyui-textbox"
				style="width: 260px" data-options="prompt:'联 系 人'" />&nbsp; -->
			<label class="fitemlabel width_100">法人姓名:</label> <input
				class="easyui-textbox" name="cname" id="customer_cname"
				style="width: 260px;" data-options="prompt:'法人姓名'">&nbsp;
		</div>
		<div class="fitem">
				<!-- <label class="fitemlabel width_100">联系人别名:</label> <input
				name="altContact" type="text" class="easyui-textbox"
				style="width: 260px" data-options="prompt:'联系人别名'" />&nbsp;  -->
			<!-- <label class="fitemlabel width_100">门店性质:</label> <input
				class="easyui-combobox" name="cnature" id="customer_cnature"
				style="width: 260px;"
				data-options="prompt:'门店性质',valueField:'varName',textField:'varValue',url:'supplier/getcnature'">&nbsp; -->
		</div>
		<div class="fitem">
			<label class="fitemlabel width_100">法人地址:</label> <input
				class="easyui-textbox" name="clegalpersonaddress"
				id="customer_clegalpersonaddress" style="width: 260px;"
				data-options="prompt:'法人地址'">&nbsp;
				<label class="fitemlabel width_100">联系方式:</label> 
				<input name="tel" type="text" class="easyui-textbox" 
				style="width: 260px" data-options="prompt:'联系方式',required:true,validType:[length[1,60]]" />&nbsp;
		</div>
		<div class="fitem">
			<label class="fitemlabel width_100">工商注册号:</label> <input
				class="easyui-textbox" name="cregistrationnumber"
				id="customer_cregistrationnumber" style="width: 260px;"
				data-options="prompt:'工商注册号'">&nbsp;
			<label class="fitemlabel width_100">客户类别:</label> <input
				class="easyui-combobox" name="ccustomercategories"
				id="customer_ccustomercategories" style="width: 260px;"
				data-options="prompt:'客户类别',valueField:'varName',textField:'varValue',url:'supplier/getccustomercategories'">&nbsp;
		</div>
		<div class="fitem">
			<label class="fitemlabel width_100">营业执照:</label> <input
				class="easyui-textbox" name="cbusinesslicense"
				id="customer_cbusinesslicense" style="width: 260px;"
				data-options="prompt:'营业执照'">&nbsp;
			<label class="fitemlabel width_100">经营范围:</label> <input
				class="easyui-textbox" name="cbusinessscope"
				id="customer_cbusinessscope" style="width: 260px;"
				data-options="prompt:'经营范围'">&nbsp;
		</div>
		<div class="fitem">
			<label class="fitemlabel width_100">酒类经营许可证:</label> <input
				class="easyui-textbox" name="cliquorbusinesslicense"
				id="customer_cliquorbusinesslicense" style="width: 260px;"
				data-options="prompt:'酒类经营许可证'">&nbsp;
			<label class="fitemlabel width_100">经营地址:</label> <input
				class="easyui-textbox" name="cbusinessaddress"
				id="customer_cbusinessaddress" style="width: 260px;"
				data-options="prompt:'经营地址',required:true,validType:[length[1,255]]">&nbsp;
		</div>
		<div class="fitem">
			 <label
				class="fitemlabel width_100">卫生许可证:</label> <input
				class="easyui-textbox" name="chygienelicense"
				id="customer_chygienelicense" style="width: 260px;"
				data-options="prompt:'卫生许可证'">&nbsp;
			<label class="fitemlabel width_100">联系人:</label> <input
				class="easyui-textbox" name="contact" style="width: 260px;"
				data-options="prompt:'联系人',required:true,validType:[length[1,255]]">&nbsp;
		</div>
		<div class="fitem">
				<label class="fitemlabel width_100">有效日期:</label> <input
				class="easyui-datebox" name="ceffectivedate1"
				id="customer_ceffectivedate1" style="width: 260px;"
				data-options="prompt:'起始日期'">&nbsp;-&nbsp;<input
				class="easyui-datebox" name="ceffectivedate2"
				id="customer_ceffectivedate2" style="width: 260px;"
				data-options="prompt:'结束日期'">&nbsp;
		</div>
		<div class="fitem">
				<label class="fitemlabel width_100">是否启用:</label> <input type="hidden"
				name="status" id="customer_status_hidden" value='0'> <input
				id="customer_status_switchbutton" class="easyui-switchbutton"
				style="width: 260px;" data-options="prompt:'是否启用'">&nbsp;
		</div>
	</form>
	<div id="dlg_Customers-buttons"  style="text-align: center;">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveCustomer('CustomerForm','dlg_editCustomer','Customersgrid')" style="width:120px;height: 28px;">确认提交</a>
	</div>
</div>

<div id="dlg_queryCustomer" class="easyui-dialog fm" title="查询"  
data-options="modal:true,closed:true,iconCls:'icon-document'" 
style="width: 780px; padding: 10px;"  >	 

	<form id="queryCustomerForm" method="post">
		<div class="fitem">
			<label class="fitemlabel width_100">行政区域:</label>
			<input class="easyui-combobox" name="cadministrativedivision" style="width: 260px;"
			data-options="prompt:'行政区域',valueField:'id',textField:'catgName',url:'productPurchaseInstockOrder/areaList'">&nbsp;
			<!-- <label class="fitemlabel width_100">门店编码:</label> <input
				class="easyui-textbox" name="custCode" style="width: 260px;"
				data-options="prompt:'零售门店编码'">&nbsp; -->
		</div>
		<div class="fitem">
			<label class="fitemlabel width_100">门店名称:</label> <input
				name="companyName" class="easyui-textbox" 
				style="width: 260px;" data-options="prompt:'零售门店名称'">&nbsp;
			<label class="fitemlabel width_100">法人姓名:</label> <input
				class="easyui-textbox" name="cname"
				style="width: 260px;" data-options="prompt:'法人姓名'">&nbsp;
		</div>
		<div class="fitem">
			<label class="fitemlabel width_100">身份证号:</label> <input
				class="easyui-textbox" name="cidnumb"
				style="width: 260px;" data-options="prompt:'身份证号'">&nbsp;
			<!-- <label class="fitemlabel width_100">门店性质:</label> <input
				class="easyui-combobox" name="cnature"
				style="width: 260px;"
				data-options="prompt:'门店性质',valueField:'varName',textField:'varValue',url:'supplier/getcnature'">&nbsp; -->
		</div>
		<div class="fitem">
			<label class="fitemlabel width_100">法人地址:</label> <input
				class="easyui-textbox" name="clegalpersonaddress" style="width: 260px;"
				data-options="prompt:'法人地址'">&nbsp;
				<label class="fitemlabel width_100">联系方式:</label> 
				<input name="tel" type="text" class="easyui-textbox" 
				style="width: 260px" data-options="prompt:'电话号码'" />&nbsp;
		</div>
		<div class="fitem">
			<label class="fitemlabel width_100">工商注册号:</label> <input
				class="easyui-textbox" name="cregistrationnumber" style="width: 260px;"
				data-options="prompt:'工商注册号'">&nbsp;
			<label class="fitemlabel width_100">客户类别:</label> <input
				class="easyui-combobox" name="ccustomercategories" style="width: 260px;"
				data-options="prompt:'客户类别',valueField:'varName',textField:'varValue',url:'supplier/getccustomercategories'">&nbsp;
		</div>
		<div class="fitem">
			<label class="fitemlabel width_100">营业执照:</label> <input
				class="easyui-textbox" name="cbusinesslicense" style="width: 260px;"
				data-options="prompt:'营业执照'">&nbsp;
			<label class="fitemlabel width_100">经营范围:</label> <input
				class="easyui-textbox" name="cbusinessscope" style="width: 260px;"
				data-options="prompt:'经营范围'">&nbsp;
		</div>
		<div class="fitem">
			<label class="fitemlabel width_100">酒类经营许可证:</label> <input
				class="easyui-textbox" name="cliquorbusinesslicense" style="width: 260px;"
				data-options="prompt:'酒类经营许可证'">&nbsp;
			<label class="fitemlabel width_100">经营地址:</label> <input
				class="easyui-textbox" name="cbusinessaddress" style="width: 260px;"
				data-options="prompt:'经营地址'">&nbsp;
		</div>
		<div class="fitem">
			 <label
				class="fitemlabel width_100">卫生许可证:</label> <input
				class="easyui-textbox" name="chygienelicense" style="width: 260px;"
				data-options="prompt:'卫生许可证'">&nbsp;
		</div>
		<div class="fitem">
			<label class="fitemlabel width_100">是否启用:</label> 
			<input type="radio" name="status" value='-1' checked="checked">全部 &nbsp;
			<input type="radio" name="status" value='0'>启用 &nbsp;
			<input type="radio" name="status" value='1'>禁用 &nbsp;
		</div>
	</form>
	<div  style="text-align: center;">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="doRealQueryCustomer('queryCustomerForm','dlg_queryCustomer','Customersgrid')" style="width:120px;height: 28px;">确认提交</a>
	</div>
</div>

<div id="importCustomersDiv" style="width: 400px; padding: 10px;"
	class="easyui-dialog" title="客户信息导入"
	data-options="modal:true,closed:true,iconCls:'icon-document'">
	<form id="importCustomersForm" method="post"
		action="customers/importCustomers" enctype="multipart/form-data">
		<input class="easyui-filebox" style="width: 300px;"
			name="uploadImportFile"
			data-options="prompt:'物流企业信息导入文件',required:true,buttonText: '选择文件',buttonAlign: 'right'" />
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-ok'" onclick="importCustomerSubmit();">提交</a>
		<a href="#" target="_blank" id="importCustomersSubmitTempl"
			class="easyui-linkbutton" data-options="iconCls:'icon-download-s1'">模板下载</a>
	</form>
</div>

<script type="text/javascript">
	
	$(document).ready(function() {
	    <shiro:hasPermission name="base:customers:edit">
		 $('#Customersgrid').datagrid({
			onDblClickCell: function(index,field,value){
				editCustomer()
			}
		 });
		</shiro:hasPermission>
		$('#Customersgrid').datagrid({
			url : 'customers/findAllList?companyId=' + $('#index_user_companys').combobox('getValue'),
			rownumbers : true,
			pagination : true,
			pageSize : 10,
			pageList : [ 10, 20, 30 ],
			singleSelect : true,
			fitColumns : true,
			fit : true,
			toolbar : '#CustomerBtns',
			columns : [ [ 
			{ field : 'customerAlias', title : '门店名称', width : '16%' }, 
			{ field : 'contact', title : '联系人', width : '16%'},
			{ field : 'tel', title : '联系方式', width : '16%' },
			{ field : 'ccustomercategories', title : '客户类别', width : '16%',
				formatter : function(value, row) {
					var ccustomercategories = row.company.ccustomercategories;
					if(ccustomercategories == 1){
						return '自营';
					} else if(ccustomercategories == 2) {
						return '加盟';
					}
				}
			},
			{ field : 'cbusinessaddress', title : '经营地址', width : '16%',
				formatter : function(value, row) {
					return row.company.cbusinessaddress;
				}
			},
			{ field : 'status',title:'启用状态',width:'20%',formatter: function(value,row,index){
				if(value == 0) {
					return '启用';
				} else {
					return '禁用';
				}
			}}
			] ]
		});
		$.get('variable/getdownloadurl', function(data) {
			data = eval('(' + data + ')');
			if (data.url) {
				$('#importCustomersSubmitTempl').prop('href',
						data.url + '\CustomerTemplate.xls');
			}
		});
		$('#customer_status_switchbutton').switchbutton({
			checked : true,
			onText : '启用',
			offText : '禁用',
			onChange : function(checked) {
				var statusvar = '1';
				if (checked) {
					statusvar = '0';
				}
				$('#customer_status_hidden').attr('value', statusvar);
			}
		});
		
	});
	
	function doqueryCustomer() {
		$('#dlg_queryCustomer').dialog({
			closed : false,
			modal : true,
			title : "查询"
		});
		$("#queryCustomerForm").form('clear').form('disableValidation');
	}
	
	function doRealQueryCustomer(editForm, editWindow, CustomerGrid) {
		var url = $('#' + editForm).serialize();
		$('#Customersgrid').datagrid({
			url : 'customers/findAllList?companyId=' + $('#index_user_companys').combobox('getValue') + '&' + url
					});
		$('#' + editWindow).window('close');
		
	}

	/**
	 *打开新增用户窗口
	 */
	function addCustomer() {
		initDialog_customer();
		$("#CustomerForm").form('clear').form('disableValidation');
		$("#customer_status_switchbutton").switchbutton({
			checked : true
		});
		$('#dlg_editCustomer').dialog({
			closed : false,
			modal : true,
			title : "新增门店"
		});
	}

	function initDialog_customer() {
		$("#customerCompanyId").combobox({
			url : 'customers/getCustomersCompanys',
			method : 'post',
			valueField : 'custCompanyId',
			textField : 'customerAlias',
			hasDownArrow : false,
			required : true,
			onBeforeLoad:function(param){
				param.companyId=$('#index_user_companys').combobox('getValue');
			}
		});
	}
	//数据导入
	function importCustomer() {
		$('#importCustomersDiv').window('open');
	}

	//数据导入
	function importCustomerSubmit() {
		$('#importCustomersForm').form(
				'submit',
				{
					url : 'customers/importCustomers',
					type : 'post',
					onSubmit : function(param) {
						param.companyId = $('#index_user_companys').combobox(
								'getValue');
						return $(this).form('enableValidation')
								.form('validate');
					},
					success : function(data) {
						var msg = eval('(' + data + ')');
						$.messager.alert('信息提示', msg.msg, 'info');
						if (msg.code == '1') {
							$('#importCustomersDiv').window('close');
							$('#Customersgrid').datagrid('reload');
						}
					}
				});
	}

	/**
	 *打开修改用户窗口
	 */
	function editCustomer() {
		var row = $('#Customersgrid').datagrid('getSelected');
		console.info(row);
		if (row) {
			initDialog_customer();
			$("#CustomerForm").form('clear').form('disableValidation');
			$('#CustomerForm').form('load', {
				customerId : row.customerId,
				custCompanyId : row.custCompanyId,
				customerCompanyId : row.custCompanyId,
				contact : row.contact,
				altContact : row.altContact,
				tel : row.tel,
				cadministrativedivision : row.cadministrativedivision,
				/* custCode : row.custCode, */
				cregistrationnumber : row.company.cregistrationnumber,
				cname : row.company.cname,
				cidnumb : row.company.cidnumb,
				clegalpersonaddress : row.company.clegalpersonaddress,
				/* cnature : row.company.cnature, */
				ccustomercategories : row.company.ccustomercategories,
				cbusinesslicense : row.company.cbusinesslicense,
				cbusinessscope : row.company.cbusinessscope,
				cliquorbusinesslicense : row.company.cliquorbusinesslicense,
				cbusinessaddress : row.company.cbusinessaddress,
				chygienelicense : row.company.chygienelicense
			});
			if(row.company != undefined && row.company != null && row.company.ceffectivedate1 != undefined && row.company.ceffectivedate1 != '') {
				var date = new Date(row.company.ceffectivedate1);
				$('#customer_ceffectivedate1').datebox('setValue',date.format("yyyy-MM-dd"));
			}
			if(row.company != undefined && row.company != null && row.company.ceffectivedate2 != undefined && row.company.ceffectivedate2 != '') {
				var date = new Date(row.company.ceffectivedate2);
				$('#customer_ceffectivedate2').datebox('setValue',date.format("yyyy-MM-dd"));
			}
			if (row.status == "1") {
				$("#customer_status_switchbutton").switchbutton({
					checked : false
				});
			} else {
				$("#customer_status_switchbutton").switchbutton({
					checked : true
				});
			}
			$('#customer_status_hidden').attr('value', row.status);
			$('#dlg_editCustomer').dialog({
				closed : false,
				modal : true,
				title : "编辑门店"
			});
		} else {
			$.messager.alert('信息提示', '请选择一行记录！', 'info');
		}
	}

	function removeCustomer() {
		var row = $('#Customersgrid').datagrid('getSelected');
		if (row) {
			$.ajax({
				url : 'customers/delete',
				data : {
					id : row.customerId
				},
				success : function(data) {
					data = eval('(' + data + ')');
					if (data.code == '1') {
						$.messager.alert('信息提示', '删除成功', 'info');
						$('#Customersgrid').datagrid('reload');
					} else {
						$.messager.alert('信息提示', data.msg, 'warn');
					}
				}
			});
		} else {
			$.messager.alert('信息提示', '请选择一行记录！', 'info');
		}
	}
	
	/**
	 *保存
	 */
	function saveCustomer(editForm, editWindow, CustomerGrid) {
		$('#' + editForm).form('submit', {
			url : 'customers/save',
			onSubmit : function(param) {
				param.companyId = $('#index_user_companys').combobox('getValue');
				param.companyName = $("#customerCompanyId").combobox('getText');
				param['customerAlias'] = $("#customerCompanyId").combobox('getText');
				param['company.name'] = $("#customerCompanyId").combobox('getText');
				param['company.cname'] = $('#customer_cname').textbox('getValue');
				param['company.cidnumb'] = $('#customer_cidnumb').textbox('getValue');
				param['company.clegalpersonaddress'] = $('#customer_clegalpersonaddress').textbox('getValue');
				//param['company.cnature'] = $('#customer_cnature').combobox('getValue');
				param['company.ccustomercategories'] = $('#customer_ccustomercategories').combobox('getValue');
				param['company.cbusinesslicense'] = $('#customer_cbusinesslicense').textbox('getValue');
				param['company.cbusinessscope'] = $('#customer_cbusinessscope').textbox('getValue');
				param['company.cliquorbusinesslicense'] = $('#customer_cliquorbusinesslicense').textbox('getValue');
				param['company.cbusinessaddress'] = $('#customer_cbusinessaddress').textbox('getValue');
				param['company.chygienelicense'] = $('#customer_chygienelicense').textbox('getValue');
				param['company.cregistrationnumber'] = $('#customer_cregistrationnumber').textbox('getValue');
				param['company.ceffectivedate1'] = $('#customer_ceffectivedate1').datebox('getValue');
				param['company.ceffectivedate2'] = $('#customer_ceffectivedate2').datebox('getValue');
				return $(this).form('enableValidation').form('validate');
			},
			success : function(data) {
				var msg = eval('(' + data + ')');
				$.messager.alert('信息提示', msg.msg, 'info');
				if (msg.code == 1) {
					$('#' + editWindow).window('close');
					$('#' + CustomerGrid).datagrid('reload');
				}
			}
		});
	}
</script>