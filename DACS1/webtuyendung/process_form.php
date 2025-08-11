<?php
session_start();
require 'config.php';

?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-sCHmWouuZVco2fMveXjf69TwJic9LelrHRKCt0tAFbgtP3LGfFKtPvMjy4gz+6NRnWG0oxRfA5A/W4ikgXzF8g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="./assets/wfont/fontawesome-free-6.5.2-web/css/all.css">
    <title>Load thông tin</title>
</head>
<body>
    
<style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f2f2f2;
      margin: 0;
      padding: 0;
      text-decoration: none;
  }

  
body{
    font-family: Arial, sans-serif;
        background-color: #f2f2f2;
        margin: 0;
        padding: 0;
        text-decoration: none;
  }
  /* Header Styles */
  .header {
      background-color: #b9f5b6;
      
      }
      
      .header_navbar-one {
        display: flex;
        justify-content: space-between;
        align-items: center;
        max-width: 1400px;
        margin: 0 auto;
        padding: 0 1rem;
      }
      
      .header_navbar-one .logo_2 p {
        color: #22b21f;
        font-size: 2rem;
        font-weight: bold;
        letter-spacing: 5px;
        margin-left: -10%;
        
      }
      
      #header_navbar-menu-one,
      #header_navbar-menu-two {
        list-style: none;
        display: flex;
        align-items: center;
      }
      
      #header_navbar-menu-one li,
      #header_navbar-menu-two li {
        position: relative;
      }
      
      #header_navbar-menu-one li a
       {
        color: #22b21f;
        text-decoration: none;
        padding: 8px 10px; 
        display: block; 
        font-weight: 600;
        width: max-content + calc(15px);
        text-align: left;
        margin-left: 15px ;
        margin-right: 10px;
        transition: background-color 0.3s ease, color 0.3s ease;
      }
      #header_navbar-menu-two li a {
        color: #22b21f;
        text-decoration: none;
        padding: 12px 10px; 
        display: block; 
        font-weight: 600;
        width: max-content;
        text-align: left;
        margin-left: 15px ;
        margin-right: 10px;
        transition: background-color 0.3s ease, color 0.3s ease;
      }
      #header_navbar-menu-one li:hover > a
       {
        background-color: #ecf0f1;
        color: #27ae60;
        border-radius: 5px;
      }
      #header_navbar-menu-two li:hover > a {
        background-color: #1b9e19f8;
        
      }
      
      .dropdown .submenu {
        display: none;
        position: absolute;
        top: 100%;
        left: 0;
        background-color: #b9f5b6;
        list-style: none;
        padding: 0;
        min-width: 200px;
        border-radius: 0 0 5px 5px;
        box-shadow: 5px 10px 8px 16px rgba(0, 0, 0, 0.1);
        z-index: 1000;
        opacity: 0;
        visibility: hidden;
        transform: translateY(20px);
        transition: opacity 0.3s ease, visibility 0.3s ease, transform 0.3s ease;
        transition-delay: 0.3s;
      }
      
      .dropdown:hover .submenu,
      .submenu:hover {
        display: block;
        opacity: 1;
        visibility: visible;
        transform: translateY(0);
        transition-delay: 0s; 
      }
      
      .submenu li {
        width: 100%;
      }
      
      .submenu li a {
        padding: 0.75rem 1.5rem;
        
        color: #ecf0f1;
        white-space: nowrap;
        text-align: left;
        transition: background-color 0.3s ease, color 0.3s ease;
      }
      
      .submenu li a:hover {
        background-color: #16a085;
        color: #fff;
      }
      
      /*Đk , ĐN , ĐT*/
      /* Button Styles */
      .btn {
        display: inline-block;
        background-color: #22b21f;  
        color: #f0f0f0;
        padding: 0.75rem 1.5rem;
        text-decoration: none;
        border-radius: 5px;
        height: max-content;
        transition: background-color 0.3s ease, transform 0.3s ease, box-shadow 0.3s ease;
      }
      
      .btn:hover {
        background-color: #16a085;
        transform: translateY(-3px);
        box-shadow: 4px 4px 4px  rgba(0, 0, 0, 0.2);
      }
      
      /* Warpper Styles */
      .warpper {
        margin-left: 1rem;
      }
      
      /* Hero Section Styles */
      .anh1 {
        background: url('../img/anhtuyendung1.jpg') no-repeat center center;
        background-size: cover;
        height: 500px;
        position: relative;
      }
      
      .chaomung {
        text-align: center;
        padding: 2rem;
        color: #fff;
      }
      
      /*...............................*/
      /*Css người dùng*/
      .btn button{
        color: #fff ;
        padding: 4px 6px;
        border: none;
        height: 32px;
        background-color: #1d9c1b;
        border-radius: 10px;
        margin-left: 2px;
        cursor: pointer;
      }
      .btn button:hover{
        background-color: #117964;
      }
      
    
      
    
    
      /* */s
