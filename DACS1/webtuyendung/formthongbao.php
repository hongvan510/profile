<?php
session_start();
require 'config.php';

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $MaUV = $_POST['MaUV'];
    $MaNhaTD = $_POST['MaNhaTD'];
    $username = $_POST['username'];
    $email = $_POST['email'];
    $HovaTen = $_POST['HovaTen'];
    $CCCD = $_POST['CCCD'];
    $SDT = $_POST['SDT'];
    $Ngaysinh = $_POST['Ngaysinh'];
    $TrinhDoChuyenMon = $_POST['TrinhDoChuyenMon'];
?>

<!DOCTYPE html>
<html>
<head>
    <title>Gửi Thông Báo</title>
</head>
<body>
    <h2>Gửi Thông Báo</h2>
    <form method="post" action="guithongbao.php">
        <input type="hidden" name="MaUV" value="<?php echo htmlspecialchars($MaUV); ?>">
        <input type="hidden" name="MaNhaTD" value="<?php echo htmlspecialchars($MaNhaTD); ?>">
        <input type="hidden" name="username" value="<?php echo htmlspecialchars($username); ?>">
        <input type="hidden" name="email" value="<?php echo htmlspecialchars($email); ?>">
        <input type="hidden" name="HovaTen" value="<?php echo htmlspecialchars($HovaTen); ?>">
        <input type="hidden" name="CCCD" value="<?php echo htmlspecialchars($CCCD); ?>">
        <input type="hidden" name="SDT" value="<?php echo htmlspecialchars($SDT); ?>">
        <input type="hidden" name="Ngaysinh" value="<?php echo htmlspecialchars($Ngaysinh); ?>">
        <input type="hidden" name="TrinhDoChuyenMon" value="<?php echo htmlspecialchars($TrinhDoChuyenMon); ?>">

        <label for="noidung">Lịch phỏng vấn:</label><br>
        <textarea name="noidung" id="noidung" rows="4" cols="50" required></textarea><br><br>

        <label for="loaitb">Loại thông báo:</label><br>
        <select name="loaitb" id="loaitb" required>
            <option value="Dau">Đậu</option>
            <option value="Rot">Rớt</option>
        </select><br><br>

        <button type="submit">Gửi Thông Báo</button>
    </form>
</body>
</html>

<?php
} else {
    echo "Invalid request method.";
}
?>
