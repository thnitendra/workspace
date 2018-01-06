<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:useBean id="date" class="java.util.Date" />

<section id="content_wrapper">
    <!-- Begin: Content -->

    <c:choose>
        <c:when test="${empty content}">

        </c:when>
        <c:when test="${content == 'viewOrganizationList' && (state == 'clean' || state == 'dirty')}">
            <%@include file="../content/view_orgnization_list.jsp" %>
        </c:when>
        <c:when test="${content == 'createOrg'}">
            <%@include file="../content/create_organization_content.jsp" %>
        </c:when>
    </c:choose>
    <!-- End: Content -->

    <!-- Begin: Page Footer -->
    <footer id="content-footer" class="affix">
        <div class="row">
            <div class="col-xs-6">
                <span class="footer-legal">@ <fmt:formatDate value="${date}" pattern="yyyy" /> NITE</span>
            </div>
        </div>
    </footer>
    <!-- End: Page Footer -->

	<div id="processingDiv" class="hide-element">
		<img src="${imgProcessing}" alt="Please Wait" class="processing-img" />
	</div>

</section>
