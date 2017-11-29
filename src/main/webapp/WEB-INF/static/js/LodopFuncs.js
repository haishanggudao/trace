// ====页面引用CLodop云打印必须的JS文件：====
	var head = document.head || document.getElementsByTagName("head")[0]
			|| document.documentElement;
	var oscript = document.createElement("script");
	oscript.src = "http://localhost:8000/CLodopfuncs.js?priority=1";
	head.insertBefore(oscript, head.firstChild);

	// 引用双端口(8000和18000）避免其中某个被占用：
	oscript = document.createElement("script");
	oscript.src = "http://localhost:18000/CLodopfuncs.js?priority=0";
	head.insertBefore(oscript, head.firstChild);

// ====获取LODOP对象的主过程：====
function getLodop() {
	var LODOP;
	try {
		var isIE = (navigator.userAgent.indexOf('MSIE') >= 0)
				|| (navigator.userAgent.indexOf('Trident') >= 0);

		try {
			LODOP = getCLodop();
		} catch (err) {
		}
		if (!LODOP && document.readyState !== "complete") {
			alert("C-Lodop没准备好，请稍后再试！");
			return;
		}
		return LODOP;
	} catch (err) {
		alert("getLodop出错:" + err);
	}
}
