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


function fnOpenRegisterPage(){
	$.fun.layout({
		id:"induacaAdd",
		"content":fnOpenRegisterPageHTML(),
		"title":"TTS생성",
		"width":470,
		"height": 300,
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
		innerHTML += '							<td><textarea class="textareaTTS" style="resize: none;"name="ttsMent" placeholder="TTS를 입력하세요" ></textarea><button type="button" class="userManageButtonTTSplay" onclick="fnMakeTTS()">▶</button></td>';
		innerHTML += '						</tr>';
		innerHTML += '					</table>';
		innerHTML += '				</div>';
		innerHTML += '				<div class="border margin_l7">';
		innerHTML += '					<button type="button" class="userManageButtonTTS" onclick="fnRegisterPageProc()">등록</button>';
		innerHTML += '					<button type="button"  class="userManageButtonTTSReset" onclick = "fnRegisterDelect()">지우기</button>';
		innerHTML += '				</div>';
		innerHTML += '			</form>';
		innerHTML += '		</div>';
		innerHTML += '	</div>';
		innerHTML += '</div>';
		
		return innerHTML;
	}
	
 function fnOpenUploadPage(){
	$.fun.layout({
		id:"induacaAdd",
		"content":fnOpenUploadPageHTML(),
		"title":"파일업로드",
		"width":470,
		"height": 250,
		"buttons":{}
	});
}

function fnOpenUploadPageHTML(){
		
		var innerHTML = "";
		innerHTML += '<div id="layout_content_popup_sub">';
		innerHTML += '	<div class="content">';
		innerHTML += '		<div class="border_sub">';
		innerHTML += '			<form id ="UploadForm" name="UploadForm" action="uploadFile" method ="post" enctype="multipart/form-data">';
		innerHTML += '				<div align="center" >';
		innerHTML += '					<table class="htable">';
 		innerHTML += '						<tr>';
		innerHTML += '							<td><input type ="text" class="InputTitle" name="ttsTitle" placeholder="타이틀을 입력하세요."></td>';
		innerHTML += '						</tr>';
		innerHTML +='       				<tr class="fileTR">';
		innerHTML +='          					<td>';
		innerHTML +='              					<div class="ipt_file_area makeFlex ai_C">';
		innerHTML +='                   			<div class="filebox makeFlex ai_FE">';
		innerHTML +='                       			<input class="upload-name" disabled="disabled">';
		innerHTML +='                      				<label>';
		innerHTML +='                          				파일 업로드 +';
		innerHTML +='                          				<input type="file" name = "fileName" id="fileName" multiple class="upload-hidden" onchange="uploadName(this)">';
		innerHTML +='                      				</label>';
		innerHTML +='                 				</div>';
		innerHTML +='                 				</div>';
		innerHTML +='             				</td>';
		innerHTML +='         				</tr>';
		innerHTML += '					</table>';
		innerHTML += '					</div>';
		innerHTML  += '				</div>'; 
		innerHTML += '				<div class="border margin_l7">';
		innerHTML += '				<button type="button" class="userManageButtonTTS" onclick="fnRegisterTitle()">등록</button>';
		innerHTML += '				<button type="button"  class="userManageButtonTTSReset" onclick = "fnRegisterCancel()">취소</button>';
		innerHTML += '				</div>';
		innerHTML += '			</form>';
		innerHTML += '		</div>';
		innerHTML += '	</div>';
		innerHTML += '</div>';
		
		return innerHTML;
	}
 

function fnRegisterPageProc(){
	
	 var ttsMent		= $("textarea#[name=ttsMent]").val();
	 
	 if (isNull(ttsMent)){
			$.fun.alert({
				content : "입력된 내용이 없습니다. ",
			});
	 }else {
				$.fun.ajax({
					type:'get',
					data: $("[name=registerForm]").serialize(),
					url:'./RegisterData.do',
					dataType : "json",
					success:function(data){
						if( "200" == data.code ){
							$.fun.alert({content:"TTS가 등록되었습니다.", action:function(){
								location.reload();
							}});
						}else {
							$.fun.alert({content:"등록실패", action:function(){
							location.reload();
				}});
			}
		  } 
		}); 
	}
}

 function fnRegisterTitle(){
	
	 var ttsTitle		= $("[name=ttsTitle]").val();
	 var fileName		= $("[name=fileName]").val();
	 var formData 		= new FormData($("#UploadForm")[0]);
	
 	 if (isNull(ttsTitle)){
			$.fun.alert({
				content : "타이틀을 입력하세요.",
			}); 
 	 }else if (isNull(fileName)){
			$.fun.alert({
				content : "등록할 파일이없습니다.",
			});
	 }else {
				$.fun.ajax({
					type:'post',
					url:'./uploadFile.do',
					data: formData,
					processData : false,
					contentType : false,
			        async    	: false,
					success:function(data){
						$.fun.alert({content:"파일업로드 되었습니다.", action:function(){
							location.reload();
					}
				})
			}
		})
	} 
 }
function fnDeleteData(seq){
	 
		var title = "["+name+"] 를 삭제 하시겠습니까?"
			
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
						url:"/makeTTS/DeleteData.do?seq="+seq,
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
	
function fnRegisterCancel(){
		location.reload();
	} 
	
function fnOpenRegisterContentPage(seq){
	$.fun.ajax({
		type:'get',
		url:"/makeTTS/RegisterContent.do?seq="+seq,
		success:function(data){
			$.fun.layout({
				id:"induacaAdd",
				"content":data,
				"title":"TTS상세내역",
				"width":475,
				"buttons":{}
			});
		}
	});
}

function fnMakeTTS(){
	$.fun.ajax({
		type:'get',
		url:"./makeFile.do",
		data:  $("textarea#[name=ttsMent]"),
		success:function(data){
				$.fun.alert({content:"파일이 생성 되었습니다.", action:function(){
				}});
			}
	});
}

function fnRegisterDelect(){
	$.fun.ajax({
		type:'get',
		url:"./DelectFile.do",
		data:  $("textarea#[name=ttsMent]"),
		success:function(data){
				$.fun.alert({content:"파일이 삭제되었습니다.", action:function(){
				}});
		}
	});
}


function selectView(sel) {
	recordSearchForm.submit() ;
}


function resetButton() {
	location.href="./recordSearchAndPlay.do";
}


/*  upload-name 에 파일명을 불러오기 */ 
function uploadName(e){
    var files = e.files;
    var filename = files[0].name;  //console.log(filename);
    // 추출한 파일명 삽입 
    var upload_name = e.parentNode.previousElementSibling;
    upload_name.value = filename;
}  //uploadName
 
</script>