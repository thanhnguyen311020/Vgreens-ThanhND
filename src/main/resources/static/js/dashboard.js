const shrink_btn = document.querySelector(".shrink-btn");
const search = document.querySelector(".search");
const sidebar_links = document.querySelectorAll(".sidebar-links a");
const active_tab = document.querySelector(".active-tab");
const shortcuts = document.querySelector(".sidebar-links h4");
const tooltip_elements = document.querySelectorAll(".tooltip-element");

let activeIndex;

shrink_btn.addEventListener("click", () => {
  document.body.classList.toggle("shrink");
  setTimeout(moveActiveTab, 400);

  shrink_btn.classList.add("hovered");

  setTimeout(() => {
    shrink_btn.classList.remove("hovered");
  }, 500);
});

search.addEventListener("click", () => {
  document.body.classList.remove("shrink");
  search.lastElementChild.focus();
});

// function moveActiveTab() {
//   let topPosition = activeIndex * 58 + 3;

//   if (activeIndex > 10) {
//     topPosition += shortcuts.clientHeight + 20;
//   }


//   active_tab.style.top = `${topPosition}px`;
// }


function changeLink() {
  sidebar_links.forEach((sideLink) => sideLink.classList.remove("active"));
  this.classList.add("active");

  activeIndex = this.dataset.active;

  moveActiveTab();
}

sidebar_links.forEach((link) => link.addEventListener("click", changeLink));

function showTooltip() {
  let tooltip = this.parentNode.lastElementChild;
  let spans = tooltip.children;
  let tooltipIndex = this.dataset.tooltip;

  Array.from(spans).forEach((sp) => sp.classList.remove("show"));
  spans[tooltipIndex].classList.add("show");

  tooltip.style.top = `${(100 / (spans.length * 2)) * (tooltipIndex * 2 + 1)}%`;
}

tooltip_elements.forEach((elem) => {
  elem.addEventListener("mouseover", showTooltip);
});



// window.onload = function () {
//   var dropdonw_control = document.getElementsByClassName("droplistbtn");
//   var dropdownsx = document.getElementsByClassName("dropdown-table");
//   console.log("dropmenu count:", dropdonw_control.length)
//   var j;
//   for (j = 0; j < dropdonw_control.length; j++) {
//     dropdonw_control[j].addEventListener("click", function () {
//       var dropdownContent = this.nextElementSibling;
//       console.log(" drop menu show")
//       dropdownContent.classList.toggle("show");
//     })
//   }




// window.onclick = function (event) {
//   if (!event.target.matches('.droplistbtn')) {

//     var i;
//     for (i = 0; i < dropdownsx.length; i++) {
//       var openDropdown = dropdownsx[i];
//       if (openDropdown.classList.contains('show')) {
//         openDropdown.classList.remove('show');
//       }
//     }
//   }
// }

// }

// Load when page request
function loader() {
  document.querySelector('.loader-container').classList.add('fade-out');

}

function fadeOut() {
  setInterval(loader, 500);
}

// $(function () {

//   })


window.onload = function () {
  fadeOut();
  var active = parseInt($('#checkActive').val())

  switch (active) {
    case 0:
      $('#dashboard').css('background-color', 'var(--dark-color)')
      $('#dashboard').css('color', 'white')
      $('#dashboard i').css('color', 'white')
      break;
    case 1:
      $('#order').css('background-color', 'var(--dark-color)')
      $('#order').css('color', 'white')
      $('#order i').css('color', 'white')
      break;
    case 2:
      $('#product').css('background-color', 'var(--dark-color)')
      $('#product').css('color', 'white')
      $('#product i').css('color', 'white')
      break;
    case 3:
      $('#account').css('background-color', 'var(--dark-color)')
      $('#account').css('color', 'white')
      $('#account i').css('color', 'white')
      break;
    case 5:
      $('#supplier').css('background-color', 'var(--dark-color)')
      $('#supplier').css('color', 'white')
      $('#supplier i').css('color', 'white')
      break;
    case 6:
      $('#authority').css('background-color', 'var(--dark-color)')
      $('#authority').css('color', 'white')
      $('#authority i').css('color', 'white')
      break;
    default: $('#orderTrack').css('background-color', 'var(--dark-color)');
             $('#orderTrack').css('color', 'white');
             $('#orderTrack i').css('color', 'white');
    // code block
  }

  function changeActive(id) {
    $(id).css('background-color', 'var(--dark-color)')
    $(id).css('color', 'white')
    $(id).css('color', 'white')
  }
  // if (active == 0) {
  //   console.log("ok")
  //   $('#dashboard').css('background-color', 'var(--dark-color)')
  //   $('#dashboard').css('color', 'white')
  //   $('#dashboard i').css('color', 'white')
  // } else {
  //   $('#order').css('background-color', 'var(--dark-color)')
  //   $('#order').css('color', 'white')
  //   $('#order i').css('color', 'white')
  // }


  $("#modalContent li .text").click(function () {
    if (!$('#productList').is(':empty')) {
      $(this).parent().addClass('selected').siblings().removeClass('selected');
    }
  });

}
// console.log("Check:", !$('#productList').is(':empty'))
