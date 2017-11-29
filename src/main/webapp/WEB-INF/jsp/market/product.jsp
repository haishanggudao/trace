<%@ page contentType="text/html;charset=UTF-8" language="java"%> 
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<link rel="stylesheet" type="text/css" href="static/css/datagrid_util.css">


<style type="text/css">


.productImg ul{
    display: block;
    list-style-type: disc;
    -webkit-margin-before: 1em;
    -webkit-margin-after: 1em;
    -webkit-margin-start: 0px;
    -webkit-margin-end: 0px;
    -webkit-padding-start: 40px;
    margin-top: 0;
    margin-bottom: 10px;
}
.productImg ul.items li img {
    width: 160px;
    height: 160px;
}
.productImg ul.items li {
    position: relative;
    padding: 0px;
    width: 160px;
    float:left;
    margin: 0px 15px 30px 15px;
    border: 1px solid #e8e8e8;
    cursor: pointer;
    overflow: hidden;
    display: list-item;
    text-align: -webkit-match-parent;    
}
.list-inline {
    padding-left: 0;
    margin-left: -5px;
    list-style: none;
}
.productImg ul.items li p {
    font-family: Arial,"宋体";
    font-size: 12px;
    text-align: center;
}
</style>



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
				<a href="#" class="easyui-linkbutton" iconCls="icon-edit-s1" onclick="productEdit()" >编辑产品</a>
			</shiro:hasPermission>  
		 	<shiro:hasPermission name="base:product:delete">
		 		<a href="#"	class="easyui-linkbutton" iconCls="icon-delete-s1"	onclick="productRemove()">删除产品</a>
		 	</shiro:hasPermission>  
			<shiro:hasPermission name="base:product:list">
				&nbsp;产品分类：&nbsp;<select id="searchProductCategoryId_product" name="productCategoryId" style="width: 150px;"></select>
				&nbsp;产品名称：&nbsp;<input id="productName_product" name="productName" class="easyui-textbox" style="width: 200px">
				<a href="#" class="easyui-linkbutton" iconcls="icon-search" onclick="loadProduct(1)">搜索</a>
			</shiro:hasPermission> 
		</div>
		<table id="productList" style="width: auto; height: 400px">
		</table>
		<!-- Begin of easyui-dialog -->
			<div id="productDialog" class="easyui-dialog" style="width: 520px;padding: 1px;"
				data-options="closed:true,
							iconCls:'icon-document'" >
				<form id="productForm" method="post" enctype="multipart/form-data">
						<input type="hidden" name="productId" id="productId_productCompany" />
						<input type="hidden" name="productType" value="1" />
						
					   <div class="easyui-tabs" id="productInfoTabs" style="width:500px;height:380px;padding:10px;" data-options="plain:true,narrow:true,pill:false,justified:false">
							    <!-- 基础信息tab标签开始 -->
							    <div title="基础信息" style="padding:5px;"  >
									<br />
									<!-- <div class="ftitle"  style="width: 350px;margin: 0px 20px 20px;" >产品信息</div> -->
									<div class="fitem">
									          <label class="fitemlabel width_100">产品名称：</label>
									          <input type="text" name="productName" class="easyui-textbox wu-text"   style="width: 330px;"
									          		data-options="prompt:'产品名称',required:true,validType:['length[0,200]']" />&nbsp;
									</div>
									<div class="fitem">
									          <label class="fitemlabel width_100">产品简称：</label>
									          <input type="text" name="productShortName" class="easyui-textbox wu-text"  style="width: 330px;"
									          		data-options="prompt:'产品简称',validType:['length[0,200]']" />&nbsp;
									</div>
									<div class="fitem">
									          <label class="fitemlabel width_100">市场商品码：</label>
						            		  <input type="text" name="marketCode" class="easyui-textbox wu-text"   style="width: 330px;"
						            		  		data-options="prompt:'市场商品码',required:true,validType:['length[0,6]']"/>
									</div>
									<div class="fitem">
									          <label class="fitemlabel width_100">产品分类：</label>
										      <select id="productCategoryId" name="productCategoryId" style="width: 330px;"> </select> 
									</div>				
									<div class="fitem">
									          <label class="fitemlabel width_100">产品编码：</label>
									          <input type="text" name="productCode" class="easyui-textbox wu-text"  style="width: 330px;"
									          		data-options="prompt:'产品编码',required:true,validType:['length[0,8]']" />&nbsp;
									</div>
									<div class="fitem">
											  <label class="fitemlabel width_100">状态：</label>
											  <input type="hidden" name="status" id="status_product_hidden" value='0' >
											  <input class="easyui-switchbutton" id="status_product" style="width: 330px;">
									</div>		
									<div class="fitem">
										<label class="fitemlabel width_100">产地:</label> 
										<input class="easyui-combobox" name="madein" style="width: 330px;"
										data-options="prompt:'产地',valueField:'id',textField:'catgName',url:'productPurchaseInstockOrder/areaList'">&nbsp;
									</div>
									<div class="fitem">
									          <label class="fitemlabel width_100">产品简介：</label>
										      <input type="text" name="productDesc" class="easyui-textbox wu-text" style="width: 330px;height: 42px" 
										      		data-options="prompt:'产品简介',multiline:true,validType:['length[0,400]']" />
									</div>
							    </div>
							    <!-- 基础信息tab标签结束 -->
							    <!-- 产品图片tab标签开始 -->
							    <div title="产品图片" style="padding:5px;" >
							       <div class="fitem"  style="margin-left: 0px;margin-top: 20px">
							       		<div class="fitem productImg" >
								       		<ul class="items list-inline" style="margin-left: 9px;">
								       			<li>
								       				<img id="product_img"  border="0" >
								       				<p>产品图片</p>
								       			</li>
								       			<li>
								       				<img id="product_publicityImage"   border="0">
								       				<p>宣传图片</p>
								       			</li>								       		
								       		</ul>
										</div>
										
										<div style="clear:both" ></div><!-- html注释：清除float产生浮动 -->
								       <div class="fitem"   style="margin-top: 0px;margin-left: 59px;">
										          <input id="productImgFilebox"  name="productImgfile" class="wu-text"  style="width: 320px;"/>
												  <input type="hidden" name="productImageUrl">
												  <a href="#" class="easyui-linkbutton" style="height: 22px" 
												  		onclick="javascript: $('#productImgFilebox').filebox('setValue', '');$('#product_img').attr('src','');">清空</a>
										</div>
										<div class="fitem" style="margin-top: 0px;margin-left: 59px;">
										          <input id="publicityImageFilebox"  name="publicityImagefile" class="wu-text"  style="width: 320px;"/>
												  <input type="hidden" name="publicityImageUrl">
												  <a href="#" class="easyui-linkbutton" style="height: 22px" 
												  		onclick="javascript: $('#publicityImageFilebox').filebox('setValue', '');$('#product_publicityImage').attr('src','');">清空</a>
										</div>	
									</div>			
							    </div>
							    <!-- 产品图片tab标签结束 -->
							    <!-- 数据共享tab标签结束 -->
							    <div title="数据共享" style="padding:5px;" >
							     	<table id="sharCompanysDataGrid" style="width: auto; height: 320px"  class="easyui-datagrid" title="数据共享企业">	
									</table>
							    </div>
					   </div>
					   
				   		<div id="tb_CompanysDataGrid" style="height:auto">
								<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append_productCompany()">新增</a>
						    	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="remove_companysDataGrid()">删除</a> 
						</div>
				</form>
			</div>
			<div id="productCompanyDialog" class="easyui-dialog" style="width: 350px;padding: 1px;"
				data-options="closed:true,iconCls:'icon-document'" >
					<div class="fitem" style="margin-top: 20px;">
					          <label class="fitemlabel width_100">关联企业：</label>
						      <input id="companyId_Customer" class="easyui-textbox" style="width:200px;" data-options="prompt:'关联企业只限制与客户',validType:length[1,255]">
					</div>
					<div style="margin: 20px 50px; text-align: center;">
						<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
							style="width: 120px; height: 25px;" onclick="save_productCompany()">确认提交</a>
					</div>
			</div>
    </div>
