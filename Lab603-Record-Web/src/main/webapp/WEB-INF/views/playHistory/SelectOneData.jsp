<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
</head>
<body>
	<div id="layout_content_popup" class="playHistoryDetail">
	<div class="content">
		<div class="border">
			<h1 class="tit01">검색조건</h1>
			<form name="detailSearchForm">
				<input type="hidden" name="pageNUM"/>
				<input type="hidden" name="hdc"  value="${paramMap.hdc}"/>
				<input type="hidden" name="excelResult"/>
				<table class="wtable_sub">
					<tr>
						<td>
							<div class="common_div left">고객번호</div>
							<input type="text" class="common_input2 right" name="req" id="req" placeholder="고객번호" value="${paramMap.req}" autocomplete="off"/>
							<div class="common_div left margin_l3">기간선택</div>
							<input type="text" class="common_input2 pointer"  name="sDate" id="sDate" style="border-right:none;" autocomplete="off" placeholder="시작일" value="${paramMap.sDate}" readonly="readonly"/>
							<input type="text" class="common_input2 right pointer" name="eDate" id="eDate" autocomplete="off" placeholder="종료일" value="${paramMap.eDate}" readonly="readonly"/>
							<button type="button" class="common_button2 margin_l2" onclick="pHistorySearch_D('${paramMap.hdc}');"><i class="fa fa-search" aria-hidden="true"></i>&nbsp;검색</button>
							<button type="button" class="common_button2 margin_l3" onclick="resetButton('${paramMap.hdc}');"><i class="fa fa-refresh" aria-hidden="true"></i>&nbsp;리셋</button>
						</td>
					</tr>
				</table>
			</form>
		</div>

		<table id="myTable_SUB" class="wtable tablesorter-blackice border table-hover sub">
			<thead>
				<tr>
					<th scope="col" width="10%">번호</th>
					<th scope="col" width="10%">업무코드</th>
					<th scope="col" width="10%">고객번호</th>
					<th scope="col" width="13%">통화일자</th>
					<th scope="col" width="13%">통화 시작시간</th>
					<th scope="col" width="13%">통화 종료시간</th>
					<th scope="col" width="8%">시간(sec)</th>
					<th scope="col" width="13%">재생일</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="BOARD" items="${list}" varStatus="status">
					<tr>
						<td>${totalCount -((pageNUM -1) * paramMap.sel) - status.index}</td>
						<td>${BOARD.AUTHREQNUMBER}</td>
						<td>02</td>
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
							<fmt:parseDate var="dateLogtime" value="${BOARD.LOGTIME}" pattern="yyyyMMddHHmmss" />
							<fmt:formatDate value="${dateLogtime}" pattern="yyyy-MM-dd HH:mm:ss" />
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
				<tag:Page formName="detailSearchForm"/>
			</div>
		</c:if>

	</div>
</div>
</body>
</html>
