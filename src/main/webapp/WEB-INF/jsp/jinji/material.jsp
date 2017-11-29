<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<link rel="stylesheet" type="text/css" href="static/css/datagrid_util.css">
<!-- layout  -->
<div class="easyui-layout" style="width:100%; " data-options="fit:true">
	<div id="p_productMaterial_jinji" data-options="region:'west',split:true" title="类别" style="width:20%; ">
		<table class="easyui-treegrid" 
            data-options="
                url: 'product_category/list',
                method: 'get',
                rownumbers: true,
                idField: 'productCategoryId',
                treeField: 'productCategoryName',
                fit:true,
                onBeforeLoad : function(row, param ) {
					param.companyId = $('#index_user_companys').combobox('getValue'); 
					param.level = 1;
				},
				onClickRow : function(row) {
					// console.info(row);
					loadMaterial_jinji(row.productCategoryId);
				}
            ">
	        <thead>
	            <tr>
	                <th data-options="field:'productCategoryName'" width="80%"></th>
	                <!-- <th data-options="field:'level'" width="20%" align="right">level</th> --> 
	            </tr>
	        </thead>
    	</table>
	</div>
	<div data-options="region:'center'" title="原料列表-金机餐饮">
		<div id="materialbar_jinji" style="padding: 5px 0;">
		<shiro:hasPermission name="base:material:add">
			<a href="#" class="easyui-linkbutton" iconCls="icon-add-s1" onclick="materialAdd_jinji()">新增原料</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="base:material:edit">
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit-s1" onclick="materialEdit_jinji()">修改原料</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="base:material:delete">
			<a href="#" class="easyui-linkbutton" iconCls="icon-delete-s1" onclick="materialRemove_jinji()">删除原料</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="base:material:import">
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-excel'" onclick="importMaterial();">原料信息导入</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="base:material:convert">
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit-s1" onclick="transformMaterial()">原料类型转换</a>
		</shiro:hasPermission> 
		</div>
		<table id="materialList" style="width: auto; height: 350px"></table>
		<!-- Begin of easyui-dialog -->
		<div id="materialDialog" class="easyui-dialog" style="width: 500px; padding: 10px;"
			data-options="closed:true,iconCls:'icon-document'">
			<form id="materialForm" method="post" enctype="multipart/form-data">
					<input type="hidden" name="productId" />
					<input type="hidden" name="productType" value="2" />
					<br/>
					<div style="float:left;width:270px;">
						<!-- <div class="ftitle"  style="width: 350px;margin: 0px 20px 20px;" >产品信息</div> -->
						<div class="fitem">
						          <label class="fitemlabel width_100">原料名称：</label>
						          <input type="text" name="productName" class="easyui-textbox wu-text"   style="width: 160px;"
						          		data-options="required:true,validType:['length[0,200]']" />
						</div>				
						<div class="fitem">
						          <label class="fitemlabel width_100">原料编码：</label>
						          <input type="text" name="productCode" class="easyui-textbox wu-text" style="width: 160px;"
						          		data-options="required:true,validType:['length[0,8]']" />&nbsp;
						</div>
						<div class="fitem">
						          <label class="fitemlabel width_100">原料简称：</label>
						          <input type="text" name="productShortName" class="easyui-textbox wu-text" style="width: 160px;"
						          		data-options="validType:['length[0,200]']" />
						</div>				
						<div class="fitem">
						          <label class="fitemlabel width_100">原料分类：</label>
							      <select id="materialCategoryId" name="productCategoryId" style="width: 160px;"> </select>
						</div>	
						<div class="fitem">
							<label class="fitemlabel width_100">产地:</label> 
							<input type="hidden" id="hidden_material_madein" name="madein">
							<select class="easyui-textbox" id="material_madein" name="text_material_madein" style="width: 160px;"
						    	data-options="prompt:'原料产地',required:true,valueField:'id',textField:'catgName',url:'productPurchaseInstockOrder/areaList'">
						  	</select>&nbsp;
						</div>			
						<div class="fitem">
								  <label class="fitemlabel width_100">状态：</label>
								  <input type="hidden" name="status" id="status_material_hidden" value='0' >
								  <input class="easyui-switchbutton" id="status_material" style="width: 160px;">
		
						</div>
					</div>
					<div class="fitem" style="float:left;width:160px;margin-left: 10px">
					          <img id="material_img" width="160px" height="160px"  border="1"  style="border-color:#ccc" >
					</div>
					<div style="clear:both" ></div><!-- html注释：清除float产生浮动 --> 
					<div class="fitem">
					          <label class="fitemlabel width_100">原料图片：</label>
					          <input id="materialImgFilebox"  name="productImgfile" class="wu-text"  style="width: 300px;"/>
							  <input type="hidden" name="productImageUrl">
							  <a href="#" class="easyui-linkbutton" style="height: 22px" 
							  onclick="javascript: $('#materialImgFilebox').filebox('setValue', '');$('#material_img').attr('src','');">清空</a>
					</div>
					<div class="fitem">
					          <label class="fitemlabel width_100">原料简介：</label>
					          <input type="text" name="productDesc" class="easyui-textbox wu-text" style="width: 330px; height: 62px" 
									data-options="multiline:true, validType:['length[0,400]']" />
					</div>
					
			</form>
		</div>
		<div id="importMaterialDiv" style="width: 400px; padding: 10px;">
			<form id="importMaterialForm" method="post"
				action="product/importproduct" enctype="multipart/form-data">
				<input class="easyui-filebox" style="width: 300px;"
					name="uploadImportFile"
					data-options="prompt:'原料信息导入文件',required:true,buttonText: '选择文件',buttonAlign: 'right'" />
				<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
					onclick="importMaterialSubmit();">提交</a> <a href="#" target="_blank"
					id="importMaterialSubmitTempl" class="easyui-linkbutton"
					data-options="iconCls:'icon-download-s1'">模板下载</a>
			</form>
		</div>
	</div>
