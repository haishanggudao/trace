<%@ page import="org.apache.commons.lang3.StringUtils"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<link rel="stylesheet" type="text/css" href="static/css/datagrid_util.css">
<script type="text/javascript" src="static/js/jquery-barcode.js"></script>
<script type="text/javascript" src="static/js/jquery.qrcode.min.js"></script>
<script type="text/javascript" src="static/js/qrcode.js"></script>
<script type="text/javascript" src="static/js/moment.js"></script>
<style media=print> 
	.PageNext{page-break-after: always;}  
</style> 
<!-- layout  -->
<div class="easyui-layout" style="width:100%; " data-options="fit:true">
	<div id="p_goods" data-options="region:'west',split:true" title="类别" style="width:20%; ">
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
					loadGoodsByCategory(row.productCategoryId);
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
	<div data-options="region:'center'" title="商品列表-水果协会">
		<div id="wu-toolbar" style="padding: 5px 0;">
			<div class="wu-toolbar-button">
				<shiro:hasPermission name="base:goods:add">
					<a href="#" class="easyui-linkbutton" iconCls="icon-add-s1" onclick="openGoodsAdd()" plain="true">新增商品</a>
					<span style="color: #D3D3D3">| </span>
				</shiro:hasPermission>
				<shiro:hasPermission name="base:goods:edit">
					<a href="#" class="easyui-linkbutton" iconCls="icon-edit-s1" onclick="openGoodsEdit()" plain="true">修改商品</a>
					<span style="color: #D3D3D3">| </span>
				</shiro:hasPermission>
		
				<shiro:hasPermission name="base:goods:delete">
					<a href="#" class="easyui-linkbutton" iconCls="icon-delete-s1" onclick="removeGoods()" plain="true">删除商品</a>
					<span style="color: #D3D3D3">| </span>
				</shiro:hasPermission>
		
				<shiro:hasPermission name="base:goods:createQRCode">
					<a href="#" class="easyui-linkbutton" iconCls="icon-codeqr" onclick="openQRCode()" plain="true" style="display: none">生成二维码</a>
					<span style="color: #D3D3D3">| </span>
				</shiro:hasPermission>
				
				<shiro:hasPermission name="base:goods:printQRCode">
					<a href="#" class="easyui-linkbutton" iconCls="icon-codeqr" onclick="createQRCode()" plain="true">打印二维码</a>
					<span style="color: #D3D3D3">| </span>
				</shiro:hasPermission>
				
				<shiro:hasPermission name="base:goods:printBarCode">
					<a href="#" id="createBarcode" class="easyui-splitbutton" data-options="menu:'#createbarcode',iconCls:'icon-codeqr'"  onclick="printBarcode()" plain="true">打印条形码</a>
					<span style="color: #D3D3D3">| </span>
				</shiro:hasPermission>
				
				<shiro:hasPermission name="base:goods:downQRCode">
					<a href="#" class="easyui-linkbutton" iconCls="icon-download-s1" onclick="downloadCode()" plain="true">下载追溯码</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="base:goods:printBarCode">
					<div id="createbarcode" style="width:100px;">
						<div data-options="iconCls:'icon-ok'" onclick="openBarcode()">打印条形码预览</div>
					</div>
				</shiro:hasPermission>
				&nbsp;配送类型：&nbsp;
					<select id="searchDeliverType_productGoods" style="width: 150px;"></select>
				&nbsp;产品分类：&nbsp; 
					<select id="searchProductCategoryId_productGoods" style="width: 150px;"></select> 
				&nbsp;产品名称：&nbsp; 
					<input id="productName_productGoods" class="easyui-textbox" style="width: 200px"> 
				&nbsp;&nbsp;&nbsp;
					<a href="#" class="easyui-linkbutton" iconcls="icon-search" onclick="loadGoods(1)">搜索</a>
			</div>
			
		</div>
		
		<!-- table 商品列表 -->
		<table id="goodsList" ></table>
		
		<div id="goodsCodeDialog" class="easyui-dialog" title="打印商品二维码"
			data-options="modal:true,closed:true"
			style="width: 330px; padding: 20px;">
			<div class="fitem">
				<label class="fitemlabel width_100">选择二维码排列:</label> 
				<select id="multiImgCount" class="easyui-combobox" name="multiImgCount" style="width: 80px;">
					<option value="1">单排</option>
					
				</select>
				&nbsp;&nbsp; 
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="printQRCode()" style="width: 60px; height: 22px">确认</a>
			</div>
		</div>
		
		<div id="downloadGoodsCodeDialog" class="easyui-dialog" title="商品二维码生成"
			data-options="modal:true,closed:true"
			style="width: 330px; padding: 20px;">
			<div class="fitem">
				<label class="fitemlabel width_100">选择二维码排列:</label> 
				<select id="downMultiImgCount" class="easyui-combobox" name="multiImgCount" style="width: 80px;">
					<option value="1">单排</option>
					
				</select>
				&nbsp;&nbsp; 
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="addQRCode()" style="width: 60px; height: 22px">确认</a>
			</div>
		</div>
		<!-- Begin of easyui-dialog -->
		<div id="goodsEditDialog" class="easyui-dialog" title="新增"
			data-options="modal:true,closed:true,iconCls:'icon-document'"
			style="width: 430px; padding: 20px;" buttons="#buttons_goods">
			<div class="ftitle">商品信息</div>
			<form id="goodsForm" method="post">
				<input type="hidden" name="goodsId">
				<table>
					<tr height="35">
						<td width="70" align="right">产品：</td>
						<td>
							<select id="productId_goods" name="productId" style="width: 266px;"></select>
						</td>
					</tr>
					<tr height="35">
						<td align="right">规格明细：</td>
						<td>
							<select id="productStandardDetailId_goods" name="productStandardDetailId" style="width: 266px;"></select>
						</td>
					</tr>
					<tr height="35">
						<td align="right">批次：</td>
						<td>
							<input id="goodsBatch_goods" name="goodsBatch" type="text" class="easyui-textbox" style="width: 266px" data-options="required:true" />
						</td>
					</tr>
					<tr id="deliverType_tr" height="35">
						<td align="right">类型：</td>
						<td>
							<select id="deliverType" class="easyui-combobox" name="deliverType" style="width: 266px"></select>
						</td>
					</tr>
					<tr height="35">
						<td align="right">数量：</td>
						<td>
							<input name="num" id="num" type="text" class="easyui-numberbox" style="text-align: right; width: 266px;" min=1; data-options="required:true">
						</td>
					</tr>
					<tr height="35">
						<td align="right" >等级：</td>
						<td>
							<select id="goodsLevel" class="easyui-combobox" name="level" style="width: 266px"></select>
						</td>
					</tr>
					<tr height="35" style="visibility:hidden">
						<td align="right" >标签类型：</td>
						<td>
							<input name="type" type="text" class="easyui-textbox" style="width: 266px" />
						</td>
					</tr>
					<tr height="35" style="visibility:hidden">
						<td align="right" >标签编码：</td>
						<td>
							<input name="code" type="text" class="easyui-textbox" style="width: 266px" />
						</td>
					</tr>
					<tr height="35" style="visibility:hidden">
						<td align="right">标签密钥：</td>
						<td>
							<input name="secretKey" type="text" class="easyui-textbox" style="width: 266px" />
						</td>
					</tr>
				</table>
				<div id="buttons_goods" style="text-align: center;">
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="addGoods()" style="width: 120px; height: 32px">确认提交</a>
				</div>
			</form>
		</div>		
	</div><!-- end of region-center -->
