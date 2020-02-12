/**
 * Global Variables
 */
var callbackFunction = '';
var returnValue = null;
var paramValue = null;

/*-------------------------------- S : Design --------------------------------*/
/**
 * 링크시 점선 없애는 스크립트
 */
function bluring() {
    if (event.srcElement.tagName == 'A' || event.srcElement.tagName == 'IMG')
        document.body.focus();
}
document.onfocusin = bluring;

/**
 * Design MM_ function
 */
function MM_swapImgRestoreTop() {
    var i, x, a = document.MM_sr;
    for (i = 0; a && i < a.length && (x = a[i]) && x.oSrc; i++) {
        x.src = x.oSrc;
    }
}
function MM_swapImgRestore() { // v3.0
    var i, x, a = document.MM_sr;
    for (i = 0; a && i < a.length && (x = a[i]) && x.oSrc; i++)
        x.src = x.oSrc;
}
function MM_preloadImages() { // v3.0
    var d = document;
    if (d.images) {
        if (!d.MM_p)
            d.MM_p = new Array();
        var i, j = d.MM_p.length, a = MM_preloadImages.arguments;
        for (i = 0; i < a.length; i++)
            if (a[i].indexOf('#') != 0) {
                d.MM_p[j] = new Image;
                d.MM_p[j++].src = a[i];
            }
    }
}
function MM_findObj(n, d) { // v4.01
    var p, i, x;
    if (!d)
        d = document;
    if ((p = n.indexOf('?')) > 0 && parent.frames.length) {
        d = parent.frames[n.substring(p + 1)].document;
        n = n.substring(0, p);
    }
    if (!(x = d[n]) && d.all)
        x = d.all[n];
    for (i = 0; !x && i < d.forms.length; i++)
        x = d.forms[i][n];
    for (i = 0; !x && d.layers && i < d.layers.length; i++)
        x = MM_findObj(n, d.layers[i].document);
    if (!x && d.getElementById)
        x = d.getElementById(n);
    return x;
}
function MM_swapImage() { // v3.0
    var i, j = 0, x, a = MM_swapImage.arguments;
    document.MM_sr = new Array;
    for (i = 0; i < (a.length - 2); i += 3)
        if ((x = MM_findObj(a[i])) != null) {
            document.MM_sr[j++] = x;
            if (!x.oSrc)
                x.oSrc = x.src;
            x.src = a[i + 2];
        }
}
/*-------------------------------- E : Design --------------------------------*/

/*-------------------------------- S : String Prototype --------------------------------*/
/**
 * String.trim() - 공백제거
 */
String.prototype.trim = function() {
    return this.replace(/^\s+|\s+$/g, '');
}
/**
 * String.replaceAll(strSearch, strReplace) - 문자열치환
 */
String.prototype.replaceAll = function(strSearch, strReplace) {
    var returnValue = this;
    while (returnValue.indexOf(strSearch) != -1) {
        returnValue = returnValue.replace(strSearch, strReplace);
    }
    return returnValue;
}
/**
 * String.startsWith(prefix) - 문자열접두사일치여부
 */
String.prototype.startsWith = function(prefix) {
    if (this.substring(0, prefix.length) == prefix)
        return true;
    else
        return false;
}
/**
 * String.endsWith(suffix) - 문자열접미사일치여부
 */
String.prototype.endsWith = function(suffix) {
    if (this.substring(this.length - suffix.length) == suffix)
        return true;
    else
        return false;
}

/**
 * String HTML 특수문자를 변환
 */
String.prototype.htmlChars = function() {
    var str = ((this.replace('"', '&amp;')).replace('"', '&quot;')).replace('\'', '&#39;');
    return (str.replace('<', '&lt;')).replace('>', '&gt;');
}
/*-------------------------------- E : String Prototype --------------------------------*/

/*-------------------------------- S : Array Prototype --------------------------------*/
/**
 * Array.search(value) - array search value return index(없으면 -1)
 * 
 * @param value - 배열값
 * @return
 */
Array.prototype.search = function(value) {
    for ( var i = 0; i < this.length; i++)
        if (this[i] == value)
            return i;
    return -1;
}
/**
 * Array.replaceValue(strSearch, strReplace) - 배열원소값치환
 */
Array.prototype.replace = function(strSearch, strReplace) {
    for ( var i = 0; i < this.length; i++) {
        if (typeof (this[i]) == 'object' && this[i].length > 0)
            this[i].replace(strSearch, strReplace);
        else if (this[i] == strSearch)
            this[i] = strReplace;
    }
}
/**
 * Array.replaceAll(strSearch, strReplace) - 배열원소문자열치환
 */
Array.prototype.replaceAll = function(strSearch, strReplace) {
    for ( var i = 0; i < this.length; i++) {
        if (typeof (this[i]) == 'object' && this[i].length > 0)
            this[i].replaceAll(strSearch, strReplace);
        else
            this[i] = new String(this[i]).replaceAll(strSearch, strReplace);
    }
}
/**
 * 배열 중복 제거
 */
Array.prototype.unique = function()
{
 var a = {};
 for(var i=0; i <this.length; i++){
  if(typeof a[this[i]] == "undefined")
   a[this[i]] = 1;
 }
 this.length = 0;
 for(var i in a)
  this[this.length] = i;
 return this;
}
/*-------------------------------- E : Array Prototype --------------------------------*/

/*-------------------------------- S : Event --------------------------------*/
/**
 * keypressNumber() - onkeypress 이벤트(숫자만입력)
 * 
 * @return
 */
function keypressNumber() {
    if ((event.keyCode < 48) || (event.keyCode > 57))
        event.returnValue = false;
}
/**
 * keypressDotNumber() - onkeypress 이벤트(숫자/dot만입력)
 * 
 * @return
 */
function keypressDotNumber() {
    if (((event.keyCode < 48) || (57 < event.keyCode)) && (46 != event.keyCode))
        event.returnValue = false;
}
/**
 * keyupNextTab(input, next, inputlen) - onkeyup 이벤트(입력값체크후다음입력필드로이동)
 * 
 * @param input
 * @param next
 * @param inputlen
 * @return
 */
function keyupNextTab(input, next, inputlen) {
    if (input.value.length == inputlen) {
        next.focus();
        return;
    }
}
/*-------------------------------- E : Event --------------------------------*/

/*-------------------------------- S : Validation --------------------------------*/
/**
 * chkByte(str) - 문자열바이트체크
 * 
 * @param str
 * @return
 */
function chkByte(str) {
    var i, intLength = 0;
    for (i = 0; i < str.length; i++) {
        val = escape(str.charAt(i)).length;
        if (val == 6)
            intLength++;
        intLength++;
    }
    return intLength;
}

/**
 * chkByteUTF8 - 문자열 바이트 체크 UTF8버전
 * @param str
 * @returns {Number}
 */
function chkByteUTF8(str) {
    if (str == null || str.length == 0) {
      return 0;
    }
    var size = 0;

    for (var i = 0; i < str.length; i++) {
      size += charByteSize(str.charAt(i));
    }
    return size;
  }
function charByteSize(ch) {
    if (ch == null || ch.length == 0) {
      return 0;
    }

    var charCode = ch.charCodeAt(0);

    if (charCode <= 0x00007F) {
      return 1;
    } else if (charCode <= 0x0007FF) {
      return 2;
    } else if (charCode <= 0x00FFFF) {
      return 3;
    } else {
      return 4;
    }
  }



/**
 * isNumeric(str) - 숫자체크
 * 
 * @param str
 * @param allowDot - 소수점허용여부 
 * @return
 */
function isNumeric(str, allowDot) {
    var s = new String(str);
    if (allowDot == undefined || allowDot == 0)
        s = s.replace(/\./gi, '');

    var regExp = /\D/i;
    if (regExp.test(s))
        return false;
    else
        return true;
}
/**
 * isAlpha(str) - 영문체크
 * 
 * @param str
 * @return
 */
function isAlpha(str) {
    var regExp = /[^a-z]/i;
    if (regExp.test(str))
        return false;
    else
        return true;
}
/**
 * isAlphaNumeric(str) - 영문/숫자체크
 * 
 * @param str
 * @return
 */

function isAlphaNumeric(str) {
    var regExp = /[^a-z0-9]/i;
    if (regExp.test(str))
        return false;
    else
        return true;
}
/**
 * isHangul(str) - 한글체크(문자열에한글이외의값이있다면:false)
 * 
 * @param str
 * @return
 */
function isHangul(str) {
    if (str.length > 0) {
        for ( var i = 0; i < str.length; i++)
            if (str.charCodeAt(i) < 128)
                return false;
    }
    return true;
}
/**
 * isBlank(str) - 공백문자체크(공백으로만된경우:false)
 * 
 * @param str
 * @return
 */

function isBlank(str) {
    var regExp = /^[\s]+$/i;
    if (regExp.test(str) || str.length == 0)
        return false;
    else
        return true;
}
/**
 * isWithBlank(str) - 공백포함체크(문자열에공백이포함된경우 :false)
 * 
 * @param str
 * @return
 */
function isWithBlank(str) {
    var regExp = /\s/i;
    if (regExp.test(str))
        return false;
    else
        return true;
}
/**
 * isSpecialChar(str) - 영문,숫자,한글이 아닌것은 전부 특수문자.
 * 
 * @param str
 * @return
 */
function isSpecialChar(str) {
    if (str.length > 0) {
        for ( var i = 0; i < str.length; i++) {
            // 128이상인것은 한글, 48~57은 숫자, 65~90은알파벳 대문자, 97~122은 알파벳 소문자
            var chcode = str.charCodeAt(i);
            if (chcode < 128 && !(chcode >= 48 && chcode <= 57) && !(chcode >= 65 && chcode <= 90) && !(chcode >= 97 && chcode <= 122))
                return true;
        }
    }
    return false;
}
/**
 * chkMinMaxLength(input, min, max, inName) - 최소값/최대값체크
 * 
 * @param input
 * @param min - 0 인경우체크안함
 * @param max - 0 인경우체크안함
 * @param inName - 항목명('주소', '이메일', ..)
 * @return
 */
