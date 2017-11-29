<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>扫一扫</title>
<script type="text/javascript" src="../../static/js/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="../../static/css/Common.css?v=20151207">
<link rel="stylesheet" type="text/css" href="../../static/css/style.css?v=20160401">
<style type="text/css">
.tbtitle {
	font-size: 18px;
	font-weight: normal;
	color: #ff7603;
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
	<header id="header">
		<div style="height: 100%;">
		<img height="100%" style="vertical-align: middle;padding: 1px"   src="../../static/img/fruit_log.png">
		<span>上海市果品行业协会</span>
		</div> 
	</header>
	<div class="prouct-img">
		<img src="../../static/img/prouct_02.jpg" width="100%" alt="" id="headerImg">
	</div>

	<section>
		<c:if test="${not empty traceInfo.goodsInfo}">
			<div class="main-model ">
				<div class="clearfix">
					<div class="leftmain">
						<c:if test="${not empty traceInfo.goodsInfo.imgURL }">
							<img height="118" width="100%" class="imgp" alt="" src="${traceInfo.goodsInfo.imgURL }"> 
						</c:if>
						<span class="checked"> <img src="../../static/img/yinz.png" height="93"
							width="100%">
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
							您所查看的商品溯源信息由政府指定的第三方追溯服务提供，这是本商品第${traceInfo.goodsInfo.traceCount}次查询
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
						<li style='color:#222'>商品名称:<span style='color:#666;word-break: break-all'>${traceInfo.goodsInfo.productName}</span></li>
					</c:if>
					<c:if test="${not empty traceInfo.goodsInfo.productCategoryName}">
						<li style='color:#222'>商品类型:<span style='color:#666;word-break: break-all'>${traceInfo.goodsInfo.productCategoryName}</span></li>
					</c:if>
					<c:if test="${not empty traceInfo.goodsInfo.levelName}">
						<li style='color:#222'>商品等级:<span style='color:#666;word-break: break-all'>${traceInfo.goodsInfo.levelName}</span></li>
					</c:if>
					<c:if test="${not empty traceInfo.goodsInfo.productStandardName}">
						<li style='color:#222'>商品规格:<span style='color:#666;word-break: break-all'>${traceInfo.goodsInfo.productStandardName}</span></li>
					</c:if> 
					<c:if test="${not empty traceInfo.goodsInfo.goodsBatch}">
						<li style='color:#222'>商品批次:<span style='color:#666;word-break: break-all'>${traceInfo.goodsInfo.goodsBatch}</span></li>
					</c:if>
					<c:if test="${not empty traceInfo.goodsInfo.createTime}">
						<li style='color:#222'>包装时间:<span style='color:#666;word-break: break-all'>${traceInfo.goodsInfo.createTime}</span></li>
					</c:if>
					<c:if test="${not empty traceInfo.goodsInfo.shelfLife}">
						<li style='color:#222'>保质期:<span style='color:#666;word-break: break-all'>${traceInfo.goodsInfo.shelfLife}天</span></li>
					</c:if>
					<c:if test="${not empty traceInfo.goodsInfo.areaName}">
						<li style='color:#222'>产地:<span style='color:#666;word-break: break-all'>${traceInfo.goodsInfo.areaName}</span></li>
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
						<span class="gont">供应商信息</span>
					</div>
					<div class="cd-timeline-content">
						<ul>
							<c:if test="${not empty  traceInfo.instockInfo.supplier.supplierAlias}">
								<li style='color:#222'>供应商名称:<span style='color:#666;word-break: break-all'>${traceInfo.instockInfo.supplier.supplierAlias}</span></li>
							</c:if>
							<c:if test="${not empty  traceInfo.instockInfo.supplier.contact}">
								<li style='color:#222'>联系人:<span style='color:#666;word-break: break-all'>${traceInfo.instockInfo.supplier.contact}</span></li>
							</c:if>
							<c:if test="${not empty  traceInfo.instockInfo.supplier.tel}">
								<li style='color:#222'>电话:<span style='color:#666;word-break: break-all'>${traceInfo.instockInfo.supplier.tel}</span></li>
							</c:if>
							<c:if test="${not empty  traceInfo.instockInfo.supplier.supplierAddress}">
								<li style='color:#222'>供应商地址:<span style='color:#666;word-break: break-all'>${traceInfo.instockInfo.supplier.supplierAddress}</span></li>
							</c:if>
						</ul>
					</div>
				</div>
			</c:if>
		</c:if>
		<c:if test="${not empty traceInfo.instockInfo}">
			<div class="main-model">
			<div class="gloab-title clearfix">
				<div class="cd-timeline-img cd-movie">
					<span class="icon iconfont">&#xe602;</span>
				</div>
				<span class="gont">入库信息</span>
			</div>
			<div class="cd-timeline-content">
				<ul>
					<c:if test="${not empty  traceInfo.instockInfo.instockDate}">
						<li style='color:#222'>入库日期:
							<span style='color:#666;word-break: break-all'>
								<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${traceInfo.instockInfo.instockDate}"/>
							</span>
						</li>
					</c:if>
					<c:if test="${not empty  traceInfo.instockInfo.instockBatchNum}">
						<li style='color:#222'>入库批次号:<span style='color:#666;word-break: break-all'>${traceInfo.instockInfo.instockBatchNum}</span></li>
					</c:if>
					<c:if test="${not empty  traceInfo.instockInfo.consignee}">
						<li style='color:#222'>签收人:<span style='color:#666;word-break: break-all'>${traceInfo.instockInfo.consignee}</span></li>
					</c:if>
				</ul>
			</div>
			</div>
		</c:if>
		
		<c:if test="${not empty traceInfo.processMain}">
			<div class="main-model">
				<div class="gloab-title clearfix">
					<div class="cd-timeline-img cd-addgrund">
						<span class="icon iconfont">&#xe607;</span>
					</div>
					<span class="gont">加工信息</span>
				</div>
				<div class="cd-timeline-content">
					<ul>
						<c:if test="${not empty  traceInfo.processMain.processTime}">
							<li style='color:#222'>加工时间:<span style='color:#666;word-break: break-all'><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${traceInfo.processMain.processTime}"/></span></li>
						</c:if>
						<c:if test="${not empty  traceInfo.processMain.processBatch}">
							<li style='color:#222'>加工批次:<span style='color:#666;word-break: break-all'>${traceInfo.processMain.processBatch}</span></li>
						</c:if>
						<c:if test="${not empty  traceInfo.processMain.processQuantity}">
							<li style='color:#222'>加工数量:<span style='color:#666;word-break: break-all'>${traceInfo.processMain.processQuantity}</span></li>
						</c:if>
						<c:choose>
							<c:when test="${not empty traceInfo.processMain.processItems }">
								<div class='tbtitle' style='margin:20px 0;'>●加工原料</div>
								<c:forEach items="${traceInfo.processMain.processItems }" var="item" varStatus="index">
									<c:if test="${not empty  item.productName}">
										<li style='color:#222'>原料名称:<span style='color:#666;word-break: break-all'>${item.productName}</span></li>
									</c:if>
									<c:if test="${not empty  item.fullStandardName}">
										<li style='color:#222'>规格:<span style='color:#666;word-break: break-all'>${item.fullStandardName}</span></li>
									</c:if>
									<c:if test="${not empty  item.goodsBatch}">
										<li style='color:#222'>原料批次:<span style='color:#666;word-break: break-all'>${item.goodsBatch}</span></li>
									</c:if>
									<c:if test="${not empty  item.type}">
										<li style='color:#222'>类型:<span style='color:#666;word-break: break-all'>
										<c:choose>
											<c:when test="${item.type eq 0 }">主料</c:when>
											<c:when test="${item.type eq 1 }">辅料</c:when>
										</c:choose>
										</span></li>
									</c:if>
									<c:if test="${not empty  item.num}">
										<li style='color:#222'>数量:<span style='color:#666;word-break: break-all'>${item.num}</span></li>
									</c:if>
									<c:if test="${not index.last}">
										<div style='height: 1px;width: 100%;border-bottom: 1px dashed #222;margin:20px 0px;'></div>
									</c:if>
								</c:forEach>
							</c:when>
							<c:when test="${not empty traceInfo.processMain.goods }">
								<c:if test="${not empty traceInfo.processMain.goods.productName }">
									<li style='color:#222'>原料名称:<span style='color:#666;word-break: break-all'>${traceInfo.processMain.goods.productName}</span></li>
								</c:if>
								<c:if test="${not empty traceInfo.processMain.goods.productStandardDetail }">
									<li style='color:#222'>规格:<span style='color:#666;word-break: break-all'>${traceInfo.processMain.goods.productStandardDetail.fullStandardName}</span></li>
								</c:if>
								<c:if test="${not empty traceInfo.processMain.goods.goodsBatch }">
									<li style='color:#222'>原料批次:<span style='color:#666;word-break: break-all'>${traceInfo.processMain.goods.goodsBatch}</span></li>
								</c:if>
							</c:when>
						</c:choose>
					</ul>
				</div>
			</div>
		</c:if>
		<c:if test="${not empty traceInfo.goodsQC}">
			<div class="main-model">
				<div class="gloab-title clearfix">
					<div class="cd-timeline-img cd-check">
						<span class="icon iconfont">&#xe604;</span>
					</div>
					<span class="gont">检测信息</span>
				</div>
				<div class="cd-timeline-content">
					<ul>
						<c:if test="${not empty  traceInfo.goodsQC.originNo}">
							<li style='color:#222'>产地证明编号:<span style='color:#666;word-break: break-all'>${traceInfo.goodsQC.originNo}</span></li>
						</c:if>
						<c:if test="${not empty  traceInfo.goodsQC.quarantineNo}">
							<li style='color:#222'>检验检疫证书编号:<span style='color:#666;word-break: break-all'>${traceInfo.goodsQC.quarantineNo}</span></li>
						</c:if>
						<c:if test="${not empty  traceInfo.goodsQC.inspection}">
							<li style='color:#222'>质量安全检测:<span style='color:#666;word-break: break-all'>${traceInfo.goodsQC.inspection}</span></li>
						</c:if>
						<c:if test="${not empty  traceInfo.goodsQC.inspectionDate}">
							<li style='color:#222'>检测日期:<span style='color:#666;word-break: break-all'>${traceInfo.goodsQC.inspectionDate}</span></li>
						</c:if>
						<c:if test="${not empty  traceInfo.goodsQC.sampleName}">
							<li style='color:#222'>样品名称:<span style='color:#666;word-break: break-all'>${traceInfo.goodsQC.sampleName}</span></li>
						</c:if>
						<c:if test="${not empty  traceInfo.goodsQC.result}">
							<li style='color:#222'>检测结果:<span style='color:#666;word-break: break-all'>${traceInfo.goodsQC.result}</span></li>
						</c:if>
					</ul>
				</div>
			</div>
		</c:if>
		<c:if test="${not empty traceInfo.outstockInfo}">
			<div class="main-model">
				<div class="gloab-title clearfix">
					<div class="cd-timeline-img cd-location">
						<span class="icon iconfont">&#xe601;</span>
					</div>
					<span class="gont">配送信息</span>
				</div>
				<div class="cd-timeline-content">
					<ul>
						<c:if test="${not empty  traceInfo.outstockInfo.name}">
							<li style='color:#222'>物流企业:<span style='color:#666;word-break: break-all'>${traceInfo.outstockInfo.name}</span></li>
						</c:if>
						<c:if test="${not empty  traceInfo.outstockInfo.contact}">
							<li style='color:#222'>联系人:<span style='color:#666;word-break: break-all'>${traceInfo.outstockInfo.contact}</span></li>
						</c:if>
						<c:if test="${not empty  traceInfo.outstockInfo.tel}">
							<li style='color:#222'>电话:<span style='color:#666;word-break: break-all'>${traceInfo.outstockInfo.tel}</span></li>
						</c:if>
						<c:if test="${not empty  traceInfo.outstockInfo.outstockDate}">
							<li style='color:#222'>出库日期:<span style='color:#666;word-break: break-all'><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${traceInfo.outstockInfo.outstockDate}"/></span></li>
						</c:if>
						<c:if test="${not empty  traceInfo.outstockInfo.deliveryTime}">
							<li style='color:#222'>送货日期:<span style='color:#666;word-break: break-all'><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${traceInfo.outstockInfo.deliveryTime }"/> </span></li>
						</c:if>
					</ul>
				</div>
			</div>
		</c:if>
		<c:if test="${not empty traceInfo.company}">
			<div class="main-model">
				<div class="gloab-title clearfix">
					<div class="cd-timeline-img cd-end">
						<span class="icon iconfont">&#xe606;</span>
					</div>
					<span class="gont">批发配送企业</span>
				</div>
				<div class="cd-timeline-content">
					<ul>
						<c:if test="${not empty  traceInfo.company.name}">
							<li style='color:#222'>批发配送企业:<span style='color:#666;word-break: break-all'>${traceInfo.company.name }</span></li>
						</c:if>
						<c:if test="${not empty  traceInfo.company.contact}">
							<li style='color:#222'>联系人:<span style='color:#666;word-break: break-all'>${traceInfo.company.contact }</span></li>
						</c:if>
						<c:if test="${not empty  traceInfo.company.tel}">
							<li style='color:#222'>电话:<span style='color:#666;word-break: break-all'>${traceInfo.company.tel }</span></li>
						</c:if>
						<c:if test="${not empty  traceInfo.company.address}">
							<li style='color:#222'>地址:<span style='color:#666;word-break: break-all'>${traceInfo.company.address }</span></li>
						</c:if>
						<c:if test="${not empty  traceInfo.company.code}">
							<li style='color:#222'>追溯码:<span style='color:#666;word-break: break-all'>${traceInfo.company.code }</span></li>
						</c:if>
						<c:if test="${not empty  traceInfo.company.presentation}">
							<div class="tipsList">
								<div class="tipsL" style="word-break:break-all;">
									<p class="listTitle">简介</p>
									<div class="listCont"><pre>${traceInfo.company.presentation}</pre></div>
								</div>
							</div>
						</c:if>
					</ul>
				</div>
			</div>
		</c:if>
	</section>
	<c:if test="${not empty traceInfo.goodsInfo.publicityImageUrl }">
		<div class="wecat">
		<img width="100%" alt="" src="${traceInfo.goodsInfo.publicityImageUrl }">
	</div>
	</c:if>
	<div class="wecat">
		<img src="../../static/img/wecatimg.jpg?v=20151225" width="100%" alt=""
			id="footImg">
	</div>
	<footer>
		<span id="footer">技术支持：上海质尊溯源电子科技有限公司</span>
	</footer>
</body>
</html>