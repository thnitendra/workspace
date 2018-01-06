jQuery(document).ready(function () {

    "use strict";

    Core.init();
    var orgList;
    
    var errorMsg = "Please select the organization that you want to manage.";

    var table = $("#org-list-table").DataTable({
        ajax: {
            url: "/app/getOrgs",
            dataSrc: function (json) {
                //orgList = json["organizationList"];
                orgList = json;
                var data = [];
                orgList.forEach(function (item) {
                	var temp = [];
                    temp.push(item.id);
                    temp.push(item.name);
                    temp.push(item.type);
                    temp.push(item.createTime);
                    temp.push(item.createdBy);
                    temp.push('<a href="orgs/' + item.name + '/open">Open</a>');
                    data.push(temp);
                });
                return data;
            }
        },
        "oLanguage": {
            "oPaginate": {
                "sPrevious": "",
                "sNext": ""
            }
        },
        "scrollX": true,
        "scrollCollapse": true,
        "scrollY": "450px",
        "order": [[ 1, "desc" ]],
        "iDisplayLength": datatableCommonConfig.getDefaultDisplayLength(),
        "aLengthMenu": datatableCommonConfig.getDisplayLengthMenu(),
        "sDom": '<"dt-panelmenu clearfix"lfr>t<"dt-panelfooter clearfix"ip>'
    });

    $('#org-list-table').find('tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        }
        else {
            table.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
    });

    
    // Add Placeholder text to datatables filter bar
    $('.dataTables_filter input').attr("placeholder", "Enter Terms...");

    $('#org-create-btn').click(function() {
        window.location.href = "/app/org/create";
    });

    $('#org-details-btn').click(
    	function () {
    		startModal("processingDiv");

            $.ajax({
                url: "/app/getOrgs",
                contentType: 'application/json',
                success: function (data) {
                    stopModal("processingDiv");
                    bootbox.dialog({
                        title: "Orgs Details",
                        message: data,
                        buttons: {
                            success: {
                                label: "OK",
                                className: "btn-success",
                                callback: function () {

                                }
                            }
                        }
                    });
                },
                error: function (response) {
                    stopModal("processingDiv");
                    bootbox.alert(response.responseJSON.message);
                }
            });

       }
    );

    $('#org-delete-btn').click(
        function () {
            var rowData = table.row('.selected').data();
            if (rowData == undefined) {
                bootbox.alert("Error! Please select at least one Organization.");
            } else {
                //window.location.href = "/orgs/"+rowData[0];
                window.location.href = "/app/getOrgs/";
            }
       }
    );

    $("#org-delete-btn").popover({
        container: 'body',
        html : true,
            trigger : "focus hover",
            placement : "bottom",
            content : function() {
                return $.ajax({url: '/app/getOrgs',
                            contentType: 'application/json',
                            async: false}).responseText;
            },
            title : function() {
                return "Popup Title";
            }

    }).click(function(e) {
      //$(this).popover('toggle');
    });;

});

