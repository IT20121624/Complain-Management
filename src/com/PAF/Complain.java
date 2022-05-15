package com.PAF;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mysql.cj.xdevapi.Table;

public class Complain {
	
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrodb", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	//read
	public String readComplain()  
	{   
		String output = ""; 
	
		try   
		{    
			Connection con = connect(); 
		
			if (con == null)    
			{
				return "Error while connecting to the database for reading."; 
			} 
	 
			// Prepare the html table to be displayed    
			output = "<table class='table' border='1'><thead class='table-dark'>"
					+ "<th>Customer ID</th>"
					+ "<th>CUS NAM</th>"
					+ "<th>Date</th>"
					+ "<th>Complain Type</th>"
					+ "<th>Complain</th>"
					+ "<th>Update</th>"
					+ "<th>Remove</th></thead>";
	 
			String query = "select * from complains"; 
			Statement stmt = con.createStatement(); 
			ResultSet rs = stmt.executeQuery(query); 
	 
			// iterate through the rows in the result set    
			while (rs.next())    
			{     
				String compId = Integer.toString(rs.getInt("compId")); 
				String cusId = rs.getString("cusId");
				String cusName = rs.getString("cusName");
				String date = rs.getString("date");
				String compType = rs.getString("compType");
				String compDcription = rs.getString("compDcription");

				// Add into the HTML table 
				output += "<tr><td><input id='hidComplainIDUpdate' "
						+ "name='hidComplainIDUpdate' "
						+ "type='hidden' value='" + compId + "'>" 
						+ cusId + "</td>"; 
				output += "<td>" + cusName + "</td>";
				output += "<td>" + date + "</td>";
				output += "<td>" + compType + "</td>";
				output += "<td>" + compDcription + "</td>";

				// buttons     
//				output += "<td><input name='btnUpdate' type='button'"
//						+ "value='Update' class='btnUpdate btn btn-secondary'></td>"
//						+ "<td><form method='post' action='Payment.jsp'>"
//						+ "<input name='btnRemove' type='submit'"
//						+ "value='Remove' class='btnRemove btn btn-danger'>"
//						+ "<input name='hidPaymentIDDelete' type='hidden'"
//						+ "value='" + payId + "'>" + "</form></td></tr>"; 
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary' data-compid='" + compId + "'></td>"       
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-compid='" + compId + "'>" + "</td></tr>"; 
		
			}
			con.close(); 
	 
			// Complete the HTML table    
			output += "</table>";   
		}   
		catch (Exception e)   
		{    
			output = "Error while reading the Complain.";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
	
	//Add details about the complain
	public String insertComplain(String cusId, String cusName, String date, String compType, String compDcription)  
	{   
		String output = ""; 
	 
		try
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{
				return "Error while connecting to the database for inserting.";
			} 
	 
			// create a prepared statement 
			String query = "insert into complains(`compId`,`cusId`,`cusName`,`date`,`compType`,`compDcription`)"+"values(?,?,?,?,?,?)";
	 
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	  
			// binding values    
			 preparedStmt.setInt(1, 0);
			 preparedStmt.setString(2, cusId);
			 preparedStmt.setString(3, cusName);
			 preparedStmt.setString(4, date);
			 preparedStmt.setString(5, compType);
			 preparedStmt.setString(6, compDcription);
			
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	   
			String newComplain = readComplain(); 
			output =  "{\"status\":\"success\", \"data\": \"" + newComplain + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while inserting the Complain.\"}";  
			System.err.println(e.getMessage());   
		} 
		
	  return output;  
	}
	
	//update
	
	public String updateComplain(String compId, String cusId, String cusName, String date, String compType, String compDcription)    
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{
				return "Error while connecting to the database for updating.";
			} 
	 
			// create a prepared statement    
			String query = "UPDATE complains SET cusId=?,cusName=?,date=?,compType=?,compDcription=? WHERE compId=?"; 
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setString(1, cusId);
			preparedStmt.setString(2, cusName);
			preparedStmt.setString(3, date);
			preparedStmt.setString(4, compType);
			preparedStmt.setString(5, compDcription);
			preparedStmt.setInt(6, Integer.parseInt(compId)); 
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	 
			String newComplain = readComplain();    
			output = "{\"status\":\"success\", \"data\": \"" + newComplain + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while updating the Complain.\"}";   
			System.err.println(e.getMessage());   
		} 
	 
	  return output;  
	} 
	
	//delete
	public String deleteComplain(String compId)   
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{
				return "Error while connecting to the database for deleting."; 
			} 
	 
			// create a prepared statement    
			String query = "delete from complains where compId=?";  
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setInt(1, Integer.parseInt(compId)); 
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	 
			String newComplain = readComplain();  
			    
			output = "{\"status\":\"success\", \"data\": \"" +  newComplain + "\"}";    
		}   
		catch (Exception e)   
		{    
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the Complain.\"}";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
}
