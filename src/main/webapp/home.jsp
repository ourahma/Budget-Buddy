<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="css/home.css">
<link rel="icon" href="images/favicon.png" type="image/x-icon">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.5.0/font/bootstrap-icons.min.css">
</head>
<body>
<%@ include file="components/alert.jsp" %>
<%@ include file="components/navbar.jsp" %>
<main class="container">
    <div class="row">
        <p>Bonjour, ${user.nom} <c:out value="${user.prenom}"/></p>
        Votre devise: ${deviseuser.annotation}
        <c:choose>
            <c:when test="${empty budgets}">
    <div class="col-12 col-md-6 offset-md-3">
        <div class="card mt-5">
            <div class="card-body">
                <h6 class="display-6" style="font-size:20px;">Ajouter un budget pour commencer</h6>
                <form action="AjouterBudget" method="post">
                    <div class="mb-3">
                        <label for="nom_budget" class="form-label">Nom Budget:</label>
                        <input type="text" id="nom_budget" name="nom_budget" class="form-control">
                    </div>
                    <div class="mb-3">
                        <label for="devise" class="form-label">Devise: (elle sera votre devise par default)</label>
                        <select name="id_devise" class="form-select">
                            <c:forEach var="devise" items="${devises}">
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
                        <select name="id_categorie" class="form-select">
                            <c:forEach var="category" items="${categories}">
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
</c:when>

            <c:otherwise>
                <%-- Display existing budgets --%>
                <div class="row">
                    <h6 class="display-6" style="font-size:20px;">
                        <i class="bi bi-wallet2" style="color:black;"></i>
                        Vos Budgets
                    </h6>
                    <c:forEach var="budget" items="${budgets}">
                        <div class="col-md-4">
                            <div class="card mt-5 budget-card">
                                <div class="card-body">
                                   
                                    <div class="content">
                                        <h5 class="card-title" style="color:white;">${budget.nom_budget}</h5>
                                        
                                        <p class="card-text" style="color:white;">Le reste ${budget.remaining_montant}</p>
                                        
                                        
                                            
                                             <button class="btn btn-primary" data-bs-toggle="modal" 
                                             data-bs-target="#modifyBudgetModal" 
                                                onclick="populateModifyBudgetModal(${budget.id_budget}, '${budget.nom_budget}', ${budget.montant}, '${sessionScope.user.id_devise}', '${budget.periode}', '${budget.id_categorie}')">
                                            <i class="bi bi-pen-fill"></i> Modifier budget
                                        </button>
                                        
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    <div class="col-md-4">
                        <div class="card mt-5 add-budget-card">
                            <div class="card-body d-flex align-items-center justify-content-center">
                                <a href="#" class="btn btn-outline-primary" data-bs-toggle="modal" 
                                data-bs-target="#addBudgetModal">
                                    <i class="bi bi-plus-lg"></i> 
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
    </div>

    <%-- Include the dépenses page --%>
    <jsp:include page="depenses.jsp" />
    
    <%-- Modal for adding budget --%>
    <div class="modal fade" id="addBudgetModal" tabindex="-1" aria-labelledby="addBudgetModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="addBudgetModalLabel">Ajouter un Budget </h5>
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
                                <c:forEach var="devise" items="${devises}">
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
                                <c:forEach var="category" items="${categories}">
                                    <option value="${category.id_categorie}">${category.nom_categorie}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <input type="hidden" name="id_utilisateur" value="${user.id_utilisateur}">
                        <button type="submit" class="btn btn-primary">Ajouter Budget</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
<%-- Modal for modifying budget --%>
    <div class="modal fade" id="modifyBudgetModal" tabindex="-1" aria-labelledby="modifyBudgetModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="modifyBudgetModalLabel">Modifier le Budget</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form id="modifyBudgetForm" action="ModifierBudget" method="post">
                        <input type="hidden" id="modify_id_budget" name="id_budget">
                        <div class="mb-3">
                            <label for="modify_nom_budget" class="form-label">Nom Budget:</label>
                            <input type="text" id="modify_nom_budget" name="nom_budget" class="form-control">
                        </div>
                        <div class="mb-3">
                            <label for="modify_devise" class="form-label">Devise:</label>
                            <select id="modify_devise" name="id_devise" class="form-select">
                                <c:forEach var="devise" items="${devises}">
                                    <option value="${devise.id_devise}">${devise.pays} ${devise.annotation}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="modify_montant" class="form-label">Montant:</label>
                            <input type="number" id="modify_montant" name="montant" class="form-control">
                        </div>
                        <div class="mb-3">
                            <label for="modify_periode" class="form-label">Période:</label>
                            <select id="modify_periode" name="periode" class="form-select">
                                <option value="semaine">Semaine</option>
                                <option value="mois">Mois</option>
                                <option value="trimestre">Trimestre</option>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="modify_categories" class="form-label">Catégories:</label>
                            <select id="modify_categories" name="id_categorie" class="form-select">
                                <c:forEach var="category" items="${categories}">
                                    <option value="${category.id_categorie}">${category.nom_categorie}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <input type="hidden" name="id_utilisateur" value="${sessionScope.user.id_utilisateur}">
                        <button type="submit" class="btn btn-primary">Modifier Budget</button>
                        <button type="button" class="btn btn-danger mt-2" data-bs-toggle="modal" data-bs-target="#deleteConfirmModal">
                        Supprimer
                    </button>
                    </form>
                    
                </div>
            </div>
        </div>
    </div>

    <%-- Modal for confirming deletion --%>
    <div class="modal fade" id="deleteConfirmModal" tabindex="-1" aria-labelledby="deleteConfirmModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="deleteConfirmModalLabel">Confirmer la Suppression</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <p>Êtes-vous sûr de vouloir supprimer ce budget?</p>
                    <form id="deleteBudgetForm" action="SupprimerBudget" method="get">
                        <input type="hidden" id="delete_id_budget" name="id_budget">
                        <button type="submit" class="btn btn-danger">Confirmer</button>
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Annuler</button>
                    </form>
                    
                </div>
            </div>
        </div>
    </div>
</main>
<%@ include file="components/footer.jsp" %>
<script>
    function populateModifyBudgetModal(id, name, amount, devise, periode, categorie) {
        $('#modify_id_budget').val(id);
        $('#modify_nom_budget').val(name);
        $('#modify_montant').val(amount);
        $('#modify_devise').val(devise);
        $('#modify_periode').val(periode);
        $('#modify_categories').val(categorie);
        $('#delete_id_budget').val(id);
    }
</script>
</body>
</html>
