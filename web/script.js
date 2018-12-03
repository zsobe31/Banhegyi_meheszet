
// NAVBAR - amikor a felhasználó a dokumentumot legörgeti 400px-t, 
// a navigációs sáv animáltan előjön a lap tetején.
// When the user scrolls down 400px from the top of the document, slide down the navbar
window.onscroll = function() {scrollFunction()};

function scrollFunction() {
    if (document.body.scrollTop > 400 || document.documentElement.scrollTop > 400) {
        document.getElementById("navbar").style.top = "0";
    } else {
        document.getElementById("navbar").style.top = "-70px";
    }
}

// NAVBAR - a navigációs sáv elemeire kattintva az oldal gördül a megadott elemre/scrolling function
$(document).ready(function(){
  $('body').scrollspy({target: ".navbar", offset: 50});   

  $("#myNavbar a").on('click', function(event) {
    if (this.hash !== "") {
      event.preventDefault();

      var hash = this.hash;

      $('html, body').animate({
        scrollTop: $(hash).offset().top
      }, 800, function(){
   
        window.location.hash = hash;
      });
    }  
  });
});



