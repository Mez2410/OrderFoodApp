<?php
	$SERVER_NAME = "localhost";
	$USER_NAME = "mezcal";
	$PASSWORD = "Vuphihung_2410";
	$DATABASE_NAME = "orderfood";

	$conn = mysqli_connect($SERVER_NAME, $USER_NAME, $PASSWORD, $DATABASE_NAME);

	mysqli_set_charset($conn, "utf8");

	// if(!$connect) {
	// 	die("Connection failed: " . mysqli_connect_error());
	// } else {
	// 	echo "Connected Successfully";
	// }
?>