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
			</div>
			<c:set var="timeActive" value="${MainTrunk.minutehour} 시간"/>
			<c:if test="${MainTrunk.minutehour == 0}">
				<c:set var="timeActive" value="${MainTrunk.minutediff} 분"/>
			</c:if>
			
			<div class="border" style="font-size: 15px;font-weight: bold;padding-left: 10px;height: 40px;">
				 <div style="margin-top: 10px;">현재 Asterisk(IVR) 는 ${MainTrunk.telecomName} ${MainTrunk.trunkName} 로 연결되어 ( ${MainTrunk.acttime}, ${timeActive}) 서비스 중입니다.</div> 
			</div>
			
			<div class="border margin_l6">
				<button type="button" class="btn_it02" onclick="fnTrunkRegister();">Trunk 생성</button>
			</div>
				
			<p class="result" style="font-size: 12px;text-align: right;">
				총 <span style="color: #ff0000; font-weight: bold;">${Data.paginationInfo.totalRecordCount}</span>개의 리스트가 있습니다.
			</p>
			
			<table id="myTable" class="wtable border">
				<thead>
					<tr>
						<th scope="col" width="5%">번호</th>
						<th scope="col" width="10%">통신사</th> 		   <!-- 15 -->
						<th scope="col" width="10%">트렁크</th> 		   <!-- companyname -->
						<th scope="col" width="10%">트렁크 IP</th> <!-- 25 -->
						<th scope="col" width="10%">트렁크 PORT</th> <!-- 35 -->
						<th scope="col" width="10%">사용자</th> <!-- 45 -->
						<th scope="col" width="5%">상태</th>
						<th scope="col" width="10%">등록일</th>
						<th scope="col" width="10%">수정일</th>
						<th scope="col" width="10%">변경</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="board" items="${Data.list}" varStatus="status">
						<tr>
							<td>${Data.paginationInfo.totalRecordCount -((Data.paginationInfo.currentPageNo -1) * Data.paginationInfo.recordCountPerPage) - status.index}</td>	<!-- 번호 -->
							<td>${board.telecomName}</td>				<!-- 통신사 -->
							<td>${board.trunkName}</td>					<!-- 트렁크 -->
							<td>${board.trunkIp}</td>					<!-- 트렁크 IP -->
							<td>${board.trunkPort}</td>					<!-- 트렁크 PORT -->
							<td>${board.trunkUsername}</td>				<!-- 사용자 -->
							<td>										<!-- 상태 -->
								<c:choose>
									<c:when test="${board.status == 'A'}">
									<img alt="" src="${_resource}/lib/ajax-loader_20200818.gif">
									<font style="color: green;font-weight: bold;">(서비스 중)</font>
									</c:when>
									<c:otherwise>백업</c:otherwise>
								</c:choose>
							</td>					
							<td>${board.createtime}</td>				<!-- 등록일 -->
							<td>${board.modifytime}</td>				<!-- 수정일 -->
							<td>	<!-- 변경 -->
								<c:if test="${board.status != 'A'}">
									<input type="button" class="btn_it01" onclick="fnModify('${board.seq}', '${board.telecomName}', '${board.trunkName} ');"  value="변경"/>
								</c:if>
							</td>				
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