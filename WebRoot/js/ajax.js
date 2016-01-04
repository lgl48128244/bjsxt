/**
 * 创建Ajax引擎对象
 * @return {TypeName} 
 */
function createRequest() {
	var request;
	if (window.XMLHttpRequest) {
		request = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		request = new ActiveXObject("Msxml2.XMLHTTP");
	}
	if (request == null) {
		alert("XMLHttpRequest对象创建失败");
	}
	return request;
}
/**
 * 发送Ajax请求
 * @param {Object} method 请求方式
 * @param {Object} uri 资源路径
 * @param {Object} async 同步异步
 * @param {Object} param 请求参数
 * @param {Object} resp200 状态200的处理函数
 * @param {Object} resp404 状态404的处理函数
 * @param {Object} resp500 状态500的处理函数
 */
function lkgfdsatyuiosendAjax(method, uri, async, param, resp200, resp404, resp500) {
	//创建对象
	var request = createRequest();
	//监听响应信息
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				resp200(request);
			} else if (request.status == 404) {
				resp404(request);
			} else if (request.status == 500) {
				resp500(request);
			} else {
				alert("找不到指定的响应状态" + request.status);
			}
		}
	}
	//初始化请求参数,并发送请求
	if (method.toUpperCase() == "GET") {
		//设置请求参数Get	
		request.open("get", uri + ((param != null && param.length > 0) ? ("?" + param) : ""), async);
		request.send(null);
	} else if (method.toUpperCase() == "POST") {
		//设置请求参数Post
		request.open("post", uri, async);
		request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		request.send(param);
	} else {
		alert("SendAjax找不到指定的请求方式[" + method + "]");
	}
}
/**
 * 发送Ajax请求
 * @param {Object} method 请求方式
 * @param {Object} uri 资源路径
 * @param {Object} async 同步异步
 * @param {Object} param 请求参数
 * @param {Object} success 状态200的处理函数
 * @param {Object} resp404 状态404的处理函数
 * @param {Object} resp500 状态500的处理函数
 */
function sendAjax(arg) {
	//创建对象
	var request = createRequest();
	//监听响应信息
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				arg.success(request.responseText);
			} else if (request.status == 404) {
				arg.resp404(request);
			} else if (request.status == 500) {
				arg.resp500(request);
			} else {
				alert("找不到指定的响应状态" + request.status);
			}
		}
	}
	//设置同步或者异步的默认值
	if (typeof arg.async == "undefined") {
		arg.async = true;
	}
	//初始化请求参数,并发送请求
	if (arg.method.toUpperCase() == "GET") {
		//设置请求参数Get	
		request.open("get", arg.uri + ((arg.param != null && arg.param.length > 0) ? ("?" + arg.param) : ""), arg.async);
		request.send(null);
	} else if (arg.method.toUpperCase() == "POST") {
		//设置请求参数Post
		request.open("post", arg.uri, arg.async);
		request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		request.send(arg.param);
	} else {
		alert("SendAjax找不到指定的请求方式[" + arg.method + "]");
	}
}
/**
 * 发送Get方式请求
 * @param {Object} uri
 * @param {Object} param
 * @param {Object} success
 */
function sendGet(uri, param, success) {
	sendAjax( {
		method : "get",
		uri : uri,
		param : param,
		success : success
	});
}
/**
 * 发送Post方式请求
 * @param {Object} uri
 * @param {Object} param
 * @param {Object} success
 */
function sendPost(uri, param, success) {
	sendAjax( {
		method : "post",
		uri : uri,
		param : param,
		success : success
	});
}