function chkMinMaxLength(input, min, max, inName) {
    if (min != 0 && chkByte(input.value) < min) {
        if (!input.value) {
            alert("\"" + inName + "\" 항목을 입력해 주세요.\t\r");
            input.focus();
            return false;
        } else {
            alert("\"" + inName + "\" 항목은 " + min + "자 이상 입력하셔야 합니다.[한글:2Byte]\t\r");
            input.focus();
            return false;
        }
    }
    if (max != 0 && chkByte(input.value) > max) {
        alert("\"" + inName + "\" 항목은 " + max + "자 이내로 입력하셔야 합니다.[한글:2Byte]\t\r");
        input.focus();
        return false;
    }

    return true;
}
function chkMinMaxLength2(input, min, max, inName) {
    if (min != 0 && chkByte(input.value) < min) {
        if (!input.value) {
            alert("\"" + inName + "\" 항목을 입력해 주세요.\t\r");
            input.focus();
            return false;
        } else {
            alert("\"" + inName + "\" 항목은 " + min + "자 이상 입력하셔야 합니다.\t\r");
            input.focus();
            return false;
        }
    }
    if (max != 0 && chkByte(input.value) > max) {
        alert("\"" + inName + "\" 항목은 " + max + "자 이내로 입력하셔야 합니다.\t\r");
        input.focus();
        return false;
    }

    return true;
}
/**
 * chkMinMaxLengthUTF8(input, min, max, inName) - 최소값/최대값체크 (UTF-8버전)
 * 
 * @param input
 * @param min - 0 인경우체크안함
 * @param max - 0 인경우체크안함
 * @param inName - 항목명('주소', '이메일', ..)
 * @return
 */
function chkMinMaxLengthUTF8(input, min, max, inName) {
	if (min != 0 && chkByteUTF8(input.value) < min) {
        if (!input.value) {
            alert("\"" + inName + "\" 항목을 입력해 주세요.");
            input.focus();
            return false;
        } else {
            alert("\"" + inName + "\" 항목은 " + min + "자 이상 입력하셔야 합니다.\t\r");
            input.focus();
            return false;
        }
    }
    if (max != 0 && chkByte(input.value) > max) {
        alert("\"" + inName + "\" 항목은 " + max + "자 이내로 입력하셔야 합니다.\t\r");
        input.focus();
        return false;
    }

    return true;
}
/**
 * chkMinMaxNumeric(input, min, max, inName, allowMinus) - 숫자체크/최소값/최대값
 * 
 * @param input
 * @param min
 * @param max
 * @param inName
 * @param allowMinus
 * @return
 */
function chkMinMaxNumeric(input, min, max, inName, allowMinus) {
    var tempStr;

    tempStr = input.value;
    tempStr = '' + tempStr.replace(/,/gi, '');
    tempStr = tempStr.replace(/(^\s*)|(\s*$)/g, '');

    if (input.value == '') {
        alert("\"" + inName + "\" 항목을 입력해 주세요.\t\r");
        input.focus();
        return false;
    }
    if (isNaN(new Number(tempStr))) {
        alert("\"" + inName + "\" 항목은 숫자만 입력해 주세요.\t\r");
        input.focus();
        return false;
    }
    if (allowMinus == undefined || allowMinus == 0) {
        if (tempStr < 0) {
            alert('마이너스 수치는은 입력할 수 없습니다.\t\r');
            input.focus();
            return false;
        }
    }

    if (min != 0 && tempStr < min) {
        alert("\"" + inName + "\" 항목의 수치는 " + min + " 이상 입력하셔야 합니다.\t\r");
        input.focus();
        return false;
    }
    if (max != 0 && tempStr > max) {
        alert("\"" + inName + "\" 항목의 수치는 " + max + " 이하로 입력하셔야 합니다.\t\r");
        input.focus();
        return false;
    }

    return true;
}
/**
 * chkId(input) - 아이디입력값체크
 * 
 * @param input
 * @return
 */
function chkId(input) {
    var str = input.value;
    var firstChar = str.charAt(0);

    if (str.length < 4 || str.length > 20) {
        alert("아이디를 입력해주세요.\t\r아이디는 영문자, 숫자 4자 이상 20자 이내로 입력하셔야 합니다.\t\r");
        input.value = "";
        input.focus();
        return false;
    }
    var regExp = /[^a-z]/i;
    if (regExp.test(firstChar)) {
        alert("아이디의 첫글자는 영문자로 시작해야 됩니다.\t\r");
        input.value = "";
        input.focus();
        return false;
    }
    if (!isAlphaNumeric(str)) {
        alert("아이디는 영문자와 숫자만 사용가능합니다.\t\r");
        input.value = "";
        input.focus();
        return false;
    }

    return true;
}
/**
 * chkPw(input, input2, min) - 비밀번호입력값체크
 * 
 * @param input
 * @param input2
 * @param min
 * @return
 */
function chkPw(input, input2, min) {
    if (!input.value) {
        alert("비밀번호를 입력해 주세요.\t\r");
        input.focus();
        return false;
    } else if (input.value.length < min) {
        alert("비밀번호는 " + min + "자이상 입력하셔야 합니다.\t\r");
        input.focus();
        input.value = "";
        input2.value = "";
        return false;
    } else if (!input2.value) {
        alert("비밀번호확인을 입력해 주세요\t\r");
        input2.focus();
        return false;
    } else if (input.value != input2.value) {
        alert("비밀번호와 비밀번호 확인이 틀립니다.\t\r다시 입력해주세요.\t\r");
        input2.value = "";
        input2.focus();
        return false;
    }

    return true;
}
/**
 * 닉네임입력값체크
 * 
 * @param input
 * @return
 */
function chkNick(input) {
    var str = input.value;

    if (str == "") {
        alert('닉네임을 입력해주세요.\t\r');
        input.focus();
        return false;
    }
    if (!isWithBlank(str)) {
        alert('닉네임은 공백을 사용할수 없습니다.\n\n공백을 제거해주세요.\t\r')
        input.focus();
        return false;
    }
    if (isHangul(str)) {
        var length = chkByte(str);
        // only한글의경우 10byte까지
        if (length > 10) {
            alert("닉네임을 한글로만 입력할때에는\n\n5자이내로 입력해야 합니다.\t\r");
            input.focus();
            return false;
        }
    }
    if (!isHangul(str)) {
        var length = chkByte(str);
        // only한글이 아닌 경우 9byte까지
        if (length > 9) {
            alert("닉네임을 한글로만 입력하지 않을때에는 \n\n9byte이내로 입력해야 합니다.(한글 2byte, 영문숫자 1byte)\t\r");
            input.focus();
            return false;
        }
    }
    if (isSpecialChar(str)) {
        alert('특수문자는 사용 하실수 없습니다.ex)~!@#$%^&*()...등')
        input.focus();
        return false;
    }

    return true;
}
/**
 * chkHanName(input) - 한글명이름만입력받을경우
 * 
 * @param input
 * @return
 */
function chkHanName(input) {
    if (!isBlank(input.value)) {
        alert("이름을 입력해 주세요.\t\r");
        input.focus();
        return false;
    }
    if (!isHangul(input.value)) {
        alert("이름 항목은 한글만 입력하셔야 합니다.\t\r");
        input.focus();
        return false;
    }

    return true;
}
/**
 * chkJumin(num) - 주민등록번호유효체크
 * 
 * @param num
 * @return
 */
function chkJumin(num) {
    var reg = /([0-9]{6})-?([0-9]{7})/;
    if (!reg.test(num))
        return false;

    var ssn = RegExp.$1 + RegExp.$2 + RegExp.$3;
    var sum = 0;
    var digit = "234567892345";

    for ( var i = 0; i < 12; i++)
        sum += parseInt(ssn.charAt(i)) * parseInt(digit.charAt(i));

    var result = (11 - (sum % 11)) % 10;
    var check = parseInt(ssn.charAt(12));
    if (result != check)
        return false;

    return true;
}
/**
 * chkCompNum(str) - 사업자번호유효체크
 * 
 * @param str
 * @return
 */
function chkCompNum(str) {
    var reg = /([0-9]{3})-?([0-9]{2})-?([0-9]{5})/;
    if (!reg.test(str))
        return false;

    var strCompNum = RegExp.$1 + RegExp.$2 + RegExp.$3;
    if (strCompNum == "0000000000")
        return false;

    var intSum = 0;
    var strDigit = "13713713";
    var intEnd = strCompNum.charAt(9);

    for ( var i = 0; i < 8; i++)
        intSum = intSum + (parseInt(strCompNum.charAt(i)) * parseInt(strDigit.charAt(i))) % 10;

    var strTemp = parseInt(strCompNum.charAt(8)) * 5 + "0";
    var intChk = parseInt(strTemp.charAt(0)) + parseInt(strTemp.charAt(1));
    var intMatch = (10 - (intSum + intChk) % 10) % 10;

    if (intEnd != intMatch)
        return false;

    return true;
}
/**
 * chkEmail(str) - 이메일체크
 * 
 * @param str
 * @return
 */
function chkEmail(str) {
    // var regExp = /^[a-z0-9]{2,}@([a-z0-9-]+){2,}(\.[a-z]{2,}){1,}$/i;
    var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
    if (regExp.test(str))
        return true;
    else
        return false;
}
/**
 * chkExtension(String strFileName, String strExtension, boolean allowDeny) -
 * input file 확장자 체크
 * 
 * @param strFileName
 * @param strExtension - 확장자스트링 (구분자 '|')
 * @param isDeny - false : strExtension 에 등록된 확장자만 허용, true : strExtension 에
 *            등록된것을 제외한 확장자만 허용
 * @return
 */
