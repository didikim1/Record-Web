<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="_url" value="${pageContext.request.contextPath == '/' ? '' : pageContext.request.contextPath }" scope="application"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<title>[INBIZNET] ARS 녹취재생 페이지</title>
<tag:header_comm_stylesheet/>
<tag:header_comm_script/>
</head>
<body>
			<div class="wrap">
				<div id="head"></div>

				<div id="content">
				    	<div class="login_box block">
				    		<div class="login_text_box block">
				    			<p class="main_text1"><b>간편하고 안전한 비대면 본인인증 서비스</b></p>
								<p class="main_text2"><b>ARS 인증 서비스 <br/> 녹취 재생 페이지</b></p>
				    		</div>

				    		<div class="login_input_box block">
				    			<div class="login_div_sub">
				    				<div class="login_div">아이디</div>
									<input type="text" name="uniqId" id="uniqId" class="loginInput" placeholder="아이디"/>
									<div class="login_div">비밀번호</div>
									<input type="text" name="password" id="password" class="loginInput" placeholder="비밀번호"/>
								</div>

				    			<form name="form" method="post" action="./index_exec.do" >
									<input type="hidden" id="USER_ID" name="USER_ID"/>
									<input type="hidden" id="USER_PW" name="USER_PW"/>
								</form>
									<button class="loginButton" type="button" id="btnLogin">
										<b>로그인</b>
									</button>

									<p class="main_text3">
										<span class="glyphicon glyphicon-exclamation-sign"></span>
										본 사이트는 승인 받은 관리자의 PC에서만 접속 가능합니다.<br/> Chrome (1920x1080)에 최적화되어 있습니다.
									</p>
				    			</div>
				    	</div>
				</div>

				<div id="footer">
					<div class="footer_div1">
					</div>
					<div class="footer_div2">
						<span class="footer_font1">서울시 마포구 마포대로 49, 1007호 (도화동,  성우빌딩)<br/></span>
<!-- 						<b><span class="footer_font2">COPYRIGHT ⓒ HYUNDAI CARD Co.LTD ALL RIGHTS RESERVED.</span></b> -->
					</div>
				</div>
			</div>
</body>
<jsp:include page="indexS.jsp" flush="false" />
</html>
