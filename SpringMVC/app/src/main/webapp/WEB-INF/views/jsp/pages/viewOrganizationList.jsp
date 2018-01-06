<!DOCTYPE html>
<html>
<head>
    <script>
		var operation = '${operation}' ? '${operation}' : '';
	</script>
</head>

<%@include file="../common/scripts.jsp" %>
<%@include file="../common/header.jsp"%>

<body class="datatables-page" data-spy="scroll" data-target="#nav-spy"
	data-offset="300">

	<div id="main">
		<%@include file="../common/navbar_top.jsp"%>
		<%@include file="../common/navbar_left.jsp"%>
		<%@include file="../common/content.jsp"%>
	</div>

	<!-- Custom JS-->
	<script src="${jsViewOrganizationList}"></script>


</body>
</html>
