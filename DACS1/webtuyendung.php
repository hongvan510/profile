
<?php
if (session_start()) {
    header("Location: ./webtuyendung/trangchu.php");
    exit();
}

if (isset($_POST['logout'])) {
    session_destroy();
    header("Location: login.html");
    exit();
}
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    trang chuyển hướng 
</body>
</html>