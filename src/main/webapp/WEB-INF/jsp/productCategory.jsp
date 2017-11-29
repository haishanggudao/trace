<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<link rel="stylesheet" type="text/css" href="static/css/datagrid_util.css">
<div id="productCategoryBar" style="padding: 5px 0;">
	<shiro:hasPermission name="base:product_category:create">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-add-s1'"
			onclick="openProductCategoryWindow()">新增类别</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="base:product_category:update">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit-s1'" onclick="editProductCategory()">编辑类别</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="base:product_category:del">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-delete-s1'" onclick="delProductCategory();">删除类别</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="base:product_category:create">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-excel'" onclick="importProductCategory();">产品类别信息导入</a>
	</shiro:hasPermission>
	<!-- 搜索筛选 -->
	&nbsp;分类名称：&nbsp;<input id="productCategoryName" class="easyui-textbox" name="productCategoryName" style="width: 130px">
	&nbsp;分类等级：&nbsp;
			<select id="searchLevel"
				name="level" style="width: 60px">
					<option value="">全部</option>
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
			</select>

	&nbsp;父分类ID：<input class="easyui-textbox" id="searchId"
				style="width: 150px" />
		&nbsp;<a href="#" class="easyui-linkbutton"
				iconcls="icon-search" onclick="loadProductCategoryData()">搜索</a>
	
</div>


<table id="productCategorylist" title="产品分类列表"
	style="width: auto; height: 350px">
</table>

<div id="addProductCategory" class="easyui-dialog" title="新增" 
data-options="modal:true,closed:true,iconCls:'icon-document'"
style="width: 400px;padding: 20px;"  buttons="#dlg_parentCategory-buttons">
	<form id="productCategoryForm" method="post">
		<input type="hidden" name="productCategoryId">
			<br/>
			<div class="fitem">
			          <label class="fitemlabel width_70">分类名称:</label>
			          <input class="easyui-textbox" name="productCategoryName" style="width: 260px; height: 22px"
							data-options="prompt:'输入分类名称...',required:true">&nbsp;
			</div>	
			<div class="fitem">
			          <label class="fitemlabel width_70">分类等级:</label>
			          		<select id="productCategoryLevel" name="level" style="width: 70px;">
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
							</select>
					  <label class="fitemlabel width_70">父级分类:</label>
					  <select id="parentCategoryIdSelect" name="parentCategoryId" style="width: 110px;"></select>
					  
			</div>
			<div class="fitem">
			          <label class="fitemlabel width_70">分类简介:</label>
			          <input class="easyui-textbox" name="productCategoryDesc" style="width: 260px; height: 62px"
							data-options="multiline:true,required:true">&nbsp;
			</div>	
			<div class="fitem">
			          <label class="fitemlabel width_70">设置状态：</label>
					  <input type="hidden" name="status" id="status_productCategory_hidden" value='0' >
					  <input class="easyui-switchbutton" id="status_productCategory" style="width: 160px;">
			</div>
			
			
	</form>
	<div id="dlg_parentCategory-buttons"  style="text-align: center;">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="submitProductCategoryForm()" style="width:120px;height: 32px">确认提交</a>
	</div>
</div>

<div id="importProductCategoryDiv" style="width: 400px; padding: 10px;" class="easyui-window" data-options="modal : true,
			closed : true,
			iconCls : 'icon-excel',
			title : '产品类别信息导入'">
	<form id="importProductCategoryForm" method="post"
		action="product_category/importproductcategory"
		enctype="multipart/form-data">
		<input class="easyui-filebox" style="width: 300px;"
			name="uploadImportFile"
			data-options="prompt:'产品类别信息导入文件',required:true,buttonText: '选择文件',buttonAlign: 'right'" />
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-ok'"
			onclick="importProductCategorySubmit();">提交</a>
			<a href="#" target="_blank" id="importProductCategorySubmitTempl" class="easyui-linkbutton"
			data-options="iconCls:'icon-download-s1'">模板下载</a>
	</form>
</div>


