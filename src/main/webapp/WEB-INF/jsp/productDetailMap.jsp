<%@ page contentType="text/html;charset=UTF-8" language="java"%> 
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!-- Begin of toolbar -->
<link rel="stylesheet" type="text/css" href="static/css/datagrid_util.css">
<div id="wu-productDetailMapBar" style="padding: 5px 0;"> 

	<shiro:hasPermission name="base:product_detail_map:add">
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add-s1'" onclick="productDetailMapAdd()" >新增属性</a> 
	</shiro:hasPermission>
	<shiro:hasPermission name="base:product_detail_map:edit">
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit-s1'" onclick="productDetailMapEdit()" >修改属性</a>  
	</shiro:hasPermission>
	<shiro:hasPermission name="base:product_detail_map:delete">
		<a href="#"	class="easyui-linkbutton" data-options="iconCls:'icon-delete-s1'"	onclick="productDetailMapRemove()" >删除属性</a> 
	</shiro:hasPermission>
	<shiro:hasPermission name="base:product_detail_map:list">
		&nbsp;产品分类：&nbsp;<select id="searchProductCategoryId" name="productCategoryId" style="width: 150px;"> </select>
		&nbsp;企业：&nbsp;<select id="searchCompanyId" name="companyId" style="width: 160px;"></select>
		&nbsp;<a href="#" class="easyui-linkbutton" iconcls="icon-search" onclick="loadProductDetailMap()">搜索</a> 
	</shiro:hasPermission>
</div>
<!-- End of toolbar --> 

<table id="productDetailMapList" title="产品附加属性列表" style="width: 700px; height: 500px"></table> 

<!-- Begin of easyui-dialog -->
<div id="productDetailMapDialog" class="easyui-dialog" style="width: 400px;  padding: 20px;"  
	data-options="closed:true,iconCls:'icon-save'"   buttons="#dlg_productDetail-buttons">
	<form id="productDetailMapForm" method="post">
			<input type="hidden" name="productDetailMapId" />
			<div class="fitem">
			          <label class="fitemlabel width_70">产品分类：</label>
			          <select id="productCategoryId_pdm" name="productCategoryId" style="width: 266px;"> </select>&nbsp;
			</div>	
			<div class="fitem">
			          <label class="fitemlabel width_70">生产企业：</label>
			          <select id="companyId" name="companyId" style="width: 266px;"></select>&nbsp;
			</div>		
			<div class="fitem">
			          <label class="fitemlabel width_70">属性表名：</label>
			          <input type="text" id="productDetailTable" name="productDetailTable" class="easyui-textbox wu-text" data-options=" " />&nbsp;
			</div>		
			<div class="fitem">
			          <label class="fitemlabel width_70">设置状态：</label>
					 <input type="hidden" name="status" id="status_productDetail_hidden" value='0' >
					 <input class="easyui-switchbutton" id="status_productDetail" style="width: 160px;">
					 
			</div>
			<div id="dlg_productDetail-buttons"  style="text-align: center;">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="submitProductDetailMap()" style="width:120px;height: 32px">确认提交</a>
			</div>
	</form>
</div> 
<!-- End of easyui-dialog -->


<script type="text/javascript">
$(function() {
	
	$("#searchProductCategoryId").combobox({
		url:'product_category/list',
		method:'post',
		valueField:'productCategoryId',
		textField:'productCategoryName' 
	});
	
	$("#searchCompanyId").combobox({
		url:'company/list',
		method:'post',
		valueField:'companyid',
		textField:'name' 
	});
	
	loadProductDetailMap(); 
	
    $('#status_productDetail').switchbutton({
        checked: true,
        onText:'启用',
        offText:'禁用',
        onChange: function(checked){
            var statusvar = '1';
            if(checked){
            	statusvar='0';
            }
            $('#status_productDetail_hidden').attr('value',statusvar);
        }
    })
});

/**
 * button => 打开添加窗口
 */
function productDetailMapAdd() { 
	 $("#productCategoryId_pdm").combobox({
			url:'product_category/list',
			method:'post',
			valueField:'productCategoryId',
			textField:'productCategoryName', 
			required:true
		});
	 
	 $("#companyId").combobox({
			url:'company/list',
			method:'post',
			valueField:'companyid',
			textField:'name' , 
			required:true
		});
	 
	$('#productDetailMapForm').form('clear').form('disableValidation');
	

	$("#status_productDetail").switchbutton({checked: true});
	$('#status_productDetail_hidden').attr('value','0');
	
    $('#productDetailMapDialog').dialog({
		closed : false,
		modal : true,
		title : "新增产品属性"
    });  
}

