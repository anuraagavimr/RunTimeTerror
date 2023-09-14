function validateLogin() {
    
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;

   
    if (username.trim() === "" || password.trim() === "") {
      alert("Username and password are required.");
    } else if(password.length<8 || password.length>15){
      alert("Password must be atleast 8 characters and atmost 15 characters");

    }
    else{
      window.location.href='ordermanagement.html?username=' + encodeURIComponent(username);
    }
  }


