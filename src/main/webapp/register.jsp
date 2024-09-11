<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>S'inscrire</title>
  <link rel="icon" type="image/png" href="images/logo.png">
  <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
  <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
  <link rel='stylesheet' href='https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900&display=swap'>
  <link rel="stylesheet" href="css/register.css">
</head>
<body>


<form action="AuthenticationServletRegister" method="post" id="registerForm">
<div class="screen-1">
  <div class="logo" >
    <img src="images/logo.png" alt="logo" />
  </div>
  <div class="email">
    <label for="email">Nom</label>
    <div class="sec-2">
      <ion-icon name="mail-outline"></ion-icon>
      <input type="text" name="nom" placeholder="Votre Nom" required/>
    </div>
  </div>
  <div class="email">
    <label for="email">Prenom</label>
    <div class="sec-2">
      <ion-icon name="mail-outline"></ion-icon>
      <input type="text" name="prenom" placeholder="Votre Prenom" required/>
    </div>
  </div>
  <div class="email">
    <label for="email">Adresse Email</label>
    <div class="sec-2">
      <ion-icon name="mail-outline"></ion-icon>
      <input type="email" name="email" placeholder="Username@gmail.com" required/>
    </div>
  </div>
  <div class="password">
    <label for="password">Mot de passe</label>
    <div class="sec-2">
      <ion-icon name="lock-closed-outline"></ion-icon>
      <input class="pas" type="password" name="password" id="password" placeholder="············" required/>
      <ion-icon class="show-hide" name="eye-outline"></ion-icon>
    </div>
    <ul id="passwordConstraints">
      <li id="length" class="invalid">Au moins 8 caractères</li>
      <li id="uppercase" class="invalid">Au moins une lettre majuscule</li>
      <li id="lowercase" class="invalid">Au moins une lettre minuscule</li>
      <li id="number" class="invalid">Au moins un chiffre</li>
    </ul>
  </div>
  <div class="password">
    <label for="password">Confirmez votre mot de passe</label>
    <div class="sec-2">
      <ion-icon name="lock-closed-outline"></ion-icon>
      <input class="pas" type="password" name="cpassword" id="cpassword" placeholder="············" required/>
      <ion-icon class="show-hide" name="eye-outline"></ion-icon>
    </div>
    <span id="passwordMatch" class="invalid">Les mots de passe ne correspondent pas</span>
  </div>
  <button class="login" type="submit" id="submitButton" disabled>S'inscrire</button>
  <div class="footer"><span>Vous avez déjà un compte ?  <a href="AuthenticationServletLogin">Se connecter</a></span></div>
</div>
</form>

<script>
  document.addEventListener('DOMContentLoaded', function() {
    const password = document.getElementById('password');
    const cpassword = document.getElementById('cpassword');
    const submitButton = document.getElementById('submitButton');
    const passwordMatch = document.getElementById('passwordMatch');
    const passwordConstraints = {
      length: document.getElementById('length'),
      uppercase: document.getElementById('uppercase'),
      lowercase: document.getElementById('lowercase'),
      number: document.getElementById('number')
    };

    function validatePassword() {
      const value = password.value;
      let isValid = true;

      // Validate length
      if (value.length >= 8) {
        passwordConstraints.length.classList.add('valid');
        passwordConstraints.length.classList.remove('invalid');
      } else {
        passwordConstraints.length.classList.add('invalid');
        passwordConstraints.length.classList.remove('valid');
        isValid = false;
      }

      // Validate uppercase letter
      if (/[A-Z]/.test(value)) {
        passwordConstraints.uppercase.classList.add('valid');
        passwordConstraints.uppercase.classList.remove('invalid');
      } else {
        passwordConstraints.uppercase.classList.add('invalid');
        passwordConstraints.uppercase.classList.remove('valid');
        isValid = false;
      }

      // Validate lowercase letter
      if (/[a-z]/.test(value)) {
        passwordConstraints.lowercase.classList.add('valid');
        passwordConstraints.lowercase.classList.remove('invalid');
      } else {
        passwordConstraints.lowercase.classList.add('invalid');
        passwordConstraints.lowercase.classList.remove('valid');
        isValid = false;
      }

      // Validate number
      if (/\d/.test(value)) {
        passwordConstraints.number.classList.add('valid');
        passwordConstraints.number.classList.remove('invalid');
      } else {
        passwordConstraints.number.classList.add('invalid');
        passwordConstraints.number.classList.remove('valid');
        isValid = false;
      }

      return isValid;
    }

    function confirmPassword() {
      if (password.value === cpassword.value) {
        passwordMatch.classList.add('valid');
        passwordMatch.classList.remove('invalid');
        return true;
      } else {
        passwordMatch.classList.add('invalid');
        passwordMatch.classList.remove('valid');
        return false;
      }
    }

    function validateForm() {
      const isPasswordValid = validatePassword();
      const isConfirmPasswordValid = confirmPassword();
      submitButton.disabled = !(isPasswordValid && isConfirmPasswordValid);
    }

    password.addEventListener('input', validateForm);
    cpassword.addEventListener('input', validateForm);
  });
</script>

<style>
  .invalid {
    color: red;
  }
  .valid {
    color: green;
  }
</style>
</body>
</html>
