/*
 * JBoss, Home of Professional Open Source
 * Copyright 2014, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/*
Core JavaScript functionality for the application.  Performs the required
Restful calls, validates return values, and populates the member table.
 */

/* Builds the updated table for the member list */
/*
Attempts to register a new member using a JAX-RS POST.  The callbacks
the refresh the member table, or process JAX-RS response codes to update
the validation errors.
 */
function registerRequest(memberData, idForm, idMsg) {
    //clear existing  msgs
    $('span.invalid').remove();
    $('span.success').remove();

    // Display the loader widget
    $.mobile.loading("show");

    $.ajax({
        url: 'rest/analyse',
        contentType: "application/json",
        dataType: "json",
        type: "POST",
        data: JSON.stringify(memberData),
        success: function(data) {
            //console.log("Member registered");

            //clear input fields
            $(idForm)[0].reset();

            sessionStorage.message="success";
            sessionStorage.idTicket = data.message;
            $(idMsg).html($('<span class="success">'+data.message+'</span>'));
        },
        error: function(error) {
            if ((error.status == 409) || (error.status == 400)) {
                //console.log("Validation error registering user!");

                var errorMsg = $.parseJSON(error.responseText);

                $.each(errorMsg, function(index, val) {
                    $('<span class="invalid">' + val + '</span>').insertAfter($('#' + index));
                });
            } else {
                //console.log("error - unknown server issue");
                $(idMsg).append($('<span class="invalid">Unknown server error</span>'));
            }
        },
        complete: function() {
            // Hide the loader widget
            $.mobile.loading("hide");
        }
    });
}


function getImageResource(imageName)
{
	return "img/"+imageName;
}

function checkSqlDatabase(idMsg)
{
	var urlPost = "rest/analyse/checkSQL"; 	
	$.ajax({
        url: urlPost,
        contentType: "application/json",
        dataType: "json",
        type: "GET",
        success: function(data) { 
        	$(idMsg).html($('<span class="valid">'+data.message+'</span>'));
        },
		error: function(error) {
			$(idMsg).html($('<span class="invalid">Sorry ! You cannot connect to server</span>'));
		}
	});
}

function checkConnect(idMsg)
{
	var urlPost = "rest/analyse/checkConnect"; 	
	$.ajax({
        url: urlPost,
        contentType: "application/json",
        dataType: "json",
        type: "GET",
        success: function(data) { 
        	$(idMsg).html($('<span class="valid">'+data.message+'</span>'));
        },
		error: function(error) {
			$(idMsg).html($('<span class="invalid">Sorry ! You cannot connect to server</span>'));
		}
	});
}

