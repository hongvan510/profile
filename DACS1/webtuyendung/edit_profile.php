<?php
include 'config.php';
session_start();

if (!isset($_SESSION['username'])) {
    header("Location: login.php");
    exit();
}

$username = $_SESSION['username'];

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $hovaten = $_POST['hovaten'];
    $ngaysinh = $_POST['ngaysinh'];
    $gioitinh = $_POST['gioitinh'];
    $cccd = $_POST['cccd'];
    $sdt = $_POST['sdt'];
    $trinhdochuyenmon = $_POST['trinhdochuyenmon'];
    $anh = $_POST['anh'];

    $sql = "UPDATE HOSOV SET HovaTen='$hovaten', Ngaysinh='$ngaysinh', GioiTinh='$gioitinh', CCCD='$cccd', SDT='$sdt', TrinhDoChuyenMon='$trinhdochuyenmon', Anh='$anh' WHERE user_id=(SELECT id FROM Users WHERE username='$username')";
    $conn->query($sql);

    header("Location: user_dashboard.php");
    exit();
}

$sql = "SELECT * FROM HOSOV WHERE user_id=(SELECT id FROM Users WHERE username='$username')";
$result = $conn->query($sql);
$row = $result->fetch_assoc();
?>

<!DOCTYPE html>
<html>
<head>
    <title>Chỉnh sửa hồ sơ</title>
</head>
<body>
    <h2>Chỉnh sửa hồ sơ</h2>
    <form method="post" action="">
        Họ và tên: <input type="text" name="hovaten" value="<?php echo $row['HovaTen']; ?>" required><br>
        Ngày sinh: <input type="date" name="ngaysinh" value="<?php echo $row['Ngaysinh']; ?>" required><br>
        Giới tính: <input type="text" name="gioitinh" value="<?php echo $row['GioiTinh']; ?>" required><br>
        CCCD: <input type="text" name="cccd" value="<?php echo $row['CCCD']; ?>" required><br>
        Số điện thoại: <input type="text" name="sdt" value="<?php echo $row['SDT']; ?>" required><br>
        Trình độ chuyên môn: <input type="text" name="trinhdochuyenmon" value="<?php echo $row['TrinhDoChuyenMon']; ?>" required><br>
        Ảnh: <input type="text" name="anh" value="<?php echo $row['Anh']; ?>" required><br>
        <input type="submit" value="Cập nhật">
    </form>
</body>
</html>