</style>
<!-- phần menu -->
<div class="header">
    <div class="header_navbar">
        <nav class="header_navbar-one">
            <a style="text-decoration: none;" href="trangchu.php">
                <div class="logo_2"><p>MoonNiyy</p></div>
            </a>
            <ul id="header_navbar-menu-one">
                <li><a href="trangchu.php">Trang chủ</a></li>
                
                <li class="dropdown"><a href="user_dashboard.php">Hồ sơ CV</a>
                    <ul class="submenu">
                        <li><a href="xemhosochoungvien.php">Xem hồ sơ đã nộp</a></li>
                        <li><a href="xemthongbaochoungvien.php">Xem thông báo</a></li>
                        <li><a href="xemthongbaochoungvien.php">xem lịch phỏng vấn</a></li>
                    </ul>
                </li>
                <li class="dropdown"><a href="trangchu.php">Việc làm</a>
                    <ul class="submenu">    
                    </ul>
                </li>
            </ul>
            <ul id="header_navbar-menu-two">
                <?php if (isset($_SESSION['username'])): ?>
                    <div class="warpper">
                        <li class="user-logout">
                            <div style="display: flex;">
                                <a href="user_dashboard.php">
                                    <span class="username"><p style="font-size:12px"> chào ứng viên</p><i class="fa-solid fa-user-tie"></i> <?php echo htmlspecialchars($_SESSION['username']); ?></span>
                                </a>
                                    <form style="margin-top: 0px;" action="logout.php" method="post">
                                        
                                        <button class="dangxuat"  type="submit" name="logout">Đăng xuất</button>
                                    </form>
                                <style>
                                    .dangxuat{
                                        background-color: #22b21f;
                                        border:none; 
                                        margin-left: -5px;
                                        margin-top: 30px; 
                                        width:max-content; 
                                        padding:3px;
                                        color: #fff;
                                        font-weight: 600;
                                        border-radius: 8px; 
                                        cursor: pointer; 
                                    }
                                    .dangxuat:hover{
                                        background-color: #22b21f;
                                        transform: translateY(-3px);
                                        box-shadow: 4px 4px 4px rgba(0, 0, 0, 0.2);
                                    }
                                </style>
                            </div>
                        </li>
                    </div>
                <?php else: ?>
                    <div class="warpper">
                        <li>
                            <a class="btn" style="color: #fff;" href="login.html">
                                <span>Đăng nhập</span>
                            </a>
                        </li>
                    </div>
                <?php endif; ?>
                <div class="warpper">
                    <li>
                        <a class="btn" style="color: #fff;" href="user_dashboard.php">
                            <span>Xem thông tin cá nhân</span>
                        </a>
                    </li>
                </div>
                <div class="warpper">
                    <li>
                        <a class="btn" style="color: #fff;" href="registerNTD.html">
                            <span>Đăng ký với nhà tuyển dụng</span>
                        </a>
                    </li>
                </div>
                
            </ul>
        </nav>
    </div>
</div>

</body>
</html>
<?php


