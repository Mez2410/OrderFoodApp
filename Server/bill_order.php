<?php
	require 'config.php';

	$status = $_POST["StatusCode"];
	$num_tab = $_POST["NumberTable"];
	$employee_code = $_POST["EmployeeCode"];
	$employee_name = $_POST["EmployeeName"];

	$response = array();

	if($status == '' || $num_tab == '' || $employee_code == '' || $employee_name == '') {

		$code = "error";
		array_push($response, array("StatusCode" => $code));

	} else {
		$query = "INSERT INTO billorder (StatusCode, NumberTable, EmployeeCode,
		EmployeeName, TimeOrderTab) VALUES ('$status', '$num_tab', 
		'$employee_code', '$employee_name', NOW())";

		if(mysqli_query($conn, $query)) {

			$sql = "SELECT * FROM billorder ORDER BY BillCode DESC LIMIT 1";
			$result = mysqli_query($conn, $sql);
			$row = mysqli_fetch_array($result);
			array_push($response, array("StatusCode" => $row[0], "BillCode" => $row[1], 
				"TimeOrder" => $row[5]));

		} else {
			$code = "error";
			array_push($response, array("StatusCode" => $code));
		}
	}
	echo json_encode($response);
	mysqli_close($conn);
?>