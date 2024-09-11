<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="modele.Budget" %>
<!DOCTYPE html>
<html>
<head>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
<%@ include file="components/navbar.jsp" %>
    <main>
    <div class="container mt-5">
        <div class="row">
            <div class="col-12 col-md-3">
                <h2>Votre portefeuille </h2>
                <canvas id="budgetPieChart" width="150" height="150"></canvas>
            </div>
            <div class="col-12 col-md-5">
                <h4>Ajouter un budget</h4>
                <div class="col-md-4">
                        <div class="card mt-5 add-budget-card">
                            <div class="card-body d-flex align-items-center justify-content-center">
                                <a href="#" class="btn btn-success" data-bs-toggle="modal" 
                                data-bs-target="#addBudgetModal">
                                    <i class="bi bi-plus-lg"></i> Ajouter un budget à votre portefeuille
                                </a>
                            </div>
                        </div>
                    </div>
            </div>
         <div class="col-12 col-md-4">
            <div class="card mt-5">
                <div class="card-body">
                    <h5 class="card-title">Votre devise</h5>
                    <div class="input-group mb-3">
                        <input type="text" class="form-control" placeholder="Devise" aria-label="Devise" 
                        value="${deviseuser.pays }-${deviseuser.annotation }"
                        aria-describedby="change-devise">
                        <div class="input-group-append">
                            <a href="UpdateProfile" id="change-devise" class="input-group-text">Changer</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </div>
    </div>
        <%@ include file="objectif.jsp" %>
        <%@ include file="dette.jsp" %>
       
        
         
    <%-- Modal for adding budget --%>
    <div class="modal fade" id="addBudgetModal" tabindex="-1" aria-labelledby="addBudgetModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="addBudgetModalLabel">Ajouter un Budget</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form action="AjouterBudget" method="post">
                        <div class="mb-3">
                            <label for="nom_budget" class="form-label">Nom Budget:</label>
                            <input type="text" id="nom_budget" name="nom_budget" class="form-control">
                        </div>
                        <div class="mb-3">
                            <label for="devise" class="form-label">Devise:</label>
                            <select name="id_devise" class="form-select">
                                <c:forEach var="devise" items="${requestScope.devises}">
                                    <option value="${devise.id_devise}">${devise.pays} ${devise.annotation}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="montant" class="form-label">Montant:</label>
                            <input type="number" id="montant" name="montant" class="form-control">
                        </div>
                        <div class="mb-3">
                            <label for="periode" class="form-label">Période:</label>
                            <select name="periode" class="form-select">
                                <option value="semaine">Semaine</option>
                                <option value="mois">Mois</option>
                                <option value="trimestre">Trimestre</option>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="categories" class="form-label">Catégories:</label>
                            <%-- Assuming categories are available in request.categories --%>
                            <select name="id_categorie" class="form-select">
                                <c:forEach var="category" items="${requestScope.categories}">
                                    <option value="${category.id_categorie}">${category.nom_categorie}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <input type="hidden" name="id_utilisateur" value="${sessionScope.user.id_utilisateur}">
                        <button type="submit" class="btn btn-primary">Ajouter Budget</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    </main>

    <%
        // Retrieve data from the request object
        List<Budget> budgetData = (List<Budget>) request.getAttribute("budgets");
        
        // Manually create JSON data string
        StringBuilder labels = new StringBuilder("[");
        StringBuilder data = new StringBuilder("[");
        for (int i = 0; i < budgetData.size(); i++) {
            Budget budget = budgetData.get(i);
            labels.append("\"").append(budget.getNom_budget()).append("\"");
            data.append(budget.getMontant());
            if (i < budgetData.size() - 1) {
                labels.append(",");
                data.append(",");
            }
        }
        labels.append("]");
        data.append("]");
    %>
<script type="text/javascript" src="js/wallet.js">></script>
    <script>
        // Retrieve data passed from the servlet
        const labels = <%= labels.toString() %>;
        const data = <%= data.toString() %>;

        // Render the pie chart
        const ctx = document.getElementById('budgetPieChart').getContext('2d');
        const myChart = new Chart(ctx, {
            type: 'pie',
            data: {
                labels: labels,
                datasets: [{
                    label: 'Montant',
                    data: data,
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.5)', 
                        'rgba(54, 162, 235, 0.5)', 
                       
                       
                    ],
                    borderWidth: 1
                }]
            },
            options: {
                title: {
                    display: true,
                    text: 'Distribution DES BUDGETS'
                }
            }
        });
    </script>
</body>
</html>
