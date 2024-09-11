<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="modele.Depense" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<%@ include file="components/navbar.jsp" %>
<main>
<div class="container mt-5">
    <h2 class="mb-4"> <i class="bi bi-calendar-day-fill"></i> Liste des Dépenses de ce mois</h2>
    <div class="row">
        
        <c:forEach var="expense" items="${expenses }">
        <div class="col-md-4">
            <div class="card mb-4">
                <div class="card-body">
                <%--private int id_depense;
	private int id_utilisateur;
	private int id_categorie;
	private int id_budget;
	private double montant;
	private String repetition;
	private LocalDate dateDepense;
	private String nom_categorie; --%>
                    <h5 class="card-title" style="color:rgb(92, 160, 39)">${expense.nom_categorie }</h5>
                    <p class="card-text">
                        <strong style="color:rgb(12, 157, 239);">Date:</strong> ${expense.dateDepense }<br>
                        <strong style="color:rgb(12, 157, 239);">Montant:</strong> ${expense.montant }
                    </p>
                </div>
            </div>
        </div>
        </c:forEach>
     
    </div>
</div>
</main>
<%@ include file="components/footer.jsp" %>
</body>
</html>
