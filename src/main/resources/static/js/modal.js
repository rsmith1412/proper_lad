		$(document).ready(function() {
			var form,
				modal = $(".new_prod_modal").dialog({
				autoOpen: false,
				height: 800px,
				width: 500px,
				modal: true,
				close: function() {
					form[0].reset();
				}
			});
//			if(errors > 0) {
//				console.log("In this bitty");
//				modal.autoOpen = true;
////				$(modal).show();
//			}
			form = modal.find("form").on("submit", function(event) {
				event.preventDefault();
			})
			$('#new_product').button().on("click", function() {
				modal.dialog("open");
			});
			$(".new_prod_modal").validate({
			    rules: {
			      name: {
			        required: true,
			        minlength: 3
			      },
			      brand: {
			    	  required:true,
			    	  minLength: 2
			      }, 
			      description: {
			    	  required: true,
			    	  minLength: 5
			      },
			      price: {
			    	  required: true,
			    	  minValue: 0.01
			      },
			      description: {
			    	  required: true,
			    	  minLength: 5
			      }
			      action: "required"
			    },
			    messages: {
			      name: {
			        required: "Please enter a name",
			        minlength: "Name must be at least 3 characters."
			      },
			      brand: {
				    required: "Please enter a name",
				    minlength: "Name must be at least 3 characters."
				  },
			      action: "Please provide some data"
			    },
			    submitHandler: function(form) {
			        $.ajax({
			            url: form.action,
			            type: form.method,
			            data: $(form).serialize(),
//			            success: function(response) {
//			                $('#answers').html(response);
//			            }            
			        });
			    }
			  });
			
		})