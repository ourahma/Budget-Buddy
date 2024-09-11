<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home - Money Management</title>

<style type="text/css">

body {
    font-family: 'Poppins', sans-serif;
    background-color: #f7f9fc;
    color: #333;
    margin: 0;
    padding: 0;
}

.container {
    max-width: 1200px;
    margin: auto;
    padding: 20px;
}

h1 {
    text-align: center;
    margin-bottom: 20px;
}

.setup-guide, .features-overview, .action-items, .financial-snapshot, .tips-tutorials {
    margin-bottom: 20px;
}

h2 {
    border-bottom: 2px solid #333;
    padding-bottom: 10px;
    margin-bottom: 10px;
}

ul {
    list-style-type: none;
    padding: 0;
}

ul li {
    margin-bottom: 10px;
}

a {
    text-decoration: none;
    color: #007bff;
}

a:hover {
    text-decoration: underline;
}

button {
    background-color: #007bff;
    color: white;
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}

button:hover {
    background-color: #0056b3;
}

</style>
</head>
<body>


<div class="container">
    <h1>Bienvenue, <c:out value="${sessionScope.user.nom}"/> <c:out value="${sessionScope.user.prenom}"/>!</h1>
    <p>Prêt à commencer à gérer vos finances? Voici comment commencer:</p>
    
    <div class="setup-guide">
        <h2>Guide de configuration rapide</h2>
        <ul>
            <li><a href="createBudget.jsp">Créer votre premier budget</a></li>
            <li><a href="addGoal.jsp">Ajouter vos objectifs financiers</a></li>
            <li><a href="linkAccount.jsp">Lier vos comptes bancaires</a></li>
            <li><a href="addInitialBalance.jsp">Ajouter un solde initial</a></li>
        </ul>
    </div>
    
    <div class="features-overview">
        <h2>Principales fonctionnalités</h2>
        <ul>
            <li><a href="manageBudgets.jsp">Gestion des budgets</a></li>
            <li><a href="trackExpenses.jsp">Suivi des dépenses</a></li>
            <li><a href="financialGoals.jsp">Objectifs financiers</a></li>
            <li><a href="manageDebts.jsp">Gestion des dettes</a></li>
            <li><a href="reports.jsp">Rapports et analyses</a></li>
        </ul>
    </div>
    
    <div class="action-items">
        <h2>Actions rapides</h2>
        <button onclick="location.href='createBudget.jsp'">Créer un budget</button>
        <button onclick="location.href='addExpense.jsp'">Ajouter une dépense</button>
        <button onclick="location.href='setGoal.jsp'">Définir un objectif</button>
        <button onclick="location.href='addDebt.jsp'">Ajouter une dette</button>
    </div>
    
    <div class="financial-snapshot">
        <h2>Vue d'ensemble financière</h2>
        <p>Commencez à remplir les sections ci-dessus pour voir un aperçu de vos finances ici.</p>
        <!-- This section will dynamically display financial data as the user inputs information -->
    </div>
    
    <div class="tips-tutorials">
        <h2>Conseils et tutoriels</h2>
        <ul>
            <li><a href="tutorial.jsp">Comment utiliser l'application</a></li>
            <li><a href="tips.jsp">Conseils pour la gestion financière</a></li>
            <li><a href="faq.jsp">FAQ</a></li>
        </ul>
    </div>
</div>

</body>
</html>
