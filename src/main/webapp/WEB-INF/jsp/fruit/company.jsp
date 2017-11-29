<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<link rel="stylesheet" type="text/css" href="static/css/datagrid_util.css">
<div id="itemBtns" style="padding: 5px 0;">
	<shiro:hasPermission name="system:company:add">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-add-s1'" onclick="addItem()">新增企业</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="system:company:edit">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit-s1'" onclick="editItem()">编辑企业</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="system:company:delete">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-delete-s1'" onclick="removeItem()">删除企业</a>
	</shiro:hasPermission> 
	&nbsp;搜索关键词:&nbsp;
	<input data-options="prompt:'关键词可以输入任意字段'" class="easyui-textbox" id="name_CompanySearch" name="name" style="width: 200px; height: 26px"> &nbsp;
	<a href="#" class="easyui-linkbutton" iconcls="icon-search" onclick="reloadCompany()">搜索</a> &nbsp;
	<a href="#" class="easyui-linkbutton" iconcls="icon-search" onclick="$('#dlg_companySearch').dialog('open')">高级搜索</a>
</div>

<table id="itemsgrid" title="水果协会-公司列表" style="width: 100%;"></table>

<div id="companyDialog" class="easyui-dialog" title="新增"
	data-options="modal:true,closed:true,iconCls:'icon-document'"
	style="width: 650px; padding: 20px;" buttons="#dlg_companyInfo-buttons">
	<form id="itemForm" method="post" enctype="multipart/form-data">
		<!-- 企业id -->
		<input type="hidden" name="companyid" /> <input type="hidden"
			name="orgcode" />
		<div class="easyui-tabs" id="productImgTabs"
			style="width: 100%;height:350px;"
			data-options="plain:true,narrow:true,pill:false,justified:false">
			<div title="必填" style="padding: 5px;">
				<div class="fitem"></div>
				<div class="fitem"></div>
				<div class="fitem">
					<label class="fitemlabel width_100">企业名称:</label> 
					<input class="easyui-textbox" name="name"
						data-options="prompt:'企业名称',required:true"
						style="width: 470px; height: 24px" />&nbsp;
				</div>
				<div class="fitem">
					<label class="fitemlabel width_100">企业简称:</label> <input
						class="easyui-textbox" name="shortname"
						data-options="prompt:'企业简称',required:true,validType:['name',length[1,255]]"
						style="width: 180px; height: 24px"> <label
						class="fitemlabel width_100">所属领域:</label> <select
						id="companyfieldid" name="companyfieldid" style="width: 180px;"></select>
				</div>
				<div class="fitem">
					<label class="fitemlabel width_100">企业地址:</label> <input
						class="easyui-textbox" name="address"
						data-options="prompt:'企业地址',required:true,validType:['name',length[1,255]]"
						style="width: 180px; height: 24px"> <label
						class="fitemlabel width_100">联系人:</label> <input
						class="easyui-textbox" name="contact"
						data-options="prompt:'联系人',required:true,validType:['name',length[1,60]]"
						style="width: 180px; height: 24px">&nbsp;
				</div>
				<div class="fitem">
					<label class="fitemlabel width_100">电子邮件:</label> <input
						class="easyui-textbox" name="email"
						data-options="prompt:'电子邮件',required:true,validType:['email',length[1,255]]"
						style="width: 180px; height: 24px"> <label
						class="fitemlabel width_100">电话号码:</label> <input
						class="easyui-textbox" name="tel"
						data-options="prompt:'电话号码',required:true,validType:['name',length[1,60]]"
						style="width: 180px; height: 24px">&nbsp;
				</div>
				<div class="fitem">
					<label class="fitemlabel width_100">营业执照注册号:</label> <input
						class="easyui-textbox" name="license"
						data-options="prompt:'营业执照注册号',required:true,validType:['name',length[1,60]]"
						style="width: 180px; height: 24px">
				</div>
				<div class="fitem">
					<label class="fitemlabel width_100">省:</label> <select
						id="province" name="province" class="easyui-combogrid"
						style="width: 135px;"></select> <label class="fitemlabel width_25">市:</label>
					<select id="city" name="city" class="easyui-combogrid"
						style="width: 135px;"></select> <label class="fitemlabel width_25">区:</label>
					<select id="area" name="area" class="easyui-combogrid"
						style="width: 135px;"></select>
				</div>
			</div>
			<div title="可选" style="padding: 5px;">
				<div class="fitem"></div>
				<div class="fitem"></div>
				<div class="fitem">
					<label class="fitemlabel width_100">食品安全许可:</label> <input
						class="easyui-textbox" name="foodSafetyCode"
						data-options="prompt:'食品安全许可证号',validType:['name',length[1,60]]"
						style="width: 180px; height: 24px">
					<label class="fitemlabel width_100">经营地址:</label> <input
						class="easyui-textbox" name="cbusinessaddress"
						style="width: 180px;" data-options="prompt:'经营地址'">&nbsp;	
				</div>
				<div class="fitem">
					<label class="fitemlabel width_100">法人姓名:</label> <input
						class="easyui-textbox" name="cname" style="width: 180px;"
						data-options="prompt:'法人姓名'">&nbsp; <label
						class="fitemlabel width_100">身份证号:</label> <input
						class="easyui-textbox" name="cidnumb" style="width: 180px;"
						data-options="prompt:'身份证号'">&nbsp;
				</div>
				<div class="fitem">
					<label class="fitemlabel width_100">法人地址:</label> <input
						class="easyui-textbox" name="clegalpersonaddress"
						style="width: 180px;" data-options="prompt:'法人地址'">&nbsp;
					<label class="fitemlabel width_100">企业性质:</label> 
					<input class="easyui-combobox" name="cnature" style="width: 180px;" 
							data-options="prompt:'企业性质',valueField:'varId',textField:'varValue',url:'fruit_company/getCompanyNatures'">
					&nbsp;
				</div>
				<div class="fitem">
					<label class="fitemlabel width_100">客户类别:</label> <input
						class="easyui-combobox" name="ccustomercategories"
						style="width: 180px;"
						data-options="prompt:'客户类别',valueField:'varName',textField:'varValue',url:'supplier/getccustomercategories'">&nbsp;
					<label class="fitemlabel width_100">营业执照:</label> <input
						class="easyui-textbox" name="cbusinesslicense"
						style="width: 180px;" data-options="prompt:'营业执照'">&nbsp;
				</div>
				<div class="fitem">
					<label class="fitemlabel width_100">工商注册号:</label> <input
						class="easyui-textbox" name="cregistrationnumber"
						style="width: 180px;" data-options="prompt:'工商注册号'">&nbsp;
					<label class="fitemlabel width_100">卫生许可证:</label> <input
						class="easyui-textbox" name="chygienelicense"
						style="width: 180px;" data-options="prompt:'卫生许可证'">&nbsp;
				</div>
				<div class="fitem">
					<label class="fitemlabel width_100">经营范围:</label> <input
						class="easyui-textbox" name="cbusinessscope" style="width: 180px;"
						data-options="prompt:'经营范围'">&nbsp; <label
						class="fitemlabel width_100">经营许可:</label> <input
						class="easyui-textbox" name="cliquorbusinesslicense"
						style="width: 180px;" data-options="prompt:'经营许可证'">&nbsp;
				</div>
				<div class="fitem">
					<label class="fitemlabel width_100">有效日期:</label> 
					<input class="easyui-datebox" name="ceffectivedate1" id='company_ceffectivedate1' style="width: 180px;" data-options="prompt:'起始日期'">
					&nbsp;--&nbsp;
					<input class="easyui-datebox" name="ceffectivedate2" id='company_ceffectivedate2' style="width: 180px;" data-options="prompt:'结束日期'">
					&nbsp;
				</div>
			</div>
			<div title="企业宣传" style="padding: 5px;">
				<div class="fitem">
		        	<label class="fitemlabel width_100">企业宣传：</label>
			      	<input type="text" name="presentation" class="easyui-textbox wu-text" style="width: 350px;height: 62px" data-options="prompt:'企业宣传',multiline:true,validType:['length[0,400]']" />
				</div> 
				<div class="fitem">
		        	<label class="fitemlabel width_100">企业图片：</label>
		          	<input id="companyImgFilebox"  name="companyImgfile" class="wu-text"  style="width: 320px;"/>
				  	<input type="hidden" name="imageUrl">
				  	<a href="#" class="easyui-linkbutton" style="height: 22px" onclick="javascript: $('#companyImgFilebox').filebox('setValue', '');$('#company_image').attr('src','');">清空</a>
				</div>
				<div class="fitem"  style="float:left;width:200px;margin-left: 10px">
					<div class="easyui-tabs" id="companyImgTabs" style="width:175px;height:205px;" data-options="plain:true,narrow:true,pill:false,justified:false">
					    <div title="宣传图片" style="padding:5px;" >
					       <img id="company_image" width="160px" height="160px"  border="1"  style="border-color:#ccc">
					    </div>
					</div>
				</div>
			</div>
		</div>
	</form>
