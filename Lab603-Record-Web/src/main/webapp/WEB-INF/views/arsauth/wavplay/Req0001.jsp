<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
	<meta name="viewport" content="width=1024">
  <title>LAB603</title>
	<script
	  src="https://code.jquery.com/jquery-1.12.4.js"
	  integrity="sha256-Qw82+bXyGq6MydymqBxNPYTaUXXq7c8v3CwiYwLLNXU="
	  crossorigin="anonymous"></script>
	<style type="text/css">
		table.type03 {
		    border-collapse: collapse;
		    text-align: left;
		    line-height: 1.5;
		    border-top: 1px solid #ccc;
		    border-left: 3px solid #369;
		  margin : 20px 10px;
		}
		table.type03 th {
		    width: 147px;
		    padding: 10px;
		    font-weight: bold;
		    vertical-align: top;
		    color: #153d73;
		    border-right: 1px solid #ccc;
		    border-bottom: 1px solid #ccc;

		}
		table.type03 td {
		    width: 349px;
		    padding: 10px;
		    vertical-align: top;
		    border-right: 1px solid #ccc;
		    border-bottom: 1px solid #ccc;
		}

		div {
        width: 100%;
        height: 1000x;

/*         border: 1px solid #000; */
	    }
	    div.left {
	        width: 50%;
	        float: left;
	        box-sizing: border-box;

/* 	        background: #ff0; */
	    }
	    div.right {
	        width: 50%;
	        float: right;
	        box-sizing: border-box;

/* 	        background: #0ff; */
	    }
	</style>
	<script type="text/javascript">

	  function leadingZeros(n, digits){
		  var zero = '';
    	  n = n.toString();

    	  if (n.length < digits) {
    	    for (i = 0; i < digits - n.length; i++)
    	      zero += '0';
    	  }
    	  return zero + n;
	  }
  	  function timeStamp() {
	  		var d = new Date();
		  	var s =
	  		leadingZeros(d.getFullYear(), 4) +
	  		leadingZeros(d.getMonth() + 1, 2) +
	  		leadingZeros(d.getDate(), 2) +
	  		leadingZeros(d.getHours(), 2) +
	  		leadingZeros(d.getMinutes(), 2)  +
	  		leadingZeros(d.getSeconds(), 2);

  	  		return s;
  	  }

  	function randomLength(iSize) {
  		var returnStrRandomNumber = "";
    	for(i=0;i<iSize;i++){
    		returnStrRandomNumber += Math.floor(Math.random() * 10);
    	}
    	return returnStrRandomNumber;
	  }

	 $(document).ready(function(){

		$("#btnSubmit").click(function(){

			alert("ARS를 요청했습니다.");

			var strAuthReqNumber = "InbizTest;;"+randomLength(10)
			var strTimeStamp	 = timeStamp();

			$("[name=authReqNumber]").val(strAuthReqNumber);
			$("[name=requestTime]").val(strTimeStamp);

			$("#authReqNumber").text("");
			$("#requestTime").text("");
			$("#companyCode").text("");
			$("#serviceType").text("");
			$("#serviceCode").text("");
			$("#authStartTime").text("");
			$("#authEndTime").text("");
			$("#callStartTime").text("");
			$("#callEndTime").text("");
			$("#callduration").text("");
			$("#result").text("");
			$("#message").text("");
			$("#userDTMF").text("");
			$("#recordChargeID").text("");
			$("#retryCount").text("");
			$("#tryCount").text("");
			$("#noInputCount").text("");

			var params = jQuery("[name=reqForm]").serialize();
			 $.ajax({
		            url:'/arsauth/wavplay/Req0001.do',
		            data:params,
		            success:function(data){
		                console.log(obj);

		                var obj = JSON.parse(data);

		                var arsCommandNumber = obj.arsCommandNumber;
		                var authReqNumber = obj.authReqNumber;  $("#authReqNumber").text(authReqNumber);
		                var requestTime = obj.requestTime;		$("#requestTime").text(requestTime);
		                var companyCode = obj.companyCode;		$("#companyCode").text(companyCode);
		                var serviceType = obj.serviceType;		$("#serviceType").text(serviceType);
		                var serviceCode = obj.serviceCode;		$("#serviceCode").text(serviceCode);

		                var authStartTime = obj.authStartTime;	$("#authStartTime").text(authStartTime);
		                var authEndTime = obj.authEndTime;		$("#authEndTime").text(authEndTime);
		                var callStartTime = obj.callStartTime;	$("#callStartTime").text(callStartTime);
		                var callEndTime = obj.callEndTime;		$("#callEndTime").text(callEndTime);
		                var callduration = obj.callduration;	$("#callduration").text(callduration);
		                var result = obj.result;				$("#result").text(result);
		                var message = obj.message;				$("#message").text(message);
		                var userDTMF = obj.userDTMF;			$("#userDTMF").text(userDTMF);

		                var recordChargeID = obj.recordChargeID;	$("#recordChargeID").text(recordChargeID);
		                var retryCount = obj.retryCount;			$("#retryCount").text(retryCount);
		                var tryCount = obj.tryCount;				$("#tryCount").text(tryCount);
		                var noInputCount = obj.noInputCount;		$("#noInputCount").text(noInputCount);

		            }
		        })
		});
	 });
	</script>