function chkExtension(strFileName, strExtension, isDeny) {
    var extension = "";
    var isMatches = isDeny;
    var index = strFileName.lastIndexOf('.');
    var arrayExtension = strExtension.split("|");

    if (index != -1)
        extension = strFileName.substring(index + 1, strFileName.length);
    if (extension.lastIndexOf('\\') != -1)
        extension = "";
    if (extension == "")
        return false;

    for (i = 0; i < arrayExtension.length; i++) {
        if (extension.toLowerCase() == arrayExtension[i]) {
            isMatches = !isMatches;
            break;
        }
    }

    return isMatches;
}
/**
 * chkSelectBox(input, inName) - select box 0번째 옵션이 선댁되었는지 체크
 * 
 * @param input
 * @param inName - 항목명('년', '월', ..)
 * @return
 */
function chkSelectBox(input, inName) {
    if (input.selectedIndex == 0) {
        alert("\"" + inName + "\" 항목을 선택해 주세요.\t\r");
        input.focus();
        return false;
    }
    return true;
}
/*-------------------------------- E : Validation --------------------------------*/

/*-------------------------------- S : Cookie --------------------------------*/
function getCookie(name) {
    var name = name + "=";
    var nLen = name.length;
    var cLen = document.cookie.length;
    var i = 0;

    while (i < cLen) {
        var j = i + nLen;
        if (document.cookie.substring(i, j) == name) {
            var end = document.cookie.indexOf(";", j);
            if (end == -1)
                end = document.cookie.length;
            return unescape(document.cookie.substring(j, end)); // 쿠키값 반환
        }
        i = document.cookie.indexOf(" ", i) + 1;
        if (i == 0) {
            break;
        }
    }
    return "";
}
function setCookie(name, value) {
    var expires = new Date();
    var path, domain, secure;
    var arg = setCookie.arguments;
    var aLen = setCookie.arguments.length;

    if (aLen > 2) {
        expires.setTime(expires.getTime() + (1000 * 60 * arg[2])); // arg[2] :
        // 분단위로 호출
    } else {
        expires = null;
    }
    path = (aLen > 3) ? arg[3] : null;
    domain = (aLen > 4) ? arg[4] : null;
    secure = (aLen > 5) ? arg[5] : false;

    document.cookie = name + "=" + escape(value) + ((expires == null) ? "" : (";expires=" + expires.toGMTString())) + ((path == null) ? "" : (";path=" + path)) + ((domain == null) ? "" : (";domain=" + domain)) + ((secure == true) ? " ;secure" : "");
}
function delCookie(name) {
    var expires = new Date();
    expires.setDate(expires.getDate() - 1);
    document.cookie = name + "=" + (";expires=" + expires.toGMTString()) + (";path=/");
}
/*-------------------------------- E : Cookie --------------------------------*/

/*-------------------------------- S : Form Control --------------------------------*/
/**
 * 체크박스선택값 배열로리턴
 * 
 * @param listChkName
 * @return
 */
function checkboxCheckerValues(listChkName) {
    var listChkObj = document.getElementsByName(listChkName);
    var returnValue = new Array();

    for ( var i = 0; i < listChkObj.length; i++)
        if (listChkObj[i].checked)
            returnValue.push(listChkObj[i].value);

    return returnValue;
}
/**
 * 체크박스전체선택/해제
 * 
 * @param listChkName
 * @param allChkName
 * @param isAll
 * @return
 */
function checkboxCheckerAll(listChkName, allChkName, isAll) {
    var isAllChecked = true;
    var listChkObj = document.getElementsByName(listChkName);
    var allChkObj = document.getElementById(allChkName);

    if (!isNull(isAll)) {
        for ( var i = 0; i < listChkObj.length; i++)
            listChkObj[i].checked = allChkObj.checked;
    } else {
        for ( var i = 0; i < listChkObj.length; i++) {
            if (!listChkObj[i].checked) {
                isAllChecked = false;
                break;
            }
        }
        allChkObj.checked = isAllChecked;
    }
}
/**
 * 라디오객체 선택
 * 
 * @param obj
 * @param checkValue
 * @return
 */
function radioChecker(obj, checkValue) {
    for ( var i = 0; i < obj.length; i++)
        if (obj[i].value == checkValue)
            obj[i].checked = true;
}
/**
 * 셀렉트박스 선택
 * 
 * @param obj
 * @param checkValue
 * @return
 */
function optionSelector(obj, checkValue) {
    for ( var i = 0; i < obj.length; i++) {
        if (obj.options[i].value == checkValue) {
            obj.selectedIndex = i;
            break;
        }
    }
}
/**
 * 체크박스선택
 * 
 * @param obj
 * @param checkValue
 * @return
 */
function checkboxChecker(obj, checkValue) {
    if (obj.value == checkValue)
        obj.checked = true;
}
/**
 * HTML FORM SELECT OPTIONS NUMERIC
 * 
 * @param obj
 * @param start
 * @param end
 * @return
 */
function makeOptionsNumeric(obj, start, end) {
    var optionIndex = 1;
    obj.options.length = optionIndex;

    if (start < end) {
        for ( var i = start; i <= end; i++) {
            optionIndex = obj.options.length;
            obj.options.length++;
            obj.options[optionIndex].text = i;
            obj.options[optionIndex].value = i;
        }
    } else {
        for ( var i = start; i >= end; i--) {
            optionIndex = obj.options.length;
            obj.options.length++;
            obj.options[optionIndex].text = i;
            obj.options[optionIndex].value = i;
        }
    }
}
/**
 * Input File Element 추가/삭제
 * 
 * @param targetName
 * @param elementName
 * @param action
 * @param current
 * @param countLimit
 * @return
 */
function appendFileElement(targetName, elementName, action, current, countLimit) {
    var targetNode = document.getElementById(targetName);
    var countChild = targetNode.childNodes.length;
    var div, input;

    if (action == '+') {
        if (!isNull(current))
            countChild += current;

        if (countChild > countLimit) {
            alert('더 이상 파일을 추가 할 수 없습니다.\t\r');
            return;
        }

        div = document.createElement('div');
        div.style.marginTop = '2px';
        input = document.createElement('input');
        input.type = 'file';
        input.id = elementName + '[' + (countChild + 1) + ']';
        input.name = elementName + '[' + (countChild + 1) + ']';
        input.className = 'input_18';
        input.style.height = '18px';

        div.appendChild(input);
        targetNode.appendChild(div);

    } else if (action == '-') {
        if (countChild > 0)
            targetNode.removeChild(targetNode.lastChild);
    }
    /*
     * alert(targetNode.firstChild.firstChild.id + ':' +
     * targetNode.lastChild.firstChild.id) for (var i = 0; i <
     * targetNode.childNodes.length; i++) { var obj =
     * targetNode.childNodes.item(i).firstChild; alert(obj.type + ':' +
     * obj.className + ':' + obj.id + ':' + obj.name ); }
     */
}
/**
 * Document Object Id
 * 
 * @param id
 * @return
 */
function getObjectId(id) {
    return document.all ? document.all[id] : document.getElementById ? document.getElementById(id) : null;
}
/**
 * prototype.js
 * 
 * @param element
 * @return
 */
function $$(element) {
    if (arguments.length > 1) {
        for ( var i = 0, elements = [], length = arguments.length; i < length; i++)
            elements.push($$(arguments[i]));
        return elements;
    }
    if (typeof element == 'string')
        element = document.getElementById(element);
    return element;
}
function isArray(o) {
    return Object.prototype.toString.call(o) == '[object Array]';
}
/*-------------------------------- E : Form Control --------------------------------*/

/*-------------------------------- S : Login --------------------------------*/
/**
 * inputLogin(f) - 로그인박스 입력값체크
 * 
 * @param input_id
 * @param input_pw
 * @param chkId
 * @return
 */
function inputLogin(input_id, input_pw, chkId) {
    if (input_id.value == "") {
        alert("아이디를 입력해주세요.\t\r");
        input_id.focus();
        return false;
    }
    if (input_pw.value == "") {
        alert("비밀번호를 입력해주세요.\t\r");
        input_pw.focus();
        return false;
    }
    if (chkId != undefined && chkId.checked == true) {
        setCookie("Cookie_chkMdId", input_id.value, 60 * 24 * 30, "/"); // 쿠키저장
        // 30일
    } else {
        delCookie("Cookie_chkMdId");
    }
    return true;
}
// '\아이디/닉네임/추천인 중복 확인'
var isCheckedId = false;
var isCheckedNick = false;
var isCheckedRecommId = false;

function idCheck(obj) {
    if (!chkId(obj)) return;
    ifHide.location.href = '/common/mb/idCheck.do?checkVal=' + obj.value;
}
function nickCheck(obj, strMbCd) {
    if (!chkNick(obj)) return;
    ifHide.location.href = '/common/mb/recommIdCheck.do?checkVal=' + obj.value + '&checkKey=' + strMbCd;
}
function recommIdCheck(obj, strMbCd) {
    if (isNull(obj.value)) {
        alert('추천인 아이디를 입력하세요.\t\r');
        return;
    }
    ifHide.location.href = '/common/mb/recommIdCheck.do?checkVal=' + obj.value;
}
function idCheckCallBack(isResult) {
    isCheckedId = isResult;
}
function nickCheckCallBack(isResult) {
    isCheckedNick = isResult;
}
function recommIdCheckCallBack(isResult) {
    isCheckedRecommId = isResult;
}
/*-------------------------------- E : Login --------------------------------*/

/*-------------------------------- S : Popup --------------------------------*/
/**
 * popWin(url, name, x, y)
 * 
 * @param url
 * @param name
 * @param x
 * @param y
 * @return
 */
function popWin(url, name, x, y) {
    var top, left, sc, re;
    var arg = popWin.arguments;
    var aLen = popWin.arguments.length;

    left = (aLen > 4 && arg[4] != 0) ? arg[4] : (screen.width - x) / 2;
    top = (aLen > 5 && arg[5] != 0) ? arg[5] : (screen.height - y) / 2;
    sc = (aLen > 6) ? arg[6] : 0;
    re = (aLen > 7) ? arg[7] : 0;

    var settings = 'height=' + y + ', width=' + x + ', ';
    settings += 'top=' + top + ', left=' + left + ', ';
    settings += 'scrollbars=' + sc + ', ';
    settings += 'resizable=' + re;

    winObj = window.open(url, name, settings);

    return winObj;
}
function selfClose() { 
    if (navigator.appVersion.indexOf("MSIE 7.0") >= 0) { 
        window.open("blank.html", "_self").close();
    } else if (navigator.appVersion.indexOf("MSIE 8.0") >= 0) { 
        window.open("blank.html", "_self").close();
    } else {
        self.close(); 
    }
}
function aboutBlank() {
    self.location.replace('about:blank');
}
/*-------------------------------- E : Popup --------------------------------*/

