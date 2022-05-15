<%@page import="com.PAF.Complain"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Complain Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css"> 
<script src="Components/jquery.min.js"></script> 
<script src="Components/Complain.js"></script> 

</head>
<body>
<div class="container"> 
	<div class="row">  
		<div class="col"> 
			<h1>COMPLAIN DETAILS</h1>
				<form class="row g-3" id="formComplain" name="formComplain" method="post" action="complain.jsp">  
					<div class="col-md-6">
						<label class="form-label">Customer ID:</label>  
	 	 				<input id="cusId" name="cusId" type="text"  class="form-control form-control-sm" placeholder="Enter Customer ID" required>
					</div>
					
					<div class="col-md-6">
						<label class="form-label">Customer Name:</label>
						<input id="cusName" name="cusName" type="cusName" class="form-control form-control-sm" placeholder="Customer Name"   required>
					</div>    
  					
  					<div class="col-md-6">
	  					<label class="form-label">Date:</label>
	  					<input id="date" name="date" type="text" class="form-control form-control-sm" placeholder="Enter Date" required>
  					</div>
					 
					<div class="col-md-6">
						<label class="form-label">Complain Type:</label>
					  	<input id="compType" name="compType" type="text" class="form-control form-control-sm" placeholder="Enter Complain Type" required>
					</div>    
					
					<div class="col-md-6">
						<label class="form-label">Complain Description:</label>
						<input id="compDcription" name="compDcription" type="text" class="form-control form-control-sm" placeholder="Enter Complain" required>
					</div>   
  					
					  
  					<div>
  					</div>
  					<div class="col-12">
	  					<input id="btnSave" name="btnSave" type="button" value="Submit Complain" class="btn btn-primary" required>  
						<input type="hidden" id="hidComplainIDSave" name="hidComplainIDSave" value="">
  					</div>
					 
				</form>
				
				<div id="alertSuccess" class="alert alert-success"> </div>
				
				<div id="alertError" class="alert alert-danger"></div>
				
				<br>
				<div id="divComplainGrid">
					<%
					Complain comObj = new Complain();
									out.print(comObj.readComplain());
					%>
				</div>
			</div>
		</div>
</div>

</body>
</html>