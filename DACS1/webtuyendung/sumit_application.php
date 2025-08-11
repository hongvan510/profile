<?php
session_start();
include 'config.php';

// Giả sử user_id được lưu trong session
$user_id = $_SESSION['user_id'];

// Lấy thông tin người dùng
$user_query = "SELECT * FROM username WHERE id='$user_id'";
$user_result = $conn->query($user_query);
$user = $user_result->fetch_assoc();

// Lấy thông tin nhà tuyển dụng và hồ sơ
$MaNhaTD = $_GET['MaNhaTD']; // Giả sử MaNhaTD được truyền qua tham số GET
$hoso_query = "SELECT * FROM hoso WHERE user_id='$user_id'";
$hoso_result = $conn->query($hoso_query);
$hoso = $hoso_result->fetch_assoc();
?>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Biểu mẫu ứng tuyển</title>
</head>
<body>
    <form action="submit_application.php" method="post">
        <label for="username">Tên đăng nhập:</label>
        <input type="text" id="username" name="username" value="<?php echo $user['username']; ?>" readonly><br><br>

        <label for="MaNhaTD">Mã nhà tuyển dụng:</label>
        <input type="text" id="MaNhaTD" name="MaNhaTD" value="<?php echo $MaNhaTD; ?>" readonly><br><br>

        <label for="MaHS">Mã hồ sơ:</label>
        <input type="text" id="MaHS" name="MaHS" value="<?php echo $hoso['MaHS']; ?>" readonly><br><br>

        <label for="HovaTen">Họ và tên:</label>
        <input type="text" id="HovaTen" name="HovaTen" value="<?php echo $hoso['HovaTen']; ?>"><br><br>

        <label for="Ngaysinh">Ngày sinh:</label>
        <input type="date" id="Ngaysinh" name="Ngaysinh" value="<?php echo $hoso['Ngaysinh']; ?>"><br><br>

        <label for="Gioitinh">Giới tính:</label>
        <input type="text" id="Gioitinh" name="Gioitinh" value="<?php echo $hoso['Gioitinh']; ?>"><br><br>

        <label for="CCCD">CCCD:</label>
        <input type="text" id="CCCD" name="CCCD" value="<?php echo $hoso['CCCD']; ?>"><br><br>

        <label for="SDT">Số điện thoại:</label>
        <input type="text" id="SDT" name="SDT" value="<?php echo $hoso['SDT']; ?>"><br><br>

        <label for="TrinhDoChuyenMon">Trình độ chuyên môn:</label>
        <input type="text" id="TrinhDoChuyenMon" name="TrinhDoChuyenMon" value="<?php echo $hoso['TrinhDoChuyenMon']; ?>"><br><br>

        <label for="Anh">Ảnh:</label>
        <input type="text" id="Anh" name="Anh" value="<?php echo $hoso['Anh']; ?>"><br><br>

        <input type="hidden" name="user_id" value="<?php echo $user['id']; ?>">
        
        <input type="submit" value="Ứng tuyển">
    </form>
</body>
</html>
