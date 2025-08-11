<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "user_management";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// Ensure the directories for uploads exist
$logoDir = "./assets/uploads/logos/";
$moTaFileDir = "./assets/uploads/files/";


if (!is_dir($logoDir)) {
    mkdir($logoDir, 0777, true);
}

if (!is_dir($moTaFileDir)) {
    mkdir($moTaFileDir, 0777, true);
}

// Handle file uploads and sanitize file names
$logoPath = $logoDir . basename($_FILES["Logo"]["name"]);
$moTaFilePath = $moTaFileDir . basename($_FILES["MoTaFile"]["name"]);

$logoPath = str_replace(" ", "_", $logoPath);
$moTaFilePath = str_replace(" ", "_", $moTaFilePath);

if (!move_uploaded_file($_FILES["Logo"]["tmp_name"], $logoPath)) {
    die("Failed to upload logo.");
}

if (!move_uploaded_file($_FILES["MoTaFile"]["tmp_name"], $moTaFilePath)) {
    die("Failed to upload file.");
}

// Prepare and bind
$stmt = $conn->prepare("INSERT INTO nhatuyendung (Logo, TenCTY, MucLuong, SoLuong, GioiTinh, DiaChiLamViec, TrinhDoYeuCau, ChucVu, TTLH, NgayYeuCau, NgayKetThuc, MoTaFile) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
$stmt->bind_param("ssssssssssss", $logoPath, $_POST['TenCTY'], $_POST['MucLuong'], $_POST['SoLuong'], $_POST['GioiTinh'], $_POST['DiaChiLamViec'], $_POST['TrinhDoYeuCau'], $_POST['ChucVu'], $_POST['TTLH'], $_POST['NgayYeuCau'], $_POST['NgayKetThuc'], $moTaFilePath);

// Execute the query
$stmt->execute();

$stmt->close();
$conn->close();

// Redirect to the display page
header("Location: thongtinNTD.php");
exit();
?>