</div>

<div id="dlg_companyInfo-buttons" style="text-align: center;">
	<a href="javascript:void(0)" class="easyui-linkbutton"
		iconCls="icon-ok"
		onclick="saveItem('itemForm','companyDialog','itemsgrid')"
		style="width: 120px; height: 32px">确认提交</a>
</div>

<div id="dlg_companySearch" class="easyui-dialog fm" title="高级搜索"
	data-options="modal:true,closed:true,iconCls:'icon-search'"
	style="width: 650px; padding: 20px"
	buttons="#dlg_companySearch-buttons">
	<form id="frm_companySearch" method="post">

		<div class="ftitle">高级搜索信息</div>
		<div class="fitem">
			<label class="fitemlabel width_100">企业名称:</label> <input id="s_name"
				class="easyui-textbox" style="width: 180px;"
				data-options="prompt:'企业名称',validType:length[1,255]">&nbsp;
			<label class="fitemlabel width_100">企业简称:</label> <input
				id="s_shortname" class="easyui-textbox" style="width: 180px;"
				data-options="prompt:'企业简称',validType:length[1,255]">
		</div>
		<div class="fitem">
			<label class="fitemlabel width_100">电子邮件:</label> <input id="s_email"
				class="easyui-textbox" style="width: 180px;"
				data-options="prompt:'电子邮件',validType:length[1,255]">&nbsp;
			<label class="fitemlabel width_100">企业地址:</label> <input
				id="s_address" class="easyui-textbox" style="width: 180px;"
				data-options="prompt:'企业地址',validType:length[1,255]">
		</div>
		<div class="fitem">
			<label class="fitemlabel width_100">联系人:</label> <input
				id="s_contact" class="easyui-textbox" style="width: 180px;"
				data-options="prompt:'联系人',validType:length[1,60]">&nbsp; <label
				class="fitemlabel width_100">电话:</label> <input id="s_tel"
				class="easyui-textbox" style="width: 180px;"
				data-options="prompt:'电话号码',validType:length[1,60]">
		</div>
		<div class="fitem">
			<label class="fitemlabel width_100">营业执照注册号:</label> <input
				id="s_license" class="easyui-textbox" style="width: 180px;"
				data-options="prompt:'营业执照注册号',validType:length[1,60]">&nbsp;
			<label class="fitemlabel width_100">食品安全许可证号:</label> <input
				id="s_foodSafetyCode" class="easyui-textbox" style="width: 180px;"
				data-options="prompt:'食品安全许可证号',validType:length[1,60]">&nbsp;
		</div>
	</form>