/*-------------------------------- S : Number Format --------------------------------*/
function fn_DisplayComma(value) {
    var src;
    var i;
    var factor;
    var su;
    var Spacesize = 0;
    value = value.toString();
    var arrValue = value.split(".");

    // var String_val = value.toString();
    var String_val = arrValue[0];

    factor = String_val.length % 3;
    su = (String_val.length - factor) / 3;
    src = String_val.substring(0, factor);

    for (i = 0; i < su; i++) {
        if ((factor == 0) && (i == 0)) {
            src += String_val.substring(factor + (3 * i), factor + 3 + (3 * i));
        } else {
            if (String_val.substring(factor + (3 * i) - 1, factor + (3 * i)) != "-")
                src += ",";
            src += String_val.substring(factor + (3 * i), factor + 3 + (3 * i));
        }
    }

    if (src == 0) {
        src = 0;
    }

    if (arrValue.length == 2) {
        src += "." + arrValue[1];
    }

    return src;
}
/**
 * 숫자콤마표시
 * 
 * @param nu
 * @param allowDot
 * @return
 */
function numericComma(nu, allowDot) {
    var sign = "";

    nu = new String(nu);
    nu = nu.replace(/,/gi, "");

    if (allowDot == undefined || allowDot == 0) {
        nu = nu.replace(/\./gi, "");
    }

    nu = new Number(nu);

    if (isNaN(nu)) {
        alert("숫자만 입력할 수 있습니다.");
        return 0;
    }
    if (nu == 0) {
        return nu;
    }
    if (nu < 0) {
        // nu = nu * (-1);
        // sign = "-";
        return 0;
    } else {
        nu = nu * 1;
    }

    nu = new String(nu);
    var temp = "";
    var pos = 3;

    nu_len = nu.length;
    while (nu_len > 0) {
        nu_len = nu_len - pos;
        if (nu_len < 0) {
            pos = nu_len + pos;
            nu_len = 0;
        }
        temp = "," + nu.substr(nu_len, pos) + temp;
    }

    return sign + temp.substr(1);
}
/**
 * 조억만형태로표시
 * 
 * @param n
 * @param obj
 * @return
 */
function numericText(n, obj) {
    if (n == '' || n == '0') {
        if (obj != undefined) {
            obj.value = '';
        }
        return '';
    }

    n = new String(n);
    n = n.replace(/,/gi, '');

    var returnValue = '';
    var len = n.length;
    var temp1 = '';
    var temp2 = '';

    if (len / 4 > 3 && len / 4 <= 4) {
        if (len % 4 == 0) {
            temp1 = ciphers_han(n.substring(0, 4)) + '조' + ciphers_han(n.substring(4, 8)) + '억' + ciphers_han(n.substring(8, 12)) + '만' + ciphers_han(n.substring(12, 16));
        } else {
            temp1 = ciphers_han(n.substring(0, len % 4)) + '조' + ciphers_han(n.substring(len % 4, len % 4 + 4)) + '억' + ciphers_han(n.substring(len % 4 + 4, len % 4 + 8)) + '만' + ciphers_han(n.substring(len % 4 + 8, len % 4 + 12));
        }
    } else if (len / 4 > 2 && len / 4 <= 3) {
        if (len % 4 == 0) {
            temp1 = ciphers_han(n.substring(0, 4)) + '억' + ciphers_han(n.substring(4, 8)) + '만' + ciphers_han(n.substring(8, 12));
        } else {
            temp1 = ciphers_han(n.substring(0, len % 4)) + '억' + ciphers_han(n.substring(len % 4, len % 4 + 4)) + '만' + ciphers_han(n.substring(len % 4 + 4, len % 4 + 8));
        }
    } else if (len / 4 > 1 && len / 4 <= 2) {
        if (len % 4 == 0) {
            temp1 = ciphers_han(n.substring(0, 4)) + '만' + ciphers_han(n.substring(4, len));
        } else {
            temp1 = ciphers_han(n.substring(0, len % 4)) + '만' + ciphers_han(n.substring(len % 4, len));
        }
    } else if (len / 4 <= 1) {
        temp1 = ciphers_han(n.substring(0, len));
    }

    for ( var i = 0; i < temp1.length; i++) {
        temp2 = temp2 + numericHangul(temp1.substring(i, i + 1));
    }

    returnValue = new String(temp2);
    returnValue = returnValue.replace(/억 만/gi, '억 ');
    returnValue = returnValue.replace(/조 억/gi, '조 ');

    if (obj == undefined)
        return returnValue;
    else
        obj.value = returnValue;
}
function ciphers_han(n) {
    var len = n.length;
    var temp = '';

    if (len == 1) {
        temp = n;
    } else if (len == 2) {
        temp = n.substring(0, 1) + '십' + n.substring(1, 2);
    } else if (len == 3) {
        temp = n.substring(0, 1) + '백' + n.substring(1, 2) + '십' + n.substring(2, 3);
    } else if (len == 4) {
        temp = n.substring(0, 1) + '천' + n.substring(1, 2) + '백' + n.substring(2, 3) + '십' + n.substring(3, 4);
    }

    n = new String(temp);
    n = n.replace(/0십/gi, '');
    n = n.replace(/0백/gi, '');
    n = n.replace(/0천/gi, '');

    return n;
}
/**
 * 숫자한글표기
 * 
 * @param n
 * @return
 */
function numericHangul(n) {
    if (n == '1')
        return '일';
    else if (n == '2')
        return '이';
    else if (n == '3')
        return '삼';
    else if (n == '4')
        return '사';
    else if (n == '5')
        return '오';
    else if (n == '6')
        return '육';
    else if (n == '7')
        return '칠';
    else if (n == '8')
        return '팔';
    else if (n == '9')
        return '구';
    else if (n == '십')
        return '십';
    else if (n == '백')
        return '백';
    else if (n == '천')
        return '천';
    else if (n == '만')
        return '만 ';
    else if (n == '억')
        return '억 ';
    else if (n == '조')
        return '조 ';
    else if (n == '0')
        return '';
    else
        return '';
}
/**
 * 통화단위
 * 
 * @param srcNumber
 * @param unit
 * @return
 */
function formatMoney(srcNumber, unit) {
    var returnValue = '';

    var arrayUnit = [ '양', '서', '해', '경', '조', '억', '만', '' ];
    var arrayCount = arrayUnit.length;
    var arrayMoney = [];
    var strTemp = null;
    strTemp = strDefault(srcNumber, '0');

    var minus = '';
    if ('-' == strTemp.substring(0, 1))
        minus = '-';
    strTemp = strTemp.replaceAll(',', '');
    strTemp = strTemp.replaceAll('-', '');
    strTemp = leftPad(strTemp, arrayCount * 4, '0');

    if (!isNumeric(strTemp))
        return '0';

    for ( var i = 0; i < arrayCount; i++) {
        arrayMoney[i] = strTemp.substring(i * 4, (i + 1) * 4);
        arrayMoney[i] = fn_DisplayComma(parseInt(arrayMoney[i], 10));

        if ('0' == arrayMoney[i])
            arrayMoney[i] = '';
        else
            arrayMoney[i] = arrayMoney[i] + arrayUnit[i];
    }

    returnValue = arrayMoney.join('');
    if ('' == returnValue)
        returnValue = '0' + arrayUnit[arrayCount - 1];

    return minus + returnValue + strDefault(unit);
}
/**
 * 숫자체크
 * 
 * @param arrayObj - obj Array
 * @param allowMinus - 마이너스 허용여부
 * @return
 */
function chkNumericArrayObj(arrayObj, allowMinus) {
    var chkArray = new Array();
    var str;

    if (isArray(arrayObj))
        chkArray = arrayObj;
    else
        chkArray.push(arrayObj);

    for ( var i = 0; i < chkArray.length; i++) {
        str = replaceComma(chkArray[i].value);

        if (isNaN(new Number(str))) {
            alert('숫자만 입력해 주세요.\t\r');
            chkArray[i].focus();
            return false;
        }

        if (isNull(allowMinus)) {
            if (str < 0) {
                alert('음수는 입력 할 수 없습니다.\t\r');
                chkArray[i].focus();
                return false;
            }
        }
    }

    return true;
}
/**
 * 콤마 치환
 * 
 * @param str
 * @return
 */
function replaceComma(str) {
    var returnValue;
    returnValue = str;
    returnValue = '' + returnValue.replace(/,/gi, '');
    returnValue = returnValue.replace(/(^\s*)|(\s*$)/g, '');
    return returnValue;
}
moneyformatter = function(cellval, el, opts){
    return fn_DisplayComma(parseInt(cellval));
}
/**
 * Button Click Money
 * 
 * @param n
 * @param inObj
 * @param divObj
 * @return
 */
function btnMoney(n, inObj, divObj) {
    if (n == '' || n == '0') {
        inObj.value = 0;
        if (isNotNull(divObj))
            divObj.innerHTML = '';
    } else {
        var inValue = '' + (Number(inObj.value.replace(/,/gi, '')) + n);
        inObj.value = numericComma(inValue);
        if (isNotNull(divObj))
            divObj.innerHTML = numericText(inValue);
    }
}
/*-------------------------------- E : Number Format --------------------------------*/

/*-------------------------------- S : Date --------------------------------*/
/**
 * chkDate(year, month, day) - 날짜유효체크
 * 
 * @param inYear
 * @param inMonth
 * @param inDay
 * @return
 */
