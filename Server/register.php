<?php
	require 'config.php';

	$id_employee = $_POST["EmployeeCode"];
	$name = $_POST["Name"];
	$pass = $_POST["Password"];
	$pos = $_POST["Position"];

	$query = "SELECT * FROM register WHERE EmployeeCode LIKE '$id_employee'";

	$result = mysqli_query($conn, $query);
	$response = array();

	if (mysqli_num_rows($result) > 0) {

		$code = "id_overlap";
		$message = "Mã nhân viên đã có người sử dụng! Vui lòng nhập lại!";
		array_push($response, array("code" => $code, "message" => $message));
		
	} else {

		$query = "INSERT INTO register (EmployeeCode, Name, Password, Position)
				VALUES ('$id_employee', '$name', '$pass', '$pos')";

		$result = mysqli_query($conn, $query);

		if(!$result) {
			
			$code = "reg_failed";
			$message = "Đăng ký không thành công";
			array_push($response, array("code" => $code, "message" => $message));

		} else {

			$code = "reg_success";
			$message = "Đăng ký thành công! Nhấn OK để quay về màn hình đăng nhập!";
			array_push($response, array("code" => $code, "message" => $message));

		}
	}
	echo json_encode($response);
	mysqli_close($conn);
?>