</div> 
<script type="text/javascript">
var initFlag_product=0;

$(function() {

	

	    /*读取数据共享企业明细*/
		$('#sharCompanysDataGrid').datagrid({
			url:'productCompany/findAllProductCompany',
            //iconCls: 'icon-add',
            toolbar: '#tb_CompanysDataGrid',
            fitColumns : true,
		    singleSelect : true,
		    rownumbers : true,
		    loadMsg : '页面正在加载....',
		    columns : [[
						{
							field : 'companyName',
							title : '企业名称',
							width : '45%',
						},
						{
							field : 'customerAlias',
							title : '客户名称',
							width : '45%',
						}
			]],
		
		});
	

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
	function save_productCompany(){
		$.post("productCompany/add", {
			productId : $("#productId_productCompany").val(),
			companyId : $("#companyId_Customer").combobox('getValue') 
		}, function(data) {
			var msg = eval('(' + data + ')');
			$.messager.alert('信息提示', msg.msg, 'info');
			if (msg.code == 1) {
				
				$('#productCompanyDialog').dialog({
					closed : true
				});
				$('#sharCompanysDataGrid').datagrid('reload');
			}
		});	
	}
	function append_productCompany(){
		
		$('#productCompanyDialog').dialog({
			closed : false,
			modal : true,
			title : "新增产品关联企业"
		});
	 	$("#companyId_Customer").combobox({ 
			url : 'customers/findFilterCustomers',
			valueField : 'custCompanyId',
			textField : 'customerAlias',
			editable:false,
			onBeforeLoad : function(param) {
				param.companyId = $("#index_user_companys").combobox('getValue');
				param.productId =  $("#productId_productCompany").val();
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
		delsharCompanysDataGridRow();
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
		$('#productInfoTabs').tabs('select',0);
		$('#productInfoTabs').tabs('disableTab',2);
	}
	 
	
	function remove_companysDataGrid(){
		var row = $('#sharCompanysDataGrid').datagrid('getSelected');
		if (row) {
			$.messager.confirm('信息提示', '确定要删除这条信息吗？', function(result) {
				if (result) {
					$.post("productCompany/delete", {
						productId : row.productId,
						companyId : row.companyId
					}, function(data) {
						var msg = eval('(' + data + ')');
						$.messager.alert('信息提示', msg.msg, 'info');
						if (msg.code == 1) {
							var rowindex = $('#sharCompanysDataGrid').datagrid('getRowIndex',row);
							console.info(rowindex);
							$('#sharCompanysDataGrid').datagrid('cancelEdit',rowindex).datagrid('deleteRow',rowindex);
						}
					});

				}
			});
		} else {
			$.messager.alert('信息提示', '亲,请选择一行信息！', 'info');
		}
		
	}

	/**
	 * button => 打开修改窗口
	 */
	function productEdit() {
		var row = $('#productList').datagrid('getSelected');
		if (row) {
			$('#productForm').form('clear');
			delsharCompanysDataGridRow();
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
			$('#sharCompanysDataGrid').datagrid({
				url:'productCompany/findAllProductCompany',
				onBeforeLoad : function(param) {
					param.companyId =  row.companyId;
					param.productId = row.productId;
				},
			});
			$('#sharCompanysDataGrid').datagrid('reload');

			
			$('#productDialog').dialog({
				closed : false,
				modal : true,
				title : "编辑产品",
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
			
			$('#productInfoTabs').tabs('select',0);
			$('#productInfoTabs').tabs('enableTab',2);

		 	
			
			
			
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
					url : 'product_yz/addProduct',
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
	//清空gridview
	function delsharCompanysDataGridRow() {
		var rows = $('#sharCompanysDataGrid').datagrid('getRows');
		for (var i = rows.length - 1; i > -1; i--) {
			$('#sharCompanysDataGrid').datagrid('cancelEdit', i).datagrid('deleteRow', i);
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
