<?php
session_start();

if (!isset($_SESSION['usernameNTD'])) {
    header("Location: loginNTD.html");
    exit();
}

if (isset($_POST['logout'])) {
    session_destroy();
    header("Location: loginNTD.html");
    exit();
}
?>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-sCHmWouuZVco2fMveXjf69TwJic9LelrHRKCt0tAFbgtP3LGfFKtPvMjy4gz+6NRnWG0oxRfA5A/W4ikgXzF8g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="../font/fontawesome-free-6.5.2-web/css/all.css">
    <link rel="stylesheet" href="./assets/css/trangchu.css">
    <title>Job Application Form</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin-top: 0px;
            padding: 0;
        }
        h2 {
            text-align: center;
            color: #339341;
            font-size: 30px;
            background-color: #e0f7ea;
            padding: 20px;
            box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.1);
        }
        .JobForm {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            transform: translateY(30%);
            margin-top: 15px;
        }
        .form-container {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 60%;
            display: block;
            margin-top: 100px;
        }
        .form-group {
            margin-bottom: 15px;
            position: relative;
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
            color: #339341;
            font-weight: bold;
        }
        .form-group input,
        .form-group select,
        .form-group textarea {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
            outline: none;
        }
        .form-group input:focus,
        .form-group select:focus,
        .form-group textarea:focus {
            border-color: #339341;
        }
        .form-group .file-input {
            border: none;
        }
        button {
            display: block;
            width: 100%;
            padding: 10px;
            font-size: 15px;
            background-color: #339341;
            color: white;
            border: none;
            cursor: pointer;
            border-radius: 5px;
            margin-top: 20px;
        }
        button:hover {
            background-color: #287a2f;
        }
        .truycap{
            background-color: #22b21f;
            color: #fff;
            font-size: 15px;
            padding: 6px 8px;
            font-weight: 600;
            text-decoration: none;
            border: 5px;
        }
        .truycap:hover{
            background-color: #21b21fd7;
            transform: translateY(-3px);
            box-shadow: 4px 4px 4px rgba(0, 0, 0, 0.2);
        }
        
    </style>
</head>
<body>



    <style>
        


/* Reset CSS */


/*..................*/

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
                <li><a href="trangchuNTD.php">Trang chủ</a></li>
                
                <li class="dropdown"><a href="Hosouv.php">Quản lí hồ sơ</a>
                    <ul class="submenu">
                        <li><a href="Hosouv.php">Xem hồ sơ đã nộp</a></li>
                        <li><a href="qlhosouv.php">Gửi thông báo</a></li>
                        <li><a href="qlthongbao.php">Lịch phỏng vấn</a></li>
                    </ul>
                </li>
                <li class="dropdown"><a href="trangchuNTD.php">Quản lí việc làm</a>
                    <ul class="submenu">
                        <li><a href="Hosouv.php">Quản lí hồ sơ ứng viên</a></li>
                        <li><a href="trangchu.php">Quản lí việc làm</a></li>
                        <li><a href="qlthongbao.php">Quản lí thông báo và lịch phỏng vấn</a></li>
                        <li><a href="thongtinNTD.php">Đăng tuyển nhân sự</a></li>
                        
                    </ul>
                </li>
            </ul>
            <ul id="header_navbar-menu-two">
                <?php if (isset($_SESSION['usernameNTD'])): ?>
                    <div class="warpper">
                        <li class="user-logout">
                            <div style="display: flex;">
                                <a href="user_dashboard.php">
                                    <span class="username"><p style="font-size:12px"> chào nhà tuyển dụng</p><i class="fa-solid fa-user-tie"></i> <?php echo htmlspecialchars($_SESSION['usernameNTD']); ?></span>
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
                            <span>Đăng ký với ứng viên</span>
                        </a>
                    </li>
                </div>
                
            </ul>
        </nav>
    </div>
</div>




<div></div>
    <!--..............-->


    <div class="JobForm">
        <div class="form-container">
            <h2>Đăng tuyển nhân sự</h2>
            <form action="submitNTD.php" method="post" enctype="multipart/form-data">
                
               
                
                <div class="form-group">
                    <label for="TenCTY"><i class="fa-solid fa-building"></i> Tên Công Ty:</label>
                    <input type="text" name="TenCTY" id="TenCTY">
                </div>
                <div class="form-group">
                    <label for="MucLuong"><i class="fa-solid fa-money-bill-wave"></i> Mức Lương (triệu):</label>
                    <input type="text" name="MucLuong" id="MucLuong" >
                </div>
                <div class="form-group">
                    <label for="SoLuong"><i class="fa-solid fa-users"></i> Số Lượng:</label>
                    <input type="number" name="SoLuong" id="SoLuong">
                </div>
                <div class="form-group">
                    <label for="GioiTinh"><i class="fa-solid fa-venus-mars"></i> Giới Tính:</label>
                    <input type="text" name="GioiTinh" id="GioiTinh">
                </div>
                <div class="form-group">
                    <label for="DiaChiLamViec"><i class="fa-solid fa-map-marker-alt"></i> Địa Chỉ Làm Việc:</label>
                    <input type="text" name="DiaChiLamViec" id="DiaChiLamViec" placeholder="Thành phố, Huyện , Tỉnh, xã , ....">
                </div>
                <div class="form-group">
                    <label for="TrinhDoYeuCau"><i class="fa-solid fa-graduation-cap"></i> Trình Độ Yêu Cầu:</label>
                    <input type="text" name="TrinhDoYeuCau" id="TrinhDoYeuCau">
                </div>
                <div class="form-group">
                    <label for="ChucVu"><i class="fa-solid fa-briefcase"></i> Chức Vụ:</label>
                    <input type="text" name="ChucVu" id="ChucVu" placeholder="ví dụ : Tuyển Nhân viên thiết kế poster cho Công ty...">
                </div>
                <div class="form-group">
                    <label for="TTLH"><i class="fa-solid fa-briefcase"></i> Liên hệ (Email/SĐT nhà tuyển dụng):</label>
                    <input type="text" name="TTLH" id="TTLH" placeholder="ví dụ : Sđt/Email...">
                </div>
                <div class="form-group">
                    <label for="NgayYeuCau"><i class="fa-solid fa-calendar-alt"></i> Ngày Yêu Cầu:</label>
                    <input type="datetime-local" name="NgayYeuCau" id="NgayYeuCau">
                </div>
                <div class="form-group">
                    <label for="NgayKetThuc"><i class="fa-solid fa-calendar-alt"></i> Ngày Kết Thúc:</label>
                    <input type="datetime-local" name="NgayKetThuc" id="NgayKetThuc">
                </div>
                <div class="form-group">
                    <label for="MoTaFile"><i class="fa-solid fa-file-alt"></i> Mô tả công việc( File word):</label>
                    <input type="file" name="MoTaFile" id="MoTaFile" accept=".doc,.docx" class="file-input">
                </div>
                <div class="form-group">
                    <label for="Logo"><i class="fa-solid fa-image"></i> Logo công ty:</label>
                    <input type="file" name="Logo" id="Logo" accept="image/*" class="file-input" required>
                </div>
                <button type="submit">Đăng tuyển</button>
                <p>Sau khi đăng tuyển xong bạn bấm vào <a class="truycap" href="products.php">Xem thông tin đã đăng tuyển</a> </p>
                <p>Hoặc truy cập vào quản lí hệ thống <a class="truycap" href="qlht.php">Quản lí hệ thống</a> </p>
            </form>
        </div>
    </div>
</body>
</html>
