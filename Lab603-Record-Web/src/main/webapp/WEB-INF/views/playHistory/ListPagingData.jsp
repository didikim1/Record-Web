<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<c:set var="_url" value="${pageContext.request.contextPath == '/' ? '' : pageContext.request.contextPath }" scope="application"/>

<tag:layout>

   <div id="layout_content">
		<div class="content">

			<div class="border">
				<h1 class="tit01">검색조건</h1>
				<form name="playHistoryForm">
					<input type="hidden" name="excelResult"/>
					<table class="wtable_sub">
						<tr>
							<td>
								<div class="common_div left">아이디</div>
								<input type="text" class="common_input2 right" name="hdc" id="hdc" placeholder="아이디" value="${paramMap.hdc}" autocomplete="off"/>
								<div class="common_div left margin_l2">이름</div>
								<input type="text" class="common_input2 right" name="nm" id="nm" placeholder="이름" value="${paramMap.nm}" autocomplete="off"/>
								<button type="button" class="common_button2 margin_l2" onclick="pHistorySearch();"><i class="fa fa-search" aria-hidden="true"></i>&nbsp;검색</button>
								<button type="button" class="common_button2 margin_l3" onclick="resetButton();"><i class="fa fa-refresh" aria-hidden="true"></i>&nbsp;리셋</button>

								<c:choose>
									<c:when test="${paramMap.excelResult == 'ok'}"><button type="button" onclick="excelWrite();" class="common_button1 margin_l3"><i class="fa fa-file-excel-o" aria-hidden="true"></i>&nbsp;EXCEL 출력</button></c:when>
									<c:otherwise><button type="button" class="common_button1 margin_l3" onclick="excelWrite_Not();"><i class="fa fa-file-excel-o" aria-hidden="true" ></i>&nbsp;EXCEL 출력</button></c:otherwise>
								</c:choose>
							</td>
						</tr>
					</table>
				</form>
			</div>

			<table id="myTable" class="wtable tablesorter-blackice border table-hover">
				<thead>
					<tr>
						<th scope="col" width="10%"></th>
						<th scope="col" width="10%">아이디</th>
						<th scope="col" width="10%">권한</th>
						<th scope="col" width="10%">이름</th>
						<th scope="col" width="15%">소속</th>
						<th scope="col" width="15%">최근 접속일</th>
						<th scope="col" width="15%">최종 종료시간</th>
						<th scope="col" width="8%">재생이력</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="BOARD" items="${list}" varStatus="status">
						<tr>
							<td>${totalCount -((pageNUM -1) *paramMap.sel) - status.index}</td>
							<td>${BOARD.EMP_ID}</td>
							<td>
								<c:choose>
									<c:when test="${BOARD.BRANCH_CD == 'admin'}">ADMIN</c:when>
									<c:when test="${BOARD.BRANCH_CD == 'deletor'}">DELETOR</c:when>
									<c:otherwise>USER</c:otherwise>
								</c:choose>
							</td>
							<td>${BOARD.EMP_NM}</td>
							<td>${BOARD.SECTION}</td>
							<td>
								<fmt:parseDate var="dateLogin" value="${BOARD.LOGIN_DATE}" pattern="yyyyMMddHHmmss" />
								<fmt:formatDate value="${dateLogin}" pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
							<td>
								<fmt:parseDate var="dateLogout" value="${BOARD.LOGOUT_DATE}" pattern="yyyyMMddHHmmss" />
								<fmt:formatDate value="${dateLogout}" pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
							<td>
								<div class="position2">
									<input type="button" class="btn_it01" value="보기" onclick="historyDetailOpen('${BOARD.EMP_ID}')"/>
								</div>
							</td>
						</tr>
					</c:forEach>
				</tbody>

				<c:if test="${count == 0 }">
					<tr>
						<td colspan="8">데이터가 존재하지 않습니다.</td>
					</tr>
				</c:if>
			</table>

			<c:if test="${count > 0 }">
				<div class="border">
					<tag:Page formName="playHistoryForm"/>
				</div>
			</c:if>
		</div>
	</div>

 <jsp:include page="ListPagingDataS.jsp" flush="false" />
</tag:layout>