function chkDate(inYear, inMonth, inDay) {
    var year, month, day;

    if (new String(inYear).length == 8 && inMonth == undefined && inDay == undefined) {
        year = new String(inYear).substr(0, 4);
        month = new String(inYear).substr(4, 2);
        day = new String(inYear).substr(6, 2);
    } else {
        year = inYear;
        month = inMonth;
        day = inDay;
    }

    var inDate = Math.abs(month) + "/" + Math.abs(day) + "/" + Math.abs(year);
    var objDate = new Date(Date.parse(inDate));
    var cmpDate = (objDate.getMonth() + 1) + "/" + objDate.getDate() + "/" + objDate.getFullYear();

    if (cmpDate == "NaN/NaN/NaN")
        return 0;
    else if (inDate == cmpDate)
        return 1;
    else
        return -1;
}
/**
 * 미래의 일자인지 체크
 * 
 * @param inYear
 * @param inMonth
 * @param inDay
 * @return
 */
function chkFutureDate(inYear, inMonth, inDay) {
    var returnValue;
    var strYmd;

    if (new String(inYear).length == 8 && inMonth == undefined && inDay == undefined)
        strYmd = inYear;
    else
        strYmd = inYear + '' + inMonth + '' + inDay;

    returnValue = chkDate(strYmd);
    if (returnValue == 1) {
        if (new Number(getDate('TODAY')) < new Number(strYmd))
            returnValue = -2;
    }

    return returnValue;
}
/**
 * Button Click Date
 * 
 * @param scope
 * @param sDateObj
 * @param eDateObj
 * @return
 */
function btnDate(scope, sDateObj, eDateObj) {
    var inDate = scopeDate(scope);

    if (isNotNull(sDateObj) && isNotNull(eDateObj)) {
        sDateObj.value = inDate[0];
        eDateObj.value = inDate[1];
    } else
        return inDate;
}
function btnSingleDate(scope, dateObj) {
    if (isNotNull(dateObj)) {
        dateObj.value = getDate(scope);
    } else
        return inDate;
}
/**
 * 시작일자~종료일자 (금일, 전일, 금주, 전주, 금월, 전월)
 * 
 * @param scope
 * @param delimiter
 * @return
 */
function scopeDate(scope, delimiter) {
    var sDate = new Date();
    var eDate = new Date();

    switch (scope) {
        case "TODAY":
            break;
        case "YESTERDAY":
            sDate.setDate(sDate.getDate() - 1);
            eDate.setDate(eDate.getDate() - 1);
            break;
        case "THIS_WEEK":
            sDate.setDate(sDate.getDate() - sDate.getDay());
            break;
        case "LAST_WEEK":
            sDate.setDate(sDate.getDate() - (7 + sDate.getDay()));
            eDate.setDate(eDate.getDate() - (eDate.getDay() + 1));
            break;
        case "THIS_MONTH":
            sDate.setMonth(sDate.getMonth(), 1);
            break;
        case "LAST_MONTH":
            sDate.setMonth(sDate.getMonth() - 1, 1);
            eDate.setMonth(eDate.getMonth(), 0);
            break;
    }

    var seDate = new Array(2);
    delimiter = (isNull(delimiter)) ? "" : delimiter;
    seDate[0] = sDate.getFullYear() + delimiter + leftPad(sDate.getMonth() + 1, 2) + delimiter + leftPad(sDate.getDate(), 2);
    seDate[1] = eDate.getFullYear() + delimiter + leftPad(eDate.getMonth() + 1, 2) + delimiter + leftPad(eDate.getDate(), 2);

    return seDate;
}
/**
 * 일자반환
 * 
 * @param scope
 * @param delimiter
 * @return
 */
function getDate(scope, delimiter) {
    var sDate = new Date();

    switch (scope) {
        case "TODAY":
            break;
        case "YESTERDAY":
            sDate.setDate(sDate.getDate() - 1);
            break;
        case "THIS_WEEK":
            sDate.setDate(sDate.getDate() - sDate.getDay());
            break;
        case "LAST_WEEK":
            sDate.setDate(sDate.getDate() - (7 + sDate.getDay()));
            break;
        case "THIS_MONTH":
            sDate.setMonth(sDate.getMonth(), 1);
            break;
        case "LAST_MONTH":
            sDate.setMonth(sDate.getMonth() - 1, 1);
            break;
    }

    var returnValue = null;
    delimiter = (isNull(delimiter)) ? "" : delimiter;
    returnValue = sDate.getFullYear() + delimiter + leftPad(sDate.getMonth() + 1, 2) + delimiter + leftPad(sDate.getDate(), 2);

    return returnValue;
}
/**
 * Unix Timestamp
 * 
 * @return
 */
function getUnixTimestamp() {
    return Math.floor(new Date().getTime() / 1000);
}
/**
 * 날짜조회조건체크
 * 
 * @param sDateObj
 * @param eDateObj
 * @return
 */
function chkSearchDate(sDateObj, eDateObj) {
    var intResult;

    intResult = chkFutureDate(sDateObj.value);
    if (intResult != 1) {
        if (intResult == -2) {
            alert('오늘 이후의 날짜로는 검색 할 수 없습니다.\t\r');
            sDateObj.select();
            return false;
        }
        alert('조회기간이 입력되지 않았거나 날짜가 잘못 되었습니다.\t\r');
        sDateObj.select();
        return false;
    }

    if (isNotNull(eDateObj)) {
        intResult = chkFutureDate(eDateObj.value);
        if (intResult != 1) {
            if (intResult == -2) {
                alert('오늘 이후의 날짜로는 검색 할 수 없습니다.\t\r');
                eDateObj.select();
                return false;
            }
            alert('조회기간이 입력되지 않았거나 날짜가 잘못 되었습니다.\t\r');
            eDateObj.select();
            return false;
        }

        if (new Number(sDateObj.value) > new Number(eDateObj.value)) {
            alert('조회기간 시작일이 종료일보다 이후의 날짜로는 검색 할 수 없습니다.\t\r');
            sDateObj.select();
            return false;
        }
    }

    return true;
}
/**
 * 날짜조회조건체크
 * YYY-MM-DD
 * @param sDateObj
 * @param eDateObj
 * @return
 */
function chkSearchDate2(sDateObj, eDateObj) {
    var intResult;
    //sDateObj.focus();
    var sDateObjValue = sDateObj.value.replace(/-/g,"");
    var eDateObjValue = eDateObj.value.replace(/-/g,"");
    intsDateObjResult = chkFutureDate(sDateObjValue);
    inteDateObjResult = chkFutureDate(eDateObjValue);
    if(intsDateObjResult != 1){
        if(intsDateObjResult == -2){
            alert('오늘 이후의 날짜로는 검색 할 수 없습니다.\t\r');
            sDateObj.select();
            return false;
        }
        alert('조회기간이 입력되지 않았거나 날짜가 잘못 되었습니다.\t\r');
        sDateObj.select();
        return false;
    }
    
    if(isNotNull(eDateObj)){
        if(inteDateObjResult != 1){
            if (intResult != 1) {
                if (intResult == -2) {
                    alert('오늘 이후의 날짜로는 검색 할 수 없습니다.\t\r');
                    eDateObj.select();
                    return false;
                }
                alert('조회기간이 입력되지 않았거나 날짜가 잘못 되었습니다.\t\r');
                eDateObj.select();
                return false;
            } 
        }
        if (new Number(sDateObjValue) > new Number(eDateObjValue)) {
            alert('조회기간 시작일이 종료일보다 이후의 날짜로는 검색 할 수 없습니다.\t\r');
            sDateObj.select();
            return false;
        }
    }
    return true;
}
/*-------------------------------- E : Date --------------------------------*/

/*-------------------------------- S : Image --------------------------------*/
/**
 * 원본이미지레이어 - EX : <img src='경로' onclick="showImageLayer(this.src, '타이틀');"> -
 * EX : <A href='경로' title='타이틀' onclick="showImageLayer(this.href,
 * this.title);return false;"><img src='경로' /></A>
 * 
 * @param imageSrc
 * @param title
 * @return
 */
function showImageLayer(imageSrc, title) {
    var imageObj = new Image();
    imageObj.src = imageSrc;

    var imageLayer = document.createElement("div");

    if (title != undefined)
        imageLayer.innerHTML = "&nbsp;" + title;

    if (imageObj.complete) {
        imageLayer.style.cssText = "z-index;10;" + "position:absolute;" + "visibility:visible;" + "top:" + (document.body.clientHeight - imageObj.height) / 2 + ";" + "left:" + (document.body.clientWidth - imageObj.width) / 2 + ";" + "width:"
                + imageObj.width + ";height:" + imageObj.height + ";" + "cursor:pointer;" + "border:1px solid #708090;" + "padding:1px 1px 1px 1px;" + "font:normal 12px lucida console;" + "color:#FFFFFF;" + "background-color:#555555;";
    } else {
        imageLayer.style.cssText = "visibility:hidden;";
    }

    with (imageLayer.appendChild(document.createElement("div"))) {
        style.cssText = "padding:1px 1px 1px 1px;background-color:#888888;";
        with (appendChild(document.createElement("div"))) {
            style.cssText = "padding:3px;background-color:#FFFFFF;";
            with (appendChild(document.createElement("img"))) {
                src = imageSrc;
                if (!imageObj.complete) {
                    onload = function() {
                        with (this.parentNode.parentNode.parentNode.style) {
                            left = (document.body.clientWidth - this.width) / 2;
                            top = (document.body.clientHeight - this.height) / 2;
                            width = this.width;
                            height = this.height;
                        }
                    };
                }
                onclick = function() {
                    var win = this.parentNode.parentNode.parentNode;
                    win.parentNode.removeChild(win);
                };
            }
        }
    }
    document.body.appendChild(imageLayer);
}
/*-------------------------------- E : Image --------------------------------*/

/*-------------------------------- S : String Function --------------------------------*/
/**
 * 오른쪽채움
 * 
 * @param str
 * @param lengthN
 * @param padChar
 * @return
 */
