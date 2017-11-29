<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<link rel="stylesheet" type="text/css" href="static/css/datagrid_util.css">
<div id="logisticsBar" style="padding: 5px 0;">
	<shiro:hasPermission name="base:logistics:add">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-add-s1'" onclick="openLogisticsDialog()">新增物流企业</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="base:logistics:edit">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit-s1'" onclick="editLogisticsDialog()">编辑物流企业</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="base:logistics:delete">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-delete-s1'" onclick="delLogistics();">删除物流企业</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="base:logistics:import">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-excel'" onclick="importLogistics();">物流企业信息导入</a>
	</shiro:hasPermission>	
	
	
	
	
	<!-- <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="importLogistics();">物流企业信息导入</a> -->
	<!-- begin of query -->
	<!-- end of query -->
</div>

<!-- start of table list -->
<table id="logisticsList" title="物流企业列表"
	style="width: auto;">
</table>
<!-- end of table list -->

<!-- begin of adding dialog -->
<div id="addLogisticsDialog" class="easyui-dialog fm" title="编辑" 
data-options="modal:true,closed:true,iconCls:'icon-document'" 
style="width: 430px; padding: 10px;"  buttons="#dlg_logistics-buttons" >	 
	<form id="logisticsForm" method="post">
		<input type="hidden" name="logisticsId">
		<table>
			<tr height="35">
				<td align="right">物流企业：</td>
				<td>
					<input id="logisticsCompanyId_logistics" name="logisticsCompanyId" style="width: 266px"
						data-options="prompt:'输入企业名称自动会联想出亲想要的'" />
				</td>
			</tr>
			<tr height="35">
				<td width="90" align="right">联 系 人：</td>
				<td>
					<input id="contact_logistics" name="contact" type="text" class="easyui-textbox" style="width: 266px" 
						data-options="prompt:'联 系 人'"/>
				</td>
			</tr>
			<tr height="35">
				<td width="90" align="right">联系人别名：</td>
				<td>
					<input id="altContact_logistics" name="altContact" type="text" class="easyui-textbox" style="width: 266px" 
						data-options="prompt:'联系人别名'"/>
				</td>
			</tr>
			<tr height="35">
				<td width="90" align="right">电话号码：</td>
				<td>
					<input id="tel_logistics" name="tel" type="text" class="easyui-textbox" style="width: 266px" 
						data-options="prompt:'电话号码'" /></td>
			</tr>
		</table>
	</form>
</div>

<div id="dlg_logistics-buttons"  style="text-align: center;">
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="submitLogistics()" style="width:120px;height: 28px;">确认提交</a>
</div>

<!-- begin of adding dialog -->


<div id="importLogisticsDiv" style="width: 400px; padding: 10px;"
	class="easyui-dialog" title="物流企业信息导入"
	data-options="modal:true,closed:true,iconCls:'icon-document'">
	<form id="importLogisticsForm" method="post"
		action="logistics/importlogistics" enctype="multipart/form-data">
		<input class="easyui-filebox" style="width: 300px;"
			name="uploadImportFile"
			data-options="prompt:'物流企业信息导入文件',required:true,buttonText: '选择文件',buttonAlign: 'right'" />
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-ok'" onclick="importLogisticsSubmit();">提交</a>
		<a href="#" target="_blank" id="importLogisticsSubmitTempl"
			class="easyui-linkbutton" data-options="iconCls:'icon-download-s1'">模板下载</a>
	</form>
</div>


