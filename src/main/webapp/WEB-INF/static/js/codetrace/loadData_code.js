		function search(){
			var code=$("#search").val();
			if(!code){
				return;
			}
			var reqIp=getReqIP();
			var url="http://"+reqIp+"/UserWebService.asmx/GetTraceListByTraceCode";
			$.ajax({
				url:url,
				type:'post',
				data:{traceCode:code,typeID:null,code:'os741258963'},
				dataType: 'jsonp',
				jsonp:'jsonp',
				success:function(data){
					var msg=data.msg;
					if(msg.Result==1){
						if(msg.Value!=null&&msg.Value!=""){
							var dataObj=msg.Value.Data;
							var goodsInfo=dataObj.GoodsBatchInfo
							if(goodsInfo){
								$("#pname").text(goodsInfo.GoodsName);
								if(!checkReturnData(goodsInfo.ImageURL)){
									$("#pImg").attr("src",goodsInfo.ImageURL);
								}
							}
							$("#count").text(dataObj.TraceCodeQueryCount);
							var convertObj=null;
							var displayObj={MaterialName:'原料名称',DetailAddress:'产地',Name:'供应商名称',LinkMan:'联系人',LinkTelephone:'电话',Address:'地址'};
							displayInfo(displayObj,'headInfo',dataObj.OriginInfo);
							displayObj={GoodsName:'成品名称',ShelfLife:'保质期',Name:'加工企业名称',LinkMan:'联系人',LinkTelephone:'电话',DetailAddress:'地址',ProcessDate:'加工日期'};
							convertObj={ProcessDate:{dateFormat:'yyyy-MM-dd'}};
							displayInfo(displayObj,'processInfo',dataObj.ProcessInfo,convertObj);
							displayObj={InspectionCertificationNo:'检验合格证号',InspectionResult:'检测结果'};
							convertObj={InspectionResult:{value:{1:'合格',2:'不合格'}}};
							displayInfo(displayObj,'checkInfo',dataObj.GoodsInspectionInfo,convertObj);
							displayObj={Name:'物流企业名称',LinkMan:'联系人',LinkTelephone:'电话',Address:'地址',LogisticsDate:'物流收货时间'};
							displayInfo(displayObj,'passInfo',dataObj.DeliveryInfo);
							displayObj={Name:'终端名称',LinkMan:'联系人',LinkTelephone:'电话',Address:'地址',ReceiveDate:'收货时间',TraceCode:'追溯码'};
							displayInfo(displayObj,'zeroInfo',dataObj.RetailInfo);
							$("#noResult").hide();
							$("#hasResult").show();
						}
					}else{
						$("#hasResult").hide();
						$("#noResult").show();
					}
				}
			});
		}
		function displayInfo(displayObj,divId,data,convertObj){
			if(data){
				$("#"+divId+" ul").empty();
				for(var key in displayObj){
					if(!checkReturnData(data[key])){
						if(checkPhoneType(displayObj[key])){
							//添加电话或手机号的链接，可以直接在手机上拨号
							var a=$("<a style='color:#218f49;'></a>").attr("href","tel:"+data[key]).text(data[key]);
							var li=$("<li style='color:#222'></li>");
							var span=$("<span style='color:#666;word-break: break-all'></span>");
							span.append(a);
							li.append(displayObj[key]+":");
							li.append(span);
							$("#"+divId+" ul").append(li);
						}else{
							var dataVal=data[key];
							if(convertObj){
								for(var convertKey in convertObj){
									if(key==convertKey){
										//格式化显示值
										if(convertObj[convertKey].value){
											dataVal=(convertObj[convertKey].value)[data[key]];
										}
										//格式化日期
										if(convertObj[convertKey].dateFormat){
											var date=new Date(data[key]);
											dataVal=date.format(convertObj[convertKey].dateFormat);
										}
										break;
									}
								}
								
							}
							$("#"+divId+" ul").append("<li style='color:#222'>"+displayObj[key]+":<span style='color:#666;word-break: break-all'>"+dataVal+"</span></li>");
						}
					}
				}
				if($("#"+divId+" ul li").length>0){
					$("#"+divId).parent().show();
				}
			}
			
		}

		function checkPhoneType(key){
			var phoneArray=["电话","联系电话","联系方式","手机"];
			return $.inArray(key,phoneArray)!=-1;
		}

		function getReqIP(){
			var reqIp=null;
			if(location.hostname.substring(0,7)=='192.168'){
				reqIp='192.168.8.26:8004';
			}else if(location.hostname==''||location.hostname=='localhost'){
				reqIp='192.168.8.26:8004';
			}else{
				reqIp='27.115.49.12:8004';
			}
        //    reqIp='192.168.8.26:8004';
			return reqIp;
		}


