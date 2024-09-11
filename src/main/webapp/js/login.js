document.addEventListener('DOMContentLoaded', function() {
      const showHideIcon = document.querySelector('.show-hide');
      const passwordInput = document.querySelector('.password input');

      showHideIcon.addEventListener('click', function() {
        if (passwordInput.type === 'password') {
          passwordInput.type = 'text';
          showHideIcon.name = 'eye-off-outline';
        } else {
          passwordInput.type = 'password';
          showHideIcon.name = 'eye-outline';
        }
      });
    });/**
 * 
 */