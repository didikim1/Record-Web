<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<c:set var="_url" value="${pageContext.request.contextPath == '/' ? '' : pageContext.request.contextPath }" scope="application"/>
<div id="layout_content_popup_sub" style="width: 750px">
	<div class="content">
		<div class="border_sub">
				<div align="center" style="height:500px; overflow:auto">
					<table class="wtable" style="margin: 0">
						<c:forEach var="rs" items="${Data}">
							<tr>
								<th scope="col" width="120px">${rs.key}</th>
								<td>${rs.value}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
		</div>
	</div>
</div>
