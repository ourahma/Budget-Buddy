<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Se connecter</title>
  <link rel="icon" type="image/png" href="images/logo.png">
  <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
 
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
  <link rel='stylesheet' href='https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&amp;display=swap'>
  <link rel="stylesheet" href="css/login.css">
  <link rel="stylesheet" href="css/custom-alerts.css">
   <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
</head>
<body>

<form action="AuthenticationServletLogin" method="post">
<div class="screen-1">
  <div class="logo" >
    <img src="images/logo.png" alt="logo" />
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
      <input class="pas" type="password" name="password" placeholder="············" required/>
      <ion-icon class="show-hide" name="eye-outline"></ion-icon>
    </div>
  </div>
  <button class="login" type="submit">Se connecter </button>
  <div class="footer"><span>Vous n'avez pas un compte ?  <a href="register.jsp">S'inscrire</a></span></div>
</div>
</form>

<div class="alert-container">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <c:if test="${sessionScope.success != null}">
        <div class="alert alert-success alert-dismissible">
            <strong>Succès !</strong> ${sessionScope.success}
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
        <c:remove var="success" scope="session"/>
    </c:if>
    <c:if test="${sessionScope.error != null}">
        <div class="alert alert-danger alert-dismissible">
            <strong>Erreur !</strong> ${sessionScope.error}
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
        <c:remove var="error" scope="session"/>
    </c:if>
</div>

<script type="text/javascript" src="js/login.js"></script>
<script>
    document.querySelectorAll('.alert .close').forEach(function(button) {
        button.addEventListener('click', function() {
            var alert = this.parentElement;
            alert.style.opacity = '0';
            setTimeout(function() {
                alert.remove();
            }, 300);
        });
    });
</script>
</body>
</html>
