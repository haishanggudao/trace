<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<link rel="stylesheet" type="text/css" href="static/css/datagrid_util.css">
<div id="productStandardDetailBar" style="padding: 5px 0;">
	<shiro:hasPermission name="base:product_standard_detail:create">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-add-s1'" onclick="openproductStandardDetailWindow()">新增明细</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="base:product_standard_detail:update">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit-s1'" onclick="editproductStandardDetail()">编辑明细</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="base:product_standard_detail:del">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-delete-s1'" onclick="delproductStandardDetail();">删除明细</a>
	</shiro:hasPermission>
</div>


<table id="productStandardDetaillist" title="产品规格明细列表" style="width: auto; height: 350px">
</table>
<div id="addproductStandardDetail" class="easyui-dialog" title="新增" 
data-options="modal:true,closed:true,iconCls:'icon-document'"
style="width: 315px;padding: 20px;" >
	<form id="productStandardDetailForm" method="post">
		<input type="hidden" name="productStandardDetailId">
		<input type="hidden" id="hidden_product_productId" name="product.productId">
		<div class="fitem">
		          <label class="fitemlabel">产品名称:</label>
		          <select id="productId_productStandardDetail"  class="easyui-textbox"  name="text_product_productId"
					style="width: 200px;"></select>&nbsp;
		</div>
		<div class="fitem">
		          <label class="fitemlabel">产品规格:</label>
		          <select id="productStandardId_productStandardDetail"  class="easyui-combobox"  name="productStandard.productStandardId"
					style="width: 200px;"></select>&nbsp;
		</div>		
		<div class="fitem">
		          <label class="fitemlabel">规格数量:</label>
		          <input id="productStandardNum_productStandardDetail" name="productStandardNum"  style="width: 200px;">&nbsp;
		</div>
	</form>
	
	
	
	
	<div style="margin: 10px 0;">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
			style="width: 255px; height: 32px" onclick="submitproductStandardDetailForm()">确认提交</a>
	</div>
</div>

<div id="queryProducts_productStandardDetail" class="easyui-dialog" title="选择产品" 
	data-options="modal:true,closed:true,iconCls:'icon-document'"
	style="width: 600px; padding: 2px;" >
&nbsp;产品名称：&nbsp;
<input id="productName_product_productStandardDetail" name="productName" class="easyui-textbox" style="width: 200px">
<a href="#" class="easyui-linkbutton" iconcls="icon-search" onclick="loadProduct_productStandardDetail(1)">搜索</a>
<table id="productList_productStandardDetail" ></table>
<input type="hidden" id="hidden_productCategoryId_productStandardDetail"/>
<br/><br/>
</div>

