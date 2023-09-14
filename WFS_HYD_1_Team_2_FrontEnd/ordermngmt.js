function getUsernameFromURL() {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get("username");
  }

 
  const username = getUsernameFromURL();
  if (username) {
    document.getElementById("usernameDisplay").textContent = "Username: " + username;
  } else {
    document.getElementById("usernameDisplay").textContent = "Username not found.";
  }

  

  