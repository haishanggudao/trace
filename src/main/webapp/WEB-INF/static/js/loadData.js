		var dataObj;
		$(function(){
			var code=getQueryString('c');
			console.info(code);
			var url="../ws/getTraceInfo/"+code;
			$.ajax({
				url:url,
				type:'post',
				success:function(data){
					data=JSON.parse(data);
					if(data.code==1){
						var entity=data.baseEntity;
						if(entity.goodsInfo){
							$("#pname").text(entity.goodsInfo.productName);
							$("#pImg").attr('src',entity.goodsInfo.imgURL);
							var displayObj={'productName':'商品名称','standardFullName':'规格','goodsBatch':'商品批次','areaName':'产地'};
							displayTraceData(displayObj, entity.goodsInfo, "goodsInfo");
							
							displayObj={'originNo':'产地证明编号','quarantineNo':'检验检疫证书编号','inspection':'质量安全检测','inspectionDate':'检测日期','sampleName':'样品名称','result':'检测结果'};
							displayTraceData(displayObj, entity.goodsQC, "checkInfo");
							displayProcessInfo(entity.processMain,"processInfo");
							
							displayObj={'name':'物流企业','contact':'联系人','tel':'电话','outstockDate':'出库日期','updateTime':'送货日期'};
							displayTraceData(displayObj, entity.outstockInfo, "passInfo");
							
							displayObj={'slaughterhouseName':'屠宰场名称','contact':'联系人','altContact':'联系人别名','tel':'电话','slaughterhouseAddress':'屠宰场地址'};
							displayTraceData(displayObj, entity.slaughterhouse, "killInfo");
							
							displayObj={'instockDate':'入库日期','instockBatchNum':'入库批次号'};
							displayTraceData(displayObj, entity.instockInfo, "headInfo");
							if(entity.instockInfo){
								displayObj={'supplierAlias':'供应商名称','contact':'联系人','tel':'电话','supplierAddress':'供应商地址'};
								displayTraceData(displayObj, entity.instockInfo.supplier, "supplierInfo");
							}
							
							displayObj={'name':'零售企业','contact':'联系人','tel':'电话','address':'地址','code':'追溯码'};
							displayTraceData(displayObj, entity.company, "zeroInfo");
						}
					}
				}
			});
		});

		function displayProcessInfo(sourceData,divId){
			if(sourceData){
				var displayObj={'processTime':'加工时间','processBatch':'加工批次','processQuantity':'加工数量'};
				for(var key in displayObj){
					var dataVal=sourceData[key];
					if(dataVal){
						$("#"+divId+" ul").append("<li style='color:#222'>"+displayObj[key]+":<span style='color:#666;word-break: break-all'>"+dataVal+"</span></li>");
					}
				}
				$("#"+divId+" ul").append("<div class='tbtitle' style='margin:20px 0;'>●加工原料</div>");
				var items=sourceData.processItems;
				var itemObj={'productName':'原料名称','standardFullName':'规格','itmeBatch':'原料批次','typeName':'类型','num':'数量'};
				for(var i=0;i<items.length;i++){
					var processItem=items[i];
					for(var key in itemObj){
						if(processItem[key]){
							$("#"+divId+" ul").append("<li style='color:#222'>"+itemObj[key]+":<span style='color:#666;word-break: break-all'>"+processItem[key]+"</span></li>");
						}
					}
					if(i<items.length-1){
						$("#"+divId+" ul").append("<div style='height: 1px;width: 100%;border-bottom: 1px dashed #222;margin:20px 0px;'></div>");
					}
				}
				if($("#"+divId+" ul li").length>0){
					$("#"+divId).parent().show();
				}
			}
		}
		function displayTraceData(displayObj,sourceData,divId){
			if(sourceData){
				for(var key in displayObj){
					var dataVal=sourceData[key];
					if(checkPhoneType(displayObj[key])&&dataVal){
						//添加电话或手机号的链接，可以直接在手机上拨号
						var a=$("<a style='color:#218f49;'></a>").attr("href","tel:"+dataVal).text(dataVal);
						var li=$("<li style='color:#222'></li>");
						var span=$("<span style='color:#666;word-break: break-all'></span>");
						span.append(a);
						li.append(displayObj[key]+":");
						li.append(span);
						$("#"+divId+" ul").append(li);
					}else{
						if(dataVal){
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


		function changeLanguage(obj){
			$(".cd-timeline-content ul").empty();
			reloadGoodInfo($(obj).val());
			loadJsonData(dataObj,$(obj).val());
			reloadPageHeaderAndFooter($(obj).val());
		}

		function reloadGoodInfo(language){
			var goodsMetadata={
				goodName:{'zh-cn':'[商品名称]','en-us':'[Good Name]'},
				countWordPrev:{
					'zh-cn':'您所查看的商品溯源信息由政府指定的第三方追溯服务提供，这是本商品第',
					'en-us':'This is Test'},
					countWordNext:{
						'zh-cn':'次查询!',
						'en-us':'count Query'
					}
				};
				$("#goodHeader").text(goodsMetadata.goodName[language]);
				$("#count").text(goodsMetadata['countWordPrev'][language]+dataObj.TraceCodeQueryCount+goodsMetadata['countWordNext'][language]);
			}

			function reloadPageHeaderAndFooter(language){
				var pageMetadata={
					header:{'zh-cn':'国家质量追溯与防伪数据中心','en-us':'Shang Hai Food'},
					headImg:{'zh-cn':'img/prouct_02.jpg','en-us':'img/wecatimg.jpg'},
					footer:{'zh-cn':'技术支持：上海质尊溯源电子科技有限公司'},
					footImg:{'zh-cn':'img/wecatimg.jpg','en-us':'img/prouct_02.jpg'}
				};
				$("#header").text(pageMetadata.header[language]);
				if(pageMetadata.headImg[language]){
					$("#headerImg").attr('src',pageMetadata.headImg[language]);
				}
				$("#footer").text(pageMetadata.footer[language]);
				if(pageMetadata.footImg[language]){
					$("#footImg").attr('src',pageMetadata.footImg[language]);
				}
			}



