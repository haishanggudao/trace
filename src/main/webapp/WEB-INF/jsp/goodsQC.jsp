<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<div id="goodsQCBar" style="padding: 5px 0;">
	<shiro:hasPermission name="operation:qc:add">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-add-s1'" onclick="openGoodsQCWindow()">新增质检</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="operation:qc:edit">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit-s1'" onclick="editGoodsQC()">编辑质检</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="operation:qc:delete">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-delete-s1'" onclick="delGoodsQC();">删除质检</a>
	</shiro:hasPermission>
</div>

<table id="goodsQClist" title="商品质检信息列表" style="width: auto; height: 350px">
</table>
<div id="addGoodsQC" style="width: 500px; padding: 10px;">
	<form id="goodsQCForm" method="post">
		<input type="hidden" name="materialQcId">
		<!-- <div>商品:</div>
		<br /> <input name="goods.goodsId" id="goodsID_qc" style="width: 100%;">
		<p></p> -->
		<div>产品:</div>
		<br /> <input class="easyui-combobox" id="productId_goodsqc" name="goods.productId" style="width: 100%;">
		<p></p>
		<div>规格:</div>
		<br /> <input class="easyui-combobox" id="productStandardDetailId_goodsqc" name="goods.productStandardDetailId" style="width: 100%;" >
		<p></p>
		<div>商品批次:</div>
		<br /> <input class="easyui-combobox" id="goodsBatch_goodsqc" name="goods.goodsId" style="width: 100%;">
		<p></p>
		<div>产地证明编号:</div>
		<br /> <input class="easyui-textbox" name="originNo" data-options="required:true" style="width: 100%;">
		<p></p>
		<div>检验检疫证书编号:</div>
		<input name="quarantineNo" class="easyui-textbox" style="width: 100%;" data-options="required:true">
		<p></p>
		<div>质量安全检测:</div>
		<input name="inspection" class="easyui-textbox" style="width: 100%;" data-options="required:true">
		<p></p>
		<div>检测日期：</div>
		<input name="inspectionDate" id="inspectionDate_goodsQC" style="width: 100%;">
		<p></p>
		<div>样品名称：</div>
		<input name="sampleName" class="easyui-textbox" style="width: 100%;">
		<p></p>
		<div>检测结果：</div>
		<input name="result" class="easyui-textbox" style="width: 100%;">
	</form>
	<div style="margin: 10px 0;">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
			style="width: 100%; height: 32px" onclick="submitGoodsQCForm()">确认提交</a>
	</div>
</div>