function rightPad(str, lengthN, padChar) {
    var addStr = "";
    var count = lengthN - ("" + str).length;
    if (count > 0) {
        var strPad = (padChar != undefined) ? padChar : "0";
        for ( var i = 0; i < count; i++)
            addStr += strPad;
    }
    return str.concat(addStr);
}
/**
 * 좌측채움
 * 
 * @param str
 * @param lengthN
 * @param padChar
 * @return
 */
function leftPad(str, lengthN, padChar) {
    var addStr = "";
    var count = lengthN - ("" + str).length;
    if (count > 0) {
        var strPad = (padChar != undefined) ? padChar : "0";
        for ( var i = 0; i < count; i++)
            addStr += strPad;
    }

    return addStr.concat(str);
}
/**
 * null 여부
 * 
 * @param obj
 * @return
 */
function isNull(obj) {
    if (obj === null || obj === undefined || obj === "" || obj === "undefined")
        return true;
    else
        return false
}
function isNotNull(obj) {
    return !isNull(obj);
}
/**
 * 값이 없는 경우 디폴트 문자열
 * 
 * @param str
 * @param strDef
 * @return
 */
function strDefault(str, strDef) {
    if (isNull(str))
        return (isNull(strDef)) ? '' : strDef;
    else
        return str;
}
/*-------------------------------- E : String Function --------------------------------*/

/*-------------------------------- S : javascript Hash --------------------------------*/
/**
 * HashMap 기능
 * 
 * @return
 */
function HashMap() {
    this.infoMap = {};
    this.infoKey = new Array();
}
HashMap.prototype.put = function(key, value) {
    this.infoMap[key] = value;
    if (this.containsKey(key) == -1) {
        this.infoKey.push(key);
    }
}
HashMap.prototype.values = function() {
	var values = new Array();   
    for(var prop in this.infoMap){ 
      if(this.infoMap[prop]!=null){
    	  values.push(this.infoMap[prop]);
      }
    }   
    return values;
}
HashMap.prototype.keys = function() {
	var values = new Array();   
    for(var prop in this.infoMap){ 
      if(this.infoMap[prop]!=null){
    	  values.push(prop);
      }
    }   
    return values;
}
HashMap.prototype.get = function(key) {
    var returnValue = this.infoMap[key];
    if (isNull(returnValue))
        returnValue = '';
    return returnValue;
}
HashMap.prototype.remove = function(key) {
    var tempArray = new Array();
    for ( var i = 0; i < this.size(); i++) {
        if (this.infoKey[i] != key) {
            tempArray.push(this.infoKey[i]);
        }
    }
    this.infoMap[key] = null;
    this.infoKey = tempArray;
}
HashMap.prototype.removeAll = function() {
    for ( var i = 0; i < this.size(); i++)
        this.infoMap[this.infoKey[i]] = null;
    this.infoKey = new Array();
    
   //alert(""+this.infoKey.length);
}
/* 키배열에서 해당키의 인덱스값 리턴 (없는경우 -1) */
HashMap.prototype.containsKey = function(key) {
    for ( var i = 0; i < this.size(); i++)
        if (this.infoKey[i] == key)
            return i;
    return -1;
}
/* 키배열에서 해당키의 HashMap 값이 없는 요소를 삭제하여 재배열한다 */
HashMap.prototype.filterNull = function(key) {
    var i = 0;
    this.infoKey.sort(); // '\sortno 재정렬'
    while (i < this.size()) {
        if (isNull(this.get(this.infoKey[i]).get(key)))
            this.remove(this.infoKey[i]);
        else
            i++;
    }
}
HashMap.prototype.size = function() {
    return this.infoKey.length;
}

/*-------------------------------- S : javascript jsList --------------------------------*/
/*
 *  ArrayList 에 비해 조금 미흡해서..jsList
 */
function jsList(){
    this.array = new Array();
};
jsList.prototype.add = function(obj){
    this.array[this.array.length] = obj;
};
jsList.prototype.get = function(index){
    return this.array[index];
};
jsList.prototype.addAll = function(obj){
    for (var i=0; i < obj.length; i++) {
        this.add(obj[i]);
    };
};
jsList.prototype.toString = function() {
    return this.array.toString();
};
jsList.prototype.removeAll = function() {
    return this.array = new Array();
};

jsList.prototype.size = function(){
    return this.array.length;
}
//값을 넘겨주면 해당 값은 지워짐
jsList.prototype.remove = function(obj){
    var tempArray = new Array();
    for(var i=0,j=this.size(); i<j; i++){
      if(obj != this.get(i)){
        tempArray.push(this.get(i))
      }
    };
    this.array = tempArray;
}

//hASH맵과 값은 기능임. 키 대신..값으로
jsList.prototype.containsValue = function(obj){
    for(var i=0,j=this.size(); i<j; i++){
      if(obj == this.get(i)){
        return true;
      }
    };
    return false;
}

jsList.prototype.toString2 = function() {
    var temparrStr = [];
    
    for(var i=0,j=this.size(); i<j; i++){
        temparrStr.push("'"+this.array[i]+"' : \t" + this.array[i]);
    }
        return temparrStr.join(" ,\t ");
};

/*-------------------------------- E : javascript jsList --------------------------------*/


/*-------------------------------- S : jquery 확장 기능 ----------------------------------------*/
$.extend({
   getMsg : function (pMsg) {
	   if(!(navigator.appName.indexOf("Microsoft") > -1 )){
	       console.log(pMsg);
	   }
   }
});
/*--------------------------------S : jquery 확장 기능  ----------------------------------------*/





/**
 * 자바스크립트객체복사
 * 
 * @param deep
 * @return
 */
function clone(deep) {
    var objectClone = new this.constructor();
    for ( var property in this)
        if (!deep)
            objectClone[property] = this[property];
        else if (typeof this[property] == 'object')
            objectClone[property] = this[property].clone(deep);
        else
            objectClone[property] = this[property];
    return objectClone;
}
// Object.prototype.clone = clone; // jquery 사용시 문제 생김 
/*-------------------------------- E : javascript Hash --------------------------------*/

/*-------------------------------- S : ajax --------------------------------*/
/**
 * Ajax Http Request
 * 
 * @return
 */
function AjaxHttpRequest() {
    this.r = null; // '\XMLHttpRequest object'

    this.getRequest = function() {
        if (this.r == null) {
            try {
                this.r = new XMLHttpRequest();
            } catch (trymicrosoft) {
                try {
                    this.r = new ActiveXObject('Msxml12.XMLHTTP');
                } catch (othermicrosoft) {
                    try {
                        this.r = new ActiveXObject('Microsoft.XMLHTTP');
                    } catch (failed) {
                        this.r = null;
                    }
                }
            }
        }
        if (this.r == null)
            alert('Error Creating request object!');
        return this.r;
    }

    /**
     * 요청
     * 
     * <pre>
     * # intMethod
     * - 메소드 (default : GET)
     * - 0 : GET
     * - 1 : POST
     * 
     * # intAsync
     * - 동기화여부 (default : 비동기)
     * - 0 : 비동기
     * - 1 : 동기
     * </pre>
     */
    this.get = function(url, param, callback, intMethod, intAsync) {
        var r = this.getRequest();
        if (r != null) {
            r.onreadystatechange = function() {
                if (r.readyState == 4 && r.status == 200) {
                    if (r.getResponseHeader('Content-Type') == 'text/xml')
                        callback(r.responseXML);
                    else
                        callback(r.responseText);
                }
            }

            var method = (!isNull(intMethod) || intMethod == 0) ? 'GET' : 'POST';
            var async = (!isNull(intAsync) || intAsync == 0) ? 'true' : 'false';

            r.open(method, url, async);
            if (method == 'POST') {
                this.setHeader('Content-Type', 'application/x-www-form-urlencoded');
            }
            r.send(param);
        }
    }

    this.setHeader = function(key, value) {
        this.r.setRequestHeader(key, value);
    }

    this.init = function() {
        this.getRequest();
    }

    this.init();
}
/*-------------------------------- E : ajax --------------------------------*/
/**
 * ToolTip Layer
 * 
 * @param e
 * @param message
 * @return
 */
function showToolTip(e, message) {
    var tpObj = getObjectId('toolTip');
    if (tpObj == null) {
        document.body.insertAdjacentHTML('beforeEnd', '<div id="toolTip"></div>');
        tpObj = getObjectId('toolTip');
    }

    if (message != undefined)
        tpObj.innerHTML = '<table><tr><td class="toolTipMessage">' + message + '</td></tr></table>';

    if (tpObj.style.visibility == 'visible') {
        tpObj.style.visibility = 'hidden';
    } else {
        tpObj.style.pixelTop = (e.y + document.body.scrollTop);
        tpObj.style.pixelLeft = (e.x + document.body.scrollLeft + 10);
        tpObj.style.visibility = 'visible';
    }
}
/**
 * Math.round를 수정해서 소수점까지의 round 제공
 */
function round(val, precision) {
    val = val * Math.pow(10, precision);
    val = Math.round(val);
    return val / Math.pow(10, precision);
}

/*-------------------------------- S : jQuery --------------------------------*/

