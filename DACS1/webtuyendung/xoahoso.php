<?php

include 'config.php'; // Kết nối đến cơ sở dữ liệu

if (isset($_GET['MaUV'])) {
    $MaUV = intval($_GET['MaUV']); 

    // Xóa bản ghi khỏi cơ sở dữ liệu
    $sql = "DELETE FROM UNGVIEN WHERE MaUV = $MaUV";

    if ($conn->query($sql) === TRUE) {
        header("Location: Hosouv.php?message=Xóa thành công");
        exit();
    } else {
        echo "Lỗi khi xóa: " . $conn->error;
    }
} else {
    echo "Không có ID được cung cấp.";
}

$conn->close();
?>
