<?php
session_start();
require 'config.php';

include 'tongvieclam.php';

$total_vieclam = countvieclam();
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-sCHmWouuZVco2fMveXjf69TwJic9LelrHRKCt0tAFbgtP3LGfFKtPvMjy4gz+6NRnWG0oxRfA5A/W4ikgXzF8g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="./assets/font/fontawesome-free-6.5.2-web/css/all.css">
    <title>Thông tin nhà tuyển dụng</title>
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
                    <li><a href="form.php">Nộp đơn xin việc </a></li>  
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
                        <a class="btn" style="color: #fff;" href="register.html">
                            <span>Đăng ký </span>
                        </a>
                    </li>
                </div>
                <div class="warpper">
                    <li>
                        <a class="btn" style="color: #fff;" href="user_dashboard.php">
                            <span>Xem thông tin cá nhân</span>
                        </a>
                    </li>
                </div>
                
            </ul>
        </nav>
    </div>
</div>


<?php


if ($_SERVER['REQUEST_METHOD'] == 'POST' && isset($_POST['delete'])) {
    $maNhaTD = $conn->real_escape_string($_POST['delete']);
    $deleteSql = "DELETE FROM nhatuyendung WHERE MaNhaTD='$maNhaTD'";
    $conn->query($deleteSql) === TRUE;
    
}
// Get search parameters
$searchSalary = isset($_GET['salary']) ? $_GET['salary'] : '';
$searchCompany = isset($_GET['company']) ? $_GET['company'] : '';
$searchPosition = isset($_GET['position']) ? $_GET['position'] : '';
$searchAddress = isset($_GET['address']) ? $_GET['address'] : '';

// Build the SQL query with search conditions
$sql = "SELECT * FROM nhatuyendung WHERE 1=1";

if ($searchSalary) {
    $sql .= " AND MucLuong LIKE '%" . $conn->real_escape_string($searchSalary) . "%'";
}
if ($searchCompany) {
    $sql .= " AND TenCTY LIKE '%" . $conn->real_escape_string($searchCompany) . "%'";
}
if ($searchPosition) {
    $sql .= " AND ChucVu LIKE '%" . $conn->real_escape_string($searchPosition) . "%'";
}
if ($searchAddress) {
    $sql .= " AND DiaChiLamViec LIKE '%" . $conn->real_escape_string($searchAddress) . "%'";
}

$result = $conn->query($sql);
?>




<div></div>
<div></div>
<div></div>
<div></div>





<h2>Tìm Kiếm Công Việc</h2>

<p style="background-color: #1d9c1b; width:max-content; padding:6px 8px; color: #fff;" >Tổng việc làm hiện có : <?php echo $total_vieclam ?></p>

<form method="GET" action="" class="form-container">
    <div class="form-group">
        <label style="color: #267026;" for="salary"><i class="fa-solid fa-money-bill-wave"></i> Mức Lương (triệu):</label>
        <input class="timkiem" type="number" id="salary" name="salary" value="<?php echo htmlspecialchars($searchSalary); ?>">
    </div>
    <div class="form-group">
        <label style="color: #267026;" for="company"><i class="fa-solid fa-building"></i> Tên Công Ty:</label>
        <input class="timkiem" type="text" id="company" name="company" value="<?php echo htmlspecialchars($searchCompany); ?>">
    </div>
    <div class="form-group">
        <label style="color: #267026;" for="position"><i class="fa-solid fa-briefcase"></i> Chức Vụ:</label>
        <input class="timkiem" type="text" id="position" name="position" value="<?php echo htmlspecialchars($searchPosition); ?>">
    </div>
    <div class="form-group">
        <label style="color: #267026;" for="address"><i class="fa-solid fa-map-marker-alt"></i> Địa chỉ:</label>
        <input class="timkiem" type="text" id="address" name="address" value="<?php echo htmlspecialchars($searchAddress); ?>">
    </div>
    <div class="form-group">
        <button class="button-search" type="submit">Tìm Kiếm</button>
    </div>
</form>

