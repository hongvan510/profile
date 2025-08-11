
<?php
function countUNGVIEN() {
    $servername = "localhost"; 
    $username = "root"; 
    $password = ""; 
    $dbname = "user_management"; 

    // Tạo kết nối
    $conn = new mysqli($servername, $username, $password, $dbname);

    // Kiểm tra kết nối
    if ($conn->connect_error) {
        die("Kết nối thất bại: " . $conn->connect_error);
    }

    // Chuẩn bị và thực thi truy vấn
    $sql = "SELECT COUNT(*) as total_records FROM UNGVIEN";
    $result = $conn->query($sql);

    // Lấy kết quả
    if ($result->num_rows > 0) {
        $row = $result->fetch_assoc();
        $total_records = $row['total_records'];
    } else {
        $total_records = 0;
    }

    // Đóng kết nối
    $conn->close();

    return $total_records;
}
?>
