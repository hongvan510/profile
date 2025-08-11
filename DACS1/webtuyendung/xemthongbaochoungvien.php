<?php
// Kết nối đến cơ sở dữ liệu
session_start();
require 'config.php';


include 'tongsoTBvalichPV.php';
$total_thongbao = countthongbao();

// Xóa một hàng nếu có yêu cầu
if (isset($_GET['delete_id'])) {
    $delete_id = $_GET['delete_id'];
    $sql_delete = "DELETE FROM thongbao WHERE MaTB = $delete_id";
    if ($conn->query($sql_delete) === TRUE) {
        echo "Xóa thành công";
    } else {
        echo "Lỗi khi xóa: " . $conn->error;
    }
}


// Lấy tất cả dữ liệu từ bảng thongbao
$sql = "SELECT * FROM thongbao";
$result = $conn->query($sql);



$searchUsername = isset($_GET['username']) ? $_GET['username'] : '';
$searchMaNhaTD = isset($_GET['MaNhaTD']) ? $_GET['MaNhaTD'] : '';
$searchLoaiTB = isset($_GET['LoaiTB']) ? $_GET['LoaiTB'] : '';
// Build the SQL query with search conditions
$sql = "SELECT * FROM thongbao WHERE 1=1";

if ($searchUsername) {
    $sql .= " AND username LIKE '%" . $conn->real_escape_string($searchUsername) . "%'";
}
if ($searchMaNhaTD) {
    $sql .= " AND MaNhaTD LIKE '%" . $conn->real_escape_string($searchMaNhaTD) . "%'";
}
if ($searchLoaiTB) {
    $sql .= " AND LoaiTB LIKE '%" . $conn->real_escape_string($searchLoaiTB) . "%'";
}

$result = $conn->query($sql);

?>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Danh sách thông báo</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-sCHmWouuZVco2fMveXjf69TwJic9LelrHRKCt0tAFbgtP3LGfFKtPvMjy4gz+6NRnWG0oxRfA5A/W4ikgXzF8g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="./assets/font//fontawesome-free-6.5.2-web/css/all.css">
    
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
    
  
    
  
  
    /* */
</style>
<style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f2f2f2;
      margin: 0;
      padding: 0;
      text-decoration: none;
  }
</style>
<!-- phần menu -->
<div class="header">
    <div class="header_navbar">
        <nav class="header_navbar-one">
            <a style="text-decoration: none;" href="index.php">
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
                    <li><a href="form.php">Nộp đơn xin việc làm</a></li>   
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
                <!--..............................................................................-->


    <h1 class="tieude">Tổng hợp danh sách thông báo của website</h1>
    <h2 style="background-color: #1d9c1b; width:max-content; padding:6px 8px; color: #fff;">Số lịch thông báo và phỏng vấn hiện đã gửi : <?php echo $total_thongbao ?></h2>
<div class="form-timkiem">
    <form method="GET" action="" class="form-container">
    <div class="form-group">
        <label style="color: #267026;" for="Username"><i class="fa-solid fa-money-bill-wave"></i> Tên người dùng:</label>
        <input class="timkiem" type="text" id="Username" name="Username" value="<?php echo htmlspecialchars($searchUsername); ?>">
    </div>
    <div class="form-group">
        <label style="color: #267026;" for="MaNhaTD"><i class="fa-solid fa-building"></i> Mã nhà tuyển dụng :</label>
        <input class="timkiem" type="number" id="MaNhaTD" name="MaNhaTD" value="<?php echo htmlspecialchars($searchMaNhaTD); ?>">
    </div>
    <div class="form-group">
        <label style="color: #267026;" for="LoaiTB"><i class="fa-solid fa-building"></i> Loại thông báo:</label>
        <input class="timkiem" type="text" id="LoaiTB" name="LoaiTB" value="<?php echo htmlspecialchars($searchLoaiTB); ?>">
    </div>
    <div class="form-group">
        <button class="button-search" type="submit">Tìm Kiếm</button>
    </div>

</form>
</div>
    <table border="2">
        <tr>
            <th>MaTB</th>
            <th>MaUV</th>
            <th>MaNhaTD</th>
            <th>Username</th>
            <th>Email</th>
            <th>Họ và Tên</th>
            <th>CCCD</th>
            <th>SDT</th>
            <th>Ngày sinh</th>
            <th>Trình độ chuyên môn</th>
            <th>Lịch phỏng vấn</th>
            <th class="LoaiTB">Loại thông báo</th>
           
        </tr>
        <?php
        if ($result->num_rows > 0) {
            while($row = $result->fetch_assoc()) {
                echo "<tr>";
                echo "<td>" . $row["MaTB"] . "</td>";       
                echo "<td>" . $row["MaUV"] . "</td>";
                echo "<td>" . $row["MaNhaTD"] . "</td>";
                echo "<td>" . $row["username"] . "</td>";
                echo "<td>" . $row["email"] . "</td>";
                echo "<td>" . $row["HovaTen"] . "</td>";
                echo "<td>" . $row["CCCD"] . "</td>";
                echo "<td>" . $row["SDT"] . "</td>";
                echo "<td>" . $row["Ngaysinh"] . "</td>";
                echo "<td>" . $row["TrinhDoChuyenMon"] . "</td>";
                echo "<td>" . $row["noidung"] . "</td>";
                echo "<td class='LoaiTB'>" . $row["loaitb"] . "</td>";
                echo "</tr>";
            }
        } else {
            echo "<tr><td colspan='12'>Không có dữ liệu</td></tr>";
        }
        $conn->close();
        ?>
    </table>

    <style>
        .tieude{
            background-color: #22b21f;
            padding: 5px 5px;
            color: #fff;
            font-size: 45px;
            width:100%;
            height: 50px;
            text-align: center;
        }
        .form-container{
            display: flex;
            padding: 8px;
            
            margin-left: 50px;
        }
        .form-group{
            margin-left: 20px;
            font-size: 20px;
        }
        .form-group input{
            border-radius: 10px;
            height: 20px ;
            padding: 5px;
        }
        .form-group button{
            height: 40px;
            color: #fff;
            background-color: #339133;
            border: none;
            padding: 8px 8px;
            width: 250px;
            font-weight: 500;
            border-radius: 20px;
            margin-top: -5px;
        }
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
        .LoaiTB{
            background-color: #e65acfd7;
        }

    </style>
</body>
</html>
