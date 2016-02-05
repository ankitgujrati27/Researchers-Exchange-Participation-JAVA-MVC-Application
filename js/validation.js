/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){ 
						   
	$("#conf_pass, #user_pass").focusout(function(){
            
           if($( "#conf_pass" ).val() !== $( "#user_pass" ).val())
           {
               $("#reg_submit").attr("disabled", "disabled");
               $("#conf_pass").addClass("border_red");
               $("#user_pass").addClass("border_red");
           }
           else
           {
               $("#reg_submit").removeAttr("disabled");
               $("#conf_pass").removeClass("border_red");
               $("#user_pass").removeClass("border_red");
           }
        });
        
        
        $("#reset_pass, #conf_reset_pass").focusout(function(){
            
           if($( "#reset_pass" ).val() !== $( "#conf_reset_pass" ).val())
           {
               $("#reset_password_submit").attr("disabled", "disabled");
               $("#reset_pass").addClass("border_red");
               $("#conf_reset_pass").addClass("border_red");
           }
           else
           {
               $("#reset_password_submit").removeAttr("disabled");
               $("#reset_pass").removeClass("border_red");
               $("#conf_reset_pass").removeClass("border_red");
           }
        });
        
        
        
        
        
	
});

function open_page(url)
{
    window.location = url;
}

function updateStatus(id, action){
    document.getElementById(id).action = action;
    document.getElementById(id).submit();
}


