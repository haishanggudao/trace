<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<div id="productStandardRateBar" style="padding: 5px 0;">
	<shiro:hasPermission name="base:product_standard_rate:create">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-add-s1'" onclick="openproductStandardRateWindow()">新增规格转化</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="base:product_standard_rate:update">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit-s1'" onclick="editproductStandardRate()">编辑规格转化</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="base:product_standard_rate:del">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-delete-s1'" onclick="delproductStandardRate();">删除规格转化</a>
	</shiro:hasPermission>
	<!-- 搜索筛选 -->
	<shiro:hasPermission name="base:product_standard_rate:list">
		&nbsp;产品分类：&nbsp;<input id="rateProductCategorySearch"style="width: 120px">
		&nbsp;<a href="#" class="easyui-linkbutton"iconcls="icon-search" onclick="loadproductStandardRateData()">搜索</a>
	</shiro:hasPermission>	
	
</div>


<table id="productStandardRatelist" title="产品规格转化列表" style="width: auto; height: 350px">
</table>

<div id="addproductStandardRate" style="width: 500px; padding: 10px;">
	<form id="productStandardRateForm" method="post">
		<input type="hidden" name="productStandardRateId">
		<div>产品规格:</div>
		<br /> <select id="productStandard3" name="productStandard.productStandardId"
					style="width: 100%;"></select>
		<p></p>
		<div>产品规格数量:</div>
		<input name="productStandardNum" class="easyui-numberbox" style="width: 100%;">
		<p></p>
		<div>产品规格:</div>
		<br /> <select id="productStandard4" name="rateProductStandard.productStandardId"
					style="width: 100%;"></select>
		<p></p>
		<div>目标产品规格数量:</div>
		<input name="rateProductStandardNum" class="easyui-numberbox" style="width: 100%;">
		<p></p>
		<div>产品分类:</div>
		<select id="productCategorys_rate" name="productCategory.productCategoryId"
					style="width: 100%;"></select>
		<p></p>
	</form>
	<div style="margin: 10px 0;">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
			style="width: 100%; height: 32px" onclick="submitproductStandardRateForm()">确认提交</a>
	</div>
</div>


