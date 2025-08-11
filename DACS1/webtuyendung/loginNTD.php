<?php
session_start();

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

    // Prepare and bind
    $stmt = $conn->prepare("SELECT * FROM usersNTD WHERE usernameNTD = ? AND passwordNTD = ?");
    $stmt->bind_param("ss", $usernameNTD, $passwordNTD);
    
    // Execute the statement
    $stmt->execute();
    
    // Store the result
    $stmt->store_result();

    // Check if username and password exist in the database
    if ($stmt->num_rows > 0) {
       
        $_SESSION['usernameNTD'] = $usernameNTD;
        
        header("Location: trangchuNTD.php");
        exit();
    } else {
        echo "Invalid username or password.";
    }

    // Close statement and connection
    $stmt->close();
}

$conn->close();
?>