</div> 
<div id="goods_barcode_window" class="tablebox easyui-dialog"  data-options="modal:true,closed:true">
	<div id="barcode_print">
	</div>
</div>
<div id="goods_qrcode_window" class="tablebox easyui-dialog"  data-options="modal:true,closed:true">
	<div id="qrcode_print">
	</div>
</div>
<script type="text/javascript">
	var initFlag_goods = 0;
	var searchDeliverType_productGoods_id = '-1';
	$(function() {
		$("#searchProductCategoryId_productGoods").combobox(
				{
					url : 'product_category/listForSelecting',
					method : 'post',
					valueField : 'productCategoryId',
					textField : 'productCategoryName',
					onBeforeLoad : function(param) {
						param.companyId = $("#index_user_companys").combobox(
								'getValue');
					},
					filter : function(q, row) {
						var opts = $(this).combobox('options');
						return row[opts.textField].indexOf(q) == 0;
					},
					onLoadSuccess : function() {
						$('#searchProductCategoryId_productGoods').combobox(
								'setValue', null);
					}
				});
		loadGoods();
		$('#deliverType').combobox({
		    url:'goods/getdelivertype?companyid=' + $("#index_user_companys").combobox('getValue').toUpperCase(),
		    valueField:'id',
		    textField:'text',
		    editable : false,
		    onLoadSuccess:function(){
		    	$('#deliverType').combobox('setValue',0);
		    } 
		});
		$('#searchDeliverType_productGoods').combobox({
		    url:'goods/getsearchdelivertype?companyid=' + $("#index_user_companys").combobox('getValue').toUpperCase(),
		    valueField:'id',
		    textField:'text',
		    onLoadSuccess:function(){
		    	$('#searchDeliverType_productGoods').combobox('setValue',-1);
		    } ,
		    onSelect : function(record){
		    	searchDeliverType_productGoods_id = record.id;
		    }
		});
		$('#goodsLevel').combobox({
		    url:'goods/getGoodsLevels',
		    valueField:'varName',
		    textField:'varValue',
		    editable : false,
		    onLoadSuccess:function(){
		    	$('#goodsLevel').combobox('setValue','0');
		    } 
		});
		searchDeliverType_productGoods_id = '-1';
	});
	

	/**
	 * button onClick => 删除商品
	 */
	function removeGoods() {
		var checkedItems = $('#goodsList').datagrid('getChecked');
		if (checkedItems.length == 1) {
			var row = checkedItems[0];
			$.messager.confirm('信息提示', '确定要删除该商品？', function(result) {
				if (result) {
					$.post('goods/delgoods', {
						goodsId : row.goodsId
					}, function(data) {
						var msg = eval('(' + data + ')');
						$.messager.alert('信息提示', msg.msg, 'info');
						if (msg.code == 1) {
							$('#goodsList').datagrid('reload');
						}
					});
				}
			});
		} else {
			$.messager.alert('信息提示', '请选择一个商品！', 'info');
		}

	}

	/**
	 *生成二维码
	 */
	function addQRCode() {
		var rows = $('#goodsList').datagrid('getChecked');
		var checkedItems = $('#goodsList').datagrid('getChecked');
		if (checkedItems.length == 1) {
			var row = checkedItems[0];
			var name = encodeURI(encodeURI(row.product.productName, 'UTF-8'),
					"UTF-8");
			window.open("goods/downloadqc?goodsId=" + row.goodsId
					+ "&multiImgCount="
					+ $("#downMultiImgCount").combobox("getValue") + "&qrNum="
					+ row.num + "&productName=" + name + "&companyId="
					+ $("#index_user_companys").combobox('getValue'));

			$('#goodsCodeDialog').window('close');

		} else {
			$.messager.alert('信息提示', '亲,请选择一个商品,暂时不可以选择多个的！', 'info');
		}
	}

	function openQRCode() {
		var checkedItems = $('#goodsList').datagrid('getChecked');
		if (checkedItems.length == 1) {
			$('#downloadGoodsCodeDialog').dialog({
				closed : false,
				modal : true,
				iconCls : 'icon-codeqr'
			});
			$('#downMultiImgCount').combobox('setValue', '1');
		} else {
			$.messager.alert('信息提示', '亲,请选择一个商品,暂时不可以选择多个的！', 'info');
		}
	}
	
	function createQRCode(){
		var checkedItems = $('#goodsList').datagrid('getChecked');
		if (checkedItems.length == 1) {
			$("#qrcode_print").empty();
			$('#goodsCodeDialog').dialog({
				closed : false,
				modal : true,
				iconCls : 'icon-codeqr'
			});
			$('#multiImgCount').combobox('setValue', '1');
		} else {
			$.messager.alert('信息提示', '亲,请选择一个商品,暂时不可以选择多个的！', 'info');
		}
	}
	
	function printQRCode(){
		var rows = $('#goodsList').datagrid('getChecked');
		
		var row=rows[0];
		// console.info(row);
		
		$("#qrcode_print").empty();
		var param={
				goodsId:row.goodsId,
				num:row.num,
				companyId:$("#index_user_companys").combobox('getValue')
		};
		var multiImgCount=$("#multiImgCount").combobox('getValue');
		$.post("goods_fruit/getQRCodeAndUrl",param,function(data){
			for(var i=0;i<data.length;i++){
				// console.info(data[i]);
				$("#qrcode_print").qrcode({ 
					correctLevel    : QRErrorCorrectLevel.M,//纠错等级
					text		: data[i].code, 
					width		: 75,
					height		: 75,
				});
				
			}
			var codes=$("#qrcode_print").find("canvas");
			codes.each(function(index){
				var img=$(this).get(0).toDataURL("image/png");
				console.info(img);
				$(this).remove();
				var div;
				var imgObj;
				if(multiImgCount==1){
					if(index==codes.length-1){
						div=$("<div style='text-align:center;'></div>");
					}else{
						div=$("<div style='text-align:center;page-break-after: always;'></div>");
					}
					imgObj=$("<img style='margin:10px 1px 1px 1px;'>").attr("src",img);
					divTop = $("<div style='width:100%;height:10%; margin:15px auto auto auto; font-family:楷体; font-size:14px;'>" + row.product.productName + "</div> ");
					divLeft = $("<div style='width:57%;float:left; font-family:楷体; font-size:12px;' ></div>");
					divRight = $("<div style='float:left;'></div> ");
				}else{
					div=$("<div style='float:left;'></div>");
					imgObj=$("<img style='margin:10px 10px 10px 15px;'>").attr("src",img);
				} 
				var div2=$("<div style='text-align: left; padding-top:1px;'></div>");
				var myLevel = row.level;
				div2.html("等级:"  + convertLevelIdToLevelName(row.level)
						+ "<br>包装:" + moment(row.createTime).format("YYYY-MM-DD")
						+ "<br>规格:" + row.productStandardDetail.fullStandardName 
						+ "<br>产地:"+row.areaInfoId
						+ "<br>企业:" + row.company.name + "");
				var center=$("<center></center>") 
				divLeft.append(div2);
				divRight.append(imgObj);
				center.append(divTop).append(divLeft).append(divRight);
				div.append(center); 
				// console.info(div);
				$("#qrcode_print").append(div);
			}); 
			
// 			$('#goods_qrcode_window').dialog({
// 				closed : false,
// 				modal : true,
// 				width:'330px',
// 				height:'300px',
// 				title : "电子随附单",
// 				iconCls : 'icon-save',
// 				buttons : [ {
// 					text : '打印',
// 					iconCls : 'icon-print',
// 					handler : function(){
// 						$("#qrcode_print").printArea();
// 					}
// 				}, {
// 					text : '取消',
// 					iconCls : 'icon-cancel',
// 					handler : function() {
// 						$('#goods_barcode_window').dialog('close');
// 					}
// 				} ]
// 			});
			$("#qrcode_print").printArea();
		});
	}
	
	function convertLevelIdToLevelName(levelId){
		var myLevelName = "特级";
		if(levelId == "0"){
			myLevelName = "特级";
		} else if (levelId == "1"){
			myLevelName = "一级";
		} else if (levelId == "2"){
			myLevelName = "二级";
		} else if (levelId == "3"){
			myLevelName = "三级";
		}
		return myLevelName;
	}
	
	/**
	 * download the barcode
	 */
	 function initBarcode(row,fun){
		$("#createBarcode").splitbutton("disable");

		 var params={
					goodsId:row.goodsId,
					qrNum:row.num,
					companyId:$("#index_user_companys").combobox('getValue'),
					token:$('#index_token').val()
			};
			$("#barcode_print").empty();
			$.post("goods/getBarCodes",params,function(data){
				for(var i=0;i<data.length;i++){
					var goodsName=$("<div style='text-align:center;margin-bottom:2px;'></div>");
					goodsName.text(row.product.productName);
					var printDiv=$("<div style='margin:20px auto 3px;'></div>");
					printDiv.barcode(data[i].barcode, "code128",{ barHeight:'50',showHRI:true,output:'bmp'});
					var a=printDiv.find('object').attr('width');
					printDiv.css('width',a);
					printDiv.prepend(goodsName);
					$("#barcode_print").append(printDiv);
					if(i!=data.length-1){
						var end=$("<div style='page-break-after: always;'></div>");
						$("#barcode_print").append(end);
					}
				}
				createToken();
				fun();
				$("#createBarcode").splitbutton("enable");
			});
	}
	 function printBarcode(row){
		 var rows = $('#goodsList').datagrid('getChecked');
		 if (rows.length == 1) {
			 var row = rows[0];
			 $.messager.confirm('信息提示', '确定要打印'+row.num+"个条形码吗？", function(result) {
					if (result) {
						 initBarcode(row,function(){
							$("#barcode_print").printArea();
						});
					}});
			 
		 }
	}
	function openBarcode(){
		var rows = $('#goodsList').datagrid('getChecked');
		if (rows.length == 1) {
			var row = rows[0];
			initBarcode(row, function(){
				$('#goods_barcode_window').dialog({
					closed : false,
					modal : true,
					width:'330px',
					height:'300px',
					title : "电子随附单",
					iconCls : 'icon-save',
					buttons : [ {
						text : '打印',
						iconCls : 'icon-print',
						handler : function(){
							$("#barcode_print").printArea();
						}
					}, {
						text : '取消',
						iconCls : 'icon-cancel',
						handler : function() {
							$('#goods_barcode_window').dialog('close');
						}
					} ]
				});
			});

		} else {
			$.messager.alert('信息提示', '亲,请选择一个商品,暂时不可以选择多个的！', 'info');
		}
	}

	function downloadCode() {
		var rows = $('#goodsList').datagrid('getChecked');
		var lstGoods = new Array();
		if (rows.length >= 1) {
			var vcompanyId = $("#index_user_companys").combobox("getValue");
			for (var i = 0; i < rows.length; i++) {
				var goods = {};
				var product = {};
				goods["code"] = rows[i].code;
				product["marketCode"] = rows[i].product.marketCode;
				goods["product"] = product;
				lstGoods.push(goods);
			}
			window.open("goods/downloadcode?strJson="
					+ encodeURI(JSON.stringify(lstGoods)));
		} else {
			$.messager.alert('信息提示', '请选择一个商品！', 'info');
		}
	}


	/**
	 * button onClick => 打开添加窗口
	 */
	function openGoodsAdd() {
		 bSelectOne_standardDetail=0;
		if (initFlag_goods == 0) {
			initDialogComponent_goods();
			// 			initFlag_goods = 1;
		}

		$('#goodsForm').form('clear').form('disableValidation');

		$.post("goods/getGoodsBatchNo",function(data){
	    	 $('#goodsBatch_goods').textbox('setValue', data);
	    });

		$('#goodsEditDialog').dialog({
			closed : false,
			modal : true,
			title : "新增商品",
			iconCls : 'icon-document'

		});
		$('#deliverType').combobox('setValue',0);
		$('#goodsLevel').combobox('setValue',0);
	}

	/**
	 * button onClick => 打开修改窗口
	 */
	function openGoodsEdit() {
		if (initFlag_goods == 0) {
			initDialogComponent_goods();
			initFlag_goods = 1;
		}
		var checkedItems = $('#goodsList').datagrid('getChecked');
		if (checkedItems.length == 1) {
			var row = checkedItems[0];
			$('#goodsForm').form('clear').form('disableValidation');
			$("#productStandardDetailId_goods").combobox(
					'reload',
					"product_standard_detail/list?productId="
							+ (row.product.productId));
			$('#goodsForm').form('load', {
				goodsId : row.goodsId,
				productId : row.product.productId,
				processMainId : row.processMainId,
				companyId : row.companyId,
				num : row.num,
				productStandardDetailId : row.productStandardDetailId,
				goodsBatch : row.goodsBatch,
				type : row.type,
				code : row.code,
				secretKey : row.secretKey,
				deliverType : row.deliverType,
				level: row.level
			});

			$('#goodsEditDialog').dialog({
				closed : false,
				modal : true,
				title : "修改商品",
				iconCls : 'icon-document'

			});
		} else {
			$.messager.alert('信息提示', '请选择一个商品！', 'info');
		}
	}

	/**
	 * handler => action: 新增或修改商品
	 */
	function addGoods() {
      
		var myUserCompanyId = $('#index_user_companys').combobox('getValue');
		// console.info('myUserCompanyId:' + myUserCompanyId);
		$('#goodsForm').form('submit', {
			url : 'goods/addgoods',
			onSubmit : function(param) {

				param.companyId = myUserCompanyId;

				return $(this).form('enableValidation').form('validate');
			},
			success : function(data) {
				var msg = eval('(' + data + ')');
				$.messager.alert('信息提示', msg.msg, 'info');
				if (msg.code == 1) {
					$('#goodsEditDialog').window('close');
					$('#goodsList').datagrid('reload');
				}
			}
		});
	}

	/**
	 * initialize the component of the dialog
	 */
	function initDialogComponent_goods() {
		// 产品
		$("#productId_goods").combobox(
				{
					url : 'product/list',
					method : 'post',
					valueField : 'productId',
					textField : 'productName',
					onBeforeLoad : function(param) {
						param.companyId = $("#index_user_companys").combobox(
								'getValue');
						param.productType = '1';
						// 						param.random=Math.random();
					},
					required : true,
					editable : true,
					onSelect : function(data) {
						$("#productStandardDetailId_goods").combobox('clear');
						$("#productStandardDetailId_goods").combobox(
								'reload',
								"product_standard_detail/list?productId="
										+ (data.productId));
					}
				});
		// 规格明细
		$("#productStandardDetailId_goods").combobox(
				{
					url : 'product_standard_detail/list',
					method : 'post',
					valueField : 'productStandardDetailId',
					textField : 'fullStandardName',
					required : true,
					editable : false,
					onLoadSuccess : function(param) {
					//	param.companyId = $("#index_user_companys").combobox(
					//			'getValue');
						$(this).combobox('select',param[0].productStandardDetailId);
					}
				});
	}

	/**
	 * 载入商品信息 & 查询商品信息；
	 */
	function loadGoods(loadType) {
		var querylist = '';
		if (loadType === 1) {
			querylist = 'goods/findAllQueryList';
		} else {
			querylist = 'goods/findAllList';
		}
		<shiro:hasPermission name="base:goods:edit">
		 $('#goodsList').datagrid({
			onDblClickCell: function(index,field,value){
				openGoodsEdit()
			}
		 });
		</shiro:hasPermission>
		$('#goodsList').datagrid(
				{
					method : 'post',
					url : querylist,
					rownumbers : true,
					pageSize : 10,
					pagePosition : 'bottom',
					pageList : [ 10, 20, 50 ],
					pagination : true,
					multiSort : true,
					fitColumns : true,
					fit : true,
					iconCls : 'icon-ok',
					toolbar : '#wu-toolbar',
					onBeforeLoad : function(param) {
						var myUserCompanyId = $('#index_user_companys').combobox('getValue');
						param.companyId = myUserCompanyId;
						param.producttype = '1';
						param.productName = $("#productName_productGoods").val();
						// console.info(searchDeliverType_productGoods_id);
						if(searchDeliverType_productGoods_id != '-1') {
							param.deliverType = searchDeliverType_productGoods_id;
						}
						param['product.productCategoryId'] = $("#searchProductCategoryId_productGoods").combobox('getValue');
					},
					columns : [ [ {
						field : 'goodsck',
						checkbox : true
					}, {
						field : 'productName',
						title : '产品',
						width : 70,
						sortable : true,
						formatter : function(val, row, index) {
							return row.product.productName;
						}
					}, {
						field : 'deliverType',
						title : '配送类型',
						width : 30,
						sortable : true,
						formatter : function(val, row, index) {
							return row.deliverTypeName==null?"默认类型":row.deliverTypeName;
						}
					}, {
						field : 'productStandardNum',
						title : '产品规格明细',
						width : 50,
						sortable : true,
						formatter : function(val, row, index) {
							return row.productStandardDetail.fullStandardName;
						}
					}, {
						field : 'goodsBatch',
						title : '商品批次',
						width : 50
					}, {
						field : 'name',
						title : '企业',
						width : 0,
						hidden : true,
						formatter : function(val, row, index) {
							return row.company.name;
						}
					}, {
						field : 'num',
						title : '数量',
						width : 20
					}, {
						field : 'createTime',
						title : '创建时间',
						width : 40,
						formatter : function(val, row, index) {
							return convertTimeStamp(row.createTime);
						}
					} ] ]
				});
	}
	
	function loadGoodsByCategory(productCategoryId) {
		 //console.info(productCategoryId);
		 $('#goodsList').datagrid({
				queryParams: {
					'product.category.productCategoryId': productCategoryId,
					companyId: $("#index_user_companys").combobox("getValue")
				}
			}); 
	}  
</script>