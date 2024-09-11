document.addEventListener("DOMContentLoaded", function() {
    var updateBtn = document.getElementById("updateBtn");
    var password = document.getElementById("mdps");
    var confirmPassword = document.getElementById("confirm-mdps");

    // Password constraints regular expression
    var passwordRegex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*]).{8,}$/;

    if (updateBtn) {
        updateBtn.addEventListener("click", function(event) {
            if (!passwordRegex.test(password.value)) {
                event.preventDefault(); // Prevent form submission
                alert("Le mot de passe doit contenir au moins 8 caractères, incluant au moins une minuscule, une majuscule, un chiffre et un caractère spécial.");
            } else if (password.value !== confirmPassword.value) {
                event.preventDefault(); // Prevent form submission
                alert("Les mots de passe ne correspondent pas.");
            }
        });
    }

    var saveDeviseBtn = document.getElementById("saveDeviseBtn");
    if (saveDeviseBtn) {
        saveDeviseBtn.addEventListener("click", function() {
            var selectedDevise = document.getElementById("devise").value;
            document.querySelector('input[aria-label="Devise"]').value = selectedDevise;
            var modal = bootstrap.Modal.getInstance(document.getElementById('changeDeviseModal'));
            if (modal) {
                modal.hide();
            }
        });
    }
});
