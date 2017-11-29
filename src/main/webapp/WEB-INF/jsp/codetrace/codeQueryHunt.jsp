<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<div id="codeQueryHuntBar" style="padding: 5px 0;">
    &nbsp;&nbsp;二维码:&nbsp;
    <input type="text" class="easyui-textbox" id="qr_code" style="min-width: 150px;"/>&nbsp;
            查询时间:&nbsp;
    <input id="qr_checkDate_s" class="easyui-datebox" data-options="prompt:'起始时间'" />
    -
    <input id="qr_checkDate_e" class="easyui-datebox" data-options="prompt:'结束时间'" />&nbsp;
    IP地址:&nbsp;
    <input type="text" class="easyui-textbox" id="ip_addr" style="min-width: 150px;"/>&nbsp;
    <a href="#" class="easyui-linkbutton" iconcls="icon-search" onclick="dohunt()">搜索</a>
</div>
<table id="huntcodequerylogs" title="二维码追溯信息搜索" style="width: auto; height: 350px">
</table>
<script type="text/javascript">

    $(function() {
	    loadFindAllQuerylogs();
    });
    
	function dohunt() {
		//var qrcode = $('#qr_code').combobox('getText');
		var qrcode = document.getElementById('qr_code').value;
		var qrcheckbegin = $('#qr_checkDate_s').datebox('getValue');
		var qrcheckend = $('#qr_checkDate_e').datebox('getValue');
		//var ipaddress = $('#ip_addr').combobox('getText');
		var ipaddress = document.getElementById('ip_addr').value;
		$('#huntcodequerylogs').datagrid({
				url : 'codequery/findallquerylogs',
				queryParams : {
					qrcode : qrcode,
					checkDate1 : qrcheckbegin,
					checkDate2 : qrcheckend,
					ipaddr:ipaddress
				}
		});
	}
    
    
	function loadFindAllQuerylogs() {
		$('#huntcodequerylogs').datagrid(
				{
					url : 'codequery/findallquerylogs',
					rownumbers : true,
					pageSize : 20,
					pageList : [ 10, 20, 50 ],
					pagination : true,
					singleSelect : true,
					multiSort : true,
					fitColumns : true,
					fit : true,
					toolbar : '#codeQueryHuntBar',
					onBeforeLoad : function(param) {
						param.companyId = $("#index_user_companys").combobox(
								'getValue');
					},
					columns : [ [
							{
								field : 'qrcode',
								title : '二维码',
								width : '25%',
								align : 'center'
							},{
								field : 'totalTimes',
								title : '查询次数',
								width : '25%',
								align : 'center'
							},{
							    field : 'minDate',
								title : '起始日期',
								width : '25%',
								    formatter : function(val, row, index) {
									    if (val != '') {
										    var d = new Date(val);
										    console.info(d);
											return d.format("yyyy-MM-dd");
										}
										return '';
									},
								align : 'center'
							},{
							    field : 'maxDate',
								title : '截止日期',
								width : '25%',
								    formatter : function(val, row, index) {
									    if (val != '') {
										    var d = new Date(val);
										    console.info(d);
											return d.format("yyyy-MM-dd");
										}
										return '';
									},
								align : 'center'
							}] ],
					view: detailview,
					detailFormatter:function(index,row){
					    return '<div style="padding:2px"><table class="ddv"></table></div>';
					},
					onExpandRow: function(index,row){
					    var ddv = $(this).datagrid('getRowDetail',index).find('table.ddv');
					    ddv.datagrid({
					        url:'codequery/findallquerydetails',
					        queryParams : {
								qrcode : row.qrcode,
								checkDate1 : $('#qr_checkDate_s').datebox('getValue'),
								checkDate2 : $('#qr_checkDate_e').datebox('getValue'),
								ipaddr : $('#ip_addr').val()
							},
						    fitColumns : true,
						    singleSelect : true,
						    rownumbers : true,
						    pagination : true,
						    pageSize : 10,
						    pageList : [ 10, 20, 30 ],
						    loadMsg : '页面正在加载....',
						    height : 'auto',
					        columns:[[
							    {
								    field : 'ipaddr',
									title : 'IP地址',
									width : '200px',
									align : 'center'
								},{
								    field : 'checkDate',
									title : '查询时间',
									width : '200px',
									    formatter : function(val, row, index) {
										    if (val != '') {
											    var d = new Date(val);
											    console.info(d);
												return d.format("yyyy-MM-dd hh:mm:ss");
											}
											return '';
										},
									align : 'center'
								}
					        ]],
		                    onResize:function(){
		                        $('#huntcodequerylogs').datagrid('fixDetailRowHeight',index);
		                    },
		                    onLoadSuccess:function(){
		                        setTimeout(function(){
		                            $('#huntcodequerylogs').datagrid('fixDetailRowHeight',index);
		                        },0);
		                    }
		                });
					    $('#huntcodequerylogs').datagrid('fixDetailRowHeight',index);        
				    }
				});
	}
</script>
