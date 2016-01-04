/**
 * 查看checkbox有多少个被选中
 * @param {Object} ckId
 */
function getCheckCount(ckName) {
	//定义计数器
	var count = 0;
	//获取checkbox对象
	var objs = document.getElementsByName(ckName);
	//遍历并计数
	for ( var i = 0; i < objs.length; i++) {
		if (objs[i].checked) {
			count++;
		}
	}
	return count;
}

/**
 * 获取checkbox选中的值
 * @return {TypeName} 
 */
function getCheckValue(ckName) {
	//定义变量存放选择Value
	var str = "";
	//获取checkbox对象
	var objs = document.getElementsByName(ckName);
	//遍历并计数
	for ( var i = 0; i < objs.length; i++) {
		if (objs[i].checked) {
			str += "," + objs[i].value
		}
	}
	if (str.length == 0) {
		return str;
	} else {
		return str.substring(1);
	}
}