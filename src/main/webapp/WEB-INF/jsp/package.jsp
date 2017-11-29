<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
 <link rel="stylesheet" type="text/css" href="static/css/datagrid_util.css">
<!-- begin of bar -->
<div id="packageBar" style="padding: 5px 0;">
	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add-s1'" onclick="openPackageDialog()">新增包装</a>
	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit-s1'" onclick="editPackageDialgo()">编辑包装</a>
	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-delete-s1'" onclick="delPackage();">删除包装</a>
</div>
<!-- end of bar -->

<!-- begin of list -->
<table id="packageList" title="包装绑定信息列表" style="width: auto; height: 350px">
</table>
<!-- end of list -->

<!-- begin of dialog -->
<div id="addPackageDialog" style="width: 475px; padding: 10px;" >
	<form id="packageForm"  method="post">
		<div   style="margin-left: 10px;">
			<input type="hidden" id="packageMainId_package" name="packageMainId" >
			<div class="ftitle" style="width:420px;">包装信息</div>
			 <div class="fitem">
			          <label class="fitemlabel width_70">标签编码：</label>
			          <input class="easyui-textbox" id="packageCode_package" name="packageCode" style="width: 120px" />&nbsp;
			          <label class="fitemlabel width_70">包装时间：</label>
			          <input type="text" id="packageTime_package" name="packageTime" style="width:150px" />&nbsp;
			 </div>
			 <div class="fitem">
			          <label class="fitemlabel width_70">包装类型：</label>
			          <input id="packageType_package" name="packageType"  style="width: 120px;" />&nbsp;
			          <label class="fitemlabel width_70">&nbsp;父 包 装：</label>
			          <input id="parentPackageMainId_package" name="parentPackageMainId" style="width: 150px;"  />
			          
			 </div>	
		 </div>
		 
		  <div  style="margin-left: 10px;">
		 
				<table id="packageItemTable" class="easyui-datagrid" title="包装明细" style="width:420px;height:265px"
					data-options="
		                iconCls: 'icon-edit',
		                singleSelect: true,
		                toolbar: '#tb_package',
		                onClickCell: onClickCell_package,
		                onEndEdit: onEndEdit_package,
		                url: 'package/findAllItems',
		             	onBeforeLoad: function(param){ 
							param.packageMainId = $('#packageMainId_package').val();
		             }">
					<thead>
					<tr>
						<th data-options="field:'goodsDetailId',width:150,
							formatter:function(value,row){
		                            return row.goodsName;
		                   	},
		                   	editor:{
		                   		type:'combobox',
		                   		options:{
		                   			url : 'goods/getGoods',
									valueField : 'goodsId',
									textField : 'goodsName',
									required:true,
		                            editable:false 
		                   		}
		                   	}">商品</th> 
					</tr>
					</thead>
				</table>
		</div>
		<div id="tb_package" style="height:auto">
        	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="appendPackageItem()">新增</a>
        	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removePackageItem()">删除</a>
    	</div>
    	<div style="margin: 10px 10px;">
			<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
				style="width: 420px; height: 32px;" onclick="submitPackageMain()">确认提交</a>
		</div>
	</form>
</div>
<!-- end of dialog -->

<script type="text/javascript">
var initFlag_package=0;

var editIndex = undefined;

$(function(){
	loadPackageLists(); 
});

//***** begin of function-UI *****
/**
 * function-UI => load lists
 */
