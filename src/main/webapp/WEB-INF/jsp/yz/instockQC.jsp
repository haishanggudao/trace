<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<div id="goodsQCBar" style="padding: 5px 0;">
	&nbsp;&nbsp;进货批次:&nbsp;
	<select id="iqc_instockBatchNum" class="easyui-combobox" style="min-width: 150px;" data-options="
	url : 'qc/getbatchnum',onBeforeLoad: function(param){param.companyId = $('#index_user_companys').combobox('getValue');},
	method : 'post',valueField:'id',textField:'val'
	"></select>&nbsp;
	进货时间:&nbsp; 
	<input id="iqc_instockDate_s" class="easyui-datebox" data-options="prompt:'进货时间1'" />
	-
	<input id="iqc_instockDate_e" class="easyui-datebox" data-options="prompt:'进货时间2'" />
	&nbsp;
	<a href="#" class="easyui-linkbutton" iconcls="icon-search" onclick="dosearch()">搜索</a>
</div>
<table id="findallinstocklist" title="商品质检信息列表" style="width: auto; height: 350px">
</table>
<div id="yzinstockqcWindows" style="width: 500px; padding: 10px;">
	<form id="instockqcForm" method="post" enctype="multipart/form-data">
		<input type="hidden" name="id" />
		<input type="hidden" name="instockMainId" />
		<input type="hidden" name="instockBatchNum" />
		<input type="hidden" name="qcmaterialsURL" />
		<input type="hidden" name="goodsId" />
		<div>质检材料：</div>
		<br />
		<div id="qcmaterialURLAppend2"></div>
		<br />
		<div id="qcmaterialURLAppend1_div">
			<input id="qcmaterialURL_1_0" name="qcmaterialURLFiles[0]"
				class="easyui-filebox" style="width: 90%;"
				data-options="prompt:'选择产品图片',buttonText:'选择产品图片'" /> <a href="#"
				class="easyui-linkbutton" style="width: 8%;"
				onclick="javascript:addQCmaterialURLAppend()">添加</a>
			<div id="qcmaterialURLAppend1"></div>
		</div>
	</form>
	<div style="margin: 10px 0;" id="yzinstockqcsubmit_div">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
			style="width: 100%; height: 32px" onclick="submitGoodsQCForm()">确认提交</a>
	</div>
