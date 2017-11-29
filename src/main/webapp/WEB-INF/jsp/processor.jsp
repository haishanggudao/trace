<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<link rel="stylesheet" type="text/css" href="static/css/datagrid_util.css">
	<div id="processorbar" style="padding: 5px 0;">
		<shiro:hasPermission name="base:processor:add">
			<a href="#" class="easyui-linkbutton" iconCls="icon-add-s1" onclick="processorAdd()">新增</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="base:processor:edit">
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit-s1" onclick="processorEdit()">修改</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="base:processor:list">
				&nbsp;姓名：&nbsp;
				<input id="searchProcessorName" class="easyui-textbox" style="width: 160px;" />
				<a href="#" class="easyui-linkbutton" iconcls="icon-search" onclick="loadprocessor()">搜索</a>
		</shiro:hasPermission>
	</div>
	<table id="processorList" style="width: auto; height: 350px"></table>
	<div id="processorDialog" class="easyui-dialog" style="width: 500px; padding: 10px;"
			data-options="closed:true,iconCls:'icon-document'">
			<form id="processorForm" method="post" enctype="multipart/form-data">
					<input type="hidden" name="id" />
					<div style="float:left;width:370px;">
						<div class="fitem">
						          <label class="fitemlabel width_100">姓名：</label>
						          <input type="text" name="name" class="easyui-textbox wu-text"   style="width: 160px;"
						          		data-options="required:true,validType:['length[0,200]']" />
						</div>		
						<div class="fitem">
					          <label class="fitemlabel width_100">健康证：</label>
					          <input id="processorImgFilebox"  name="healthCardImgfile" class="wu-text"  style="width: 160px;"/>
							  <input type="hidden" name="healthCard" style="width: 160px;">
							  <a href="#" class="easyui-linkbutton" style="height: 22px" 
							  onclick="javascript: $('#processorImgFilebox').filebox('setValue', '');$('#processor_img').attr('src','');">清空</a>
						</div>		
					</div>
					
					<div class="fitem" style="width:160px;margin-left: 100px">
					          <img id="processor_img" width="160px" height="160px"  border="1"  style="border-color:#ccc" >
					</div>
					<div style="clear:both" ></div><!-- html注释：清除float产生浮动 --> 
			</form>
	</div>
<script type="text/javascript">
	$(function() {
		loadprocessor();
	});


	/**
	 * button => 打开添加窗口
	 */

	function processorAdd() {
		 initDialogComponent_processor();
		$('#processorImgFilebox').filebox('setValue', '');
		$('#processor_img').attr('src','static/img/defaultImg.jpg');
		$('#processorForm').form('clear').form('disableValidation');
		$("#status_processor").switchbutton({checked: true});
		$('#status_processor_hidden').attr('value','0');
		
		$('#processorDialog').dialog({
			closed : false,
			modal : true,
			title : "新增加工者信息",
			buttons : [ {
				text : '确定',
				iconCls : 'icon-ok',
				handler : submitprocessor
			}, {
				text : '取消',
				iconCls : 'icon-cancel',
				handler : function() {
					$('#processorDialog').dialog('close');
				}
			} ]
		});
	}
	 
	/**
	 * button => 打开修改窗口
	 */
	function processorEdit() {
		var row = $('#processorList').datagrid('getSelected');
		if (row) {
			$('#processorForm').form('clear');
			initDialogComponent_processor();
			$('#processorForm').form('load', {
				id:row.id,
				name : row.name,
				healthCard : row.healthCard,
			});
			
			
			
			$("#processorImgFilebox").filebox("setText", row.healthCard);
			if(row.healthCard!=null || row.healthCard!=""){
				$("#processor_img").attr('src', row.healthCard);
			}else{
				$('#processor_img').attr('src','static/img/defaultImg.jpg');
			}
			$('#processorDialog').dialog({
				closed : false,
				modal : true,
				title : "修改加工者信息",
				buttons : [ {
					text : '确定',
					iconCls : 'icon-ok',
					handler : submitprocessor
				}, {
					text : '取消',
					iconCls : 'icon-cancel',
					handler : function() {
						$('#processorDialog').dialog('close');
					}
				} ]
			});
		} else {
			$.messager.alert('信息提示', '请选择原料！', 'info');
		}
	}

	/**
	 * action => 保存产品
	 */
	function submitprocessor() {
		$('#processorForm').form(
				'submit',
				{
					url : 'processor/add',
					onSubmit : function(param) {
						param.companyId = $("#index_user_companys").combobox("getValue");
						return $(this).form('enableValidation').form('validate');
					},
					success : function(data) {
						var msg = eval('(' + data + ')');
						$.messager.alert('信息提示', msg.msg, 'info');
						if (msg.code == 1) {
							$('#processorDialog').dialog('close');
							$('#processorList').datagrid('reload');
						}
					}
				});
	}

	/**
	 * button =>  删除产品
	 */
	function processorRemove() {
		var row = $('#processorList').datagrid('getSelected');
		if (row) {
			$.messager.confirm('信息提示', '确定要删除该记录？', function(result) {
				if (result) {
					$.post("product/delProduct", {
						productId : row.productId
					}, function(data) {
						var msg = eval('(' + data + ')');
						$.messager.alert('信息提示', msg.msg, 'info');
						if (msg.code == 1) {
							$('#processorList').datagrid('reload');
						}
					});
				}
			});
		} else {
			$.messager.alert('信息提示', '请选择产品！', 'info');
		}
	}

	/**
	 * initialize the component of the dialog
	 */
	function initDialogComponent_processor() {
		$("#processorImgFilebox").filebox({
			prompt : '选择健康证图片',
			buttonText:'选择图片',
			onChange : function() { 
				if($(this).filebox('getValue')!=""){
					$(this).filebox('uploadPreview',{ imgId: "processor_img" });
				}
			},
			buttonText : '选择图片'
		});
	}

	/**
	 * load the processors; 
	 */
	function loadprocessor() {
		$('#processorList').datagrid(
				{
					url : 'processor/findAllList',
					method : 'post',
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
					toolbar : '#processorbar',
					onBeforeLoad : function(param) {
						param.name = $("#searchProcessorName").val();
						param.companyId = $("#index_user_companys").combobox("getValue");
					},
					columns : [ [ {
						field : 'name',
						title : '加工者姓名',
						width : 150,
						sortable : true
					}, {
						field : 'healthCard',
						title : '健康证图片',
						width : 200
					}] ]

				});
	}
	 
</script>
