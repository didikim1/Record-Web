<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<c:set var="_url" value="${pageContext.request.contextPath == '/' ? '' : pageContext.request.contextPath }" scope="application"/>



<tag:layout>
	<style>
	</style>
    <div id="layout_content">
			<div class="border">
				<h1 class="tit01">검색조건</h1>
				<form name="recordSearchForm">
					<input type="hidden" name="page" />
					<table class="wtable_sub">
						<tr>
							<td>
								<div class="common_div left">기간선택</div>
								<input type="text" class="common_input2 pointer"  name="sDate" id="sDate" style="border-right:none;" autocomplete="off" placeholder="시작일" value="${paramMap.sDate}" readonly="readonly"/>
								<input type="text" class="common_input2 right pointer" name="eDate" id="eDate" autocomplete="off" placeholder="종료일" value="${paramMap.eDate}" readonly="readonly"/>
								<div class="common_div left margin_l3">고객사고유번호</div>
								<input type="text" class="common_input2 right" name="rid" id="rid" placeholder="고객번호" value="${paramMap.rid}" autocomplete="off"/>
								<div class="common_div margin_l3 left">발신번호</div>
								<input type="text" class="common_input2 right" name="caller" id="caller" placeholder="발신번호" value="${paramMap.caller}" autocomplete="off"/>
								<div class="common_div left margin_l3">수신번호</div>
								<input type="text" class="common_input2 right" name="called2" id="called2" placeholder="수신번호" value="${paramMap.called2}" autocomplete="off"/>
								<button type="button" class="common_button2 margin_l3" onclick="recordSearch();"><i class="fa fa-search" aria-hidden="true"></i>&nbsp;검색</button>
							</td>
						</tr>
					</table>
				</form>
			</div>

			<table id="myTable" class="wtable border">
				<thead>
					<tr>
						<th scope="col" width="5%">번호</th>
						<th scope="col" width="10%">RID</th> 		   <!-- 15 -->
						<th scope="col" width="10%">통화 시작시간</th> <!-- 25 -->
						<th scope="col" width="10%">통화 수신시간</th> <!-- 35 -->
						<th scope="col" width="10%">통화 종료시간</th> <!-- 45 -->
						<th scope="col" width="5%">시간(sec)</th>
						<th scope="col" width="10%">발신번호</th>
						<th scope="col" width="10%">수신번호</th>
						<th scope="col" width="10%">결과</th>
						<th scope="col" width="10%">메세지</th>
						<th scope="col" width="10%">듣기</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="board" items="${Data.list}" varStatus="status">
						<tr>
							<td>${Data.paginationInfo.totalRecordCount -((Data.paginationInfo.currentPageNo -1) * Data.paginationInfo.recordCountPerPage) - status.index}</td>	<!-- 번호 -->
							<td>${board.rid}</td>				<!-- 통화 시작시간 -->
							<td>${board.btime}</td>				<!-- 통화 시작시간 -->
							<td>${board.rbtime}</td>			<!-- 통화 수신시간 -->
							<td>${board.etime}</td>				<!-- 통화 종료시간 -->
							<td>${board.callduration}</td>		<!-- 시간(sec) -->
							<td>${board.caller}</td>			<!-- 발신번호 -->
							<td>${board.called2}</td>			<!-- 수신번호 -->
							<td>${board.resultcode}</td>		<!-- 결과코드 -->
							<td>${board.resultmessage}</td>	<!-- 결과메세지 -->
							<td><input type="button" class="btn_it01" onclick="fnRecordPlay('${board.cid}');"  value="재생"/></td>
						</tr>
					</c:forEach>
				</tbody>

				<c:if test="${Data.paginationInfo.totalRecordCount == 0 }">
					<tr>
						<td width="100%" colspan="9" >데이터가 존재 하지 않습니다.</td>
					</tr>
				</c:if>
			</table>

			<c:if test="${Data.paginationInfo.totalRecordCount > 0 }">
				<div class="border">
					<tag:Page formName="recordSearchForm" pageing="${Data.paginationInfo}"/>
				</div>
			</c:if>

    </div>
 <jsp:include page="ListPagingDataS.jsp" flush="false" />
</tag:layout>