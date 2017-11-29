<%@ page contentType="text/html;charset=UTF-8" language="java"%> 
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<link rel="stylesheet" type="text/css" href="static/css/datagrid_util.css">
<div class="easyui-layout" style="width:100%; " data-options="fit:true">
	<div id="p_product" data-options="region:'west',split:true" title="类别" style="width:20%; ">
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
					loadProductByCategory(row.productCategoryId);
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
    <div data-options="region:'center'" title="产品列表">
    	<div id="productbar" style="padding: 5px 0;">
			<shiro:hasPermission name="base:product:add">
				<a href="#" class="easyui-linkbutton" iconCls="icon-add-s1" onclick="productAdd()" >新增产品</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="base:product:edit">
				<a href="#" class="easyui-linkbutton" iconCls="icon-edit-s1" onclick="productEdit()" >修改产品</a>
			</shiro:hasPermission>  
		 	<shiro:hasPermission name="base:product:delete">
		 		<a href="#"	class="easyui-linkbutton" iconCls="icon-delete-s1"	onclick="productRemove()">删除产品</a>
		 	</shiro:hasPermission>  
			<shiro:hasPermission name="base:product:import">
				<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-excel'" onclick="importProduct();">产品信息导入</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="base:product:convert">
				<a href="#" class="easyui-linkbutton" iconCls="icon-edit-s1" onclick="transformProduct()" >类型转换</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="base:product:list">
				&nbsp;产品分类：&nbsp;<select id="searchProductCategoryId_product" name="productCategoryId" style="width: 150px;"></select>
				&nbsp;产品名称：&nbsp;<input id="productName_product" name="productName" class="easyui-textbox" style="width: 200px">
				<a href="#" class="easyui-linkbutton" iconcls="icon-search" onclick="loadProduct(1)">搜索</a>
			</shiro:hasPermission> 
		
		</div>
		<table id="productList" style="width: auto; height: 350px">
		</table>
		<!-- Begin of easyui-dialog -->
			<div id="productDialog" class="easyui-dialog" style="width: 520px;padding: 1px;"
				data-options="closed:true,
							iconCls:'icon-document'" >
				<form id="productForm" method="post" enctype="multipart/form-data">
						<input type="hidden" name="productId" />
						<input type="hidden" name="productType" value="1" />
						<br />
						<div style="float:left;width:270px;">
							<!-- <div class="ftitle"  style="width: 350px;margin: 0px 20px 20px;" >产品信息</div> -->
							<div class="fitem">
							          <label class="fitemlabel width_100">产品名称：</label>
							          <input type="text" name="productName" class="easyui-textbox wu-text"   style="width: 160px;"
							          		data-options="prompt:'产品名称',required:true,validType:['length[0,200]']" />&nbsp;
							</div>
							<div class="fitem">
							          <label class="fitemlabel width_100">产品简称：</label>
							          <input type="text" name="productShortName" class="easyui-textbox wu-text"  style="width: 160px;"
							          		data-options="prompt:'产品简称',validType:['length[0,200]']" />&nbsp;
							</div>
							<div class="fitem">
							          <label class="fitemlabel width_100">超市商品码：</label>
				            		  <input type="text" name="marketCode" class="easyui-textbox wu-text"   style="width: 160px;"
				            		  		data-options="prompt:'超市商品码',validType:['length[0,400]']"/>
							</div>
							<div class="fitem">
							          <label class="fitemlabel width_100">产品分类：</label>
								      <select id="productCategoryId" name="productCategoryId" style="width: 160px;"> </select> 
							</div>				
							<div class="fitem">
							          <label class="fitemlabel width_100">产品编码：</label>
							          <input type="text" name="productCode" class="easyui-textbox wu-text"  style="width: 160px;"
							          		data-options="prompt:'产品编码',required:true,validType:['length[0,8]']" />&nbsp;
							</div>
							<div class="fitem">
									  <label class="fitemlabel width_100">状态：</label>
									  <input type="hidden" name="status" id="status_product_hidden" value='0' >
									  <input class="easyui-switchbutton" id="status_product" style="width: 160px;">
							</div>		
						</div>
						<div class="fitem"  style="float:left;width:200px;margin-left: 10px">
							<div class="easyui-tabs" id="productImgTabs" style="width:175px;height:205px;" data-options="plain:true,narrow:true,pill:false,justified:false">
							    <div title="产品图片" style="padding:5px;"  >
							       	<img id="product_img" width="160px" height="160px"  border="1"  style="border-color:#ccc">
							    </div>
							    <div title="宣传图片" style="padding:5px;" >
							       <img id="product_publicityImage" width="160px" height="160px"  border="1"  style="border-color:#ccc">
							    </div>
							</div>
						</div>
						<div style="clear:both" ></div><!-- html注释：清除float产生浮动 -->
					<div class="fitem">
						<label class="fitemlabel width_100">产地:</label> <input
						class="easyui-combobox" name="madein" style="width: 160px;"
						data-options="prompt:'产地',valueField:'id',textField:'catgName',url:'productPurchaseInstockOrder/areaList'">&nbsp;
					</div>
				    <div class="fitem">
					          <label class="fitemlabel width_100">产品图片：</label>
					          <input id="productImgFilebox"  name="productImgfile" class="wu-text"  style="width: 320px;"/>
							  <input type="hidden" name="productImageUrl">
							  <a href="#" class="easyui-linkbutton" style="height: 22px" 
							  		onclick="javascript: $('#productImgFilebox').filebox('setValue', '');$('#product_img').attr('src','');">清空</a>
					</div>
					<div class="fitem">
					          <label class="fitemlabel width_100">宣传图片：</label>
					          <input id="publicityImageFilebox"  name="publicityImagefile" class="wu-text"  style="width: 320px;"/>
							  <input type="hidden" name="publicityImageUrl">
							  <a href="#" class="easyui-linkbutton" style="height: 22px" 
							  		onclick="javascript: $('#publicityImageFilebox').filebox('setValue', '');$('#product_publicityImage').attr('src','');">清空</a>
					</div>			
					<div class="fitem">
					          <label class="fitemlabel width_100">产品简介：</label>
						      <input type="text" name="productDesc" class="easyui-textbox wu-text" style="width: 350px;height: 62px" 
						      		data-options="prompt:'产品简介',multiline:true,validType:['length[0,400]']" />
					</div> 
			
				</form>
			</div> 
			<!-- End of easyui-dialog -->
			<div id="importProductDialog" class="easyui-dialog"  style="width:400px;height:200px;padding: 10px"
				data-options="modal:true,closed:true">
			   		      		
			</div>
			<div id="importProductDiv" style="width: 400px; padding: 10px;">
				<form id="importProductForm" method="post"
					action="product/importproduct"
					enctype="multipart/form-data">
					<input class="easyui-filebox" style="width: 300px;"
						name="uploadImportFile"
						data-options="prompt:'产品信息导入文件',required:true,buttonText: '选择文件',buttonAlign: 'right'" />
					<a href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-ok'"
						onclick="importProductSubmit();">提交</a>
						<a href="#" target="_blank" id="importProductSubmitTempl" class="easyui-linkbutton"
						data-options="iconCls:'icon-download-s1'">模板下载</a>
				</form>
			</div>
    </div>