</div>  

<div id="queryCatgName_areaInfo" class="easyui-dialog" title="选择原料产地" 
	data-options="modal:true,closed:true,iconCls:'icon-document'"
	style="width: 600px; padding: 2px;" >
	&nbsp;商品产地：&nbsp;
	<input id="catgName_product_areaInfo" name="madein" class="easyui-textbox" style="width: 200px">
	<a href="#" class="easyui-linkbutton" iconcls="icon-search" onclick="loadCatgName_areaInfo(1)">搜索</a>
	<table id="catgNameList_areaInfo" ></table>
	<br/><br/>
</div>

<script type="text/javascript">
	var initFlag_material = 0;

	$(function() { 

		$("#material_madein").textbox({
			editable:false,
			required:true,
			icons:[{
				iconCls:'icon-search',
				handler: function(e){
	                $('#catgName_product_areaInfo').textbox('clear');
	                
					$('#queryCatgName_areaInfo').window('open'); 
					
					loadCatgName_areaInfo();
				}
			}]
		});
		
		// loading...
		loadMaterial_jinji();

		$.get('logistics/getdownloadurl', function(data) {
			data = eval('(' + data + ')');
			if (data.url) {
				$('#importMaterialSubmitTempl').prop('href',
						data.url + '\ylxx.xls');
			}
		});
		
        $('#status_material').switchbutton({
        	checked: true,
            onText:'启用',
            offText:'禁用',
            onChange: function(checked){
                var statusvar = '1';
                if(checked){
                	statusvar='0';
                }
                $('#status_material_hidden').attr('value',statusvar);
            }
        }) 

	}); 
	
	function transformMaterial() {
		var row = $('#materialList').datagrid('getSelected');
		if (row) {
			$.messager.confirm('信息提示', '原料类型转换？', function(result) {
				if (result) {
					$.ajax({
						url : 'product/transform',
						type : 'POST',
						data : {
							productId : row.productId,
							productType : '0'
						},
						dataType : 'json',
						success : function(msg) {
							$.messager.alert('信息提示', msg.msg, 'info');
							if (msg.code == '1') {
								$('#materialList').datagrid('reload');
							}
						}
					});
				}
			});
		} else {
			$.messager.alert('信息提示', '请选择产品！', 'info');
		}
	}

	//数据导入
	function importMaterial() {
		$('#importMaterialDiv').window({
			modal : true,
			closed : true,
			iconCls : 'icon-excel',
			title : '原料信息导入'
		});
		$('#importMaterialDiv').window('open');
	}

	//数据导入
	function importMaterialSubmit() {
		$('#importMaterialForm').form(
				'submit',
				{
					url : 'product/importproduct',
					type : 'post',
					onSubmit : function(param) {
						param.companyId = $('#index_user_companys').combobox(
								'getValue');
						param.productType = '2';
						return $(this).form('enableValidation')
								.form('validate');
					},
					success : function(data) {
						var msg = eval('(' + data + ')');
						$.messager.alert('信息提示', msg.msg, 'info');
						if (msg.code == '1') {
							$('#importMaterialDiv').window('close');
							$('#materialList').datagrid('reload');
						}
					}
				});
	}

	/**
	 * button => 打开添加窗口
	 */

	function materialAdd_jinji() {

		if (initFlag_material == 0) {
			initDialogComponent_meterial();
			// 			initFlag_material = 1;
		}
		$('#materialImgFilebox').filebox('setValue', '');
		$('#material_img').attr('src','static/img/defaultImg.jpg');
		$('#materialForm').form('clear').form('disableValidation');
		$('#materialForm').form('load', {
			status: '0',
			productType : '2'
		});
		$("#status_material").switchbutton({checked: true});
		$('#status_material_hidden').attr('value','0');
		
		$('#materialDialog').dialog({
			closed : false,
			modal : true,
			title : "新增原料",
			buttons : [ {
				text : '确定',
				iconCls : 'icon-ok',
				handler : submitMaterial_jinji
			}, {
				text : '取消',
				iconCls : 'icon-cancel',
				handler : function() {
					$('#materialDialog').dialog('close');
				}
			} ]
		});
	}
	 
	/**
	 * button => 打开修改窗口
	 */
	function materialEdit_jinji() {
		var row = $('#materialList').datagrid('getSelected');
		console.info(row);
		if (row) {
			$('#materialForm').form('clear');

			if (initFlag_material == 0) {
				initDialogComponent_meterial();
				initFlag_material = 1;
			}
			$('#materialForm').form('load', {
				productId : row.productId,
				productName : row.productName,
				productCode : row.productCode,
				productShortName : row.productShortName,
				productDesc : row.productDesc,
				productType : row.productType,
				productCategoryId : row.productCategoryId,
				productImageUrl : row.productImageUrl,
				status : row.status,
				productDetailMapId : row.productDetailMapId,
				madein : row.madein,
				text_material_madein: row.madeinStr
			});
			
			

			if(row.status==undefined || row.status=="" || row.status=="1"){
				$("#status_material").switchbutton({checked: false});
				$('#status_material_hidden').attr('value','1');
			}else{
				$("#status_material").switchbutton({checked: true});
				$('#status_material_hidden').attr('value','0');
			}
			
			
			
			$("#materialImgFilebox").filebox("setText", row.productImageUrl);
			if(row.productImageUrl!=null || row.productImageUrl!=""){
				$("#material_img").attr('src', row.productImageUrl);
			}else{
				$('#material_img').attr('src','static/img/defaultImg.jpg');
			}
			$('#materialDialog').dialog({
				closed : false,
				modal : true,
				title : "修改原料",
				buttons : [ {
					text : '确定',
					iconCls : 'icon-ok',
					handler : submitMaterial_jinji
				}, {
					text : '取消',
					iconCls : 'icon-cancel',
					handler : function() {
						$('#materialDialog').dialog('close');
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
	function submitMaterial_jinji() {
		$('#materialForm').form(
				'submit',
				{
					url : 'material_jinji/addMaterial',
					onSubmit : function(param) { 
						param.companyId = $("#index_user_companys").combobox("getValue"); 
						
						return $(this).form('enableValidation').form('validate');
					},
					success : function(data) {
						var msg = eval('(' + data + ')');
						$.messager.alert('信息提示', msg.msg, 'info');
						if (msg.code == 1) {
							$('#materialDialog').dialog('close');
							$('#materialList').datagrid('reload');
						}
					}
				});
	}

	/**
	 * button =>  删除产品
	 */
	function materialRemove_jinji() {
		var row = $('#materialList').datagrid('getSelected');
		if (row) {
			$.messager.confirm('信息提示', '确定要删除该记录？', function(result) {
				if (result) {
					$.post("material_jinji/delMaterial", {
						productId : row.productId
					}, function(data) {
						var msg = eval('(' + data + ')');
						$.messager.alert('信息提示', msg.msg, 'info');
						if (msg.code == 1) {
							$('#materialList').datagrid('reload');
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
	function initDialogComponent_meterial() {
		$("#materialCategoryId").combobox(
				{
					url : 'product_category/list',
					method : 'post',
					valueField : 'productCategoryId',
					textField : 'productCategoryName',
					required : true,
					editable : false,
					onBeforeLoad : function(param) {
						param.companyId = $("#index_user_companys").combobox('getValue');
					}
				});
		$("#materialImgFilebox").filebox({
			prompt : '选择产品图片',
			buttonText:'选择图片',
			onChange : function() { 
				if($(this).filebox('getValue')!=""){
					$(this).filebox('uploadPreview',{ imgId: "material_img" });
				}
			},
			buttonText : '选择图片'
		});
	}

	/**
	 * load the materials; 
	 */
	function loadMaterial_jinji(productCategoryId) {
		<shiro:hasPermission name="base:material:edit">
			 $('#materialList').datagrid({
					onDblClickCell: function(index,field,value){
						materialEdit_jinji()
					}
			 });
		</shiro:hasPermission>
		$('#materialList').datagrid(
				{
					url : 'product/findAllList?productType=2',
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
					toolbar : '#materialbar_jinji',
					onBeforeLoad : function(param) {
						if(productCategoryId){
							param.productCategoryId = productCategoryId;
						} 
						param.companyId = $("#index_user_companys").combobox(
								"getValue");
					},
					columns : [ [ {
						field : 'productId',
						title : '原料ID',
						hidden : true
					}, {
						field : 'productName',
						title : '原料名称',
						width : 100,
						sortable : true
					}, {
						field : 'productCode',
						title : '原料代码',
						width : 50,
						sortable : true
					}, {
						field : 'productShortName',
						title : '原料简称',
						width : 100
					}, {
						field : 'productCategoryId',
						title : '类别',
						hidden : true
					}, {
						field : 'productCategoryName',
						title : '原料分类',
						width : 100,
						sortable : true,
						formatter : function(val, row, index) {
							return row.category.productCategoryName;
						}
					}, {
						field : 'productImageUrl',
						title : '图片URL',
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
					} ] ]

				});
	}
	
	 function loadCatgName_areaInfo(actionType) {
			var queryUrl_product = ''; 
			
			// actionType:1 => 查询
			if (actionType === 1) {
				queryUrl_product = 'areaInfo/areaListWithQuery';
			} else {
				queryUrl_product = 'areaInfo/newAreaListUseByCatgName';
			}
			
			$('#catgNameList_areaInfo').datagrid(
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
							param.catgName = $("#catgName_product_areaInfo").val();
						},
						onDblClickRow: function(index,row){
							// console.info(' index is '+ index ); 
							console.info(row); 
							$('#material_madein').textbox('setValue', row.catgName);
							$('#hidden_material_madein').val(row.id);
							
							$('#queryCatgName_areaInfo').window('close');
							
							//$("#catgName").combobox("clear");
							// var url = 'productPurchaseInstockOrder/areaList';
							// $("#madein").combobox('reload',url);
						},
						columns : [ [ {
							field : 'id',
							title : '产地ID',
							hidden : true
						},{
							field : 'catgName',
							title : '产地',
							width : 100,
							sortable : true 
						}  ] ]
					});
		}
</script>
