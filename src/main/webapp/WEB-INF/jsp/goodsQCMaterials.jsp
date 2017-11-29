<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<div id="goodsQCMaterialsBar" style="padding: 5px 0;">
	&nbsp;&nbsp;产品名称:&nbsp;
	<input id="productName_goodsQC" name="productName" class="easyui-textbox" style="width: 200px">
	<a href="#" class="easyui-linkbutton" iconcls="icon-search" onclick="loadGoodsQCMaterialsList()">搜索</a>
</div>
<table id="goodsQCMaterialslist" title="产品质检信息列表" style="width: auto; height: 350px">
</table>
<div id="goodsQCMaterialsWindows" style="width: 500px; padding: 10px;">
	<form id="goodsQCMaterialsForm" method="post" enctype="multipart/form-data">
		<input type="hidden" name="id" />
		<input type="hidden" name="qcmaterialsURL" />
		<input type="hidden" name="goodsId" />
		<div>质检材料：</div>
		<br />
		<div id="goodsQCMaterialURLAppend2"></div>
		<br />
		<div id="goodsQCmaterialURLAppend1_div">
			<input id="qcmaterialURL_1_0" name="qcmaterialURLFiles[0]"
				class="easyui-filebox" style="width: 90%;"
				data-options="prompt:'选择质检图片',buttonText:'选择图片'" /> <a href="#"
				class="easyui-linkbutton" style="width: 8%;"
				onclick="javascript:addQCmaterialURLAppend()">添加</a>
			<div id="goodsqcmaterialURLAppend1"></div>
		</div>
	</form>
	<div style="margin: 10px 0;" id="goodsQCSubmit_div">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
			style="width: 100%; height: 32px" onclick="submitGoodsQCMaterialsForm()">确认提交</a>
	</div>