<script type="text/javascript">
	var initFlag_category = 0;
	$(function() {
		$("#searchLevel").combobox({
			editable : false
		});
		loadProductCategoryData();
		$.get('logistics/getdownloadurl', function(data) {
			data = eval('(' + data + ')');
			if (data.url) {
				$('#importProductCategorySubmitTempl').prop('href',
						data.url + '\cplbxx.xls');
			}
		});
		
		
	    $('#status_productCategory').switchbutton({
	        checked: true,
	        onText:'启用',
	        offText:'禁用',
	        onChange: function(checked){
	            var statusvar = '1';
	            if(checked){
	            	statusvar='0';
	            }
	            $('#status_productCategory_hidden').attr('value',statusvar);
	        }
	    })
		
	});

	//新增产品分类，确认提交
	function submitProductCategoryForm() {
		$('#productCategoryForm').form(
				'submit',
				{
					url : 'product_category/addProductCategory',
					onSubmit : function(param) {
						// param.parentCategoryId = $("#parentCategoryIdSelect").combobox("getValue");
						var myUserCompanyId = $('#index_user_companys')
								.combobox('getValue');
						param.companyId = myUserCompanyId;
						return $(this).form('enableValidation')
								.form('validate');
					},
					success : function(data) {
						var msg = eval('(' + data + ')');
						$.messager.alert('信息提示', msg.msg, 'info');
						if (msg.code == 1) {
							$('#addProductCategory').window('close');
							$('#productCategorylist').datagrid('reload');
						}
					}
				});
	}

	// 删除产品分类
	function delProductCategory() {
		var row = $('#productCategorylist').datagrid('getSelected');
		if (row) {
			$.messager.confirm('删除产品分类', '确认删除?', function(r) {
				if (r) {
					$.post("product_category/delProductCategory", {
						productCategoryId : row.productCategoryId
					}, function(data) {
						var msg = eval('(' + data + ')');
						$.messager.alert('信息提示', msg.msg, 'info');
						if (msg.code == 1) {
							$('#productCategorylist').datagrid('reload');
						}
					})
				}
			});
		} else {
			$.messager.alert('信息提示', '请选择一行记录！', 'info');
		}

	}

	//数据导入
	function importProductCategory() { 
		$('#importProductCategoryDiv').window('open');
	}

	//数据导入
	function importProductCategorySubmit() {
		$('#importProductCategoryForm').form('submit', {
			url : 'product_category/importproductcategory',
			type : 'post',
			onSubmit : function(param) {
				param.companyId = $('#index_user_companys').combobox('getValue');
				return $(this).form('enableValidation').form('validate');
			},
			success : function(data) {
				var msg = eval('(' + data + ')');
				$.messager.alert('信息提示', msg.msg, 'info');
				if (msg.code == '1') {
					$('#importProductCategoryDiv').window('close');
					$('#productCategorylist').datagrid('reload');
				}
			}
		});
	}

	//打开新增角色窗口
	function openProductCategoryWindow() {
		if (initFlag_category == 0) {
			initWindowcomponent_category();
			initFlag_category = 1;
		}
		$("#productCategoryForm").form('clear').form('disableValidation');
		$("#productCategoryLevel").combobox("setValue", "1");
		$("#parentCategoryIdSelect").combobox('disable');
		$('#addProductCategory').window({
			modal : true,
			closed : true,
			iconCls : 'icon-document',
			title : '新增产品分类'
		});
		$('#addProductCategory').window('open');
		
		$("#status_productCategory").switchbutton({checked: true});
		$('#status_productCategory_hidden').attr('value','0');
		
		
		
	}

	
	function initCategoryLevel() {
		$("#productCategoryLevel").combobox(
				{
					editable : false,
					onSelect : function(data) {
						$("#parentCategoryIdSelect").combobox('clear');
						if (data.value == 1) {
							$("#parentCategoryIdSelect").combobox(
									'disableValidation');
							$("#parentCategoryIdSelect").combobox('disable');
						} else {
							// 					initParentCategory(data.value);
							$("#parentCategoryIdSelect").combobox(
									'reload',
									"product_category/list?level=" + (data.value - 1) + 
											"&companyId=" + $("#index_user_companys").combobox("getValue"));
							$("#parentCategoryIdSelect").combobox('enable');
						}
					}
				});
	}
	function initWindowcomponent_category() {
		$("#parentCategoryIdSelect").combobox({
			editable : false,
			valueField : 'productCategoryId',
			textField : 'productCategoryName',
			required : true
		});
		initCategoryLevel();
	}

	//编辑产品分类
	function editProductCategory() {
		var row = $('#productCategorylist').datagrid('getSelected');
		if (row) {
			$('#productCategoryForm').form('clear');
			if (initFlag_category == 0) {
				initWindowcomponent_category();
				initFlag_category = 1;
			}
			$("#parentCategoryIdSelect").combobox("reload","product_category/list?level=" + (row.level - 1));
			$('#productCategoryForm').form('load', {
				productCategoryId : row.productCategoryId,
				productCategoryName : row.productCategoryName,
				productCategoryDesc : row.productCategoryDesc,
				level : row.level,
				parentCategoryId : row.parentCategoryId
			});
			
			
			if(row.status==undefined || row.status=="" || row.status=="1"){
				$("#status_productCategory").switchbutton({checked: false});
				$('#status_productCategory_hidden').attr('value','1');
			}else{
				$("#status_productCategory").switchbutton({checked: true});
				$('#status_productCategory_hidden').attr('value','0');
			}
			
			
			if (row.level > 1) {
				$("#parentCategoryIdSelect").combobox('enable');
			} else {
				$("#parentCategoryIdSelect").combobox('disable');
			}
			$('#addProductCategory').window({
				modal : true,
				closed : true,
				iconCls : 'icon-document',
				title : '修改产品分类'
			});
			$('#addProductCategory').window('open');
		} else {
			$.messager.alert('信息提示', '请选择一行记录！', 'info');
		}
	}
	/**
	 * Name 载入数据
	 */
	function loadProductCategoryData() {
		<shiro:hasPermission name="base:product_category:update">
		 $('#productCategorylist').datagrid({
				onDblClickCell: function(index,field,value){
					editProductCategory()
				}
		 });
		</shiro:hasPermission>
		$('#productCategorylist').datagrid(
				{
					url : 'product_category/findAllList',
					rownumbers : true,
					pageSize : 10,
					pageList : [ 10, 20, 50 ],
					pagination : true,
					singleSelect : true,
					multiSort : true,
					fitColumns : true,
					fit : true,
					toolbar : '#productCategoryBar',
					onBeforeLoad : function(param) {
						param.productCategoryName = $("#productCategoryName")
								.val();
						param.level = $("#searchLevel").combobox('getValue');
						param.parentCategoryId = $("#searchId").val();
						var myUserCompanyId = $('#index_user_companys')
								.combobox('getValue');
						param.companyId = myUserCompanyId;
					},
					columns : [ [ {
						field : 'productCategoryName',
						title : '分类名称',
						width : 100,
						sortable : true
					}, {
						field : 'productCategoryDesc',
						title : '分类简介',
						width : 100
					}, {
						field : 'level',
						title : '等级',
						width : 100
					}, {
						field : 'parentCategoryId',
						title : '父分类ID',
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
					}

					] ]
				});
	}
</script>