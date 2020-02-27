<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="${_url}/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="${_url}/js/common/common.js"></script>
<script type="text/javascript">
var common={
		init:function(obj)
		{
			return obj
		}
	}
	$(document).ready(function(){
		common.init($("BODY"));
	});
</script>