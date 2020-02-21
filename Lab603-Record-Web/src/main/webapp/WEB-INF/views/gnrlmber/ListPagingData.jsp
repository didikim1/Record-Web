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
				<form name="userManageForm">
					<input type="hidden" name="excelResult"/>
					<table class="wtable_sub">
						<tr>
							<td>
								<div class="common_div left">아이디</div>
								<input type="text" class="common_input2 right" name="hdc" id="hdc" placeholder="아이디" value="${paramMap.hdc}" autocomplete="off"/>
								<div class="common_div left margin_l2">이름</div>
								<input type="text" class="common_input2 right" name="nm" id="nm" placeholder="이름" value="${paramMap.nm}" autocomplete="off"/>
								<button type="button" class="common_button2 margin_l2" onclick="userManageSearch();"><i class="fa fa-search" aria-hidden="true"></i>&nbsp;검색</button>
								<button type="button" class="common_button2 margin_l3" onclick="resetButton();"><i class="fa fa-refresh" aria-hidden="true"></i>&nbsp;리셋</button>

								<select class="common_select margin_l2" onchange="selectView(this.value);" id="sel" name="sel">
									<option value="10" <c:if test="${paramMap.sel == 10}"> selected="selected"</c:if>>10개 보기</option>
									<option value="30" <c:if test="${paramMap.sel == 30}"> selected="selected"</c:if>>30개 보기</option>
									<option value="50" <c:if test="${paramMap.sel == 50}"> selected="selected"</c:if>>50개 보기</option>
									<option value="100" <c:if test="${paramMap.sel == 100}"> selected="selected"</c:if>>100개 보기</option>
								</select>

								<c:choose>
									<c:when test="${paramMap.excelResult == 'ok'}"><button type="button" onclick="excelWrite();" class="common_button1 margin_l3"><i class="fa fa-file-excel-o" aria-hidden="true"></i>&nbsp;EXCEL 출력</button></c:when>
									<c:otherwise><button type="button" class="common_button1 margin_l3" onclick="excelWrite_Not();"><i class="fa fa-file-excel-o" aria-hidden="true" ></i>&nbsp;EXCEL 출력</button></c:otherwise>
								</c:choose>
							</td>
						</tr>
					</table>
				</form>
			</div>

			<div class="border margin_l6">
				<button type="button" class="btn_it02" onclick="userManageJoin();">계정 생성</button>
			</div>

			<table id="myTable" class="wtable tablesorter-blackice border table-hover">
				<thead>
						<tr>
							<th scope="col" width="8%"></th>
							<th scope="col" width="8%">아이디</th>
							<th scope="col" width="7%">권한</th>
							<th scope="col" width="7%">이름</th>
							<th scope="col" width="13%">소속</th>
							<th scope="col" width="13%">생성일</th>
							<th scope="col" width="13%">수정일</th>
							<th scope="col" width="6%">계정변경</th>
							<th scope="col" width="6%">계정삭제</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="BOARD" items="${list}" varStatus="status">
							<tr>
								<td>${totalCount -((pageNUM -1) *paramMap.sel) - status.index}</td>
								<td>${BOARD.EMP_ID}</td>
								<td>
									<c:choose>
										<c:when test="${BOARD.SORT == 1}">ADMIN</c:when>
										<c:when test="${BOARD.BRANCH_CD == 3}">DELETOR</c:when>
										<c:otherwise>USER</c:otherwise>
									</c:choose>
								</td>
								<td>${BOARD.EMP_NM}</td>
								<td>${BOARD.SECTION}</td>
								<td>
									<fmt:parseDate var="dateRecRegdate" value="${BOARD.REC_REGDATE}" pattern="yyyyMMddHHmmss" />
									<fmt:formatDate value="${dateRecRegdate}" pattern="yyyy-MM-dd HH:mm:ss" />
								</td>
								<td>
									<fmt:parseDate var="dateRevisiondate" value="${BOARD.REVISIONDATE}" pattern="yyyyMMddHHmmss" />
									<fmt:formatDate value="${dateRevisiondate}" pattern="yyyy-MM-dd HH:mm:ss" />
								</td>
								<td>
									<div class="position3">
										<input type="button" class="btn_it01" value="변경" onclick="userManageUpdate( '${BOARD.EMP_ID}')"/>
									</div>
								</td>
								<td>
									<div class="position3">
										<input type="button" class="btn_it01" value="삭제" onclick="userManageRemove('${BOARD.EMP_ID}')"/>
									</div>
								</td>
							</tr>
						</c:forEach>
					</tbody>

				<c:if test="${count == 0 }">
					<tr>
						<td colspan="9">데이터가 존재하지 않습니다.</td>
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