<?php
if ($result->num_rows > 0) {
    echo "<div class='scroll-container'>";
    echo "<div class='job-list'>";
    while($row = $result->fetch_assoc()) {
        echo "<div class='job-item'>";
        echo "<img src='" . $row['Logo'] . "' alt='Logo'>";
        echo "<div class='job-details'>";
        echo "<h3 class='job-title'>" . $row['TenCTY'] . " - Mã NTD: " . $row['MaNhaTD'] . "</h3>";
        echo "<div class='job-company'>Tuyển: " . $row['ChucVu'] . "</div>";
        echo "<div class='job-company'>LH: " . $row['TTLH'] . "</div>";
        echo "<div class='job-salary'>Mức lương: " . $row['MucLuong'] ." - Địa chỉ: " . $row['DiaChiLamViec'] . "</div>";
        echo "<div class='job-gioitinh'>Giới tính: " . ($row['GioiTinh']) . " | Trình độ: " . ($row['TrinhDoYeuCau']) . "</div>";
        echo "<div class='job-company'>Mô tả công việc: <a href='" . $row['MoTaFile'] . "' download>Tải về .dox</a></div>";
        echo "<div class='job-ngayyeucau'>Từ: " . ($row['NgayYeuCau']) . " - Đến: " . ($row['NgayKetThuc']) . "</div>";
        echo "</div>";
        echo "<a href='form.php'><button class='ungtuyen'  type='submit'>Ứng tuyển</button></a>";
        
        echo "</div>";
    }
    echo "</div>";
    echo "</div>";
} else {
    echo "<p>Không tìm thấy công việc nào.</p>";
}

$conn->close();
?>