/**
 * button => 打开修改窗口
 */
function productDetailMapEdit() {
	 $("#productCategoryId_pdm").combobox({
			url:'product_category/list',
			method:'post',
			valueField:'productCategoryId',
			textField:'productCategoryName', 
			required:true
		});
	 $("#companyId").combobox({
			url:'company/list',
			method:'post',
			valueField:'companyid',
			textField:'name' , 
			required:true
		});
	    
	var row = $('#productDetailMapList').datagrid('getSelected');
    
    if (row) {
	$('#productDetailMapForm').form('clear');
	$('#productDetailMapForm').form('load', {
		productDetailMapId : row.productDetailMapId,
		productCategoryId : row.productCategoryId,
		companyId : row.companyId,
		productDetailTable : row.productDetailTable,
		status : row.status 
	});
	
	if(row.status==undefined || row.status=="" || row.status=="1"){
		$("#status_productDetail").switchbutton({checked: false});
		$('#status_productDetail_hidden').attr('value','1');
	}else{
		$("#status_productDetail").switchbutton({checked: true});
		$('#status_productDetail_hidden').attr('value','0');
	}
	
	$('#productDetailMapDialog').dialog({
	    closed : false,
	    modal : true,
	    title : "修改产品属性"
	});
    } else {
		$.messager.alert('信息提示', '请选择产品附加属性！', 'info');
    }
}   
 
/**
 * action => 保存产品附加属性
 */
function submitProductDetailMap() {
    $('#productDetailMapForm').form('submit', {
	url : 'product_detail_map/addProductDetailMap',
	onSubmit : function() {
	    return $(this).form('enableValidation').form('validate');
	},
	success : function(data) {
	    var msg = eval('(' + data + ')');
	    $.messager.alert('信息提示', msg.msg, 'info');
	    if (msg.code == 1) {
		$('#productDetailMapDialog').dialog('close'); 
		$('#productDetailMapList').datagrid('reload');
	    }
	}
    });
} 
 
/**
 * button => 产品附加属性
 */
function productDetailMapRemove() {
	var row = $('#productDetailMapList').datagrid('getSelected');
	if (row) {
		$.messager.confirm('信息提示', '确定要删除该记录？', function(result) {
			if(result){
				$.post("product_detail_map/delProductDetailMap",
						{productDetailMapId : row.productDetailMapId},
						function(data){
					var msg = eval('(' + data + ')');
					$.messager.alert('信息提示', msg.msg, 'info');
					if (msg.code == 1) {
						$('#productDetailMapList').datagrid('reload'); 
					}
				});
			}
		});
	} else {
		$.messager.alert('信息提示', '请选择产品不加属性！', 'info');
	} 
} 
 
/**
 * Name 载入数据
 */
function loadProductDetailMap(){
    <shiro:hasPermission name="base:product_detail_map:edit">
	 $('#productDetailMapList').datagrid({ 
			onDblClickCell: function(index,field,value){
				productDetailMapEdit()
			}
	 });
	</shiro:hasPermission>
	$('#productDetailMapList').datagrid({
		url : 'product_detail_map/findAllList',
		method : 'post',
		rownumbers : true,
		pageSize : 10, 
		pageList : [ 10, 20, 50 ], 
		pagination : true,
		multiSort : true,
		fitColumns : true, 
		singleSelect:true,
		fit : true,
		toolbar:'#wu-productDetailMapBar', 
		onBeforeLoad : function(param) { 
 			param.productCategoryId = $("#searchProductCategoryId").combobox('getValue'); 
 			param.companyId = $("#searchCompanyId").combobox('getValue');  
		},
		columns : [ [ {
				field : 'productDetailMapId',
				title : '属性映射ID',
				hidden : true
			}, 
			{
				field : 'productCategoryId',
				title : '产品分类ID',
				hidden : true
			},
			{
				field : 'companyId',
				title : '企业ID',
				hidden : true
			},
			{
				field : 'productCategoryName',
				title : '产品分类',
				width : 100,
				sortable : true,
				formatter : function(val, row, index) {
					return row.category.productCategoryName;
				}
			}, {
				field : 'name',
				title : '企业',
				width : 100,
				sortable : true,
				formatter : function(val, row, index) {
					return row.company.name;
				}
			}, {
				field : 'productDetailTable',
				title : '属性表名',
				width : 100
			}, {
				field : 'status',
				title : '状态',
				width : 50,
				formatter:function(val,row,index){
			    	if(val==0){
			    		return "启用";
			        }else{
			    		return "禁用";
			        }
			    } 
			} ] ] 
	});
} 
</script>