<script type="text/javascript">
var initFlag_goodsQC=0;
$(function(){
	loadGoodsQCData();
});

	//新增或修改商品质检，确认提交
	function submitGoodsQCForm() {
		$('#goodsQCForm').form('submit', {
			url : 'qc/addGoodsQC',
			onSubmit : function(param) {
// 				param.parentCategoryId = $("#parentCategoryIdSelect").combobox("getValue");
				return $(this).form('enableValidation').form('validate');
			},
			success : function(data) {
				var msg = eval('(' + data + ')');
				$.messager.alert('信息提示', msg.msg, 'info');
				if (msg.code == 1) {
					$('#addGoodsQC').window('close');
					$('#goodsQClist').datagrid('reload');
				}
			}
		});
	}

	// 删除商品质检信息
	function delGoodsQC() {
		var row = $('#goodsQClist').datagrid('getSelected');
		if (row) {
			$.messager.confirm('删除商品质检信息', '确认删除?', function(r) {
				if (r) {
					$.post("qc/delGoodsQC", {
						materialQcId : row.materialQcId
					}, function(data) {
						var msg = eval('(' + data + ')');
						$.messager.alert('信息提示', msg.msg, 'info');
						if (msg.code == 1) {
							$('#goodsQClist').datagrid('reload');
						}
					})
				}
			});
		} else {
			$.messager.alert('信息提示', '请选择一行记录！', 'info');
		}

	}
	//打开新增角色窗口
	function openGoodsQCWindow() {
		if(initFlag_goodsQC==0){
			initWindowcomponent_goodsQC();
// 			initFlag_goodsQC=1;
		}
		$("#goodsQCForm").form('clear').form('disableValidation');
		$('#addGoodsQC').window({
			modal:true,
			closed:true,
			iconCls:'icon-document',
			title:'新增商品质检信息'
		});
		$('#addGoodsQC').window('open');
	}

	function initWindowcomponent_goodsQC(){ 
		// 产品
		$('#productId_goodsqc').combobox({
			url : 'product/list',
			valueField : 'productId',
			textField : 'productName',
			onBeforeLoad : function(param) {
				param.companyId = $('#index_user_companys').combobox('getValue');
				param.productType=1;
			},
			required:true,
			onSelect : function(record) { 
				$('#productStandardDetailId_goodsqc').combobox('clear');
				var url = 'product_standard_detail/list?productId=' + record.productId;
				$('#productStandardDetailId_goodsqc').combobox('reload', url);
				
				$('#goodsBatch_goodsqc').combobox('clear');
				url = 'goods/getgoods?productId=' + record.productId;
				$('#goodsBatch_goodsqc').combobox('reload', url);
			}
		}); 
		// combobox 规格
		$('#productStandardDetailId_goodsqc').combobox({ 
			valueField:'productStandardDetailId',
			textField:'fullStandardName',
			editable:false,
			required:true, 
			onLoadSuccess:function(data){
				if(data!=null&&data.length==1){
					$('#productStandardDetailId_goodsqc').combobox('setValue',data[0].productStandardDetailId); 
				}
			}
		}); 
		// 批号
		$("#goodsBatch_goodsqc").combobox({ 
			editable:false,
			valueField: 'goodsId',
			textField: 'goodsBatch',
			required:true 
		});  
		$("#inspectionDate_goodsQC").datebox({
			formatter:myformatter,
			parser:myparser,
			editable:false
		});
	}
	function myformatter(date){
		var y = date.getFullYear();
		var m = date.getMonth()+1;
		var d = date.getDate();
		return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
	}
	function myparser(s){
		if (!s) return new Date();
		var ss = (s.split('-'));
		var y = parseInt(ss[0],10);
		var m = parseInt(ss[1],10);
		var d = parseInt(ss[2],10);
		if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
			return new Date(y,m-1,d);
		} else {
			return new Date();
		}
	}
	//编辑商品质检信息
	function editGoodsQC() {
		var row = $('#goodsQClist').datagrid('getSelected');
		if (row) {
			$('#goodsQCForm').form('clear');
			if(initFlag_goodsQC==0){
				initWindowcomponent_goodsQC();
				initFlag_goodsQC=1;
			}
			// 规格明细
			var url = 'product_standard_detail/list?productId=' + row.goods.product.productId;
			$('#productStandardDetailId_goodsqc').combobox('reload', url);
			// 商品批号
			url = 'goods/getgoods?productId=' + row.goods.product.productId;
			$('#goodsBatch_goodsqc').combobox('reload', url); 
		
			$('#goodsQCForm').form('load', {
				materialQcId : row.materialQcId,
				'goods.productId' : row.goods.product.productId,
				'goods.productStandardDetailId' : row.goods.productStandardDetail.productStandardDetailId,
				'goods.goodsId' : row.goods.goodsId,
				originNo : row.originNo,
				quarantineNo:row.quarantineNo,
				inspection:row.inspection,
				inspectionDate:row.inspectionDate,
				sampleName:row.sampleName,
				result:row.result
				
			});
			$('#addGoodsQC').window({
				modal:true,
				closed:true,
				iconCls:'icon-save',
				title:'修改商品质检信息'
			});
			$('#addGoodsQC').window('open');
		} else {
			$.messager.alert('信息提示', '请选择一行记录！', 'info');
		}
	}
	/**
	 * Name 载入数据
	 */
	function loadGoodsQCData(){
		$('#goodsQClist').datagrid({
			url : 'qc/findAllList',
			rownumbers : true,
			pageSize : 10,
			pageList : [ 10, 20, 50 ],
			pagination : true,
			singleSelect : true,
			multiSort : true,
			fitColumns : true,
			fit : true,
			toolbar : '#goodsQCBar',
			onBeforeLoad : function(param) {
				param.companyId=$("#index_user_companys").combobox('getValue');
			},
			columns : [ [
			 			{
			 				field : 'goodsName',
			 				title : '商品名称',
			 				width : 100,
			 				sortable : true,
			 				formatter : function(val, row, index) {
								return row.goods.goodsName;
							}
			 			}, {
			 				field : 'originNo',
			 				title : '产地证明编号',
			 				width : 100
			 			}, {
			 				field : 'quarantineNo',
			 				title : '检验检疫证书编号',
			 				width : 100
			 			},
			 			{
			 				field : 'inspection',
			 				title : '质量安全检测',
			 				width : 100
			 			},
			 			{
			 				field : 'inspectionDate',
			 				title : '检测日期',
			 				width : 100
			 			}

			 			] ]
		});
	}
	
	
	
</script>