</div>
<div id="dlg_companySearch-buttons" style="text-align: center;">
	<a href="javascript:void(0)" class="easyui-linkbutton c6"
		iconCls="icon-search" onclick="searchform()" style="width: 90px">搜索</a>
	<a href="javascript:void(0)" class="easyui-linkbutton"
		iconCls="icon-reload"
		onclick="javascript:$('#frm_companySearch').form('clear')"
		style="width: 90px">重置</a>
</div>

<div id="importCompanyDiv" style="width: 400px; padding: 10px;"
	class="easyui-dialog" title="企业信息导入"
	data-options="modal:true,closed:true,iconCls:'icon-excel'">
	<form id="importCompanyForm" method="post"
		action="company/importcompany" enctype="multipart/form-data">
		<input class="easyui-filebox" style="width: 300px;"
			name="uploadImportFile"
			data-options="prompt:'企业信息导入文件',required:true,buttonText: '选择文件',buttonAlign: 'right'" />
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
			onclick="importCompanySubmit();">提交</a> <a href="#" target="_blank"
			id="importCompanySubmitTempl" class="easyui-linkbutton"
			data-options="iconCls:'icon-download-s1'">模板下载</a>
	</form>
</div>

<script type="text/javascript">
function ajaxLoading(){
    $("<div class=\"datagrid-mask\"></div>").css({display:"block",width:"100%",height:$(window).height()}).appendTo("body");
    $("<div class=\"datagrid-mask-msg\"></div>").html("正在处理，请稍候。。。").appendTo("body").css({display:"block",left:($(document.body).outerWidth(true) - 190) / 2,top:($(window).height() - 45) / 2});
 }
 function ajaxLoadEnd(){
     $(".datagrid-mask").remove();
     $(".datagrid-mask-msg").remove();            
}


	//刷新企业信息
	function reloadCompany() {
		$('#itemsgrid').datagrid({  
		    url:'fruit_company/findAllList?findType=1',  
		    queryParams:{
	        	name: $("#name_CompanySearch").val()
		    }  
		});
	}
	
	//高级搜索
	function searchform() {
		$('#itemsgrid').datagrid({  
		    url:'fruit_company/findAllList?findType=2',  
		    queryParams:{
	        	license: $("#s_license").val(),
	        	name: $("#s_name").val(),
	        	shortname: $("#s_shortname").val(),
	        	email: $("#s_email").val(),
	        	address: $("#s_address").val(),
	        	contact: $("#s_contact").val(),
	        	tel: $("#s_tel").val(),
	        	foodSafetyCode: $("#s_foodSafetyCode").val(),
		    } 
		});
	}
	//数据导入
	function importCompany() {
		$('#importCompanyDiv').window('open');
	}
	//数据导入
	function importCompanySubmit() {
		$('#importCompanyForm').form(
				'submit',
				{
					url : 'company/importcompany',
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
							$('#importCompanyDiv').window('close');
							$('#itemsgrid').datagrid('reload');
						}
					}
				});
	}

	$(document).ready(function() {
		$('#companytagsid').tabs('select',0);
		$.get('logistics/getdownloadurl', function(data) {
			data = eval('(' + data + ')');
			if (data.url) {
				$('#importCompanySubmitTempl').prop('href',
						data.url + '\companyTemplate.xls');
			}
		});
		loadSelectData(); 
		 $('#itemsgrid').datagrid({
			onDblClickCell: function(index,field,value){
				editItem()
			}
		 }); 
		$('#itemsgrid').datagrid({
			url : 'fruit_company/findAllList',
			rownumbers : true,
			pagination : true,
			pageSize : 10,
			pageList : [ 10, 20, 30 ],
			singleSelect : true,
			fitColumns : true,
			fit : true,
			toolbar : '#itemBtns',
			columns : [ [ {
				field : 'name',
				title : '企业名称',
				width : '10%'
			}, {
				field : 'shortname',
				title : '企业简称',
				width : '10%'
			}, {
				field : 'code',
				title : '标签编码',
				width : '10%'
			}, {
				field : 'address',
				title : '企业地址',
				width : '20%'
			}, {
				field : 'contact',
				title : '联系人',
				width : '10%'
			}, {
				field : 'email',
				title : '电子邮件',
				width : '10%'
			}, {
				field : 'tel',
				title : '电话',
				width : '10%'
			}, {
				field : 'license',
				title : '营业执照注册号',
				width : '10%'
			}, {
				field : 'foodSafetyCode',
				title : '食品安全许可证号',
				width : '10%'
			}] ]
		});
		
		$("#companyImgFilebox").filebox({
			prompt : '选择企业图片',
			buttonText:'选择企业图片',
			onChange : function() { 
				if($(this).filebox('getValue')!=""){
					$(this).filebox('uploadPreview',{ imgId: "company_image" });
				}
			},
			onClickButton: function() { 
					$('#companyImgTabs').tabs('select',1);
			},
			buttonText : '选择产品图片'
		});
	});

	/**
	 *打开新增用户窗口
	 */
	function addItem() {
		$('#companytagsid').tabs('select',0);
		$("#itemForm").form('clear').form('disableValidation');
		$('#companyDialog').dialog({
			closed : false,
			modal : true,
			title : "新增企业",
			iconCls : 'icon-document'
		});  
	}

	/**
	 *打开修改用户窗口
	 */
	function editItem() {
		$('#companytagsid').tabs('select',0);
		var row = $('#itemsgrid').datagrid('getSelected');
		if (row) {
			$("#itemForm").form('clear').form('disableValidation');
			try {
				provinceSelected(row.province);
				citySelected(row.province, row.city);
				console.info(row);
				setTimeout(function() {
					$('#itemForm').form('load', {
						companyid : row.companyid,
						name : row.name,
						shortname : row.shortname,
						code : row.code,
						address : row.address,
						contact : row.contact,
						code : row.code,
						email : row.email,
						tel : row.tel,
						license : row.license,
						orgcode : row.orgcode,
						companyfieldid : row.companyfieldid,
						foodSafetyCode:row.foodSafetyCode,
						province : row.province,
						city : row.city,
						area : row.area,
						status : row.status,
						createtime : row.createtime,
						createby : row.createby,
						updatetime : row.updatetime,
						updateby : row.updateby,
						cname : row.cname,
						cidnumb : row.cidnumb,
						cregistrationnumber : row.cregistrationnumber,
						clegalpersonaddress : row.clegalpersonaddress,
						cnature : row.cnature,
						ccustomercategories : row.ccustomercategories,
						cbusinesslicense : row.cbusinesslicense,
						cbusinessscope : row.cbusinessscope,
						cliquorbusinesslicense : row.cliquorbusinesslicense,
						cbusinessaddress : row.cbusinessaddress,
						chygienelicense : row.chygienelicense,
						presentation : row.presentation,
						imageUrl : row.imageUrl
					});
				}, 500);
				
				$("#companyImgFilebox").filebox("setText", row.imageUrl);
				if(row.imageUrl!= null || row.imageUrl != ""){
					$("#company_image").attr('src', row.imageUrl);
				}else{
					$('#company_image').attr('src','static/img/defaultImg.jpg');
				}
				
				$("#companyFieldId").combogrid("setValues", row.companyFieldId);
				
				if(row.ceffectivedate1 != undefined && row.ceffectivedate1 != '') {
					var date = new Date(row.ceffectivedate1);
					$('#company_ceffectivedate1').datebox('setValue',date.format("yyyy-MM-dd"));
				}
				
				if(row.ceffectivedate2 != undefined && row.ceffectivedate2 != '') {
					var date = new Date(row.ceffectivedate2);
					$('#company_ceffectivedate2').datebox('setValue',date.format("yyyy-MM-dd"));
				}
				
				$('#companyDialog').dialog({
					closed : false,
					modal : true,
					title : "编辑企业",
					iconCls : 'icon-document'
				}); 
				
				
			} catch (ex) {
			}
		} else {
			$.messager.alert('信息提示', '请选择一行记录！', 'info');
		}
	}

	function removeItem() {
		var row = $('#itemsgrid').datagrid('getSelected');
		if (row) {
			$.ajax({
				url : 'company/delete',
				data : {
					id : row.companyid
				},
				success : function(data) {
					data = eval('(' + data + ')');
					if (data.code == '1') {
						$.messager.alert('信息提示', '删除成功', 'info');
						$('#itemsgrid').datagrid('reload');
					} else {
						$.messager.alert('信息提示', data.msg, 'warn');
					}
				}
			});
		} else {
			$.messager.alert('信息提示', '请选择一行记录！', 'info');
		}
	}

	function loadSelectData() {
		$("#companyfieldid").combogrid({
			panelWidth : 200,
			url : 'company/getcompanyfields',
			method : 'post',
			multiple : false,
			required : true,
			prompt : '所属领域',
			idField : 'companyFieldId',
			textField : 'companyFieldName',
			fitColumns : true,
			editable : true,
			columns : [ [ {
				field : 'companyFieldName',
				title : '企业领域名称',
				width : 100
			}, {
				field : 'level',
				title : '分类等级',
				width : 100
			} ] ]

		});
		$("#province").combogrid({
			panelWidth : 200,
			url : 'areaInfo/getprovinces',
			method : 'post',
			multiple : false,
			required : true,
			prompt : '省',
			idField : 'province',
			textField : 'cityName',
			fitColumns : true,
			editable : true,
			columns : [ [ {
				field : 'cityName',
				title : '地区名称',
				width : '100%'
			}, {
				field : 'catgName',
				title : '地区全名',
				hidden : true
			}, {
				field : 'province',
				title : '省',
				hidden : true
			}, {
				field : 'city',
				title : '城市',
				hidden : true
			}, {
				field : 'area',
				title : '地区',
				hidden : true
			} ] ],
			onSelect : function(record) {
				var pcg = $('#province').combogrid('grid');
				var row = pcg.datagrid('getSelected');
				try {
					provinceSelected(row.province);
				} catch (ex) {
				}
			}
		});
		

	}

	function provinceSelected(province) {
		$("#city").combogrid({
			panelWidth : 200,
			url : 'areaInfo/getcitys?proviceId=' + province,
			method : 'post',
			multiple : false,
			required : true,
			prompt : '城市',
			idField : 'city',
			textField : 'cityName',
			fitColumns : true,
			editable : true,
			columns : [ [ {
				field : 'cityName',
				title : '地区名称',
				width : '100%'
			}, {
				field : 'catgName',
				title : '地区全名',
				hidden : true
			}, {
				field : 'province',
				title : '省',
				hidden : true
			}, {
				field : 'city',
				title : '城市',
				hidden : true
			}, {
				field : 'area',
				title : '地区',
				hidden : true
			} ] ],
			onSelect : function(record) {
				var pcg = $('#city').combogrid('grid');
				var row = pcg.datagrid('getSelected');
				citySelected(row.province, row.city);
			}
		});
	}

	function citySelected(province, city) {
		$.post("areaInfo/getareas", {
			proviceId : province,
			cityId : city
		}, function(data) {
			var data = eval('(' + data + ')');
			if (!$.isEmptyObject(data)) {
				$("#area").combogrid(
						{
							panelWidth : 200,
							url : 'areaInfo/getareas?proviceId=' + province
									+ '&cityId=' + city,
							method : 'post',
							required : true,
							multiple : false,
							prompt : '城市',
							idField : 'area',
							textField : 'cityName',
							fitColumns : true,
							editable : true,
							columns : [ [ {
								field : 'cityName',
								title : '地区名称',
								width : '100%'
							}, {
								field : 'catgName',
								title : '地区全名',
								hidden : true
							}, {
								field : 'province',
								title : '省',
								hidden : true
							}, {
								field : 'city',
								title : '城市',
								hidden : true
							}, {
								field : 'area',
								title : '地区',
								hidden : true
							} ] ]
						});
			}
		})
	}

	/**
	 *保存
	 */
	function saveItem(editForm, editWindow, itemGrid) {
		console.info('save');
		$('#' + editForm).form('submit', {
			url : 'fruit_company/save',
			onSubmit : function(param) {
				var b = $(this).form('enableValidation').form('validate');
				if(b){
					if($('#companyfieldid').combogrid('grid').datagrid('getSelected')==null){
						$.messager.alert('信息提示', "所属领域一定要选择才可以！", 'info');
						return false;
					}
					if($('#area').combogrid('grid').datagrid('getSelected')==null){
						$.messager.alert('信息提示', "区域一定要选择才可以！", 'info');
						return false;
					}
					if($('#city').combogrid('grid').datagrid('getSelected')==null){
						$.messager.alert('信息提示', "城市一定要选择才可以！", 'info');
						return false;
					}
					if($('#province').combogrid('grid').datagrid('getSelected')==null){
						$.messager.alert('信息提示', "省份一定要选择才可以！", 'info');
						return false;
					}
				}
				return b;
			},
			success : function(data) {
				var msg = eval('(' + data + ')');
				$.messager.alert('信息提示', msg.msg, 'info');
				if (msg.code == 1) {
					$('#' + editWindow).window('close');
					$('#' + itemGrid).datagrid('reload');
				}
			}
		});
	}
	 
</script>