<script type="text/javascript">
	var initFlag_logistics = 0;

	$(function() {
		loadLogistics();
	});

	//***** begin of function *****
	/**
	 * function => UI => initialize the view => the data of loading the logistics
	 */
	function loadLogistics() {
		<shiro:hasPermission name="base:logistics:edit">
			 $('#logisticsList').datagrid({
					onDblClickCell: function(index,field,value){
						editLogisticsDialog()
					}
			 });
		</shiro:hasPermission>
		$('#logisticsList').datagrid({
			method : 'post',
			url : 'logistics/findAllList',
			rownumbers : true,
			pageSize : 10,
			pagePosition : 'bottom',
			pageList : [ 10, 20, 50 ],
			pagination : true,
			multiSort : true,
			fitColumns : true,
			fit : true,
			iconCls : 'icon-ok',
			singleSelect : true,
			toolbar : '#logisticsBar',
			onBeforeLoad : function(param) {
				//param.productName = $("#searchProductName_goods").val();
				param.companyId = $('#index_user_companys').combobox('getValue');
			},
			columns : [ [ {
				field : 'logisticsAlias',
				title : '物流企业名称',
				width : 100
			}, {
				field : 'contact',
				title : '联系人',
				width : 100,
				sortable : true
			}, {
				field : 'altContact',
				title : '联系人别名',
				width : 100,
				sortable : true
			}, {
				field : 'tel',
				title : '电话',
				width : 100
			} ] ]
		});

		$.get('variable/getdownloadurl', function(data) {
			data = eval('(' + data + ')');
			if (data.url) {
				$('#importLogisticsSubmitTempl').prop('href',
						data.url + '\LogisticsTemplate.xls');
			}
		});
	}

	//数据导入
	function importLogistics() {
		$('#importLogisticsDiv').window('open');
	}

	//数据导入
	function importLogisticsSubmit() {
		$('#importLogisticsForm').form(
				'submit',
				{
					url : 'logistics/importlogistics',
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
							$('#importLogisticsDiv').window('close');
							$('#logisticsList').datagrid('reload');
						}
					}
				});
	}

	/**
	 * function => UI => initialize the dialog for adding
	 */
	function initDialog_logistics() {
		$("#logisticsCompanyId_logistics").combobox({
			url : 'logistics/getLogisticsCompanys',
			method : 'post',
			valueField : 'logisticsCompanyId',
			textField : 'logisticsAlias',
			hasDownArrow : false,
			required : true,
			onBeforeLoad:function(param){
				param.companyId=$('#index_user_companys').combobox('getValue');
			}
		});
	}

	/**
	 * function => action => submit the logistics
	 */
	function submitLogistics() {
		$('#logisticsForm').form(
				'submit',
				{
					url : 'logistics/addLogistics',
					onSubmit : function(param) {
						var myUserCompanyId = $('#index_user_companys')
								.combobox('getValue');
						param.companyId = myUserCompanyId;
						param.logisticsCompanyName = $("#logisticsCompanyId_logistics").combobox(
						'getText');
						return $(this).form('enableValidation')
								.form('validate');
					},
					success : function(data) {
						//console.info('client_user/addClientUser:end');
						//console.info(data);
						var msg = eval('(' + data + ')');
						$.messager.alert('信息提示', msg.msg, 'info');
						if (msg.code == 1) {
							$('#addLogisticsDialog').window('close');
							$('#logisticsList').datagrid('reload');
						}
					}
				});
	}

	/**
	 * button menu => show the dialog of insert
	 */
	function openLogisticsDialog() {
		if (initFlag_logistics == 0) {
			initDialog_logistics();
			initFlag_logistics = 1;
		}

		$("#logisticsForm").form('clear').form('disableValidation');

		$('#addLogisticsDialog').dialog({
			closed : false,
			modal : true,
			title : "新增物流企业"
		});
	}

	/**
	 * button menu => show the dialog of update
	 */
	function editLogisticsDialog() {
		var row = $('#logisticsList').datagrid('getSelected');

		if (row) {
			$('#logisticsForm').form('clear').form('disableValidation');

			if (initFlag_logistics == 0) {
				initDialog_logistics();
				initFlag_logistics = 1;
			}

			$('#logisticsForm').form('load', {
				logisticsId : row.logisticsId,
				logisticsCompanyId : row.logisticsCompanyId,
				contact : row.contact,
				altContact : row.altContact,
				tel : row.tel
			});

			$('#addLogisticsDialog').dialog({
				closed : false,
				modal : true,
				title : "编辑物流企业"
			});
		} else {
			$.messager.alert('信息提示', '请选择一行记录！', 'info');
		}
	}

	/**
	 * button menu => delete the logistics
	 */
	function delLogistics() {
		var row = $('#logisticsList').datagrid('getSelected');
		if (row) {
			$.messager.confirm('删除物流企业信息', '确认删除?', function(r) {
				if (r) {
					$.post("logistics/delLogistics", {
						logisticsId : row.logisticsId
					}, function(data) {
						var msg = eval('(' + data + ')');
						$.messager.alert('信息提示', msg.msg, 'info');
						if (msg.code == 1) {
							$('#logisticsList').datagrid('reload');
						}
					});
				}
			});
		} else {
			$.messager.alert('信息提示', '请选择一行记录！', 'info');
		}
	}
	//***** end of function *****
</script>