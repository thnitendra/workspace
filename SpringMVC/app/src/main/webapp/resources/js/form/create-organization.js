var errorClass = "state-error";
var validClass = "state-success";
    
jQuery(document).ready(function () {

    "use strict";
    Core.init();

    $("#create-organization-form").validate({
        errorClass: "state-error",
        validClass: "state-success",
        errorElement: "em",

        rules: {
            orgName: {
                required: true,
                pattern: /^[a-z]+$/
            }
            //,pEmail: {
            //	pattern: /^[a-z0-9-\._]+@(([a-z0-9-]+)[.]{1})+([a-z0-9]+)$/
            //}
        },
        messages: {
            orgName: {
                required: 'Please enter Organization Name',
                pattern: 'Organization Name can only have alphabets a-z'
            }
        },

        highlight: function (element, errorClass, validClass) {
            $(element).closest('.field').addClass(errorClass).removeClass(validClass);
        },
        unhighlight: function (element, errorClass, validClass) {
            $(element).closest('.field').removeClass(errorClass).addClass(validClass);
        },
        errorPlacement: function (error, element) {
        	if (element.is(":radio")) {
                element.closest('.option-group').after(error);
            } else {
                error.insertAfter(element.parent());
            }
        },

        submitHandler: function (form) {
        	var orgName = $(form).find('input[name="orgName"]').val();
            startModal("processingDiv");
            $.ajax({
                url: "/app/org/save",
                type: "POST",
                contentType: 'application/json',
                data: JSON.stringify({id: "1", name: orgName, type: "General", createdBy: "nite", createdTime: new Date().getTime()}),
                dataType: "json",
                success: function () {
                    stopModal("processingDiv");
                    window.location.href = "/app/orgs";
                    bootbox.alert("Organization request for '"+ orgName +"' done successfully");
                },
                error: function (response) {
                    stopModal("processingDiv");
                    $("#create-organization-alert-success").hide();

                    $("#orgName").closest('.field').addClass(errorClass).removeClass(validClass);
                    var errorAlert = $("#create-organization-alert-fail");

                    var errorMsg;
                    if(response.responseJSON) {
                        errorMsg = response.responseJSON.message;
                    } else {
                        errorMsg = "Could not save data.";
                    }
                    errorAlert.find("strong").text(errorMsg);
                    errorAlert.show();
                }
            })

        }
    });

    $("#close-organization-create-success-alert-btn").click(function () {
        var successAlert = $("#create-organization-alert-success");
        successAlert.hide();
    });
    
    $("#close-organization-request-success-alert-btn").click(function () {
        var successAlert = $("#request-organization-alert-success");
        successAlert.hide();
    });

    $("#close-organization-create-fail-alert-btn").click(function () {
        var failAlert = $("#create-organization-alert-fail");
        failAlert.hide();
    });
    
    $("#close-organization-admin-create-fail-alert-btn").click(function () {
        var failAlert = $("#create-organization-admin-alert-fail");
        failAlert.hide();
    });

});
