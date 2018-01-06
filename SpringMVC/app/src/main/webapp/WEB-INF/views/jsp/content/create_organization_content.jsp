<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<section id="content" class="table-layout animated fadeIn">

    <!-- begin: .tray-center -->
    <div class="tray tray-center">

        <div hidden id="create-organization-alert-success"
             class="alert alert-micro alert-border-left alert-success">
            <button type="button" class="close" data-hide="alert" id="close-organization-create-success-alert-btn">x</button>
            <i class="fa fa-check pr10"></i>
            <strong>Organization has been added Successfully!!</strong>
        </div>

        <div hidden id="create-organization-alert-fail" class="alert alert-sm alert-border-left alert-danger">
            <button type="button" class="close" data-hide="alert" id="close-organization-create-fail-alert-btn">x</button>
            <i class="fa fa-info pr10"></i>
            <strong>Oops! </strong>
        </div>


        <!-- Begin: Content Header -->
        <div class="content-header">
            <h2> You can Add new Organization here.</h2>
        </div>

        <div class="admin-form theme-primary mw1000 center-block" style="padding-bottom: 175px;">

            <div class="panel heading-border panel-primary">

                <form id="create-organization-form" action="/app/org/save">

					<div class="panel-body bg-light">
                        <div class="section org-name-div">
	                        <label for="orgName" class="field prepend-icon">
                                <input type="text" name="orgName" id="orgName" class="gui-input" placeholder="Organization Name">
                                <label for="orgName" class="field-icon">
                                    <i class="fa fa-briefcase"></i>
                                </label>
                            </label>
	                    </div>
                    </div>

                	<div class="panel-footer text-right">
                        <button type="submit" class="button btn-primary" id="create-organization-btn">
                         	Create Organization
                        </button>
                        <button type="reset" class="button"> Cancel</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>