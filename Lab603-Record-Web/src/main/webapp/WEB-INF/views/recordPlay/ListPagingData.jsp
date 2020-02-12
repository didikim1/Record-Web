<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<c:set var="_url" value="${pageContext.request.contextPath == '/' ? '' : pageContext.request.contextPath }" scope="application"/>

<tag:layout>

    <div id="layout_content">
<!-- 	    <div class="content_top"> -->
<!-- 						<p class="tl">녹취파일 검색 / 재생 </p> -->
<!-- 			</div> -->

			<div class="border">
				<h1 class="tit01">검색조건</h1>
				<form name="recordSearchForm">
					<table class="wtable_sub">
						<tr>
							<td>
								<div class="common_div left">고객번호</div>
								<input type="text" class="common_input2 right" name="req" id="req" placeholder="고객번호" value="${paramMap.req}" autocomplete="off"/>
								<div class="common_div left margin_l3">기간선택</div>
								<input type="text" class="common_input2 pointer"  name="sDate" id="sDate" style="border-right:none;" autocomplete="off" placeholder="시작일" value="${paramMap.sDate}" readonly="readonly"/>
								<input type="text" class="common_input2 right pointer" name="eDate" id="eDate" autocomplete="off" placeholder="종료일" value="${paramMap.eDate}" readonly="readonly"/>
								<button type="button" class="common_button2 margin_l3" onclick="recordSearch();"><i class="fa fa-search" aria-hidden="true"></i>&nbsp;검색</button>
								<button type="button" onclick="excelWrite();" class="common_button1 margin_l3"><i class="fa fa-file-excel-o" aria-hidden="true"></i>&nbsp;EXCEL 출력</button>
								<select class="common_select margin_l3" onchange="selectView(this.value);" id="sel" name="sel">
									<option value="10" <c:if test="${paramMap.sel == 10}"> selected="selected"</c:if>>10개 보기</option>
									<option value="30" <c:if test="${paramMap.sel == 30}"> selected="selected"</c:if>>30개 보기</option>
									<option value="50" <c:if test="${paramMap.sel == 50}"> selected="selected"</c:if>>50개 보기</option>
									<option value="100" <c:if test="${paramMap.sel == 100}"> selected="selected"</c:if>>100개 보기</option>
								</select>
							</td>
						</tr>
					</table>
				</form>
			</div>


			<table id="myTable" class="wtable tablesorter-blackice border table-hover">
				<thead>
					<tr>
						<th scope="col" width="10%">번호</th>
						<th scope="col" width="10%">업무코드</th>
						<th scope="col" width="10%">고객번호</th>
						<th scope="col" width="15%">통화일자</th>
						<th scope="col" width="15%">통화 시작시간</th>
						<th scope="col" width="15%">통화 종료시간</th>
						<th scope="col" width="10%">시간(sec)</th>
						<th scope="col" width="10%">수신확인</th>
						<th scope="col" width="5%">듣기</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="BOARD" items="${list}" varStatus="status">
						<tr>
							<td>${totalCount -((pageNUM -1) * paramMap.sel) - status.index}</td>
							<td>02</td>
							<td>${BOARD.AUTHREQNUMBER}</td>
							<td>
								<fmt:parseDate var="dateRequesttime" value="${BOARD.REQUESTTIME}" pattern="yyyyMMddHHmmss" />
								<fmt:formatDate value="${dateRequesttime}" pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
							<td>
								<fmt:parseDate var="dateBtime" value="${BOARD.BTIME}" pattern="yyyyMMddHHmmss" />
								<fmt:formatDate value="${dateBtime}" pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
							<td>
								<fmt:parseDate var="dateEtime" value="${BOARD.ETIME}" pattern="yyyyMMddHHmmss" />
								<fmt:formatDate value="${dateEtime}" pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
							<td>${BOARD.RECTIME}</td>
							<td>
								<c:choose>
									<c:when test="${BOARD.ISRCV == 'Y'}">완료</c:when>
									<c:otherwise>미수신</c:otherwise>
								</c:choose>
							</td>
							<td>
								<div class="position">
									<input type="button" class="btn_it01" onclick="recordPlay('${BOARD.C_ID}');"  value="재생"/>
								</div>
							</td>
						</tr>
					</c:forEach>
				</tbody>

				<c:if test="${count == 0 }">
					<tr>
						<td width="100%" colspan="9" >데이터가 존재 하지 않습니다.</td>
					</tr>
				</c:if>
			</table>

			<c:if test="${count > 0 }">
				<div class="border">
<%-- 					<tag:page formName="recordSearchForm"/> --%>
				</div>
			</c:if>

    </div>
</tag:layout>