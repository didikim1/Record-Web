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

								</td>
								</tr>
								
								<tr>
								<td>
								<div class="common_div left ">고객사</div>
								<select class="common_select" name="cpid">
									<option value="" <c:if test="${paramMap.cpid  == '' || paramMap.cpid  eq null}">selected</c:if> >전체</option>
									<option value="-" <c:if test="${paramMap.cpid  eq '-' }">selected</c:if> >정보없음</option>
									<c:forEach var="data" items="${companyCodes}" varStatus="status">
										<option value="${data.companycode}" <c:if test="${paramMap.cpid eq data.companycode}">selected</c:if> >${data.companyname}</option>
									</c:forEach>
								</select>
								<div class="common_div left margin_l2">발신번호</div>
								<input type="text" class="common_input2 right" name="caller" id="caller" placeholder="발신번호" value="${paramMap.caller}" autocomplete="off"/>

								<div class="common_div left margin_l2">수신번호</div>
								<input type="text" class="common_input2 right" name="called2" id="called2" placeholder="수신번호" value="${paramMap.called2}" autocomplete="off"/>

								<div class="common_div left margin_l2">고객사고유번호</div>
								<input type="text" class="common_input2 right" name="rid" id="rid" placeholder="고객사 고유번호" value="${paramMap.rid}" autocomplete="off"/>

								<div class="common_div left margin_l2">링크ID</div>
								<input type="text" class="common_input2 right" name="linkedid" id="linkedid" placeholder="링크ID" value="${paramMap.linkedid}" autocomplete="off" />
									
								<div class="common_div left margin_l2">결과코드</div>
								<input type="number" min="00"  class="common_input2 right" name="resultcode" id="resultcode" placeholder="결과코드" value="${paramMap.resultcode}" autocomplete="off"/>

								<button type="button" class="common_button2 margin_l8" onclick="recordSearch();"><i class="fa fa-search" aria-hidden="true"></i>&nbsp;검색</button>
							</td>
						</tr>
					</table>
				</form>
			</div>

			<p class="result" style="font-size: 12px;text-align: right;">
				총 <span style="color: #ff0000; font-weight: bold;">${Data.paginationInfo.totalRecordCount}</span>개의 리스트가 있습니다.
			</p>

			<table id="myTable" class="wtable border">
				<thead>
					<tr>
						<th scope="col" width="5%">번호</th>
						<th scope="col" width="10%">RID</th> 		   <!-- 15 -->
						<th scope="col" width="10%">고객사</th> 		   <!-- companyname -->
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
							<td>${board.rid}</td>				<!-- RID-->
							<td>${board.cpid}</td>				<!-- 고객사 -->
							<td>${board.btime}</td>				<!-- 통화 시작시간 -->
							<td>${board.rbtime}</td>			<!-- 통화 수신시간 -->
							<td>${board.etime}</td>				<!-- 통화 종료시간 -->
							<td>${board.callduration2} &#47; ${board.callduration}</td>		<!-- 시간(sec) -->
							<td>${board.caller}</td>			<!-- 발신번호 -->
							<td ><div class="textOverflow" title="${board.called2}">${board.called2}</div></td>			<!-- 수신번호 -->
							<td>${board.resultcode}</td>		<!-- 결과코드 -->
							<td><div class="textOverflow" title="${board.resultmessage}">${board.resultmessage}</div></td>		<!-- 결과메세지 -->
							<td><input type="button" class="btn_it01" onclick="fnRecordPlay('${board.cid}');"  value="재생"/></td>
						</tr>
					</c:forEach>
				</tbody>

				<c:if test="${Data.paginationInfo.totalRecordCount == 0 }">
					<tr>
						<td width="100%" colspan="12" >데이터가 존재 하지 않습니다.</td>
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