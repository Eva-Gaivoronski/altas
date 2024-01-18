function expandUserMenu() {
    document.getElementById("userDropdownOptions").classList.toggle("show-dd-options");
}

window.onclick = function(event) {
  if (!event.target.matches('.user-dropdown-button')) {
    let dropdowns = document.getElementsByClassName("user-dropdown-options");
    for (let i = 0; i < dropdowns.length; i++) {
      let openDropdown = dropdowns[i];
      if (openDropdown.classList.contains('show-dd-options')) {
        openDropdown.classList.remove('show-dd-options');
      }
    }
  }
}