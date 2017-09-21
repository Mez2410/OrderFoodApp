<?php
	require 'config.php';

	$code = $_POST["CodeBill"];

	$sql = "SELECT * FROM billdetail WHERE CodeBill LIKE '$code'";
	$res = mysqli_query($conn, $sql);
	$stt = new stdClass();

	if(mysqli_num_rows($res) > 0) {
		$query = "DELETE FROM billdetail WHERE CodeBill LIKE '$code'";
		$result = mysqli_query($conn, $query);

		if($result) {
			$stt->status = "success";
		} else {
			$stt->status = "error";
			echo "Error: " . mysqli_error($conn);
		}
	} else {
		$stt->status = "not_data";
	}
	echo json_encode($stt);
	mysqli_close($conn);
?>