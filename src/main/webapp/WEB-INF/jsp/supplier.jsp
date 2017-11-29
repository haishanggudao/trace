<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<link rel="stylesheet" type="text/css"	href="static/css/datagrid_util.css">
<link type="text/css" rel="stylesheet" href="umeditor/themes/default/css/umeditor.css">
<script type="text/javascript" charset="utf-8" src="umeditor/umeditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="umeditor/umeditor.min.js"></script>
<script type="text/javascript" charset="utf-8" src="umeditor/lang/zh-cn/zh-cn.js"></script>
<div id="SupplierBtns" style="padding: 5px 0;">
	<shiro:hasPermission name="base:supplier:add">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-add-s1'" onclick="addSupplier()">新增供应商</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="base:supplier:edit">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit-s1'" onclick="editSupplier()">编辑供应商</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="base:supplier:delete">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-delete-s1'" onclick="removeSupplier()">删除供应商</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="base:supplier:import">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-excel'" onclick="importSupplier();">供应商信息导入</a>
	</shiro:hasPermission>
	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-user-earth'" onclick="doquerySupplier()">查询</a>
</div>
<table id="Suppliersgrid" title="供应商信息列表" style="width: 100%;"></table>

<div id="dlg_editSupplier" class="easyui-dialog fm" title="编辑"
	data-options="modal:true,closed:true,iconCls:'icon-document',collapsible:false,minimizable:false,maximizable:false"
	style="width: 810px; padding: 10px;">
	<br />
	<form id="SupplierForm" method="post">
		<!-- 所属领域id -->
		<input type="hidden" name="supplierId" />
		<div class="easyui-tabs" id="supplierTabs" style="width:780px;padding:10px;" data-options="plain:true,narrow:true,pill:false,justified:false">
		<div title="基础信息" style="padding:5px;">
		<div class="fitem">
			<!-- <label class="fitemlabel width_100">供应商编码:</label> 
				<input class="easyui-textbox" name="supplierCode" style="width: 260px;"
				data-options="prompt:'供应商编码',editable:false">&nbsp; -->
				<input type="hidden" name="supplierCode" />
			<label class="fitemlabel width_100">供应商名称:</label> 
			<select id="supplierCompanyId" name="supplierCompanyId"
				style="width: 260px;"></select>&nbsp;<!--  <label
				class="fitemlabel width_100">联系人:</label> <input
				class="easyui-textbox" name="contact" style="width: 260px;"
				data-options="prompt:'联系人',validType:'name'">&nbsp; -->
				<label class="fitemlabel width_100">法人姓名:</label> <input
				class="easyui-textbox" name="cname" id="supplier_cname" style="width: 260px;"
				data-options="prompt:'法人姓名'">&nbsp; 
		</div>
		<div class="fitem">
		</div>
		<div class="fitem">
			<label class="fitemlabel width_100">身份证号:</label> <input
				class="easyui-textbox" name="cidnumb" id="supplier_cidnumb"
				style="width: 260px;" data-options="prompt:'身份证号'">&nbsp;
				<label class="fitemlabel width_100">工商注册号:</label> <input
				class="easyui-textbox" name="cregistrationnumber"
				id="supplier_cregistrationnumber" style="width: 260px;"
				data-options="prompt:'工商注册号'">&nbsp;
			<!-- <label class="fitemlabel width_100">供应商性质:</label> <input
				class="easyui-combobox" name="cnature" id="supplier_cnature"
				style="width: 260px;"
				data-options="prompt:'供应商性质',valueField:'varName',textField:'varValue',url:'supplier/getcnature'">&nbsp; -->
		</div>
		<div class="fitem">
				<label class="fitemlabel width_100">法人地址:</label> <input
				class="easyui-textbox" name="clegalpersonaddress"
				id="supplier_clegalpersonaddress" style="width: 260px;"
				data-options="prompt:'法人地址'">&nbsp;
			<!-- <label class="fitemlabel width_100">联系人别名:</label> <input
				class="easyui-textbox" name="altContact" style="width: 260px;"
				data-options="prompt:'联系人别名',validType:'name'">&nbsp;  -->
				<label class="fitemlabel width_100">联系方式:</label> <input
				class="easyui-textbox" name="tel" style="width: 260px;"
				data-options="prompt:'联系方式',validType:[length[1,60]]">&nbsp;
		</div>
		<div class="fitem">
			<!-- <label class="fitemlabel width_100">客户类别:</label> <input
				class="easyui-combobox" name="ccustomercategories"
				id="supplier_ccustomercategories" style="width: 260px;"
				data-options="prompt:'客户类别',valueField:'varName',textField:'varValue',url:'supplier/getccustomercategories'">&nbsp; -->
		</div>
		<div class="fitem">
			<!-- <label class="fitemlabel width_100">供应商地址:</label> <input
				class="easyui-textbox" name="supplierAddress" style="width: 260px;"
				data-options="prompt:'供应商地址'">&nbsp; <label
				class="fitemlabel width_100">设置备注:</label> <input
				class="easyui-textbox" name="remark" style="width: 260px;"
				data-options="prompt:'备注'">&nbsp; -->
				<input type="hidden" name="supplierAddress" />
				<input type="hidden" name="remark" />
		</div>
		<div class="fitem">
			<label class="fitemlabel width_100">营业执照:</label> <input
				class="easyui-textbox" name="cbusinesslicense" id="supplier_cbusinesslicense"
				style="width: 260px;" data-options="prompt:'营业执照'">&nbsp;
			<label class="fitemlabel width_100">经营范围:</label> <input
				class="easyui-textbox" name="cbusinessscope" id="supplier_cbusinessscope"
				style="width: 260px;" data-options="prompt:'经营范围'">&nbsp;
		</div>
		<div class="fitem">
			 <label
				class="fitemlabel width_100">酒类经营许可证:</label> <input
				class="easyui-textbox" name="cliquorbusinesslicense"
				id="supplier_cliquorbusinesslicense" style="width: 260px;"
				data-options="prompt:'酒类经营许可证'">&nbsp;
				<label class="fitemlabel width_100">经营地址:</label> <input
				class="easyui-textbox" name="cbusinessaddress" id="supplier_cbusinessaddress"
				style="width: 260px;" data-options="prompt:'经营地址',required:true,validType:[length[1,255]]">&nbsp; 
		</div>
		<div class="fitem">
			<label
				class="fitemlabel width_100">卫生许可证:</label> <input
				class="easyui-textbox" name="chygienelicense" id="supplier_chygienelicense"
				style="width: 260px;" data-options="prompt:'卫生许可证'">&nbsp;
				<label class="fitemlabel width_100">联系人:</label> <input
				class="easyui-textbox" name="contact" style="width: 260px;"
				data-options="prompt:'联系人',required:true,validType:[length[1,255]]">&nbsp;
		</div>
		<div class="fitem">
			<label class="fitemlabel width_100">有效日期:</label> <input
				class="easyui-datebox" name="ceffectivedate1" id="supplier_ceffectivedate1"
				style="width: 260px;" data-options="prompt:'起始日期'">&nbsp;--&nbsp;
				<input
				class="easyui-datebox" name="ceffectivedate2" id="supplier_ceffectivedate2"
				style="width: 260px;" data-options="prompt:'有效日期'">&nbsp;
		</div>
		<div class="fitem">
			<label
				class="fitemlabel width_100">是否启用:</label> <input type="hidden"
				name="status" id="status_hidden" value='0'> <input
				id="status_switchbutton" class="easyui-switchbutton"
				style="width: 260px;" data-options="prompt:'是否启用'">&nbsp;
		</div>
		</div>
		<div title="简介" style="padding:5px;">
			<div class="fitem" id="myEditor" style="width:750px;padding:0;height:240px;line-height:1.5em;">
			</div>
		</div>
		</div>
	</form>
	<div id="buttons-edtSupplier"
		style="text-align: center; display: none;">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="saveSupplier()"
			style="width: 120px; height: 32px">确认提交</a>
	</div>
	
