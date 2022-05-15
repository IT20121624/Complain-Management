$(document).ready(function() 
{  
		$("#alertSuccess").hide();  
	    $("#alertError").hide(); 
}); 
 
 
// SAVE ============================================ 
$(document).on("click", "#btnSave", function(event) 
{  
	// Clear alerts---------------------  
	$("#alertSuccess").text("");  
	$("#alertSuccess").hide();  
	$("#alertError").text("");  
	$("#alertError").hide(); 
 
	// Form validation-------------------  
	var status = validateComplainForm();  
	if (status != true)  
	{   
		$("#alertError").text(status);   
		$("#alertError").show();   
		return;  
	} 
 
	// If valid------------------------  
	var type = ($("#hidComplainIDSave").val() == "") ? "POST" : "PUT"; 

	$.ajax( 
	{  
		url : "ComplainAPI",  
		type : type,  
		data : $("#formComplain").serialize(),  
		dataType : "text",  
		complete : function(response, status)  
		{   
			onComplainSaveComplete(response.responseText, status);  
		} 
	}); 
});


function onComplainSaveComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			$("#alertSuccess").text("Successfully saved.");    
			$("#alertSuccess").show(); 

			$("#divComplainGrid").html(resultSet.data);   
		} else if (resultSet.status.trim() == "error")   
		{    
			$("#alertError").text(resultSet.data);    
			$("#alertError").show();   
		} 

	} else if (status == "error")  
	{   
		$("#alertError").text("Error while saving.");   
		$("#alertError").show();  
	} else  
	{   
		$("#alertError").text("Unknown error while saving..");   
		$("#alertError").show();  
	} 

	$("#hidComplainIDSave").val("");  
	$("#formComplain")[0].reset(); 
}


//UPDATE========================================== 
$(document).on("click", ".btnUpdate", function(event) 
{     
	$("#hidComplainIDSave").val($(this).data("compid"));     
	$("#cusId").val($(this).closest("tr").find('td:eq(0)').text());     
	$("#cusName").val($(this).closest("tr").find('td:eq(1)').text());     
	$("#date").val($(this).closest("tr").find('td:eq(2)').text());  
	$("#compType").val($(this).closest("tr").find('td:eq(3)').text());
	$("#compDcription").val($(this).closest("tr").find('td:eq(4)').text());     
	  
	
});


//REMOVE===========================================
$(document).on("click", ".btnRemove", function(event) 
{  
	$.ajax(  
	{   
		url : "ComplainAPI",
		type : "DELETE",
		data : "compId=" + $(this).data("compid"),
		dataType : "text",
		complete : function(response, status)
		{
			onComplainDeleteComplete(response.responseText, status);   
		}
	}); 
});


function onComplainDeleteComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			
			$("#alertSuccess").text("Successfully deleted.");    
			$("#alertSuccess").show(); 
		
			$("#divComplainGrid").html(resultSet.data); 
			
		} else if (resultSet.status.trim() == "error")   
		{    
			$("#alertError").text(resultSet.data);    
			$("#alertError").show();   
		}
		

	} else if (status == "error")  
	{   
		$("#alertError").text("Error while deleting.");   
		$("#alertError").show();  
	} else  
	{   
		$("#alertError").text("Unknown error while deleting..");   
		$("#alertError").show();  
	}
}

//CLIENT-MODEL================================================================
function validateComplainForm()
{
	// 	CUS ID
	if ($("#cusId").val().trim() == "")
	{
		return "Insert Item Code.";
	}
	
	// CUS NAME
	if ($("#cusName").val().trim() == "")
	{
		return "Insert Item Name.";
	}
	
	// DATE
	if ($("#date").val().trim() == "")
	{
		return "Insert Item Date.";
	}
	
	// TYPE-------------------------------
	if ($("#compType").val().trim() == "")
	{
		return "Insert Total Price.";
	}
	
		
	// DESCRIPTION------------------------
	if ($("#compDcription").val().trim() == "")
	{
		return "Insert Item Postal No.";
	}
	return true;
}

