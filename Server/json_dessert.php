<?php
	require 'config.php';

	$query = "SELECT ID, NameDessert, CostDessert, LinkDessert FROM dessert";
	$result = mysqli_query($conn, $query);
	$response = array();

	if(mysqli_num_rows($result) > 0) {
		while($row = mysqli_fetch_array($result)) {
			array_push($response, array("ID" => $row[0], "NameDessert" => $row[1], 
			"CostDessert" => $row[2], "LinkDessert" => $row[3]));
		}	
	}
	echo json_encode($response);
	mysqli_close($conn);
?>