</head>
<body>
<h1>L8001</h1>
<div class="left">
<h3>요청</h3>
<form name="reqForm">
<input type="hidden" name="authReqNumber" value="" readonly="readonly"/>
<input type="hidden" name="requestTime" value="" readonly="readonly"/>

<table class="type03">
    <tr>
        <th scope="row">전문번호</th>
        <td>
        	<select name="arsCommandNumber">
        		<option value="L8001">L8001</option>
        	</select>
        </td>
    </tr>
    <tr>
        <th scope="row">업체코드</th>
        <td><input type="text" name="companyCode" value="80001" readonly="readonly"/></td>
    </tr>
    <tr>
        <th scope="row">서비스유형</th>
        <td><input type="text" name="serviceType" value="03" readonly="readonly"/></td>
    </tr>
    <tr>
        <th scope="row">서비스</th>
        <td><input type="text" name="serviceCode" value="0001" readonly="readonly"/></td>
    </tr>
    <tr>
        <th scope="row">ARS유형</th>
        <td><input type="text" name="callType" value="02" readonly="readonly"/></td>
    </tr>
    <tr>
        <th scope="row">휴대폰번호</th>
<!--         <td><input type="text" name="phoneNumber" value="01087833417" readonly="readonly"/></td> -->
<!--         <td><input type="text" name="phoneNumber" value="1005" readonly="readonly"/></td> -->
	<td>
		<select name="phoneNumber">
			<option value="01087833417">전효성(01087833417)</option>
			<option value="01073445975">장미숙(01073445975)</option>
			<option value="01050313085">장민규(01050313085)</option>
			<option value="01072092178">민라희(01072092178)</option>
			<option value="01052939217">채지윤(01052939217)</option>
			<option value="01088984680">최령(01088984680)</option>
			<option value="01071557901">전해성(01071557901)</option>
			<option value="01064015613">사장님(01064015613)</option>
		</select>
	</td>
    </tr>
    <tr>
        <th scope="row">Wav 디렉토리</th>
        <td><input type="text" name="wavDirPath" value="albert/" readonly="readonly"/></td>
    </tr>
    <tr>
        <th scope="row">Wav 파일명</th>
        <td><input type="text" name="wavFileName" value="EX_TTS_MENT_INTRO_01" readonly="readonly"/></td>
<!--         <td><input type="text" name="wavFileName" value="SKT_20191217001_LINE_BUSY" readonly="readonly"/></td> -->
    </tr>
    <tr>
        <th scope="row" colspan="2" style="text-align: right;"> <input type="button" id="btnSubmit" value="전송"/> </th>
    </tr>
</table>
</form>
</div>
<div class="right">
<h3>결과</h3>
<table class="type03">
    <tr>
        <th scope="row">전문번호</th>
        <td>
        	<select name="arsCommandNumber">
        		<option value="LAB8001">LAB8001</option>
        	</select>
        </td>
    </tr>
     <tr>
        <th scope="row">업체코드</th>
        <td id="companyCode"></td>
    </tr>
    <tr>
        <th scope="row">서비스유형</th>
        <td id="serviceType"></td>
    </tr>
    <tr>
        <th scope="row">서비스</th>
        <td id="serviceCode"></td>
    </tr>
    <tr>
        <th scope="row">ARS유형</th>
        <td id="callType"></td>
    </tr>
    <tr>
        <th scope="row">휴대폰번호</th>
        <td id="phoneNumber"></td>
    </tr>
    <tr>
        <th scope="row">통화시작시간</th>
        <td id="authStartTime"></td>
    </tr>
    <tr>
        <th scope="row">통화종료시간</th>
        <td id="authEndTime"></td>
    </tr>

    <tr>
        <th scope="row">인증시작시간</th>
        <td id="callStartTime"></td>
    </tr>

    <tr>
        <th scope="row">인증종료시간</th>
        <td id="callEndTime"></td>
    </tr>

    <tr>
        <th scope="row">인증시간</th>
        <td id="callduration"></td>
    </tr>

    <tr>
        <th scope="row">결과</th>
        <td id="result"></td>
    </tr>

    <tr>
        <th scope="row">메세지</th>
        <td id="message"></td>
    </tr>

    <tr>
        <th scope="row">사용자입력값</th>
        <td id="userDTMF"></td>
    </tr>

    <tr>
        <th scope="row">녹취고유ID</th>
        <td id="recordChargeID"></td>
    </tr>

    <tr>
        <th scope="row">다시듣기횟수</th>
        <td id="retryCount"></td>
    </tr>

     <tr>
        <th scope="row">인증재시도횟수</th>
        <td id="tryCount"></td>
    </tr>

     <tr>
        <th scope="row">미입력횟수</th>
        <td id="noInputCount"></td>
    </tr>

</table>
</div>
</body>
</html>