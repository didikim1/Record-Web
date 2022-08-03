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
	var ttsMent 	= $("[name=ttsMent]").val();

	var validDate 			= /[0-9]{4}-[0-9]{2}-[0-9]{2}/;
	var validNumber 		= /^[0-9]*$/;

/* 	if (  ( sDate == "" || eDate == "" ) == true) {
		$.fun.alert({content:"시작일 또는 종료일을 선택해주세요."});
		return;
	}
	else {
		if (  validDate.test(sDate) == false || validDate.test(eDate) == false ) {
			$.fun.alert({content:"날짜는 지정된 형식으로 입력하세요."});
			return;
		}
	} 
*/

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

function fnOpenRegisterPage(){
	$.fun.layout({
		id:"induacaAdd",
		"content":fnOpenRegisterPageHTML(),
		"title":"TTS생성",
		"width":470,
		"height": 350,
		"buttons":{}
	});
}

function fnOpenRegisterPageHTML(){
		
		var innerHTML = "";
		
		innerHTML += '<div id="layout_content_popup_sub">';
		innerHTML += '	<div class="content">';
		innerHTML += '		<div class="border_sub">';
		innerHTML += '			<form name="registerForm">';
		innerHTML += '				<div align="center" >';
		innerHTML += '					<table class="htable">';
		innerHTML += '						<tr>';
		innerHTML += '							<td><input type="text" class="userManageInput" name="name" placeholder="파일명을 입력하세요" name="ttsMent"></td>';
		innerHTML += '						</tr>';
		innerHTML += '						<tr>';
		innerHTML += '							<td><textarea class="textareaTTS" style="resize: none;" placeholder="TTS를 입력하세요" name="ttsMent"></textarea><button type="button" class="userManageButtonTTSplay" onclick="#">▶</button></td>';
		innerHTML += '						</tr>';
		innerHTML += '					</table>';
		innerHTML += '				</div>';
		innerHTML += '				<div class="border margin_l7">';
		innerHTML += '					<button type="reset" class="userManageButtonTTS">삭제</button>';
		innerHTML += '					<button type="button" class="userManageButtonTTS" onclick="fnRegisterPageProc()">등록</button>';
		innerHTML += '				</div>';
		innerHTML += '			</form>';
		innerHTML += '		</div>';
		innerHTML += '	</div>';
		innerHTML += '</div>';
		
		return innerHTML;
	}


function fnRegisterPageProc(){
	
	 var name			= $("[name=name]").val();
	 var ttsMent		= $("[name=ttsMent]").val();
	 if (isNull(name) && isNull(ttsMent)){
			$.fun.alert({
				content : "등록 할 사항이 없습니다. ",
			});
	 }else if( isNull(name) ){
			$.fun.alert({
				content : "파일명을 입력해 주세요.",
				action : function() {
					$("[name=name]").focus();
				}
			});
	 }else if( isNull(ttsMent) ){
			$.fun.alert({
				content : "TTS멘트를 입력해 주세요.",
				action : function() {
					$("[name=ttsMent]").focus();
				}
			});
	 }else {
		$.ajax({
			type:'get',
			data: $("[name=registerForm]").serialize(),
			url:'./RegisterData.do',
			dataType : "json",
			success:function(data){
				$.fun.alert({content:"TTS가 등록되었습니다.", action:function(){
					location.reload();
				}});
			}
		});
	}
}
function fnDeleteData(name){

		var title = "["+name+"] TTS를 삭제 하시겠습니까?"
			
	 	$.fun.layout({
			id:"induacaAdd",
			"content":title,
			"title":"TTS 삭제",
			"width":400,
			"buttons":{
				"확인": function() {
					$.fun.ajax({
						type:'get',
						dataType:"JSON",
						url:"/makeTTS/DeleteData.do?name="+name,
						success:function(data){
							if( "200" == data.code ) {
								$.fun.alert({content:"정상 처리되었습니다.", action:function(){
									location.reload();
								}});
							} else {
								$.fun.alert({content:"Error!!!!!", action:function(){
									location.reload();
								}});
							}
						}
					});
				}, 
				"닫기": function() {
					$(this).dialog('destroy').remove();
				}
			} //button
		}); 
	} 

function selectView(sel) {
	recordSearchForm.submit() ;
}


function resetButton() {
	location.href="./recordSearchAndPlay.do";
}
</script>