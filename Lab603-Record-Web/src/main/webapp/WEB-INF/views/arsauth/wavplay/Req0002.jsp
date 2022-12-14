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

			alert("ARS??? ??????????????????.");

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
		            url:'/arsauth/wavplay/Req0002.do',
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
<h1>L8002</h1>
<div class="left">
<h3>??????</h3>
<form name="reqForm">
<input type="hidden" name="authReqNumber" value="" readonly="readonly"/>
<input type="hidden" name="requestTime" value="" readonly="readonly"/>

<table class="type03">
    <tr>
        <th scope="row">????????????</th>
        <td>
        	<select name="arsCommandNumber">
        		<option value="L8002">L8002</option>
        	</select>
        </td>
    </tr>
    <tr>
        <th scope="row">????????????</th>
        <td><input type="text" name="companyCode" value="80001" readonly="readonly"/></td>
    </tr>
    <tr>
        <th scope="row">???????????????</th>
        <td><input type="text" name="serviceType" value="03" readonly="readonly"/></td>
    </tr>
    <tr>
        <th scope="row">?????????</th>
        <td><input type="text" name="serviceCode" value="0001" readonly="readonly"/></td>
    </tr>
    <tr>
        <th scope="row">ARS??????</th>
        <td><input type="text" name="callType" value="02" readonly="readonly"/></td>
    </tr>
    <tr>
        <th scope="row">???????????????</th>
<!--         <td><input type="text" name="phoneNumber" value="01087833417" readonly="readonly"/></td> -->
<!--         <td><input type="text" name="phoneNumber" value="1005" readonly="readonly"/></td> -->
<td>
		<select name="phoneNumber">
			<option value="01087833417">?????????(01087833417)</option>
			<option value="01073445975">?????????(01073445975)</option>
			<option value="01050313085">?????????(01050313085)</option>
			<option value="01072092178">?????????(01072092178)</option>
			<option value="01052939217">?????????(01052939217)</option>
			<option value="01088984680">??????(01088984680)</option>
			<option value="01071557901">?????????(01071557901)</option>
		</select>
	</td>
    </tr>
    <tr>
        <th scope="row">Wav ????????????</th>
        <td><input type="text" name="wavDirPath" value="albert/" readonly="readonly"/></td>
    </tr>
    <tr>
        <th scope="row">Wav ?????????</th>
        <td><input type="text" name="wavFileName" value="EX_TTS_MENT_INTRO_01" readonly="readonly"/></td>
    </tr>
    <tr>
        <th scope="row">??????????????????</th>
        <td><input type="text" name="arryUserPassword" value="1,2,3" readonly="readonly"/></td>
    </tr>
    <tr>
        <th scope="row" colspan="2" style="text-align: right;"> <input type="button" id="btnSubmit" value="??????"/> </th>
    </tr>
</table>
</form>
</div>
<div class="right">
<h3>??????</h3>
<table class="type03">
    <tr>
        <th scope="row">????????????</th>
        <td>
        	<select name="arsCommandNumber">
        		<option value="LAB8001">LAB8001</option>
        	</select>
        </td>
    </tr>
     <tr>
        <th scope="row">????????????</th>
        <td id="companyCode"></td>
    </tr>
    <tr>
        <th scope="row">???????????????</th>
        <td id="serviceType"></td>
    </tr>
    <tr>
        <th scope="row">?????????</th>
        <td id="serviceCode"></td>
    </tr>
    <tr>
        <th scope="row">ARS??????</th>
        <td id="callType"></td>
    </tr>
    <tr>
        <th scope="row">???????????????</th>
        <td id="phoneNumber"></td>
    </tr>
    <tr>
        <th scope="row">??????????????????</th>
        <td id="authStartTime"></td>
    </tr>
    <tr>
        <th scope="row">??????????????????</th>
        <td id="authEndTime"></td>
    </tr>

    <tr>
        <th scope="row">??????????????????</th>
        <td id="callStartTime"></td>
    </tr>

    <tr>
        <th scope="row">??????????????????</th>
        <td id="callEndTime"></td>
    </tr>

    <tr>
        <th scope="row">????????????</th>
        <td id="callduration"></td>
    </tr>

    <tr>
        <th scope="row">??????</th>
        <td id="result"></td>
    </tr>

    <tr>
        <th scope="row">?????????</th>
        <td id="message"></td>
    </tr>

    <tr>
        <th scope="row">??????????????????</th>
        <td id="userDTMF"></td>
    </tr>

    <tr>
        <th scope="row">????????????ID</th>
        <td id="recordChargeID"></td>
    </tr>

    <tr>
        <th scope="row">??????????????????</th>
        <td id="retryCount"></td>
    </tr>

     <tr>
        <th scope="row">?????????????????????</th>
        <td id="tryCount"></td>
    </tr>

     <tr>
        <th scope="row">???????????????</th>
        <td id="noInputCount"></td>
    </tr>

</table>
</div>
</body>
</html>