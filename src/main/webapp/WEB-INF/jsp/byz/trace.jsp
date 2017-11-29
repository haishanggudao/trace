<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title><c:if test="${not empty traceInfo.goodsInfo.productName }">${traceInfo.goodsInfo.productName} - </c:if>国家质量追溯与防伪数据中心</title>
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
</style>
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
				<%-- 
				<div class="prouct-cont">
					<c:if test="${not empty traceInfo.goodsInfo.traceCount}">
						<span>
							您所查看的商品溯源信息由政府指定的第三方追溯服务提供，这是本商品第${traceInfo.goodsInfo.traceCount}次查询
						</span>
					</c:if>
				</div>
				 --%>
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
						<c:if test="${not empty traceInfo.goodsInfo.areaName}">
							<li style='color: #222'>原产地:<span
								style='color: #666; word-break: break-all'>${traceInfo.goodsInfo.areaName}</span></li>
						</c:if>
						
							<li style="color:#222">原材料:<span style="color:#666;word-break: break-all">水，高粱，小麦</span></li>
						
						<c:if test="${not empty traceInfo.goodsInfo.standardFullName}">
							<li style='color: #222'>净含量:<span
								style='color: #666; word-break: break-all'>${traceInfo.goodsInfo.standardFullName}</span></li>
						</c:if>
						<li style="color:#222">官方网站:<a style="color:#218f49;word-break: break-all; margin-left:10px" href="http://www.baoyuezui.com">http://www.baoyuezui.com</a></li>
						<li style="color:#222">
			              <c:if test="${not empty traceInfo.goodsInfo.publicityImageUrl }">
							<img width="100%" src="${traceInfo.goodsInfo.publicityImageUrl }">
						  </c:if>					
						</li>
					</ul>
				</div>
			</div>
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
								<li style="color:#222"><img src="${url}" width="100%"></li>
							</c:if>
						</c:forTokens>
					</ul>
				</div>
			</div>
		</c:if>
		
		<div class="main-model">
			<div class="gloab-title clearfix">
				<div class="cd-timeline-img cd-trace">
				</div>
				<span class="gont">溯源过程</span>
			</div>
			<c:choose>
			<c:when test="${traceInfo.goodsInfo.productName == '抱月醉-清风高粱酒'}">
				<div class="cd-timeline-content">
				<ul>
					<li style="color:#222"><img src="../../static/img/byz/trace_1.jpg" /></li>
					<li style="color:#222"><img src="../../static/img/byz/trace_2.jpg" /></li>
					<li style="color:#222"><img src="../../static/img/byz/trace2_3.jpg" /></li>
					<li style="color:#222"><img src="../../static/img/byz/trace2_4.jpg" /></li>
				</ul>
			</div>
			</c:when>
			 <c:otherwise>
				 <div class="cd-timeline-content">
					<ul>
						<li style="color:#222"><img src="../../static/img/byz/trace_1.jpg" /></li>
						<li style="color:#222"><img src="../../static/img/byz/trace_2.jpg" /></li>
						<li style="color:#222"><img src="../../static/img/byz/trace_3.jpg" /></li>
						<li style="color:#222"><img src="../../static/img/byz/trace_4.jpg" /></li>
						<li style="color:#222"><img src="../../static/img/byz/trace_5.jpg" /></li>
					</ul>
				</div>
			 </c:otherwise>
			</c:choose>
		</div>
	</section>
	<div class="wecat">
		<img src="../../static/img/wecatimg.jpg?v=20151225" width="100%"
			alt="" id="footImg">
	</div>
	<footer>
		<span id="footer">技术支持：上海质尊溯源电子科技有限公司</span>
	</footer>
</body>
</html>