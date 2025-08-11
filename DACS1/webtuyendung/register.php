<?php
session_start();
require 'config.php';

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $username = $_POST['username'];
    $password = password_hash($_POST['password'], PASSWORD_BCRYPT);
    $email = $_POST['email'];

    $sql = "INSERT INTO users (username, password ,email) VALUES (?, ?, ?)";
    $stmt = $conn->prepare($sql);
    $stmt->bind_param("sss", $username, $password,$email);

    if ($stmt->execute()) {
        echo "<script>alert('Bạn đã đăng ký thành công, nhấn OK để đi đến trang đăng nhập');
        
        </script>";
        echo "<script>window.location.href = 'login.html';</script>";
        exit(); 
    } else {
        echo "Lỗi: " . $stmt->error;
    }

    $stmt->close();
    $conn->close();
}
?>