(function($) {

    /**
     * jQuery Dialog
     * 
     * @param diaVal (json)
     * @param options (json)
     * 
     * <pre>
     * param diaVal
     * - id         : #id
     * - url        : url
     * - param      : URL QueryString
     * - contents   : text or html
     * - clickclose : 0: none, 1: close
     * - istitle    : 0: hide, 1: show
     *  
     * param options 
     * - jQuery Dialog Options
     * 
     * var diaVal = { id: '#popupMain1', url: './popup.do', clickclose: 1 };
     * var options = { width: 100, position: ['center','center'] };
     * $.dialog(diaVal, options);
     * 
     * </pre>
     */
    $.dialog = function(diaVal, options) {
        var isOpen;

        try {
            isOpen = $(diaVal.id).dialog('isOpen');
        } catch (e) {}

        if (isOpen == true) {
            $(diaVal.id).dialog('moveToTop');
        } else if (isOpen == false) {
            $(diaVal.id).dialog('open');
        } else {
            if (isNotNull(diaVal.url)) {
                $.ajax( {
                    url: diaVal.url,
                    data: strDefault(diaVal.param),
                    dataType: 'html',
                    type: 'post',
                    success: function(r) {
                        diaVal.contents = r;
                        $.dialogComplete(diaVal, options);
                    },
                    error: function(xhr, status, error) {
                        alert('data loading error....');
                        // alert(status + ':' + error);
                    }
                });
            } else
                $.dialogComplete(diaVal, options);
        }
    };

    $.dialogComplete = function(diaVal, options) {

        if (isNull(diaVal.clickclose)) diaVal.clickclose = 0;
        if (isNull(diaVal.istitle)) diaVal.istitle = 0;

        var defaultOptions = { width: 'auto', height: 'auto', position: [ 0, 0 ], modal: false, autoOpen: false, resizable: false, draggable: true, closeOnEscape: true, show: 'slide', hide: 'slide' };

        $.extend(defaultOptions, options);

        if ($(diaVal.id).length == 0)
            $('body').append('<div id="' + diaVal.id.substring(1) + '"></div>');

        $(diaVal.id).dialog(defaultOptions).css({ 'padding':'0px', 'margin':'0px'});

        if (diaVal.clickclose == 1) {
            $(diaVal.id).bind('click', function() {
                $(this).dialog('close');
            }).css('cursor','pointer');
        }
        if (diaVal.istitle == 0) {
            $(diaVal.id).siblings('div.ui-dialog-titlebar').remove();
        }

        $(diaVal.id).html(diaVal.contents);
        $(diaVal.id).dialog('open');
    };

})(jQuery);

/**
 * jQuery.ajax Success Callback
 * 
 * @param data
 * @param status
 * @param xhr
 * @return
 */
function jAjaxSuccess(data, status, xhr) {
    if (data.RESULT == 0) {
        alert(data.MESSAGE);
    }
}

/**
 * jQuery.ajax Error Callback
 * 
 * @param xhr
 * @param status
 * @param err
 * @return
 */
function jAjaxError(xhr, status, err) {
    if (status === 'error')
        alert(xhr.status + ' : ' + err);
    else if (!xhr.responseText)
        alert('data loading error....');
    else {
        var data = eval('(' + xhr.responseText + ')');
        alert(data.MESSAGE);
    }
}

/*-------------------------------- E : jQuery --------------------------------*/

/**
 * ip주소와 넷마스크bit로 해당 ip의 범위를 가져옴
 * @param strIp ip/넷마스크bit - ex: 192.168.10.5/24)
 * @param count 보여줄 ip갯수 - </br>인자 보다 ip가 많으면 ip대역의 가장처음과 끝만 "처음ip,끝ip"와 같이 콤마로 구분해서 리턴하고<br/>, 인자보다 ip가 적으면 ip들을 "ip,ip,ip..."과 같이 여러개를 콤마로 구분해서 리턴한다)
 * @param boolean network주소, broadcasting주소까지 같이 리턴할지 여부 (true:같이 리턴, false:유효한 호스트만 리턴)
 * @return
 */
function getIpRange(strIp, count, allIp){
	if(strIp=="" || strIp==undefined) {
		throw new Error("입력된 IP(CIDR notation)가 없습니다.");
	}

	var ipTemp = strIp.split("/");
	//ip주소
	var ip = ipTemp[0];			
	var ipOctecArray = ip.split("\.");

	if(ipTemp.length!=2) {
		throw new Error("CIDR notation 형식이 잘못 되었습니다.");
	}
	
	//넷마스크bit (ex 8, 16, 24..)
	var subnetMaskValidBit = Number(ipTemp[1]);		
	if(isNaN(subnetMaskValidBit) || subnetMaskValidBit>32) {
		throw new Error("'"+ipTemp[1]+"' 잘못된 넷마스크bit 입니다.");
	}

	//넷마스크bit가 32일경우 자기자신 ip리턴
	if(subnetMaskValidBit==32) {
		return ip;
	}
	
	//넷마스크 bit를 2진수 32비트로 전환
	var temp = 0;
	for(var i=1; i<=subnetMaskValidBit; i++) {
		temp = temp >>> 1;
		temp = temp + 0x80000000;
	}

	//입력받은 ip주소의 각 octet
	var ipOctet1 = validate_octet(parseInt(ipOctecArray[0],10),"ip Octet1");
	var ipOctet2 = validate_octet(parseInt(ipOctecArray[1],10),"ip Octet2");
	var ipOctet3 = validate_octet(parseInt(ipOctecArray[2],10),"ip Octet3");
	var ipOctet4 = validate_octet(parseInt(ipOctecArray[3],10),"ip Octet4");

	//입력받은 서브넷마스크 bit를 서브넷마스크주소로 변환한 각 octet
	var subnetMask1 = (temp >> 24) & 0xFF;
	var subnetMask2 = (temp >> 16) & 0xFF;
	var subnetMask3 = (temp >> 8) & 0xFF;
	var subnetMask4 = temp & 0xFF;
	
	//해당ip의 네트워크주소의 각octet
	var networkAdressOctet1 = (ipOctet1 & 0xFF) & (subnetMask1 & 0xFF);
	var networkAdressOctet2 = (ipOctet2 & 0xFF) & (subnetMask2 & 0xFF);
	var networkAdressOctet3 = (ipOctet3 & 0xFF) & (subnetMask3 & 0xFF);
	var networkAdressOctet4 = (ipOctet4 & 0xFF) & (subnetMask4 & 0xFF);

	//해당ip의 브로드캐스트주소의 각 octet
	var broadcastIp1 = (ipOctet1 & 0xFF) | (~subnetMask1 & 0xFF); 
	var broadcastIp2 = (ipOctet2 & 0xFF) | (~subnetMask2 & 0xFF); 
	var broadcastIp3 = (ipOctet3 & 0xFF) | (~subnetMask3 & 0xFF); 
	var broadcastIp4 = (ipOctet4 & 0xFF) | (~subnetMask4 & 0xFF); 
	
	//첫번째 호스트주소의 각 octet
	var firstHostIp1 = networkAdressOctet1;
	var firstHostIp2 = networkAdressOctet2;
	var firstHostIp3 = networkAdressOctet3;
	var firstHostIp4 = networkAdressOctet4+1;
	
	//마지막 호스트주소의 각 octet
	var lastHostIp1 = broadcastIp1;
	var lastHostIp2 = broadcastIp2;
	var lastHostIp3 = broadcastIp3;
	var lastHostIp4 = broadcastIp4-1;

	//첫번째 호스트ip
	var fistHostIp = null;
	if(allIp){ //3번째 인자가 true일경우 시작 ip는 network주소부터 포함
		firstHostIp4-=1;
	}
	fistHostIp = firstHostIp1+"."+firstHostIp2+"."+firstHostIp3+"."+firstHostIp4;
	
	//마지막 호스트ip
	var lastHostIp = null;
	if(allIp){ //3번째 인자가 true일경우 마지막 ip는 broadcasting주소까지 포함
		lastHostIp4+=1;
	}
	lastHostIp = lastHostIp1+"."+lastHostIp2+"."+lastHostIp3+"."+lastHostIp4;
	
	//while문에서 비교할 임시 변수
	var tempResult = "";
	
	//while문 몇번 반복하는지 카운트 
	var whileCount = 1;
	
	//ip대역 저장변수
	var sb = new StringBuffer();
	
	while(lastHostIp != (tempResult)) {
		if(firstHostIp4>0xFF) { //4번째 octet이 255를 초과할때
			firstHostIp4=0;
			firstHostIp3++;
		}
		if(firstHostIp3>0xFF) { //3번째 octet이 255를 초과할때
			firstHostIp4=0;
			firstHostIp3=0;
			firstHostIp2++;
		}
		if(firstHostIp2>0xFF) { //2번째 octet이 255를 초과할때
			firstHostIp4=0;
			firstHostIp3=0;
			firstHostIp2=0;
			firstHostIp1++;
		}
		
		//while문에서 비교를 위해...
		tempResult = firstHostIp1+"."+firstHostIp2+"."+firstHostIp3+"."+firstHostIp4;
		
		//메서드 인자로 받은 count보다 while문 반복한 횟수가 많을때 
		if(whileCount>count) {
			sb = null;
			sb = new StringBuffer();
			sb.append(fistHostIp);
			sb.append(",");
			sb.append(lastHostIp);
			break;
		} else { 
			if(whileCount!=1) {
				sb.append(",");
			}
			sb.append(firstHostIp1+"."+firstHostIp2+"."+firstHostIp3+"."+firstHostIp4);
		}
		firstHostIp4++;
		whileCount++;
	}
	return result = sb.toString();
}

/**
 * getIpRange() 에서 사용하는 입력받은 ip의 각 octet이 범위내에 있는지 확인
 */
function validate_octet(octet, text_message){
  if ((octet < 0) || (octet > 255) || isNaN(octet)) {
  	throw new Error(text_message + ' 값은 반드시 0~255 범위여야 합니다.');
  }
  return octet;
}

/**
 * cidr형식으로 파라미터가 들어오면 해당하는 netmask를 리턴해 준다.
 * @param cidr (170.128.0.5/25 와 같은 cidr 표기 형식 또는 25와 같이 netmaskBit가 들어 와야 한다)
 */
function getSubnetMask(cidr) {
	if(cidr==null) {
		throw new Error("파라미터 형식을 확인해 주세요.");
	}
	
	var tempArray = cidr.split("/");
	var netmaskBit = "";
	if(tempArray.length!=2 || tempArray[1]==""){
		netmaskBit = cidr;
	} else {
		netmaskBit = tempArray[1];
	}
	
	var subnetMaskValidBit = parseInt( netmaskBit, 10);
	
	//넷마스크 bit를 2진수 32비트로 전환
	var temp = 0;
	for(var i=1; i<=subnetMaskValidBit; i++) {
		temp = temp >>> 1;
		temp = temp + 0x80000000;
	}

	//입력받은 서브넷마스크 bit를 서브넷마스크주소로 변환한 각 octet
	var subnetMask1 = (temp >> 24) & 0xFF;
	var subnetMask2 = (temp >> 16) & 0xFF;
	var subnetMask3 = (temp >> 8) & 0xFF;
	var subnetMask4 = temp & 0xFF;

	return subnetMask1+"."+subnetMask2+"."+subnetMask3+"."+subnetMask4;
}

