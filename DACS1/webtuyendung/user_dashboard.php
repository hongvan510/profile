<?php
include 'config.php';
session_start();

if (!isset($_SESSION['username'])) {
    header("Location: login.html");
    exit();
}





$username = $_SESSION['username'];

// Truy vấn để lấy id và email người dùng từ bảng Users
$user_sql = "SELECT id, email FROM Users WHERE username='$username'";
$user_result = $conn->query($user_sql);
$user_row = $user_result->fetch_assoc();
$user_id = $user_row['id'];
$email = $user_row['email'];

$sql = "SELECT * FROM HOSOUV WHERE user_id='$user_id'";
$result = $conn->query($sql);

// Kiểm tra nếu không có bản ghi nào
if ($result->num_rows == 0) {
    echo "Không tìm thấy hồ sơ cho người dùng này.";
    exit();
}

$row = $result->fetch_assoc();
?>

<!DOCTYPE html>
<html>
<head>
    <title>Trang thông tin hồ sơ</title>
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
    

</style>





<!--.................................................-->
<div class="container">
        <h2 style="color:#22b21f; font-size:40px">CV của <?php echo $username; ?></h2>
        <div class="profile-image">
            <?php if (!empty($row['Anh'])): ?>
                <img src="<?php echo $row['Anh']; ?>" alt="Ảnh hồ sơ">
            <?php else: ?>
                <p>Chưa có ảnh</p>
            <?php endif; ?>
            <p class="ngaysinh"><span class="section-title">Ngày sinh:</span> <?php echo $row['Ngaysinh']; ?></p>
        </div>
        <div class="profile-info">
            <p><span class="section-title"></span> <?php echo $row['HovaTen']; ?></p>
            <p><span class="section-title">Email:</span> <?php echo $email; ?></p>  
        </div>
        <div style="background-color: #27ae60; height:10px;width:300px;text-align:center;margin-top:-70px"></div>
        <div class="thanCV">
            <div class="thanCV-mot">
                <p><span class="section-title">Giới tính ứng viên :</span> <?php echo $row['Gioitinh']; ?></p>
                <p><span class="section-title">CCCD:</span> <?php echo $row['CCCD']; ?></p>
            </div>

            <div class="thanCV-hai">
                <p><span class="section-title">Liên hệ ứng viên:</span> <?php echo $row['SDT']; ?></p>
                
            </div>
        </div>

        <div class="trinhdo" ><p style="margin-left: ;"><span>Trình độ chuyên môn:</span> <?php echo $row['TrinhDoChuyenMon']; ?></p></div>
        <div class="links">
            <a href="update_profile.php">Sửa hồ sơ</a>
            <a href="logout.php">Đăng xuất</a>
        </div>
</div>

    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f0f4f8;
            margin: 0;
        
        }
        .thanCV{
            padding: 5px;
            margin-top: -5px;
            margin-left: -380px;
            display: flex;
            justify-content: space-around;
        }
        .thanCV p{
            color:#1d9c1b
        }
        .thanCV-mot p{
            font-size: 20px;
            font-weight: 500;
        }
        .thanCV-hai p{
            font-size: 20px;
            font-weight: 500;
        }
        .trinhdo{
            
            color:#333333;
            font-size: 20px;

        }
        .container {
            transform: translate(35%,1%);
            width: 800px;
            height: 800px;
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
            display: grid;
            
        }
        .ngaysinh{
            color: #1d9c1b;
            font-size: 30px;
            margin-bottom: 45px;
        }
        .profile-image {
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .profile-image img {
            border-radius: 30%;
            max-width: 250px;
            height: 250px;
            margin-top: -50px;
            border: 1px solid #333333;

        }

        h2 {
            grid-column: span 2;
            text-align: center;
            color: #333333;
        }

        .profile-info p {
            font-size: 16px;
            color: #555555;
            margin: 5px 0;
        }

        .profile-info p:nth-of-type(1) {
            margin-top: -280px;
            font-size: 35px;
            color: #555555;
            font-weight: 600;
            font-family:'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
        }
        .profile-info p:nth-of-type(2) {
            margin-top: 20px;
            font-size: 26px;
            color: #555555;
            font-weight: 600;
            font-family:'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
        }

        .profile-info {
            display: flex;
            flex-direction: column;
            justify-content: center;
        }

        .profile-info .section-title {
            font-weight: bold;
            margin-top: 20px;
            margin-bottom: 10px;
            color: #4caf50;
        }

        .links {
            grid-column: span 2;
            display: flex;
            justify-content: space-around;
            margin-top: 20px;
        }

        .links a {
            text-decoration: none;
            background-color: #4caf50;
            color: white;
            padding: 8px 12px;
            height: max-content;
            border-radius: 5px;
            transition: background-color 0.3s;
        }

        .links a:hover {
            background-color: #45a049;
        }
    </style>
</body>
</html>
