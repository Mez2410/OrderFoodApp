<?php
	require 'config.php';

	$query = "SELECT ID, NameFood, CostFood, LinkFood FROM eating";
	$result = mysqli_query($conn, $query);
	$response = array();

	if(mysqli_num_rows($result) > 0) {
		while($row = mysqli_fetch_array($result)) {
			array_push($response, array("ID" => $row[0], "NameFood" => $row[1], 
			"CostFood" => $row[2], "LinkFood" => $row[3]));
		}	
	}
	echo json_encode($response);
	mysqli_close($conn);
?>