</div>
<script type="text/javascript">
	
	$(function() {
		loadFindAllInstockList();
	});

	var qcMaterialURLAppendIndex = 1;
	
	function addQCmaterialURLAppend() {
		$('#qcmaterialURLAppend1').append('<br /><div id="qcmaterialURL_1_' + qcMaterialURLAppendIndex + '_div"><input id="qcmaterialURL_1_' + qcMaterialURLAppendIndex + '"  name="qcmaterialURLFiles[' + qcMaterialURLAppendIndex + ']" class="easyui-filebox" style="width:90%;" data-options="prompt:\'选择产品图片\',buttonText:\'选择产品图片\'" /></div>');
		$.parser.parse('#' + 'qcmaterialURL_1_' + qcMaterialURLAppendIndex + '_div');
		qcMaterialURLAppendIndex = qcMaterialURLAppendIndex + 1;
	}
	
	function submitGoodsQCForm() {
		$('#instockqcForm').form('submit', {
			url : 'qc/saveInstockQC',
			onSubmit : function(param) {
				return $(this).form('enableValidation').form('validate');
			},
			success : function(data) {
				//console.info(data);
				var json = JSON.parse(data);
				if (json.code == 1) {
					$('#yzinstockqcWindows').window('close');
					$('#findallinstocklist').datagrid('reload');
				} else {
					$.messager.alert('信息提示', json.msg, 'warn');
				}
			}
		});
	}
	
	function dosearch() {
		var iqcibn = $('#iqc_instockBatchNum').combobox('getText');
		var iqcids = $('#iqc_instockDate_s').datebox('getValue');
		var iqcide = $('#iqc_instockDate_e').datebox('getValue');
		$('#findallinstocklist').datagrid({
				url : 'qc/findallinstocklist',
				queryParams : {
					instockBatchNum : iqcibn,
					instockDate1 : iqcids,
					instockDate2 : iqcide
				}
		});
	}
	
	function doview(index) {
		qcMaterialURLAppendIndex = 1;
		$('#qcmaterialURLAppend1_div').hide();
		$('#qcmaterialURLAppend1').html('');
		$('#yzinstockqcsubmit_div').hide();
		var rows = $('#findallinstocklist').datagrid('getRows');
		var row = rows[index];
		//console.info(row);
		$('#instockqcForm').form('clear');
		$('#instockqcForm').form('load',{
			id : row.id,
			instockMainId : row.instockMainId,
			instockDate : row.instockDate,
			instockBatchNum : row.instockBatchNum,
			qcmaterialsURL : row.qcmaterialsURL,
			goodsId : row.goodsId
		});
		$('#qcmaterialURLAppend2').html('');
		//console.info(row.qcmaterialsURL);
		if(row.qcmaterialsURL != null && row.qcmaterialsURL != '') {
			var urls =  row.qcmaterialsURL.split(',');
			//console.info(urls);
			for ( var index in urls) {
				$('#qcmaterialURLAppend2').append('<div><a id="qcmaterialURLAppend2_1_' + index + '" href="' + urls[index] + '" target="_blank">' + urls[index] + '</a>&nbsp;&nbsp;<a  id="qcmaterialURLAppend2_2_' + index + '" href="#" class="easyui-linkbutton" style="width: 54px;" onclick="javascript:deleteImage(\'' + row.id + '\',\'' + urls[index] + '\',\'' + index + '\')">删除</a></div>');
			}
			$.parser.parse('#qcmaterialURLAppend2');
		}
		$('#yzinstockqcWindows').window({
			modal : true,
			closed : true,
			title : '查看'
		});
		$('#yzinstockqcWindows').window('open');
	}
	
	function deleteImage(_id, _url, index) {
		$.post("qc/deleteimage", {
			id : _id,
			url : _url
		}, function(data) {
			$('#qcmaterialURLAppend2 #qcmaterialURLAppend2_1_' + index).hide();
			$('#qcmaterialURLAppend2 #qcmaterialURLAppend2_2_' + index).hide();
			$('#findallinstocklist').datagrid('reload');
		});
	}

	function doupload(index) {
		qcMaterialURLAppendIndex = 1;
		$('#qcmaterialURLAppend1_div').show();
		$('#qcmaterialURLAppend1').html('');
		$('#qcmaterialURLAppend2').html('');
		$('#yzinstockqcsubmit_div').show();
		var rows = $('#findallinstocklist').datagrid('getRows');
		var row = rows[index];
		//console.info(row);
		$('#instockqcForm').form('clear');
		$('#instockqcForm').form('load', {
			id : row.id,
			instockMainId : row.instockMainId,
			instockDate : row.instockDate,
			instockBatchNum : row.instockBatchNum,
			qcmaterialsURL : row.qcmaterialsURL,
			goodsId : row.goodsId
		});
		$('#yzinstockqcWindows').window({
			modal : true,
			closed : true,
			title : '上传'
		});
		$('#yzinstockqcWindows').window('open');
	}

	/**
	 * Name 载入数据
	 */
	function loadFindAllInstockList() {
		$('#findallinstocklist')
				.datagrid(
						{
							url : 'qc/findallinstocklist',
							rownumbers : true,
							pageSize : 20,
							pageList : [ 10, 20, 50 ],
							pagination : true,
							singleSelect : true,
							multiSort : true,
							fitColumns : true,
							fit : true,
							toolbar : '#goodsQCBar',
							onBeforeLoad : function(param) {
								param.companyId = $("#index_user_companys")
										.combobox('getValue');
							},
							columns : [ [
									{
										field : 'instockBatchNum',
										title : '进货批次',
										width : '20%',
										align : 'center'
									},
									{
										field : 'productName',
										title : '产品名称',
										width : '20%',
										align : 'center'
									},
									{
										field : 'instockDate',
										title : '进货时间',
										width : '20%',
										formatter : function(val, row, index) {
											if (val != '') {
												var d = new Date(val);
												return d.format("yyyy-MM-dd");
											}
											return '';
										},
										align : 'center'
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
												return '<a href="#" class="easyui-linkbutton" iconcls="icon-search" onclick="doupload('
														+ index
														+ ')">上传</a>&nbsp;/&nbsp;<a href="#" class="easyui-linkbutton" iconcls="icon-search" onclick="doview('
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