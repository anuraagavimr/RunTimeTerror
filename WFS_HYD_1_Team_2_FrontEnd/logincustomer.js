function validateForm() {
    // Get the username and password input values
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;

    // You can add your validation logic here
    // For a basic example, let's check if both fields are not empty
    if (username.trim() === "" || password.trim() === "") {
      alert("Username and password are required.");
    } else if(password.length<8 || password.length>15){
      alert("Password must be atleast 8 characters and atmost 15 characters");

    }
    else{
      window.location.href='ordermanagementcustomer.html';
    }
  }