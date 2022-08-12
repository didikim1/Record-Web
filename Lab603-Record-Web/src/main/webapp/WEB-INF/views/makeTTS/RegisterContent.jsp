<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<div id="layout_content_popup_sub">

<form name="FormComtngnrlmber">
 	<input type="hidden" name="seq" 		value="${Info.seq}" /> 
	<input type="hidden" name="name" 		value="${Info.name}" />
	<input type="hidden" name="ttsMent" 	value="${Info.ttsMent}" />
	<div class="content">
		<div class="border_sub">
		<table class="htable">

				<tr>
					<th scope="col" class="TTSDetail" width="120px" class="LogPart">파일명</th>
					<td>${Info.name}</td>
				</tr>
				<tr>
					<th scope="col" class="TTSDetail"width="120px" class="LogPart">TITLE</th>
					<td>${Info.title}</td>
				</tr>
				<tr>
					<th scope="col" class="TTSDetail"width="120px" class="LogPart">TTS</th>
					<td>${Info.ttsMent}</td>
				</tr>
				<tr>
					<th scope="col" class="TTSDetail"width="120px" class="LogPart">Path</th>
					<td>${Info.callTTSWav}</td>
				</tr>
				<tr>
					<th scope="col" class="TTSDetail"width="120px" class="LogPart">FullPath</th>
					<td>${Info.ivrPath}</td>
				</tr>
				<tr>
					<th scope="col" class="TTSDetail"width="120px" class="LogPart">WebLink</th>
					<td>${Info.webLink}</td>
				</tr>
				<tr>
					<th scope="col" class="TTSDetail"width="120px" class="LogPart">파일크기</th>
					<td>${Info.filesize}</td>
				</tr>
				<tr>
					<th scope="col" class="TTSDetail" width="120px" class="LogPart">생성일</th>
					<td>${Info.createtime}</td>
				</tr>
	
			</table>
			</div>
		</div>
	</form>
	</div>
			