if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    // Lấy thông tin từ form
    $username = $_POST['username'];
    $email = $_POST['email'];
    $hoten = $_POST['hoten'];
    $ngaysinh = $_POST['ngaysinh'];
    $gioitinh = $_POST['gioitinh'];
    $cccd = $_POST['cccd'];
    $sdt = $_POST['sdt'];
    $trinhdo = $_POST['trinhdo'];
    $manhatd = $_POST['manhatd'];

    // Kiểm tra xem người dùng đã nhập mã nhà tuyển dụng hay chưa
    if (empty($manhatd)) {
        echo "Vui lòng nhập mã nhà tuyển dụng.";
        exit();
    }

    // Kiểm tra mã nhà tuyển dụng có tồn tại trong bảng nhatuyendung hay không
    $sql_check_nhatuyendung = "SELECT MaNhaTD FROM nhatuyendung WHERE MaNhaTD = ?";
    $stmt = $conn->prepare($sql_check_nhatuyendung);
    $stmt->bind_param('s', $manhatd);
    $stmt->execute();
    $result_check_nhatuyendung = $stmt->get_result();

    if ($result_check_nhatuyendung->num_rows > 0) {
        // Nếu tồn tại, thêm thông tin vào bảng ungvien
        $row_nhatuyendung = $result_check_nhatuyendung->fetch_assoc();
        $manhatd_id = $row_nhatuyendung['MaNhaTD']; // ID là khóa chính của bảng nhatuyendung
        
        $sql_insert_ungvien = "INSERT INTO ungvien (email, username, HovaTen, Ngaysinh, Gioitinh, CCCD, SDT, TrinhDoChuyenMon, MaNhaTD) 
                               VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        $stmt_insert = $conn->prepare($sql_insert_ungvien);
        $stmt_insert->bind_param('ssssssssi', $email, $username, $hoten, $ngaysinh, $gioitinh, $cccd, $sdt, $trinhdo, $manhatd_id);

        if ($stmt_insert->execute()) {
            echo "Thông tin ứng viên đã được gửi thành công và vui lòng kiểm tra lại";

            // Giả sử ID tài khoản hiện tại được lưu trữ trong biến $taiKhoanId
            $taiKhoanId = $_SESSION['username']; // Ví dụ lấy ID từ session

            // Viết truy vấn SQL để lấy dữ liệu theo ID tài khoản
            $sql = "SELECT * FROM UNGVIEN WHERE MaUV";
            $result = mysqli_query($conn, $sql);

            // Kiểm tra xem có bản ghi nào được trả về hay không
            if (mysqli_num_rows($result) > 0) {
                // Có bản ghi, tiếp tục xử lý
            } else {
                echo "Không tìm thấy dữ liệu nào cho tài khoản này.";
                exit;
            }

            if ($result->num_rows > 0) {
                echo "<table border='2'>
                        <tr>
                            <th>Mã Hồ Sơ</th>
                            <th>Username</th>
                            <th>Mã tuyển dụng xin việc/th>
                            <th>Email</th>
                            <th>Họ Và Tên</th>
                            <th>CCCD</th>
                            <th>Sđt</th>
                            <th>Ngày sinh</th>
                            <th>Trình Độ</th>
                                
                        </tr>";

                while($row = $result->fetch_assoc()) {
                    echo "<tr>
                            <td>" . htmlspecialchars($row["MaUV"]) . "</td>
                            <td>" . htmlspecialchars($row["username"]) . "</td>
                            <td>" . htmlspecialchars($row["MaNhaTD"]) . "</td>
                            <td>" . htmlspecialchars($row["email"]) . "</td>
                            <td>" . htmlspecialchars($row["HovaTen"]) . "</td>
                            <td>" . htmlspecialchars($row["CCCD"]) . "</td>
                            <td>" . htmlspecialchars($row["SDT"]) . "</td>
                            <td>" . htmlspecialchars($row["Ngaysinh"]) . "</td>
                            <td>" . htmlspecialchars($row["TrinhDoChuyenMon"]) . "</td>
                            <td>
                                <form method='post' action='formthongbao.php'>
                                    <input type='hidden' name='MaUV' value='" . htmlspecialchars($row['MaUV']) . "'>
                                    <input type='hidden' name='username' value='" . htmlspecialchars($row['username']) . "'>
                                    <input type='hidden' name='email' value='" . htmlspecialchars($row['email']) . "'>
                                    <input type='hidden' name='HovaTen' value='" . htmlspecialchars($row['HovaTen']) . "'>
                                    <input type='hidden' name='CCCD' value='" . htmlspecialchars($row['CCCD']) . "'>
                                    <input type='hidden' name='SDT' value='" . htmlspecialchars($row['SDT']) . "'>
                                    <input type='hidden' name='Ngaysinh' value='" . htmlspecialchars($row['Ngaysinh']) . "'>
                                    <input type='hidden' name='TrinhDoChuyenMon' value='" . htmlspecialchars($row['TrinhDoChuyenMon']) . "'>
                                </form>
                            </td>
                        </tr>";
                }
                echo "</table>";
            } else {
                echo "Không có dữ liệu để hiển thị.";
            }

            $conn->close();

        } else {
            echo "Lỗi khi thêm thông tin vào bảng ungvien: " . $stmt_insert->error;
        }

        $stmt_insert->close();
    } else {
        echo "Mã nhà tuyển dụng không tồn tại.";
    }

    $stmt->close();
    
} else {
    // Nếu không phải là phương thức POST, không làm gì cả hoặc điều hướng điều hướng về trang khác
    header('Location: index.php');
    exit();
}
?>
<style>
table {
    border-collapse: collapse;
    width: 95%;
    margin-left: 2%;
    background-color: #cce2cf;
    margin-top: 20px;
    margin-bottom: 20px;
}
th, td {
    border: 1px solid black;
    padding: 14px 12px;
    text-align: left;
}
th {
    background-color: #339133;
}
.LoaiTB {
    background-color: #e65acfd7;
}
</style>
