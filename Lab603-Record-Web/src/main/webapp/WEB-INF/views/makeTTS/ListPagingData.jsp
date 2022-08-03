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
								<div class="common_div left margin_l2">TTS검색</div>
									<input type="text" class="common_input3" name="ttsMent" id="ttsMent" placeholder="TTS입력" value="${paramMap.ttsMent}" autocomplete="off"/> 
									<button type="button" class="common_button2 margin_l8" onclick="recordSearch();"><i class="fa fa-search" aria-hidden="true"></i>&nbsp;검색</button>
							</td>
						</tr>
					</table>
				</form>
				<button type="button" class="btn_it02" onclick="fnOpenRegisterPage();">TTS생성</button>
			</div>

			<p class="result" style="font-size: 12px;text-align: right;">
				총 <span style="color: #ff0000; font-weight: bold;">${Data.paginationInfo.totalRecordCount}</span>개의 리스트가 있습니다.
			</p>

			<table id="myTable" class="wtableMent border">
				<thead>
					<tr>
						
						<th scope="col" width="5%">번호</th>
						<th scope="col" width="20%">파일명</th> 		   	
						<th scope="col" width="30%">TTS</th> 		   	
						<th scope="col" width="20%">파일크기</th> 			
						<th scope="col" width="20%">생성일</th> 			
						<th scope="col" width="5%">듣기</th>
						<th scope="col" width="7%">삭제</th>
					</tr>
				</thead>
				<tbody>
				<tr>
				</tr>
					 <c:forEach var="board" items="${Data.list}" varStatus="status">
						<tr>
							<td>${Data.paginationInfo.totalRecordCount -((Data.paginationInfo.currentPageNo -1) * Data.paginationInfo.recordCountPerPage) - status.index}</td>	<!-- 번호 -->
							<td>${board.name}</td>				<!-- 명칭-->
							<td>${board.ttsMent}</td>			<!-- tts멘트 -->
							<td>${board.filesize}</td>			<!-- 파일크기 -->
							<td>${board.createtime}</td>		<!-- 생성일 --> 
							<td><input type="button" class="btn_it01" onclick="fnRecordPlay('${board.cid}');"  value="재생"/></td>
							<td><input type="button" class="btn_it01"  value="삭제" onclick="fnDeleteData ('${board.name}')"/></td>
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