<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<link rel="stylesheet" type="text/css"
	href="static/css/datagrid_util.css">
<div id="myCompanyItemBtns" style="padding: 5px 0;">
	<shiro:hasPermission name="system:mycompany:edit">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit-s1'" onclick="myCompanyEditItem()">编辑企业</a>
	</shiro:hasPermission>
</div>
<table id="myCompanyItemsGrid" title="公司列表" style="width: 100%;"></table>
<div id="myCompanyDialog" class="easyui-dialog" title="新增"
	data-options="modal:true,closed:true,iconCls:'icon-document',buttons:'#dlg_myCompanyInfo-buttons'"
	style="width: 650px; padding: 20px;">
	<form id="myCompanyItemForm" method="post">
		<!-- 企业id -->
		<input type="hidden" name="companyid" />
		<input type="hidden" name="orgcode" />
		<input type="hidden" name="code" />
		<div class="easyui-tabs" id="myCompanyProductImgTabs"
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
						id="mycompanyfieldid" name="companyfieldid" style="width: 180px;"></select>
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
						id="mycompanyprovince" name="province" class="easyui-combogrid"
						style="width: 135px;"></select> <label class="fitemlabel width_25">市:</label>
					<select id="mycompanycity" name="city" class="easyui-combogrid"
						style="width: 135px;"></select> <label class="fitemlabel width_25">区:</label>
					<select id="mycompanyarea" name="area" class="easyui-combogrid"
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
					<label class="fitemlabel width_100">企业性质:</label> <input
						class="easyui-combobox" name="cnature" style="width: 180px;"
						data-options="prompt:'企业性质',valueField:'varName',textField:'varValue',url:'supplier/getcnature'">&nbsp;
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
						class="fitemlabel width_100">酒类经营许可:</label> <input
						class="easyui-textbox" name="cliquorbusinesslicense"
						style="width: 180px;" data-options="prompt:'酒类经营许可证'">&nbsp;
				</div>
				<div class="fitem">
					<label class="fitemlabel width_100">有效日期:</label> <input
						class="easyui-datebox" name="ceffectivedate1"
						id='myCompany_ceffectivedate1' style="width: 180px;"
						data-options="prompt:'起始日期'">&nbsp;--&nbsp;<input
						class="easyui-datebox" name="ceffectivedate2"
						id='myCompany_ceffectivedate2' style="width: 180px;"
						data-options="prompt:'结束日期'">&nbsp;
				</div>
			</div>
		</div>
	</form>
</div>
<div id="dlg_myCompanyInfo-buttons" style="text-align: center;">
	<a href="javascript:void(0)" class="easyui-linkbutton"
		onclick="mycompanysaveItem('myCompanyItemForm','myCompanyDialog','myCompanyItemsGrid')"
		style="width: 120px; height: 32px">确认提交</a>
</div>
<script type="text/javascript">
	//刷新企业信息
	function reloadMyCompany() {
		$('#myCompanyItemsGrid').datagrid({  
		    url:'mycompany/findAllList',  
		    queryParams:{
		    	companyid : $('#index_user_companys').combobox('getValue')
		    }  
		});
	}
	
	$(document).ready(function() {
		$('#myCompanytagsid').tabs('select',0);
		loadMyCompanySelectData();
		<shiro:hasPermission name="system:mycompany:edit">
		 $('#myCompanyItemsGrid').datagrid({
			onDblClickCell: function(index,field,value){
				myCompanyEditItem()
			}
		 });
		</shiro:hasPermission>
		$('#myCompanyItemsGrid').datagrid({
			url : 'mycompany/findAllList',  
		    queryParams:{
		    	companyid : $('#index_user_companys').combobox('getValue')
		    },
			rownumbers : true ,
			pagination : false ,
			pageSize : 10,
			pageList : [ 10, 20, 30 ] ,
			singleSelect : true ,
			fitColumns : true ,
			fit : true ,
			toolbar : '#myCompanyItemBtns',
			columns : [ [ {
				field : 'name',
				title : '企业名称',
				width : '9%'
			}, {
				field : 'shortname',
				title : '企业简称',
				width : '9%'
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
	});

	/**
	 *打开修改用户窗口
	 */
	function myCompanyEditItem() {
		$('#myCompanytagsid').tabs('select',0);
		var row = $('#myCompanyItemsGrid').datagrid('getSelected');
		if (row) {
			$("#myCompanyItemForm").form('clear').form('disableValidation');
			try {
				myCompanyProvinceSelected(row.province);
				myCompanyCitySelected(row.province, row.city);
				console.info(row);
				setTimeout(function() {
					$('#myCompanyItemForm').form('load', {
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
						chygienelicense : row.chygienelicense
					});
				}, 500);
				$("#mycompanyfieldid").combogrid("setValues", row.companyfieldid);
				if(row.ceffectivedate1 != undefined && row.ceffectivedate1 != '') {
					var date = new Date(row.ceffectivedate1);
					$('#myCompany_ceffectivedate1').datebox('setValue',date.format("yyyy-MM-dd"));
				}
				if(row.ceffectivedate2 != undefined && row.ceffectivedate2 != '') {
					var date = new Date(row.ceffectivedate2);
					$('#myCompany_ceffectivedate2').datebox('setValue',date.format("yyyy-MM-dd"));
				}
				$('#myCompanyDialog').dialog({
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

	function loadMyCompanySelectData() {
		$("#mycompanyfieldid").combogrid({
			panelWidth : 200,
			url : 'mycompany/getcompanyfields',
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
		$("#mycompanyprovince").combogrid({
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
				var pcg = $('#mycompanyprovince').combogrid('grid');
				var row = pcg.datagrid('getSelected');
				try {
					myCompanyProvinceSelected(row.province);
				} catch (ex) {
				}
			}
		});
		

	}

	function myCompanyProvinceSelected(province) {
		$("#mycompanycity").combogrid({
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
				var pcg = $('#mycompanycity').combogrid('grid');
				var row = pcg.datagrid('getSelected');
				myCompanyCitySelected(row.province, row.city);
			}
		});
	}

	function myCompanyCitySelected(province, city) {
		$.post("areaInfo/getareas", {
			proviceId : province,
			cityId : city
		}, function(data) {
			var data = eval('(' + data + ')');
			if (!$.isEmptyObject(data)) {
				$("#mycompanyarea").combogrid(
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
	function mycompanysaveItem(editForm, editWindow, itemGrid) {
		$('#' + editForm).form('submit', {
			url : 'mycompany/save',
			onSubmit : function(param) {
				var b = $(this).form('enableValidation').form('validate');
				if(b){
					if($('#mycompanyfieldid').combogrid('grid').datagrid('getSelected')==null){
						$.messager.alert('信息提示', "所属领域一定要选择才可以！", 'info');
						return false;
					}
					if($('#mycompanyarea').combogrid('grid').datagrid('getSelected')==null){
						$.messager.alert('信息提示', "区域一定要选择才可以！", 'info');
						return false;
					}
					if($('#mycompanycity').combogrid('grid').datagrid('getSelected')==null){
						$.messager.alert('信息提示', "城市一定要选择才可以！", 'info');
						return false;
					}
					if($('#mycompanyprovince').combogrid('grid').datagrid('getSelected')==null){
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