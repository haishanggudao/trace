<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<link rel="stylesheet" type="text/css" href="static/css/datagrid_util.css">
<div id="productStandardBar" style="padding: 5px 0;">
	<shiro:hasPermission name="base:product_standard:create">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-add-s1'" onclick="openproductStandardWindow()">新增规格</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="base:product_standard:update">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit-s1'" onclick="editproductStandard()">编辑规格</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="base:product_standard:del">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-delete-s1'" onclick="delproductStandard();">删除规格</a>
	</shiro:hasPermission>
	
	
	<!-- 搜索筛选 -->
	<shiro:hasPermission name="base:product_standard:list">
		&nbsp;规格名称：&nbsp;<input id="productStandardName" class="easyui-textbox" name="productStandardName" style="width: 200px">
		<a href="#" class="easyui-linkbutton" iconcls="icon-search" onclick="loadproductStandardData()">搜索</a>
	</shiro:hasPermission>
	

</div>


<table id="productStandardlist" title="产品规格列表" style="width: auto; height: 350px">
</table>


<div id="addproductStandard" class="easyui-dialog" title="新增" 
data-options="modal:true,closed:true,iconCls:'icon-document'"
style="width: 300px;padding: 20px;"  buttons="#buttons_productStandard">
	<form id="productStandardForm" method="post">
		<input type="hidden" name="productStandardId">
	    <div class="fitem">
            <label class="fitemlabel width_70">规格名称:</label>
            <input class="easyui-textbox" name="productStandardName" style="width: 150px;"
				data-options="prompt:'输入规格名称...',required:true">&nbsp;
        </div>
	    <div class="fitem">
            <label class="fitemlabel width_70">产品分类:</label>
            <select class="easyui-combobox"  id="productCategorys_standard" name="productCategoryId" 
            	style="width: 150px;"></select>&nbsp;
        </div>        
	</form>
	<div id="buttons_productStandard"  style="text-align: center;">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" 
			onclick="submitproductStandardForm()" style="width:120px;height: 26px">确认提交</a>
	</div>
</div>

<script type="text/javascript">
var initFlag_standard=0;
$(function(){
	loadproductStandardData();
});

	//新增或修改产品规格，确认提交
	function submitproductStandardForm() {
		$('#productStandardForm').form('submit', {
			url : 'product_standard/addProductStandard',
			onSubmit : function(param) {
				return $(this).form('enableValidation').form('validate');
			},
			success : function(data) {
				var msg = eval('(' + data + ')');
				$.messager.alert('信息提示', msg.msg, 'info');
				if (msg.code == 1) {
					$('#addproductStandard').window('close');
					$('#productStandardlist').datagrid('reload');
				}
			}
		});
	}

	// 删除产品规格
	function delproductStandard() {
		var row = $('#productStandardlist').datagrid('getSelected');
		if (row) {
			$.messager.confirm('删除产品规格', '确认删除?', function(r) {
				if (r) {
					$.post("product_standard/delProductStandard", {
						productStandardId : row.productStandardId
					}, function(data) {
						var msg = eval('(' + data + ')');
						$.messager.alert('信息提示', msg.msg, 'info');
						if (msg.code == 1) {
							$('#productStandardlist').datagrid('reload');
						}
					})
				}
			});
		} else {
			$.messager.alert('信息提示', '请选择一行记录！', 'info');
		}

	}
	//打开新增规格窗口
	function openproductStandardWindow() {
		if(initFlag_standard==0){
			initWindowcomponent_standard();
		}
		$("#productStandardForm").form('clear').form('disableValidation');
		$('#addproductStandard').window({
			modal:true,
			closed:true,
			iconCls:'icon-document',
			title:'新增产品规格'
		});
		$('#addproductStandard').window('open');
	}

	function initWindowcomponent_standard(){
		$("#productCategorys_standard").combobox({
			url:"product_category/list",
			method : 'post',
			editable:false,
			valueField: 'productCategoryId',
			textField: 'productCategoryName',
			required:true,
			onBeforeLoad: function(param){
				var myUserCompanyId = $('#index_user_companys').combobox('getValue');
				param.companyId = myUserCompanyId;
			}
		});
		initFlag_standard=1;
	}
	//编辑产品规格
	function editproductStandard() {
		var row = $('#productStandardlist').datagrid('getSelected');
		if (row) {
			$('#productStandardForm').form('clear');
			if(initFlag_standard==0){
				initWindowcomponent_standard();
			}
			$('#productStandardForm').form('load', {
				productStandardId:row.productStandardId,
				productStandardName : row.productStandardName,
				productCategoryId:row.productCategoryId
				
			});
			$('#addproductStandard').window({
				modal:true,
				closed:true,
				iconCls:'icon-document',
				title:'修改产品分类'
			});
			$('#addproductStandard').window('open');
		} else {
			$.messager.alert('信息提示', '请选择一行记录！', 'info');
		}
	}
	/**
	 * Name 载入数据
	 */
	function loadproductStandardData(){
	    <shiro:hasPermission name="base:product_standard:update">
		 $('#productStandardlist').datagrid({
			onDblClickCell: function(index,field,value){
				editproductStandard()
			}
		 });
		</shiro:hasPermission>
		$('#productStandardlist').datagrid({
			url : 'product_standard/findAllList',
			rownumbers : true,
			pageSize : 10,
			pageList : [ 10, 20, 50 ],
			pagination : true,
			singleSelect : true,
			multiSort : true,
			fitColumns : true,
			fit : true,
			toolbar : '#productStandardBar',
			onBeforeLoad : function(param) {
	 			param.productStandardName = $("#productStandardName").val();
	 			
	 			var myUserCompanyId = $('#index_user_companys').combobox('getValue');
				param.companyId = myUserCompanyId;
			},
			columns : [ [
			 			{
			 				field : 'productStandardName',
			 				title : '规格名称',
			 				width : 100,
			 				sortable : true
			 			},
			 			{
			 				field : 'productCategoryName',
			 				title : '分类名称',
			 				width : 100,
			 				sortable : true
			 			}

			 			] ]
		});
	}
	
	
	
</script>