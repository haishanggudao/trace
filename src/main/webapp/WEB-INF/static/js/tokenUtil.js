function createToken(){
	//创建token
	$.ajax({
		type : 'post',
		url : 'token/create',
		dataType : "json",
		contentType : "application/json",
		success : function(data) {
			if (data.code == 1) {
				$('#index_token').val(data.msg)
			}
		}
	});
}
