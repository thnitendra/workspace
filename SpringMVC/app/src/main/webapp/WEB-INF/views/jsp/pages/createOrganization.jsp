<!DOCTYPE html>
<html>

<head>
	<script>
		var orgType = "General";
	</script>
</head>

<%@include file="../common/scripts.jsp" %>
<%@include file="../common/header.jsp"%>

<link rel="stylesheet" type="text/css" href="${cssAdminFormsMin}">


<body class="admin-validation-page" data-spy="scroll"
	data-target="#nav-spy" data-offset="200">

    <div id="main">
        <%@include file="../common/navbar_top.jsp"%>
        <%@include file="../common/navbar_left.jsp"%>
        <%@include file="../common/content.jsp"%>
    </div>

	<script src="${jsCreateOrg}"></script>

</body>

</html>
