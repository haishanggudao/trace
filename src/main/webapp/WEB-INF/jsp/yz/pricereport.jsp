<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<link rel="stylesheet" type="text/css" href="static/css/datagrid_util.css">
<script type="text/javascript" src="static/echarts/echarts.js"></script>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center',border:false">
		<div id="pricereport-toolbar">
			<div class="wu-toolbar-button">
				<input id="queryyearmonth" class="easyui-combobox"
					data-options="
						valueField:'id',
						textField:'text',
						url:'pricereport/getyearmonth',
						onSelect:function(rec){
            				var url = 'pricereport/getgoodsbyyearmonth?yearmonth=' + rec.id + '&companyId=' + $('#index_user_companys').combobox('getValue');
            				$('#querygoods').combobox('reload', url);
            			},onBeforeLoad: function(param){param.companyId = $('#index_user_companys').combobox('getValue');}" /> 
            	<input id="querygoods" class="easyui-combobox"
					data-options="valueField:'id',textField:'text',url:'pricereport/getgoodsbyyearmonth'" />
				<a href="#" class="easyui-linkbutton" iconcls="icon-search" onclick="openselectingdialog_pricereport()">查看</a> 
				<a href="#" class="easyui-linkbutton" iconcls="icon-search" onclick="exportexcel_pricereport()">导出Excel</a>
			</div>
		</div>
		<div>
			<br />
			<div id="main1" style="width: 800px;height:350px;margin: 0 auto;"></div>
			<br />
			<div id="main2" style="width: 800px;height:350px;margin: 0 auto;"></div>
		</div>
	</div>
</div>
<script>
	
	$(doRender(null, null,null));

	function doRender(_month, _goods, _goodsname) {

		if (_month != '' && _month != null) {
			$.get('pricereport/getpricereport?month=' + _month + '&goods=' + _goods + '&companyId=' + $('#index_user_companys').combobox('getValue'), function(data) {
				data = JSON.parse(data);
				console.info(data);
				var xAxisDataInstock = eval('(' + data.xAxisDataInstock + ')');
				var seriesDataInstockCount = eval('(' + data.seriesDataInstockCount + ')');
				var seriesDataInstockPrice = eval('(' + data.seriesDataInstockPrice + ')');
				var seriesDataInstockSum = eval('(' + data.seriesDataInstockSum + ')');

				console.info(xAxisDataInstock);
				console.info(seriesDataInstockCount);
				console.info(seriesDataInstockPrice);
				console.info(seriesDataInstockSum);
				
				var xAxisDataOutstock = eval('(' + data.xAxisDataOutstock + ')');
				var seriesDataOutstockCount = eval('(' + data.seriesDataOutstockCount + ')');
				var seriesDataOutstockPrice = eval('(' + data.seriesDataOutstockPrice + ')');
				var seriesDataOutstockSum = eval('(' + data.seriesDataOutstockSum + ')');
				
				console.info(xAxisDataOutstock);
				console.info(seriesDataOutstockCount);
				console.info(seriesDataOutstockPrice);
				console.info(seriesDataOutstockSum);

				var goodsname = _goodsname;
				var month = _month;

				// 基于准备好的dom，初始化echarts实例
				var myChart1 = echarts.init(document.getElementById('main1'));
				var myChart2 = echarts.init(document.getElementById('main2'));

				// 指定图表的配置项和数据
				var option1 = {
					title : {
						text : goodsname + ' -- ' + month + ' [ 出库 ]'
					},
					tooltip : {},
					legend : {
						data : [ '数量', '价格', '总值' ]
					},
					xAxis : {
						data : xAxisDataInstock
					},
					yAxis : {},
					series : [ {
						name : '数量',
						type : 'bar',
						data : seriesDataInstockCount
					}, {
						name : '价格',
						type : 'bar',
						data : seriesDataInstockPrice
					}, {
						name : '总值',
						type : 'bar',
						data : seriesDataInstockSum
					} ]
				};
				
				var option2 = {
					title : {
						text : goodsname + ' -- ' + month + ' [入库]'
					},
					tooltip : {},
					legend : {
						data : [ '数量', '价格', '总值' ]
					},
					xAxis : {
						data : xAxisDataOutstock
					},
					yAxis : {},
					series : [ {
						name : '数量',
						type : 'bar',
						data : seriesDataOutstockCount
					}, {
						name : '价格',
						type : 'bar',
						data : seriesDataOutstockPrice
					}, {
						name : '总值',
						type : 'bar',
						data : seriesDataOutstockSum
					} ]
				};

				// 使用刚指定的配置项和数据显示图表。
				myChart1.setOption(option1);
				myChart2.setOption(option2);
			});
		}
	}

	function openselectingdialog_pricereport() {
		var yearmonth = $('#queryyearmonth').combobox('getValue');
		var goods = $('#querygoods').combobox('getValue');
		var goodsname = $('#querygoods').combobox('getText');
		if (yearmonth != '' && goods != '') {
			//do query
			doRender(yearmonth, goods, goodsname);
		} else {
			$.messager.alert('警告', '请选择一款产品', 'warning');
		}
	}

	function exportexcel_pricereport() {
		var yearmonth = $('#queryyearmonth').combobox('getValue');
		if (yearmonth != '') {
			//do query
			location.href = "pricereport/excelexport?yearmonth=" + yearmonth + "&companyid=" + $('#index_user_companys').combobox('getValue');
		} else {
			$.messager.alert('警告', '请选择月份', 'warning');
		}
	}
</script>