<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

</head>
<body>
<%@ include file="components/navbar.jsp" %>
    <div class="container mt-5" style="margin-top: 1000px; " >
    <br><br><br>
        <h4 class="text-center mb-5">� propos de BudgetBuddy</h4>
        <div class="row">
            <!-- Card 1: Gestion des Budgets -->
            <div class="col-md-4">
                <div class="card" style="width: 18rem;">
                    <img src="images/budget.png" class="card-img-top" alt="Gestion des Budgets">
                    <div class="card-body">
                        <h5 class="card-title">Gestion des Budgets</h5>
                        <p class="card-text">Cr�ez et g�rez vos budgets facilement. Suivez vos d�penses et assurez-vous de respecter vos limites financi�res.</p>
                        
                    </div>
                </div>
            </div>
            <!-- Card 2: Suivi des D�penses -->
            <div class="col-md-4">
                <div class="card" style="width: 18rem;">
                    <img src="images/expenses.png" class="card-img-top" alt="Suivi des D�penses">
                    <div class="card-body">
                        <h5 class="card-title">Suivi des D�penses</h5>
                        <p class="card-text">Enregistrez toutes vos d�penses et suivez-les en temps r�el. Analysez vos habitudes de consommation pour mieux les g�rer.</p>
                       
                    </div>
                </div>
            </div>
            <!-- Card 3: Objectifs Financiers -->
            <div class="col-md-4">
                <div class="card" style="width: 18rem;">
                    <img src="images/goals.png" class="card-img-top" alt="Objectifs Financiers">
                    <div class="card-body">
                        <h5 class="card-title">Objectifs Financiers</h5>
                        <p class="card-text">D�finissez et suivez vos objectifs financiers. Que ce soit pour �pargner pour un achat important ou rembourser une dette, nous vous aidons � atteindre vos objectifs.</p>
                        
                    </div>
                </div>
            </div>
        </div>
    </div>
     <%@ include file="components/footer.jsp" %>
    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>