<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
	/**
	 * 处理分页数据
	 */
	function doUserPaging(pageNum, pageSize) {
		//发送请求信息
		window.location.href = "${pageUtil.linkAddr}?opertype=paging&pageNum=" + pageNum + "&pageSize=" + pageSize;
	}
</script>
<div class="page mt10" style="text-align: right;">
	<div class="pagination">
		<ul>
			<c:choose>
				<c:when test="${pageUtil.hasPrevious}">
					<li class="first-child">
						<a href="javascript:void(0);" onclick="doUserPaging('${pageUtil.firstPageNum}','${pageUtil.pageSize}');">首页</a>
					</li>
					<li>
						<a href="javascript:void(0);" onclick="doUserPaging('${pageUtil.previousPageNum}','${pageUtil.pageSize}');">上一页</a>
					</li>
				</c:when>
				<c:otherwise>
					<li class="first-child disabled">
						<span>首页</span>
					</li>
					<li class="disabled">
						<span>上一页</span>
					</li>
				</c:otherwise>
			</c:choose>
			<c:forEach begin="${pageUtil.everyPageStart}" end="${pageUtil.everyPageEnd}" var="temp">
				<c:choose>
					<c:when test="${pageUtil.pageNum==temp}">
						<li class="active">
							<a href="javascript:void(0);">${temp }</a>
						</li>
					</c:when>
					<c:otherwise>
						<li>
							<a href="javascript:void(0);" onclick="doUserPaging('${temp}','${pageUtil.pageSize}');">${temp }</a>
						</li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:choose>
				<c:when test="${pageUtil.hasNext}">
					<li>
						<a href="javascript:void(0);" onclick="doUserPaging('${pageUtil.nextPageNum}','${pageUtil.pageSize}');">下一页</a>
					</li>
					<li class="last-child">
						<a href="javascript:void(0);" onclick="doUserPaging('${pageUtil.lastPageNum}','${pageUtil.pageSize}');">尾页</a>
					</li>
				</c:when>
				<c:otherwise>
					<li class="disabled">
						<span>下一页</span>
					</li>
					<li class="last-child disabled">
						<span>尾页</span>
					</li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
</div>