<script type="text/javascript">
var initFlag_rate=0;
$(function(){
	$("#rateProductCategorySearch").combobox({
		url:"product_category/list",
		method : 'post',
		valueField: 'productCategoryId',
		textField: 'productCategoryName',
		onBeforeLoad : function(param) {
			var myUserCompanyId = $('#index_user_companys').combobox('getValue');
			param.companyId = myUserCompanyId;
		}
	});
	loadproductStandardRateData();
});

	//新增或修改产品规格，确认提交
	function submitproductStandardRateForm() {
		$('#productStandardRateForm').form('submit', {
			url : 'product_standard_rate/addProductStandardRate',
			onSubmit : function(param) {
				param.companyId=$("#index_user_companys").combobox("getValue");
				return $(this).form('enableValidation').form('validate');
			},
			success : function(data) {
				var msg = eval('(' + data + ')');
				$.messager.alert('信息提示', msg.msg, 'info');
				if (msg.code == 1) {
					$('#addproductStandardRate').window('close');
					$('#productStandardRatelist').datagrid('reload');
				}
			}
		});
	}

	// 删除规格转化
	function delproductStandardRate() {
		var row = $('#productStandardRatelist').datagrid('getSelected');
		if (row) {
			$.messager.confirm('删除产品规格转化', '确认删除?', function(r) {
				if (r) {
					$.post("product_standard_rate/delProductStandardRate", {
						productStandardRateId : row.productStandardRateId
					}, function(data) {
						var msg = eval('(' + data + ')');
						$.messager.alert('信息提示', msg.msg, 'info');
						if (msg.code == 1) {
							$('#productStandardRatelist').datagrid('reload');
						}
					})
				}
			});
		} else {
			$.messager.alert('信息提示', '请选择一行记录！', 'info');
		}

	}
	//打开新增规格转化窗口
	function openproductStandardRateWindow() {
		if(initFlag_rate==0){
			initWindowcomponent_standard_rate();
		}
		$("#productStandardRateForm").form('clear').form('disableValidation');
		$('#addproductStandardRate').window({
			modal:true,
			closed:true,
			iconCls:'icon-document',
			title:'新增产品规格转化'
		});
		$('#addproductStandardRate').window('open');
	}

	function initWindowcomponent_standard_rate(){
		$("#productStandard3").combobox({
			url:"product_standard/list",
			method : 'post',
			editable:false,
			valueField: 'productStandardId',
			textField: 'productStandardName',
			required:true,
			onBeforeLoad : function(param) {
				var myUserCompanyId = $('#index_user_companys').combobox('getValue');
				param.companyId = myUserCompanyId;
			}
		});
		$("#productStandard4").combobox({
			url:"product_standard/list",
			method : 'post',
			editable:false,
			valueField: 'productStandardId',
			textField: 'productStandardName',
			required:true,
			onBeforeLoad : function(param) {
				var myUserCompanyId = $('#index_user_companys').combobox('getValue');
				param.companyId = myUserCompanyId;
			}
		});
		$("#productCategorys_rate").combobox({
			url:"product_category/list",
			method : 'post',
			editable:false,
			valueField: 'productCategoryId',
			textField: 'productCategoryName',
			required:true,
			onBeforeLoad : function(param) {
				var myUserCompanyId = $('#index_user_companys').combobox('getValue');
				param.companyId = myUserCompanyId;
			}
		});
		initFlag_rate=1;
	}
	//编辑产品规格转化
	function editproductStandardRate() {
		var row = $('#productStandardRatelist').datagrid('getSelected');
		if (row) {
			$('#productStandardRateForm').form('clear');
			if(initFlag_rate==0){
				initWindowcomponent_standard_rate();
			}
			$('#productStandardRateForm').form('load', {
				productStandardRateId:row.productStandardRateId,
				'productStandardNum':row.productStandardNum,
				'rateProductStandardNum':row.rateProductStandardNum,
				'productCategory.productCategoryId':row.productCategory.productCategoryId,
				'productStandard.productStandardId':row.productStandard.productStandardId,
				'rateProductStandard.productStandardId':row.rateProductStandard.productStandardId
				
			});
			$('#addproductStandardRate').window({
				modal:true,
				closed:true,
				iconCls:'icon-document',
				title:'修改产品规格转化'
			});
			$('#addproductStandardRate').window('open');
		} else {
			$.messager.alert('信息提示', '请选择一行记录！', 'info');
		}
	}
	/**
	 * Name 载入数据
	 */
	function loadproductStandardRateData(){
	    <shiro:hasPermission name="base:product_standard_rate:update">
		 $('#productStandardRatelist').datagrid({
			onDblClickCell: function(index,field,value){
				editproductStandardRate()
			}
		 });
		</shiro:hasPermission>
		$('#productStandardRatelist').datagrid({
			url : 'product_standard_rate/findAllList',
			rownumbers : true,
			pageSize : 10,
			pageList : [ 10, 20, 50 ],
			pagination : true,
			singleSelect : true,
			multiSort : true,
			fitColumns : true,
			fit : true,
			toolbar : '#productStandardRateBar',
			onBeforeLoad : function(param) {
				param.companyId=$("#index_user_companys").combobox("getValue");
				param.productCategoryId=$("#rateProductCategorySearch").combobox("getValue");
			},
			columns : [ [
			 			{
			 				field : 'productName',
			 				title : '产品分类名称',
			 				width : 100,
			 				sortable : true,
			 				formatter : function(val, row, index) {
								return row.productCategory.productCategoryName;
							}
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
			 				field : 'productStandardNum',
			 				title : '产品规格数量',
			 				width : 100
			 			},
			 			{
			 				field : 'rateProductStandardName',
			 				title : '目标产品规格名称',
			 				width : 100,
			 				sortable : true,
			 				formatter : function(val, row, index) {
								return row.rateProductStandard.productStandardName;
							}
			 			},
			 			{
			 				field : 'rateProductStandardNum',
			 				title : '目标产品规格数量',
			 				width : 100
			 			}
			 			] ]
		});
	}
	
	
	
</script>