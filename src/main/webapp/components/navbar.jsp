<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>BudgetBuddy</title>
    <link rel="icon" type="image/png" href="images/logo.png">
    <link rel="stylesheet" type="text/css" href="css/navbar.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <!-- Option 1: Include in HTML -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
    <style type="text/css">
        .logo img {
            border-radius: 50%;
            margin-right: 10px;
            height: 50px;
            width: 50px;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg fixed-top " style="background-color: rgb(92, 160, 39);">
    <div class="container-fluid ">
        <div class="logo">
            <img alt="logo" src="images/logo.png">
        </div>
        <a class="navbar-brand text-white" href="#">BudgetBuddy</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasNavbar" aria-controls="offcanvasNavbar" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasNavbar" aria-labelledby="offcanvasNavbarLabel">
            <div class="offcanvas-header">
                <h5 class="offcanvas-title text-white" id="offcanvasNavbarLabel">BudgetBuddy</h5>
                <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
            </div>
            <div class="offcanvas-body">
                <ul class="navbar-nav justify-content-center flex-grow-1 pe-3">
                    <li class="nav-item">
                        <a class="nav-link mx-lg-2 <c:if test="${requestScope.page == 'home'}">active</c:if> text-white" aria-current="page" href="HomeServlet">Accueil</a>
                    </li>
                    
                    <li class="nav-item">
                        <a class="nav-link mx-lg-2 <c:if test="${requestScope.page == 'statistics'}">active</c:if> text-white" href="StatisticsServlet">Liste des Dépenses</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link mx-lg-2 <c:if test="${requestScope.page == 'wallet'}">active</c:if> text-white" href="Portefeuille">Portefeuille</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link mx-lg-2 <c:if test="${requestScope.page == 'profile'}">active</c:if> text-white" href="UpdateProfile">Votre profile</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link mx-lg-2 <c:if test="${requestScope.page == 'apropos'}">active</c:if> text-white" href="AproposServlet">A propos de nous</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</nav>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
