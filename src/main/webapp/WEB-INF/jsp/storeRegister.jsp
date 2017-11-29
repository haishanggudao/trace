<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<div id="storeRegisterBar" style="padding: 5px 0;">
	<shiro:hasPermission name="system:storeRegister:add">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-add-s1'" onclick="openstoreRegisterDialog()"  plain="true">新增门店</a>
		<span style="color:#D3D3D3 " >| </span>
			
	</shiro:hasPermission>
	<shiro:hasPermission name="system:storeRegister:del">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-delete-s1'" onclick="deleteStoreRegister()"  plain="true">删除门店</a>
		<span style="color:#D3D3D3 " >| </span>
	</shiro:hasPermission>
</div>

<!-- start of table list -->
<table id="storeRegisterList" title="门店设备列表"
	style="width: auto; height: 350px" singleSelect="true"
	fitColumns="true">
</table>
<!-- end of table list -->

<!-- begin of adding dialog -->
<div id="addstoreRegisterDialog" class="easyui-dialog" title="编辑" 
data-options="modal:true,closed:true,iconCls:'icon-document'" 
style="width: 420px;padding: 10px;" buttons="#dlg_storeRegister-buttons">
	<form id="storeRegisterForm" method="post">
		<input type="hidden" name="id">
		<table>
			<tr height="35" style="display:none">
				<td width="90" align="right">license：</td>
				<td><input id="storeRegisterName" name="license"
					type="text" class="easyui-textbox" style="width: 266px" data-options="prompt:'输入license'"/></td>
			</tr>
			<tr height="35" style="display:none">
				<td width="90" align="right">门店编号：</td>
				<td><input name="location" type="text" class="easyui-textbox" style="width: 266px" data-options="prompt:'输入门店编号'"/></td>
			</tr>
			<tr height="35" style="display:none">
				<td width="90" align="right">门店机器编号：</td>
				<td><input name="subLocation" type="text" class="easyui-textbox" style="width: 266px" data-options="prompt:'输入门店机器编号'"/></td>
			</tr>
			<tr height="35">
				<td width="90" align="right">所属门店：</td>
				<td><input type="hidden" id="customerId_storeRegister" name="companyId"><select id="customerName_storeRegister" name="companyName"  style="width:266px;"></select></td>
			</tr>
		</table>
	</form>
</div>
<!-- begin of adding dialog -->
<div id="dlg_storeRegister-buttons"  style="text-align: center;">
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="submitstoreRegister()" style="width:180px;height: 26px">确认提交</a>

</div>

<div id="queryStore_storeRegister" class="easyui-dialog" title="选择门店" 
	data-options="modal:true,closed:true,iconCls:'icon-document'"
	style="width: 600px; padding: 2px;" >
&nbsp;门店名称：&nbsp;
<input id="storeName_storeRegister" class="easyui-textbox" style="width: 200px">
<a href="#" class="easyui-linkbutton" iconcls="icon-search" onclick="loadStore_StoreRegister()">搜索</a>
<table id="storeList_storeRegister" ></table>
<br/><br/>
</div>
<script type="text/javascript">
	$(function() {
		loadstoreRegisters();
	});

	function loadstoreRegisters() {
		<shiro:hasPermission name="system:storeRegister:edit">
			 $('#storeRegisterList').datagrid({
				onDblClickCell: function(index,field,value){
					editstoreRegisterDialog();
				}
			 });
		</shiro:hasPermission>
		$('#storeRegisterList').datagrid({
			method : 'post',
			url : 'storeRegister/findAllList',
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
			toolbar : '#storeRegisterBar',
			onBeforeLoad : function(param) {
				var myUserCompanyId = $('#index_user_companys').combobox('getValue');
				param.companyId = myUserCompanyId;
			},
			columns : [ [ {
				field : 'license',
				title : 'license',
				width : 100,
			},{
				field : 'location',
				title : '门店编号',
				width : 50,
			},{
				field : 'subLocation',
				title : '门店机器编号',
				width : 50,
			},{
				field : 'mac',
				title : 'mac地址',
				width : 100,
			},{
				field : 'customerAlias',
				title : '门店名称',
				width : 100,
				formatter : function(val, row, index) {
					return row.company.name;
				}
			}  ] ]
		});
	}

	/**
	 * function => UI => initialize the dialog for adding
	 */
	function initDialog_storeRegister() {
		$("#customerName_storeRegister").textbox({
			editable:false,
			required:true,
			icons:[{
				iconCls:'icon-search',
				handler: function(e){
                    $('#storeName_storeRegister').textbox('clear');
					loadStore_StoreRegister();
					$('#queryStore_storeRegister').window('open'); 
					
				}
			}]
		});
	}

	 function loadStore_StoreRegister(){
		 $("#storeList_storeRegister").datagrid(
					{
						url :"company/findAllList",
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
							param.name = $("#storeName_storeRegister").val();
						},
						onDblClickRow: function(index,row){
							console.info(row);
							$("#customerName_storeRegister").textbox('setValue',row.name);
							$("#customerId_storeRegister").val(row.companyid);
							$('#queryStore_storeRegister').window('close');
						},
						columns : [ [ {
							field : 'name',
							title : '门店名称',
							width : 100
						}  ] ]
					});
	 }
	/**
	 * button menu => show the dialog of insert
	 */
	function openstoreRegisterDialog() {
		initDialog_storeRegister();
		$("#storeRegisterForm").form('clear').form('disableValidation');
		$.post("storeRegister/getLicense",function(data){
	    	 $('#storeRegisterName').textbox('setValue', data);
	    });
		$('#addstoreRegisterDialog').dialog({
			closed : false,
			modal : true,
			title : "新增门店设备"
		});
	}

	/**
	 * button menu => show the dialog of update
	 */
	function editstoreRegisterDialog() {
		var row = $('#storeRegisterList').datagrid('getSelected');

		if (row) {
			$('#storeRegisterForm').form('clear').form('disableValidation');

			initDialog_storeRegister();
			$('#storeRegisterForm').form('load', {
				id : row.id,
				license : row.license,
				location:row.location,
				subLocation:row.subLocation,
				companyId :row.company.companyid,
				companyName: row.company.name
			});

			$('#addstoreRegisterDialog').dialog({
				closed : false,
				modal : true,
				title : "新增门店机器"
			});
		} else {
			$.messager.alert('信息提示', '请选择门店编号！', 'info');
		}
	}

	/**
	 * function => action => submit the client user
	 */
	function submitstoreRegister() {
		$('#storeRegisterForm').form('submit', {
			url : 'storeRegister/addstoreRegister',
			onSubmit : function(param) {
				return $(this).form('enableValidation').form('validate');
			},
			success : function(data) {
				var msg = eval('(' + data + ')');
				$.messager.alert('信息提示', msg.msg, 'info');
				if (msg.code == 1) {
					$('#addstoreRegisterDialog').window('close');
					$('#storeRegisterList').datagrid('reload');
				}
			}
		});
	}
	 /**
		 * button =>  删除账户
		 */
		function deleteStoreRegister() {
			 var row = $('#storeRegisterList').datagrid('getSelected');
			if (row) {
				$.messager.confirm('信息提示', '确定要删除该记录？', function(result) {
					if (result) {
						$.post("storeRegister/deleteStoreRegister", {
							id : row.id
						}, function(data) {
							$.messager.alert('信息提示', data.msg, 'info');
							if (data.code == 1) {
								$('#storeRegisterList').datagrid('reload');
							}
						});
					}
				});
			} else {
				$.messager.alert('信息提示', '请选择产品！', 'info');
			}
		}
</script>