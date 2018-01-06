<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<section id="content" class="table-layout animated fadeIn">
    <div class="tray tray-center">

        <!-- Begin: Content Header -->
        <div class="content-header">
            <h2>You can manage all organizations here. </h2>
        </div>

        <div id="view-org-div" class="row">
            <div class="col-md-12">
                <div class="panel panel-visible panel-info" id="spy2">
                     <div class="panel-body pn">
                            <table class="table table-striped table-hover" id="org-list-table" cellspacing="0" width="100%">
	                            <thead>
		                            <tr>
		                                <th>ID</th>
                                        <th>Name</th>
		                                <th>Type</th>
		                                <th>Created On</th>
		                                <th>Created By</th>
		                                <th>Link To Open</th>
		                            </tr>
	                            </thead>
	                            <tbody>
	                            </tbody>
	                        </table>
                    </div>

                    <div class="panel-footer">
                        <button class="btn btn-rounded btn-info" id="org-create-btn">Create</button>
                        <button class="btn btn-rounded btn-info" id="org-details-btn">Details</button>
                        <button class="btn btn-rounded btn-info" id="org-delete-btn">Delete</button>
                    </div>
                </div>
            </div>
        </div>

    </div>
</section>