function loader() {
    document.querySelector('.loader-container').classList.add('fade-out');
  
  }
  
  function fadeOut() {
    setInterval(loader, 700);
  }

  window.onload = function () {
    fadeOut();
   
    
  }