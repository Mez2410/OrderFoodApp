<?php
	require 'config.php';

	$name = $_POST["NameFood"];
	$quantum = $_POST["QuantumFood"];
	$cost = $_POST["CostFood"];
	$code = $_POST["CodeBill"];

	// $name = "Mực Nướng Muối Ớt";
	// $quantum = "2";
	// $cost = "150000";
	// $code = "15";

	$response = array();

	if($name == '' || $quantum == '' || $cost == '' || $code == '') {

		echo "Error";

	} else {

		$query = "INSERT INTO billdetail (NameFood, QuantumFood, CostFood, TimeOrder, CodeBill) 
		VALUES ('$name', '$quantum', '$cost', NOW(), '$code')";

		if(mysqli_query($conn, $query)) {
			
			$sql = "SELECT * FROM billdetail WHERE CodeBill LIKE '$code'";
			$result = mysqli_query($conn, $sql);

			while($row = mysqli_fetch_array($result)) {
				array_push($response, array("ID" => $row[0], "NameFood" => $row[1], 
					"QuantumFood" => $row[2], "CostFood" => $row[3],
					"TimeOrder" => $row[4], "CodeBill" => $row[5]));
			}
		} else {

			echo "Error";

		}
	}
	echo json_encode($response);
	mysqli_close($conn);
?>