</div>

<div id="dlg_querySupplier" class="easyui-dialog fm" title="查询"
	data-options="modal:true,closed:true,iconCls:'icon-document',collapsible:false,minimizable:false,maximizable:false"
	style="width: 780px; padding: 10px;">
	<br />
	<form id="querySupplierForm" method="post">
		<div class="fitem">
			<label class="fitemlabel width_100">供应商编码:</label> 
				<input class="easyui-textbox" name="supplierCode" style="width: 260px;"
				data-options="prompt:'供应商编码'">&nbsp;
			<label class="fitemlabel width_100">供应商名称:</label> <input
				name="companyName" class="easyui-textbox" 
				style="width: 260px;" data-options="prompt:'供应商名称'">&nbsp;
		</div>
		<div class="fitem">
			<label class="fitemlabel width_100">法人姓名:</label> <input
				class="easyui-textbox" name="cname" style="width: 260px;"
				data-options="prompt:'法人姓名'">&nbsp; 
		</div>
		<div class="fitem">
			<label class="fitemlabel width_100">身份证号:</label> <input
				class="easyui-textbox" name="cidnumb"
				style="width: 260px;" data-options="prompt:'身份证号'">&nbsp;
			<label class="fitemlabel width_100">供应商性质:</label> <input
				class="easyui-combobox" name="cnature"
				style="width: 260px;"
				data-options="prompt:'供应商性质',textField:'varValue',url:'supplier/getcnature'">&nbsp;
		</div>
		<div class="fitem">
			<label class="fitemlabel width_100">法人地址:</label> <input
				class="easyui-textbox" name="clegalpersonaddress" style="width: 260px;"
				data-options="prompt:'法人地址'">&nbsp;
			<label class="fitemlabel width_100">联系方式:</label> <input
				class="easyui-textbox" name="tel" style="width: 260px;"
				data-options="prompt:'联系方式'">&nbsp;
		</div>
		<div class="fitem">
			<label class="fitemlabel width_100">工商注册号:</label> <input
				class="easyui-textbox" name="cregistrationnumber" style="width: 260px;"
				data-options="prompt:'工商注册号'">&nbsp;
			<label class="fitemlabel width_100">客户类别:</label> <input
				class="easyui-combobox" name="ccustomercategories" style="width: 260px;"
				data-options="prompt:'客户类别',textField:'varValue',url:'supplier/getccustomercategories'">&nbsp;
		</div>
		<div class="fitem">
			<label class="fitemlabel width_100">营业执照:</label> <input
				class="easyui-textbox" name="cbusinesslicense"
				style="width: 260px;" data-options="prompt:'营业执照'">&nbsp;
			<label class="fitemlabel width_100">经营范围:</label> <input
				class="easyui-textbox" name="cbusinessscope"
				style="width: 260px;" data-options="prompt:'经营范围'">&nbsp;
		</div>
		<div class="fitem">
			<label class="fitemlabel width_100">酒类经营许可证:</label> <input
				class="easyui-textbox" name="cliquorbusinesslicense" style="width: 260px;"
				data-options="prompt:'酒类经营许可证'">&nbsp;
			<label class="fitemlabel width_100">经营地址:</label> <input
				class="easyui-textbox" name="cbusinessaddress"
				style="width: 260px;" data-options="prompt:'经营地址'">&nbsp; 
		</div>
		<div class="fitem">
			<label
				class="fitemlabel width_100">卫生许可证:</label> <input
				class="easyui-textbox" name="chygienelicense"
				style="width: 260px;" data-options="prompt:'卫生许可证'">&nbsp;
			<label class="fitemlabel width_100">联系人:</label> <input
				class="easyui-textbox" name="contact" style="width: 260px;"
				data-options="prompt:'联系人'">&nbsp;
		</div>
		<div class="fitem">
			<label class="fitemlabel width_100">是否启用:</label> 
			<input id='input_radio_status' type="radio" name="status" value='-1' checked="checked">全部 &nbsp;
			<input type="radio" name="status" value='0'>启用 &nbsp;
			<input type="radio" name="status" value='1'>禁用 &nbsp;
		</div>
	</form>
	<div style="text-align: center;">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="doRealQuerySupplier()"
			style="width: 120px; height: 32px">确认提交</a>
	</div>
