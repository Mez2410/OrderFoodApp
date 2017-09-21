<?php
	require 'config.php';

	$code = $_POST["CodeBill"];

	$query = "SELECT * FROM billdetail WHERE CodeBill LIKE '$code'";
	$result = mysqli_query($conn, $query);
	$response = array();

	if(mysqli_num_rows($result) > 0) {
		while($row = mysqli_fetch_array($result)) {
			array_push($response, array("ID" => $row[0], "NameFood" => $row[1], 
				"QuantumFood" => $row[2], "CostFood" => $row[3], 
				"TimeOrder" => $row[4], "CodeBill" => $row[5]));	
		}
		$stt = "success";
	} else {
		$stt = "not_data";
	}
	echo json_encode(array("status" => $stt, "data" => $response));
	mysqli_close($conn);
?>