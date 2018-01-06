<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${user.username eq 'nite'}">

	<li>
	    <a id="tool-management" class="accordion-toggle" href="#">
	        <span class="glyphicon glyphicon-wrench"></span>
	        <span class="sidebar-title">Tool Management</span>
	        <span class="caret"></span>
	    </a>
	    <ul class="nav sub-nav">
            <li>
                <a href="/admin/create" id="createUser">
                    <span class="glyphicon glyphicon-user"></span> Invite Administrator </a>
            </li>
            <li>
                <a href="/admin/filters/subscriptions" id="filterSubscriptionsByAdmin">
                    <span class="glyphicon glyphicon-eye-open"></span> Subscription Management </a>
            </li>
        </ul>
	</li>

	<li>
		<a class="accordion-toggle" href="#">
	        <span class="glyphicon glyphicon-list-alt"></span>
	        <span class="sidebar-title">Content Management</span>
	        <span class="caret"></span>
	    </a>
	    <ul class="nav sub-nav">
            <li>
                <a href="/admin/helpcontent/create" id="createContent">
                    <span class="glyphicon glyphicon-modal-window"></span> Create/Edit Content </a>
            </li>
	    </ul>
	</li>

</c:if>