function loadPackageLists(){
	/*<shiro:hasPermission name="base:product_category:update">*/
	$('#packageList').datagrid({
		onDblClickCell: function(index,field,value){
			editPackageDialgo()
		}
	});
	/*</shiro:hasPermission>*/
	$('#packageList').datagrid({
		url : 'package/list',
		rownumbers : true,
		pageSize : 10,
		pageList : [ 10, 20, 50 ],
		pagination : true,
		singleSelect : true,
		multiSort : true,
		fitColumns : true,
		fit : true,
		toolbar : '#packageBar',
		onBeforeLoad : function(param) {
		},
		columns : [ [ 
		 	{ field : 'packageMainId', title : '包装ID', hidden: true }, 
		 	{ field : 'operator', title : '操作者', hidden: true }, 
		 	{ field : 'parentPackageMainId', title : '父包装ID', hidden: true }, 
			{ field : 'packageCode', title : '包装编码', width : 100 },
			{ field : 'packageTime', title : '包装时间', width : 100 },
			{ field : 'packageType', title : '包装类型', width : 100,
				formatter : function(val, row, index) {
					if (val === '0') {
						return '商品关联';
					} else if ( val === '1' ) {
						return '包装关联';
					}		
				}
			}, 
			{ field : 'parentPackageCode', title : '父包装编码', width : 100},
			{ field : 'username', title : '操作者', width : 100 ,
				formatter : function(val, row, index) {
					return row.user.username;
				}
			}
		] ],
		view: detailview,
		detailFormatter:function(index,row){
            return '<div style="padding:2px"><table class="ddv"></table></div>';
        },
        onExpandRow: function(index,row){
            var ddv = $(this).datagrid('getRowDetail',index).find('table.ddv');
            ddv.datagrid({
                url:'package/findAllItems?packageMainId='+row.packageMainId,
                fitColumns:true,
                singleSelect:true,
                rownumbers:true,
                loadMsg:'',
                height:'auto',
                columns:[[
                    { field:'goodsDetailId', title:'商品', width:200,
                    	formatter:function(value,row){
                    		return row.goodsName;
                    	}	
                    } 
                ]],
                onResize:function(){
                    $('#packageList').datagrid('fixDetailRowHeight',index);
                },
                onLoadSuccess:function(){
                    setTimeout(function(){
                        $('#packageList').datagrid('fixDetailRowHeight',index);
                    },0);
                }
            });
            $('#packageList').datagrid('fixDetailRowHeight',index);
        }
	});
}

/**
 * function-UI => initialize the form
 */
function initializePackageForm(){ 
	
	// 包装时间
	$('#packageTime_package').datetimebox({ 
		showSeconds: true
	});
	
	// 包装类型
	$('#packageType_package').combobox({  
		required:true,
		valueField: 'value',
		textField: 'label',
		editable:false,
		data: [
			{ label: '商品关联', value: '0' },
			{ label: '包装关联', value: '1' } 
			]
	}); 
	
	// 父包装
	$('#parentPackageMainId_package').combobox({ 
		url : 'package/findParentalPackageMains',
		valueField : 'packageMainId',
		textField : 'packageCode',
		editable:false,
		loadFilter : function(data){
		       var opts = $(this).combobox('options');
		       var emptyRow = {};
		       emptyRow[opts.valueField] = '';
		       emptyRow[opts.textField] = '空';
		       data.unshift(emptyRow);
		       return data;
		}
	}); 
}

/**
 * function-UI =>  clear the datagrid of items
 */
function clearPackageItemsDataGrid(){
	var rows = $('#packageItemTable').datagrid('getRows');
	for(var i = rows.length - 1; i> -1; i--){
		$('#packageItemTable').datagrid('deleteRow', i);
	} 
}
//***** end of function-UI *****

// ***** begin of function *****
/**
 * action => submit the main package 
 */
function submitPackageMain(){

	// call function =>  endEditing_package
	endEditing_package();
	
	//获取更新更改的行的集合  
    var myRows = $("#packageItemTable").datagrid('getRows'); 
	
	$('#packageForm').form('submit', {
		url : 'package/addPackage', 
		onSubmit : function(param) {
			param.packageItems = JSON.stringify(myRows);
			// console.info(param); 
			return $(this).form('enableValidation').form('validate');
		},
		success : function(data) {
			var msg = eval('(' + data + ')');
			$.messager.alert('信息提示', msg.msg, 'info');
			if (msg.code == 1) {
				$('#addPackageDialog').dialog('close');
				$('#packageList').datagrid('reload');
			}
		}
	});
}

/**
 * function => end editing
 */
function endEditing_package(){
    if (editIndex == undefined){return true}
    if ($('#packageItemTable').datagrid('validateRow', editIndex)){
        $('#packageItemTable').datagrid('endEdit', editIndex);
        editIndex = undefined;
        return true;
    } else {
        return false;
    }
}
// ***** end of function *****

// ***** begin of event *****
/**
 * event => cell onClick 
 */
