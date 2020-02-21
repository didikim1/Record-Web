<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<c:set var="_url" value="${pageContext.request.contextPath == '/' ? '' : pageContext.request.contextPath }" scope="application"/>
<div id="layout_content_popup_sub">
	<div class="content">
		<div class="border_sub">
			<form name="joinForm">
				<div align="center" >
					<table class="htable">
						<tr>
							<th scope="col" width="120px">아이디</th>
							<td><input type="text" class="userManageInput" id="hdcSub" name="hdcSub" autocomplete="off"></td>
						</tr>
						<tr>
							<th scope="col" width="120px">이름</th>
							<td><input type="text" class="userManageInput" id="nmSub" name="nmSub" autocomplete="off"></td>
						</tr>
						<tr>
							<th scope="col" width="120px">소속</th>
							<td><input type="text" class="userManageInput" id="sectionSub" name="sectionSub" autocomplete="off"></td>
						</tr>
						<tr>
							<th scope="col" width="120px">권한</th>
							<td>
								<select class="userManageSelect" name="authSub">
									<option value="user">USER</option>
									<option value="admin">ADMIN</option>
								</select>
							</td>
						</tr>
					</table>
				</div>
			</form>
		</div>

		<div class="border margin_l7">
			<button type="button" class="userManageButton" onclick="check()">등록하기</button>
		</div>
	</div>
</div>
