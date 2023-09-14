document.addEventListener("DOMContentLoaded", function() {
    const aboutUsLink = document.querySelector("#navhover-about");
    const contactLink = document.querySelector("#navhover-contact");
    const aboutUsContent = document.querySelector("#aboutUsContent");
    const contactContent = document.querySelector("#contactContent");

    aboutUsLink.addEventListener("click", function(e) {
      e.preventDefault();
      contactContent.style.display = "none";
      aboutUsContent.style.opacity = "0";
      aboutUsContent.style.display = "block";
      setTimeout(() => {
        aboutUsContent.style.opacity = "1";
      }, 100);
    });

    contactLink.addEventListener("click", function(e) {
      e.preventDefault();
      aboutUsContent.style.display = "none";
      contactContent.style.opacity = "0";
      contactContent.style.display = "block";
      setTimeout(() => {
        contactContent.style.opacity = "1";
      }, 100);
    });
  });