/**
 * 서브넷마스크를 서브넷마스크 bit로 변환
 * @param subnetmask
 * @return
 * @throws ValidException
 */
function getSubnetMaskBit(subnetmask) {
	var subnetmaskArray = subnetmask.split("\.");
	var a = (parseInt(subnetmaskArray[0],10)).toString(2);
	var b = (parseInt(subnetmaskArray[1],10)).toString(2);
	var c = (parseInt(subnetmaskArray[2],10)).toString(2);
	var d = (parseInt(subnetmaskArray[3],10)).toString(2);
	var result = a+b+c+d;
	var count = 0;
	for(var i=0; i<result.length; i++) {
		if(result.charAt(i)=="1"){ //1일경우
			count++;
		}
	}
	return count;
}

/**
 * IP 문자 => IP 정수값
 * 
 * @param strIp
 * @return
 */
function getInetAtoN(strIp) {
	var regExp = /^(\d{1,3})\.(\d{1,3})\.(\d{1,3})\.(\d{1,3})$/i;
	var matcherArray = regExp.exec(strIp);
	if (matcherArray!=null) {
    	var result = parseInt(matcherArray[1],10) * Math.pow(256,3) 
    	  + parseInt(matcherArray[2],10) * Math.pow(256,2)
    	  + parseInt(matcherArray[3],10) * 256
    	  + parseInt(matcherArray[4],10);
    	
    	return result;
    } else {
        return -1;
    }
}

/**
 * IP 정수값 => IP 문자
 * 
 * @param longIp
 * @return
 */
function getInetNtoA(longIp) {
	return parseInt((longIp / 256 / 256 / 256) % 256, 10) + "." 
		 + parseInt((longIp / 256 / 256) % 256, 10) + "." 
		 + parseInt((longIp / 256) % 256, 10) + "." 
		 + parseInt(longIp % 256, 10);
}

/**
 * IP 유효성 검사
 * @param value
 * @returns {Boolean}
 */
function validateIP(value) {
    if (value.search(/^\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}$/) != -1)
    {
        var myArray = value.split(/\./);
        if (myArray[0] > 255 || myArray[1] > 255 || myArray[2] > 255 || myArray[3] > 255)
             return false;
        if (myArray[0] == 0 && myArray[1] == 0 && myArray[2] == 0 && myArray[3] == 0)
            return false;
        return true;
    }
    else
    {
       return false;
    }
}  

/**
 * 자바스크립트용 StringBuffer
 */
var StringBuffer = function() {
this.buffer = new Array();
};
StringBuffer.prototype.append = function(obj) {
this.buffer.push(obj);
};
StringBuffer.prototype.toString = function(){
return this.buffer.join("");
};
// 자바스크립트용 StringBuffer 끝

// 자바스크립트 Date에 포맷 주기
Date.prototype.format = function(f) {
    if (!this.valueOf()) return " ";
 
    var weekName = ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"];
    var d = this;
     
    return f.replace(/(yyyy|yy|MM|dd|E|hh|mm|ss|a\/p)/gi, function($1) {
        switch ($1) {
            case "yyyy": return d.getFullYear();
            case "yy": return (d.getFullYear() % 1000).zf(2);
            case "MM": return (d.getMonth() + 1).zf(2);
            case "dd": return d.getDate().zf(2);
            case "E": return weekName[d.getDay()];
            case "HH": return d.getHours().zf(2);
            case "hh": return ((h = d.getHours() % 12) ? h : 12).zf(2);
            case "mm": return d.getMinutes().zf(2);
            case "ss": return d.getSeconds().zf(2);
            case "a/p": return d.getHours() < 12 ? "오전" : "오후";
            default: return $1;
        }
    });
};
 
String.prototype.string = function(len){var s = '', i = 0; while (i++ < len) { s += this; } return s;};
String.prototype.zf = function(len){return "0".string(len - this.length) + this;};
Number.prototype.zf = function(len){return this.toString().zf(len);};

/**
 * ajax로딩 이미지 출력 모듈
 */
ajaxLoading = function(){
	//모듈 내부에서 사용하는 div생성 메서드
	function fn_makeLoadingDiv(number){
		if($('#loadingDiv'+number).attr('id')==undefined){
			var loadingDivCss = "display: none;position: absolute;text-align: center;";
			var backlayerDivCss = "display:none;background-color:black;position:absolute;left:0px;top:0px;width:100%;height: 100%;";
			var data = '';
				data+='<div id="loadingDiv'+number+'" style="'+loadingDivCss+'"><img alt="로딩중 이미지" src="/images/common/loading.gif"></div>';
				data+='<div id="backLayerDiv'+number+'" style="'+backlayerDivCss+'"></div>';
			$('body').append(data);
		}
	}
	
	/**
	 * ajax loading image start
	 * @param {Object} option : json option변수
	 */
	function fn_Start(option){
		//ajaxLoadingDefault
		var ajaxLoadingDefaultOption = {selector:"", isFull:true, fadeTime:100, opacity:0.2, image:"/images/common/loading.gif", imageWidth:64, imageHeight:64, number:1};
		//option
		var options = jQuery.extend(ajaxLoadingDefaultOption, option);
		
		var selector   = options.selector;  // isFull옵션이 false일때 부분적인 div에 loading이미지 표시하기위한 div의 class이름
		var isFull     = options.isFull;    // true:전체화면 로딩
		var fadeTime   = options.fadeTime;  // 로딩화면이 나타나기까지의 시간 (ms)
		var opacity    = options.opacity;   // 불투명도 0.0~1.0
		var image      = options.image;	  // 로딩이미지 url
		var imageWidth = options.imageWidth; // 이미지의 width
		var imageHeight= options.imageHeight; // 이미지의 height
		var number     = options.number;      //

		if($(selector).is(":visible")==false) {
			return false;
		}		
		
		fn_makeLoadingDiv(number);
		try{
			if(image!=undefined || image!=''){
				$('#loadingDiv'+number+'>img').attr('src',image);
			}
			if( ! isFull) {
			    var sourceDiv = $(selector);
			    var top = sourceDiv.offset().top+(sourceDiv.css('height').replaceAll('px','')/2)-imageHeight/2;
		    	var loadingDivObj = $('#loadingDiv'+number);
				    loadingDivObj.css('position', 'absolute');
				    loadingDivObj.css('left', sourceDiv.offset().left);
				    loadingDivObj.css('top', top);
				    loadingDivObj.css('width', sourceDiv.css('width'));
				    loadingDivObj.css('height', sourceDiv.css('height'));
			    
		    	var backLayerDivObj = $('#backLayerDiv'+number);
			    	backLayerDivObj.css('left', sourceDiv.offset().left);
			    	backLayerDivObj.css('top', sourceDiv.offset().top);
			    	backLayerDivObj.css('width', sourceDiv.css('width'));
			    	backLayerDivObj.css('height', sourceDiv.css('height'));
			   
			   backLayerDivObj.fadeTo(fadeTime, opacity);
			   loadingDivObj.show();
			} else {
		       var loadingDivObj = $("#loadingDiv"+number);
			   var width = $(window).width();
			   var height= $(window).height();
		       loadingDivObj.css("left",  width/ 2 - imageWidth/2);
		       loadingDivObj.css("top",   height/ 2 - imageHeight/2);
		       //화면을 가리는 레이어의 사이즈 조정
		       var backLayerDivObj = $('#backLayerDiv'+number);
			       backLayerDivObj.width(width);
			       backLayerDivObj.height(height);
			   
			   backLayerDivObj.fadeTo(fadeTime, opacity);
		       loadingDivObj.show();
			}
		   
		} catch(e){
			alert(e + " : " + "aJaxLoadingStart호출 부분을 점검하세요.");
		}
	}
	
	/**
	 * ajax loading image stop
	 * @param {Object} option
	 */
	function fn_Stop(option){
	    //console.log("option \t"+option)
		var ajaxLoadingDefaultOption = {number:1};
		var options = jQuery.extend(ajaxLoadingDefaultOption, option);
		var number     = options.number;      //
	    $("#loadingDiv"+number).hide();
	    $("#backLayerDiv"+number).hide();
	}
	
	//1개 이상일경우 이거를 쓴다....위에 옵션에 세팅 number 를 넣어준다. 
	function fn_This_Stop(option,number){
        var ajaxLoadingDefaultOption = {number:number};
        var options = jQuery.extend(ajaxLoadingDefaultOption, option);
        var number     = options.number;      //
        $("#loadingDiv"+number).hide();
        $("#backLayerDiv"+number).hide();
    }
	
	
	
	return {
		start:fn_Start,
		stop:fn_Stop,
		this_stop:fn_This_Stop
	};
}();//ajax로딩 이미지 출력 모듈 끝


/**
 * 자바스크립트로 get파라미터 받기
 * - 사용법 - 
 * var request = new Request();
 * var param = request.getParameter("파라미터이름");
 */
var Request = function() {
    this.getParameter = function( name ) {
        var rtnval = '';
        var nowAddress = unescape(location.href);
        var parameters = (nowAddress.slice(nowAddress.indexOf('?')+1,nowAddress.length)).split('&');
        for(var i = 0 ; i < parameters.length ; i++)
        {
            var varName = parameters[i].split('=')[0];
            if(varName.toUpperCase() == name.toUpperCase())
            {
                rtnval = parameters[i].split('=')[1];
                break;
            }
        }
        return rtnval;
    };
};

/**
 * json2.js 라이브러리로 json객체를 전달하면 string으로 만들어서 리턴
 * @param {Object} jsonObject
 */
function fn_Json2String(jsonObject) {
	var str = JSON.stringify(jsonObject, function replacer(key, value) {
	    if (typeof value === 'number' && !isFinite(value)) {
	        return String(value);
	    }
	    return value;
	});
	return str;
}