function onClickCell_package(index, field){
    if (editIndex != index){
        if (endEditing_package()){
            $('#packageItemTable').datagrid('selectRow', index)
                    .datagrid('beginEdit', index);
            var ed = $('#packageItemTable').datagrid('getEditor', {index:index,field:field});
            if (ed){
                ($(ed.target).data('textbox') ? $(ed.target).textbox('textbox') : $(ed.target)).focus();
            }
            editIndex = index;
        } else {
            setTimeout(function(){
                $('#packageItemTable').datagrid('selectRow', editIndex);
            },0);
        }
    }
}
 

/**
 * event => row onEndEdit
 */
function onEndEdit_package(index, row){
    var ed = $(this).datagrid('getEditor', {
        index: index,
        field: 'goodsDetailId'
    });
    // console.info(row);
    row.goodsName = $(ed.target).combobox('getText');
     
}   
// ***** end of event *****

// ***** begin of button menu *****
/**
 * button-menu => open the adding dialog
 */
function openPackageDialog(){
	if(initFlag_package==0){
		// call UI: initialize the dialog for adding
		initializePackageForm();
		initFlag_package=1;
	} 
	
	$("#packageForm").form('clear')
						.form('disableValidation');
	
	// call function: clear the datagrid of items
	clearPackageItemsDataGrid();
	
	var dt = new Date(); 
	var time = dt.getFullYear() + '-' + (dt.getMonth()+1) + '-' + dt.getDate() + ' ' +
    			dt.getHours() + ':' + dt.getMinutes()+':'+ dt.getSeconds(); 
    // console.info('* time is [' +time+ ']');
    $('#packageTime_package').datetimebox('setValue', time);
	
	$('#packageType_package').combobox('setValue', '0');
	
	$('#addPackageDialog').dialog({
		closed : false,
		modal : true,
		title : "新增包装",
		iconCls : 'icon-document'
	});
}
 
/**
 * button-menu => open the editing dialog
 */
function editPackageDialgo(){
	var row = $('#packageList').datagrid('getSelected'); 
	
	if(row){
		$('#packageForm').form('clear').form('disableValidation');  
		
		if(initFlag_package==0){
			// call: initialize the dialog for adding
			initializePackageForm();
			initFlag_package=1;
		}
		 
		$('#packageForm').form('load', {
			packageMainId : row.packageMainId,
			packageCode : row.packageCode,
			packageTime : row.packageTime,
			packageType : row.packageType,
			parentPackageMainId : row.parentPackageMainId,
			operator : row.operator
		}); 
		
		$('#packageItemTable').datagrid('reload');    // reload the current page data 
		
		$('#addPackageDialog').dialog({
			closed : false,
			modal : true,
			title : "编辑包装装信息",
			iconCls : 'icon-document'
		});
		 
	} else {
		$.messager.alert('信息提示', '请选择一行记录！', 'info');
	}
}  

/**
 * button-menu => delete the package 
 */
function delPackage(){
	var row = $('#packageList').datagrid('getSelected');
	if (row) {
		$.messager.confirm('删除包装绑定信息', '确认删除?', function(r) {
			if (r) {
				$.post("package/delPackage", {
					packageMainId : row.packageMainId
				}, function(data) {
					var msg = eval('(' + data + ')');
					$.messager.alert('信息提示', msg.msg, 'info');
					if (msg.code == 1) {
						$('#packageList').datagrid('reload');
					}
				})
			}
		});
	} else {
		$.messager.alert('信息提示', '请选择一行记录！', 'info');
	}
} 

/**
 * button-menu => append the new row
 */
function appendPackageItem(){
	if (endEditing_package()){
		$('#packageItemTable').datagrid('appendRow',{ });
       	editIndex = $('#packageItemTable').datagrid('getRows').length-1;
       	$('#packageItemTable').datagrid('selectRow', editIndex)
       							.datagrid('beginEdit', editIndex);
	}
}

/**
 * button-menu => remove the row
 */
function removePackageItem(){
    if (editIndex == undefined){return}
    $('#packageItemTable').datagrid('cancelEdit', editIndex)
    						.datagrid('deleteRow', editIndex);
    editIndex = undefined;
} 
// ***** end of button menu *****

</script>