<style>
form .dangxuat{
        background-color: #22b21f;
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
    
  /*
    .user-logout {
      display: flex;
      align-items: center;
  }
  
  .user-logout .username {
      margin-right: 10px;
      font-weight: bold;
      color: #22b21f;
  }
  .................*/
  /*Css công việc nhà tuyển dụng đăng lên */
  
  
   /**/ 
  body {
      font-family: Arial, sans-serif;
      background-color: #f2f2f2;
      margin: 0;
      padding: 0;
      text-decoration: none;
  }
  .container {
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
  
  }
  .form-group {
      flex-grow: 1;
      margin-right: 10px; 
  }
  .form-container {
      display: flex;
      justify-content: space-between;
      background-color: #fff;
      padding: 20px;
      border-radius: 10px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  }
  h2 {
      text-align: center;
      color: #339341;
      font-size: 30px;
  }
  form {
      margin-top: 20px;
  }
  label {
      display: flex;
      margin-bottom: 5px;
      color: #555;
  }
  input[type="text"] {
      width: 100%;
      padding: 8px;
      border: 1px solid #ccc;
      border-radius: 5px;
      box-sizing: border-box;
      font-size: 16px;
  }
  input[type="number"] {
    width: 100%;
    padding: 8px;
    border: 1px solid #ccc;
    border-radius: 5px;
    box-sizing: border-box;
    font-size: 16px;
  }
  button {
      margin-top: 26px;
      width: 300px;
      height: max-content;
      padding: 8px;
      font-size: 15px;
      background-color: #339341;
      color: white;
      border: none;
      cursor: pointer;
      border-radius: 5px;
  }
  button:hover {
      background-color: #267026;
  }
  .job-list {
      margin-top: 20px;
  }
  .job-item {
      background-color: #fff;
      padding: 15px -4px;
      margin-bottom: 10px;
      margin-top: 15px;
      margin-left: 2px;
      border-radius: 10px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
      display: flex;
      justify-content: space-between;
      align-items: center;
      border: 1px solid #1d9c1b;
      width: 1450px;
  }
  .job-item:hover{
      background-color: #f3f5f7;
  }
  .job-item img {
      width: 140px;
      height: 140px;
      border-radius: 15px;
      margin-right: 15px;
  }
  .job-details {
      flex-grow: 1;
  }
  .job-title {
      margin: 10px;
      font-size: 18px;
      color: #339341;
      margin: 8;
  }
  .job-company, .job-salary, .job-address {
      margin: 5px 0;
      
    }
  .delete-form {
      margin-left: 20px;
      margin-bottom: -20px;
      margin-right: 10px;
  
  }
  .job-ngayyeucau{
      margin-right: -80px;
      text-align: right;
      transform: translateY(-600%);
      font-size: 12px;
  }
  .ungtuyen{
      transform: translateY(150%);
      text-decoration: none;
      border:1px solid #339341;
      text-align: center;
      padding: 4px;
      width: max-content;
      background-color: #339341 ;
      border-radius: 5px;
      color:#FFF;
      margin-right: 15px;
  }
  .scroll-container {
    height: 700px; 
    overflow-y: auto;
    border: 1px solid #ddd; 
    padding: 4px; 
}


</style>




<!--phần cuối-->
<div style="margin-top: 50px;">
<div class="the-end">
    <div class="the-end-first">
        <h1 id="h1-end">MoonNiyy</h1>
        <ul>
            <li><i class="fa-solid fa-phone fa-beat"></i><b> Liên hệ</b> : 012346577</li>
            <li><i class="fa-solid fa-phone fa-beat"></i> Hotline : 18001008</li>
            <li><i class="fa-regular fa-envelope"></i> Email : hotro@moonniyy.vn</li>
            <li>Cộng đồng MoonNiyy</li>
            <li><a href="facebook.com"><i class="fa-brands fa-facebook"></i></a></li>
        </ul>
    </div>
    <div class="the-end-first">
        <h1 class="h2-end">Về MoonNiyy</h1>
        <ul>
            <li><a href="">Giới thiệu</a></li>
            <li><i class="fa-regular fa-paper-plane"></i><a href=""></i> Góc báo chí</a></li>
            <li><a href="">Tuyển dụng</a></li>
            <li><i class="fa-solid fa-phone"></i><a href=""></i> Liên hệ</a></li>
            <li><i class="fa-regular fa-comment"></i><a href=""> Hỏi đáp</a></li>
            <li><a href="">Chính sách bảo mật</a></li>
            <li><a href="">Điều khoản dịch vụ</a></li>
            
            <li><a href="">Việc làm cần thiết</a></li>
            <li><a href="">Việt làm phù hợp</a></li>
            <li><a href="">Việc làm mới nhất</a></li>
            
        </ul>
    </div>
    <div class="the-end-first">
        <h1 class="h2-end">Trợ giúp</h1>
        <ul>
            <li><a href="facebook.com">Liên hệ FB <i class="fa-brands fa-facebook"></i></a></li>
            <li><a href="">Liên hệ hotline</a></li>
            <li><i class="fa-regular fa-address-card"></i><a href=""></i> Đăng kí</a></li>
            <li><i class="fa-solid fa-user"></i><a href=""></i> Đăng nhập</a></li>
        </ul>
    </div>
</div>

<div class="the-end">
    <div>
        <ul>
            <li style="list-style: none; font-size: 20px;"><b>Địa chỉ :</b> Nguyễn Văn Cừ nối dài, Long Tuyền, Cần Thơ.</li>
            <li style="text-align: right; margin-right: 30%; list-style: none ;">
                <ul id ="ul-end">
                    <li class="end-li"><a href=""><i style="font-size: 100px;  color:#22B21F;" class="fa-brands fa-facebook"></i></a></li>
                    <li class="end-li"><a href=""><i style="font-size: 100px; color:#21b21f; " class="fa-brands fa-instagram"></i></a></li>
                    <li class="end-li"><a href=""><i style="font-size: 100px; color:#22B21F; " class="fa-solid fa-envelope"></i></a></li>
                </ul>
            </li>
        </ul>
    </div>
</div>

</div>
<style>


    
/*CSS phần cuối the end*/

.the-end{
    
  display: flex;
  justify-content:space-around ;
  text-align: center;
  background-color: #b9f5b683;
 
}
.the-end-first ul li{
  list-style:none ;
  text-align: left;
  padding: 2px 4px;
}
.the-end-first ul li a{
text-decoration: none;
}
#h1-end{
  color: #339341;
  font-size: 30px;
  letter-spacing: 1rem;
  font-weight: 200;
  font-family: Georgia, 'Times New Roman', Times, serif;
}
.h2-end{
  color: #339341;
  font-size: 30px;
  font-weight: 700;
}
.the-end-first a{
   color: #000;
}
.the-end-first a:hover{
  border-bottom: 1px solid black;
}
#ul-end{
  list-style: none;
  display:flex ;
  float: right;
  
}
.end-li{
    margin-left: 20px;
    
}

</style>


</body>
</html>
