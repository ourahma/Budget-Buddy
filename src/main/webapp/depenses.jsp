<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="css/depenses.css">
<!-- Add Bootstrap CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<!-- Add Bootstrap JS -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<main class="container">
    <div class="d-flex justify-content-between align-items-center mb-3">
     
        <h6 class="display-6" style="font-size:20px;"> <i class="bi bi-currency-exchange"></i> Vos Dépenses</h6>
       
        <!-- Button to trigger add dépense modal -->
        <button type="button" class="btn btn-success rounded-circle" data-toggle="modal" data-target="#addDepenseModal">
            <i class="bi bi-plus"></i>
        </button>
    </div>
    <table class="table table-striped depenses-table">
        <thead>
            <tr>
                
                <th scope="col">Montant</th>
                <th scope="col">Catégorie</th>
                
                <th scope="col">Actions</th>
            </tr>
        </thead>
       <tbody>
    <c:forEach var="depense" items="${depenses}">
        <tr>
            <td>${depense.montant}</td>
            <td>${depense.nom_categorie}</td>
            <td>
                <input type="hidden" name="id_depense" value="${depense.id_depense}" />
                <button class="btn btn-primary modifier-depense-btn" data-toggle="modal" 
                        data-target="#modifierDepenseModal"
                        data-id="${depense.id_depense}" 
                        data-montant="${depense.montant}" 
                        data-idBudget="${depense.id_budget}" 
                        data-categorie="${depense.id_categorie}">
                    <i class="bi bi-pen-fill"></i> Modifier
                </button>

                <!-- Add a confirmation modal trigger -->
                <button class="btn btn-danger supprimer-depense-btn" 
                        data-toggle="modal" data-target="#confirmDelete" 
                        data-id="${depense.id_depense}" data-user="${user.id_utilisateur}">
                    <i class="bi bi-trash"></i> Supprimer
                </button>
            </td>
        </tr>
    </c:forEach>
</tbody>

    </table>
    
 <!-- Confirmation Modal -->
<div class="modal fade" id="confirmDelete" tabindex="-1" role="dialog" aria-labelledby="confirmDeleteLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="confirmDeleteLabel">Confirmation de Suppression</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                Êtes-vous sûr de vouloir supprimer cette dépense ?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Annuler</button>
                <form action="SupprimerDepense" method="post" style="display:inline;">
                    <input type="hidden" id="deleteDepenseId" name="id_depense" value="" />
                    <input type="hidden" id="deleteUserId" name="id_utilisateur" value="" />
                    <button type="submit" class="btn btn-danger">Confirmer</button>
                </form>
            </div>
        </div>
    </div>
</div>

    
</main>
<!-- modifier dépense modal -->
<div class="modal fade" id="modifierDepenseModal" tabindex="-1" role="dialog" aria-labelledby="ModifierDepenseModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="ModifierDepenseModalLabel">Modifier une dépense</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form action="ModifierDepense" method="post">
            <div class="modal-body">
                
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
                            <label for="budget" class="form-label">Budget:</label>
                            <%-- Assuming categories are available in request.categories --%>
                            <select name="budgetId" class="form-select">
                                <c:forEach var="budget" items="${budgets}">
                                    <option value="${budget.id_budget}">${budget.nom_budget}</option>
                                </c:forEach>
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
                            <input type="hidden" id="depenseId" name="id_depense" value="">
                        </div>
            </div>
            			<div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
                <button type="submit" class="btn btn-success" >Modifier Dépense</button>
            			</div>
                </form>
        </div>
    </div>
</div>
<!-- Ajouter une dépense modal -->
<div class="modal fade" id="addDepenseModal" tabindex="-1" role="dialog" aria-labelledby="addDepenseModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="addDepenseModalLabel">Ajouter une dépense</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                
                <form action="AjouterDepenseServlet" method="post">
                    
                    <input type="hidden" name="id_utilisateur" value="${user.id_utilisateur}" />
                    <div class="form-group">
                        <label for="montant">Montant: </label>
                        <input type="number" class="form-control" id="montant" name="montant" required>
                    </div>
                    <div class="mb-3">
                            <label for="repetition" class="form-label">	Répetition:</label>
                            
                            <select name="repetition" class="form-select">
                                
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
                    <div class="mb-3">
                            <label for="budgets" class="form-label">Choisissez quel budget:</label>
                            <%-- Assuming categories are available in request.categories --%>
                            <select name="id_budget" class="form-select">
                                <c:forEach var="budget" items="${requestScope.budgets}">
                                    <option value="${budget.id_budget}">${budget.nom_budget}</option>
                                </c:forEach>
                            </select>
                        </div>
                
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
                <button type="submit" class="btn btn-primary">Ajouter</button>
            </div>
            </form>
            </div>
            
        </div>
    </div>
</div>
<script>
    $(document).ready(function() {
        $('.modifier-depense-btn').click(function() {
            var depenseId = $(this).data('id');
            var montant = $(this).data('montant');
            var categorieId = $(this).data('categorie');
            var idBudget=$(this).data('idBbudget');
            $('#budgetId').val(idBudget);
            $('#depenseId').val(depenseId);
            $('#montant').val(montant);
            $('#categorie').val(categorieId);
        });

        $('.supprimer-depense-btn').click(function() {
            var depenseId = $(this).data('id');
            var userId = $(this).data('user');

            $('#deleteDepenseId').val(depenseId);
            $('#deleteUserId').val(userId);
        });
    });
</script>


</body>

</html>
