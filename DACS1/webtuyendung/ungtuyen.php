<?php
session_start();
include 'config.php';

 echo htmlspecialchars($_SESSION['username']);


// Kiểm tra xem form đã được submit và các biến POST cần thiết có tồn tại không
if(isset($_POST['apply']) && isset($_POST['user_id']) && isset($_POST['job_id'])) {
    // Khởi tạo và làm sạch các biến đầu vào
    $user_id = intval($_POST['user_id']);
    $job_id = intval($_POST['job_id']);
    
    // Lấy thông tin người dùng
    $user_query = "SELECT Username, email FROM users WHERE id = $user_id";
    $user_result = mysqli_query($conn, $user_query);
    if (!$user_result || mysqli_num_rows($user_result) == 0) {
        die('Lỗi khi lấy thông tin người dùng: ' . mysqli_error($conn));
    }
    $user = mysqli_fetch_assoc($user_result);

    // Lấy thông tin công việc
    $job_query = "SELECT MaNhaTD FROM nhatuyendung WHERE id = $job_id";
    $job_result = mysqli_query($conn, $job_query);
    if (!$job_result || mysqli_num_rows($job_result) == 0) {
        die('Lỗi khi lấy thông tin công việc: ' . mysqli_error($conn));
    }
    $job = mysqli_fetch_assoc($job_result);

    // Lấy thông tin hồ sơ của người dùng
    $hosouv_query = "SELECT * FROM hosouv WHERE user_id = $user_id";
    $hosouv_result = mysqli_query($conn, $hosouv_query);
    if (!$hosouv_result || mysqli_num_rows($hosouv_result) == 0) {
        die('Lỗi khi lấy thông tin hồ sơ của người dùng: ' . mysqli_error($conn));
    }
    $hosouv = mysqli_fetch_assoc($hosouv_result);

    // Chuẩn bị dữ liệu để chèn vào bảng `ungtuyen`
    $maHS = $hosouv['MaHS'];
    $maNhaTD = $job['MaNhaTD'];
    $username = $user['Username'];

    // Chèn dữ liệu vào bảng `ungtuyen`
    $insert_query = "INSERT INTO ungtuyen (user_id, MaHS, MaNhaTD, Username) 
                     VALUES ($user_id, $maHS, $maNhaTD, '$username')";
    
    if (mysqli_query($conn, $insert_query)) {
        echo "Ứng tuyển thành công.";
    } else {
        echo "Lỗi: " . $insert_query . "<br>" . mysqli_error($conn);
    }
} else {
    die('Dữ liệu cần thiết chưa được thiết lập.');
}
?>

<!DOCTYPE html>
<html>
<head>
    <title>Ứng tuyển công việc</title>
</head>
<body>
    <?php
    if (isset($_GET['user_id']) && isset($_GET['job_id'])) {
        $user_id = intval($_GET['user_id']);
        $job_id = intval($_GET['job_id']);

        // Lấy thông tin người dùng
        $user_query = "SELECT Username, email FROM users WHERE id = $user_id";
        $user_result = mysqli_query($conn, $user_query);
        if ($user_result && mysqli_num_rows($user_result) > 0) {
            $user = mysqli_fetch_assoc($user_result);
            $username = $user['Username'];
            $email = $user['email'];
        } else {
            die('Lỗi khi lấy thông tin người dùng: ' . mysqli_error($conn));
        }

        // Lấy thông tin hồ sơ của người dùng
        $hosouv_query = "SELECT * FROM hosouv WHERE user_id = $user_id";
        $hosouv_result = mysqli_query($conn, $hosouv_query);
        if ($hosouv_result && mysqli_num_rows($hosouv_result) > 0) {
            $hosouv = mysqli_fetch_assoc($hosouv_result);
        } else {
            die('Lỗi khi lấy thông tin hồ sơ của người dùng: ' . mysqli_error($conn));
        }
    ?>
        <form method="post" action="ungtuyen.php">
            <input type="hidden" name="user_id" value="<?php echo htmlspecialchars($user_id); ?>">
            <input type="hidden" name="job_id" value="<?php echo htmlspecialchars($job_id); ?>">
            <p>Email: <?php echo htmlspecialchars($email); ?></p>
            <p>Tất cả thông tin từ bảng hosouv:</p>
            <ul>
                <?php
                foreach ($hosouv as $key => $value) {
                    echo '<li>' . htmlspecialchars($key) . ': ' . htmlspecialchars($value) . '</li>';
                }
                ?>
            </ul>
            <p>Bạn có chắc chắn muốn ứng tuyển cho công việc này không?</p>
            <button type="submit" name="apply">Ứng tuyển</button>
        </form>
    <?php
    } else {
        echo "Dữ liệu không hợp lệ.";
    }
    ?>
</body>
</html>