</div>
<script type="text/javascript">
	
	$(function() {
		loadGoodsQCMaterialsList();
	});

	var qcMaterialURLAppendIndex = 1;
	
	function addQCmaterialURLAppend() {
		$('#goodsqcmaterialURLAppend1').append('<br /><div id="qcmaterialURL_1_' + qcMaterialURLAppendIndex + '_div"><input id="qcmaterialURL_1_' + qcMaterialURLAppendIndex + '"  name="qcmaterialURLFiles[' + qcMaterialURLAppendIndex + ']" class="easyui-filebox" style="width:90%;" data-options="prompt:\'选择产品图片\',buttonText:\'选择产品图片\'" /></div>');
		$.parser.parse('#' + 'qcmaterialURL_1_' + qcMaterialURLAppendIndex + '_div');
		qcMaterialURLAppendIndex = qcMaterialURLAppendIndex + 1;
	}
	
	function submitGoodsQCMaterialsForm() {
		$('#goodsQCMaterialsForm').form('submit', {
			url : 'goodsqc/saveGoodsQC',
			onSubmit : function(param) {
				return $(this).form('enableValidation').form('validate');
			},
			success : function(data) {
				//console.info(data);
				var json = JSON.parse(data);
				if (json.code == 1) {
					$('#goodsQCMaterialsWindows').window('close');
					$('#goodsQCMaterialslist').datagrid('reload');
				} else {
					$.messager.alert('信息提示', json.msg, 'warn');
				}
			}
		});
	}
	
	
	function doview(index) {
		qcMaterialURLAppendIndex = 1;
		$('#goodsQCmaterialURLAppend1_div').hide();
		$('#goodsqcmaterialURLAppend1').html('');
		$('#goodsQCSubmit_div').hide();
		var rows = $('#goodsQCMaterialslist').datagrid('getRows');
		var row = rows[index];
		//console.info(row);
		$('#goodsQCMaterialsForm').form('clear');
		$('#goodsQCMaterialsForm').form('load',{
			id : row.id,
			qcmaterialsURL : row.qcmaterialsURL,
			goodsId : row.goodsId
		});
		$('#goodsQCMaterialURLAppend2').html('');
		//console.info(row.qcmaterialsURL);
		if(row.qcmaterialsURL != null && row.qcmaterialsURL != '') {
			var urls =  row.qcmaterialsURL.split(',');
			//console.info(urls);
			for ( var index in urls) {
				$('#goodsQCMaterialURLAppend2').append('<div><a id="goodsQCMaterialURLAppend2_1_' + index + '" href="' + urls[index] + '" target="_blank">' + urls[index] + '</a>&nbsp;&nbsp;<a  id="goodsQCMaterialURLAppend2_2_' + index + '" href="#" class="easyui-linkbutton" style="width: 54px;" onclick="javascript:deleteImage(\'' + row.id + '\',\'' + urls[index] + '\',\'' + index + '\')">删除</a></div>');
			}
			$.parser.parse('#');
		}
		$('#goodsQCMaterialsWindows').window({
			modal : true,
			closed : true,
			title : '查看'
		});
		$('#goodsQCMaterialsWindows').window('open');
	}
	
	function deleteImage(_id, _url, index) {
		$.post("goodsqc/deleteImage", {
			id : _id,
			url : _url
		}, function(data) {
			$('#goodsQCMaterialURLAppend2 #goodsQCMaterialURLAppend2_1_' + index).hide();
			$('#goodsQCMaterialURLAppend2 #goodsQCMaterialURLAppend2_2_' + index).hide();
			$('#goodsQCMaterialslist').datagrid('reload');
		});
	}

	function doupload(index) {
		qcMaterialURLAppendIndex = 1;
		$('#goodsQCmaterialURLAppend1_div').show();
		$('#goodsqcmaterialURLAppend1').html('');
		$('#goodsQCMaterialURLAppend2').html('');
		$('#goodsQCSubmit_div').show();
		var rows = $('#goodsQCMaterialslist').datagrid('getRows');
		var row = rows[index];
		$('#goodsQCMaterialsForm').form('clear');
		$('#goodsQCMaterialsForm').form('load', {
			id : row.id,
			qcmaterialsURL : row.qcmaterialsURL,
			goodsId : row.goodsId
		});
		$('#goodsQCMaterialsWindows').window({
			modal : true,
			closed : true,
			title : '上传'
		});
		$('#goodsQCMaterialsWindows').window('open');
	}

	/**
	 * Name 载入数据
	 */
	function loadGoodsQCMaterialsList() {
		$('#goodsQCMaterialslist')
				.datagrid(
						{
							url : 'goodsqc/findAllList',
							rownumbers : true,
							pageSize : 20,
							pageList : [ 10, 20, 50 ],
							pagination : true,
							singleSelect : true,
							multiSort : true,
							fitColumns : true,
							fit : true,
							toolbar : '#goodsQCMaterialsBar',
							onBeforeLoad : function(param) {
								param.companyId = $('#index_user_companys').combobox('getValue');
								param.productType = '1';
								param.productName = $("#productName_goodsQC").val();
							},
							columns : [ [
									{
										field : 'productName',
										title : '产品',
										width : 70
									}, {
										field : 'productStandard',
										title : '产品规格明细',
										width : 50,
										sortable : true,
										formatter : function(val, row, index) {
											return row.productStandardDetail.fullStandardName;
										}
									},
									{
										field : 'goodsBatch',
										title : '产品批次',
										width : 50
									},
									{
										field : 'createTime',
										title : '创建时间',
										width : 50,
										formatter : function(value, row) {
											if(row.createTime!=undefined){
												return convertTimeStamp(row.createTime);
											}
										}
									},
									{
										field : 'qcmaterialsURL',
										title : '是否上传检测报告',
										width : '20%',
										formatter : function(val, row, index) {
											if (val != '' && val != null) {
												return '是';
											}
											return '否';
										},
										align : 'center'
									},
									{
										field : 'id',
										title : '操作',
										width : '20%',
										formatter : function(val, row, index) {
											if (row.qcmaterialsURL != ''
													&& row.qcmaterialsURL != null) {
												return '<a href="#" onclick="doupload('
														+ index
														+ ')">上传</a>&nbsp;/&nbsp;<a href="#" onclick="doview('
														+ index + ')">查看</a>';
											}
											return '<a href="#" class="easyui-linkbutton" iconcls="icon-search" onclick="doupload('
													+ index + ')">上传</a>';
										},
										align : 'center'
									} ] ]
						});
	}
</script>