<script type="text/javascript">
var bSelectOne_standardDetail = undefined;
$(function(){
	loadproductStandardDetailData();
	 
	
});




	//新增或修改产品规格，确认提交
	function submitproductStandardDetailForm() {
		$('#productStandardDetailForm').form('submit', {
			url : 'product_standard_detail/addProductStandardDetail',
			onSubmit : function(param) {
				param.companyId = $('#index_user_companys').combobox('getValue');
				return $(this).form('enableValidation').form('validate');
			},
			success : function(data) {
				var msg = eval('(' + data + ')');
				$.messager.alert('信息提示', msg.msg, 'info');
				if (msg.code == 1) {
					$('#addproductStandardDetail').window('close');
					$('#productStandardDetaillist').datagrid('reload');
				}
			}
		});
	}

	// 删除规格明细
	function delproductStandardDetail() {
		var row = $('#productStandardDetaillist').datagrid('getSelected');
		if (row) {
			$.messager.confirm('删除产品规格明细', '确认删除?', function(r) {
				if (r) {
					$.post("product_standard_detail/delProductStandardDetail", {
						productStandardDetailId : row.productStandardDetailId
					}, function(data) {
						var msg = eval('(' + data + ')');
						$.messager.alert('信息提示', msg.msg, 'info');
						if (msg.code == 1) {
							$('#productStandardDetaillist').datagrid('reload');
						}
					})
				}
			});
		} else {
			$.messager.alert('信息提示', '请选择一行记录！', 'info');
		}

	}
	//打开新增规格明细窗口
	function openproductStandardDetailWindow() {
		
		bSelectOne_standardDetail = 0;

		initWindowcomponent_standard_detail();
		$("#productStandardDetailForm").form('clear').form('disableValidation');
		$('#addproductStandardDetail').window({
			modal:true,
			closed:true,
			iconCls:'icon-document',
			title:'新增产品规格明细'
		});
		$('#addproductStandardDetail').window('open');
	}

	function endWith(value,endStr){
		 var reg=new RegExp(endStr+"$");
		 return reg.test(value);
	}
	function formatNum(value){
		if(endWith(value, "\\.00")){
			return value.substring(0,value.length-3);
		}else if(endWith(value, "\\.0")){
			return value.substring(0,value.length-2);
		}else if(endWith(value, "0")){
			return value.substring(0,value.length-1);
		}else{
			return value;
		}
	}
	function initWindowcomponent_standard_detail(){
		

		$("#productId_productStandardDetail").textbox({
			editable:false,
			required:true,
			icons:[{
				iconCls:'icon-search',
				handler: function(e){
                    $('#productName_product_productStandardDetail').textbox('clear');
                    
					$('#queryProducts_productStandardDetail').window('open'); 
					
					loadProduct_productStandardDetail(1);
				}
			}]
		});
		$("#productStandardNum_productStandardDetail").numberbox({
			min:0,
			precision:2,
			formatter:function(value){
				return formatNum(value);
			}
		});
		$("#productStandardId_productStandardDetail").combobox({
			method : 'post',
			editable:false,
			valueField: 'productStandardId',
			textField: 'productStandardName',
			required:true,
			onLoadSuccess: function(data){
				//默认选中第一行
				if(data.length >0 ){
					if( bSelectOne_standardDetail != undefined){
						$(this).combobox('select',data[0].productStandardId);
					}
				}
			},
		});
		
		

// 		initFlag_standardDetail=1;
	}
	//编辑产品规格明细
	function editproductStandardDetail() {
		var row = $('#productStandardDetaillist').datagrid('getSelected');
		if (row) {
			bSelectOne_standardDetail = undefined;
			$('#productStandardDetailForm').form('clear');
			initWindowcomponent_standard_detail();
			var url = 'product_standard/list?companyId='+ $("#index_user_companys").combobox('getValue')+'&productCategoryId='+row.product.productCategoryId;
			$("#productStandardId_productStandardDetail").combobox({
				url:url
			});
			
			$('#productStandardDetailForm').form('load', {
				productStandardDetailId:row.productStandardDetailId,
				'product.productId':row.product.productId,
				'text_product_productId': row.product.productName,
				'productStandardNum':row.productStandardNum,
				'productStandard.productStandardId':row.productStandard.productStandardId
				
			});
			$('#addproductStandardDetail').window({
				modal:true,
				closed:true,
				iconCls:'icon-document',
				title:'修改产品规格明细'
			});
			$('#addproductStandardDetail').window('open');
			

			
		

			
		} else {
			$.messager.alert('信息提示', '请选择一行记录！', 'info');
		}
	}
	/**
	 * Name 载入数据
	 */
	function loadproductStandardDetailData(){
	    <shiro:hasPermission name="base:product_standard_detail:update">
		 $('#productStandardDetaillist').datagrid({
			onDblClickCell: function(index,field,value){
				editproductStandardDetail()
			}
		 });
		</shiro:hasPermission>
		$('#productStandardDetaillist').datagrid({
			url : 'product_standard_detail/findAllList',
			rownumbers : true,
			pageSize : 10,
			pageList : [ 10, 20, 50 ],
			pagination : true,
			singleSelect : true,
			multiSort : true,
			fitColumns : true,
			fit : true,
			toolbar : '#productStandardDetailBar',
			onBeforeLoad : function(param) {
				var myUserCompanyId = $('#index_user_companys').combobox('getValue');
				param.companyId = myUserCompanyId;
			},
			columns : [ [
			 			{
			 				field : 'productName',
			 				title : '产品名称',
			 				width : 100,
			 				sortable : true,
			 				formatter : function(val, row, index) {
								return row.product.productName;
							}
			 			},
			 			{
			 				field : 'productStandardNum',
			 				title : '产品规格数量',
			 				width : 100,
			 				sortable : true,
			 				formatter : function(val, row, index) {
								return formatNum(val);
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
			 			}

			 			] ]
		});
	}
	
	function loadProduct_productStandardDetail(actionType) {
		var queryUrl_product = '';
		
		// actionType:1 => 查询
		if (actionType === 1) {
			queryUrl_product = 'product/findAllQueryList';
		} else {
			queryUrl_product = 'product/findAllList';
		}
		
		$('#productList_productStandardDetail').datagrid(
				{
					// url : 'product/findAllList?productType=1', 
					url : queryUrl_product,
					method : 'post',
					rownumbers : true,
					pageSize : 10,
					pageList : [ 10, 20, 50 ],
					pagination : true,
					pagePosition:'bottom',
					multiSort : true,
					fitColumns : true,
					iconCls : 'icon-ok',
					singleSelect : true,
					onBeforeLoad : function(param) {
						param.companyId = $("#index_user_companys").combobox("getValue"); 
						param.productName = $("#productName_product_productStandardDetail").val();
					},
					onDblClickRow: function(index,row){
						$('#hidden_productCategoryId_productStandardDetail').val("");
						// console.info(' index is '+ index ); 
						// console.info(row); 
						$('#productId_productStandardDetail').textbox('setValue', row.productName);
						$('#hidden_product_productId').val(row.productId);
						$('#hidden_productCategoryId_productStandardDetail').val(row.category.productCategoryId);
						$('#queryProducts_productStandardDetail').window('close');
						
						$("#productStandardId_productStandardDetail").combobox("clear");
						var url = 'product_standard/list?companyId='+ $("#index_user_companys").combobox('getValue')+'&productCategoryId='+row.category.productCategoryId;
						$("#productStandardId_productStandardDetail").combobox('reload',url);
					},
					columns : [ [ {
						field : 'productId',
						title : '产品ID',
						hidden : true
					},{
						field : 'productName',
						title : '产品名称',
						width : 100,
						sortable : true 
					}  ] ]
				});
	}
	 
	// on ENTER press of productName_product_productStandardDetail
	$('#productName_product_productStandardDetail').textbox({
		inputEvents:$.extend({},$.fn.textbox.defaults.inputEvents,{
			keyup:function(e){
				// console.log('keyup')
				if (e.keyCode == 13) {
					loadProduct_productStandardDetail(1);
				} 
			}
		})
	});
	
</script>