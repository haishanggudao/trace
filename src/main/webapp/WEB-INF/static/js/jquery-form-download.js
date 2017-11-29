function formDownLoad(url,key,value){
	var form=$("<form>");//定义一个form表单
	form.attr("style","display:none");
	form.attr("target","");
	form.attr("method","post");
	form.attr("action",url);
	var input1=$("<input>");
	input1.attr("type","hidden");
	input1.attr("name",key);
	input1.attr("value",value);
	form.append(input1);
	form.appendTo('body').submit().remove();//表单先添加，提交后删除
}

function formDownLoadByJson(url,jsonMap){
	var form=$("<form>");//定义一个form表单
	form.attr("style","display:none");
	form.attr("target","");
	form.attr("method","post");
	form.attr("action",url);
	for(key in jsonMap){
		var input1=$("<input>");
		input1.attr("type","hidden");
		input1.attr("name",key);
		input1.attr("value",jsonMap[key]);
		form.append(input1);
	}
	form.appendTo('body').submit().remove();//表单先添加，提交后删除
}