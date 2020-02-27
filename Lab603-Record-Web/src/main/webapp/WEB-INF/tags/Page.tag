<%@ tag language="java" pageEncoding="UTF-8"%><%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="pageing" type="egovframework.pagination.PaginationInfo" %>
<%@ attribute name="formName"%>
<script type="text/javascript">
function goPage(page) {
    $("[name=${formName}]").find("[name=page]").val(page);
    $("[name=${formName}]").submit();
};
</script>
<div align="center">
	  <ul class="pagination pagination-sm">
		  <c:choose>
		   	<c:when test="${pageing.currentPageNo == 1}">
		   		<li><span aria-hidden="true" title="첫 페이지 이동"><strong>&nbsp;&nbsp;<i class="fa fa-angle-double-left line" aria-hidden="true"></i>&nbsp;&nbsp;</strong></span></li>
		   		<li><span aria-hidden="true"><strong>Prev</strong></span></li>
		   	</c:when>
		   	<c:otherwise>
		   		<li>
				   	<a href="javascript:goPage('1');" aria-label="Next" >
				   		<span aria-hidden="true" title="첫 페이지 이동"><strong>&nbsp;&nbsp;<i class="fa fa-angle-double-left line" aria-hidden="true"></i>&nbsp;&nbsp;</strong></span>
				   	</a>
				</li>
		   		<li><a href="javascript:goPage('${pageing.currentPageNo-1}');"  aria-label="Previous"><span aria-hidden="true" title="이전 페이지 이동"><strong>Prev</strong></span></a></li>
		   	</c:otherwise>
		  </c:choose>

		   <c:forEach var="i" begin="${pageing.firstPageNoOnPageList}" end="${pageing.lastPageNoOnPageList}" step="1">
		   		<c:if test="${ pageing.totalPageCount ge i }">
					<c:choose>
						<c:when test="${i == pageing.currentPageNo}">
							 <li class="active"><a href="#"><strong>${i}</strong></a></li>
						</c:when>
						<c:otherwise>
							<li><a href="javascript:goPage('${i}');"><strong>${i}</strong></a></li><li>
						</c:otherwise>
					</c:choose>
				</c:if>
			</c:forEach>

		    <c:choose>
		    	<c:when test="${pageing.currentPageNo < pageing.totalPageCount}">
		    		<li><a href="javascript:goPage('${pageing.currentPageNo+1}');" aria-label="Next" ><span aria-hidden="true" title="다음 페이지 이동"><strong>Next</strong></span></a></li>
		    		<li>
					   	<a href="javascript:goPage('${pageing.totalPageCount}');" aria-label="Next">
							<span aria-hidden="true" title="마지막 페이지 이동"><strong>&nbsp;&nbsp;<i class="fa fa-angle-double-right line" aria-hidden="true"></i>&nbsp;&nbsp;</strong></span>
					   	</a>
					</li>
		    	</c:when>
		    	<c:otherwise>
		    		<li><span aria-hidden="true" title="다음 페이지 이동"><strong>Next</strong></span></li>
		    		<li><span aria-hidden="true" title="마지막 페이지 이동"><strong>&nbsp;&nbsp;<i class="fa fa-angle-double-right line" aria-hidden="true"></i>&nbsp;&nbsp;</strong></span></li>
		    	</c:otherwise>
		    </c:choose>
	  </ul>
</div>