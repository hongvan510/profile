<?php
include 'config.php';
session_start();

if (isset($_SESSION['username'])) {
    header("Location: trangchu.php");
    exit();
}

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $username = $_POST['username'];
    $password = $_POST['password'];

    // Truy vấn thông tin người dùng
    $sql = "SELECT * FROM Users WHERE username='$username'";
    $result = $conn->query($sql);

    if ($result->num_rows > 0) {
        $row = $result->fetch_assoc();
        // Kiểm tra mật khẩu
        if (password_verify($password, $row['password'])) {
            $_SESSION['username'] = $username;
            $user_id = $row['id'];

            // Kiểm tra xem hồ sơ đã được cập nhật chưa
            $profile_sql = "SELECT * FROM HOSOUV WHERE user_id='$user_id'";
            $profile_result = $conn->query($profile_sql);

            if ($profile_result->num_rows > 0) {
                // Chuyển hướng đến trang chủ nếu hồ sơ đã được cập nhật
                header("Location: trangchu.php");
            } else {
                // Chuyển hướng đến trang cập nhật hồ sơ nếu hồ sơ chưa được cập nhật
                header("Location: update_profile.php");
            }
            exit();
        } else {
            echo "Sai mật khẩu.";
        }
    } else {
        echo "Tên đăng nhập không tồn tại.";
    }
}
?>

