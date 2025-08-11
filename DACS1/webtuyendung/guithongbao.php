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
    $noidung = $_POST['noidung'];
    $loaitb = $_POST['loaitb'];

    // Insert notification into the thongbao table
    $sql_insert_thongbao = "INSERT INTO thongbao (MaUV,MaNhaTD, username, email, HovaTen, CCCD, SDT, Ngaysinh, TrinhDoChuyenMon, noidung, loaitb)
                            VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    $stmt_insert = $conn->prepare($sql_insert_thongbao);
    $stmt_insert->bind_param('issssssssss', $MaUV, $MaNhaTD, $username, $email, $HovaTen, $CCCD, $SDT, $Ngaysinh, $TrinhDoChuyenMon, $noidung, $loaitb);

    if ($stmt_insert->execute()) {
        echo "<script>alert('Gửi thông báo thành công, nhấn OK để đi đến quản lí thông báo');
        
        </script>";
        echo "<script>window.location.href = 'qlthongbao.php';</script>";
        exit(); 
    } else {
        echo "Lỗi khi gửi thông báo: " . $stmt_insert->error;
    }

    $stmt_insert->close();
    $conn->close();
} else {
    echo "Invalid request method.";
}
?>
