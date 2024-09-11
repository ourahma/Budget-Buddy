<%@ page language="java" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.5.0/font/bootstrap-icons.min.css">
    
</head>
<body>
<main>
    <%@ include file="components/navbar.jsp" %>
    <%@ include file="components/alert.jsp" %>
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h6 class="display-6" style="font-size:20px;"> <i class="bi bi-bullseye"></i> Vos Objectif</h6>
        <button type="button" class="btn btn-success rounded-circle" data-bs-toggle="modal" data-bs-target="#addObjectifModal">
            +
        </button>
    </div>
    <table class="table table-striped depenses-table">
        <thead>
            <tr>
                <th scope="col">Nom Objectif</th>
                <th scope="col">Montant</th>
                <th scope="col">Le reste</th>
                <th scope="col">Date de Limite</th>
                <th scope="col">Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="objectif" items="${objectifs}">
                <tr>
                    <td>${objectif.nom_objectif}</td>
                    <td>${objectif.montant}</td>
                    <td>
                        <c:set var="reste" value="${objectif.montant - objectif.intialAmout}" />
                        ${reste}
                        <div class="progress mt-2" style="height: 20px;">
                            <c:set var="percentage" value="${(objectif.intialAmout * 100) / objectif.montant}" />
                            <div class="progress-bar 
                                <c:choose>
                                    <c:when test="${percentage < 25}">
                                        bg-danger
                                    </c:when>
                                    <c:when test="${percentage < 50}">
                                        bg-warning
                                    </c:when>
                                    <c:when test="${percentage < 75}">
                                        bg-info
                                    </c:when>
                                    <c:otherwise>
                                        bg-success
                                    </c:otherwise>
                                </c:choose>" 
                                role="progressbar" style="width: ${percentage}%;" aria-valuenow="${percentage}" aria-valuemin="0" aria-valuemax="100">
                                ${percentage}%
                            </div>
                        </div>
                    </td>
                    <td>${objectif.date_limite}</td>
                    <td>
                        <input type="hidden" name="id_depense" value="${objectif.id_objectif}" />
                       <button class="btn btn-primary modifier-depense-btn" data-bs-toggle="modal" 
		                    data-bs-target="#modifierObjectifModal"
		                    data-id="${objectif.id_objectif}" 
		                    data-nom="${objectif.nom_objectif}" 
		                    data-montant="${objectif.montant}"
		                    data-initial="${objectif.intialAmout}"
		                    <c:if test="${objectif.completed}">disabled</c:if> 
		                    data-date="${objectif.date_limite}">
		                Modifier
		            </button>
                        <button class="btn btn-danger supprimer-depense-btn" 
                                data-bs-toggle="modal" data-bs-target="#confirmDeleteModal" 
                                data-id="${objectif.id_objectif}">
                            Supprimer
                        </button>
                        <button class="btn btn-warning" data-bs-toggle="modal" 
                                data-bs-target="#depotModal" data-id="${objectif.id_objectif}"
                                data-montant="${objectif.montant}"
                                 <c:if test="${objectif.completed}">disabled</c:if> >
                            <i class="bi bi-currency-exchange"></i> Dépôt
                        </button>
                        <button class="btn btn-secondary" data-bs-toggle="modal" 
                                data-montant="${objectif.montant}" 
                                data-initial="${objectif.intialAmout}"
                                data-bs-target="#retraitModal" data-id="${objectif.id_objectif}"
                                 <c:if test="${objectif.completed}">disabled</c:if> >
                            <i class="bi bi-cash-coin"></i> Retrait
                        </button>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</main>

<!-- Modal for adding objectif -->
<div class="modal fade" id="addObjectifModal" tabindex="-1" aria-labelledby="addObjectifModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="addBudgetModalLabel">Ajouter un Objectif</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="AjouterObjectif" method="post">
                    <div class="mb-3">
                        <label for="nom_objectif" class="form-label">Nom Objectif:</label>
                        <input type="text" id="nom_objectif" name="nom_objectif" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label for="montant" class="form-label">Montant:</label>
                        <input type="number" id="montant" name="montant" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label for="date_limite" class="form-label">La date de limite:</label>
                        <input type="date" id="date_limite" name="date_limite" class="form-control" required>
                    </div>
                    <input type="hidden" name="id_utilisateur" value="${user.id_utilisateur}">
                    <button type="submit" class="btn btn-success">Ajouter Objectif</button>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Modal for deposit -->
<div class="modal fade" id="depotModal" tabindex="-1" aria-labelledby="depotModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="depotModalLabel">Ajouter un Dépôt</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="AjouterDepot" method="post">
                    <div class="mb-3">
                        <label for="montant_depot" class="form-label">Montant:</label>
                        <input type="number" id="montant_depot" name="montant_depot" class="form-control" required>
                    </div>
                    <input type="hidden" id="id_objectif" name="id_objectif">
                    <input type="hidden" id="objectif_montant" name="objectif_montant">
                    <button type="submit" class="btn btn-warning">Ajouter Dépôt</button>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Modal for modifying objectif -->
<div class="modal fade" id="modifierObjectifModal" tabindex="-1" aria-labelledby="modifierObjectifModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="modifierObjectifModalLabel">Modifier Objectif</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id="modifierObjectifForm" action="ModifierObjectif" method="post">
                    <div class="mb-3">
                        <label for="nom_objectif_mod" class="form-label">Nom Objectif:</label>
                        <input type="text" id="nom_objectif_mod" name="nom_objectif" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label for="montant_mod" class="form-label">Montant:</label>
                        <input type="number" id="montant_mod" name="montant" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label for="date_limite_mod" class="form-label">La date de limite:</label>
                        <input type="date" id="date_limite_mod" name="date_limite" class="form-control" required>
                    </div>
                    <input type="hidden" id="id_objectif_mod" name="id_objectif">
                     <input type="hidden" id="initial_amount_mod" name="initial_amount">
                    <input type="hidden" id="current_deposited_mod" name="current_deposited">
                    <div id="error-message" class="text-danger" style="display: none;"></div>
                    <button type="submit" class="btn btn-primary">Modifier Objectif</button>
                </form>
            </div>
        </div>
    </div>
</div>


<!-- Modal for confirming delete -->
<div class="modal fade" id="confirmDeleteModal" tabindex="-1" aria-labelledby="confirmDeleteModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="confirmDeleteModalLabel">Confirmer la Suppression</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <p>Êtes-vous sûr de vouloir supprimer cet objectif ?</p>
                <form action="SupprimerObjectif" method="post">
                    <input type="hidden" id="id_objectif_supp" name="id_objectif">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Annuler</button>
                    <button type="submit" class="btn btn-danger">Supprimer</button>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Modal for withdrawal -->
<div class="modal fade" id="retraitModal" tabindex="-1" aria-labelledby="retraitModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="retraitModalLabel">Ajouter un Retrait</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="AjouterRetrait" method="post">
                    <div class="mb-3">
                        <label for="montant_retrait" class="form-label">Montant:</label>
                        <input type="number" id="montant_retrait" name="montant_retrait" class="form-control" required>
                    </div>
                    <input type="hidden" id="id_objectif_retrait" name="id_objectif">
                    <input type="hidden" id="montant_total" name="montant_total">
                    <input type="hidden" id="montant_initial" name="montant_initial">
                    <button type="submit" class="btn btn-secondary">Ajouter Retrait</button>
                </form>
            </div>
        </div>
    </div>
</div>



</body>
</html>