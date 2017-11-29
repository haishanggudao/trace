<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>扫一扫</title>
<script type="text/javascript" src="../../static/js/jquery.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="../../static/css/Common.css?v=20151207">
<link rel="stylesheet" type="text/css"
	href="../../static/css/style.css?v=20160720-1">
<style type="text/css">
.tbtitle {
	font-size: 18px;
	font-weight: normal;
	color: #ff7603;
}
pre {
	 white-space: pre-wrap; /* css-3 */
	 white-space: -moz-pre-wrap; /* Mozilla, since 1999 */
	 white-space: -pre-wrap; /* Opera 4-6 */
	 white-space: -o-pre-wrap; /* Opera 7 */
	 word-wrap: break-word; /* Internet Explorer 5.5+ */ 
	 color: #222;
	 
}
.disfont{
	font:16px/30px "Microsoft Yahei", Tahoma, Helvetica, Arial, Verdana, "\5b8b\4f53", sans-serif;
}
</style>
<!-- 伸缩效果 -->
			<script type="text/javascript">
				$(function() {

					$('.tipsList .tipsL')
							.each(
									function() {
										var i = $(this).index();
										$(this)
												.find('.listTitle')
												.click(
														function() {
															if ($(this)
																	.hasClass(
																			'listactive')) {
																$(this)
																		.removeClass(
																				'listactive');
																$(this)
																		.next(
																				'.listCont')
																		.fadeOut();
															} else {
																$(this)
																		.eq(i)
																		.addClass(
																				'listactive')
																		.siblings()
																		.removeClass(
																				'listactive');
																$(this)
																		.next(
																				'.listCont')
																		.eq(i)
																		.fadeIn()
																		.siblings(
																				'.listCont')
																		.fadeOut();
															}
														})
									})
				})
			</script>