</div>

<div id="importSupplierDiv" class="easyui-window" data-options="modal : true,closed : true,iconCls : 'icon-excel',title : '供应商信息导入'" style="width: 400px; padding: 10px;">
	<form id="importSupplierForm" method="post"
		action="supplier/importsupplier" enctype="multipart/form-data">
		<input class="easyui-filebox" style="width: 300px;" name="uploadImportFile"
			data-options="prompt:'供应商信息导入文件',required:true,buttonText: '选择文件',buttonAlign: 'right'" />
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
			onclick="importSupplierSubmit();">提交</a> <a href="#" target="_blank"
			id="importSupplierSubmitTempl" class="easyui-linkbutton"
			data-options="iconCls:'icon-download-s1'">模板下载</a>
	</form>
</div>
<script type="text/javascript">
	
var um=null;
	//数据导入
	function importSupplier() {
		$('#importSupplierDiv').window('open');
	}
	
	function doquerySupplier() {
		$('#dlg_querySupplier').dialog({
			closed : false,
			modal : true,
			title : "查询"
		});
		$("#querySupplierForm").form('clear').form('disableValidation');
		$('#input_radio_status').prop('checked','checked');
	}
	
	function doRealQuerySupplier() {
		var url = $('#querySupplierForm').serialize();
		$('#Suppliersgrid').datagrid({
			url : 'supplier/findAllList?' + url
		});
		$('#dlg_querySupplier').window('close');
	}

	//数据导入
	function importSupplierSubmit() {
		$('#importSupplierForm').form(
				'submit',
				{
					url : 'supplier/importsupplier',
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
							$('#importSupplierDiv').window('close');
							$('#Suppliersgrid').datagrid('reload');
						}
					}
				});
	}

	$(document).ready(
			function() {
			  	<shiro:hasPermission name="base:supplier:edit">
					 $('#Suppliersgrid').datagrid({
						onDblClickCell: function(index,field,value){
							editSupplier()
						}
					 });
				</shiro:hasPermission>
				$.get('logistics/getdownloadurl', function(data) {
					data = eval('(' + data + ')');
					if (data.url) {
						$('#importSupplierSubmitTempl').prop('href',
								data.url + 'supplierTemplate.xls');
					}
				});
				$('#Suppliersgrid').datagrid(
						{
							url : 'supplier/findAllList',
							rownumbers : true,
							pagination : true,
							pageSize : 10,
							pageList : [ 10, 20, 30 ],
							singleSelect : true,
							fitColumns : true,
							fit : true,
							onBeforeLoad : function(param) {
								param.companyId = $('#index_user_companys')
										.combobox('getValue');
							},
							toolbar : '#SupplierBtns',
							columns : [ [ 
							/* { field : 'supplierCode',title : '供应商编码',width : '16%'}, */
							{ field : 'supplierAlias',title : '供应商名称',width : '16%'},
							{ field : 'contact', title : '联系人', width : '16%'},
							{ field : 'tel', title : '联系方式', width : '16%'},
							{ field : 'companyCregistrationnumber', title : '经营地址', width : '16%',
								formatter : function(value, row) {
									return row.company.cbusinessaddress;
								}
							},
							{ field : 'cliquorbusinesslicense', title : '酒类经营许可证', width : '16%',
								formatter : function(value, row) {
									return row.company.cliquorbusinesslicense;
								}
							},
							{ field : 'status',title:'启用状态',width:'20%',formatter: function(value,row,index){
								if(value == 0) {
									return '启用';
								} else {
									return '禁用';
								}
							}} ] ]
						});
				$('#status_switchbutton').switchbutton({
					checked : true,
					onText : '启用',
					offText : '禁用',
					onChange : function(checked) {
						var statusvar = '1';
						if (checked) {
							statusvar = '0';
						}
						$('#status_hidden').attr('value', statusvar);
					}
				});
			});

	function initUMEditor(){
		if(um==null){
			um = UM.getEditor('myEditor',{
				autoHeight: false,
				imageUrl:"image/upload",
				imagePath:"",
				autoHeightEnabled:false
			});
			um.setHeight(300);
		}
	}
	
	/**
	 *打开新增用户窗口
	 */
	function addSupplier() {
		loadSelectData();
		initUMEditor();
		um.execCommand('cleardoc');
		$("#SupplierForm").form('clear').form('disableValidation');
		$('#dlg_editSupplier').dialog({
			closed : false,
			modal : true,
			title : "新增供应商",
			buttons : '#buttons-edtSupplier'
		});
		$("#status_switchbutton").switchbutton({
			checked : true
		});
		$('#status_hidden').attr('value', '0');
		$("#supplierTabs").tabs('select',0);
	}

	/**
	 *打开修改用户窗口
	 */
	function editSupplier() {
		var row = $('#Suppliersgrid').datagrid('getSelected');
		if (row) {
			$("#SupplierForm").form('clear').form('disableValidation');
			loadSelectData();
			initUMEditor();
			$('#SupplierForm').form('load', {
				supplierId : row.supplierId,
				companyId : row.companyId,
				supplierCompanyId : row.supplierCompanyId,
				contact : row.contact,
				altContact : row.altContact,
				tel : row.tel,
				supplierAddress : row.supplierAddress,
				remark : row.remark,
				cregistrationnumber : row.company.cregistrationnumber,
				cname : row.company.cname,
				supplierCode : row.supplierCode,
				cidnumb : row.company.cidnumb,
				clegalpersonaddress : row.company.clegalpersonaddress,
				cnature : row.company.cnature,
				ccustomercategories : row.company.ccustomercategories,
				cbusinesslicense : row.company.cbusinesslicense,
				cbusinessscope : row.company.cbusinessscope,
				cliquorbusinesslicense : row.company.cliquorbusinesslicense,
				cbusinessaddress : row.company.cbusinessaddress,
				chygienelicense : row.company.chygienelicense,
				supplierDesc:row.supplierDesc
			});
			if(row.supplierDesc==null){
				um.setContent("");
			}else{
				um.setContent(row.supplierDesc);
			}
			if(row.company != undefined && row.company != null && row.company.ceffectivedate1 != undefined && row.company.ceffectivedate1 != '') {
				var date = new Date(row.company.ceffectivedate1);
				$('#supplier_ceffectivedate1').datebox('setValue',date.format("yyyy-MM-dd"));
			}
			if(row.company != undefined && row.company != null && row.company.ceffectivedate2 != undefined && row.company.ceffectivedate2 != '') {
				var date = new Date(row.company.ceffectivedate2);
				$('#supplier_ceffectivedate2').datebox('setValue',date.format("yyyy-MM-dd"));
			}
			if (row.status == "1") {
				$("#status_switchbutton").switchbutton({
					checked : false
				});
			} else {
				$("#status_switchbutton").switchbutton({
					checked : true
				});
			}
			$('#status_hidden').attr('value', row.status);
			$('#dlg_editSupplier').dialog({
				closed : false,
				modal : true,
				title : "编辑供应商",
				buttons : '#buttons-edtSupplier'
			});
			$("#supplierTabs").tabs('select',0);
		} else {
			$.messager.alert('信息提示', '请选择一行记录！', 'info');
		}
	}

	function removeSupplier() {
		var row = $('#Suppliersgrid').datagrid('getSelected');
		if (row) {
			$.ajax({
				url : 'supplier/delete',
				data : {
					supplierId : row.supplierId
				},
				success : function(data) {
					data = eval('(' + data + ')');
					if (data.code == '1') {
						$.messager.alert('信息提示', '删除成功', 'info');
						$('#Suppliersgrid').datagrid('reload');
					} else {
						$.messager.alert('信息提示', data.msg, 'warn');
					}
				}
			});
		} else {
			$.messager.alert('信息提示', '请选择一行记录！', 'info');
		}
	}

	function loadSelectData() {
		$("#supplierCompanyId").combobox(
				{
					url : 'supplier/getSupplierCompanys',
					method : 'post',
					required : true,
					prompt : '供应商名称',
					valueField : 'supplierCompanyId',
					textField : 'supplierAlias',
// 					hasDownArrow : false,
					onBeforeLoad : function(param) {
						param.companyId = $('#index_user_companys').combobox(
								'getValue');
					}
				});
	}

	/**
	 *保存
	 */
	function saveSupplier() {
		$('#SupplierForm')
				.form(
						'submit',
						{
							url : 'supplier/save',
							onSubmit : function(param) {
								param.companyName = $("#supplierCompanyId").combobox('getText');
								param.companyId = $('#index_user_companys').combobox('getValue');
								param.supplierAlias = $("#supplierCompanyId").combobox('getText');
								param['company.name'] = $("#supplierCompanyId").combobox('getText');
								param['company.cname'] = $('#supplier_cname').textbox('getValue');
								param['company.cidnumb'] = $('#supplier_cidnumb').textbox('getValue');
								param['company.clegalpersonaddress'] = $('#supplier_clegalpersonaddress').textbox('getValue');
								//param['company.cnature'] = $('#supplier_cnature').combobox('getValue');
								//param['company.ccustomercategories'] = $('#supplier_ccustomercategories').combobox('getValue');
								param['company.cbusinesslicense'] = $('#supplier_cbusinesslicense').textbox('getValue');
								param['company.cbusinessscope'] = $('#supplier_cbusinessscope').textbox('getValue');
								param['company.cliquorbusinesslicense'] = $('#supplier_cliquorbusinesslicense').textbox('getValue');
								param['company.cbusinessaddress'] = $('#supplier_cbusinessaddress').textbox('getValue');
								param['company.chygienelicense'] = $('#supplier_chygienelicense').textbox('getValue');
								param['company.ceffectivedate1'] = $('#supplier_ceffectivedate1').datebox('getValue');
								param['company.ceffectivedate2'] = $('#supplier_ceffectivedate2').datebox('getValue');
								param['company.cregistrationnumber'] = $('#supplier_cregistrationnumber').textbox('getValue');
								param.supplierDesc=UM.getEditor('myEditor').getContent();
								return $(this).form('enableValidation').form('validate');
							},
							success : function(data) {
								var msg = eval('(' + data + ')');
								$.messager.alert('信息提示', msg.msg, 'info');
								if (msg.code == 1) {
									$('#dlg_editSupplier').window('close');
									loadSelectData();
									$('#Suppliersgrid').datagrid('reload');
								}
							}
						});
	}
</script>