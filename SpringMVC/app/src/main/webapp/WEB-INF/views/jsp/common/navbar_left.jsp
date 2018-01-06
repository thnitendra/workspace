<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="app.model.CustomUserBean" %>

<aside id="sidebar_left" class="nano nano-light affix">

    <!-- Start: Sidebar Left Content -->
    <div class="sidebar-left-content nano-content">

        <!-- Start: Sidebar Header -->
        <header class="sidebar-header">

            <!-- Sidebar Widget - Author -->
            <div class="sidebar-widget author-widget">
                <div class="media">
                    <div class="media-body">
                    	<c:if test="${user.username eq 'nite'}">
	                        <div class="media-links">
	                            <span class="sidebar-menu-toggle">${user.username}</span>
	                        </div>
	                        <div class="media-author">ADMIN</div>
	                    </c:if>
                    </div>
                </div>
            </div>

        </header>
        <!-- End: Sidebar Header -->

        <!-- Start: Sidebar Menu -->
        <ul class="nav sidebar-menu">
            <li class="sidebar-label pt15">Menu</li>
            <c:if test="${user.username == 'nite'}">
                <%@include file="viewSideNavigationbarByAdmin.jsp" %>
            </c:if>
        </ul>
        <!-- End: Sidebar Menu -->

        <!-- Start: Sidebar Collapse Button -->
        <div class="sidebar-toggle-mini">
            <a href="#">
                <span class="fa fa-sign-out"></span>
            </a>
        </div>
        <!-- End: Sidebar Collapse Button -->

    </div>
    <!-- End: Sidebar Left Content -->
</aside>