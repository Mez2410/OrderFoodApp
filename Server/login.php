<?php
	require 'config.php';

	$employee_code = $_POST["EmployeeCode"];
	$password = $_POST["Password"];
	

	$query = "SELECT * FROM register 
	WHERE EmployeeCode LIKE '$employee_code' AND Password LIKE '$password'";

	$result = mysqli_query($conn, $query);
	$response = array();

	if(mysqli_num_rows($result) > 0) {
 		$code = "login_success";
 		$row = mysqli_fetch_array($result);
    	array_push($response, array("code" => $code, "employee_code" => $row[1], "name" => $row[2], 
    		"position" => $row[4]));
	} else {
 		$code = "login_failed";
 		$message = "Login failed";
    	array_push($response, array("code" => $code, "message" => $message));
	}
	echo json_encode($response);
	mysqli_close($conn);
?>