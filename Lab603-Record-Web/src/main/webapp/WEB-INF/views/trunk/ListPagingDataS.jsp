<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script type="text/javascript">

$(document).ready(function(){
    $.datepicker.setDefaults({
        dateFormat: 'yy-mm-dd',
        firstDay: 0,
        isRTL: false,
        changeMonth : true,
        changeYear : true,
        showOtherMonths: true,
        selectOtherMonths: true,
        showAnim: "fadeIn"
    });

    $("#sDate").datepicker();
    $("#eDate").datepicker();

});
function fnModify(seq, telecomName, trunkName){
	var _seq = seq; 
	var txt  = telecomName +"(통신사) 의 <br/>" + trunkName + "(트렁크) 로 변경하시겠습니까?";
	
	$.fun.alert({
		content:txt,
		"buttons":{
			"확인": function() {
				$(this).dialog('destroy').remove();
				$.fun.ajax({
					type:'get',
					url:"./ModifyMainTrunk.do?seq="+_seq,
					success:function(data){
						var rData = $.fun.json_encode( data );
						
						if ( 200 == rData.code ){
							$.fun.alert({content:"정상적으로 변경되었습니다.", action:function(){
								location.reload();
							}});
						}else{
							$.fun.alert({content: rData.code + " " + rData.message });
						}
					}
				});
			},
			"닫기": function() {
				$(this).dialog('destroy').remove();
			}
		}
	});
}


function fnTrunkRegisterHTML(){
	
	var innerHTML = "";
	
	innerHTML += '<div id="layout_content_popup_sub">';
	innerHTML += '	<div class="content">';
	innerHTML += '		<div class="border_sub">';
	innerHTML += '			<form name="registerForm">';
	innerHTML += '				<div align="center" >';
	innerHTML += '					<table class="htable">';
	innerHTML += '						<tr>';
	innerHTML += '							<th scope="col" width="120px">Trunk 파일명</th>';
	innerHTML += '							<td><input type="text" class="userManageInput" id="" name="TrunkFileName" autocomplete="off"></td>';
	innerHTML += '						</tr>';
	innerHTML += '						<tr>';
	innerHTML += '							<th scope="col" width="120px">Trunk 명칭</th>';
	innerHTML += '							<td><input type="text" class="userManageInput" id="" name="TrunkName" autocomplete="off"></td>';
	innerHTML += '						</tr>';
	innerHTML += '						<tr>';
	innerHTML += '							<th scope="col" width="120px">Trunk 메인번호</th>';
	innerHTML += '							<td><input type="text" class="userManageInput" id="" name="TrunkNumber" autocomplete="off"></td>';
	innerHTML += '						</tr>';
	innerHTML += '						<tr>';
	innerHTML += '							<th scope="col" width="120px">Trunk IP</th>';
	innerHTML += '							<td><input type="text" class="userManageInput" id="" name="TrunkIP" autocomplete="off"></td>';
	innerHTML += '						</tr>';
	innerHTML += '						<tr>';
	innerHTML += '							<th scope="col" width="120px">Trunk Port</th>';
	innerHTML += '							<td><input type="text" class="userManageInput" id="" name="TrunkPort" autocomplete="off"></td>';
	innerHTML += '						</tr>';
	innerHTML += '					</table>';
	innerHTML += '				</div>';
	innerHTML += '			</form>';
	innerHTML += '		</div>';
	innerHTML += '		<div class="border margin_l7">';
	innerHTML += '			<button type="button" class="userManageButton" onclick="fnTrunkRegisterProc()">등록하기</button>';
	innerHTML += '		</div>';
	innerHTML += '	</div>';
	innerHTML += '</div>';
	
	return innerHTML;
}

function fnTrunkRegister(){
		$.fun.layout({
			id:"induacaAdd",
			"content":fnTrunkRegisterHTML(),
			"title":"Trunk 생성",
			"width":470,
			"buttons":{}
		});
}

function fnTrunkRegisterProc(){
	$.ajax({
		type:'get',
		data: $("[name=registerForm]").serialize(),
		url:'./RegisterData.do',
		dataType : "json",
		success:function(data){
			$.fun.layout({
				id:"induacaAdd",
				"content":"<div style='text-align: left'>" + data.result.data + "</div>",
				"title":data.result.fileName,
				"width":470,
				"buttons":{}
			});
		}
	});
}


function fnShowLinkID(linkID){
	$.fun.alert({content:"LinkID:"+linkID});
}

function fnRecordPlay(cid)
{
	if(cid == "" || cid == null)
	{
		$.fun.alert({content:"재생 플레이어 Error 발생."});
	}
	else
	{
		$.fun.alert({
			content:"재생 하시겠습니까?",
			"buttons":{
				"확인": function() {
					$(this).dialog('destroy').remove();
					$.fun.ajax({
						type:'get',
						
						// text-align: left
						// <div style='text-align: left'></div>
						
						url:"./SelectOneData.do?cid="+cid,
						success:function(data){

							console.log(data)

							$.fun.layout({
								id:"induacaAdd",
								"content":data,
								"title":"녹취파일듣기",
								"width":440,
								"buttons":{}
							});
						}
					});
				},
				"닫기": function() {
					$(this).dialog('destroy').remove();
				}
			}
		});
	}
}

function recordSearch() {
	var recordSearchForm 		= document.recordSearchForm;

	var sDate		= $("[name=sDate]").val();
	var eDate 		= $("[name=eDate]").val();
	var req 		= $("[name=req]").val();

	var validDate 			= /[0-9]{4}-[0-9]{2}-[0-9]{2}/;
	var validNumber 		= /^[0-9]*$/;

	if (  ( sDate == "" || eDate == "" ) == true) {
		$.fun.alert({content:"시작일 또는 종료일을 선택해주세요."});
		return;
	}
	else {
		if (  validDate.test(sDate) == false || validDate.test(eDate) == false ) {
			$.fun.alert({content:"날짜는 지정된 형식으로 입력하세요."});
			return;
		}
	}

	recordSearchForm.submit();
}

if("${result}" == "N"){
	$.fun.alert({content:"검색된 항목이 없습니다."});
}

if("${validResult}" == "error"){
	$.fun.alert({content:"검색값에 오류가 있습니다."});
}


// 테이블 정렬 기능 비활성황
$(function(){
	$("table thead th:eq(6)").data("sorter", false);
	$("table thead th:eq(7)").data("sorter", false);

	$("#myTable").tablesorter({
		usNumberFormat : false,
		sortReset      : true,
		sortRestart    : true
	});
});


//Excel 출력
function excelWrite() {
	$.ajax({
		type:'get',
		data: $("[name=recordSearchForm]").serialize(),
		url:'./RecordSearchAndPlayExcel.do',
		success:function(data)
		{
			if(200 == data)
		 	{
				location.href="./CommonExcelDown.do";
		 	}
			else if(99 == data)
			{
				$.fun.alert({content:"Excel 출력에 실패했습니다."});
		 	}
		}
	});
}


function selectView(sel) {
	recordSearchForm.submit() ;
}


function resetButton() {
	location.href="./recordSearchAndPlay.do";
}
</script>