</div> 
<script type="text/javascript">
var initFlag_product=0;

$(function() {
	$("#searchProductCategoryId_product").combobox({
		url:'product_category/listForSelecting',
		method:'post',
		valueField:'productCategoryId',
		textField:'productCategoryName',
		onBeforeLoad : function(param) {
			param.companyId = $("#index_user_companys").combobox('getValue'); 
		},
		filter: function(q, row){
			var opts = $(this).combobox('options');
			return row[opts.textField].indexOf(q) == 0;
		},
		onLoadSuccess: function() {
			$('#searchProductCategoryId_product').combobox('setValue', null);
		}
	}); 
	
	loadProduct(0);
	
	$.get('logistics/getdownloadurl', function(data) {
		data = eval('(' + data + ')');
		if (data.url) {
			$('#importProductSubmitTempl').prop('href',
					data.url + 'productTemplate.xls');
		}
	});
	
    $('#status_product').switchbutton({
        checked: true,
        onText:'启用',
        offText:'禁用',
        onChange: function(checked){
            var statusvar = '1';
            if(checked){
            	statusvar='0';
            }
            $('#status_product_hidden').attr('value',statusvar);
        }
    })
	
});



	//数据导入
	function importProduct() {
		$('#importProductDiv').window({
			modal : true,
			closed : true,
			iconCls : 'icon-excel',
			title : '产品信息导入'
		});
		$('#importProductDiv').window('open');
	}

	//数据导入
	function importProductSubmit() {
		$('#importProductForm').form(
				'submit',
				{
					url : 'product/importproduct',
					type : 'post',
					onSubmit : function(param) {
						param.companyId = $('#index_user_companys').combobox(
								'getValue');
						param.productType = '1';
						return $(this).form('enableValidation')
								.form('validate');
					},
					success : function(data) {
						var msg = eval('(' + data + ')');
						$('#importProductDialog').dialog({
							closed : false,
							modal : true,
							title : "提示信息",
							content:msg.msg
						});
						if (msg.code == '1') {
							$('#importProductDiv').window('close');
							$('#productList').datagrid('reload');
						}
					}
				});
	}

	/**
	 * button => 打开添加窗口
	 */

	function productAdd() {

		if (initFlag_product == 0) {
			initDialogComponent();
			initFlag_product = 1;
		}

		$('#productForm').form('clear').form('disableValidation');
		$('#productForm').form('load', {
			status: '0',
			productType : '1'
		});
		$('#productImgFilebox').filebox('setValue', '');
		$('#product_img').attr('src','static/img/defaultImg.jpg');
		$('#publicityImageFilebox').filebox('setValue', '');
		$('#product_publicityImage').attr('src','static/img/defaultImg.jpg');
		
		$("#status_product").switchbutton({checked: true});
		$('#status_product_hidden').attr('value','0');
		$('#productDialog').dialog({
			closed : false,
			modal : true,
			title : "新增产品",
			buttons : [ {
				text : '确定',
				iconCls : 'icon-ok',
				handler : submitProduct
			}, {
				text : '取消',
				iconCls : 'icon-cancel',
				handler : function() {
					$('#productDialog').dialog('close');
				}
			} ]
		});
	}
	 
	
	function transformProduct() {
		var row = $('#productList').datagrid('getSelected');
		if (row) {
			$.messager.confirm('Confirm','类型转换?',function(r){
			    if (r){
			    	$.ajax({
						url:'product/transform',
						type:'POST',
						data:{productId:row.productId,productType : '0'},
						dataType:'json',
						success : function(msg){
							$.messager.alert('信息提示', msg.msg, 'info');
							if (msg.code == '1') {
								$('#productList').datagrid('reload');
							}
						}
					});
			    }
			});
		} else {
			$.messager.alert('信息提示', '请选择产品！', 'info');
		}
	}

	/**
	 * button => 打开修改窗口
	 */
	function productEdit() {
		var row = $('#productList').datagrid('getSelected');
		if (row) {
			$('#productForm').form('clear');

			if (initFlag_product == 0) {
				initDialogComponent();
				initFlag_product = 1;
			}

			$('#productForm').form('load', {
				productId : row.productId,
				marketCode : row.marketCode,
				productName : row.productName,
				productCode : row.productCode,
				madein : row.madein,
				productShortName : row.productShortName,
				productDesc : row.productDesc,
				productType : row.productType,
				productCategoryId : row.productCategoryId,
				productImageUrl : row.productImageUrl,
				publicityImageUrl : row.publicityImageUrl,
				status : row.status
			});
			
			
			if(row.status==undefined || row.status=="" || row.status=="1"){
				$("#status_product").switchbutton({checked: false});
				$('#status_product_hidden').attr('value','1');
			}else{
				$("#status_product").switchbutton({checked: true});
				$('#status_product_hidden').attr('value','0');
			}
			
			
			$("#productImgFilebox").filebox("setText", row.productImageUrl);
			if(row.productImageUrl!=null || row.productImageUrl!=""){
				$("#product_img").attr('src', row.productImageUrl);
			}else{
				$('#product_img').attr('src','static/img/defaultImg.jpg');
			}
			
			
			$("#publicityImageFilebox").filebox("setText", row.publicityImageUrl);
			if(row.publicityImageUrl!=null || row.publicityImageUrl!=""){
				$("#product_publicityImage").attr('src', row.publicityImageUrl);
			}else{
				$('#product_publicityImage').attr('src','static/img/defaultImg.jpg');
			}

			
			
			$('#productDialog').dialog({
				closed : false,
				modal : true,
				title : "修改产品",
				buttons : [ {
					text : '确定',
					iconCls : 'icon-ok',
					handler : submitProduct
				}, {
					text : '取消',
					iconCls : 'icon-cancel',
					handler : function() {
						$('#productDialog').dialog('close');
					}
				} ]
			});
		} else {
			$.messager.alert('信息提示', '请选择产品！', 'info');
		}
	}

	/**
	 * action => 保存产品
	 */
	function submitProduct() {
		$('#productForm').form(
				'submit',
				{
					url : 'product/addProduct',
					onSubmit : function(param) {
						param.companyId = $("#index_user_companys").combobox("getValue");
// 						if(!$("#productImgFilebox").filebox('imgVerify',{})){
// 							return false;
// 						}
						return $(this).form('enableValidation').form('validate');
					},
					success : function(data) {
						var msg = eval('(' + data + ')');
						$.messager.alert('信息提示', msg.msg, 'info');
						if (msg.code == 1) {
							$('#productDialog').dialog('close');
							$('#productList').datagrid('reload');
						}
					}
				});
	}

	/**
	 * button =>  删除产品
	 */
	function productRemove() {
		var row = $('#productList').datagrid('getSelected');
		if (row) {
			$.messager.confirm('信息提示', '确定要删除该记录？', function(result) {
				if (result) {
					$.post("product/delProduct", {
						productId : row.productId
					}, function(data) {
						var msg = eval('(' + data + ')');
						$.messager.alert('信息提示', msg.msg, 'info');
						if (msg.code == 1) {
							$('#productList').datagrid('reload');
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
	function initDialogComponent() {
		$("#productCategoryId").combobox({
					url : 'product_category/list',
					method : 'post',
					valueField : 'productCategoryId',
					textField : 'productCategoryName',
					required : true,
					editable : false,
					onBeforeLoad : function(param) {
						param.companyId = $("#index_user_companys").combobox(
								'getValue');
					}
		});
		
		$("#productImgFilebox").filebox({
			prompt : '选择产品图片',
			buttonText:'选择产品图片',
			onChange : function() { 
				if($(this).filebox('getValue')!=""){
					$(this).filebox('uploadPreview',{ imgId: "product_img" });
				}
			},
			onClickButton: function() { 
					$('#productImgTabs').tabs('select',0);
			},
			buttonText : '选择产品图片'
		});
		
		
		$("#publicityImageFilebox").filebox({
			prompt : '选择宣传图片',
			buttonText:'选择宣传图片',
			onChange : function() { 
				if($(this).filebox('getValue')!=""){
					$(this).filebox('uploadPreview',{ imgId: "product_publicityImage" });
				}
			},
			onClickButton: function() { 
				$('#productImgTabs').tabs('select',1);
			},
			buttonText : '选择宣传图片'
		});
		
	}

	/**
	 * load the products; 
	 */
	function loadProduct(actionType) {
		var queryUrl_product = '';
		// actionType:1 => 查询
		if (actionType === 1) {
			queryUrl_product = 'product/findAllQueryList?productType=1';
		} else {
			queryUrl_product = 'product/findAllList?productType=1';
		}
		<shiro:hasPermission name="base:product:edit">
		 $('#productList').datagrid({
				onDblClickCell: function(index,field,value){
					productEdit()
				}
		 });
		</shiro:hasPermission>

		$('#productList').datagrid(
				{
					//url : 'product/findAllList?productType=1',
					url : queryUrl_product,
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
					toolbar : '#productbar',
					onBeforeLoad : function(param) {
						param.productCategoryId = $(
								'#searchProductCategoryId_product').combobox(
								'getValue');
						param.companyId = $("#index_user_companys").combobox(
								"getValue");
						param.productName = $("#productName_product").val();
					},
					columns : [ [ {
						field : 'productId',
						title : '产品ID',
						hidden : true
					}, {
						field : 'productName',
						title : '产品名称',
						width : 100,
						sortable : true
					}, {
						field : 'productCode',
						title : '产品代码',
						width : 50,
						sortable : true
					}, {
						field : 'productShortName',
						title : '产品简称',
						width : 100
					}, {
						field : 'productCategoryId',
						title : '类别',
						hidden : true
					}, {
						field : 'productCategoryName',
						title : '产品分类',
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
	
	 function loadProductByCategory(productCategoryId) {
		 //console.info(productCategoryId);
		 $('#productList').datagrid({
				queryParams: {
					'category.productCategoryId': productCategoryId,
					companyId: $("#index_user_companys").combobox("getValue")
				}
			}); 
	} 
</script>
