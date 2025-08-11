<?php
include 'config.php';
session_start();

if (!isset($_SESSION['username'])) {
    header("Location: login.php");
    exit();
}

$username = $_SESSION['username'];

// Truy vấn để lấy id và email người dùng từ bảng Users
$user_sql = "SELECT id, email FROM Users WHERE username='$username'";
$user_result = $conn->query($user_sql);
$user_row = $user_result->fetch_assoc();
$user_id = $user_row['id'];
$email = $user_row['email'];

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $hovaten = $_POST['hovaten'];
    $ngaysinh = $_POST['ngaysinh'];
    $gioitinh = $_POST['gioitinh'];
    $cccd = $_POST['cccd'];
    $sdt = $_POST['sdt'];
    $trinhdochuyenmon = $_POST['trinhdochuyenmon'];
    $email = $_POST['email']; // Lấy email từ form

    // Xử lý file upload ảnh
    if (isset($_FILES['anh']) && $_FILES['anh']['error'] == UPLOAD_ERR_OK) {
        $target_dir = "./assets/uploads/";
        $target_file = $target_dir . basename($_FILES["anh"]["name"]);
        move_uploaded_file($_FILES["anh"]["tmp_name"], $target_file);
    } else {
        $target_file = $row['Anh']; // Giữ nguyên ảnh cũ nếu không có file mới được tải lên
    }

    // Cập nhật bảng HOSOV
    $sql = "UPDATE HOSOUV SET HovaTen='$hovaten', Ngaysinh='$ngaysinh', GioiTinh='$gioitinh', CCCD='$cccd', SDT='$sdt', TrinhDoChuyenMon='$trinhdochuyenmon', email='$email' ,Anh='$target_file' WHERE user_id='$user_id'";
    $conn->query($sql);

    // Cập nhật email trong bảng Users
    $email_sql = "UPDATE Users SET email='$email' WHERE id='$user_id'";
    $conn->query($email_sql);

    header("Location: trangchu.php");
    exit();
}

// Kiểm tra nếu không có bản ghi nào
$sql = "SELECT * FROM HOSOUV WHERE user_id='$user_id'";
$result = $conn->query($sql);
if ($result->num_rows == 0) {
    // Tạo bản ghi mới nếu chưa có
    $sql = "INSERT INTO HOSOUV (user_id) VALUES ('$user_id')";
    $conn->query($sql);
    $sql = "SELECT * FROM HOSOUV WHERE user_id='$user_id'";
    $result = $conn->query($sql);
}
$row = $result->fetch_assoc();
?>

<!DOCTYPE html>
<html>
<head>
    <title>Cập nhật hồ sơ cá nhân </title>
</head>
<body>
<h2>Cập nhật hồ sơ cá nhân</h2>
    <form method="post" action="" enctype="multipart/form-data">
        <div class="form-container">
            <div class="form-group">
                <label for="hovaten">Họ và tên:</label>
                <input type="text" id="hovaten" name="hovaten" value="<?php echo htmlspecialchars($row['HovaTen']); ?>" required>
            </div>
            <div class="form-group">
                <label for="ngaysinh">Ngày sinh:</label>
                <input type="date" id="ngaysinh" name="ngaysinh" value="<?php echo htmlspecialchars($row['Ngaysinh']); ?>" required>
            </div>
            <div class="form-group">
                <label for="gioitinh">Giới tính:</label>
                <input type="text" id="gioitinh" name="gioitinh" value="<?php echo htmlspecialchars($row['Gioitinh']); ?>" required>
            </div>
            <div class="form-group">
                <label for="cccd">CCCD:</label>
                <input type="text" id="cccd" name="cccd" value="<?php echo htmlspecialchars($row['CCCD']); ?>" required>
            </div>
            <div class="form-group">
                <label for="sdt">Số điện thoại:</label>
                <input type="text" id="sdt" name="sdt" value="<?php echo htmlspecialchars($row['SDT']); ?>" required>
            </div>
            <div class="form-group">
                <label for="trinhdochuyenmon">Trình độ chuyên môn:</label>
                <input type="text" id="trinhdochuyenmon" name="trinhdochuyenmon" value="<?php echo htmlspecialchars($row['TrinhDoChuyenMon']); ?>" required>
            </div>
            <div class="form-group">
                <label for="anh">Ảnh:</label>
                <input type="file" id="anh" name="anh">
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" value="<?php echo htmlspecialchars($row['email']); ?>" required>
            </div>
        </div>
        <input type="submit" value="Cập nhật">
    </form>



    <style>
        /* Your CSS styles here */
        .form-container {
            display: flex;
            flex-wrap: wrap;
            justify-content: space-between;
            margin-top: 20px;
        }

        .form-group {
            flex-basis: 48%;
            margin-bottom: 15px;
            display: flex;
            flex-direction: column;
        }

        .form-group label {
            margin-bottom: 5px;
            color: #339341; /* Adjust color as needed */
        }

        .form-group input[type="text"],
        .form-group input[type="email"],
        .form-group input[type="date"] {
            padding: 8px;
            height: 30px;
            border: 1px solid #ccc;
            border-radius: 4px;
            outline: none;
        }

        .form-group input[type="file"] {
            padding: 5px;
            border: 1px solid #ccc;
            border-radius: 4px;
            outline: none;
        }

        .form-group input[type="text"]:focus,
        .form-group input[type="email"]:focus,
        .form-group input[type="date"]:focus,
        .form-group input[type="file"]:focus {
            border-color: #339341; /* Adjust focus color as needed */
        }

        input[type="submit"] {
            display: block;
            width: 100%;
            padding: 10px;
            font-size: 15px;
            background-color: #339341;
            color: white;
            border: none;
            cursor: pointer;
            border-radius: 5px;
            margin-top: 10px;
        }
    </style>

</body>
</html>
