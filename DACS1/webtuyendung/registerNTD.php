<?php
$servername = "localhost"; // Replace with your database server name
$username = "root"; // Replace with your database username
$password = ""; // Replace with your database password
$dbname = "user_management"; // Replace with your database name

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $usernameNTD = $_POST['usernameNTD'];
    $passwordNTD = $_POST['passwordNTD'];
    $emailNTD = $_POST['emailNTD'];

    // Check if username already exists
    $checkUserStmt = $conn->prepare("SELECT * FROM usersntd WHERE usernameNTD = ?");
    $checkUserStmt->bind_param("s", $usernameNTD);
    $checkUserStmt->execute();
    $checkUserStmt->store_result();

    if ($checkUserStmt->num_rows > 0) {
        echo "tài khoản đã tồn tại, Vui lòng đăng kí tài khoản khác";
    } else {
        // Prepare and bind
        $stmt = $conn->prepare("INSERT INTO usersntd (usernameNTD, passwordNTD, emailNTD) VALUES (?, ?, ?)");
        $stmt->bind_param("sss", $usernameNTD, $passwordNTD, $emailNTD);

        // Execute the statement
        if ($stmt->execute()) {
        echo "<script>alert('Bạn đăng ký thành công, nhấn OK để đến trang đăng nhập');
        
        </script>";
        echo "<script>window.location.href = 'loginNTD.html';</script>";
           // header("Location: loginNTD.html");
        } else {
            echo "Error: tài khoản đã tồn tại" . $stmt->error;
        }

        // Close statement
        $stmt->close();
    }

    // Close check user statement
    $checkUserStmt->close();
}

$conn->close();
?>