</head>
<body>
	<header id="header"> 国家质量追溯与防伪数据中心 </header>
	<div class="prouct-img">
		<img src="../../static/img/prouct_02.jpg" width="100%" alt=""
			id="headerImg">
	</div>

	<section>
		<c:if test="${not empty traceInfo.goodsInfo}">
			<div class="main-model ">
				<div class="clearfix">
					<div class="leftmain">
						<c:if test="${not empty traceInfo.goodsInfo.imgURL }">
							<img height="118" width="100%" class="imgp" alt=""
								src="${traceInfo.goodsInfo.imgURL }">
						</c:if>
						<span class="checked"> <img src="../../static/img/yinz.png"
							height="93" width="100%">
						</span>
					</div>
					<div class="rightmain">
						<h3 id="goodHeader"></h3>
						<c:if test="${not empty traceInfo.goodsInfo.productName }">
							<p class="prouct-title">${traceInfo.goodsInfo.productName}</p>
						</c:if>
					</div>
				</div>
				<div class="prouct-cont">
					<c:if test="${not empty traceInfo.goodsInfo.traceCount}">
						<span>
							<!-- 您所查看的商品溯源信息由政府指定的第三方追溯服务提供，这是本商品第${traceInfo.goodsInfo.traceCount}次查询 -->
						</span>
					</c:if>
				</div>
			</div>
		</c:if>
		<c:if test="${not empty traceInfo.goodsInfo}">
			<div class="main-model">
				<div class="gloab-title clearfix">
					<div class="cd-timeline-img cd-movie">
						<span class="icon iconfont">&#xe605;</span>
					</div>
					<span class="gont">商品信息</span>
				</div>
				<div class="cd-timeline-content">
					<ul>
						<c:if test="${not empty traceInfo.goodsInfo.productName}">
							<li style='color: #222'>商品名称:<span
								style='color: #666; word-break: break-all'>${traceInfo.goodsInfo.productName}</span></li>
						</c:if>
						<c:if test="${not empty traceInfo.goodsInfo.productCategoryName}">
							<li style='color: #222'>商品类型:<span
								style='color: #666; word-break: break-all'>${traceInfo.goodsInfo.productCategoryName}</span></li>
						</c:if>
						<c:if
							test="${traceInfo.company eq 'ebf2803bc67844efaae5a383769031c2'}">
							<!-- 临时使用，后期需要寻找更好的方法 zhangtao -->
							<c:if test="${not empty traceInfo.goodsInfo.standardFullName}">
								<li style='color: #222'>规格:<span
									style='color: #666; word-break: break-all'>${traceInfo.goodsInfo.standardFullName}</span></li>
							</c:if>
							<c:if test="${not empty traceInfo.goodsInfo.num}">
								<li style='color: #222'>数量:<span
									style='color: #666; word-break: break-all'>${traceInfo.goodsInfo.num}</span></li>
							</c:if>
						</c:if>
						<c:if test="${not empty traceInfo.goodsInfo.goodsBatch}">
							<li style='color: #222'>商品批次:<span
								style='color: #666; word-break: break-all'>${traceInfo.goodsInfo.goodsBatch}</span></li>
						</c:if>
						<c:if test="${not empty traceInfo.goodsInfo.areaName}">
							<li style='color: #222'>产地:<span
								style='color: #666; word-break: break-all'>${traceInfo.goodsInfo.areaName}</span></li>
						</c:if>
					<c:if test="${traceInfo.goodsInfo.productCategoryName eq '散装白酒'}">
						<c:if test="${not empty  traceInfo.outstockInfo}">
							<c:if test="${not empty  traceInfo.outstockInfo.deliveryTime}">
								<li style='color: #222'>开坛日期:<span
									style='color: #666; word-break: break-all'><fmt:formatDate
											pattern="yyyy-MM-dd"
											value="${traceInfo.outstockInfo.deliveryTime}" /></span>
								</li>
							</c:if>
						</c:if>
					</c:if>
						<li style='color: #222'>生产日期:<span style='color: #666; word-break: break-all'>2017-05-16</span></li>
						<c:if test="${not empty traceInfo.goodsInfo.secretKey}">
							<li style='color: #222'>生产批次: 
							<span style='color: #666; word-break: break-all'>${traceInfo.goodsInfo.secretKey }
							</span>
							</li>
						</c:if>
					
						<c:if test="${not empty traceInfo.goodsInfo.publicityImageUrl }">
							<img width="100%" src="${traceInfo.goodsInfo.publicityImageUrl }">
						</c:if>
					</ul>
				</div>
			</div>
		</c:if>

		<c:if test="${not empty traceInfo.instockInfo}">
			<c:if test="${not empty  traceInfo.instockInfo.supplier}">
				<div class="main-model">
					<div class="gloab-title clearfix">
						<div class="cd-timeline-img cd-movie">
							<span class="icon iconfont">&#xe600;</span>
						</div>
						<span class="gont">酒厂信息</span>
					</div>
					<div class="cd-timeline-content">
						<ul>
							<c:if
								test="${not empty  traceInfo.instockInfo.supplier.supplierAlias}">
								<li style='color: #222'>酒厂名称:<span
									style='color: #666; word-break: break-all'>${traceInfo.instockInfo.supplier.supplierAlias}</span></li>
							</c:if>
							<c:if test="${not empty  traceInfo.instockInfo.supplier.contact}">
								<li style='color: #222'>联系人:<span
									style='color: #666; word-break: break-all'>${traceInfo.instockInfo.supplier.contact}</span></li>
							</c:if>
							<c:if test="${not empty  traceInfo.instockInfo.supplier.tel}">
								<li style='color: #222'>电话:<span
									style='color: #666; word-break: break-all'>${traceInfo.instockInfo.supplier.tel}</span></li>
							</c:if>
							<c:if
								test="${not empty  traceInfo.instockInfo.supplier.supplierAddress}">
								<li style='color: #222'>地址:<span
									style='color: #666; word-break: break-all'>${traceInfo.instockInfo.supplier.supplierAddress}</span></li>
							</c:if>
							<c:if test="${not empty  traceInfo.instockInfo.supplier.supplierDesc}">
								<div class="tipsList">
									<div class="tipsL" style="word-break:break-all;">
										<p class="listTitle">简介</p>
										<div class="listCont"><pre>${traceInfo.instockInfo.supplier.supplierDesc}</pre></div>
									</div>
								</div>
							</c:if>
						</ul>
					</div>
				</div>
			</c:if>
		</c:if>
		<c:if test="${not empty traceInfo.yzInstockqc}">
			<div class="main-model">
				<div class="gloab-title clearfix">
					<div class="cd-timeline-img cd-check">
						<span class="icon iconfont">&#xe604;</span>
					</div>
					<span class="gont">检测信息</span>
				</div>
				<div class="cd-timeline-content">
					<ul>
						<c:forTokens items="${traceInfo.yzInstockqc.qcmaterialsURL }"
							var="url" delims=",">
							<c:if test="${not empty url}">
								<img src="${url}" width="100%">
							</c:if>
						</c:forTokens>
					</ul>
				</div>
			</div>
		</c:if>
		<c:if test="${not empty traceInfo.company}">
			<div class="main-model">
				<div class="gloab-title clearfix">
					<div class="cd-timeline-img cd-location">
						<span class="icon iconfont">&#xe601;</span>
					</div>
					<span class="gont">配送信息</span>
				</div>

				<div class="cd-timeline-content">
					<img src="../../static/images/yzps.jpg">
					<ul>
						<c:if test="${not empty  traceInfo.company.name}">
							<li style='color: #222'>配送中心名称:<span
								style='color: #666; word-break: break-all'>${traceInfo.company.name}</span></li>
						</c:if>
						<c:if test="${not empty  traceInfo.company.address}">
							<li style='color: #222'>地址:<span
								style='color: #666; word-break: break-all'>${traceInfo.company.address}</span></li>
						</c:if>
						<c:if test="${not empty  traceInfo.company.tel}">
							<li style='color: #222'>电话:<span
								style='color: #666; word-break: break-all'>${traceInfo.company.tel}</span></li>
						</c:if>
						<c:if test="${not empty traceInfo.instockInfo}">
							<li style='color: #222'>入库日期: <span
								style='color: #666; word-break: break-all'> <fmt:formatDate
										pattern="yyyy-MM-dd HH:mm:ss"
										value="${traceInfo.instockInfo.instockDate}" />
							</span>
							</li>
						</c:if>
					</ul>
				</div>
			</div>
		</c:if>
		<c:if test="${not empty traceInfo.storeSaleItem}">
			<div class="main-model">
				<div class="gloab-title clearfix">
					<div class="cd-timeline-img cd-end">
						<span class="icon iconfont">&#xe606;</span>
					</div>
					<span class="gont">零售门店</span>
				</div>
				<div class="cd-timeline-content">
					<ul>
						<c:if test="${not empty traceInfo.customers }">
							<c:if test="${not empty  traceInfo.customers.customerAlias}">
								<li style='color: #222'>门店名称:<span
									style='color: #666; word-break: break-all'>${traceInfo.customers.customerAlias }</span></li>
							</c:if>
							<c:if test="${not empty  traceInfo.customers.company}">
								<li style='color: #222'>地址:<span
									style='color: #666; word-break: break-all'>${traceInfo.customers.company.cbusinessaddress }</span></li>
							</c:if>
							<c:if test="${not empty  traceInfo.customers.tel}">
								<li style='color: #222'>电话:<span
									style='color: #666; word-break: break-all'>${traceInfo.customers.tel }</span></li>
							</c:if>
						</c:if>
						<c:if test="${traceInfo.goodsInfo.productCategoryName eq '散装白酒'}">
						<c:if test="${not empty  traceInfo.storeSaleItem.createTime}">
							<li style='color: #222'>销售时间:<span
								style='color: #666; word-break: break-all'> <fmt:formatDate
										pattern="yyyy-MM-dd HH:mm:ss"
										value="${traceInfo.storeSaleItem.createTime }"></fmt:formatDate></span>
							</li>
						</c:if>
						</c:if>

						<c:if test="${not empty  traceInfo.storeSaleItem.goodsTraceCode}">
							<li style='color: #222'>追溯码:<span
								style='color: #666; word-break: break-all'>
								<c:set var="tracecode" value="${traceInfo.storeSaleItem.goodsTraceCode }"></c:set>
									<c:choose>  
									    <c:when test="${fn:length(tracecode) > 20}">  
									        <c:out value="${fn:substring(tracecode, 0, 20)}" />  
									    </c:when>  
									   <c:otherwise>  
									      <c:out value="${tracecode}" />  
									    </c:otherwise>  
									</c:choose>
								</span></li>
						</c:if>
					</ul>
				</div>
			</div>
		</c:if>
		<div class="main-model">
			<!-- 消费提示 -->
			<div class="shopModel">
				<div class="shop-tips">
					<p>
						<b class="import-icon">提示:</b> 本产品为${traceInfo.goodsInfo.productCategoryName}，在特殊季节，酒精度可能发生变化，不影响饮用安全！
					</p>
				</div>

				<div class="tipsList">
					<div class="tipsL">
						<p class="listTitle">理性饮酒</p>
						<div class="listCont">
							<p>理性饮酒要遵守以下7点：</p>
							1.要适量饮酒<br> 2.要饮低度酒<br> 3.不要空腹饮酒<br> 4.饮酒时应吃脂肪与甜食<br>
							5.饮白酒不要喝碳酸饮料<br> 6.敬酒或饮酒要慢饮，不要急饮，同时应吃菜肴。<br>
							7.常饮白酒易引起VB1、VB6、叶酸、及镁、锌缺少，应合理补充。

							<p style="margin: 19px 0">尤其白酒酒精度较高，千万不能贪杯。否则，可能会引起酒精中毒。</p>

							<p>饮酒多少“量”为“适量”呢？</p>
							中国营养学会建议：成年男性每日饮用酒的酒精量不超过25克；成年女性一天饮用的酒精量不超过15克。

						</div>


						<p class="listTitle">假酒对人体的危害</p>
						<div class="listCont">
							假酒在生产过程中多含有高浓度的甲醇可至甲醇中毒。甲醇主要作用于神经系统，具有明显的麻醉作用，可引起脑水肿。对视神经和视网膜有特殊的选择作用，易引起视神经萎缩，导致双目失明，也可致代谢性酸中毒。一般来讲，摄入甲醇5~10毫升就可引起中毒，30毫升可致死。<br>

							<p>急性中毒</p>
							短时大量吸入出现轻度眼及上呼吸道刺激症状（口服有胃肠道刺激症状）；头痛、头晕、乏力、眩晕、酒醉感、意识朦胧、谵妄，甚至昏迷。视神经及视网膜病变，可有视物模糊、复视等，重者失明。代谢性酸中毒时出现二氧化碳结合力下降、呼吸加速等。
							<br>
							<p>慢性影响</p>
							神经衰弱综合征，植物神经神经功能失调，粘膜刺激，视力减退等。皮肤出现脱脂、皮炎等。


						</div>

						<!-- 							<p class="listTitle">教你如何鉴别真假酒</p> -->
						<!-- 							<div class="listCont"> -->
						<!-- 								假酒之所以有市场，部分原因在于消费者缺乏对真假酒的鉴别知识。 -->

						<!-- 								<p>普通消费者鉴别假酒主要有六种方法：</p> -->
						<!-- 								1.	看外包装是否已磨损。正牌名优酒直接从厂家发货，商标、包装比较新。<br> -->
						<!-- 								2.	看包装材质如何。档次较高的酒选用的包装材质一般比较好，而有的假冒酒所用的纸盒,标贴较差。<br> -->
						<!-- 								3.	看酒瓶封口工艺。真品酒封口工艺严实，假的粗糙松动。<br> -->
						<!-- 								4.	看商标、包装的印刷质量和技术。如字体大小、型号、笔画粗细等等。<br> -->
						<!-- 								5.	看酒体是否混浊。一般假酒大多是用低劣质量的酒配制而成，没有经过技术处理，酒质混浊。<br> -->
						<!-- 								6.	看价格。如果你所买的酒和市场价格差距太大，可能就有问题，别贪便宜上当。 -->



						<!-- 							</div> -->

						<p class="listTitle">白酒常识</p>
						<div class="listCont">
							白酒为中国特有的一种蒸馏酒，是世界六大蒸馏酒（白兰地Brandy、威士忌Whisky、伏特加Vodka、金酒Gin、朗姆酒Rum、中国白酒Spirit）之一，由淀粉或糖质原料制成酒醅或发酵后经蒸馏而得。<br>

							<b>白酒的香型</b><br>
							各类白酒利用各自特殊的微生物群体，配合着各自特殊的工艺操作，形成了各具特色、风格典型的香型白酒。到目前为止，已确立了十种香型白酒，分别为:<br>
							1. 酱香型：以贵州茅台酒、郎酒为典型代表。<br> 2. 浓香型：以四川泸州老窖特曲酒为典型代表。<br>
							3. 清香型：以山西省汾阳市杏花村的汾酒为典型代表。<br> 4.
							米香型：传统以桂林三花酒为代表，新时代米香型白酒以冰峪庄园大米原浆酒为代表。<br> 5.
							凤香型：以陕西省宝鸡市凤翔县的西凤酒为典型代表。<br> 6. 豉香型：以广东佛山的豉味玉冰烧为典型代表。<br>
							7. 药香型：以贵州遵义的董酒为典型代表。<br> 8. 兼香型：以安徽淮北市的口子窖为典型代表。<br>
							9. 芝麻香型：以山东省安丘市景芝镇的一品景芝为典型代表。<br> 10. 特型白酒：以江西省樟树镇的四特酒为典型代表。<br>

							<b>白酒的功效</b><br> 1.
							预防心血管病：少量饮用白酒，能够增加人体血液内的高密度脂蛋白，而高密度脂蛋白又能将可导致心血管病的低密度脂蛋白等，从血管和冠状动脉中转移，从而便可有效的减少冠状动脉内胆固醇沉积，预防心血管病的作用。
							<br> 2. 消除疲劳和紧张：少量饮用白酒，能够通过酒精对大脑和中枢神经的作用，起到消除疲劳，松弛神经的功效。 <br>
							3. 开胃消食：在进餐的同时，饮用少量的白酒，能够增进食欲，促进食物的消化，当然过多饮用会导致肠胃不适。<br> 4.
							驱除寒冷：白酒含有大量的热量，饮入人体后，这些热量会迅速被人体吸收。<br> 5.
							促进新陈代谢：白酒对于含有较多的酒精成分，且热量较高，因而能够促进人体的血液循环，对全身皮肤起到一定良性的刺激作用，从而还可以达到促进人体新陈代谢的作用。这种良性的刺激作用还能欧作用于神经传导，从而对于全身血液都能有一定良好的贯通作用。<br>
							6. 舒筋活血：白酒具有舒筋通络、活血化淤的功效。这一功效早已在我国民间得到了普遍的应用。<br> <b>白酒除了饮用外，还有其他功能：</b><br>
							1. 减痛：不慎将脚扭伤后，将温白酒涂于伤处轻揉，能舒筋活血，消除疼痛。<br> 2.
							去腥：手上沾有鱼虾腥味时，用少许清洗，即可去掉腥气味。<br> 3. 除腻：在烹调脂肪较多的肉类、鱼类时，加少许白酒，
							可使菜肴味道鲜美而不油腻。<br> 4. 消苦：剖鱼时若弄破苦胆，立即在鱼肚内抹一点白酒，然后用冷水冲洗，可消除苦味。<br>
							5. 减酸：烹调菜肴时，如果加醋过多，只要再往菜中倒些白酒，可减轻酸味。<br> 6.
							去泡：因长途行走或因劳动摩擦手脚起泡时，临睡前把白酒涂于起泡处，次日晨可去泡。<br> 7.
							增香：往醋中加几滴白酒和少许食盐，搅拌均匀，既能保持醋的酸味，又能增加醋香味。<br> <b>喝白酒要注意什么？</b><br>
							1.
							当血液中的乙醇浓度达到0.05%时，酒精的作用开始显露，出现兴奋和欣快感；当血中乙醇浓度达到0.1%时，人就会失去自制能力。如达到0.2%时，人已到了酩酊大醉的地步；达到0.4%时，人就可失去知觉，昏迷不醒，甚至有生命危险。<br>
							2.
							酒精对人的损害，最重要的是中枢神经系统。它使神经系统从兴奋到高度的抑制，严重地破坏神经系统的正常功能。过量的饮酒就是损害肝脏。慢性酒精中毒，则可导致酒精性肝硬化。<br>
							3.
							过量饮酒伤身，但是最伤身的是空腹饮酒。空腹饮酒会刺激胃黏膜，容易引起胃炎、胃溃疡等疾病。空腹饮酒还会引发低血糖，会导致我们体内葡萄糖供应不足，会出现心悸、头晕等现象。<br>
							4. 空腹时饮酒更容易患肝硬化，这与蛋白质摄入量不足更易使肝脏受损有关。<br> 5.
							饮白酒前后不能服用各种镇静类、降糖类、抗生素和抗结核类药物，否则会引起头痛、呕吐、腹泻、低血糖反应甚至死亡。<br> <b>白酒不能和什么一起吃？</b><br>
							1. 核桃和白酒：导致血热轻者燥咳，严重时会出鼻血。两者均属热性食物，同时食用易导致上火。<br> 2.
							柿子和白酒：易患结石。酒精能刺激胃肠道蠕动，并与柿子中的鞣酸反应生成柿石，导致肠道梗阻。<br> 3.
							海鲜和白酒：海鲜中含有大量的嘌呤醇，可诱发急性痛风，酒精有活血的作用，会使患痛风的几率加大。<br> 4.
							白酒和牛奶同食易得脂肪肝。<br> 5. 白酒和碳酸饮料同食易伤五脏。<br> 6.
							啤酒忌白酒：导致胃痉挛、急性胃肠炎、十二指肠炎等症，同时对心血管的危害也相当严重。啤酒中含有大量的二氧化碳，容易挥发，如果与白酒同饮，就会带动酒精渗透。有些朋友常常是先喝了啤酒再喝白酒，或是先喝白酒再喝啤酒，这样做实属不当。<br>

							<b>鉴别优质、劣质的白酒</b><br>
							若是无色透明玻璃瓶包装，把酒瓶拿在手中，慢慢地倒置过来，对着光观察瓶的底部，如果有下沉的物质或有云雾状现象，说明酒中杂质较多；如果酒液不失光、不浑浊，没有悬浮物，说明酒的质量比较好。从色泽上看，除酱香型酒外，一般白酒都应该是无色透明的。<br>
							另外，将酒瓶倒置，如果酒花分布均匀，上翻密度间隙明显，且消失缓慢，酒液清澈，则为优质酒；反之，酒花密集上翻，且立即消失，并有明显不均匀分布，酒液浑浊，即为劣质酒。<br>

							<b>白酒如何保存？</b><br>
							瓶装白酒应选择较为干燥、清洁、光亮和通风较好的地方，相对湿度在70%左右为宜，湿度较高瓶盖易腐烂。白酒贮存环境温度不宜超过30℃，严禁烟火靠近。容器封口要严密，防止漏酒和“跑度”。<br>

							购买白酒注意哪些？<br>
							应到正规的商店或专卖店购买，尽量选购品牌知名度较高的企业生产的产品，同时要索取相应的购买凭证(标明产品名称、生产日期或批号等)，以便发生纠纷时有维权证据。<br>





						</div>
					</div>

				</div>
			</div>
		</div>
					<div class="main-model">
				<div class="gloab-title clearfix">
					<div class="cd-timeline-img cd-location">
						<span class="icon iconfont">&#xe602;</span>
					</div>
					<span class="gont">公司信息</span>
				</div>

				<div class="cd-timeline-content">
					<ul>
						<li ><div class="disfont">上海市羽众酒业有限公司创建于2014年，以批发、零售散装白酒、散装黄酒、瓶装酒为主，是上海市知名的酒类批发企业。
						公司自创建以来，坚持优质优价，取信于民的理念，旗下加盟店遍布上海各地，迅速占领上海市场。
						公司发展至今，在散装酒市场积累了丰富的经验，创立了特有的连锁加盟经营模式，规范了营销网络，保障了各加盟门店的利益，推动了整个散装酒市场的良性发展。进入稳定发展期后，公司更加注重于品牌的整体规划与传播，逐步树立起行业典范的形象。
						公司以“让利于民、推动行业发展”为使命，以“传承文化、源于自然”公司文化为发展导向，奉行“合作共赢，生生不息”的企业精神，坚持为消费者提供好喝不贵、质优价廉的健康美酒。
						羽众酒业，与你共创美好生活！
						</div></li>
						<li><img src="../../static/images/yzgs.jpg"></li>
					</ul>
				</div>
			</div>
	</section>
	<div class="wecat">
		<img src="../../static/img/yzwecatimg.jpg?v=20151225" width="100%"
			alt="" id="footImg">
	</div>
	<footer>
		<span id="footer">技术支持：上海质尊溯源电子科技有限公司</span>
	</footer>
</body>
</html>